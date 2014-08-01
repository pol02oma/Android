package com.ibuildapp.romanblack.TableReservationPlugin.entity;

import com.appbuilder.sdk.android.authorization.entities.User.ACCOUNT_TYPES;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FanWallMessage
  implements Serializable
{
  private final String DATE_PATTERN = "d MMM yyyy HH:mm:ss Z";
  private String accountId = "";
  private User.ACCOUNT_TYPES accountType = User.ACCOUNT_TYPES.IBUILDAPP;
  private String author = "";
  private ArrayList<FanWallMessage> comments = new ArrayList();
  private Date date = null;
  private long id = 0L;
  private String imageCachePath = "";
  private String imageUrl = "";
  private float latitude = 1000.0F;
  private float longitude = 1000.0F;
  private long parentId = 0L;
  private long replyId = 0L;
  private String text = "";
  private int totalComments = 0;
  private String userAvatarCache = "";
  private String userAvatarUrl = "";

  public FanWallMessage()
  {
  }

  public FanWallMessage(String paramString1, String paramString2)
  {
    this.author = paramString1;
    this.text = paramString2;
  }

  public FanWallMessage(String paramString1, String paramString2, String paramString3)
    throws ParseException
  {
    this.author = paramString1;
    this.text = paramString2;
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("d MMM yyyy HH:mm:ss Z");
    try
    {
      this.date = localSimpleDateFormat.parse(paramString3);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
    }
    throw new ParseException(paramString3, 0);
  }

  public FanWallMessage(String paramString1, String paramString2, Date paramDate)
  {
    this.author = paramString1;
    this.text = paramString2;
    this.date = paramDate;
  }

  public void addComment(FanWallMessage paramFanWallMessage)
  {
    this.comments.add(paramFanWallMessage);
    this.totalComments = (1 + this.totalComments);
  }

  public void addComments(ArrayList<FanWallMessage> paramArrayList)
  {
    this.comments.addAll(paramArrayList);
    this.totalComments += paramArrayList.size();
  }

  public String getAccountId()
  {
    return this.accountId;
  }

  public User.ACCOUNT_TYPES getAccountType()
  {
    return this.accountType;
  }

  public String getAuthor()
  {
    return this.author;
  }

  public ArrayList<FanWallMessage> getComments()
  {
    return this.comments;
  }

  public Date getDate()
  {
    return this.date;
  }

  public long getId()
  {
    return this.id;
  }

  public String getImageCachePath()
  {
    return this.imageCachePath;
  }

  public String getImageUrl()
  {
    return this.imageUrl;
  }

  public float getLatitude()
  {
    return this.latitude;
  }

  public float getLongitude()
  {
    return this.longitude;
  }

  public long getParentId()
  {
    return this.parentId;
  }

  public long getReplyId()
  {
    return this.replyId;
  }

  public String getText()
  {
    return this.text;
  }

  public int getTotalComments()
  {
    return this.totalComments;
  }

  public String getUserAvatarCache()
  {
    return this.userAvatarCache;
  }

  public String getUserAvatarUrl()
  {
    return this.userAvatarUrl;
  }

  public boolean hasAvatar()
  {
    return this.userAvatarUrl.length() > 0;
  }

  public boolean hasImage()
  {
    return this.imageUrl.length() > 0;
  }

  public boolean hasImageCache()
  {
    return this.imageUrl.length() > 0;
  }

  public boolean hasUserAvatarCache()
  {
    return this.userAvatarCache.length() > 0;
  }

  public void setAccountId(String paramString)
  {
    this.accountId = paramString;
  }

  public void setAccountType(String paramString)
  {
    if (paramString.equalsIgnoreCase("facebook"))
    {
      this.accountType = User.ACCOUNT_TYPES.FACEBOOK;
      return;
    }
    if (paramString.equalsIgnoreCase("twitter"))
    {
      this.accountType = User.ACCOUNT_TYPES.TWITTER;
      return;
    }
    this.accountType = User.ACCOUNT_TYPES.IBUILDAPP;
  }

  public void setAuthor(String paramString)
  {
    this.author = paramString;
  }

  public void setComments(ArrayList<FanWallMessage> paramArrayList)
  {
    this.comments = paramArrayList;
  }

  public void setDate(String paramString)
    throws ParseException
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("d MMM yyyy HH:mm:ss Z");
    try
    {
      this.date = localSimpleDateFormat.parse(paramString);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
    }
    throw new ParseException(paramString, 0);
  }

  public void setDate(Date paramDate)
  {
    this.date = paramDate;
  }

  public void setId(long paramLong)
  {
    this.id = paramLong;
  }

  public void setImageCachePath(String paramString)
  {
    this.imageCachePath = paramString;
  }

  public void setImageUrl(String paramString)
  {
    this.imageUrl = paramString;
  }

  public void setParentId(long paramLong)
  {
    this.parentId = paramLong;
  }

  public void setPoint(float paramFloat1, float paramFloat2)
  {
    this.latitude = paramFloat1;
    this.longitude = paramFloat2;
  }

  public void setReplyId(long paramLong)
  {
    this.replyId = paramLong;
  }

  public void setText(String paramString)
  {
    this.text = paramString;
  }

  public void setTotalComments(int paramInt)
  {
    this.totalComments = paramInt;
  }

  public void setUserAvatarCache(String paramString)
  {
    this.userAvatarCache = paramString;
  }

  public void setUserAvatarUrl(String paramString)
  {
    this.userAvatarUrl = paramString;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.TableReservationPlugin.entity.FanWallMessage
 * JD-Core Version:    0.6.0
 */