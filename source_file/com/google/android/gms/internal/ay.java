package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ay
{
  public final List<ax> mr;
  public final long ms;
  public final List<String> mt;
  public final List<String> mu;
  public final List<String> mv;
  public final String mw;
  public final long mx;
  public int my;
  public int mz;

  public ay(String paramString)
    throws JSONException
  {
    JSONObject localJSONObject1 = new JSONObject(paramString);
    if (da.n(2))
      da.v("Mediation Response JSON: " + localJSONObject1.toString(2));
    JSONArray localJSONArray = localJSONObject1.getJSONArray("ad_networks");
    ArrayList localArrayList = new ArrayList(localJSONArray.length());
    int i = -1;
    for (int j = 0; j < localJSONArray.length(); j++)
    {
      ax localax = new ax(localJSONArray.getJSONObject(j));
      localArrayList.add(localax);
      if ((i >= 0) || (!a(localax)))
        continue;
      i = j;
    }
    this.my = i;
    this.mz = localJSONArray.length();
    this.mr = Collections.unmodifiableList(localArrayList);
    this.mw = localJSONObject1.getString("qdata");
    JSONObject localJSONObject2 = localJSONObject1.optJSONObject("settings");
    if (localJSONObject2 != null)
    {
      this.ms = localJSONObject2.optLong("ad_network_timeout_millis", -1L);
      this.mt = bd.a(localJSONObject2, "click_urls");
      this.mu = bd.a(localJSONObject2, "imp_urls");
      this.mv = bd.a(localJSONObject2, "nofill_urls");
      long l1 = localJSONObject2.optLong("refresh", -1L);
      long l2;
      if (l1 > 0L)
        l2 = l1 * 1000L;
      while (true)
      {
        this.mx = l2;
        return;
        l2 = -1L;
      }
    }
    this.ms = -1L;
    this.mt = null;
    this.mu = null;
    this.mv = null;
    this.mx = -1L;
  }

  private boolean a(ax paramax)
  {
    Iterator localIterator = paramax.mn.iterator();
    while (localIterator.hasNext())
      if (((String)localIterator.next()).equals("com.google.ads.mediation.admob.AdMobAdapter"))
        return true;
    return false;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ay
 * JD-Core Version:    0.6.0
 */