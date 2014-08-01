package com.appbuilder.u846253p1176378.GPSNotification;

import java.io.Serializable;

public class GPSItem
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String description = "";
  private int distance = 0;
  private double latitude = 0.0D;
  private double longitude = 0.0D;
  private int radius = 0;
  private Show showType = Show.SINGLE;
  private String title = "";

  public Show getCountOfView()
  {
    return this.showType;
  }

  public String getDescription()
  {
    return this.description;
  }

  public int getDistance()
  {
    return this.distance;
  }

  public double getLatitude()
  {
    return this.latitude;
  }

  public double getLongitude()
  {
    return this.longitude;
  }

  public int getRadius()
  {
    return this.radius;
  }

  public String getTitle()
  {
    return this.title;
  }

  public boolean isInRadius()
  {
    return this.distance < this.radius;
  }

  public void setCountOfView(Show paramShow)
  {
    this.showType = paramShow;
  }

  public void setDescription(String paramString)
  {
    this.description = paramString;
  }

  public void setDistance(int paramInt)
  {
    this.distance = paramInt;
  }

  public void setLatitude(double paramDouble)
  {
    this.latitude = paramDouble;
  }

  public void setLongitude(double paramDouble)
  {
    this.longitude = paramDouble;
  }

  public void setRadius(int paramInt)
  {
    this.radius = paramInt;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }

  public static enum Show
  {
    static
    {
      PLURAL = new Show("PLURAL", 1);
      Show[] arrayOfShow = new Show[2];
      arrayOfShow[0] = SINGLE;
      arrayOfShow[1] = PLURAL;
      $VALUES = arrayOfShow;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.GPSNotification.GPSItem
 * JD-Core Version:    0.6.0
 */