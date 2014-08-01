package com.ibuildapp.romanblack.NewsPlugin;

import android.util.Log;
import android.util.Xml;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ArrayList<Lcom.ibuildapp.romanblack.NewsPlugin.FeedItem;>;
import java.util.Iterator;

public class FeedParser
{
  private String encoding = "";
  private URL feedUrl = null;
  private String url = null;

  FeedParser(String paramString)
  {
    try
    {
      if ("http://ibuildapp.com/feed/".equals(paramString))
        paramString = paramString + "?no_redirect";
      this.feedUrl = new URL(paramString);
      this.url = paramString;
      return;
    }
    catch (MalformedURLException localMalformedURLException)
    {
    }
  }

  private String parseEncoding(String paramString)
  {
    String str = "UTF-8";
    if (paramString.contains("ISO-8859-1"))
      str = "ISO-8859-1";
    do
    {
      return str;
      if (paramString.contains("US-ASCII"))
        return "US-ASCII";
      if (paramString.contains("UTF-16"))
        return "UTF-16";
    }
    while (!paramString.contains("windows-1251"));
    return "windows-1251";
  }

  public String getEncoding()
  {
    return this.encoding;
  }

  public ArrayList<FeedItem> parseFeed()
  {
    FeedHandler localFeedHandler = new FeedHandler();
    localFeedHandler.setFeedUrl(this.url);
    Object localObject1 = "";
    try
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      BufferedReader localBufferedReader1 = new BufferedReader(new InputStreamReader(this.feedUrl.openStream()));
      while (true)
      {
        StringBuilder localStringBuilder2;
        Iterator localIterator;
        try
        {
          String str3 = localBufferedReader1.readLine();
          if (str3 == null)
            break label218;
          localStringBuilder1.append(str3);
          localStringBuilder1.append("\n");
          continue;
        }
        catch (Exception localException1)
        {
          this.encoding = parseEncoding((String)localObject1);
          localObject2 = "";
          localStringBuilder2 = new StringBuilder();
          BufferedReader localBufferedReader2 = new BufferedReader(new InputStreamReader(this.feedUrl.openStream(), getEncoding()));
          try
          {
            String str1 = localBufferedReader2.readLine();
            if (str1 == null)
              break label230;
            localStringBuilder2.append(str1);
            localStringBuilder2.append("\n");
            continue;
          }
          catch (Exception localException3)
          {
          }
          this.encoding = parseEncoding((String)localObject2);
          Xml.parse((String)localObject2, localFeedHandler);
          localIterator = localFeedHandler.getItems().iterator();
          if (!localIterator.hasNext())
            break;
        }
        ((FeedItem)localIterator.next()).setEncoding(getEncoding());
        continue;
        label218: String str4 = localStringBuilder1.toString();
        localObject1 = str4;
        continue;
        label230: String str2 = localStringBuilder2.toString();
        Object localObject2 = str2;
      }
    }
    catch (Exception localException2)
    {
      while (true)
        Log.w("", "");
    }
    return (ArrayList<FeedItem>)(ArrayList<FeedItem>)localFeedHandler.getItems();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.NewsPlugin.FeedParser
 * JD-Core Version:    0.6.0
 */