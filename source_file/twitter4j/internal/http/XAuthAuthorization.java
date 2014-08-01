package twitter4j.internal.http;

import java.io.Serializable;
import twitter4j.auth.Authorization;
import twitter4j.auth.BasicAuthorization;

public class XAuthAuthorization
  implements Authorization, Serializable
{
  private static final long serialVersionUID = -6082451214083464902L;
  private BasicAuthorization basic;
  private String consumerKey;
  private String consumerSecret;

  public XAuthAuthorization(BasicAuthorization paramBasicAuthorization)
  {
    this.basic = paramBasicAuthorization;
  }

  public String getAuthorizationHeader(HttpRequest paramHttpRequest)
  {
    return this.basic.getAuthorizationHeader(paramHttpRequest);
  }

  public String getConsumerKey()
  {
    return this.consumerKey;
  }

  public String getConsumerSecret()
  {
    return this.consumerSecret;
  }

  public String getPassword()
  {
    return this.basic.getPassword();
  }

  public String getUserId()
  {
    return this.basic.getUserId();
  }

  public boolean isEnabled()
  {
    return this.basic.isEnabled();
  }

  public void setOAuthConsumer(String paramString1, String paramString2)
  {
    monitorenter;
    try
    {
      this.consumerKey = paramString1;
      this.consumerSecret = paramString2;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.internal.http.XAuthAuthorization
 * JD-Core Version:    0.6.0
 */