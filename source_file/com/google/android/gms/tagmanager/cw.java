package com.google.android.gms.tagmanager;

class cw
  implements cg
{
  private long Xe;
  private final long tP;
  private final int tQ;
  private double tR;
  private final Object tT = new Object();

  public cw()
  {
    this(60, 2000L);
  }

  public cw(int paramInt, long paramLong)
  {
    this.tQ = paramInt;
    this.tR = this.tQ;
    this.tP = paramLong;
  }

  public boolean cl()
  {
    synchronized (this.tT)
    {
      long l = System.currentTimeMillis();
      if (this.tR < this.tQ)
      {
        double d = (l - this.Xe) / this.tP;
        if (d > 0.0D)
          this.tR = Math.min(this.tQ, d + this.tR);
      }
      this.Xe = l;
      if (this.tR >= 1.0D)
      {
        this.tR -= 1.0D;
        return true;
      }
      bh.w("No more tokens available.");
      return false;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.cw
 * JD-Core Version:    0.6.0
 */