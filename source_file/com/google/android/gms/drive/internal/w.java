package com.google.android.gms.drive.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface w extends IInterface
{
  public abstract void a(OnEventResponse paramOnEventResponse)
    throws RemoteException;

  public static abstract class a extends Binder
    implements w
  {
    public a()
    {
      attachInterface(this, "com.google.android.gms.drive.internal.IEventCallback");
    }

    public static w F(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.drive.internal.IEventCallback");
      if ((localIInterface != null) && ((localIInterface instanceof w)))
        return (w)localIInterface;
      return new a(paramIBinder);
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
        paramParcel2.writeString("com.google.android.gms.drive.internal.IEventCallback");
        return true;
      case 1:
      }
      paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IEventCallback");
      if (paramParcel1.readInt() != 0);
      for (OnEventResponse localOnEventResponse = (OnEventResponse)OnEventResponse.CREATOR.createFromParcel(paramParcel1); ; localOnEventResponse = null)
      {
        a(localOnEventResponse);
        paramParcel2.writeNoException();
        return true;
      }
    }

    private static class a
      implements w
    {
      private IBinder ky;

      a(IBinder paramIBinder)
      {
        this.ky = paramIBinder;
      }

      public void a(OnEventResponse paramOnEventResponse)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IEventCallback");
          if (paramOnEventResponse != null)
          {
            localParcel1.writeInt(1);
            paramOnEventResponse.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(1, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public IBinder asBinder()
      {
        return this.ky;
      }
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.w
 * JD-Core Version:    0.6.0
 */