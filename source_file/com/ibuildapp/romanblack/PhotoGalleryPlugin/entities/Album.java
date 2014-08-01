package com.ibuildapp.romanblack.PhotoGalleryPlugin.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Album
  implements Serializable
{
  private boolean allowComments = false;
  private boolean allowSharing = false;
  private boolean default_ = false;
  private long id = 0L;
  private ArrayList<ImageItem> images = new ArrayList();
  private String rssUrl = "";
  private String title = "";
  private String url = "";

  public long getId()
  {
    return this.id;
  }

  public ArrayList<ImageItem> getImages()
  {
    return this.images;
  }

  public String getRssUrl()
  {
    return this.rssUrl;
  }

  public String getTitle()
  {
    return this.title;
  }

  public String getUrl()
  {
    return this.url;
  }

  public boolean isAllowComments()
  {
    return this.allowComments;
  }

  public boolean isAllowSharing()
  {
    return this.allowSharing;
  }

  public boolean isDefault()
  {
    return this.default_;
  }

  public boolean isRSS()
  {
    if (this.rssUrl == null);
    do
      return false;
    while (this.rssUrl.length() < 1);
    return true;
  }

  public void setAllowComments(boolean paramBoolean)
  {
    this.allowComments = paramBoolean;
  }

  public void setAllowSharing(boolean paramBoolean)
  {
    this.allowSharing = paramBoolean;
  }

  public void setDefault(boolean paramBoolean)
  {
    this.default_ = paramBoolean;
  }

  public void setId(long paramLong)
  {
    this.id = paramLong;
  }

  public void setImages(ArrayList<ImageItem> paramArrayList)
  {
    this.images = paramArrayList;
  }

  public void setRssUrl(String paramString)
  {
    this.rssUrl = paramString;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }

  public void setUrl(String paramString)
  {
    this.url = paramString;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.Album
 * JD-Core Version:    0.6.0
 */