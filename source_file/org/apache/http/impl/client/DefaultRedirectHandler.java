package org.apache.http.impl.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.annotation.Immutable;
import org.apache.http.client.RedirectHandler;
import org.apache.http.protocol.HttpContext;

@Deprecated
@Immutable
public class DefaultRedirectHandler
  implements RedirectHandler
{
  private static final String REDIRECT_LOCATIONS = "http.protocol.redirect-locations";
  private final Log log = LogFactory.getLog(getClass());

  // ERROR //
  public java.net.URI getLocationURI(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws org.apache.http.ProtocolException
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +13 -> 14
    //   4: new 38	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc 40
    //   10: invokespecial 43	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   13: athrow
    //   14: aload_1
    //   15: ldc 45
    //   17: invokeinterface 51 2 0
    //   22: astore_3
    //   23: aload_3
    //   24: ifnonnull +40 -> 64
    //   27: new 34	org/apache/http/ProtocolException
    //   30: dup
    //   31: new 53	java/lang/StringBuilder
    //   34: dup
    //   35: invokespecial 54	java/lang/StringBuilder:<init>	()V
    //   38: ldc 56
    //   40: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   43: aload_1
    //   44: invokeinterface 64 1 0
    //   49: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   52: ldc 69
    //   54: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   60: invokespecial 74	org/apache/http/ProtocolException:<init>	(Ljava/lang/String;)V
    //   63: athrow
    //   64: aload_3
    //   65: invokeinterface 79 1 0
    //   70: astore 4
    //   72: aload_0
    //   73: getfield 30	org/apache/http/impl/client/DefaultRedirectHandler:log	Lorg/apache/commons/logging/Log;
    //   76: invokeinterface 85 1 0
    //   81: ifeq +37 -> 118
    //   84: aload_0
    //   85: getfield 30	org/apache/http/impl/client/DefaultRedirectHandler:log	Lorg/apache/commons/logging/Log;
    //   88: new 53	java/lang/StringBuilder
    //   91: dup
    //   92: invokespecial 54	java/lang/StringBuilder:<init>	()V
    //   95: ldc 87
    //   97: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: aload 4
    //   102: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   105: ldc 89
    //   107: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   110: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   113: invokeinterface 93 2 0
    //   118: new 95	java/net/URI
    //   121: dup
    //   122: aload 4
    //   124: invokespecial 96	java/net/URI:<init>	(Ljava/lang/String;)V
    //   127: astore 5
    //   129: aload_1
    //   130: invokeinterface 100 1 0
    //   135: astore 6
    //   137: aload 5
    //   139: invokevirtual 103	java/net/URI:isAbsolute	()Z
    //   142: ifne +157 -> 299
    //   145: aload 6
    //   147: ldc 105
    //   149: invokeinterface 111 2 0
    //   154: ifeq +68 -> 222
    //   157: new 34	org/apache/http/ProtocolException
    //   160: dup
    //   161: new 53	java/lang/StringBuilder
    //   164: dup
    //   165: invokespecial 54	java/lang/StringBuilder:<init>	()V
    //   168: ldc 113
    //   170: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: aload 5
    //   175: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   178: ldc 115
    //   180: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   186: invokespecial 74	org/apache/http/ProtocolException:<init>	(Ljava/lang/String;)V
    //   189: athrow
    //   190: astore 15
    //   192: new 34	org/apache/http/ProtocolException
    //   195: dup
    //   196: new 53	java/lang/StringBuilder
    //   199: dup
    //   200: invokespecial 54	java/lang/StringBuilder:<init>	()V
    //   203: ldc 117
    //   205: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   208: aload 4
    //   210: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   213: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   216: aload 15
    //   218: invokespecial 120	org/apache/http/ProtocolException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   221: athrow
    //   222: aload_2
    //   223: ldc 122
    //   225: invokeinterface 128 2 0
    //   230: checkcast 130	org/apache/http/HttpHost
    //   233: astore 11
    //   235: aload 11
    //   237: ifnonnull +13 -> 250
    //   240: new 132	java/lang/IllegalStateException
    //   243: dup
    //   244: ldc 134
    //   246: invokespecial 135	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   249: athrow
    //   250: aload_2
    //   251: ldc 137
    //   253: invokeinterface 128 2 0
    //   258: checkcast 139	org/apache/http/HttpRequest
    //   261: astore 12
    //   263: new 95	java/net/URI
    //   266: dup
    //   267: aload 12
    //   269: invokeinterface 143 1 0
    //   274: invokeinterface 148 1 0
    //   279: invokespecial 96	java/net/URI:<init>	(Ljava/lang/String;)V
    //   282: aload 11
    //   284: iconst_1
    //   285: invokestatic 154	org/apache/http/client/utils/URIUtils:rewriteURI	(Ljava/net/URI;Lorg/apache/http/HttpHost;Z)Ljava/net/URI;
    //   288: aload 5
    //   290: invokestatic 158	org/apache/http/client/utils/URIUtils:resolve	(Ljava/net/URI;Ljava/net/URI;)Ljava/net/URI;
    //   293: astore 14
    //   295: aload 14
    //   297: astore 5
    //   299: aload 6
    //   301: ldc 160
    //   303: invokeinterface 163 2 0
    //   308: ifeq +173 -> 481
    //   311: aload_2
    //   312: ldc 12
    //   314: invokeinterface 128 2 0
    //   319: checkcast 165	org/apache/http/impl/client/RedirectLocations
    //   322: astore 7
    //   324: aload 7
    //   326: ifnonnull +22 -> 348
    //   329: new 165	org/apache/http/impl/client/RedirectLocations
    //   332: dup
    //   333: invokespecial 166	org/apache/http/impl/client/RedirectLocations:<init>	()V
    //   336: astore 7
    //   338: aload_2
    //   339: ldc 12
    //   341: aload 7
    //   343: invokeinterface 170 3 0
    //   348: aload 5
    //   350: invokevirtual 173	java/net/URI:getFragment	()Ljava/lang/String;
    //   353: ifnull +114 -> 467
    //   356: aload 5
    //   358: new 130	org/apache/http/HttpHost
    //   361: dup
    //   362: aload 5
    //   364: invokevirtual 176	java/net/URI:getHost	()Ljava/lang/String;
    //   367: aload 5
    //   369: invokevirtual 180	java/net/URI:getPort	()I
    //   372: aload 5
    //   374: invokevirtual 183	java/net/URI:getScheme	()Ljava/lang/String;
    //   377: invokespecial 186	org/apache/http/HttpHost:<init>	(Ljava/lang/String;ILjava/lang/String;)V
    //   380: iconst_1
    //   381: invokestatic 154	org/apache/http/client/utils/URIUtils:rewriteURI	(Ljava/net/URI;Lorg/apache/http/HttpHost;Z)Ljava/net/URI;
    //   384: astore 10
    //   386: aload 10
    //   388: astore 8
    //   390: aload 7
    //   392: aload 8
    //   394: invokevirtual 190	org/apache/http/impl/client/RedirectLocations:contains	(Ljava/net/URI;)Z
    //   397: ifeq +77 -> 474
    //   400: new 192	org/apache/http/client/CircularRedirectException
    //   403: dup
    //   404: new 53	java/lang/StringBuilder
    //   407: dup
    //   408: invokespecial 54	java/lang/StringBuilder:<init>	()V
    //   411: ldc 194
    //   413: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   416: aload 8
    //   418: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   421: ldc 89
    //   423: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   426: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   429: invokespecial 195	org/apache/http/client/CircularRedirectException:<init>	(Ljava/lang/String;)V
    //   432: athrow
    //   433: astore 13
    //   435: new 34	org/apache/http/ProtocolException
    //   438: dup
    //   439: aload 13
    //   441: invokevirtual 198	java/net/URISyntaxException:getMessage	()Ljava/lang/String;
    //   444: aload 13
    //   446: invokespecial 120	org/apache/http/ProtocolException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   449: athrow
    //   450: astore 9
    //   452: new 34	org/apache/http/ProtocolException
    //   455: dup
    //   456: aload 9
    //   458: invokevirtual 198	java/net/URISyntaxException:getMessage	()Ljava/lang/String;
    //   461: aload 9
    //   463: invokespecial 120	org/apache/http/ProtocolException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   466: athrow
    //   467: aload 5
    //   469: astore 8
    //   471: goto -81 -> 390
    //   474: aload 7
    //   476: aload 8
    //   478: invokevirtual 202	org/apache/http/impl/client/RedirectLocations:add	(Ljava/net/URI;)V
    //   481: aload 5
    //   483: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   118	129	190	java/net/URISyntaxException
    //   263	295	433	java/net/URISyntaxException
    //   356	386	450	java/net/URISyntaxException
  }

  public boolean isRedirectRequested(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    switch (paramHttpResponse.getStatusLine().getStatusCode())
    {
    case 304:
    case 305:
    case 306:
    default:
    case 301:
    case 302:
    case 307:
      String str;
      do
      {
        return false;
        str = ((HttpRequest)paramHttpContext.getAttribute("http.request")).getRequestLine().getMethod();
      }
      while ((!str.equalsIgnoreCase("GET")) && (!str.equalsIgnoreCase("HEAD")));
      return true;
    case 303:
    }
    return true;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.client.DefaultRedirectHandler
 * JD-Core Version:    0.6.0
 */