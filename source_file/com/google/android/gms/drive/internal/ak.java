package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a.c;

public class ak extends c
{
  private final a.c<Status> vj;

  public ak(a.c<Status> paramc)
  {
    this.vj = paramc;
  }

  public void l(Status paramStatus)
    throws RemoteException
  {
    this.vj.b(paramStatus);
  }

  public void onSuccess()
    throws RemoteException
  {
    this.vj.b(Status.zQ);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.ak
 * JD-Core Version:    0.6.0
 */