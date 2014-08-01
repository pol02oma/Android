package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;

public final class aq
{
  public static final ar lW = new ar()
  {
    public void a(dd paramdd, Map<String, String> paramMap)
    {
      String str1 = (String)paramMap.get("urls");
      if (str1 == null)
      {
        da.w("URLs missing in canOpenURLs GMSG.");
        return;
      }
      String[] arrayOfString1 = str1.split(",");
      HashMap localHashMap = new HashMap();
      PackageManager localPackageManager = paramdd.getContext().getPackageManager();
      int i = arrayOfString1.length;
      int j = 0;
      if (j < i)
      {
        String str2 = arrayOfString1[j];
        String[] arrayOfString2 = str2.split(";", 2);
        String str3 = arrayOfString2[0].trim();
        String str4;
        if (arrayOfString2.length > 1)
        {
          str4 = arrayOfString2[1].trim();
          label105: if (localPackageManager.resolveActivity(new Intent(str4, Uri.parse(str3)), 65536) == null)
            break label158;
        }
        label158: for (boolean bool = true; ; bool = false)
        {
          localHashMap.put(str2, Boolean.valueOf(bool));
          j++;
          break;
          str4 = "android.intent.action.VIEW";
          break label105;
        }
      }
      paramdd.a("openableURLs", localHashMap);
    }
  };
  public static final ar lX = new ar()
  {
    public void a(dd paramdd, Map<String, String> paramMap)
    {
      String str1 = (String)paramMap.get("u");
      if (str1 == null)
      {
        da.w("URL missing from click GMSG.");
        return;
      }
      Uri localUri1 = Uri.parse(str1);
      try
      {
        l locall = paramdd.bc();
        if ((locall != null) && (locall.a(localUri1)))
        {
          Uri localUri3 = locall.a(localUri1, paramdd.getContext());
          localUri2 = localUri3;
          String str2 = localUri2.toString();
          new cy(paramdd.getContext(), paramdd.bd().pU, str2).start();
          return;
        }
      }
      catch (m localm)
      {
        while (true)
        {
          da.w("Unable to append parameter to URL: " + str1);
          Uri localUri2 = localUri1;
        }
      }
    }
  };
  public static final ar lY = new ar()
  {
    public void a(dd paramdd, Map<String, String> paramMap)
    {
      bo localbo = paramdd.ba();
      if (localbo == null)
      {
        da.w("A GMSG tried to close something that wasn't an overlay.");
        return;
      }
      localbo.close();
    }
  };
  public static final ar lZ = new ar()
  {
    public void a(dd paramdd, Map<String, String> paramMap)
    {
      bo localbo = paramdd.ba();
      if (localbo == null)
      {
        da.w("A GMSG tried to use a custom close button on something that wasn't an overlay.");
        return;
      }
      localbo.g("1".equals(paramMap.get("custom_close")));
    }
  };
  public static final ar ma = new ar()
  {
    public void a(dd paramdd, Map<String, String> paramMap)
    {
      String str = (String)paramMap.get("u");
      if (str == null)
      {
        da.w("URL missing from httpTrack GMSG.");
        return;
      }
      new cy(paramdd.getContext(), paramdd.bd().pU, str).start();
    }
  };
  public static final ar mb = new ar()
  {
    public void a(dd paramdd, Map<String, String> paramMap)
    {
      da.u("Received log message: " + (String)paramMap.get("string"));
    }
  };
  public static final ar mc = new as();
  public static final ar md = new ar()
  {
    public void a(dd paramdd, Map<String, String> paramMap)
    {
      String str1 = (String)paramMap.get("tx");
      String str2 = (String)paramMap.get("ty");
      String str3 = (String)paramMap.get("td");
      try
      {
        int i = Integer.parseInt(str1);
        int j = Integer.parseInt(str2);
        int k = Integer.parseInt(str3);
        l locall = paramdd.bc();
        if (locall != null)
          locall.y().a(i, j, k);
        return;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        da.w("Could not parse touch parameters from gmsg.");
      }
    }
  };
  public static final ar me = new at();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.aq
 * JD-Core Version:    0.6.0
 */