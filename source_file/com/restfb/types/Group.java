package com.restfb.types;

import com.restfb.Facebook;
import com.restfb.util.DateUtils;
import java.util.Date;

public class Group extends NamedFacebookType
{
  private static final long serialVersionUID = 1L;

  @Facebook
  private String description;

  @Facebook
  private String link;

  @Facebook
  private NamedFacebookType owner;

  @Facebook
  private String privacy;

  @Facebook("updated_time")
  private String updatedTime;

  @Facebook
  private Venue venue;

  public String getDescription()
  {
    return this.description;
  }

  public String getLink()
  {
    return this.link;
  }

  public NamedFacebookType getOwner()
  {
    return this.owner;
  }

  public String getPrivacy()
  {
    return this.privacy;
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
 * Qualified Name:     com.restfb.types.Group
 * JD-Core Version:    0.6.0
 */