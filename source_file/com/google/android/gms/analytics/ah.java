package com.google.android.gms.analytics;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;

class ah
  implements n
{
  private final Context mContext;
  private GoogleAnalytics rA;
  private final String ul;
  private final HttpClient um;
  private URL un;

  ah(HttpClient paramHttpClient, Context paramContext)
  {
    this(paramHttpClient, GoogleAnalytics.getInstance(paramContext), paramContext);
  }

  ah(HttpClient paramHttpClient, GoogleAnalytics paramGoogleAnalytics, Context paramContext)
  {
    this.mContext = paramContext.getApplicationContext();
    this.ul = a("GoogleAnalytics", "3.0", Build.VERSION.RELEASE, ak.a(Locale.getDefault()), Build.MODEL, Build.ID);
    this.um = paramHttpClient;
    this.rA = paramGoogleAnalytics;
  }

  // ERROR //
  private void a(ab paramab, URL paramURL, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 89	com/google/android/gms/analytics/ab:cn	()Ljava/lang/String;
    //   4: invokestatic 95	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   7: ifeq +4 -> 11
    //   10: return
    //   11: aload_2
    //   12: ifnonnull +254 -> 266
    //   15: aload_0
    //   16: getfield 97	com/google/android/gms/analytics/ah:un	Ljava/net/URL;
    //   19: ifnull +190 -> 209
    //   22: aload_0
    //   23: getfield 97	com/google/android/gms/analytics/ah:un	Ljava/net/URL;
    //   26: astore 4
    //   28: new 99	org/apache/http/HttpHost
    //   31: dup
    //   32: aload 4
    //   34: invokevirtual 104	java/net/URL:getHost	()Ljava/lang/String;
    //   37: aload 4
    //   39: invokevirtual 108	java/net/URL:getPort	()I
    //   42: aload 4
    //   44: invokevirtual 111	java/net/URL:getProtocol	()Ljava/lang/String;
    //   47: invokespecial 114	org/apache/http/HttpHost:<init>	(Ljava/lang/String;ILjava/lang/String;)V
    //   50: astore 5
    //   52: aload_0
    //   53: aload_1
    //   54: invokevirtual 89	com/google/android/gms/analytics/ab:cn	()Ljava/lang/String;
    //   57: aload 4
    //   59: invokevirtual 117	java/net/URL:getPath	()Ljava/lang/String;
    //   62: invokespecial 121	com/google/android/gms/analytics/ah:c	(Ljava/lang/String;Ljava/lang/String;)Lorg/apache/http/HttpEntityEnclosingRequest;
    //   65: astore 8
    //   67: aload 8
    //   69: ifnull -59 -> 10
    //   72: aload 8
    //   74: ldc 123
    //   76: aload 5
    //   78: invokevirtual 126	org/apache/http/HttpHost:toHostString	()Ljava/lang/String;
    //   81: invokeinterface 132 3 0
    //   86: invokestatic 138	com/google/android/gms/analytics/aa:cm	()Z
    //   89: ifeq +9 -> 98
    //   92: aload_0
    //   93: aload 8
    //   95: invokespecial 141	com/google/android/gms/analytics/ah:a	(Lorg/apache/http/HttpEntityEnclosingRequest;)V
    //   98: iload_3
    //   99: ifeq +10 -> 109
    //   102: aload_0
    //   103: getfield 38	com/google/android/gms/analytics/ah:mContext	Landroid/content/Context;
    //   106: invokestatic 147	com/google/android/gms/analytics/q:p	(Landroid/content/Context;)V
    //   109: aload_0
    //   110: getfield 74	com/google/android/gms/analytics/ah:um	Lorg/apache/http/client/HttpClient;
    //   113: aload 5
    //   115: aload 8
    //   117: invokeinterface 153 3 0
    //   122: astore 9
    //   124: aload 9
    //   126: invokeinterface 159 1 0
    //   131: invokeinterface 164 1 0
    //   136: istore 10
    //   138: aload 9
    //   140: invokeinterface 168 1 0
    //   145: astore 11
    //   147: aload 11
    //   149: ifnull +10 -> 159
    //   152: aload 11
    //   154: invokeinterface 173 1 0
    //   159: iload 10
    //   161: sipush 200
    //   164: if_icmpeq -154 -> 10
    //   167: new 175	java/lang/StringBuilder
    //   170: dup
    //   171: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   174: ldc 178
    //   176: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   179: aload 9
    //   181: invokeinterface 159 1 0
    //   186: invokeinterface 164 1 0
    //   191: invokevirtual 185	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   194: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   197: invokestatic 192	com/google/android/gms/analytics/aa:w	(Ljava/lang/String;)V
    //   200: return
    //   201: astore 7
    //   203: ldc 194
    //   205: invokestatic 192	com/google/android/gms/analytics/aa:w	(Ljava/lang/String;)V
    //   208: return
    //   209: new 101	java/net/URL
    //   212: dup
    //   213: ldc 196
    //   215: invokespecial 198	java/net/URL:<init>	(Ljava/lang/String;)V
    //   218: astore 4
    //   220: goto -192 -> 28
    //   223: astore 12
    //   225: return
    //   226: astore 6
    //   228: new 175	java/lang/StringBuilder
    //   231: dup
    //   232: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   235: ldc 200
    //   237: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   240: aload 6
    //   242: invokevirtual 204	java/lang/Object:getClass	()Ljava/lang/Class;
    //   245: invokevirtual 209	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   248: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   251: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   254: invokestatic 192	com/google/android/gms/analytics/aa:w	(Ljava/lang/String;)V
    //   257: aload 6
    //   259: invokevirtual 212	java/io/IOException:getMessage	()Ljava/lang/String;
    //   262: invokestatic 192	com/google/android/gms/analytics/aa:w	(Ljava/lang/String;)V
    //   265: return
    //   266: aload_2
    //   267: astore 4
    //   269: goto -241 -> 28
    //
    // Exception table:
    //   from	to	target	type
    //   52	67	201	org/apache/http/client/ClientProtocolException
    //   72	98	201	org/apache/http/client/ClientProtocolException
    //   102	109	201	org/apache/http/client/ClientProtocolException
    //   109	147	201	org/apache/http/client/ClientProtocolException
    //   152	159	201	org/apache/http/client/ClientProtocolException
    //   167	200	201	org/apache/http/client/ClientProtocolException
    //   15	28	223	java/net/MalformedURLException
    //   209	220	223	java/net/MalformedURLException
    //   52	67	226	java/io/IOException
    //   72	98	226	java/io/IOException
    //   102	109	226	java/io/IOException
    //   109	147	226	java/io/IOException
    //   152	159	226	java/io/IOException
    //   167	200	226	java/io/IOException
  }

  private void a(HttpEntityEnclosingRequest paramHttpEntityEnclosingRequest)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Header[] arrayOfHeader = paramHttpEntityEnclosingRequest.getAllHeaders();
    int i = arrayOfHeader.length;
    for (int j = 0; j < i; j++)
      localStringBuffer.append(arrayOfHeader[j].toString()).append("\n");
    localStringBuffer.append(paramHttpEntityEnclosingRequest.getRequestLine().toString()).append("\n");
    if (paramHttpEntityEnclosingRequest.getEntity() != null);
    try
    {
      InputStream localInputStream = paramHttpEntityEnclosingRequest.getEntity().getContent();
      if (localInputStream != null)
      {
        int k = localInputStream.available();
        if (k > 0)
        {
          byte[] arrayOfByte = new byte[k];
          localInputStream.read(arrayOfByte);
          localStringBuffer.append("POST:\n");
          localStringBuffer.append(new String(arrayOfByte)).append("\n");
        }
      }
      aa.v(localStringBuffer.toString());
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        aa.v("Error Writing hit to log...");
    }
  }

  private HttpEntityEnclosingRequest c(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1))
    {
      aa.w("Empty hit, discarding.");
      return null;
    }
    String str = paramString2 + "?" + paramString1;
    BasicHttpEntityEnclosingRequest localBasicHttpEntityEnclosingRequest;
    if (str.length() < 2036)
      localBasicHttpEntityEnclosingRequest = new BasicHttpEntityEnclosingRequest("GET", str);
    while (true)
    {
      localBasicHttpEntityEnclosingRequest.addHeader("User-Agent", this.ul);
      return localBasicHttpEntityEnclosingRequest;
      localBasicHttpEntityEnclosingRequest = new BasicHttpEntityEnclosingRequest("POST", paramString2);
      try
      {
        localBasicHttpEntityEnclosingRequest.setEntity(new StringEntity(paramString1));
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        aa.w("Encoding error, discarding hit");
      }
    }
    return null;
  }

  public void A(String paramString)
  {
    try
    {
      this.un = new URL(paramString);
      return;
    }
    catch (MalformedURLException localMalformedURLException)
    {
      this.un = null;
    }
  }

  public int a(List<x> paramList, ab paramab, boolean paramBoolean)
  {
    int i = 0;
    int j = Math.min(paramList.size(), 40);
    paramab.c("_hr", paramList.size());
    int k = 0;
    Object localObject1 = null;
    boolean bool = true;
    int m = 0;
    if (m < j)
    {
      x localx = (x)paramList.get(m);
      URL localURL = a(localx);
      localx.ch().contains("_t=flow");
      int n;
      Object localObject2;
      if (localURL == null)
      {
        if (aa.cm())
          aa.w("No destination: discarding hit: " + localx.ch());
        while (true)
        {
          int i3 = i + 1;
          k++;
          Object localObject3 = localObject1;
          n = i3;
          localObject2 = localObject3;
          m++;
          i = n;
          localObject1 = localObject2;
          break;
          aa.w("No destination: discarding hit.");
        }
      }
      HttpHost localHttpHost = new HttpHost(localURL.getHost(), localURL.getPort(), localURL.getProtocol());
      String str1 = localURL.getPath();
      if (TextUtils.isEmpty(localx.ch()));
      HttpEntityEnclosingRequest localHttpEntityEnclosingRequest;
      for (String str2 = ""; ; str2 = y.a(localx, System.currentTimeMillis()))
      {
        localHttpEntityEnclosingRequest = c(str2, str1);
        if (localHttpEntityEnclosingRequest != null)
          break label258;
        int i2 = i + 1;
        k++;
        n = i2;
        localObject2 = localURL;
        break;
      }
      label258: localHttpEntityEnclosingRequest.addHeader("Host", localHttpHost.toHostString());
      if (aa.cm())
        a(localHttpEntityEnclosingRequest);
      if (str2.length() > 8192)
      {
        aa.w("Hit too long (> 8192 bytes)--not sent");
        k++;
      }
      while (true)
      {
        paramab.c("_td", str2.getBytes().length);
        n = i + 1;
        localObject2 = localURL;
        break;
        if (this.rA.isDryRunEnabled())
        {
          aa.u("Dry run enabled. Hit not actually sent.");
          continue;
        }
        if (bool);
        try
        {
          q.p(this.mContext);
          bool = false;
          HttpResponse localHttpResponse = this.um.execute(localHttpHost, localHttpEntityEnclosingRequest);
          int i1 = localHttpResponse.getStatusLine().getStatusCode();
          HttpEntity localHttpEntity = localHttpResponse.getEntity();
          if (localHttpEntity != null)
            localHttpEntity.consumeContent();
          if (i1 == 200)
            continue;
          aa.w("Bad response: " + localHttpResponse.getStatusLine().getStatusCode());
        }
        catch (ClientProtocolException localClientProtocolException)
        {
          aa.w("ClientProtocolException sending hit; discarding hit...");
          paramab.c("_hd", k);
        }
        catch (IOException localIOException)
        {
          aa.w("Exception sending hit: " + localIOException.getClass().getSimpleName());
          aa.w(localIOException.getMessage());
          paramab.c("_de", 1);
          paramab.c("_hd", k);
          paramab.c("_hs", i);
          a(paramab, localURL, bool);
          return i;
        }
      }
    }
    paramab.c("_hd", k);
    paramab.c("_hs", i);
    if (paramBoolean)
      a(paramab, localObject1, bool);
    return i;
  }

  String a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[] { paramString1, paramString2, paramString3, paramString4, paramString5, paramString6 });
  }

  URL a(x paramx)
  {
    if (this.un != null)
      return this.un;
    String str1 = paramx.ck();
    while (true)
    {
      try
      {
        if ("http:".equals(str1))
        {
          str2 = "http://www.google-analytics.com/collect";
          URL localURL = new URL(str2);
          return localURL;
        }
      }
      catch (MalformedURLException localMalformedURLException)
      {
        aa.t("Error trying to parse the hardcoded host url. This really shouldn't happen.");
        return null;
      }
      String str2 = "https://ssl.google-analytics.com/collect";
    }
  }

  public boolean bA()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo == null) || (!localNetworkInfo.isConnected()))
    {
      aa.v("...no network connectivity");
      return false;
    }
    return true;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.analytics.ah
 * JD-Core Version:    0.6.0
 */