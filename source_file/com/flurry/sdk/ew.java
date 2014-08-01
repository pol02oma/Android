package com.flurry.sdk;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.params.HttpParams;

public final class ew
{
  private static SchemeRegistry a;

  public static HttpClient a(HttpParams paramHttpParams)
  {
    return new DefaultHttpClient(paramHttpParams);
  }

  private static SchemeRegistry a()
  {
    monitorenter;
    try
    {
      SchemeRegistry localSchemeRegistry;
      if (a != null)
      {
        localSchemeRegistry = a;
        return localSchemeRegistry;
      }
      a = new SchemeRegistry();
      a.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
      if (ex.d())
        a.register(new Scheme("https", new eu(), 443));
      while (true)
      {
        localSchemeRegistry = a;
        break;
        a.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static HttpClient b(HttpParams paramHttpParams)
  {
    return new DefaultHttpClient(new SingleClientConnManager(paramHttpParams, a()), paramHttpParams);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.sdk.ew
 * JD-Core Version:    0.6.0
 */