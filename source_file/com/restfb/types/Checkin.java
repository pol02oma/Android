package com.restfb.types;

import com.restfb.Facebook;
import com.restfb.util.DateUtils;
import com.restfb.util.ReflectionUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Checkin extends FacebookType
{
  private static final long serialVersionUID = 1L;

  @Facebook
  private NamedFacebookType application;

  @Facebook
  private List<Comment> comments = new ArrayList();

  @Facebook("created_time")
  private String createdTime;

  @Facebook
  private NamedFacebookType from;

  @Facebook
  private String message;

  @Facebook
  private Place place;

  public NamedFacebookType getApplication()
  {
    return this.application;
  }

  public List<Comment> getComments()
  {
    return Collections.unmodifiableList(this.comments);
  }

  public Date getCreatedTime()
  {
    return DateUtils.toDateFromLongFormat(this.createdTime);
  }

  public NamedFacebookType getFrom()
  {
    return this.from;
  }

  public String getMessage()
  {
    return this.message;
  }

  public Place getPlace()
  {
    return this.place;
  }

  public static class Place extends CategorizedFacebookType
  {
    private static final long serialVersionUID = 1L;

    @Facebook
    private Location location;

    public boolean equals(Object paramObject)
    {
      return ReflectionUtils.equals(this, paramObject);
    }

    public Location getLocation()
    {
      return this.location;
    }

    public int hashCode()
    {
      return ReflectionUtils.hashCode(this);
    }

    public String toString()
    {
      return ReflectionUtils.toString(this);
    }

    public static class Location
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

      public int hashCode()
      {
        return ReflectionUtils.hashCode(this);
      }

      public String toString()
      {
        return ReflectionUtils.toString(this);
      }
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.types.Checkin
 * JD-Core Version:    0.6.0
 */