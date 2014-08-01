package com.ibuildapp.romanblack.NewsPlugin;

import android.graphics.Color;
import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.sax.StartElementListener;
import android.util.Log;
import android.util.Xml;
import com.appbuilder.sdk.android.Utils;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import org.xml.sax.Attributes;

public class EntityParser
{
  private int color1 = Color.parseColor("#ffffff");
  private int color2 = Color.parseColor("#ffffff");
  private int color3 = Color.parseColor("#000000");
  private int color4 = Color.parseColor("#000000");
  private int color5 = Color.parseColor("#000000");
  private String feed = "";
  private String func = "";
  private FeedItem item = null;
  private ArrayList<FeedItem> items = new ArrayList();
  private String title = "";
  private String type = "";
  private String xml = "";

  EntityParser(String paramString)
  {
    this.xml = paramString;
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

  public String getFeedType()
  {
    return this.type;
  }

  public String getFeedUrl()
  {
    return this.feed;
  }

  public String getFuncName()
  {
    return this.func;
  }

  public ArrayList<FeedItem> getItems()
  {
    return this.items;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void parse()
  {
    RootElement localRootElement = new RootElement("data");
    Element localElement1 = localRootElement.getChild("colorskin");
    localElement1.getChild("color1").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        EntityParser.access$002(EntityParser.this, Color.parseColor(paramString.trim()));
      }
    });
    localElement1.getChild("color2").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        EntityParser.access$102(EntityParser.this, Color.parseColor(paramString.trim()));
      }
    });
    localElement1.getChild("color3").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        EntityParser.access$202(EntityParser.this, Color.parseColor(paramString.trim()));
      }
    });
    localElement1.getChild("color4").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        EntityParser.access$302(EntityParser.this, Color.parseColor(paramString.trim()));
      }
    });
    localElement1.getChild("color5").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        EntityParser.access$402(EntityParser.this, Color.parseColor(paramString.trim()));
      }
    });
    Element localElement2 = localRootElement.getChild("title");
    Element localElement3 = localRootElement.getChild("rss");
    Element localElement4 = localRootElement.getChild("news");
    Element localElement5 = localRootElement.getChild("event");
    localRootElement.setEndElementListener(new EndElementListener()
    {
      public void end()
      {
      }
    });
    localElement2.setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        EntityParser.access$502(EntityParser.this, paramString);
      }
    });
    localElement5.setStartElementListener(new StartElementListener()
    {
      public void start(Attributes paramAttributes)
      {
        EntityParser.access$602(EntityParser.this, "events");
        EntityParser.access$702(EntityParser.this, new FeedItem());
      }
    });
    localElement5.setEndElementListener(new EndElementListener()
    {
      public void end()
      {
        EntityParser.this.items.add(EntityParser.this.item);
        EntityParser.access$702(EntityParser.this, null);
      }
    });
    localElement5.getChild("title").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (EntityParser.this.item != null)
          EntityParser.this.item.setTitle(paramString);
      }
    });
    localElement5.getChild("date").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (EntityParser.this.item != null)
          EntityParser.this.item.setPubdate(paramString);
      }
    });
    localElement5.getChild("description").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (EntityParser.this.item != null)
          EntityParser.this.item.setDescription(paramString);
      }
    });
    localElement4.setStartElementListener(new StartElementListener()
    {
      public void start(Attributes paramAttributes)
      {
        EntityParser.access$602(EntityParser.this, "news");
        EntityParser.access$702(EntityParser.this, new FeedItem());
      }
    });
    localElement4.setEndElementListener(new EndElementListener()
    {
      public void end()
      {
        EntityParser.this.items.add(EntityParser.this.item);
        EntityParser.access$702(EntityParser.this, null);
      }
    });
    localElement4.getChild("title").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (EntityParser.this.item != null)
          EntityParser.this.item.setTitle(paramString);
      }
    });
    localElement4.getChild("indextext").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (EntityParser.this.item != null)
          EntityParser.this.item.setIndextext(paramString);
      }
    });
    localElement4.getChild("date").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (EntityParser.this.item != null)
          EntityParser.this.item.setPubdate(paramString);
      }
    });
    localElement4.getChild("url").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (EntityParser.this.item != null)
          EntityParser.this.item.setImageUrl(paramString);
      }
    });
    localElement4.getChild("description").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (EntityParser.this.item != null)
          EntityParser.this.item.setDescription(paramString);
      }
    });
    localElement3.setStartElementListener(new StartElementListener()
    {
      public void start(Attributes paramAttributes)
      {
        EntityParser.access$602(EntityParser.this, "rss");
        EntityParser.access$902(EntityParser.this, "rss");
        if (paramAttributes.getValue("type") != null)
          EntityParser.access$602(EntityParser.this, paramAttributes.getValue("type"));
      }
    });
    localElement3.setEndElementListener(new EndElementListener()
    {
      public void end()
      {
      }
    });
    localElement3.setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        EntityParser.access$1002(EntityParser.this, paramString);
      }
    });
    String str = Utils.inputStreamToString(new ByteArrayInputStream(this.xml.getBytes())).replace('\013', ' ');
    try
    {
      Xml.parse(str, localRootElement.getContentHandler());
      return;
    }
    catch (Exception localException)
    {
      Log.e("PARSER", "", localException);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.NewsPlugin.EntityParser
 * JD-Core Version:    0.6.0
 */