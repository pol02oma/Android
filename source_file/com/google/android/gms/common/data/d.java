package com.google.android.gms.common.data;

import java.util.ArrayList;

public abstract class d<T> extends DataBuffer<T>
{
  private boolean Ap = false;
  private ArrayList<Integer> Aq;

  protected d(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
  }

  private int K(int paramInt)
  {
    if ((paramInt < 0) || (paramInt == this.Aq.size()))
      return 0;
    if (paramInt == -1 + this.Aq.size())
      return this.zU.getCount() - ((Integer)this.Aq.get(paramInt)).intValue();
    return ((Integer)this.Aq.get(paramInt + 1)).intValue() - ((Integer)this.Aq.get(paramInt)).intValue();
  }

  private void dK()
  {
    monitorenter;
    while (true)
    {
      int k;
      try
      {
        if (this.Ap)
          continue;
        int i = this.zU.getCount();
        this.Aq = new ArrayList();
        if (i <= 0)
          continue;
        this.Aq.add(Integer.valueOf(0));
        String str = getPrimaryDataMarkerColumn();
        int j = this.zU.I(0);
        localObject2 = this.zU.getString(str, 0, j);
        k = 1;
        if (k >= i)
          continue;
        int m = this.zU.I(k);
        localObject3 = this.zU.getString(str, k, m);
        if (!((String)localObject3).equals(localObject2))
        {
          this.Aq.add(Integer.valueOf(k));
          break label152;
          this.Ap = true;
          return;
        }
      }
      finally
      {
        monitorexit;
      }
      Object localObject3 = localObject2;
      label152: k++;
      Object localObject2 = localObject3;
    }
  }

  int J(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.Aq.size()))
      throw new IllegalArgumentException("Position " + paramInt + " is out of bounds for this buffer");
    return ((Integer)this.Aq.get(paramInt)).intValue();
  }

  protected abstract T c(int paramInt1, int paramInt2);

  public final T get(int paramInt)
  {
    dK();
    return c(J(paramInt), K(paramInt));
  }

  public int getCount()
  {
    dK();
    return this.Aq.size();
  }

  protected abstract String getPrimaryDataMarkerColumn();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.common.data.d
 * JD-Core Version:    0.6.0
 */