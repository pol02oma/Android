package org.apache.http.message;

import java.io.Serializable;
import org.apache.http.NameValuePair;
import org.apache.http.annotation.Immutable;
import org.apache.http.util.LangUtils;

@Immutable
public class BasicNameValuePair
  implements NameValuePair, Cloneable, Serializable
{
  private static final long serialVersionUID = -6437800749411518984L;
  private final String name;
  private final String value;

  public BasicNameValuePair(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Name may not be null");
    this.name = paramString1;
    this.value = paramString2;
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    while (true)
    {
      return true;
      if (!(paramObject instanceof Serializable))
        break;
      BasicNameValuePair localBasicNameValuePair = (BasicNameValuePair)paramObject;
      if ((!this.name.equals(localBasicNameValuePair.name)) || (!LangUtils.equals(this.value, localBasicNameValuePair.value)))
        return false;
    }
    return false;
  }

  public String getName()
  {
    return this.name;
  }

  public String getValue()
  {
    return this.value;
  }

  public int hashCode()
  {
    return LangUtils.hashCode(LangUtils.hashCode(17, this.name), this.value);
  }

  public String toString()
  {
    if (this.value == null)
      return this.name;
    StringBuilder localStringBuilder = new StringBuilder(1 + this.name.length() + this.value.length());
    localStringBuilder.append(this.name);
    localStringBuilder.append("=");
    localStringBuilder.append(this.value);
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.message.BasicNameValuePair
 * JD-Core Version:    0.6.0
 */