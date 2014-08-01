package com.restfb.types;

import com.restfb.Facebook;
import com.restfb.util.DateUtils;
import java.util.Date;

public class Note extends FacebookType
{
  private static final long serialVersionUID = 1L;

  @Facebook("created_time")
  private String createdTime;

  @Facebook
  private NamedFacebookType from;

  @Facebook
  private String icon;

  @Facebook
  private String message;

  @Facebook
  private String subject;

  @Facebook("updated_time")
  private String updatedTime;

  public Date getCreatedTime()
  {
    return DateUtils.toDateFromLongFormat(this.createdTime);
  }

  public NamedFacebookType getFrom()
  {
    return this.from;
  }

  public String getIcon()
  {
    return this.icon;
  }

  public String getMessage()
  {
    return this.message;
  }

  public String getSubject()
  {
    return this.subject;
  }

  public Date getUpdatedTime()
  {
    return DateUtils.toDateFromLongFormat(this.updatedTime);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.types.Note
 * JD-Core Version:    0.6.0
 */