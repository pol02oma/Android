package com.google.android.gms.games.multiplayer.turnbased;

public abstract interface OnTurnBasedMatchUpdateReceivedListener
{
  public abstract void onTurnBasedMatchReceived(TurnBasedMatch paramTurnBasedMatch);

  public abstract void onTurnBasedMatchRemoved(String paramString);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener
 * JD-Core Version:    0.6.0
 */