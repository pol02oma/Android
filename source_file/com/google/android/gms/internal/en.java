package com.google.android.gms.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface en extends IInterface
{
  public abstract void a(em paramem, int paramInt)
    throws RemoteException;

  public abstract void a(em paramem, int paramInt, String paramString)
    throws RemoteException;

  public abstract void a(em paramem, int paramInt, String paramString, Bundle paramBundle)
    throws RemoteException;

  public abstract void a(em paramem, int paramInt, String paramString, IBinder paramIBinder, Bundle paramBundle)
    throws RemoteException;

  public abstract void a(em paramem, int paramInt, String paramString1, String paramString2, String[] paramArrayOfString)
    throws RemoteException;

  public abstract void a(em paramem, int paramInt, String paramString1, String paramString2, String[] paramArrayOfString, String paramString3, Bundle paramBundle)
    throws RemoteException;

  public abstract void a(em paramem, int paramInt, String paramString1, String paramString2, String[] paramArrayOfString, String paramString3, IBinder paramIBinder, String paramString4, Bundle paramBundle)
    throws RemoteException;

  public abstract void a(em paramem, int paramInt, String paramString1, String[] paramArrayOfString, String paramString2, Bundle paramBundle)
    throws RemoteException;

  public abstract void b(em paramem, int paramInt, String paramString)
    throws RemoteException;

  public abstract void b(em paramem, int paramInt, String paramString, Bundle paramBundle)
    throws RemoteException;

  public abstract void c(em paramem, int paramInt, String paramString)
    throws RemoteException;

  public abstract void c(em paramem, int paramInt, String paramString, Bundle paramBundle)
    throws RemoteException;

  public abstract void d(em paramem, int paramInt, String paramString)
    throws RemoteException;

  public abstract void d(em paramem, int paramInt, String paramString, Bundle paramBundle)
    throws RemoteException;

  public abstract void e(em paramem, int paramInt, String paramString, Bundle paramBundle)
    throws RemoteException;

  public abstract void f(em paramem, int paramInt, String paramString, Bundle paramBundle)
    throws RemoteException;

  public abstract void g(em paramem, int paramInt, String paramString, Bundle paramBundle)
    throws RemoteException;

  public abstract void h(em paramem, int paramInt, String paramString, Bundle paramBundle)
    throws RemoteException;

  public abstract void i(em paramem, int paramInt, String paramString, Bundle paramBundle)
    throws RemoteException;

  public abstract void j(em paramem, int paramInt, String paramString, Bundle paramBundle)
    throws RemoteException;

  public abstract void k(em paramem, int paramInt, String paramString, Bundle paramBundle)
    throws RemoteException;

  public abstract void l(em paramem, int paramInt, String paramString, Bundle paramBundle)
    throws RemoteException;

  public abstract void m(em paramem, int paramInt, String paramString, Bundle paramBundle)
    throws RemoteException;

  public abstract void n(em paramem, int paramInt, String paramString, Bundle paramBundle)
    throws RemoteException;

  public static abstract class a extends Binder
    implements en
  {
    public static en z(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
      if ((localIInterface != null) && ((localIInterface instanceof en)))
        return (en)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.common.internal.IGmsServiceBroker");
        return true;
      case 1:
        paramParcel1.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
        em localem18 = em.a.y(paramParcel1.readStrongBinder());
        int i27 = paramParcel1.readInt();
        String str22 = paramParcel1.readString();
        String str23 = paramParcel1.readString();
        String[] arrayOfString3 = paramParcel1.createStringArray();
        String str24 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0);
        for (Bundle localBundle18 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1); ; localBundle18 = null)
        {
          a(localem18, i27, str22, str23, arrayOfString3, str24, localBundle18);
          paramParcel2.writeNoException();
          return true;
        }
      case 2:
        paramParcel1.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
        em localem17 = em.a.y(paramParcel1.readStrongBinder());
        int i25 = paramParcel1.readInt();
        String str21 = paramParcel1.readString();
        int i26 = paramParcel1.readInt();
        Bundle localBundle17 = null;
        if (i26 != 0)
          localBundle17 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        a(localem17, i25, str21, localBundle17);
        paramParcel2.writeNoException();
        return true;
      case 3:
        paramParcel1.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
        a(em.a.y(paramParcel1.readStrongBinder()), paramParcel1.readInt(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 4:
        paramParcel1.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
        a(em.a.y(paramParcel1.readStrongBinder()), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 5:
        paramParcel1.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
        em localem16 = em.a.y(paramParcel1.readStrongBinder());
        int i23 = paramParcel1.readInt();
        String str20 = paramParcel1.readString();
        int i24 = paramParcel1.readInt();
        Bundle localBundle16 = null;
        if (i24 != 0)
          localBundle16 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        b(localem16, i23, str20, localBundle16);
        paramParcel2.writeNoException();
        return true;
      case 6:
        paramParcel1.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
        em localem15 = em.a.y(paramParcel1.readStrongBinder());
        int i21 = paramParcel1.readInt();
        String str19 = paramParcel1.readString();
        int i22 = paramParcel1.readInt();
        Bundle localBundle15 = null;
        if (i22 != 0)
          localBundle15 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        c(localem15, i21, str19, localBundle15);
        paramParcel2.writeNoException();
        return true;
      case 7:
        paramParcel1.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
        em localem14 = em.a.y(paramParcel1.readStrongBinder());
        int i19 = paramParcel1.readInt();
        String str18 = paramParcel1.readString();
        int i20 = paramParcel1.readInt();
        Bundle localBundle14 = null;
        if (i20 != 0)
          localBundle14 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        d(localem14, i19, str18, localBundle14);
        paramParcel2.writeNoException();
        return true;
      case 8:
        paramParcel1.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
        em localem13 = em.a.y(paramParcel1.readStrongBinder());
        int i17 = paramParcel1.readInt();
        String str17 = paramParcel1.readString();
        int i18 = paramParcel1.readInt();
        Bundle localBundle13 = null;
        if (i18 != 0)
          localBundle13 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        e(localem13, i17, str17, localBundle13);
        paramParcel2.writeNoException();
        return true;
      case 9:
        paramParcel1.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
        em localem12 = em.a.y(paramParcel1.readStrongBinder());
        int i16 = paramParcel1.readInt();
        String str13 = paramParcel1.readString();
        String str14 = paramParcel1.readString();
        String[] arrayOfString2 = paramParcel1.createStringArray();
        String str15 = paramParcel1.readString();
        IBinder localIBinder2 = paramParcel1.readStrongBinder();
        String str16 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0);
        for (Bundle localBundle12 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1); ; localBundle12 = null)
        {
          a(localem12, i16, str13, str14, arrayOfString2, str15, localIBinder2, str16, localBundle12);
          paramParcel2.writeNoException();
          return true;
        }
      case 10:
        paramParcel1.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
        a(em.a.y(paramParcel1.readStrongBinder()), paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.createStringArray());
        paramParcel2.writeNoException();
        return true;
      case 11:
        paramParcel1.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
        em localem11 = em.a.y(paramParcel1.readStrongBinder());
        int i14 = paramParcel1.readInt();
        String str12 = paramParcel1.readString();
        int i15 = paramParcel1.readInt();
        Bundle localBundle11 = null;
        if (i15 != 0)
          localBundle11 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        f(localem11, i14, str12, localBundle11);
        paramParcel2.writeNoException();
        return true;
      case 12:
        paramParcel1.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
        em localem10 = em.a.y(paramParcel1.readStrongBinder());
        int i12 = paramParcel1.readInt();
        String str11 = paramParcel1.readString();
        int i13 = paramParcel1.readInt();
        Bundle localBundle10 = null;
        if (i13 != 0)
          localBundle10 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        g(localem10, i12, str11, localBundle10);
        paramParcel2.writeNoException();
        return true;
      case 13:
        paramParcel1.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
        em localem9 = em.a.y(paramParcel1.readStrongBinder());
        int i10 = paramParcel1.readInt();
        String str10 = paramParcel1.readString();
        int i11 = paramParcel1.readInt();
        Bundle localBundle9 = null;
        if (i11 != 0)
          localBundle9 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        h(localem9, i10, str10, localBundle9);
        paramParcel2.writeNoException();
        return true;
      case 14:
        paramParcel1.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
        em localem8 = em.a.y(paramParcel1.readStrongBinder());
        int i8 = paramParcel1.readInt();
        String str9 = paramParcel1.readString();
        int i9 = paramParcel1.readInt();
        Bundle localBundle8 = null;
        if (i9 != 0)
          localBundle8 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        i(localem8, i8, str9, localBundle8);
        paramParcel2.writeNoException();
        return true;
      case 15:
        paramParcel1.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
        em localem7 = em.a.y(paramParcel1.readStrongBinder());
        int i6 = paramParcel1.readInt();
        String str8 = paramParcel1.readString();
        int i7 = paramParcel1.readInt();
        Bundle localBundle7 = null;
        if (i7 != 0)
          localBundle7 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        j(localem7, i6, str8, localBundle7);
        paramParcel2.writeNoException();
        return true;
      case 16:
        paramParcel1.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
        em localem6 = em.a.y(paramParcel1.readStrongBinder());
        int i4 = paramParcel1.readInt();
        String str7 = paramParcel1.readString();
        int i5 = paramParcel1.readInt();
        Bundle localBundle6 = null;
        if (i5 != 0)
          localBundle6 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        k(localem6, i4, str7, localBundle6);
        paramParcel2.writeNoException();
        return true;
      case 17:
        paramParcel1.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
        em localem5 = em.a.y(paramParcel1.readStrongBinder());
        int i2 = paramParcel1.readInt();
        String str6 = paramParcel1.readString();
        int i3 = paramParcel1.readInt();
        Bundle localBundle5 = null;
        if (i3 != 0)
          localBundle5 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        l(localem5, i2, str6, localBundle5);
        paramParcel2.writeNoException();
        return true;
      case 18:
        paramParcel1.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
        em localem4 = em.a.y(paramParcel1.readStrongBinder());
        int n = paramParcel1.readInt();
        String str5 = paramParcel1.readString();
        int i1 = paramParcel1.readInt();
        Bundle localBundle4 = null;
        if (i1 != 0)
          localBundle4 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        m(localem4, n, str5, localBundle4);
        paramParcel2.writeNoException();
        return true;
      case 19:
        paramParcel1.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
        em localem3 = em.a.y(paramParcel1.readStrongBinder());
        int m = paramParcel1.readInt();
        String str4 = paramParcel1.readString();
        IBinder localIBinder1 = paramParcel1.readStrongBinder();
        if (paramParcel1.readInt() != 0);
        for (Bundle localBundle3 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1); ; localBundle3 = null)
        {
          a(localem3, m, str4, localIBinder1, localBundle3);
          paramParcel2.writeNoException();
          return true;
        }
      case 20:
        paramParcel1.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
        em localem2 = em.a.y(paramParcel1.readStrongBinder());
        int k = paramParcel1.readInt();
        String str2 = paramParcel1.readString();
        String[] arrayOfString1 = paramParcel1.createStringArray();
        String str3 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0);
        for (Bundle localBundle2 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1); ; localBundle2 = null)
        {
          a(localem2, k, str2, arrayOfString1, str3, localBundle2);
          paramParcel2.writeNoException();
          return true;
        }
      case 21:
        paramParcel1.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
        b(em.a.y(paramParcel1.readStrongBinder()), paramParcel1.readInt(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 22:
        paramParcel1.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
        c(em.a.y(paramParcel1.readStrongBinder()), paramParcel1.readInt(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 23:
        paramParcel1.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
        em localem1 = em.a.y(paramParcel1.readStrongBinder());
        int i = paramParcel1.readInt();
        String str1 = paramParcel1.readString();
        int j = paramParcel1.readInt();
        Bundle localBundle1 = null;
        if (j != 0)
          localBundle1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        n(localem1, i, str1, localBundle1);
        paramParcel2.writeNoException();
        return true;
      case 24:
      }
      paramParcel1.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
      d(em.a.y(paramParcel1.readStrongBinder()), paramParcel1.readInt(), paramParcel1.readString());
      paramParcel2.writeNoException();
      return true;
    }

    private static class a
      implements en
    {
      private IBinder ky;

      a(IBinder paramIBinder)
      {
        this.ky = paramIBinder;
      }

      public void a(em paramem, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
          if (paramem != null);
          for (IBinder localIBinder = paramem.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            this.ky.transact(4, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void a(em paramem, int paramInt, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
          if (paramem != null);
          for (IBinder localIBinder = paramem.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            localParcel1.writeString(paramString);
            this.ky.transact(3, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void a(em paramem, int paramInt, String paramString, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
          IBinder localIBinder;
          if (paramem != null)
          {
            localIBinder = paramem.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            localParcel1.writeString(paramString);
            if (paramBundle == null)
              break label105;
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(2, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label105: localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void a(em paramem, int paramInt, String paramString, IBinder paramIBinder, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
          IBinder localIBinder;
          if (paramem != null)
          {
            localIBinder = paramem.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            localParcel1.writeString(paramString);
            localParcel1.writeStrongBinder(paramIBinder);
            if (paramBundle == null)
              break label113;
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(19, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label113: localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void a(em paramem, int paramInt, String paramString1, String paramString2, String[] paramArrayOfString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
          if (paramem != null);
          for (IBinder localIBinder = paramem.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            localParcel1.writeStringArray(paramArrayOfString);
            this.ky.transact(10, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void a(em paramem, int paramInt, String paramString1, String paramString2, String[] paramArrayOfString, String paramString3, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
          IBinder localIBinder;
          if (paramem != null)
          {
            localIBinder = paramem.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            localParcel1.writeStringArray(paramArrayOfString);
            localParcel1.writeString(paramString3);
            if (paramBundle == null)
              break label126;
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(1, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label126: localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void a(em paramem, int paramInt, String paramString1, String paramString2, String[] paramArrayOfString, String paramString3, IBinder paramIBinder, String paramString4, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
          IBinder localIBinder;
          if (paramem != null)
          {
            localIBinder = paramem.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            localParcel1.writeStringArray(paramArrayOfString);
            localParcel1.writeString(paramString3);
            localParcel1.writeStrongBinder(paramIBinder);
            localParcel1.writeString(paramString4);
            if (paramBundle == null)
              break label141;
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(9, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label141: localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void a(em paramem, int paramInt, String paramString1, String[] paramArrayOfString, String paramString2, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
          IBinder localIBinder;
          if (paramem != null)
          {
            localIBinder = paramem.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            localParcel1.writeString(paramString1);
            localParcel1.writeStringArray(paramArrayOfString);
            localParcel1.writeString(paramString2);
            if (paramBundle == null)
              break label120;
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(20, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label120: localParcel1.writeInt(0);
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

      public void b(em paramem, int paramInt, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
          if (paramem != null);
          for (IBinder localIBinder = paramem.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            localParcel1.writeString(paramString);
            this.ky.transact(21, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void b(em paramem, int paramInt, String paramString, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
          IBinder localIBinder;
          if (paramem != null)
          {
            localIBinder = paramem.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            localParcel1.writeString(paramString);
            if (paramBundle == null)
              break label105;
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(5, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label105: localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void c(em paramem, int paramInt, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
          if (paramem != null);
          for (IBinder localIBinder = paramem.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            localParcel1.writeString(paramString);
            this.ky.transact(22, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void c(em paramem, int paramInt, String paramString, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
          IBinder localIBinder;
          if (paramem != null)
          {
            localIBinder = paramem.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            localParcel1.writeString(paramString);
            if (paramBundle == null)
              break label106;
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(6, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label106: localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void d(em paramem, int paramInt, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
          if (paramem != null);
          for (IBinder localIBinder = paramem.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            localParcel1.writeString(paramString);
            this.ky.transact(24, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void d(em paramem, int paramInt, String paramString, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
          IBinder localIBinder;
          if (paramem != null)
          {
            localIBinder = paramem.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            localParcel1.writeString(paramString);
            if (paramBundle == null)
              break label106;
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(7, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label106: localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void e(em paramem, int paramInt, String paramString, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
          IBinder localIBinder;
          if (paramem != null)
          {
            localIBinder = paramem.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            localParcel1.writeString(paramString);
            if (paramBundle == null)
              break label106;
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(8, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label106: localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void f(em paramem, int paramInt, String paramString, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
          IBinder localIBinder;
          if (paramem != null)
          {
            localIBinder = paramem.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            localParcel1.writeString(paramString);
            if (paramBundle == null)
              break label106;
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(11, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label106: localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void g(em paramem, int paramInt, String paramString, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
          IBinder localIBinder;
          if (paramem != null)
          {
            localIBinder = paramem.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            localParcel1.writeString(paramString);
            if (paramBundle == null)
              break label106;
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(12, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label106: localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void h(em paramem, int paramInt, String paramString, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
          IBinder localIBinder;
          if (paramem != null)
          {
            localIBinder = paramem.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            localParcel1.writeString(paramString);
            if (paramBundle == null)
              break label106;
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(13, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label106: localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void i(em paramem, int paramInt, String paramString, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
          IBinder localIBinder;
          if (paramem != null)
          {
            localIBinder = paramem.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            localParcel1.writeString(paramString);
            if (paramBundle == null)
              break label106;
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(14, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label106: localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void j(em paramem, int paramInt, String paramString, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
          IBinder localIBinder;
          if (paramem != null)
          {
            localIBinder = paramem.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            localParcel1.writeString(paramString);
            if (paramBundle == null)
              break label106;
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(15, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label106: localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void k(em paramem, int paramInt, String paramString, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
          IBinder localIBinder;
          if (paramem != null)
          {
            localIBinder = paramem.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            localParcel1.writeString(paramString);
            if (paramBundle == null)
              break label106;
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(16, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label106: localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void l(em paramem, int paramInt, String paramString, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
          IBinder localIBinder;
          if (paramem != null)
          {
            localIBinder = paramem.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            localParcel1.writeString(paramString);
            if (paramBundle == null)
              break label106;
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(17, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label106: localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void m(em paramem, int paramInt, String paramString, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
          IBinder localIBinder;
          if (paramem != null)
          {
            localIBinder = paramem.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            localParcel1.writeString(paramString);
            if (paramBundle == null)
              break label106;
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(18, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label106: localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void n(em paramem, int paramInt, String paramString, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
          IBinder localIBinder;
          if (paramem != null)
          {
            localIBinder = paramem.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            localParcel1.writeString(paramString);
            if (paramBundle == null)
              break label106;
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(23, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label106: localParcel1.writeInt(0);
          }
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
 * Qualified Name:     com.google.android.gms.internal.en
 * JD-Core Version:    0.6.0
 */