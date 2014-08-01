package com.google.android.gms.games.multiplayer.realtime;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

public final class a extends d<Room>
{
  public a(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
  }

  protected Room d(int paramInt1, int paramInt2)
  {
    return new c(this.zU, paramInt1, paramInt2);
  }

  protected String getPrimaryDataMarkerColumn()
  {
    return "external_match_id";
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.multiplayer.realtime.a
 * JD-Core Version:    0.6.0
 */