package twitter4j;

public abstract interface ConnectionLifeCycleListener
{
  public abstract void onCleanUp();

  public abstract void onConnect();

  public abstract void onDisconnect();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.ConnectionLifeCycleListener
 * JD-Core Version:    0.6.0
 */