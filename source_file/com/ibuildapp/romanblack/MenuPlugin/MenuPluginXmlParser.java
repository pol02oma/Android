package com.ibuildapp.romanblack.MenuPlugin;

import android.graphics.Color;
import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.sax.StartElementListener;
import android.util.Log;
import android.util.Xml;
import android.util.Xml.Encoding;
import com.appbuilder.sdk.android.Utils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class MenuPluginXmlParser
{
  private MenuPluginXmlClasses.categoryItem catItem = null;
  private MenuPluginXmlClasses.MenuCategory currentCategory = null;
  private MenuPluginXmlClasses result = null;
  private String xml;

  public MenuPluginXmlParser(String paramString)
    throws NullPointerException
  {
    if (paramString != null)
    {
      this.xml = Utils.removeSpec(paramString);
      return;
    }
    throw new NullPointerException();
  }

  public MenuPluginXmlClasses getResult()
  {
    return this.result;
  }

  public void parse()
  {
    RootElement localRootElement = new RootElement("data");
    Element localElement1 = localRootElement.getChild("category");
    Element localElement2 = localRootElement.getChild("currency");
    Element localElement3 = localRootElement.getChild("colorskin");
    Element localElement4 = localRootElement.getChild("mainpagestyle");
    Element localElement5 = localRootElement.getChild("module_id");
    Element localElement6 = localRootElement.getChild("app_id");
    Element localElement7 = localRootElement.getChild("app_name");
    Element localElement8 = localRootElement.getChild("showimages");
    Element localElement9 = localRootElement.getChild("currencyposition");
    localElement3.getChild("color1").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        try
        {
          MenuPluginXmlParser.this.result.colorSkin.color1 = Color.parseColor(paramString);
          return;
        }
        catch (Exception localException)
        {
          MenuPluginXmlParser.this.result.colorSkin.color1 = 0;
        }
      }
    });
    localElement3.getChild("color2").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        try
        {
          MenuPluginXmlParser.this.result.colorSkin.color2 = Color.parseColor(paramString);
          return;
        }
        catch (Exception localException)
        {
          MenuPluginXmlParser.this.result.colorSkin.color2 = 0;
        }
      }
    });
    localElement3.getChild("color3").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        try
        {
          MenuPluginXmlParser.this.result.colorSkin.color3 = Color.parseColor(paramString);
          return;
        }
        catch (Exception localException)
        {
          MenuPluginXmlParser.this.result.colorSkin.color3 = 0;
        }
      }
    });
    localElement3.getChild("color4").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        try
        {
          MenuPluginXmlParser.this.result.colorSkin.color4 = Color.parseColor(paramString);
          return;
        }
        catch (Exception localException)
        {
          MenuPluginXmlParser.this.result.colorSkin.color4 = 0;
        }
      }
    });
    localElement3.getChild("color5").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        try
        {
          MenuPluginXmlParser.this.result.colorSkin.color5 = Color.parseColor(paramString);
          return;
        }
        catch (Exception localException)
        {
          MenuPluginXmlParser.this.result.colorSkin.color5 = 0;
        }
      }
    });
    localElement3.getChild("color6").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        try
        {
          MenuPluginXmlParser.this.result.colorSkin.color6 = Color.parseColor(paramString);
          return;
        }
        catch (Exception localException)
        {
          MenuPluginXmlParser.this.result.colorSkin.color6 = 0;
        }
      }
    });
    localElement8.setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if ((paramString != null) && (!paramString.equals("")))
        {
          if (paramString.compareToIgnoreCase("on") == 0)
            MenuPluginXmlParser.this.result.showimages = true;
        }
        else
          return;
        MenuPluginXmlParser.this.result.showimages = false;
      }
    });
    localElement9.setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        MenuPluginXmlParser.this.result.currencyposition = paramString;
      }
    });
    localElement4.setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        MenuPluginXmlParser.this.result.mainPageStyle = paramString;
      }
    });
    localElement5.setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        MenuPluginXmlParser.this.result.module_id = paramString;
      }
    });
    localElement6.setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        MenuPluginXmlParser.this.result.app_id = paramString;
      }
    });
    localElement7.setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        MenuPluginXmlParser.this.result.app_name = paramString;
      }
    });
    localElement2.setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        MenuPluginXmlParser.this.result.currency = paramString;
      }
    });
    localElement1.setStartElementListener(new StartElementListener()
    {
      public void start(Attributes paramAttributes)
      {
        MenuPluginXmlParser.access$102(MenuPluginXmlParser.this, new MenuPluginXmlClasses.MenuCategory());
        Log.d("", "");
      }
    });
    localElement1.setEndElementListener(new EndElementListener()
    {
      public void end()
      {
        MenuPluginXmlParser.this.result.categoryList.add(MenuPluginXmlParser.this.currentCategory);
      }
    });
    localElement1.getChild("categoryimg").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        MenuPluginXmlParser.this.currentCategory.setCategoryImg(paramString);
      }
    });
    localElement1.getChild("categoryname").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        MenuPluginXmlParser.this.currentCategory.setCategoryName(paramString);
      }
    });
    localElement1.getChild("categoryimg_res").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        MenuPluginXmlParser.this.currentCategory.setCategoryResFile(paramString);
      }
    });
    localElement1.getChild("item").setStartElementListener(new StartElementListener()
    {
      public void start(Attributes paramAttributes)
      {
        MenuPluginXmlParser.access$202(MenuPluginXmlParser.this, new MenuPluginXmlClasses.categoryItem());
        MenuPluginXmlParser.this.catItem.setItemPrice(paramAttributes.getValue("price"));
      }
    });
    localElement1.getChild("item").getChild("itemimg").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        MenuPluginXmlParser.this.catItem.setItemUrl(paramString);
      }
    });
    localElement1.getChild("item").getChild("itemthumbnail").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        MenuPluginXmlParser.this.catItem.setItemThumbnailUrl(paramString);
      }
    });
    localElement1.getChild("item").getChild("itemname").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        MenuPluginXmlParser.this.catItem.setItemName(paramString);
      }
    });
    localElement1.getChild("item").getChild("itemdescription").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        MenuPluginXmlParser.this.catItem.setItemDescripton(paramString);
      }
    });
    localElement1.getChild("item").getChild("itemimg_res").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        MenuPluginXmlParser.this.catItem.setItemImageResPath(paramString);
      }
    });
    localElement1.getChild("item").getChild("itemthumbnail_res").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        MenuPluginXmlParser.this.catItem.setItemThumbnailResPath(paramString);
      }
    });
    localElement1.getChild("item").setEndElementListener(new EndElementListener()
    {
      public void end()
      {
        MenuPluginXmlParser.this.currentCategory.items.add(MenuPluginXmlParser.this.catItem);
      }
    });
    try
    {
      Xml.parse(new ByteArrayInputStream(this.xml.getBytes()), Xml.Encoding.UTF_8, localRootElement.getContentHandler());
      return;
    }
    catch (SAXException localSAXException)
    {
      Log.d("parse", localSAXException.getMessage());
      return;
    }
    catch (IOException localIOException)
    {
      Log.d("parse", localIOException.getMessage());
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.MenuPlugin.MenuPluginXmlParser
 * JD-Core Version:    0.6.0
 */