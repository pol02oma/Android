package twitter4j;

public abstract interface EntitySupport
{
  public abstract HashtagEntity[] getHashtagEntities();

  public abstract MediaEntity[] getMediaEntities();

  public abstract SymbolEntity[] getSymbolEntities();

  public abstract URLEntity[] getURLEntities();

  public abstract UserMentionEntity[] getUserMentionEntities();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.EntitySupport
 * JD-Core Version:    0.6.0
 */