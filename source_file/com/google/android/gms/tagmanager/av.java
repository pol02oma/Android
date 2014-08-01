package com.google.android.gms.tagmanager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

class av
  implements bl
{
  private HttpClient Vi;

  private InputStream a(HttpClient paramHttpClient, HttpResponse paramHttpResponse)
    throws IOException
  {
    int i = paramHttpResponse.getStatusLine().getStatusCode();
    if (i == 200)
    {
      bh.v("Success response");
      return paramHttpResponse.getEntity().getContent();
    }
    String str = "Bad response: " + i;
    if (i == 404)
      throw new FileNotFoundException(str);
    throw new IOException(str);
  }

  private void a(HttpClient paramHttpClient)
  {
    if ((paramHttpClient != null) && (paramHttpClient.getConnectionManager() != null))
      paramHttpClient.getConnectionManager().shutdown();
  }

  public InputStream bo(String paramString)
    throws IOException
  {
    this.Vi = jg();
    HttpResponse localHttpResponse = this.Vi.execute(new HttpGet(paramString));
    return a(this.Vi, localHttpResponse);
  }

  public void close()
  {
    a(this.Vi);
  }

  HttpClient jg()
  {
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 20000);
    HttpConnectionParams.setSoTimeout(localBasicHttpParams, 20000);
    return new DefaultHttpClient(localBasicHttpParams);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.av
 * JD-Core Version:    0.6.0
 */