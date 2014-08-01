package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.ep.a;
import com.google.android.gms.internal.fm;
import java.util.ArrayList;

public final class a
  implements Leaderboard
{
  private final String FE;
  private final Uri FJ;
  private final String FU;
  private final String IJ;
  private final int IK;
  private final ArrayList<f> IL;
  private final Game IM;

  public a(Leaderboard paramLeaderboard)
  {
    this.IJ = paramLeaderboard.getLeaderboardId();
    this.FE = paramLeaderboard.getDisplayName();
    this.FJ = paramLeaderboard.getIconImageUri();
    this.FU = paramLeaderboard.getIconImageUrl();
    this.IK = paramLeaderboard.getScoreOrder();
    Game localGame = paramLeaderboard.getGame();
    if (localGame == null);
    for (GameEntity localGameEntity = null; ; localGameEntity = new GameEntity(localGame))
    {
      this.IM = localGameEntity;
      ArrayList localArrayList = paramLeaderboard.getVariants();
      int i = localArrayList.size();
      this.IL = new ArrayList(i);
      for (int j = 0; j < i; j++)
        this.IL.add((f)(f)((LeaderboardVariant)localArrayList.get(j)).freeze());
    }
  }

  static int a(Leaderboard paramLeaderboard)
  {
    Object[] arrayOfObject = new Object[5];
    arrayOfObject[0] = paramLeaderboard.getLeaderboardId();
    arrayOfObject[1] = paramLeaderboard.getDisplayName();
    arrayOfObject[2] = paramLeaderboard.getIconImageUri();
    arrayOfObject[3] = Integer.valueOf(paramLeaderboard.getScoreOrder());
    arrayOfObject[4] = paramLeaderboard.getVariants();
    return ep.hashCode(arrayOfObject);
  }

  static boolean a(Leaderboard paramLeaderboard, Object paramObject)
  {
    int i = 1;
    if (!(paramObject instanceof Leaderboard))
      i = 0;
    Leaderboard localLeaderboard;
    do
    {
      do
        return i;
      while (paramLeaderboard == paramObject);
      localLeaderboard = (Leaderboard)paramObject;
    }
    while ((ep.equal(localLeaderboard.getLeaderboardId(), paramLeaderboard.getLeaderboardId())) && (ep.equal(localLeaderboard.getDisplayName(), paramLeaderboard.getDisplayName())) && (ep.equal(localLeaderboard.getIconImageUri(), paramLeaderboard.getIconImageUri())) && (ep.equal(Integer.valueOf(localLeaderboard.getScoreOrder()), Integer.valueOf(paramLeaderboard.getScoreOrder()))) && (ep.equal(localLeaderboard.getVariants(), paramLeaderboard.getVariants())));
    return false;
  }

  static String b(Leaderboard paramLeaderboard)
  {
    return ep.e(paramLeaderboard).a("LeaderboardId", paramLeaderboard.getLeaderboardId()).a("DisplayName", paramLeaderboard.getDisplayName()).a("IconImageUri", paramLeaderboard.getIconImageUri()).a("IconImageUrl", paramLeaderboard.getIconImageUrl()).a("ScoreOrder", Integer.valueOf(paramLeaderboard.getScoreOrder())).a("Variants", paramLeaderboard.getVariants()).toString();
  }

  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }

  public Leaderboard fW()
  {
    return this;
  }

  public String getDisplayName()
  {
    return this.FE;
  }

  public void getDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    fm.b(this.FE, paramCharArrayBuffer);
  }

  public Game getGame()
  {
    return this.IM;
  }

  public Uri getIconImageUri()
  {
    return this.FJ;
  }

  public String getIconImageUrl()
  {
    return this.FU;
  }

  public String getLeaderboardId()
  {
    return this.IJ;
  }

  public int getScoreOrder()
  {
    return this.IK;
  }

  public ArrayList<LeaderboardVariant> getVariants()
  {
    return new ArrayList(this.IL);
  }

  public int hashCode()
  {
    return a(this);
  }

  public boolean isDataValid()
  {
    return true;
  }

  public String toString()
  {
    return b(this);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.leaderboard.a
 * JD-Core Version:    0.6.0
 */