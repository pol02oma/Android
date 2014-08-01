package com.appbuilder.sdk.android;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders.EventBuilder;
import com.google.android.gms.analytics.Tracker;

public class GoogleAnaliticsHandler
{
  private final String TAG = "com.appbuilder.sdk.android.GoogleAnaliticsHandler";
  private String appName;
  private GoogleAnalytics gaInstance;
  private String googleAnalyticsIbuildAppId = "UA-20239101-6";
  private String googleAnalyticsId;
  private Tracker ibuildAppTracker = null;
  private Tracker userTracker = null;

  public GoogleAnaliticsHandler(Context paramContext, String paramString1, String paramString2)
  {
    this.appName = paramString1;
    this.gaInstance = GoogleAnalytics.getInstance(paramContext);
    this.ibuildAppTracker = this.gaInstance.newTracker(this.googleAnalyticsIbuildAppId);
    if (!TextUtils.isEmpty(paramString2))
      this.userTracker = this.gaInstance.newTracker(this.googleAnalyticsId);
  }

  public void sendIbuildAppEvent(String paramString1, String paramString2)
  {
    this.ibuildAppTracker.send(new HitBuilders.EventBuilder().setCategory("Android").setAction(paramString1).setLabel(paramString2).build());
    this.gaInstance.dispatchLocalHits();
    Log.e("com.appbuilder.sdk.android.GoogleAnaliticsHandler", "IBUILDAPP Action = " + paramString1 + " Label = " + paramString2);
  }

  public void sendUserEvent(String paramString1, String paramString2)
  {
    if (!TextUtils.isEmpty(this.googleAnalyticsId))
      this.userTracker.send(new HitBuilders.EventBuilder().setCategory(this.appName + " (Android)").setAction(paramString1).setLabel(paramString2).build());
    this.gaInstance.dispatchLocalHits();
    Log.e("com.appbuilder.sdk.android.GoogleAnaliticsHandler", "USER Action = " + paramString1 + " Label = " + paramString2);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.sdk.android.GoogleAnaliticsHandler
 * JD-Core Version:    0.6.0
 */