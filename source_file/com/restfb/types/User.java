package com.restfb.types;

import com.restfb.Facebook;
import com.restfb.util.DateUtils;
import com.restfb.util.ReflectionUtils;
import com.restfb.util.StringUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class User extends NamedFacebookType
{
  private static final long serialVersionUID = 1L;

  @Facebook
  private String about;

  @Facebook
  private String bio;

  @Facebook
  private String birthday;

  @Facebook
  private List<Education> education = new ArrayList();

  @Facebook
  private String email;

  @Facebook("favorite_athletes")
  private List<NamedFacebookType> favoriteAthletes = new ArrayList();

  @Facebook("favorite_teams")
  private List<NamedFacebookType> favoriteTeams = new ArrayList();

  @Facebook("first_name")
  private String firstName;

  @Facebook
  private String gender;

  @Facebook
  private NamedFacebookType hometown;

  @Facebook("hometown")
  private String hometownAsString;

  @Facebook("interested_in")
  private List<String> interestedIn = new ArrayList();

  @Facebook
  private List<NamedFacebookType> languages = new ArrayList();

  @Facebook("last_name")
  private String lastName;

  @Facebook
  private String link;

  @Facebook
  private String locale;

  @Facebook
  private NamedFacebookType location;

  @Facebook("meeting_for")
  private List<String> meetingFor = new ArrayList();

  @Facebook("middle_name")
  private String middleName;

  @Facebook
  private String political;

  @Facebook
  private String quotes;

  @Facebook("relationship_status")
  private String relationshipStatus;

  @Facebook
  private String religion;

  @Facebook("significant_other")
  private NamedFacebookType significantOther;

  @Facebook
  private List<Sport> sports = new ArrayList();

  @Facebook("third_party_id")
  private String thirdPartyId;

  @Facebook
  private Double timezone;

  @Facebook("updated_time")
  private String updatedTime;

  @Facebook
  private String username;

  @Facebook
  private Boolean verified;

  @Facebook
  private String website;

  @Facebook
  private List<Work> work = new ArrayList();

  public String getAbout()
  {
    return this.about;
  }

  public String getBio()
  {
    return this.bio;
  }

  public String getBirthday()
  {
    return this.birthday;
  }

  public Date getBirthdayAsDate()
  {
    if ((StringUtils.isBlank(getBirthday())) || (getBirthday().split("/").length < 2))
      return null;
    return DateUtils.toDateFromShortFormat(this.birthday);
  }

  public List<Education> getEducation()
  {
    return Collections.unmodifiableList(this.education);
  }

  public String getEmail()
  {
    return this.email;
  }

  public List<NamedFacebookType> getFavoriteAthletes()
  {
    return Collections.unmodifiableList(this.favoriteAthletes);
  }

  public List<NamedFacebookType> getFavoriteTeams()
  {
    return Collections.unmodifiableList(this.favoriteTeams);
  }

  public String getFirstName()
  {
    return this.firstName;
  }

  public String getGender()
  {
    return this.gender;
  }

  public NamedFacebookType getHometown()
  {
    return this.hometown;
  }

  public String getHometownName()
  {
    if (getHometown() != null)
      return getHometown().getName();
    return this.hometownAsString;
  }

  public List<String> getInterestedIn()
  {
    return Collections.unmodifiableList(this.interestedIn);
  }

  public List<NamedFacebookType> getLanguages()
  {
    return Collections.unmodifiableList(this.languages);
  }

  public String getLastName()
  {
    return this.lastName;
  }

  public String getLink()
  {
    return this.link;
  }

  public String getLocale()
  {
    return this.locale;
  }

  public NamedFacebookType getLocation()
  {
    return this.location;
  }

  public List<String> getMeetingFor()
  {
    return Collections.unmodifiableList(this.meetingFor);
  }

  public String getMiddleName()
  {
    return this.middleName;
  }

  public String getPolitical()
  {
    return this.political;
  }

  public String getQuotes()
  {
    return this.quotes;
  }

  public String getRelationshipStatus()
  {
    return this.relationshipStatus;
  }

  public String getReligion()
  {
    return this.religion;
  }

  public NamedFacebookType getSignificantOther()
  {
    return this.significantOther;
  }

  public List<Sport> getSports()
  {
    return Collections.unmodifiableList(this.sports);
  }

  public String getThirdPartyId()
  {
    return this.thirdPartyId;
  }

  public Double getTimezone()
  {
    return this.timezone;
  }

  public Date getUpdatedTime()
  {
    return DateUtils.toDateFromLongFormat(this.updatedTime);
  }

  public String getUsername()
  {
    return this.username;
  }

  public Boolean getVerified()
  {
    return this.verified;
  }

  public String getWebsite()
  {
    return this.website;
  }

  public List<Work> getWork()
  {
    return Collections.unmodifiableList(this.work);
  }

  public static class Education
    implements Serializable
  {
    private static final long serialVersionUID = 2L;

    @Facebook
    private List<User.EducationClass> classes = new ArrayList();

    @Facebook
    private List<NamedFacebookType> concentration = new ArrayList();

    @Facebook
    private NamedFacebookType degree;

    @Facebook
    private NamedFacebookType school;

    @Facebook
    private String type;

    @Facebook
    private List<NamedFacebookType> with = new ArrayList();

    @Facebook
    private NamedFacebookType year;

    public boolean equals(Object paramObject)
    {
      return ReflectionUtils.equals(this, paramObject);
    }

    public List<User.EducationClass> getClasses()
    {
      return Collections.unmodifiableList(this.classes);
    }

    public List<NamedFacebookType> getConcentration()
    {
      return Collections.unmodifiableList(this.concentration);
    }

    public NamedFacebookType getDegree()
    {
      return this.degree;
    }

    public NamedFacebookType getSchool()
    {
      return this.school;
    }

    public String getType()
    {
      return this.type;
    }

    public List<NamedFacebookType> getWith()
    {
      return Collections.unmodifiableList(this.with);
    }

    public NamedFacebookType getYear()
    {
      return this.year;
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

  public static class EducationClass extends NamedFacebookType
  {
    private static final long serialVersionUID = 1L;

    @Facebook
    private String description;

    @Facebook
    private List<NamedFacebookType> with = new ArrayList();

    public String getDescription()
    {
      return this.description;
    }

    public List<NamedFacebookType> getWith()
    {
      return Collections.unmodifiableList(this.with);
    }
  }

  public static class Sport extends NamedFacebookType
  {
    private static final long serialVersionUID = 1L;

    @Facebook
    private List<NamedFacebookType> with = new ArrayList();

    public List<NamedFacebookType> getWith()
    {
      return Collections.unmodifiableList(this.with);
    }
  }

  public static class Work
    implements Serializable
  {
    private static final long serialVersionUID = 1L;

    @Facebook
    private String description;

    @Facebook
    private NamedFacebookType employer;

    @Facebook("end_date")
    private String endDate;

    @Facebook
    private NamedFacebookType location;

    @Facebook
    private NamedFacebookType position;

    @Facebook("start_date")
    private String startDate;

    @Facebook
    private List<NamedFacebookType> with = new ArrayList();

    public boolean equals(Object paramObject)
    {
      return ReflectionUtils.equals(this, paramObject);
    }

    public String getDescription()
    {
      return this.description;
    }

    public NamedFacebookType getEmployer()
    {
      return this.employer;
    }

    public Date getEndDate()
    {
      return DateUtils.toDateFromMonthYearFormat(this.endDate);
    }

    public NamedFacebookType getLocation()
    {
      return this.location;
    }

    public NamedFacebookType getPosition()
    {
      return this.position;
    }

    public Date getStartDate()
    {
      return DateUtils.toDateFromMonthYearFormat(this.startDate);
    }

    public List<NamedFacebookType> getWith()
    {
      return Collections.unmodifiableList(this.with);
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
 * Qualified Name:     com.restfb.types.User
 * JD-Core Version:    0.6.0
 */