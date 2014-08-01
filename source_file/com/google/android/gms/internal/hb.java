package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import java.util.HashMap;
import java.util.Set;

public final class hb
{
  private static final String[] IH = { "requestId", "outcome" };
  private final HashMap<String, Integer> II;
  private final int yJ;

  private hb(int paramInt, HashMap<String, Integer> paramHashMap)
  {
    this.yJ = paramInt;
    this.II = paramHashMap;
  }

  public static hb H(DataHolder paramDataHolder)
  {
    a locala = new a();
    locala.aZ(paramDataHolder.getStatusCode());
    int i = paramDataHolder.getCount();
    for (int j = 0; j < i; j++)
    {
      int k = paramDataHolder.I(j);
      locala.p(paramDataHolder.getString("requestId", j, k), paramDataHolder.getInteger("outcome", j, k));
    }
    return locala.fV();
  }

  public Set<String> getRequestIds()
  {
    return this.II.keySet();
  }

  public int getRequestOutcome(String paramString)
  {
    er.b(this.II.containsKey(paramString), "Request " + paramString + " was not part of the update operation!");
    return ((Integer)this.II.get(paramString)).intValue();
  }

  public static final class a
  {
    private HashMap<String, Integer> II = new HashMap();
    private int yJ = 0;

    public a aZ(int paramInt)
    {
      this.yJ = paramInt;
      return this;
    }

    public hb fV()
    {
      return new hb(this.yJ, this.II, null);
    }

    public a p(String paramString, int paramInt)
    {
      if (gt.isValid(paramInt))
        this.II.put(paramString, Integer.valueOf(paramInt));
      return this;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.hb
 * JD-Core Version:    0.6.0
 */