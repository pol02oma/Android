package com.ibuildapp.romanblack.TableReservationPlugin.entity;

import com.appbuilder.sdk.android.authorization.entities.User.ACCOUNT_TYPES;
import java.io.Serializable;

public class FanWallUser
  implements Serializable
{
  private String accountId = "";
  private User.ACCOUNT_TYPES accountType = User.ACCOUNT_TYPES.IBUILDAPP;
  private String avatarUrl = "";
  private String userFirstName = "";
  private String userLastName = "";
  private String userName = "";

  public String getAccountId()
  {
    return this.accountId;
  }

  public User.ACCOUNT_TYPES getAccountType()
  {
    return this.accountType;
  }

  public String getAvatarUrl()
  {
    return this.avatarUrl;
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

  public void setAccountId(String paramString)
  {
    this.accountId = paramString;
  }

  public void setAccountType(String paramString)
  {
    if (paramString.equalsIgnoreCase("facebook"))
    {
      this.accountType = User.ACCOUNT_TYPES.FACEBOOK;
      return;
    }
    if (paramString.equalsIgnoreCase("twitter"))
    {
      this.accountType = User.ACCOUNT_TYPES.TWITTER;
      return;
    }
    this.accountType = User.ACCOUNT_TYPES.IBUILDAPP;
  }

  public void setAvatarUrl(String paramString)
  {
    this.avatarUrl = paramString;
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
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.TableReservationPlugin.entity.FanWallUser
 * JD-Core Version:    0.6.0
 */