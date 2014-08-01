package com.ibuildapp.romanblack.VideoPlugin;

import android.graphics.Color;
import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.sax.StartElementListener;
import android.util.Log;
import android.util.Xml;
import android.util.Xml.Encoding;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import org.xml.sax.Attributes;

public class EntityParser
{
  private String appId = "0";
  private String appName = "";
  private int color1 = Color.parseColor("#4d4948");
  private int color2 = Color.parseColor("#fff58d");
  private int color3 = Color.parseColor("#fff7a2");
  private int color4 = Color.parseColor("#ffffff");
  private int color5 = Color.parseColor("#bbbbbb");
  private String commentsOn = "off";
  private VideoItem item = null;
  private ArrayList<VideoItem> items = new ArrayList();
  private String moduleId = "0";
  private String sharingOn = "off";
  private String title = "";
  private String xml = "";

  EntityParser(String paramString)
  {
    this.xml = paramString;
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

  public String getCommentsOn()
  {
    return this.commentsOn;
  }

  public ArrayList<VideoItem> getItems()
  {
    return this.items;
  }

  public String getModuleId()
  {
    return this.moduleId;
  }

  public String getSharingOn()
  {
    return this.sharingOn;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void parse()
  {
    RootElement localRootElement = new RootElement("data");
    Element localElement1 = localRootElement.getChild("title");
    Element localElement2 = localRootElement.getChild("app_id");
    1 local1 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        EntityParser.access$002(EntityParser.this, paramString.trim());
      }
    };
    localElement2.setEndTextElementListener(local1);
    Element localElement3 = localRootElement.getChild("module_id");
    2 local2 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        EntityParser.access$102(EntityParser.this, paramString.trim());
      }
    };
    localElement3.setEndTextElementListener(local2);
    Element localElement4 = localRootElement.getChild("app_name");
    3 local3 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        EntityParser.access$202(EntityParser.this, paramString.trim());
      }
    };
    localElement4.setEndTextElementListener(local3);
    Element localElement5 = localRootElement.getChild("colorskin");
    Element localElement6 = localElement5.getChild("color1");
    4 local4 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        EntityParser.access$302(EntityParser.this, Color.parseColor(paramString.trim()));
      }
    };
    localElement6.setEndTextElementListener(local4);
    Element localElement7 = localElement5.getChild("color2");
    5 local5 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        EntityParser.access$402(EntityParser.this, Color.parseColor(paramString.trim()));
      }
    };
    localElement7.setEndTextElementListener(local5);
    Element localElement8 = localElement5.getChild("color3");
    6 local6 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        EntityParser.access$502(EntityParser.this, Color.parseColor(paramString.trim()));
      }
    };
    localElement8.setEndTextElementListener(local6);
    Element localElement9 = localElement5.getChild("color4");
    7 local7 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        EntityParser.access$602(EntityParser.this, Color.parseColor(paramString.trim()));
      }
    };
    localElement9.setEndTextElementListener(local7);
    Element localElement10 = localElement5.getChild("color5");
    8 local8 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        EntityParser.access$702(EntityParser.this, Color.parseColor(paramString.trim()));
      }
    };
    localElement10.setEndTextElementListener(local8);
    Element localElement11 = localRootElement.getChild("allowsharing");
    9 local9 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        EntityParser.access$802(EntityParser.this, paramString.trim());
      }
    };
    localElement11.setEndTextElementListener(local9);
    Element localElement12 = localRootElement.getChild("allowcomments");
    10 local10 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        EntityParser.access$902(EntityParser.this, paramString.trim());
      }
    };
    localElement12.setEndTextElementListener(local10);
    Element localElement13 = localRootElement.getChild("video");
    11 local11 = new EndElementListener()
    {
      public void end()
      {
      }
    };
    localRootElement.setEndElementListener(local11);
    12 local12 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        EntityParser.access$1002(EntityParser.this, paramString.trim());
      }
    };
    localElement1.setEndTextElementListener(local12);
    13 local13 = new StartElementListener()
    {
      public void start(Attributes paramAttributes)
      {
        EntityParser.access$1102(EntityParser.this, new VideoItem());
      }
    };
    localElement13.setStartElementListener(local13);
    14 local14 = new EndElementListener()
    {
      public void end()
      {
        EntityParser.this.items.add(EntityParser.this.item);
        EntityParser.access$1102(EntityParser.this, null);
      }
    };
    localElement13.setEndElementListener(local14);
    Element localElement14 = localElement13.getChild("title");
    15 local15 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (EntityParser.this.item != null)
          EntityParser.this.item.setTitle(paramString.trim());
      }
    };
    localElement14.setEndTextElementListener(local15);
    Element localElement15 = localElement13.getChild("url");
    16 local16 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (EntityParser.this.item != null)
          EntityParser.this.item.setUrl(paramString.trim());
      }
    };
    localElement15.setEndTextElementListener(local16);
    Element localElement16 = localElement13.getChild("cover");
    17 local17 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (EntityParser.this.item != null)
          EntityParser.this.item.setCoverUrl(paramString.trim());
      }
    };
    localElement16.setEndTextElementListener(local17);
    Element localElement17 = localElement13.getChild("description");
    18 local18 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (EntityParser.this.item != null)
          EntityParser.this.item.setDescription(paramString.trim());
      }
    };
    localElement17.setEndTextElementListener(local18);
    Element localElement18 = localElement13.getChild("id");
    19 local19 = new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (EntityParser.this.item != null)
          EntityParser.this.item.setId(Long.parseLong(paramString.trim()));
      }
    };
    localElement18.setEndTextElementListener(local19);
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
 * Qualified Name:     com.ibuildapp.romanblack.VideoPlugin.EntityParser
 * JD-Core Version:    0.6.0
 */