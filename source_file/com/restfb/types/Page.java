package com.restfb.types;

import com.restfb.Facebook;

public class Page extends CategorizedFacebookType
{
  private static final long serialVersionUID = 2L;

  @Facebook("access_token")
  private String accessToken;

  @Facebook
  private Integer checkins;

  @Facebook("company_overview")
  private String companyOverview;

  @Facebook
  private String description;

  @Facebook("fan_count")
  @Deprecated
  private Long fanCount;

  @Facebook
  private String founded;

  @Facebook("is_community_page")
  private Boolean isCommunityPage;

  @Facebook
  private Long likes;

  @Facebook
  private String link;

  @Facebook
  private Location location;

  @Facebook
  private String mission;

  @Facebook
  private String phone;

  @Facebook
  private String picture;

  @Facebook
  private String products;

  @Facebook
  private String username;

  public String getAccessToken()
  {
    return this.accessToken;
  }

  public Integer getCheckins()
  {
    return this.checkins;
  }

  public String getCompanyOverview()
  {
    return this.companyOverview;
  }

  public String getDescription()
  {
    return this.description;
  }

  public Long getFanCount()
  {
    return this.fanCount;
  }

  public String getFounded()
  {
    return this.founded;
  }

  public Boolean getIsCommunityPage()
  {
    return this.isCommunityPage;
  }

  public Long getLikes()
  {
    return this.likes;
  }

  public String getLink()
  {
    return this.link;
  }

  public Location getLocation()
  {
    return this.location;
  }

  public String getMission()
  {
    return this.mission;
  }

  public String getPhone()
  {
    return this.phone;
  }

  public String getPicture()
  {
    return this.picture;
  }

  public String getProducts()
  {
    return this.products;
  }

  public String getUsername()
  {
    return this.username;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.types.Page
 * JD-Core Version:    0.6.0
 */