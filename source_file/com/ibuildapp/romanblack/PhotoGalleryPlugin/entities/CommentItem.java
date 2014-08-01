package com.ibuildapp.romanblack.PhotoGalleryPlugin.entities;

import java.io.Serializable;
import java.util.Date;

public class CommentItem
  implements Serializable
{
  private String author = "";
  private String avatarPath = "";
  private String avatarUrl = "";
  private int commentsCount = 0;
  private Date date = null;
  private long id = 0L;
  private long replyId = 0L;
  private String text = "";
  private long trackId = 0L;

  public String getAuthor()
  {
    return this.author;
  }

  public String getAvatarPath()
  {
    return this.avatarPath;
  }

  public String getAvatarUrl()
  {
    return this.avatarUrl;
  }

  public int getCommentsCount()
  {
    return this.commentsCount;
  }

  public Date getDate()
  {
    return this.date;
  }

  public long getId()
  {
    return this.id;
  }

  public long getReplyId()
  {
    return this.replyId;
  }

  public String getText()
  {
    return this.text;
  }

  public long getTrackId()
  {
    return this.trackId;
  }

  public void increaseComments()
  {
    this.commentsCount = (1 + this.commentsCount);
  }

  public void setAuthor(String paramString)
  {
    this.author = paramString;
  }

  public void setAvatarPath(String paramString)
  {
    this.avatarPath = paramString;
  }

  public void setAvatarUrl(String paramString)
  {
    this.avatarUrl = paramString;
  }

  public void setCommentsCount(int paramInt)
  {
    this.commentsCount = paramInt;
  }

  public void setDate(long paramLong)
  {
    this.date = new Date(paramLong);
  }

  public void setDate(String paramString)
  {
    this.date = new Date(Long.parseLong(paramString));
  }

  public void setDate(Date paramDate)
  {
    this.date = paramDate;
  }

  public void setId(long paramLong)
  {
    this.id = paramLong;
  }

  public void setReplyId(long paramLong)
  {
    this.replyId = paramLong;
  }

  public void setText(String paramString)
  {
    this.text = paramString;
  }

  public void setTrackId(long paramLong)
  {
    this.trackId = paramLong;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.CommentItem
 * JD-Core Version:    0.6.0
 */