package com.appbuilder.u846253p1176378.PushNotification;

import java.util.Date;

public class AppPushNotificationMessage
{
  public long androidNotificationId;
  public String descriptionText;
  public String imagePath;
  public String imgUrl;
  public boolean isPackageExist;
  public Date notificationDate;
  public String packageName;
  public String statusBarText;
  public String titleText;
  public long uid;
  public int widgetOrder;

  public AppPushNotificationMessage()
  {
    this.uid = -1L;
    this.packageName = "";
    this.statusBarText = "";
    this.descriptionText = "";
    this.imgUrl = "";
    Date localDate = new Date();
    localDate.setTime(-1L);
    this.notificationDate = localDate;
    this.imagePath = "";
    this.androidNotificationId = -1L;
    this.widgetOrder = -1;
    this.isPackageExist = false;
  }

  public AppPushNotificationMessage(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, Date paramDate, int paramInt)
  {
    this.titleText = paramString2;
    this.packageName = paramString1;
    this.statusBarText = paramString3;
    this.descriptionText = paramString4;
    this.imgUrl = paramString5;
    this.notificationDate = paramDate;
    this.widgetOrder = paramInt;
  }

  public String toString()
  {
    return "AppPushNotificationMessage{uid=" + this.uid + ", statusBarText='" + this.statusBarText + '\'' + ", titleText='" + this.titleText + '\'' + ", descriptionText='" + this.descriptionText + '\'' + ", imgUrl='" + this.imgUrl + '\'' + ", imagePath='" + this.imagePath + '\'' + ", widgetOrder='" + this.widgetOrder + '\'' + ", isPackageExist='" + this.isPackageExist + '\'' + '}';
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.PushNotification.AppPushNotificationMessage
 * JD-Core Version:    0.6.0
 */