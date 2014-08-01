package org.apache.http.impl.conn;

import java.io.IOException;
import java.io.InterruptedIOException;
import org.apache.http.HttpHost;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.RouteTracker;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

@Deprecated
public abstract class AbstractPoolEntry
{
  protected final ClientConnectionOperator connOperator;
  protected final OperatedClientConnection connection;
  protected volatile HttpRoute route;
  protected volatile Object state;
  protected volatile RouteTracker tracker;

  protected AbstractPoolEntry(ClientConnectionOperator paramClientConnectionOperator, HttpRoute paramHttpRoute)
  {
    if (paramClientConnectionOperator == null)
      throw new IllegalArgumentException("Connection operator may not be null");
    this.connOperator = paramClientConnectionOperator;
    this.connection = paramClientConnectionOperator.createConnection();
    this.route = paramHttpRoute;
    this.tracker = null;
  }

  public Object getState()
  {
    return this.state;
  }

  public void layerProtocol(HttpContext paramHttpContext, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters must not be null.");
    if ((this.tracker == null) || (!this.tracker.isConnected()))
      throw new IllegalStateException("Connection not open.");
    if (!this.tracker.isTunnelled())
      throw new IllegalStateException("Protocol layering without a tunnel not supported.");
    if (this.tracker.isLayered())
      throw new IllegalStateException("Multiple protocol layering not supported.");
    HttpHost localHttpHost = this.tracker.getTargetHost();
    this.connOperator.updateSecureConnection(this.connection, localHttpHost, paramHttpContext, paramHttpParams);
    this.tracker.layerProtocol(this.connection.isSecure());
  }

  public void open(HttpRoute paramHttpRoute, HttpContext paramHttpContext, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramHttpRoute == null)
      throw new IllegalArgumentException("Route must not be null.");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters must not be null.");
    if ((this.tracker != null) && (this.tracker.isConnected()))
      throw new IllegalStateException("Connection already open.");
    this.tracker = new RouteTracker(paramHttpRoute);
    HttpHost localHttpHost1 = paramHttpRoute.getProxyHost();
    ClientConnectionOperator localClientConnectionOperator = this.connOperator;
    OperatedClientConnection localOperatedClientConnection = this.connection;
    if (localHttpHost1 != null);
    RouteTracker localRouteTracker;
    for (HttpHost localHttpHost2 = localHttpHost1; ; localHttpHost2 = paramHttpRoute.getTargetHost())
    {
      localClientConnectionOperator.openConnection(localOperatedClientConnection, localHttpHost2, paramHttpRoute.getLocalAddress(), paramHttpContext, paramHttpParams);
      localRouteTracker = this.tracker;
      if (localRouteTracker != null)
        break;
      throw new InterruptedIOException("Request aborted");
    }
    if (localHttpHost1 == null)
    {
      localRouteTracker.connectTarget(this.connection.isSecure());
      return;
    }
    localRouteTracker.connectProxy(localHttpHost1, this.connection.isSecure());
  }

  public void setState(Object paramObject)
  {
    this.state = paramObject;
  }

  protected void shutdownEntry()
  {
    this.tracker = null;
    this.state = null;
  }

  public void tunnelProxy(HttpHost paramHttpHost, boolean paramBoolean, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Next proxy must not be null.");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters must not be null.");
    if ((this.tracker == null) || (!this.tracker.isConnected()))
      throw new IllegalStateException("Connection not open.");
    this.connection.update(null, paramHttpHost, paramBoolean, paramHttpParams);
    this.tracker.tunnelProxy(paramHttpHost, paramBoolean);
  }

  public void tunnelTarget(boolean paramBoolean, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters must not be null.");
    if ((this.tracker == null) || (!this.tracker.isConnected()))
      throw new IllegalStateException("Connection not open.");
    if (this.tracker.isTunnelled())
      throw new IllegalStateException("Connection is already tunnelled.");
    this.connection.update(null, this.tracker.getTargetHost(), paramBoolean, paramHttpParams);
    this.tracker.tunnelTarget(paramBoolean);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.conn.AbstractPoolEntry
 * JD-Core Version:    0.6.0
 */