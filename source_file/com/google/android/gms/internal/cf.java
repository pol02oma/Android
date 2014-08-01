package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.List;

public final class cf
  implements SafeParcelable
{
  public static final cg CREATOR = new cg();
  public final int errorCode;
  public final List<String> mt;
  public final List<String> mu;
  public final long mx;
  public final String nw;
  public final String oi;
  public final long oj;
  public final boolean ok;
  public final long ol;
  public final List<String> om;
  public final String on;
  public final long oo;
  public final int orientation;
  public final int versionCode;

  public cf(int paramInt)
  {
    this(3, null, null, null, paramInt, null, -1L, false, -1L, null, -1L, -1, null, -1L);
  }

  cf(int paramInt1, String paramString1, String paramString2, List<String> paramList1, int paramInt2, List<String> paramList2, long paramLong1, boolean paramBoolean, long paramLong2, List<String> paramList3, long paramLong3, int paramInt3, String paramString3, long paramLong4)
  {
    this.versionCode = paramInt1;
    this.nw = paramString1;
    this.oi = paramString2;
    List localList1;
    List localList2;
    if (paramList1 != null)
    {
      localList1 = Collections.unmodifiableList(paramList1);
      this.mt = localList1;
      this.errorCode = paramInt2;
      if (paramList2 == null)
        break label128;
      localList2 = Collections.unmodifiableList(paramList2);
      label55: this.mu = localList2;
      this.oj = paramLong1;
      this.ok = paramBoolean;
      this.ol = paramLong2;
      if (paramList3 == null)
        break label134;
    }
    label128: label134: for (List localList3 = Collections.unmodifiableList(paramList3); ; localList3 = null)
    {
      this.om = localList3;
      this.mx = paramLong3;
      this.orientation = paramInt3;
      this.on = paramString3;
      this.oo = paramLong4;
      return;
      localList1 = null;
      break;
      localList2 = null;
      break label55;
    }
  }

  public cf(String paramString1, String paramString2, List<String> paramList1, List<String> paramList2, long paramLong1, boolean paramBoolean, long paramLong2, List<String> paramList3, long paramLong3, int paramInt, String paramString3, long paramLong4)
  {
    this(3, paramString1, paramString2, paramList1, -2, paramList2, paramLong1, paramBoolean, paramLong2, paramList3, paramLong3, paramInt, paramString3, paramLong4);
  }

  public int describeContents()
  {
    return 0;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    cg.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.cf
 * JD-Core Version:    0.6.0
 */