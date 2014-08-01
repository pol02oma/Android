package com.flurry.sdk;

import android.content.Context;
import android.telephony.TelephonyManager;
import java.util.Arrays;

public class em
{
  private static final String a = em.class.getSimpleName();
  private static byte[] b;

  public static byte[] a()
  {
    if (b != null)
      return b;
    if (eg.a().b().checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0)
      return null;
    b();
    return b;
  }

  private static void b()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)eg.a().b().getSystemService("phone");
    if (localTelephonyManager == null);
    String str;
    do
    {
      return;
      str = localTelephonyManager.getDeviceId();
    }
    while ((str == null) || (str.trim().length() <= 0));
    byte[] arrayOfByte;
    try
    {
      arrayOfByte = fh.d(str);
      if ((arrayOfByte != null) && (arrayOfByte.length == 20))
      {
        b = arrayOfByte;
        return;
      }
    }
    catch (Exception localException)
    {
      ex.a(6, a, "Exception in generateHashedImei()");
      return;
    }
    ex.a(6, a, "sha1 is not " + 20 + " bytes long: " + Arrays.toString(arrayOfByte));
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.sdk.em
 * JD-Core Version:    0.6.0
 */