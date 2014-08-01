package twitter4j;

public abstract interface PagableResponseList<T extends TwitterResponse> extends ResponseList<T>, CursorSupport
{
  public abstract long getNextCursor();

  public abstract long getPreviousCursor();

  public abstract boolean hasNext();

  public abstract boolean hasPrevious();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.PagableResponseList
 * JD-Core Version:    0.6.0
 */