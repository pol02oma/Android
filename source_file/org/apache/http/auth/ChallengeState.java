package org.apache.http.auth;

public enum ChallengeState
{
  static
  {
    PROXY = new ChallengeState("PROXY", 1);
    ChallengeState[] arrayOfChallengeState = new ChallengeState[2];
    arrayOfChallengeState[0] = TARGET;
    arrayOfChallengeState[1] = PROXY;
    $VALUES = arrayOfChallengeState;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.auth.ChallengeState
 * JD-Core Version:    0.6.0
 */