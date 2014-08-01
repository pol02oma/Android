package com.google.android.gms.games.request;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.Player;
import java.util.ArrayList;

public abstract interface GameRequest extends Parcelable, Freezable<GameRequest>
{
  public static final int RECIPIENT_STATUS_ACCEPTED = 1;
  public static final int RECIPIENT_STATUS_PENDING = 0;
  public static final int TYPE_ALL = 65535;
  public static final int TYPE_GIFT = 1;
  public static final int TYPE_WISH = 2;

  public abstract ArrayList<Player> fU();

  public abstract long getCreationTimestamp();

  public abstract byte[] getData();

  public abstract long getExpirationTimestamp();

  public abstract Game getGame();

  public abstract Player getRecipient();

  public abstract int getRecipientStatus();

  public abstract int getRecipientStatus(String paramString);

  public abstract String getRequestId();

  public abstract Player getSender();

  public abstract int getType();

  public abstract boolean isConsumed();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.request.GameRequest
 * JD-Core Version:    0.6.0
 */