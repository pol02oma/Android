package oauth.signpost.basic;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import oauth.signpost.http.HttpResponse;

public class HttpURLConnectionResponseAdapter
  implements HttpResponse
{
  private HttpURLConnection connection;

  public HttpURLConnectionResponseAdapter(HttpURLConnection paramHttpURLConnection)
  {
    this.connection = paramHttpURLConnection;
  }

  public InputStream getContent()
    throws IOException
  {
    return this.connection.getInputStream();
  }

  public String getReasonPhrase()
    throws Exception
  {
    return this.connection.getResponseMessage();
  }

  public int getStatusCode()
    throws IOException
  {
    return this.connection.getResponseCode();
  }

  public Object unwrap()
  {
    return this.connection;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     oauth.signpost.basic.HttpURLConnectionResponseAdapter
 * JD-Core Version:    0.6.0
 */