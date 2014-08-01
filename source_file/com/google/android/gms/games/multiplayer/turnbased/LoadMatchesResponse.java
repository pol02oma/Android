package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.internal.gv;

public final class LoadMatchesResponse
{
  private final InvitationBuffer JV;
  private final TurnBasedMatchBuffer JW;
  private final TurnBasedMatchBuffer JX;
  private final TurnBasedMatchBuffer JY;

  public LoadMatchesResponse(Bundle paramBundle)
  {
    DataHolder localDataHolder1 = a(paramBundle, 0);
    label48: DataHolder localDataHolder3;
    if (localDataHolder1 != null)
    {
      this.JV = new InvitationBuffer(localDataHolder1);
      DataHolder localDataHolder2 = a(paramBundle, 1);
      if (localDataHolder2 == null)
        break label107;
      this.JW = new TurnBasedMatchBuffer(localDataHolder2);
      localDataHolder3 = a(paramBundle, 2);
      if (localDataHolder3 == null)
        break label115;
    }
    label107: label115: for (this.JX = new TurnBasedMatchBuffer(localDataHolder3); ; this.JX = null)
    {
      DataHolder localDataHolder4 = a(paramBundle, 3);
      if (localDataHolder4 == null)
        break label123;
      this.JY = new TurnBasedMatchBuffer(localDataHolder4);
      return;
      this.JV = null;
      break;
      this.JW = null;
      break label48;
    }
    label123: this.JY = null;
  }

  private static DataHolder a(Bundle paramBundle, int paramInt)
  {
    String str = gv.aW(paramInt);
    if (!paramBundle.containsKey(str))
      return null;
    return (DataHolder)paramBundle.getParcelable(str);
  }

  public void close()
  {
    if (this.JV != null)
      this.JV.close();
    if (this.JW != null)
      this.JW.close();
    if (this.JX != null)
      this.JX.close();
    if (this.JY != null)
      this.JY.close();
  }

  public TurnBasedMatchBuffer getCompletedMatches()
  {
    return this.JY;
  }

  public InvitationBuffer getInvitations()
  {
    return this.JV;
  }

  public TurnBasedMatchBuffer getMyTurnMatches()
  {
    return this.JW;
  }

  public TurnBasedMatchBuffer getTheirTurnMatches()
  {
    return this.JX;
  }

  public boolean hasData()
  {
    if ((this.JV != null) && (this.JV.getCount() > 0));
    do
      return true;
    while (((this.JW != null) && (this.JW.getCount() > 0)) || ((this.JX != null) && (this.JX.getCount() > 0)) || ((this.JY != null) && (this.JY.getCount() > 0)));
    return false;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse
 * JD-Core Version:    0.6.0
 */