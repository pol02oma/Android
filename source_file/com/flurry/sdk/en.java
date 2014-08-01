package com.flurry.sdk;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;

public class en
  implements ei.a
{
  private static final String c = en.class.getSimpleName();
  private static en l;
  boolean a = false;
  boolean b;
  private final long d = 1800000L;
  private final long e = 0L;
  private LocationManager f;
  private Criteria g;
  private Location h;
  private a i = new a();
  private String j;
  private int k = 0;

  private en()
  {
    ei localei = eh.a();
    this.g = ((Criteria)localei.a("LocationCriteria"));
    localei.a("LocationCriteria", this);
    ex.a(4, c, "initSettings, LocationCriteria = " + this.g);
    this.b = ((Boolean)localei.a("ReportLocation")).booleanValue();
    localei.a("ReportLocation", this);
    ex.a(4, c, "initSettings, ReportLocation = " + this.b);
  }

  public static en a()
  {
    monitorenter;
    try
    {
      if (l == null)
        l = new en();
      en localen = l;
      return localen;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void a(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
      this.f.requestLocationUpdates(paramString, 1800000L, 0.0F, this.i, Looper.getMainLooper());
  }

  private Location b(String paramString)
  {
    boolean bool = TextUtils.isEmpty(paramString);
    Location localLocation = null;
    if (!bool)
      localLocation = this.f.getLastKnownLocation(paramString);
    return localLocation;
  }

  private void g()
  {
    this.f.removeUpdates(this.i);
    this.a = false;
    ex.a(4, c, "LocationProvider stoped");
  }

  private void h()
  {
    if (!this.b);
    Context localContext;
    do
    {
      return;
      localContext = eg.a().b();
    }
    while ((localContext.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0) && (localContext.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0));
    g();
    String str = i();
    a(str);
    this.h = b(str);
    this.a = true;
    ex.a(4, c, "LocationProvider started");
  }

  private String i()
  {
    Criteria localCriteria = this.g;
    if (localCriteria == null)
      localCriteria = new Criteria();
    if (TextUtils.isEmpty(this.j));
    for (String str = this.f.getBestProvider(localCriteria, true); ; str = this.j)
    {
      ex.a(4, c, "provider = " + str);
      return str;
    }
  }

  public void a(String paramString, Object paramObject)
  {
    if (paramString.equals("LocationCriteria"))
    {
      this.g = ((Criteria)paramObject);
      ex.a(4, c, "onSettingUpdate, LocationCriteria = " + this.g);
      if (this.a)
        h();
    }
    while (true)
    {
      return;
      if (!paramString.equals("ReportLocation"))
        break label138;
      this.b = ((Boolean)paramObject).booleanValue();
      ex.a(4, c, "onSettingUpdate, ReportLocation = " + this.b);
      if (!this.b)
        break;
      if ((this.a) || (this.k <= 0))
        continue;
      h();
      return;
    }
    g();
    return;
    label138: ex.a(6, c, "LocationProvider internal error! Had to be LocationCriteria or ReportLocation key.");
  }

  public void b()
  {
    monitorenter;
    try
    {
      LocationManager localLocationManager = this.f;
      if (localLocationManager != null);
      while (true)
      {
        return;
        this.f = ((LocationManager)eg.a().b().getSystemService("location"));
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void c()
  {
    monitorenter;
    try
    {
      ex.a(4, c, "Location provider subscribed");
      this.k = (1 + this.k);
      if (!this.a)
        h();
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

  public void d()
  {
    monitorenter;
    try
    {
      ex.a(4, c, "Location provider unsubscribed");
      if (this.k <= 0)
        ex.a(6, c, "Error! Unsubscribed too many times!");
      while (true)
      {
        return;
        this.k = (-1 + this.k);
        if (this.k != 0)
          continue;
        g();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public Location e()
  {
    boolean bool = this.b;
    Location localLocation1 = null;
    if (bool)
    {
      Location localLocation2 = b(i());
      if (localLocation2 != null)
        this.h = localLocation2;
      localLocation1 = this.h;
    }
    ex.a(4, c, "getLocation() = " + localLocation1);
    return localLocation1;
  }

  public void f()
  {
    this.k = 0;
    g();
  }

  class a
    implements LocationListener
  {
    public a()
    {
    }

    public void onLocationChanged(Location paramLocation)
    {
      if (paramLocation != null)
        en.a(en.this, paramLocation);
    }

    public void onProviderDisabled(String paramString)
    {
    }

    public void onProviderEnabled(String paramString)
    {
    }

    public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
    {
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.sdk.en
 * JD-Core Version:    0.6.0
 */