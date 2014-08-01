package com.restfb;

import com.restfb.exception.FacebookJsonMappingException;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonException;
import com.restfb.json.JsonObject;
import com.restfb.util.ReflectionUtils;
import com.restfb.util.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Connection<T>
  implements Iterable<List<T>>
{
  private Class<T> connectionType;
  private List<T> data;
  private FacebookClient facebookClient;
  private String nextPageUrl;
  private String previousPageUrl;

  public Connection(FacebookClient paramFacebookClient, String paramString, Class<T> paramClass)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramString == null)
      throw new FacebookJsonMappingException("You must supply non-null connection JSON.");
    JsonObject localJsonObject1;
    while (true)
    {
      JsonArray localJsonArray;
      int i;
      try
      {
        localJsonObject1 = new JsonObject(paramString);
        localJsonArray = localJsonObject1.getJsonArray("data");
        i = 0;
        if (i >= localJsonArray.length())
          break;
        if (paramClass.equals(JsonObject.class))
        {
          localObject = localJsonArray.get(i);
          localArrayList.add(localObject);
          i++;
          continue;
        }
      }
      catch (JsonException localJsonException)
      {
        throw new FacebookJsonMappingException("The connection JSON you provided was invalid: " + paramString, localJsonException);
      }
      Object localObject = paramFacebookClient.getJsonMapper().toJavaObject(localJsonArray.get(i).toString(), paramClass);
    }
    String str1;
    String str2;
    if (localJsonObject1.has("paging"))
    {
      JsonObject localJsonObject2 = localJsonObject1.getJsonObject("paging");
      if (localJsonObject2.has("previous"))
      {
        str1 = localJsonObject2.getString("previous");
        this.previousPageUrl = str1;
        boolean bool = localJsonObject2.has("next");
        str2 = null;
        if (bool)
          str2 = localJsonObject2.getString("next");
      }
    }
    for (this.nextPageUrl = str2; ; this.nextPageUrl = null)
    {
      this.data = Collections.unmodifiableList(localArrayList);
      this.facebookClient = paramFacebookClient;
      this.connectionType = paramClass;
      return;
      str1 = null;
      break;
      this.previousPageUrl = null;
    }
  }

  public boolean equals(Object paramObject)
  {
    return ReflectionUtils.equals(this, paramObject);
  }

  protected Connection<T> fetchNextPage()
  {
    return this.facebookClient.fetchConnectionPage(getNextPageUrl(), this.connectionType);
  }

  public List<T> getData()
  {
    return this.data;
  }

  public String getNextPageUrl()
  {
    return this.nextPageUrl;
  }

  public String getPreviousPageUrl()
  {
    return this.previousPageUrl;
  }

  public boolean hasNext()
  {
    return !StringUtils.isBlank(getNextPageUrl());
  }

  public boolean hasPrevious()
  {
    return !StringUtils.isBlank(getPreviousPageUrl());
  }

  public int hashCode()
  {
    return ReflectionUtils.hashCode(this);
  }

  public Iterator<List<T>> iterator()
  {
    return new ConnectionIterator(this);
  }

  public String toString()
  {
    return ReflectionUtils.toString(this);
  }

  protected static class ConnectionIterator<T>
    implements Iterator<List<T>>
  {
    private Connection<T> connection;
    private boolean initialPage = true;

    protected ConnectionIterator(Connection<T> paramConnection)
    {
      this.connection = paramConnection;
    }

    public boolean hasNext()
    {
      if (this.initialPage)
        return true;
      return this.connection.hasNext();
    }

    public List<T> next()
    {
      if (this.initialPage)
      {
        this.initialPage = false;
        return this.connection.getData();
      }
      if (!this.connection.hasNext())
        throw new NoSuchElementException("There are no more pages in the connection.");
      this.connection = this.connection.fetchNextPage();
      return this.connection.getData();
    }

    public void remove()
    {
      throw new UnsupportedOperationException(ConnectionIterator.class.getSimpleName() + " doesn't support the remove() operation.");
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.Connection
 * JD-Core Version:    0.6.0
 */