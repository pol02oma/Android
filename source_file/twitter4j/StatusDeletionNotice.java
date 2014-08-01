package twitter4j;

import java.io.Serializable;

public abstract interface StatusDeletionNotice extends Comparable<StatusDeletionNotice>, Serializable
{
  public abstract long getStatusId();

  public abstract long getUserId();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.StatusDeletionNotice
 * JD-Core Version:    0.6.0
 */