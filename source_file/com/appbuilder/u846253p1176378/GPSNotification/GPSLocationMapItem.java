package com.appbuilder.u846253p1176378.GPSNotification;

import android.graphics.Bitmap;
import com.google.android.maps.GeoPoint;

public class GPSLocationMapItem
{
  private String description = "";
  private Bitmap icon = null;
  private GeoPoint point = null;
  private Bitmap shadow = null;
  private states state = states.SHOW;
  private String title = "";

  public String getDescription()
  {
    return this.description;
  }

  public GeoPoint getGeoPoint()
  {
    return this.point;
  }

  public Bitmap getIcon()
  {
    return this.icon;
  }

  public Bitmap getIconShadow()
  {
    return this.shadow;
  }

  public states getState()
  {
    return this.state;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setDescription(String paramString)
  {
    this.description = paramString;
  }

  public void setGeoPoint(GeoPoint paramGeoPoint)
  {
    this.point = paramGeoPoint;
  }

  public void setIcon(Bitmap paramBitmap)
  {
    this.icon = paramBitmap;
  }

  public void setIconShadow(Bitmap paramBitmap)
  {
    this.shadow = paramBitmap;
  }

  public void setState(states paramstates)
  {
    this.state = paramstates;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }

  public static enum states
  {
    static
    {
      HIDE = new states("HIDE", 1);
      states[] arrayOfstates = new states[2];
      arrayOfstates[0] = SHOW;
      arrayOfstates[1] = HIDE;
      $VALUES = arrayOfstates;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.GPSNotification.GPSLocationMapItem
 * JD-Core Version:    0.6.0
 */