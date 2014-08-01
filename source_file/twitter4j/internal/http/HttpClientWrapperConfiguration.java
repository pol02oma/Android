package twitter4j.internal.http;

import java.util.Map;

public abstract interface HttpClientWrapperConfiguration extends HttpClientConfiguration
{
  public abstract Map<String, String> getRequestHeaders();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.internal.http.HttpClientWrapperConfiguration
 * JD-Core Version:    0.6.0
 */