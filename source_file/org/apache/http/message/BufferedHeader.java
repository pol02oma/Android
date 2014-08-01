package org.apache.http.message;

import java.io.Serializable;
import org.apache.http.FormattedHeader;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public class BufferedHeader
  implements FormattedHeader, Cloneable, Serializable
{
  private static final long serialVersionUID = -2768352615787625448L;
  private final CharArrayBuffer buffer;
  private final String name;
  private final int valuePos;

  public BufferedHeader(CharArrayBuffer paramCharArrayBuffer)
    throws ParseException
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    int i = paramCharArrayBuffer.indexOf(58);
    if (i == -1)
      throw new ParseException("Invalid header: " + paramCharArrayBuffer.toString());
    String str = paramCharArrayBuffer.substringTrimmed(0, i);
    if (str.length() == 0)
      throw new ParseException("Invalid header: " + paramCharArrayBuffer.toString());
    this.buffer = paramCharArrayBuffer;
    this.name = str;
    this.valuePos = (i + 1);
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }

  public CharArrayBuffer getBuffer()
  {
    return this.buffer;
  }

  public HeaderElement[] getElements()
    throws ParseException
  {
    ParserCursor localParserCursor = new ParserCursor(0, this.buffer.length());
    localParserCursor.updatePos(this.valuePos);
    return BasicHeaderValueParser.DEFAULT.parseElements(this.buffer, localParserCursor);
  }

  public String getName()
  {
    return this.name;
  }

  public String getValue()
  {
    return this.buffer.substringTrimmed(this.valuePos, this.buffer.length());
  }

  public int getValuePos()
  {
    return this.valuePos;
  }

  public String toString()
  {
    return this.buffer.toString();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.message.BufferedHeader
 * JD-Core Version:    0.6.0
 */