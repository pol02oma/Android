package com.ibuildapp.romanblack.PhotoGalleryPlugin.entities;

import java.io.Serializable;

public class ImageItem
  implements Serializable
{
  private String description = "";
  private long id = 0L;
  private String imagePath = "";
  private String imageThumbPath = "";
  private String imageUrl = "";
  private boolean liked = false;
  private int likesCount = 0;
  private String permalinkUrl = "";
  private String title = "";
  private int totalComments = 0;

  public String getDescription()
  {
    return this.description;
  }

  public long getId()
  {
    return this.id;
  }

  public String getImagePath()
  {
    return this.imagePath;
  }

  public String getImageThumbPath()
  {
    return this.imageThumbPath;
  }

  public String getImageUrl()
  {
    return this.imageUrl;
  }

  public int getLikesCount()
  {
    return this.likesCount;
  }

  public String getPermalinkUrl()
  {
    return this.permalinkUrl;
  }

  public String getTitle()
  {
    return this.title;
  }

  public int getTotalComments()
  {
    return this.totalComments;
  }

  public boolean isLiked()
  {
    return this.liked;
  }

  public void setDescription(String paramString)
  {
    this.description = paramString;
  }

  public void setId(long paramLong)
  {
    this.id = paramLong;
  }

  public void setImagePath(String paramString)
  {
    this.imagePath = paramString;
  }

  public void setImageThumbPath(String paramString)
  {
    this.imageThumbPath = paramString;
  }

  public void setImageUrl(String paramString)
  {
    this.imageUrl = paramString;
    if (this.permalinkUrl == null)
      this.permalinkUrl = paramString;
    if (this.permalinkUrl.equalsIgnoreCase(""))
      this.permalinkUrl = paramString;
  }

  public void setLiked(boolean paramBoolean)
  {
    this.liked = paramBoolean;
  }

  public void setLikesCount(int paramInt)
  {
    this.likesCount = paramInt;
  }

  public void setPermalinkUrl(String paramString)
  {
    this.permalinkUrl = paramString;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }

  public void setTotalComments(int paramInt)
  {
    this.totalComments = paramInt;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.ImageItem
 * JD-Core Version:    0.6.0
 */