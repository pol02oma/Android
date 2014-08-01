package com.google.android.gms.appstate;

import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.ep.a;

public final class a
  implements AppState
{
  private final int uU;
  private final String uV;
  private final byte[] uW;
  private final boolean uX;
  private final String uY;
  private final byte[] uZ;

  public a(AppState paramAppState)
  {
    this.uU = paramAppState.getKey();
    this.uV = paramAppState.getLocalVersion();
    this.uW = paramAppState.getLocalData();
    this.uX = paramAppState.hasConflict();
    this.uY = paramAppState.getConflictVersion();
    this.uZ = paramAppState.getConflictData();
  }

  static int a(AppState paramAppState)
  {
    Object[] arrayOfObject = new Object[6];
    arrayOfObject[0] = Integer.valueOf(paramAppState.getKey());
    arrayOfObject[1] = paramAppState.getLocalVersion();
    arrayOfObject[2] = paramAppState.getLocalData();
    arrayOfObject[3] = Boolean.valueOf(paramAppState.hasConflict());
    arrayOfObject[4] = paramAppState.getConflictVersion();
    arrayOfObject[5] = paramAppState.getConflictData();
    return ep.hashCode(arrayOfObject);
  }

  static boolean a(AppState paramAppState, Object paramObject)
  {
    int i = 1;
    if (!(paramObject instanceof AppState))
      i = 0;
    AppState localAppState;
    do
    {
      do
        return i;
      while (paramAppState == paramObject);
      localAppState = (AppState)paramObject;
    }
    while ((ep.equal(Integer.valueOf(localAppState.getKey()), Integer.valueOf(paramAppState.getKey()))) && (ep.equal(localAppState.getLocalVersion(), paramAppState.getLocalVersion())) && (ep.equal(localAppState.getLocalData(), paramAppState.getLocalData())) && (ep.equal(Boolean.valueOf(localAppState.hasConflict()), Boolean.valueOf(paramAppState.hasConflict()))) && (ep.equal(localAppState.getConflictVersion(), paramAppState.getConflictVersion())) && (ep.equal(localAppState.getConflictData(), paramAppState.getConflictData())));
    return false;
  }

  static String b(AppState paramAppState)
  {
    return ep.e(paramAppState).a("Key", Integer.valueOf(paramAppState.getKey())).a("LocalVersion", paramAppState.getLocalVersion()).a("LocalData", paramAppState.getLocalData()).a("HasConflict", Boolean.valueOf(paramAppState.hasConflict())).a("ConflictVersion", paramAppState.getConflictVersion()).a("ConflictData", paramAppState.getConflictData()).toString();
  }

  public AppState cL()
  {
    return this;
  }

  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }

  public byte[] getConflictData()
  {
    return this.uZ;
  }

  public String getConflictVersion()
  {
    return this.uY;
  }

  public int getKey()
  {
    return this.uU;
  }

  public byte[] getLocalData()
  {
    return this.uW;
  }

  public String getLocalVersion()
  {
    return this.uV;
  }

  public boolean hasConflict()
  {
    return this.uX;
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
 * Qualified Name:     com.google.android.gms.appstate.a
 * JD-Core Version:    0.6.0
 */