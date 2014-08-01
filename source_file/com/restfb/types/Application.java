package com.restfb.types;

import com.restfb.Facebook;

public class Application extends CategorizedFacebookType
{
  private static final long serialVersionUID = 1L;

  @Facebook
  private String description;

  @Facebook
  private String link;

  public String getDescription()
  {
    return this.description;
  }

  public String getLink()
  {
    return this.link;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.types.Application
 * JD-Core Version:    0.6.0
 */