package twitter4j.internal.json;

import java.util.Date;
import twitter4j.ResponseList;
import twitter4j.SavedSearch;
import twitter4j.TwitterException;
import twitter4j.conf.Configuration;
import twitter4j.internal.http.HttpResponse;
import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

final class SavedSearchJSONImpl extends TwitterResponseImpl
  implements SavedSearch
{
  private static final long serialVersionUID = 3083819860391598212L;
  private Date createdAt;
  private int id;
  private String name;
  private int position;
  private String query;

  SavedSearchJSONImpl(HttpResponse paramHttpResponse, Configuration paramConfiguration)
    throws TwitterException
  {
    super(paramHttpResponse);
    if (paramConfiguration.isJSONStoreEnabled())
      DataObjectFactoryUtil.clearThreadLocalMap();
    JSONObject localJSONObject = paramHttpResponse.asJSONObject();
    init(localJSONObject);
    if (paramConfiguration.isJSONStoreEnabled())
      DataObjectFactoryUtil.registerJSONObject(this, localJSONObject);
  }

  SavedSearchJSONImpl(JSONObject paramJSONObject)
    throws TwitterException
  {
    init(paramJSONObject);
  }

  static ResponseList<SavedSearch> createSavedSearchList(HttpResponse paramHttpResponse, Configuration paramConfiguration)
    throws TwitterException
  {
    if (paramConfiguration.isJSONStoreEnabled())
      DataObjectFactoryUtil.clearThreadLocalMap();
    JSONArray localJSONArray = paramHttpResponse.asJSONArray();
    while (true)
    {
      int i;
      try
      {
        ResponseListImpl localResponseListImpl = new ResponseListImpl(localJSONArray.length(), paramHttpResponse);
        i = 0;
        if (i >= localJSONArray.length())
          continue;
        JSONObject localJSONObject = localJSONArray.getJSONObject(i);
        SavedSearchJSONImpl localSavedSearchJSONImpl = new SavedSearchJSONImpl(localJSONObject);
        localResponseListImpl.add(localSavedSearchJSONImpl);
        if (paramConfiguration.isJSONStoreEnabled())
        {
          DataObjectFactoryUtil.registerJSONObject(localSavedSearchJSONImpl, localJSONObject);
          break label149;
          if (!paramConfiguration.isJSONStoreEnabled())
            continue;
          DataObjectFactoryUtil.registerJSONObject(localResponseListImpl, localJSONArray);
          return localResponseListImpl;
        }
      }
      catch (JSONException localJSONException)
      {
        throw new TwitterException(localJSONException.getMessage() + ":" + paramHttpResponse.asString(), localJSONException);
      }
      label149: i++;
    }
  }

  private void init(JSONObject paramJSONObject)
    throws TwitterException
  {
    this.createdAt = z_T4JInternalParseUtil.getDate("created_at", paramJSONObject, "EEE MMM dd HH:mm:ss z yyyy");
    this.query = z_T4JInternalParseUtil.getUnescapedString("query", paramJSONObject);
    this.position = z_T4JInternalParseUtil.getInt("position", paramJSONObject);
    this.name = z_T4JInternalParseUtil.getUnescapedString("name", paramJSONObject);
    this.id = z_T4JInternalParseUtil.getInt("id", paramJSONObject);
  }

  public int compareTo(SavedSearch paramSavedSearch)
  {
    return this.id - paramSavedSearch.getId();
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    SavedSearch localSavedSearch;
    do
    {
      return true;
      if (!(paramObject instanceof SavedSearch))
        return false;
      localSavedSearch = (SavedSearch)paramObject;
    }
    while (this.id == localSavedSearch.getId());
    return false;
  }

  public Date getCreatedAt()
  {
    return this.createdAt;
  }

  public int getId()
  {
    return this.id;
  }

  public String getName()
  {
    return this.name;
  }

  public int getPosition()
  {
    return this.position;
  }

  public String getQuery()
  {
    return this.query;
  }

  public int hashCode()
  {
    return 31 * (31 * (31 * (31 * this.createdAt.hashCode() + this.query.hashCode()) + this.position) + this.name.hashCode()) + this.id;
  }

  public String toString()
  {
    return "SavedSearchJSONImpl{createdAt=" + this.createdAt + ", query='" + this.query + '\'' + ", position=" + this.position + ", name='" + this.name + '\'' + ", id=" + this.id + '}';
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.internal.json.SavedSearchJSONImpl
 * JD-Core Version:    0.6.0
 */