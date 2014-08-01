package com.appbuilder.u846253p1176378;

import android.app.Activity;
import java.io.File;

public class DownloadHelper extends Thread
{
  private static final String ERR_FILE_CREATE_DIR = "ERR_FILE_CREATE_DIR";
  private static final String ERR_FILE_DELETE_EXCEPTION = "ERR_FILE_DELETE_EXCEPTION";
  private static final String ERR_FILE_EXISTS = "ERR_FILE_EXISTS";
  private static final String ERR_FILE_IO = "ERR_FILE_IO";
  private static final String ERR_HTTP_REQUEST_FAILED = "ERR_HTTP_REQUEST_FAILED";
  private static final String ERR_NO_ERROR = "ERR_NO_ERROR";
  private static final String ERR_ZIP_IO_ENTRY = "ERR_ZIP_IO_ENTRY";
  private static final String ERR_ZIP_READ_ENTRY = "ERR_ZIP_READ_ENTRY";
  private static final String ERR_ZIP_STREAM_CREATE = "ERR_ZIP_STREAM_CREATE";
  DownloadHelper _this;
  private DownloadHelperCallback mCallback;
  private String mDownloadPath;
  private String mErrorString;
  private Activity mHolder;
  private boolean mIsZip;
  private boolean mReplaceFile;
  private Runnable mRunFailed;
  private Runnable mRunStarted;
  private Runnable mRunSuccess;
  private String mSourceUrl;

