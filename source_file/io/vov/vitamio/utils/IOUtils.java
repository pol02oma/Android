package io.vov.vitamio.utils;

import android.database.Cursor;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.Closeable;

public class IOUtils
{
  private static final String TAG = "IOUtils";

  public static void closeSilently(Cursor paramCursor)
  {
    if (paramCursor != null);
    try
    {
      paramCursor.close();
      return;
    }
    catch (Throwable localThrowable)
    {
      Log.w("IOUtils", "fail to close", localThrowable);
    }
  }

  public static void closeSilently(ParcelFileDescriptor paramParcelFileDescriptor)
  {
    if (paramParcelFileDescriptor == null)
      return;
    try
    {
      paramParcelFileDescriptor.close();
      return;
    }
    catch (Throwable localThrowable)
    {
      Log.w("IOUtils", "fail to close", localThrowable);
    }
  }

  public static void closeSilently(Closeable paramCloseable)
  {
    if (paramCloseable == null)
      return;
    try
    {
      paramCloseable.close();
      return;
    }
    catch (Throwable localThrowable)
    {
      Log.w("IOUtils", "fail to close", localThrowable);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     io.vov.vitamio.utils.IOUtils
 * JD-Core Version:    0.6.0
 */