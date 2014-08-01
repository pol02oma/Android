package com.restfb;

import com.restfb.util.ReflectionUtils;
import com.restfb.util.StringUtils;
import java.io.InputStream;

public class BinaryAttachment
{
  private InputStream data;
  private String filename;

  protected BinaryAttachment(String paramString, InputStream paramInputStream)
  {
    if (StringUtils.isBlank(paramString))
      throw new IllegalArgumentException("Binary attachment filename cannot be blank.");
    if (paramInputStream == null)
      throw new IllegalArgumentException("Binary attachment data cannot be null.");
    this.filename = paramString;
    this.data = paramInputStream;
  }

  public static BinaryAttachment with(String paramString, InputStream paramInputStream)
  {
    return new BinaryAttachment(paramString, paramInputStream);
  }

  public boolean equals(Object paramObject)
  {
    return ReflectionUtils.equals(this, paramObject);
  }

  public InputStream getData()
  {
    return this.data;
  }

  public String getFilename()
  {
    return this.filename;
  }

  public int hashCode()
  {
    return ReflectionUtils.hashCode(this);
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = getFilename();
    return String.format("[filename=%s]", arrayOfObject);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.BinaryAttachment
 * JD-Core Version:    0.6.0
 */