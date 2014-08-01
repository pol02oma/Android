package com.restfb;

import com.restfb.exception.FacebookException;
import com.restfb.exception.FacebookExceptionMapper;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.exception.FacebookResponseStatusException;
import com.restfb.json.JsonException;
import com.restfb.json.JsonObject;
import com.restfb.util.StringUtils;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Logger;

abstract class BaseFacebookClient
{
  protected static final String ACCESS_TOKEN_PARAM_NAME = "access_token";
  protected static final String LEGACY_ERROR_CODE_ATTRIBUTE_NAME = "error_code";
  protected static final String LEGACY_ERROR_MSG_ATTRIBUTE_NAME = "error_msg";
  protected final Set<String> illegalParamNames = new HashSet();
  protected JsonMapper jsonMapper;
  protected FacebookExceptionMapper legacyFacebookExceptionMapper;
  protected final Logger logger = Logger.getLogger(getClass().getName());
  protected final Set<String> readOnlyApiCalls = new HashSet();
  protected WebRequestor webRequestor;

  public BaseFacebookClient()
  {
    initializeReadOnlyApiCalls();
    this.legacyFacebookExceptionMapper = createLegacyFacebookExceptionMapper();
  }

  protected abstract String createEndpointForApiCall(String paramString, boolean paramBoolean);

  protected FacebookExceptionMapper createLegacyFacebookExceptionMapper()
  {
    return new DefaultLegacyFacebookExceptionMapper();
  }

  protected abstract String getFacebookReadOnlyEndpointUrl();

  protected void initializeReadOnlyApiCalls()
  {
    this.readOnlyApiCalls.addAll(Arrays.asList(new String[] { "admin.getallocation", "admin.getappproperties", "admin.getbannedusers", "admin.getlivestreamvialink", "admin.getmetrics", "admin.getrestrictioninfo", "application.getpublicinfo", "auth.getapppublickey", "auth.getsession", "auth.getsignedpublicsessiondata", "comments.get", "connect.getunconnectedfriendscount", "dashboard.getactivity", "dashboard.getcount", "dashboard.getglobalnews", "dashboard.getnews", "dashboard.multigetcount", "dashboard.multigetnews", "data.getcookies", "events.get", "events.getmembers", "fbml.getcustomtags", "feed.getappfriendstories", "feed.getregisteredtemplatebundlebyid", "feed.getregisteredtemplatebundles", "fql.multiquery", "fql.query", "friends.arefriends", "friends.get", "friends.getappusers", "friends.getlists", "friends.getmutualfriends", "gifts.get", "groups.get", "groups.getmembers", "intl.gettranslations", "links.get", "notes.get", "notifications.get", "pages.getinfo", "pages.isadmin", "pages.isappadded", "pages.isfan", "permissions.checkavailableapiaccess", "permissions.checkgrantedapiaccess", "photos.get", "photos.getalbums", "photos.gettags", "profile.getinfo", "profile.getinfooptions", "stream.get", "stream.getcomments", "stream.getfilters", "users.getinfo", "users.getloggedinuser", "users.getstandardinfo", "users.hasapppermission", "users.isappuser", "users.isverified", "video.getuploadlimits" }));
  }

  protected Parameter[] parametersWithAdditionalParameter(Parameter paramParameter, Parameter[] paramArrayOfParameter)
  {
    Parameter[] arrayOfParameter = new Parameter[1 + paramArrayOfParameter.length];
    System.arraycopy(paramArrayOfParameter, 0, arrayOfParameter, 0, paramArrayOfParameter.length);
    arrayOfParameter[paramArrayOfParameter.length] = paramParameter;
    return arrayOfParameter;
  }

