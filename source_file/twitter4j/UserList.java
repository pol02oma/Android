package twitter4j;

import java.io.Serializable;
import java.net.URI;

public abstract interface UserList extends Comparable<UserList>, TwitterResponse, Serializable
{
  public abstract String getDescription();

  public abstract String getFullName();

  public abstract int getId();

  public abstract int getMemberCount();

  public abstract String getName();

  public abstract String getSlug();

  public abstract int getSubscriberCount();

  public abstract URI getURI();

  public abstract User getUser();

  public abstract boolean isFollowing();

  public abstract boolean isPublic();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.UserList
 * JD-Core Version:    0.6.0
 */