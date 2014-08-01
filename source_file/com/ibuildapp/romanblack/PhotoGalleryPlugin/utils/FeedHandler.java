package com.ibuildapp.romanblack.PhotoGalleryPlugin.utils;

import com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.ImageItem;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class FeedHandler extends DefaultHandler
{
  private StringBuilder builder;
  private ImageItem item = null;
  private ArrayList<ImageItem> items = new ArrayList();
  private boolean wasMediaContent = false;

  public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws SAXException
  {
    super.characters(paramArrayOfChar, paramInt1, paramInt2);
    if (this.item != null)
      this.builder.append(paramArrayOfChar, paramInt1, paramInt2);
  }

  public void endElement(String paramString1, String paramString2, String paramString3)
    throws SAXException
  {
    super.endElement(paramString1, paramString2, paramString3);
    if (this.item != null)
    {
      if (!paramString2.equalsIgnoreCase("TITLE"))
        break label49;
      this.item.setTitle(this.builder.toString().trim());
    }
    while (true)
    {
      this.builder.setLength(0);
      return;
      label49: if (paramString2.equalsIgnoreCase("DESCRIPTION"))
      {
        this.item.setDescription(this.builder.toString().trim());
        continue;
      }
      if ((paramString2.equalsIgnoreCase("ITEM")) || (paramString2.equalsIgnoreCase("ENTRY")))
      {
        this.items.add(this.item);
        this.item = null;
        continue;
      }
      if (!paramString2.equalsIgnoreCase("content"))
        continue;
      if (this.item.getDescription() != null)
      {
        if (!this.item.getDescription().equals(""))
          continue;
        this.item.setDescription(this.builder.toString().trim());
        continue;
      }
      this.item.setDescription(this.builder.toString().trim());
    }
  }

  public ArrayList<ImageItem> getItems()
  {
    return this.items;
  }

  public void startDocument()
    throws SAXException
  {
    super.startDocument();
    this.items = new ArrayList();
    this.builder = new StringBuilder();
  }

  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
    throws SAXException
  {
    super.startElement(paramString1, paramString2, paramString3, paramAttributes);
    if ((paramString2.equalsIgnoreCase("ITEM")) || (paramString2.equalsIgnoreCase("ENTRY")))
      this.item = new ImageItem();
    String str1;
    String str2;
    do
    {
      do
        while (true)
        {
          return;
          if (!paramString2.equalsIgnoreCase("CONTENT"))
            break;
          if (this.item == null)
            continue;
          String str3 = paramAttributes.getValue("url");
          if ((str3 == null) || (str3.length() <= 0))
            continue;
          this.item.setImageUrl(str3);
          this.wasMediaContent = true;
          return;
        }
      while ((!paramString2.equalsIgnoreCase("link")) || (this.wasMediaContent) || (this.item == null));
      str1 = paramAttributes.getValue("href");
      str2 = paramAttributes.getValue("type");
    }
    while ((str1 == null) || (str2 == null) || (str1.length() <= 0) || (!str2.startsWith("image")));
    this.item.setImageUrl(str1);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.PhotoGalleryPlugin.utils.FeedHandler
 * JD-Core Version:    0.6.0
 */