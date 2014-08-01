package org.apache.http.client.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Stack;
import org.apache.http.HttpHost;
import org.apache.http.annotation.Immutable;

@Immutable
public class URIUtils
{
  @Deprecated
  public static URI createURI(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4, String paramString5)
    throws URISyntaxException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramString2 != null)
    {
      if (paramString1 != null)
      {
        localStringBuilder.append(paramString1);
        localStringBuilder.append("://");
      }
      localStringBuilder.append(paramString2);
      if (paramInt > 0)
      {
        localStringBuilder.append(':');
        localStringBuilder.append(paramInt);
      }
    }
    if ((paramString3 == null) || (!paramString3.startsWith("/")))
      localStringBuilder.append('/');
    if (paramString3 != null)
      localStringBuilder.append(paramString3);
    if (paramString4 != null)
    {
      localStringBuilder.append('?');
      localStringBuilder.append(paramString4);
    }
    if (paramString5 != null)
    {
      localStringBuilder.append('#');
      localStringBuilder.append(paramString5);
    }
    return new URI(localStringBuilder.toString());
  }

  public static HttpHost extractHost(URI paramURI)
  {
    if (paramURI == null);
    while (true)
    {
      return null;
      if (!paramURI.isAbsolute())
        continue;
      int i = paramURI.getPort();
      String str1 = paramURI.getHost();
      int k;
      int m;
      label129: int i2;
      if (str1 == null)
      {
        str1 = paramURI.getAuthority();
        if (str1 != null)
        {
          int j = str1.indexOf('@');
          if (j >= 0)
          {
            if (str1.length() <= j + 1)
              break label129;
            str1 = str1.substring(j + 1);
          }
          while (true)
            if (str1 != null)
            {
              k = str1.indexOf(':');
              if (k >= 0)
              {
                m = k + 1;
                int n = 0;
                int i1 = m;
                while (true)
                  if ((i1 < str1.length()) && (Character.isDigit(str1.charAt(i1))))
                  {
                    n++;
                    i1++;
                    continue;
                    str1 = null;
                    break;
                  }
                if (n <= 0)
                  break;
                i2 = m + n;
              }
            }
        }
      }
      try
      {
        int i3 = Integer.parseInt(str1.substring(m, i2));
        i = i3;
        label162: str1 = str1.substring(0, k);
        String str2 = paramURI.getScheme();
        if (str1 == null)
          continue;
        return new HttpHost(str1, i, str2);
      }
      catch (NumberFormatException localNumberFormatException)
      {
        break label162;
      }
    }
  }

  private static URI removeDotSegments(URI paramURI)
  {
    String str1 = paramURI.getPath();
    if ((str1 == null) || (str1.indexOf("/.") == -1))
      return paramURI;
    String[] arrayOfString = str1.split("/");
    Stack localStack = new Stack();
    int i = 0;
    if (i < arrayOfString.length)
    {
      if ((arrayOfString[i].length() == 0) || (".".equals(arrayOfString[i])));
      while (true)
      {
        i++;
        break;
        if ("..".equals(arrayOfString[i]))
        {
          if (localStack.isEmpty())
            continue;
          localStack.pop();
          continue;
        }
        localStack.push(arrayOfString[i]);
      }
    }
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = localStack.iterator();
    while (localIterator.hasNext())
    {
      String str2 = (String)localIterator.next();
      localStringBuilder.append('/').append(str2);
    }
    try
    {
      URI localURI = new URI(paramURI.getScheme(), paramURI.getAuthority(), localStringBuilder.toString(), paramURI.getQuery(), paramURI.getFragment());
      return localURI;
    }
    catch (URISyntaxException localURISyntaxException)
    {
    }
    throw new IllegalArgumentException(localURISyntaxException);
  }

  public static URI resolve(URI paramURI, String paramString)
  {
    return resolve(paramURI, URI.create(paramString));
  }

  public static URI resolve(URI paramURI1, URI paramURI2)
  {
    if (paramURI1 == null)
      throw new IllegalArgumentException("Base URI may nor be null");
    if (paramURI2 == null)
      throw new IllegalArgumentException("Reference URI may nor be null");
    String str1 = paramURI2.toString();
    if (str1.startsWith("?"))
      return resolveReferenceStartingWithQueryString(paramURI1, paramURI2);
    if (str1.length() == 0);
    for (int i = 1; ; i = 0)
    {
      if (i != 0)
        paramURI2 = URI.create("#");
      URI localURI = paramURI1.resolve(paramURI2);
      if (i != 0)
      {
        String str2 = localURI.toString();
        localURI = URI.create(str2.substring(0, str2.indexOf('#')));
      }
      return removeDotSegments(localURI);
    }
  }

  private static URI resolveReferenceStartingWithQueryString(URI paramURI1, URI paramURI2)
  {
    String str = paramURI1.toString();
    if (str.indexOf('?') > -1)
      str = str.substring(0, str.indexOf('?'));
    return URI.create(str + paramURI2.toString());
  }

  public static URI rewriteURI(URI paramURI)
    throws URISyntaxException
  {
    if (paramURI == null)
      throw new IllegalArgumentException("URI may not be null");
    if ((paramURI.getFragment() != null) || (paramURI.getUserInfo() != null))
      paramURI = new URIBuilder(paramURI).setFragment(null).setUserInfo(null).build();
    return paramURI;
  }

  public static URI rewriteURI(URI paramURI, HttpHost paramHttpHost)
    throws URISyntaxException
  {
    return rewriteURI(paramURI, paramHttpHost, false);
  }

  public static URI rewriteURI(URI paramURI, HttpHost paramHttpHost, boolean paramBoolean)
    throws URISyntaxException
  {
    if (paramURI == null)
      throw new IllegalArgumentException("URI may not be null");
    URIBuilder localURIBuilder = new URIBuilder(paramURI);
    if (paramHttpHost != null)
    {
      localURIBuilder.setScheme(paramHttpHost.getSchemeName());
      localURIBuilder.setHost(paramHttpHost.getHostName());
      localURIBuilder.setPort(paramHttpHost.getPort());
    }
    while (true)
    {
      if (paramBoolean)
        localURIBuilder.setFragment(null);
      return localURIBuilder.build();
      localURIBuilder.setScheme(null);
      localURIBuilder.setHost(null);
      localURIBuilder.setPort(-1);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.client.utils.URIUtils
 * JD-Core Version:    0.6.0
 */