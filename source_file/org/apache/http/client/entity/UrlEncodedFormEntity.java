package org.apache.http.client.entity;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.entity.StringEntity;

@NotThreadSafe
public class UrlEncodedFormEntity extends StringEntity
{
  public UrlEncodedFormEntity(Iterable<? extends NameValuePair> paramIterable)
  {
    this(paramIterable, null);
  }

  public UrlEncodedFormEntity(Iterable<? extends NameValuePair> paramIterable, Charset paramCharset)
  {
  }

  public UrlEncodedFormEntity(List<? extends NameValuePair> paramList)
    throws UnsupportedEncodingException
  {
    this(paramList, (Charset)null);
  }

  public UrlEncodedFormEntity(List<? extends NameValuePair> paramList, String paramString)
    throws UnsupportedEncodingException
  {
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.client.entity.UrlEncodedFormEntity
 * JD-Core Version:    0.6.0
 */