package com.restfb.types;

import com.restfb.Facebook;
import com.restfb.util.DateUtils;
import com.restfb.util.ReflectionUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Photo extends NamedFacebookType
{
  private static final long serialVersionUID = 1L;

  @Facebook
  private List<Comment> comments = new ArrayList();

  @Facebook("created_time")
  private String createdTime;

  @Facebook
  private CategorizedFacebookType from;

  @Facebook
  private Integer height;

  @Facebook
  private String icon;

  @Facebook
  private List<Image> images = new ArrayList();

  @Facebook
  private List<NamedFacebookType> likes = new ArrayList();

  @Facebook
  private String link;

  @Facebook
  private String picture;

  @Facebook
  private Integer position;

  @Facebook
  private String source;

  @Facebook
  private List<Tag> tags = new ArrayList();

  @Facebook("updated_time")
  private String updatedTime;

  @Facebook
  private Integer width;

  public List<Comment> getComments()
  {
    return Collections.unmodifiableList(this.comments);
  }

  public Date getCreatedTime()
  {
    return DateUtils.toDateFromLongFormat(this.createdTime);
  }

  public CategorizedFacebookType getFrom()
  {
    return this.from;
  }

  public Integer getHeight()
  {
    return this.height;
  }

  public String getIcon()
  {
    return this.icon;
  }

  public List<Image> getImages()
  {
    return Collections.unmodifiableList(this.images);
  }

  public List<NamedFacebookType> getLikes()
  {
    return Collections.unmodifiableList(this.likes);
  }

  public String getLink()
  {
    return this.link;
  }

  public String getPicture()
  {
    return this.picture;
  }

  public Integer getPosition()
  {
    return this.position;
  }

  public String getSource()
  {
    return this.source;
  }

  public List<Tag> getTags()
  {
    return Collections.unmodifiableList(this.tags);
  }

  public Date getUpdatedTime()
  {
    return DateUtils.toDateFromLongFormat(this.updatedTime);
  }

  public Integer getWidth()
  {
    return this.width;
  }

  public static class Image
    implements Serializable
  {
    private static final long serialVersionUID = 1L;

    @Facebook
    private Integer height;

    @Facebook
    private String source;

    @Facebook
    private Integer width;

    public boolean equals(Object paramObject)
    {
      return ReflectionUtils.equals(this, paramObject);
    }

    public Integer getHeight()
    {
      return this.height;
    }

    public String getSource()
    {
      return this.source;
    }

    public Integer getWidth()
    {
      return this.width;
    }

    public int hashCode()
    {
      return ReflectionUtils.hashCode(this);
    }

    public String toString()
    {
      return ReflectionUtils.toString(this);
    }
  }

  public static class Tag extends NamedFacebookType
  {
    private static final long serialVersionUID = 1L;

    @Facebook("created_time")
    private String createdTime;

    @Facebook
    private Integer x;

    @Facebook
    private Integer y;

    public Date getCreatedTime()
    {
      return DateUtils.toDateFromLongFormat(this.createdTime);
    }

    public Integer getX()
    {
      return this.x;
    }

    public Integer getY()
    {
      return this.y;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.types.Photo
 * JD-Core Version:    0.6.0
 */