package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;

public abstract interface Game extends Parcelable, Freezable<Game>
{
  public abstract int getAchievementTotalCount();

  public abstract String getApplicationId();

  public abstract String getDescription();

  public abstract void getDescription(CharArrayBuffer paramCharArrayBuffer);

  public abstract String getDeveloperName();

  public abstract void getDeveloperName(CharArrayBuffer paramCharArrayBuffer);

  public abstract String getDisplayName();

  public abstract void getDisplayName(CharArrayBuffer paramCharArrayBuffer);

  public abstract Uri getFeaturedImageUri();

  @Deprecated
  public abstract String getFeaturedImageUrl();

  public abstract int getGameplayAclStatus();

  public abstract Uri getHiResImageUri();

  @Deprecated
  public abstract String getHiResImageUrl();

  public abstract Uri getIconImageUri();

  @Deprecated
  public abstract String getIconImageUrl();

  public abstract String getInstancePackageName();

  public abstract int getLeaderboardCount();

  public abstract String getPrimaryCategory();

  public abstract String getSecondaryCategory();

  public abstract boolean isInstanceInstalled();

  public abstract boolean isMuted();

  public abstract boolean isPlayEnabledGame();

  public abstract boolean isRealTimeMultiplayerEnabled();

  public abstract boolean isTurnBasedMultiplayerEnabled();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.Game
 * JD-Core Version:    0.6.0
 */