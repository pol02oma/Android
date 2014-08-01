package com.flurry.sdk;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ei
{
  private Map<String, Object> a = new HashMap();
  private Map<String, List<a>> b = new HashMap();

  private boolean a(Object paramObject1, Object paramObject2)
  {
    return (paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)));
  }

  private void b(String paramString, Object paramObject)
  {
    if (this.b.get(paramString) != null)
    {
      Iterator localIterator = ((List)this.b.get(paramString)).iterator();
      while (localIterator.hasNext())
        ((a)localIterator.next()).a(paramString, paramObject);
    }
  }

  public Object a(String paramString)
  {
    monitorenter;
    try
    {
      Object localObject2 = this.a.get(paramString);
      monitorexit;
      return localObject2;
    }
    finally
    {
      localObject1 = finally;
      monitorexit;
    }
    throw localObject1;
  }

  public void a(String paramString, a parama)
  {
    monitorenter;
    try
    {
      boolean bool = TextUtils.isEmpty(paramString);
      if (bool);
      while (true)
      {
        return;
        if (parama == null)
          continue;
        Object localObject2 = (List)this.b.get(paramString);
        if (localObject2 == null)
          localObject2 = new LinkedList();
        ((List)localObject2).add(parama);
        this.b.put(paramString, localObject2);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public void a(String paramString, Object paramObject)
  {
    monitorenter;
    while (true)
    {
      try
      {
        boolean bool = TextUtils.isEmpty(paramString);
        if (bool)
          return;
        if (a(paramObject, this.a.get(paramString)))
          continue;
        if (paramObject == null)
        {
          this.a.remove(paramString);
          b(paramString, paramObject);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      this.a.put(paramString, paramObject);
    }
  }

  public boolean b(String paramString, a parama)
  {
    monitorenter;
    try
    {
      boolean bool1 = TextUtils.isEmpty(paramString);
      int i;
      if (bool1)
        i = 0;
      while (true)
      {
        return i;
        if (parama == null)
        {
          i = 0;
          continue;
        }
        List localList = (List)this.b.get(paramString);
        if (localList == null)
        {
          i = 0;
          continue;
        }
        boolean bool2 = localList.remove(parama);
        i = bool2;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static abstract interface a
  {
    public abstract void a(String paramString, Object paramObject);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.sdk.ei
 * JD-Core Version:    0.6.0
 */