package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.api.Status;

class n
  implements ContainerHolder
{
  private Container TU;
  private Container TV;
  private b TW;
  private a TX;
  private TagManager TY;
  private Status vl;
  private boolean zk;
  private final Looper zs;

  public n(Status paramStatus)
  {
    this.vl = paramStatus;
    this.zs = null;
  }

  public n(TagManager paramTagManager, Looper paramLooper, Container paramContainer, a parama)
  {
    this.TY = paramTagManager;
    if (paramLooper != null);
    while (true)
    {
      this.zs = paramLooper;
      this.TU = paramContainer;
      this.TX = parama;
      this.vl = Status.zQ;
      paramTagManager.a(this);
      return;
      paramLooper = Looper.getMainLooper();
    }
  }

  private void iG()
  {
    if (this.TW != null)
      this.TW.bd(this.TV.iD());
  }

  public void a(Container paramContainer)
  {
    monitorenter;
    while (true)
    {
      try
      {
        boolean bool = this.zk;
        if (bool)
          return;
        if (paramContainer == null)
        {
          bh.t("Unexpected null container.");
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      this.TV = paramContainer;
      iG();
    }
  }

  public void ba(String paramString)
  {
    monitorenter;
    try
    {
      boolean bool = this.zk;
      if (bool);
      while (true)
      {
        return;
        this.TU.ba(paramString);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  void bc(String paramString)
  {
    if (this.zk)
    {
      bh.t("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
      return;
    }
    this.TX.bc(paramString);
  }

  public Container getContainer()
  {
    Container localContainer = null;
    monitorenter;
    try
    {
      if (this.zk)
        bh.t("ContainerHolder is released.");
      while (true)
      {
        return localContainer;
        if (this.TV != null)
        {
          this.TU = this.TV;
          this.TV = null;
        }
        localContainer = this.TU;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  String getContainerId()
  {
    if (this.zk)
    {
      bh.t("getContainerId called on a released ContainerHolder.");
      return "";
    }
    return this.TU.getContainerId();
  }

  public Status getStatus()
  {
    return this.vl;
  }

  String iF()
  {
    if (this.zk)
    {
      bh.t("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
      return "";
    }
    return this.TX.iF();
  }

  public void refresh()
  {
    monitorenter;
    try
    {
      if (this.zk)
        bh.t("Refreshing a released ContainerHolder.");
      while (true)
      {
        return;
        this.TX.iH();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void release()
  {
    monitorenter;
    try
    {
      if (this.zk)
        bh.t("Releasing a released ContainerHolder.");
      while (true)
      {
        return;
        this.zk = true;
        this.TY.b(this);
        this.TU.release();
        this.TU = null;
        this.TV = null;
        this.TX = null;
        this.TW = null;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void setContainerAvailableListener(ContainerHolder.ContainerAvailableListener paramContainerAvailableListener)
  {
    monitorenter;
    while (true)
    {
      try
      {
        if (!this.zk)
          continue;
        bh.t("ContainerHolder is released.");
        return;
        if (paramContainerAvailableListener == null)
        {
          this.TW = null;
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      this.TW = new b(paramContainerAvailableListener, this.zs);
      if (this.TV == null)
        continue;
      iG();
    }
  }

  public static abstract interface a
  {
    public abstract void bc(String paramString);

    public abstract String iF();

    public abstract void iH();
  }

  private class b extends Handler
  {
    private final ContainerHolder.ContainerAvailableListener TZ;

    public b(ContainerHolder.ContainerAvailableListener paramLooper, Looper arg3)
    {
      super();
      this.TZ = paramLooper;
    }

    public void bd(String paramString)
    {
      sendMessage(obtainMessage(1, paramString));
    }

    protected void be(String paramString)
    {
      this.TZ.onContainerAvailable(n.this, paramString);
    }

    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        bh.t("Don't know how to handle this message.");
        return;
      case 1:
      }
      be((String)paramMessage.obj);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.n
 * JD-Core Version:    0.6.0
 */