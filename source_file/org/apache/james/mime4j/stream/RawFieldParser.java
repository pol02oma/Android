package org.apache.james.mime4j.stream;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.util.ByteSequence;
import org.apache.james.mime4j.util.CharsetUtil;
import org.apache.james.mime4j.util.ContentUtil;

public class RawFieldParser
{
  static final BitSet COLON = INIT_BITSET(new int[] { 58 });
  public static final RawFieldParser DEFAULT;
  static final BitSet EQUAL_OR_SEMICOLON = INIT_BITSET(new int[] { 61, 59 });
  static final BitSet SEMICOLON = INIT_BITSET(new int[] { 59 });

  static
  {
    DEFAULT = new RawFieldParser();
  }

  public static BitSet INIT_BITSET(int[] paramArrayOfInt)
  {
    BitSet localBitSet = new BitSet(paramArrayOfInt.length);
    for (int i = 0; i < paramArrayOfInt.length; i++)
      localBitSet.set(paramArrayOfInt[i]);
    return localBitSet;
  }

  public void copyContent(ByteSequence paramByteSequence, ParserCursor paramParserCursor, BitSet paramBitSet, StringBuilder paramStringBuilder)
  {
    int i = paramParserCursor.getPos();
    int j = paramParserCursor.getPos();
    int k = paramParserCursor.getUpperBound();
    for (int m = j; ; m++)
    {
      char c;
      if (m < k)
      {
        c = (char)(0xFF & paramByteSequence.byteAt(m));
        if (((paramBitSet == null) || (!paramBitSet.get(c))) && (!CharsetUtil.isWhitespace(c)) && (c != '('));
      }
      else
      {
        paramParserCursor.updatePos(i);
        return;
      }
      i++;
      paramStringBuilder.append(c);
    }
  }

  public void copyQuotedContent(ByteSequence paramByteSequence, ParserCursor paramParserCursor, StringBuilder paramStringBuilder)
  {
    if (paramParserCursor.atEnd());
    int i;
    int j;
    int k;
    do
    {
      return;
      i = paramParserCursor.getPos();
      j = paramParserCursor.getPos();
      k = paramParserCursor.getUpperBound();
    }
    while ((char)(0xFF & paramByteSequence.byteAt(i)) != '"');
    int m = i + 1;
    int n = j + 1;
    int i1 = 0;
    int i2 = n;
    char c;
    if (i2 < k)
    {
      c = (char)(0xFF & paramByteSequence.byteAt(i2));
      if (i1 != 0)
      {
        if ((c != '"') && (c != '\\'))
          paramStringBuilder.append('\\');
        paramStringBuilder.append(c);
        i1 = 0;
      }
    }
    while (true)
    {
      i2++;
      m++;
      break;
      if (c == '"')
      {
        m++;
        paramParserCursor.updatePos(m);
        return;
      }
      if (c == '\\')
      {
        i1 = 1;
        continue;
      }
      if ((c == '\r') || (c == '\n'))
        continue;
      paramStringBuilder.append(c);
    }
  }

  public RawField parseField(ByteSequence paramByteSequence)
    throws MimeException
  {
    if (paramByteSequence == null)
      return null;
    ParserCursor localParserCursor = new ParserCursor(0, paramByteSequence.length());
    String str = parseToken(paramByteSequence, localParserCursor, COLON);
    if (localParserCursor.atEnd())
      throw new MimeException("Invalid MIME field: no name/value separator found: " + paramByteSequence.toString());
    return new RawField(paramByteSequence, localParserCursor.getPos(), str, null);
  }

  public NameValuePair parseParameter(ByteSequence paramByteSequence, ParserCursor paramParserCursor)
  {
    String str1 = parseToken(paramByteSequence, paramParserCursor, EQUAL_OR_SEMICOLON);
    if (paramParserCursor.atEnd())
      return new NameValuePair(str1, null);
    int i = paramByteSequence.byteAt(paramParserCursor.getPos());
    paramParserCursor.updatePos(1 + paramParserCursor.getPos());
    if (i == 59)
      return new NameValuePair(str1, null);
    String str2 = parseValue(paramByteSequence, paramParserCursor, SEMICOLON);
    if (!paramParserCursor.atEnd())
      paramParserCursor.updatePos(1 + paramParserCursor.getPos());
    return new NameValuePair(str1, str2);
  }

  public List<NameValuePair> parseParameters(ByteSequence paramByteSequence, ParserCursor paramParserCursor)
  {
    ArrayList localArrayList = new ArrayList();
    skipWhiteSpace(paramByteSequence, paramParserCursor);
    while (!paramParserCursor.atEnd())
      localArrayList.add(parseParameter(paramByteSequence, paramParserCursor));
    return localArrayList;
  }

  public RawBody parseRawBody(RawField paramRawField)
  {
    ByteSequence localByteSequence = paramRawField.getRaw();
    int i = 1 + paramRawField.getDelimiterIdx();
    if (localByteSequence == null)
    {
      String str = paramRawField.getBody();
      if (str == null)
        return new RawBody("", null);
      localByteSequence = ContentUtil.encode(str);
      i = 0;
    }
    return parseRawBody(localByteSequence, new ParserCursor(i, localByteSequence.length()));
  }

