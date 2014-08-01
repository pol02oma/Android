package io.vov.vitamio.utils;

import java.util.MissingFormatArgumentException;

public class Log
{
  public static final String TAG = "Vitamio[Player]";

  public static void d(String paramString, Object[] paramArrayOfObject)
  {
    try
    {
      android.util.Log.d("Vitamio[Player]", String.format(paramString, paramArrayOfObject));
      return;
    }
    catch (MissingFormatArgumentException localMissingFormatArgumentException)
    {
      android.util.Log.e("Vitamio[Player]", "vitamio.Log", localMissingFormatArgumentException);
      android.util.Log.d("Vitamio[Player]", paramString);
    }
  }

  public static void e(String paramString, Throwable paramThrowable)
  {
    android.util.Log.e("Vitamio[Player]", paramString, paramThrowable);
  }

  public static void e(String paramString, Object[] paramArrayOfObject)
  {
    try
    {
      android.util.Log.e("Vitamio[Player]", String.format(paramString, paramArrayOfObject));
      return;
    }
    catch (MissingFormatArgumentException localMissingFormatArgumentException)
    {
      android.util.Log.e("Vitamio[Player]", "vitamio.Log", localMissingFormatArgumentException);
      android.util.Log.e("Vitamio[Player]", paramString);
    }
  }

  public static void i(String paramString, Object[] paramArrayOfObject)
  {
    try
    {
      android.util.Log.i("Vitamio[Player]", String.format(paramString, paramArrayOfObject));
      return;
    }
    catch (MissingFormatArgumentException localMissingFormatArgumentException)
    {
      android.util.Log.e("Vitamio[Player]", "vitamio.Log", localMissingFormatArgumentException);
      android.util.Log.i("Vitamio[Player]", paramString);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     io.vov.vitamio.utils.Log
 * JD-Core Version:    0.6.0
 */