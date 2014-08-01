package org.apache.james.mime4j.dom.field;

public abstract interface MimeVersionField extends ParsedField
{
  public abstract int getMajorVersion();

  public abstract int getMinorVersion();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.dom.field.MimeVersionField
 * JD-Core Version:    0.6.0
 */