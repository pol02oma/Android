package com.ibuildapp.romanblack.PhotoGalleryPlugin.utils;

import android.graphics.Color;
import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.sax.StartElementListener;
import android.util.Log;
import android.util.Xml;
import android.util.Xml.Encoding;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.Album;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.ImageItem;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import org.xml.sax.Attributes;

public class EntityParser
{
  private Album album = null;
  private ArrayList<Album> albums = new ArrayList();
  private String appId = "";
  private String appName = "";
  private int color1 = 0;
  private int color2 = 0;
  private int color3 = 0;
  private int color4 = 0;
  private int color5 = 0;
  private int color6 = 0;
  private int color7 = 0;
  private String feed = "";
  private ImageItem item = null;
  private ArrayList<ImageItem> items = new ArrayList();
  private String moduleId = "";
  private String title = "";
  private String xml = "";

  public EntityParser(String paramString)
  {
    this.xml = paramString;
  }

  public ArrayList<Album> getAlbums()
  {
    return this.albums;
  }

  public String getAppId()
  {
    return this.appId;
  }

  public String getAppName()
  {
    return this.appName;
  }

  public int getColor1()
  {
    return this.color1;
  }

  public int getColor2()
  {
    return this.color2;
  }

  public int getColor3()
  {
    return this.color3;
  }

  public int getColor4()
  {
    return this.color4;
  }

  public int getColor5()
  {
    return this.color5;
  }

  @Deprecated
  public int getColor6()
  {
    return this.color6;
  }

  @Deprecated
  public int getColor7()
  {
    return this.color7;
  }

  public String getFeedUrl()
  {
    return this.feed;
  }

  public ArrayList<ImageItem> getItems()
  {
    return this.items;
  }

