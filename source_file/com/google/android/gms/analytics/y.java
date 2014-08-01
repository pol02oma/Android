package com.google.android.gms.analytics;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class y
{
  static String a(x paramx, long paramLong)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramx.ch());
    if (paramx.cj() > 0L)
    {
      long l = paramLong - paramx.cj();
      if (l >= 0L)
        localStringBuilder.append("&qt").append("=").append(l);
    }
    localStringBuilder.append("&z").append("=").append(paramx.ci());
    return localStringBuilder.toString();
  }

  static String encode(String paramString)
  {
    try
    {
      String str = URLEncoder.encode(paramString, "UTF-8");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw new AssertionError("URL encoding failed for: " + paramString);
  }

  static Map<String, String> s(Map<String, String> paramMap)
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if ((!((String)localEntry.getKey()).startsWith("&")) || (localEntry.getValue() == null))
        continue;
      String str = ((String)localEntry.getKey()).substring(1);
      if (TextUtils.isEmpty(str))
        continue;
      localHashMap.put(str, localEntry.getValue());
    }
    return localHashMap;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.analytics.y
 * JD-Core Version:    0.6.0
 */