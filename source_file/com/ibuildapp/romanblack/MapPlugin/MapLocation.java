package com.ibuildapp.romanblack.MapPlugin;

import com.google.android.maps.GeoPoint;

public class MapLocation
{
  private String description = "";
  private int latitude = 0;
  private int longitude = 0;
  private GeoPoint point = null;
  private String subtitle = "";
  private String title = "";

  public MapLocation(double paramDouble1, double paramDouble2)
  {
    this.latitude = (int)(paramDouble1 * 1000000.0D);
    this.longitude = (int)(paramDouble2 * 1000000.0D);
  }

  public String getDescription()
  {
    return this.description;
  }

  public int getLatitude()
  {
    return this.latitude;
  }

  public int getLongitude()
  {
    return this.longitude;
  }

  public GeoPoint getPoint()
  {
    return this.point;
  }

  public String getSubtitle()
  {
    return this.subtitle;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setDescription(String paramString)
  {
    this.description = paramString;
  }

  public void setSubtitle(String paramString)
  {
    this.subtitle = paramString;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.MapPlugin.MapLocation
 * JD-Core Version:    0.6.0
 */