package org.apache.http.protocol;

import org.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public final class DefaultedHttpContext
  implements HttpContext
{
  private final HttpContext defaults;
  private final HttpContext local;

  public DefaultedHttpContext(HttpContext paramHttpContext1, HttpContext paramHttpContext2)
  {
    if (paramHttpContext1 == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    this.local = paramHttpContext1;
    this.defaults = paramHttpContext2;
  }

  public Object getAttribute(String paramString)
  {
    Object localObject = this.local.getAttribute(paramString);
    if (localObject == null)
      localObject = this.defaults.getAttribute(paramString);
    return localObject;
  }

  public HttpContext getDefaults()
  {
    return this.defaults;
  }

  public Object removeAttribute(String paramString)
  {
    return this.local.removeAttribute(paramString);
  }

  public void setAttribute(String paramString, Object paramObject)
  {
    this.local.setAttribute(paramString, paramObject);
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[local: ").append(this.local);
    localStringBuilder.append("defaults: ").append(this.defaults);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.protocol.DefaultedHttpContext
 * JD-Core Version:    0.6.0
 */