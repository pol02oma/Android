package com.google.android.gms.internal;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class bd
{
  public static List<String> a(JSONObject paramJSONObject, String paramString)
    throws JSONException
  {
    JSONArray localJSONArray = paramJSONObject.optJSONArray(paramString);
    if (localJSONArray != null)
    {
      ArrayList localArrayList = new ArrayList(localJSONArray.length());
      for (int i = 0; i < localJSONArray.length(); i++)
        localArrayList.add(localJSONArray.getString(i));
      return Collections.unmodifiableList(localArrayList);
    }
    return null;
  }

  public static void a(Context paramContext, String paramString1, cn paramcn, String paramString2, boolean paramBoolean, List<String> paramList)
  {
    if (paramBoolean);
    for (String str1 = "1"; ; str1 = "0")
    {
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        String str2 = ((String)localIterator.next()).replaceAll("@gw_adlocid@", paramString2).replaceAll("@gw_adnetrefresh@", str1).replaceAll("@gw_qdata@", paramcn.pf.mw).replaceAll("@gw_sdkver@", paramString1).replaceAll("@gw_sessid@", cp.pu).replaceAll("@gw_seqnum@", paramcn.of);
        if (paramcn.mM != null)
          str2 = str2.replaceAll("@gw_adnetid@", paramcn.mM.mm).replaceAll("@gw_allocid@", paramcn.mM.mo);
        new cy(paramContext, paramString1, str2).start();
      }
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.bd
 * JD-Core Version:    0.6.0
 */