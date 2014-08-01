package org.apache.http.message;

import org.apache.http.Header;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.annotation.Immutable;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharArrayBuffer;

@Immutable
public class BasicLineParser
  implements LineParser
{
  public static final BasicLineParser DEFAULT = new BasicLineParser();
  protected final ProtocolVersion protocol;

  public BasicLineParser()
  {
    this(null);
  }

  public BasicLineParser(ProtocolVersion paramProtocolVersion)
  {
    if (paramProtocolVersion == null)
      paramProtocolVersion = HttpVersion.HTTP_1_1;
    this.protocol = paramProtocolVersion;
  }

  public static final Header parseHeader(String paramString, LineParser paramLineParser)
    throws ParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Value to parse may not be null");
    if (paramLineParser == null)
      paramLineParser = DEFAULT;
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(paramString.length());
    localCharArrayBuffer.append(paramString);
    return paramLineParser.parseHeader(localCharArrayBuffer);
  }

  public static final ProtocolVersion parseProtocolVersion(String paramString, LineParser paramLineParser)
    throws ParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Value to parse may not be null.");
    if (paramLineParser == null)
      paramLineParser = DEFAULT;
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(paramString.length());
    localCharArrayBuffer.append(paramString);
    return paramLineParser.parseProtocolVersion(localCharArrayBuffer, new ParserCursor(0, paramString.length()));
  }

  public static final RequestLine parseRequestLine(String paramString, LineParser paramLineParser)
    throws ParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Value to parse may not be null.");
    if (paramLineParser == null)
      paramLineParser = DEFAULT;
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(paramString.length());
    localCharArrayBuffer.append(paramString);
    return paramLineParser.parseRequestLine(localCharArrayBuffer, new ParserCursor(0, paramString.length()));
  }

  public static final StatusLine parseStatusLine(String paramString, LineParser paramLineParser)
    throws ParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Value to parse may not be null.");
    if (paramLineParser == null)
      paramLineParser = DEFAULT;
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(paramString.length());
    localCharArrayBuffer.append(paramString);
    return paramLineParser.parseStatusLine(localCharArrayBuffer, new ParserCursor(0, paramString.length()));
  }

  protected ProtocolVersion createProtocolVersion(int paramInt1, int paramInt2)
  {
    return this.protocol.forVersion(paramInt1, paramInt2);
  }

  protected RequestLine createRequestLine(String paramString1, String paramString2, ProtocolVersion paramProtocolVersion)
  {
    return new BasicRequestLine(paramString1, paramString2, paramProtocolVersion);
  }

  protected StatusLine createStatusLine(ProtocolVersion paramProtocolVersion, int paramInt, String paramString)
  {
    return new BasicStatusLine(paramProtocolVersion, paramInt, paramString);
  }

  public boolean hasProtocolVersion(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    int i = paramParserCursor.getPos();
    String str = this.protocol.getProtocol();
    int j = str.length();
    if (paramCharArrayBuffer.length() < j + 4)
      break label77;
    label77: int m;
    while (true)
    {
      return false;
      if (i >= 0)
        break;
      i = -4 + paramCharArrayBuffer.length() - j;
      if (4 + (i + j) > paramCharArrayBuffer.length())
        continue;
      k = 1;
      m = 0;
      label96: if ((k == 0) || (m >= j))
        break label170;
      if (paramCharArrayBuffer.charAt(i + m) != str.charAt(m))
        break label164;
    }
    label164: for (int k = 1; ; k = 0)
    {
      m++;
      break label96;
      if (i != 0)
        break;
      while ((i < paramCharArrayBuffer.length()) && (HTTP.isWhitespace(paramCharArrayBuffer.charAt(i))))
        i++;
      break label77;
    }
    label170: if (k != 0)
      if (paramCharArrayBuffer.charAt(i + j) != '/')
        break label194;
    label194: for (k = 1; ; k = 0)
      return k;
  }

  public Header parseHeader(CharArrayBuffer paramCharArrayBuffer)
    throws ParseException
  {
    return new BufferedHeader(paramCharArrayBuffer);
  }

  // ERROR //
  public ProtocolVersion parseProtocolVersion(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
    throws ParseException
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +13 -> 14
    //   4: new 36	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc 105
    //   10: invokespecial 41	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   13: athrow
    //   14: aload_2
    //   15: ifnonnull +13 -> 28
    //   18: new 36	java/lang/IllegalArgumentException
    //   21: dup
    //   22: ldc 107
    //   24: invokespecial 41	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   27: athrow
    //   28: aload_0
    //   29: getfield 30	org/apache/http/message/BasicLineParser:protocol	Lorg/apache/http/ProtocolVersion;
    //   32: invokevirtual 114	org/apache/http/ProtocolVersion:getProtocol	()Ljava/lang/String;
    //   35: astore_3
    //   36: aload_3
    //   37: invokevirtual 49	java/lang/String:length	()I
    //   40: istore 4
    //   42: aload_2
    //   43: invokevirtual 110	org/apache/http/message/ParserCursor:getPos	()I
    //   46: istore 5
    //   48: aload_2
    //   49: invokevirtual 136	org/apache/http/message/ParserCursor:getUpperBound	()I
    //   52: istore 6
    //   54: aload_0
    //   55: aload_1
    //   56: aload_2
    //   57: invokevirtual 140	org/apache/http/message/BasicLineParser:skipWhitespace	(Lorg/apache/http/util/CharArrayBuffer;Lorg/apache/http/message/ParserCursor;)V
    //   60: aload_2
    //   61: invokevirtual 110	org/apache/http/message/ParserCursor:getPos	()I
    //   64: istore 7
    //   66: iconst_4
    //   67: iload 7
    //   69: iload 4
    //   71: iadd
    //   72: iadd
    //   73: iload 6
    //   75: if_icmple +37 -> 112
    //   78: new 34	org/apache/http/ParseException
    //   81: dup
    //   82: new 142	java/lang/StringBuilder
    //   85: dup
    //   86: invokespecial 143	java/lang/StringBuilder:<init>	()V
    //   89: ldc 145
    //   91: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: aload_1
    //   95: iload 5
    //   97: iload 6
    //   99: invokevirtual 152	org/apache/http/util/CharArrayBuffer:substring	(II)Ljava/lang/String;
    //   102: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   105: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   108: invokespecial 156	org/apache/http/ParseException:<init>	(Ljava/lang/String;)V
    //   111: athrow
    //   112: iconst_1
    //   113: istore 8
    //   115: iconst_0
    //   116: istore 9
    //   118: iload 8
    //   120: ifeq +43 -> 163
    //   123: iload 9
    //   125: iload 4
    //   127: if_icmpge +36 -> 163
    //   130: aload_1
    //   131: iload 7
    //   133: iload 9
    //   135: iadd
    //   136: invokevirtual 119	org/apache/http/util/CharArrayBuffer:charAt	(I)C
    //   139: aload_3
    //   140: iload 9
    //   142: invokevirtual 120	java/lang/String:charAt	(I)C
    //   145: if_icmpne +12 -> 157
    //   148: iconst_1
    //   149: istore 8
    //   151: iinc 9 1
    //   154: goto -36 -> 118
    //   157: iconst_0
    //   158: istore 8
    //   160: goto -9 -> 151
    //   163: iload 8
    //   165: ifeq +20 -> 185
    //   168: aload_1
    //   169: iload 7
    //   171: iload 4
    //   173: iadd
    //   174: invokevirtual 119	org/apache/http/util/CharArrayBuffer:charAt	(I)C
    //   177: bipush 47
    //   179: if_icmpne +45 -> 224
    //   182: iconst_1
    //   183: istore 8
    //   185: iload 8
    //   187: ifne +43 -> 230
    //   190: new 34	org/apache/http/ParseException
    //   193: dup
    //   194: new 142	java/lang/StringBuilder
    //   197: dup
    //   198: invokespecial 143	java/lang/StringBuilder:<init>	()V
    //   201: ldc 145
    //   203: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   206: aload_1
    //   207: iload 5
    //   209: iload 6
    //   211: invokevirtual 152	org/apache/http/util/CharArrayBuffer:substring	(II)Ljava/lang/String;
    //   214: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   217: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   220: invokespecial 156	org/apache/http/ParseException:<init>	(Ljava/lang/String;)V
    //   223: athrow
    //   224: iconst_0
    //   225: istore 8
    //   227: goto -42 -> 185
    //   230: iload 7
    //   232: iload 4
    //   234: iconst_1
    //   235: iadd
    //   236: iadd
    //   237: istore 10
    //   239: aload_1
    //   240: bipush 46
    //   242: iload 10
    //   244: iload 6
    //   246: invokevirtual 160	org/apache/http/util/CharArrayBuffer:indexOf	(III)I
    //   249: istore 11
    //   251: iload 11
    //   253: iconst_m1
    //   254: if_icmpne +37 -> 291
    //   257: new 34	org/apache/http/ParseException
    //   260: dup
    //   261: new 142	java/lang/StringBuilder
    //   264: dup
    //   265: invokespecial 143	java/lang/StringBuilder:<init>	()V
    //   268: ldc 162
    //   270: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   273: aload_1
    //   274: iload 5
    //   276: iload 6
    //   278: invokevirtual 152	org/apache/http/util/CharArrayBuffer:substring	(II)Ljava/lang/String;
    //   281: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   284: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   287: invokespecial 156	org/apache/http/ParseException:<init>	(Ljava/lang/String;)V
    //   290: athrow
    //   291: aload_1
    //   292: iload 10
    //   294: iload 11
    //   296: invokevirtual 165	org/apache/http/util/CharArrayBuffer:substringTrimmed	(II)Ljava/lang/String;
    //   299: invokestatic 171	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   302: istore 13
    //   304: iload 11
    //   306: iconst_1
    //   307: iadd
    //   308: istore 14
    //   310: aload_1
    //   311: bipush 32
    //   313: iload 14
    //   315: iload 6
    //   317: invokevirtual 160	org/apache/http/util/CharArrayBuffer:indexOf	(III)I
    //   320: istore 15
    //   322: iload 15
    //   324: iconst_m1
    //   325: if_icmpne +7 -> 332
    //   328: iload 6
    //   330: istore 15
    //   332: aload_1
    //   333: iload 14
    //   335: iload 15
    //   337: invokevirtual 165	org/apache/http/util/CharArrayBuffer:substringTrimmed	(II)Ljava/lang/String;
    //   340: invokestatic 171	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   343: istore 17
    //   345: aload_2
    //   346: iload 15
    //   348: invokevirtual 174	org/apache/http/message/ParserCursor:updatePos	(I)V
    //   351: aload_0
    //   352: iload 13
    //   354: iload 17
    //   356: invokevirtual 176	org/apache/http/message/BasicLineParser:createProtocolVersion	(II)Lorg/apache/http/ProtocolVersion;
    //   359: areturn
    //   360: astore 12
    //   362: new 34	org/apache/http/ParseException
    //   365: dup
    //   366: new 142	java/lang/StringBuilder
    //   369: dup
    //   370: invokespecial 143	java/lang/StringBuilder:<init>	()V
    //   373: ldc 178
    //   375: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   378: aload_1
    //   379: iload 5
    //   381: iload 6
    //   383: invokevirtual 152	org/apache/http/util/CharArrayBuffer:substring	(II)Ljava/lang/String;
    //   386: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   389: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   392: invokespecial 156	org/apache/http/ParseException:<init>	(Ljava/lang/String;)V
    //   395: athrow
    //   396: astore 16
    //   398: new 34	org/apache/http/ParseException
    //   401: dup
    //   402: new 142	java/lang/StringBuilder
    //   405: dup
    //   406: invokespecial 143	java/lang/StringBuilder:<init>	()V
    //   409: ldc 180
    //   411: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   414: aload_1
    //   415: iload 5
    //   417: iload 6
    //   419: invokevirtual 152	org/apache/http/util/CharArrayBuffer:substring	(II)Ljava/lang/String;
    //   422: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   425: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   428: invokespecial 156	org/apache/http/ParseException:<init>	(Ljava/lang/String;)V
    //   431: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   291	304	360	java/lang/NumberFormatException
    //   332	345	396	java/lang/NumberFormatException
  }

  public RequestLine parseRequestLine(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
    throws ParseException
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    int i = paramParserCursor.getPos();
    int j = paramParserCursor.getUpperBound();
    int k;
    int m;
    try
    {
      skipWhitespace(paramCharArrayBuffer, paramParserCursor);
      k = paramParserCursor.getPos();
      m = paramCharArrayBuffer.indexOf(32, k, j);
      if (m < 0)
        throw new ParseException("Invalid request line: " + paramCharArrayBuffer.substring(i, j));
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
      throw new ParseException("Invalid request line: " + paramCharArrayBuffer.substring(i, j));
    }
    String str1 = paramCharArrayBuffer.substringTrimmed(k, m);
    paramParserCursor.updatePos(m);
    skipWhitespace(paramCharArrayBuffer, paramParserCursor);
    int n = paramParserCursor.getPos();
    int i1 = paramCharArrayBuffer.indexOf(32, n, j);
    if (i1 < 0)
      throw new ParseException("Invalid request line: " + paramCharArrayBuffer.substring(i, j));
    String str2 = paramCharArrayBuffer.substringTrimmed(n, i1);
    paramParserCursor.updatePos(i1);
    ProtocolVersion localProtocolVersion = parseProtocolVersion(paramCharArrayBuffer, paramParserCursor);
    skipWhitespace(paramCharArrayBuffer, paramParserCursor);
    if (!paramParserCursor.atEnd())
      throw new ParseException("Invalid request line: " + paramCharArrayBuffer.substring(i, j));
    RequestLine localRequestLine = createRequestLine(str1, str2, localProtocolVersion);
    return localRequestLine;
  }

  public StatusLine parseStatusLine(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
    throws ParseException
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    int i = paramParserCursor.getPos();
    int j = paramParserCursor.getUpperBound();
    ProtocolVersion localProtocolVersion;
    int m;
    String str1;
    while (true)
    {
      int n;
      try
      {
        localProtocolVersion = parseProtocolVersion(paramCharArrayBuffer, paramParserCursor);
        skipWhitespace(paramCharArrayBuffer, paramParserCursor);
        int k = paramParserCursor.getPos();
        m = paramCharArrayBuffer.indexOf(32, k, j);
        if (m >= 0)
          continue;
        m = j;
        str1 = paramCharArrayBuffer.substringTrimmed(k, m);
        n = 0;
        if (n >= str1.length())
          break;
        if (!Character.isDigit(str1.charAt(n)))
          throw new ParseException("Status line contains invalid status code: " + paramCharArrayBuffer.substring(i, j));
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
      {
        throw new ParseException("Invalid status line: " + paramCharArrayBuffer.substring(i, j));
      }
      n++;
    }
    while (true)
    {
      try
      {
        int i1 = Integer.parseInt(str1);
        int i2 = m;
        if (i2 < j)
        {
          str2 = paramCharArrayBuffer.substringTrimmed(i2, j);
          return createStatusLine(localProtocolVersion, i1, str2);
        }
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new ParseException("Status line contains invalid status code: " + paramCharArrayBuffer.substring(i, j));
      }
      String str2 = "";
    }
  }

  protected void skipWhitespace(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
  {
    int i = paramParserCursor.getPos();
    int j = paramParserCursor.getUpperBound();
    while ((i < j) && (HTTP.isWhitespace(paramCharArrayBuffer.charAt(i))))
      i++;
    paramParserCursor.updatePos(i);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.message.BasicLineParser
 * JD-Core Version:    0.6.0
 */