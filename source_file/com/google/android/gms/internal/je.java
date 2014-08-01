package com.google.android.gms.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.NotifyTransactionStatusRequest;
import com.google.android.gms.wallet.d;

public abstract interface je extends IInterface
{
  public abstract void a(Bundle paramBundle, jf paramjf)
    throws RemoteException;

  public abstract void a(FullWalletRequest paramFullWalletRequest, Bundle paramBundle, jf paramjf)
    throws RemoteException;

  public abstract void a(MaskedWalletRequest paramMaskedWalletRequest, Bundle paramBundle, jf paramjf)
    throws RemoteException;

  public abstract void a(NotifyTransactionStatusRequest paramNotifyTransactionStatusRequest, Bundle paramBundle)
    throws RemoteException;

  public abstract void a(d paramd, Bundle paramBundle, jf paramjf)
    throws RemoteException;

  public abstract void a(String paramString1, String paramString2, Bundle paramBundle, jf paramjf)
    throws RemoteException;

  public static abstract class a extends Binder
    implements je
  {
    public static je aC(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.wallet.internal.IOwService");
      if ((localIInterface != null) && ((localIInterface instanceof je)))
        return (je)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.wallet.internal.IOwService");
        return true;
      case 1:
        paramParcel1.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
        MaskedWalletRequest localMaskedWalletRequest;
        if (paramParcel1.readInt() != 0)
        {
          localMaskedWalletRequest = (MaskedWalletRequest)MaskedWalletRequest.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0)
            break label157;
        }
        for (Bundle localBundle6 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1); ; localBundle6 = null)
        {
          a(localMaskedWalletRequest, localBundle6, jf.a.aD(paramParcel1.readStrongBinder()));
          return true;
          localMaskedWalletRequest = null;
          break;
        }
      case 2:
        paramParcel1.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
        FullWalletRequest localFullWalletRequest;
        if (paramParcel1.readInt() != 0)
        {
          localFullWalletRequest = (FullWalletRequest)FullWalletRequest.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0)
            break label234;
        }
        for (Bundle localBundle5 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1); ; localBundle5 = null)
        {
          a(localFullWalletRequest, localBundle5, jf.a.aD(paramParcel1.readStrongBinder()));
          return true;
          localFullWalletRequest = null;
          break;
        }
      case 3:
        paramParcel1.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
        String str1 = paramParcel1.readString();
        String str2 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0);
        for (Bundle localBundle4 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1); ; localBundle4 = null)
        {
          a(str1, str2, localBundle4, jf.a.aD(paramParcel1.readStrongBinder()));
          return true;
        }
      case 4:
        paramParcel1.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
        NotifyTransactionStatusRequest localNotifyTransactionStatusRequest;
        if (paramParcel1.readInt() != 0)
        {
          localNotifyTransactionStatusRequest = (NotifyTransactionStatusRequest)NotifyTransactionStatusRequest.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0)
            break label368;
        }
        for (Bundle localBundle3 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1); ; localBundle3 = null)
        {
          a(localNotifyTransactionStatusRequest, localBundle3);
          return true;
          localNotifyTransactionStatusRequest = null;
          break;
        }
      case 5:
        label157: label234: label368: paramParcel1.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
        if (paramParcel1.readInt() != 0);
        for (Bundle localBundle2 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1); ; localBundle2 = null)
        {
          a(localBundle2, jf.a.aD(paramParcel1.readStrongBinder()));
          return true;
        }
      case 6:
      }
      paramParcel1.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
      d locald;
      if (paramParcel1.readInt() != 0)
      {
        locald = (d)d.CREATOR.createFromParcel(paramParcel1);
        if (paramParcel1.readInt() == 0)
          break label493;
      }
      label493: for (Bundle localBundle1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1); ; localBundle1 = null)
      {
        a(locald, localBundle1, jf.a.aD(paramParcel1.readStrongBinder()));
        return true;
        locald = null;
        break;
      }
    }

    private static class a
      implements je
    {
      private IBinder ky;

      a(IBinder paramIBinder)
      {
        this.ky = paramIBinder;
      }

      public void a(Bundle paramBundle, jf paramjf)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
          if (paramBundle != null)
          {
            localParcel.writeInt(1);
            paramBundle.writeToParcel(localParcel, 0);
          }
          while (true)
          {
            IBinder localIBinder = null;
            if (paramjf != null)
              localIBinder = paramjf.asBinder();
            localParcel.writeStrongBinder(localIBinder);
            this.ky.transact(5, localParcel, null, 1);
            return;
            localParcel.writeInt(0);
          }
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void a(FullWalletRequest paramFullWalletRequest, Bundle paramBundle, jf paramjf)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
            if (paramFullWalletRequest == null)
              continue;
            localParcel.writeInt(1);
            paramFullWalletRequest.writeToParcel(localParcel, 0);
            if (paramBundle != null)
            {
              localParcel.writeInt(1);
              paramBundle.writeToParcel(localParcel, 0);
              IBinder localIBinder = null;
              if (paramjf == null)
                continue;
              localIBinder = paramjf.asBinder();
              localParcel.writeStrongBinder(localIBinder);
              this.ky.transact(2, localParcel, null, 1);
              return;
              localParcel.writeInt(0);
              continue;
            }
          }
          finally
          {
            localParcel.recycle();
          }
          localParcel.writeInt(0);
        }
      }

      public void a(MaskedWalletRequest paramMaskedWalletRequest, Bundle paramBundle, jf paramjf)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
            if (paramMaskedWalletRequest == null)
              continue;
            localParcel.writeInt(1);
            paramMaskedWalletRequest.writeToParcel(localParcel, 0);
            if (paramBundle != null)
            {
              localParcel.writeInt(1);
              paramBundle.writeToParcel(localParcel, 0);
              IBinder localIBinder = null;
              if (paramjf == null)
                continue;
              localIBinder = paramjf.asBinder();
              localParcel.writeStrongBinder(localIBinder);
              this.ky.transact(1, localParcel, null, 1);
              return;
              localParcel.writeInt(0);
              continue;
            }
          }
          finally
          {
            localParcel.recycle();
          }
          localParcel.writeInt(0);
        }
      }

      public void a(NotifyTransactionStatusRequest paramNotifyTransactionStatusRequest, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
            if (paramNotifyTransactionStatusRequest == null)
              continue;
            localParcel.writeInt(1);
            paramNotifyTransactionStatusRequest.writeToParcel(localParcel, 0);
            if (paramBundle != null)
            {
              localParcel.writeInt(1);
              paramBundle.writeToParcel(localParcel, 0);
              this.ky.transact(4, localParcel, null, 1);
              return;
              localParcel.writeInt(0);
              continue;
            }
          }
          finally
          {
            localParcel.recycle();
          }
          localParcel.writeInt(0);
        }
      }

      public void a(d paramd, Bundle paramBundle, jf paramjf)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
            if (paramd == null)
              continue;
            localParcel.writeInt(1);
            paramd.writeToParcel(localParcel, 0);
            if (paramBundle != null)
            {
              localParcel.writeInt(1);
              paramBundle.writeToParcel(localParcel, 0);
              IBinder localIBinder = null;
              if (paramjf == null)
                continue;
              localIBinder = paramjf.asBinder();
              localParcel.writeStrongBinder(localIBinder);
              this.ky.transact(6, localParcel, null, 1);
              return;
              localParcel.writeInt(0);
              continue;
            }
          }
          finally
          {
            localParcel.recycle();
          }
          localParcel.writeInt(0);
        }
      }

      public void a(String paramString1, String paramString2, Bundle paramBundle, jf paramjf)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
          localParcel.writeString(paramString1);
          localParcel.writeString(paramString2);
          if (paramBundle != null)
          {
            localParcel.writeInt(1);
            paramBundle.writeToParcel(localParcel, 0);
          }
          while (true)
          {
            IBinder localIBinder = null;
            if (paramjf != null)
              localIBinder = paramjf.asBinder();
            localParcel.writeStrongBinder(localIBinder);
            this.ky.transact(3, localParcel, null, 1);
            return;
            localParcel.writeInt(0);
          }
        }
        finally
        {
          localParcel.recycle();
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
 * Qualified Name:     com.google.android.gms.internal.je
 * JD-Core Version:    0.6.0
 */