package twitter4j.internal.json;

import twitter4j.ResponseList;
import twitter4j.TwitterException;
import twitter4j.api.HelpResources.Language;
import twitter4j.conf.Configuration;
import twitter4j.internal.http.HttpResponse;
import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONException;
import twitter4j.internal.org.json.JSONObject;

public class LanguageJSONImpl
  implements HelpResources.Language
{
  private String code;
  private String name;
  private String status;

  LanguageJSONImpl(JSONObject paramJSONObject)
    throws TwitterException
  {
    init(paramJSONObject);
  }

  static ResponseList<HelpResources.Language> createLanguageList(HttpResponse paramHttpResponse, Configuration paramConfiguration)
    throws TwitterException
  {
    return createLanguageList(paramHttpResponse.asJSONArray(), paramHttpResponse, paramConfiguration);
  }

  static ResponseList<HelpResources.Language> createLanguageList(JSONArray paramJSONArray, HttpResponse paramHttpResponse, Configuration paramConfiguration)
    throws TwitterException
  {
    if (paramConfiguration.isJSONStoreEnabled())
      DataObjectFactoryUtil.clearThreadLocalMap();
    while (true)
    {
      int j;
      try
      {
        int i = paramJSONArray.length();
        ResponseListImpl localResponseListImpl = new ResponseListImpl(i, paramHttpResponse);
        j = 0;
        if (j >= i)
          continue;
        JSONObject localJSONObject = paramJSONArray.getJSONObject(j);
        LanguageJSONImpl localLanguageJSONImpl = new LanguageJSONImpl(localJSONObject);
        localResponseListImpl.add(localLanguageJSONImpl);
        if (paramConfiguration.isJSONStoreEnabled())
        {
          DataObjectFactoryUtil.registerJSONObject(localLanguageJSONImpl, localJSONObject);
          break label123;
          if (!paramConfiguration.isJSONStoreEnabled())
            continue;
          DataObjectFactoryUtil.registerJSONObject(localResponseListImpl, paramJSONArray);
          return localResponseListImpl;
        }
      }
      catch (JSONException localJSONException)
      {
        throw new TwitterException(localJSONException);
      }
      catch (TwitterException localTwitterException)
      {
        throw localTwitterException;
      }
      label123: j++;
    }
  }

  private void init(JSONObject paramJSONObject)
    throws TwitterException
  {
    try
    {
      this.name = paramJSONObject.getString("name");
      this.code = paramJSONObject.getString("code");
      this.status = paramJSONObject.getString("status");
      return;
    }
    catch (JSONException localJSONException)
    {
    }
    throw new TwitterException(localJSONException.getMessage() + ":" + paramJSONObject.toString(), localJSONException);
  }

  public String getCode()
  {
    return this.code;
  }

  public String getName()
  {
    return this.name;
  }

  public String getStatus()
  {
    return this.status;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.internal.json.LanguageJSONImpl
 * JD-Core Version:    0.6.0
 */