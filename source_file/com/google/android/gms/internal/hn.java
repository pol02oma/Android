package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class hn
  implements SafeParcelable
{
  public static final ho CREATOR = new ho();
  final List<ht> LA;
  private final String LB;
  private final boolean LC;
  private final Set<ht> LD;
  final int wj;

  hn(int paramInt, List<ht> paramList, String paramString, boolean paramBoolean)
  {
    this.wj = paramInt;
    if (paramList == null);
    for (List localList = Collections.emptyList(); ; localList = Collections.unmodifiableList(paramList))
    {
      this.LA = localList;
      if (paramString == null)
        paramString = "";
      this.LB = paramString;
      this.LC = paramBoolean;
      if (!this.LA.isEmpty())
        break;
      this.LD = Collections.emptySet();
      return;
    }
    this.LD = Collections.unmodifiableSet(new HashSet(this.LA));
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    hn localhn;
    do
    {
      return true;
      if (!(paramObject instanceof hn))
        return false;
      localhn = (hn)paramObject;
    }
    while ((this.LD.equals(localhn.LD)) && (this.LB == localhn.LB) && (this.LC == localhn.LC));
    return false;
  }

  public String gr()
  {
    return this.LB;
  }

  public boolean gs()
  {
    return this.LC;
  }

  public int hashCode()
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = this.LD;
    arrayOfObject[1] = this.LB;
    arrayOfObject[2] = Boolean.valueOf(this.LC);
    return ep.hashCode(arrayOfObject);
  }

  public String toString()
  {
    return ep.e(this).a("types", this.LD).a("textQuery", this.LB).a("isOpenNowRequired", Boolean.valueOf(this.LC)).toString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ho.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.hn
 * JD-Core Version:    0.6.0
 */