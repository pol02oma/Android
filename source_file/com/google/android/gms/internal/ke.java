package com.google.android.gms.internal;

import java.io.IOException;

public abstract class ke
{
  protected int DY = -1;

  public static final <T extends ke> T a(T paramT, byte[] paramArrayOfByte)
    throws kd
  {
    return b(paramT, paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public static final void a(ke paramke, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    try
    {
      jz localjz = jz.b(paramArrayOfByte, paramInt1, paramInt2);
      paramke.a(localjz);
      localjz.kN();
      return;
    }
    catch (IOException localIOException)
    {
    }
    throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", localIOException);
  }

  public static final <T extends ke> T b(T paramT, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws kd
  {
    try
    {
      jy localjy = jy.a(paramArrayOfByte, paramInt1, paramInt2);
      paramT.b(localjy);
      localjy.cu(0);
      return paramT;
    }
    catch (kd localkd)
    {
      throw localkd;
    }
    catch (IOException localIOException)
    {
    }
    throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
  }

  public static final byte[] d(ke paramke)
  {
    byte[] arrayOfByte = new byte[paramke.c()];
    a(paramke, arrayOfByte, 0, arrayOfByte.length);
    return arrayOfByte;
  }

  public void a(jz paramjz)
    throws IOException
  {
  }

  public abstract ke b(jy paramjy)
    throws IOException;

  public int c()
  {
    this.DY = 0;
    return 0;
  }

  public int eW()
  {
    if (this.DY < 0)
      c();
    return this.DY;
  }

  public String toString()
  {
    return kf.e(this);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ke
 * JD-Core Version:    0.6.0
 */