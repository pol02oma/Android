package io.vov.vitamio.provider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.ParcelFileDescriptor;
import android.provider.BaseColumns;
import io.vov.vitamio.utils.Log;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class MediaStore
{
  public static final String AUTHORITY = "me.abitno.vplayer.mediaprovider";
  private static final String BASE_SQL_FIELDS = "_id INTEGER PRIMARY KEY,_data TEXT NOT NULL,_directory TEXT NOT NULL,_directory_name TEXT NOT NULL,_size INTEGER,_display_name TEXT,title TEXT,title_key TEXT,date_added INTEGER,date_modified INTEGER,mime_type TEXT,available_size INTEGER default 0,play_status INTEGER ,";
  public static final String CONTENT_AUTHORITY_SLASH = "content://me.abitno.vplayer.mediaprovider/";
  public static final Uri CONTENT_URI = Uri.parse("content://me.abitno.vplayer.mediaprovider/");
  public static final String MEDIA_SCANNER_VOLUME = "volume";

  public static Uri getMediaScannerUri()
  {
    return Uri.parse("content://me.abitno.vplayer.mediaprovider/media_scanner");
  }

  public static Uri getVolumeUri()
  {
    return Uri.parse("content://me.abitno.vplayer.mediaprovider/volume");
  }

  public static final class Audio
  {
    public static abstract interface AudioColumns extends MediaStore.MediaColumns
    {
      public static final String ALBUM = "album";
      public static final String ARTIST = "artist";
      public static final String BOOKMARK = "bookmark";
      public static final String COMPOSER = "composer";
      public static final String DURATION = "duration";
      public static final String TRACK = "track";
      public static final String YEAR = "year";
    }

    public static final class Media
      implements MediaStore.Audio.AudioColumns
    {
      public static final String CONTENT_TYPE = "vnd.android.cursor.dir/audio";
      public static final Uri CONTENT_URI = Uri.parse("content://me.abitno.vplayer.mediaprovider/audios/media");
    }
  }

  private static class InternalThumbnails
    implements BaseColumns
  {
    static final int DEFAULT_GROUP_ID = 0;
    private static final int MICRO_KIND = 3;
    private static final int MINI_KIND = 1;
    private static final String[] PROJECTION = { "_id", "_data" };
    private static byte[] sThumbBuf;
    private static final Object sThumbBufLock = new Object();

    static void cancelThumbnailRequest(ContentResolver paramContentResolver, long paramLong1, Uri paramUri, long paramLong2)
    {
      Uri localUri = paramUri.buildUpon().appendQueryParameter("cancel", "1").appendQueryParameter("orig_id", String.valueOf(paramLong1)).appendQueryParameter("group_id", String.valueOf(paramLong2)).build();
      try
      {
        Cursor localCursor = paramContentResolver.query(localUri, PROJECTION, null, null, null);
        if (localCursor != null)
          localCursor.close();
        return;
      }
      finally
      {
        if (0 != 0)
          null.close();
      }
      throw localObject;
    }

    private static Bitmap getMiniThumbFromFile(Cursor paramCursor, Uri paramUri, ContentResolver paramContentResolver, BitmapFactory.Options paramOptions)
    {
      Bitmap localBitmap = null;
      try
      {
        ParcelFileDescriptor localParcelFileDescriptor = paramContentResolver.openFileDescriptor(ContentUris.withAppendedId(paramUri, paramCursor.getLong(0)), "r");
        localBitmap = BitmapFactory.decodeFileDescriptor(localParcelFileDescriptor.getFileDescriptor(), null, paramOptions);
        localParcelFileDescriptor.close();
        return localBitmap;
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        Log.e("getMiniThumbFromFile", localFileNotFoundException);
        return localBitmap;
      }
      catch (IOException localIOException)
      {
        Log.e("getMiniThumbFromFile", localIOException);
        return localBitmap;
      }
      catch (OutOfMemoryError localOutOfMemoryError)
      {
        Log.e("getMiniThumbFromFile", localOutOfMemoryError);
      }
      return localBitmap;
    }

    // ERROR //
    static Bitmap getThumbnail(Context paramContext, ContentResolver paramContentResolver, long paramLong1, long paramLong2, int paramInt, BitmapFactory.Options paramOptions, Uri paramUri)
    {
      // Byte code:
      //   0: aload 8
      //   2: invokestatic 129	io/vov/vitamio/provider/MiniThumbFile:instance	(Landroid/net/Uri;)Lio/vov/vitamio/provider/MiniThumbFile;
      //   5: astore 9
      //   7: aload 9
      //   9: lload_2
      //   10: invokevirtual 133	io/vov/vitamio/provider/MiniThumbFile:getMagic	(J)J
      //   13: lconst_0
      //   14: lcmp
      //   15: istore 10
      //   17: aconst_null
      //   18: astore 11
      //   20: iload 10
      //   22: ifeq +208 -> 230
      //   25: iload 6
      //   27: iconst_3
      //   28: if_icmpne +85 -> 113
      //   31: getstatic 34	io/vov/vitamio/provider/MediaStore$InternalThumbnails:sThumbBufLock	Ljava/lang/Object;
      //   34: astore 22
      //   36: aload 22
      //   38: monitorenter
      //   39: getstatic 135	io/vov/vitamio/provider/MediaStore$InternalThumbnails:sThumbBuf	[B
      //   42: ifnonnull +11 -> 53
      //   45: sipush 10000
      //   48: newarray byte
      //   50: putstatic 135	io/vov/vitamio/provider/MediaStore$InternalThumbnails:sThumbBuf	[B
      //   53: aload 9
      //   55: lload_2
      //   56: getstatic 135	io/vov/vitamio/provider/MediaStore$InternalThumbnails:sThumbBuf	[B
      //   59: invokevirtual 138	io/vov/vitamio/provider/MiniThumbFile:getMiniThumbFromFile	(J[B)[B
      //   62: astore 24
      //   64: aconst_null
      //   65: astore 25
      //   67: aload 24
      //   69: ifnull +30 -> 99
      //   72: getstatic 135	io/vov/vitamio/provider/MediaStore$InternalThumbnails:sThumbBuf	[B
      //   75: iconst_0
      //   76: getstatic 135	io/vov/vitamio/provider/MediaStore$InternalThumbnails:sThumbBuf	[B
      //   79: arraylength
      //   80: invokestatic 142	android/graphics/BitmapFactory:decodeByteArray	([BII)Landroid/graphics/Bitmap;
      //   83: astore 25
      //   85: aload 25
      //   87: ifnonnull +12 -> 99
      //   90: ldc 144
      //   92: iconst_0
      //   93: anewarray 4	java/lang/Object
      //   96: invokestatic 148	io/vov/vitamio/utils/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   99: aload 22
      //   101: monitorexit
      //   102: aload 25
      //   104: areturn
      //   105: astore 23
      //   107: aload 22
      //   109: monitorexit
      //   110: aload 23
      //   112: athrow
      //   113: aconst_null
      //   114: astore 11
      //   116: iload 6
      //   118: iconst_1
      //   119: if_icmpne +111 -> 230
      //   122: aconst_null
      //   123: astore 18
      //   125: aload_1
      //   126: aload 8
      //   128: getstatic 29	io/vov/vitamio/provider/MediaStore$InternalThumbnails:PROJECTION	[Ljava/lang/String;
      //   131: new 150	java/lang/StringBuilder
      //   134: dup
      //   135: invokespecial 151	java/lang/StringBuilder:<init>	()V
      //   138: ldc 153
      //   140: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   143: lload_2
      //   144: invokevirtual 160	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   147: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   150: aconst_null
      //   151: aconst_null
      //   152: invokevirtual 70	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
      //   155: astore 18
      //   157: aconst_null
      //   158: astore 11
      //   160: aload 18
      //   162: ifnull +56 -> 218
      //   165: aload 18
      //   167: invokeinterface 168 1 0
      //   172: istore 20
      //   174: aconst_null
      //   175: astore 11
      //   177: iload 20
      //   179: ifeq +39 -> 218
      //   182: aload 18
      //   184: aload 8
      //   186: aload_1
      //   187: aload 7
      //   189: invokestatic 170	io/vov/vitamio/provider/MediaStore$InternalThumbnails:getMiniThumbFromFile	(Landroid/database/Cursor;Landroid/net/Uri;Landroid/content/ContentResolver;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
      //   192: astore 21
      //   194: aload 21
      //   196: astore 11
      //   198: aload 11
      //   200: ifnull +18 -> 218
      //   203: aload 18
      //   205: ifnull +10 -> 215
      //   208: aload 18
      //   210: invokeinterface 75 1 0
      //   215: aload 11
      //   217: areturn
      //   218: aload 18
      //   220: ifnull +10 -> 230
      //   223: aload 18
      //   225: invokeinterface 75 1 0
      //   230: aconst_null
      //   231: astore 12
      //   233: aload_1
      //   234: aload 8
      //   236: invokevirtual 42	android/net/Uri:buildUpon	()Landroid/net/Uri$Builder;
      //   239: ldc 172
      //   241: ldc 46
      //   243: invokevirtual 52	android/net/Uri$Builder:appendQueryParameter	(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
      //   246: ldc 54
      //   248: lload_2
      //   249: invokestatic 58	java/lang/String:valueOf	(J)Ljava/lang/String;
      //   252: invokevirtual 52	android/net/Uri$Builder:appendQueryParameter	(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
      //   255: ldc 60
      //   257: lload 4
      //   259: invokestatic 58	java/lang/String:valueOf	(J)Ljava/lang/String;
      //   262: invokevirtual 52	android/net/Uri$Builder:appendQueryParameter	(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
      //   265: invokevirtual 64	android/net/Uri$Builder:build	()Landroid/net/Uri;
      //   268: getstatic 29	io/vov/vitamio/provider/MediaStore$InternalThumbnails:PROJECTION	[Ljava/lang/String;
      //   271: aconst_null
      //   272: aconst_null
      //   273: aconst_null
      //   274: invokevirtual 70	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
      //   277: astore 15
      //   279: aload 15
      //   281: astore 12
      //   283: aload 12
      //   285: ifnonnull +34 -> 319
      //   288: aload 12
      //   290: ifnull +222 -> 512
      //   293: aload 12
      //   295: invokeinterface 75 1 0
      //   300: aconst_null
      //   301: areturn
      //   302: astore 19
      //   304: aload 18
      //   306: ifnull +10 -> 316
      //   309: aload 18
      //   311: invokeinterface 75 1 0
      //   316: aload 19
      //   318: athrow
      //   319: iload 6
      //   321: iconst_3
      //   322: if_icmpne +114 -> 436
      //   325: getstatic 34	io/vov/vitamio/provider/MediaStore$InternalThumbnails:sThumbBufLock	Ljava/lang/Object;
      //   328: astore 16
      //   330: aload 16
      //   332: monitorenter
      //   333: getstatic 135	io/vov/vitamio/provider/MediaStore$InternalThumbnails:sThumbBuf	[B
      //   336: ifnonnull +11 -> 347
      //   339: sipush 10000
      //   342: newarray byte
      //   344: putstatic 135	io/vov/vitamio/provider/MediaStore$InternalThumbnails:sThumbBuf	[B
      //   347: aload 9
      //   349: lload_2
      //   350: getstatic 135	io/vov/vitamio/provider/MediaStore$InternalThumbnails:sThumbBuf	[B
      //   353: invokevirtual 138	io/vov/vitamio/provider/MiniThumbFile:getMiniThumbFromFile	(J[B)[B
      //   356: ifnull +30 -> 386
      //   359: getstatic 135	io/vov/vitamio/provider/MediaStore$InternalThumbnails:sThumbBuf	[B
      //   362: iconst_0
      //   363: getstatic 135	io/vov/vitamio/provider/MediaStore$InternalThumbnails:sThumbBuf	[B
      //   366: arraylength
      //   367: invokestatic 142	android/graphics/BitmapFactory:decodeByteArray	([BII)Landroid/graphics/Bitmap;
      //   370: astore 11
      //   372: aload 11
      //   374: ifnonnull +12 -> 386
      //   377: ldc 144
      //   379: iconst_0
      //   380: anewarray 4	java/lang/Object
      //   383: invokestatic 148	io/vov/vitamio/utils/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   386: aload 16
      //   388: monitorexit
      //   389: aload 12
      //   391: ifnull +10 -> 401
      //   394: aload 12
      //   396: invokeinterface 75 1 0
      //   401: aload 11
      //   403: areturn
      //   404: astore 17
      //   406: aload 16
      //   408: monitorexit
      //   409: aload 17
      //   411: athrow
      //   412: astore 14
      //   414: ldc 173
      //   416: aload 14
      //   418: invokestatic 119	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;)V
      //   421: aload 12
      //   423: ifnull -22 -> 401
      //   426: aload 12
      //   428: invokeinterface 75 1 0
      //   433: goto -32 -> 401
      //   436: iload 6
      //   438: iconst_1
      //   439: if_icmpne +28 -> 467
      //   442: aload 12
      //   444: invokeinterface 168 1 0
      //   449: ifeq -60 -> 389
      //   452: aload 12
      //   454: aload 8
      //   456: aload_1
      //   457: aload 7
      //   459: invokestatic 170	io/vov/vitamio/provider/MediaStore$InternalThumbnails:getMiniThumbFromFile	(Landroid/database/Cursor;Landroid/net/Uri;Landroid/content/ContentResolver;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
      //   462: astore 11
      //   464: goto -75 -> 389
      //   467: new 175	java/lang/IllegalArgumentException
      //   470: dup
      //   471: new 150	java/lang/StringBuilder
      //   474: dup
      //   475: invokespecial 151	java/lang/StringBuilder:<init>	()V
      //   478: ldc 177
      //   480: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   483: iload 6
      //   485: invokevirtual 180	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   488: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   491: invokespecial 183	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
      //   494: athrow
      //   495: astore 13
      //   497: aload 12
      //   499: ifnull +10 -> 509
      //   502: aload 12
      //   504: invokeinterface 75 1 0
      //   509: aload 13
      //   511: athrow
      //   512: aconst_null
      //   513: areturn
      //
      // Exception table:
      //   from	to	target	type
      //   39	53	105	finally
      //   53	64	105	finally
      //   72	85	105	finally
      //   90	99	105	finally
      //   99	102	105	finally
      //   107	110	105	finally
      //   125	157	302	finally
      //   165	174	302	finally
      //   182	194	302	finally
      //   333	347	404	finally
      //   347	372	404	finally
      //   377	386	404	finally
      //   386	389	404	finally
      //   406	409	404	finally
      //   233	279	412	android/database/sqlite/SQLiteException
      //   325	333	412	android/database/sqlite/SQLiteException
      //   409	412	412	android/database/sqlite/SQLiteException
      //   442	464	412	android/database/sqlite/SQLiteException
      //   467	495	412	android/database/sqlite/SQLiteException
      //   233	279	495	finally
      //   325	333	495	finally
      //   409	412	495	finally
      //   414	421	495	finally
      //   442	464	495	finally
      //   467	495	495	finally
    }

    static String getThumbnailPath(Context paramContext, ContentResolver paramContentResolver, long paramLong, Uri paramUri)
    {
      Object localObject1 = "";
      Cursor localCursor = null;
      try
      {
        localCursor = paramContentResolver.query(paramUri, PROJECTION, "video_id=" + paramLong, null, null);
        if ((localCursor != null) && (localCursor.moveToFirst()))
        {
          String str = localCursor.getString(localCursor.getColumnIndex("_data"));
          localObject1 = str;
        }
        return localObject1;
      }
      finally
      {
        if (localCursor != null)
          localCursor.close();
      }
      throw localObject2;
    }
  }

  public static abstract interface MediaColumns extends BaseColumns
  {
    public static final String AVAILABLE_SIZE = "available_size";
    public static final String DATA = "_data";
    public static final String DATE_ADDED = "date_added";
    public static final String DATE_MODIFIED = "date_modified";
    public static final String DIRECTORY = "_directory";
    public static final String DIRECTORY_NAME = "_directory_name";
    public static final String DISPLAY_NAME = "_display_name";
    public static final String MIME_TYPE = "mime_type";
    public static final String PLAY_STATUS = "play_status";
    public static final String SIZE = "_size";
    public static final String TITLE = "title";
    public static final String TITLE_KEY = "title_key";
  }

  public static final class Video
  {
    public static final class Media
      implements MediaStore.Video.VideoColumns
    {
      public static final String CONTENT_TYPE = "vnd.android.cursor.dir/video";
      public static final Uri CONTENT_URI = Uri.parse("content://me.abitno.vplayer.mediaprovider/videos/media");
      protected static final String SQL_FIELDS = "_id INTEGER PRIMARY KEY,_data TEXT NOT NULL,_directory TEXT NOT NULL,_directory_name TEXT NOT NULL,_size INTEGER,_display_name TEXT,title TEXT,title_key TEXT,date_added INTEGER,date_modified INTEGER,mime_type TEXT,available_size INTEGER default 0,play_status INTEGER ,duration INTEGER,artist TEXT,album TEXT,width INTEGER,height INTEGER,description TEXT,language TEXT,latitude DOUBLE,longitude DOUBLE,datetaken INTEGER,bookmark INTEGER,mini_thumb_magic INTEGER,hidden INTEGER default 0,sub_track TEXT,audio_track INTEGER";
      protected static final String SQL_TRIGGER_VIDEO_CLEANUP = "CREATE TRIGGER IF NOT EXISTS video_cleanup AFTER DELETE ON videos BEGIN SELECT _DELETE_FILE(old._data);SELECT _DELETE_FILE(old._data || '.ssi');END";
      protected static final String SQL_TRIGGER_VIDEO_UPDATE = "CREATE TRIGGER IF NOT EXISTS video_update AFTER UPDATE ON videos WHEN new._data <> old._data BEGIN SELECT _DELETE_FILE(old._data || '.ssi');END";
      protected static final String TABLE_NAME = "videos";
    }

    public static class Thumbnails
      implements BaseColumns
    {
      public static final Uri CONTENT_URI = Uri.parse("content://me.abitno.vplayer.mediaprovider/videos/thumbnails");
      public static final String DATA = "_data";
      public static final String HEIGHT = "height";
      public static final String KIND = "kind";
      public static final int MICRO_KIND = 3;
      public static final int MINI_KIND = 1;
      protected static final String SQL_FIELDS = "_id INTEGER PRIMARY KEY,_data TEXT,video_id INTEGER,kind INTEGER,width INTEGER,height INTEGER";
      protected static final String SQL_INDEX_VIDEO_ID = "CREATE INDEX IF NOT EXISTS video_id_index on videothumbnails(video_id);";
      protected static final String SQL_TRIGGER_VIDEO_THUMBNAILS_CLEANUP = "CREATE TRIGGER IF NOT EXISTS videothumbnails_cleanup DELETE ON videothumbnails BEGIN SELECT _DELETE_FILE(old._data);END";
      protected static final String TABLE_NAME = "videothumbnails";
      public static final String THUMBNAILS_DIRECTORY = "Android/data/me.abitno.vplayer.t/thumbnails";
      public static final String VIDEO_ID = "video_id";
      public static final String WIDTH = "width";

      public static void cancelThumbnailRequest(ContentResolver paramContentResolver, long paramLong)
      {
        MediaStore.InternalThumbnails.cancelThumbnailRequest(paramContentResolver, paramLong, CONTENT_URI, 0L);
      }

      public static void cancelThumbnailRequest(ContentResolver paramContentResolver, long paramLong1, long paramLong2)
      {
        MediaStore.InternalThumbnails.cancelThumbnailRequest(paramContentResolver, paramLong1, CONTENT_URI, paramLong2);
      }

      public static Bitmap getThumbnail(Context paramContext, ContentResolver paramContentResolver, long paramLong, int paramInt, BitmapFactory.Options paramOptions)
      {
        return MediaStore.InternalThumbnails.getThumbnail(paramContext, paramContentResolver, paramLong, 0L, paramInt, paramOptions, CONTENT_URI);
      }

      public static Bitmap getThumbnail(Context paramContext, ContentResolver paramContentResolver, long paramLong1, long paramLong2, int paramInt, BitmapFactory.Options paramOptions)
      {
        return MediaStore.InternalThumbnails.getThumbnail(paramContext, paramContentResolver, paramLong1, paramLong2, paramInt, paramOptions, CONTENT_URI);
      }

      public static String getThumbnailPath(Context paramContext, ContentResolver paramContentResolver, long paramLong)
      {
        return MediaStore.InternalThumbnails.getThumbnailPath(paramContext, paramContentResolver, paramLong, CONTENT_URI);
      }
    }

    public static abstract interface VideoColumns extends MediaStore.MediaColumns
    {
      public static final String ALBUM = "album";
      public static final String ARTIST = "artist";
      public static final String AUDIO_TRACK = "audio_track";
      public static final String BOOKMARK = "bookmark";
      public static final String DATE_TAKEN = "datetaken";
      public static final String DESCRIPTION = "description";
      public static final String DURATION = "duration";
      public static final String HEIGHT = "height";
      public static final String HIDDEN = "hidden";
      public static final String LANGUAGE = "language";
      public static final String LATITUDE = "latitude";
      public static final String LONGITUDE = "longitude";
      public static final String MINI_THUMB_MAGIC = "mini_thumb_magic";
      public static final String SUBTRACK = "sub_track";
      public static final String WIDTH = "width";
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     io.vov.vitamio.provider.MediaStore
 * JD-Core Version:    0.6.0
 */