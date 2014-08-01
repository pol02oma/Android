package org.apache.http.impl.client;

import java.security.Principal;
import javax.net.ssl.SSLSession;
import org.apache.http.annotation.Immutable;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.conn.HttpRoutedConnection;
import org.apache.http.protocol.HttpContext;

@Immutable
public class DefaultUserTokenHandler
  implements UserTokenHandler
{
  private static Principal getAuthPrincipal(AuthState paramAuthState)
  {
    AuthScheme localAuthScheme = paramAuthState.getAuthScheme();
    if ((localAuthScheme != null) && (localAuthScheme.isComplete()) && (localAuthScheme.isConnectionBased()))
    {
      Credentials localCredentials = paramAuthState.getCredentials();
      if (localCredentials != null)
        return localCredentials.getUserPrincipal();
    }
    return null;
  }

  public Object getUserToken(HttpContext paramHttpContext)
  {
    AuthState localAuthState = (AuthState)paramHttpContext.getAttribute("http.auth.target-scope");
    Principal localPrincipal = null;
    if (localAuthState != null)
    {
      localPrincipal = getAuthPrincipal(localAuthState);
      if (localPrincipal == null)
        localPrincipal = getAuthPrincipal((AuthState)paramHttpContext.getAttribute("http.auth.proxy-scope"));
    }
    if (localPrincipal == null)
    {
      HttpRoutedConnection localHttpRoutedConnection = (HttpRoutedConnection)paramHttpContext.getAttribute("http.connection");
      if (localHttpRoutedConnection.isOpen())
      {
        SSLSession localSSLSession = localHttpRoutedConnection.getSSLSession();
        if (localSSLSession != null)
          localPrincipal = localSSLSession.getLocalPrincipal();
      }
    }
    return localPrincipal;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.client.DefaultUserTokenHandler
 * JD-Core Version:    0.6.0
 */