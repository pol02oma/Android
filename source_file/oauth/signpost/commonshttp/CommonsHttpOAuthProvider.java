package oauth.signpost.commonshttp;

import java.io.IOException;
import oauth.signpost.AbstractOAuthProvider;
import oauth.signpost.http.HttpRequest;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

public class CommonsHttpOAuthProvider extends AbstractOAuthProvider
{
  private static final long serialVersionUID = 1L;
  private transient HttpClient httpClient;

  public CommonsHttpOAuthProvider(String paramString1, String paramString2, String paramString3)
  {
    super(paramString1, paramString2, paramString3);
    this.httpClient = new DefaultHttpClient();
  }

  public CommonsHttpOAuthProvider(String paramString1, String paramString2, String paramString3, HttpClient paramHttpClient)
  {
    super(paramString1, paramString2, paramString3);
    this.httpClient = paramHttpClient;
  }

  protected void closeConnection(HttpRequest paramHttpRequest, oauth.signpost.http.HttpResponse paramHttpResponse)
    throws Exception
  {
    HttpEntity localHttpEntity;
    if (paramHttpResponse != null)
    {
      localHttpEntity = ((org.apache.http.HttpResponse)paramHttpResponse.unwrap()).getEntity();
      if (localHttpEntity == null);
    }
    try
    {
      localHttpEntity.consumeContent();
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }

  protected HttpRequest createRequest(String paramString)
    throws Exception
  {
    return new HttpRequestAdapter(new HttpPost(paramString));
  }

  protected oauth.signpost.http.HttpResponse sendRequest(HttpRequest paramHttpRequest)
    throws Exception
  {
    return new HttpResponseAdapter(this.httpClient.execute((HttpUriRequest)paramHttpRequest.unwrap()));
  }

  public void setHttpClient(HttpClient paramHttpClient)
  {
    this.httpClient = paramHttpClient;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     oauth.signpost.commonshttp.CommonsHttpOAuthProvider
 * JD-Core Version:    0.6.0
 */