package org.apache.http.message;

import java.util.NoSuchElementException;
import org.apache.http.FormattedHeader;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HeaderIterator;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public class BasicHeaderElementIterator
  implements HeaderElementIterator
{
  private CharArrayBuffer buffer = null;
  private HeaderElement currentElement = null;
  private ParserCursor cursor = null;
  private final HeaderIterator headerIt;
  private final HeaderValueParser parser;

  public BasicHeaderElementIterator(HeaderIterator paramHeaderIterator)
  {
    this(paramHeaderIterator, BasicHeaderValueParser.DEFAULT);
  }

  public BasicHeaderElementIterator(HeaderIterator paramHeaderIterator, HeaderValueParser paramHeaderValueParser)
  {
    if (paramHeaderIterator == null)
      throw new IllegalArgumentException("Header iterator may not be null");
    if (paramHeaderValueParser == null)
      throw new IllegalArgumentException("Parser may not be null");
    this.headerIt = paramHeaderIterator;
    this.parser = paramHeaderValueParser;
  }

  private void bufferHeaderValue()
  {
    this.cursor = null;
    this.buffer = null;
    String str;
    do
    {
      Header localHeader;
      if (this.headerIt.hasNext())
      {
        localHeader = this.headerIt.nextHeader();
        if ((localHeader instanceof FormattedHeader))
        {
          this.buffer = ((FormattedHeader)localHeader).getBuffer();
          this.cursor = new ParserCursor(0, this.buffer.length());
          this.cursor.updatePos(((FormattedHeader)localHeader).getValuePos());
        }
      }
      else
      {
        return;
      }
      str = localHeader.getValue();
    }
    while (str == null);
    this.buffer = new CharArrayBuffer(str.length());
    this.buffer.append(str);
    this.cursor = new ParserCursor(0, this.buffer.length());
  }

  private void parseNextElement()
  {
    while (true)
    {
      if ((this.headerIt.hasNext()) || (this.cursor != null))
      {
        if ((this.cursor == null) || (this.cursor.atEnd()))
          bufferHeaderValue();
        if (this.cursor == null)
          continue;
      }
      while (!this.cursor.atEnd())
      {
        HeaderElement localHeaderElement = this.parser.parseHeaderElement(this.buffer, this.cursor);
        if ((localHeaderElement.getName().length() == 0) && (localHeaderElement.getValue() == null))
          continue;
        this.currentElement = localHeaderElement;
        return;
      }
      if (!this.cursor.atEnd())
        continue;
      this.cursor = null;
      this.buffer = null;
    }
  }

  public boolean hasNext()
  {
    if (this.currentElement == null)
      parseNextElement();
    return this.currentElement != null;
  }

  public final Object next()
    throws NoSuchElementException
  {
    return nextElement();
  }

  public HeaderElement nextElement()
    throws NoSuchElementException
  {
    if (this.currentElement == null)
      parseNextElement();
    if (this.currentElement == null)
      throw new NoSuchElementException("No more header elements available");
    HeaderElement localHeaderElement = this.currentElement;
    this.currentElement = null;
    return localHeaderElement;
  }

  public void remove()
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException("Remove not supported");
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.message.BasicHeaderElementIterator
 * JD-Core Version:    0.6.0
 */