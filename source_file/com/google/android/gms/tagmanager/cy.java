package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;

class cy extends cx
{
  private static cy Xp;
  private static final Object ri = new Object();
  private Context Xf;
  private at Xg;
  private volatile ar Xh;
  private int Xi = 1800000;
  private boolean Xj = true;
  private boolean Xk = false;
  private boolean Xl = true;
  private au Xm = new au()
  {
    public void p(boolean paramBoolean)
    {
      cy.this.a(paramBoolean, cy.a(cy.this));
    }
  };
  private bn Xn;
  private boolean Xo = false;
  private boolean connected = true;
  private Handler handler;

  private void bC()
  {
    this.Xn = new bn(this);
    this.Xn.o(this.Xf);
  }

  private void bD()
  {
    this.handler = new Handler(this.Xf.getMainLooper(), new Handler.Callback()
    {
      public boolean handleMessage(Message paramMessage)
      {
        if ((1 == paramMessage.what) && (cy.bG().equals(paramMessage.obj)))
        {
          cy.this.bp();
          if ((cy.b(cy.this) > 0) && (!cy.c(cy.this)))
            cy.d(cy.this).sendMessageDelayed(cy.d(cy.this).obtainMessage(1, cy.bG()), cy.b(cy.this));
        }
        return true;
      }
    });
    if (this.Xi > 0)
      this.handler.sendMessageDelayed(this.handler.obtainMessage(1, ri), this.Xi);
  }

  public static cy kh()
  {
    if (Xp == null)
      Xp = new cy();
    return Xp;
  }

  void a(Context paramContext, ar paramar)
  {
    monitorenter;
    try
    {
      Context localContext = this.Xf;
      if (localContext != null);
      while (true)
      {
        return;
        this.Xf = paramContext.getApplicationContext();
        if (this.Xh != null)
          continue;
        this.Xh = paramar;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  void a(boolean paramBoolean1, boolean paramBoolean2)
  {
    monitorenter;
    while (true)
    {
      try
      {
        if (this.Xo != paramBoolean1)
          continue;
        boolean bool = this.connected;
        if (bool == paramBoolean2)
          return;
        if (((!paramBoolean1) && (paramBoolean2)) || (this.Xi <= 0))
          continue;
        this.handler.removeMessages(1, ri);
        if ((paramBoolean1) || (!paramBoolean2) || (this.Xi <= 0))
          continue;
        this.handler.sendMessageDelayed(this.handler.obtainMessage(1, ri), this.Xi);
        StringBuilder localStringBuilder = new StringBuilder().append("PowerSaveMode ");
        if (paramBoolean1)
          break label153;
        if (!paramBoolean2)
        {
          break label153;
          bh.v(str);
          this.Xo = paramBoolean1;
          this.connected = paramBoolean2;
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      String str = "terminated.";
      continue;
      label153: str = "initiated.";
    }
  }

  void bF()
  {
    monitorenter;
    try
    {
      if ((!this.Xo) && (this.connected) && (this.Xi > 0))
      {
        this.handler.removeMessages(1, ri);
        this.handler.sendMessage(this.handler.obtainMessage(1, ri));
      }
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

  public void bp()
  {
    monitorenter;
    try
    {
      if (!this.Xk)
      {
        bh.v("Dispatch call queued. Dispatch will run once initialization is complete.");
        this.Xj = true;
      }
      while (true)
      {
        return;
        this.Xh.a(new Runnable()
        {
          public void run()
          {
            cy.e(cy.this).bp();
          }
        });
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  at ki()
  {
    monitorenter;
    try
    {
      if (this.Xg != null)
        break label50;
      if (this.Xf == null)
        throw new IllegalStateException("Cant get a store unless we have a context");
    }
    finally
    {
      monitorexit;
    }
    this.Xg = new cb(this.Xm, this.Xf);
    label50: if (this.handler == null)
      bD();
    this.Xk = true;
    if (this.Xj)
    {
      bp();
      this.Xj = false;
    }
    if ((this.Xn == null) && (this.Xl))
      bC();
    at localat = this.Xg;
    monitorexit;
    return localat;
  }

  void q(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      a(this.Xo, paramBoolean);
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
 * Qualified Name:     com.google.android.gms.tagmanager.cy
 * JD-Core Version:    0.6.0
 */