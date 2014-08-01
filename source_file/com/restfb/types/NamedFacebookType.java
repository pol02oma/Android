package com.restfb.types;

import com.restfb.Facebook;

public class NamedFacebookType extends FacebookType
{
  private static final long serialVersionUID = 1L;

  @Facebook
  private String name;

  public String getName()
  {
    return this.name;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.types.NamedFacebookType
 * JD-Core Version:    0.6.0
 */