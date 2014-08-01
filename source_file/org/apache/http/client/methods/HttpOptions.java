package org.apache.http.client.methods;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpResponse;
import org.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class HttpOptions extends HttpRequestBase
{
  public static final String METHOD_NAME = "OPTIONS";

  public HttpOptions()
  {
  }

  public HttpOptions(String paramString)
  {
    setURI(URI.create(paramString));
  }

  public HttpOptions(URI paramURI)
  {
    setURI(paramURI);
  }

  public Set<String> getAllowedMethods(HttpResponse paramHttpResponse)
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    HeaderIterator localHeaderIterator = paramHttpResponse.headerIterator("Allow");
    HashSet localHashSet = new HashSet();
    while (localHeaderIterator.hasNext())
    {
      HeaderElement[] arrayOfHeaderElement = localHeaderIterator.nextHeader().getElements();
      int i = arrayOfHeaderElement.length;
      for (int j = 0; j < i; j++)
        localHashSet.add(arrayOfHeaderElement[j].getName());
    }
    return localHashSet;
  }

  public String getMethod()
  {
    return "OPTIONS";
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.client.methods.HttpOptions
 * JD-Core Version:    0.6.0
 */