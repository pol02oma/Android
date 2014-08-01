package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.c;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.dynamic.e.a;

public final class es extends e<eo>
{
  private static final es Cg = new es();

  private es()
  {
    super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
  }

  public static View d(Context paramContext, int paramInt1, int paramInt2)
    throws e.a
  {
    return Cg.e(paramContext, paramInt1, paramInt2);
  }

  private View e(Context paramContext, int paramInt1, int paramInt2)
    throws e.a
  {
    try
    {
      b localb = c.h(paramContext);
      View localView = (View)c.b(((eo)z(paramContext)).a(localb, paramInt1, paramInt2));
      return localView;
    }
    catch (Exception localException)
    {
    }
    throw new e.a("Could not get button with size " + paramInt1 + " and color " + paramInt2, localException);
  }

  public eo B(IBinder paramIBinder)
  {
    return eo.a.A(paramIBinder);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.es
 * JD-Core Version:    0.6.0
 */