package com.google.android.gms.internal;

public final class gv
{
  public static String aW(int paramInt)
  {
    switch (paramInt)
    {
    default:
      fz.h("MatchTurnStatus", "Unknown match turn status: " + paramInt);
      return "TURN_STATUS_UNKNOWN";
    case 0:
      return "TURN_STATUS_INVITED";
    case 1:
      return "TURN_STATUS_MY_TURN";
    case 2:
      return "TURN_STATUS_THEIR_TURN";
    case 3:
    }
    return "TURN_STATUS_COMPLETE";
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.gv
 * JD-Core Version:    0.6.0
 */