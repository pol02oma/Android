package com.google.android.gms.tagmanager;

import android.util.LruCache;

class bb<K, V>
  implements k<K, V>
{
  private LruCache<K, V> Vw;

  bb(int paramInt, l.a<K, V> parama)
  {
    this.Vw = new LruCache(paramInt, parama)
    {
      protected int sizeOf(K paramK, V paramV)
      {
        return this.Vx.sizeOf(paramK, paramV);
      }
    };
  }

  public void e(K paramK, V paramV)
  {
    this.Vw.put(paramK, paramV);
  }

  public V get(K paramK)
  {
    return this.Vw.get(paramK);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.bb
 * JD-Core Version:    0.6.0
 */