package org.apache.james.mime4j.stream;

import org.apache.james.mime4j.MimeException;

public class MimeParseEventException extends MimeException
{
  private static final long serialVersionUID = 4632991604246852302L;
  private final Event event;

  public MimeParseEventException(Event paramEvent)
  {
    super(paramEvent.toString());
    this.event = paramEvent;
  }

  public Event getEvent()
  {
    return this.event;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.stream.MimeParseEventException
 * JD-Core Version:    0.6.0
 */