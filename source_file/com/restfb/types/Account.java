package com.restfb.types;

import com.restfb.Facebook;

public class Account extends CategorizedFacebookType
{
  private static final long serialVersionUID = 1L;

  @Facebook("access_token")
  private String accessToken;

  public String getAccessToken()
  {
    return this.accessToken;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.types.Account
 * JD-Core Version:    0.6.0
 */