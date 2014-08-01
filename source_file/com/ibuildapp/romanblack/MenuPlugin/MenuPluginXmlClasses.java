package com.ibuildapp.romanblack.MenuPlugin;

import android.graphics.Bitmap;
import java.io.Serializable;
import java.util.ArrayList;

public class MenuPluginXmlClasses
{
  public String app_id;
  public String app_name;
  public ArrayList<MenuCategory> categoryList = new ArrayList();
  public ColorSkin colorSkin = new ColorSkin();
  public String currency;
  public String currencyposition;
  public String mainPageStyle;
  public String module_id;
  public boolean showimages;

  public static class ColorSkin
    implements Serializable
  {
    public int color1 = 0;
    public int color2 = 0;
    public int color3 = 0;
    public int color4 = 0;
    public int color5 = 0;
    public int color6 = 0;
  }

  public static class MenuCategory
  {
    private Bitmap categoryBitmap = null;
    private String categoryImg = null;
    private String categoryImgFileName = null;
    private String categoryName = null;
    private String categoryResFile = null;
    public ArrayList<MenuPluginXmlClasses.categoryItem> items = null;

    public Bitmap getCategoryBitmap()
    {
      return this.categoryBitmap;
    }

    public String getCategoryImg()
    {
      return this.categoryImg;
    }

    public String getCategoryImgFileName()
    {
      return this.categoryImgFileName;
    }

    public String getCategoryName()
    {
      return this.categoryName;
    }

    public String getCategoryResFile()
    {
      return this.categoryResFile;
    }

    public void setCategoryBitmap(Bitmap paramBitmap)
    {
      if (paramBitmap == null)
        throw new NullPointerException();
      this.categoryBitmap = paramBitmap;
    }

    public void setCategoryImg(String paramString)
      throws NullPointerException
    {
      if (paramString == null)
        throw new NullPointerException();
      this.categoryImg = paramString;
    }

    public void setCategoryImgFileName(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      this.categoryImgFileName = paramString;
    }

    public void setCategoryName(String paramString)
      throws NullPointerException
    {
      if (paramString == null)
        throw new NullPointerException();
      this.categoryName = paramString;
    }

    public void setCategoryResFile(String paramString)
    {
      this.categoryResFile = paramString;
    }
  }

  public static class categoryItem
    implements Serializable
  {
    String itemDescripton;
    String itemImagePath;
    String itemImageResPath;
    String itemName;
    String itemPrice;
    String itemThumbnailPath;
    String itemThumbnailResPath;
    String itemThumbnailUrl;
    String itemUrl;

    public String getItemDescripton()
    {
      return this.itemDescripton;
    }

    public String getItemImagePath()
    {
      return this.itemImagePath;
    }

    public String getItemImageResPath()
    {
      return this.itemImageResPath;
    }

    public String getItemName()
    {
      return this.itemName;
    }

    public String getItemPrice()
    {
      return this.itemPrice;
    }

    public String getItemThumbnailPath()
    {
      return this.itemThumbnailPath;
    }

    public String getItemThumbnailResPath()
    {
      return this.itemThumbnailResPath;
    }

    public String getItemThumbnailUrl()
    {
      return this.itemThumbnailUrl;
    }

    public String getItemUrl()
    {
      return this.itemUrl;
    }

    public void setItemDescripton(String paramString)
    {
      this.itemDescripton = paramString;
    }

    public void setItemImagePath(String paramString)
    {
      this.itemImagePath = paramString;
    }

    public void setItemImageResPath(String paramString)
    {
      this.itemImageResPath = paramString;
    }

    public void setItemName(String paramString)
    {
      this.itemName = paramString;
    }

    public void setItemPrice(String paramString)
    {
      this.itemPrice = paramString;
    }

    public void setItemThumbnailPath(String paramString)
    {
      this.itemThumbnailPath = paramString;
    }

    public void setItemThumbnailResPath(String paramString)
    {
      this.itemThumbnailResPath = paramString;
    }

    public void setItemThumbnailUrl(String paramString)
    {
      this.itemThumbnailUrl = paramString;
    }

    public void setItemUrl(String paramString)
    {
      this.itemUrl = paramString;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.MenuPlugin.MenuPluginXmlClasses
 * JD-Core Version:    0.6.0
 */