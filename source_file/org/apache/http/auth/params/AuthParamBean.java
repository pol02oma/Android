package org.apache.http.auth.params;

import org.apache.http.params.HttpAbstractParamBean;
import org.apache.http.params.HttpParams;

public class AuthParamBean extends HttpAbstractParamBean
{
  public AuthParamBean(HttpParams paramHttpParams)
  {
    super(paramHttpParams);
  }

  public void setCredentialCharset(String paramString)
  {
    AuthParams.setCredentialCharset(this.params, paramString);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.auth.params.AuthParamBean
 * JD-Core Version:    0.6.0
 */