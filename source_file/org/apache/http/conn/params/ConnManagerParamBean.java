package org.apache.http.conn.params;

import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.params.HttpAbstractParamBean;
import org.apache.http.params.HttpParams;

@Deprecated
@NotThreadSafe
public class ConnManagerParamBean extends HttpAbstractParamBean
{
  public ConnManagerParamBean(HttpParams paramHttpParams)
  {
    super(paramHttpParams);
  }

  public void setConnectionsPerRoute(ConnPerRouteBean paramConnPerRouteBean)
  {
    this.params.setParameter("http.conn-manager.max-per-route", paramConnPerRouteBean);
  }

  public void setMaxTotalConnections(int paramInt)
  {
    this.params.setIntParameter("http.conn-manager.max-total", paramInt);
  }

  public void setTimeout(long paramLong)
  {
    this.params.setLongParameter("http.conn-manager.timeout", paramLong);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.conn.params.ConnManagerParamBean
 * JD-Core Version:    0.6.0
 */