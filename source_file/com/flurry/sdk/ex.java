package com.flurry.sdk;

import android.text.TextUtils;
import android.util.Log;

public final class ex
{
  private static int a = 4000;
  private static boolean b = false;
  private static int c = 5;
  private static boolean d = false;

  public static void a()
  {
    b = true;
  }

  public static void a(int paramInt)
  {
    c = paramInt;
  }

  public static void a(int paramInt, String paramString1, String paramString2)
  {
    c(paramInt, paramString1, paramString2);
  }

  public static void a(int paramInt, String paramString1, String paramString2, Throwable paramThrowable)
  {
    c(paramInt, paramString1, paramString2, paramThrowable);
  }

  public static void a(String paramString1, String paramString2)
  {
    b(3, paramString1, paramString2);
  }

  public static void a(String paramString1, String paramString2, Throwable paramThrowable)
  {
    b(3, paramString1, paramString2, paramThrowable);
  }

  public static void a(boolean paramBoolean)
  {
    d = paramBoolean;
  }

  public static void b()
  {
    b = false;
  }

  private static void b(int paramInt, String paramString1, String paramString2)
  {
    if ((!b) && (c <= paramInt))
      d(paramInt, paramString1, paramString2);
  }

  private static void b(int paramInt, String paramString1, String paramString2, Throwable paramThrowable)
  {
    b(paramInt, paramString1, paramString2 + '\n' + Log.getStackTraceString(paramThrowable));
  }

  public static void b(String paramString1, String paramString2)
  {
    b(6, paramString1, paramString2);
  }

  public static void b(String paramString1, String paramString2, Throwable paramThrowable)
  {
    b(6, paramString1, paramString2, paramThrowable);
  }

  public static int c()
  {
    return c;
  }

  private static void c(int paramInt, String paramString1, String paramString2)
  {
    if (d)
      d(paramInt, paramString1, paramString2);
  }

  private static void c(int paramInt, String paramString1, String paramString2, Throwable paramThrowable)
  {
    c(paramInt, paramString1, paramString2 + '\n' + Log.getStackTraceString(paramThrowable));
  }

  public static void c(String paramString1, String paramString2)
  {
    b(4, paramString1, paramString2);
  }

  public static void c(String paramString1, String paramString2, Throwable paramThrowable)
  {
    b(5, paramString1, paramString2, paramThrowable);
  }

  private static void d(int paramInt, String paramString1, String paramString2)
  {
    int i;
    if (TextUtils.isEmpty(paramString2))
      i = 0;
    int k;
    label57: label68: for (int j = 0; ; j = k)
    {
      if (j < i)
        if (a <= i - j)
          break label57;
      for (k = i; ; k = j + a)
      {
        if (Log.println(paramInt, "FlurryAgent", paramString2.substring(j, k)) > 0)
          break label68;
        return;
        i = paramString2.length();
        break;
      }
    }
  }

  public static void d(String paramString1, String paramString2)
  {
    b(2, paramString1, paramString2);
  }

  public static boolean d()
  {
    return d;
  }

  public static void e(String paramString1, String paramString2)
  {
    b(5, paramString1, paramString2);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.sdk.ex
 * JD-Core Version:    0.6.0
 */