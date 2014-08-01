package org.apache.http.impl;

import java.io.IOException;
import java.net.Socket;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

@NotThreadSafe
public class DefaultHttpServerConnection extends SocketHttpServerConnection
{
  public void bind(Socket paramSocket, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramSocket == null)
      throw new IllegalArgumentException("Socket may not be null");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    assertNotOpen();
    paramSocket.setTcpNoDelay(HttpConnectionParams.getTcpNoDelay(paramHttpParams));
    paramSocket.setSoTimeout(HttpConnectionParams.getSoTimeout(paramHttpParams));
    paramSocket.setKeepAlive(HttpConnectionParams.getSoKeepalive(paramHttpParams));
    int i = HttpConnectionParams.getLinger(paramHttpParams);
    if (i >= 0)
      if (i <= 0)
        break label86;
    label86: for (boolean bool = true; ; bool = false)
    {
      paramSocket.setSoLinger(bool, i);
      super.bind(paramSocket, paramHttpParams);
      return;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.DefaultHttpServerConnection
 * JD-Core Version:    0.6.0
 */