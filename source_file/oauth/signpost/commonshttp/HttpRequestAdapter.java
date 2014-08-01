package oauth.signpost.commonshttp;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import oauth.signpost.http.HttpRequest;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.RequestLine;
import org.apache.http.client.methods.HttpUriRequest;

public class HttpRequestAdapter
  implements HttpRequest
{
  private HttpEntity entity;
  private HttpUriRequest request;

  public HttpRequestAdapter(HttpUriRequest paramHttpUriRequest)
  {
    this.request = paramHttpUriRequest;
    if ((paramHttpUriRequest instanceof HttpEntityEnclosingRequest))
      this.entity = ((HttpEntityEnclosingRequest)paramHttpUriRequest).getEntity();
  }

  public Map<String, String> getAllHeaders()
  {
    Header[] arrayOfHeader = this.request.getAllHeaders();
    HashMap localHashMap = new HashMap();
    int i = arrayOfHeader.length;
    for (int j = 0; j < i; j++)
    {
      Header localHeader = arrayOfHeader[j];
      localHashMap.put(localHeader.getName(), localHeader.getValue());
    }
    return localHashMap;
  }

  public String getContentType()
  {
    if (this.entity == null);
    Header localHeader;
    do
    {
      return null;
      localHeader = this.entity.getContentType();
    }
    while (localHeader == null);
    return localHeader.getValue();
  }

  public String getHeader(String paramString)
  {
    Header localHeader = this.request.getFirstHeader(paramString);
    if (localHeader == null)
      return null;
    return localHeader.getValue();
  }

  public InputStream getMessagePayload()
    throws IOException
  {
    if (this.entity == null)
      return null;
    return this.entity.getContent();
  }

  public String getMethod()
  {
    return this.request.getRequestLine().getMethod();
  }

  public String getRequestUrl()
  {
    return this.request.getURI().toString();
  }

  public void setHeader(String paramString1, String paramString2)
  {
    this.request.setHeader(paramString1, paramString2);
  }

  public void setRequestUrl(String paramString)
  {
    throw new RuntimeException(new UnsupportedOperationException());
  }

  public Object unwrap()
  {
    return this.request;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     oauth.signpost.commonshttp.HttpRequestAdapter
 * JD-Core Version:    0.6.0
 */