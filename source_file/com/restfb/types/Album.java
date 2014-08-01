package com.restfb.types;

import com.restfb.Facebook;
import com.restfb.util.DateUtils;
import java.util.Date;

public class Album extends NamedFacebookType
{
  private static final long serialVersionUID = 1L;

  @Facebook
  private Long count;

  @Facebook("cover_photo")
  private String coverPhoto;

  @Facebook("created_time")
  private String createdTime;

  @Facebook
  private String description;

  @Facebook
  private CategorizedFacebookType from;

  @Facebook
  private String link;

  @Facebook
  private String location;

  @Facebook
  private String privacy;

  @Facebook("updated_time")
  private String updatedTime;

  public Long getCount()
  {
    return this.count;
  }

  public String getCoverPhoto()
  {
    return this.coverPhoto;
  }

  public Date getCreatedTime()
  {
    return DateUtils.toDateFromLongFormat(this.createdTime);
  }

  public String getDescription()
  {
    return this.description;
  }

  public CategorizedFacebookType getFrom()
  {
    return this.from;
  }

  public String getLink()
  {
    return this.link;
  }

  public String getLocation()
  {
    return this.location;
  }

  public String getPrivacy()
  {
    return this.privacy;
  }

  public Date getUpdatedTime()
  {
    return DateUtils.toDateFromLongFormat(this.updatedTime);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.types.Album
 * JD-Core Version:    0.6.0
 */