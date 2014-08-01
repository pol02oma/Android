package com.google.android.gms.games.multiplayer.realtime;

import B;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.er;

public final class RealTimeMessage
  implements Parcelable
{
  public static final Parcelable.Creator<RealTimeMessage> CREATOR = new Parcelable.Creator()
  {
    public RealTimeMessage ap(Parcel paramParcel)
    {
      return new RealTimeMessage(paramParcel, null);
    }

    public RealTimeMessage[] bc(int paramInt)
    {
      return new RealTimeMessage[paramInt];
    }
  };
  public static final int RELIABLE = 1;
  public static final int UNRELIABLE;
  private final String JH;
  private final byte[] JI;
  private final int JJ;

  private RealTimeMessage(Parcel paramParcel)
  {
    this(paramParcel.readString(), paramParcel.createByteArray(), paramParcel.readInt());
  }

  public RealTimeMessage(String paramString, byte[] paramArrayOfByte, int paramInt)
  {
    this.JH = ((String)er.f(paramString));
    this.JI = ((byte[])((byte[])er.f(paramArrayOfByte)).clone());
    this.JJ = paramInt;
  }

  public int describeContents()
  {
    return 0;
  }

  public byte[] getMessageData()
  {
    return this.JI;
  }

  public String getSenderParticipantId()
  {
    return this.JH;
  }

  public boolean isReliable()
  {
    return this.JJ == 1;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.JH);
    paramParcel.writeByteArray(this.JI);
    paramParcel.writeInt(this.JJ);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.games.multiplayer.realtime.RealTimeMessage
 * JD-Core Version:    0.6.0
 */