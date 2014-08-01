package twitter4j.conf;

public abstract interface ConfigurationFactory
{
  public abstract void dispose();

  public abstract Configuration getInstance();

  public abstract Configuration getInstance(String paramString);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.conf.ConfigurationFactory
 * JD-Core Version:    0.6.0
 */