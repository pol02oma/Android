package com.appbuilder.statistics;

import java.util.Date;

public class StatisticsError
{
  private Date date = null;
  private String description = "";
  private String place = "";

  public Date getDate()
  {
    return this.date;
  }

  public String getDescription()
  {
    return this.description;
  }

  public String getPlace()
  {
    return this.place;
  }

  public void setDate(Date paramDate)
  {
    this.date = paramDate;
  }

  public void setDescription(String paramString)
  {
    this.description = paramString;
  }

  public void setPlace(String paramString)
  {
    this.place = paramString;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.statistics.StatisticsError
 * JD-Core Version:    0.6.0
 */