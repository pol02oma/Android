package com.flurry.sdk;

import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;

public class cl
  implements cn.b, ei.a
{
  static int a;
  static int b;
  static int c;
  static int d;
  static int e;
  static int f;
  private static final String g = cl.class.getSimpleName();
  private byte A = -1;
  private boolean B;
  private String C;
  private byte D;
  private long E;
  private long F;
  private final Map<String, bx.a> G = new HashMap();
  private final List<cb> H = new ArrayList();
  private boolean I;
  private int J;
  private final List<ca> K = new ArrayList();
  private int L;
  private int M;
  private final by N = new by();
  private Map<String, List<String>> O;
  private final Handler P;
  private cn Q;
  private a R;
  private int S;
  private boolean T = false;
  private final AtomicInteger h = new AtomicInteger(0);
  private final AtomicInteger i = new AtomicInteger(0);
  private final AtomicInteger j = new AtomicInteger(0);
  private File k = null;
  private volatile boolean l = false;
  private String m;
  private String n;
  private List<cj> o;
  private final Map<ej, ByteBuffer> p = new HashMap();
  private boolean q;
  private long r;
  private List<cj> s = new ArrayList();
  private String t;
  private long u;
  private long v;
  private long w;
  private long x;
  private String y = "";
  private String z = "";

  static
  {
    a = 100;
    b = 10;
    c = 1000;
    d = 160000;
    e = 50;
    f = 20;
  }

  public cl(Context paramContext, String paramString, a parama)
  {
    ex.a(4, g, "Initializing new Flurry session");
    HandlerThread localHandlerThread = new HandlerThread("FlurryAgentSession_" + paramString);
    localHandlerThread.start();
    this.P = new Handler(localHandlerThread.getLooper());
    u();
    this.Q = new cn(this);
    this.R = parama;
    this.m = paramString;
    this.k = paramContext.getFileStreamPath(A());
    this.n = ep.a();
    this.w = -1L;
    this.L = 0;
    this.z = TimeZone.getDefault().getID();
    this.y = (Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry());
    this.I = true;
    this.J = 0;
    this.M = 0;
    r();
  }

  private String A()
  {
    return ".flurryagent." + Integer.toString(this.m.hashCode(), 16);
  }

  private int B()
  {
    return this.h.incrementAndGet();
  }

  private int C()
  {
    return this.i.incrementAndGet();
  }

  private String D()
  {
    return this.t;
  }

  private Location E()
  {
    return bx.a().n();
  }

  private void a(long paramLong)
  {
    Iterator localIterator = this.H.iterator();
    while (localIterator.hasNext())
    {
      cb localcb = (cb)localIterator.next();
      if ((!localcb.a()) || (localcb.b()))
        continue;
      localcb.a(paramLong);
    }
  }

  private void a(Context paramContext)
  {
    try
    {
      byte[] arrayOfByte = em.a();
      if (arrayOfByte != null)
      {
        ex.a(3, g, "Fetched hashed IMEI");
        this.p.put(ej.b, ByteBuffer.wrap(arrayOfByte));
      }
      c(paramContext);
      return;
    }
    catch (Throwable localThrowable)
    {
      ex.a(6, g, "", localThrowable);
    }
  }

  private void a(byte[] paramArrayOfByte)
  {
    cd localcd = bx.a().o();
    String str = "" + bx.a().b();
    localcd.b(paramArrayOfByte, this.m, str);
  }

  private void b(Context paramContext)
  {
    try
    {
      monitorenter;
      try
      {
        if (this.s.size() > 0);
        for (int i1 = 1; ; i1 = 0)
        {
          monitorexit;
          if (i1 != 0)
            c(paramContext);
          return;
        }
      }
      finally
      {
        monitorexit;
      }
    }
    catch (Throwable localThrowable)
    {
      ex.a(6, g, "", localThrowable);
    }
  }

  private void c(Context paramContext)
  {
    try
    {
      ex.a(3, g, "generating agent report");
      cc localcc = new cc(this.m, this.n, D(), this.q, this.r, this.u, this.s, this.p, this.N.a(false), a(), System.currentTimeMillis());
      this.o = new ArrayList(this.s);
      if ((localcc != null) && (localcc.a() != null))
      {
        ex.a(3, g, "generated report of size " + localcc.a().length + " with " + this.s.size() + " reports.");
        a(localcc.a());
        this.s.removeAll(this.o);
        this.o = null;
        x();
        return;
      }
      ex.e(g, "Error generating report");
      return;
    }
    catch (Throwable localThrowable)
    {
      ex.a(6, g, "", localThrowable);
    }
  }

  // ERROR //
  private void d(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 110	com/flurry/sdk/cl:k	Ljava/io/File;
    //   6: invokevirtual 414	java/io/File:exists	()Z
    //   9: ifeq +248 -> 257
    //   12: iconst_4
    //   13: getstatic 80	com/flurry/sdk/cl:g	Ljava/lang/String;
    //   16: new 154	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   23: ldc_w 416
    //   26: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   29: aload_0
    //   30: getfield 110	com/flurry/sdk/cl:k	Ljava/io/File;
    //   33: invokevirtual 419	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   36: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   42: invokestatic 150	com/flurry/sdk/ex:a	(ILjava/lang/String;Ljava/lang/String;)V
    //   45: new 421	java/io/DataInputStream
    //   48: dup
    //   49: new 423	java/io/FileInputStream
    //   52: dup
    //   53: aload_0
    //   54: getfield 110	com/flurry/sdk/cl:k	Ljava/io/File;
    //   57: invokespecial 426	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   60: invokespecial 429	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   63: astore_3
    //   64: new 431	com/flurry/sdk/cm
    //   67: dup
    //   68: invokespecial 432	com/flurry/sdk/cm:<init>	()V
    //   71: astore 4
    //   73: aload_0
    //   74: aload 4
    //   76: aload_3
    //   77: aload_0
    //   78: getfield 194	com/flurry/sdk/cl:m	Ljava/lang/String;
    //   81: invokevirtual 435	com/flurry/sdk/cm:a	(Ljava/io/DataInputStream;Ljava/lang/String;)Z
    //   84: putfield 112	com/flurry/sdk/cl:l	Z
    //   87: aload_0
    //   88: getfield 112	com/flurry/sdk/cl:l	Z
    //   91: ifeq +30 -> 121
    //   94: aload_0
    //   95: aload 4
    //   97: invokevirtual 436	com/flurry/sdk/cm:a	()Z
    //   100: putfield 360	com/flurry/sdk/cl:q	Z
    //   103: aload_0
    //   104: aload 4
    //   106: invokevirtual 438	com/flurry/sdk/cm:c	()J
    //   109: putfield 362	com/flurry/sdk/cl:r	J
    //   112: aload_0
    //   113: aload 4
    //   115: invokevirtual 441	com/flurry/sdk/cm:b	()Ljava/util/List;
    //   118: putfield 122	com/flurry/sdk/cl:s	Ljava/util/List;
    //   121: aload_3
    //   122: invokestatic 446	com/flurry/sdk/fh:a	(Ljava/io/Closeable;)V
    //   125: aload_0
    //   126: getfield 112	com/flurry/sdk/cl:l	Z
    //   129: ifne +23 -> 152
    //   132: aload_0
    //   133: getfield 110	com/flurry/sdk/cl:k	Ljava/io/File;
    //   136: invokevirtual 449	java/io/File:delete	()Z
    //   139: ifeq +87 -> 226
    //   142: iconst_3
    //   143: getstatic 80	com/flurry/sdk/cl:g	Ljava/lang/String;
    //   146: ldc_w 451
    //   149: invokestatic 150	com/flurry/sdk/ex:a	(ILjava/lang/String;Ljava/lang/String;)V
    //   152: aload_0
    //   153: getfield 112	com/flurry/sdk/cl:l	Z
    //   156: ifne +21 -> 177
    //   159: aload_0
    //   160: iconst_0
    //   161: putfield 360	com/flurry/sdk/cl:q	Z
    //   164: aload_0
    //   165: aload_0
    //   166: getfield 364	com/flurry/sdk/cl:u	J
    //   169: putfield 362	com/flurry/sdk/cl:r	J
    //   172: aload_0
    //   173: iconst_1
    //   174: putfield 112	com/flurry/sdk/cl:l	Z
    //   177: aload_0
    //   178: monitorexit
    //   179: return
    //   180: astore 8
    //   182: aload 8
    //   184: invokevirtual 454	java/io/IOException:printStackTrace	()V
    //   187: goto -66 -> 121
    //   190: astore 6
    //   192: getstatic 80	com/flurry/sdk/cl:g	Ljava/lang/String;
    //   195: ldc_w 456
    //   198: aload 6
    //   200: invokestatic 459	com/flurry/sdk/ex:c	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   203: aload_3
    //   204: invokestatic 446	com/flurry/sdk/fh:a	(Ljava/io/Closeable;)V
    //   207: goto -82 -> 125
    //   210: astore_2
    //   211: aload_0
    //   212: monitorexit
    //   213: aload_2
    //   214: athrow
    //   215: astore 5
    //   217: aconst_null
    //   218: astore_3
    //   219: aload_3
    //   220: invokestatic 446	com/flurry/sdk/fh:a	(Ljava/io/Closeable;)V
    //   223: aload 5
    //   225: athrow
    //   226: bipush 6
    //   228: getstatic 80	com/flurry/sdk/cl:g	Ljava/lang/String;
    //   231: ldc_w 461
    //   234: invokestatic 150	com/flurry/sdk/ex:a	(ILjava/lang/String;Ljava/lang/String;)V
    //   237: goto -85 -> 152
    //   240: astore 7
    //   242: bipush 6
    //   244: getstatic 80	com/flurry/sdk/cl:g	Ljava/lang/String;
    //   247: ldc 124
    //   249: aload 7
    //   251: invokestatic 328	com/flurry/sdk/ex:a	(ILjava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   254: goto -102 -> 152
    //   257: iconst_4
    //   258: getstatic 80	com/flurry/sdk/cl:g	Ljava/lang/String;
    //   261: ldc_w 463
    //   264: invokestatic 150	com/flurry/sdk/ex:a	(ILjava/lang/String;Ljava/lang/String;)V
    //   267: goto -115 -> 152
    //   270: astore 5
    //   272: goto -53 -> 219
    //   275: astore 6
    //   277: aconst_null
    //   278: astore_3
    //   279: goto -87 -> 192
    //
    // Exception table:
    //   from	to	target	type
    //   64	121	180	java/io/IOException
    //   64	121	190	java/lang/Throwable
    //   182	187	190	java/lang/Throwable
    //   2	45	210	finally
    //   121	125	210	finally
    //   125	152	210	finally
    //   152	177	210	finally
    //   203	207	210	finally
    //   219	226	210	finally
    //   226	237	210	finally
    //   242	254	210	finally
    //   257	267	210	finally
    //   45	64	215	finally
    //   125	152	240	java/lang/Throwable
    //   226	237	240	java/lang/Throwable
    //   64	121	270	finally
    //   182	187	270	finally
    //   192	203	270	finally
    //   45	64	275	java/lang/Throwable
  }

  private void r()
  {
    ei localei = eh.a();
    this.D = ((Byte)localei.a("Gender")).byteValue();
    localei.a("Gender", this);
    ex.a(4, g, "initSettings, Gender = " + this.D);
    this.C = ((String)localei.a("UserId"));
    localei.a("UserId", this);
    ex.a(4, g, "initSettings, UserId = " + this.C);
    this.B = ((Boolean)localei.a("LogEvents")).booleanValue();
    localei.a("LogEvents", this);
    ex.a(4, g, "initSettings, LogEvents = " + this.B);
    this.E = ((Long)localei.a("Age")).longValue();
    localei.a("Age", this);
    ex.a(4, g, "initSettings, BirthDate = " + this.E);
    this.F = ((Long)localei.a("ContinueSessionMillis")).longValue();
    localei.a("ContinueSessionMillis", this);
    ex.a(4, g, "initSettings, ContinueSessionMillis = " + this.F);
  }

  private void s()
  {
    ex.e(g, "Start session");
    long l1 = SystemClock.elapsedRealtime();
    this.u = System.currentTimeMillis();
    this.v = l1;
    a(new fi()
    {
      public void a()
      {
        if (!cl.a(cl.this))
          cl.a(cl.this, eg.a().b());
        cl.b(cl.this, eg.a().b());
      }
    });
  }

  private void t()
  {
    ex.e(g, "Continuing previous session");
  }

  private void u()
  {
    if (TextUtils.isEmpty(this.t))
      a(new fi()
      {
        public void a()
        {
          cl.a(cl.this, eo.a());
        }
      });
  }

  private void v()
  {
    a(new fi()
    {
      public void a()
      {
        cj localcj = cl.this.f();
        cl.b(cl.this).clear();
        cl.b(cl.this).add(localcj);
        cl.c(cl.this);
      }
    });
  }

  private void w()
  {
    a(new fi()
    {
      public void a()
      {
        cl.c(cl.this, eg.a().b());
      }
    });
  }

  // ERROR //
  private void x()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 110	com/flurry/sdk/cl:k	Ljava/io/File;
    //   6: invokestatic 568	com/flurry/sdk/et:a	(Ljava/io/File;)Z
    //   9: ifne +15 -> 24
    //   12: getstatic 80	com/flurry/sdk/cl:g	Ljava/lang/String;
    //   15: ldc_w 570
    //   18: invokestatic 404	com/flurry/sdk/ex:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   21: aload_0
    //   22: monitorexit
    //   23: return
    //   24: new 572	java/io/DataOutputStream
    //   27: dup
    //   28: new 574	java/io/FileOutputStream
    //   31: dup
    //   32: aload_0
    //   33: getfield 110	com/flurry/sdk/cl:k	Ljava/io/File;
    //   36: invokespecial 575	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   39: invokespecial 578	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   42: astore_2
    //   43: new 431	com/flurry/sdk/cm
    //   46: dup
    //   47: invokespecial 432	com/flurry/sdk/cm:<init>	()V
    //   50: astore_3
    //   51: aload_3
    //   52: aload_0
    //   53: getfield 360	com/flurry/sdk/cl:q	Z
    //   56: invokevirtual 581	com/flurry/sdk/cm:a	(Z)V
    //   59: aload_3
    //   60: aload_0
    //   61: getfield 362	com/flurry/sdk/cl:r	J
    //   64: invokevirtual 582	com/flurry/sdk/cm:a	(J)V
    //   67: aload_3
    //   68: aload_0
    //   69: getfield 122	com/flurry/sdk/cl:s	Ljava/util/List;
    //   72: invokevirtual 585	com/flurry/sdk/cm:a	(Ljava/util/List;)V
    //   75: aload_3
    //   76: aload_2
    //   77: aload_0
    //   78: getfield 194	com/flurry/sdk/cl:m	Ljava/lang/String;
    //   81: aload_0
    //   82: invokespecial 358	com/flurry/sdk/cl:D	()Ljava/lang/String;
    //   85: invokevirtual 588	com/flurry/sdk/cm:a	(Ljava/io/DataOutputStream;Ljava/lang/String;Ljava/lang/String;)V
    //   88: goto -67 -> 21
    //   91: astore 5
    //   93: aload 5
    //   95: invokevirtual 589	java/io/FileNotFoundException:printStackTrace	()V
    //   98: goto -77 -> 21
    //   101: astore_1
    //   102: aload_0
    //   103: monitorexit
    //   104: aload_1
    //   105: athrow
    //   106: astore 4
    //   108: aload 4
    //   110: invokevirtual 454	java/io/IOException:printStackTrace	()V
    //   113: goto -92 -> 21
    //
    // Exception table:
    //   from	to	target	type
    //   24	88	91	java/io/FileNotFoundException
    //   2	21	101	finally
    //   24	88	101	finally
    //   93	98	101	finally
    //   108	113	101	finally
    //   24	88	106	java/io/IOException
  }

  private void y()
  {
    this.S = (1 + this.S);
  }

  private void z()
  {
    this.S = (-1 + this.S);
  }

  Map<String, List<String>> a()
  {
    return this.O;
  }

  public void a(fi paramfi)
  {
    this.P.post(paramfi);
  }

  public void a(String paramString, Object paramObject)
  {
    if (paramString.equals("Gender"))
    {
      this.D = ((Byte)paramObject).byteValue();
      ex.a(4, g, "onSettingUpdate, Gender = " + this.D);
      return;
    }
    if (paramString.equals("UserId"))
    {
      this.C = ((String)paramObject);
      ex.a(4, g, "onSettingUpdate, UserId = " + this.C);
      return;
    }
    if (paramString.equals("LogEvents"))
    {
      this.B = ((Boolean)paramObject).booleanValue();
      ex.a(4, g, "onSettingUpdate, LogEvents = " + this.B);
      return;
    }
    if (paramString.equals("Age"))
    {
      this.E = ((Long)paramObject).longValue();
      ex.a(4, g, "onSettingUpdate, Birthdate = " + this.E);
      return;
    }
    if (paramString.equals("ContinueSessionMillis"))
    {
      this.F = ((Long)paramObject).longValue();
      ex.a(4, g, "onSettingUpdate, ContinueSessionMillis = " + this.F);
      return;
    }
    ex.a(6, g, "onSettingUpdate internal error!");
  }

  public void a(String paramString1, String paramString2, String paramString3, Throwable paramThrowable)
  {
    monitorenter;
    if (paramString1 != null);
    while (true)
    {
      int i2;
      try
      {
        if (!"uncaught".equals(paramString1))
          continue;
        int i1 = 1;
        this.L = (1 + this.L);
        if (this.K.size() >= e)
          continue;
        Long localLong2 = Long.valueOf(System.currentTimeMillis());
        ca localca3 = new ca(C(), localLong2.longValue(), paramString1, paramString2, paramString3, paramThrowable);
        this.K.add(localca3);
        ex.e(g, "Error logged: " + localca3.c());
        return;
        i1 = 0;
        continue;
        if (i1 == 0)
          break label247;
        i2 = 0;
        if (i2 >= this.K.size())
          continue;
        ca localca1 = (ca)this.K.get(i2);
        if ((localca1.c() != null) && (!"uncaught".equals(localca1.c())))
        {
          Long localLong1 = Long.valueOf(System.currentTimeMillis());
          ca localca2 = new ca(C(), localLong1.longValue(), paramString1, paramString2, paramString3, paramThrowable);
          this.K.set(i2, localca2);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      i2++;
      continue;
      label247: ex.e(g, "Max errors logged. No more errors logged.");
    }
  }

  public void a(String paramString, Map<String, String> paramMap)
  {
    monitorenter;
    while (true)
    {
      cb localcb;
      HashMap localHashMap;
      try
      {
        Iterator localIterator = this.H.iterator();
        if (!localIterator.hasNext())
          continue;
        localcb = (cb)localIterator.next();
        if (!localcb.a(paramString))
          continue;
        long l1 = SystemClock.elapsedRealtime() - this.v;
        if ((paramMap == null) || (paramMap.size() <= 0) || (this.J >= d))
          continue;
        int i1 = this.J - localcb.d();
        localHashMap = new HashMap(localcb.c());
        localcb.a(paramMap);
        if (i1 + localcb.d() <= d)
        {
          if (localcb.c().size() <= b)
            continue;
          ex.e(g, "MaxEventParams exceeded on endEvent: " + localcb.c().size());
          localcb.b(localHashMap);
          localcb.a(l1);
          return;
          this.J = (i1 + localcb.d());
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      localcb.b(localHashMap);
      this.I = false;
      this.J = d;
      ex.e(g, "Event Log size exceeded. No more event details logged.");
    }
  }

  public void a(String paramString, Map<String, String> paramMap, boolean paramBoolean)
  {
    monitorenter;
    while (true)
    {
      long l1;
      String str;
      bx.a locala1;
      try
      {
        l1 = SystemClock.elapsedRealtime() - this.v;
        str = fh.a(paramString);
        int i1 = str.length();
        if (i1 == 0)
          return;
        locala1 = (bx.a)this.G.get(str);
        if (locala1 != null)
          break label251;
        if (this.G.size() < a)
        {
          bx.a locala2 = new bx.a();
          locala2.a = 1;
          this.G.put(str, locala2);
          ex.e(g, "Event count started: " + str);
          if ((!this.B) || (this.H.size() >= c) || (this.J >= d))
            break label382;
          if (paramMap != null)
            break label390;
          localObject2 = Collections.emptyMap();
          if (((Map)localObject2).size() <= b)
            break label293;
          ex.e(g, "MaxEventParams exceeded: " + ((Map)localObject2).size());
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      ex.e(g, "Too many different events. Event not counted: " + str);
      continue;
      label251: locala1.a = (1 + locala1.a);
      ex.e(g, "Event count incremented: " + str);
      continue;
      label293: cb localcb = new cb(B(), str, (Map)localObject2, l1, paramBoolean);
      if (localcb.d() + this.J <= d)
      {
        this.H.add(localcb);
        this.J += localcb.d();
        continue;
      }
      this.J = d;
      this.I = false;
      ex.e(g, "Event Log size exceeded. No more event details logged.");
      continue;
      label382: this.I = false;
      continue;
      label390: Object localObject2 = paramMap;
    }
  }

  public void a(Map<String, List<String>> paramMap)
  {
    this.O = paramMap;
  }

  public void b()
  {
    this.q = true;
  }

  public void c()
  {
    monitorenter;
    try
    {
      if (this.Q.b())
        this.Q.a();
      y();
      if (!this.T)
      {
        s();
        this.T = true;
      }
      while (true)
      {
        return;
        t();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void d()
  {
    monitorenter;
    try
    {
      ex.e(g, "Trying to end session");
      boolean bool = this.T;
      if (!bool);
      while (true)
      {
        return;
        this.w = (SystemClock.elapsedRealtime() - this.v);
        a(this.w);
        v();
        if (i() > 0)
          z();
        if (i() != 0)
          continue;
        this.Q.a(this.F);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void e()
  {
    monitorenter;
    try
    {
      if (i() != 0)
        ex.a(6, g, "Error! Session with apiKey = " + k() + " was ended while getSessionCount() is not 0");
      boolean bool = this.T;
      if (!bool);
      while (true)
      {
        return;
        ex.e(g, "Ending session");
        this.S = 0;
        if (this.Q.b())
          this.Q.a();
        w();
        if (this.R != null)
          this.R.d(k());
        eh.a().b("Gender", this);
        eh.a().b("UserId", this);
        eh.a().b("Age", this);
        eh.a().b("LogEvents", this);
        eh.a().b("ContinueSessionMillis", this);
        this.P.getLooper().quit();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  cj f()
  {
    monitorenter;
    try
    {
      ck localck = new ck();
      localck.a(this.n);
      localck.a(this.u);
      localck.b(this.w);
      localck.c(this.x);
      localck.b(l());
      localck.c(m());
      localck.a(this.A);
      localck.d(j());
      localck.a(E());
      localck.b(h());
      localck.a(this.D);
      localck.a(Long.valueOf(this.E));
      localck.a(q());
      localck.a(o());
      localck.a(this.I);
      localck.b(p());
      localck.c(this.L);
      try
      {
        localcj = new cj(localck);
        if (localcj == null)
          ex.e(g, "New session report wasn't created");
        monitorexit;
        return localcj;
      }
      catch (IOException localIOException)
      {
        while (true)
        {
          localIOException.printStackTrace();
          cj localcj = null;
        }
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void g()
  {
    monitorenter;
    try
    {
      this.M = (1 + this.M);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  int h()
  {
    return this.M;
  }

  int i()
  {
    return this.S;
  }

  String j()
  {
    if (this.C == null)
      return "";
    return this.C;
  }

  public String k()
  {
    return this.m;
  }

  public String l()
  {
    return this.y;
  }

  public String m()
  {
    return this.z;
  }

  public void n()
  {
    e();
  }

  List<cb> o()
  {
    return this.H;
  }

  List<ca> p()
  {
    return this.K;
  }

  Map<String, bx.a> q()
  {
    return this.G;
  }

  public static abstract interface a
  {
    public abstract void d(String paramString);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.sdk.cl
 * JD-Core Version:    0.6.0
 */