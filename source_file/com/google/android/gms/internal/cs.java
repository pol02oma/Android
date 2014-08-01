package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import java.math.BigInteger;
import java.util.Locale;

public final class cs
{
  private static final Object op = new Object();
  private static String pH;

  public static String aR()
  {
    synchronized (op)
    {
      String str = pH;
      return str;
    }
  }

  public static String b(Context paramContext, String paramString1, String paramString2)
  {
    synchronized (op)
    {
      if ((pH == null) && (!TextUtils.isEmpty(paramString1)))
        c(paramContext, paramString1, paramString2);
      String str = pH;
      return str;
    }
  }

  private static void c(Context paramContext, String paramString1, String paramString2)
  {
    BigInteger localBigInteger2;
    try
    {
      ClassLoader localClassLoader = paramContext.createPackageContext(paramString2, 3).getClassLoader();
      Class localClass = Class.forName("com.google.ads.mediation.MediationAdapter", false, localClassLoader);
      BigInteger localBigInteger1 = new BigInteger(new byte[1]);
      String[] arrayOfString = paramString1.split(",");
      localBigInteger2 = localBigInteger1;
      for (int i = 0; i < arrayOfString.length; i++)
      {
        if (!cv.a(localClassLoader, localClass, arrayOfString[i]))
          continue;
        localBigInteger2 = localBigInteger2.setBit(i);
      }
    }
    catch (Throwable localThrowable)
    {
      pH = "err";
      return;
    }
    pH = String.format(Locale.US, "%X", new Object[] { localBigInteger2 });
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.cs
 * JD-Core Version:    0.6.0
 */