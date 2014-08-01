package com.google.android.gms.internal;

import java.util.Map;

public final class as
  implements ar
{
  private static boolean a(Map<String, String> paramMap)
  {
    return "1".equals(paramMap.get("custom_close"));
  }

  private static int b(Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("o");
    if (str != null)
    {
      if ("p".equalsIgnoreCase(str))
        return cv.aU();
      if ("l".equalsIgnoreCase(str))
        return cv.aT();
    }
    return -1;
  }

  public void a(dd paramdd, Map<String, String> paramMap)
  {
    String str1 = (String)paramMap.get("a");
    if (str1 == null)
    {
      da.w("Action missing from an open GMSG.");
      return;
    }
    de localde = paramdd.bb();
    if ("expand".equalsIgnoreCase(str1))
    {
      if (paramdd.be())
      {
        da.w("Cannot expand WebView that is already expanded.");
        return;
      }
      localde.a(a(paramMap), b(paramMap));
      return;
    }
    if ("webapp".equalsIgnoreCase(str1))
    {
      String str2 = (String)paramMap.get("u");
      if (str2 != null)
      {
        localde.a(a(paramMap), b(paramMap), str2);
        return;
      }
      localde.a(a(paramMap), b(paramMap), (String)paramMap.get("html"), (String)paramMap.get("baseurl"));
      return;
    }
    localde.a(new bn((String)paramMap.get("i"), (String)paramMap.get("u"), (String)paramMap.get("m"), (String)paramMap.get("p"), (String)paramMap.get("c"), (String)paramMap.get("f"), (String)paramMap.get("e")));
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.as
 * JD-Core Version:    0.6.0
 */