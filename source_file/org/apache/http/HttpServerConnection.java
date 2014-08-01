package org.apache.http;

import java.io.IOException;

public abstract interface HttpServerConnection extends HttpConnection
{
  public abstract void flush()
    throws IOException;

  public abstract void receiveRequestEntity(HttpEntityEnclosingRequest paramHttpEntityEnclosingRequest)
    throws HttpException, IOException;

  public abstract HttpRequest receiveRequestHeader()
    throws HttpException, IOException;

  public abstract void sendResponseEntity(HttpResponse paramHttpResponse)
    throws HttpException, IOException;

  public abstract void sendResponseHeader(HttpResponse paramHttpResponse)
    throws HttpException, IOException;
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.HttpServerConnection
 * JD-Core Version:    0.6.0
 */