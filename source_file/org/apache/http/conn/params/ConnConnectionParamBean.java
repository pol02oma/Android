package org.apache.http.conn.params;

import org.apache.http.params.HttpAbstractParamBean;
import org.apache.http.params.HttpParams;

@Deprecated
public class ConnConnectionParamBean extends HttpAbstractParamBean
{
  public ConnConnectionParamBean(HttpParams paramHttpParams)
  {
    super(paramHttpParams);
  }

  public void setMaxStatusLineGarbage(int paramInt)
  {
    this.params.setIntParameter("http.connection.max-status-line-garbage", paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.conn.params.ConnConnectionParamBean
 * JD-Core Version:    0.6.0
 */