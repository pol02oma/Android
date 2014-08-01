package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Game;
import java.util.ArrayList;

public final class b extends com.google.android.gms.common.data.b
  implements Leaderboard
{
  private final Game IM;
  private final int IN;

  b(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    super(paramDataHolder, paramInt1);
    this.IN = paramInt2;
    this.IM = new com.google.android.gms.games.b(paramDataHolder, paramInt1);
  }

  public boolean equals(Object paramObject)
  {
    return a.a(this, paramObject);
  }

  public Leaderboard fW()
  {
    return new a(this);
  }

  public String getDisplayName()
  {
    return getString("name");
  }

  public void getDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    a("name", paramCharArrayBuffer);
  }

  public Game getGame()
  {
    return this.IM;
  }

  public Uri getIconImageUri()
  {
    return aa("board_icon_image_uri");
  }

  public String getIconImageUrl()
  {
    return getString("board_icon_image_url");
  }

  public String getLeaderboardId()
  {
    return getString("external_leaderboard_id");
  }

  public int getScoreOrder()
  {
    return getInteger("score_order");
  }

  public ArrayList<LeaderboardVariant> getVariants()
  {
    ArrayList localArrayList = new ArrayList(this.IN);
    for (int i = 0; i < this.IN; i++)
      localArrayList.add(new g(this.zU, i + this.zW));
    return localArrayList;
  }

  public int hashCode()
  {
    return a.a(this);
  }

  public String toString()
  {
    return a.b(this);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.leaderboard.b
 * JD-Core Version:    0.6.0
 */