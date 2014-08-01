package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.er;

public abstract class b
{
  protected final DataHolder zU;
  protected final int zW;
  private final int zX;

  public b(DataHolder paramDataHolder, int paramInt)
  {
    this.zU = ((DataHolder)er.f(paramDataHolder));
    if ((paramInt >= 0) && (paramInt < paramDataHolder.getCount()));
    for (boolean bool = true; ; bool = false)
    {
      er.v(bool);
      this.zW = paramInt;
      this.zX = paramDataHolder.I(this.zW);
      return;
    }
  }

  protected void a(String paramString, CharArrayBuffer paramCharArrayBuffer)
  {
    this.zU.copyToBuffer(paramString, this.zW, this.zX, paramCharArrayBuffer);
  }

  protected Uri aa(String paramString)
  {
    return this.zU.parseUri(paramString, this.zW, this.zX);
  }

  protected boolean ab(String paramString)
  {
    return this.zU.hasNull(paramString, this.zW, this.zX);
  }

  public boolean equals(Object paramObject)
  {
    boolean bool1 = paramObject instanceof b;
    int i = 0;
    if (bool1)
    {
      b localb = (b)paramObject;
      boolean bool2 = ep.equal(Integer.valueOf(localb.zW), Integer.valueOf(this.zW));
      i = 0;
      if (bool2)
      {
        boolean bool3 = ep.equal(Integer.valueOf(localb.zX), Integer.valueOf(this.zX));
        i = 0;
        if (bool3)
        {
          DataHolder localDataHolder1 = localb.zU;
          DataHolder localDataHolder2 = this.zU;
          i = 0;
          if (localDataHolder1 == localDataHolder2)
            i = 1;
        }
      }
    }
    return i;
  }

  protected boolean getBoolean(String paramString)
  {
    return this.zU.getBoolean(paramString, this.zW, this.zX);
  }

  protected byte[] getByteArray(String paramString)
  {
    return this.zU.getByteArray(paramString, this.zW, this.zX);
  }

  protected int getInteger(String paramString)
  {
    return this.zU.getInteger(paramString, this.zW, this.zX);
  }

  protected long getLong(String paramString)
  {
    return this.zU.getLong(paramString, this.zW, this.zX);
  }

  protected String getString(String paramString)
  {
    return this.zU.getString(paramString, this.zW, this.zX);
  }

  public boolean hasColumn(String paramString)
  {
    return this.zU.hasColumn(paramString);
  }

  public int hashCode()
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = Integer.valueOf(this.zW);
    arrayOfObject[1] = Integer.valueOf(this.zX);
    arrayOfObject[2] = this.zU;
    return ep.hashCode(arrayOfObject);
  }

  public boolean isDataValid()
  {
    return !this.zU.isClosed();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.common.data.b
 * JD-Core Version:    0.6.0
 */