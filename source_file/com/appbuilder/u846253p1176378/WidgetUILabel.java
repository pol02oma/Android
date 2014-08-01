package com.appbuilder.u846253p1176378;

import java.io.Serializable;

public class WidgetUILabel extends AppConfigureItem
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int height = 0;
  private int left = 0;
  private String mAlign = "";
  private String mColor = "";
  private int mFontSize = -1;
  private String mStyle = "";
  private String mTitle = "";
  private int top = 0;
  private int width = 0;

  public String getAlign()
  {
    return this.mAlign;
  }

  public String getColor()
  {
    return this.mColor;
  }

  public int getFontSize()
  {
    return this.mFontSize;
  }

  public int getHeight()
  {
    return this.height;
  }

  public int getLeft()
  {
    return this.left;
  }

  public String getStyle()
  {
    return this.mStyle;
  }

  public String getTitle()
  {
    return this.mTitle;
  }

  public int getTop()
  {
    return this.top;
  }

  public int getWidth()
  {
    return this.width;
  }

  public void setAlign(String paramString)
  {
    this.mAlign = paramString;
  }

  public void setColor(String paramString)
  {
    this.mColor = paramString;
  }

  public void setFontSize(int paramInt)
  {
    this.mFontSize = paramInt;
  }

  public void setHeight(int paramInt)
  {
    this.height = paramInt;
  }

  public void setLeft(int paramInt)
  {
    this.left = paramInt;
  }

  public void setStyle(String paramString)
  {
    this.mStyle = paramString;
  }

  public void setTitle(String paramString)
  {
    this.mTitle = paramString;
  }

  public void setTop(int paramInt)
  {
    this.top = paramInt;
  }

  public void setWidth(int paramInt)
  {
    this.width = paramInt;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.WidgetUILabel
 * JD-Core Version:    0.6.0
 */