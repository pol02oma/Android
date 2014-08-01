package org.apache.james.mime4j.stream;

import org.apache.james.mime4j.util.LangUtils;

public final class NameValuePair
{
  private final String name;
  private final String value;

  public NameValuePair(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Name may not be null");
    this.name = paramString1;
    this.value = paramString2;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    while (true)
    {
      return true;
      if (!(paramObject instanceof NameValuePair))
        break;
      NameValuePair localNameValuePair = (NameValuePair)paramObject;
      if ((!this.name.equals(localNameValuePair.name)) || (!LangUtils.equals(this.value, localNameValuePair.value)))
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
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.name);
    localStringBuilder.append("=");
    localStringBuilder.append("\"");
    localStringBuilder.append(this.value);
    localStringBuilder.append("\"");
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.stream.NameValuePair
 * JD-Core Version:    0.6.0
 */