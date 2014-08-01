package com.restfb.types;

import com.restfb.Facebook;
import com.restfb.util.ReflectionUtils;
import java.io.Serializable;

public class Venue
  implements Serializable
{
  private static final long serialVersionUID = 1L;

  @Facebook
  private String city;

  @Facebook
  private String country;

  @Facebook
  private Double latitude;

  @Facebook
  private Double longitude;

  @Facebook
  private String state;

  @Facebook
  private String street;

  public boolean equals(Object paramObject)
  {
    return ReflectionUtils.equals(this, paramObject);
  }

  public String getCity()
  {
    return this.city;
  }

  public String getCountry()
  {
    return this.country;
  }

  public Double getLatitude()
  {
    return this.latitude;
  }

  public Double getLongitude()
  {
    return this.longitude;
  }

  public String getState()
  {
    return this.state;
  }

  public String getStreet()
  {
    return this.street;
  }

  public int hashCode()
  {
    return ReflectionUtils.hashCode(this);
  }

  public String toString()
  {
    return ReflectionUtils.toString(this);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.types.Venue
 * JD-Core Version:    0.6.0
 */