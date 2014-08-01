package org.apache.http.impl.client;

import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthProtocolState;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.client.AuthenticationStrategy;
import org.apache.http.protocol.HttpContext;

public class HttpAuthenticator
{
  private final Log log;

  public HttpAuthenticator()
  {
    this(null);
  }

  public HttpAuthenticator(Log paramLog)
  {
    if (paramLog != null);
    while (true)
    {
      this.log = paramLog;
      return;
      paramLog = LogFactory.getLog(getClass());
    }
  }

  public boolean authenticate(HttpHost paramHttpHost, HttpResponse paramHttpResponse, AuthenticationStrategy paramAuthenticationStrategy, AuthState paramAuthState, HttpContext paramHttpContext)
  {
    while (true)
    {
      Map localMap;
      AuthScheme localAuthScheme;
      try
      {
        if (!this.log.isDebugEnabled())
          continue;
        this.log.debug(paramHttpHost.toHostString() + " requested authentication");
        localMap = paramAuthenticationStrategy.getChallenges(paramHttpHost, paramHttpResponse, paramHttpContext);
        if (!localMap.isEmpty())
          continue;
        this.log.debug("Response contains no authentication challenges");
        return false;
        localAuthScheme = paramAuthState.getAuthScheme();
        switch (1.$SwitchMap$org$apache$http$auth$AuthProtocolState[paramAuthState.getState().ordinal()])
        {
        default:
          Queue localQueue = paramAuthenticationStrategy.select(localMap, paramHttpHost, paramHttpResponse, paramHttpContext);
          if ((localQueue == null) || (localQueue.isEmpty()))
            break;
          if (!this.log.isDebugEnabled())
            continue;
          this.log.debug("Selected authentication options: " + localQueue);
          paramAuthState.setState(AuthProtocolState.CHALLENGED);
          paramAuthState.update(localQueue);
          return true;
        case 3:
          paramAuthState.reset();
          continue;
        case 1:
        case 2:
        case 5:
        case 4:
        }
      }
      catch (MalformedChallengeException localMalformedChallengeException)
      {
        if (!this.log.isWarnEnabled())
          continue;
        this.log.warn("Malformed challenge: " + localMalformedChallengeException.getMessage());
        paramAuthState.reset();
        return false;
      }
      if (localAuthScheme == null)
      {
        this.log.debug("Auth scheme is null");
        paramAuthenticationStrategy.authFailed(paramHttpHost, null, paramHttpContext);
        paramAuthState.reset();
        paramAuthState.setState(AuthProtocolState.FAILURE);
        return false;
      }
      if (localAuthScheme == null)
        continue;
      Header localHeader = (Header)localMap.get(localAuthScheme.getSchemeName().toLowerCase(Locale.US));
      if (localHeader != null)
      {
        this.log.debug("Authorization challenge processed");
        localAuthScheme.processChallenge(localHeader);
        if (localAuthScheme.isComplete())
        {
          this.log.debug("Authentication failed");
          paramAuthenticationStrategy.authFailed(paramHttpHost, paramAuthState.getAuthScheme(), paramHttpContext);
          paramAuthState.reset();
          paramAuthState.setState(AuthProtocolState.FAILURE);
          return false;
        }
        paramAuthState.setState(AuthProtocolState.HANDSHAKE);
        return true;
      }
      paramAuthState.reset();
    }
    return false;
    return false;
  }

  public boolean isAuthenticationRequested(HttpHost paramHttpHost, HttpResponse paramHttpResponse, AuthenticationStrategy paramAuthenticationStrategy, AuthState paramAuthState, HttpContext paramHttpContext)
  {
    if (paramAuthenticationStrategy.isAuthenticationRequested(paramHttpHost, paramHttpResponse, paramHttpContext))
    {
      this.log.debug("Authentication required");
      return true;
    }
    switch (1.$SwitchMap$org$apache$http$auth$AuthProtocolState[paramAuthState.getState().ordinal()])
    {
    default:
      paramAuthState.setState(AuthProtocolState.UNCHALLENGED);
    case 3:
    case 1:
    case 2:
    }
    while (true)
    {
      return false;
      this.log.debug("Authentication succeeded");
      paramAuthState.setState(AuthProtocolState.SUCCESS);
      paramAuthenticationStrategy.authSucceeded(paramHttpHost, paramAuthState.getAuthScheme(), paramHttpContext);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.client.HttpAuthenticator
 * JD-Core Version:    0.6.0
 */