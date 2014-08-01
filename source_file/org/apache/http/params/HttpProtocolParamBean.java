package org.apache.http.params;

import org.apache.http.HttpVersion;

public class HttpProtocolParamBean extends HttpAbstractParamBean
{
  public HttpProtocolParamBean(HttpParams paramHttpParams)
  {
    super(paramHttpParams);
  }

  public void setContentCharset(String paramString)
  {
    HttpProtocolParams.setContentCharset(this.params, paramString);
  }

  public void setHttpElementCharset(String paramString)
  {
    HttpProtocolParams.setHttpElementCharset(this.params, paramString);
  }

  public void setUseExpectContinue(boolean paramBoolean)
  {
    HttpProtocolParams.setUseExpectContinue(this.params, paramBoolean);
  }

  public void setUserAgent(String paramString)
  {
    HttpProtocolParams.setUserAgent(this.params, paramString);
  }

  public void setVersion(HttpVersion paramHttpVersion)
  {
    HttpProtocolParams.setVersion(this.params, paramHttpVersion);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.params.HttpProtocolParamBean
 * JD-Core Version:    0.6.0
 */