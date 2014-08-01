package org.apache.commons.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.CRC32;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

public class FileUtils
{
  public static final File[] EMPTY_FILE_ARRAY;
  private static final long FILE_COPY_BUFFER_SIZE = 31457280L;
  public static final long ONE_EB = 1152921504606846976L;
  public static final BigInteger ONE_EB_BI;
  public static final long ONE_GB = 1073741824L;
  public static final BigInteger ONE_GB_BI;
  public static final long ONE_KB = 1024L;
  public static final BigInteger ONE_KB_BI = BigInteger.valueOf(1024L);
  public static final long ONE_MB = 1048576L;
  public static final BigInteger ONE_MB_BI = ONE_KB_BI.multiply(ONE_KB_BI);
  public static final long ONE_PB = 1125899906842624L;
  public static final BigInteger ONE_PB_BI;
  public static final long ONE_TB = 1099511627776L;
  public static final BigInteger ONE_TB_BI;
  public static final BigInteger ONE_YB;
  public static final BigInteger ONE_ZB;
  private static final Charset UTF8;

  static
  {
    ONE_GB_BI = ONE_KB_BI.multiply(ONE_MB_BI);
    ONE_TB_BI = ONE_KB_BI.multiply(ONE_GB_BI);
    ONE_PB_BI = ONE_KB_BI.multiply(ONE_TB_BI);
    ONE_EB_BI = ONE_KB_BI.multiply(ONE_PB_BI);
    ONE_ZB = BigInteger.valueOf(1024L).multiply(BigInteger.valueOf(1152921504606846976L));
    ONE_YB = ONE_KB_BI.multiply(ONE_ZB);
    EMPTY_FILE_ARRAY = new File[0];
    UTF8 = Charset.forName("UTF-8");
  }

  public static String byteCountToDisplaySize(long paramLong)
  {
    return byteCountToDisplaySize(BigInteger.valueOf(paramLong));
  }

  public static String byteCountToDisplaySize(BigInteger paramBigInteger)
  {
    if (paramBigInteger.divide(ONE_EB_BI).compareTo(BigInteger.ZERO) > 0)
      return String.valueOf(paramBigInteger.divide(ONE_EB_BI)) + " EB";
    if (paramBigInteger.divide(ONE_PB_BI).compareTo(BigInteger.ZERO) > 0)
      return String.valueOf(paramBigInteger.divide(ONE_PB_BI)) + " PB";
    if (paramBigInteger.divide(ONE_TB_BI).compareTo(BigInteger.ZERO) > 0)
      return String.valueOf(paramBigInteger.divide(ONE_TB_BI)) + " TB";
    if (paramBigInteger.divide(ONE_GB_BI).compareTo(BigInteger.ZERO) > 0)
      return String.valueOf(paramBigInteger.divide(ONE_GB_BI)) + " GB";
    if (paramBigInteger.divide(ONE_MB_BI).compareTo(BigInteger.ZERO) > 0)
      return String.valueOf(paramBigInteger.divide(ONE_MB_BI)) + " MB";
    if (paramBigInteger.divide(ONE_KB_BI).compareTo(BigInteger.ZERO) > 0)
      return String.valueOf(paramBigInteger.divide(ONE_KB_BI)) + " KB";
    return String.valueOf(paramBigInteger) + " bytes";
  }

  private static void checkDirectory(File paramFile)
  {
    if (!paramFile.exists())
      throw new IllegalArgumentException(paramFile + " does not exist");
    if (!paramFile.isDirectory())
      throw new IllegalArgumentException(paramFile + " is not a directory");
  }

