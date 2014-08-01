package com.restfb.types;

import com.restfb.Facebook;

public class CategorizedFacebookType extends NamedFacebookType
{
  private static final long serialVersionUID = 1L;

  @Facebook
  private String category;

  public String getCategory()
  {
    return this.category;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.types.CategorizedFacebookType
 * JD-Core Version:    0.6.0
 */