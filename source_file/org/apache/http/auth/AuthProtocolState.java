package org.apache.http.auth;

public enum AuthProtocolState
{
  static
  {
    CHALLENGED = new AuthProtocolState("CHALLENGED", 1);
    HANDSHAKE = new AuthProtocolState("HANDSHAKE", 2);
    FAILURE = new AuthProtocolState("FAILURE", 3);
    SUCCESS = new AuthProtocolState("SUCCESS", 4);
    AuthProtocolState[] arrayOfAuthProtocolState = new AuthProtocolState[5];
    arrayOfAuthProtocolState[0] = UNCHALLENGED;
    arrayOfAuthProtocolState[1] = CHALLENGED;
    arrayOfAuthProtocolState[2] = HANDSHAKE;
    arrayOfAuthProtocolState[3] = FAILURE;
    arrayOfAuthProtocolState[4] = SUCCESS;
    $VALUES = arrayOfAuthProtocolState;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.auth.AuthProtocolState
 * JD-Core Version:    0.6.0
 */