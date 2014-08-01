package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.b.a;

public abstract interface bg extends IInterface
{
  public abstract void a(b paramb, ab paramab, z paramz, String paramString, bh parambh)
    throws RemoteException;

  public abstract void a(b paramb, ab paramab, z paramz, String paramString1, String paramString2, bh parambh)
    throws RemoteException;

  public abstract void a(b paramb, z paramz, String paramString, bh parambh)
    throws RemoteException;

  public abstract void a(b paramb, z paramz, String paramString1, String paramString2, bh parambh)
    throws RemoteException;

  public abstract void destroy()
    throws RemoteException;

  public abstract b getView()
    throws RemoteException;

  public abstract void showInterstitial()
    throws RemoteException;

  public static abstract class a extends Binder
    implements bg
  {
    public a()
    {
      attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
    }

    public static bg j(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
      if ((localIInterface != null) && ((localIInterface instanceof bg)))
        return (bg)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
        return true;
      case 1:
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
        b localb5 = b.a.G(paramParcel1.readStrongBinder());
        ab localab2;
        if (paramParcel1.readInt() != 0)
        {
          localab2 = ab.CREATOR.b(paramParcel1);
          if (paramParcel1.readInt() == 0)
            break label174;
        }
        for (z localz4 = z.CREATOR.a(paramParcel1); ; localz4 = null)
        {
          a(localb5, localab2, localz4, paramParcel1.readString(), bh.a.k(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
          localab2 = null;
          break;
        }
      case 2:
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
        b localb4 = getView();
        paramParcel2.writeNoException();
        IBinder localIBinder = null;
        if (localb4 != null)
          localIBinder = localb4.asBinder();
        paramParcel2.writeStrongBinder(localIBinder);
        return true;
      case 3:
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
        b localb3 = b.a.G(paramParcel1.readStrongBinder());
        int i = paramParcel1.readInt();
        z localz3 = null;
        if (i != 0)
          localz3 = z.CREATOR.a(paramParcel1);
        a(localb3, localz3, paramParcel1.readString(), bh.a.k(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 4:
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
        showInterstitial();
        paramParcel2.writeNoException();
        return true;
      case 5:
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
        destroy();
        paramParcel2.writeNoException();
        return true;
      case 6:
        label174: paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
        b localb2 = b.a.G(paramParcel1.readStrongBinder());
        ab localab1;
        if (paramParcel1.readInt() != 0)
        {
          localab1 = ab.CREATOR.b(paramParcel1);
          if (paramParcel1.readInt() == 0)
            break label400;
        }
        label400: for (z localz2 = z.CREATOR.a(paramParcel1); ; localz2 = null)
        {
          a(localb2, localab1, localz2, paramParcel1.readString(), paramParcel1.readString(), bh.a.k(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
          localab1 = null;
          break;
        }
      case 7:
      }
      paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
      b localb1 = b.a.G(paramParcel1.readStrongBinder());
      if (paramParcel1.readInt() != 0);
      for (z localz1 = z.CREATOR.a(paramParcel1); ; localz1 = null)
      {
        a(localb1, localz1, paramParcel1.readString(), paramParcel1.readString(), bh.a.k(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      }
    }

    private static class a
      implements bg
    {
      private IBinder ky;

      a(IBinder paramIBinder)
      {
        this.ky = paramIBinder;
      }

      public void a(b paramb, ab paramab, z paramz, String paramString, bh parambh)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            if (paramb == null)
              continue;
            IBinder localIBinder1 = paramb.asBinder();
            localParcel1.writeStrongBinder(localIBinder1);
            if (paramab == null)
              continue;
            localParcel1.writeInt(1);
            paramab.writeToParcel(localParcel1, 0);
            if (paramz != null)
            {
              localParcel1.writeInt(1);
              paramz.writeToParcel(localParcel1, 0);
              localParcel1.writeString(paramString);
              IBinder localIBinder2 = null;
              if (parambh == null)
                continue;
              localIBinder2 = parambh.asBinder();
              localParcel1.writeStrongBinder(localIBinder2);
              this.ky.transact(1, localParcel1, localParcel2, 0);
              localParcel2.readException();
              return;
              localIBinder1 = null;
              continue;
              localParcel1.writeInt(0);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          localParcel1.writeInt(0);
        }
      }

      public void a(b paramb, ab paramab, z paramz, String paramString1, String paramString2, bh parambh)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            if (paramb == null)
              continue;
            IBinder localIBinder1 = paramb.asBinder();
            localParcel1.writeStrongBinder(localIBinder1);
            if (paramab == null)
              continue;
            localParcel1.writeInt(1);
            paramab.writeToParcel(localParcel1, 0);
            if (paramz != null)
            {
              localParcel1.writeInt(1);
              paramz.writeToParcel(localParcel1, 0);
              localParcel1.writeString(paramString1);
              localParcel1.writeString(paramString2);
              IBinder localIBinder2 = null;
              if (parambh == null)
                continue;
              localIBinder2 = parambh.asBinder();
              localParcel1.writeStrongBinder(localIBinder2);
              this.ky.transact(6, localParcel1, localParcel2, 0);
              localParcel2.readException();
              return;
              localIBinder1 = null;
              continue;
              localParcel1.writeInt(0);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          localParcel1.writeInt(0);
        }
      }

      public void a(b paramb, z paramz, String paramString, bh parambh)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
          IBinder localIBinder1;
          if (paramb != null)
          {
            localIBinder1 = paramb.asBinder();
            localParcel1.writeStrongBinder(localIBinder1);
            if (paramz == null)
              break label121;
            localParcel1.writeInt(1);
            paramz.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            localParcel1.writeString(paramString);
            IBinder localIBinder2 = null;
            if (parambh != null)
              localIBinder2 = parambh.asBinder();
            localParcel1.writeStrongBinder(localIBinder2);
            this.ky.transact(3, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder1 = null;
            break;
            label121: localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void a(b paramb, z paramz, String paramString1, String paramString2, bh parambh)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
          IBinder localIBinder1;
          if (paramb != null)
          {
            localIBinder1 = paramb.asBinder();
            localParcel1.writeStrongBinder(localIBinder1);
            if (paramz == null)
              break label129;
            localParcel1.writeInt(1);
            paramz.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            IBinder localIBinder2 = null;
            if (parambh != null)
              localIBinder2 = parambh.asBinder();
            localParcel1.writeStrongBinder(localIBinder2);
            this.ky.transact(7, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder1 = null;
            break;
            label129: localParcel1.writeInt(0);
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

      public void destroy()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
          this.ky.transact(5, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public b getView()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
          this.ky.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          b localb = b.a.G(localParcel2.readStrongBinder());
          return localb;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void showInterstitial()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
          this.ky.transact(4, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.bg
 * JD-Core Version:    0.6.0
 */