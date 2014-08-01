package org.apache.james.mime4j.dom;

public abstract interface Body extends Disposable
{
  public abstract Entity getParent();

  public abstract void setParent(Entity paramEntity);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.dom.Body
 * JD-Core Version:    0.6.0
 */