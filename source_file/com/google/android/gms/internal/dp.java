package com.google.android.gms.internal;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class dp
{
  private static final AtomicInteger xA = new AtomicInteger(0);
  protected final du xB;
  private final String xC;
  private dw xD;

  protected dp(String paramString1, String paramString2)
  {
    this.xC = paramString1;
    this.xB = new du(paramString2);
    du localdu = this.xB;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(xA.incrementAndGet());
    localdu.U(String.format("instance-%d", arrayOfObject));
  }

  public void P(String paramString)
  {
  }

  public void a(long paramLong, int paramInt)
  {
  }

  public final void a(dw paramdw)
  {
    this.xD = paramdw;
    if (this.xD == null)
      cX();
  }

  protected final void a(String paramString1, long paramLong, String paramString2)
    throws IOException
  {
    this.xB.a("Sending text message: %s to: %s", new Object[] { paramString1, paramString2 });
    this.xD.a(this.xC, paramString1, paramLong, paramString2);
  }

  protected final long cW()
  {
    return this.xD.cV();
  }

  public void cX()
  {
  }

  public final String getNamespace()
  {
    return this.xC;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.dp
 * JD-Core Version:    0.6.0
 */