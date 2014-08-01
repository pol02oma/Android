package com.google.android.gms.common.data;

import com.google.android.gms.internal.er;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class a<T>
  implements Iterator<T>
{
  private final DataBuffer<T> mDataBuffer;
  private int zV;

  public a(DataBuffer<T> paramDataBuffer)
  {
    this.mDataBuffer = ((DataBuffer)er.f(paramDataBuffer));
    this.zV = -1;
  }

  public boolean hasNext()
  {
    return this.zV < -1 + this.mDataBuffer.getCount();
  }

  public T next()
  {
    if (!hasNext())
      throw new NoSuchElementException("Cannot advance the iterator beyond " + this.zV);
    DataBuffer localDataBuffer = this.mDataBuffer;
    int i = 1 + this.zV;
    this.zV = i;
    return localDataBuffer.get(i);
  }

  public void remove()
  {
    throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.common.data.a
 * JD-Core Version:    0.6.0
 */