package org.apache.http.auth;

import java.io.Serializable;
import java.security.Principal;
import org.apache.http.annotation.Immutable;
import org.apache.http.util.LangUtils;

@Immutable
public final class BasicUserPrincipal
  implements Principal, Serializable
{
  private static final long serialVersionUID = -2266305184969850467L;
  private final String username;

  public BasicUserPrincipal(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("User name may not be null");
    this.username = paramString;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    BasicUserPrincipal localBasicUserPrincipal;
    do
    {
      return true;
      if (!(paramObject instanceof BasicUserPrincipal))
        break;
      localBasicUserPrincipal = (BasicUserPrincipal)paramObject;
    }
    while (LangUtils.equals(this.username, localBasicUserPrincipal.username));
    return false;
  }

  public String getName()
  {
    return this.username;
  }

  public int hashCode()
  {
    return LangUtils.hashCode(17, this.username);
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[principal: ");
    localStringBuilder.append(this.username);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.auth.BasicUserPrincipal
 * JD-Core Version:    0.6.0
 */