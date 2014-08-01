package org.apache.james.mime4j.stream;

public final class MimeConfig
  implements Cloneable
{
  private boolean countLineNumbers = false;
  private String headlessParsing = null;
  private boolean malformedHeaderStartsBody = false;
  private long maxContentLen = -1L;
  private int maxHeaderCount = 1000;
  private int maxHeaderLen = 10000;
  private int maxLineLen = 1000;
  private boolean strictParsing = false;

  public MimeConfig clone()
  {
    try
    {
      MimeConfig localMimeConfig = (MimeConfig)super.clone();
      return localMimeConfig;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
    }
    throw new InternalError();
  }

  public String getHeadlessParsing()
  {
    return this.headlessParsing;
  }

  public long getMaxContentLen()
  {
    return this.maxContentLen;
  }

  public int getMaxHeaderCount()
  {
    return this.maxHeaderCount;
  }

  public int getMaxHeaderLen()
  {
    return this.maxHeaderLen;
  }

  public int getMaxLineLen()
  {
    return this.maxLineLen;
  }

  public boolean isCountLineNumbers()
  {
    return this.countLineNumbers;
  }

  public boolean isMalformedHeaderStartsBody()
  {
    return this.malformedHeaderStartsBody;
  }

  public boolean isStrictParsing()
  {
    return this.strictParsing;
  }

  public void setCountLineNumbers(boolean paramBoolean)
  {
    this.countLineNumbers = paramBoolean;
  }

  public void setHeadlessParsing(String paramString)
  {
    this.headlessParsing = paramString;
  }

  public void setMalformedHeaderStartsBody(boolean paramBoolean)
  {
    this.malformedHeaderStartsBody = paramBoolean;
  }

  public void setMaxContentLen(long paramLong)
  {
    this.maxContentLen = paramLong;
  }

  public void setMaxHeaderCount(int paramInt)
  {
    this.maxHeaderCount = paramInt;
  }

  public void setMaxHeaderLen(int paramInt)
  {
    this.maxHeaderLen = paramInt;
  }

  public void setMaxLineLen(int paramInt)
  {
    this.maxLineLen = paramInt;
  }

  public void setStrictParsing(boolean paramBoolean)
  {
    this.strictParsing = paramBoolean;
  }

  public String toString()
  {
    return "[strict parsing: " + this.strictParsing + ", max line length: " + this.maxLineLen + ", max header count: " + this.maxHeaderCount + ", max content length: " + this.maxContentLen + ", count line numbers: " + this.countLineNumbers + "]";
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.stream.MimeConfig
 * JD-Core Version:    0.6.0
 */