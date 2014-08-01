package com.appbuilder.sdk.android.authorization.entities;

import java.io.Serializable;

public class User
  implements Serializable
{
  private String accessToken = "";
  private String accessTokenSecret = "";
  private String accountId = "";
  private ACCOUNT_TYPES accountType = ACCOUNT_TYPES.IBUILDAPP;
  private String avatarUrl = "";
  private String consumerKey = "";
  private String consumerSecret = "";
  private String userEmail = "";
  private String userFirstName = "";
  private String userLastName = "";
  private String userName = "";

  public String getAccessToken()
  {
    return this.accessToken;
  }

  public String getAccessTokenSecret()
  {
    return this.accessTokenSecret;
  }

  public String getAccountId()
  {
    return this.accountId;
  }

  public ACCOUNT_TYPES getAccountType()
  {
    return this.accountType;
  }

  public String getAvatarUrl()
  {
    return this.avatarUrl;
  }

  public String getConsumerKey()
  {
    return this.consumerKey;
  }

  public String getConsumerSecret()
  {
    return this.consumerSecret;
  }

  public String getUserEmail()
  {
    return this.userEmail;
  }

  public String getUserFirstName()
  {
    return this.userFirstName;
  }

  public String getUserLastName()
  {
    return this.userLastName;
  }

  public String getUserName()
  {
    if (this.userName.length() > 0)
      return this.userName;
    if ((this.userFirstName.length() > 0) && (this.userLastName.length() > 0))
      return this.userFirstName + " " + this.userLastName;
    if (this.userFirstName.length() > 0)
      return this.userFirstName;
    if (this.userLastName.length() > 0)
      return this.userLastName;
    return "";
  }

  public void setAccessToken(String paramString)
  {
    this.accessToken = paramString;
  }

  public void setAccessTokenSecret(String paramString)
  {
    this.accessTokenSecret = paramString;
  }

  public void setAccountId(String paramString)
  {
    this.accountId = paramString;
  }

  public void setAccountType(String paramString)
  {
    if (paramString.equalsIgnoreCase("facebook"))
    {
      this.accountType = ACCOUNT_TYPES.FACEBOOK;
      return;
    }
    if (paramString.equalsIgnoreCase("twitter"))
    {
      this.accountType = ACCOUNT_TYPES.TWITTER;
      return;
    }
    if (paramString.equalsIgnoreCase("ibuildapp"))
    {
      this.accountType = ACCOUNT_TYPES.IBUILDAPP;
      return;
    }
    if (paramString.equalsIgnoreCase("vkontakte"))
    {
      this.accountType = ACCOUNT_TYPES.VKONTAKTE;
      return;
    }
    if (paramString.equalsIgnoreCase("linkedin"))
    {
      this.accountType = ACCOUNT_TYPES.LINKEDIN;
      return;
    }
    this.accountType = ACCOUNT_TYPES.GUEST;
  }

  public void setAvatarUrl(String paramString)
  {
    this.avatarUrl = paramString;
  }

  public void setConsumerKey(String paramString)
  {
    this.consumerKey = paramString;
  }

  public void setConsumerSecret(String paramString)
  {
    this.consumerSecret = paramString;
  }

  public void setUserEmail(String paramString)
  {
    this.userEmail = paramString;
  }

  public void setUserFirstName(String paramString)
  {
    this.userFirstName = paramString;
  }

  public void setUserLastName(String paramString)
  {
    this.userLastName = paramString;
  }

  public void setUserName(String paramString)
  {
    this.userName = paramString;
  }

  public static enum ACCOUNT_TYPES
  {
    static
    {
      IBUILDAPP = new ACCOUNT_TYPES("IBUILDAPP", 2);
      GUEST = new ACCOUNT_TYPES("GUEST", 3);
      VKONTAKTE = new ACCOUNT_TYPES("VKONTAKTE", 4);
      LINKEDIN = new ACCOUNT_TYPES("LINKEDIN", 5);
      ACCOUNT_TYPES[] arrayOfACCOUNT_TYPES = new ACCOUNT_TYPES[6];
      arrayOfACCOUNT_TYPES[0] = FACEBOOK;
      arrayOfACCOUNT_TYPES[1] = TWITTER;
      arrayOfACCOUNT_TYPES[2] = IBUILDAPP;
      arrayOfACCOUNT_TYPES[3] = GUEST;
      arrayOfACCOUNT_TYPES[4] = VKONTAKTE;
      arrayOfACCOUNT_TYPES[5] = LINKEDIN;
      $VALUES = arrayOfACCOUNT_TYPES;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.sdk.android.authorization.entities.User
 * JD-Core Version:    0.6.0
 */