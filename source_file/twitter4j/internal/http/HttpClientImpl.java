package twitter4j.internal.http;

import java.io.IOException;
import java.io.Serializable;
import java.net.Authenticator;
import java.net.Authenticator.RequestorType;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import twitter4j.TwitterException;
import twitter4j.auth.Authorization;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationContext;
import twitter4j.internal.logging.Logger;
import twitter4j.internal.util.z_T4JInternalStringUtil;

public class HttpClientImpl extends HttpClientBase
  implements HttpResponseCode, Serializable
{
  private static final Map<HttpClientConfiguration, HttpClient> instanceMap;
  private static final Logger logger = Logger.getLogger(HttpClientImpl.class);
  private static final long serialVersionUID = -8819171414069621503L;

  static
  {
    if (ConfigurationContext.getInstance().isDalvik())
      System.setProperty("http.keepAlive", "false");
    instanceMap = new HashMap(1);
  }

  public HttpClientImpl()
  {
    super(ConfigurationContext.getInstance());
  }

  public HttpClientImpl(HttpClientConfiguration paramHttpClientConfiguration)
  {
    super(paramHttpClientConfiguration);
  }

  public static HttpClient getInstance(HttpClientConfiguration paramHttpClientConfiguration)
  {
    Object localObject = (HttpClient)instanceMap.get(paramHttpClientConfiguration);
    if (localObject == null)
    {
      localObject = new HttpClientImpl(paramHttpClientConfiguration);
      instanceMap.put(paramHttpClientConfiguration, localObject);
    }
    return (HttpClient)localObject;
  }

  private void setHeaders(HttpRequest paramHttpRequest, HttpURLConnection paramHttpURLConnection)
  {
    if (logger.isDebugEnabled())
    {
      logger.debug("Request: ");
      logger.debug(paramHttpRequest.getMethod().name() + " ", paramHttpRequest.getURL());
    }
    if (paramHttpRequest.getAuthorization() != null)
    {
      String str2 = paramHttpRequest.getAuthorization().getAuthorizationHeader(paramHttpRequest);
      if (str2 != null)
      {
        if (logger.isDebugEnabled())
          logger.debug("Authorization: ", z_T4JInternalStringUtil.maskString(str2));
        paramHttpURLConnection.addRequestProperty("Authorization", str2);
      }
    }
    if (paramHttpRequest.getRequestHeaders() != null)
    {
      Iterator localIterator = paramHttpRequest.getRequestHeaders().keySet().iterator();
      while (localIterator.hasNext())
      {
        String str1 = (String)localIterator.next();
        paramHttpURLConnection.addRequestProperty(str1, (String)paramHttpRequest.getRequestHeaders().get(str1));
        logger.debug(str1 + ": " + (String)paramHttpRequest.getRequestHeaders().get(str1));
      }
    }
  }

  public HttpResponse get(String paramString)
    throws TwitterException
  {
    return request(new HttpRequest(RequestMethod.GET, paramString, null, null, null));
  }

  protected HttpURLConnection getConnection(String paramString)
    throws IOException
  {
    Proxy localProxy;
    if (isProxyConfigured())
    {
      if ((this.CONF.getHttpProxyUser() != null) && (!this.CONF.getHttpProxyUser().equals("")))
      {
        if (logger.isDebugEnabled())
        {
          logger.debug("Proxy AuthUser: " + this.CONF.getHttpProxyUser());
          logger.debug("Proxy AuthPassword: " + z_T4JInternalStringUtil.maskString(this.CONF.getHttpProxyPassword()));
        }
        Authenticator.setDefault(new Authenticator()
        {
          protected PasswordAuthentication getPasswordAuthentication()
          {
            if (getRequestorType().equals(Authenticator.RequestorType.PROXY))
              return new PasswordAuthentication(HttpClientImpl.this.CONF.getHttpProxyUser(), HttpClientImpl.this.CONF.getHttpProxyPassword().toCharArray());
            return null;
          }
        });
      }
      localProxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(this.CONF.getHttpProxyHost(), this.CONF.getHttpProxyPort()));
      if (logger.isDebugEnabled())
        logger.debug("Opening proxied connection(" + this.CONF.getHttpProxyHost() + ":" + this.CONF.getHttpProxyPort() + ")");
    }
    for (HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(paramString).openConnection(localProxy); ; localHttpURLConnection = (HttpURLConnection)new URL(paramString).openConnection())
    {
      if (this.CONF.getHttpConnectionTimeout() > 0)
        localHttpURLConnection.setConnectTimeout(this.CONF.getHttpConnectionTimeout());
      if (this.CONF.getHttpReadTimeout() > 0)
        localHttpURLConnection.setReadTimeout(this.CONF.getHttpReadTimeout());
      localHttpURLConnection.setInstanceFollowRedirects(false);
      return localHttpURLConnection;
    }
  }

  public HttpResponse post(String paramString, HttpParameter[] paramArrayOfHttpParameter)
    throws TwitterException
  {
    return request(new HttpRequest(RequestMethod.POST, paramString, paramArrayOfHttpParameter, null, null));
  }

  // ERROR //
  public HttpResponse request(HttpRequest paramHttpRequest)
    throws TwitterException
  {
    // Byte code:
    //   0: iconst_1
    //   1: aload_0
    //   2: getfield 193	twitter4j/internal/http/HttpClientImpl:CONF	Ltwitter4j/internal/http/HttpClientConfiguration;
    //   5: invokeinterface 294 1 0
    //   10: iadd
    //   11: istore_2
    //   12: iconst_0
    //   13: istore_3
    //   14: aconst_null
    //   15: astore 4
    //   17: iload_3
    //   18: iload_2
    //   19: if_icmpge +1081 -> 1100
    //   22: iconst_m1
    //   23: istore 5
    //   25: aconst_null
    //   26: astore 6
    //   28: aload_0
    //   29: aload_1
    //   30: invokevirtual 113	twitter4j/internal/http/HttpRequest:getURL	()Ljava/lang/String;
    //   33: invokevirtual 296	twitter4j/internal/http/HttpClientImpl:getConnection	(Ljava/lang/String;)Ljava/net/HttpURLConnection;
    //   36: astore 15
    //   38: aload 15
    //   40: iconst_1
    //   41: invokevirtual 299	java/net/HttpURLConnection:setDoInput	(Z)V
    //   44: aload_0
    //   45: aload_1
    //   46: aload 15
    //   48: invokespecial 301	twitter4j/internal/http/HttpClientImpl:setHeaders	(Ltwitter4j/internal/http/HttpRequest;Ljava/net/HttpURLConnection;)V
    //   51: aload 15
    //   53: aload_1
    //   54: invokevirtual 95	twitter4j/internal/http/HttpRequest:getMethod	()Ltwitter4j/internal/http/RequestMethod;
    //   57: invokevirtual 101	twitter4j/internal/http/RequestMethod:name	()Ljava/lang/String;
    //   60: invokevirtual 304	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   63: aload_1
    //   64: invokevirtual 95	twitter4j/internal/http/HttpRequest:getMethod	()Ltwitter4j/internal/http/RequestMethod;
    //   67: astore 16
    //   69: getstatic 287	twitter4j/internal/http/RequestMethod:POST	Ltwitter4j/internal/http/RequestMethod;
    //   72: astore 17
    //   74: aconst_null
    //   75: astore 6
    //   77: aload 16
    //   79: aload 17
    //   81: if_acmpne +578 -> 659
    //   84: aload_1
    //   85: invokevirtual 308	twitter4j/internal/http/HttpRequest:getParameters	()[Ltwitter4j/internal/http/HttpParameter;
    //   88: invokestatic 314	twitter4j/internal/http/HttpParameter:containsFile	([Ltwitter4j/internal/http/HttpParameter;)Z
    //   91: istore 18
    //   93: aconst_null
    //   94: astore 6
    //   96: iload 18
    //   98: ifeq +733 -> 831
    //   101: new 87	java/lang/StringBuilder
    //   104: dup
    //   105: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   108: ldc_w 316
    //   111: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   114: invokestatic 320	java/lang/System:currentTimeMillis	()J
    //   117: invokevirtual 323	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   120: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   123: astore 19
    //   125: aload 15
    //   127: ldc_w 325
    //   130: new 87	java/lang/StringBuilder
    //   133: dup
    //   134: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   137: ldc_w 327
    //   140: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: aload 19
    //   145: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   151: invokevirtual 330	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   154: new 87	java/lang/StringBuilder
    //   157: dup
    //   158: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   161: ldc_w 332
    //   164: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   167: aload 19
    //   169: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   172: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   175: astore 20
    //   177: aload 15
    //   179: iconst_1
    //   180: invokevirtual 335	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   183: aload 15
    //   185: invokevirtual 339	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   188: astore 6
    //   190: new 341	java/io/DataOutputStream
    //   193: dup
    //   194: aload 6
    //   196: invokespecial 344	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   199: astore 21
    //   201: aload_1
    //   202: invokevirtual 308	twitter4j/internal/http/HttpRequest:getParameters	()[Ltwitter4j/internal/http/HttpParameter;
    //   205: astore 22
    //   207: aload 22
    //   209: arraylength
    //   210: istore 23
    //   212: iconst_0
    //   213: istore 24
    //   215: iload 24
    //   217: iload 23
    //   219: if_icmpge +394 -> 613
    //   222: aload 22
    //   224: iload 24
    //   226: aaload
    //   227: astore 25
    //   229: aload 25
    //   231: invokevirtual 347	twitter4j/internal/http/HttpParameter:isFile	()Z
    //   234: ifeq +268 -> 502
    //   237: aload_0
    //   238: aload 21
    //   240: new 87	java/lang/StringBuilder
    //   243: dup
    //   244: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   247: aload 20
    //   249: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   252: ldc_w 349
    //   255: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   258: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   261: invokevirtual 353	twitter4j/internal/http/HttpClientImpl:write	(Ljava/io/DataOutputStream;Ljava/lang/String;)V
    //   264: aload_0
    //   265: aload 21
    //   267: new 87	java/lang/StringBuilder
    //   270: dup
    //   271: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   274: ldc_w 355
    //   277: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   280: aload 25
    //   282: invokevirtual 358	twitter4j/internal/http/HttpParameter:getName	()Ljava/lang/String;
    //   285: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   288: ldc_w 360
    //   291: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   294: aload 25
    //   296: invokevirtual 364	twitter4j/internal/http/HttpParameter:getFile	()Ljava/io/File;
    //   299: invokevirtual 367	java/io/File:getName	()Ljava/lang/String;
    //   302: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   305: ldc_w 369
    //   308: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   311: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   314: invokevirtual 353	twitter4j/internal/http/HttpClientImpl:write	(Ljava/io/DataOutputStream;Ljava/lang/String;)V
    //   317: aload_0
    //   318: aload 21
    //   320: new 87	java/lang/StringBuilder
    //   323: dup
    //   324: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   327: ldc_w 371
    //   330: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   333: aload 25
    //   335: invokevirtual 374	twitter4j/internal/http/HttpParameter:getContentType	()Ljava/lang/String;
    //   338: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   341: ldc_w 376
    //   344: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   347: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   350: invokevirtual 353	twitter4j/internal/http/HttpClientImpl:write	(Ljava/io/DataOutputStream;Ljava/lang/String;)V
    //   353: aload 25
    //   355: invokevirtual 379	twitter4j/internal/http/HttpParameter:hasFileBody	()Z
    //   358: ifeq +110 -> 468
    //   361: aload 25
    //   363: invokevirtual 383	twitter4j/internal/http/HttpParameter:getFileBody	()Ljava/io/InputStream;
    //   366: astore 26
    //   368: new 385	java/io/BufferedInputStream
    //   371: dup
    //   372: aload 26
    //   374: invokespecial 388	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   377: astore 27
    //   379: sipush 1024
    //   382: newarray byte
    //   384: astore 28
    //   386: aload 27
    //   388: aload 28
    //   390: invokevirtual 392	java/io/BufferedInputStream:read	([B)I
    //   393: istore 29
    //   395: iload 29
    //   397: iconst_m1
    //   398: if_icmpeq +87 -> 485
    //   401: aload 21
    //   403: aload 28
    //   405: iconst_0
    //   406: iload 29
    //   408: invokevirtual 395	java/io/DataOutputStream:write	([BII)V
    //   411: goto -25 -> 386
    //   414: astore 7
    //   416: aload 4
    //   418: astore 8
    //   420: aload 6
    //   422: invokevirtual 400	java/io/OutputStream:close	()V
    //   425: aload 7
    //   427: athrow
    //   428: astore 10
    //   430: aload_0
    //   431: getfield 193	twitter4j/internal/http/HttpClientImpl:CONF	Ltwitter4j/internal/http/HttpClientConfiguration;
    //   434: invokeinterface 294 1 0
    //   439: istore 11
    //   441: iload_3
    //   442: iload 11
    //   444: if_icmpne +549 -> 993
    //   447: new 171	twitter4j/TwitterException
    //   450: dup
    //   451: aload 10
    //   453: invokevirtual 403	java/io/IOException:getMessage	()Ljava/lang/String;
    //   456: aload 10
    //   458: iload 5
    //   460: invokespecial 406	twitter4j/TwitterException:<init>	(Ljava/lang/String;Ljava/lang/Exception;I)V
    //   463: astore 12
    //   465: aload 12
    //   467: athrow
    //   468: new 408	java/io/FileInputStream
    //   471: dup
    //   472: aload 25
    //   474: invokevirtual 364	twitter4j/internal/http/HttpParameter:getFile	()Ljava/io/File;
    //   477: invokespecial 411	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   480: astore 26
    //   482: goto -114 -> 368
    //   485: aload_0
    //   486: aload 21
    //   488: ldc_w 349
    //   491: invokevirtual 353	twitter4j/internal/http/HttpClientImpl:write	(Ljava/io/DataOutputStream;Ljava/lang/String;)V
    //   494: aload 27
    //   496: invokevirtual 412	java/io/BufferedInputStream:close	()V
    //   499: goto +604 -> 1103
    //   502: aload_0
    //   503: aload 21
    //   505: new 87	java/lang/StringBuilder
    //   508: dup
    //   509: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   512: aload 20
    //   514: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   517: ldc_w 349
    //   520: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   523: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   526: invokevirtual 353	twitter4j/internal/http/HttpClientImpl:write	(Ljava/io/DataOutputStream;Ljava/lang/String;)V
    //   529: aload_0
    //   530: aload 21
    //   532: new 87	java/lang/StringBuilder
    //   535: dup
    //   536: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   539: ldc_w 355
    //   542: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   545: aload 25
    //   547: invokevirtual 358	twitter4j/internal/http/HttpParameter:getName	()Ljava/lang/String;
    //   550: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   553: ldc_w 369
    //   556: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   559: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   562: invokevirtual 353	twitter4j/internal/http/HttpClientImpl:write	(Ljava/io/DataOutputStream;Ljava/lang/String;)V
    //   565: aload_0
    //   566: aload 21
    //   568: ldc_w 414
    //   571: invokevirtual 353	twitter4j/internal/http/HttpClientImpl:write	(Ljava/io/DataOutputStream;Ljava/lang/String;)V
    //   574: getstatic 27	twitter4j/internal/http/HttpClientImpl:logger	Ltwitter4j/internal/logging/Logger;
    //   577: aload 25
    //   579: invokevirtual 417	twitter4j/internal/http/HttpParameter:getValue	()Ljava/lang/String;
    //   582: invokevirtual 85	twitter4j/internal/logging/Logger:debug	(Ljava/lang/String;)V
    //   585: aload 21
    //   587: aload 25
    //   589: invokevirtual 417	twitter4j/internal/http/HttpParameter:getValue	()Ljava/lang/String;
    //   592: ldc_w 419
    //   595: invokevirtual 423	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   598: invokevirtual 426	java/io/DataOutputStream:write	([B)V
    //   601: aload_0
    //   602: aload 21
    //   604: ldc_w 349
    //   607: invokevirtual 353	twitter4j/internal/http/HttpClientImpl:write	(Ljava/io/DataOutputStream;Ljava/lang/String;)V
    //   610: goto +493 -> 1103
    //   613: aload_0
    //   614: aload 21
    //   616: new 87	java/lang/StringBuilder
    //   619: dup
    //   620: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   623: aload 20
    //   625: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   628: ldc_w 428
    //   631: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   634: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   637: invokevirtual 353	twitter4j/internal/http/HttpClientImpl:write	(Ljava/io/DataOutputStream;Ljava/lang/String;)V
    //   640: aload_0
    //   641: aload 21
    //   643: ldc_w 349
    //   646: invokevirtual 353	twitter4j/internal/http/HttpClientImpl:write	(Ljava/io/DataOutputStream;Ljava/lang/String;)V
    //   649: aload 6
    //   651: invokevirtual 431	java/io/OutputStream:flush	()V
    //   654: aload 6
    //   656: invokevirtual 400	java/io/OutputStream:close	()V
    //   659: aload_0
    //   660: getfield 193	twitter4j/internal/http/HttpClientImpl:CONF	Ltwitter4j/internal/http/HttpClientConfiguration;
    //   663: astore 30
    //   665: new 433	twitter4j/internal/http/HttpResponseImpl
    //   668: dup
    //   669: aload 15
    //   671: aload 30
    //   673: invokespecial 436	twitter4j/internal/http/HttpResponseImpl:<init>	(Ljava/net/HttpURLConnection;Ltwitter4j/internal/http/HttpClientConfiguration;)V
    //   676: astore 8
    //   678: aload 15
    //   680: invokevirtual 439	java/net/HttpURLConnection:getResponseCode	()I
    //   683: istore 5
    //   685: getstatic 27	twitter4j/internal/http/HttpClientImpl:logger	Ltwitter4j/internal/logging/Logger;
    //   688: invokevirtual 79	twitter4j/internal/logging/Logger:isDebugEnabled	()Z
    //   691: ifeq +418 -> 1109
    //   694: getstatic 27	twitter4j/internal/http/HttpClientImpl:logger	Ltwitter4j/internal/logging/Logger;
    //   697: ldc_w 441
    //   700: invokevirtual 85	twitter4j/internal/logging/Logger:debug	(Ljava/lang/String;)V
    //   703: aload 15
    //   705: invokevirtual 444	java/net/HttpURLConnection:getHeaderFields	()Ljava/util/Map;
    //   708: astore 35
    //   710: aload 35
    //   712: invokeinterface 149 1 0
    //   717: invokeinterface 155 1 0
    //   722: astore 36
    //   724: aload 36
    //   726: invokeinterface 160 1 0
    //   731: ifeq +378 -> 1109
    //   734: aload 36
    //   736: invokeinterface 164 1 0
    //   741: checkcast 166	java/lang/String
    //   744: astore 37
    //   746: aload 35
    //   748: aload 37
    //   750: invokeinterface 67 2 0
    //   755: checkcast 446	java/util/List
    //   758: invokeinterface 447 1 0
    //   763: astore 38
    //   765: aload 38
    //   767: invokeinterface 160 1 0
    //   772: ifeq -48 -> 724
    //   775: aload 38
    //   777: invokeinterface 164 1 0
    //   782: checkcast 166	java/lang/String
    //   785: astore 39
    //   787: aload 37
    //   789: ifnull +120 -> 909
    //   792: getstatic 27	twitter4j/internal/http/HttpClientImpl:logger	Ltwitter4j/internal/logging/Logger;
    //   795: new 87	java/lang/StringBuilder
    //   798: dup
    //   799: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   802: aload 37
    //   804: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   807: ldc 168
    //   809: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   812: aload 39
    //   814: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   817: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   820: invokevirtual 85	twitter4j/internal/logging/Logger:debug	(Ljava/lang/String;)V
    //   823: goto -58 -> 765
    //   826: astore 7
    //   828: goto -408 -> 420
    //   831: aload 15
    //   833: ldc_w 325
    //   836: ldc_w 449
    //   839: invokevirtual 330	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   842: aload_1
    //   843: invokevirtual 308	twitter4j/internal/http/HttpRequest:getParameters	()[Ltwitter4j/internal/http/HttpParameter;
    //   846: invokestatic 453	twitter4j/internal/http/HttpParameter:encodeParameters	([Ltwitter4j/internal/http/HttpParameter;)Ljava/lang/String;
    //   849: astore 40
    //   851: getstatic 27	twitter4j/internal/http/HttpClientImpl:logger	Ltwitter4j/internal/logging/Logger;
    //   854: ldc_w 455
    //   857: aload 40
    //   859: invokevirtual 116	twitter4j/internal/logging/Logger:debug	(Ljava/lang/String;Ljava/lang/String;)V
    //   862: aload 40
    //   864: ldc_w 419
    //   867: invokevirtual 423	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   870: astore 41
    //   872: aload 15
    //   874: ldc_w 457
    //   877: aload 41
    //   879: arraylength
    //   880: invokestatic 462	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   883: invokevirtual 330	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   886: aload 15
    //   888: iconst_1
    //   889: invokevirtual 335	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   892: aload 15
    //   894: invokevirtual 339	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   897: astore 6
    //   899: aload 6
    //   901: aload 41
    //   903: invokevirtual 463	java/io/OutputStream:write	([B)V
    //   906: goto -257 -> 649
    //   909: getstatic 27	twitter4j/internal/http/HttpClientImpl:logger	Ltwitter4j/internal/logging/Logger;
    //   912: aload 39
    //   914: invokevirtual 85	twitter4j/internal/logging/Logger:debug	(Ljava/lang/String;)V
    //   917: goto -152 -> 765
    //   920: iload 5
    //   922: sipush 420
    //   925: if_icmpeq +36 -> 961
    //   928: iload 5
    //   930: sipush 400
    //   933: if_icmpeq +28 -> 961
    //   936: iload 5
    //   938: sipush 500
    //   941: if_icmplt +20 -> 961
    //   944: aload_0
    //   945: getfield 193	twitter4j/internal/http/HttpClientImpl:CONF	Ltwitter4j/internal/http/HttpClientConfiguration;
    //   948: invokeinterface 294 1 0
    //   953: istore 32
    //   955: iload_3
    //   956: iload 32
    //   958: if_icmpne +30 -> 988
    //   961: new 171	twitter4j/TwitterException
    //   964: dup
    //   965: aload 8
    //   967: invokevirtual 468	twitter4j/internal/http/HttpResponse:asString	()Ljava/lang/String;
    //   970: aload 8
    //   972: invokespecial 471	twitter4j/TwitterException:<init>	(Ljava/lang/String;Ltwitter4j/internal/http/HttpResponse;)V
    //   975: astore 31
    //   977: aload 31
    //   979: athrow
    //   980: aload 6
    //   982: invokevirtual 400	java/io/OutputStream:close	()V
    //   985: aload 8
    //   987: areturn
    //   988: aload 6
    //   990: invokevirtual 400	java/io/OutputStream:close	()V
    //   993: getstatic 27	twitter4j/internal/http/HttpClientImpl:logger	Ltwitter4j/internal/logging/Logger;
    //   996: invokevirtual 79	twitter4j/internal/logging/Logger:isDebugEnabled	()Z
    //   999: ifeq +14 -> 1013
    //   1002: aload 8
    //   1004: ifnull +9 -> 1013
    //   1007: aload 8
    //   1009: invokevirtual 468	twitter4j/internal/http/HttpResponse:asString	()Ljava/lang/String;
    //   1012: pop
    //   1013: getstatic 27	twitter4j/internal/http/HttpClientImpl:logger	Ltwitter4j/internal/logging/Logger;
    //   1016: new 87	java/lang/StringBuilder
    //   1019: dup
    //   1020: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   1023: ldc_w 473
    //   1026: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1029: aload_0
    //   1030: getfield 193	twitter4j/internal/http/HttpClientImpl:CONF	Ltwitter4j/internal/http/HttpClientConfiguration;
    //   1033: invokeinterface 476 1 0
    //   1038: invokevirtual 253	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1041: ldc_w 478
    //   1044: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1047: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1050: invokevirtual 85	twitter4j/internal/logging/Logger:debug	(Ljava/lang/String;)V
    //   1053: sipush 1000
    //   1056: aload_0
    //   1057: getfield 193	twitter4j/internal/http/HttpClientImpl:CONF	Ltwitter4j/internal/http/HttpClientConfiguration;
    //   1060: invokeinterface 476 1 0
    //   1065: imul
    //   1066: i2l
    //   1067: invokestatic 484	java/lang/Thread:sleep	(J)V
    //   1070: iinc 3 1
    //   1073: aload 8
    //   1075: astore 4
    //   1077: goto -1060 -> 17
    //   1080: astore 34
    //   1082: aload 8
    //   1084: areturn
    //   1085: astore 33
    //   1087: goto -94 -> 993
    //   1090: astore 9
    //   1092: goto -667 -> 425
    //   1095: astore 13
    //   1097: goto -27 -> 1070
    //   1100: aload 4
    //   1102: areturn
    //   1103: iinc 24 1
    //   1106: goto -891 -> 215
    //   1109: iload 5
    //   1111: sipush 200
    //   1114: if_icmplt -194 -> 920
    //   1117: iload 5
    //   1119: sipush 302
    //   1122: if_icmpeq -142 -> 980
    //   1125: sipush 300
    //   1128: iload 5
    //   1130: if_icmpgt -150 -> 980
    //   1133: goto -213 -> 920
    //
    // Exception table:
    //   from	to	target	type
    //   28	74	414	finally
    //   84	93	414	finally
    //   101	212	414	finally
    //   222	368	414	finally
    //   368	386	414	finally
    //   386	395	414	finally
    //   401	411	414	finally
    //   468	482	414	finally
    //   485	499	414	finally
    //   502	610	414	finally
    //   613	649	414	finally
    //   649	659	414	finally
    //   659	678	414	finally
    //   831	906	414	finally
    //   420	425	428	java/io/IOException
    //   425	428	428	java/io/IOException
    //   980	985	428	java/io/IOException
    //   988	993	428	java/io/IOException
    //   678	724	826	finally
    //   724	765	826	finally
    //   765	787	826	finally
    //   792	823	826	finally
    //   909	917	826	finally
    //   944	955	826	finally
    //   961	980	826	finally
    //   980	985	1080	java/lang/Exception
    //   988	993	1085	java/lang/Exception
    //   420	425	1090	java/lang/Exception
    //   993	1002	1095	java/lang/InterruptedException
    //   1007	1013	1095	java/lang/InterruptedException
    //   1013	1070	1095	java/lang/InterruptedException
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.internal.http.HttpClientImpl
 * JD-Core Version:    0.6.0
 */