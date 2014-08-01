package com.google.android.gms.internal;

import java.util.Arrays;

public final class kg
{
  final byte[] aai;
  final int tag;

  kg(int paramInt, byte[] paramArrayOfByte)
  {
    this.tag = paramInt;
    this.aai = paramArrayOfByte;
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == this);
    kg localkg;
    do
    {
      return true;
      if (!(paramObject instanceof kg))
        return false;
      localkg = (kg)paramObject;
    }
    while ((this.tag == localkg.tag) && (Arrays.equals(this.aai, localkg.aai)));
    return false;
  }

  public int hashCode()
  {
    return 31 * (527 + this.tag) + Arrays.hashCode(this.aai);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.kg
 * JD-Core Version:    0.6.0
 */