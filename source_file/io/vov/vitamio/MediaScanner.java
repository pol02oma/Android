package io.vov.vitamio;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import io.vov.vitamio.provider.MediaStore.Video.Media;
import io.vov.vitamio.utils.ContextUtils;
import io.vov.vitamio.utils.FileUtils;
import io.vov.vitamio.utils.Log;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class MediaScanner
{
  private static final int DATE_MODIFIED_VIDEO_COLUMN_INDEX = 2;
  private static final int ID_VIDEO_COLUMN_INDEX = 0;
  private static final int PATH_VIDEO_COLUMN_INDEX = 1;
  private static final String[] VIDEO_PROJECTION = { "_id", "_data", "date_modified" };
  private boolean mCaseInsensitivePaths;
  private MyMediaScannerClient mClient = new MyMediaScannerClient(null);
  private Context mContext;
  private HashMap<String, FileCacheEntry> mFileCache;
  private ContentProviderClient mProvider;

  static
  {
    String str = Vitamio.getLibraryPath();
    Log.i("LIB ROOT: %s", new Object[] { str });
    System.load(str + "libstlport_shared.so");
    System.load(str + "libvscanner.so");
    loadFFmpeg_native(str + "libffmpeg.so");
  }

  public MediaScanner(Context paramContext)
  {
    this.mContext = paramContext;
    native_init(this.mClient);
  }

  private boolean inScanDirectory(String paramString, String[] paramArrayOfString)
  {
    for (int i = 0; i < paramArrayOfString.length; i++)
      if (paramString.startsWith(paramArrayOfString[i]))
        return true;
    return false;
  }

  private void initialize()
  {
    this.mCaseInsensitivePaths = true;
  }

  private static native boolean loadFFmpeg_native(String paramString);

  private final native void native_finalize();

  private final native void native_init(MediaScannerClient paramMediaScannerClient);

  private void postscan(String[] paramArrayOfString)
    throws RemoteException
  {
    Iterator localIterator = this.mFileCache.values().iterator();
    while (localIterator.hasNext())
    {
      FileCacheEntry localFileCacheEntry = (FileCacheEntry)localIterator.next();
      String str = localFileCacheEntry.mPath;
      if ((localFileCacheEntry.mSeenInFileSystem) || (!inScanDirectory(str, paramArrayOfString)) || (new File(str).exists()))
        continue;
      this.mProvider.delete(ContentUris.withAppendedId(localFileCacheEntry.mTableUri, localFileCacheEntry.mRowId), null, null);
      localIterator.remove();
    }
    this.mFileCache.clear();
    this.mFileCache = null;
    this.mProvider.release();
    this.mProvider = null;
  }

  private void prescan(String paramString)
    throws RemoteException
  {
    this.mProvider = this.mContext.getContentResolver().acquireContentProviderClient("me.abitno.vplayer.mediaprovider");
    Object localObject1 = null;
    String str1;
    String[] arrayOfString;
    if (this.mFileCache == null)
    {
      this.mFileCache = new HashMap();
      str1 = null;
      arrayOfString = null;
      if (paramString != null)
        str1 = "_data=?";
    }
    label275: label282: label290: 
    do
    {
      while (true)
      {
        String str3;
        try
        {
          while (true)
          {
            arrayOfString = new String[] { paramString };
            Cursor localCursor = this.mProvider.query(MediaStore.Video.Media.CONTENT_URI, VIDEO_PROJECTION, str1, arrayOfString, null);
            localObject1 = localCursor;
            if (localObject1 == null)
              break label290;
            try
            {
              while (true)
              {
                if (!localObject1.moveToNext())
                  break label282;
                long l1 = localObject1.getLong(0);
                String str2 = localObject1.getString(1);
                long l2 = localObject1.getLong(2);
                if (!str2.startsWith("/"))
                  continue;
                File localFile = new File(str2);
                if ((!TextUtils.isEmpty(paramString)) && (!localFile.exists()))
                {
                  this.mProvider.delete(MediaStore.Video.Media.CONTENT_URI, str1, arrayOfString);
                  localObject1.close();
                  if (0 != 0)
                    null.close();
                  return;
                  this.mFileCache.clear();
                  break;
                }
                str3 = FileUtils.getCanonical(localFile);
                if (!this.mCaseInsensitivePaths)
                  break label275;
                str4 = str3.toLowerCase();
                this.mFileCache.put(str4, new FileCacheEntry(MediaStore.Video.Media.CONTENT_URI, l1, str3, l2));
              }
            }
            finally
            {
              localObject1.close();
              localObject1 = null;
            }
          }
        }
        finally
        {
          if (localObject1 == null)
            continue;
          localObject1.close();
        }
        String str4 = str3;
      }
      localObject1.close();
      localObject1 = null;
    }
    while (localObject1 == null);
    localObject1.close();
  }

  private native void processDirectory(String paramString1, String paramString2);

  private native boolean processFile(String paramString1, String paramString2);

  protected void finalize()
    throws Throwable
  {
    try
    {
      native_finalize();
      return;
    }
    finally
    {
      super.finalize();
    }
    throw localObject;
  }

  public native void release();

  public void scanDirectories(String[] paramArrayOfString)
  {
    while (true)
    {
      int i;
      try
      {
        long l1 = System.currentTimeMillis();
        prescan(null);
        long l2 = System.currentTimeMillis();
        i = 0;
        if (i >= paramArrayOfString.length)
          continue;
        if (!TextUtils.isEmpty(paramArrayOfString[i]))
        {
          paramArrayOfString[i] = ContextUtils.fixLastSlash(paramArrayOfString[i]);
          processDirectory(paramArrayOfString[i], MediaFile.sFileExtensions);
          break label209;
          long l3 = System.currentTimeMillis();
          postscan(paramArrayOfString);
          long l4 = System.currentTimeMillis();
          Object[] arrayOfObject1 = new Object[1];
          arrayOfObject1[0] = Long.valueOf(l2 - l1);
          Log.d(" prescan time: %dms", arrayOfObject1);
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = Long.valueOf(l3 - l2);
          Log.d("    scan time: %dms", arrayOfObject2);
          Object[] arrayOfObject3 = new Object[1];
          arrayOfObject3[0] = Long.valueOf(l4 - l3);
          Log.d("postscan time: %dms", arrayOfObject3);
          Object[] arrayOfObject4 = new Object[1];
          arrayOfObject4[0] = Long.valueOf(l4 - l1);
          Log.d("   total time: %dms", arrayOfObject4);
          return;
        }
      }
      catch (SQLException localSQLException)
      {
        Log.e("SQLException in MediaScanner.scan()", localSQLException);
        return;
      }
      catch (UnsupportedOperationException localUnsupportedOperationException)
      {
        Log.e("UnsupportedOperationException in MediaScanner.scan()", localUnsupportedOperationException);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("RemoteException in MediaScanner.scan()", localRemoteException);
        return;
      }
      label209: i++;
    }
  }

  public Uri scanSingleFile(String paramString1, String paramString2)
  {
    try
    {
      prescan(paramString1);
      File localFile = new File(paramString1);
      long l = localFile.lastModified() / 1000L;
      Uri localUri = this.mClient.doScanFile(paramString1, l, localFile.length(), true);
      return localUri;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("RemoteException in MediaScanner.scanFile()", localRemoteException);
    }
    return null;
  }

  private static class FileCacheEntry
  {
    long mLastModified;
    boolean mLastModifiedChanged;
    String mPath;
    long mRowId;
    boolean mSeenInFileSystem;
    Uri mTableUri;

    FileCacheEntry(Uri paramUri, long paramLong1, String paramString, long paramLong2)
    {
      this.mTableUri = paramUri;
      this.mRowId = paramLong1;
      this.mPath = paramString;
      this.mLastModified = paramLong2;
      this.mSeenInFileSystem = false;
      this.mLastModifiedChanged = false;
    }

    public String toString()
    {
      return this.mPath;
    }
  }

  private class MyMediaScannerClient
    implements MediaScannerClient
  {
    private String mAlbum;
    private String mArtist;
    private long mDuration;
    private long mFileSize;
    private int mFileType;
    private int mHeight;
    private String mLanguage;
    private long mLastModified;
    private String mMimeType;
    private String mPath;
    private String mTitle;
    private int mWidth;

    private MyMediaScannerClient()
    {
    }

    private Uri endFile(MediaScanner.FileCacheEntry paramFileCacheEntry)
      throws RemoteException
    {
      int i;
      if ((MediaFile.isVideoFileType(this.mFileType)) && (this.mWidth > 0) && (this.mHeight > 0))
        i = 1;
      Uri localUri1;
      ContentValues localContentValues;
      long l;
      while (true)
        if (i != 0)
        {
          localUri1 = MediaStore.Video.Media.CONTENT_URI;
          paramFileCacheEntry.mTableUri = localUri1;
          localContentValues = toValues();
          if (TextUtils.isEmpty(localContentValues.getAsString("title")))
          {
            String str = localContentValues.getAsString("_data");
            int j = str.lastIndexOf('/');
            if (j >= 0)
            {
              int m = j + 1;
              if (m < str.length())
                str = str.substring(m);
            }
            int k = str.lastIndexOf('.');
            if (k > 0)
              str = str.substring(0, k);
            localContentValues.put("title", str);
          }
          l = paramFileCacheEntry.mRowId;
          if (l != 0L)
            break;
          Uri localUri3 = MediaScanner.this.mProvider.insert(localUri1, localContentValues);
          if (localUri3 != null)
            paramFileCacheEntry.mRowId = ContentUris.parseId(localUri3);
          return localUri3;
          i = 0;
          continue;
        }
        else
        {
          return null;
        }
      Uri localUri2 = ContentUris.withAppendedId(localUri1, l);
      MediaScanner.this.mProvider.update(localUri2, localContentValues, null, null);
      return localUri2;
    }

    private int parseSubstring(String paramString, int paramInt1, int paramInt2)
    {
      int i = paramString.length();
      if (paramInt1 == i)
        return paramInt2;
      int j = paramInt1 + 1;
      int k = paramString.charAt(paramInt1);
      if ((k < 48) || (k > 57))
        return paramInt2;
      int m = k - 48;
      while (j < i)
      {
        int n = j + 1;
        int i1 = paramString.charAt(j);
        if ((i1 < 48) || (i1 > 57))
          return m;
        m = m * 10 + (i1 - 48);
        j = n;
      }
      return m;
    }

    private ContentValues toValues()
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("_data", this.mPath);
      localContentValues.put("date_modified", Long.valueOf(this.mLastModified));
      localContentValues.put("_size", Long.valueOf(this.mFileSize));
      localContentValues.put("mime_type", this.mMimeType);
      localContentValues.put("title", this.mTitle);
      if (MediaFile.isVideoFileType(this.mFileType))
      {
        localContentValues.put("duration", Long.valueOf(this.mDuration));
        localContentValues.put("language", this.mLanguage);
        localContentValues.put("album", this.mAlbum);
        localContentValues.put("artist", this.mArtist);
        localContentValues.put("width", Integer.valueOf(this.mWidth));
        localContentValues.put("height", Integer.valueOf(this.mHeight));
      }
      return localContentValues;
    }

    public void addNoMediaFolder(String paramString)
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("_data", "");
      String[] arrayOfString = new String[1];
      arrayOfString[0] = (paramString + '%');
      try
      {
        MediaScanner.this.mProvider.update(MediaStore.Video.Media.CONTENT_URI, localContentValues, "_data LIKE ?", arrayOfString);
        return;
      }
      catch (RemoteException localRemoteException)
      {
      }
      throw new RuntimeException();
    }

    public MediaScanner.FileCacheEntry beginFile(String paramString, long paramLong1, long paramLong2)
    {
      int i = paramString.lastIndexOf('/');
      if ((i >= 0) && (i + 2 < paramString.length()))
      {
        if (paramString.regionMatches(i + 1, "._", 0, 2))
          return null;
        if (paramString.regionMatches(true, -4 + paramString.length(), ".jpg", 0, 4))
        {
          if ((paramString.regionMatches(true, i + 1, "AlbumArt_{", 0, 10)) || (paramString.regionMatches(true, i + 1, "AlbumArt.", 0, 9)))
            return null;
          int j = -1 + (paramString.length() - i);
          if (((j == 17) && (paramString.regionMatches(true, i + 1, "AlbumArtSmall", 0, 13))) || ((j == 10) && (paramString.regionMatches(true, i + 1, "Folder", 0, 6))))
            return null;
        }
      }
      MediaFile.MediaFileType localMediaFileType = MediaFile.getFileType(paramString);
      if (localMediaFileType != null)
      {
        this.mFileType = localMediaFileType.fileType;
        this.mMimeType = localMediaFileType.mimeType;
      }
      String str = FileUtils.getCanonical(new File(paramString));
      if (MediaScanner.this.mCaseInsensitivePaths)
        str = paramString.toLowerCase();
      MediaScanner.FileCacheEntry localFileCacheEntry = (MediaScanner.FileCacheEntry)MediaScanner.this.mFileCache.get(str);
      if (localFileCacheEntry == null)
      {
        localFileCacheEntry = new MediaScanner.FileCacheEntry(null, 0L, paramString, 0L);
        MediaScanner.this.mFileCache.put(str, localFileCacheEntry);
      }
      localFileCacheEntry.mSeenInFileSystem = true;
      long l = paramLong1 - localFileCacheEntry.mLastModified;
      if ((l > 1L) || (l < -1L))
      {
        localFileCacheEntry.mLastModified = paramLong1;
        localFileCacheEntry.mLastModifiedChanged = true;
      }
      this.mPath = paramString;
      this.mLastModified = paramLong1;
      this.mFileSize = paramLong2;
      this.mTitle = null;
      this.mDuration = 0L;
      return localFileCacheEntry;
    }

    public Uri doScanFile(String paramString, long paramLong1, long paramLong2, boolean paramBoolean)
    {
      try
      {
        MediaScanner.FileCacheEntry localFileCacheEntry = beginFile(paramString, paramLong1, paramLong2);
        if ((localFileCacheEntry == null) || ((!localFileCacheEntry.mLastModifiedChanged) && (!paramBoolean)))
          break label98;
        if (MediaScanner.this.processFile(paramString, null))
          return endFile(localFileCacheEntry);
        if (MediaScanner.this.mCaseInsensitivePaths)
        {
          MediaScanner.this.mFileCache.remove(paramString.toLowerCase());
          return null;
        }
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("RemoteException in MediaScanner.scanFile()", localRemoteException);
        return null;
      }
      MediaScanner.this.mFileCache.remove(paramString);
      label98: return null;
    }

    public void handleStringTag(String paramString1, byte[] paramArrayOfByte, String paramString2)
    {
      try
      {
        str = new String(paramArrayOfByte, paramString2);
        Log.i("%s : %s", new Object[] { paramString1, str });
        if (paramString1.equalsIgnoreCase("title"))
        {
          this.mTitle = str;
          return;
        }
      }
      catch (Exception localException)
      {
        String str;
        do
        {
          do
          {
            while (true)
            {
              Log.e("handleStringTag", localException);
              str = new String(paramArrayOfByte);
            }
            if (paramString1.equalsIgnoreCase("artist"))
            {
              this.mArtist = str.trim();
              return;
            }
            if (!paramString1.equalsIgnoreCase("albumartist"))
              break;
          }
          while (!TextUtils.isEmpty(this.mArtist));
          this.mArtist = str.trim();
          return;
          if (paramString1.equalsIgnoreCase("album"))
          {
            this.mAlbum = str.trim();
            return;
          }
          if (paramString1.equalsIgnoreCase("language"))
          {
            this.mLanguage = str.trim();
            return;
          }
          if (paramString1.equalsIgnoreCase("duration"))
          {
            this.mDuration = parseSubstring(str, 0, 0);
            return;
          }
          if (!paramString1.equalsIgnoreCase("width"))
            continue;
          this.mWidth = parseSubstring(str, 0, 0);
          return;
        }
        while (!paramString1.equalsIgnoreCase("height"));
        this.mHeight = parseSubstring(str, 0, 0);
      }
    }

    public void scanFile(String paramString, long paramLong1, long paramLong2)
    {
      Log.i("scanFile: %s", new Object[] { paramString });
      doScanFile(paramString, paramLong1, paramLong2, false);
    }

    public void setMimeType(String paramString)
    {
      Log.i("setMimeType: %s", new Object[] { paramString });
      this.mMimeType = paramString;
      this.mFileType = MediaFile.getFileTypeForMimeType(paramString);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     io.vov.vitamio.MediaScanner
 * JD-Core Version:    0.6.0
 */