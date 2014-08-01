package com.google.android.gms.games.achievement;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.b;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.d;
import com.google.android.gms.internal.ed;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.ep.a;

public final class a extends b
  implements Achievement
{
  a(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
  }

  public String getAchievementId()
  {
    return getString("external_achievement_id");
  }

  public int getCurrentSteps()
  {
    int i = 1;
    if (getType() == i);
    while (true)
    {
      ed.v(i);
      return getInteger("current_steps");
      int j = 0;
    }
  }

  public String getDescription()
  {
    return getString("description");
  }

  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    a("description", paramCharArrayBuffer);
  }

  public String getFormattedCurrentSteps()
  {
    int i = 1;
    if (getType() == i);
    while (true)
    {
      ed.v(i);
      return getString("formatted_current_steps");
      int j = 0;
    }
  }

  public void getFormattedCurrentSteps(CharArrayBuffer paramCharArrayBuffer)
  {
    int i = 1;
    if (getType() == i);
    while (true)
    {
      ed.v(i);
      a("formatted_current_steps", paramCharArrayBuffer);
      return;
      int j = 0;
    }
  }

  public String getFormattedTotalSteps()
  {
    int i = 1;
    if (getType() == i);
    while (true)
    {
      ed.v(i);
      return getString("formatted_total_steps");
      int j = 0;
    }
  }

  public void getFormattedTotalSteps(CharArrayBuffer paramCharArrayBuffer)
  {
    int i = 1;
    if (getType() == i);
    while (true)
    {
      ed.v(i);
      a("formatted_total_steps", paramCharArrayBuffer);
      return;
      int j = 0;
    }
  }

  public long getLastUpdatedTimestamp()
  {
    return getLong("last_updated_timestamp");
  }

  public String getName()
  {
    return getString("name");
  }

  public void getName(CharArrayBuffer paramCharArrayBuffer)
  {
    a("name", paramCharArrayBuffer);
  }

  public Player getPlayer()
  {
    return new d(this.zU, this.zW);
  }

  public Uri getRevealedImageUri()
  {
    return aa("revealed_icon_image_uri");
  }

  public int getState()
  {
    return getInteger("state");
  }

  public int getTotalSteps()
  {
    int i = 1;
    if (getType() == i);
    while (true)
    {
      ed.v(i);
      return getInteger("total_steps");
      int j = 0;
    }
  }

  public int getType()
  {
    return getInteger("type");
  }

  public Uri getUnlockedImageUri()
  {
    return aa("unlocked_icon_image_uri");
  }

  public String toString()
  {
    ep.a locala = ep.e(this).a("id", getAchievementId()).a("name", getName()).a("state", Integer.valueOf(getState())).a("type", Integer.valueOf(getType()));
    if (getType() == 1)
      locala.a("steps", getCurrentSteps() + "/" + getTotalSteps());
    return locala.toString();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.achievement.a
 * JD-Core Version:    0.6.0
 */