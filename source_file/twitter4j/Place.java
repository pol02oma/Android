package twitter4j;

import java.io.Serializable;

public abstract interface Place extends TwitterResponse, Comparable<Place>, Serializable
{
  public abstract GeoLocation[][] getBoundingBoxCoordinates();

  public abstract String getBoundingBoxType();

  public abstract Place[] getContainedWithIn();

  public abstract String getCountry();

  public abstract String getCountryCode();

  public abstract String getFullName();

  public abstract GeoLocation[][] getGeometryCoordinates();

  public abstract String getGeometryType();

  public abstract String getId();

  public abstract String getName();

  public abstract String getPlaceType();

  public abstract String getStreetAddress();

  public abstract String getURL();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.Place
 * JD-Core Version:    0.6.0
 */