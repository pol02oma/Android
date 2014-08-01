package oauth.signpost;

import com.google.gdata.util.common.base.PercentEscaper;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import oauth.signpost.http.HttpParameters;

public class OAuth
{
  public static final String ENCODING = "UTF-8";
  public static final String FORM_ENCODED = "application/x-www-form-urlencoded";
  public static final String HTTP_AUTHORIZATION_HEADER = "Authorization";
  public static final String OAUTH_CALLBACK = "oauth_callback";
  public static final String OAUTH_CALLBACK_CONFIRMED = "oauth_callback_confirmed";
  public static final String OAUTH_CONSUMER_KEY = "oauth_consumer_key";
  public static final String OAUTH_NONCE = "oauth_nonce";
  public static final String OAUTH_SIGNATURE = "oauth_signature";
  public static final String OAUTH_SIGNATURE_METHOD = "oauth_signature_method";
  public static final String OAUTH_TIMESTAMP = "oauth_timestamp";
  public static final String OAUTH_TOKEN = "oauth_token";
  public static final String OAUTH_TOKEN_SECRET = "oauth_token_secret";
  public static final String OAUTH_VERIFIER = "oauth_verifier";
  public static final String OAUTH_VERSION = "oauth_version";
  public static final String OUT_OF_BAND = "oob";
  public static final String VERSION_1_0 = "1.0";
  private static final PercentEscaper percentEncoder = new PercentEscaper("-._~", false);

  public static String addQueryParameters(String paramString, Map<String, String> paramMap)
  {
    String[] arrayOfString = new String[2 * paramMap.size()];
    int i = 0;
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      arrayOfString[i] = str;
      arrayOfString[(i + 1)] = ((String)paramMap.get(str));
      i += 2;
    }
    return addQueryParameters(paramString, arrayOfString);
  }

  public static String addQueryParameters(String paramString, String[] paramArrayOfString)
  {
    if (paramString.contains("?"));
    StringBuilder localStringBuilder;
    for (String str = "&"; ; str = "?")
    {
      localStringBuilder = new StringBuilder(paramString + str);
      for (int i = 0; i < paramArrayOfString.length; i += 2)
      {
        if (i > 0)
          localStringBuilder.append("&");
        localStringBuilder.append(percentEncode(paramArrayOfString[i]) + "=" + percentEncode(paramArrayOfString[(i + 1)]));
      }
    }
    return localStringBuilder.toString();
  }

  public static void debugOut(String paramString1, String paramString2)
  {
    if (System.getProperty("debug") != null)
      System.out.println("[SIGNPOST] " + paramString1 + ": " + paramString2);
  }

  public static HttpParameters decodeForm(InputStream paramInputStream)
    throws IOException
  {
    BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream));
    StringBuilder localStringBuilder = new StringBuilder();
    for (String str = localBufferedReader.readLine(); str != null; str = localBufferedReader.readLine())
      localStringBuilder.append(str);
    return decodeForm(localStringBuilder.toString());
  }

  public static HttpParameters decodeForm(String paramString)
  {
    HttpParameters localHttpParameters = new HttpParameters();
    if (isEmpty(paramString))
      return localHttpParameters;
    String[] arrayOfString = paramString.split("\\&");
    int i = arrayOfString.length;
    int j = 0;
    label30: String str1;
    int k;
    String str2;
    if (j < i)
    {
      str1 = arrayOfString[j];
      k = str1.indexOf('=');
      if (k >= 0)
        break label81;
      str2 = percentDecode(str1);
    }
    for (String str3 = null; ; str3 = percentDecode(str1.substring(k + 1)))
    {
      localHttpParameters.put(str2, str3);
      j++;
      break label30;
      break;
      label81: str2 = percentDecode(str1.substring(0, k));
    }
  }

  public static <T extends Map.Entry<String, String>> String formEncode(Collection<T> paramCollection)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    formEncode(paramCollection, localByteArrayOutputStream);
    return new String(localByteArrayOutputStream.toByteArray());
  }

  public static <T extends Map.Entry<String, String>> void formEncode(Collection<T> paramCollection, OutputStream paramOutputStream)
    throws IOException
  {
    if (paramCollection != null)
    {
      int i = 1;
      Iterator localIterator = paramCollection.iterator();
      if (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        if (i != 0)
          i = 0;
        while (true)
        {
          paramOutputStream.write(percentEncode(safeToString(localEntry.getKey())).getBytes());
          paramOutputStream.write(61);
          paramOutputStream.write(percentEncode(safeToString(localEntry.getValue())).getBytes());
          break;
          paramOutputStream.write(38);
        }
      }
    }
  }

  public static boolean isEmpty(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }

  public static HttpParameters oauthHeaderToParamsMap(String paramString)
  {
    HttpParameters localHttpParameters = new HttpParameters();
    if ((paramString == null) || (!paramString.startsWith("OAuth ")));
    while (true)
    {
      return localHttpParameters;
      String[] arrayOfString1 = paramString.substring("OAuth ".length()).split(",");
      int i = arrayOfString1.length;
      for (int j = 0; j < i; j++)
      {
        String[] arrayOfString2 = arrayOfString1[j].split("=");
        localHttpParameters.put(arrayOfString2[0].trim(), arrayOfString2[1].replace("\"", "").trim());
      }
    }
  }

  public static String percentDecode(String paramString)
  {
    if (paramString == null)
      return "";
    try
    {
      String str = URLDecoder.decode(paramString, "UTF-8");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw new RuntimeException(localUnsupportedEncodingException.getMessage(), localUnsupportedEncodingException);
  }

  public static String percentEncode(String paramString)
  {
    if (paramString == null)
      return "";
    return percentEncoder.escape(paramString);
  }

  public static String prepareOAuthHeader(String[] paramArrayOfString)
  {
    StringBuilder localStringBuilder = new StringBuilder("OAuth ");
    int i = 0;
    if (i < paramArrayOfString.length)
    {
      if (i > 0)
        localStringBuilder.append(", ");
      if (paramArrayOfString[i].startsWith("oauth_"));
      for (String str = percentEncode(paramArrayOfString[(i + 1)]); ; str = paramArrayOfString[(i + 1)])
      {
        localStringBuilder.append(percentEncode(paramArrayOfString[i]) + "=\"" + str + "\"");
        i += 2;
        break;
      }
    }
    return localStringBuilder.toString();
  }

  public static final String safeToString(Object paramObject)
  {
    if (paramObject == null)
      return null;
    return paramObject.toString();
  }

  public static String toHeaderElement(String paramString1, String paramString2)
  {
    return percentEncode(paramString1) + "=\"" + percentEncode(paramString2) + "\"";
  }

  public static <T extends Map.Entry<String, String>> Map<String, String> toMap(Collection<T> paramCollection)
  {
    HashMap localHashMap = new HashMap();
    if (paramCollection != null)
    {
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        String str = (String)localEntry.getKey();
        if (localHashMap.containsKey(str))
          continue;
        localHashMap.put(str, localEntry.getValue());
      }
    }
    return localHashMap;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     oauth.signpost.OAuth
 * JD-Core Version:    0.6.0
 */