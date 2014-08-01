package com.google.android.gms.appstate;

import com.google.android.gms.common.data.DataHolder;

public final class b extends com.google.android.gms.common.data.b
  implements AppState
{
  b(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
  }

  public AppState cL()
  {
    return new a(this);
  }

  public boolean equals(Object paramObject)
  {
    return a.a(this, paramObject);
  }

  public byte[] getConflictData()
  {
    return getByteArray("conflict_data");
  }

  public String getConflictVersion()
  {
    return getString("conflict_version");
  }

  public int getKey()
  {
    return getInteger("key");
  }

  public byte[] getLocalData()
  {
    return getByteArray("local_data");
  }

  public String getLocalVersion()
  {
    return getString("local_version");
  }

  public boolean hasConflict()
  {
    return !ab("conflict_version");
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
 * Qualified Name:     com.google.android.gms.appstate.b
 * JD-Core Version:    0.6.0
 */