  // ERROR //
  public static java.util.zip.Checksum checksum(File paramFile, java.util.zip.Checksum paramChecksum)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 148	java/io/File:isDirectory	()Z
    //   4: ifeq +13 -> 17
    //   7: new 137	java/lang/IllegalArgumentException
    //   10: dup
    //   11: ldc 156
    //   13: invokespecial 145	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   16: athrow
    //   17: aconst_null
    //   18: astore_2
    //   19: new 158	java/util/zip/CheckedInputStream
    //   22: dup
    //   23: new 160	java/io/FileInputStream
    //   26: dup
    //   27: aload_0
    //   28: invokespecial 162	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   31: aload_1
    //   32: invokespecial 165	java/util/zip/CheckedInputStream:<init>	(Ljava/io/InputStream;Ljava/util/zip/Checksum;)V
    //   35: astore_3
    //   36: aload_3
    //   37: new 167	org/apache/commons/io/output/NullOutputStream
    //   40: dup
    //   41: invokespecial 168	org/apache/commons/io/output/NullOutputStream:<init>	()V
    //   44: invokestatic 174	org/apache/commons/io/IOUtils:copy	(Ljava/io/InputStream;Ljava/io/OutputStream;)I
    //   47: pop
    //   48: aload_3
    //   49: invokestatic 178	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   52: aload_1
    //   53: areturn
    //   54: astore 4
    //   56: aload_2
    //   57: invokestatic 178	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   60: aload 4
    //   62: athrow
    //   63: astore 4
    //   65: aload_3
    //   66: astore_2
    //   67: goto -11 -> 56
    //
    // Exception table:
    //   from	to	target	type
    //   19	36	54	finally
    //   36	48	63	finally
  }

  public static long checksumCRC32(File paramFile)
    throws IOException
  {
    CRC32 localCRC32 = new CRC32();
    checksum(paramFile, localCRC32);
    return localCRC32.getValue();
  }

  public static void cleanDirectory(File paramFile)
    throws IOException
  {
    if (!paramFile.exists())
      throw new IllegalArgumentException(paramFile + " does not exist");
    if (!paramFile.isDirectory())
      throw new IllegalArgumentException(paramFile + " is not a directory");
    File[] arrayOfFile = paramFile.listFiles();
    if (arrayOfFile == null)
      throw new IOException("Failed to list contents of " + paramFile);
    Object localObject = null;
    int i = arrayOfFile.length;
    int j = 0;
    while (true)
      if (j < i)
      {
        File localFile = arrayOfFile[j];
        try
        {
          forceDelete(localFile);
          j++;
        }
        catch (IOException localIOException)
        {
          while (true)
            localObject = localIOException;
        }
      }
    if (localObject != null)
      throw localObject;
  }

  private static void cleanDirectoryOnExit(File paramFile)
    throws IOException
  {
    if (!paramFile.exists())
      throw new IllegalArgumentException(paramFile + " does not exist");
    if (!paramFile.isDirectory())
      throw new IllegalArgumentException(paramFile + " is not a directory");
    File[] arrayOfFile = paramFile.listFiles();
    if (arrayOfFile == null)
      throw new IOException("Failed to list contents of " + paramFile);
    Object localObject = null;
    int i = arrayOfFile.length;
    int j = 0;
    while (true)
      if (j < i)
      {
        File localFile = arrayOfFile[j];
        try
        {
          forceDeleteOnExit(localFile);
          j++;
        }
        catch (IOException localIOException)
        {
          while (true)
            localObject = localIOException;
        }
      }
    if (localObject != null)
      throw localObject;
  }

  // ERROR //
  public static boolean contentEquals(File paramFile1, File paramFile2)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 135	java/io/File:exists	()Z
    //   4: istore_2
    //   5: iload_2
    //   6: aload_1
    //   7: invokevirtual 135	java/io/File:exists	()Z
    //   10: if_icmpeq +5 -> 15
    //   13: iconst_0
    //   14: ireturn
    //   15: iload_2
    //   16: ifne +5 -> 21
    //   19: iconst_1
    //   20: ireturn
    //   21: aload_0
    //   22: invokevirtual 148	java/io/File:isDirectory	()Z
    //   25: ifne +10 -> 35
    //   28: aload_1
    //   29: invokevirtual 148	java/io/File:isDirectory	()Z
    //   32: ifeq +13 -> 45
    //   35: new 154	java/io/IOException
    //   38: dup
    //   39: ldc 208
    //   41: invokespecial 197	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   44: athrow
    //   45: aload_0
    //   46: invokevirtual 211	java/io/File:length	()J
    //   49: aload_1
    //   50: invokevirtual 211	java/io/File:length	()J
    //   53: lcmp
    //   54: ifne -41 -> 13
    //   57: aload_0
    //   58: invokevirtual 215	java/io/File:getCanonicalFile	()Ljava/io/File;
    //   61: aload_1
    //   62: invokevirtual 215	java/io/File:getCanonicalFile	()Ljava/io/File;
    //   65: invokevirtual 219	java/io/File:equals	(Ljava/lang/Object;)Z
    //   68: ifeq +5 -> 73
    //   71: iconst_1
    //   72: ireturn
    //   73: aconst_null
    //   74: astore_3
    //   75: aconst_null
    //   76: astore 4
    //   78: new 160	java/io/FileInputStream
    //   81: dup
    //   82: aload_0
    //   83: invokespecial 162	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   86: astore 5
    //   88: new 160	java/io/FileInputStream
    //   91: dup
    //   92: aload_1
    //   93: invokespecial 162	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   96: astore 6
    //   98: aload 5
    //   100: aload 6
    //   102: invokestatic 222	org/apache/commons/io/IOUtils:contentEquals	(Ljava/io/InputStream;Ljava/io/InputStream;)Z
    //   105: istore 8
    //   107: aload 5
    //   109: invokestatic 178	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   112: aload 6
    //   114: invokestatic 178	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   117: iload 8
    //   119: ireturn
    //   120: astore 7
    //   122: aload_3
    //   123: invokestatic 178	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   126: aload 4
    //   128: invokestatic 178	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   131: aload 7
    //   133: athrow
    //   134: astore 7
    //   136: aload 5
    //   138: astore_3
    //   139: aconst_null
    //   140: astore 4
    //   142: goto -20 -> 122
    //   145: astore 7
    //   147: aload 6
    //   149: astore 4
    //   151: aload 5
    //   153: astore_3
    //   154: goto -32 -> 122
    //
    // Exception table:
    //   from	to	target	type
    //   78	88	120	finally
    //   88	98	134	finally
    //   98	107	145	finally
  }

  // ERROR //
  public static boolean contentEqualsIgnoreEOL(File paramFile1, File paramFile2, String paramString)
    throws IOException
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_3
    //   2: aload_0
    //   3: invokevirtual 135	java/io/File:exists	()Z
    //   6: istore 4
    //   8: iload 4
    //   10: aload_1
    //   11: invokevirtual 135	java/io/File:exists	()Z
    //   14: if_icmpeq +7 -> 21
    //   17: iconst_0
    //   18: istore_3
    //   19: iload_3
    //   20: ireturn
    //   21: iload 4
    //   23: ifeq -4 -> 19
    //   26: aload_0
    //   27: invokevirtual 148	java/io/File:isDirectory	()Z
    //   30: ifne +10 -> 40
    //   33: aload_1
    //   34: invokevirtual 148	java/io/File:isDirectory	()Z
    //   37: ifeq +13 -> 50
    //   40: new 154	java/io/IOException
    //   43: dup
    //   44: ldc 208
    //   46: invokespecial 197	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   49: athrow
    //   50: aload_0
    //   51: invokevirtual 215	java/io/File:getCanonicalFile	()Ljava/io/File;
    //   54: aload_1
    //   55: invokevirtual 215	java/io/File:getCanonicalFile	()Ljava/io/File;
    //   58: invokevirtual 219	java/io/File:equals	(Ljava/lang/Object;)Z
    //   61: ifne -42 -> 19
    //   64: aconst_null
    //   65: astore 5
    //   67: aconst_null
    //   68: astore 6
    //   70: aload_2
    //   71: ifnonnull +67 -> 138
    //   74: new 226	java/io/InputStreamReader
    //   77: dup
    //   78: new 160	java/io/FileInputStream
    //   81: dup
    //   82: aload_0
    //   83: invokespecial 162	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   86: invokespecial 228	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   89: astore 7
    //   91: new 226	java/io/InputStreamReader
    //   94: dup
    //   95: new 160	java/io/FileInputStream
    //   98: dup
    //   99: aload_1
    //   100: invokespecial 162	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   103: invokespecial 228	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   106: astore 11
    //   108: aload 11
    //   110: astore 6
    //   112: aload 7
    //   114: astore 5
    //   116: aload 5
    //   118: aload 6
    //   120: invokestatic 231	org/apache/commons/io/IOUtils:contentEqualsIgnoreEOL	(Ljava/io/Reader;Ljava/io/Reader;)Z
    //   123: istore 10
    //   125: aload 5
    //   127: invokestatic 234	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/Reader;)V
    //   130: aload 6
    //   132: invokestatic 234	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/Reader;)V
    //   135: iload 10
    //   137: ireturn
    //   138: new 226	java/io/InputStreamReader
    //   141: dup
    //   142: new 160	java/io/FileInputStream
    //   145: dup
    //   146: aload_0
    //   147: invokespecial 162	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   150: aload_2
    //   151: invokespecial 237	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   154: astore 7
    //   156: new 226	java/io/InputStreamReader
    //   159: dup
    //   160: new 160	java/io/FileInputStream
    //   163: dup
    //   164: aload_1
    //   165: invokespecial 162	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   168: aload_2
    //   169: invokespecial 237	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   172: astore 8
    //   174: aload 8
    //   176: astore 6
    //   178: aload 7
    //   180: astore 5
    //   182: goto -66 -> 116
    //   185: astore 9
    //   187: aload 5
    //   189: invokestatic 234	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/Reader;)V
    //   192: aload 6
    //   194: invokestatic 234	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/Reader;)V
    //   197: aload 9
    //   199: athrow
    //   200: astore 9
    //   202: aload 7
    //   204: astore 5
    //   206: aconst_null
    //   207: astore 6
    //   209: goto -22 -> 187
    //
    // Exception table:
    //   from	to	target	type
    //   74	91	185	finally
    //   116	125	185	finally
    //   138	156	185	finally
    //   91	108	200	finally
    //   156	174	200	finally
  }

  public static File[] convertFileCollectionToFileArray(Collection<File> paramCollection)
  {
    return (File[])paramCollection.toArray(new File[paramCollection.size()]);
  }

  public static void copyDirectory(File paramFile1, File paramFile2)
    throws IOException
  {
    copyDirectory(paramFile1, paramFile2, true);
  }

  public static void copyDirectory(File paramFile1, File paramFile2, FileFilter paramFileFilter)
    throws IOException
  {
    copyDirectory(paramFile1, paramFile2, paramFileFilter, true);
  }

  public static void copyDirectory(File paramFile1, File paramFile2, FileFilter paramFileFilter, boolean paramBoolean)
    throws IOException
  {
    if (paramFile1 == null)
      throw new NullPointerException("Source must not be null");
    if (paramFile2 == null)
      throw new NullPointerException("Destination must not be null");
    if (!paramFile1.exists())
      throw new FileNotFoundException("Source '" + paramFile1 + "' does not exist");
    if (!paramFile1.isDirectory())
      throw new IOException("Source '" + paramFile1 + "' exists but is not a directory");
    if (paramFile1.getCanonicalPath().equals(paramFile2.getCanonicalPath()))
      throw new IOException("Source '" + paramFile1 + "' and destination '" + paramFile2 + "' are the same");
    boolean bool = paramFile2.getCanonicalPath().startsWith(paramFile1.getCanonicalPath());
    ArrayList localArrayList = null;
    if (bool)
    {
      if (paramFileFilter == null);
      for (File[] arrayOfFile1 = paramFile1.listFiles(); ; arrayOfFile1 = paramFile1.listFiles(paramFileFilter))
      {
        localArrayList = null;
        if (arrayOfFile1 == null)
          break;
        int i = arrayOfFile1.length;
        localArrayList = null;
        if (i <= 0)
          break;
        localArrayList = new ArrayList(arrayOfFile1.length);
        File[] arrayOfFile2 = arrayOfFile1;
        int j = arrayOfFile2.length;
        for (int k = 0; k < j; k++)
          localArrayList.add(new File(paramFile2, arrayOfFile2[k].getName()).getCanonicalPath());
      }
    }
    doCopyDirectory(paramFile1, paramFile2, paramFileFilter, paramBoolean, localArrayList);
  }

  public static void copyDirectory(File paramFile1, File paramFile2, boolean paramBoolean)
    throws IOException
  {
    copyDirectory(paramFile1, paramFile2, null, paramBoolean);
  }

  public static void copyDirectoryToDirectory(File paramFile1, File paramFile2)
    throws IOException
  {
    if (paramFile1 == null)
      throw new NullPointerException("Source must not be null");
    if ((paramFile1.exists()) && (!paramFile1.isDirectory()))
      throw new IllegalArgumentException("Source '" + paramFile2 + "' is not a directory");
    if (paramFile2 == null)
      throw new NullPointerException("Destination must not be null");
    if ((paramFile2.exists()) && (!paramFile2.isDirectory()))
      throw new IllegalArgumentException("Destination '" + paramFile2 + "' is not a directory");
    copyDirectory(paramFile1, new File(paramFile2, paramFile1.getName()), true);
  }

  public static long copyFile(File paramFile, OutputStream paramOutputStream)
    throws IOException
  {
    FileInputStream localFileInputStream = new FileInputStream(paramFile);
    try
    {
      long l = IOUtils.copyLarge(localFileInputStream, paramOutputStream);
      return l;
    }
    finally
    {
      localFileInputStream.close();
    }
    throw localObject;
  }

  public static void copyFile(File paramFile1, File paramFile2)
    throws IOException
  {
    copyFile(paramFile1, paramFile2, true);
  }

  public static void copyFile(File paramFile1, File paramFile2, boolean paramBoolean)
    throws IOException
  {
    if (paramFile1 == null)
      throw new NullPointerException("Source must not be null");
    if (paramFile2 == null)
      throw new NullPointerException("Destination must not be null");
    if (!paramFile1.exists())
      throw new FileNotFoundException("Source '" + paramFile1 + "' does not exist");
    if (paramFile1.isDirectory())
      throw new IOException("Source '" + paramFile1 + "' exists but is a directory");
    if (paramFile1.getCanonicalPath().equals(paramFile2.getCanonicalPath()))
      throw new IOException("Source '" + paramFile1 + "' and destination '" + paramFile2 + "' are the same");
    File localFile = paramFile2.getParentFile();
    if ((localFile != null) && (!localFile.mkdirs()) && (!localFile.isDirectory()))
      throw new IOException("Destination '" + localFile + "' directory cannot be created");
    if ((paramFile2.exists()) && (!paramFile2.canWrite()))
      throw new IOException("Destination '" + paramFile2 + "' exists but is read-only");
    doCopyFile(paramFile1, paramFile2, paramBoolean);
  }

  public static void copyFileToDirectory(File paramFile1, File paramFile2)
    throws IOException
  {
    copyFileToDirectory(paramFile1, paramFile2, true);
  }

  public static void copyFileToDirectory(File paramFile1, File paramFile2, boolean paramBoolean)
    throws IOException
  {
    if (paramFile2 == null)
      throw new NullPointerException("Destination must not be null");
    if ((paramFile2.exists()) && (!paramFile2.isDirectory()))
      throw new IllegalArgumentException("Destination '" + paramFile2 + "' is not a directory");
    copyFile(paramFile1, new File(paramFile2, paramFile1.getName()), paramBoolean);
  }

  public static void copyInputStreamToFile(InputStream paramInputStream, File paramFile)
    throws IOException
  {
    try
    {
      FileOutputStream localFileOutputStream = openOutputStream(paramFile);
      try
      {
        IOUtils.copy(paramInputStream, localFileOutputStream);
        localFileOutputStream.close();
        IOUtils.closeQuietly(localFileOutputStream);
        return;
      }
      finally
      {
        IOUtils.closeQuietly(localFileOutputStream);
      }
    }
    finally
    {
      IOUtils.closeQuietly(paramInputStream);
    }
    throw localObject1;
  }

  public static void copyURLToFile(URL paramURL, File paramFile)
    throws IOException
  {
    copyInputStreamToFile(paramURL.openStream(), paramFile);
  }

  public static void copyURLToFile(URL paramURL, File paramFile, int paramInt1, int paramInt2)
    throws IOException
  {
    URLConnection localURLConnection = paramURL.openConnection();
    localURLConnection.setConnectTimeout(paramInt1);
    localURLConnection.setReadTimeout(paramInt2);
    copyInputStreamToFile(localURLConnection.getInputStream(), paramFile);
  }

  static String decodeUrl(String paramString)
  {
    String str = paramString;
    if ((paramString != null) && (paramString.indexOf('%') >= 0))
    {
      int i = paramString.length();
      StringBuffer localStringBuffer = new StringBuffer();
      ByteBuffer localByteBuffer = ByteBuffer.allocate(i);
      int j = 0;
      while (j < i)
      {
        label54: int m;
        int n;
        if (paramString.charAt(j) == '%')
        {
          m = j + 1;
          n = j + 3;
        }
        try
        {
          localByteBuffer.put((byte)Integer.parseInt(paramString.substring(m, n), 16));
          j += 3;
          if (j < i)
          {
            int i1 = paramString.charAt(j);
            if (i1 == 37)
              break label54;
          }
          if (localByteBuffer.position() <= 0)
            continue;
          localByteBuffer.flip();
          localStringBuffer.append(UTF8.decode(localByteBuffer).toString());
          localByteBuffer.clear();
          continue;
        }
        catch (RuntimeException localRuntimeException)
        {
          if (localByteBuffer.position() > 0)
          {
            localByteBuffer.flip();
            localStringBuffer.append(UTF8.decode(localByteBuffer).toString());
            localByteBuffer.clear();
          }
          int k = j + 1;
          localStringBuffer.append(paramString.charAt(j));
          j = k;
          continue;
        }
        finally
        {
          if (localByteBuffer.position() > 0)
          {
            localByteBuffer.flip();
            localStringBuffer.append(UTF8.decode(localByteBuffer).toString());
            localByteBuffer.clear();
          }
        }
      }
      str = localStringBuffer.toString();
    }
    return str;
  }

  public static void deleteDirectory(File paramFile)
    throws IOException
  {
    if (!paramFile.exists());
    do
    {
      return;
      if (isSymlink(paramFile))
        continue;
      cleanDirectory(paramFile);
    }
    while (paramFile.delete());
    throw new IOException("Unable to delete directory " + paramFile + ".");
  }

  private static void deleteDirectoryOnExit(File paramFile)
    throws IOException
  {
    if (!paramFile.exists());
    do
    {
      return;
      paramFile.deleteOnExit();
    }
    while (isSymlink(paramFile));
    cleanDirectoryOnExit(paramFile);
  }

  public static boolean deleteQuietly(File paramFile)
  {
    if (paramFile == null)
      return false;
    try
    {
      if (paramFile.isDirectory())
        cleanDirectory(paramFile);
      try
      {
        label17: boolean bool = paramFile.delete();
        return bool;
      }
      catch (Exception localException2)
      {
        return false;
      }
    }
    catch (Exception localException1)
    {
      break label17;
    }
  }

  public static boolean directoryContains(File paramFile1, File paramFile2)
    throws IOException
  {
    if (paramFile1 == null)
      throw new IllegalArgumentException("Directory must not be null");
    if (!paramFile1.isDirectory())
      throw new IllegalArgumentException("Not a directory: " + paramFile1);
    if (paramFile2 == null);
    do
      return false;
    while ((!paramFile1.exists()) || (!paramFile2.exists()));
    return FilenameUtils.directoryContains(paramFile1.getCanonicalPath(), paramFile2.getCanonicalPath());
  }

  private static void doCopyDirectory(File paramFile1, File paramFile2, FileFilter paramFileFilter, boolean paramBoolean, List<String> paramList)
    throws IOException
  {
    if (paramFileFilter == null);
    for (File[] arrayOfFile1 = paramFile1.listFiles(); arrayOfFile1 == null; arrayOfFile1 = paramFile1.listFiles(paramFileFilter))
      throw new IOException("Failed to list contents of " + paramFile1);
    if (paramFile2.exists())
    {
      if (!paramFile2.isDirectory())
        throw new IOException("Destination '" + paramFile2 + "' exists but is not a directory");
    }
    else if ((!paramFile2.mkdirs()) && (!paramFile2.isDirectory()))
      throw new IOException("Destination '" + paramFile2 + "' directory cannot be created");
    if (!paramFile2.canWrite())
      throw new IOException("Destination '" + paramFile2 + "' cannot be written to");
    File[] arrayOfFile2 = arrayOfFile1;
    int i = arrayOfFile2.length;
    int j = 0;
    if (j < i)
    {
      File localFile1 = arrayOfFile2[j];
      File localFile2 = new File(paramFile2, localFile1.getName());
      if ((paramList == null) || (!paramList.contains(localFile1.getCanonicalPath())))
      {
        if (!localFile1.isDirectory())
          break label275;
        doCopyDirectory(localFile1, localFile2, paramFileFilter, paramBoolean, paramList);
      }
      while (true)
      {
        j++;
        break;
        label275: doCopyFile(localFile1, localFile2, paramBoolean);
      }
    }
    if (paramBoolean)
      paramFile2.setLastModified(paramFile1.lastModified());
  }

  // ERROR //
  private static void doCopyFile(File paramFile1, File paramFile2, boolean paramBoolean)
    throws IOException
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 135	java/io/File:exists	()Z
    //   4: ifeq +44 -> 48
    //   7: aload_1
    //   8: invokevirtual 148	java/io/File:isDirectory	()Z
    //   11: ifeq +37 -> 48
    //   14: new 154	java/io/IOException
    //   17: dup
    //   18: new 101	java/lang/StringBuilder
    //   21: dup
    //   22: invokespecial 102	java/lang/StringBuilder:<init>	()V
    //   25: ldc_w 315
    //   28: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: aload_1
    //   32: invokevirtual 140	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   35: ldc_w 328
    //   38: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: invokevirtual 117	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   44: invokespecial 197	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   47: athrow
    //   48: aconst_null
    //   49: astore_3
    //   50: aconst_null
    //   51: astore 4
    //   53: aconst_null
    //   54: astore 5
    //   56: aconst_null
    //   57: astore 6
    //   59: new 160	java/io/FileInputStream
    //   62: dup
    //   63: aload_0
    //   64: invokespecial 162	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   67: astore 7
    //   69: new 355	java/io/FileOutputStream
    //   72: dup
    //   73: aload_1
    //   74: invokespecial 492	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   77: astore 8
    //   79: aload 7
    //   81: invokevirtual 496	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   84: astore 5
    //   86: aload 8
    //   88: invokevirtual 497	java/io/FileOutputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   91: astore 6
    //   93: aload 5
    //   95: invokevirtual 501	java/nio/channels/FileChannel:size	()J
    //   98: lstore 10
    //   100: lconst_0
    //   101: lstore 12
    //   103: goto +179 -> 282
    //   106: aload 6
    //   108: aload 5
    //   110: lload 12
    //   112: lload 15
    //   114: invokevirtual 505	java/nio/channels/FileChannel:transferFrom	(Ljava/nio/channels/ReadableByteChannel;JJ)J
    //   117: lstore 17
    //   119: lload 12
    //   121: lload 17
    //   123: ladd
    //   124: lstore 12
    //   126: goto +156 -> 282
    //   129: lload 10
    //   131: lload 12
    //   133: lsub
    //   134: lstore 15
    //   136: goto -30 -> 106
    //   139: aload 6
    //   141: invokestatic 508	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/Closeable;)V
    //   144: aload 8
    //   146: invokestatic 359	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   149: aload 5
    //   151: invokestatic 508	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/Closeable;)V
    //   154: aload 7
    //   156: invokestatic 178	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   159: aload_0
    //   160: invokevirtual 211	java/io/File:length	()J
    //   163: aload_1
    //   164: invokevirtual 211	java/io/File:length	()J
    //   167: lcmp
    //   168: ifeq +71 -> 239
    //   171: new 154	java/io/IOException
    //   174: dup
    //   175: new 101	java/lang/StringBuilder
    //   178: dup
    //   179: invokespecial 102	java/lang/StringBuilder:<init>	()V
    //   182: ldc_w 510
    //   185: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   188: aload_0
    //   189: invokevirtual 140	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   192: ldc_w 512
    //   195: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   198: aload_1
    //   199: invokevirtual 140	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   202: ldc_w 514
    //   205: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   208: invokevirtual 117	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   211: invokespecial 197	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   214: athrow
    //   215: astore 9
    //   217: aload 6
    //   219: invokestatic 508	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/Closeable;)V
    //   222: aload 4
    //   224: invokestatic 359	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   227: aload 5
    //   229: invokestatic 508	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/Closeable;)V
    //   232: aload_3
    //   233: invokestatic 178	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   236: aload 9
    //   238: athrow
    //   239: iload_2
    //   240: ifeq +12 -> 252
    //   243: aload_1
    //   244: aload_0
    //   245: invokevirtual 487	java/io/File:lastModified	()J
    //   248: invokevirtual 491	java/io/File:setLastModified	(J)Z
    //   251: pop
    //   252: return
    //   253: astore 9
    //   255: aload 7
    //   257: astore_3
    //   258: aconst_null
    //   259: astore 6
    //   261: aconst_null
    //   262: astore 5
    //   264: aconst_null
    //   265: astore 4
    //   267: goto -50 -> 217
    //   270: astore 9
    //   272: aload 8
    //   274: astore 4
    //   276: aload 7
    //   278: astore_3
    //   279: goto -62 -> 217
    //   282: lload 12
    //   284: lload 10
    //   286: lcmp
    //   287: ifge -148 -> 139
    //   290: lload 10
    //   292: lload 12
    //   294: lsub
    //   295: ldc2_w 9
    //   298: lcmp
    //   299: ifle -170 -> 129
    //   302: ldc2_w 9
    //   305: lstore 15
    //   307: goto -201 -> 106
    //
    // Exception table:
    //   from	to	target	type
    //   59	69	215	finally
    //   69	79	253	finally
    //   79	100	270	finally
    //   106	119	270	finally
  }

  public static void forceDelete(File paramFile)
    throws IOException
  {
    if (paramFile.isDirectory())
      deleteDirectory(paramFile);
    boolean bool;
    do
    {
      return;
      bool = paramFile.exists();
    }
    while (paramFile.delete());
    if (!bool)
      throw new FileNotFoundException("File does not exist: " + paramFile);
    throw new IOException("Unable to delete file: " + paramFile);
  }

  public static void forceDeleteOnExit(File paramFile)
    throws IOException
  {
    if (paramFile.isDirectory())
    {
      deleteDirectoryOnExit(paramFile);
      return;
    }
    paramFile.deleteOnExit();
  }

  public static void forceMkdir(File paramFile)
    throws IOException
  {
    if (paramFile.exists())
    {
      if (!paramFile.isDirectory())
        throw new IOException("File " + paramFile + " exists and is " + "not a directory. Unable to create directory.");
    }
    else if ((!paramFile.mkdirs()) && (!paramFile.isDirectory()))
      throw new IOException("Unable to create directory " + paramFile);
  }

  public static File getFile(File paramFile, String[] paramArrayOfString)
  {
    if (paramFile == null)
      throw new NullPointerException("directorydirectory must not be null");
    if (paramArrayOfString == null)
      throw new NullPointerException("names must not be null");
    int i = paramArrayOfString.length;
    int j = 0;
    File localFile;
    for (Object localObject = paramFile; j < i; localObject = localFile)
    {
      localFile = new File((File)localObject, paramArrayOfString[j]);
      j++;
    }
    return (File)localObject;
  }

  public static File getFile(String[] paramArrayOfString)
  {
    if (paramArrayOfString == null)
      throw new NullPointerException("names must not be null");
    int i = paramArrayOfString.length;
    int j = 0;
    Object localObject = null;
    if (j < i)
    {
      String str = paramArrayOfString[j];
      if (localObject == null);
      for (File localFile = new File(str); ; localFile = new File(localObject, str))
      {
        j++;
        localObject = localFile;
        break;
      }
    }
    return localObject;
  }

  public static File getTempDirectory()
  {
    return new File(getTempDirectoryPath());
  }

  public static String getTempDirectoryPath()
  {
    return System.getProperty("java.io.tmpdir");
  }

  public static File getUserDirectory()
  {
    return new File(getUserDirectoryPath());
  }

  public static String getUserDirectoryPath()
  {
    return System.getProperty("user.home");
  }

  private static void innerListFiles(Collection<File> paramCollection, File paramFile, IOFileFilter paramIOFileFilter, boolean paramBoolean)
  {
    File[] arrayOfFile = paramFile.listFiles(paramIOFileFilter);
    if (arrayOfFile != null)
    {
      int i = arrayOfFile.length;
      int j = 0;
      if (j < i)
      {
        File localFile = arrayOfFile[j];
        if (localFile.isDirectory())
        {
          if (paramBoolean)
            paramCollection.add(localFile);
          innerListFiles(paramCollection, localFile, paramIOFileFilter, paramBoolean);
        }
        while (true)
        {
          j++;
          break;
          paramCollection.add(localFile);
        }
      }
    }
  }

  public static boolean isFileNewer(File paramFile, long paramLong)
  {
    if (paramFile == null)
      throw new IllegalArgumentException("No specified file");
    if (!paramFile.exists());
    do
      return false;
    while (paramFile.lastModified() <= paramLong);
    return true;
  }

  public static boolean isFileNewer(File paramFile1, File paramFile2)
  {
    if (paramFile2 == null)
      throw new IllegalArgumentException("No specified reference file");
    if (!paramFile2.exists())
      throw new IllegalArgumentException("The reference file '" + paramFile2 + "' doesn't exist");
    return isFileNewer(paramFile1, paramFile2.lastModified());
  }

  public static boolean isFileNewer(File paramFile, Date paramDate)
  {
    if (paramDate == null)
      throw new IllegalArgumentException("No specified date");
    return isFileNewer(paramFile, paramDate.getTime());
  }

  public static boolean isFileOlder(File paramFile, long paramLong)
  {
    if (paramFile == null)
      throw new IllegalArgumentException("No specified file");
    if (!paramFile.exists());
    do
      return false;
    while (paramFile.lastModified() >= paramLong);
    return true;
  }

  public static boolean isFileOlder(File paramFile1, File paramFile2)
  {
    if (paramFile2 == null)
      throw new IllegalArgumentException("No specified reference file");
    if (!paramFile2.exists())
      throw new IllegalArgumentException("The reference file '" + paramFile2 + "' doesn't exist");
    return isFileOlder(paramFile1, paramFile2.lastModified());
  }

  public static boolean isFileOlder(File paramFile, Date paramDate)
  {
    if (paramDate == null)
      throw new IllegalArgumentException("No specified date");
    return isFileOlder(paramFile, paramDate.getTime());
  }

  public static boolean isSymlink(File paramFile)
    throws IOException
  {
    if (paramFile == null)
      throw new NullPointerException("File must not be null");
    if (FilenameUtils.isSystemWindows());
    while (true)
    {
      return false;
      if (paramFile.getParent() == null);
      for (File localFile = paramFile; !localFile.getCanonicalFile().equals(localFile.getAbsoluteFile()); localFile = new File(paramFile.getParentFile().getCanonicalFile(), paramFile.getName()))
        return true;
    }
  }

  public static Iterator<File> iterateFiles(File paramFile, IOFileFilter paramIOFileFilter1, IOFileFilter paramIOFileFilter2)
  {
    return listFiles(paramFile, paramIOFileFilter1, paramIOFileFilter2).iterator();
  }

  public static Iterator<File> iterateFiles(File paramFile, String[] paramArrayOfString, boolean paramBoolean)
  {
    return listFiles(paramFile, paramArrayOfString, paramBoolean).iterator();
  }

  public static Iterator<File> iterateFilesAndDirs(File paramFile, IOFileFilter paramIOFileFilter1, IOFileFilter paramIOFileFilter2)
  {
    return listFilesAndDirs(paramFile, paramIOFileFilter1, paramIOFileFilter2).iterator();
  }

  public static LineIterator lineIterator(File paramFile)
    throws IOException
  {
    return lineIterator(paramFile, null);
  }

  public static LineIterator lineIterator(File paramFile, String paramString)
    throws IOException
  {
    FileInputStream localFileInputStream = null;
    try
    {
      localFileInputStream = openInputStream(paramFile);
      LineIterator localLineIterator = IOUtils.lineIterator(localFileInputStream, paramString);
      return localLineIterator;
    }
    catch (IOException localIOException)
    {
      IOUtils.closeQuietly(localFileInputStream);
      throw localIOException;
    }
    catch (RuntimeException localRuntimeException)
    {
      IOUtils.closeQuietly(localFileInputStream);
    }
    throw localRuntimeException;
  }

  public static Collection<File> listFiles(File paramFile, IOFileFilter paramIOFileFilter1, IOFileFilter paramIOFileFilter2)
  {
    validateListFilesParameters(paramFile, paramIOFileFilter1);
    IOFileFilter localIOFileFilter1 = setUpEffectiveFileFilter(paramIOFileFilter1);
    IOFileFilter localIOFileFilter2 = setUpEffectiveDirFilter(paramIOFileFilter2);
    LinkedList localLinkedList = new LinkedList();
    innerListFiles(localLinkedList, paramFile, FileFilterUtils.or(new IOFileFilter[] { localIOFileFilter1, localIOFileFilter2 }), false);
    return localLinkedList;
  }

  public static Collection<File> listFiles(File paramFile, String[] paramArrayOfString, boolean paramBoolean)
  {
    Object localObject;
    if (paramArrayOfString == null)
    {
      localObject = TrueFileFilter.INSTANCE;
      if (!paramBoolean)
        break label40;
    }
    label40: for (IOFileFilter localIOFileFilter = TrueFileFilter.INSTANCE; ; localIOFileFilter = FalseFileFilter.INSTANCE)
    {
      return listFiles(paramFile, (IOFileFilter)localObject, localIOFileFilter);
      localObject = new SuffixFileFilter(toSuffixes(paramArrayOfString));
      break;
    }
  }

  public static Collection<File> listFilesAndDirs(File paramFile, IOFileFilter paramIOFileFilter1, IOFileFilter paramIOFileFilter2)
  {
    validateListFilesParameters(paramFile, paramIOFileFilter1);
    IOFileFilter localIOFileFilter1 = setUpEffectiveFileFilter(paramIOFileFilter1);
    IOFileFilter localIOFileFilter2 = setUpEffectiveDirFilter(paramIOFileFilter2);
    LinkedList localLinkedList = new LinkedList();
    if (paramFile.isDirectory())
      localLinkedList.add(paramFile);
    innerListFiles(localLinkedList, paramFile, FileFilterUtils.or(new IOFileFilter[] { localIOFileFilter1, localIOFileFilter2 }), true);
    return localLinkedList;
  }

  public static void moveDirectory(File paramFile1, File paramFile2)
    throws IOException
  {
    if (paramFile1 == null)
      throw new NullPointerException("Source must not be null");
    if (paramFile2 == null)
      throw new NullPointerException("Destination must not be null");
    if (!paramFile1.exists())
      throw new FileNotFoundException("Source '" + paramFile1 + "' does not exist");
    if (!paramFile1.isDirectory())
      throw new IOException("Source '" + paramFile1 + "' is not a directory");
    if (paramFile2.exists())
      throw new FileExistsException("Destination '" + paramFile2 + "' already exists");
    if (!paramFile1.renameTo(paramFile2))
    {
      if (paramFile2.getCanonicalPath().startsWith(paramFile1.getCanonicalPath()))
        throw new IOException("Cannot move directory: " + paramFile1 + " to a subdirectory of itself: " + paramFile2);
      copyDirectory(paramFile1, paramFile2);
      deleteDirectory(paramFile1);
      if (paramFile1.exists())
        throw new IOException("Failed to delete original directory '" + paramFile1 + "' after copy to '" + paramFile2 + "'");
    }
  }

  public static void moveDirectoryToDirectory(File paramFile1, File paramFile2, boolean paramBoolean)
    throws IOException
  {
    if (paramFile1 == null)
      throw new NullPointerException("Source must not be null");
    if (paramFile2 == null)
      throw new NullPointerException("Destination directory must not be null");
    if ((!paramFile2.exists()) && (paramBoolean))
      paramFile2.mkdirs();
    if (!paramFile2.exists())
      throw new FileNotFoundException("Destination directory '" + paramFile2 + "' does not exist [createDestDir=" + paramBoolean + "]");
    if (!paramFile2.isDirectory())
      throw new IOException("Destination '" + paramFile2 + "' is not a directory");
    moveDirectory(paramFile1, new File(paramFile2, paramFile1.getName()));
  }

  public static void moveFile(File paramFile1, File paramFile2)
    throws IOException
  {
    if (paramFile1 == null)
      throw new NullPointerException("Source must not be null");
    if (paramFile2 == null)
      throw new NullPointerException("Destination must not be null");
    if (!paramFile1.exists())
      throw new FileNotFoundException("Source '" + paramFile1 + "' does not exist");
    if (paramFile1.isDirectory())
      throw new IOException("Source '" + paramFile1 + "' is a directory");
    if (paramFile2.exists())
      throw new FileExistsException("Destination '" + paramFile2 + "' already exists");
    if (paramFile2.isDirectory())
      throw new IOException("Destination '" + paramFile2 + "' is a directory");
    if (!paramFile1.renameTo(paramFile2))
    {
      copyFile(paramFile1, paramFile2);
      if (!paramFile1.delete())
      {
        deleteQuietly(paramFile2);
        throw new IOException("Failed to delete original file '" + paramFile1 + "' after copy to '" + paramFile2 + "'");
      }
    }
  }

  public static void moveFileToDirectory(File paramFile1, File paramFile2, boolean paramBoolean)
    throws IOException
  {
    if (paramFile1 == null)
      throw new NullPointerException("Source must not be null");
    if (paramFile2 == null)
      throw new NullPointerException("Destination directory must not be null");
    if ((!paramFile2.exists()) && (paramBoolean))
      paramFile2.mkdirs();
    if (!paramFile2.exists())
      throw new FileNotFoundException("Destination directory '" + paramFile2 + "' does not exist [createDestDir=" + paramBoolean + "]");
    if (!paramFile2.isDirectory())
      throw new IOException("Destination '" + paramFile2 + "' is not a directory");
    moveFile(paramFile1, new File(paramFile2, paramFile1.getName()));
  }

  public static void moveToDirectory(File paramFile1, File paramFile2, boolean paramBoolean)
    throws IOException
  {
    if (paramFile1 == null)
      throw new NullPointerException("Source must not be null");
    if (paramFile2 == null)
      throw new NullPointerException("Destination must not be null");
    if (!paramFile1.exists())
      throw new FileNotFoundException("Source '" + paramFile1 + "' does not exist");
    if (paramFile1.isDirectory())
    {
      moveDirectoryToDirectory(paramFile1, paramFile2, paramBoolean);
      return;
    }
    moveFileToDirectory(paramFile1, paramFile2, paramBoolean);
  }

  public static FileInputStream openInputStream(File paramFile)
    throws IOException
  {
    if (paramFile.exists())
    {
      if (paramFile.isDirectory())
        throw new IOException("File '" + paramFile + "' exists but is a directory");
      if (!paramFile.canRead())
        throw new IOException("File '" + paramFile + "' cannot be read");
    }
    else
    {
      throw new FileNotFoundException("File '" + paramFile + "' does not exist");
    }
    return new FileInputStream(paramFile);
  }

  public static FileOutputStream openOutputStream(File paramFile)
    throws IOException
  {
    return openOutputStream(paramFile, false);
  }

  public static FileOutputStream openOutputStream(File paramFile, boolean paramBoolean)
    throws IOException
  {
    if (paramFile.exists())
    {
      if (paramFile.isDirectory())
        throw new IOException("File '" + paramFile + "' exists but is a directory");
      if (!paramFile.canWrite())
        throw new IOException("File '" + paramFile + "' cannot be written to");
    }
    else
    {
      File localFile = paramFile.getParentFile();
      if ((localFile != null) && (!localFile.mkdirs()) && (!localFile.isDirectory()))
        throw new IOException("Directory '" + localFile + "' could not be created");
    }
    return new FileOutputStream(paramFile, paramBoolean);
  }

  public static byte[] readFileToByteArray(File paramFile)
    throws IOException
  {
    FileInputStream localFileInputStream = null;
    try
    {
      localFileInputStream = openInputStream(paramFile);
      byte[] arrayOfByte = IOUtils.toByteArray(localFileInputStream, paramFile.length());
      return arrayOfByte;
    }
    finally
    {
      IOUtils.closeQuietly(localFileInputStream);
    }
    throw localObject;
  }

  public static String readFileToString(File paramFile)
    throws IOException
  {
    return readFileToString(paramFile, Charset.defaultCharset());
  }

  public static String readFileToString(File paramFile, String paramString)
    throws IOException
  {
    return readFileToString(paramFile, Charsets.toCharset(paramString));
  }

  public static String readFileToString(File paramFile, Charset paramCharset)
    throws IOException
  {
    FileInputStream localFileInputStream = null;
    try
    {
      localFileInputStream = openInputStream(paramFile);
      String str = IOUtils.toString(localFileInputStream, Charsets.toCharset(paramCharset));
      return str;
    }
    finally
    {
      IOUtils.closeQuietly(localFileInputStream);
    }
    throw localObject;
  }

  public static List<String> readLines(File paramFile)
    throws IOException
  {
    return readLines(paramFile, Charset.defaultCharset());
  }

  public static List<String> readLines(File paramFile, String paramString)
    throws IOException
  {
    return readLines(paramFile, Charsets.toCharset(paramString));
  }

  public static List<String> readLines(File paramFile, Charset paramCharset)
    throws IOException
  {
    FileInputStream localFileInputStream = null;
    try
    {
      localFileInputStream = openInputStream(paramFile);
      List localList = IOUtils.readLines(localFileInputStream, Charsets.toCharset(paramCharset));
      return localList;
    }
    finally
    {
      IOUtils.closeQuietly(localFileInputStream);
    }
    throw localObject;
  }

  private static IOFileFilter setUpEffectiveDirFilter(IOFileFilter paramIOFileFilter)
  {
    if (paramIOFileFilter == null)
      return FalseFileFilter.INSTANCE;
    IOFileFilter[] arrayOfIOFileFilter = new IOFileFilter[2];
    arrayOfIOFileFilter[0] = paramIOFileFilter;
    arrayOfIOFileFilter[1] = DirectoryFileFilter.INSTANCE;
    return FileFilterUtils.and(arrayOfIOFileFilter);
  }

  private static IOFileFilter setUpEffectiveFileFilter(IOFileFilter paramIOFileFilter)
  {
    IOFileFilter[] arrayOfIOFileFilter = new IOFileFilter[2];
    arrayOfIOFileFilter[0] = paramIOFileFilter;
    arrayOfIOFileFilter[1] = FileFilterUtils.notFileFilter(DirectoryFileFilter.INSTANCE);
    return FileFilterUtils.and(arrayOfIOFileFilter);
  }

  public static long sizeOf(File paramFile)
  {
    if (!paramFile.exists())
      throw new IllegalArgumentException(paramFile + " does not exist");
    if (paramFile.isDirectory())
      return sizeOfDirectory(paramFile);
    return paramFile.length();
  }

  public static BigInteger sizeOfAsBigInteger(File paramFile)
  {
    if (!paramFile.exists())
      throw new IllegalArgumentException(paramFile + " does not exist");
    if (paramFile.isDirectory())
      return sizeOfDirectoryAsBigInteger(paramFile);
    return BigInteger.valueOf(paramFile.length());
  }

  public static long sizeOfDirectory(File paramFile)
  {
    checkDirectory(paramFile);
    File[] arrayOfFile = paramFile.listFiles();
    long l1;
    if (arrayOfFile == null)
      l1 = 0L;
    while (true)
    {
      return l1;
      l1 = 0L;
      int i = arrayOfFile.length;
      int j = 0;
      if (j >= i)
        continue;
      File localFile = arrayOfFile[j];
      try
      {
        if (!isSymlink(localFile))
        {
          long l2 = sizeOf(localFile);
          l1 += l2;
          if (l1 < 0L)
            continue;
        }
        label65: j++;
      }
      catch (IOException localIOException)
      {
        break label65;
      }
    }
  }

  public static BigInteger sizeOfDirectoryAsBigInteger(File paramFile)
  {
    checkDirectory(paramFile);
    File[] arrayOfFile = paramFile.listFiles();
    if (arrayOfFile == null)
    {
      localObject = BigInteger.ZERO;
      return localObject;
    }
    Object localObject = BigInteger.ZERO;
    int i = arrayOfFile.length;
    int j = 0;
    while (j < i)
    {
      File localFile = arrayOfFile[j];
      try
      {
        if (!isSymlink(localFile))
        {
          BigInteger localBigInteger = ((BigInteger)localObject).add(BigInteger.valueOf(sizeOf(localFile)));
          localObject = localBigInteger;
        }
        label66: j++;
      }
      catch (IOException localIOException)
      {
        break label66;
      }
    }
  }

  public static File toFile(URL paramURL)
  {
    if ((paramURL == null) || (!"file".equalsIgnoreCase(paramURL.getProtocol())))
      return null;
    return new File(decodeUrl(paramURL.getFile().replace('/', File.separatorChar)));
  }

  public static File[] toFiles(URL[] paramArrayOfURL)
  {
    File[] arrayOfFile;
    if ((paramArrayOfURL == null) || (paramArrayOfURL.length == 0))
      arrayOfFile = EMPTY_FILE_ARRAY;
    while (true)
    {
      return arrayOfFile;
      arrayOfFile = new File[paramArrayOfURL.length];
      for (int i = 0; i < paramArrayOfURL.length; i++)
      {
        URL localURL = paramArrayOfURL[i];
        if (localURL == null)
          continue;
        if (!localURL.getProtocol().equals("file"))
          throw new IllegalArgumentException("URL could not be converted to a File: " + localURL);
        arrayOfFile[i] = toFile(localURL);
      }
    }
  }

  private static String[] toSuffixes(String[] paramArrayOfString)
  {
    String[] arrayOfString = new String[paramArrayOfString.length];
    for (int i = 0; i < paramArrayOfString.length; i++)
      arrayOfString[i] = ("." + paramArrayOfString[i]);
    return arrayOfString;
  }

  public static URL[] toURLs(File[] paramArrayOfFile)
    throws IOException
  {
    URL[] arrayOfURL = new URL[paramArrayOfFile.length];
    for (int i = 0; i < arrayOfURL.length; i++)
      arrayOfURL[i] = paramArrayOfFile[i].toURI().toURL();
    return arrayOfURL;
  }

  public static void touch(File paramFile)
    throws IOException
  {
    if (!paramFile.exists())
      IOUtils.closeQuietly(openOutputStream(paramFile));
    if (!paramFile.setLastModified(System.currentTimeMillis()))
      throw new IOException("Unable to set the last modification time for " + paramFile);
  }

  private static void validateListFilesParameters(File paramFile, IOFileFilter paramIOFileFilter)
  {
    if (!paramFile.isDirectory())
      throw new IllegalArgumentException("Parameter 'directory' is not a directory");
    if (paramIOFileFilter == null)
      throw new NullPointerException("Parameter 'fileFilter' is null");
  }

  public static boolean waitFor(File paramFile, int paramInt)
  {
    int i = 0;
    int j = 0;
    int k;
    if (!paramFile.exists())
    {
      k = j + 1;
      if (j < 10)
        break label57;
      j = 0;
      int m = i + 1;
      if (i > paramInt)
        return false;
      i = m;
    }
    while (true)
    {
      try
      {
        Thread.sleep(100L);
      }
      catch (InterruptedException localInterruptedException)
      {
      }
      catch (Exception localException)
      {
      }
      return true;
      label57: j = k;
    }
  }

  public static void write(File paramFile, CharSequence paramCharSequence)
    throws IOException
  {
    write(paramFile, paramCharSequence, Charset.defaultCharset(), false);
  }

  public static void write(File paramFile, CharSequence paramCharSequence, String paramString)
    throws IOException
  {
    write(paramFile, paramCharSequence, paramString, false);
  }

  public static void write(File paramFile, CharSequence paramCharSequence, String paramString, boolean paramBoolean)
    throws IOException
  {
    write(paramFile, paramCharSequence, Charsets.toCharset(paramString), paramBoolean);
  }

  public static void write(File paramFile, CharSequence paramCharSequence, Charset paramCharset)
    throws IOException
  {
    write(paramFile, paramCharSequence, paramCharset, false);
  }

  public static void write(File paramFile, CharSequence paramCharSequence, Charset paramCharset, boolean paramBoolean)
    throws IOException
  {
    if (paramCharSequence == null);
    for (String str = null; ; str = paramCharSequence.toString())
    {
      writeStringToFile(paramFile, str, paramCharset, paramBoolean);
      return;
    }
  }

  public static void write(File paramFile, CharSequence paramCharSequence, boolean paramBoolean)
    throws IOException
  {
    write(paramFile, paramCharSequence, Charset.defaultCharset(), paramBoolean);
  }

  public static void writeByteArrayToFile(File paramFile, byte[] paramArrayOfByte)
    throws IOException
  {
    writeByteArrayToFile(paramFile, paramArrayOfByte, false);
  }

  public static void writeByteArrayToFile(File paramFile, byte[] paramArrayOfByte, boolean paramBoolean)
    throws IOException
  {
    FileOutputStream localFileOutputStream = null;
    try
    {
      localFileOutputStream = openOutputStream(paramFile, paramBoolean);
      localFileOutputStream.write(paramArrayOfByte);
      localFileOutputStream.close();
      return;
    }
    finally
    {
      IOUtils.closeQuietly(localFileOutputStream);
    }
    throw localObject;
  }

  public static void writeLines(File paramFile, String paramString, Collection<?> paramCollection)
    throws IOException
  {
    writeLines(paramFile, paramString, paramCollection, null, false);
  }

  public static void writeLines(File paramFile, String paramString1, Collection<?> paramCollection, String paramString2)
    throws IOException
  {
    writeLines(paramFile, paramString1, paramCollection, paramString2, false);
  }

  public static void writeLines(File paramFile, String paramString1, Collection<?> paramCollection, String paramString2, boolean paramBoolean)
    throws IOException
  {
    FileOutputStream localFileOutputStream = null;
    try
    {
      localFileOutputStream = openOutputStream(paramFile, paramBoolean);
      BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(localFileOutputStream);
      IOUtils.writeLines(paramCollection, paramString2, localBufferedOutputStream, paramString1);
      localBufferedOutputStream.flush();
      localFileOutputStream.close();
      return;
    }
    finally
    {
      IOUtils.closeQuietly(localFileOutputStream);
    }
    throw localObject;
  }

  public static void writeLines(File paramFile, String paramString, Collection<?> paramCollection, boolean paramBoolean)
    throws IOException
  {
    writeLines(paramFile, paramString, paramCollection, null, paramBoolean);
  }

  public static void writeLines(File paramFile, Collection<?> paramCollection)
    throws IOException
  {
    writeLines(paramFile, null, paramCollection, null, false);
  }

  public static void writeLines(File paramFile, Collection<?> paramCollection, String paramString)
    throws IOException
  {
    writeLines(paramFile, null, paramCollection, paramString, false);
  }

  public static void writeLines(File paramFile, Collection<?> paramCollection, String paramString, boolean paramBoolean)
    throws IOException
  {
    writeLines(paramFile, null, paramCollection, paramString, paramBoolean);
  }

  public static void writeLines(File paramFile, Collection<?> paramCollection, boolean paramBoolean)
    throws IOException
  {
    writeLines(paramFile, null, paramCollection, null, paramBoolean);
  }

  public static void writeStringToFile(File paramFile, String paramString)
    throws IOException
  {
    writeStringToFile(paramFile, paramString, Charset.defaultCharset(), false);
  }

  public static void writeStringToFile(File paramFile, String paramString1, String paramString2)
    throws IOException
  {
    writeStringToFile(paramFile, paramString1, paramString2, false);
  }

  public static void writeStringToFile(File paramFile, String paramString1, String paramString2, boolean paramBoolean)
    throws IOException
  {
    writeStringToFile(paramFile, paramString1, Charsets.toCharset(paramString2), paramBoolean);
  }

  public static void writeStringToFile(File paramFile, String paramString, Charset paramCharset)
    throws IOException
  {
    writeStringToFile(paramFile, paramString, paramCharset, false);
  }

  public static void writeStringToFile(File paramFile, String paramString, Charset paramCharset, boolean paramBoolean)
    throws IOException
  {
    FileOutputStream localFileOutputStream = null;
    try
    {
      localFileOutputStream = openOutputStream(paramFile, paramBoolean);
      IOUtils.write(paramString, localFileOutputStream, paramCharset);
      localFileOutputStream.close();
      return;
    }
    finally
    {
      IOUtils.closeQuietly(localFileOutputStream);
    }
    throw localObject;
  }

  public static void writeStringToFile(File paramFile, String paramString, boolean paramBoolean)
    throws IOException
  {
    writeStringToFile(paramFile, paramString, Charset.defaultCharset(), paramBoolean);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.FileUtils
 * JD-Core Version:    0.6.0
 */