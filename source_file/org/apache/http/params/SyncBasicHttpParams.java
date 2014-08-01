package org.apache.http.params;

import org.apache.http.annotation.ThreadSafe;

@ThreadSafe
public class SyncBasicHttpParams extends BasicHttpParams
{
  private static final long serialVersionUID = 5387834869062660642L;

  public void clear()
  {
    monitorenter;
    try
    {
      super.clear();
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

  public Object clone()
    throws CloneNotSupportedException
  {
    monitorenter;
    try
    {
      Object localObject2 = super.clone();
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

  public Object getParameter(String paramString)
  {
    monitorenter;
    try
    {
      Object localObject2 = super.getParameter(paramString);
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

  public boolean isParameterSet(String paramString)
  {
    monitorenter;
    try
    {
      boolean bool = super.isParameterSet(paramString);
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public boolean isParameterSetLocally(String paramString)
  {
    monitorenter;
    try
    {
      boolean bool = super.isParameterSetLocally(paramString);
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public boolean removeParameter(String paramString)
  {
    monitorenter;
    try
    {
      boolean bool = super.removeParameter(paramString);
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public HttpParams setParameter(String paramString, Object paramObject)
  {
    monitorenter;
    try
    {
      HttpParams localHttpParams = super.setParameter(paramString, paramObject);
      monitorexit;
      return localHttpParams;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setParameters(String[] paramArrayOfString, Object paramObject)
  {
    monitorenter;
    try
    {
      super.setParameters(paramArrayOfString, paramObject);
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
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.params.SyncBasicHttpParams
 * JD-Core Version:    0.6.0
 */