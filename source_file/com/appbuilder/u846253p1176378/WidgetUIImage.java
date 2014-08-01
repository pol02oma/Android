package com.appbuilder.u846253p1176378;

import java.io.Serializable;

public class WidgetUIImage extends AppConfigureItem
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int height = 0;
  private int left = 0;
  private String mImageData = "";
  private String mSourceCache = "";
  private String mSourceUrl = "";
  private int top = 0;
  private int width = 0;

  public int getHeight()
  {
    return this.height;
  }

  public int getLeft()
  {
    return this.left;
  }

  public String getSourceCache()
  {
    return this.mSourceCache;
  }

  public String getSourceUrl()
  {
    return this.mSourceUrl;
  }

  public int getTop()
  {
    return this.top;
  }

  public int getWidth()
  {
    return this.width;
  }

  public String getmImageData()
  {
    return this.mImageData;
  }

  public void setHeight(int paramInt)
  {
    this.height = paramInt;
  }

  public void setLeft(int paramInt)
  {
    this.left = paramInt;
  }

  public void setSourceCache(String paramString)
  {
    this.mSourceCache = paramString;
  }

  public void setSourceUrl(String paramString)
  {
    this.mSourceUrl = paramString;
  }

  public void setTop(int paramInt)
  {
    this.top = paramInt;
  }

  public void setWidth(int paramInt)
  {
    this.width = paramInt;
  }

  public void setmImageData(String paramString)
  {
    this.mImageData = paramString;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.WidgetUIImage
 * JD-Core Version:    0.6.0
 */