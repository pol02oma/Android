package com.ibuildapp.romanblack.MapPlugin;

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
  private MapItem item = null;
  private ArrayList<MapItem> items = new ArrayList();
  public boolean showCurrentLocation = true;
  private String title = "";
  private String xml = "";
  public int zoom = -1;

  EntityParser(String paramString)
  {
    this.xml = paramString;
  }

  public ArrayList<MapItem> getItems()
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
    Element localElement1 = localRootElement.getChild("title");
    Element localElement2 = localRootElement.getChild("object");
    localRootElement.getChild("showCurrentUserLocation").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (paramString.contains("0"))
          EntityParser.this.showCurrentLocation = false;
      }
    });
    localRootElement.getChild("initialZoom").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        EntityParser.this.zoom = Integer.parseInt(paramString);
      }
    });
    localRootElement.setEndElementListener(new EndElementListener()
    {
      public void end()
      {
      }
    });
    localElement1.setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        EntityParser.access$002(EntityParser.this, paramString);
      }
    });
    localElement2.setStartElementListener(new StartElementListener()
    {
      public void start(Attributes paramAttributes)
      {
        EntityParser.access$102(EntityParser.this, new MapItem());
      }
    });
    localElement2.setEndElementListener(new EndElementListener()
    {
      public void end()
      {
        EntityParser.this.items.add(EntityParser.this.item);
        EntityParser.access$102(EntityParser.this, null);
      }
    });
    localElement2.getChild("title").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (EntityParser.this.item != null)
          EntityParser.this.item.setTitle(paramString);
      }
    });
    localElement2.getChild("subtitle").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (EntityParser.this.item != null)
          EntityParser.this.item.setSubtitle(paramString);
      }
    });
    localElement2.getChild("description").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (EntityParser.this.item != null)
          EntityParser.this.item.setDescription(paramString);
      }
    });
    localElement2.getChild("longitude").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (EntityParser.this.item != null);
        try
        {
          EntityParser.this.item.setLongitude(Double.parseDouble(paramString));
          return;
        }
        catch (Exception localException)
        {
        }
      }
    });
    localElement2.getChild("latitude").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (EntityParser.this.item != null);
        try
        {
          EntityParser.this.item.setLatitude(Double.parseDouble(paramString));
          return;
        }
        catch (Exception localException)
        {
        }
      }
    });
    localElement2.getChild("pinurl").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        EntityParser.this.item.setIconUrl(paramString.trim());
      }
    });
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
 * Qualified Name:     com.ibuildapp.romanblack.MapPlugin.EntityParser
 * JD-Core Version:    0.6.0
 */