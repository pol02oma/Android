package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class ba
{
  public static cr.c br(String paramString)
    throws JSONException
  {
    d.a locala = k(new JSONObject(paramString));
    cr.d locald = cr.c.jI();
    for (int i = 0; i < locala.ga.length; i++)
      locald.a(cr.a.jE().b(b.cT.toString(), locala.ga[i]).b(b.cI.toString(), di.bI(m.iB())).b(m.iC(), locala.gb[i]).jH());
    return locald.jL();
  }

  private static d.a k(Object paramObject)
    throws JSONException
  {
    return di.r(l(paramObject));
  }

  static Object l(Object paramObject)
    throws JSONException
  {
    if ((paramObject instanceof JSONArray))
      throw new RuntimeException("JSONArrays are not supported");
    if (JSONObject.NULL.equals(paramObject))
      throw new RuntimeException("JSON nulls are not supported");
    if ((paramObject instanceof JSONObject))
    {
      JSONObject localJSONObject = (JSONObject)paramObject;
      HashMap localHashMap = new HashMap();
      Iterator localIterator = localJSONObject.keys();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        localHashMap.put(str, l(localJSONObject.get(str)));
      }
      paramObject = localHashMap;
    }
    return paramObject;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.ba
 * JD-Core Version:    0.6.0
 */