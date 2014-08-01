package twitter4j;

import java.io.Serializable;

public abstract interface HashtagEntity extends TweetEntity, Serializable
{
  public abstract int getEnd();

  public abstract int getStart();

  public abstract String getText();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.HashtagEntity
 * JD-Core Version:    0.6.0
 */