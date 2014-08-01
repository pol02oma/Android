package oauth.signpost;

import oauth.signpost.http.HttpRequest;
import oauth.signpost.http.HttpResponse;

public abstract interface OAuthProviderListener
{
  public abstract boolean onResponseReceived(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse)
    throws Exception;

  public abstract void prepareRequest(HttpRequest paramHttpRequest)
    throws Exception;

  public abstract void prepareSubmission(HttpRequest paramHttpRequest)
    throws Exception;
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     oauth.signpost.OAuthProviderListener
 * JD-Core Version:    0.6.0
 */