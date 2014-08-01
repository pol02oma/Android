package org.apache.http.impl.cookie;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.FormattedHeader;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.message.BufferedHeader;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public class BrowserCompatSpec extends CookieSpecBase
{
  private static final String[] DEFAULT_DATE_PATTERNS = { "EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z" };
  private final String[] datepatterns;

  public BrowserCompatSpec()
  {
    this(null);
  }

  public BrowserCompatSpec(String[] paramArrayOfString)
  {
    if (paramArrayOfString != null);
    for (this.datepatterns = ((String[])paramArrayOfString.clone()); ; this.datepatterns = DEFAULT_DATE_PATTERNS)
    {
      registerAttribHandler("path", new BasicPathHandler());
      registerAttribHandler("domain", new BasicDomainHandler());
      registerAttribHandler("max-age", new BasicMaxAgeHandler());
      registerAttribHandler("secure", new BasicSecureHandler());
      registerAttribHandler("comment", new BasicCommentHandler());
      registerAttribHandler("expires", new BasicExpiresHandler(this.datepatterns));
      return;
    }
  }

  public List<Header> formatCookies(List<Cookie> paramList)
  {
    if (paramList == null)
      throw new IllegalArgumentException("List of cookies may not be null");
    if (paramList.isEmpty())
      throw new IllegalArgumentException("List of cookies may not be empty");
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(20 * paramList.size());
    localCharArrayBuffer.append("Cookie");
    localCharArrayBuffer.append(": ");
    for (int i = 0; i < paramList.size(); i++)
    {
      Cookie localCookie = (Cookie)paramList.get(i);
      if (i > 0)
        localCharArrayBuffer.append("; ");
      localCharArrayBuffer.append(localCookie.getName());
      localCharArrayBuffer.append("=");
      String str = localCookie.getValue();
      if (str == null)
        continue;
      localCharArrayBuffer.append(str);
    }
    ArrayList localArrayList = new ArrayList(1);
    localArrayList.add(new BufferedHeader(localCharArrayBuffer));
    return localArrayList;
  }

  public int getVersion()
  {
    return 0;
  }

  public Header getVersionHeader()
  {
    return null;
  }

  public List<Cookie> parse(Header paramHeader, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramHeader == null)
      throw new IllegalArgumentException("Header may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    if (!paramHeader.getName().equalsIgnoreCase("Set-Cookie"))
      throw new MalformedCookieException("Unrecognized cookie header '" + paramHeader.toString() + "'");
    HeaderElement[] arrayOfHeaderElement1 = paramHeader.getElements();
    int i = 0;
    int j = 0;
    for (HeaderElement localHeaderElement : arrayOfHeaderElement1)
    {
      if (localHeaderElement.getParameterByName("version") != null)
        i = 1;
      if (localHeaderElement.getParameterByName("expires") == null)
        continue;
      j = 1;
    }
    NetscapeDraftHeaderParser localNetscapeDraftHeaderParser;
    CharArrayBuffer localCharArrayBuffer;
    if ((j != 0) || (i == 0))
    {
      localNetscapeDraftHeaderParser = NetscapeDraftHeaderParser.DEFAULT;
      if (!(paramHeader instanceof FormattedHeader))
        break label231;
      localCharArrayBuffer = ((FormattedHeader)paramHeader).getBuffer();
    }
    for (ParserCursor localParserCursor = new ParserCursor(((FormattedHeader)paramHeader).getValuePos(), localCharArrayBuffer.length()); ; localParserCursor = new ParserCursor(0, localCharArrayBuffer.length()))
    {
      arrayOfHeaderElement1 = new HeaderElement[1];
      arrayOfHeaderElement1[0] = localNetscapeDraftHeaderParser.parseHeader(localCharArrayBuffer, localParserCursor);
      return parse(arrayOfHeaderElement1, paramCookieOrigin);
      label231: String str = paramHeader.getValue();
      if (str == null)
        throw new MalformedCookieException("Header value is null");
      localCharArrayBuffer = new CharArrayBuffer(str.length());
      localCharArrayBuffer.append(str);
    }
  }

  public String toString()
  {
    return "compatibility";
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.cookie.BrowserCompatSpec
 * JD-Core Version:    0.6.0
 */