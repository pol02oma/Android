package com.google.android.gms.games.multiplayer.turnbased;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

public final class TurnBasedMatchBuffer extends d<TurnBasedMatch>
{
  public TurnBasedMatchBuffer(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
  }

  protected TurnBasedMatch getEntry(int paramInt1, int paramInt2)
  {
    return new a(this.zU, paramInt1, paramInt2);
  }

  protected String getPrimaryDataMarkerColumn()
  {
    return "external_match_id";
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchBuffer
 * JD-Core Version:    0.6.0
 */