package org.apache.http.impl.io;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.io.EofSensor;
import org.apache.http.params.HttpParams;

@NotThreadSafe
public class SocketInputBuffer extends AbstractSessionInputBuffer
  implements EofSensor
{
  private boolean eof;
  private final Socket socket;

  public SocketInputBuffer(Socket paramSocket, int paramInt, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramSocket == null)
      throw new IllegalArgumentException("Socket may not be null");
    this.socket = paramSocket;
    this.eof = false;
    if (paramInt < 0)
      paramInt = paramSocket.getReceiveBufferSize();
    if (paramInt < 1024)
      paramInt = 1024;
    init(paramSocket.getInputStream(), paramInt, paramHttpParams);
  }

  protected int fillBuffer()
    throws IOException
  {
    int i = super.fillBuffer();
    if (i == -1);
    for (boolean bool = true; ; bool = false)
    {
      this.eof = bool;
      return i;
    }
  }

  public boolean isDataAvailable(int paramInt)
    throws IOException
  {
    boolean bool1 = hasBufferedData();
    int i;
    if (!bool1)
      i = this.socket.getSoTimeout();
    try
    {
      this.socket.setSoTimeout(paramInt);
      fillBuffer();
      boolean bool2 = hasBufferedData();
      bool1 = bool2;
      return bool1;
    }
    catch (SocketTimeoutException localSocketTimeoutException)
    {
      throw localSocketTimeoutException;
    }
    finally
    {
      this.socket.setSoTimeout(i);
    }
    throw localObject;
  }

  public boolean isEof()
  {
    return this.eof;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.io.SocketInputBuffer
 * JD-Core Version:    0.6.0
 */