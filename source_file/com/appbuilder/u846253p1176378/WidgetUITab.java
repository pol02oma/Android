package com.appbuilder.u846253p1176378;

import java.io.Serializable;

public class WidgetUITab extends AppConfigureItem
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String mIconCache = "";
  private String mIconData = "";
  private String mIconUrl = "";
  private String mLabel = "";
  private int mOrder = 0;

  public String getIconCache()
  {
    return this.mIconCache;
  }

  public String getIconUrl()
  {
    return this.mIconUrl;
  }

  public String getLabel()
  {
    return this.mLabel;
  }

  public int getOrder()
  {
    return this.mOrder;
  }

  public String getmIconData()
  {
    return this.mIconData;
  }

  public void setIconCache(String paramString)
  {
    this.mIconCache = paramString;
  }

  public void setIconUrl(String paramString)
  {
    this.mIconUrl = paramString;
  }

  public void setLabel(String paramString)
  {
    this.mLabel = paramString;
  }

  public void setOrder(int paramInt)
  {
    this.mOrder = paramInt;
  }

  public void setmIconData(String paramString)
  {
    this.mIconData = paramString;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.WidgetUITab
 * JD-Core Version:    0.6.0
 */