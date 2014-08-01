package org.jsoup.helper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import org.jsoup.Connection;
import org.jsoup.Connection.Base;
import org.jsoup.Connection.KeyVal;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Request;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.parser.Parser;
import org.jsoup.parser.TokenQueue;

public class HttpConnection
  implements Connection
{
  private Connection.Request req = new Request(null);
  private Connection.Response res = new Response();

  public static Connection connect(String paramString)
  {
    HttpConnection localHttpConnection = new HttpConnection();
    localHttpConnection.url(paramString);
    return localHttpConnection;
  }

  public static Connection connect(URL paramURL)
  {
    HttpConnection localHttpConnection = new HttpConnection();
    localHttpConnection.url(paramURL);
    return localHttpConnection;
  }

  public Connection cookie(String paramString1, String paramString2)
  {
    this.req.cookie(paramString1, paramString2);
    return this;
  }

  public Connection cookies(Map<String, String> paramMap)
  {
    Validate.notNull(paramMap, "Cookie map must not be null");
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      this.req.cookie((String)localEntry.getKey(), (String)localEntry.getValue());
    }
    return this;
  }

  public Connection data(String paramString1, String paramString2)
  {
    this.req.data(KeyVal.create(paramString1, paramString2));
    return this;
  }

  public Connection data(Map<String, String> paramMap)
  {
    Validate.notNull(paramMap, "Data map must not be null");
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      this.req.data(KeyVal.create((String)localEntry.getKey(), (String)localEntry.getValue()));
    }
    return this;
  }

  public Connection data(String[] paramArrayOfString)
  {
    Validate.notNull(paramArrayOfString, "Data key value pairs must not be null");
    if (paramArrayOfString.length % 2 == 0);
    for (boolean bool = true; ; bool = false)
    {
      Validate.isTrue(bool, "Must supply an even number of key value pairs");
      for (int i = 0; i < paramArrayOfString.length; i += 2)
      {
        String str1 = paramArrayOfString[i];
        String str2 = paramArrayOfString[(i + 1)];
        Validate.notEmpty(str1, "Data key must not be empty");
        Validate.notNull(str2, "Data value must not be null");
        this.req.data(KeyVal.create(str1, str2));
      }
    }
    return this;
  }

  public Connection.Response execute()
    throws IOException
  {
    this.res = Response.execute(this.req);
    return this.res;
  }

  public Connection followRedirects(boolean paramBoolean)
  {
    this.req.followRedirects(paramBoolean);
    return this;
  }

  public Document get()
    throws IOException
  {
    this.req.method(Connection.Method.GET);
    execute();
    return this.res.parse();
  }

  public Connection header(String paramString1, String paramString2)
  {
    this.req.header(paramString1, paramString2);
    return this;
  }

  public Connection ignoreContentType(boolean paramBoolean)
  {
    this.req.ignoreContentType(paramBoolean);
    return this;
  }

  public Connection ignoreHttpErrors(boolean paramBoolean)
  {
    this.req.ignoreHttpErrors(paramBoolean);
    return this;
  }

  public Connection method(Connection.Method paramMethod)
  {
    this.req.method(paramMethod);
    return this;
  }

  public Connection parser(Parser paramParser)
  {
    this.req.parser(paramParser);
    return this;
  }

  public Document post()
    throws IOException
  {
    this.req.method(Connection.Method.POST);
    execute();
    return this.res.parse();
  }

  public Connection referrer(String paramString)
  {
    Validate.notNull(paramString, "Referrer must not be null");
    this.req.header("Referer", paramString);
    return this;
  }

  public Connection.Request request()
  {
    return this.req;
  }

  public Connection request(Connection.Request paramRequest)
  {
    this.req = paramRequest;
    return this;
  }

  public Connection.Response response()
  {
    return this.res;
  }

  public Connection response(Connection.Response paramResponse)
  {
    this.res = paramResponse;
    return this;
  }

  public Connection timeout(int paramInt)
  {
    this.req.timeout(paramInt);
    return this;
  }

  public Connection url(String paramString)
  {
    Validate.notEmpty(paramString, "Must supply a valid URL");
    try
    {
      this.req.url(new URL(paramString));
      return this;
    }
    catch (MalformedURLException localMalformedURLException)
    {
    }
    throw new IllegalArgumentException("Malformed URL: " + paramString, localMalformedURLException);
  }

  public Connection url(URL paramURL)
  {
    this.req.url(paramURL);
    return this;
  }

  public Connection userAgent(String paramString)
  {
    Validate.notNull(paramString, "User agent must not be null");
    this.req.header("User-Agent", paramString);
    return this;
  }

  private static abstract class Base<T extends Connection.Base>
    implements Connection.Base<T>
  {
    Map<String, String> cookies = new LinkedHashMap();
    Map<String, String> headers = new LinkedHashMap();
    Connection.Method method;
    URL url;

    private String getHeaderCaseInsensitive(String paramString)
    {
      Validate.notNull(paramString, "Header name must not be null");
      String str = (String)this.headers.get(paramString);
      if (str == null)
        str = (String)this.headers.get(paramString.toLowerCase());
      if (str == null)
      {
        Map.Entry localEntry = scanHeaders(paramString);
        if (localEntry != null)
          str = (String)localEntry.getValue();
      }
      return str;
    }

    private Map.Entry<String, String> scanHeaders(String paramString)
    {
      String str = paramString.toLowerCase();
      Iterator localIterator = this.headers.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        if (((String)localEntry.getKey()).toLowerCase().equals(str))
          return localEntry;
      }
      return null;
    }

    public String cookie(String paramString)
    {
      Validate.notNull(paramString, "Cookie name must not be null");
      return (String)this.cookies.get(paramString);
    }

    public T cookie(String paramString1, String paramString2)
    {
      Validate.notEmpty(paramString1, "Cookie name must not be empty");
      Validate.notNull(paramString2, "Cookie value must not be null");
      this.cookies.put(paramString1, paramString2);
      return this;
    }

    public Map<String, String> cookies()
    {
      return this.cookies;
    }

    public boolean hasCookie(String paramString)
    {
      Validate.notEmpty("Cookie name must not be empty");
      return this.cookies.containsKey(paramString);
    }

    public boolean hasHeader(String paramString)
    {
      Validate.notEmpty(paramString, "Header name must not be empty");
      return getHeaderCaseInsensitive(paramString) != null;
    }

    public String header(String paramString)
    {
      Validate.notNull(paramString, "Header name must not be null");
      return getHeaderCaseInsensitive(paramString);
    }

    public T header(String paramString1, String paramString2)
    {
      Validate.notEmpty(paramString1, "Header name must not be empty");
      Validate.notNull(paramString2, "Header value must not be null");
      removeHeader(paramString1);
      this.headers.put(paramString1, paramString2);
      return this;
    }

    public Map<String, String> headers()
    {
      return this.headers;
    }

    public T method(Connection.Method paramMethod)
    {
      Validate.notNull(paramMethod, "Method must not be null");
      this.method = paramMethod;
      return this;
    }

    public Connection.Method method()
    {
      return this.method;
    }

    public T removeCookie(String paramString)
    {
      Validate.notEmpty("Cookie name must not be empty");
      this.cookies.remove(paramString);
      return this;
    }

    public T removeHeader(String paramString)
    {
      Validate.notEmpty(paramString, "Header name must not be empty");
      Map.Entry localEntry = scanHeaders(paramString);
      if (localEntry != null)
        this.headers.remove(localEntry.getKey());
      return this;
    }

    public URL url()
    {
      return this.url;
    }

    public T url(URL paramURL)
    {
      Validate.notNull(paramURL, "URL must not be null");
      this.url = paramURL;
      return this;
    }
  }

  public static class KeyVal
    implements Connection.KeyVal
  {
    private String key;
    private String value;

    private KeyVal(String paramString1, String paramString2)
    {
      this.key = paramString1;
      this.value = paramString2;
    }

    public static KeyVal create(String paramString1, String paramString2)
    {
      Validate.notEmpty(paramString1, "Data key must not be empty");
      Validate.notNull(paramString2, "Data value must not be null");
      return new KeyVal(paramString1, paramString2);
    }

    public String key()
    {
      return this.key;
    }

    public KeyVal key(String paramString)
    {
      Validate.notEmpty(paramString, "Data key must not be empty");
      this.key = paramString;
      return this;
    }

    public String toString()
    {
      return this.key + "=" + this.value;
    }

    public String value()
    {
      return this.value;
    }

    public KeyVal value(String paramString)
    {
      Validate.notNull(paramString, "Data value must not be null");
      this.value = paramString;
      return this;
    }
  }

  public static class Request extends HttpConnection.Base<Connection.Request>
    implements Connection.Request
  {
    private Collection<Connection.KeyVal> data = new ArrayList();
    private boolean followRedirects = true;
    private boolean ignoreContentType = false;
    private boolean ignoreHttpErrors = false;
    private Parser parser;
    private int timeoutMilliseconds = 3000;

    private Request()
    {
      super();
      this.method = Connection.Method.GET;
      this.headers.put("Accept-Encoding", "gzip");
      this.parser = Parser.htmlParser();
    }

    public Collection<Connection.KeyVal> data()
    {
      return this.data;
    }

    public Request data(Connection.KeyVal paramKeyVal)
    {
      Validate.notNull(paramKeyVal, "Key val must not be null");
      this.data.add(paramKeyVal);
      return this;
    }

    public Connection.Request followRedirects(boolean paramBoolean)
    {
      this.followRedirects = paramBoolean;
      return this;
    }

    public boolean followRedirects()
    {
      return this.followRedirects;
    }

    public Connection.Request ignoreContentType(boolean paramBoolean)
    {
      this.ignoreContentType = paramBoolean;
      return this;
    }

    public boolean ignoreContentType()
    {
      return this.ignoreContentType;
    }

    public Connection.Request ignoreHttpErrors(boolean paramBoolean)
    {
      this.ignoreHttpErrors = paramBoolean;
      return this;
    }

    public boolean ignoreHttpErrors()
    {
      return this.ignoreHttpErrors;
    }

    public Request parser(Parser paramParser)
    {
      this.parser = paramParser;
      return this;
    }

    public Parser parser()
    {
      return this.parser;
    }

    public int timeout()
    {
      return this.timeoutMilliseconds;
    }

    public Request timeout(int paramInt)
    {
      if (paramInt >= 0);
      for (boolean bool = true; ; bool = false)
      {
        Validate.isTrue(bool, "Timeout milliseconds must be 0 (infinite) or greater");
        this.timeoutMilliseconds = paramInt;
        return this;
      }
    }
  }

  public static class Response extends HttpConnection.Base<Connection.Response>
    implements Connection.Response
  {
    private static final int MAX_REDIRECTS = 20;
    private ByteBuffer byteData;
    private String charset;
    private String contentType;
    private boolean executed = false;
    private int numRedirects = 0;
    private Connection.Request req;
    private int statusCode;
    private String statusMessage;

    Response()
    {
      super();
    }

    private Response(Response paramResponse)
      throws IOException
    {
      super();
      if (paramResponse != null)
      {
        this.numRedirects = (1 + paramResponse.numRedirects);
        if (this.numRedirects >= 20)
        {
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = paramResponse.url();
          throw new IOException(String.format("Too many redirects occurred trying to load URL %s", arrayOfObject));
        }
      }
    }

    private static HttpURLConnection createConnection(Connection.Request paramRequest)
      throws IOException
    {
      HttpURLConnection localHttpURLConnection = (HttpURLConnection)paramRequest.url().openConnection();
      localHttpURLConnection.setRequestMethod(paramRequest.method().name());
      localHttpURLConnection.setInstanceFollowRedirects(false);
      localHttpURLConnection.setConnectTimeout(paramRequest.timeout());
      localHttpURLConnection.setReadTimeout(paramRequest.timeout());
      if (paramRequest.method() == Connection.Method.POST)
        localHttpURLConnection.setDoOutput(true);
      if (paramRequest.cookies().size() > 0)
        localHttpURLConnection.addRequestProperty("Cookie", getRequestCookieString(paramRequest));
      Iterator localIterator = paramRequest.headers().entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        localHttpURLConnection.addRequestProperty((String)localEntry.getKey(), (String)localEntry.getValue());
      }
      return localHttpURLConnection;
    }

    static Response execute(Connection.Request paramRequest)
      throws IOException
    {
      return execute(paramRequest, null);
    }

    static Response execute(Connection.Request paramRequest, Response paramResponse)
      throws IOException
    {
      Validate.notNull(paramRequest, "Request must not be null");
      String str = paramRequest.url().getProtocol();
      boolean bool1;
      HttpURLConnection localHttpURLConnection;
      int i;
      int j;
      if ((str.equals("http")) || (str.equals("https")))
      {
        bool1 = true;
        Validate.isTrue(bool1, "Only http & https protocols supported");
        if ((paramRequest.method() == Connection.Method.GET) && (paramRequest.data().size() > 0))
          serialiseRequestUrl(paramRequest);
        localHttpURLConnection = createConnection(paramRequest);
        localHttpURLConnection.connect();
        if (paramRequest.method() == Connection.Method.POST)
          writePost(paramRequest.data(), localHttpURLConnection.getOutputStream());
        i = localHttpURLConnection.getResponseCode();
        j = 0;
        if (i != 200)
        {
          if ((i != 302) && (i != 301) && (i != 303))
            break label308;
          j = 1;
        }
      }
      Response localResponse;
      label308: boolean bool3;
      do
      {
        localResponse = new Response(paramResponse);
        localResponse.setupFromConnection(localHttpURLConnection, paramResponse);
        if ((j == 0) || (!paramRequest.followRedirects()))
          break label372;
        paramRequest.method(Connection.Method.GET);
        paramRequest.data().clear();
        paramRequest.url(new URL(paramRequest.url(), localResponse.header("Location")));
        Iterator localIterator = localResponse.cookies.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          paramRequest.cookie((String)localEntry.getKey(), (String)localEntry.getValue());
        }
        bool1 = false;
        break;
        bool3 = paramRequest.ignoreHttpErrors();
        j = 0;
      }
      while (bool3);
      throw new IOException(i + " error loading URL " + paramRequest.url().toString());
      return execute(paramRequest, localResponse);
      label372: localResponse.req = paramRequest;
      Object localObject1 = null;
      InputStream localInputStream1 = null;
      try
      {
        InputStream localInputStream2 = localHttpURLConnection.getErrorStream();
        localObject1 = null;
        localInputStream1 = null;
        if (localInputStream2 != null)
        {
          localInputStream1 = localHttpURLConnection.getErrorStream();
          if (!localResponse.hasHeader("Content-Encoding"))
            break label524;
          boolean bool2 = localResponse.header("Content-Encoding").equalsIgnoreCase("gzip");
          localObject1 = null;
          if (!bool2)
            break label524;
        }
        label524: BufferedInputStream localBufferedInputStream;
        for (localObject1 = new BufferedInputStream(new GZIPInputStream(localInputStream1)); ; localObject1 = localBufferedInputStream)
        {
          localResponse.byteData = DataUtil.readToByteBuffer((InputStream)localObject1);
          localResponse.charset = DataUtil.getCharsetFromContentType(localResponse.contentType);
          if (localObject1 != null)
            ((InputStream)localObject1).close();
          if (localInputStream1 != null)
            localInputStream1.close();
          localResponse.executed = true;
          return localResponse;
          localInputStream1 = localHttpURLConnection.getInputStream();
          break;
          localBufferedInputStream = new BufferedInputStream(localInputStream1);
        }
      }
      finally
      {
        if (localObject1 != null)
          ((InputStream)localObject1).close();
        if (localInputStream1 != null)
          localInputStream1.close();
      }
      throw localObject2;
    }

    private static String getRequestCookieString(Connection.Request paramRequest)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      int i = 1;
      Iterator localIterator = paramRequest.cookies().entrySet().iterator();
      if (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        if (i == 0)
          localStringBuilder.append("; ");
        while (true)
        {
          localStringBuilder.append((String)localEntry.getKey()).append('=').append((String)localEntry.getValue());
          break;
          i = 0;
        }
      }
      return localStringBuilder.toString();
    }

    private static void serialiseRequestUrl(Connection.Request paramRequest)
      throws IOException
    {
      URL localURL = paramRequest.url();
      StringBuilder localStringBuilder = new StringBuilder();
      int i = 1;
      localStringBuilder.append(localURL.getProtocol()).append("://").append(localURL.getAuthority()).append(localURL.getPath()).append("?");
      if (localURL.getQuery() != null)
      {
        localStringBuilder.append(localURL.getQuery());
        i = 0;
      }
      Iterator localIterator = paramRequest.data().iterator();
      if (localIterator.hasNext())
      {
        Connection.KeyVal localKeyVal = (Connection.KeyVal)localIterator.next();
        if (i == 0)
          localStringBuilder.append('&');
        while (true)
        {
          localStringBuilder.append(URLEncoder.encode(localKeyVal.key(), "UTF-8")).append('=').append(URLEncoder.encode(localKeyVal.value(), "UTF-8"));
          break;
          i = 0;
        }
      }
      paramRequest.url(new URL(localStringBuilder.toString()));
      paramRequest.data().clear();
    }

    private void setupFromConnection(HttpURLConnection paramHttpURLConnection, Connection.Response paramResponse)
      throws IOException
    {
      this.method = Connection.Method.valueOf(paramHttpURLConnection.getRequestMethod());
      this.url = paramHttpURLConnection.getURL();
      this.statusCode = paramHttpURLConnection.getResponseCode();
      this.statusMessage = paramHttpURLConnection.getResponseMessage();
      this.contentType = paramHttpURLConnection.getContentType();
      processResponseHeaders(paramHttpURLConnection.getHeaderFields());
      if (paramResponse != null)
      {
        Iterator localIterator = paramResponse.cookies().entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          if (hasCookie((String)localEntry.getKey()))
            continue;
          cookie((String)localEntry.getKey(), (String)localEntry.getValue());
        }
      }
    }

    private static void writePost(Collection<Connection.KeyVal> paramCollection, OutputStream paramOutputStream)
      throws IOException
    {
      OutputStreamWriter localOutputStreamWriter = new OutputStreamWriter(paramOutputStream, "UTF-8");
      int i = 1;
      Iterator localIterator = paramCollection.iterator();
      if (localIterator.hasNext())
      {
        Connection.KeyVal localKeyVal = (Connection.KeyVal)localIterator.next();
        if (i == 0)
          localOutputStreamWriter.append('&');
        while (true)
        {
          localOutputStreamWriter.write(URLEncoder.encode(localKeyVal.key(), "UTF-8"));
          localOutputStreamWriter.write(61);
          localOutputStreamWriter.write(URLEncoder.encode(localKeyVal.value(), "UTF-8"));
          break;
          i = 0;
        }
      }
      localOutputStreamWriter.close();
    }

    public String body()
    {
      Validate.isTrue(this.executed, "Request must be executed (with .execute(), .get(), or .post() before getting response body");
      if (this.charset == null);
      for (String str = Charset.forName("UTF-8").decode(this.byteData).toString(); ; str = Charset.forName(this.charset).decode(this.byteData).toString())
      {
        this.byteData.rewind();
        return str;
      }
    }

    public byte[] bodyAsBytes()
    {
      Validate.isTrue(this.executed, "Request must be executed (with .execute(), .get(), or .post() before getting response body");
      return this.byteData.array();
    }

    public String charset()
    {
      return this.charset;
    }

    public String contentType()
    {
      return this.contentType;
    }

    public Document parse()
      throws IOException
    {
      Validate.isTrue(this.executed, "Request must be executed (with .execute(), .get(), or .post() before parsing response");
      if ((!this.req.ignoreContentType()) && ((this.contentType == null) || ((!this.contentType.startsWith("text/")) && (!this.contentType.startsWith("application/xml")) && (!this.contentType.startsWith("application/xhtml+xml")))))
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = this.contentType;
        arrayOfObject[1] = this.url.toString();
        throw new IOException(String.format("Unhandled content type \"%s\" on URL %s. Must be text/*, application/xml, or application/xhtml+xml", arrayOfObject));
      }
      Document localDocument = DataUtil.parseByteData(this.byteData, this.charset, this.url.toExternalForm(), this.req.parser());
      this.byteData.rewind();
      this.charset = localDocument.outputSettings().charset().name();
      return localDocument;
    }

    void processResponseHeaders(Map<String, List<String>> paramMap)
    {
      Iterator localIterator1 = paramMap.entrySet().iterator();
      while (localIterator1.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator1.next();
        String str1 = (String)localEntry.getKey();
        if (str1 == null)
          continue;
        List localList = (List)localEntry.getValue();
        if (str1.equalsIgnoreCase("Set-Cookie"))
        {
          Iterator localIterator2 = localList.iterator();
          while (localIterator2.hasNext())
          {
            String str2 = (String)localIterator2.next();
            if (str2 == null)
              continue;
            TokenQueue localTokenQueue = new TokenQueue(str2);
            String str3 = localTokenQueue.chompTo("=").trim();
            String str4 = localTokenQueue.consumeTo(";").trim();
            if (str4 == null)
              str4 = "";
            if ((str3 == null) || (str3.length() <= 0))
              continue;
            cookie(str3, str4);
          }
          continue;
        }
        if (localList.isEmpty())
          continue;
        header(str1, (String)localList.get(0));
      }
    }

    public int statusCode()
    {
      return this.statusCode;
    }

    public String statusMessage()
    {
      return this.statusMessage;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.jsoup.helper.HttpConnection
 * JD-Core Version:    0.6.0
 */