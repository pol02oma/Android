package com.ibuildapp.romanblack.MapPlugin;

import java.io.Serializable;

public class MapItem
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String description = "";
  private String iconUrl = "";
  private double latitude = 0.0D;
  private double longitude = 0.0D;
  private String subtitle = "";
  private String title = "";

  public String getDescription()
  {
    return this.description;
  }

  public String getIconUrl()
  {
    return this.iconUrl;
  }

  public double getLatitude()
  {
    return this.latitude;
  }

  public double getLongitude()
  {
    return this.longitude;
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

  public void setIconUrl(String paramString)
  {
    if (paramString == null)
      paramString = "";
    this.iconUrl = paramString;
  }

  public void setLatitude(double paramDouble)
  {
    this.latitude = paramDouble;
  }

  public void setLongitude(double paramDouble)
  {
    this.longitude = paramDouble;
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
 * Qualified Name:     com.ibuildapp.romanblack.MapPlugin.MapItem
 * JD-Core Version:    0.6.0
 */