package com.google.android.gms.tagmanager;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.analytics.CampaignTrackingService;

public final class InstallReferrerService extends IntentService
{
  CampaignTrackingService Vk;
  Context Vl;

  public InstallReferrerService()
  {
    super("InstallReferrerService");
  }

  public InstallReferrerService(String paramString)
  {
    super(paramString);
  }

  private void a(Context paramContext, Intent paramIntent)
  {
    if (this.Vk == null)
      this.Vk = new CampaignTrackingService();
    this.Vk.processIntent(paramContext, paramIntent);
  }

  protected void onHandleIntent(Intent paramIntent)
  {
    String str = paramIntent.getStringExtra("referrer");
    if (this.Vl != null);
    for (Context localContext = this.Vl; ; localContext = getApplicationContext())
    {
      ay.c(localContext, str);
      a(localContext, paramIntent);
      return;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.InstallReferrerService
 * JD-Core Version:    0.6.0
 */