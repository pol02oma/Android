package org.apache.james.mime4j.dom;

public abstract interface Entity extends Disposable
{
  public abstract Body getBody();

  public abstract String getCharset();

  public abstract String getContentTransferEncoding();

  public abstract String getDispositionType();

  public abstract String getFilename();

  public abstract Header getHeader();

  public abstract String getMimeType();

  public abstract Entity getParent();

  public abstract boolean isMultipart();

  public abstract Body removeBody();

  public abstract void setBody(Body paramBody);

  public abstract void setHeader(Header paramHeader);

  public abstract void setParent(Entity paramEntity);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.dom.Entity
 * JD-Core Version:    0.6.0
 */