package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.location.Location;
import android.text.TextUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class cj
{
  private static final SimpleDateFormat ow = new SimpleDateFormat("yyyyMMdd");

  public static cf a(Context paramContext, cd paramcd, String paramString)
  {
    JSONObject localJSONObject;
    String str1;
    String str3;
    String str4;
    long l1;
    long l2;
    int i;
    cf localcf3;
    label195: Object localObject1;
    label233: int m;
    while (true)
    {
      try
      {
        localJSONObject = new JSONObject(paramString);
        str1 = localJSONObject.optString("ad_base_url", null);
        String str2 = localJSONObject.optString("ad_url", null);
        str3 = localJSONObject.optString("ad_size", null);
        str4 = localJSONObject.optString("ad_html", null);
        l1 = -1L;
        if (!localJSONObject.has("interstitial_timeout"))
          break label589;
        l2 = ()(1000.0D * localJSONObject.getDouble("interstitial_timeout"));
        String str5 = localJSONObject.optString("orientation", null);
        i = -1;
        if (!"portrait".equals(str5))
          continue;
        i = cv.aU();
        if (TextUtils.isEmpty(str4))
          continue;
        if (!TextUtils.isEmpty(str1))
          break label583;
        da.w("Could not parse the mediation config: Missing required ad_base_url field");
        return new cf(0);
        if (!"landscape".equals(str5))
          continue;
        i = cv.aT();
        continue;
        if (TextUtils.isEmpty(str2))
          continue;
        cf localcf2 = ci.a(paramContext, paramcd.kN.pU, str2);
        str1 = localcf2.nw;
        str4 = localcf2.oi;
        l1 = localcf2.oo;
        localcf3 = localcf2;
        JSONArray localJSONArray1 = localJSONObject.optJSONArray("click_urls");
        if (localcf3 == null)
        {
          localObject1 = null;
          if (localJSONArray1 == null)
            break;
          if (localObject1 != null)
            break label597;
          localObject1 = new LinkedList();
          break label597;
          if (m >= localJSONArray1.length())
            break label603;
          ((List)localObject1).add(localJSONArray1.getString(m));
          m++;
          continue;
          da.w("Could not parse the mediation config: Missing required ad_html or ad_url field.");
          cf localcf1 = new cf(0);
          return localcf1;
        }
      }
      catch (JSONException localJSONException)
      {
        da.w("Could not parse the mediation config: " + localJSONException.getMessage());
        return new cf(0);
      }
      localObject1 = localcf3.mt;
    }
    label329: JSONArray localJSONArray2 = localJSONObject.optJSONArray("impression_urls");
    Object localObject3;
    if (localcf3 == null)
      localObject3 = null;
    label367: int k;
    label408: Object localObject5;
    label446: int j;
    while (localJSONArray2 != null)
    {
      if (localObject3 != null)
        break label610;
      localObject3 = new LinkedList();
      break label610;
      while (k < localJSONArray2.length())
      {
        ((List)localObject3).add(localJSONArray2.getString(k));
        k++;
      }
      localObject3 = localcf3.mu;
      continue;
      JSONArray localJSONArray3 = localJSONObject.optJSONArray("manual_impression_urls");
      if (localcf3 == null);
      for (localObject5 = null; localJSONArray3 != null; localObject5 = localcf3.om)
      {
        if (localObject5 != null)
          break label623;
        localObject5 = new LinkedList();
        break label623;
        while (j < localJSONArray3.length())
        {
          ((List)localObject5).add(localJSONArray3.getString(j));
          j++;
        }
      }
    }
    while (true)
    {
      if (localcf3 != null)
      {
        if (localcf3.orientation != -1)
          i = localcf3.orientation;
        if (localcf3.oj > 0L)
          l2 = localcf3.oj;
      }
      cf localcf4 = new cf(str1, str4, (List)localObject2, (List)localObject4, l2, false, -1L, (List)localObject6, -1L, i, str3, l1);
      return localcf4;
      Object localObject6 = localObject5;
      continue;
      Object localObject4 = localObject3;
      break label408;
      Object localObject2 = localObject1;
      break label329;
      label583: localcf3 = null;
      break label195;
      label589: l2 = -1L;
      break;
      label597: m = 0;
      break label233;
      label603: localObject2 = localObject1;
      break label329;
      label610: k = 0;
      break label367;
      localObject4 = localObject3;
      break label408;
      label623: j = 0;
      break label446;
      localObject6 = localObject5;
    }
  }

  public static String a(cd paramcd, cm paramcm, Location paramLocation)
  {
    try
    {
      HashMap localHashMap = new HashMap();
      if (paramcd.ob != null)
        localHashMap.put("ad_pos", paramcd.ob);
      a(localHashMap, paramcd.oc);
      localHashMap.put("format", paramcd.kQ.ln);
      if (paramcd.kQ.width == -1)
        localHashMap.put("smart_w", "full");
      if (paramcd.kQ.height == -2)
        localHashMap.put("smart_h", "auto");
      if (paramcd.kQ.lp != null)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        ab[] arrayOfab = paramcd.kQ.lp;
        int i = arrayOfab.length;
        int j = 0;
        if (j < i)
        {
          ab localab = arrayOfab[j];
          if (localStringBuilder.length() != 0)
            localStringBuilder.append("|");
          int k;
          if (localab.width == -1)
          {
            k = (int)(localab.widthPixels / paramcm.pc);
            label178: localStringBuilder.append(k);
            localStringBuilder.append("x");
            if (localab.height != -2)
              break label242;
          }
          label242: for (int m = (int)(localab.heightPixels / paramcm.pc); ; m = localab.height)
          {
            localStringBuilder.append(m);
            j++;
            break;
            k = localab.width;
            break label178;
          }
        }
        localHashMap.put("sz", localStringBuilder);
      }
      localHashMap.put("slotname", paramcd.adUnitId);
      localHashMap.put("pn", paramcd.applicationInfo.packageName);
      if (paramcd.od != null)
        localHashMap.put("vc", Integer.valueOf(paramcd.od.versionCode));
      localHashMap.put("ms", paramcd.oe);
      localHashMap.put("seq_num", paramcd.of);
      localHashMap.put("session_id", paramcd.og);
      localHashMap.put("js", paramcd.kN.pU);
      a(localHashMap, paramcm);
      if ((paramcd.oc.versionCode >= 2) && (paramcd.oc.ll != null))
        a(localHashMap, paramcd.oc.ll);
      if (paramcd.versionCode >= 2)
        localHashMap.put("quality_signals", paramcd.oh);
      if (da.n(2))
      {
        String str2 = cv.m(localHashMap).toString(2);
        da.v("Ad Request JSON: " + str2);
      }
      String str1 = cv.m(localHashMap).toString();
      return str1;
    }
    catch (JSONException localJSONException)
    {
      da.w("Problem serializing ad request to JSON: " + localJSONException.getMessage());
    }
    return null;
  }

  private static void a(HashMap<String, Object> paramHashMap, Location paramLocation)
  {
    HashMap localHashMap = new HashMap();
    Float localFloat = Float.valueOf(1000.0F * paramLocation.getAccuracy());
    Long localLong1 = Long.valueOf(1000L * paramLocation.getTime());
    Long localLong2 = Long.valueOf(()(10000000.0D * paramLocation.getLatitude()));
    Long localLong3 = Long.valueOf(()(10000000.0D * paramLocation.getLongitude()));
    localHashMap.put("radius", localFloat);
    localHashMap.put("lat", localLong2);
    localHashMap.put("long", localLong3);
    localHashMap.put("time", localLong1);
    paramHashMap.put("uule", localHashMap);
  }

  private static void a(HashMap<String, Object> paramHashMap, am paramam)
  {
    if (Color.alpha(paramam.lI) != 0)
      paramHashMap.put("acolor", m(paramam.lI));
    if (Color.alpha(paramam.backgroundColor) != 0)
      paramHashMap.put("bgcolor", m(paramam.backgroundColor));
    if ((Color.alpha(paramam.lJ) != 0) && (Color.alpha(paramam.lK) != 0))
    {
      paramHashMap.put("gradientto", m(paramam.lJ));
      paramHashMap.put("gradientfrom", m(paramam.lK));
    }
    if (Color.alpha(paramam.lL) != 0)
      paramHashMap.put("bcolor", m(paramam.lL));
    paramHashMap.put("bthick", Integer.toString(paramam.lM));
    Object localObject1;
    Object localObject2;
    switch (paramam.lN)
    {
    default:
      localObject1 = null;
      if (localObject1 != null)
        paramHashMap.put("btype", localObject1);
      int i = paramam.lO;
      localObject2 = null;
      switch (i)
      {
      default:
      case 2:
      case 0:
      case 1:
      }
    case 0:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      if (localObject2 != null)
        paramHashMap.put("callbuttoncolor", localObject2);
      if (paramam.lP != null)
        paramHashMap.put("channel", paramam.lP);
      if (Color.alpha(paramam.lQ) != 0)
        paramHashMap.put("dcolor", m(paramam.lQ));
      if (paramam.lR != null)
        paramHashMap.put("font", paramam.lR);
      if (Color.alpha(paramam.lS) != 0)
        paramHashMap.put("hcolor", m(paramam.lS));
      paramHashMap.put("headersize", Integer.toString(paramam.lT));
      if (paramam.lU != null)
        paramHashMap.put("q", paramam.lU);
      return;
      localObject1 = "none";
      break;
      localObject1 = "dashed";
      break;
      localObject1 = "dotted";
      break;
      localObject1 = "solid";
      break;
      localObject2 = "dark";
      continue;
      localObject2 = "light";
      continue;
      localObject2 = "medium";
    }
  }

  private static void a(HashMap<String, Object> paramHashMap, cm paramcm)
  {
    paramHashMap.put("am", Integer.valueOf(paramcm.oM));
    paramHashMap.put("cog", j(paramcm.oN));
    paramHashMap.put("coh", j(paramcm.oO));
    if (!TextUtils.isEmpty(paramcm.oP))
      paramHashMap.put("carrier", paramcm.oP);
    paramHashMap.put("gl", paramcm.oQ);
    if (paramcm.oR)
      paramHashMap.put("simulator", Integer.valueOf(1));
    paramHashMap.put("ma", j(paramcm.oS));
    paramHashMap.put("sp", j(paramcm.oT));
    paramHashMap.put("hl", paramcm.oU);
    if (!TextUtils.isEmpty(paramcm.oV))
      paramHashMap.put("mv", paramcm.oV);
    paramHashMap.put("muv", Integer.valueOf(paramcm.oW));
    if (paramcm.oX != -2)
      paramHashMap.put("cnt", Integer.valueOf(paramcm.oX));
    paramHashMap.put("gnt", Integer.valueOf(paramcm.oY));
    paramHashMap.put("pt", Integer.valueOf(paramcm.oZ));
    paramHashMap.put("rm", Integer.valueOf(paramcm.pa));
    paramHashMap.put("riv", Integer.valueOf(paramcm.pb));
    paramHashMap.put("u_sd", Float.valueOf(paramcm.pc));
    paramHashMap.put("sh", Integer.valueOf(paramcm.pe));
    paramHashMap.put("sw", Integer.valueOf(paramcm.pd));
  }

  private static void a(HashMap<String, Object> paramHashMap, z paramz)
  {
    String str = cs.aR();
    if (str != null)
      paramHashMap.put("abf", str);
    if (paramz.le != -1L)
      paramHashMap.put("cust_age", ow.format(new Date(paramz.le)));
    if (paramz.extras != null)
      paramHashMap.put("extras", paramz.extras);
    if (paramz.lf != -1)
      paramHashMap.put("cust_gender", Integer.valueOf(paramz.lf));
    if (paramz.lg != null)
      paramHashMap.put("kw", paramz.lg);
    if (paramz.tagForChildDirectedTreatment != -1)
      paramHashMap.put("tag_for_child_directed_treatment", Integer.valueOf(paramz.tagForChildDirectedTreatment));
    if (paramz.lh)
      paramHashMap.put("adtest", "on");
    if (paramz.versionCode >= 2)
    {
      if (paramz.li)
        paramHashMap.put("d_imp_hdr", Integer.valueOf(1));
      if (!TextUtils.isEmpty(paramz.lj))
        paramHashMap.put("ppid", paramz.lj);
      if (paramz.lk != null)
        a(paramHashMap, paramz.lk);
    }
    if ((paramz.versionCode >= 3) && (paramz.lm != null))
      paramHashMap.put("url", paramz.lm);
  }

  private static Integer j(boolean paramBoolean)
  {
    if (paramBoolean);
    for (int i = 1; ; i = 0)
      return Integer.valueOf(i);
  }

  private static String m(int paramInt)
  {
    Locale localLocale = Locale.US;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(0xFFFFFF & paramInt);
    return String.format(localLocale, "#%06x", arrayOfObject);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.cj
 * JD-Core Version:    0.6.0
 */