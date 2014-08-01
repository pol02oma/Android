package org.apache.http.io;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;

public abstract interface HttpMessageParser<T extends HttpMessage>
{
  public abstract T parse()
    throws IOException, HttpException;
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.io.HttpMessageParser
 * JD-Core Version:    0.6.0
 */