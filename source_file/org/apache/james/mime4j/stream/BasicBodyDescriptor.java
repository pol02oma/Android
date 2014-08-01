package org.apache.james.mime4j.stream;

class BasicBodyDescriptor
  implements BodyDescriptor
{
  private final String boundary;
  private final String charset;
  private final long contentLength;
  private final String mediaType;
  private final String mimeType;
  private final String subType;
  private final String transferEncoding;

  BasicBodyDescriptor(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, long paramLong)
  {
    this.mimeType = paramString1;
    this.mediaType = paramString2;
    this.subType = paramString3;
    this.boundary = paramString4;
    this.charset = paramString5;
    this.transferEncoding = paramString6;
    this.contentLength = paramLong;
  }

  public String getBoundary()
  {
    return this.boundary;
  }

  public String getCharset()
  {
    return this.charset;
  }

  public long getContentLength()
  {
    return this.contentLength;
  }

  public String getMediaType()
  {
    return this.mediaType;
  }

  public String getMimeType()
  {
    return this.mimeType;
  }

  public String getSubType()
  {
    return this.subType;
  }

  public String getTransferEncoding()
  {
    return this.transferEncoding;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[mimeType=");
    localStringBuilder.append(this.mimeType);
    localStringBuilder.append(", mediaType=");
    localStringBuilder.append(this.mediaType);
    localStringBuilder.append(", subType=");
    localStringBuilder.append(this.subType);
    localStringBuilder.append(", boundary=");
    localStringBuilder.append(this.boundary);
    localStringBuilder.append(", charset=");
    localStringBuilder.append(this.charset);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.stream.BasicBodyDescriptor
 * JD-Core Version:    0.6.0
 */