package com.google.android.gms.analytics;

class z
  implements ad
{
  private final long tP;
  private final int tQ;
  private double tR;
  private long tS;
  private final Object tT = new Object();
  private final String tU;

  public z(int paramInt, long paramLong, String paramString)
  {
    this.tQ = paramInt;
    this.tR = this.tQ;
    this.tP = paramLong;
    this.tU = paramString;
  }

  public z(String paramString)
  {
    this(60, 2000L, paramString);
  }

  public boolean cl()
  {
    synchronized (this.tT)
    {
      long l = System.currentTimeMillis();
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
      aa.w("Excessive " + this.tU + " detected; call ignored.");
      return false;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.analytics.z
 * JD-Core Version:    0.6.0
 */