  protected String queriesToJson(Map<String, String> paramMap)
  {
    verifyParameterPresence("queries", paramMap);
    if (paramMap.keySet().size() == 0)
      throw new IllegalArgumentException("You must specify at least one query.");
    JsonObject localJsonObject = new JsonObject();
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if ((StringUtils.isBlank((String)localEntry.getKey())) || (StringUtils.isBlank((String)localEntry.getValue())))
        throw new IllegalArgumentException("Provided queries must have non-blank keys and values. You provided: " + paramMap);
      try
      {
        localJsonObject.put(StringUtils.trimToEmpty((String)localEntry.getKey()), StringUtils.trimToEmpty((String)localEntry.getValue()));
      }
      catch (JsonException localJsonException)
      {
        throw new IllegalArgumentException("Unable to convert " + paramMap + " to JSON.", localJsonException);
      }
    }
    return localJsonObject.toString();
  }

  // ERROR //
  protected void throwLegacyFacebookResponseStatusExceptionIfNecessary(String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 309
    //   4: invokevirtual 312	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   7: istore_3
    //   8: iload_3
    //   9: ifne +4 -> 13
    //   12: return
    //   13: new 243	com/restfb/json/JsonObject
    //   16: dup
    //   17: aload_1
    //   18: invokespecial 313	com/restfb/json/JsonObject:<init>	(Ljava/lang/String;)V
    //   21: astore 4
    //   23: aload 4
    //   25: ifnull -13 -> 12
    //   28: aload 4
    //   30: ldc 11
    //   32: invokevirtual 316	com/restfb/json/JsonObject:has	(Ljava/lang/String;)Z
    //   35: ifeq -23 -> 12
    //   38: aload_0
    //   39: getfield 64	com/restfb/BaseFacebookClient:legacyFacebookExceptionMapper	Lcom/restfb/exception/FacebookExceptionMapper;
    //   42: aload 4
    //   44: ldc 11
    //   46: invokevirtual 320	com/restfb/json/JsonObject:getInt	(Ljava/lang/String;)I
    //   49: invokestatic 326	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   52: aconst_null
    //   53: aload 4
    //   55: ldc 14
    //   57: invokevirtual 329	com/restfb/json/JsonObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   60: invokeinterface 335 4 0
    //   65: athrow
    //   66: astore_2
    //   67: new 337	com/restfb/exception/FacebookJsonMappingException
    //   70: dup
    //   71: ldc_w 339
    //   74: aload_2
    //   75: invokespecial 340	com/restfb/exception/FacebookJsonMappingException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   78: athrow
    //   79: astore 5
    //   81: aconst_null
    //   82: astore 4
    //   84: goto -61 -> 23
    //
    // Exception table:
    //   from	to	target	type
    //   0	8	66	com/restfb/json/JsonException
    //   28	66	66	com/restfb/json/JsonException
    //   13	23	79	com/restfb/json/JsonException
  }

  protected String urlEncodedValueForParameterName(String paramString1, String paramString2)
  {
    if (("access_token".equals(paramString1)) && (paramString2.contains("%7C")))
      return paramString2;
    return StringUtils.urlEncode(paramString2);
  }

  protected void verifyParameterLegality(Parameter[] paramArrayOfParameter)
  {
    int i = paramArrayOfParameter.length;
    for (int j = 0; j < i; j++)
    {
      Parameter localParameter = paramArrayOfParameter[j];
      if (!this.illegalParamNames.contains(localParameter.name))
        continue;
      throw new IllegalArgumentException("Parameter '" + localParameter.name + "' is reserved for RestFB use - " + "you cannot specify it yourself.");
    }
  }

  protected void verifyParameterPresence(String paramString, Object paramObject)
  {
    if (paramObject == null)
      throw new NullPointerException("The '" + paramString + "' parameter cannot be null.");
  }

  protected void verifyParameterPresence(String paramString1, String paramString2)
  {
    verifyParameterPresence(paramString1, paramString2);
    if (paramString2.trim().length() == 0)
      throw new IllegalArgumentException("The '" + paramString1 + "' parameter cannot be an empty string.");
  }

  protected static class DefaultLegacyFacebookExceptionMapper
    implements FacebookExceptionMapper
  {
    protected static final int API_EC_PARAM_ACCESS_TOKEN = 190;

    public FacebookException exceptionForTypeAndMessage(Integer paramInteger, String paramString1, String paramString2)
    {
      if (paramInteger.intValue() == 190)
        return new FacebookOAuthException(String.valueOf(paramInteger), paramString2);
      return new FacebookResponseStatusException(paramInteger, paramString2);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.BaseFacebookClient
 * JD-Core Version:    0.6.0
 */