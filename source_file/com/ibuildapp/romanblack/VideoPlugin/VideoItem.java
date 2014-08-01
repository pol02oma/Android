package com.ibuildapp.romanblack.VideoPlugin;

import java.io.Serializable;

public class VideoItem
  implements Serializable
{
  private int color = -1;
  private String coverPath = "";
  private String coverUrl = "";
  private String description = "";
  private long id = 0L;
  private boolean liked = false;
  private int likesCount = 0;
  private String title = "";
  private int totalComments = 0;
  private String url = "";

  public String getCoverPath()
  {
    return this.coverPath;
  }

  public String getCoverUrl()
  {
    return this.coverUrl;
  }

  public String getDescription()
  {
    return this.description;
  }

  public long getId()
  {
    return this.id;
  }

  public int getLikesCount()
  {
    return this.likesCount;
  }

  public int getTextColor()
  {
    return this.color;
  }

  public String getTitle()
  {
    return this.title;
  }

  public int getTotalComments()
  {
    return this.totalComments;
  }

  public String getUrl()
  {
    return this.url;
  }

  public boolean isLiked()
  {
    return this.liked;
  }

  public void setCoverPath(String paramString)
  {
    this.coverPath = paramString;
  }

  public void setCoverUrl(String paramString)
  {
    this.coverUrl = paramString;
  }

  public void setDescription(String paramString)
  {
    this.description = paramString;
  }

  public void setId(long paramLong)
  {
    this.id = paramLong;
  }

  public void setLiked(boolean paramBoolean)
  {
    this.liked = paramBoolean;
  }

  public void setLikesCount(int paramInt)
  {
    this.likesCount = paramInt;
  }

  public void setTextColor(int paramInt)
  {
    this.color = paramInt;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }

  public void setTotalComments(int paramInt)
  {
    this.totalComments = paramInt;
  }

  public void setUrl(String paramString)
  {
    this.url = paramString;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.VideoPlugin.VideoItem
 * JD-Core Version:    0.6.0
 */