package twitter4j.internal.json;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import twitter4j.Location;
import twitter4j.ResponseList;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.TwitterException;
import twitter4j.conf.Configuration;
import twitter4j.internal.http.HttpResponse;
import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

final class TrendsJSONImpl extends TwitterResponseImpl
  implements Trends, Serializable
{
  private static final long serialVersionUID = -7151479143843312309L;
  private Date asOf;
  private Location location;
  private Date trendAt;
  private Trend[] trends;

  TrendsJSONImpl(String paramString)
    throws TwitterException
  {
    this(paramString, false);
  }

  TrendsJSONImpl(String paramString, boolean paramBoolean)
    throws TwitterException
  {
    init(paramString, paramBoolean);
  }

  TrendsJSONImpl(Date paramDate1, Location paramLocation, Date paramDate2, Trend[] paramArrayOfTrend)
  {
    this.asOf = paramDate1;
    this.location = paramLocation;
    this.trendAt = paramDate2;
    this.trends = paramArrayOfTrend;
  }

  TrendsJSONImpl(HttpResponse paramHttpResponse, Configuration paramConfiguration)
    throws TwitterException
  {
    super(paramHttpResponse);
    init(paramHttpResponse.asString(), paramConfiguration.isJSONStoreEnabled());
    if (paramConfiguration.isJSONStoreEnabled())
    {
      DataObjectFactoryUtil.clearThreadLocalMap();
      DataObjectFactoryUtil.registerJSONObject(this, paramHttpResponse.asString());
    }
  }

  static ResponseList<Trends> createTrendsList(HttpResponse paramHttpResponse, boolean paramBoolean)
    throws TwitterException
  {
    JSONObject localJSONObject1 = paramHttpResponse.asJSONObject();
    ResponseListImpl localResponseListImpl;
    while (true)
    {
      Date localDate;
      Location localLocation;
      String str;
      Trend[] arrayOfTrend;
      try
      {
        localDate = z_T4JInternalParseUtil.parseTrendsDate(localJSONObject1.getString("as_of"));
        JSONObject localJSONObject2 = localJSONObject1.getJSONObject("trends");
        localLocation = extractLocation(localJSONObject1, paramBoolean);
        localResponseListImpl = new ResponseListImpl(localJSONObject2.length(), paramHttpResponse);
        Iterator localIterator = localJSONObject2.keys();
        if (!localIterator.hasNext())
          break;
        str = (String)localIterator.next();
        arrayOfTrend = jsonArrayToTrendArray(localJSONObject2.getJSONArray(str), paramBoolean);
        if (str.length() == 19)
        {
          localResponseListImpl.add(new TrendsJSONImpl(localDate, localLocation, z_T4JInternalParseUtil.getDate(str, "yyyy-MM-dd HH:mm:ss"), arrayOfTrend));
          continue;
        }
      }
      catch (JSONException localJSONException)
      {
        throw new TwitterException(localJSONException.getMessage() + ":" + paramHttpResponse.asString(), localJSONException);
      }
      if (str.length() == 16)
      {
        localResponseListImpl.add(new TrendsJSONImpl(localDate, localLocation, z_T4JInternalParseUtil.getDate(str, "yyyy-MM-dd HH:mm"), arrayOfTrend));
        continue;
      }
      if (str.length() != 10)
        continue;
      localResponseListImpl.add(new TrendsJSONImpl(localDate, localLocation, z_T4JInternalParseUtil.getDate(str, "yyyy-MM-dd"), arrayOfTrend));
    }
    Collections.sort(localResponseListImpl);
    return localResponseListImpl;
  }

  private static Location extractLocation(JSONObject paramJSONObject, boolean paramBoolean)
    throws TwitterException
  {
    if (paramJSONObject.isNull("locations"))
      return null;
    try
    {
      ResponseList localResponseList = LocationJSONImpl.createLocationList(paramJSONObject.getJSONArray("locations"), paramBoolean);
      if (localResponseList.size() != 0)
        return (Location)localResponseList.get(0);
    }
    catch (JSONException localJSONException)
    {
      throw new AssertionError("locations can't be null");
    }
    return null;
  }

  private static Trend[] jsonArrayToTrendArray(JSONArray paramJSONArray, boolean paramBoolean)
    throws JSONException
  {
    Trend[] arrayOfTrend = new Trend[paramJSONArray.length()];
    for (int i = 0; i < paramJSONArray.length(); i++)
      arrayOfTrend[i] = new TrendJSONImpl(paramJSONArray.getJSONObject(i), paramBoolean);
    return arrayOfTrend;
  }

  public int compareTo(Trends paramTrends)
  {
    return this.trendAt.compareTo(paramTrends.getTrendAt());
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    Trends localTrends;
    do
    {
      return true;
      if (!(paramObject instanceof Serializable))
        return false;
      localTrends = (Serializable)paramObject;
      if (this.asOf != null)
      {
        if (this.asOf.equals(localTrends.getAsOf()));
      }
      else
        do
          return false;
        while (localTrends.getAsOf() != null);
      if (this.trendAt != null)
        if (this.trendAt.equals(localTrends.getTrendAt()))
          continue;
      do
        return false;
      while (localTrends.getTrendAt() != null);
    }
    while (Arrays.equals(this.trends, localTrends.getTrends()));
    return false;
  }

  public Date getAsOf()
  {
    return this.asOf;
  }

  public Location getLocation()
  {
    return this.location;
  }

  public Date getTrendAt()
  {
    return this.trendAt;
  }

  public Trend[] getTrends()
  {
    return this.trends;
  }

  public int hashCode()
  {
    int i;
    int j;
    if (this.asOf != null)
    {
      i = this.asOf.hashCode();
      j = i * 31;
      if (this.trendAt == null)
        break label77;
    }
    label77: for (int k = this.trendAt.hashCode(); ; k = 0)
    {
      int m = 31 * (j + k);
      Trend[] arrayOfTrend = this.trends;
      int n = 0;
      if (arrayOfTrend != null)
        n = Arrays.hashCode(this.trends);
      return m + n;
      i = 0;
      break;
    }
  }

  void init(String paramString, boolean paramBoolean)
    throws TwitterException
  {
    while (true)
    {
      try
      {
        if (paramString.startsWith("["))
        {
          JSONArray localJSONArray1 = new JSONArray(paramString);
          if (localJSONArray1.length() <= 0)
            continue;
          localJSONObject = localJSONArray1.getJSONObject(0);
          this.asOf = z_T4JInternalParseUtil.parseTrendsDate(localJSONObject.getString("as_of"));
          this.location = extractLocation(localJSONObject, paramBoolean);
          JSONArray localJSONArray2 = localJSONObject.getJSONArray("trends");
          this.trendAt = this.asOf;
          this.trends = jsonArrayToTrendArray(localJSONArray2, paramBoolean);
          return;
          throw new TwitterException("No trends found on the specified woeid");
        }
      }
      catch (JSONException localJSONException)
      {
        throw new TwitterException(localJSONException.getMessage(), localJSONException);
      }
      JSONObject localJSONObject = new JSONObject(paramString);
    }
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("TrendsJSONImpl{asOf=").append(this.asOf).append(", trendAt=").append(this.trendAt).append(", trends=");
    if (this.trends == null);
    for (Object localObject = null; ; localObject = Arrays.asList(this.trends))
      return localObject + '}';
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.internal.json.TrendsJSONImpl
 * JD-Core Version:    0.6.0
 */