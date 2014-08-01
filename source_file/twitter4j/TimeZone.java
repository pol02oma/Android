package twitter4j;

import java.io.Serializable;

public abstract interface TimeZone extends Serializable
{
  public abstract String getName();

  public abstract String tzinfoName();

  public abstract int utcOffset();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.TimeZone
 * JD-Core Version:    0.6.0
 */