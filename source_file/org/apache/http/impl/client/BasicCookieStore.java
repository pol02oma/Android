package org.apache.http.impl.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import org.apache.http.annotation.GuardedBy;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieIdentityComparator;

@ThreadSafe
public class BasicCookieStore
  implements CookieStore, Serializable
{
  private static final long serialVersionUID = -7581093305228232025L;

  @GuardedBy("this")
  private final TreeSet<Cookie> cookies = new TreeSet(new CookieIdentityComparator());

  public void addCookie(Cookie paramCookie)
  {
    monitorenter;
    if (paramCookie != null);
    try
    {
      this.cookies.remove(paramCookie);
      if (!paramCookie.isExpired(new Date()))
        this.cookies.add(paramCookie);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void addCookies(Cookie[] paramArrayOfCookie)
  {
    monitorenter;
    if (paramArrayOfCookie != null);
    try
    {
      int i = paramArrayOfCookie.length;
      for (int j = 0; j < i; j++)
        addCookie(paramArrayOfCookie[j]);
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void clear()
  {
    monitorenter;
    try
    {
      this.cookies.clear();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public boolean clearExpired(Date paramDate)
  {
    monitorenter;
    int i;
    if (paramDate == null)
      i = 0;
    while (true)
    {
      monitorexit;
      return i;
      i = 0;
      try
      {
        Iterator localIterator = this.cookies.iterator();
        while (localIterator.hasNext())
        {
          if (!((Cookie)localIterator.next()).isExpired(paramDate))
            continue;
          localIterator.remove();
          i = 1;
        }
      }
      finally
      {
        monitorexit;
      }
    }
    throw localObject;
  }

  public List<Cookie> getCookies()
  {
    monitorenter;
    try
    {
      ArrayList localArrayList = new ArrayList(this.cookies);
      monitorexit;
      return localArrayList;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public String toString()
  {
    monitorenter;
    try
    {
      String str = this.cookies.toString();
      monitorexit;
      return str;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.client.BasicCookieStore
 * JD-Core Version:    0.6.0
 */