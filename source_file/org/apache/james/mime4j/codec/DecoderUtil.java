package org.apache.james.mime4j.codec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.james.mime4j.util.CharsetUtil;

public class DecoderUtil
{
  private static final Pattern PATTERN_ENCODED_WORD = Pattern.compile("(.*?)=\\?(.+?)\\?(\\w)\\?(.+?)\\?=", 32);

  static String decodeB(String paramString1, String paramString2, DecodeMonitor paramDecodeMonitor)
    throws UnsupportedEncodingException
  {
    return new String(decodeBase64(paramString1, paramDecodeMonitor), paramString2);
  }

  private static byte[] decodeBase64(String paramString, DecodeMonitor paramDecodeMonitor)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      Base64InputStream localBase64InputStream = new Base64InputStream(new ByteArrayInputStream(paramString.getBytes("US-ASCII")), paramDecodeMonitor);
      while (true)
      {
        int i = localBase64InputStream.read();
        if (i == -1)
          break;
        localByteArrayOutputStream.write(i);
      }
    }
    catch (IOException localIOException)
    {
      throw new IllegalStateException(localIOException);
    }
    return localByteArrayOutputStream.toByteArray();
  }

  static String decodeEncodedWords(String paramString)
  {
    return decodeEncodedWords(paramString, DecodeMonitor.SILENT);
  }

  public static String decodeEncodedWords(String paramString, DecodeMonitor paramDecodeMonitor)
    throws IllegalArgumentException
  {
    int i = 0;
    int j = 0;
    StringBuilder localStringBuilder = new StringBuilder();
    Matcher localMatcher = PATTERN_ENCODED_WORD.matcher(paramString);
    if (localMatcher.find())
    {
      String str1 = localMatcher.group(1);
      String str2 = tryDecodeEncodedWord(localMatcher.group(2), localMatcher.group(3), localMatcher.group(4), paramDecodeMonitor);
      if (str2 == null)
      {
        localStringBuilder.append(localMatcher.group(0));
        label79: i = localMatcher.end();
        if (str2 == null)
          break label126;
      }
      label126: for (j = 1; ; j = 0)
      {
        break;
        if ((j == 0) || (!CharsetUtil.isWhitespace(str1)))
          localStringBuilder.append(str1);
        localStringBuilder.append(str2);
        break label79;
      }
    }
    if (i == 0)
      return paramString;
    localStringBuilder.append(paramString.substring(i));
    return localStringBuilder.toString();
  }

  static String decodeQ(String paramString1, String paramString2, DecodeMonitor paramDecodeMonitor)
    throws UnsupportedEncodingException
  {
    return new String(decodeQuotedPrintable(replaceUnderscores(paramString1), paramDecodeMonitor), paramString2);
  }

  private static byte[] decodeQuotedPrintable(String paramString, DecodeMonitor paramDecodeMonitor)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      QuotedPrintableInputStream localQuotedPrintableInputStream = new QuotedPrintableInputStream(new ByteArrayInputStream(paramString.getBytes("US-ASCII")), paramDecodeMonitor);
      while (true)
      {
        int i = localQuotedPrintableInputStream.read();
        if (i == -1)
          break;
        localByteArrayOutputStream.write(i);
      }
    }
    catch (IOException localIOException)
    {
      throw new IllegalStateException(localIOException);
    }
    return localByteArrayOutputStream.toByteArray();
  }

  private static void monitor(DecodeMonitor paramDecodeMonitor, String paramString1, String paramString2, String paramString3, String paramString4, String[] paramArrayOfString)
    throws IllegalArgumentException
  {
    if (paramDecodeMonitor.isListening())
    {
      String str = recombine(paramString1, paramString2, paramString3);
      StringBuilder localStringBuilder = new StringBuilder();
      int i = paramArrayOfString.length;
      for (int j = 0; j < i; j++)
        localStringBuilder.append(paramArrayOfString[j]);
      localStringBuilder.append(" (");
      localStringBuilder.append(str);
      localStringBuilder.append(")");
      if (paramDecodeMonitor.warn(localStringBuilder.toString(), paramString4))
        throw new IllegalArgumentException(localStringBuilder.toString());
    }
  }

  private static String recombine(String paramString1, String paramString2, String paramString3)
  {
    return "=?" + paramString1 + "?" + paramString2 + "?" + paramString3 + "?=";
  }

  private static String replaceUnderscores(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder(128);
    int i = 0;
    if (i < paramString.length())
    {
      char c = paramString.charAt(i);
      if (c == '_')
        localStringBuilder.append("=20");
      while (true)
      {
        i++;
        break;
        localStringBuilder.append(c);
      }
    }
    return localStringBuilder.toString();
  }

  private static String tryDecodeEncodedWord(String paramString1, String paramString2, String paramString3, DecodeMonitor paramDecodeMonitor)
  {
    Charset localCharset = CharsetUtil.lookup(paramString1);
    if (localCharset == null)
    {
      monitor(paramDecodeMonitor, paramString1, paramString2, paramString3, "leaving word encoded", new String[] { "Mime charser '", paramString1, "' doesn't have a corresponding Java charset" });
      return null;
    }
    if (paramString3.length() == 0)
    {
      monitor(paramDecodeMonitor, paramString1, paramString2, paramString3, "leaving word encoded", new String[] { "Missing encoded text in encoded word" });
      return null;
    }
    try
    {
      if (paramString2.equalsIgnoreCase("Q"))
        return decodeQ(paramString3, localCharset.name(), paramDecodeMonitor);
      if (paramString2.equalsIgnoreCase("B"))
        return decodeB(paramString3, localCharset.name(), paramDecodeMonitor);
      monitor(paramDecodeMonitor, paramString1, paramString2, paramString3, "leaving word encoded", new String[] { "Warning: Unknown encoding in encoded word" });
      return null;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      String[] arrayOfString2 = new String[3];
      arrayOfString2[0] = "Unsupported encoding (";
      arrayOfString2[1] = localUnsupportedEncodingException.getMessage();
      arrayOfString2[2] = ") in encoded word";
      monitor(paramDecodeMonitor, paramString1, paramString2, paramString3, "leaving word encoded", arrayOfString2);
      return null;
    }
    catch (RuntimeException localRuntimeException)
    {
      String[] arrayOfString1 = new String[3];
      arrayOfString1[0] = "Could not decode (";
      arrayOfString1[1] = localRuntimeException.getMessage();
      arrayOfString1[2] = ") encoded word";
      monitor(paramDecodeMonitor, paramString1, paramString2, paramString3, "leaving word encoded", arrayOfString1);
    }
    return null;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.codec.DecoderUtil
 * JD-Core Version:    0.6.0
 */