  static
  {
    if (!DownloadHelper.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public DownloadHelper(Activity paramActivity, String paramString1, String paramString2, DownloadHelperCallback paramDownloadHelperCallback, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.mHolder = paramActivity;
    this.mSourceUrl = paramString1;
    this.mDownloadPath = paramString2;
    this.mCallback = paramDownloadHelperCallback;
    this.mIsZip = paramBoolean1;
    this.mReplaceFile = paramBoolean2;
    this._this = this;
    this.mErrorString = "ERR_NO_ERROR";
  }

  public static boolean deleteDirectory(File paramFile)
  {
    if (paramFile.exists())
    {
      File[] arrayOfFile = paramFile.listFiles();
      assert (arrayOfFile != null);
      int i = 0;
      if (i < arrayOfFile.length)
      {
        if (arrayOfFile[i].isDirectory())
          deleteDirectory(arrayOfFile[i]);
        while (true)
        {
          i++;
          break;
          arrayOfFile[i].delete();
        }
      }
    }
    return paramFile.delete();
  }

  private void processError(String paramString)
  {
    this.mErrorString = paramString;
    if ((this.mCallback != null) && (this.mHolder != null))
    {
      1 local1 = new Runnable(paramString)
      {
        public void run()
        {
          DownloadHelper.this.mCallback.DownloadHelperCallbackFailed(DownloadHelper.this._this, this.val$err);
        }
      };
      this.mHolder.runOnUiThread(local1);
    }
    if (this.mRunFailed != null)
    {
      assert (this.mHolder != null);
      this.mHolder.runOnUiThread(this.mRunFailed);
    }
    throw new RuntimeException();
  }

  // ERROR //
  private void processRawFile()
  {
    // Byte code:
    //   0: new 77	java/io/File
    //   3: dup
    //   4: aload_0
    //   5: getfield 61	com/appbuilder/u846253p1176378/DownloadHelper:mDownloadPath	Ljava/lang/String;
    //   8: invokespecial 122	java/io/File:<init>	(Ljava/lang/String;)V
    //   11: astore_1
    //   12: aload_1
    //   13: invokevirtual 80	java/io/File:exists	()Z
    //   16: ifeq +15 -> 31
    //   19: aload_0
    //   20: getfield 67	com/appbuilder/u846253p1176378/DownloadHelper:mReplaceFile	Z
    //   23: ifeq +88 -> 111
    //   26: aload_1
    //   27: invokevirtual 95	java/io/File:delete	()Z
    //   30: pop
    //   31: new 124	org/apache/http/client/methods/HttpGet
    //   34: dup
    //   35: aload_0
    //   36: getfield 59	com/appbuilder/u846253p1176378/DownloadHelper:mSourceUrl	Ljava/lang/String;
    //   39: invokespecial 125	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   42: astore_2
    //   43: aload_2
    //   44: ldc 127
    //   46: ldc 129
    //   48: invokevirtual 133	org/apache/http/client/methods/HttpGet:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   51: new 135	org/apache/http/impl/client/DefaultHttpClient
    //   54: dup
    //   55: invokespecial 136	org/apache/http/impl/client/DefaultHttpClient:<init>	()V
    //   58: astore_3
    //   59: aload_3
    //   60: invokevirtual 140	org/apache/http/impl/client/DefaultHttpClient:getParams	()Lorg/apache/http/params/HttpParams;
    //   63: ldc 142
    //   65: iconst_1
    //   66: invokeinterface 148 3 0
    //   71: pop
    //   72: aload_3
    //   73: aload_2
    //   74: invokevirtual 152	org/apache/http/impl/client/DefaultHttpClient:execute	(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
    //   77: astore 20
    //   79: aload 20
    //   81: astore 5
    //   83: aload 5
    //   85: ifnonnull +35 -> 120
    //   88: aload_0
    //   89: ldc 18
    //   91: invokespecial 154	com/appbuilder/u846253p1176378/DownloadHelper:processError	(Ljava/lang/String;)V
    //   94: return
    //   95: astore 21
    //   97: aload 21
    //   99: invokevirtual 157	java/lang/SecurityException:printStackTrace	()V
    //   102: aload_0
    //   103: ldc 12
    //   105: invokespecial 154	com/appbuilder/u846253p1176378/DownloadHelper:processError	(Ljava/lang/String;)V
    //   108: goto -77 -> 31
    //   111: aload_0
    //   112: ldc 14
    //   114: invokespecial 154	com/appbuilder/u846253p1176378/DownloadHelper:processError	(Ljava/lang/String;)V
    //   117: goto -86 -> 31
    //   120: aconst_null
    //   121: astore 6
    //   123: aconst_null
    //   124: astore 7
    //   126: new 159	java/io/BufferedInputStream
    //   129: dup
    //   130: aload 5
    //   132: invokeinterface 165 1 0
    //   137: invokeinterface 171 1 0
    //   142: invokespecial 174	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   145: astore 8
    //   147: new 176	java/io/FileOutputStream
    //   150: dup
    //   151: aload_1
    //   152: invokespecial 179	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   155: astore 9
    //   157: sipush 1024
    //   160: newarray byte
    //   162: astore 14
    //   164: aload 8
    //   166: aload 14
    //   168: invokevirtual 183	java/io/BufferedInputStream:read	([B)I
    //   171: istore 15
    //   173: iload 15
    //   175: ifle +59 -> 234
    //   178: aload 9
    //   180: aload 14
    //   182: iconst_0
    //   183: iload 15
    //   185: invokevirtual 189	java/io/OutputStream:write	([BII)V
    //   188: aload 9
    //   190: invokevirtual 192	java/io/OutputStream:flush	()V
    //   193: goto -29 -> 164
    //   196: astore 12
    //   198: aload 9
    //   200: astore 7
    //   202: aload 8
    //   204: astore 6
    //   206: aload_0
    //   207: ldc 16
    //   209: invokespecial 154	com/appbuilder/u846253p1176378/DownloadHelper:processError	(Ljava/lang/String;)V
    //   212: getstatic 51	com/appbuilder/u846253p1176378/DownloadHelper:$assertionsDisabled	Z
    //   215: ifne +68 -> 283
    //   218: aload 7
    //   220: ifnonnull +63 -> 283
    //   223: new 86	java/lang/AssertionError
    //   226: dup
    //   227: invokespecial 87	java/lang/AssertionError:<init>	()V
    //   230: athrow
    //   231: astore 13
    //   233: return
    //   234: getstatic 51	com/appbuilder/u846253p1176378/DownloadHelper:$assertionsDisabled	Z
    //   237: ifne +16 -> 253
    //   240: aload 9
    //   242: ifnonnull +11 -> 253
    //   245: new 86	java/lang/AssertionError
    //   248: dup
    //   249: invokespecial 87	java/lang/AssertionError:<init>	()V
    //   252: athrow
    //   253: aload 9
    //   255: invokevirtual 195	java/io/OutputStream:close	()V
    //   258: getstatic 51	com/appbuilder/u846253p1176378/DownloadHelper:$assertionsDisabled	Z
    //   261: ifne +16 -> 277
    //   264: aload 8
    //   266: ifnonnull +11 -> 277
    //   269: new 86	java/lang/AssertionError
    //   272: dup
    //   273: invokespecial 87	java/lang/AssertionError:<init>	()V
    //   276: athrow
    //   277: aload 8
    //   279: invokevirtual 196	java/io/BufferedInputStream:close	()V
    //   282: return
    //   283: aload 7
    //   285: invokevirtual 195	java/io/OutputStream:close	()V
    //   288: getstatic 51	com/appbuilder/u846253p1176378/DownloadHelper:$assertionsDisabled	Z
    //   291: ifne +16 -> 307
    //   294: aload 6
    //   296: ifnonnull +11 -> 307
    //   299: new 86	java/lang/AssertionError
    //   302: dup
    //   303: invokespecial 87	java/lang/AssertionError:<init>	()V
    //   306: athrow
    //   307: aload 6
    //   309: invokevirtual 196	java/io/BufferedInputStream:close	()V
    //   312: return
    //   313: astore 10
    //   315: getstatic 51	com/appbuilder/u846253p1176378/DownloadHelper:$assertionsDisabled	Z
    //   318: ifne +21 -> 339
    //   321: aload 7
    //   323: ifnonnull +16 -> 339
    //   326: new 86	java/lang/AssertionError
    //   329: dup
    //   330: invokespecial 87	java/lang/AssertionError:<init>	()V
    //   333: athrow
    //   334: astore 11
    //   336: aload 10
    //   338: athrow
    //   339: aload 7
    //   341: invokevirtual 195	java/io/OutputStream:close	()V
    //   344: getstatic 51	com/appbuilder/u846253p1176378/DownloadHelper:$assertionsDisabled	Z
    //   347: ifne +16 -> 363
    //   350: aload 6
    //   352: ifnonnull +11 -> 363
    //   355: new 86	java/lang/AssertionError
    //   358: dup
    //   359: invokespecial 87	java/lang/AssertionError:<init>	()V
    //   362: athrow
    //   363: aload 6
    //   365: invokevirtual 196	java/io/BufferedInputStream:close	()V
    //   368: goto -32 -> 336
    //   371: astore 10
    //   373: aload 8
    //   375: astore 6
    //   377: aconst_null
    //   378: astore 7
    //   380: goto -65 -> 315
    //   383: astore 10
    //   385: aload 9
    //   387: astore 7
    //   389: aload 8
    //   391: astore 6
    //   393: goto -78 -> 315
    //   396: astore 18
    //   398: aconst_null
    //   399: astore 6
    //   401: aconst_null
    //   402: astore 7
    //   404: goto -198 -> 206
    //   407: astore 17
    //   409: aload 8
    //   411: astore 6
    //   413: aconst_null
    //   414: astore 7
    //   416: goto -210 -> 206
    //   419: astore 4
    //   421: aconst_null
    //   422: astore 5
    //   424: goto -341 -> 83
    //   427: astore 16
    //   429: return
    //
    // Exception table:
    //   from	to	target	type
    //   26	31	95	java/lang/SecurityException
    //   157	164	196	java/lang/Exception
    //   164	173	196	java/lang/Exception
    //   178	193	196	java/lang/Exception
    //   212	218	231	java/io/IOException
    //   223	231	231	java/io/IOException
    //   283	294	231	java/io/IOException
    //   299	307	231	java/io/IOException
    //   307	312	231	java/io/IOException
    //   126	147	313	finally
    //   206	212	313	finally
    //   315	321	334	java/io/IOException
    //   326	334	334	java/io/IOException
    //   339	350	334	java/io/IOException
    //   355	363	334	java/io/IOException
    //   363	368	334	java/io/IOException
    //   147	157	371	finally
    //   157	164	383	finally
    //   164	173	383	finally
    //   178	193	383	finally
    //   126	147	396	java/lang/Exception
    //   147	157	407	java/lang/Exception
    //   51	79	419	java/io/IOException
    //   234	240	427	java/io/IOException
    //   245	253	427	java/io/IOException
    //   253	264	427	java/io/IOException
    //   269	277	427	java/io/IOException
    //   277	282	427	java/io/IOException
  }

  // ERROR //
  private void processZipFile()
  {
    // Byte code:
    //   0: new 77	java/io/File
    //   3: dup
    //   4: aload_0
    //   5: getfield 61	com/appbuilder/u846253p1176378/DownloadHelper:mDownloadPath	Ljava/lang/String;
    //   8: invokespecial 122	java/io/File:<init>	(Ljava/lang/String;)V
    //   11: astore_1
    //   12: aload_1
    //   13: invokevirtual 80	java/io/File:exists	()Z
    //   16: ifeq +15 -> 31
    //   19: aload_0
    //   20: getfield 67	com/appbuilder/u846253p1176378/DownloadHelper:mReplaceFile	Z
    //   23: ifeq +99 -> 122
    //   26: aload_1
    //   27: invokestatic 92	com/appbuilder/u846253p1176378/DownloadHelper:deleteDirectory	(Ljava/io/File;)Z
    //   30: pop
    //   31: aload_1
    //   32: invokevirtual 202	java/io/File:mkdir	()Z
    //   35: pop
    //   36: new 124	org/apache/http/client/methods/HttpGet
    //   39: dup
    //   40: aload_0
    //   41: getfield 59	com/appbuilder/u846253p1176378/DownloadHelper:mSourceUrl	Ljava/lang/String;
    //   44: invokespecial 125	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   47: astore 4
    //   49: aload 4
    //   51: ldc 127
    //   53: ldc 129
    //   55: invokevirtual 133	org/apache/http/client/methods/HttpGet:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   58: new 135	org/apache/http/impl/client/DefaultHttpClient
    //   61: dup
    //   62: invokespecial 136	org/apache/http/impl/client/DefaultHttpClient:<init>	()V
    //   65: astore 5
    //   67: aload 5
    //   69: invokevirtual 140	org/apache/http/impl/client/DefaultHttpClient:getParams	()Lorg/apache/http/params/HttpParams;
    //   72: ldc 142
    //   74: iconst_1
    //   75: invokeinterface 148 3 0
    //   80: pop
    //   81: aload 5
    //   83: aload 4
    //   85: invokevirtual 152	org/apache/http/impl/client/DefaultHttpClient:execute	(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
    //   88: astore 24
    //   90: aload 24
    //   92: astore 7
    //   94: aload 7
    //   96: ifnonnull +47 -> 143
    //   99: aload_0
    //   100: ldc 18
    //   102: invokespecial 154	com/appbuilder/u846253p1176378/DownloadHelper:processError	(Ljava/lang/String;)V
    //   105: return
    //   106: astore 25
    //   108: aload 25
    //   110: invokevirtual 157	java/lang/SecurityException:printStackTrace	()V
    //   113: aload_0
    //   114: ldc 12
    //   116: invokespecial 154	com/appbuilder/u846253p1176378/DownloadHelper:processError	(Ljava/lang/String;)V
    //   119: goto -88 -> 31
    //   122: aload_0
    //   123: ldc 14
    //   125: invokespecial 154	com/appbuilder/u846253p1176378/DownloadHelper:processError	(Ljava/lang/String;)V
    //   128: goto -97 -> 31
    //   131: astore_2
    //   132: aload_2
    //   133: invokevirtual 157	java/lang/SecurityException:printStackTrace	()V
    //   136: aload_0
    //   137: ldc 10
    //   139: invokespecial 154	com/appbuilder/u846253p1176378/DownloadHelper:processError	(Ljava/lang/String;)V
    //   142: return
    //   143: new 204	java/util/zip/ZipInputStream
    //   146: dup
    //   147: aload 7
    //   149: invokeinterface 165 1 0
    //   154: invokeinterface 171 1 0
    //   159: invokespecial 205	java/util/zip/ZipInputStream:<init>	(Ljava/io/InputStream;)V
    //   162: astore 8
    //   164: sipush 1024
    //   167: newarray byte
    //   169: astore 9
    //   171: aload 8
    //   173: invokevirtual 209	java/util/zip/ZipInputStream:getNextEntry	()Ljava/util/zip/ZipEntry;
    //   176: astore 11
    //   178: aload 11
    //   180: ifnonnull +30 -> 210
    //   183: aload 8
    //   185: invokevirtual 210	java/util/zip/ZipInputStream:close	()V
    //   188: return
    //   189: astore 21
    //   191: return
    //   192: astore 22
    //   194: aload_0
    //   195: ldc 26
    //   197: invokespecial 154	com/appbuilder/u846253p1176378/DownloadHelper:processError	(Ljava/lang/String;)V
    //   200: return
    //   201: astore 10
    //   203: aload_0
    //   204: ldc 24
    //   206: invokespecial 154	com/appbuilder/u846253p1176378/DownloadHelper:processError	(Ljava/lang/String;)V
    //   209: return
    //   210: aload 11
    //   212: invokevirtual 213	java/util/zip/ZipEntry:isDirectory	()Z
    //   215: ifeq +47 -> 262
    //   218: new 77	java/io/File
    //   221: dup
    //   222: new 215	java/lang/StringBuilder
    //   225: dup
    //   226: invokespecial 216	java/lang/StringBuilder:<init>	()V
    //   229: aload_0
    //   230: getfield 61	com/appbuilder/u846253p1176378/DownloadHelper:mDownloadPath	Ljava/lang/String;
    //   233: invokevirtual 220	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   236: aload 11
    //   238: invokevirtual 224	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   241: invokevirtual 220	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   244: invokevirtual 227	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   247: invokespecial 122	java/io/File:<init>	(Ljava/lang/String;)V
    //   250: invokevirtual 230	java/io/File:mkdirs	()Z
    //   253: pop
    //   254: goto -83 -> 171
    //   257: astore 19
    //   259: goto -88 -> 171
    //   262: new 215	java/lang/StringBuilder
    //   265: dup
    //   266: invokespecial 216	java/lang/StringBuilder:<init>	()V
    //   269: aload_0
    //   270: getfield 61	com/appbuilder/u846253p1176378/DownloadHelper:mDownloadPath	Ljava/lang/String;
    //   273: invokevirtual 220	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   276: aload 11
    //   278: invokevirtual 224	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   281: invokevirtual 220	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   284: invokevirtual 227	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   287: astore 12
    //   289: new 176	java/io/FileOutputStream
    //   292: dup
    //   293: aload 12
    //   295: invokespecial 231	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   298: astore 13
    //   300: aload 13
    //   302: astore 14
    //   304: aload 14
    //   306: ifnonnull +10 -> 316
    //   309: aload_0
    //   310: ldc 24
    //   312: invokespecial 154	com/appbuilder/u846253p1176378/DownloadHelper:processError	(Ljava/lang/String;)V
    //   315: return
    //   316: aload 8
    //   318: aload 9
    //   320: invokevirtual 232	java/util/zip/ZipInputStream:read	([B)I
    //   323: istore 16
    //   325: iload 16
    //   327: ifle +25 -> 352
    //   330: aload 14
    //   332: aload 9
    //   334: iconst_0
    //   335: iload 16
    //   337: invokevirtual 189	java/io/OutputStream:write	([BII)V
    //   340: goto -24 -> 316
    //   343: astore 15
    //   345: aload_0
    //   346: ldc 22
    //   348: invokespecial 154	com/appbuilder/u846253p1176378/DownloadHelper:processError	(Ljava/lang/String;)V
    //   351: return
    //   352: aload 14
    //   354: invokevirtual 192	java/io/OutputStream:flush	()V
    //   357: goto -186 -> 171
    //   360: astore 18
    //   362: aconst_null
    //   363: astore 14
    //   365: goto -61 -> 304
    //   368: astore 17
    //   370: aconst_null
    //   371: astore 14
    //   373: goto -69 -> 304
    //   376: astore 6
    //   378: aconst_null
    //   379: astore 7
    //   381: goto -287 -> 94
    //
    // Exception table:
    //   from	to	target	type
    //   26	31	106	java/lang/SecurityException
    //   31	36	131	java/lang/SecurityException
    //   183	188	189	java/io/IOException
    //   143	164	192	java/io/IOException
    //   171	178	201	java/io/IOException
    //   218	254	257	java/lang/SecurityException
    //   316	325	343	java/io/IOException
    //   330	340	343	java/io/IOException
    //   352	357	343	java/io/IOException
    //   289	300	360	java/lang/SecurityException
    //   289	300	368	java/io/FileNotFoundException
    //   58	90	376	java/io/IOException
  }

  public DownloadHelperCallback getCallback()
  {
    return this.mCallback;
  }

  public String getDownloadPath()
  {
    return this.mDownloadPath;
  }

  public final String getErrorString()
  {
    return this.mErrorString;
  }

  public boolean getIsZip()
  {
    return this.mIsZip;
  }

  public boolean getReplaceFile()
  {
    return this.mReplaceFile;
  }

  public String getSourceUrl()
  {
    return this.mSourceUrl;
  }

  public void run()
  {
    if ((this.mCallback != null) && (this.mHolder != null))
    {
      2 local2 = new Runnable()
      {
        public void run()
        {
          DownloadHelper.this.mCallback.DownloadHelperCallbackStarted(DownloadHelper.this._this);
        }
      };
      this.mHolder.runOnUiThread(local2);
    }
    if (this.mRunStarted != null)
    {
      assert (this.mHolder != null);
      this.mHolder.runOnUiThread(this.mRunStarted);
    }
    try
    {
      if (this.mIsZip)
        processZipFile();
      while (true)
      {
        if ((this.mCallback != null) && (this.mHolder != null))
        {
          3 local3 = new Runnable()
          {
            public void run()
            {
              DownloadHelper.this.mCallback.DownloadHelperCallbackSuccess(DownloadHelper.this._this);
            }
          };
          this.mHolder.runOnUiThread(local3);
        }
        if (this.mRunSuccess != null)
          this.mHolder.runOnUiThread(this.mRunSuccess);
        return;
        processRawFile();
      }
    }
    catch (RuntimeException localRuntimeException)
    {
    }
  }

  public void setFailedRunnable(Runnable paramRunnable)
  {
    this.mRunFailed = paramRunnable;
  }

  public void setStartedRunnable(Runnable paramRunnable)
  {
    this.mRunStarted = paramRunnable;
  }

  public void setSuccessRunnable(Runnable paramRunnable)
  {
    this.mRunSuccess = paramRunnable;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.DownloadHelper
 * JD-Core Version:    0.6.0
 */