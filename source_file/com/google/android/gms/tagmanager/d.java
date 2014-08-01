package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import java.util.Map;

class d
  implements DataLayer.b
{
  private final Context kL;

  public d(Context paramContext)
  {
    this.kL = paramContext;
  }

  public void v(Map<String, Object> paramMap)
  {
    Object localObject1 = paramMap.get("gtm.url");
    Object localObject3;
    if (localObject1 == null)
    {
      localObject3 = paramMap.get("gtm");
      if ((localObject3 == null) || (!(localObject3 instanceof Map)));
    }
    for (Object localObject2 = ((Map)localObject3).get("url"); ; localObject2 = localObject1)
    {
      if ((localObject2 == null) || (!(localObject2 instanceof String)));
      String str;
      do
      {
        return;
        str = Uri.parse((String)localObject2).getQueryParameter("referrer");
      }
      while (str == null);
      ay.e(this.kL, str);
      return;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.d
 * JD-Core Version:    0.6.0
 */