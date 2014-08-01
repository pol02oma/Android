package io.vov.vitamio.provider;

import android.net.Uri;
import android.os.Environment;
import io.vov.vitamio.utils.Log;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public class MiniThumbFile
{
  protected static final int BYTES_PER_MINTHUMB = 10000;
  private static final int HEADER_SIZE = 13;
  private static final int MINI_THUMB_DATA_FILE_VERSION = 7;
  private static Hashtable<String, MiniThumbFile> sThumbFiles = new Hashtable();
  private ByteBuffer mBuffer;
  private FileChannel mChannel;
  private RandomAccessFile mMiniThumbFile;
  private Uri mUri;

  public MiniThumbFile(Uri paramUri)
  {
    this.mUri = paramUri;
    this.mBuffer = ByteBuffer.allocateDirect(10000);
  }

  protected static MiniThumbFile instance(Uri paramUri)
  {
    monitorenter;
    try
    {
      String str = (String)paramUri.getPathSegments().get(0);
      MiniThumbFile localMiniThumbFile = (MiniThumbFile)sThumbFiles.get(str);
      if (localMiniThumbFile == null)
      {
        localMiniThumbFile = new MiniThumbFile(Uri.parse("content://me.abitno.vplayer.mediaprovider/" + str + "/media"));
        sThumbFiles.put(str, localMiniThumbFile);
      }
      return localMiniThumbFile;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private RandomAccessFile miniThumbDataFile()
  {
    File localFile2;
    if (this.mMiniThumbFile == null)
    {
      removeOldFile();
      String str = randomAccessFilePath(7);
      File localFile1 = new File(str).getParentFile();
      if ((!localFile1.isDirectory()) && (!localFile1.mkdirs()))
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localFile1.toString();
        Log.e("Unable to create .thumbnails directory %s", arrayOfObject);
      }
      localFile2 = new File(str);
    }
    try
    {
      this.mMiniThumbFile = new RandomAccessFile(localFile2, "rw");
      if (this.mMiniThumbFile != null)
        this.mChannel = this.mMiniThumbFile.getChannel();
      return this.mMiniThumbFile;
    }
    catch (IOException localIOException1)
    {
      while (true)
        try
        {
          this.mMiniThumbFile = new RandomAccessFile(localFile2, "r");
        }
        catch (IOException localIOException2)
        {
        }
    }
  }

  private String randomAccessFilePath(int paramInt)
  {
    String str = Environment.getExternalStorageDirectory().toString() + "/" + "Android/data/me.abitno.vplayer.t/thumbnails";
    return str + "/.thumbdata" + paramInt + "-" + this.mUri.hashCode();
  }

  private void removeOldFile()
  {
    File localFile = new File(randomAccessFilePath(6));
    if (localFile.exists());
    try
    {
      localFile.delete();
      return;
    }
    catch (SecurityException localSecurityException)
    {
    }
  }

  protected static void reset()
  {
    monitorenter;
    try
    {
      Iterator localIterator = sThumbFiles.values().iterator();
      while (localIterator.hasNext())
        ((MiniThumbFile)localIterator.next()).deactivate();
    }
    finally
    {
      monitorexit;
    }
    sThumbFiles.clear();
    monitorexit;
  }

  // ERROR //
  protected void deactivate()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 93	io/vov/vitamio/provider/MiniThumbFile:mMiniThumbFile	Ljava/io/RandomAccessFile;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnull +15 -> 23
    //   11: aload_0
    //   12: getfield 93	io/vov/vitamio/provider/MiniThumbFile:mMiniThumbFile	Ljava/io/RandomAccessFile;
    //   15: invokevirtual 197	java/io/RandomAccessFile:close	()V
    //   18: aload_0
    //   19: aconst_null
    //   20: putfield 93	io/vov/vitamio/provider/MiniThumbFile:mMiniThumbFile	Ljava/io/RandomAccessFile;
    //   23: aload_0
    //   24: monitorexit
    //   25: return
    //   26: astore_1
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_1
    //   30: athrow
    //   31: astore_3
    //   32: goto -9 -> 23
    //
    // Exception table:
    //   from	to	target	type
    //   2	7	26	finally
    //   11	23	26	finally
    //   11	23	31	java/io/IOException
  }

  // ERROR //
  protected long getMagic(long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial 203	io/vov/vitamio/provider/MiniThumbFile:miniThumbDataFile	()Ljava/io/RandomAccessFile;
    //   6: astore 4
    //   8: aload 4
    //   10: ifnull +122 -> 132
    //   13: lload_1
    //   14: ldc2_w 204
    //   17: lmul
    //   18: lstore 7
    //   20: aconst_null
    //   21: astore 9
    //   23: aload_0
    //   24: getfield 43	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   27: invokevirtual 208	java/nio/ByteBuffer:clear	()Ljava/nio/Buffer;
    //   30: pop
    //   31: aload_0
    //   32: getfield 43	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   35: bipush 9
    //   37: invokevirtual 212	java/nio/ByteBuffer:limit	(I)Ljava/nio/Buffer;
    //   40: pop
    //   41: aload_0
    //   42: getfield 138	io/vov/vitamio/provider/MiniThumbFile:mChannel	Ljava/nio/channels/FileChannel;
    //   45: lload 7
    //   47: ldc2_w 213
    //   50: iconst_1
    //   51: invokevirtual 220	java/nio/channels/FileChannel:lock	(JJZ)Ljava/nio/channels/FileLock;
    //   54: astore 9
    //   56: aload_0
    //   57: getfield 138	io/vov/vitamio/provider/MiniThumbFile:mChannel	Ljava/nio/channels/FileChannel;
    //   60: aload_0
    //   61: getfield 43	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   64: lload 7
    //   66: invokevirtual 224	java/nio/channels/FileChannel:read	(Ljava/nio/ByteBuffer;J)I
    //   69: bipush 9
    //   71: if_icmpne +51 -> 122
    //   74: aload_0
    //   75: getfield 43	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   78: iconst_0
    //   79: invokevirtual 227	java/nio/ByteBuffer:position	(I)Ljava/nio/Buffer;
    //   82: pop
    //   83: aload_0
    //   84: getfield 43	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   87: invokevirtual 230	java/nio/ByteBuffer:get	()B
    //   90: iconst_1
    //   91: if_icmpne +31 -> 122
    //   94: aload_0
    //   95: getfield 43	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   98: invokevirtual 234	java/nio/ByteBuffer:getLong	()J
    //   101: lstore 21
    //   103: lload 21
    //   105: lstore 5
    //   107: aload 9
    //   109: ifnull +8 -> 117
    //   112: aload 9
    //   114: invokevirtual 239	java/nio/channels/FileLock:release	()V
    //   117: aload_0
    //   118: monitorexit
    //   119: lload 5
    //   121: lreturn
    //   122: aload 9
    //   124: ifnull +8 -> 132
    //   127: aload 9
    //   129: invokevirtual 239	java/nio/channels/FileLock:release	()V
    //   132: lconst_0
    //   133: lstore 5
    //   135: goto -18 -> 117
    //   138: astore 15
    //   140: ldc 241
    //   142: aload 15
    //   144: invokestatic 244	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   147: aload 9
    //   149: ifnull -17 -> 132
    //   152: aload 9
    //   154: invokevirtual 239	java/nio/channels/FileLock:release	()V
    //   157: goto -25 -> 132
    //   160: astore 16
    //   162: goto -30 -> 132
    //   165: astore 12
    //   167: iconst_2
    //   168: anewarray 4	java/lang/Object
    //   171: astore 13
    //   173: aload 13
    //   175: iconst_0
    //   176: lload_1
    //   177: invokestatic 250	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   180: aastore
    //   181: aload 13
    //   183: iconst_1
    //   184: aload 12
    //   186: invokevirtual 254	java/lang/Object:getClass	()Ljava/lang/Class;
    //   189: invokevirtual 257	java/lang/Class:toString	()Ljava/lang/String;
    //   192: aastore
    //   193: ldc_w 259
    //   196: aload 13
    //   198: invokestatic 125	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   201: aload 9
    //   203: ifnull -71 -> 132
    //   206: aload 9
    //   208: invokevirtual 239	java/nio/channels/FileLock:release	()V
    //   211: goto -79 -> 132
    //   214: astore 14
    //   216: goto -84 -> 132
    //   219: astore 10
    //   221: aload 9
    //   223: ifnull +8 -> 231
    //   226: aload 9
    //   228: invokevirtual 239	java/nio/channels/FileLock:release	()V
    //   231: aload 10
    //   233: athrow
    //   234: astore_3
    //   235: aload_0
    //   236: monitorexit
    //   237: aload_3
    //   238: athrow
    //   239: astore 23
    //   241: goto -124 -> 117
    //   244: astore 19
    //   246: goto -114 -> 132
    //   249: astore 11
    //   251: goto -20 -> 231
    //
    // Exception table:
    //   from	to	target	type
    //   23	103	138	java/io/IOException
    //   152	157	160	java/io/IOException
    //   23	103	165	java/lang/RuntimeException
    //   206	211	214	java/io/IOException
    //   23	103	219	finally
    //   140	147	219	finally
    //   167	201	219	finally
    //   2	8	234	finally
    //   112	117	234	finally
    //   127	132	234	finally
    //   152	157	234	finally
    //   206	211	234	finally
    //   226	231	234	finally
    //   231	234	234	finally
    //   112	117	239	java/io/IOException
    //   127	132	244	java/io/IOException
    //   226	231	249	java/io/IOException
  }

  // ERROR //
  protected byte[] getMiniThumbFromFile(long paramLong, byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial 203	io/vov/vitamio/provider/MiniThumbFile:miniThumbDataFile	()Ljava/io/RandomAccessFile;
    //   6: astore 5
    //   8: aload 5
    //   10: ifnonnull +9 -> 19
    //   13: aconst_null
    //   14: astore_3
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_3
    //   18: areturn
    //   19: lload_1
    //   20: ldc2_w 204
    //   23: lmul
    //   24: lstore 6
    //   26: aconst_null
    //   27: astore 8
    //   29: aload_0
    //   30: getfield 43	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   33: invokevirtual 208	java/nio/ByteBuffer:clear	()Ljava/nio/Buffer;
    //   36: pop
    //   37: aload_0
    //   38: getfield 138	io/vov/vitamio/provider/MiniThumbFile:mChannel	Ljava/nio/channels/FileChannel;
    //   41: lload 6
    //   43: ldc2_w 204
    //   46: iconst_1
    //   47: invokevirtual 220	java/nio/channels/FileChannel:lock	(JJZ)Ljava/nio/channels/FileLock;
    //   50: astore 8
    //   52: aload_0
    //   53: getfield 138	io/vov/vitamio/provider/MiniThumbFile:mChannel	Ljava/nio/channels/FileChannel;
    //   56: aload_0
    //   57: getfield 43	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   60: lload 6
    //   62: invokevirtual 224	java/nio/channels/FileChannel:read	(Ljava/nio/ByteBuffer;J)I
    //   65: istore 18
    //   67: iload 18
    //   69: bipush 13
    //   71: if_icmple +69 -> 140
    //   74: aload_0
    //   75: getfield 43	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   78: bipush 9
    //   80: invokevirtual 227	java/nio/ByteBuffer:position	(I)Ljava/nio/Buffer;
    //   83: pop
    //   84: aload_0
    //   85: getfield 43	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   88: invokevirtual 264	java/nio/ByteBuffer:getInt	()I
    //   91: istore 20
    //   93: iload 18
    //   95: iload 20
    //   97: bipush 13
    //   99: iadd
    //   100: if_icmplt +40 -> 140
    //   103: aload_3
    //   104: arraylength
    //   105: iload 20
    //   107: if_icmplt +33 -> 140
    //   110: aload_0
    //   111: getfield 43	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   114: aload_3
    //   115: iconst_0
    //   116: iload 20
    //   118: invokevirtual 267	java/nio/ByteBuffer:get	([BII)Ljava/nio/ByteBuffer;
    //   121: pop
    //   122: aload 8
    //   124: ifnull -109 -> 15
    //   127: aload 8
    //   129: invokevirtual 239	java/nio/channels/FileLock:release	()V
    //   132: goto -117 -> 15
    //   135: astore 23
    //   137: goto -122 -> 15
    //   140: aload 8
    //   142: ifnull +8 -> 150
    //   145: aload 8
    //   147: invokevirtual 239	java/nio/channels/FileLock:release	()V
    //   150: aconst_null
    //   151: astore_3
    //   152: goto -137 -> 15
    //   155: astore 14
    //   157: iconst_2
    //   158: anewarray 4	java/lang/Object
    //   161: astore 15
    //   163: aload 15
    //   165: iconst_0
    //   166: lload_1
    //   167: invokestatic 250	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   170: aastore
    //   171: aload 15
    //   173: iconst_1
    //   174: aload 14
    //   176: invokevirtual 270	java/io/IOException:getMessage	()Ljava/lang/String;
    //   179: aastore
    //   180: ldc_w 272
    //   183: aload 15
    //   185: invokestatic 125	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   188: aload 8
    //   190: ifnull -40 -> 150
    //   193: aload 8
    //   195: invokevirtual 239	java/nio/channels/FileLock:release	()V
    //   198: goto -48 -> 150
    //   201: astore 16
    //   203: goto -53 -> 150
    //   206: astore 11
    //   208: iconst_2
    //   209: anewarray 4	java/lang/Object
    //   212: astore 12
    //   214: aload 12
    //   216: iconst_0
    //   217: lload_1
    //   218: invokestatic 250	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   221: aastore
    //   222: aload 12
    //   224: iconst_1
    //   225: aload 11
    //   227: invokevirtual 254	java/lang/Object:getClass	()Ljava/lang/Class;
    //   230: invokevirtual 257	java/lang/Class:toString	()Ljava/lang/String;
    //   233: aastore
    //   234: ldc_w 274
    //   237: aload 12
    //   239: invokestatic 125	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   242: aload 8
    //   244: ifnull -94 -> 150
    //   247: aload 8
    //   249: invokevirtual 239	java/nio/channels/FileLock:release	()V
    //   252: goto -102 -> 150
    //   255: astore 13
    //   257: goto -107 -> 150
    //   260: astore 9
    //   262: aload 8
    //   264: ifnull +8 -> 272
    //   267: aload 8
    //   269: invokevirtual 239	java/nio/channels/FileLock:release	()V
    //   272: aload 9
    //   274: athrow
    //   275: astore 4
    //   277: aload_0
    //   278: monitorexit
    //   279: aload 4
    //   281: athrow
    //   282: astore 21
    //   284: goto -134 -> 150
    //   287: astore 10
    //   289: goto -17 -> 272
    //
    // Exception table:
    //   from	to	target	type
    //   127	132	135	java/io/IOException
    //   29	67	155	java/io/IOException
    //   74	93	155	java/io/IOException
    //   103	122	155	java/io/IOException
    //   193	198	201	java/io/IOException
    //   29	67	206	java/lang/RuntimeException
    //   74	93	206	java/lang/RuntimeException
    //   103	122	206	java/lang/RuntimeException
    //   247	252	255	java/io/IOException
    //   29	67	260	finally
    //   74	93	260	finally
    //   103	122	260	finally
    //   157	188	260	finally
    //   208	242	260	finally
    //   2	8	275	finally
    //   127	132	275	finally
    //   145	150	275	finally
    //   193	198	275	finally
    //   247	252	275	finally
    //   267	272	275	finally
    //   272	275	275	finally
    //   145	150	282	java/io/IOException
    //   267	272	287	java/io/IOException
  }

  // ERROR //
  protected void saveMiniThumbToFile(byte[] paramArrayOfByte, long paramLong1, long paramLong2)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial 203	io/vov/vitamio/provider/MiniThumbFile:miniThumbDataFile	()Ljava/io/RandomAccessFile;
    //   6: astore 7
    //   8: aload 7
    //   10: ifnonnull +6 -> 16
    //   13: aload_0
    //   14: monitorexit
    //   15: return
    //   16: lload_2
    //   17: ldc2_w 204
    //   20: lmul
    //   21: lstore 8
    //   23: aconst_null
    //   24: astore 10
    //   26: aload_1
    //   27: ifnull +114 -> 141
    //   30: aload_1
    //   31: arraylength
    //   32: istore 19
    //   34: iload 19
    //   36: sipush 9987
    //   39: if_icmple +19 -> 58
    //   42: iconst_0
    //   43: ifeq -30 -> 13
    //   46: aconst_null
    //   47: invokevirtual 239	java/nio/channels/FileLock:release	()V
    //   50: goto -37 -> 13
    //   53: astore 27
    //   55: goto -42 -> 13
    //   58: aload_0
    //   59: getfield 43	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   62: invokevirtual 208	java/nio/ByteBuffer:clear	()Ljava/nio/Buffer;
    //   65: pop
    //   66: aload_0
    //   67: getfield 43	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   70: iconst_1
    //   71: invokevirtual 279	java/nio/ByteBuffer:put	(B)Ljava/nio/ByteBuffer;
    //   74: pop
    //   75: aload_0
    //   76: getfield 43	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   79: lload 4
    //   81: invokevirtual 283	java/nio/ByteBuffer:putLong	(J)Ljava/nio/ByteBuffer;
    //   84: pop
    //   85: aload_0
    //   86: getfield 43	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   89: aload_1
    //   90: arraylength
    //   91: invokevirtual 286	java/nio/ByteBuffer:putInt	(I)Ljava/nio/ByteBuffer;
    //   94: pop
    //   95: aload_0
    //   96: getfield 43	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   99: aload_1
    //   100: invokevirtual 289	java/nio/ByteBuffer:put	([B)Ljava/nio/ByteBuffer;
    //   103: pop
    //   104: aload_0
    //   105: getfield 43	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   108: invokevirtual 292	java/nio/ByteBuffer:flip	()Ljava/nio/Buffer;
    //   111: pop
    //   112: aload_0
    //   113: getfield 138	io/vov/vitamio/provider/MiniThumbFile:mChannel	Ljava/nio/channels/FileChannel;
    //   116: lload 8
    //   118: ldc2_w 204
    //   121: iconst_0
    //   122: invokevirtual 220	java/nio/channels/FileChannel:lock	(JJZ)Ljava/nio/channels/FileLock;
    //   125: astore 10
    //   127: aload_0
    //   128: getfield 138	io/vov/vitamio/provider/MiniThumbFile:mChannel	Ljava/nio/channels/FileChannel;
    //   131: aload_0
    //   132: getfield 43	io/vov/vitamio/provider/MiniThumbFile:mBuffer	Ljava/nio/ByteBuffer;
    //   135: lload 8
    //   137: invokevirtual 295	java/nio/channels/FileChannel:write	(Ljava/nio/ByteBuffer;J)I
    //   140: pop
    //   141: aload 10
    //   143: ifnull -130 -> 13
    //   146: aload 10
    //   148: invokevirtual 239	java/nio/channels/FileLock:release	()V
    //   151: goto -138 -> 13
    //   154: astore 11
    //   156: goto -143 -> 13
    //   159: astore 17
    //   161: iconst_2
    //   162: anewarray 4	java/lang/Object
    //   165: astore 18
    //   167: aload 18
    //   169: iconst_0
    //   170: lload_2
    //   171: invokestatic 250	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   174: aastore
    //   175: aload 18
    //   177: iconst_1
    //   178: aload 17
    //   180: invokevirtual 270	java/io/IOException:getMessage	()Ljava/lang/String;
    //   183: aastore
    //   184: ldc_w 297
    //   187: aload 18
    //   189: invokestatic 125	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   192: aload 17
    //   194: athrow
    //   195: astore 15
    //   197: aload 10
    //   199: ifnull +8 -> 207
    //   202: aload 10
    //   204: invokevirtual 239	java/nio/channels/FileLock:release	()V
    //   207: aload 15
    //   209: athrow
    //   210: astore 6
    //   212: aload_0
    //   213: monitorexit
    //   214: aload 6
    //   216: athrow
    //   217: astore 12
    //   219: iconst_2
    //   220: anewarray 4	java/lang/Object
    //   223: astore 13
    //   225: aload 13
    //   227: iconst_0
    //   228: lload_2
    //   229: invokestatic 250	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   232: aastore
    //   233: aload 13
    //   235: iconst_1
    //   236: aload 12
    //   238: invokevirtual 254	java/lang/Object:getClass	()Ljava/lang/Class;
    //   241: invokevirtual 257	java/lang/Class:toString	()Ljava/lang/String;
    //   244: aastore
    //   245: ldc_w 299
    //   248: aload 13
    //   250: invokestatic 125	io/vov/vitamio/utils/Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   253: aload 10
    //   255: ifnull -242 -> 13
    //   258: aload 10
    //   260: invokevirtual 239	java/nio/channels/FileLock:release	()V
    //   263: goto -250 -> 13
    //   266: astore 14
    //   268: goto -255 -> 13
    //   271: astore 16
    //   273: goto -66 -> 207
    //
    // Exception table:
    //   from	to	target	type
    //   46	50	53	java/io/IOException
    //   146	151	154	java/io/IOException
    //   30	34	159	java/io/IOException
    //   58	141	159	java/io/IOException
    //   30	34	195	finally
    //   58	141	195	finally
    //   161	195	195	finally
    //   219	253	195	finally
    //   2	8	210	finally
    //   46	50	210	finally
    //   146	151	210	finally
    //   202	207	210	finally
    //   207	210	210	finally
    //   258	263	210	finally
    //   30	34	217	java/lang/RuntimeException
    //   58	141	217	java/lang/RuntimeException
    //   258	263	266	java/io/IOException
    //   202	207	271	java/io/IOException
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     io.vov.vitamio.provider.MiniThumbFile
 * JD-Core Version:    0.6.0
 */