package org.apache.http.impl.io;

import java.io.IOException;
import org.apache.http.ConnectionClosedException;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestFactory;
import org.apache.http.ParseException;
import org.apache.http.RequestLine;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.LineParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.params.HttpParams;
import org.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public class DefaultHttpRequestParser extends AbstractMessageParser<HttpRequest>
{
  private final CharArrayBuffer lineBuf;
  private final HttpRequestFactory requestFactory;

  public DefaultHttpRequestParser(SessionInputBuffer paramSessionInputBuffer, LineParser paramLineParser, HttpRequestFactory paramHttpRequestFactory, HttpParams paramHttpParams)
  {
    super(paramSessionInputBuffer, paramLineParser, paramHttpParams);
    if (paramHttpRequestFactory == null)
      throw new IllegalArgumentException("Request factory may not be null");
    this.requestFactory = paramHttpRequestFactory;
    this.lineBuf = new CharArrayBuffer(128);
  }

  protected HttpRequest parseHead(SessionInputBuffer paramSessionInputBuffer)
    throws IOException, HttpException, ParseException
  {
    this.lineBuf.clear();
    if (paramSessionInputBuffer.readLine(this.lineBuf) == -1)
      throw new ConnectionClosedException("Client closed connection");
    ParserCursor localParserCursor = new ParserCursor(0, this.lineBuf.length());
    RequestLine localRequestLine = this.lineParser.parseRequestLine(this.lineBuf, localParserCursor);
    return this.requestFactory.newHttpRequest(localRequestLine);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.io.DefaultHttpRequestParser
 * JD-Core Version:    0.6.0
 */