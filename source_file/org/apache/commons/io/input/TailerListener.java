package org.apache.commons.io.input;

public abstract interface TailerListener
{
  public abstract void fileNotFound();

  public abstract void fileRotated();

  public abstract void handle(Exception paramException);

  public abstract void handle(String paramString);

  public abstract void init(Tailer paramTailer);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.input.TailerListener
 * JD-Core Version:    0.6.0
 */