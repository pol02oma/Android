package com.appbuilder.sdk.android;

import android.graphics.Color;
import java.io.Serializable;
import java.util.HashMap;

public class Widget
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private boolean addToSidebar = false;
  private String appname = "AppBuilder";
  private String background = "";
  private int backgroundColor = 0;
  private String cachePath = "";
  private int dateFormat = 0;
  private String faviconFilePath = "";
  private String faviconURL = "";
  private boolean mHaveAdvertisement = true;
  private int mOrder = -1;
  private String mPluginHash = "";
  private String mPluginName = "";
  private String mPluginPackage = "";
  private String mPluginType = "";
  private String mPluginUrl = "";
  private String mPluginXmlData = "";
  private HashMap<String, Object> params = new HashMap();
  private String pluginId = "0";
  private String pluginType = "";
  private String subtitle = "";
  private int textColor = 0;
  private String title = "";

  public Widget()
  {
  }

  public Widget(Widget paramWidget)
  {
    this.mHaveAdvertisement = paramWidget.mHaveAdvertisement;
    this.mOrder = paramWidget.mOrder;
    this.mPluginName = paramWidget.mPluginName.toString();
    this.mPluginPackage = paramWidget.mPluginPackage.toString();
    this.mPluginUrl = paramWidget.mPluginUrl.toString();
    this.mPluginHash = paramWidget.mPluginHash.toString();
    this.mPluginXmlData = paramWidget.mPluginXmlData.toString();
    this.pluginType = paramWidget.pluginType.toString();
    this.pluginId = paramWidget.pluginId.toString();
    this.background = paramWidget.background.toString();
    this.subtitle = paramWidget.subtitle.toString();
    this.faviconURL = paramWidget.faviconURL.toString();
    this.faviconFilePath = paramWidget.faviconFilePath.toString();
    this.addToSidebar = paramWidget.addToSidebar;
    this.textColor = paramWidget.textColor;
    this.backgroundColor = paramWidget.backgroundColor;
    this.dateFormat = paramWidget.dateFormat;
    this.appname = paramWidget.appname.toString();
    this.title = paramWidget.title.toString();
    this.cachePath = paramWidget.cachePath.toString();
    this.params = ((HashMap)paramWidget.params.clone());
  }

  public void addParameter(String paramString, Object paramObject)
  {
    this.params.put(paramString, paramObject);
  }

  public String getAppName()
  {
    return this.appname;
  }

  public int getBackgroundColor()
  {
    try
    {
      int i = Color.parseColor(this.background.toUpperCase());
      return i;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      return 0;
    }
    catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException)
    {
    }
    return 0;
  }

  public String getBackgroundURL()
  {
    return this.background;
  }

  public String getCachePath()
  {
    return this.cachePath;
  }

  public int getDateFormat()
  {
    return this.dateFormat;
  }

  public String getFaviconFilePath()
  {
    return this.faviconFilePath;
  }

  public String getFaviconURL()
  {
    return this.faviconURL;
  }

  public int getOrder()
  {
    return this.mOrder;
  }

  public Object getParameter(String paramString)
  {
    if (this.params.containsKey(paramString))
      return this.params.get(paramString);
    return null;
  }

  public String getPluginHash()
  {
    return this.mPluginHash;
  }

  public String getPluginId()
  {
    return this.pluginId;
  }

  public String getPluginName()
  {
    return this.mPluginName;
  }

  public String getPluginPackage()
  {
    return this.mPluginPackage;
  }

  public String getPluginType()
  {
    return this.pluginType;
  }

  public String getPluginUrl()
  {
    return this.mPluginUrl;
  }

  public String getPluginXmlData()
  {
    return this.mPluginXmlData;
  }

  public String getSubtitle()
  {
    return this.subtitle;
  }

  public int getTextColor()
  {
    return this.textColor;
  }

  public String getTitle()
  {
    return this.title;
  }

  public String getmPluginType()
  {
    return this.mPluginType;
  }

  public boolean hasParameter(String paramString)
  {
    return this.params.containsKey(paramString);
  }

  public boolean isAddToSidebar()
  {
    return this.addToSidebar;
  }

  public boolean isBackgroundColor()
  {
    return (this.background.length() == 6) || (this.background.length() == 7);
  }

  public boolean isBackgroundInAssets()
  {
    if (isBackgroundColor());
    do
      return false;
    while (isBackgroundURL());
    return true;
  }

  public boolean isBackgroundURL()
  {
    return (this.background.contains("http://")) || (this.background.contains("https://"));
  }

  public boolean isHaveAdvertisement()
  {
    return this.mHaveAdvertisement;
  }

  public void setAddToSidebar(boolean paramBoolean)
  {
    this.addToSidebar = paramBoolean;
  }

  public void setAppName(String paramString)
  {
    this.appname = paramString;
  }

  public void setBackground(String paramString)
  {
    this.background = paramString;
  }

  public void setCachePath(String paramString)
  {
    this.cachePath = paramString;
  }

  public void setDateFormat(int paramInt)
  {
    int i = 1;
    if ((paramInt != 0) || (paramInt != i))
      i = 0;
    this.dateFormat = i;
  }

  public void setFaviconFilePath(String paramString)
  {
    this.faviconFilePath = paramString;
  }

  public void setFaviconURL(String paramString)
  {
    this.faviconURL = paramString;
  }

  public void setHaveAdvertisement(boolean paramBoolean)
  {
    this.mHaveAdvertisement = paramBoolean;
  }

  public void setNormalPluginXmlData(String paramString)
  {
    this.mPluginXmlData = paramString;
  }

  public void setOrder(int paramInt)
  {
    this.mOrder = paramInt;
  }

  public void setPluginHash(String paramString)
  {
    this.mPluginHash = paramString;
  }

  public void setPluginId(String paramString)
  {
    this.pluginId = paramString;
  }

  public void setPluginName(String paramString)
  {
    this.mPluginName = paramString;
  }

  public void setPluginPackage(String paramString)
  {
    this.mPluginPackage = paramString;
  }

  public void setPluginType(String paramString)
  {
    this.pluginType = paramString;
  }

  public void setPluginUrl(String paramString)
  {
    this.mPluginUrl = paramString;
  }

  public void setPluginXmlData(String paramString)
  {
    this.mPluginXmlData = Utils.fromBase64(paramString);
  }

  public void setSubtitle(String paramString)
  {
    this.subtitle = paramString;
  }

  public void setTextColor(String paramString)
  {
    try
    {
      this.textColor = Color.parseColor(paramString.toUpperCase());
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      this.textColor = 0;
    }
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }

  public void setmPluginType(String paramString)
  {
    this.mPluginType = paramString;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.sdk.android.Widget
 * JD-Core Version:    0.6.0
 */