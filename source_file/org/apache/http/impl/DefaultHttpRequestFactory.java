package org.apache.http.impl;

import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestFactory;
import org.apache.http.MethodNotSupportedException;
import org.apache.http.RequestLine;
import org.apache.http.annotation.Immutable;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.message.BasicHttpRequest;

@Immutable
public class DefaultHttpRequestFactory
  implements HttpRequestFactory
{
  private static final String[] RFC2616_COMMON_METHODS = { "GET" };
  private static final String[] RFC2616_ENTITY_ENC_METHODS = { "POST", "PUT" };
  private static final String[] RFC2616_SPECIAL_METHODS = { "HEAD", "OPTIONS", "DELETE", "TRACE", "CONNECT" };

  private static boolean isOneOf(String[] paramArrayOfString, String paramString)
  {
    for (int i = 0; i < paramArrayOfString.length; i++)
      if (paramArrayOfString[i].equalsIgnoreCase(paramString))
        return true;
    return false;
  }

  public HttpRequest newHttpRequest(String paramString1, String paramString2)
    throws MethodNotSupportedException
  {
    if (isOneOf(RFC2616_COMMON_METHODS, paramString1))
      return new BasicHttpRequest(paramString1, paramString2);
    if (isOneOf(RFC2616_ENTITY_ENC_METHODS, paramString1))
      return new BasicHttpEntityEnclosingRequest(paramString1, paramString2);
    if (isOneOf(RFC2616_SPECIAL_METHODS, paramString1))
      return new BasicHttpRequest(paramString1, paramString2);
    throw new MethodNotSupportedException(paramString1 + " method not supported");
  }

  public HttpRequest newHttpRequest(RequestLine paramRequestLine)
    throws MethodNotSupportedException
  {
    if (paramRequestLine == null)
      throw new IllegalArgumentException("Request line may not be null");
    String str = paramRequestLine.getMethod();
    if (isOneOf(RFC2616_COMMON_METHODS, str))
      return new BasicHttpRequest(paramRequestLine);
    if (isOneOf(RFC2616_ENTITY_ENC_METHODS, str))
      return new BasicHttpEntityEnclosingRequest(paramRequestLine);
    if (isOneOf(RFC2616_SPECIAL_METHODS, str))
      return new BasicHttpRequest(paramRequestLine);
    throw new MethodNotSupportedException(str + " method not supported");
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.DefaultHttpRequestFactory
 * JD-Core Version:    0.6.0
 */