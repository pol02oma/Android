package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.c;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.dynamic.e.a;

public final class bv extends e<bx>
{
  private static final bv nL = new bv();

  private bv()
  {
    super("com.google.android.gms.ads.AdOverlayCreatorImpl");
  }

  public static bw a(Activity paramActivity)
  {
    try
    {
      if (b(paramActivity))
      {
        da.s("Using AdOverlay from the client jar.");
        return new bo(paramActivity);
      }
      bw localbw = nL.c(paramActivity);
      return localbw;
    }
    catch (a locala)
    {
      da.w(locala.getMessage());
    }
    return null;
  }

  private static boolean b(Activity paramActivity)
    throws bv.a
  {
    Intent localIntent = paramActivity.getIntent();
    if (!localIntent.hasExtra("com.google.android.gms.ads.internal.overlay.useClientJar"))
      throw new a("Ad overlay requires the useClientJar flag in intent extras.");
    return localIntent.getBooleanExtra("com.google.android.gms.ads.internal.overlay.useClientJar", false);
  }

  private bw c(Activity paramActivity)
  {
    try
    {
      b localb = c.h(paramActivity);
      bw localbw = bw.a.m(((bx)z(paramActivity)).a(localb));
      return localbw;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Could not create remote AdOverlay.", localRemoteException);
      return null;
    }
    catch (e.a locala)
    {
      da.b("Could not create remote AdOverlay.", locala);
    }
    return null;
  }

  protected bx l(IBinder paramIBinder)
  {
    return bx.a.n(paramIBinder);
  }

  private static final class a extends Exception
  {
    public a(String paramString)
    {
      super();
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.bv
 * JD-Core Version:    0.6.0
 */