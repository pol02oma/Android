package com.ibuildapp.romanblack.PhotoGalleryPlugin.utils;

import android.util.Log;
import android.util.Xml;
import android.util.Xml.Encoding;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.ImageItem;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class FeedParser
{
  private URL feedUrl = null;

  FeedParser()
  {
  }

  public FeedParser(String paramString)
  {
    try
    {
      this.feedUrl = new URL(paramString);
      return;
    }
    catch (MalformedURLException localMalformedURLException)
    {
    }
  }

  public ArrayList<ImageItem> parseFeed()
  {
    FeedHandler localFeedHandler = new FeedHandler();
    try
    {
      Xml.parse(this.feedUrl.openStream(), Xml.Encoding.UTF_8, localFeedHandler);
      return localFeedHandler.getItems();
    }
    catch (Exception localException)
    {
      while (true)
        Log.w("IMAGE FEED PARSER", localException);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.PhotoGalleryPlugin.utils.FeedParser
 * JD-Core Version:    0.6.0
 */