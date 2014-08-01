package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;
import android.os.Parcel;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.ep.a;

public final class Status
  implements Result, SafeParcelable
{
  public static final StatusCreator CREATOR;
  public static final Status zQ = new Status(0, null, null);
  public static final Status zR = new Status(14, null, null);
  public static final Status zS = new Status(15, null, null);
  private final PendingIntent mPendingIntent;
  private final int wj;
  private final int yJ;
  private final String zT;

  static
  {
    CREATOR = new StatusCreator();
  }

  public Status(int paramInt)
  {
    this(1, paramInt, null, null);
  }

  Status(int paramInt1, int paramInt2, String paramString, PendingIntent paramPendingIntent)
  {
    this.wj = paramInt1;
    this.yJ = paramInt2;
    this.zT = paramString;
    this.mPendingIntent = paramPendingIntent;
  }

  public Status(int paramInt, String paramString, PendingIntent paramPendingIntent)
  {
    this(1, paramInt, paramString, paramPendingIntent);
  }

  private String dn()
  {
    if (this.zT != null)
      return this.zT;
    return CommonStatusCodes.getStatusCodeString(this.yJ);
  }

  PendingIntent dE()
  {
    return this.mPendingIntent;
  }

  String dF()
  {
    return this.zT;
  }

  @Deprecated
  public ConnectionResult dG()
  {
    return new ConnectionResult(this.yJ, this.mPendingIntent);
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Status));
    Status localStatus;
    do
    {
      return false;
      localStatus = (Status)paramObject;
    }
    while ((this.wj != localStatus.wj) || (this.yJ != localStatus.yJ) || (!ep.equal(this.zT, localStatus.zT)) || (!ep.equal(this.mPendingIntent, localStatus.mPendingIntent)));
    return true;
  }

  public PendingIntent getResolution()
  {
    return this.mPendingIntent;
  }

  public Status getStatus()
  {
    return this;
  }

  public int getStatusCode()
  {
    return this.yJ;
  }

  int getVersionCode()
  {
    return this.wj;
  }

  public boolean hasResolution()
  {
    return this.mPendingIntent != null;
  }

  public int hashCode()
  {
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = Integer.valueOf(this.wj);
    arrayOfObject[1] = Integer.valueOf(this.yJ);
    arrayOfObject[2] = this.zT;
    arrayOfObject[3] = this.mPendingIntent;
    return ep.hashCode(arrayOfObject);
  }

  public boolean isInterrupted()
  {
    return this.yJ == 14;
  }

  public boolean isSuccess()
  {
    return this.yJ <= 0;
  }

  public void startResolutionForResult(Activity paramActivity, int paramInt)
    throws IntentSender.SendIntentException
  {
    if (!hasResolution())
      return;
    paramActivity.startIntentSenderForResult(this.mPendingIntent.getIntentSender(), paramInt, null, 0, 0, 0);
  }

  public String toString()
  {
    return ep.e(this).a("statusCode", dn()).a("resolution", this.mPendingIntent).toString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    StatusCreator.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.common.api.Status
 * JD-Core Version:    0.6.0
 */