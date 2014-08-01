package com.ibuildapp.romanblack.NewsPlugin;

import android.text.TextUtils;
import com.appbuilder.sdk.android.Utils;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class FeedHandler extends DefaultHandler
{
  private StringBuilder builder;
  private String datePattern = "";
  private String feedUrl = "";
  private boolean hasLink = false;
  private boolean isMedia = false;
  private FeedItem item = null;
  private ArrayList<FeedItem> items = new ArrayList();
  private boolean wasGoogleDate = false;

  public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws SAXException
  {
    if (this.item != null)
      this.builder.append(paramArrayOfChar, paramInt1, paramInt2);
  }

  public void endDocument()
    throws SAXException
  {
  }

  public void endElement(String paramString1, String paramString2, String paramString3)
    throws SAXException
  {
    if (this.item != null)
    {
      if ((paramString1.equalsIgnoreCase("http://www.w3.org/2005/Atom")) || (paramString1.equalsIgnoreCase("http://purl.org/rss/1.0/")))
        paramString1 = "";
      if (!paramString2.equalsIgnoreCase("TITLE"))
        break label70;
      if (TextUtils.isEmpty(paramString1))
        this.item.setTitle(Utils.removeSpec(this.builder.toString()));
    }
    while (true)
    {
      this.builder.setLength(0);
      return;
      label70: if (paramString2.equalsIgnoreCase("description"))
      {
        this.item.setDescription(this.builder.toString());
        continue;
      }
      if (paramString2.equalsIgnoreCase("CONTENT"))
      {
        if (!TextUtils.isEmpty(this.item.getDescription()))
          continue;
        this.item.setDescription(this.builder.toString());
        continue;
      }
      if (paramString2.equalsIgnoreCase("SUMMARY"))
      {
        if (!TextUtils.isEmpty(this.item.getDescription()))
          continue;
        this.item.setDescription(this.builder.toString());
        continue;
      }
      if (paramString2.equalsIgnoreCase("PUBDATE"))
      {
        if (this.wasGoogleDate)
          continue;
        if (this.datePattern.length() == 0)
        {
          this.datePattern = this.item.setPubdate(this.builder.toString());
          continue;
        }
        this.item.setPubdate(this.builder.toString(), this.datePattern);
        continue;
      }
      if (paramString2.equalsIgnoreCase("UPDATED"))
      {
        if (this.wasGoogleDate)
          continue;
        if (this.datePattern.length() == 0)
        {
          this.datePattern = this.item.setPubdate(this.builder.toString());
          continue;
        }
        this.item.setPubdate(this.builder.toString(), this.datePattern);
        continue;
      }
      if (paramString2.equalsIgnoreCase("LINK"))
      {
        if ((!this.hasLink) || (this.builder.toString().trim().length() == 0))
          continue;
        this.item.setLink(this.builder.toString().trim());
        continue;
      }
      if ((paramString2.equalsIgnoreCase("ITEM")) || (paramString2.equalsIgnoreCase("ENTRY")))
      {
        this.items.add(this.item);
        this.item = null;
        continue;
      }
      if ((paramString2.equalsIgnoreCase("date")) && (paramString1.equalsIgnoreCase("http://purl.org/dc/elements/1.1/")))
      {
        if (!"".equals(this.item.getPubdate("")))
          continue;
        if (this.datePattern.length() == 0)
        {
          this.datePattern = this.item.setPubdate(this.builder.toString());
          continue;
        }
        this.item.setPubdate(this.builder.toString(), this.datePattern);
        continue;
      }
      if ((!paramString2.equalsIgnoreCase("encoded")) || (!paramString1.equalsIgnoreCase("http://purl.org/rss/1.0/modules/content/")))
        continue;
      this.item.setDescription(this.builder.toString());
    }
  }

  public ArrayList<FeedItem> getItems()
  {
    return this.items;
  }

  public void setFeedUrl(String paramString)
  {
    this.feedUrl = paramString;
  }

  public void startDocument()
    throws SAXException
  {
    this.items = new ArrayList();
    this.builder = new StringBuilder();
  }

  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
    throws SAXException
  {
    if ((!paramString2.equalsIgnoreCase("content")) || (!paramString1.equalsIgnoreCase("http://search.yahoo.com/mrss/")) || (this.item != null));
    try
    {
      if (!paramAttributes.getValue("url").equals(""))
        this.item.addMediaItem(paramAttributes.getValue("url"), paramAttributes.getValue("type"));
      try
      {
        label67: if ((paramAttributes.getValue("medium").equalsIgnoreCase("image")) && (!this.item.hasImage()))
          this.item.setImageUrl(paramAttributes.getValue("url"));
        label110: if (paramString1.indexOf("mrss") == -1);
        for (boolean bool = false; ; bool = true)
        {
          this.isMedia = bool;
          if ((this.isMedia) && (paramString2.equalsIgnoreCase("thumbnail")) && (this.item != null))
          {
            String str3 = paramAttributes.getValue("url");
            if (str3.length() > 0)
              this.item.setImageUrl(str3);
          }
          if ((this.item != null) && (paramString2.equalsIgnoreCase("LINK")))
          {
            String str1 = paramAttributes.getValue("rel");
            if ((str1 == null) || (str1.equalsIgnoreCase("ALTERNATE")) || (str1.length() == 0))
            {
              this.hasLink = true;
              String str2 = paramAttributes.getValue("href");
              if ((str2 != null) && (str2.length() != 0))
                this.item.setLink(str2);
            }
            if ((str1 == null) || (str1.equalsIgnoreCase("IMAGE")) || (str1.length() == 0))
              this.item.setImageUrl(paramAttributes.getValue("href"));
          }
          return;
          if ((paramString2.equalsIgnoreCase("when")) && (paramString1.equalsIgnoreCase("http://schemas.google.com/g/2005")))
          {
            if ((this.item == null) || (paramAttributes.getValue("startTime").equalsIgnoreCase("")))
              break;
            this.item.setPubdate(paramAttributes.getValue("startTime"));
            this.wasGoogleDate = true;
            break;
          }
          if (paramString2.equalsIgnoreCase("enclosure"))
          {
            if ((this.item == null) || (paramAttributes.getValue("url") == null) || (paramAttributes.getValue("url").equals("")) || (this.item.hasMedia()))
              break;
            this.item.addMediaItem(paramAttributes.getValue("url"), paramAttributes.getValue("type"));
            break;
          }
          if ((paramString2.equalsIgnoreCase("player")) && (paramString1.equalsIgnoreCase("http://search.yahoo.com/mrss/")))
          {
            if ((this.item == null) || (paramAttributes.getValue("url").equals("")) || (!this.item.getMediaUrl().equals("")))
              break;
            this.item.addMediaItem(paramAttributes.getValue("url"), this.item.getMediaType());
            break;
          }
          if ((!paramString2.equalsIgnoreCase("ITEM")) && (!paramString2.equalsIgnoreCase("ENTRY")))
            break;
          this.item = new FeedItem();
          this.item.setFeedUrl(this.feedUrl);
          break;
        }
      }
      catch (NullPointerException localNullPointerException2)
      {
        break label110;
      }
    }
    catch (NullPointerException localNullPointerException1)
    {
      break label67;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.NewsPlugin.FeedHandler
 * JD-Core Version:    0.6.0
 */