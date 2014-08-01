package com.google.android.gms.analytics;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class GoogleAnalytics extends TrackerHandler
{
  private static boolean tB;
  private static GoogleAnalytics tI;
  private Context mContext;
  private String qR;
  private String qS;
  private f rk;
  private boolean tC;
  private af tD;
  private volatile Boolean tE = Boolean.valueOf(false);
  private Logger tF;
  private Set<a> tG;
  private boolean tH = false;

  protected GoogleAnalytics(Context paramContext)
  {
    this(paramContext, t.q(paramContext), r.bB());
  }

  private GoogleAnalytics(Context paramContext, f paramf, af paramaf)
  {
    if (paramContext == null)
      throw new IllegalArgumentException("context cannot be null");
    this.mContext = paramContext.getApplicationContext();
    this.rk = paramf;
    this.tD = paramaf;
    g.n(this.mContext);
    ae.n(this.mContext);
    h.n(this.mContext);
    this.tF = new l();
    this.tG = new HashSet();
    cg();
  }

  private int D(String paramString)
  {
    String str = paramString.toLowerCase();
    if ("verbose".equals(str))
      return 0;
    if ("info".equals(str))
      return 1;
    if ("warning".equals(str))
      return 2;
    if ("error".equals(str))
      return 3;
    return -1;
  }

  private Tracker a(Tracker paramTracker)
  {
    if (this.qR != null)
      paramTracker.set("&an", this.qR);
    if (this.qS != null)
      paramTracker.set("&av", this.qS);
    return paramTracker;
  }

  static GoogleAnalytics cf()
  {
    monitorenter;
    try
    {
      GoogleAnalytics localGoogleAnalytics = tI;
      return localGoogleAnalytics;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void cg()
  {
    if (tB);
    w localw;
    do
    {
      int i;
      do
      {
        Bundle localBundle;
        do
        {
          return;
          try
          {
            ApplicationInfo localApplicationInfo2 = this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), 129);
            localApplicationInfo1 = localApplicationInfo2;
            if (localApplicationInfo1 == null)
            {
              aa.w("Couldn't get ApplicationInfo to load gloabl config.");
              return;
            }
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            ApplicationInfo localApplicationInfo1;
            while (true)
            {
              aa.v("PackageManager doesn't know about package: " + localNameNotFoundException);
              localApplicationInfo1 = null;
            }
            localBundle = localApplicationInfo1.metaData;
          }
        }
        while (localBundle == null);
        i = localBundle.getInt("com.google.android.gms.analytics.globalConfigResource");
      }
      while (i <= 0);
      localw = (w)new v(this.mContext).p(i);
    }
    while (localw == null);
    a(localw);
  }

  private void d(Activity paramActivity)
  {
    Iterator localIterator = this.tG.iterator();
    while (localIterator.hasNext())
      ((a)localIterator.next()).f(paramActivity);
  }

  private void e(Activity paramActivity)
  {
    Iterator localIterator = this.tG.iterator();
    while (localIterator.hasNext())
      ((a)localIterator.next()).g(paramActivity);
  }

  public static GoogleAnalytics getInstance(Context paramContext)
  {
    monitorenter;
    try
    {
      if (tI == null)
        tI = new GoogleAnalytics(paramContext);
      GoogleAnalytics localGoogleAnalytics = tI;
      return localGoogleAnalytics;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  void a(a parama)
  {
    this.tG.add(parama);
  }

  void a(w paramw)
  {
    aa.v("Loading global config values.");
    if (paramw.bV())
    {
      this.qR = paramw.bW();
      aa.v("app name loaded: " + this.qR);
    }
    if (paramw.bX())
    {
      this.qS = paramw.bY();
      aa.v("app version loaded: " + this.qS);
    }
    if (paramw.bZ())
    {
      int i = D(paramw.ca());
      if (i >= 0)
      {
        aa.v("log level loaded: " + i);
        getLogger().setLogLevel(i);
      }
    }
    if (paramw.cb())
      this.tD.setLocalDispatchPeriod(paramw.cc());
    if (paramw.cd())
      setDryRun(paramw.ce());
  }

  void b(a parama)
  {
    this.tG.remove(parama);
  }

  @Deprecated
  public void dispatchLocalHits()
  {
    this.tD.dispatchLocalHits();
  }

  public void enableAutoActivityReports(Application paramApplication)
  {
    if ((Build.VERSION.SDK_INT >= 14) && (!this.tH))
    {
      paramApplication.registerActivityLifecycleCallbacks(new b());
      this.tH = true;
    }
  }

  public boolean getAppOptOut()
  {
    u.bR().a(u.a.tc);
    return this.tE.booleanValue();
  }

  public Logger getLogger()
  {
    return this.tF;
  }

  public boolean isDryRunEnabled()
  {
    u.bR().a(u.a.to);
    return this.tC;
  }

  void n(Map<String, String> paramMap)
  {
    monitorenter;
    if (paramMap == null)
      try
      {
        throw new IllegalArgumentException("hit cannot be null");
      }
      finally
      {
        monitorexit;
      }
    ak.a(paramMap, "&ul", ak.a(Locale.getDefault()));
    ak.a(paramMap, "&sr", ae.cs().getValue("&sr"));
    paramMap.put("&_u", u.bR().bT());
    u.bR().bS();
    this.rk.n(paramMap);
    monitorexit;
  }

  public Tracker newTracker(int paramInt)
  {
    monitorenter;
    try
    {
      u.bR().a(u.a.sY);
      Tracker localTracker1 = new Tracker(null, this);
      if (paramInt > 0)
      {
        aj localaj = (aj)new ai(this.mContext).p(paramInt);
        if (localaj != null)
          localTracker1.a(this.mContext, localaj);
      }
      Tracker localTracker2 = a(localTracker1);
      return localTracker2;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public Tracker newTracker(String paramString)
  {
    monitorenter;
    try
    {
      u.bR().a(u.a.sY);
      Tracker localTracker = a(new Tracker(paramString, this));
      return localTracker;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void reportActivityStart(Activity paramActivity)
  {
    if (!this.tH)
      d(paramActivity);
  }

  public void reportActivityStop(Activity paramActivity)
  {
    if (!this.tH)
      e(paramActivity);
  }

  public void setAppOptOut(boolean paramBoolean)
  {
    u.bR().a(u.a.tb);
    this.tE = Boolean.valueOf(paramBoolean);
    if (this.tE.booleanValue())
      this.rk.bk();
  }

  public void setDryRun(boolean paramBoolean)
  {
    u.bR().a(u.a.tn);
    this.tC = paramBoolean;
  }

  @Deprecated
  public void setLocalDispatchPeriod(int paramInt)
  {
    this.tD.setLocalDispatchPeriod(paramInt);
  }

  public void setLogger(Logger paramLogger)
  {
    u.bR().a(u.a.tp);
    this.tF = paramLogger;
  }

  static abstract interface a
  {
    public abstract void f(Activity paramActivity);

    public abstract void g(Activity paramActivity);
  }

  class b
    implements Application.ActivityLifecycleCallbacks
  {
    b()
    {
    }

    public void onActivityCreated(Activity paramActivity, Bundle paramBundle)
    {
    }

    public void onActivityDestroyed(Activity paramActivity)
    {
    }

    public void onActivityPaused(Activity paramActivity)
    {
    }

    public void onActivityResumed(Activity paramActivity)
    {
    }

    public void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle)
    {
    }

    public void onActivityStarted(Activity paramActivity)
    {
      GoogleAnalytics.a(GoogleAnalytics.this, paramActivity);
    }

    public void onActivityStopped(Activity paramActivity)
    {
      GoogleAnalytics.b(GoogleAnalytics.this, paramActivity);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.analytics.GoogleAnalytics
 * JD-Core Version:    0.6.0
 */