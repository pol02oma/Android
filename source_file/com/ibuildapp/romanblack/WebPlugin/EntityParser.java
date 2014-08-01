package com.ibuildapp.romanblack.WebPlugin;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.sax.StartElementListener;
import android.util.Log;
import android.util.Xml;
import android.util.Xml.Encoding;
import java.io.ByteArrayInputStream;
import org.xml.sax.Attributes;

public class EntityParser
{
  private String html = "";
  private String title = "";
  private String url = "";
  private String xml = "";

  EntityParser(String paramString)
  {
    this.xml = paramString.replaceAll("\013", "");
  }

  public String getHtml()
  {
    return this.html;
  }

  public String getTitle()
  {
    return this.title;
  }

  public String getUrl()
  {
    return this.url;
  }

  public void parse()
  {
    RootElement localRootElement = new RootElement("data");
    Element localElement1 = localRootElement.getChild("title");
    Element localElement2 = localRootElement.getChild("content");
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
        if (paramAttributes.getValue("src") != null)
          EntityParser.access$102(EntityParser.this, paramAttributes.getValue("src"));
      }
    });
    localElement2.setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        EntityParser.access$202(EntityParser.this, paramString);
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
 * Qualified Name:     com.ibuildapp.romanblack.WebPlugin.EntityParser
 * JD-Core Version:    0.6.0
 */