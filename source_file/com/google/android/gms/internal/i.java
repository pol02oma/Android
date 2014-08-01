package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public abstract class i
  implements h
{
  protected MotionEvent jY;
  protected DisplayMetrics jZ;
  protected n ka;
  private o kb;

  protected i(Context paramContext, n paramn, o paramo)
  {
    this.ka = paramn;
    this.kb = paramo;
    try
    {
      this.jZ = paramContext.getResources().getDisplayMetrics();
      return;
    }
    catch (UnsupportedOperationException localUnsupportedOperationException)
    {
      this.jZ = new DisplayMetrics();
      this.jZ.density = 1.0F;
    }
  }

  private String a(Context paramContext, String paramString, boolean paramBoolean)
  {
    try
    {
      monitorenter;
      try
      {
        t();
        if (paramBoolean)
          c(paramContext);
        while (true)
        {
          arrayOfByte = u();
          monitorexit;
          if (arrayOfByte.length != 0)
            break;
          String str2 = Integer.toString(5);
          return str2;
          b(paramContext);
        }
      }
      finally
      {
        monitorexit;
      }
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      byte[] arrayOfByte;
      return Integer.toString(7);
      String str1 = a(arrayOfByte, paramString);
      return str1;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      return Integer.toString(7);
    }
    catch (IOException localIOException)
    {
    }
    return Integer.toString(3);
  }

  private void t()
  {
    this.kb.reset();
  }

  private byte[] u()
    throws IOException
  {
    return this.kb.z();
  }

  public String a(Context paramContext)
  {
    return a(paramContext, null, false);
  }

  public String a(Context paramContext, String paramString)
  {
    return a(paramContext, paramString, true);
  }

  String a(byte[] paramArrayOfByte, String paramString)
    throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException
  {
    if (paramArrayOfByte.length > 239)
    {
      t();
      a(20, 1L);
      paramArrayOfByte = u();
    }
    byte[] arrayOfByte5;
    if (paramArrayOfByte.length < 239)
    {
      arrayOfByte5 = new byte[239 - paramArrayOfByte.length];
      new SecureRandom().nextBytes(arrayOfByte5);
    }
    for (byte[] arrayOfByte1 = ByteBuffer.allocate(240).put((byte)paramArrayOfByte.length).put(paramArrayOfByte).put(arrayOfByte5).array(); ; arrayOfByte1 = ByteBuffer.allocate(240).put((byte)paramArrayOfByte.length).put(paramArrayOfByte).array())
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(arrayOfByte1);
      byte[] arrayOfByte2 = localMessageDigest.digest();
      byte[] arrayOfByte3 = ByteBuffer.allocate(256).put(arrayOfByte2).put(arrayOfByte1).array();
      byte[] arrayOfByte4 = new byte[256];
      new f().a(arrayOfByte3, arrayOfByte4);
      if ((paramString != null) && (paramString.length() > 0))
        a(paramString, arrayOfByte4);
      return this.ka.a(arrayOfByte4, true);
    }
  }

  public void a(int paramInt1, int paramInt2, int paramInt3)
  {
    if (this.jY != null)
      this.jY.recycle();
    this.jY = MotionEvent.obtain(0L, paramInt3, 1, paramInt1 * this.jZ.density, paramInt2 * this.jZ.density, 0.0F, 0.0F, 0, 0.0F, 0.0F, 0, 0);
  }

  protected void a(int paramInt, long paramLong)
    throws IOException
  {
    this.kb.b(paramInt, paramLong);
  }

  protected void a(int paramInt, String paramString)
    throws IOException
  {
    this.kb.b(paramInt, paramString);
  }

  public void a(MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() == 1)
    {
      if (this.jY != null)
        this.jY.recycle();
      this.jY = MotionEvent.obtain(paramMotionEvent);
    }
  }

  void a(String paramString, byte[] paramArrayOfByte)
    throws UnsupportedEncodingException
  {
    if (paramString.length() > 32)
      paramString = paramString.substring(0, 32);
    new jx(paramString.getBytes("UTF-8")).m(paramArrayOfByte);
  }

  protected abstract void b(Context paramContext);

  protected abstract void c(Context paramContext);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.i
 * JD-Core Version:    0.6.0
 */