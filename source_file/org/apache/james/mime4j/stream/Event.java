package org.apache.james.mime4j.stream;

public final class Event
{
  public static final Event HEADERS_PREMATURE_END;
  public static final Event INVALID_HEADER;
  public static final Event MIME_BODY_PREMATURE_END = new Event("Body part ended prematurely. Boundary detected in header or EOF reached.");
  public static final Event OBSOLETE_HEADER;
  private final String code;

  static
  {
    HEADERS_PREMATURE_END = new Event("Unexpected end of headers detected. Higher level boundary detected or EOF reached.");
    INVALID_HEADER = new Event("Invalid header encountered");
    OBSOLETE_HEADER = new Event("Obsolete header encountered");
  }

  public Event(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Code may not be null");
    this.code = paramString;
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    do
    {
      return false;
      if (this == paramObject)
        return true;
    }
    while (!(paramObject instanceof Event));
    Event localEvent = (Event)paramObject;
    return this.code.equals(localEvent.code);
  }

  public int hashCode()
  {
    return this.code.hashCode();
  }

  public String toString()
  {
    return this.code;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.stream.Event
 * JD-Core Version:    0.6.0
 */