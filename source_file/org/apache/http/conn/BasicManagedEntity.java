package org.apache.http.conn;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import org.apache.http.HttpEntity;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.util.EntityUtils;

@NotThreadSafe
public class BasicManagedEntity extends HttpEntityWrapper
  implements ConnectionReleaseTrigger, EofSensorWatcher
{
  protected final boolean attemptReuse;
  protected ManagedClientConnection managedConn;

  public BasicManagedEntity(HttpEntity paramHttpEntity, ManagedClientConnection paramManagedClientConnection, boolean paramBoolean)
  {
    super(paramHttpEntity);
    if (paramManagedClientConnection == null)
      throw new IllegalArgumentException("Connection may not be null.");
    this.managedConn = paramManagedClientConnection;
    this.attemptReuse = paramBoolean;
  }

  private void ensureConsumed()
    throws IOException
  {
    if (this.managedConn == null)
      return;
    try
    {
      if (this.attemptReuse)
      {
        EntityUtils.consume(this.wrappedEntity);
        this.managedConn.markReusable();
      }
      return;
    }
    finally
    {
      releaseManagedConnection();
    }
    throw localObject;
  }

  public void abortConnection()
    throws IOException
  {
    if (this.managedConn != null);
    try
    {
      this.managedConn.abortConnection();
      return;
    }
    finally
    {
      this.managedConn = null;
    }
    throw localObject;
  }

  public void consumeContent()
    throws IOException
  {
    ensureConsumed();
  }

  public boolean eofDetected(InputStream paramInputStream)
    throws IOException
  {
    try
    {
      if ((this.attemptReuse) && (this.managedConn != null))
      {
        paramInputStream.close();
        this.managedConn.markReusable();
      }
      return false;
    }
    finally
    {
      releaseManagedConnection();
    }
    throw localObject;
  }

  public InputStream getContent()
    throws IOException
  {
    return new EofSensorInputStream(this.wrappedEntity.getContent(), this);
  }

  public boolean isRepeatable()
  {
    return false;
  }

  public void releaseConnection()
    throws IOException
  {
    ensureConsumed();
  }

  protected void releaseManagedConnection()
    throws IOException
  {
    if (this.managedConn != null);
    try
    {
      this.managedConn.releaseConnection();
      return;
    }
    finally
    {
      this.managedConn = null;
    }
    throw localObject;
  }

  public boolean streamAbort(InputStream paramInputStream)
    throws IOException
  {
    if (this.managedConn != null)
      this.managedConn.abortConnection();
    return false;
  }

  public boolean streamClosed(InputStream paramInputStream)
    throws IOException
  {
    try
    {
      boolean bool;
      if ((this.attemptReuse) && (this.managedConn != null))
        bool = this.managedConn.isOpen();
      try
      {
        paramInputStream.close();
        this.managedConn.markReusable();
        releaseManagedConnection();
        return false;
      }
      catch (SocketException localSocketException)
      {
        while (!bool);
        throw localSocketException;
      }
    }
    finally
    {
      releaseManagedConnection();
    }
    throw localObject;
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    super.writeTo(paramOutputStream);
    ensureConsumed();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.conn.BasicManagedEntity
 * JD-Core Version:    0.6.0
 */