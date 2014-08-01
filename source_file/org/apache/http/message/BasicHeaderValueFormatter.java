package org.apache.http.message;

import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.annotation.Immutable;
import org.apache.http.util.CharArrayBuffer;

@Immutable
public class BasicHeaderValueFormatter
  implements HeaderValueFormatter
{
  public static final BasicHeaderValueFormatter DEFAULT = new BasicHeaderValueFormatter();
  public static final String SEPARATORS = " ;,:@()<>\\\"/[]?={}\t";
  public static final String UNSAFE_CHARS = "\"\\";

  public static final String formatElements(HeaderElement[] paramArrayOfHeaderElement, boolean paramBoolean, HeaderValueFormatter paramHeaderValueFormatter)
  {
    if (paramHeaderValueFormatter == null)
      paramHeaderValueFormatter = DEFAULT;
    return paramHeaderValueFormatter.formatElements(null, paramArrayOfHeaderElement, paramBoolean).toString();
  }

  public static final String formatHeaderElement(HeaderElement paramHeaderElement, boolean paramBoolean, HeaderValueFormatter paramHeaderValueFormatter)
  {
    if (paramHeaderValueFormatter == null)
      paramHeaderValueFormatter = DEFAULT;
    return paramHeaderValueFormatter.formatHeaderElement(null, paramHeaderElement, paramBoolean).toString();
  }

  public static final String formatNameValuePair(NameValuePair paramNameValuePair, boolean paramBoolean, HeaderValueFormatter paramHeaderValueFormatter)
  {
    if (paramHeaderValueFormatter == null)
      paramHeaderValueFormatter = DEFAULT;
    return paramHeaderValueFormatter.formatNameValuePair(null, paramNameValuePair, paramBoolean).toString();
  }

  public static final String formatParameters(NameValuePair[] paramArrayOfNameValuePair, boolean paramBoolean, HeaderValueFormatter paramHeaderValueFormatter)
  {
    if (paramHeaderValueFormatter == null)
      paramHeaderValueFormatter = DEFAULT;
    return paramHeaderValueFormatter.formatParameters(null, paramArrayOfNameValuePair, paramBoolean).toString();
  }

  protected void doFormatValue(CharArrayBuffer paramCharArrayBuffer, String paramString, boolean paramBoolean)
  {
    if (!paramBoolean)
      for (int j = 0; (j < paramString.length()) && (!paramBoolean); j++)
        paramBoolean = isSeparator(paramString.charAt(j));
    if (paramBoolean)
      paramCharArrayBuffer.append('"');
    for (int i = 0; i < paramString.length(); i++)
    {
      char c = paramString.charAt(i);
      if (isUnsafe(c))
        paramCharArrayBuffer.append('\\');
      paramCharArrayBuffer.append(c);
    }
    if (paramBoolean)
      paramCharArrayBuffer.append('"');
  }

  protected int estimateElementsLen(HeaderElement[] paramArrayOfHeaderElement)
  {
    int i;
    if ((paramArrayOfHeaderElement == null) || (paramArrayOfHeaderElement.length < 1))
      i = 0;
    while (true)
    {
      return i;
      i = 2 * (-1 + paramArrayOfHeaderElement.length);
      for (int j = 0; j < paramArrayOfHeaderElement.length; j++)
        i += estimateHeaderElementLen(paramArrayOfHeaderElement[j]);
    }
  }

  protected int estimateHeaderElementLen(HeaderElement paramHeaderElement)
  {
    int i;
    if (paramHeaderElement == null)
      i = 0;
    while (true)
    {
      return i;
      i = paramHeaderElement.getName().length();
      String str = paramHeaderElement.getValue();
      if (str != null)
        i += 3 + str.length();
      int j = paramHeaderElement.getParameterCount();
      if (j <= 0)
        continue;
      for (int k = 0; k < j; k++)
        i += 2 + estimateNameValuePairLen(paramHeaderElement.getParameter(k));
    }
  }

  protected int estimateNameValuePairLen(NameValuePair paramNameValuePair)
  {
    int i;
    if (paramNameValuePair == null)
      i = 0;
    String str;
    do
    {
      return i;
      i = paramNameValuePair.getName().length();
      str = paramNameValuePair.getValue();
    }
    while (str == null);
    return i + (3 + str.length());
  }

  protected int estimateParametersLen(NameValuePair[] paramArrayOfNameValuePair)
  {
    int i;
    if ((paramArrayOfNameValuePair == null) || (paramArrayOfNameValuePair.length < 1))
      i = 0;
    while (true)
    {
      return i;
      i = 2 * (-1 + paramArrayOfNameValuePair.length);
      for (int j = 0; j < paramArrayOfNameValuePair.length; j++)
        i += estimateNameValuePairLen(paramArrayOfNameValuePair[j]);
    }
  }

  public CharArrayBuffer formatElements(CharArrayBuffer paramCharArrayBuffer, HeaderElement[] paramArrayOfHeaderElement, boolean paramBoolean)
  {
    if (paramArrayOfHeaderElement == null)
      throw new IllegalArgumentException("Header element array must not be null.");
    int i = estimateElementsLen(paramArrayOfHeaderElement);
    if (paramCharArrayBuffer == null)
      paramCharArrayBuffer = new CharArrayBuffer(i);
    while (true)
    {
      for (int j = 0; j < paramArrayOfHeaderElement.length; j++)
      {
        if (j > 0)
          paramCharArrayBuffer.append(", ");
        formatHeaderElement(paramCharArrayBuffer, paramArrayOfHeaderElement[j], paramBoolean);
      }
      paramCharArrayBuffer.ensureCapacity(i);
    }
    return paramCharArrayBuffer;
  }

  public CharArrayBuffer formatHeaderElement(CharArrayBuffer paramCharArrayBuffer, HeaderElement paramHeaderElement, boolean paramBoolean)
  {
    if (paramHeaderElement == null)
      throw new IllegalArgumentException("Header element must not be null.");
    int i = estimateHeaderElementLen(paramHeaderElement);
    if (paramCharArrayBuffer == null)
      paramCharArrayBuffer = new CharArrayBuffer(i);
    while (true)
    {
      paramCharArrayBuffer.append(paramHeaderElement.getName());
      String str = paramHeaderElement.getValue();
      if (str != null)
      {
        paramCharArrayBuffer.append('=');
        doFormatValue(paramCharArrayBuffer, str, paramBoolean);
      }
      int j = paramHeaderElement.getParameterCount();
      if (j <= 0)
        break;
      for (int k = 0; k < j; k++)
      {
        paramCharArrayBuffer.append("; ");
        formatNameValuePair(paramCharArrayBuffer, paramHeaderElement.getParameter(k), paramBoolean);
      }
      paramCharArrayBuffer.ensureCapacity(i);
    }
    return paramCharArrayBuffer;
  }

  public CharArrayBuffer formatNameValuePair(CharArrayBuffer paramCharArrayBuffer, NameValuePair paramNameValuePair, boolean paramBoolean)
  {
    if (paramNameValuePair == null)
      throw new IllegalArgumentException("NameValuePair must not be null.");
    int i = estimateNameValuePairLen(paramNameValuePair);
    if (paramCharArrayBuffer == null)
      paramCharArrayBuffer = new CharArrayBuffer(i);
    while (true)
    {
      paramCharArrayBuffer.append(paramNameValuePair.getName());
      String str = paramNameValuePair.getValue();
      if (str != null)
      {
        paramCharArrayBuffer.append('=');
        doFormatValue(paramCharArrayBuffer, str, paramBoolean);
      }
      return paramCharArrayBuffer;
      paramCharArrayBuffer.ensureCapacity(i);
    }
  }

  public CharArrayBuffer formatParameters(CharArrayBuffer paramCharArrayBuffer, NameValuePair[] paramArrayOfNameValuePair, boolean paramBoolean)
  {
    if (paramArrayOfNameValuePair == null)
      throw new IllegalArgumentException("Parameters must not be null.");
    int i = estimateParametersLen(paramArrayOfNameValuePair);
    if (paramCharArrayBuffer == null)
      paramCharArrayBuffer = new CharArrayBuffer(i);
    while (true)
    {
      for (int j = 0; j < paramArrayOfNameValuePair.length; j++)
      {
        if (j > 0)
          paramCharArrayBuffer.append("; ");
        formatNameValuePair(paramCharArrayBuffer, paramArrayOfNameValuePair[j], paramBoolean);
      }
      paramCharArrayBuffer.ensureCapacity(i);
    }
    return paramCharArrayBuffer;
  }

  protected boolean isSeparator(char paramChar)
  {
    return " ;,:@()<>\\\"/[]?={}\t".indexOf(paramChar) >= 0;
  }

  protected boolean isUnsafe(char paramChar)
  {
    return "\"\\".indexOf(paramChar) >= 0;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.message.BasicHeaderValueFormatter
 * JD-Core Version:    0.6.0
 */