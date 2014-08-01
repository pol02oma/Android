package com.restfb;

import com.restfb.exception.FacebookJsonMappingException;
import com.restfb.exception.FacebookNetworkException;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonException;
import com.restfb.json.JsonObject;
import com.restfb.util.StringUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultLegacyFacebookClient extends BaseFacebookClient
  implements LegacyFacebookClient
{
  protected static final String API_KEY_PARAM_NAME = "api_key";
  protected static final String CALL_ID_PARAM_NAME = "call_id";
  protected static final String FACEBOOK_READ_ONLY_ENDPOINT_URL = "https://api-read.facebook.com/restserver.php";
  protected static final String FACEBOOK_REST_ENDPOINT_URL = "https://api.facebook.com/restserver.php";
  protected static final String FORMAT_PARAM_NAME = "format";
  protected static final String FORMAT_PARAM_VALUE = "json";
  protected static final String METHOD_PARAM_NAME = "method";
  protected static final String SESSION_KEY_PARAM_NAME = "session_key";
  protected static final String SIG_PARAM_NAME = "sig";
  protected static final String VERSION_PARAM_NAME = "v";
  protected static final String VERSION_PARAM_VALUE = "1.0";
  protected String accessToken;
  protected String apiKey;
  protected String secretKey;

  public DefaultLegacyFacebookClient(String paramString)
  {
    this(paramString, new DefaultWebRequestor(), new DefaultJsonMapper());
  }

  public DefaultLegacyFacebookClient(String paramString, WebRequestor paramWebRequestor, JsonMapper paramJsonMapper)
  {
    verifyParameterPresence("accessToken", paramString);
    verifyParameterPresence("webRequestor", paramWebRequestor);
    verifyParameterPresence("jsonMapper", paramJsonMapper);
    this.accessToken = paramString.trim();
    this.webRequestor = paramWebRequestor;
    this.jsonMapper = paramJsonMapper;
    initializeIllegalParamNames();
  }

  @Deprecated
  public DefaultLegacyFacebookClient(String paramString1, String paramString2)
  {
    this(paramString1, paramString2, new DefaultWebRequestor(), new DefaultJsonMapper());
  }

  @Deprecated
  public DefaultLegacyFacebookClient(String paramString1, String paramString2, WebRequestor paramWebRequestor, JsonMapper paramJsonMapper)
  {
    verifyParameterPresence("apiKey", paramString1);
    verifyParameterPresence("secretKey", paramString2);
    verifyParameterPresence("webRequestor", paramWebRequestor);
    verifyParameterPresence("jsonMapper", paramJsonMapper);
    this.apiKey = paramString1.trim();
    this.secretKey = paramString2.trim();
    this.webRequestor = paramWebRequestor;
    this.jsonMapper = paramJsonMapper;
    initializeIllegalParamNames();
  }

  protected String createEndpointForApiCall(String paramString, boolean paramBoolean)
  {
    String str = StringUtils.trimToEmpty(paramString).toLowerCase();
    if (this.readOnlyApiCalls.contains(str))
      return getFacebookReadOnlyEndpointUrl();
    return getFacebookRestEndpointUrl();
  }

  public <T> T execute(String paramString, Class<T> paramClass, Parameter[] paramArrayOfParameter)
  {
    return execute(paramString, null, paramClass, paramArrayOfParameter);
  }

  public <T> T execute(String paramString1, String paramString2, Class<T> paramClass, Parameter[] paramArrayOfParameter)
  {
    return this.jsonMapper.toJavaObject(makeRequest(paramString1, paramString2, paramArrayOfParameter), paramClass);
  }

  public void execute(String paramString1, String paramString2, Parameter[] paramArrayOfParameter)
  {
    makeRequest(paramString1, paramString2, paramArrayOfParameter);
  }

  public void execute(String paramString, Parameter[] paramArrayOfParameter)
  {
    execute(paramString, (String)null, paramArrayOfParameter);
  }

  public <T> List<T> executeForList(String paramString, Class<T> paramClass, Parameter[] paramArrayOfParameter)
  {
    return executeForList(paramString, null, paramClass, paramArrayOfParameter);
  }

  public <T> List<T> executeForList(String paramString1, String paramString2, Class<T> paramClass, Parameter[] paramArrayOfParameter)
  {
    return this.jsonMapper.toJavaList(makeRequest(paramString1, paramString2, paramArrayOfParameter), paramClass);
  }

  public <T> T executeMultiquery(Map<String, String> paramMap, Class<T> paramClass, Parameter[] paramArrayOfParameter)
  {
    return executeMultiquery(paramMap, paramClass, paramArrayOfParameter);
  }

  public <T> T executeMultiquery(Map<String, String> paramMap, String paramString, Class<T> paramClass, Parameter[] paramArrayOfParameter)
  {
    int i = 0;
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(Parameter.with("queries", queriesToJson(paramMap)));
    int j = paramArrayOfParameter.length;
    for (int k = 0; k < j; k++)
    {
      Parameter localParameter = paramArrayOfParameter[k];
      if (localParameter.name.equals("queries"))
        throw new IllegalArgumentException("You cannot specify a parameter named 'queries' because it's reserved for use by RestFB for this call. Specify your queries in the Map that gets passed to this method.");
      localArrayList.add(localParameter);
    }
    JsonObject localJsonObject1 = new JsonObject();
    try
    {
      JsonArray localJsonArray1 = new JsonArray(makeRequest("fql.multiquery", paramString, (Parameter[])localArrayList.toArray(new Parameter[0])));
      if (i < localJsonArray1.length())
      {
        JsonObject localJsonObject2 = localJsonArray1.getJsonObject(i);
        if ((localJsonObject2.get("fql_result_set") instanceof JsonArray));
        for (JsonArray localJsonArray2 = localJsonObject2.getJsonArray("fql_result_set"); ; localJsonArray2 = new JsonArray())
        {
          localJsonObject1.put(localJsonObject2.getString("name"), localJsonArray2);
          i++;
          break;
        }
      }
    }
    catch (JsonException localJsonException)
    {
      throw new FacebookJsonMappingException("Unable to process fql.multiquery JSON response", localJsonException);
    }
    return this.jsonMapper.toJavaObject(localJsonObject1.toString(), paramClass);
  }

  protected String generateMd5(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      byte[] arrayOfByte1 = StringUtils.toBytes(paramString);
      StringBuilder localStringBuilder = new StringBuilder();
      for (int k : localMessageDigest.digest(arrayOfByte1))
      {
        localStringBuilder.append(Integer.toHexString((k & 0xF0) >>> 4));
        localStringBuilder.append(Integer.toHexString(k & 0xF));
      }
      String str = localStringBuilder.toString();
      return str;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
    }
    throw new IllegalStateException("MD5 isn't available on this JVM", localNoSuchAlgorithmException);
  }

  protected String generateSignature(Map<String, String> paramMap)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localStringBuilder.append((String)localEntry.getKey());
      localStringBuilder.append("=");
      localStringBuilder.append((String)localEntry.getValue());
    }
    localStringBuilder.append(this.secretKey);
    return generateMd5(localStringBuilder.toString());
  }

  protected String getFacebookReadOnlyEndpointUrl()
  {
    return "https://api-read.facebook.com/restserver.php";
  }

  protected String getFacebookRestEndpointUrl()
  {
    return "https://api.facebook.com/restserver.php";
  }

  protected void initializeIllegalParamNames()
  {
    this.illegalParamNames.addAll(Arrays.asList(new String[] { "api_key", "call_id", "sig", "method", "session_key", "format", "v", "access_token" }));
  }

  protected String makeRequest(String paramString1, String paramString2, Parameter[] paramArrayOfParameter)
  {
    verifyParameterLegality(paramArrayOfParameter);
    String str1 = toParameterString(paramString1, paramString2, paramArrayOfParameter);
    WebRequestor.Response localResponse;
    try
    {
      localResponse = this.webRequestor.executePost(createEndpointForApiCall(paramString1, false), str1);
      if (this.logger.isLoggable(Level.INFO))
        this.logger.info("Facebook responded with " + localResponse);
      if (200 != localResponse.getStatusCode().intValue())
        throw new FacebookNetworkException("Facebook POST failed", localResponse.getStatusCode());
    }
    catch (Throwable localThrowable)
    {
      throw new FacebookNetworkException("Facebook POST failed", localThrowable);
    }
    String str2 = localResponse.getBody();
    throwLegacyFacebookResponseStatusExceptionIfNecessary(str2);
    return str2;
  }

  protected String toParameterString(String paramString1, String paramString2, Parameter[] paramArrayOfParameter)
  {
    TreeMap localTreeMap = new TreeMap();
    int i = paramArrayOfParameter.length;
    for (int j = 0; j < i; j++)
    {
      Parameter localParameter = paramArrayOfParameter[j];
      localTreeMap.put(localParameter.name, localParameter.value);
    }
    localTreeMap.put("format", "json");
    localTreeMap.put("method", paramString1);
    StringBuilder localStringBuilder;
    int k;
    label139: Map.Entry localEntry;
    int m;
    if (usesAccessTokenAuthentication())
    {
      if (paramString2 != null)
        throw new IllegalArgumentException("If you're using the OAuth access token for authentication, you cannot specify a session key.");
      localTreeMap.put("access_token", this.accessToken);
      localStringBuilder = new StringBuilder();
      Iterator localIterator = localTreeMap.entrySet().iterator();
      k = 1;
      if (!localIterator.hasNext())
        break label358;
      localEntry = (Map.Entry)localIterator.next();
      if (k == 0)
        break label324;
      m = 0;
      label169: localStringBuilder.append(StringUtils.urlEncode((String)localEntry.getKey()));
      localStringBuilder.append("=");
      if (!usesAccessTokenAuthentication())
        break label340;
    }
    label324: label340: for (String str = urlEncodedValueForParameterName((String)localEntry.getKey(), (String)localEntry.getValue()); ; str = StringUtils.urlEncode((String)localEntry.getValue()))
    {
      localStringBuilder.append(str);
      k = m;
      break label139;
      localTreeMap.put("api_key", this.apiKey);
      localTreeMap.put("v", "1.0");
      localTreeMap.put("call_id", String.valueOf(System.currentTimeMillis()));
      if (!StringUtils.isBlank(paramString2))
        localTreeMap.put("session_key", paramString2);
      localTreeMap.put("sig", generateSignature(localTreeMap));
      break;
      localStringBuilder.append("&");
      m = k;
      break label169;
    }
    label358: return localStringBuilder.toString();
  }

  protected boolean usesAccessTokenAuthentication()
  {
    return !StringUtils.isBlank(this.accessToken);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.DefaultLegacyFacebookClient
 * JD-Core Version:    0.6.0
 */