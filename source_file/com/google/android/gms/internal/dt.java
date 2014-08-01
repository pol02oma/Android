package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;

public abstract interface dt extends IInterface
{
  public abstract void A(int paramInt)
    throws RemoteException;

  public abstract void B(int paramInt)
    throws RemoteException;

  public abstract void C(int paramInt)
    throws RemoteException;

  public abstract void a(ApplicationMetadata paramApplicationMetadata, String paramString1, String paramString2, boolean paramBoolean)
    throws RemoteException;

  public abstract void a(String paramString, long paramLong)
    throws RemoteException;

  public abstract void a(String paramString, long paramLong, int paramInt)
    throws RemoteException;

  public abstract void b(String paramString, double paramDouble, boolean paramBoolean)
    throws RemoteException;

  public abstract void b(String paramString, byte[] paramArrayOfByte)
    throws RemoteException;

  public abstract void d(String paramString1, String paramString2)
    throws RemoteException;

  public abstract void onApplicationDisconnected(int paramInt)
    throws RemoteException;

  public abstract void z(int paramInt)
    throws RemoteException;

  public static abstract class a extends Binder
    implements dt
  {
    public a()
    {
      attachInterface(this, "com.google.android.gms.cast.internal.ICastDeviceControllerListener");
    }

    public IBinder asBinder()
    {
      return this;
    }

    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      switch (paramInt1)
      {
      default:
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902:
        paramParcel2.writeString("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        return true;
      case 1:
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        z(paramParcel1.readInt());
        return true;
      case 2:
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        if (paramParcel1.readInt() != 0);
        for (ApplicationMetadata localApplicationMetadata = (ApplicationMetadata)ApplicationMetadata.CREATOR.createFromParcel(paramParcel1); ; localApplicationMetadata = null)
        {
          String str2 = paramParcel1.readString();
          String str3 = paramParcel1.readString();
          int j = paramParcel1.readInt();
          boolean bool2 = false;
          if (j != 0)
            bool2 = true;
          a(localApplicationMetadata, str2, str3, bool2);
          return true;
        }
      case 3:
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        A(paramParcel1.readInt());
        return true;
      case 4:
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        String str1 = paramParcel1.readString();
        double d = paramParcel1.readDouble();
        int i = paramParcel1.readInt();
        boolean bool1 = false;
        if (i != 0)
          bool1 = true;
        b(str1, d, bool1);
        return true;
      case 5:
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        d(paramParcel1.readString(), paramParcel1.readString());
        return true;
      case 6:
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        b(paramParcel1.readString(), paramParcel1.createByteArray());
        return true;
      case 7:
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        C(paramParcel1.readInt());
        return true;
      case 8:
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        B(paramParcel1.readInt());
        return true;
      case 9:
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        onApplicationDisconnected(paramParcel1.readInt());
        return true;
      case 10:
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        a(paramParcel1.readString(), paramParcel1.readLong(), paramParcel1.readInt());
        return true;
      case 11:
      }
      paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
      a(paramParcel1.readString(), paramParcel1.readLong());
      return true;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.dt
 * JD-Core Version:    0.6.0
 */