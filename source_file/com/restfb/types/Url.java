package com.restfb.types;

import com.restfb.Facebook;

public class Url extends NamedFacebookType
{
  private static final long serialVersionUID = 1L;

  @Facebook
  private String category;

  @Facebook("fan_count")
  private Long fanCount;

  @Facebook
  private String link;

  @Facebook
  private String picture;

  @Facebook
  private Long shares;

  public String getCategory()
  {
    return this.category;
  }

  public Long getFanCount()
  {
    return this.fanCount;
  }

  public String getLink()
  {
    return this.link;
  }

  public String getPicture()
  {
    return this.picture;
  }

  public Long getShares()
  {
    return this.shares;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.types.Url
 * JD-Core Version:    0.6.0
 */