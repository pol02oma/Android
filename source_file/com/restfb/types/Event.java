package com.restfb.types;

import com.restfb.Facebook;
import com.restfb.util.DateUtils;
import java.util.Date;

public class Event extends NamedFacebookType
{
  private static final long serialVersionUID = 1L;

  @Facebook
  private String description;

  @Facebook("end_time")
  private String endTime;

  @Facebook
  private String location;

  @Facebook
  private NamedFacebookType owner;

  @Facebook
  private String privacy;

  @Facebook("rsvp_status")
  private String rsvpStatus;

  @Facebook("start_time")
  private String startTime;

  @Facebook("updated_time")
  private String updatedTime;

  @Facebook
  private Venue venue;

  public String getDescription()
  {
    return this.description;
  }

  public Date getEndTime()
  {
    return DateUtils.toDateFromLongFormat(this.endTime);
  }

  public String getLocation()
  {
    return this.location;
  }

  public NamedFacebookType getOwner()
  {
    return this.owner;
  }

  public String getPrivacy()
  {
    return this.privacy;
  }

  public String getRsvpStatus()
  {
    return this.rsvpStatus;
  }

  public Date getStartTime()
  {
    return DateUtils.toDateFromLongFormat(this.startTime);
  }

  public Date getUpdatedTime()
  {
    return DateUtils.toDateFromLongFormat(this.updatedTime);
  }

  public Venue getVenue()
  {
    return this.venue;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.types.Event
 * JD-Core Version:    0.6.0
 */