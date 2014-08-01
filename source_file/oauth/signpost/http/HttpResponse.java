package oauth.signpost.http;

import java.io.IOException;
import java.io.InputStream;

public abstract interface HttpResponse
{
  public abstract InputStream getContent()
    throws IOException;

  public abstract String getReasonPhrase()
    throws Exception;

  public abstract int getStatusCode()
    throws IOException;

  public abstract Object unwrap();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     oauth.signpost.http.HttpResponse
 * JD-Core Version:    0.6.0
 */