package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface ch extends IInterface
{
  public abstract cf b(cd paramcd)
    throws RemoteException;

  public static abstract class a extends Binder
    implements ch
  {
    public a()
    {
      attachInterface(this, "com.google.android.gms.ads.internal.request.IAdRequestService");
    }

    public static ch q(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.request.IAdRequestService");
      if ((localIInterface != null) && ((localIInterface instanceof ch)))
        return (ch)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.ads.internal.request.IAdRequestService");
        return true;
      case 1:
      }
      paramParcel1.enforceInterface("com.google.android.gms.ads.internal.request.IAdRequestService");
      cd localcd;
      if (paramParcel1.readInt() != 0)
      {
        localcd = cd.CREATOR.f(paramParcel1);
        cf localcf = b(localcd);
        paramParcel2.writeNoException();
        if (localcf == null)
          break label105;
        paramParcel2.writeInt(1);
        localcf.writeToParcel(paramParcel2, 1);
      }
      while (true)
      {
        return true;
        localcd = null;
        break;
        label105: paramParcel2.writeInt(0);
      }
    }

    private static class a
      implements ch
    {
      private IBinder ky;

      a(IBinder paramIBinder)
      {
        this.ky = paramIBinder;
      }

      public IBinder asBinder()
      {
        return this.ky;
      }

      public cf b(cd paramcd)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.request.IAdRequestService");
            if (paramcd == null)
              continue;
            localParcel1.writeInt(1);
            paramcd.writeToParcel(localParcel1, 0);
            this.ky.transact(1, localParcel1, localParcel2, 0);
            localParcel2.readException();
            if (localParcel2.readInt() != 0)
            {
              cf localcf2 = cf.CREATOR.g(localParcel2);
              localcf1 = localcf2;
              return localcf1;
              localParcel1.writeInt(0);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          cf localcf1 = null;
        }
      }
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ch
 * JD-Core Version:    0.6.0
 */