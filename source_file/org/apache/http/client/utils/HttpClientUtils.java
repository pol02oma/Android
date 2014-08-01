package org.apache.http.client.utils;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.util.EntityUtils;

public class HttpClientUtils
{
  public static void closeQuietly(HttpResponse paramHttpResponse)
  {
    HttpEntity localHttpEntity;
    if (paramHttpResponse != null)
    {
      localHttpEntity = paramHttpResponse.getEntity();
      if (localHttpEntity == null);
    }
    try
    {
      EntityUtils.consume(localHttpEntity);
      return;
    }
    catch (IOException localIOException)
    {
    }
  }

  public static void closeQuietly(HttpClient paramHttpClient)
  {
    if (paramHttpClient != null)
      paramHttpClient.getConnectionManager().shutdown();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.client.utils.HttpClientUtils
 * JD-Core Version:    0.6.0
 */