package com.appbuilder.sdk.android;

import java.io.Serializable;

public class LoginScreen
  implements Serializable
{
  Boolean allowSignup;
  String appId;
  String loginEndpoint;
  String logo;
  String recoveryPasswordEndpoint;
  String signupEndpoint;
  Boolean useEmail;
  Boolean useFacebook;
  Boolean useTwitter;

  public Boolean getAllowSignup()
  {
    return this.allowSignup;
  }

  public String getAppId()
  {
    return this.appId;
  }

  public String getLoginEndpoint()
  {
    return this.loginEndpoint;
  }

  public String getLogo()
  {
    return this.logo;
  }

  public String getRecoveryPasswordEndpoint()
  {
    return this.recoveryPasswordEndpoint;
  }

  public String getSignupEndpoint()
  {
    return this.signupEndpoint;
  }

  public Boolean getUseEmail()
  {
    return this.useEmail;
  }

  public Boolean getUseFacebook()
  {
    return this.useFacebook;
  }

  public Boolean getUseTwitter()
  {
    return this.useTwitter;
  }

  public void setAllowSignup(Boolean paramBoolean)
  {
    this.allowSignup = paramBoolean;
  }

  public void setAppId(String paramString)
  {
    this.appId = paramString;
  }

  public void setLoginEndpoint(String paramString)
  {
    this.loginEndpoint = paramString;
  }

  public void setLogo(String paramString)
  {
    this.logo = paramString;
  }

  public void setRecoveryPasswordEndpoint(String paramString)
  {
    this.recoveryPasswordEndpoint = paramString;
  }

  public void setSignupEndpoint(String paramString)
  {
    this.signupEndpoint = paramString;
  }

  public void setUseEmail(Boolean paramBoolean)
  {
    this.useEmail = paramBoolean;
  }

  public void setUseFacebook(Boolean paramBoolean)
  {
    this.useFacebook = paramBoolean;
  }

  public void setUseTwitter(Boolean paramBoolean)
  {
    this.useTwitter = paramBoolean;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.sdk.android.LoginScreen
 * JD-Core Version:    0.6.0
 */