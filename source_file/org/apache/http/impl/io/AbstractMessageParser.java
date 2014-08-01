package org.apache.http.impl.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.ParseException;
import org.apache.http.ProtocolException;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.BasicLineParser;
import org.apache.http.message.LineParser;
import org.apache.http.params.HttpParams;
import org.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public abstract class AbstractMessageParser<T extends HttpMessage>
  implements HttpMessageParser<T>
{
  private static final int HEADERS = 1;
  private static final int HEAD_LINE;
  private final List<CharArrayBuffer> headerLines;
  protected final LineParser lineParser;
  private final int maxHeaderCount;
  private final int maxLineLen;
  private T message;
  private final SessionInputBuffer sessionBuffer;
  private int state;

  public AbstractMessageParser(SessionInputBuffer paramSessionInputBuffer, LineParser paramLineParser, HttpParams paramHttpParams)
  {
    if (paramSessionInputBuffer == null)
      throw new IllegalArgumentException("Session input buffer may not be null");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    this.sessionBuffer = paramSessionInputBuffer;
    this.maxHeaderCount = paramHttpParams.getIntParameter("http.connection.max-header-count", -1);
    this.maxLineLen = paramHttpParams.getIntParameter("http.connection.max-line-length", -1);
    if (paramLineParser != null);
    while (true)
    {
      this.lineParser = paramLineParser;
      this.headerLines = new ArrayList();
      this.state = 0;
      return;
      paramLineParser = BasicLineParser.DEFAULT;
    }
  }

  public static Header[] parseHeaders(SessionInputBuffer paramSessionInputBuffer, int paramInt1, int paramInt2, LineParser paramLineParser)
    throws HttpException, IOException
  {
    if (paramLineParser == null)
      paramLineParser = BasicLineParser.DEFAULT;
    return parseHeaders(paramSessionInputBuffer, paramInt1, paramInt2, paramLineParser, new ArrayList());
  }

  public static Header[] parseHeaders(SessionInputBuffer paramSessionInputBuffer, int paramInt1, int paramInt2, LineParser paramLineParser, List<CharArrayBuffer> paramList)
    throws HttpException, IOException
  {
    if (paramSessionInputBuffer == null)
      throw new IllegalArgumentException("Session input buffer may not be null");
    if (paramLineParser == null)
      throw new IllegalArgumentException("Line parser may not be null");
    if (paramList == null)
      throw new IllegalArgumentException("Header line list may not be null");
    CharArrayBuffer localCharArrayBuffer1 = null;
    CharArrayBuffer localCharArrayBuffer2 = null;
    label65: Header[] arrayOfHeader;
    while (true)
    {
      int i;
      label101: CharArrayBuffer localCharArrayBuffer3;
      if (localCharArrayBuffer1 == null)
      {
        localCharArrayBuffer1 = new CharArrayBuffer(64);
        if ((paramSessionInputBuffer.readLine(localCharArrayBuffer1) != -1) && (localCharArrayBuffer1.length() >= 1))
          break label154;
        arrayOfHeader = new Header[paramList.size()];
        i = 0;
        if (i >= paramList.size())
          break;
        localCharArrayBuffer3 = (CharArrayBuffer)paramList.get(i);
      }
      try
      {
        arrayOfHeader[i] = paramLineParser.parseHeader(localCharArrayBuffer3);
        i++;
        break label101;
        localCharArrayBuffer1.clear();
        break label65;
        label154: if (((localCharArrayBuffer1.charAt(0) == ' ') || (localCharArrayBuffer1.charAt(0) == '\t')) && (localCharArrayBuffer2 != null))
        {
          for (int j = 0; ; j++)
          {
            if (j < localCharArrayBuffer1.length())
            {
              int k = localCharArrayBuffer1.charAt(j);
              if ((k == 32) || (k == 9))
                continue;
            }
            if ((paramInt2 <= 0) || (1 + localCharArrayBuffer2.length() + localCharArrayBuffer1.length() - j <= paramInt2))
              break;
            throw new IOException("Maximum line length limit exceeded");
          }
          localCharArrayBuffer2.append(' ');
          localCharArrayBuffer2.append(localCharArrayBuffer1, j, localCharArrayBuffer1.length() - j);
        }
        while ((paramInt1 > 0) && (paramList.size() >= paramInt1))
        {
          throw new IOException("Maximum header count exceeded");
          paramList.add(localCharArrayBuffer1);
          localCharArrayBuffer2 = localCharArrayBuffer1;
          localCharArrayBuffer1 = null;
        }
      }
      catch (ParseException localParseException)
      {
        throw new ProtocolException(localParseException.getMessage());
      }
    }
    return arrayOfHeader;
  }

  public T parse()
    throws IOException, HttpException
  {
    switch (this.state)
    {
    default:
      throw new IllegalStateException("Inconsistent parser state");
    case 0:
    case 1:
    }
    try
    {
      this.message = parseHead(this.sessionBuffer);
      this.state = 1;
      Header[] arrayOfHeader = parseHeaders(this.sessionBuffer, this.maxHeaderCount, this.maxLineLen, this.lineParser, this.headerLines);
      this.message.setHeaders(arrayOfHeader);
      HttpMessage localHttpMessage = this.message;
      this.message = null;
      this.headerLines.clear();
      this.state = 0;
      return localHttpMessage;
    }
    catch (ParseException localParseException)
    {
    }
    throw new ProtocolException(localParseException.getMessage(), localParseException);
  }

  protected abstract T parseHead(SessionInputBuffer paramSessionInputBuffer)
    throws IOException, HttpException, ParseException;
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.io.AbstractMessageParser
 * JD-Core Version:    0.6.0
 */