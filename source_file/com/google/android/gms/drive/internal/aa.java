package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class aa
  implements Parcelable.Creator<OnDownloadProgressResponse>
{
  static void a(OnDownloadProgressResponse paramOnDownloadProgressResponse, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramOnDownloadProgressResponse.wj);
    b.a(paramParcel, 2, paramOnDownloadProgressResponse.DZ);
    b.a(paramParcel, 3, paramOnDownloadProgressResponse.Ea);
    b.D(paramParcel, i);
  }

  public OnDownloadProgressResponse O(Parcel paramParcel)
  {
    long l1 = 0L;
    int i = a.o(paramParcel);
    int j = 0;
    long l2 = l1;
    while (paramParcel.dataPosition() < i)
    {
      int k = a.n(paramParcel);
      switch (a.S(k))
      {
      default:
        a.b(paramParcel, k);
        break;
      case 1:
        j = a.g(paramParcel, k);
        break;
      case 2:
        l2 = a.h(paramParcel, k);
        break;
      case 3:
        l1 = a.h(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new OnDownloadProgressResponse(j, l2, l1);
  }

  public OnDownloadProgressResponse[] at(int paramInt)
  {
    return new OnDownloadProgressResponse[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.aa
 * JD-Core Version:    0.6.0
 */