package org.apache.http.impl.cookie;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieSpec;

@NotThreadSafe
public abstract class AbstractCookieSpec
  implements CookieSpec
{
  private final Map<String, CookieAttributeHandler> attribHandlerMap = new HashMap(10);

  protected CookieAttributeHandler findAttribHandler(String paramString)
  {
    return (CookieAttributeHandler)this.attribHandlerMap.get(paramString);
  }

  protected CookieAttributeHandler getAttribHandler(String paramString)
  {
    CookieAttributeHandler localCookieAttributeHandler = findAttribHandler(paramString);
    if (localCookieAttributeHandler == null)
      throw new IllegalStateException("Handler not registered for " + paramString + " attribute.");
    return localCookieAttributeHandler;
  }

  protected Collection<CookieAttributeHandler> getAttribHandlers()
  {
    return this.attribHandlerMap.values();
  }

  public void registerAttribHandler(String paramString, CookieAttributeHandler paramCookieAttributeHandler)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Attribute name may not be null");
    if (paramCookieAttributeHandler == null)
      throw new IllegalArgumentException("Attribute handler may not be null");
    this.attribHandlerMap.put(paramString, paramCookieAttributeHandler);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.cookie.AbstractCookieSpec
 * JD-Core Version:    0.6.0
 */