package org.apache.http.protocol;

import java.util.HashMap;
import java.util.Map;
import org.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class BasicHttpContext
  implements HttpContext
{
  private Map<String, Object> map = null;
  private final HttpContext parentContext;

  public BasicHttpContext()
  {
    this(null);
  }

  public BasicHttpContext(HttpContext paramHttpContext)
  {
    this.parentContext = paramHttpContext;
  }

  public void clear()
  {
    if (this.map != null)
      this.map.clear();
  }

  public Object getAttribute(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Id may not be null");
    Map localMap = this.map;
    Object localObject = null;
    if (localMap != null)
      localObject = this.map.get(paramString);
    if ((localObject == null) && (this.parentContext != null))
      localObject = this.parentContext.getAttribute(paramString);
    return localObject;
  }

  public Object removeAttribute(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Id may not be null");
    if (this.map != null)
      return this.map.remove(paramString);
    return null;
  }

  public void setAttribute(String paramString, Object paramObject)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Id may not be null");
    if (this.map == null)
      this.map = new HashMap();
    this.map.put(paramString, paramObject);
  }

  public String toString()
  {
    if (this.map != null)
      return this.map.toString();
    return "{}";
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.protocol.BasicHttpContext
 * JD-Core Version:    0.6.0
 */