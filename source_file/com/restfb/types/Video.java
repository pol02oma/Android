package com.restfb.types;

import com.restfb.Facebook;
import com.restfb.util.DateUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Video extends NamedFacebookType
{
  private static final long serialVersionUID = 1L;

  @Facebook
  private List<Comment> comments = new ArrayList();

  @Facebook("created_time")
  private String createdTime;

  @Facebook
  private String description;

  @Facebook("embed_html")
  private String embedHtml;

  @Facebook
  private CategorizedFacebookType from;

  @Facebook
  private String icon;

  @Facebook
  private Integer length;

  @Facebook
  @Deprecated
  private String message;

  @Facebook
  private String picture;

  @Facebook
  private String source;

  @Facebook
  private List<NamedFacebookType> tags = new ArrayList();

  @Facebook("updated_time")
  private String updatedTime;

  public List<Comment> getComments()
  {
    return Collections.unmodifiableList(this.comments);
  }

  public Date getCreatedTime()
  {
    return DateUtils.toDateFromLongFormat(this.createdTime);
  }

  public String getDescription()
  {
    return this.description;
  }

  public String getEmbedHtml()
  {
    return this.embedHtml;
  }

  public CategorizedFacebookType getFrom()
  {
    return this.from;
  }

  public String getIcon()
  {
    return this.icon;
  }

  public Integer getLength()
  {
    return this.length;
  }

  public String getMessage()
  {
    return this.message;
  }

  public String getPicture()
  {
    return this.picture;
  }

  public String getSource()
  {
    return this.source;
  }

  public List<NamedFacebookType> getTags()
  {
    return Collections.unmodifiableList(this.tags);
  }

  public Date getUpdatedTime()
  {
    return DateUtils.toDateFromLongFormat(this.updatedTime);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.types.Video
 * JD-Core Version:    0.6.0
 */