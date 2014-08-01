package com.restfb.batch;

import com.restfb.Facebook;
import com.restfb.util.ReflectionUtils;

public class BatchHeader
{

  @Facebook
  private String name;

  @Facebook
  private String value;

  protected BatchHeader()
  {
  }

  public BatchHeader(String paramString1, String paramString2)
  {
    this.name = paramString1;
    this.value = paramString2;
  }

  public boolean equals(Object paramObject)
  {
    return ReflectionUtils.equals(this, paramObject);
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
    return ReflectionUtils.hashCode(this);
  }

  public String toString()
  {
    return ReflectionUtils.toString(this);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.batch.BatchHeader
 * JD-Core Version:    0.6.0
 */