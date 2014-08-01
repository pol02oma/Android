package org.apache.http.auth.params;

import java.nio.charset.Charset;
import org.apache.http.annotation.Immutable;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

@Immutable
public final class AuthParams
{
  public static String getCredentialCharset(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    String str = (String)paramHttpParams.getParameter("http.auth.credential-charset");
    if (str == null)
      str = HTTP.DEF_PROTOCOL_CHARSET.name();
    return str;
  }

  public static void setCredentialCharset(HttpParams paramHttpParams, String paramString)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setParameter("http.auth.credential-charset", paramString);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.auth.params.AuthParams
 * JD-Core Version:    0.6.0
 */