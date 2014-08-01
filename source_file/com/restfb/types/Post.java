package com.restfb.types;

import com.restfb.Facebook;
import com.restfb.util.DateUtils;
import com.restfb.util.ReflectionUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Post extends NamedFacebookType
{
  private static final long serialVersionUID = 2L;

  @Facebook
  private List<Action> actions = new ArrayList();

  @Facebook
  private NamedFacebookType application;

  @Facebook
  private String attribution;

  @Facebook
  private String caption;

  @Facebook
  private Comments comments;

  @Facebook("created_time")
  private String createdTime;

  @Facebook
  private String description;

  @Facebook
  private CategorizedFacebookType from;

  @Facebook
  private String icon;

  @Facebook
  private Likes likes;

  @Facebook("likes")
  private Long likesCount;

  @Facebook
  private String link;

  @Facebook
  private String message;

  @Facebook("object_id")
  private String objectId;

  @Facebook
  private String picture;

  @Facebook
  private Place place;

  @Facebook
  private Privacy privacy;

  @Facebook
  private List<Property> properties = new ArrayList();

  @Facebook
  private String source;

  @Facebook
  private List<NamedFacebookType> to = new ArrayList();

  @Facebook
  private String type;

  @Facebook("updated_time")
  private String updatedTime;

  public List<Action> getActions()
  {
    return Collections.unmodifiableList(this.actions);
  }

  public NamedFacebookType getApplication()
  {
    return this.application;
  }

  public String getAttribution()
  {
    return this.attribution;
  }

  public String getCaption()
  {
    return this.caption;
  }

  public Comments getComments()
  {
    return this.comments;
  }

  public Date getCreatedTime()
  {
    return DateUtils.toDateFromLongFormat(this.createdTime);
  }

  public String getDescription()
  {
    return this.description;
  }

  public CategorizedFacebookType getFrom()
  {
    return this.from;
  }

  public String getIcon()
  {
    return this.icon;
  }

  public Likes getLikes()
  {
    return this.likes;
  }

  public Long getLikesCount()
  {
    if (getLikes() != null)
      return getLikes().getCount();
    return this.likesCount;
  }

  public String getLink()
  {
    return this.link;
  }

  public String getMessage()
  {
    return this.message;
  }

  public String getObjectId()
  {
    return this.objectId;
  }

  public String getPicture()
  {
    return this.picture;
  }

  public Place getPlace()
  {
    return this.place;
  }

  public Privacy getPrivacy()
  {
    return this.privacy;
  }

  public List<Property> getProperties()
  {
    return Collections.unmodifiableList(this.properties);
  }

  public String getSource()
  {
    return this.source;
  }

  public List<NamedFacebookType> getTo()
  {
    return Collections.unmodifiableList(this.to);
  }

  public String getType()
  {
    return this.type;
  }

  public Date getUpdatedTime()
  {
    return DateUtils.toDateFromLongFormat(this.updatedTime);
  }

  public static class Action
    implements Serializable
  {
    private static final long serialVersionUID = 1L;

    @Facebook
    private String link;

    @Facebook
    private String name;

    public boolean equals(Object paramObject)
    {
      return ReflectionUtils.equals(this, paramObject);
    }

    public String getLink()
    {
      return this.link;
    }

    public String getName()
    {
      return this.name;
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

  public static class Comments
    implements Serializable
  {
    private static final long serialVersionUID = 1L;

    @Facebook
    private Long count;

    @Facebook
    private List<Comment> data = new ArrayList();

    public boolean equals(Object paramObject)
    {
      return ReflectionUtils.equals(this, paramObject);
    }

    public Long getCount()
    {
      return this.count;
    }

    public List<Comment> getData()
    {
      return Collections.unmodifiableList(this.data);
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

  public static class Likes
    implements Serializable
  {
    private static final long serialVersionUID = 1L;

    @Facebook
    private Long count;

    @Facebook
    private List<NamedFacebookType> data = new ArrayList();

    public boolean equals(Object paramObject)
    {
      return ReflectionUtils.equals(this, paramObject);
    }

    public Long getCount()
    {
      return this.count;
    }

    public List<NamedFacebookType> getData()
    {
      return Collections.unmodifiableList(this.data);
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

  public static class Place extends NamedFacebookType
  {
    private static final long serialVersionUID = 1L;

    @Facebook
    private Location location;

    public Location getLocation()
    {
      return this.location;
    }
  }

  public static class Privacy
    implements Serializable
  {
    private static final long serialVersionUID = 1L;

    @Facebook
    private String deny;

    @Facebook
    private String description;

    @Facebook
    private String friends;

    @Facebook
    private String networks;

    @Facebook
    private String value;

    public boolean equals(Object paramObject)
    {
      return ReflectionUtils.equals(this, paramObject);
    }

    public String getDeny()
    {
      return this.deny;
    }

    public String getDescription()
    {
      return this.description;
    }

    public String getFriends()
    {
      return this.friends;
    }

    public String getNetworks()
    {
      return this.networks;
    }

    public String getValue()
    {
      return this.value;
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

  public static class Property
    implements Serializable
  {
    private static final long serialVersionUID = 1L;

    @Facebook
    private String href;

    @Facebook
    private String name;

    @Facebook
    private String text;

    public boolean equals(Object paramObject)
    {
      return ReflectionUtils.equals(this, paramObject);
    }

    public String getHref()
    {
      return this.href;
    }

    public String getName()
    {
      return this.name;
    }

    public String getText()
    {
      return this.text;
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
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.types.Post
 * JD-Core Version:    0.6.0
 */