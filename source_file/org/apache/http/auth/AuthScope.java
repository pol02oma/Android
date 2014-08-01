package org.apache.http.auth;

import java.util.Locale;
import org.apache.http.HttpHost;
import org.apache.http.annotation.Immutable;
import org.apache.http.util.LangUtils;

@Immutable
public class AuthScope
{
  public static final AuthScope ANY;
  public static final String ANY_HOST = null;
  public static final int ANY_PORT = -1;
  public static final String ANY_REALM = null;
  public static final String ANY_SCHEME = null;
  private final String host;
  private final int port;
  private final String realm;
  private final String scheme;

  static
  {
    ANY = new AuthScope(ANY_HOST, -1, ANY_REALM, ANY_SCHEME);
  }

  public AuthScope(String paramString, int paramInt)
  {
    this(paramString, paramInt, ANY_REALM, ANY_SCHEME);
  }

  public AuthScope(String paramString1, int paramInt, String paramString2)
  {
    this(paramString1, paramInt, paramString2, ANY_SCHEME);
  }

  public AuthScope(String paramString1, int paramInt, String paramString2, String paramString3)
  {
    String str1;
    if (paramString1 == null)
    {
      str1 = ANY_HOST;
      this.host = str1;
      if (paramInt < 0)
        paramInt = -1;
      this.port = paramInt;
      if (paramString2 == null)
        paramString2 = ANY_REALM;
      this.realm = paramString2;
      if (paramString3 != null)
        break label72;
    }
    label72: for (String str2 = ANY_SCHEME; ; str2 = paramString3.toUpperCase(Locale.ENGLISH))
    {
      this.scheme = str2;
      return;
      str1 = paramString1.toLowerCase(Locale.ENGLISH);
      break;
    }
  }

  public AuthScope(HttpHost paramHttpHost)
  {
    this(paramHttpHost, ANY_REALM, ANY_SCHEME);
  }

  public AuthScope(HttpHost paramHttpHost, String paramString1, String paramString2)
  {
    this(paramHttpHost.getHostName(), paramHttpHost.getPort(), paramString1, paramString2);
  }

  public AuthScope(AuthScope paramAuthScope)
  {
    if (paramAuthScope == null)
      throw new IllegalArgumentException("Scope may not be null");
    this.host = paramAuthScope.getHost();
    this.port = paramAuthScope.getPort();
    this.realm = paramAuthScope.getRealm();
    this.scheme = paramAuthScope.getScheme();
  }

  public boolean equals(Object paramObject)
  {
    int i = 1;
    if (paramObject == null)
      i = 0;
    AuthScope localAuthScope;
    do
    {
      do
        return i;
      while (paramObject == this);
      if (!(paramObject instanceof AuthScope))
        return super.equals(paramObject);
      localAuthScope = (AuthScope)paramObject;
    }
    while ((LangUtils.equals(this.host, localAuthScope.host)) && (this.port == localAuthScope.port) && (LangUtils.equals(this.realm, localAuthScope.realm)) && (LangUtils.equals(this.scheme, localAuthScope.scheme)));
    return false;
  }

  public String getHost()
  {
    return this.host;
  }

  public int getPort()
  {
    return this.port;
  }

  public String getRealm()
  {
    return this.realm;
  }

  public String getScheme()
  {
    return this.scheme;
  }

  public int hashCode()
  {
    return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, this.host), this.port), this.realm), this.scheme);
  }

  public int match(AuthScope paramAuthScope)
  {
    int i;
    if (LangUtils.equals(this.scheme, paramAuthScope.scheme))
    {
      i = 0 + 1;
      if (!LangUtils.equals(this.realm, paramAuthScope.realm))
        break label110;
      i += 2;
      label36: if (this.port != paramAuthScope.port)
        break label132;
      i += 4;
      label50: if (!LangUtils.equals(this.host, paramAuthScope.host))
        break label150;
      i += 8;
    }
    label110: 
    do
    {
      return i;
      String str1 = this.scheme;
      String str2 = ANY_SCHEME;
      i = 0;
      if (str1 == str2)
        break;
      String str3 = paramAuthScope.scheme;
      String str4 = ANY_SCHEME;
      i = 0;
      if (str3 == str4)
        break;
      return -1;
      if ((this.realm == ANY_REALM) || (paramAuthScope.realm == ANY_REALM))
        break label36;
      return -1;
      if ((this.port == -1) || (paramAuthScope.port == -1))
        break label50;
      return -1;
    }
    while ((this.host == ANY_HOST) || (paramAuthScope.host == ANY_HOST));
    label132: label150: return -1;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (this.scheme != null)
    {
      localStringBuilder.append(this.scheme.toUpperCase(Locale.ENGLISH));
      localStringBuilder.append(' ');
    }
    if (this.realm != null)
    {
      localStringBuilder.append('\'');
      localStringBuilder.append(this.realm);
      localStringBuilder.append('\'');
    }
    while (true)
    {
      if (this.host != null)
      {
        localStringBuilder.append('@');
        localStringBuilder.append(this.host);
        if (this.port >= 0)
        {
          localStringBuilder.append(':');
          localStringBuilder.append(this.port);
        }
      }
      return localStringBuilder.toString();
      localStringBuilder.append("<any realm>");
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.auth.AuthScope
 * JD-Core Version:    0.6.0
 */