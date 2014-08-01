package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.er;

public final class NotifyTransactionStatusRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<NotifyTransactionStatusRequest> CREATOR = new m();
  String Yk;
  String Zk;
  int status;
  final int wj;

  NotifyTransactionStatusRequest()
  {
    this.wj = 1;
  }

  NotifyTransactionStatusRequest(int paramInt1, String paramString1, int paramInt2, String paramString2)
  {
    this.wj = paramInt1;
    this.Yk = paramString1;
    this.status = paramInt2;
    this.Zk = paramString2;
  }

  public static Builder newBuilder()
  {
    NotifyTransactionStatusRequest localNotifyTransactionStatusRequest = new NotifyTransactionStatusRequest();
    localNotifyTransactionStatusRequest.getClass();
    return new Builder(null);
  }

  public int describeContents()
  {
    return 0;
  }

  public String getDetailedReason()
  {
    return this.Zk;
  }

  public String getGoogleTransactionId()
  {
    return this.Yk;
  }

  public int getStatus()
  {
    return this.status;
  }

  public int getVersionCode()
  {
    return this.wj;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    m.a(this, paramParcel, paramInt);
  }

  public final class Builder
  {
    private Builder()
    {
    }

    public NotifyTransactionStatusRequest build()
    {
      int i = 1;
      if (!TextUtils.isEmpty(NotifyTransactionStatusRequest.this.Yk))
      {
        int k = i;
        er.b(k, "googleTransactionId is required");
        if ((NotifyTransactionStatusRequest.this.status < i) || (NotifyTransactionStatusRequest.this.status > 8))
          break label62;
      }
      while (true)
      {
        er.b(i, "status is an unrecognized value");
        return NotifyTransactionStatusRequest.this;
        int m = 0;
        break;
        label62: int j = 0;
      }
    }

    public Builder setDetailedReason(String paramString)
    {
      NotifyTransactionStatusRequest.this.Zk = paramString;
      return this;
    }

    public Builder setGoogleTransactionId(String paramString)
    {
      NotifyTransactionStatusRequest.this.Yk = paramString;
      return this;
    }

    public Builder setStatus(int paramInt)
    {
      NotifyTransactionStatusRequest.this.status = paramInt;
      return this;
    }
  }

  public static abstract interface Status
  {
    public static final int SUCCESS = 1;

    public static abstract interface Error
    {
      public static final int AVS_DECLINE = 7;
      public static final int BAD_CARD = 4;
      public static final int BAD_CVC = 3;
      public static final int DECLINED = 5;
      public static final int FRAUD_DECLINE = 8;
      public static final int OTHER = 6;
      public static final int UNKNOWN = 2;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.wallet.NotifyTransactionStatusRequest
 * JD-Core Version:    0.6.0
 */