  public String getModuleId()
  {
    return this.moduleId;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void parse()
  {
    RootElement localRootElement = new RootElement("data");
    Element localElement1 = localRootElement.getChild("app_id");
    1 local1 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        EntityParser.access$002(EntityParser.this, paramString.trim());
      }
    };
    localElement1.setEndTextElementListener(local1);
    Element localElement2 = localRootElement.getChild("module_id");
    2 local2 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        EntityParser.access$102(EntityParser.this, paramString.trim());
      }
    };
    localElement2.setEndTextElementListener(local2);
    Element localElement3 = localRootElement.getChild("app_name");
    3 local3 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        EntityParser.access$202(EntityParser.this, paramString.trim());
      }
    };
    localElement3.setEndTextElementListener(local3);
    Element localElement4 = localRootElement.getChild("colorskin");
    Element localElement5 = localElement4.getChild("color1");
    4 local4 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        try
        {
          EntityParser.access$302(EntityParser.this, Color.parseColor(paramString.trim()));
          return;
        }
        catch (Exception localException)
        {
        }
      }
    };
    localElement5.setEndTextElementListener(local4);
    Element localElement6 = localElement4.getChild("color2");
    5 local5 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        try
        {
          EntityParser.access$402(EntityParser.this, Color.parseColor(paramString.trim()));
          return;
        }
        catch (Exception localException)
        {
        }
      }
    };
    localElement6.setEndTextElementListener(local5);
    Element localElement7 = localElement4.getChild("color3");
    6 local6 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        try
        {
          EntityParser.access$502(EntityParser.this, Color.parseColor(paramString.trim()));
          return;
        }
        catch (Exception localException)
        {
        }
      }
    };
    localElement7.setEndTextElementListener(local6);
    Element localElement8 = localElement4.getChild("color4");
    7 local7 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        try
        {
          EntityParser.access$602(EntityParser.this, Color.parseColor(paramString.trim()));
          return;
        }
        catch (Exception localException)
        {
        }
      }
    };
    localElement8.setEndTextElementListener(local7);
    Element localElement9 = localElement4.getChild("color5");
    8 local8 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        try
        {
          EntityParser.access$702(EntityParser.this, Color.parseColor(paramString.trim()));
          return;
        }
        catch (Exception localException)
        {
        }
      }
    };
    localElement9.setEndTextElementListener(local8);
    Element localElement10 = localElement4.getChild("color6");
    9 local9 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        try
        {
          EntityParser.access$802(EntityParser.this, Color.parseColor(paramString.trim()));
          return;
        }
        catch (Exception localException)
        {
        }
      }
    };
    localElement10.setEndTextElementListener(local9);
    Element localElement11 = localElement4.getChild("color7");
    10 local10 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        try
        {
          EntityParser.access$902(EntityParser.this, Color.parseColor(paramString.trim()));
          return;
        }
        catch (Exception localException)
        {
        }
      }
    };
    localElement11.setEndTextElementListener(local10);
    Element localElement12 = localRootElement.getChild("album");
    11 local11 = new StartElementListener()
    {
      public void start(Attributes paramAttributes)
      {
        EntityParser.access$1002(EntityParser.this, new Album());
      }
    };
    localElement12.setStartElementListener(local11);
    12 local12 = new EndElementListener()
    {
      public void end()
      {
        EntityParser.this.albums.add(EntityParser.this.album);
        EntityParser.access$1002(EntityParser.this, null);
      }
    };
    localElement12.setEndElementListener(local12);
    Element localElement13 = localElement12.getChild("id");
    13 local13 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (EntityParser.this.album != null)
          EntityParser.this.album.setId(Long.parseLong(paramString.trim()));
      }
    };
    localElement13.setEndTextElementListener(local13);
    Element localElement14 = localElement12.getChild("url");
    14 local14 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (EntityParser.this.album != null)
          EntityParser.this.album.setUrl(paramString.trim());
      }
    };
    localElement14.setEndTextElementListener(local14);
    Element localElement15 = localElement12.getChild("rss");
    15 local15 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (EntityParser.this.album != null)
          EntityParser.this.album.setRssUrl(paramString.trim());
      }
    };
    localElement15.setEndTextElementListener(local15);
    Element localElement16 = localElement12.getChild("title");
    16 local16 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (EntityParser.this.album != null)
          EntityParser.this.album.setTitle(paramString.trim());
      }
    };
    localElement16.setEndTextElementListener(local16);
    Element localElement17 = localElement12.getChild("default");
    17 local17 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if ((EntityParser.this.album != null) && (paramString.trim().equalsIgnoreCase("yes")))
          EntityParser.this.album.setDefault(true);
      }
    };
    localElement17.setEndTextElementListener(local17);
    Element localElement18 = localElement12.getChild("allow_sharing");
    18 local18 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if ((EntityParser.this.album != null) && (paramString.trim().equalsIgnoreCase("on")))
          EntityParser.this.album.setAllowSharing(true);
      }
    };
    localElement18.setEndTextElementListener(local18);
    Element localElement19 = localElement12.getChild("allow_comments");
    19 local19 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if ((EntityParser.this.album != null) && (paramString.trim().equalsIgnoreCase("on")))
          EntityParser.this.album.setAllowComments(true);
      }
    };
    localElement19.setEndTextElementListener(local19);
    Element localElement20 = localElement12.getChild("images").getChild("image");
    20 local20 = new EndElementListener()
    {
      public void end()
      {
        EntityParser.this.album.getImages().add(EntityParser.this.item);
        EntityParser.access$1202(EntityParser.this, null);
      }
    };
    localElement20.setEndElementListener(local20);
    21 local21 = new StartElementListener()
    {
      public void start(Attributes paramAttributes)
      {
        EntityParser.access$1202(EntityParser.this, new ImageItem());
      }
    };
    localElement20.setStartElementListener(local21);
    Element localElement21 = localElement20.getChild("id");
    22 local22 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (EntityParser.this.item != null)
          EntityParser.this.item.setId(Long.parseLong(paramString.trim()));
      }
    };
    localElement21.setEndTextElementListener(local22);
    Element localElement22 = localElement20.getChild("url");
    23 local23 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (EntityParser.this.item != null)
          EntityParser.this.item.setImageUrl(paramString.trim());
      }
    };
    localElement22.setEndTextElementListener(local23);
    Element localElement23 = localElement20.getChild("title");
    24 local24 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (EntityParser.this.item != null)
          EntityParser.this.item.setTitle(paramString.trim());
      }
    };
    localElement23.setEndTextElementListener(local24);
    Element localElement24 = localElement20.getChild("description");
    25 local25 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (EntityParser.this.item != null)
          EntityParser.this.item.setDescription(paramString.trim());
      }
    };
    localElement24.setEndTextElementListener(local25);
    try
    {
      Xml.parse(new ByteArrayInputStream(this.xml.getBytes()), Xml.Encoding.UTF_8, localRootElement.getContentHandler());
      return;
    }
    catch (Exception localException)
    {
      Log.d("", "");
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.PhotoGalleryPlugin.utils.EntityParser
 * JD-Core Version:    0.6.0
 */