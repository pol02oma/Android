package org.apache.http.protocol;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.http.annotation.GuardedBy;
import org.apache.http.annotation.ThreadSafe;

@ThreadSafe
public class UriPatternMatcher<T>
{

  @GuardedBy("this")
  private final Map<String, T> map = new HashMap();

  public Map<String, T> getObjects()
  {
    monitorenter;
    try
    {
      Map localMap = this.map;
      monitorexit;
      return localMap;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public T lookup(String paramString)
  {
    monitorenter;
    if (paramString == null)
      try
      {
        throw new IllegalArgumentException("Request URI may not be null");
      }
      finally
      {
        monitorexit;
      }
    int i = paramString.indexOf("?");
    if (i != -1)
      paramString = paramString.substring(0, i);
    Object localObject1 = this.map.get(paramString);
    if (localObject1 == null)
    {
      Object localObject2 = null;
      Iterator localIterator = this.map.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        if ((!matchUriRequestPattern(str, paramString)) || ((localObject2 != null) && (localObject2.length() >= str.length()) && ((localObject2.length() != str.length()) || (!str.endsWith("*")))))
          continue;
        Object localObject3 = this.map.get(str);
        localObject1 = localObject3;
        localObject2 = str;
      }
    }
    monitorexit;
    return localObject1;
  }

  protected boolean matchUriRequestPattern(String paramString1, String paramString2)
  {
    if (paramString1.equals("*"))
      return true;
    int i;
    if ((!paramString1.endsWith("*")) || (!paramString2.startsWith(paramString1.substring(0, -1 + paramString1.length()))))
    {
      boolean bool1 = paramString1.startsWith("*");
      i = 0;
      if (bool1)
      {
        boolean bool2 = paramString2.endsWith(paramString1.substring(1, paramString1.length()));
        i = 0;
        if (!bool2);
      }
    }
    else
    {
      i = 1;
    }
    return i;
  }

  public void register(String paramString, T paramT)
  {
    monitorenter;
    if (paramString == null)
      try
      {
        throw new IllegalArgumentException("URI request pattern may not be null");
      }
      finally
      {
        monitorexit;
      }
    this.map.put(paramString, paramT);
    monitorexit;
  }

  @Deprecated
  public void setHandlers(Map<String, T> paramMap)
  {
    monitorenter;
    if (paramMap == null)
      try
      {
        throw new IllegalArgumentException("Map of handlers may not be null");
      }
      finally
      {
        monitorexit;
      }
    this.map.clear();
    this.map.putAll(paramMap);
    monitorexit;
  }

  public void setObjects(Map<String, T> paramMap)
  {
    monitorenter;
    if (paramMap == null)
      try
      {
        throw new IllegalArgumentException("Map of handlers may not be null");
      }
      finally
      {
        monitorexit;
      }
    this.map.clear();
    this.map.putAll(paramMap);
    monitorexit;
  }

  public void unregister(String paramString)
  {
    monitorenter;
    if (paramString == null);
    while (true)
    {
      monitorexit;
      return;
      try
      {
        this.map.remove(paramString);
      }
      finally
      {
        monitorexit;
      }
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.protocol.UriPatternMatcher
 * JD-Core Version:    0.6.0
 */