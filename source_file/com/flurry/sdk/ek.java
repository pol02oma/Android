package com.flurry.sdk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ek<K, V>
{
  private final Map<K, List<V>> a = new HashMap();
  private int b;

  private List<V> a(K paramK, boolean paramBoolean)
  {
    Object localObject = (List)this.a.get(paramK);
    if ((paramBoolean) && (localObject == null))
      if (this.b <= 0)
        break label55;
    label55: for (localObject = new ArrayList(this.b); ; localObject = new ArrayList())
    {
      this.a.put(paramK, localObject);
      return localObject;
    }
  }

  public List<V> a(K paramK)
  {
    if (paramK == null)
      return null;
    return a(paramK, false);
  }

  public void a(K paramK, V paramV)
  {
    if (paramK == null)
      return;
    a(paramK, true).add(paramV);
  }

  public boolean b(K paramK, V paramV)
  {
    boolean bool = false;
    if (paramK == null);
    List localList;
    do
    {
      do
      {
        return bool;
        localList = a(paramK, false);
        bool = false;
      }
      while (localList == null);
      bool = localList.remove(paramV);
    }
    while (localList.size() != 0);
    this.a.remove(paramK);
    return bool;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.sdk.ek
 * JD-Core Version:    0.6.0
 */