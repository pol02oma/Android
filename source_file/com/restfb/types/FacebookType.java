package com.restfb.types;

import com.restfb.Facebook;
import com.restfb.util.ReflectionUtils;
import com.restfb.util.StringUtils;
import java.io.Serializable;

public class FacebookType
  implements Serializable
{
  private static final long serialVersionUID = 1L;

  @Facebook
  private String id;

  @Facebook
  private Metadata metadata;

  @Facebook
  private String type;

  public boolean equals(Object paramObject)
  {
    return ReflectionUtils.equals(this, paramObject);
  }

  public String getId()
  {
    return this.id;
  }

  public Metadata getMetadata()
  {
    return this.metadata;
  }

  public String getType()
  {
    return this.type;
  }

  public int hashCode()
  {
    return ReflectionUtils.hashCode(this);
  }

  public String toString()
  {
    return ReflectionUtils.toString(this);
  }

  public static class Metadata
    implements Serializable
  {
    private static final long serialVersionUID = 1L;

    @Facebook
    private Connections connections;

    public boolean equals(Object paramObject)
    {
      return ReflectionUtils.equals(this, paramObject);
    }

    public Connections getConnections()
    {
      return this.connections;
    }

    public int hashCode()
    {
      return ReflectionUtils.hashCode(this);
    }

    public String toString()
    {
      return ReflectionUtils.toString(this);
    }

    public static class Connections
      implements Serializable
    {
      private static final long serialVersionUID = 1L;

      @Facebook
      private String activities;

      @Facebook
      private String albums;

      @Facebook
      private String books;

      @Facebook
      private String events;

      @Facebook
      private String family;

      @Facebook
      private String feed;

      @Facebook
      private String friends;

      @Facebook
      private String groups;

      @Facebook
      private String home;

      @Facebook
      private String inbox;

      @Facebook
      private String interests;

      @Facebook
      private String likes;

      @Facebook
      private String links;

      @Facebook
      private String movies;

      @Facebook
      private String music;

      @Facebook
      private String notes;

      @Facebook
      private String outbox;

      @Facebook
      private String photos;

      @Facebook
      private String picture;

      @Facebook
      private String posts;

      @Facebook
      private String statuses;

      @Facebook
      private String tagged;

      @Facebook
      private String television;

      @Facebook
      private String updates;

      @Facebook
      private String videos;

      public boolean equals(Object paramObject)
      {
        return ReflectionUtils.equals(this, paramObject);
      }

      public String getActivities()
      {
        return this.activities;
      }

      public String getAlbums()
      {
        return this.albums;
      }

      public String getBooks()
      {
        return this.books;
      }

      public String getEvents()
      {
        return this.events;
      }

      public String getFamily()
      {
        return this.family;
      }

      public String getFeed()
      {
        return this.feed;
      }

      public String getFriends()
      {
        return this.friends;
      }

      public String getGroups()
      {
        return this.groups;
      }

      public String getHome()
      {
        return this.home;
      }

      public String getInbox()
      {
        return this.inbox;
      }

      public String getInterests()
      {
        return this.interests;
      }

      public String getLikes()
      {
        return this.likes;
      }

      public String getLinks()
      {
        return this.links;
      }

      public String getMovies()
      {
        return this.movies;
      }

      public String getMusic()
      {
        return this.music;
      }

      public String getNotes()
      {
        return this.notes;
      }

      public String getOutbox()
      {
        return this.outbox;
      }

      public String getPhotos()
      {
        return this.photos;
      }

      public String getPicture()
      {
        return this.picture;
      }

      public String getPosts()
      {
        return this.posts;
      }

      public String getStatuses()
      {
        return this.statuses;
      }

      public String getTagged()
      {
        return this.tagged;
      }

      public String getTelevision()
      {
        return this.television;
      }

      public String getUpdates()
      {
        return this.updates;
      }

      public String getVideos()
      {
        return this.videos;
      }

      public Boolean hasActivities()
      {
        if (!StringUtils.isBlank(this.activities));
        for (boolean bool = true; ; bool = false)
          return Boolean.valueOf(bool);
      }

      public Boolean hasAlbums()
      {
        if (!StringUtils.isBlank(this.albums));
        for (boolean bool = true; ; bool = false)
          return Boolean.valueOf(bool);
      }

      public Boolean hasBooks()
      {
        if (!StringUtils.isBlank(this.books));
        for (boolean bool = true; ; bool = false)
          return Boolean.valueOf(bool);
      }

      public Boolean hasEvents()
      {
        if (!StringUtils.isBlank(this.events));
        for (boolean bool = true; ; bool = false)
          return Boolean.valueOf(bool);
      }

      public Boolean hasFamily()
      {
        if (!StringUtils.isBlank(this.family));
        for (boolean bool = true; ; bool = false)
          return Boolean.valueOf(bool);
      }

      public Boolean hasFeed()
      {
        if (!StringUtils.isBlank(this.feed));
        for (boolean bool = true; ; bool = false)
          return Boolean.valueOf(bool);
      }

      public Boolean hasFriends()
      {
        if (!StringUtils.isBlank(this.friends));
        for (boolean bool = true; ; bool = false)
          return Boolean.valueOf(bool);
      }

      public Boolean hasGroups()
      {
        if (!StringUtils.isBlank(this.groups));
        for (boolean bool = true; ; bool = false)
          return Boolean.valueOf(bool);
      }

      public Boolean hasHome()
      {
        if (!StringUtils.isBlank(this.home));
        for (boolean bool = true; ; bool = false)
          return Boolean.valueOf(bool);
      }

      public Boolean hasInbox()
      {
        if (!StringUtils.isBlank(this.inbox));
        for (boolean bool = true; ; bool = false)
          return Boolean.valueOf(bool);
      }

      public Boolean hasInterests()
      {
        if (!StringUtils.isBlank(this.interests));
        for (boolean bool = true; ; bool = false)
          return Boolean.valueOf(bool);
      }

      public Boolean hasLikes()
      {
        if (!StringUtils.isBlank(this.likes));
        for (boolean bool = true; ; bool = false)
          return Boolean.valueOf(bool);
      }

      public Boolean hasLinks()
      {
        if (!StringUtils.isBlank(this.links));
        for (boolean bool = true; ; bool = false)
          return Boolean.valueOf(bool);
      }

      public Boolean hasMovies()
      {
        if (!StringUtils.isBlank(this.movies));
        for (boolean bool = true; ; bool = false)
          return Boolean.valueOf(bool);
      }

      public Boolean hasMusic()
      {
        if (!StringUtils.isBlank(this.music));
        for (boolean bool = true; ; bool = false)
          return Boolean.valueOf(bool);
      }

      public Boolean hasNotes()
      {
        if (!StringUtils.isBlank(this.notes));
        for (boolean bool = true; ; bool = false)
          return Boolean.valueOf(bool);
      }

      public Boolean hasOutbox()
      {
        if (!StringUtils.isBlank(this.outbox));
        for (boolean bool = true; ; bool = false)
          return Boolean.valueOf(bool);
      }

      public Boolean hasPhotos()
      {
        if (!StringUtils.isBlank(this.photos));
        for (boolean bool = true; ; bool = false)
          return Boolean.valueOf(bool);
      }

      public Boolean hasPicture()
      {
        if (!StringUtils.isBlank(this.picture));
        for (boolean bool = true; ; bool = false)
          return Boolean.valueOf(bool);
      }

      public Boolean hasPosts()
      {
        if (!StringUtils.isBlank(this.posts));
        for (boolean bool = true; ; bool = false)
          return Boolean.valueOf(bool);
      }

      public Boolean hasStatuses()
      {
        if (!StringUtils.isBlank(this.statuses));
        for (boolean bool = true; ; bool = false)
          return Boolean.valueOf(bool);
      }

      public Boolean hasTagged()
      {
        if (!StringUtils.isBlank(this.tagged));
        for (boolean bool = true; ; bool = false)
          return Boolean.valueOf(bool);
      }

      public Boolean hasTelevision()
      {
        if (!StringUtils.isBlank(this.television));
        for (boolean bool = true; ; bool = false)
          return Boolean.valueOf(bool);
      }

      public Boolean hasUpdates()
      {
        if (!StringUtils.isBlank(this.updates));
        for (boolean bool = true; ; bool = false)
          return Boolean.valueOf(bool);
      }

      public Boolean hasVideos()
      {
        if (!StringUtils.isBlank(this.videos));
        for (boolean bool = true; ; bool = false)
          return Boolean.valueOf(bool);
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
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.types.FacebookType
 * JD-Core Version:    0.6.0
 */