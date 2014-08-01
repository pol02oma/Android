package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;

public abstract interface Player extends Parcelable, Freezable<Player>
{
  public abstract int fl();

  public abstract String getDisplayName();

  public abstract void getDisplayName(CharArrayBuffer paramCharArrayBuffer);

  public abstract Uri getHiResImageUri();

  @Deprecated
  public abstract String getHiResImageUrl();

  public abstract Uri getIconImageUri();

  @Deprecated
  public abstract String getIconImageUrl();

  public abstract long getLastPlayedWithTimestamp();

  public abstract String getPlayerId();

  public abstract long getRetrievedTimestamp();

  public abstract boolean hasHiResImage();

  public abstract boolean hasIconImage();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.Player
 * JD-Core Version:    0.6.0
 */