package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.fl;

class bf
  implements cg
{
  private final fl Ty;
  private final long Vz;
  private final long tP;
  private final int tQ;
  private double tR;
  private long tS;
  private final Object tT = new Object();
  private final String tU;

  public bf(int paramInt, long paramLong1, long paramLong2, String paramString, fl paramfl)
  {
    this.tQ = paramInt;
    this.tR = this.tQ;
    this.tP = paramLong1;
    this.Vz = paramLong2;
    this.tU = paramString;
    this.Ty = paramfl;
  }

  public boolean cl()
  {
    synchronized (this.tT)
    {
      long l = this.Ty.currentTimeMillis();
      if (l - this.tS < this.Vz)
      {
        bh.w("Excessive " + this.tU + " detected; call ignored.");
        return false;
      }
      if (this.tR < this.tQ)
      {
        double d = (l - this.tS) / this.tP;
        if (d > 0.0D)
          this.tR = Math.min(this.tQ, d + this.tR);
      }
      this.tS = l;
      if (this.tR >= 1.0D)
      {
        this.tR -= 1.0D;
        return true;
      }
    }
    bh.w("Excessive " + this.tU + " detected; call ignored.");
    monitorexit;
    return false;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.bf
 * JD-Core Version:    0.6.0
 */