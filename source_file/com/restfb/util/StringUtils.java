package com.restfb.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public final class StringUtils
{
  public static final String ENCODING_CHARSET = "UTF-8";
  private static final Logger logger = Logger.getLogger(StringUtils.class.getName());

  // ERROR //
  public static String fromInputStream(java.io.InputStream paramInputStream)
    throws java.io.IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_0
    //   3: ifnonnull +5 -> 8
    //   6: aload_1
    //   7: areturn
    //   8: new 37	java/io/BufferedReader
    //   11: dup
    //   12: new 39	java/io/InputStreamReader
    //   15: dup
    //   16: aload_0
    //   17: ldc 8
    //   19: invokespecial 42	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   22: invokespecial 45	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   25: astore_2
    //   26: new 47	java/lang/StringBuilder
    //   29: dup
    //   30: invokespecial 48	java/lang/StringBuilder:<init>	()V
    //   33: astore_3
    //   34: aload_2
    //   35: invokevirtual 51	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   38: astore 6
    //   40: aload 6
    //   42: ifnull +26 -> 68
    //   45: aload_3
    //   46: aload 6
    //   48: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: pop
    //   52: goto -18 -> 34
    //   55: astore 4
    //   57: aload_2
    //   58: ifnull +7 -> 65
    //   61: aload_2
    //   62: invokevirtual 58	java/io/BufferedReader:close	()V
    //   65: aload 4
    //   67: athrow
    //   68: aload_3
    //   69: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   72: astore 8
    //   74: aload 8
    //   76: astore_1
    //   77: aload_2
    //   78: ifnull -72 -> 6
    //   81: aload_2
    //   82: invokevirtual 58	java/io/BufferedReader:close	()V
    //   85: aload_1
    //   86: areturn
    //   87: astore 9
    //   89: getstatic 26	com/restfb/util/StringUtils:logger	Ljava/util/logging/Logger;
    //   92: getstatic 67	java/util/logging/Level:WARNING	Ljava/util/logging/Level;
    //   95: invokevirtual 71	java/util/logging/Logger:isLoggable	(Ljava/util/logging/Level;)Z
    //   98: ifeq -92 -> 6
    //   101: getstatic 26	com/restfb/util/StringUtils:logger	Ljava/util/logging/Logger;
    //   104: new 47	java/lang/StringBuilder
    //   107: dup
    //   108: invokespecial 48	java/lang/StringBuilder:<init>	()V
    //   111: ldc 73
    //   113: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: aload 9
    //   118: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   121: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   124: invokevirtual 80	java/util/logging/Logger:warning	(Ljava/lang/String;)V
    //   127: aload_1
    //   128: areturn
    //   129: astore 5
    //   131: getstatic 26	com/restfb/util/StringUtils:logger	Ljava/util/logging/Logger;
    //   134: getstatic 67	java/util/logging/Level:WARNING	Ljava/util/logging/Level;
    //   137: invokevirtual 71	java/util/logging/Logger:isLoggable	(Ljava/util/logging/Level;)Z
    //   140: ifeq -75 -> 65
    //   143: getstatic 26	com/restfb/util/StringUtils:logger	Ljava/util/logging/Logger;
    //   146: new 47	java/lang/StringBuilder
    //   149: dup
    //   150: invokespecial 48	java/lang/StringBuilder:<init>	()V
    //   153: ldc 73
    //   155: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: aload 5
    //   160: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   163: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   166: invokevirtual 80	java/util/logging/Logger:warning	(Ljava/lang/String;)V
    //   169: goto -104 -> 65
    //   172: astore 10
    //   174: aload 10
    //   176: astore 4
    //   178: aconst_null
    //   179: astore_2
    //   180: goto -123 -> 57
    //
    // Exception table:
    //   from	to	target	type
    //   26	34	55	finally
    //   34	40	55	finally
    //   45	52	55	finally
    //   68	74	55	finally
    //   81	85	87	java/lang/Throwable
    //   61	65	129	java/lang/Throwable
    //   8	26	172	finally
  }

  public static boolean isBlank(String paramString)
  {
    return (paramString == null) || ("".equals(paramString.trim()));
  }

  public static String join(List<String> paramList)
  {
    if (paramList == null)
      return null;
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramList.iterator();
    int i = 1;
    if (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (i != 0)
        i = 0;
      while (true)
      {
        localStringBuilder.append(str);
        break;
        localStringBuilder.append(",");
      }
    }
    return localStringBuilder.toString();
  }

  public static String join(String[] paramArrayOfString)
  {
    if (paramArrayOfString == null)
      return null;
    return join(Arrays.asList(paramArrayOfString));
  }

  public static byte[] toBytes(String paramString)
  {
    if (paramString == null)
      throw new NullPointerException("Parameter 'string' cannot be null.");
    try
    {
      byte[] arrayOfByte = paramString.getBytes("UTF-8");
      return arrayOfByte;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw new IllegalStateException("Platform doesn't support UTF-8", localUnsupportedEncodingException);
  }

  public static String trimToEmpty(String paramString)
  {
    if (isBlank(paramString))
      return "";
    return paramString.trim();
  }

  public static String trimToNull(String paramString)
  {
    if (isBlank(paramString))
      return null;
    return paramString.trim();
  }

  public static String urlDecode(String paramString)
  {
    if (paramString == null)
      return null;
    try
    {
      String str = URLDecoder.decode(paramString, "UTF-8");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw new IllegalStateException("Platform doesn't support UTF-8", localUnsupportedEncodingException);
  }

  public static String urlEncode(String paramString)
  {
    if (paramString == null)
      return null;
    try
    {
      String str = URLEncoder.encode(paramString, "UTF-8");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw new IllegalStateException("Platform doesn't support UTF-8", localUnsupportedEncodingException);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.util.StringUtils
 * JD-Core Version:    0.6.0
 */