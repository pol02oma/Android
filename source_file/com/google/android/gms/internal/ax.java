package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ax
{
  public final String adJson;
  public final String mm;
  public final List<String> mn;
  public final String mo;
  public final List<String> mp;
  public final String mq;

  public ax(JSONObject paramJSONObject)
    throws JSONException
  {
    this.mm = paramJSONObject.getString("id");
    JSONArray localJSONArray = paramJSONObject.getJSONArray("adapters");
    ArrayList localArrayList = new ArrayList(localJSONArray.length());
    for (int i = 0; i < localJSONArray.length(); i++)
      localArrayList.add(localJSONArray.getString(i));
    this.mn = Collections.unmodifiableList(localArrayList);
    this.mo = paramJSONObject.optString("allocation_id", null);
    this.mp = bd.a(paramJSONObject, "imp_urls");
    JSONObject localJSONObject1 = paramJSONObject.optJSONObject("ad");
    if (localJSONObject1 != null);
    for (String str1 = localJSONObject1.toString(); ; str1 = null)
    {
      this.adJson = str1;
      JSONObject localJSONObject2 = paramJSONObject.optJSONObject("data");
      String str2 = null;
      if (localJSONObject2 != null)
        str2 = localJSONObject2.toString();
      this.mq = str2;
      return;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ax
 * JD-Core Version:    0.6.0
 */