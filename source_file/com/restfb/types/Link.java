package com.restfb.types;

import com.restfb.Facebook;
import com.restfb.util.DateUtils;
import java.util.Date;

public class Link extends NamedFacebookType
{
  private static final long serialVersionUID = 1L;

  @Facebook("created_time")
  private String createdTime;

  @Facebook
  private String description;

  @Facebook
  private NamedFacebookType from;

  @Facebook
  private String icon;

  @Facebook
  private String link;

  @Facebook
  private String message;

  @Facebook
  private String picture;

  public Date getCreatedTime()
  {
    return DateUtils.toDateFromLongFormat(this.createdTime);
  }

  public String getDescription()
  {
    return this.description;
  }

  public NamedFacebookType getFrom()
  {
    return this.from;
  }

  public String getIcon()
  {
    return this.icon;
  }

  public String getLink()
  {
    return this.link;
  }

  public String getMessage()
  {
    return this.message;
  }

  public String getPicture()
  {
    return this.picture;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.types.Link
 * JD-Core Version:    0.6.0
 */