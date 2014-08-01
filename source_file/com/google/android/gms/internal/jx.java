package com.google.android.gms.internal;

public class jx
{
  private final byte[] ZR = new byte[256];
  private int ZS;
  private int ZT;

  public jx(byte[] paramArrayOfByte)
  {
    for (int i = 0; i < 256; i++)
      this.ZR[i] = (byte)i;
    int j = 0;
    for (int k = 0; k < 256; k++)
    {
      j = 0xFF & j + this.ZR[k] + paramArrayOfByte[(k % paramArrayOfByte.length)];
      int m = this.ZR[k];
      this.ZR[k] = this.ZR[j];
      this.ZR[j] = m;
    }
    this.ZS = 0;
    this.ZT = 0;
  }

  public void m(byte[] paramArrayOfByte)
  {
    int i = this.ZS;
    int j = this.ZT;
    for (int k = 0; k < paramArrayOfByte.length; k++)
    {
      i = 0xFF & i + 1;
      j = 0xFF & j + this.ZR[i];
      int m = this.ZR[i];
      this.ZR[i] = this.ZR[j];
      this.ZR[j] = m;
      paramArrayOfByte[k] = (byte)(paramArrayOfByte[k] ^ this.ZR[(0xFF & this.ZR[i] + this.ZR[j])]);
    }
    this.ZS = i;
    this.ZT = j;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.jx
 * JD-Core Version:    0.6.0
 */