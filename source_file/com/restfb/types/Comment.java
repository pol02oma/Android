package com.restfb.types;

import com.restfb.Facebook;
import com.restfb.util.DateUtils;
import java.util.Date;

public class Comment extends FacebookType
{
  private static final long serialVersionUID = 1L;

  @Facebook("created_time")
  private String createdTime;

  @Facebook
  private NamedFacebookType from;

  @Facebook
  private Long likes;

  @Facebook
  private String message;

  public Date getCreatedTime()
  {
    return DateUtils.toDateFromLongFormat(this.createdTime);
  }

  public NamedFacebookType getFrom()
  {
    return this.from;
  }

  public Long getLikes()
  {
    return this.likes;
  }

  public String getMessage()
  {
    return this.message;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.types.Comment
 * JD-Core Version:    0.6.0
 */