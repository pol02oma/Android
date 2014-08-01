package org.apache.james.mime4j.stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class RawBody
{
  private final List<NameValuePair> params;
  private final String value;

  RawBody(String paramString, List<NameValuePair> paramList)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Field value not be null");
    this.value = paramString;
    if (paramList != null);
    while (true)
    {
      this.params = paramList;
      return;
      paramList = new ArrayList();
    }
  }

  public List<NameValuePair> getParams()
  {
    return new ArrayList(this.params);
  }

  public String getValue()
  {
    return this.value;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.value);
    localStringBuilder.append("; ");
    Iterator localIterator = this.params.iterator();
    while (localIterator.hasNext())
    {
      NameValuePair localNameValuePair = (NameValuePair)localIterator.next();
      localStringBuilder.append("; ");
      localStringBuilder.append(localNameValuePair);
    }
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.stream.RawBody
 * JD-Core Version:    0.6.0
 */