package org.jsoup.helper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class DataUtil
{
  private static final int bufferSize = 131072;
  private static final Pattern charsetPattern = Pattern.compile("(?i)\\bcharset=\\s*\"?([^\\s;\"]*)");
  static final String defaultCharset = "UTF-8";

  static String getCharsetFromContentType(String paramString)
  {
    if (paramString == null);
    Matcher localMatcher;
    do
    {
      return null;
      localMatcher = charsetPattern.matcher(paramString);
    }
    while (!localMatcher.find());
    return localMatcher.group(1).trim().toUpperCase();
  }

  // ERROR //
  public static Document load(java.io.File paramFile, String paramString1, String paramString2)
    throws IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 59	java/io/FileInputStream
    //   5: dup
    //   6: aload_0
    //   7: invokespecial 62	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   10: astore 4
    //   12: aload 4
    //   14: invokestatic 66	org/jsoup/helper/DataUtil:readToByteBuffer	(Ljava/io/InputStream;)Ljava/nio/ByteBuffer;
    //   17: aload_1
    //   18: aload_2
    //   19: invokestatic 72	org/jsoup/parser/Parser:htmlParser	()Lorg/jsoup/parser/Parser;
    //   22: invokestatic 76	org/jsoup/helper/DataUtil:parseByteData	(Ljava/nio/ByteBuffer;Ljava/lang/String;Ljava/lang/String;Lorg/jsoup/parser/Parser;)Lorg/jsoup/nodes/Document;
    //   25: astore 6
    //   27: aload 4
    //   29: ifnull +8 -> 37
    //   32: aload 4
    //   34: invokevirtual 79	java/io/FileInputStream:close	()V
    //   37: aload 6
    //   39: areturn
    //   40: astore 5
    //   42: aload_3
    //   43: ifnull +7 -> 50
    //   46: aload_3
    //   47: invokevirtual 79	java/io/FileInputStream:close	()V
    //   50: aload 5
    //   52: athrow
    //   53: astore 5
    //   55: aload 4
    //   57: astore_3
    //   58: goto -16 -> 42
    //
    // Exception table:
    //   from	to	target	type
    //   2	12	40	finally
    //   12	27	53	finally
  }

  public static Document load(InputStream paramInputStream, String paramString1, String paramString2)
    throws IOException
  {
    return parseByteData(readToByteBuffer(paramInputStream), paramString1, paramString2, Parser.htmlParser());
  }

  public static Document load(InputStream paramInputStream, String paramString1, String paramString2, Parser paramParser)
    throws IOException
  {
    return parseByteData(readToByteBuffer(paramInputStream), paramString1, paramString2, paramParser);
  }

  static Document parseByteData(ByteBuffer paramByteBuffer, String paramString1, String paramString2, Parser paramParser)
  {
    String str1;
    Element localElement;
    String str2;
    if (paramString1 == null)
    {
      str1 = Charset.forName("UTF-8").decode(paramByteBuffer).toString();
      localDocument = paramParser.parseInput(str1, paramString2);
      localElement = localDocument.select("meta[http-equiv=content-type], meta[charset]").first();
      if (localElement != null)
      {
        if (!localElement.hasAttr("http-equiv"))
          break label160;
        str2 = getCharsetFromContentType(localElement.attr("content"));
        if ((str2 != null) && (str2.length() != 0) && (!str2.equals("UTF-8")))
        {
          paramString1 = str2;
          paramByteBuffer.rewind();
          str1 = Charset.forName(str2).decode(paramByteBuffer).toString();
        }
      }
    }
    for (Document localDocument = null; ; localDocument = null)
    {
      if (localDocument == null)
      {
        if (str1.charAt(0) == 65279)
          str1 = str1.substring(1);
        localDocument = paramParser.parseInput(str1, paramString2);
        localDocument.outputSettings().charset(paramString1);
      }
      return localDocument;
      label160: str2 = localElement.attr("charset");
      break;
      Validate.notEmpty(paramString1, "Must set charset arg to character set of file to parse. Set to null to attempt to detect from HTML");
      str1 = Charset.forName(paramString1).decode(paramByteBuffer).toString();
    }
  }

  static ByteBuffer readToByteBuffer(InputStream paramInputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte[131072];
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(131072);
    while (true)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1)
        return ByteBuffer.wrap(localByteArrayOutputStream.toByteArray());
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.jsoup.helper.DataUtil
 * JD-Core Version:    0.6.0
 */