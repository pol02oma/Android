package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.b.a;

public abstract interface ah extends IInterface
{
  public abstract IBinder a(b paramb, ab paramab, String paramString, bf parambf, int paramInt)
    throws RemoteException;

  public static abstract class a extends Binder
    implements ah
  {
    public static ah g(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
      if ((localIInterface != null) && ((localIInterface instanceof ah)))
        return (ah)localIInterface;
      return new a(paramIBinder);
    }

    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      switch (paramInt1)
      {
      default:
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902:
        paramParcel2.writeString("com.google.android.gms.ads.internal.client.IAdManagerCreator");
        return true;
      case 1:
      }
      paramParcel1.enforceInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
      b localb = b.a.G(paramParcel1.readStrongBinder());
      if (paramParcel1.readInt() != 0);
      for (ab localab = ab.CREATOR.b(paramParcel1); ; localab = null)
      {
        IBinder localIBinder = a(localb, localab, paramParcel1.readString(), bf.a.i(paramParcel1.readStrongBinder()), paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel2.writeStrongBinder(localIBinder);
        return true;
      }
    }

    private static class a
      implements ah
    {
      private IBinder ky;

      a(IBinder paramIBinder)
      {
        this.ky = paramIBinder;
      }

      public IBinder a(b paramb, ab paramab, String paramString, bf parambf, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManagerCreator");
          IBinder localIBinder1;
          if (paramb != null)
          {
            localIBinder1 = paramb.asBinder();
            localParcel1.writeStrongBinder(localIBinder1);
            if (paramab == null)
              break label137;
            localParcel1.writeInt(1);
            paramab.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            localParcel1.writeString(paramString);
            IBinder localIBinder2 = null;
            if (parambf != null)
              localIBinder2 = parambf.asBinder();
            localParcel1.writeStrongBinder(localIBinder2);
            localParcel1.writeInt(paramInt);
            this.ky.transact(1, localParcel1, localParcel2, 0);
            localParcel2.readException();
            IBinder localIBinder3 = localParcel2.readStrongBinder();
            return localIBinder3;
            localIBinder1 = null;
            break;
            label137: localParcel1.writeInt(0);
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
 * Qualified Name:     com.google.android.gms.internal.ah
 * JD-Core Version:    0.6.0
 */