package org.apache.http.protocol;

@Deprecated
public class SyncBasicHttpContext extends BasicHttpContext
{
  public SyncBasicHttpContext()
  {
  }

  public SyncBasicHttpContext(HttpContext paramHttpContext)
  {
    super(paramHttpContext);
  }

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

  public Object getAttribute(String paramString)
  {
    monitorenter;
    try
    {
      Object localObject2 = super.getAttribute(paramString);
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

  public Object removeAttribute(String paramString)
  {
    monitorenter;
    try
    {
      Object localObject2 = super.removeAttribute(paramString);
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

  public void setAttribute(String paramString, Object paramObject)
  {
    monitorenter;
    try
    {
      super.setAttribute(paramString, paramObject);
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
 * Qualified Name:     org.apache.http.protocol.SyncBasicHttpContext
 * JD-Core Version:    0.6.0
 */