  public RawBody parseRawBody(ByteSequence paramByteSequence, ParserCursor paramParserCursor)
  {
    String str = parseToken(paramByteSequence, paramParserCursor, SEMICOLON);
    if (paramParserCursor.atEnd())
      return new RawBody(str, new ArrayList());
    paramParserCursor.updatePos(1 + paramParserCursor.getPos());
    return new RawBody(str, parseParameters(paramByteSequence, paramParserCursor));
  }

  public String parseToken(ByteSequence paramByteSequence, ParserCursor paramParserCursor, BitSet paramBitSet)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (true)
    {
      char c;
      if (!paramParserCursor.atEnd())
      {
        c = (char)(0xFF & paramByteSequence.byteAt(paramParserCursor.getPos()));
        if ((paramBitSet == null) || (!paramBitSet.get(c)));
      }
      else
      {
        return localStringBuilder.toString();
      }
      if (CharsetUtil.isWhitespace(c))
      {
        skipWhiteSpace(paramByteSequence, paramParserCursor);
        i = 1;
        continue;
      }
      if (c == '(')
      {
        skipComment(paramByteSequence, paramParserCursor);
        continue;
      }
      if ((localStringBuilder.length() > 0) && (i != 0))
        localStringBuilder.append(' ');
      copyContent(paramByteSequence, paramParserCursor, paramBitSet, localStringBuilder);
      i = 0;
    }
  }

  public String parseValue(ByteSequence paramByteSequence, ParserCursor paramParserCursor, BitSet paramBitSet)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (true)
    {
      char c;
      if (!paramParserCursor.atEnd())
      {
        c = (char)(0xFF & paramByteSequence.byteAt(paramParserCursor.getPos()));
        if ((paramBitSet == null) || (!paramBitSet.get(c)));
      }
      else
      {
        return localStringBuilder.toString();
      }
      if (CharsetUtil.isWhitespace(c))
      {
        skipWhiteSpace(paramByteSequence, paramParserCursor);
        i = 1;
        continue;
      }
      if (c == '(')
      {
        skipComment(paramByteSequence, paramParserCursor);
        continue;
      }
      if (c == '"')
      {
        if ((localStringBuilder.length() > 0) && (i != 0))
          localStringBuilder.append(' ');
        copyQuotedContent(paramByteSequence, paramParserCursor, localStringBuilder);
        i = 0;
        continue;
      }
      if ((localStringBuilder.length() > 0) && (i != 0))
        localStringBuilder.append(' ');
      copyContent(paramByteSequence, paramParserCursor, paramBitSet, localStringBuilder);
      i = 0;
    }
  }

  public void skipAllWhiteSpace(ByteSequence paramByteSequence, ParserCursor paramParserCursor)
  {
    while (!paramParserCursor.atEnd())
    {
      char c = (char)(0xFF & paramByteSequence.byteAt(paramParserCursor.getPos()));
      if (CharsetUtil.isWhitespace(c))
      {
        skipWhiteSpace(paramByteSequence, paramParserCursor);
        continue;
      }
      if (c != '(')
        break;
      skipComment(paramByteSequence, paramParserCursor);
    }
  }

  public void skipComment(ByteSequence paramByteSequence, ParserCursor paramParserCursor)
  {
    if (paramParserCursor.atEnd());
    int i;
    int j;
    int k;
    do
    {
      return;
      i = paramParserCursor.getPos();
      j = paramParserCursor.getPos();
      k = paramParserCursor.getUpperBound();
    }
    while ((char)(0xFF & paramByteSequence.byteAt(i)) != '(');
    int m = i + 1;
    int n = j + 1;
    int i1 = 1;
    int i2 = 0;
    int i3 = n;
    while (true)
    {
      int i4;
      if (i3 < k)
      {
        i4 = (char)(0xFF & paramByteSequence.byteAt(i3));
        if (i2 == 0)
          break label108;
        i2 = 0;
      }
      while (i1 <= 0)
      {
        m++;
        paramParserCursor.updatePos(m);
        return;
        label108: if (i4 == 92)
        {
          i2 = 1;
          continue;
        }
        if (i4 == 40)
        {
          i1++;
          continue;
        }
        if (i4 != 41)
          continue;
        i1--;
      }
      i3++;
      m++;
    }
  }

  public void skipWhiteSpace(ByteSequence paramByteSequence, ParserCursor paramParserCursor)
  {
    int i = paramParserCursor.getPos();
    int j = paramParserCursor.getPos();
    int k = paramParserCursor.getUpperBound();
    for (int m = j; ; m++)
    {
      if ((m >= k) || (!CharsetUtil.isWhitespace((char)(0xFF & paramByteSequence.byteAt(m)))))
      {
        paramParserCursor.updatePos(i);
        return;
      }
      i++;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.stream.RawFieldParser
 * JD-Core Version:    0.6.0
 */