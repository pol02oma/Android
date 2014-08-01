package org.apache.http.impl.conn;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseFactory;
import org.apache.http.NoHttpResponseException;
import org.apache.http.ProtocolException;
import org.apache.http.StatusLine;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.impl.io.AbstractMessageParser;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.LineParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.params.HttpParams;
import org.apache.http.util.CharArrayBuffer;

@ThreadSafe
public class DefaultHttpResponseParser extends AbstractMessageParser<HttpResponse>
{
  private final CharArrayBuffer lineBuf;
  private final Log log = LogFactory.getLog(getClass());
  private final HttpResponseFactory responseFactory;

  public DefaultHttpResponseParser(SessionInputBuffer paramSessionInputBuffer, LineParser paramLineParser, HttpResponseFactory paramHttpResponseFactory, HttpParams paramHttpParams)
  {
    super(paramSessionInputBuffer, paramLineParser, paramHttpParams);
    if (paramHttpResponseFactory == null)
      throw new IllegalArgumentException("Response factory may not be null");
    this.responseFactory = paramHttpResponseFactory;
    this.lineBuf = new CharArrayBuffer(128);
  }

  protected HttpResponse parseHead(SessionInputBuffer paramSessionInputBuffer)
    throws IOException, HttpException
  {
    for (int i = 0; ; i++)
    {
      this.lineBuf.clear();
      int j = paramSessionInputBuffer.readLine(this.lineBuf);
      if ((j == -1) && (i == 0))
        throw new NoHttpResponseException("The target server failed to respond");
      ParserCursor localParserCursor = new ParserCursor(0, this.lineBuf.length());
      if (this.lineParser.hasProtocolVersion(this.lineBuf, localParserCursor))
      {
        StatusLine localStatusLine = this.lineParser.parseStatusLine(this.lineBuf, localParserCursor);
        return this.responseFactory.newHttpResponse(localStatusLine, null);
      }
      if ((j == -1) || (reject(this.lineBuf, i)))
        throw new ProtocolException("The server failed to respond with a valid HTTP response");
      if (!this.log.isDebugEnabled())
        continue;
      this.log.debug("Garbage in response: " + this.lineBuf.toString());
    }
  }

  protected boolean reject(CharArrayBuffer paramCharArrayBuffer, int paramInt)
  {
    return false;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.conn.DefaultHttpResponseParser
 * JD-Core Version:    0.6.0
 */