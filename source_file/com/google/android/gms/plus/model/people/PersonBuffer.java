package com.google.android.gms.plus.model.people;

import android.os.Bundle;
import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.c;
import com.google.android.gms.internal.ir;
import com.google.android.gms.internal.jc;

public final class PersonBuffer extends DataBuffer<Person>
{
  private final c<ir> Tu;

  public PersonBuffer(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
    if ((paramDataHolder.getMetadata() != null) && (paramDataHolder.getMetadata().getBoolean("com.google.android.gms.plus.IsSafeParcelable", false)))
    {
      this.Tu = new c(paramDataHolder, ir.CREATOR);
      return;
    }
    this.Tu = null;
  }

  public Person get(int paramInt)
  {
    if (this.Tu != null)
      return (Person)this.Tu.H(paramInt);
    return new jc(this.zU, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.plus.model.people.PersonBuffer
 * JD-Core Version:    0.6.0
 */