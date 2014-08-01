package com.google.android.gms.internal;

import android.os.SystemClock;
import org.json.JSONObject;

public final class dy
{
  private static final du xE = new du("RequestTracker");
  public static final Object yD = new Object();
  private long yA;
  private long yB;
  private dx yC;
  private long yz;

  public dy(long paramLong)
  {
    this.yz = paramLong;
    this.yA = -1L;
    this.yB = 0L;
  }

  private void dk()
  {
    this.yA = -1L;
    this.yC = null;
    this.yB = 0L;
  }

  public void a(long paramLong, dx paramdx)
  {
    synchronized (yD)
    {
      dx localdx = this.yC;
      long l = this.yA;
      this.yA = paramLong;
      this.yC = paramdx;
      this.yB = SystemClock.elapsedRealtime();
      if (localdx != null)
        localdx.k(l);
      return;
    }
  }

  public boolean b(long paramLong, int paramInt, JSONObject paramJSONObject)
  {
    for (int i = 1; ; i = 0)
    {
      synchronized (yD)
      {
        if ((this.yA != -1L) && (this.yA == paramLong))
        {
          du localdu = xE;
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Long.valueOf(this.yA);
          localdu.b("request %d completed", arrayOfObject);
          localdx = this.yC;
          dk();
          if (localdx != null)
            localdx.a(paramLong, paramInt, paramJSONObject);
          return i;
        }
      }
      dx localdx = null;
    }
  }

  public boolean c(long paramLong, int paramInt)
  {
    return b(paramLong, paramInt, null);
  }

  public void clear()
  {
    synchronized (yD)
    {
      if (this.yA != -1L)
        dk();
      return;
    }
  }

  public boolean d(long paramLong, int paramInt)
  {
    int i = 1;
    long l = 0L;
    while (true)
    {
      synchronized (yD)
      {
        if ((this.yA != -1L) && (paramLong - this.yB >= this.yz))
        {
          du localdu = xE;
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Long.valueOf(this.yA);
          localdu.b("request %d timed out", arrayOfObject);
          l = this.yA;
          localdx = this.yC;
          dk();
          if (localdx == null)
            continue;
          localdx.a(l, paramInt, null);
          return i;
        }
      }
      dx localdx = null;
      i = 0;
    }
  }

  public boolean dl()
  {
    while (true)
    {
      synchronized (yD)
      {
        if (this.yA != -1L)
        {
          i = 1;
          return i;
        }
      }
      int i = 0;
    }
  }

  public boolean m(long paramLong)
  {
    while (true)
    {
      synchronized (yD)
      {
        if ((this.yA != -1L) && (this.yA == paramLong))
        {
          i = 1;
          return i;
        }
      }
      int i = 0;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.dy
 * JD-Core Version:    0.6.0
 */