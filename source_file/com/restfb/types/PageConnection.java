package com.restfb.types;

import com.restfb.Facebook;
import com.restfb.util.DateUtils;
import java.util.Date;

public class PageConnection extends CategorizedFacebookType
{
  private static final long serialVersionUID = 1L;

  @Facebook("created_time")
  private String createdTime;

  public Date getCreatedTime()
  {
    return DateUtils.toDateFromLongFormat(this.createdTime);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.types.PageConnection
 * JD-Core Version:    0.6.0
 */