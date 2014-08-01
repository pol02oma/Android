package com.google.android.gms.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public abstract class j extends i
{
  private static Method kc;
  private static Method kd;
  private static Method ke;
  private static Method kf;
  private static Method kg;
  private static Method kh;
  private static String ki;
  private static p kj;
  static boolean kk;
  private static long startTime = 0L;

  static
  {
    kk = false;
  }

  protected j(Context paramContext, n paramn, o paramo)
  {
    super(paramContext, paramn, paramo);
  }

  static String a(Context paramContext, n paramn)
    throws j.a
  {
    if (ke == null)
      throw new a();
    try
    {
      localByteBuffer = (ByteBuffer)ke.invoke(null, new Object[] { paramContext });
      if (localByteBuffer == null)
        throw new a();
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      ByteBuffer localByteBuffer;
      throw new a(localIllegalAccessException);
      String str = paramn.a(localByteBuffer.array(), true);
      return str;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
    }
    throw new a(localInvocationTargetException);
  }

  static ArrayList<Long> a(MotionEvent paramMotionEvent, DisplayMetrics paramDisplayMetrics)
    throws j.a
  {
    if ((kf == null) || (paramMotionEvent == null))
      throw new a();
    try
    {
      ArrayList localArrayList = (ArrayList)kf.invoke(null, new Object[] { paramMotionEvent, paramDisplayMetrics });
      return localArrayList;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new a(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
    }
    throw new a(localInvocationTargetException);
  }

  // ERROR //
  protected static void a(String paramString, Context paramContext, n paramn)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 25	com/google/android/gms/internal/j:kk	Z
    //   6: istore 4
    //   8: iload 4
    //   10: ifne +36 -> 46
    //   13: new 73	com/google/android/gms/internal/p
    //   16: dup
    //   17: aload_2
    //   18: aconst_null
    //   19: invokespecial 76	com/google/android/gms/internal/p:<init>	(Lcom/google/android/gms/internal/n;Ljava/security/SecureRandom;)V
    //   22: putstatic 78	com/google/android/gms/internal/j:kj	Lcom/google/android/gms/internal/p;
    //   25: aload_0
    //   26: putstatic 80	com/google/android/gms/internal/j:ki	Ljava/lang/String;
    //   29: aload_1
    //   30: invokestatic 84	com/google/android/gms/internal/j:e	(Landroid/content/Context;)V
    //   33: invokestatic 88	com/google/android/gms/internal/j:w	()Ljava/lang/Long;
    //   36: invokevirtual 94	java/lang/Long:longValue	()J
    //   39: putstatic 23	com/google/android/gms/internal/j:startTime	J
    //   42: iconst_1
    //   43: putstatic 25	com/google/android/gms/internal/j:kk	Z
    //   46: ldc 2
    //   48: monitorexit
    //   49: return
    //   50: astore_3
    //   51: ldc 2
    //   53: monitorexit
    //   54: aload_3
    //   55: athrow
    //   56: astore 6
    //   58: goto -12 -> 46
    //   61: astore 5
    //   63: goto -17 -> 46
    //
    // Exception table:
    //   from	to	target	type
    //   3	8	50	finally
    //   13	46	50	finally
    //   13	46	56	java/lang/UnsupportedOperationException
    //   13	46	61	com/google/android/gms/internal/j$a
  }

  static String b(Context paramContext, n paramn)
    throws j.a
  {
    if (kh == null)
      throw new a();
    try
    {
      localByteBuffer = (ByteBuffer)kh.invoke(null, new Object[] { paramContext });
      if (localByteBuffer == null)
        throw new a();
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      ByteBuffer localByteBuffer;
      throw new a(localIllegalAccessException);
      String str = paramn.a(localByteBuffer.array(), true);
      return str;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
    }
    throw new a(localInvocationTargetException);
  }

  private static String b(byte[] paramArrayOfByte, String paramString)
    throws j.a
  {
    try
    {
      String str = new String(kj.c(paramArrayOfByte, paramString), "UTF-8");
      return str;
    }
    catch (p.a locala)
    {
      throw new a(locala);
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw new a(localUnsupportedEncodingException);
  }

  static String d(Context paramContext)
    throws j.a
  {
    if (kg == null)
      throw new a();
    String str;
    try
    {
      str = (String)kg.invoke(null, new Object[] { paramContext });
      if (str == null)
        throw new a();
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new a(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new a(localInvocationTargetException);
    }
    return str;
  }

  private static void e(Context paramContext)
    throws j.a
  {
    try
    {
      arrayOfByte1 = kj.d(r.getKey());
      arrayOfByte2 = kj.c(arrayOfByte1, r.A());
      localFile1 = paramContext.getCacheDir();
      if (localFile1 == null)
      {
        localFile1 = paramContext.getDir("dex", 0);
        if (localFile1 == null)
          throw new a();
      }
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      byte[] arrayOfByte1;
      byte[] arrayOfByte2;
      File localFile1;
      throw new a(localFileNotFoundException);
      File localFile2 = File.createTempFile("ads", ".jar", localFile1);
      FileOutputStream localFileOutputStream = new FileOutputStream(localFile2);
      localFileOutputStream.write(arrayOfByte2, 0, arrayOfByte2.length);
      localFileOutputStream.close();
      DexClassLoader localDexClassLoader = new DexClassLoader(localFile2.getAbsolutePath(), localFile1.getAbsolutePath(), null, paramContext.getClassLoader());
      Class localClass1 = localDexClassLoader.loadClass(b(arrayOfByte1, r.B()));
      Class localClass2 = localDexClassLoader.loadClass(b(arrayOfByte1, r.H()));
      Class localClass3 = localDexClassLoader.loadClass(b(arrayOfByte1, r.F()));
      Class localClass4 = localDexClassLoader.loadClass(b(arrayOfByte1, r.L()));
      Class localClass5 = localDexClassLoader.loadClass(b(arrayOfByte1, r.D()));
      Class localClass6 = localDexClassLoader.loadClass(b(arrayOfByte1, r.J()));
      kc = localClass1.getMethod(b(arrayOfByte1, r.C()), new Class[0]);
      kd = localClass2.getMethod(b(arrayOfByte1, r.I()), new Class[0]);
      ke = localClass3.getMethod(b(arrayOfByte1, r.G()), new Class[] { Context.class });
      kf = localClass4.getMethod(b(arrayOfByte1, r.M()), new Class[] { MotionEvent.class, DisplayMetrics.class });
      kg = localClass5.getMethod(b(arrayOfByte1, r.E()), new Class[] { Context.class });
      kh = localClass6.getMethod(b(arrayOfByte1, r.K()), new Class[] { Context.class });
      String str = localFile2.getName();
      localFile2.delete();
      new File(localFile1, str.replace(".jar", ".dex")).delete();
      return;
    }
    catch (IOException localIOException)
    {
      throw new a(localIOException);
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      throw new a(localClassNotFoundException);
    }
    catch (p.a locala)
    {
      throw new a(locala);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw new a(localNoSuchMethodException);
    }
    catch (NullPointerException localNullPointerException)
    {
    }
    throw new a(localNullPointerException);
  }

  static String v()
    throws j.a
  {
    if (ki == null)
      throw new a();
    return ki;
  }

  static Long w()
    throws j.a
  {
    if (kc == null)
      throw new a();
    try
    {
      Long localLong = (Long)kc.invoke(null, new Object[0]);
      return localLong;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new a(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
    }
    throw new a(localInvocationTargetException);
  }

  static String x()
    throws j.a
  {
    if (kd == null)
      throw new a();
    try
    {
      String str = (String)kd.invoke(null, new Object[0]);
      return str;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new a(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
    }
    throw new a(localInvocationTargetException);
  }

  // ERROR //
  protected void b(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_1
    //   2: invokestatic 260	com/google/android/gms/internal/j:x	()Ljava/lang/String;
    //   5: invokevirtual 263	com/google/android/gms/internal/j:a	(ILjava/lang/String;)V
    //   8: aload_0
    //   9: iconst_2
    //   10: invokestatic 265	com/google/android/gms/internal/j:v	()Ljava/lang/String;
    //   13: invokevirtual 263	com/google/android/gms/internal/j:a	(ILjava/lang/String;)V
    //   16: aload_0
    //   17: bipush 25
    //   19: invokestatic 88	com/google/android/gms/internal/j:w	()Ljava/lang/Long;
    //   22: invokevirtual 94	java/lang/Long:longValue	()J
    //   25: invokevirtual 268	com/google/android/gms/internal/j:a	(IJ)V
    //   28: aload_0
    //   29: bipush 24
    //   31: aload_1
    //   32: invokestatic 270	com/google/android/gms/internal/j:d	(Landroid/content/Context;)Ljava/lang/String;
    //   35: invokevirtual 263	com/google/android/gms/internal/j:a	(ILjava/lang/String;)V
    //   38: return
    //   39: astore 6
    //   41: return
    //   42: astore 5
    //   44: return
    //   45: astore 4
    //   47: goto -19 -> 28
    //   50: astore_3
    //   51: goto -35 -> 16
    //   54: astore_2
    //   55: goto -47 -> 8
    //
    // Exception table:
    //   from	to	target	type
    //   0	8	39	java/io/IOException
    //   8	16	39	java/io/IOException
    //   16	28	39	java/io/IOException
    //   28	38	39	java/io/IOException
    //   28	38	42	com/google/android/gms/internal/j$a
    //   16	28	45	com/google/android/gms/internal/j$a
    //   8	16	50	com/google/android/gms/internal/j$a
    //   0	8	54	com/google/android/gms/internal/j$a
  }

  // ERROR //
  protected void c(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_2
    //   2: invokestatic 265	com/google/android/gms/internal/j:v	()Ljava/lang/String;
    //   5: invokevirtual 263	com/google/android/gms/internal/j:a	(ILjava/lang/String;)V
    //   8: aload_0
    //   9: iconst_1
    //   10: invokestatic 260	com/google/android/gms/internal/j:x	()Ljava/lang/String;
    //   13: invokevirtual 263	com/google/android/gms/internal/j:a	(ILjava/lang/String;)V
    //   16: invokestatic 88	com/google/android/gms/internal/j:w	()Ljava/lang/Long;
    //   19: invokevirtual 94	java/lang/Long:longValue	()J
    //   22: lstore 10
    //   24: aload_0
    //   25: bipush 25
    //   27: lload 10
    //   29: invokevirtual 268	com/google/android/gms/internal/j:a	(IJ)V
    //   32: getstatic 23	com/google/android/gms/internal/j:startTime	J
    //   35: lconst_0
    //   36: lcmp
    //   37: ifeq +24 -> 61
    //   40: aload_0
    //   41: bipush 17
    //   43: lload 10
    //   45: getstatic 23	com/google/android/gms/internal/j:startTime	J
    //   48: lsub
    //   49: invokevirtual 268	com/google/android/gms/internal/j:a	(IJ)V
    //   52: aload_0
    //   53: bipush 23
    //   55: getstatic 23	com/google/android/gms/internal/j:startTime	J
    //   58: invokevirtual 268	com/google/android/gms/internal/j:a	(IJ)V
    //   61: aload_0
    //   62: getfield 274	com/google/android/gms/internal/j:jY	Landroid/view/MotionEvent;
    //   65: aload_0
    //   66: getfield 278	com/google/android/gms/internal/j:jZ	Landroid/util/DisplayMetrics;
    //   69: invokestatic 280	com/google/android/gms/internal/j:a	(Landroid/view/MotionEvent;Landroid/util/DisplayMetrics;)Ljava/util/ArrayList;
    //   72: astore 9
    //   74: aload_0
    //   75: bipush 14
    //   77: aload 9
    //   79: iconst_0
    //   80: invokevirtual 284	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   83: checkcast 90	java/lang/Long
    //   86: invokevirtual 94	java/lang/Long:longValue	()J
    //   89: invokevirtual 268	com/google/android/gms/internal/j:a	(IJ)V
    //   92: aload_0
    //   93: bipush 15
    //   95: aload 9
    //   97: iconst_1
    //   98: invokevirtual 284	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   101: checkcast 90	java/lang/Long
    //   104: invokevirtual 94	java/lang/Long:longValue	()J
    //   107: invokevirtual 268	com/google/android/gms/internal/j:a	(IJ)V
    //   110: aload 9
    //   112: invokevirtual 288	java/util/ArrayList:size	()I
    //   115: iconst_3
    //   116: if_icmplt +21 -> 137
    //   119: aload_0
    //   120: bipush 16
    //   122: aload 9
    //   124: iconst_2
    //   125: invokevirtual 284	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   128: checkcast 90	java/lang/Long
    //   131: invokevirtual 94	java/lang/Long:longValue	()J
    //   134: invokevirtual 268	com/google/android/gms/internal/j:a	(IJ)V
    //   137: aload_0
    //   138: bipush 27
    //   140: aload_1
    //   141: aload_0
    //   142: getfield 292	com/google/android/gms/internal/j:ka	Lcom/google/android/gms/internal/n;
    //   145: invokestatic 294	com/google/android/gms/internal/j:a	(Landroid/content/Context;Lcom/google/android/gms/internal/n;)Ljava/lang/String;
    //   148: invokevirtual 263	com/google/android/gms/internal/j:a	(ILjava/lang/String;)V
    //   151: aload_0
    //   152: bipush 29
    //   154: aload_1
    //   155: aload_0
    //   156: getfield 292	com/google/android/gms/internal/j:ka	Lcom/google/android/gms/internal/n;
    //   159: invokestatic 296	com/google/android/gms/internal/j:b	(Landroid/content/Context;Lcom/google/android/gms/internal/n;)Ljava/lang/String;
    //   162: invokevirtual 263	com/google/android/gms/internal/j:a	(ILjava/lang/String;)V
    //   165: return
    //   166: astore 8
    //   168: return
    //   169: astore 7
    //   171: return
    //   172: astore 6
    //   174: goto -23 -> 151
    //   177: astore 5
    //   179: goto -42 -> 137
    //   182: astore 4
    //   184: goto -123 -> 61
    //   187: astore_3
    //   188: goto -172 -> 16
    //   191: astore_2
    //   192: goto -184 -> 8
    //
    // Exception table:
    //   from	to	target	type
    //   0	8	166	java/io/IOException
    //   8	16	166	java/io/IOException
    //   16	61	166	java/io/IOException
    //   61	137	166	java/io/IOException
    //   137	151	166	java/io/IOException
    //   151	165	166	java/io/IOException
    //   151	165	169	com/google/android/gms/internal/j$a
    //   137	151	172	com/google/android/gms/internal/j$a
    //   61	137	177	com/google/android/gms/internal/j$a
    //   16	61	182	com/google/android/gms/internal/j$a
    //   8	16	187	com/google/android/gms/internal/j$a
    //   0	8	191	com/google/android/gms/internal/j$a
  }

  static class a extends Exception
  {
    public a()
    {
    }

    public a(Throwable paramThrowable)
    {
      super();
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.j
 * JD-Core Version:    0.6.0
 */