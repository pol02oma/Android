package oauth.signpost.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public abstract interface HttpRequest
{
  public abstract Map<String, String> getAllHeaders();

  public abstract String getContentType();

  public abstract String getHeader(String paramString);

  public abstract InputStream getMessagePayload()
    throws IOException;

  public abstract String getMethod();

  public abstract String getRequestUrl();

  public abstract void setHeader(String paramString1, String paramString2);

  public abstract void setRequestUrl(String paramString);

  public abstract Object unwrap();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     oauth.signpost.http.HttpRequest
 * JD-Core Version:    0.6.0
 */