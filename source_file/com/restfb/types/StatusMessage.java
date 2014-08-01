package com.restfb.types;

import com.restfb.Facebook;
import com.restfb.util.DateUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class StatusMessage extends NamedFacebookType
{
  private static final long serialVersionUID = 2L;

  @Facebook
  private List<Comment> comments = new ArrayList();

  @Facebook("comments")
  private EmptyComments emptyComments;

  @Facebook("likes")
  private EmptyLikes emptyLikes;

  @Facebook
  private NamedFacebookType from;

  @Facebook
  private List<NamedFacebookType> likes = new ArrayList();

  @Facebook
  private String message;

  @Facebook
  private String type;

  @Facebook("updated_time")
  private String updatedTime;

  public List<Comment> getComments()
  {
    return Collections.unmodifiableList(this.comments);
  }

  public NamedFacebookType getFrom()
  {
    return this.from;
  }

  public List<NamedFacebookType> getLikes()
  {
    return Collections.unmodifiableList(this.likes);
  }

  public String getMessage()
  {
    return this.message;
  }

  public String getType()
  {
    return this.type;
  }

  public Date getUpdatedTime()
  {
    return DateUtils.toDateFromLongFormat(this.updatedTime);
  }

  private static class EmptyComments
    implements Serializable
  {
    private static final long serialVersionUID = 1L;

    @Facebook
    private Long count;
  }

  private static class EmptyLikes
    implements Serializable
  {
    private static final long serialVersionUID = 1L;

    @Facebook
    private Long count;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.types.StatusMessage
 * JD-Core Version:    0.6.0
 */