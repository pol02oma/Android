package org.apache.http.impl.auth;

import org.apache.http.annotation.Immutable;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthSchemeFactory;
import org.apache.http.params.HttpParams;

@Immutable
public class SPNegoSchemeFactory
  implements AuthSchemeFactory
{
  private final boolean stripPort;

  public SPNegoSchemeFactory()
  {
    this(false);
  }

  public SPNegoSchemeFactory(boolean paramBoolean)
  {
    this.stripPort = paramBoolean;
  }

  public boolean isStripPort()
  {
    return this.stripPort;
  }

  public AuthScheme newInstance(HttpParams paramHttpParams)
  {
    return new SPNegoScheme(this.stripPort);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.auth.SPNegoSchemeFactory
 * JD-Core Version:    0.6.0
 */