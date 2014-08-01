package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

public class k extends j
{
  private k(Context paramContext, n paramn, o paramo)
  {
    super(paramContext, paramn, paramo);
  }

  public static k a(String paramString, Context paramContext)
  {
    e locale = new e();
    a(paramString, paramContext, locale);
    return new k(paramContext, locale, new q(239));
  }

  protected void b(Context paramContext)
  {
    long l = 1L;
    super.b(paramContext);
    try
    {
      locala = f(paramContext);
    }
    catch (IOException localIOException1)
    {
      while (true)
      {
        try
        {
          a locala;
          if (locala.isLimitAdTrackingEnabled())
          {
            a(28, l);
            String str = locala.getId();
            if (str == null)
              break;
            a(30, str);
            return;
            localIOException1 = localIOException1;
            a(28, 1L);
            return;
          }
        }
        catch (IOException localIOException2)
        {
          return;
        }
        l = 0L;
      }
    }
    catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
    {
    }
  }

  a f(Context paramContext)
    throws IOException, GooglePlayServicesNotAvailableException
  {
    int i = 0;
    AdvertisingIdClient.Info localInfo;
    String str1;
    byte[] arrayOfByte;
    try
    {
      localInfo = AdvertisingIdClient.getAdvertisingIdInfo(paramContext);
      str1 = localInfo.getId();
      if ((str1 != null) && (str1.matches("^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$")))
      {
        arrayOfByte = new byte[16];
        int j = 0;
        while (i < str1.length())
        {
          if ((i == 8) || (i == 13) || (i == 18) || (i == 23))
            i++;
          arrayOfByte[j] = (byte)((Character.digit(str1.charAt(i), 16) << 4) + Character.digit(str1.charAt(i + 1), 16));
          j++;
          i += 2;
        }
      }
    }
    catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
    {
      throw new IOException(localGooglePlayServicesRepairableException);
    }
    for (String str2 = this.ka.a(arrayOfByte, true); ; str2 = str1)
      return new a(str2, localInfo.isLimitAdTrackingEnabled());
  }

  class a
  {
    private String kl;
    private boolean km;

    public a(String paramBoolean, boolean arg3)
    {
      this.kl = paramBoolean;
      boolean bool;
      this.km = bool;
    }

    public String getId()
    {
      return this.kl;
    }

    public boolean isLimitAdTrackingEnabled()
    {
      return this.km;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.k
 * JD-Core Version:    0.6.0
 */