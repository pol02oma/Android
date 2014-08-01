package twitter4j.conf;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import twitter4j.Version;
import twitter4j.internal.logging.Logger;
import twitter4j.internal.util.z_T4JInternalStringUtil;

class ConfigurationBase
  implements Configuration, Serializable
{
  public static final String DALVIK = "twitter4j.dalvik";
  private static final String DEFAULT_OAUTH2_INVALIDATE_TOKEN_URL = "https://api.twitter.com/oauth2/invalidate_token";
  private static final String DEFAULT_OAUTH2_TOKEN_URL = "https://api.twitter.com/oauth2/token";
  private static final String DEFAULT_OAUTH_ACCESS_TOKEN_URL = "http://api.twitter.com/oauth/access_token";
  private static final String DEFAULT_OAUTH_AUTHENTICATION_URL = "http://api.twitter.com/oauth/authenticate";
  private static final String DEFAULT_OAUTH_AUTHORIZATION_URL = "http://api.twitter.com/oauth/authorize";
  private static final String DEFAULT_OAUTH_REQUEST_TOKEN_URL = "http://api.twitter.com/oauth/request_token";
  private static final String DEFAULT_REST_BASE_URL = "http://api.twitter.com/1.1/";
  private static final String DEFAULT_SITE_STREAM_BASE_URL = "https://sitestream.twitter.com/1.1/";
  private static final String DEFAULT_STREAM_BASE_URL = "https://stream.twitter.com/1.1/";
  private static final String DEFAULT_USER_STREAM_BASE_URL = "https://userstream.twitter.com/1.1/";
  public static final String GAE = "twitter4j.gae";
  static String dalvikDetected;
  static String gaeDetected;
  private static final List<ConfigurationBase> instances;
  private static final long serialVersionUID = -6610497517837844232L;
  private boolean IS_DALVIK;
  private boolean IS_GAE;
  private boolean applicationOnlyAuthEnabled = false;
  private int asyncNumThreads;
  private String clientURL;
  private String clientVersion;
  private long contributingTo;
  private boolean debug;
  private int defaultMaxPerRoute;
  private String dispatcherImpl;
  private boolean gzipEnabled;
  private int httpConnectionTimeout;
  private String httpProxyHost;
  private String httpProxyPassword;
  private int httpProxyPort;
  private String httpProxyUser;
  private int httpReadTimeout;
  private int httpRetryCount;
  private int httpRetryIntervalSeconds;
  private int httpStreamingReadTimeout;
  private boolean includeEntitiesEnabled = true;
  private boolean includeMyRetweetEnabled = true;
  private boolean includeRTsEnabled = true;
  private boolean jsonStoreEnabled;
  private String loggerFactory;
  private int maxTotalConnections;
  private boolean mbeanEnabled;
  private String mediaProvider;
  private String mediaProviderAPIKey;
  private Properties mediaProviderParameters;
  private String oAuth2AccessToken;
  private String oAuth2InvalidateTokenURL;
  private String oAuth2TokenType;
  private String oAuth2TokenURL;
  private String oAuthAccessToken;
  private String oAuthAccessTokenSecret;
  private String oAuthAccessTokenURL;
  private String oAuthAuthenticationURL;
  private String oAuthAuthorizationURL;
  private String oAuthConsumerKey;
  private String oAuthConsumerSecret;
  private String oAuthRequestTokenURL;
  private String password;
  private boolean prettyDebug;
  Map<String, String> requestHeaders;
  private String restBaseURL;
  private String siteStreamBaseURL;
  private boolean stallWarningsEnabled;
  private String streamBaseURL;
  private boolean trimUserEnabled = false;
  private boolean useSSL;
  private String user;
  private String userAgent;
  private String userStreamBaseURL;
  private boolean userStreamRepliesAllEnabled;

  static
  {
    try
    {
      Class.forName("dalvik.system.VMRuntime");
      dalvikDetected = "true";
    }
    catch (ClassNotFoundException localClassNotFoundException1)
    {
      try
      {
        while (true)
        {
          Class.forName("com.google.appengine.api.urlfetch.URLFetchService");
          gaeDetected = "true";
          instances = new ArrayList();
          return;
          localClassNotFoundException1 = localClassNotFoundException1;
          dalvikDetected = "false";
        }
      }
      catch (ClassNotFoundException localClassNotFoundException2)
      {
        while (true)
          gaeDetected = "false";
      }
    }
  }

  protected ConfigurationBase()
  {
    setDebug(false);
    setUser(null);
    setPassword(null);
    setUseSSL(true);
    setPrettyDebugEnabled(false);
    setGZIPEnabled(true);
    setHttpProxyHost(null);
    setHttpProxyUser(null);
    setHttpProxyPassword(null);
    setHttpProxyPort(-1);
    setHttpConnectionTimeout(20000);
    setHttpReadTimeout(120000);
    setHttpStreamingReadTimeout(40000);
    setHttpRetryCount(0);
    setHttpRetryIntervalSeconds(5);
    setHttpMaxTotalConnections(20);
    setHttpDefaultMaxPerRoute(2);
    setOAuthConsumerKey(null);
    setOAuthConsumerSecret(null);
    setOAuthAccessToken(null);
    setOAuthAccessTokenSecret(null);
    setAsyncNumThreads(1);
    setContributingTo(-1L);
    setClientVersion(Version.getVersion());
    setClientURL("http://twitter4j.org/en/twitter4j-" + Version.getVersion() + ".xml");
    setUserAgent("twitter4j http://twitter4j.org/ /" + Version.getVersion());
    setJSONStoreEnabled(false);
    setMBeanEnabled(false);
    setOAuthRequestTokenURL("http://api.twitter.com/oauth/request_token");
    setOAuthAuthorizationURL("http://api.twitter.com/oauth/authorize");
    setOAuthAccessTokenURL("http://api.twitter.com/oauth/access_token");
    setOAuthAuthenticationURL("http://api.twitter.com/oauth/authenticate");
    setOAuth2TokenURL("https://api.twitter.com/oauth2/token");
    setOAuth2InvalidateTokenURL("https://api.twitter.com/oauth2/invalidate_token");
    setRestBaseURL("http://api.twitter.com/1.1/");
    setStreamBaseURL("https://stream.twitter.com/1.1/");
    setUserStreamBaseURL("https://userstream.twitter.com/1.1/");
    setSiteStreamBaseURL("https://sitestream.twitter.com/1.1/");
    setDispatcherImpl("twitter4j.internal.async.DispatcherImpl");
    setLoggerFactory(null);
    setUserStreamRepliesAllEnabled(false);
    setStallWarningsEnabled(true);
    try
    {
      String str4 = System.getProperty("twitter4j.dalvik", dalvikDetected);
      str1 = str4;
      this.IS_DALVIK = Boolean.valueOf(str1).booleanValue();
    }
    catch (SecurityException localSecurityException1)
    {
      try
      {
        String str3 = System.getProperty("twitter4j.gae", gaeDetected);
        str2 = str3;
        this.IS_GAE = Boolean.valueOf(str2).booleanValue();
        setMediaProvider("TWITTER");
        setMediaProviderAPIKey(null);
        setMediaProviderParameters(null);
        return;
        localSecurityException1 = localSecurityException1;
        String str1 = dalvikDetected;
      }
      catch (SecurityException localSecurityException2)
      {
        while (true)
          String str2 = gaeDetected;
      }
    }
  }

  private static void cacheInstance(ConfigurationBase paramConfigurationBase)
  {
    if (!instances.contains(paramConfigurationBase))
      instances.add(paramConfigurationBase);
  }

  private void fixRestBaseURL()
  {
    if ("http://api.twitter.com/1.1/".equals(fixURL(false, this.restBaseURL)))
      this.restBaseURL = fixURL(this.useSSL, this.restBaseURL);
    if ("http://api.twitter.com/oauth/access_token".equals(fixURL(false, this.oAuthAccessTokenURL)))
      this.oAuthAccessTokenURL = fixURL(this.useSSL, this.oAuthAccessTokenURL);
    if ("http://api.twitter.com/oauth/authenticate".equals(fixURL(false, this.oAuthAuthenticationURL)))
      this.oAuthAuthenticationURL = fixURL(this.useSSL, this.oAuthAuthenticationURL);
    if ("http://api.twitter.com/oauth/authorize".equals(fixURL(false, this.oAuthAuthorizationURL)))
      this.oAuthAuthorizationURL = fixURL(this.useSSL, this.oAuthAuthorizationURL);
    if ("http://api.twitter.com/oauth/request_token".equals(fixURL(false, this.oAuthRequestTokenURL)))
      this.oAuthRequestTokenURL = fixURL(this.useSSL, this.oAuthRequestTokenURL);
  }

  static String fixURL(boolean paramBoolean, String paramString)
  {
    if (paramString == null)
      return null;
    int i = paramString.indexOf("://");
    if (-1 == i)
      throw new IllegalArgumentException("url should contain '://'");
    String str = paramString.substring(i + 3);
    if (paramBoolean)
      return "https://" + str;
    return "http://" + str;
  }

  private static ConfigurationBase getInstance(ConfigurationBase paramConfigurationBase)
  {
    int i = instances.indexOf(paramConfigurationBase);
    if (i == -1)
    {
      instances.add(paramConfigurationBase);
      return paramConfigurationBase;
    }
    return (ConfigurationBase)instances.get(i);
  }

  private void initRequestHeaders()
  {
    this.requestHeaders = new HashMap();
    this.requestHeaders.put("X-Twitter-Client-Version", getClientVersion());
    this.requestHeaders.put("X-Twitter-Client-URL", getClientURL());
    this.requestHeaders.put("X-Twitter-Client", "Twitter4J");
    this.requestHeaders.put("User-Agent", getUserAgent());
    if (this.gzipEnabled)
      this.requestHeaders.put("Accept-Encoding", "gzip");
    if (this.IS_DALVIK)
      this.requestHeaders.put("Connection", "close");
  }

  protected void cacheInstance()
  {
    cacheInstance(this);
  }

  public void dumpConfiguration()
  {
    Logger localLogger = Logger.getLogger(ConfigurationBase.class);
    Field[] arrayOfField;
    int i;
    int j;
    if (this.debug)
    {
      arrayOfField = ConfigurationBase.class.getDeclaredFields();
      i = arrayOfField.length;
      j = 0;
    }
    while (true)
    {
      Field localField;
      if (j < i)
        localField = arrayOfField[j];
      try
      {
        Object localObject = localField.get(this);
        String str = String.valueOf(localObject);
        if ((localObject != null) && (localField.getName().matches("oAuthConsumerSecret|oAuthAccessTokenSecret|password")))
          str = z_T4JInternalStringUtil.maskString(String.valueOf(localObject));
        localLogger.debug(localField.getName() + ": " + str);
        label114: j++;
        continue;
        if (!this.includeRTsEnabled)
          localLogger.warn("includeRTsEnabled is set to false. This configuration may not take effect after May 14th, 2012. https://dev.twitter.com/blog/api-housekeeping");
        if (!this.includeEntitiesEnabled)
          localLogger.warn("includeEntitiesEnabled is set to false. This configuration may not take effect after May 14th, 2012. https://dev.twitter.com/blog/api-housekeeping");
        return;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        break label114;
      }
    }
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    ConfigurationBase localConfigurationBase;
    do
    {
      return true;
      if ((paramObject == null) || (getClass() != paramObject.getClass()))
        return false;
      localConfigurationBase = (ConfigurationBase)paramObject;
      if (this.IS_DALVIK != localConfigurationBase.IS_DALVIK)
        return false;
      if (this.IS_GAE != localConfigurationBase.IS_GAE)
        return false;
      if (this.asyncNumThreads != localConfigurationBase.asyncNumThreads)
        return false;
      if (this.contributingTo != localConfigurationBase.contributingTo)
        return false;
      if (this.debug != localConfigurationBase.debug)
        return false;
      if (this.defaultMaxPerRoute != localConfigurationBase.defaultMaxPerRoute)
        return false;
      if (this.gzipEnabled != localConfigurationBase.gzipEnabled)
        return false;
      if (this.httpConnectionTimeout != localConfigurationBase.httpConnectionTimeout)
        return false;
      if (this.httpProxyPort != localConfigurationBase.httpProxyPort)
        return false;
      if (this.httpReadTimeout != localConfigurationBase.httpReadTimeout)
        return false;
      if (this.httpRetryCount != localConfigurationBase.httpRetryCount)
        return false;
      if (this.httpRetryIntervalSeconds != localConfigurationBase.httpRetryIntervalSeconds)
        return false;
      if (this.httpStreamingReadTimeout != localConfigurationBase.httpStreamingReadTimeout)
        return false;
      if (this.includeEntitiesEnabled != localConfigurationBase.includeEntitiesEnabled)
        return false;
      if (this.includeMyRetweetEnabled != localConfigurationBase.includeMyRetweetEnabled)
        return false;
      if (this.trimUserEnabled != localConfigurationBase.trimUserEnabled)
        return false;
      if (this.includeRTsEnabled != localConfigurationBase.includeRTsEnabled)
        return false;
      if (this.jsonStoreEnabled != localConfigurationBase.jsonStoreEnabled)
        return false;
      if (this.maxTotalConnections != localConfigurationBase.maxTotalConnections)
        return false;
      if (this.mbeanEnabled != localConfigurationBase.mbeanEnabled)
        return false;
      if (this.prettyDebug != localConfigurationBase.prettyDebug)
        return false;
      if (this.stallWarningsEnabled != localConfigurationBase.stallWarningsEnabled)
        return false;
      if (this.applicationOnlyAuthEnabled != localConfigurationBase.applicationOnlyAuthEnabled)
        return false;
      if (this.useSSL != localConfigurationBase.useSSL)
        return false;
      if (this.userStreamRepliesAllEnabled != localConfigurationBase.userStreamRepliesAllEnabled)
        return false;
      if (this.clientURL != null)
      {
        if (this.clientURL.equals(localConfigurationBase.clientURL));
      }
      else
        do
          return false;
        while (localConfigurationBase.clientURL != null);
      if (this.clientVersion != null)
      {
        if (this.clientVersion.equals(localConfigurationBase.clientVersion));
      }
      else
        do
          return false;
        while (localConfigurationBase.clientVersion != null);
      if (this.dispatcherImpl != null)
      {
        if (this.dispatcherImpl.equals(localConfigurationBase.dispatcherImpl));
      }
      else
        do
          return false;
        while (localConfigurationBase.dispatcherImpl != null);
      if (this.httpProxyHost != null)
      {
        if (this.httpProxyHost.equals(localConfigurationBase.httpProxyHost));
      }
      else
        do
          return false;
        while (localConfigurationBase.httpProxyHost != null);
      if (this.httpProxyPassword != null)
      {
        if (this.httpProxyPassword.equals(localConfigurationBase.httpProxyPassword));
      }
      else
        do
          return false;
        while (localConfigurationBase.httpProxyPassword != null);
      if (this.httpProxyUser != null)
      {
        if (this.httpProxyUser.equals(localConfigurationBase.httpProxyUser));
      }
      else
        do
          return false;
        while (localConfigurationBase.httpProxyUser != null);
      if (this.loggerFactory != null)
      {
        if (this.loggerFactory.equals(localConfigurationBase.loggerFactory));
      }
      else
        do
          return false;
        while (localConfigurationBase.loggerFactory != null);
      if (this.mediaProvider != null)
      {
        if (this.mediaProvider.equals(localConfigurationBase.mediaProvider));
      }
      else
        do
          return false;
        while (localConfigurationBase.mediaProvider != null);
      if (this.mediaProviderAPIKey != null)
      {
        if (this.mediaProviderAPIKey.equals(localConfigurationBase.mediaProviderAPIKey));
      }
      else
        do
          return false;
        while (localConfigurationBase.mediaProviderAPIKey != null);
      if (this.mediaProviderParameters != null)
      {
        if (this.mediaProviderParameters.equals(localConfigurationBase.mediaProviderParameters));
      }
      else
        do
          return false;
        while (localConfigurationBase.mediaProviderParameters != null);
      if (this.oAuthAccessToken != null)
      {
        if (this.oAuthAccessToken.equals(localConfigurationBase.oAuthAccessToken));
      }
      else
        do
          return false;
        while (localConfigurationBase.oAuthAccessToken != null);
      if (this.oAuthAccessTokenSecret != null)
      {
        if (this.oAuthAccessTokenSecret.equals(localConfigurationBase.oAuthAccessTokenSecret));
      }
      else
        do
          return false;
        while (localConfigurationBase.oAuthAccessTokenSecret != null);
      if (this.oAuth2TokenType != null)
      {
        if (this.oAuth2TokenType.equals(localConfigurationBase.oAuth2TokenType));
      }
      else
        do
          return false;
        while (localConfigurationBase.oAuth2TokenType != null);
      if (this.oAuth2AccessToken != null)
      {
        if (this.oAuth2AccessToken.equals(localConfigurationBase.oAuth2AccessToken));
      }
      else
        do
          return false;
        while (localConfigurationBase.oAuth2AccessToken != null);
      if (this.oAuthAccessTokenURL != null)
      {
        if (this.oAuthAccessTokenURL.equals(localConfigurationBase.oAuthAccessTokenURL));
      }
      else
        do
          return false;
        while (localConfigurationBase.oAuthAccessTokenURL != null);
      if (this.oAuthAuthenticationURL != null)
      {
        if (this.oAuthAuthenticationURL.equals(localConfigurationBase.oAuthAuthenticationURL));
      }
      else
        do
          return false;
        while (localConfigurationBase.oAuthAuthenticationURL != null);
      if (this.oAuthAuthorizationURL != null)
      {
        if (this.oAuthAuthorizationURL.equals(localConfigurationBase.oAuthAuthorizationURL));
      }
      else
        do
          return false;
        while (localConfigurationBase.oAuthAuthorizationURL != null);
      if (this.oAuth2TokenURL != null)
      {
        if (this.oAuth2TokenURL.equals(localConfigurationBase.oAuth2TokenURL));
      }
      else
        do
          return false;
        while (localConfigurationBase.oAuth2TokenURL != null);
      if (this.oAuth2InvalidateTokenURL != null)
      {
        if (this.oAuth2InvalidateTokenURL.equals(localConfigurationBase.oAuth2InvalidateTokenURL));
      }
      else
        do
          return false;
        while (localConfigurationBase.oAuth2InvalidateTokenURL != null);
      if (this.oAuthConsumerKey != null)
      {
        if (this.oAuthConsumerKey.equals(localConfigurationBase.oAuthConsumerKey));
      }
      else
        do
          return false;
        while (localConfigurationBase.oAuthConsumerKey != null);
      if (this.oAuthConsumerSecret != null)
      {
        if (this.oAuthConsumerSecret.equals(localConfigurationBase.oAuthConsumerSecret));
      }
      else
        do
          return false;
        while (localConfigurationBase.oAuthConsumerSecret != null);
      if (this.oAuthRequestTokenURL != null)
      {
        if (this.oAuthRequestTokenURL.equals(localConfigurationBase.oAuthRequestTokenURL));
      }
      else
        do
          return false;
        while (localConfigurationBase.oAuthRequestTokenURL != null);
      if (this.password != null)
      {
        if (this.password.equals(localConfigurationBase.password));
      }
      else
        do
          return false;
        while (localConfigurationBase.password != null);
      if (this.requestHeaders != null)
      {
        if (this.requestHeaders.equals(localConfigurationBase.requestHeaders));
      }
      else
        do
          return false;
        while (localConfigurationBase.requestHeaders != null);
      if (this.restBaseURL != null)
      {
        if (this.restBaseURL.equals(localConfigurationBase.restBaseURL));
      }
      else
        do
          return false;
        while (localConfigurationBase.restBaseURL != null);
      if (this.siteStreamBaseURL != null)
      {
        if (this.siteStreamBaseURL.equals(localConfigurationBase.siteStreamBaseURL));
      }
      else
        do
          return false;
        while (localConfigurationBase.siteStreamBaseURL != null);
      if (this.streamBaseURL != null)
      {
        if (this.streamBaseURL.equals(localConfigurationBase.streamBaseURL));
      }
      else
        do
          return false;
        while (localConfigurationBase.streamBaseURL != null);
      if (this.user != null)
      {
        if (this.user.equals(localConfigurationBase.user));
      }
      else
        do
          return false;
        while (localConfigurationBase.user != null);
      if (this.userAgent != null)
      {
        if (this.userAgent.equals(localConfigurationBase.userAgent));
      }
      else
        do
          return false;
        while (localConfigurationBase.userAgent != null);
      if (this.userStreamBaseURL == null)
        break;
    }
    while (this.userStreamBaseURL.equals(localConfigurationBase.userStreamBaseURL));
    while (true)
    {
      return false;
      if (localConfigurationBase.userStreamBaseURL == null)
        break;
    }
  }

  public final int getAsyncNumThreads()
  {
    return this.asyncNumThreads;
  }

  public final String getClientURL()
  {
    return this.clientURL;
  }

  public final String getClientVersion()
  {
    return this.clientVersion;
  }

  public final long getContributingTo()
  {
    return this.contributingTo;
  }

  public String getDispatcherImpl()
  {
    return this.dispatcherImpl;
  }

  public final int getHttpConnectionTimeout()
  {
    return this.httpConnectionTimeout;
  }

  public final int getHttpDefaultMaxPerRoute()
  {
    return this.defaultMaxPerRoute;
  }

  public final int getHttpMaxTotalConnections()
  {
    return this.maxTotalConnections;
  }

  public final String getHttpProxyHost()
  {
    return this.httpProxyHost;
  }

  public final String getHttpProxyPassword()
  {
    return this.httpProxyPassword;
  }

  public final int getHttpProxyPort()
  {
    return this.httpProxyPort;
  }

  public final String getHttpProxyUser()
  {
    return this.httpProxyUser;
  }

  public final int getHttpReadTimeout()
  {
    return this.httpReadTimeout;
  }

  public final int getHttpRetryCount()
  {
    return this.httpRetryCount;
  }

  public final int getHttpRetryIntervalSeconds()
  {
    return this.httpRetryIntervalSeconds;
  }

  public int getHttpStreamingReadTimeout()
  {
    return this.httpStreamingReadTimeout;
  }

  public String getLoggerFactory()
  {
    return this.loggerFactory;
  }

  public String getMediaProvider()
  {
    return this.mediaProvider;
  }

  public String getMediaProviderAPIKey()
  {
    return this.mediaProviderAPIKey;
  }

  public Properties getMediaProviderParameters()
  {
    return this.mediaProviderParameters;
  }

  public String getOAuth2AccessToken()
  {
    return this.oAuth2AccessToken;
  }

  public String getOAuth2InvalidateTokenURL()
  {
    return this.oAuth2InvalidateTokenURL;
  }

  public String getOAuth2TokenType()
  {
    return this.oAuth2TokenType;
  }

  public String getOAuth2TokenURL()
  {
    return this.oAuth2TokenURL;
  }

  public String getOAuthAccessToken()
  {
    return this.oAuthAccessToken;
  }

  public String getOAuthAccessTokenSecret()
  {
    return this.oAuthAccessTokenSecret;
  }

  public String getOAuthAccessTokenURL()
  {
    return this.oAuthAccessTokenURL;
  }

  public String getOAuthAuthenticationURL()
  {
    return this.oAuthAuthenticationURL;
  }

  public String getOAuthAuthorizationURL()
  {
    return this.oAuthAuthorizationURL;
  }

  public final String getOAuthConsumerKey()
  {
    return this.oAuthConsumerKey;
  }

  public final String getOAuthConsumerSecret()
  {
    return this.oAuthConsumerSecret;
  }

  public String getOAuthRequestTokenURL()
  {
    return this.oAuthRequestTokenURL;
  }

  public final String getPassword()
  {
    return this.password;
  }

  public Map<String, String> getRequestHeaders()
  {
    return this.requestHeaders;
  }

  public String getRestBaseURL()
  {
    return this.restBaseURL;
  }

  public String getSiteStreamBaseURL()
  {
    return this.siteStreamBaseURL;
  }

  public String getStreamBaseURL()
  {
    return this.streamBaseURL;
  }

  public final String getUser()
  {
    return this.user;
  }

  public final String getUserAgent()
  {
    return this.userAgent;
  }

  public String getUserStreamBaseURL()
  {
    return this.userStreamBaseURL;
  }

  public int hashCode()
  {
    int i = 1;
    int j;
    int m;
    label32: int i1;
    label57: int i3;
    label83: int i5;
    label103: int i7;
    label123: int i9;
    label143: int i11;
    label169: int i13;
    label195: int i15;
    label221: int i17;
    label311: int i19;
    label337: int i21;
    label363: int i23;
    label389: int i25;
    label415: int i27;
    label441: int i29;
    label467: int i31;
    label493: int i33;
    label519: int i35;
    label545: int i37;
    label571: int i39;
    label597: int i41;
    label623: int i43;
    label649: int i45;
    label675: int i47;
    label701: int i49;
    label727: int i51;
    label753: int i53;
    label798: int i55;
    label818: int i57;
    label838: int i59;
    label858: int i61;
    label878: int i63;
    label898: int i65;
    label918: int i67;
    label938: int i69;
    label958: int i71;
    label984: int i73;
    label1010: int i75;
    label1036: int i77;
    label1062: int i79;
    label1088: int i81;
    label1108: int i82;
    if (this.debug)
    {
      j = i;
      int k = j * 31;
      if (this.userAgent == null)
        break label1170;
      m = this.userAgent.hashCode();
      int n = 31 * (k + m);
      if (this.user == null)
        break label1176;
      i1 = this.user.hashCode();
      int i2 = 31 * (n + i1);
      if (this.password == null)
        break label1182;
      i3 = this.password.hashCode();
      int i4 = 31 * (i2 + i3);
      if (!this.useSSL)
        break label1188;
      i5 = i;
      int i6 = 31 * (i4 + i5);
      if (!this.prettyDebug)
        break label1194;
      i7 = i;
      int i8 = 31 * (i6 + i7);
      if (!this.gzipEnabled)
        break label1200;
      i9 = i;
      int i10 = 31 * (i8 + i9);
      if (this.httpProxyHost == null)
        break label1206;
      i11 = this.httpProxyHost.hashCode();
      int i12 = 31 * (i10 + i11);
      if (this.httpProxyUser == null)
        break label1212;
      i13 = this.httpProxyUser.hashCode();
      int i14 = 31 * (i12 + i13);
      if (this.httpProxyPassword == null)
        break label1218;
      i15 = this.httpProxyPassword.hashCode();
      int i16 = 31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * (i14 + i15) + this.httpProxyPort) + this.httpConnectionTimeout) + this.httpReadTimeout) + this.httpStreamingReadTimeout) + this.httpRetryCount) + this.httpRetryIntervalSeconds) + this.maxTotalConnections) + this.defaultMaxPerRoute);
      if (this.oAuthConsumerKey == null)
        break label1224;
      i17 = this.oAuthConsumerKey.hashCode();
      int i18 = 31 * (i16 + i17);
      if (this.oAuthConsumerSecret == null)
        break label1230;
      i19 = this.oAuthConsumerSecret.hashCode();
      int i20 = 31 * (i18 + i19);
      if (this.oAuthAccessToken == null)
        break label1236;
      i21 = this.oAuthAccessToken.hashCode();
      int i22 = 31 * (i20 + i21);
      if (this.oAuthAccessTokenSecret == null)
        break label1242;
      i23 = this.oAuthAccessTokenSecret.hashCode();
      int i24 = 31 * (i22 + i23);
      if (this.oAuth2TokenType == null)
        break label1248;
      i25 = this.oAuth2TokenType.hashCode();
      int i26 = 31 * (i24 + i25);
      if (this.oAuth2AccessToken == null)
        break label1254;
      i27 = this.oAuth2AccessToken.hashCode();
      int i28 = 31 * (i26 + i27);
      if (this.oAuthRequestTokenURL == null)
        break label1260;
      i29 = this.oAuthRequestTokenURL.hashCode();
      int i30 = 31 * (i28 + i29);
      if (this.oAuthAuthorizationURL == null)
        break label1266;
      i31 = this.oAuthAuthorizationURL.hashCode();
      int i32 = 31 * (i30 + i31);
      if (this.oAuthAccessTokenURL == null)
        break label1272;
      i33 = this.oAuthAccessTokenURL.hashCode();
      int i34 = 31 * (i32 + i33);
      if (this.oAuthAuthenticationURL == null)
        break label1278;
      i35 = this.oAuthAuthenticationURL.hashCode();
      int i36 = 31 * (i34 + i35);
      if (this.oAuth2TokenURL == null)
        break label1284;
      i37 = this.oAuth2TokenURL.hashCode();
      int i38 = 31 * (i36 + i37);
      if (this.oAuth2InvalidateTokenURL == null)
        break label1290;
      i39 = this.oAuth2InvalidateTokenURL.hashCode();
      int i40 = 31 * (i38 + i39);
      if (this.restBaseURL == null)
        break label1296;
      i41 = this.restBaseURL.hashCode();
      int i42 = 31 * (i40 + i41);
      if (this.streamBaseURL == null)
        break label1302;
      i43 = this.streamBaseURL.hashCode();
      int i44 = 31 * (i42 + i43);
      if (this.userStreamBaseURL == null)
        break label1308;
      i45 = this.userStreamBaseURL.hashCode();
      int i46 = 31 * (i44 + i45);
      if (this.siteStreamBaseURL == null)
        break label1314;
      i47 = this.siteStreamBaseURL.hashCode();
      int i48 = 31 * (i46 + i47);
      if (this.dispatcherImpl == null)
        break label1320;
      i49 = this.dispatcherImpl.hashCode();
      int i50 = 31 * (i48 + i49);
      if (this.loggerFactory == null)
        break label1326;
      i51 = this.loggerFactory.hashCode();
      int i52 = 31 * (31 * (31 * (i50 + i51) + this.asyncNumThreads) + (int)(this.contributingTo ^ this.contributingTo >>> 32));
      if (!this.includeRTsEnabled)
        break label1332;
      i53 = i;
      int i54 = 31 * (i52 + i53);
      if (!this.includeEntitiesEnabled)
        break label1338;
      i55 = i;
      int i56 = 31 * (i54 + i55);
      if (!this.includeMyRetweetEnabled)
        break label1344;
      i57 = i;
      int i58 = 31 * (i56 + i57);
      if (!this.trimUserEnabled)
        break label1350;
      i59 = i;
      int i60 = 31 * (i58 + i59);
      if (!this.jsonStoreEnabled)
        break label1356;
      i61 = i;
      int i62 = 31 * (i60 + i61);
      if (!this.mbeanEnabled)
        break label1362;
      i63 = i;
      int i64 = 31 * (i62 + i63);
      if (!this.userStreamRepliesAllEnabled)
        break label1368;
      i65 = i;
      int i66 = 31 * (i64 + i65);
      if (!this.stallWarningsEnabled)
        break label1374;
      i67 = i;
      int i68 = 31 * (i66 + i67);
      if (!this.applicationOnlyAuthEnabled)
        break label1380;
      i69 = i;
      int i70 = 31 * (i68 + i69);
      if (this.mediaProvider == null)
        break label1386;
      i71 = this.mediaProvider.hashCode();
      int i72 = 31 * (i70 + i71);
      if (this.mediaProviderAPIKey == null)
        break label1392;
      i73 = this.mediaProviderAPIKey.hashCode();
      int i74 = 31 * (i72 + i73);
      if (this.mediaProviderParameters == null)
        break label1398;
      i75 = this.mediaProviderParameters.hashCode();
      int i76 = 31 * (i74 + i75);
      if (this.clientVersion == null)
        break label1404;
      i77 = this.clientVersion.hashCode();
      int i78 = 31 * (i76 + i77);
      if (this.clientURL == null)
        break label1410;
      i79 = this.clientURL.hashCode();
      int i80 = 31 * (i78 + i79);
      if (!this.IS_DALVIK)
        break label1416;
      i81 = i;
      i82 = 31 * (i80 + i81);
      if (!this.IS_GAE)
        break label1422;
    }
    while (true)
    {
      int i83 = 31 * (i82 + i);
      Map localMap = this.requestHeaders;
      int i84 = 0;
      if (localMap != null)
        i84 = this.requestHeaders.hashCode();
      return i83 + i84;
      j = 0;
      break;
      label1170: m = 0;
      break label32;
      label1176: i1 = 0;
      break label57;
      label1182: i3 = 0;
      break label83;
      label1188: i5 = 0;
      break label103;
      label1194: i7 = 0;
      break label123;
      label1200: i9 = 0;
      break label143;
      label1206: i11 = 0;
      break label169;
      label1212: i13 = 0;
      break label195;
      label1218: i15 = 0;
      break label221;
      label1224: i17 = 0;
      break label311;
      label1230: i19 = 0;
      break label337;
      label1236: i21 = 0;
      break label363;
      label1242: i23 = 0;
      break label389;
      label1248: i25 = 0;
      break label415;
      label1254: i27 = 0;
      break label441;
      label1260: i29 = 0;
      break label467;
      label1266: i31 = 0;
      break label493;
      label1272: i33 = 0;
      break label519;
      label1278: i35 = 0;
      break label545;
      label1284: i37 = 0;
      break label571;
      label1290: i39 = 0;
      break label597;
      label1296: i41 = 0;
      break label623;
      label1302: i43 = 0;
      break label649;
      label1308: i45 = 0;
      break label675;
      label1314: i47 = 0;
      break label701;
      label1320: i49 = 0;
      break label727;
      label1326: i51 = 0;
      break label753;
      label1332: i53 = 0;
      break label798;
      label1338: i55 = 0;
      break label818;
      label1344: i57 = 0;
      break label838;
      label1350: i59 = 0;
      break label858;
      label1356: i61 = 0;
      break label878;
      label1362: i63 = 0;
      break label898;
      label1368: i65 = 0;
      break label918;
      label1374: i67 = 0;
      break label938;
      label1380: i69 = 0;
      break label958;
      label1386: i71 = 0;
      break label984;
      label1392: i73 = 0;
      break label1010;
      label1398: i75 = 0;
      break label1036;
      label1404: i77 = 0;
      break label1062;
      label1410: i79 = 0;
      break label1088;
      label1416: i81 = 0;
      break label1108;
      label1422: i = 0;
    }
  }

  public boolean isApplicationOnlyAuthEnabled()
  {
    return this.applicationOnlyAuthEnabled;
  }

  public final boolean isDalvik()
  {
    return this.IS_DALVIK;
  }

  public final boolean isDebugEnabled()
  {
    return this.debug;
  }

  public boolean isGAE()
  {
    return this.IS_GAE;
  }

  public boolean isGZIPEnabled()
  {
    return this.gzipEnabled;
  }

  public boolean isIncludeEntitiesEnabled()
  {
    return this.includeEntitiesEnabled;
  }

  public boolean isIncludeMyRetweetEnabled()
  {
    return this.includeMyRetweetEnabled;
  }

  public boolean isIncludeRTsEnabled()
  {
    return this.includeRTsEnabled;
  }

  public boolean isJSONStoreEnabled()
  {
    return this.jsonStoreEnabled;
  }

  public boolean isMBeanEnabled()
  {
    return this.mbeanEnabled;
  }

  public boolean isPrettyDebugEnabled()
  {
    return this.prettyDebug;
  }

  public boolean isStallWarningsEnabled()
  {
    return this.stallWarningsEnabled;
  }

  public boolean isTrimUserEnabled()
  {
    return this.trimUserEnabled;
  }

  public boolean isUserStreamRepliesAllEnabled()
  {
    return this.userStreamRepliesAllEnabled;
  }

  protected Object readResolve()
    throws ObjectStreamException
  {
    return getInstance(this);
  }

  protected final void setApplicationOnlyAuthEnabled(boolean paramBoolean)
  {
    this.applicationOnlyAuthEnabled = paramBoolean;
  }

  protected final void setAsyncNumThreads(int paramInt)
  {
    this.asyncNumThreads = paramInt;
  }

  protected final void setClientURL(String paramString)
  {
    this.clientURL = paramString;
    initRequestHeaders();
  }

  protected final void setClientVersion(String paramString)
  {
    this.clientVersion = paramString;
    initRequestHeaders();
  }

  protected final void setContributingTo(long paramLong)
  {
    this.contributingTo = paramLong;
  }

  protected final void setDebug(boolean paramBoolean)
  {
    this.debug = paramBoolean;
  }

  protected final void setDispatcherImpl(String paramString)
  {
    this.dispatcherImpl = paramString;
  }

  protected final void setGZIPEnabled(boolean paramBoolean)
  {
    this.gzipEnabled = paramBoolean;
    initRequestHeaders();
  }

  protected final void setHttpConnectionTimeout(int paramInt)
  {
    this.httpConnectionTimeout = paramInt;
  }

  protected final void setHttpDefaultMaxPerRoute(int paramInt)
  {
    this.defaultMaxPerRoute = paramInt;
  }

  protected final void setHttpMaxTotalConnections(int paramInt)
  {
    this.maxTotalConnections = paramInt;
  }

  protected final void setHttpProxyHost(String paramString)
  {
    this.httpProxyHost = paramString;
  }

  protected final void setHttpProxyPassword(String paramString)
  {
    this.httpProxyPassword = paramString;
  }

  protected final void setHttpProxyPort(int paramInt)
  {
    this.httpProxyPort = paramInt;
  }

  protected final void setHttpProxyUser(String paramString)
  {
    this.httpProxyUser = paramString;
  }

  protected final void setHttpReadTimeout(int paramInt)
  {
    this.httpReadTimeout = paramInt;
  }

  protected final void setHttpRetryCount(int paramInt)
  {
    this.httpRetryCount = paramInt;
  }

  protected final void setHttpRetryIntervalSeconds(int paramInt)
  {
    this.httpRetryIntervalSeconds = paramInt;
  }

  protected final void setHttpStreamingReadTimeout(int paramInt)
  {
    this.httpStreamingReadTimeout = paramInt;
  }

  protected final void setIncludeEntitiesEnbled(boolean paramBoolean)
  {
    this.includeEntitiesEnabled = paramBoolean;
  }

  public void setIncludeMyRetweetEnabled(boolean paramBoolean)
  {
    this.includeMyRetweetEnabled = paramBoolean;
  }

  protected final void setIncludeRTsEnbled(boolean paramBoolean)
  {
    this.includeRTsEnabled = paramBoolean;
  }

  protected final void setJSONStoreEnabled(boolean paramBoolean)
  {
    this.jsonStoreEnabled = paramBoolean;
  }

  protected final void setLoggerFactory(String paramString)
  {
    this.loggerFactory = paramString;
  }

  protected final void setMBeanEnabled(boolean paramBoolean)
  {
    this.mbeanEnabled = paramBoolean;
  }

  protected final void setMediaProvider(String paramString)
  {
    this.mediaProvider = paramString;
  }

  protected final void setMediaProviderAPIKey(String paramString)
  {
    this.mediaProviderAPIKey = paramString;
  }

  protected final void setMediaProviderParameters(Properties paramProperties)
  {
    this.mediaProviderParameters = paramProperties;
  }

  protected final void setOAuth2AccessToken(String paramString)
  {
    this.oAuth2AccessToken = paramString;
  }

  protected final void setOAuth2InvalidateTokenURL(String paramString)
  {
    this.oAuth2InvalidateTokenURL = paramString;
    fixRestBaseURL();
  }

  protected final void setOAuth2TokenType(String paramString)
  {
    this.oAuth2TokenType = paramString;
  }

  protected final void setOAuth2TokenURL(String paramString)
  {
    this.oAuth2TokenURL = paramString;
    fixRestBaseURL();
  }

  protected final void setOAuthAccessToken(String paramString)
  {
    this.oAuthAccessToken = paramString;
  }

  protected final void setOAuthAccessTokenSecret(String paramString)
  {
    this.oAuthAccessTokenSecret = paramString;
  }

  protected final void setOAuthAccessTokenURL(String paramString)
  {
    this.oAuthAccessTokenURL = paramString;
    fixRestBaseURL();
  }

  protected final void setOAuthAuthenticationURL(String paramString)
  {
    this.oAuthAuthenticationURL = paramString;
    fixRestBaseURL();
  }

  protected final void setOAuthAuthorizationURL(String paramString)
  {
    this.oAuthAuthorizationURL = paramString;
    fixRestBaseURL();
  }

  protected final void setOAuthConsumerKey(String paramString)
  {
    this.oAuthConsumerKey = paramString;
    fixRestBaseURL();
  }

  protected final void setOAuthConsumerSecret(String paramString)
  {
    this.oAuthConsumerSecret = paramString;
    fixRestBaseURL();
  }

  protected final void setOAuthRequestTokenURL(String paramString)
  {
    this.oAuthRequestTokenURL = paramString;
    fixRestBaseURL();
  }

  protected final void setPassword(String paramString)
  {
    this.password = paramString;
  }

  protected final void setPrettyDebugEnabled(boolean paramBoolean)
  {
    this.prettyDebug = paramBoolean;
  }

  protected final void setRestBaseURL(String paramString)
  {
    this.restBaseURL = paramString;
    fixRestBaseURL();
  }

  protected final void setSiteStreamBaseURL(String paramString)
  {
    this.siteStreamBaseURL = paramString;
  }

  protected final void setStallWarningsEnabled(boolean paramBoolean)
  {
    this.stallWarningsEnabled = paramBoolean;
  }

  protected final void setStreamBaseURL(String paramString)
  {
    this.streamBaseURL = paramString;
  }

  public void setTrimUserEnabled(boolean paramBoolean)
  {
    this.trimUserEnabled = paramBoolean;
  }

  protected final void setUseSSL(boolean paramBoolean)
  {
    this.useSSL = paramBoolean;
    fixRestBaseURL();
  }

  protected final void setUser(String paramString)
  {
    this.user = paramString;
  }

  protected final void setUserAgent(String paramString)
  {
    this.userAgent = paramString;
    initRequestHeaders();
  }

  protected final void setUserStreamBaseURL(String paramString)
  {
    this.userStreamBaseURL = paramString;
  }

  protected final void setUserStreamRepliesAllEnabled(boolean paramBoolean)
  {
    this.userStreamRepliesAllEnabled = paramBoolean;
  }

  public String toString()
  {
    return "ConfigurationBase{debug=" + this.debug + ", userAgent='" + this.userAgent + '\'' + ", user='" + this.user + '\'' + ", password='" + this.password + '\'' + ", useSSL=" + this.useSSL + ", prettyDebug=" + this.prettyDebug + ", gzipEnabled=" + this.gzipEnabled + ", httpProxyHost='" + this.httpProxyHost + '\'' + ", httpProxyUser='" + this.httpProxyUser + '\'' + ", httpProxyPassword='" + this.httpProxyPassword + '\'' + ", httpProxyPort=" + this.httpProxyPort + ", httpConnectionTimeout=" + this.httpConnectionTimeout + ", httpReadTimeout=" + this.httpReadTimeout + ", httpStreamingReadTimeout=" + this.httpStreamingReadTimeout + ", httpRetryCount=" + this.httpRetryCount + ", httpRetryIntervalSeconds=" + this.httpRetryIntervalSeconds + ", maxTotalConnections=" + this.maxTotalConnections + ", defaultMaxPerRoute=" + this.defaultMaxPerRoute + ", oAuthConsumerKey='" + this.oAuthConsumerKey + '\'' + ", oAuthConsumerSecret='" + this.oAuthConsumerSecret + '\'' + ", oAuthAccessToken='" + this.oAuthAccessToken + '\'' + ", oAuthAccessTokenSecret='" + this.oAuthAccessTokenSecret + '\'' + ", oAuth2TokenType='" + this.oAuth2TokenType + '\'' + ", oAuth2AccessToken='" + this.oAuth2AccessToken + '\'' + ", oAuthRequestTokenURL='" + this.oAuthRequestTokenURL + '\'' + ", oAuthAuthorizationURL='" + this.oAuthAuthorizationURL + '\'' + ", oAuthAccessTokenURL='" + this.oAuthAccessTokenURL + '\'' + ", oAuthAuthenticationURL='" + this.oAuthAuthenticationURL + '\'' + ", oAuth2TokenURL='" + this.oAuth2TokenURL + '\'' + ", oAuth2InvalidateTokenURL='" + this.oAuth2InvalidateTokenURL + '\'' + ", restBaseURL='" + this.restBaseURL + '\'' + ", streamBaseURL='" + this.streamBaseURL + '\'' + ", userStreamBaseURL='" + this.userStreamBaseURL + '\'' + ", siteStreamBaseURL='" + this.siteStreamBaseURL + '\'' + ", dispatcherImpl='" + this.dispatcherImpl + '\'' + ", loggerFactory='" + this.loggerFactory + '\'' + ", asyncNumThreads=" + this.asyncNumThreads + ", contributingTo=" + this.contributingTo + ", includeRTsEnabled=" + this.includeRTsEnabled + ", includeEntitiesEnabled=" + this.includeEntitiesEnabled + ", includeMyRetweetEnabled=" + this.includeMyRetweetEnabled + ", trimUserEnabled=" + this.trimUserEnabled + ", jsonStoreEnabled=" + this.jsonStoreEnabled + ", mbeanEnabled=" + this.mbeanEnabled + ", userStreamRepliesAllEnabled=" + this.userStreamRepliesAllEnabled + ", stallWarningsEnabled=" + this.stallWarningsEnabled + ", applicationOnlyAuthEnabled=" + this.applicationOnlyAuthEnabled + ", mediaProvider='" + this.mediaProvider + '\'' + ", mediaProviderAPIKey='" + this.mediaProviderAPIKey + '\'' + ", mediaProviderParameters=" + this.mediaProviderParameters + ", clientVersion='" + this.clientVersion + '\'' + ", clientURL='" + this.clientURL + '\'' + ", IS_DALVIK=" + this.IS_DALVIK + ", IS_GAE=" + this.IS_GAE + ", requestHeaders=" + this.requestHeaders + '}';
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.conf.ConfigurationBase
 * JD-Core Version:    0.6.0
 */