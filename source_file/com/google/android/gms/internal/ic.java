package com.google.android.gms.internal;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ic
{
  private static int OB = 10000;
  private static int OC = 1000;
  private final hl<hg> Lk;
  private final String OD;
  private final BlockingQueue<hx.a> OE;
  private int OF;
  private final int Ou;
  private final Object mg = new Object();

  public ic(hl<hg> paramhl, String paramString, int paramInt)
  {
    this.Lk = paramhl;
    this.OD = paramString;
    this.Ou = paramInt;
    this.OE = new LinkedBlockingQueue(OB);
    this.OF = OC;
  }

  public void a(hx.a.a parama)
  {
    parama.aK(this.OD);
    parama.bv(this.Ou);
    this.OE.offer(parama.gJ());
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ic
 * JD-Core Version:    0.6.0
 */