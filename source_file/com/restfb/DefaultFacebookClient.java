package com.restfb;

import com.restfb.batch.BatchRequest;
import com.restfb.batch.BatchResponse;
import com.restfb.exception.FacebookException;
import com.restfb.exception.FacebookExceptionMapper;
import com.restfb.exception.FacebookGraphException;
import com.restfb.exception.FacebookJsonMappingException;
import com.restfb.exception.FacebookNetworkException;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.exception.FacebookQueryParseException;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonException;
import com.restfb.json.JsonObject;
import com.restfb.util.StringUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultFacebookClient extends BaseFacebookClient
  implements FacebookClient
{
  protected static final String BATCH_ERROR_ATTRIBUTE_NAME = "error";
  protected static final String BATCH_ERROR_DESCRIPTION_ATTRIBUTE_NAME = "error_description";
  protected static final String ERROR_ATTRIBUTE_NAME = "error";
  protected static final String ERROR_MESSAGE_ATTRIBUTE_NAME = "message";
  protected static final String ERROR_TYPE_ATTRIBUTE_NAME = "type";
  protected static final String FACEBOOK_GRAPH_ENDPOINT_URL = "https://graph.facebook.com";
  protected static final String FACEBOOK_GRAPH_VIDEO_ENDPOINT_URL = "https://graph-video.facebook.com";
  protected static final String FACEBOOK_READ_ONLY_ENDPOINT_URL = "https://api-read.facebook.com/method";
  protected static final String FORMAT_PARAM_NAME = "format";
  protected static final String IDS_PARAM_NAME = "ids";
  protected static final String METHOD_PARAM_NAME = "method";
  protected static final String QUERIES_PARAM_NAME = "queries";
  protected static final String QUERY_PARAM_NAME = "query";
  protected String accessToken;
  protected FacebookExceptionMapper graphFacebookExceptionMapper;

  public DefaultFacebookClient()
  {
    this(null);
  }

  public DefaultFacebookClient(String paramString)
  {
    this(paramString, new DefaultWebRequestor(), new DefaultJsonMapper());
  }

  public DefaultFacebookClient(String paramString, WebRequestor paramWebRequestor, JsonMapper paramJsonMapper)
  {
    verifyParameterPresence("jsonMapper", paramJsonMapper);
    verifyParameterPresence("webRequestor", paramWebRequestor);
    this.accessToken = StringUtils.trimToNull(paramString);
    this.webRequestor = paramWebRequestor;
    this.jsonMapper = paramJsonMapper;
    this.graphFacebookExceptionMapper = createGraphFacebookExceptionMapper();
    this.illegalParamNames.addAll(Arrays.asList(new String[] { "access_token", "method", "format" }));
  }

  public List<FacebookClient.AccessToken> convertSessionKeysToAccessTokens(String paramString1, String paramString2, String[] paramArrayOfString)
  {
    verifyParameterPresence("appId", paramString1);
    verifyParameterPresence("secretKey", paramString2);
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0))
      return Collections.emptyList();
    Parameter[] arrayOfParameter = new Parameter[3];
    arrayOfParameter[0] = Parameter.with("client_id", paramString1);
    arrayOfParameter[1] = Parameter.with("client_secret", paramString2);
    arrayOfParameter[2] = Parameter.with("sessions", StringUtils.join(paramArrayOfString));
    String str = makeRequest("/oauth/exchange_sessions", true, false, null, arrayOfParameter);
    return this.jsonMapper.toJavaList(str, FacebookClient.AccessToken.class);
  }

  protected String createEndpointForApiCall(String paramString, boolean paramBoolean)
  {
    StringUtils.trimToEmpty(paramString).toLowerCase();
    while (paramString.startsWith("/"))
      paramString = paramString.substring(1);
    String str = getFacebookGraphEndpointUrl();
    if (this.readOnlyApiCalls.contains(paramString));
    for (str = getFacebookReadOnlyEndpointUrl(); ; str = getFacebookGraphVideoEndpointUrl())
      do
        return String.format("%s/%s", new Object[] { str, paramString });
      while ((!paramBoolean) || (!paramString.endsWith("/videos")));
  }

  protected FacebookExceptionMapper createGraphFacebookExceptionMapper()
  {
    return new DefaultGraphFacebookExceptionMapper();
  }

  public boolean deleteObject(String paramString)
  {
    verifyParameterPresence("object", paramString);
    return "true".equals(makeRequest(paramString, true, true, null, new Parameter[0]));
  }

  public List<BatchResponse> executeBatch(List<BatchRequest> paramList, List<BinaryAttachment> paramList1)
  {
    verifyParameterPresence("binaryAttachments", paramList1);
    if ((paramList == null) || (paramList.size() == 0))
      throw new IllegalArgumentException("You must specify at least one batch request.");
    JsonMapper localJsonMapper = this.jsonMapper;
    Parameter[] arrayOfParameter = new Parameter[1];
    arrayOfParameter[0] = Parameter.with("batch", this.jsonMapper.toJson(paramList, true));
    return localJsonMapper.toJavaList(makeRequest("", true, false, paramList1, arrayOfParameter), BatchResponse.class);
  }

  public List<BatchResponse> executeBatch(BatchRequest[] paramArrayOfBatchRequest)
  {
    return executeBatch(Arrays.asList(paramArrayOfBatchRequest), Collections.emptyList());
  }

  public <T> T executeMultiquery(Map<String, String> paramMap, Class<T> paramClass, Parameter[] paramArrayOfParameter)
  {
    verifyParameterPresence("objectType", paramClass);
    int i = paramArrayOfParameter.length;
    for (int j = 0; j < i; j++)
    {
      if (!"queries".equals(paramArrayOfParameter[j].name))
        continue;
      throw new IllegalArgumentException("You cannot specify the 'queries' URL parameter yourself - RestFB will populate this for you with the queries you passed to this method.");
    }
    JsonObject localJsonObject1;
    try
    {
      JsonArray localJsonArray1 = new JsonArray(makeRequest("fql.multiquery", false, false, null, parametersWithAdditionalParameter(Parameter.with("queries", queriesToJson(paramMap)), paramArrayOfParameter)));
      localJsonObject1 = new JsonObject();
      int k = 0;
      if (k < localJsonArray1.length())
      {
        JsonObject localJsonObject2 = localJsonArray1.getJsonObject(k);
        if ((localJsonObject2.get("fql_result_set") instanceof JsonArray));
        for (JsonArray localJsonArray2 = localJsonObject2.getJsonArray("fql_result_set"); ; localJsonArray2 = new JsonArray())
        {
          localJsonObject1.put(localJsonObject2.getString("name"), localJsonArray2);
          k++;
          break;
        }
      }
    }
    catch (JsonException localJsonException)
    {
      throw new FacebookJsonMappingException("Unable to process fql.multiquery JSON response", localJsonException);
    }
    if (paramClass.equals(JsonObject.class))
      return localJsonObject1;
    Object localObject = this.jsonMapper.toJavaObject(localJsonObject1.toString(), paramClass);
    return localObject;
  }

  public <T> List<T> executeQuery(String paramString, Class<T> paramClass, Parameter[] paramArrayOfParameter)
  {
    verifyParameterPresence("query", paramString);
    verifyParameterPresence("objectType", paramClass);
    int i = paramArrayOfParameter.length;
    for (int j = 0; j < i; j++)
    {
      if (!"query".equals(paramArrayOfParameter[j].name))
        continue;
      throw new IllegalArgumentException("You cannot specify the 'query' URL parameter yourself - RestFB will populate this for you with the query you passed to this method.");
    }
    return this.jsonMapper.toJavaList(makeRequest("fql.query", false, false, null, parametersWithAdditionalParameter(Parameter.with("query", paramString), paramArrayOfParameter)), paramClass);
  }

  public <T> Connection<T> fetchConnection(String paramString, Class<T> paramClass, Parameter[] paramArrayOfParameter)
  {
    verifyParameterPresence("connection", paramString);
    verifyParameterPresence("connectionType", paramClass);
    return new Connection(this, makeRequest(paramString, paramArrayOfParameter), paramClass);
  }

  public <T> Connection<T> fetchConnectionPage(String paramString, Class<T> paramClass)
  {
    return new Connection(this, makeRequestAndProcessResponse(new Requestor(paramString)
    {
      public WebRequestor.Response makeRequest()
        throws IOException
      {
        return DefaultFacebookClient.this.webRequestor.executeGet(this.val$connectionPageUrl);
      }
    }), paramClass);
  }

  public <T> T fetchObject(String paramString, Class<T> paramClass, Parameter[] paramArrayOfParameter)
  {
    verifyParameterPresence("object", paramString);
    verifyParameterPresence("objectType", paramClass);
    return this.jsonMapper.toJavaObject(makeRequest(paramString, paramArrayOfParameter), paramClass);
  }

  public <T> T fetchObjects(List<String> paramList, Class<T> paramClass, Parameter[] paramArrayOfParameter)
  {
    verifyParameterPresence("ids", paramList);
    verifyParameterPresence("connectionType", paramClass);
    if (paramList.size() == 0)
      throw new IllegalArgumentException("The list of IDs cannot be empty.");
    int i = paramArrayOfParameter.length;
    for (int j = 0; j < i; j++)
    {
      if (!"ids".equals(paramArrayOfParameter[j].name))
        continue;
      throw new IllegalArgumentException("You cannot specify the 'ids' URL parameter yourself - RestFB will populate this for you with the list of IDs you passed to this method.");
    }
    for (int k = 0; k < paramList.size(); k++)
    {
      String str = ((String)paramList.get(k)).trim().toLowerCase();
      if ("".equals(str))
        throw new IllegalArgumentException("The list of IDs cannot contain blank strings.");
      paramList.set(k, str);
    }
    try
    {
      JsonObject localJsonObject = new JsonObject(makeRequest("", parametersWithAdditionalParameter(Parameter.with("ids", StringUtils.join(paramList)), paramArrayOfParameter)));
      if (paramClass.equals(JsonObject.class))
        return localJsonObject;
      Object localObject = this.jsonMapper.toJavaObject(localJsonObject.toString(), paramClass);
      return localObject;
    }
    catch (JsonException localJsonException)
    {
    }
    throw new FacebookJsonMappingException("Unable to map connection JSON to Java objects", localJsonException);
  }

  protected String getFacebookGraphEndpointUrl()
  {
    return "https://graph.facebook.com";
  }

  protected String getFacebookGraphVideoEndpointUrl()
  {
    return "https://graph-video.facebook.com";
  }

  protected String getFacebookReadOnlyEndpointUrl()
  {
    return "https://api-read.facebook.com/method";
  }

  public JsonMapper getJsonMapper()
  {
    return this.jsonMapper;
  }

  public WebRequestor getWebRequestor()
  {
    return this.webRequestor;
  }

  protected String makeRequest(String paramString, boolean paramBoolean1, boolean paramBoolean2, List<BinaryAttachment> paramList, Parameter[] paramArrayOfParameter)
  {
    verifyParameterLegality(paramArrayOfParameter);
    if (paramBoolean2)
      paramArrayOfParameter = parametersWithAdditionalParameter(Parameter.with("method", "delete"), paramArrayOfParameter);
    StringUtils.trimToEmpty(paramString).toLowerCase();
    if (!paramString.startsWith("/"))
      paramString = "/" + paramString;
    if ((paramList != null) && (paramList.size() > 0));
    for (boolean bool = true; ; bool = false)
      return makeRequestAndProcessResponse(new Requestor(paramBoolean1, createEndpointForApiCall(paramString, bool), toParameterString(paramArrayOfParameter), paramList)
      {
        public WebRequestor.Response makeRequest()
          throws IOException
        {
          if (this.val$executeAsPost)
          {
            WebRequestor localWebRequestor = DefaultFacebookClient.this.webRequestor;
            String str1 = this.val$fullEndpoint;
            String str2 = this.val$parameterString;
            if (this.val$binaryAttachments == null);
            for (BinaryAttachment[] arrayOfBinaryAttachment = null; ; arrayOfBinaryAttachment = (BinaryAttachment[])this.val$binaryAttachments.toArray(new BinaryAttachment[0]))
              return localWebRequestor.executePost(str1, str2, arrayOfBinaryAttachment);
          }
          return DefaultFacebookClient.this.webRequestor.executeGet(this.val$fullEndpoint + "?" + this.val$parameterString);
        }
      });
  }

  protected String makeRequest(String paramString, Parameter[] paramArrayOfParameter)
  {
    return makeRequest(paramString, false, false, null, paramArrayOfParameter);
  }

  protected String makeRequestAndProcessResponse(Requestor paramRequestor)
  {
    WebRequestor.Response localResponse;
    try
    {
      localResponse = paramRequestor.makeRequest();
      if (this.logger.isLoggable(Level.INFO))
        this.logger.info("Facebook responded with " + localResponse);
      if ((200 != localResponse.getStatusCode().intValue()) && (400 != localResponse.getStatusCode().intValue()) && (401 != localResponse.getStatusCode().intValue()) && (500 != localResponse.getStatusCode().intValue()) && (403 != localResponse.getStatusCode().intValue()))
        throw new FacebookNetworkException("Facebook request failed", localResponse.getStatusCode());
    }
    catch (Throwable localThrowable)
    {
      throw new FacebookNetworkException("Facebook request failed", localThrowable);
    }
    String str = localResponse.getBody();
    throwFacebookResponseStatusExceptionIfNecessary(str);
    if ((500 == localResponse.getStatusCode().intValue()) || (401 == localResponse.getStatusCode().intValue()))
      throw new FacebookNetworkException("Facebook request failed", localResponse.getStatusCode());
    return str;
  }

  public <T> T publish(String paramString, Class<T> paramClass, BinaryAttachment paramBinaryAttachment, Parameter[] paramArrayOfParameter)
  {
    verifyParameterPresence("connection", paramString);
    ArrayList localArrayList = new ArrayList();
    if (paramBinaryAttachment != null)
      localArrayList.add(paramBinaryAttachment);
    return this.jsonMapper.toJavaObject(makeRequest(paramString, true, false, localArrayList, paramArrayOfParameter), paramClass);
  }

  public <T> T publish(String paramString, Class<T> paramClass, Parameter[] paramArrayOfParameter)
  {
    return publish(paramString, paramClass, null, paramArrayOfParameter);
  }

  // ERROR //
  protected void throwBatchFacebookResponseStatusExceptionIfNecessary(String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 465
    //   4: invokevirtual 171	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   7: istore_3
    //   8: iload_3
    //   9: ifne +4 -> 13
    //   12: return
    //   13: new 268	com/restfb/json/JsonObject
    //   16: dup
    //   17: aload_1
    //   18: invokespecial 365	com/restfb/json/JsonObject:<init>	(Ljava/lang/String;)V
    //   21: astore 4
    //   23: aload 4
    //   25: ifnull -13 -> 12
    //   28: aload 4
    //   30: ldc 10
    //   32: invokevirtual 468	com/restfb/json/JsonObject:has	(Ljava/lang/String;)Z
    //   35: ifeq -23 -> 12
    //   38: aload 4
    //   40: ldc 13
    //   42: invokevirtual 468	com/restfb/json/JsonObject:has	(Ljava/lang/String;)Z
    //   45: ifeq -33 -> 12
    //   48: aload_0
    //   49: getfield 471	com/restfb/DefaultFacebookClient:legacyFacebookExceptionMapper	Lcom/restfb/exception/FacebookExceptionMapper;
    //   52: aload 4
    //   54: ldc 10
    //   56: invokevirtual 475	com/restfb/json/JsonObject:getInt	(Ljava/lang/String;)I
    //   59: invokestatic 479	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   62: aconst_null
    //   63: aload 4
    //   65: ldc 13
    //   67: invokevirtual 290	com/restfb/json/JsonObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   70: invokeinterface 485 4 0
    //   75: athrow
    //   76: astore_2
    //   77: new 297	com/restfb/exception/FacebookJsonMappingException
    //   80: dup
    //   81: ldc_w 487
    //   84: aload_2
    //   85: invokespecial 302	com/restfb/exception/FacebookJsonMappingException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   88: athrow
    //   89: astore 5
    //   91: aconst_null
    //   92: astore 4
    //   94: goto -71 -> 23
    //
    // Exception table:
    //   from	to	target	type
    //   0	8	76	com/restfb/json/JsonException
    //   28	76	76	com/restfb/json/JsonException
    //   13	23	89	com/restfb/json/JsonException
  }

  protected void throwFacebookResponseStatusExceptionIfNecessary(String paramString)
  {
    throwLegacyFacebookResponseStatusExceptionIfNecessary(paramString);
    throwBatchFacebookResponseStatusExceptionIfNecessary(paramString);
    try
    {
      if (!paramString.startsWith("{"))
        return;
      JsonObject localJsonObject1 = new JsonObject(paramString);
      if ((localJsonObject1 != null) && (localJsonObject1.has("error")))
      {
        JsonObject localJsonObject2 = localJsonObject1.getJsonObject("error");
        throw this.graphFacebookExceptionMapper.exceptionForTypeAndMessage(null, localJsonObject2.getString("type"), localJsonObject2.getString("message"));
      }
    }
    catch (JsonException localJsonException)
    {
      throw new FacebookJsonMappingException("Unable to process the Facebook API response", localJsonException);
    }
  }

  protected String toParameterString(Parameter[] paramArrayOfParameter)
  {
    if (!StringUtils.isBlank(this.accessToken))
      paramArrayOfParameter = parametersWithAdditionalParameter(Parameter.with("access_token", this.accessToken), paramArrayOfParameter);
    Parameter[] arrayOfParameter = parametersWithAdditionalParameter(Parameter.with("format", "json"), paramArrayOfParameter);
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 1;
    int j = arrayOfParameter.length;
    int k = 0;
    if (k < j)
    {
      Parameter localParameter = arrayOfParameter[k];
      if (i != 0)
        i = 0;
      while (true)
      {
        localStringBuilder.append(StringUtils.urlEncode(localParameter.name));
        localStringBuilder.append("=");
        localStringBuilder.append(urlEncodedValueForParameterName(localParameter.name, localParameter.value));
        k++;
        break;
        localStringBuilder.append("&");
      }
    }
    return localStringBuilder.toString();
  }

  protected static class DefaultGraphFacebookExceptionMapper
    implements FacebookExceptionMapper
  {
    public FacebookException exceptionForTypeAndMessage(Integer paramInteger, String paramString1, String paramString2)
    {
      if (("OAuthException".equals(paramString1)) || ("OAuthAccessTokenException".equals(paramString1)))
        return new FacebookOAuthException(paramString1, paramString2);
      if ("QueryParseException".equals(paramString1))
        return new FacebookQueryParseException(paramString1, paramString2);
      return new FacebookGraphException(paramString1, paramString2);
    }
  }

  protected static abstract interface Requestor
  {
    public abstract WebRequestor.Response makeRequest()
      throws IOException;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.DefaultFacebookClient
 * JD-Core Version:    0.6.0
 */