package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.gms.internal.c.j;

class co
  implements Runnable
{
  private final String TM;
  private volatile String Ui;
  private final bm Wg;
  private final String Wh;
  private bg<c.j> Wi;
  private volatile r Wj;
  private volatile String Wk;
  private final Context mContext;

  co(Context paramContext, String paramString, bm parambm, r paramr)
  {
    this.mContext = paramContext;
    this.Wg = parambm;
    this.TM = paramString;
    this.Wj = paramr;
    this.Wh = ("/r?id=" + paramString);
    this.Ui = this.Wh;
    this.Wk = null;
  }

  public co(Context paramContext, String paramString, r paramr)
  {
    this(paramContext, paramString, new bm(), paramr);
  }

  private boolean jx()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo == null) || (!localNetworkInfo.isConnected()))
    {
      bh.v("...no network connectivity");
      return false;
    }
    return true;
  }

  // ERROR //
  private void jy()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 94	com/google/android/gms/tagmanager/co:jx	()Z
    //   4: ifne +16 -> 20
    //   7: aload_0
    //   8: getfield 96	com/google/android/gms/tagmanager/co:Wi	Lcom/google/android/gms/tagmanager/bg;
    //   11: getstatic 102	com/google/android/gms/tagmanager/bg$a:VA	Lcom/google/android/gms/tagmanager/bg$a;
    //   14: invokeinterface 108 2 0
    //   19: return
    //   20: ldc 110
    //   22: invokestatic 87	com/google/android/gms/tagmanager/bh:v	(Ljava/lang/String;)V
    //   25: aload_0
    //   26: invokevirtual 113	com/google/android/gms/tagmanager/co:jz	()Ljava/lang/String;
    //   29: astore_1
    //   30: aload_0
    //   31: getfield 29	com/google/android/gms/tagmanager/co:Wg	Lcom/google/android/gms/tagmanager/bm;
    //   34: invokevirtual 117	com/google/android/gms/tagmanager/bm:ji	()Lcom/google/android/gms/tagmanager/bl;
    //   37: astore_2
    //   38: aload_2
    //   39: aload_1
    //   40: invokeinterface 123 2 0
    //   45: astore 6
    //   47: new 125	java/io/ByteArrayOutputStream
    //   50: dup
    //   51: invokespecial 126	java/io/ByteArrayOutputStream:<init>	()V
    //   54: astore 7
    //   56: aload 6
    //   58: aload 7
    //   60: invokestatic 132	com/google/android/gms/tagmanager/cr:b	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    //   63: aload 7
    //   65: invokevirtual 136	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   68: invokestatic 141	com/google/android/gms/internal/c$j:b	([B)Lcom/google/android/gms/internal/c$j;
    //   71: astore 9
    //   73: new 35	java/lang/StringBuilder
    //   76: dup
    //   77: invokespecial 36	java/lang/StringBuilder:<init>	()V
    //   80: ldc 143
    //   82: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: aload 9
    //   87: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   90: invokevirtual 46	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   93: invokestatic 87	com/google/android/gms/tagmanager/bh:v	(Ljava/lang/String;)V
    //   96: aload 9
    //   98: getfield 150	com/google/android/gms/internal/c$j:fV	Lcom/google/android/gms/internal/c$f;
    //   101: ifnonnull +37 -> 138
    //   104: aload 9
    //   106: getfield 154	com/google/android/gms/internal/c$j:fU	[Lcom/google/android/gms/internal/c$i;
    //   109: arraylength
    //   110: ifne +28 -> 138
    //   113: new 35	java/lang/StringBuilder
    //   116: dup
    //   117: invokespecial 36	java/lang/StringBuilder:<init>	()V
    //   120: ldc 156
    //   122: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: aload_0
    //   126: getfield 31	com/google/android/gms/tagmanager/co:TM	Ljava/lang/String;
    //   129: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: invokevirtual 46	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   135: invokestatic 87	com/google/android/gms/tagmanager/bh:v	(Ljava/lang/String;)V
    //   138: aload_0
    //   139: getfield 96	com/google/android/gms/tagmanager/co:Wi	Lcom/google/android/gms/tagmanager/bg;
    //   142: aload 9
    //   144: invokeinterface 160 2 0
    //   149: aload_2
    //   150: invokeinterface 163 1 0
    //   155: ldc 165
    //   157: invokestatic 87	com/google/android/gms/tagmanager/bh:v	(Ljava/lang/String;)V
    //   160: return
    //   161: astore 5
    //   163: new 35	java/lang/StringBuilder
    //   166: dup
    //   167: invokespecial 36	java/lang/StringBuilder:<init>	()V
    //   170: ldc 167
    //   172: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: aload_1
    //   176: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   179: ldc 169
    //   181: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   184: aload_0
    //   185: getfield 31	com/google/android/gms/tagmanager/co:TM	Ljava/lang/String;
    //   188: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   191: ldc 171
    //   193: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   196: invokevirtual 46	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   199: invokestatic 174	com/google/android/gms/tagmanager/bh:w	(Ljava/lang/String;)V
    //   202: aload_0
    //   203: getfield 96	com/google/android/gms/tagmanager/co:Wi	Lcom/google/android/gms/tagmanager/bg;
    //   206: getstatic 177	com/google/android/gms/tagmanager/bg$a:VC	Lcom/google/android/gms/tagmanager/bg$a;
    //   209: invokeinterface 108 2 0
    //   214: aload_2
    //   215: invokeinterface 163 1 0
    //   220: return
    //   221: astore 4
    //   223: new 35	java/lang/StringBuilder
    //   226: dup
    //   227: invokespecial 36	java/lang/StringBuilder:<init>	()V
    //   230: ldc 179
    //   232: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   235: aload_1
    //   236: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   239: ldc 181
    //   241: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   244: aload 4
    //   246: invokevirtual 184	java/io/IOException:getMessage	()Ljava/lang/String;
    //   249: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   252: invokevirtual 46	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   255: aload 4
    //   257: invokestatic 187	com/google/android/gms/tagmanager/bh:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   260: aload_0
    //   261: getfield 96	com/google/android/gms/tagmanager/co:Wi	Lcom/google/android/gms/tagmanager/bg;
    //   264: getstatic 190	com/google/android/gms/tagmanager/bg$a:VB	Lcom/google/android/gms/tagmanager/bg$a;
    //   267: invokeinterface 108 2 0
    //   272: aload_2
    //   273: invokeinterface 163 1 0
    //   278: return
    //   279: astore 8
    //   281: new 35	java/lang/StringBuilder
    //   284: dup
    //   285: invokespecial 36	java/lang/StringBuilder:<init>	()V
    //   288: ldc 192
    //   290: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   293: aload_1
    //   294: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   297: ldc 181
    //   299: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   302: aload 8
    //   304: invokevirtual 184	java/io/IOException:getMessage	()Ljava/lang/String;
    //   307: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   310: invokevirtual 46	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   313: aload 8
    //   315: invokestatic 187	com/google/android/gms/tagmanager/bh:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   318: aload_0
    //   319: getfield 96	com/google/android/gms/tagmanager/co:Wi	Lcom/google/android/gms/tagmanager/bg;
    //   322: getstatic 177	com/google/android/gms/tagmanager/bg$a:VC	Lcom/google/android/gms/tagmanager/bg$a;
    //   325: invokeinterface 108 2 0
    //   330: aload_2
    //   331: invokeinterface 163 1 0
    //   336: return
    //   337: astore_3
    //   338: aload_2
    //   339: invokeinterface 163 1 0
    //   344: aload_3
    //   345: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   38	47	161	java/io/FileNotFoundException
    //   38	47	221	java/io/IOException
    //   47	138	279	java/io/IOException
    //   138	149	279	java/io/IOException
    //   38	47	337	finally
    //   47	138	337	finally
    //   138	149	337	finally
    //   163	214	337	finally
    //   223	272	337	finally
    //   281	330	337	finally
  }

  void a(bg<c.j> parambg)
  {
    this.Wi = parambg;
  }

  void bf(String paramString)
  {
    if (paramString == null)
    {
      this.Ui = this.Wh;
      return;
    }
    bh.s("Setting CTFE URL path: " + paramString);
    this.Ui = paramString;
  }

  void bu(String paramString)
  {
    bh.s("Setting previous container version: " + paramString);
    this.Wk = paramString;
  }

  String jz()
  {
    String str = this.Wj.iO() + this.Ui + "&v=a59512756";
    if ((this.Wk != null) && (!this.Wk.trim().equals("")))
      str = str + "&pv=" + this.Wk;
    if (ce.ju().jv().equals(ce.a.VY))
      str = str + "&gtm_debug=x";
    return str;
  }

  public void run()
  {
    if (this.Wi == null)
      throw new IllegalStateException("callback must be set before execute");
    this.Wi.iM();
    jy();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.co
 * JD-Core Version:    0.6.0
 */