package com.ibuildapp.romanblack.TableReservationPlugin;

import com.ibuildapp.romanblack.TableReservationPlugin.entity.HouresMinutes;
import java.io.Serializable;
import java.util.Date;

public class TableReservationInfo
  implements Serializable
{
  private String appid = "";
  public ColorSkin colorskin = new ColorSkin();
  private String customerEmail = "";
  private boolean emailConfirmation;
  private HouresMinutes endTime = new HouresMinutes();
  private Double latitude;
  private String login = "";
  private Double longitude;
  private int maxpersons = -1;
  private String moduleid = "";
  private Integer offsetTime;
  private TableReservationOrderInfo orderInfo = new TableReservationOrderInfo();
  private String phoneNumber = "";
  private String restaurantGreeting = "";
  private String restaurantName = "";
  private String restaurantadditional = "";
  private String restaurantadress = "";
  private String restaurantimageFilePath = "";
  private String restaurantimageurl = "";
  private String restaurantkitchen = "";
  private String restaurantmail = "";
  private String restaurantphone = "";
  private boolean smsConfirmation;
  private String specialRequest = "";
  private HouresMinutes startTime = new HouresMinutes();

  public String getAppid()
  {
    return this.appid;
  }

  public String getCustomerEmail()
  {
    return this.customerEmail;
  }

  public boolean getEmailConfirmation()
  {
    return this.emailConfirmation;
  }

  public HouresMinutes getEndTime()
  {
    return this.endTime;
  }

  public String getKitchen()
  {
    return this.restaurantkitchen;
  }

  public Double getLatitude()
  {
    return this.latitude;
  }

  public String getLogin()
  {
    return this.login;
  }

  public Double getLongitude()
  {
    return this.longitude;
  }

  public int getMaxpersons()
  {
    return this.maxpersons;
  }

  public String getModuleid()
  {
    return this.moduleid;
  }

  public Integer getOffsetTime()
  {
    return this.offsetTime;
  }

  public Date getOrderDate()
  {
    return this.orderInfo.orderDate;
  }

  public HouresMinutes getOrderTime()
  {
    return this.orderInfo.orderTime;
  }

  public int getPersonsAmount()
  {
    return this.orderInfo.personsAmount;
  }

  public String getPhoneNumber()
  {
    return this.phoneNumber;
  }

  public String getRestaurantGreeting()
  {
    return this.restaurantGreeting;
  }

  public String getRestaurantName()
  {
    return this.restaurantName;
  }

  public String getRestaurantadditional()
  {
    return this.restaurantadditional;
  }

  public String getRestaurantadress()
  {
    return this.restaurantadress;
  }

  public String getRestaurantimageurl()
  {
    return this.restaurantimageurl;
  }

  public String getRestaurantmail()
  {
    return this.restaurantmail;
  }

  public String getRestaurantphone()
  {
    return this.restaurantphone;
  }

  public boolean getSmsConfirmation()
  {
    return this.smsConfirmation;
  }

  public String getSpecialRequest()
  {
    return this.specialRequest;
  }

  public HouresMinutes getStartTime()
  {
    return this.startTime;
  }

  public String getrestaurantimageFilePath()
  {
    return this.restaurantimageFilePath;
  }

  public void setAppid(String paramString)
  {
    this.appid = paramString;
  }

  public void setCustomerEmail(String paramString)
  {
    this.customerEmail = paramString;
  }

  public void setEmailConfirmation(boolean paramBoolean)
  {
    this.emailConfirmation = paramBoolean;
  }

  public void setEndTime(int paramInt1, int paramInt2)
  {
    this.endTime.houres = paramInt1;
    this.endTime.minutes = paramInt2;
  }

  public void setKitchen(String paramString)
  {
    this.restaurantkitchen = paramString;
  }

  public void setLatitude(Double paramDouble)
  {
    this.latitude = paramDouble;
  }

  public void setLogin(String paramString)
  {
    this.login = paramString;
  }

  public void setLongitude(Double paramDouble)
  {
    this.longitude = paramDouble;
  }

  public void setMaxpersons(int paramInt)
  {
    if (paramInt != 0)
      this.maxpersons = paramInt;
  }

  public void setModuleid(String paramString)
  {
    this.moduleid = paramString;
  }

  public void setOffsetTime(int paramInt)
  {
    this.offsetTime = Integer.valueOf(paramInt);
  }

  public void setOrderDate(Date paramDate)
  {
    this.orderInfo.orderDate = paramDate;
  }

  public void setOrderTime(int paramInt1, int paramInt2, String paramString)
  {
    this.orderInfo.orderTime.houres = paramInt1;
    this.orderInfo.orderTime.minutes = paramInt2;
    this.orderInfo.orderTime.am_pm = paramString;
  }

  public void setPersonsAmount(int paramInt)
  {
    this.orderInfo.personsAmount = paramInt;
  }

  public void setPhoneNumber(String paramString)
  {
    this.phoneNumber = paramString;
  }

  public void setRestaurantGreeting(String paramString)
  {
    this.restaurantGreeting = paramString;
  }

  public void setRestaurantName(String paramString)
  {
    if ((paramString.length() != 0) && (paramString != ""))
      this.restaurantName = paramString;
  }

  public void setRestaurantadditional(String paramString)
  {
    if ((paramString.length() != 0) && (paramString != ""))
      this.restaurantadditional = paramString;
  }

  public void setRestaurantadress(String paramString)
  {
    if ((paramString.length() != 0) && (paramString != ""))
      this.restaurantadress = paramString;
  }

  public void setRestaurantimageurl(String paramString)
  {
    if ((paramString.length() != 0) && (paramString != ""))
      this.restaurantimageurl = paramString;
  }

  public void setRestaurantmail(String paramString)
  {
    if ((paramString.length() != 0) && (paramString != ""))
      this.restaurantmail = paramString;
  }

  public void setRestaurantphone(String paramString)
  {
    if ((paramString.length() != 0) && (paramString != ""))
      this.restaurantphone = paramString;
  }

  public void setSmsConfirmation(boolean paramBoolean)
  {
    this.smsConfirmation = paramBoolean;
  }

  public void setSpecialRequest(String paramString)
  {
    this.specialRequest = paramString;
  }

  public void setStartTime(int paramInt1, int paramInt2)
  {
    this.startTime.houres = paramInt1;
    this.startTime.minutes = paramInt2;
  }

  public void setrestaurantimageFilePath(String paramString)
  {
    this.restaurantimageFilePath = paramString;
  }

  public static class ColorSkin
    implements Serializable
  {
    public int color1 = 0;
    public int color2 = 0;
    public int color3 = 0;
    public int color4 = 0;
    public int color5 = 0;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.TableReservationPlugin.TableReservationInfo
 * JD-Core Version:    0.6.0
 */