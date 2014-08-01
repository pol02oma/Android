package com.google.android.gms.internal;

import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataHolderCreator;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;

public abstract interface gb extends IInterface
{
  public abstract int a(ga paramga, byte[] paramArrayOfByte, String paramString1, String paramString2)
    throws RemoteException;

  public abstract Intent a(int paramInt1, int paramInt2, boolean paramBoolean)
    throws RemoteException;

  public abstract Intent a(int paramInt1, byte[] paramArrayOfByte, int paramInt2, String paramString)
    throws RemoteException;

  public abstract Intent a(RoomEntity paramRoomEntity, int paramInt)
    throws RemoteException;

  public abstract Intent a(gy paramgy, String paramString1, String paramString2)
    throws RemoteException;

  public abstract Intent a(gz paramgz, String paramString)
    throws RemoteException;

  public abstract Intent a(ParticipantEntity[] paramArrayOfParticipantEntity, String paramString1, String paramString2, Uri paramUri1, Uri paramUri2)
    throws RemoteException;

  public abstract void a(long paramLong, String paramString)
    throws RemoteException;

  public abstract void a(IBinder paramIBinder, Bundle paramBundle)
    throws RemoteException;

  public abstract void a(ga paramga)
    throws RemoteException;

  public abstract void a(ga paramga, int paramInt)
    throws RemoteException;

  public abstract void a(ga paramga, int paramInt1, int paramInt2, int paramInt3)
    throws RemoteException;

  public abstract void a(ga paramga, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;

  public abstract void a(ga paramga, int paramInt1, int paramInt2, String[] paramArrayOfString, Bundle paramBundle)
    throws RemoteException;

  public abstract void a(ga paramga, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;

  public abstract void a(ga paramga, int paramInt, int[] paramArrayOfInt)
    throws RemoteException;

  public abstract void a(ga paramga, long paramLong)
    throws RemoteException;

  public abstract void a(ga paramga, long paramLong, String paramString)
    throws RemoteException;

  public abstract void a(ga paramga, Bundle paramBundle, int paramInt1, int paramInt2)
    throws RemoteException;

  public abstract void a(ga paramga, IBinder paramIBinder, int paramInt, String[] paramArrayOfString, Bundle paramBundle, boolean paramBoolean, long paramLong)
    throws RemoteException;

  public abstract void a(ga paramga, IBinder paramIBinder, String paramString, boolean paramBoolean, long paramLong)
    throws RemoteException;

  public abstract void a(ga paramga, String paramString)
    throws RemoteException;

  public abstract void a(ga paramga, String paramString, int paramInt)
    throws RemoteException;

  public abstract void a(ga paramga, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    throws RemoteException;

  public abstract void a(ga paramga, String paramString, int paramInt, IBinder paramIBinder, Bundle paramBundle)
    throws RemoteException;

  public abstract void a(ga paramga, String paramString, int paramInt, boolean paramBoolean)
    throws RemoteException;

  public abstract void a(ga paramga, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;

  public abstract void a(ga paramga, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
    throws RemoteException;

  public abstract void a(ga paramga, String paramString, int paramInt, int[] paramArrayOfInt)
    throws RemoteException;

  public abstract void a(ga paramga, String paramString, long paramLong)
    throws RemoteException;

  public abstract void a(ga paramga, String paramString1, long paramLong, String paramString2)
    throws RemoteException;

  public abstract void a(ga paramga, String paramString, IBinder paramIBinder, Bundle paramBundle)
    throws RemoteException;

  public abstract void a(ga paramga, String paramString1, String paramString2)
    throws RemoteException;

  public abstract void a(ga paramga, String paramString1, String paramString2, int paramInt1, int paramInt2)
    throws RemoteException;

  public abstract void a(ga paramga, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3)
    throws RemoteException;

  public abstract void a(ga paramga, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    throws RemoteException;

  public abstract void a(ga paramga, String paramString1, String paramString2, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;

  public abstract void a(ga paramga, String paramString1, String paramString2, boolean paramBoolean)
    throws RemoteException;

  public abstract void a(ga paramga, String paramString1, String paramString2, String[] paramArrayOfString)
    throws RemoteException;

  public abstract void a(ga paramga, String paramString, boolean paramBoolean)
    throws RemoteException;

  public abstract void a(ga paramga, String paramString1, byte[] paramArrayOfByte, String paramString2, ParticipantResult[] paramArrayOfParticipantResult)
    throws RemoteException;

  public abstract void a(ga paramga, String paramString, byte[] paramArrayOfByte, ParticipantResult[] paramArrayOfParticipantResult)
    throws RemoteException;

  public abstract void a(ga paramga, String paramString, int[] paramArrayOfInt)
    throws RemoteException;

  public abstract void a(ga paramga, String paramString, String[] paramArrayOfString, int paramInt1, byte[] paramArrayOfByte, int paramInt2)
    throws RemoteException;

  public abstract void a(ga paramga, boolean paramBoolean)
    throws RemoteException;

  public abstract void a(ga paramga, boolean paramBoolean, Bundle paramBundle)
    throws RemoteException;

  public abstract void a(ga paramga, int[] paramArrayOfInt)
    throws RemoteException;

  public abstract void a(ga paramga, String[] paramArrayOfString)
    throws RemoteException;

  public abstract int aA(String paramString)
    throws RemoteException;

  public abstract Uri aB(String paramString)
    throws RemoteException;

  public abstract void aC(String paramString)
    throws RemoteException;

  public abstract ParcelFileDescriptor aD(String paramString)
    throws RemoteException;

  public abstract void aU(int paramInt)
    throws RemoteException;

  public abstract Intent au(String paramString)
    throws RemoteException;

  public abstract String ax(String paramString)
    throws RemoteException;

  public abstract String ay(String paramString)
    throws RemoteException;

  public abstract void az(String paramString)
    throws RemoteException;

  public abstract int b(byte[] paramArrayOfByte, String paramString, String[] paramArrayOfString)
    throws RemoteException;

  public abstract Intent b(int paramInt1, int paramInt2, boolean paramBoolean)
    throws RemoteException;

  public abstract void b(long paramLong, String paramString)
    throws RemoteException;

  public abstract void b(ga paramga)
    throws RemoteException;

  public abstract void b(ga paramga, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;

  public abstract void b(ga paramga, long paramLong)
    throws RemoteException;

  public abstract void b(ga paramga, long paramLong, String paramString)
    throws RemoteException;

  public abstract void b(ga paramga, String paramString)
    throws RemoteException;

  public abstract void b(ga paramga, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    throws RemoteException;

  public abstract void b(ga paramga, String paramString, int paramInt, IBinder paramIBinder, Bundle paramBundle)
    throws RemoteException;

  public abstract void b(ga paramga, String paramString, int paramInt, boolean paramBoolean)
    throws RemoteException;

  public abstract void b(ga paramga, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;

  public abstract void b(ga paramga, String paramString, IBinder paramIBinder, Bundle paramBundle)
    throws RemoteException;

  public abstract void b(ga paramga, String paramString1, String paramString2)
    throws RemoteException;

  public abstract void b(ga paramga, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    throws RemoteException;

  public abstract void b(ga paramga, String paramString1, String paramString2, boolean paramBoolean)
    throws RemoteException;

  public abstract void b(ga paramga, String paramString, boolean paramBoolean)
    throws RemoteException;

  public abstract void b(ga paramga, boolean paramBoolean)
    throws RemoteException;

  public abstract void b(ga paramga, String[] paramArrayOfString)
    throws RemoteException;

  public abstract void b(String paramString1, String paramString2, int paramInt)
    throws RemoteException;

  public abstract void c(long paramLong, String paramString)
    throws RemoteException;

  public abstract void c(ga paramga)
    throws RemoteException;

  public abstract void c(ga paramga, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;

  public abstract void c(ga paramga, long paramLong)
    throws RemoteException;

  public abstract void c(ga paramga, long paramLong, String paramString)
    throws RemoteException;

  public abstract void c(ga paramga, String paramString)
    throws RemoteException;

  public abstract void c(ga paramga, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;

  public abstract void c(ga paramga, String paramString1, String paramString2)
    throws RemoteException;

  public abstract void c(ga paramga, String paramString, boolean paramBoolean)
    throws RemoteException;

  public abstract void c(ga paramga, boolean paramBoolean)
    throws RemoteException;

  public abstract void c(ga paramga, String[] paramArrayOfString)
    throws RemoteException;

  public abstract void c(String paramString1, String paramString2, int paramInt)
    throws RemoteException;

  public abstract Bundle cY()
    throws RemoteException;

  public abstract void d(ga paramga)
    throws RemoteException;

  public abstract void d(ga paramga, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;

  public abstract void d(ga paramga, String paramString)
    throws RemoteException;

  public abstract void d(ga paramga, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;

  public abstract void d(ga paramga, String paramString1, String paramString2)
    throws RemoteException;

  public abstract void d(ga paramga, String paramString, boolean paramBoolean)
    throws RemoteException;

  public abstract ParcelFileDescriptor e(Uri paramUri)
    throws RemoteException;

  public abstract void e(ga paramga)
    throws RemoteException;

  public abstract void e(ga paramga, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;

  public abstract void e(ga paramga, String paramString)
    throws RemoteException;

  public abstract void e(ga paramga, String paramString1, String paramString2)
    throws RemoteException;

  public abstract void f(ga paramga)
    throws RemoteException;

  public abstract void f(ga paramga, String paramString)
    throws RemoteException;

  public abstract int fA()
    throws RemoteException;

  public abstract String fB()
    throws RemoteException;

  public abstract int fC()
    throws RemoteException;

  public abstract Intent fD()
    throws RemoteException;

  public abstract int fE()
    throws RemoteException;

  public abstract int fF()
    throws RemoteException;

  public abstract void fH()
    throws RemoteException;

  public abstract DataHolder fI()
    throws RemoteException;

  public abstract boolean fJ()
    throws RemoteException;

  public abstract DataHolder fK()
    throws RemoteException;

  public abstract void fL()
    throws RemoteException;

  public abstract Intent fM()
    throws RemoteException;

  public abstract String fn()
    throws RemoteException;

  public abstract String fo()
    throws RemoteException;

  public abstract Intent fr()
    throws RemoteException;

  public abstract Intent fs()
    throws RemoteException;

  public abstract Intent ft()
    throws RemoteException;

  public abstract Intent fu()
    throws RemoteException;

  public abstract Intent fy()
    throws RemoteException;

  public abstract Intent fz()
    throws RemoteException;

  public abstract void g(ga paramga)
    throws RemoteException;

  public abstract void g(ga paramga, String paramString)
    throws RemoteException;

  public abstract DataHolder h(ga paramga, String paramString)
    throws RemoteException;

  public abstract void h(ga paramga)
    throws RemoteException;

  public abstract void i(ga paramga)
    throws RemoteException;

  public abstract void i(ga paramga, String paramString)
    throws RemoteException;

  public abstract void j(ga paramga, String paramString)
    throws RemoteException;

  public abstract void j(String paramString1, String paramString2)
    throws RemoteException;

  public abstract void k(ga paramga, String paramString)
    throws RemoteException;

  public abstract void k(String paramString1, String paramString2)
    throws RemoteException;

  public abstract void l(ga paramga, String paramString)
    throws RemoteException;

  public abstract void l(String paramString, int paramInt)
    throws RemoteException;

  public abstract void m(ga paramga, String paramString)
    throws RemoteException;

  public abstract void m(String paramString, int paramInt)
    throws RemoteException;

  public abstract void n(long paramLong)
    throws RemoteException;

  public abstract void n(ga paramga, String paramString)
    throws RemoteException;

  public abstract void n(String paramString, int paramInt)
    throws RemoteException;

  public abstract void o(long paramLong)
    throws RemoteException;

  public abstract void o(ga paramga, String paramString)
    throws RemoteException;

  public abstract void o(String paramString, int paramInt)
    throws RemoteException;

  public abstract void p(long paramLong)
    throws RemoteException;

  public abstract void p(ga paramga, String paramString)
    throws RemoteException;

  public abstract void q(long paramLong)
    throws RemoteException;

  public abstract void q(ga paramga, String paramString)
    throws RemoteException;

  public abstract void y(boolean paramBoolean)
    throws RemoteException;

  public static abstract class a extends Binder
    implements gb
  {
    public static gb J(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.games.internal.IGamesService");
      if ((localIInterface != null) && ((localIInterface instanceof gb)))
        return (gb)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.games.internal.IGamesService");
        return true;
      case 5001:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        n(paramParcel1.readLong());
        paramParcel2.writeNoException();
        return true;
      case 5002:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(ga.a.I(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 5003:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        String str35 = fB();
        paramParcel2.writeNoException();
        paramParcel2.writeString(str35);
        return true;
      case 5004:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        Bundle localBundle10 = cY();
        paramParcel2.writeNoException();
        if (localBundle10 != null)
        {
          paramParcel2.writeInt(1);
          localBundle10.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 5005:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        IBinder localIBinder7 = paramParcel1.readStrongBinder();
        if (paramParcel1.readInt() != 0);
        for (Bundle localBundle9 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1); ; localBundle9 = null)
        {
          a(localIBinder7, localBundle9);
          paramParcel2.writeNoException();
          return true;
        }
      case 5006:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        fH();
        paramParcel2.writeNoException();
        return true;
      case 5007:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        String str34 = fn();
        paramParcel2.writeNoException();
        paramParcel2.writeString(str34);
        return true;
      case 5064:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        String str33 = ax(paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeString(str33);
        return true;
      case 5065:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        j(paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5012:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        String str32 = fo();
        paramParcel2.writeNoException();
        paramParcel2.writeString(str32);
        return true;
      case 5013:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        DataHolder localDataHolder3 = fI();
        paramParcel2.writeNoException();
        if (localDataHolder3 != null)
        {
          paramParcel2.writeInt(1);
          localDataHolder3.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 5014:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5015:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga36 = ga.a.I(paramParcel1.readStrongBinder());
        int i66 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0);
        for (boolean bool47 = true; ; bool47 = false)
        {
          int i67 = paramParcel1.readInt();
          boolean bool48 = false;
          if (i67 != 0)
            bool48 = true;
          a(localga36, i66, bool47, bool48);
          paramParcel2.writeNoException();
          return true;
        }
      case 5016:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readLong());
        paramParcel2.writeNoException();
        return true;
      case 5017:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        b(ga.a.I(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 5018:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        b(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5019:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga35 = ga.a.I(paramParcel1.readStrongBinder());
        String str31 = paramParcel1.readString();
        int i63 = paramParcel1.readInt();
        int i64 = paramParcel1.readInt();
        int i65 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0);
        for (boolean bool46 = true; ; bool46 = false)
        {
          a(localga35, str31, i63, i64, i65, bool46);
          paramParcel2.writeNoException();
          return true;
        }
      case 5020:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga34 = ga.a.I(paramParcel1.readStrongBinder());
        String str30 = paramParcel1.readString();
        int i60 = paramParcel1.readInt();
        int i61 = paramParcel1.readInt();
        int i62 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0);
        for (boolean bool45 = true; ; bool45 = false)
        {
          b(localga34, str30, i60, i61, i62, bool45);
          paramParcel2.writeNoException();
          return true;
        }
      case 5021:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga33 = ga.a.I(paramParcel1.readStrongBinder());
        if (paramParcel1.readInt() != 0);
        for (Bundle localBundle8 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1); ; localBundle8 = null)
        {
          a(localga33, localBundle8, paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        }
      case 5022:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        c(ga.a.I(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 5023:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga32 = ga.a.I(paramParcel1.readStrongBinder());
        String str29 = paramParcel1.readString();
        IBinder localIBinder6 = paramParcel1.readStrongBinder();
        if (paramParcel1.readInt() != 0);
        for (Bundle localBundle7 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1); ; localBundle7 = null)
        {
          a(localga32, str29, localIBinder6, localBundle7);
          paramParcel2.writeNoException();
          return true;
        }
      case 5024:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga31 = ga.a.I(paramParcel1.readStrongBinder());
        String str28 = paramParcel1.readString();
        IBinder localIBinder5 = paramParcel1.readStrongBinder();
        if (paramParcel1.readInt() != 0);
        for (Bundle localBundle6 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1); ; localBundle6 = null)
        {
          b(localga31, str28, localIBinder5, localBundle6);
          paramParcel2.writeNoException();
          return true;
        }
      case 5025:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga30 = ga.a.I(paramParcel1.readStrongBinder());
        String str27 = paramParcel1.readString();
        int i58 = paramParcel1.readInt();
        IBinder localIBinder4 = paramParcel1.readStrongBinder();
        int i59 = paramParcel1.readInt();
        Bundle localBundle5 = null;
        if (i59 != 0)
          localBundle5 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        a(localga30, str27, i58, localIBinder4, localBundle5);
        paramParcel2.writeNoException();
        return true;
      case 5026:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        d(ga.a.I(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 5027:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        e(ga.a.I(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 5028:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        m(paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 5029:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        l(paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 5058:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readLong());
        paramParcel2.writeNoException();
        return true;
      case 5059:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        o(paramParcel1.readLong());
        paramParcel2.writeNoException();
        return true;
      case 5030:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga29 = ga.a.I(paramParcel1.readStrongBinder());
        IBinder localIBinder3 = paramParcel1.readStrongBinder();
        int i56 = paramParcel1.readInt();
        String[] arrayOfString2 = paramParcel1.createStringArray();
        int i57 = paramParcel1.readInt();
        Bundle localBundle4 = null;
        if (i57 != 0)
          localBundle4 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        if (paramParcel1.readInt() != 0);
        for (boolean bool44 = true; ; bool44 = false)
        {
          a(localga29, localIBinder3, i56, arrayOfString2, localBundle4, bool44, paramParcel1.readLong());
          paramParcel2.writeNoException();
          return true;
        }
      case 5031:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga28 = ga.a.I(paramParcel1.readStrongBinder());
        IBinder localIBinder2 = paramParcel1.readStrongBinder();
        String str26 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0);
        for (boolean bool43 = true; ; bool43 = false)
        {
          a(localga28, localIBinder2, str26, bool43, paramParcel1.readLong());
          paramParcel2.writeNoException();
          return true;
        }
      case 5032:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        c(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5033:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        int i55 = a(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.createByteArray(), paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i55);
        return true;
      case 5034:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        int i54 = b(paramParcel1.createByteArray(), paramParcel1.readString(), paramParcel1.createStringArray());
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i54);
        return true;
      case 5035:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        String str25 = ay(paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeString(str25);
        return true;
      case 5036:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        aU(paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 5037:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        d(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5038:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5039:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga27 = ga.a.I(paramParcel1.readStrongBinder());
        String str23 = paramParcel1.readString();
        String str24 = paramParcel1.readString();
        int i50 = paramParcel1.readInt();
        int i51 = paramParcel1.readInt();
        int i52 = paramParcel1.readInt();
        int i53 = paramParcel1.readInt();
        boolean bool42 = false;
        if (i53 != 0)
          bool42 = true;
        a(localga27, str23, str24, i50, i51, i52, bool42);
        paramParcel2.writeNoException();
        return true;
      case 5040:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga26 = ga.a.I(paramParcel1.readStrongBinder());
        String str21 = paramParcel1.readString();
        String str22 = paramParcel1.readString();
        int i46 = paramParcel1.readInt();
        int i47 = paramParcel1.readInt();
        int i48 = paramParcel1.readInt();
        int i49 = paramParcel1.readInt();
        boolean bool41 = false;
        if (i49 != 0)
          bool41 = true;
        b(localga26, str21, str22, i46, i47, i48, bool41);
        paramParcel2.writeNoException();
        return true;
      case 5041:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        b(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5042:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        e(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5043:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        f(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5044:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga25 = ga.a.I(paramParcel1.readStrongBinder());
        int i44 = paramParcel1.readInt();
        int i45 = paramParcel1.readInt();
        boolean bool39;
        if (paramParcel1.readInt() != 0)
        {
          bool39 = true;
          if (paramParcel1.readInt() == 0)
            break label3039;
        }
        for (boolean bool40 = true; ; bool40 = false)
        {
          a(localga25, i44, i45, bool39, bool40);
          paramParcel2.writeNoException();
          return true;
          bool39 = false;
          break;
        }
      case 5045:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga24 = ga.a.I(paramParcel1.readStrongBinder());
        String str20 = paramParcel1.readString();
        int i43 = paramParcel1.readInt();
        boolean bool37;
        if (paramParcel1.readInt() != 0)
        {
          bool37 = true;
          if (paramParcel1.readInt() == 0)
            break label3118;
        }
        for (boolean bool38 = true; ; bool38 = false)
        {
          a(localga24, str20, i43, bool37, bool38);
          paramParcel2.writeNoException();
          return true;
          bool37 = false;
          break;
        }
      case 5046:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga23 = ga.a.I(paramParcel1.readStrongBinder());
        int i41 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0);
        for (boolean bool35 = true; ; bool35 = false)
        {
          int i42 = paramParcel1.readInt();
          boolean bool36 = false;
          if (i42 != 0)
            bool36 = true;
          b(localga23, i41, bool35, bool36);
          paramParcel2.writeNoException();
          return true;
        }
      case 5047:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        f(ga.a.I(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 5048:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga22 = ga.a.I(paramParcel1.readStrongBinder());
        int i39 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0);
        for (boolean bool33 = true; ; bool33 = false)
        {
          int i40 = paramParcel1.readInt();
          boolean bool34 = false;
          if (i40 != 0)
            bool34 = true;
          c(localga22, i39, bool33, bool34);
          paramParcel2.writeNoException();
          return true;
        }
      case 5049:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        g(ga.a.I(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 5050:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        az(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5051:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        b(paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 5052:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        g(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5053:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        DataHolder localDataHolder2 = h(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        if (localDataHolder2 != null)
        {
          paramParcel2.writeInt(1);
          localDataHolder2.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 5060:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        int i38 = aA(paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i38);
        return true;
      case 5054:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga21 = ga.a.I(paramParcel1.readStrongBinder());
        String str19 = paramParcel1.readString();
        int i37 = paramParcel1.readInt();
        boolean bool32 = false;
        if (i37 != 0)
          bool32 = true;
        a(localga21, str19, bool32);
        paramParcel2.writeNoException();
        return true;
      case 5061:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        i(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5055:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        n(paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 5067:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        boolean bool31 = fJ();
        paramParcel2.writeNoException();
        int i36 = 0;
        if (bool31)
          i36 = 1;
        paramParcel2.writeInt(i36);
        return true;
      case 5068:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        int i35 = paramParcel1.readInt();
        boolean bool30 = false;
        if (i35 != 0)
          bool30 = true;
        y(bool30);
        paramParcel2.writeNoException();
        return true;
      case 5056:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        h(ga.a.I(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 5057:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        j(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5062:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        i(ga.a.I(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 5063:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga20 = ga.a.I(paramParcel1.readStrongBinder());
        int i34 = paramParcel1.readInt();
        boolean bool29 = false;
        if (i34 != 0)
          bool29 = true;
        if (paramParcel1.readInt() != 0);
        for (Bundle localBundle3 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1); ; localBundle3 = null)
        {
          a(localga20, bool29, localBundle3);
          paramParcel2.writeNoException();
          return true;
        }
      case 5066:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        Uri localUri4 = aB(paramParcel1.readString());
        paramParcel2.writeNoException();
        if (localUri4 != null)
        {
          paramParcel2.writeInt(1);
          localUri4.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 5501:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga19 = ga.a.I(paramParcel1.readStrongBinder());
        String str18 = paramParcel1.readString();
        int i33 = paramParcel1.readInt();
        boolean bool27;
        if (paramParcel1.readInt() != 0)
        {
          bool27 = true;
          if (paramParcel1.readInt() == 0)
            break label3912;
        }
        for (boolean bool28 = true; ; bool28 = false)
        {
          b(localga19, str18, i33, bool27, bool28);
          paramParcel2.writeNoException();
          return true;
          bool27 = false;
          break;
        }
      case 5502:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        DataHolder localDataHolder1 = fK();
        paramParcel2.writeNoException();
        if (localDataHolder1 != null)
        {
          paramParcel2.writeInt(1);
          localDataHolder1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 6001:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga18 = ga.a.I(paramParcel1.readStrongBinder());
        int i32 = paramParcel1.readInt();
        boolean bool26 = false;
        if (i32 != 0)
          bool26 = true;
        a(localga18, bool26);
        paramParcel2.writeNoException();
        return true;
      case 6002:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga17 = ga.a.I(paramParcel1.readStrongBinder());
        String str16 = paramParcel1.readString();
        String str17 = paramParcel1.readString();
        int i31 = paramParcel1.readInt();
        boolean bool25 = false;
        if (i31 != 0)
          bool25 = true;
        a(localga17, str16, str17, bool25);
        paramParcel2.writeNoException();
        return true;
      case 6003:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga16 = ga.a.I(paramParcel1.readStrongBinder());
        int i29 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0);
        for (boolean bool23 = true; ; bool23 = false)
        {
          int i30 = paramParcel1.readInt();
          boolean bool24 = false;
          if (i30 != 0)
            bool24 = true;
          d(localga16, i29, bool23, bool24);
          paramParcel2.writeNoException();
          return true;
        }
      case 6004:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga15 = ga.a.I(paramParcel1.readStrongBinder());
        int i27 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0);
        for (boolean bool21 = true; ; bool21 = false)
        {
          int i28 = paramParcel1.readInt();
          boolean bool22 = false;
          if (i28 != 0)
            bool22 = true;
          e(localga15, i27, bool21, bool22);
          paramParcel2.writeNoException();
          return true;
        }
      case 6501:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga14 = ga.a.I(paramParcel1.readStrongBinder());
        String str15 = paramParcel1.readString();
        int i25 = paramParcel1.readInt();
        boolean bool17;
        boolean bool18;
        if (paramParcel1.readInt() != 0)
        {
          bool17 = true;
          if (paramParcel1.readInt() == 0)
            break label4316;
          bool18 = true;
          if (paramParcel1.readInt() == 0)
            break label4322;
        }
        for (boolean bool19 = true; ; bool19 = false)
        {
          int i26 = paramParcel1.readInt();
          boolean bool20 = false;
          if (i26 != 0)
            bool20 = true;
          a(localga14, str15, i25, bool17, bool18, bool19, bool20);
          paramParcel2.writeNoException();
          return true;
          bool17 = false;
          break;
          bool18 = false;
          break label4259;
        }
      case 6502:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga13 = ga.a.I(paramParcel1.readStrongBinder());
        String str14 = paramParcel1.readString();
        int i24 = paramParcel1.readInt();
        boolean bool16 = false;
        if (i24 != 0)
          bool16 = true;
        b(localga13, str14, bool16);
        paramParcel2.writeNoException();
        return true;
      case 6503:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga12 = ga.a.I(paramParcel1.readStrongBinder());
        int i23 = paramParcel1.readInt();
        boolean bool15 = false;
        if (i23 != 0)
          bool15 = true;
        b(localga12, bool15);
        paramParcel2.writeNoException();
        return true;
      case 6504:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga11 = ga.a.I(paramParcel1.readStrongBinder());
        String str13 = paramParcel1.readString();
        int i22 = paramParcel1.readInt();
        boolean bool14 = false;
        if (i22 != 0)
          bool14 = true;
        c(localga11, str13, bool14);
        paramParcel2.writeNoException();
        return true;
      case 6505:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga10 = ga.a.I(paramParcel1.readStrongBinder());
        String str12 = paramParcel1.readString();
        int i21 = paramParcel1.readInt();
        boolean bool13 = false;
        if (i21 != 0)
          bool13 = true;
        d(localga10, str12, bool13);
        paramParcel2.writeNoException();
        return true;
      case 6506:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga9 = ga.a.I(paramParcel1.readStrongBinder());
        String str10 = paramParcel1.readString();
        String str11 = paramParcel1.readString();
        int i20 = paramParcel1.readInt();
        boolean bool12 = false;
        if (i20 != 0)
          bool12 = true;
        b(localga9, str10, str11, bool12);
        paramParcel2.writeNoException();
        return true;
      case 6507:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        if (paramParcel1.readInt() != 0);
        for (Uri localUri3 = (Uri)Uri.CREATOR.createFromParcel(paramParcel1); ; localUri3 = null)
        {
          ParcelFileDescriptor localParcelFileDescriptor2 = e(localUri3);
          paramParcel2.writeNoException();
          if (localParcelFileDescriptor2 == null)
            break;
          paramParcel2.writeInt(1);
          localParcelFileDescriptor2.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 7001:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        k(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 7002:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readLong(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 7003:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga8 = ga.a.I(paramParcel1.readStrongBinder());
        String str9 = paramParcel1.readString();
        int i18 = paramParcel1.readInt();
        IBinder localIBinder1 = paramParcel1.readStrongBinder();
        int i19 = paramParcel1.readInt();
        Bundle localBundle2 = null;
        if (i19 != 0)
          localBundle2 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        b(localga8, str9, i18, localIBinder1, localBundle2);
        paramParcel2.writeNoException();
        return true;
      case 8001:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 8002:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        aC(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8003:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.createIntArray());
        paramParcel2.writeNoException();
        return true;
      case 8004:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga7 = ga.a.I(paramParcel1.readStrongBinder());
        int i15 = paramParcel1.readInt();
        int i16 = paramParcel1.readInt();
        String[] arrayOfString1 = paramParcel1.createStringArray();
        int i17 = paramParcel1.readInt();
        Bundle localBundle1 = null;
        if (i17 != 0)
          localBundle1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        a(localga7, i15, i16, arrayOfString1, localBundle1);
        paramParcel2.writeNoException();
        return true;
      case 8005:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        l(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8006:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        m(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8007:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.createByteArray(), paramParcel1.readString(), (ParticipantResult[])paramParcel1.createTypedArray(ParticipantResult.CREATOR));
        paramParcel2.writeNoException();
        return true;
      case 8008:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.createByteArray(), (ParticipantResult[])paramParcel1.createTypedArray(ParticipantResult.CREATOR));
        paramParcel2.writeNoException();
        return true;
      case 8009:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        n(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8010:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        o(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8011:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        c(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8012:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        b(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readLong());
        paramParcel2.writeNoException();
        return true;
      case 8013:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        p(paramParcel1.readLong());
        paramParcel2.writeNoException();
        return true;
      case 8014:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        p(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8024:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        int i14 = fC();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i14);
        return true;
      case 8025:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        k(paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8015:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        d(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8016:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        e(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8017:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.createIntArray());
        paramParcel2.writeNoException();
        return true;
      case 8026:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        c(paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 8018:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readLong(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8019:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(paramParcel1.readLong(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8020:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        b(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readLong(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8021:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        b(paramParcel1.readLong(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8022:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        fL();
        paramParcel2.writeNoException();
        return true;
      case 8023:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga6 = ga.a.I(paramParcel1.readStrongBinder());
        String str8 = paramParcel1.readString();
        int i12 = paramParcel1.readInt();
        int i13 = paramParcel1.readInt();
        boolean bool11 = false;
        if (i13 != 0)
          bool11 = true;
        a(localga6, str8, i12, bool11);
        paramParcel2.writeNoException();
        return true;
      case 8027:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga5 = ga.a.I(paramParcel1.readStrongBinder());
        int i11 = paramParcel1.readInt();
        boolean bool10 = false;
        if (i11 != 0)
          bool10 = true;
        c(localga5, bool10);
        paramParcel2.writeNoException();
        return true;
      case 9001:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga4 = ga.a.I(paramParcel1.readStrongBinder());
        String str7 = paramParcel1.readString();
        int i10 = paramParcel1.readInt();
        boolean bool8;
        if (paramParcel1.readInt() != 0)
        {
          bool8 = true;
          if (paramParcel1.readInt() == 0)
            break label5754;
        }
        for (boolean bool9 = true; ; bool9 = false)
        {
          c(localga4, str7, i10, bool8, bool9);
          paramParcel2.writeNoException();
          return true;
          bool8 = false;
          break;
        }
      case 9002:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        q(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 9003:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        Intent localIntent16 = fr();
        paramParcel2.writeNoException();
        if (localIntent16 != null)
        {
          paramParcel2.writeInt(1);
          localIntent16.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 9004:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        Intent localIntent15 = au(paramParcel1.readString());
        paramParcel2.writeNoException();
        if (localIntent15 != null)
        {
          paramParcel2.writeInt(1);
          localIntent15.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 9005:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        Intent localIntent14 = fs();
        paramParcel2.writeNoException();
        if (localIntent14 != null)
        {
          paramParcel2.writeInt(1);
          localIntent14.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 9006:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        Intent localIntent13 = ft();
        paramParcel2.writeNoException();
        if (localIntent13 != null)
        {
          paramParcel2.writeInt(1);
          localIntent13.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 9007:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        Intent localIntent12 = fu();
        paramParcel2.writeNoException();
        if (localIntent12 != null)
        {
          paramParcel2.writeInt(1);
          localIntent12.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 9008:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        int i8 = paramParcel1.readInt();
        int i9 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0);
        for (boolean bool7 = true; ; bool7 = false)
        {
          Intent localIntent11 = a(i8, i9, bool7);
          paramParcel2.writeNoException();
          if (localIntent11 == null)
            break;
          paramParcel2.writeInt(1);
          localIntent11.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 9009:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        int i6 = paramParcel1.readInt();
        int i7 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0);
        for (boolean bool6 = true; ; bool6 = false)
        {
          Intent localIntent10 = b(i6, i7, bool6);
          paramParcel2.writeNoException();
          if (localIntent10 == null)
            break;
          paramParcel2.writeInt(1);
          localIntent10.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 9010:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        Intent localIntent9 = fy();
        paramParcel2.writeNoException();
        if (localIntent9 != null)
        {
          paramParcel2.writeInt(1);
          localIntent9.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 9011:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        if (paramParcel1.readInt() != 0);
        for (RoomEntity localRoomEntity = (RoomEntity)RoomEntity.CREATOR.createFromParcel(paramParcel1); ; localRoomEntity = null)
        {
          Intent localIntent8 = a(localRoomEntity, paramParcel1.readInt());
          paramParcel2.writeNoException();
          if (localIntent8 == null)
            break;
          paramParcel2.writeInt(1);
          localIntent8.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 9012:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        Intent localIntent7 = fz();
        paramParcel2.writeNoException();
        if (localIntent7 != null)
        {
          paramParcel2.writeInt(1);
          localIntent7.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 9013:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        Intent localIntent6 = fM();
        paramParcel2.writeNoException();
        if (localIntent6 != null)
        {
          paramParcel2.writeInt(1);
          localIntent6.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 9031:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ParticipantEntity[] arrayOfParticipantEntity = (ParticipantEntity[])paramParcel1.createTypedArray(ParticipantEntity.CREATOR);
        String str5 = paramParcel1.readString();
        String str6 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0);
        for (Uri localUri1 = (Uri)Uri.CREATOR.createFromParcel(paramParcel1); ; localUri1 = null)
        {
          int i5 = paramParcel1.readInt();
          Uri localUri2 = null;
          if (i5 != 0)
            localUri2 = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
          Intent localIntent5 = a(arrayOfParticipantEntity, str5, str6, localUri1, localUri2);
          paramParcel2.writeNoException();
          if (localIntent5 == null)
            break;
          paramParcel2.writeInt(1);
          localIntent5.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 9019:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        int i4 = fA();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i4);
        return true;
      case 9020:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga3 = ga.a.I(paramParcel1.readStrongBinder());
        String str4 = paramParcel1.readString();
        int i3 = paramParcel1.readInt();
        boolean bool4;
        if (paramParcel1.readInt() != 0)
        {
          bool4 = true;
          if (paramParcel1.readInt() == 0)
            break label6582;
        }
        for (boolean bool5 = true; ; bool5 = false)
        {
          d(localga3, str4, i3, bool4, bool5);
          paramParcel2.writeNoException();
          return true;
          bool4 = false;
          break;
        }
      case 9028:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga2 = ga.a.I(paramParcel1.readStrongBinder());
        String str2 = paramParcel1.readString();
        String str3 = paramParcel1.readString();
        int i2 = paramParcel1.readInt();
        boolean bool2;
        if (paramParcel1.readInt() != 0)
        {
          bool2 = true;
          if (paramParcel1.readInt() == 0)
            break label6669;
        }
        for (boolean bool3 = true; ; bool3 = false)
        {
          a(localga2, str2, str3, i2, bool2, bool3);
          paramParcel2.writeNoException();
          return true;
          bool2 = false;
          break;
        }
      case 9030:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ParcelFileDescriptor localParcelFileDescriptor1 = aD(paramParcel1.readString());
        paramParcel2.writeNoException();
        if (localParcelFileDescriptor1 != null)
        {
          paramParcel2.writeInt(1);
          localParcelFileDescriptor1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 10001:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        c(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readLong());
        paramParcel2.writeNoException();
        return true;
      case 10002:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        q(paramParcel1.readLong());
        paramParcel2.writeNoException();
        return true;
      case 10003:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        c(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readLong(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 10004:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        c(paramParcel1.readLong(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 10005:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.createStringArray(), paramParcel1.readInt(), paramParcel1.createByteArray(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 10006:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.createStringArray());
        paramParcel2.writeNoException();
        return true;
      case 10007:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        b(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.createStringArray());
        paramParcel2.writeNoException();
        return true;
      case 10008:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.createStringArray());
        paramParcel2.writeNoException();
        return true;
      case 10009:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 10010:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 10011:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 10012:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        Intent localIntent4 = a(paramParcel1.readInt(), paramParcel1.createByteArray(), paramParcel1.readInt(), paramParcel1.readString());
        paramParcel2.writeNoException();
        if (localIntent4 != null)
        {
          paramParcel2.writeInt(1);
          localIntent4.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 10013:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        int i1 = fE();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i1);
        return true;
      case 10023:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        int n = fF();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(n);
        return true;
      case 10015:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        Intent localIntent3 = fD();
        paramParcel2.writeNoException();
        if (localIntent3 != null)
        {
          paramParcel2.writeInt(1);
          localIntent3.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 10022:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        int m = paramParcel1.readInt();
        gz localgz = null;
        if (m != 0)
          localgz = gz.CREATOR.am(paramParcel1);
        Intent localIntent2 = a(localgz, paramParcel1.readString());
        paramParcel2.writeNoException();
        if (localIntent2 != null)
        {
          paramParcel2.writeInt(1);
          localIntent2.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 10014:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        o(paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 10016:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 10017:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        ga localga1 = ga.a.I(paramParcel1.readStrongBinder());
        String str1 = paramParcel1.readString();
        int j = paramParcel1.readInt();
        int k = paramParcel1.readInt();
        boolean bool1 = false;
        if (k != 0)
          bool1 = true;
        b(localga1, str1, j, bool1);
        paramParcel2.writeNoException();
        return true;
      case 10021:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        int i = paramParcel1.readInt();
        gy localgy = null;
        if (i != 0)
          localgy = gy.CREATOR.al(paramParcel1);
        Intent localIntent1 = a(localgy, paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        if (localIntent1 != null)
        {
          paramParcel2.writeInt(1);
          localIntent1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 10018:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readInt(), paramParcel1.createIntArray());
        paramParcel2.writeNoException();
        return true;
      case 10019:
        label3039: label4322: label5754: paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        label3118: label3912: label4316: a(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.createIntArray());
        label4259: label6582: paramParcel2.writeNoException();
        label6669: return true;
      case 10020:
      }
      paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
      c(ga.a.I(paramParcel1.readStrongBinder()), paramParcel1.createStringArray());
      paramParcel2.writeNoException();
      return true;
    }

    private static class a
      implements gb
    {
      private IBinder ky;

      a(IBinder paramIBinder)
      {
        this.ky = paramIBinder;
      }

      public int a(ga paramga, byte[] paramArrayOfByte, String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeByteArray(paramArrayOfByte);
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            this.ky.transact(5033, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int i = localParcel2.readInt();
            return i;
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public Intent a(int paramInt1, int paramInt2, boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          int i = 0;
          if (paramBoolean)
            i = 1;
          localParcel1.writeInt(i);
          this.ky.transact(9008, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0);
          for (Intent localIntent = (Intent)Intent.CREATOR.createFromParcel(localParcel2); ; localIntent = null)
            return localIntent;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public Intent a(int paramInt1, byte[] paramArrayOfByte, int paramInt2, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeByteArray(paramArrayOfByte);
          localParcel1.writeInt(paramInt2);
          localParcel1.writeString(paramString);
          this.ky.transact(10012, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0)
          {
            localIntent = (Intent)Intent.CREATOR.createFromParcel(localParcel2);
            return localIntent;
          }
          Intent localIntent = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public Intent a(RoomEntity paramRoomEntity, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (paramRoomEntity == null)
              continue;
            localParcel1.writeInt(1);
            paramRoomEntity.writeToParcel(localParcel1, 0);
            localParcel1.writeInt(paramInt);
            this.ky.transact(9011, localParcel1, localParcel2, 0);
            localParcel2.readException();
            if (localParcel2.readInt() != 0)
            {
              localIntent = (Intent)Intent.CREATOR.createFromParcel(localParcel2);
              return localIntent;
              localParcel1.writeInt(0);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          Intent localIntent = null;
        }
      }

      public Intent a(gy paramgy, String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (paramgy == null)
              continue;
            localParcel1.writeInt(1);
            paramgy.writeToParcel(localParcel1, 0);
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            this.ky.transact(10021, localParcel1, localParcel2, 0);
            localParcel2.readException();
            if (localParcel2.readInt() != 0)
            {
              localIntent = (Intent)Intent.CREATOR.createFromParcel(localParcel2);
              return localIntent;
              localParcel1.writeInt(0);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          Intent localIntent = null;
        }
      }

      public Intent a(gz paramgz, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (paramgz == null)
              continue;
            localParcel1.writeInt(1);
            paramgz.writeToParcel(localParcel1, 0);
            localParcel1.writeString(paramString);
            this.ky.transact(10022, localParcel1, localParcel2, 0);
            localParcel2.readException();
            if (localParcel2.readInt() != 0)
            {
              localIntent = (Intent)Intent.CREATOR.createFromParcel(localParcel2);
              return localIntent;
              localParcel1.writeInt(0);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          Intent localIntent = null;
        }
      }

      public Intent a(ParticipantEntity[] paramArrayOfParticipantEntity, String paramString1, String paramString2, Uri paramUri1, Uri paramUri2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            localParcel1.writeTypedArray(paramArrayOfParticipantEntity, 0);
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            if (paramUri1 == null)
              continue;
            localParcel1.writeInt(1);
            paramUri1.writeToParcel(localParcel1, 0);
            if (paramUri2 != null)
            {
              localParcel1.writeInt(1);
              paramUri2.writeToParcel(localParcel1, 0);
              this.ky.transact(9031, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() == 0)
                break label166;
              localIntent = (Intent)Intent.CREATOR.createFromParcel(localParcel2);
              return localIntent;
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
          continue;
          label166: Intent localIntent = null;
        }
      }

      public void a(long paramLong, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeLong(paramLong);
          localParcel1.writeString(paramString);
          this.ky.transact(8019, localParcel1, localParcel2, 0);
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

      public void a(IBinder paramIBinder, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeStrongBinder(paramIBinder);
          if (paramBundle != null)
          {
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(5005, localParcel1, localParcel2, 0);
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

      public void a(ga paramga)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            this.ky.transact(5002, localParcel1, localParcel2, 0);
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

      public void a(ga paramga, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            this.ky.transact(10016, localParcel1, localParcel2, 0);
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

      public void a(ga paramga, int paramInt1, int paramInt2, int paramInt3)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt1);
            localParcel1.writeInt(paramInt2);
            localParcel1.writeInt(paramInt3);
            this.ky.transact(10009, localParcel1, localParcel2, 0);
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

      public void a(ga paramga, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          IBinder localIBinder;
          int j;
          if (paramga != null)
          {
            localIBinder = paramga.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt1);
            localParcel1.writeInt(paramInt2);
            if (!paramBoolean1)
              break label119;
            j = i;
            label60: localParcel1.writeInt(j);
            if (!paramBoolean2)
              break label125;
          }
          while (true)
          {
            localParcel1.writeInt(i);
            this.ky.transact(5044, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label119: j = 0;
            break label60;
            label125: i = 0;
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void a(ga paramga, int paramInt1, int paramInt2, String[] paramArrayOfString, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          IBinder localIBinder;
          if (paramga != null)
          {
            localIBinder = paramga.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt1);
            localParcel1.writeInt(paramInt2);
            localParcel1.writeStringArray(paramArrayOfString);
            if (paramBundle == null)
              break label114;
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(8004, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label114: localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void a(ga paramga, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          IBinder localIBinder;
          int j;
          if (paramga != null)
          {
            localIBinder = paramga.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            if (!paramBoolean1)
              break label112;
            j = i;
            label53: localParcel1.writeInt(j);
            if (!paramBoolean2)
              break label118;
          }
          while (true)
          {
            localParcel1.writeInt(i);
            this.ky.transact(5015, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label112: j = 0;
            break label53;
            label118: i = 0;
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void a(ga paramga, int paramInt, int[] paramArrayOfInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            localParcel1.writeIntArray(paramArrayOfInt);
            this.ky.transact(10018, localParcel1, localParcel2, 0);
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

      public void a(ga paramga, long paramLong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeLong(paramLong);
            this.ky.transact(5058, localParcel1, localParcel2, 0);
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

      public void a(ga paramga, long paramLong, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeLong(paramLong);
            localParcel1.writeString(paramString);
            this.ky.transact(8018, localParcel1, localParcel2, 0);
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

      public void a(ga paramga, Bundle paramBundle, int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          IBinder localIBinder;
          if (paramga != null)
          {
            localIBinder = paramga.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            if (paramBundle == null)
              break label106;
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            localParcel1.writeInt(paramInt1);
            localParcel1.writeInt(paramInt2);
            this.ky.transact(5021, localParcel1, localParcel2, 0);
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

      public void a(ga paramga, IBinder paramIBinder, int paramInt, String[] paramArrayOfString, Bundle paramBundle, boolean paramBoolean, long paramLong)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (paramga == null)
              continue;
            IBinder localIBinder = paramga.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeStrongBinder(paramIBinder);
            localParcel1.writeInt(paramInt);
            localParcel1.writeStringArray(paramArrayOfString);
            if (paramBundle == null)
              continue;
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
            break label164;
            localParcel1.writeInt(i);
            localParcel1.writeLong(paramLong);
            this.ky.transact(5030, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            continue;
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          label164: 
          do
          {
            i = 0;
            break;
          }
          while (!paramBoolean);
        }
      }

      public void a(ga paramga, IBinder paramIBinder, String paramString, boolean paramBoolean, long paramLong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeStrongBinder(paramIBinder);
            localParcel1.writeString(paramString);
            int i = 0;
            if (paramBoolean)
              i = 1;
            localParcel1.writeInt(i);
            localParcel1.writeLong(paramLong);
            this.ky.transact(5031, localParcel1, localParcel2, 0);
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

      public void a(ga paramga, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            this.ky.transact(5014, localParcel1, localParcel2, 0);
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

      public void a(ga paramga, String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            localParcel1.writeInt(paramInt);
            this.ky.transact(10011, localParcel1, localParcel2, 0);
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

      public void a(ga paramga, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            localParcel1.writeInt(paramInt1);
            localParcel1.writeInt(paramInt2);
            localParcel1.writeInt(paramInt3);
            int i = 0;
            if (paramBoolean)
              i = 1;
            localParcel1.writeInt(i);
            this.ky.transact(5019, localParcel1, localParcel2, 0);
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

      public void a(ga paramga, String paramString, int paramInt, IBinder paramIBinder, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          IBinder localIBinder;
          if (paramga != null)
          {
            localIBinder = paramga.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            localParcel1.writeInt(paramInt);
            localParcel1.writeStrongBinder(paramIBinder);
            if (paramBundle == null)
              break label114;
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(5025, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label114: localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void a(ga paramga, String paramString, int paramInt, boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            localParcel1.writeInt(paramInt);
            int i = 0;
            if (paramBoolean)
              i = 1;
            localParcel1.writeInt(i);
            this.ky.transact(8023, localParcel1, localParcel2, 0);
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

      public void a(ga paramga, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          IBinder localIBinder;
          int j;
          if (paramga != null)
          {
            localIBinder = paramga.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            localParcel1.writeInt(paramInt);
            if (!paramBoolean1)
              break label119;
            j = i;
            label60: localParcel1.writeInt(j);
            if (!paramBoolean2)
              break label125;
          }
          while (true)
          {
            localParcel1.writeInt(i);
            this.ky.transact(5045, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label119: j = 0;
            break label60;
            label125: i = 0;
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void a(ga paramga, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          IBinder localIBinder;
          int j;
          label60: int k;
          label76: int m;
          if (paramga != null)
          {
            localIBinder = paramga.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            localParcel1.writeInt(paramInt);
            if (!paramBoolean1)
              break label151;
            j = i;
            localParcel1.writeInt(j);
            if (!paramBoolean2)
              break label157;
            k = i;
            localParcel1.writeInt(k);
            if (!paramBoolean3)
              break label163;
            m = i;
            label92: localParcel1.writeInt(m);
            if (!paramBoolean4)
              break label169;
          }
          while (true)
          {
            localParcel1.writeInt(i);
            this.ky.transact(6501, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label151: j = 0;
            break label60;
            label157: k = 0;
            break label76;
            label163: m = 0;
            break label92;
            label169: i = 0;
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void a(ga paramga, String paramString, int paramInt, int[] paramArrayOfInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            localParcel1.writeInt(paramInt);
            localParcel1.writeIntArray(paramArrayOfInt);
            this.ky.transact(10019, localParcel1, localParcel2, 0);
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

      public void a(ga paramga, String paramString, long paramLong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            localParcel1.writeLong(paramLong);
            this.ky.transact(5016, localParcel1, localParcel2, 0);
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

      public void a(ga paramga, String paramString1, long paramLong, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString1);
            localParcel1.writeLong(paramLong);
            localParcel1.writeString(paramString2);
            this.ky.transact(7002, localParcel1, localParcel2, 0);
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

      public void a(ga paramga, String paramString, IBinder paramIBinder, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          IBinder localIBinder;
          if (paramga != null)
          {
            localIBinder = paramga.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            localParcel1.writeStrongBinder(paramIBinder);
            if (paramBundle == null)
              break label107;
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(5023, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label107: localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void a(ga paramga, String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            this.ky.transact(5038, localParcel1, localParcel2, 0);
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

      public void a(ga paramga, String paramString1, String paramString2, int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            localParcel1.writeInt(paramInt1);
            localParcel1.writeInt(paramInt2);
            this.ky.transact(8001, localParcel1, localParcel2, 0);
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

      public void a(ga paramga, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            localParcel1.writeInt(paramInt1);
            localParcel1.writeInt(paramInt2);
            localParcel1.writeInt(paramInt3);
            this.ky.transact(10010, localParcel1, localParcel2, 0);
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

      public void a(ga paramga, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            localParcel1.writeInt(paramInt1);
            localParcel1.writeInt(paramInt2);
            localParcel1.writeInt(paramInt3);
            int i = 0;
            if (paramBoolean)
              i = 1;
            localParcel1.writeInt(i);
            this.ky.transact(5039, localParcel1, localParcel2, 0);
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

      public void a(ga paramga, String paramString1, String paramString2, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          IBinder localIBinder;
          int j;
          if (paramga != null)
          {
            localIBinder = paramga.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            localParcel1.writeInt(paramInt);
            if (!paramBoolean1)
              break label126;
            j = i;
            label67: localParcel1.writeInt(j);
            if (!paramBoolean2)
              break label132;
          }
          while (true)
          {
            localParcel1.writeInt(i);
            this.ky.transact(9028, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label126: j = 0;
            break label67;
            label132: i = 0;
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void a(ga paramga, String paramString1, String paramString2, boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            int i = 0;
            if (paramBoolean)
              i = 1;
            localParcel1.writeInt(i);
            this.ky.transact(6002, localParcel1, localParcel2, 0);
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

      public void a(ga paramga, String paramString1, String paramString2, String[] paramArrayOfString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            localParcel1.writeStringArray(paramArrayOfString);
            this.ky.transact(10008, localParcel1, localParcel2, 0);
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

      public void a(ga paramga, String paramString, boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            int i = 0;
            if (paramBoolean)
              i = 1;
            localParcel1.writeInt(i);
            this.ky.transact(5054, localParcel1, localParcel2, 0);
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

      public void a(ga paramga, String paramString1, byte[] paramArrayOfByte, String paramString2, ParticipantResult[] paramArrayOfParticipantResult)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString1);
            localParcel1.writeByteArray(paramArrayOfByte);
            localParcel1.writeString(paramString2);
            localParcel1.writeTypedArray(paramArrayOfParticipantResult, 0);
            this.ky.transact(8007, localParcel1, localParcel2, 0);
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

      public void a(ga paramga, String paramString, byte[] paramArrayOfByte, ParticipantResult[] paramArrayOfParticipantResult)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            localParcel1.writeByteArray(paramArrayOfByte);
            localParcel1.writeTypedArray(paramArrayOfParticipantResult, 0);
            this.ky.transact(8008, localParcel1, localParcel2, 0);
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

      public void a(ga paramga, String paramString, int[] paramArrayOfInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            localParcel1.writeIntArray(paramArrayOfInt);
            this.ky.transact(8017, localParcel1, localParcel2, 0);
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

      public void a(ga paramga, String paramString, String[] paramArrayOfString, int paramInt1, byte[] paramArrayOfByte, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            localParcel1.writeStringArray(paramArrayOfString);
            localParcel1.writeInt(paramInt1);
            localParcel1.writeByteArray(paramArrayOfByte);
            localParcel1.writeInt(paramInt2);
            this.ky.transact(10005, localParcel1, localParcel2, 0);
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

      public void a(ga paramga, boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            int i = 0;
            if (paramBoolean)
              i = 1;
            localParcel1.writeInt(i);
            this.ky.transact(6001, localParcel1, localParcel2, 0);
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

      public void a(ga paramga, boolean paramBoolean, Bundle paramBundle)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          IBinder localIBinder;
          if (paramga != null)
          {
            localIBinder = paramga.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            if (!paramBoolean)
              break label107;
            label43: localParcel1.writeInt(i);
            if (paramBundle == null)
              break label113;
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(5063, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label107: i = 0;
            break label43;
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

      public void a(ga paramga, int[] paramArrayOfInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeIntArray(paramArrayOfInt);
            this.ky.transact(8003, localParcel1, localParcel2, 0);
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

      public void a(ga paramga, String[] paramArrayOfString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeStringArray(paramArrayOfString);
            this.ky.transact(10006, localParcel1, localParcel2, 0);
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

      public int aA(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          this.ky.transact(5060, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public Uri aB(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          this.ky.transact(5066, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0)
          {
            localUri = (Uri)Uri.CREATOR.createFromParcel(localParcel2);
            return localUri;
          }
          Uri localUri = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void aC(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          this.ky.transact(8002, localParcel1, localParcel2, 0);
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

      public ParcelFileDescriptor aD(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          this.ky.transact(9030, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0)
          {
            localParcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(localParcel2);
            return localParcelFileDescriptor;
          }
          ParcelFileDescriptor localParcelFileDescriptor = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void aU(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeInt(paramInt);
          this.ky.transact(5036, localParcel1, localParcel2, 0);
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

      public IBinder asBinder()
      {
        return this.ky;
      }

      public Intent au(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          this.ky.transact(9004, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0)
          {
            localIntent = (Intent)Intent.CREATOR.createFromParcel(localParcel2);
            return localIntent;
          }
          Intent localIntent = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public String ax(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          this.ky.transact(5064, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public String ay(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          this.ky.transact(5035, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void az(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          this.ky.transact(5050, localParcel1, localParcel2, 0);
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

      public int b(byte[] paramArrayOfByte, String paramString, String[] paramArrayOfString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeByteArray(paramArrayOfByte);
          localParcel1.writeString(paramString);
          localParcel1.writeStringArray(paramArrayOfString);
          this.ky.transact(5034, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public Intent b(int paramInt1, int paramInt2, boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          int i = 0;
          if (paramBoolean)
            i = 1;
          localParcel1.writeInt(i);
          this.ky.transact(9009, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0);
          for (Intent localIntent = (Intent)Intent.CREATOR.createFromParcel(localParcel2); ; localIntent = null)
            return localIntent;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void b(long paramLong, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeLong(paramLong);
          localParcel1.writeString(paramString);
          this.ky.transact(8021, localParcel1, localParcel2, 0);
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

      public void b(ga paramga)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            this.ky.transact(5017, localParcel1, localParcel2, 0);
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

      public void b(ga paramga, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          IBinder localIBinder;
          int j;
          if (paramga != null)
          {
            localIBinder = paramga.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            if (!paramBoolean1)
              break label112;
            j = i;
            label53: localParcel1.writeInt(j);
            if (!paramBoolean2)
              break label118;
          }
          while (true)
          {
            localParcel1.writeInt(i);
            this.ky.transact(5046, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label112: j = 0;
            break label53;
            label118: i = 0;
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void b(ga paramga, long paramLong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeLong(paramLong);
            this.ky.transact(8012, localParcel1, localParcel2, 0);
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

      public void b(ga paramga, long paramLong, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeLong(paramLong);
            localParcel1.writeString(paramString);
            this.ky.transact(8020, localParcel1, localParcel2, 0);
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

      public void b(ga paramga, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            this.ky.transact(5018, localParcel1, localParcel2, 0);
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

      public void b(ga paramga, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            localParcel1.writeInt(paramInt1);
            localParcel1.writeInt(paramInt2);
            localParcel1.writeInt(paramInt3);
            int i = 0;
            if (paramBoolean)
              i = 1;
            localParcel1.writeInt(i);
            this.ky.transact(5020, localParcel1, localParcel2, 0);
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

      public void b(ga paramga, String paramString, int paramInt, IBinder paramIBinder, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          IBinder localIBinder;
          if (paramga != null)
          {
            localIBinder = paramga.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            localParcel1.writeInt(paramInt);
            localParcel1.writeStrongBinder(paramIBinder);
            if (paramBundle == null)
              break label114;
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(7003, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label114: localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void b(ga paramga, String paramString, int paramInt, boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            localParcel1.writeInt(paramInt);
            int i = 0;
            if (paramBoolean)
              i = 1;
            localParcel1.writeInt(i);
            this.ky.transact(10017, localParcel1, localParcel2, 0);
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

      public void b(ga paramga, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          IBinder localIBinder;
          int j;
          if (paramga != null)
          {
            localIBinder = paramga.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            localParcel1.writeInt(paramInt);
            if (!paramBoolean1)
              break label119;
            j = i;
            label60: localParcel1.writeInt(j);
            if (!paramBoolean2)
              break label125;
          }
          while (true)
          {
            localParcel1.writeInt(i);
            this.ky.transact(5501, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label119: j = 0;
            break label60;
            label125: i = 0;
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void b(ga paramga, String paramString, IBinder paramIBinder, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          IBinder localIBinder;
          if (paramga != null)
          {
            localIBinder = paramga.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            localParcel1.writeStrongBinder(paramIBinder);
            if (paramBundle == null)
              break label107;
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(5024, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label107: localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void b(ga paramga, String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            this.ky.transact(5041, localParcel1, localParcel2, 0);
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

      public void b(ga paramga, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            localParcel1.writeInt(paramInt1);
            localParcel1.writeInt(paramInt2);
            localParcel1.writeInt(paramInt3);
            int i = 0;
            if (paramBoolean)
              i = 1;
            localParcel1.writeInt(i);
            this.ky.transact(5040, localParcel1, localParcel2, 0);
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

      public void b(ga paramga, String paramString1, String paramString2, boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            int i = 0;
            if (paramBoolean)
              i = 1;
            localParcel1.writeInt(i);
            this.ky.transact(6506, localParcel1, localParcel2, 0);
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

      public void b(ga paramga, String paramString, boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            int i = 0;
            if (paramBoolean)
              i = 1;
            localParcel1.writeInt(i);
            this.ky.transact(6502, localParcel1, localParcel2, 0);
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

      public void b(ga paramga, boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            int i = 0;
            if (paramBoolean)
              i = 1;
            localParcel1.writeInt(i);
            this.ky.transact(6503, localParcel1, localParcel2, 0);
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

      public void b(ga paramga, String[] paramArrayOfString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeStringArray(paramArrayOfString);
            this.ky.transact(10007, localParcel1, localParcel2, 0);
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

      public void b(String paramString1, String paramString2, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          localParcel1.writeInt(paramInt);
          this.ky.transact(5051, localParcel1, localParcel2, 0);
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

      public void c(long paramLong, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeLong(paramLong);
          localParcel1.writeString(paramString);
          this.ky.transact(10004, localParcel1, localParcel2, 0);
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

      public void c(ga paramga)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            this.ky.transact(5022, localParcel1, localParcel2, 0);
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

      public void c(ga paramga, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          IBinder localIBinder;
          int j;
          if (paramga != null)
          {
            localIBinder = paramga.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            if (!paramBoolean1)
              break label112;
            j = i;
            label53: localParcel1.writeInt(j);
            if (!paramBoolean2)
              break label118;
          }
          while (true)
          {
            localParcel1.writeInt(i);
            this.ky.transact(5048, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label112: j = 0;
            break label53;
            label118: i = 0;
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void c(ga paramga, long paramLong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeLong(paramLong);
            this.ky.transact(10001, localParcel1, localParcel2, 0);
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

      public void c(ga paramga, long paramLong, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeLong(paramLong);
            localParcel1.writeString(paramString);
            this.ky.transact(10003, localParcel1, localParcel2, 0);
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

      public void c(ga paramga, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            this.ky.transact(5032, localParcel1, localParcel2, 0);
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

      public void c(ga paramga, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          IBinder localIBinder;
          int j;
          if (paramga != null)
          {
            localIBinder = paramga.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            localParcel1.writeInt(paramInt);
            if (!paramBoolean1)
              break label119;
            j = i;
            label60: localParcel1.writeInt(j);
            if (!paramBoolean2)
              break label125;
          }
          while (true)
          {
            localParcel1.writeInt(i);
            this.ky.transact(9001, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label119: j = 0;
            break label60;
            label125: i = 0;
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void c(ga paramga, String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            this.ky.transact(8011, localParcel1, localParcel2, 0);
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

      public void c(ga paramga, String paramString, boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            int i = 0;
            if (paramBoolean)
              i = 1;
            localParcel1.writeInt(i);
            this.ky.transact(6504, localParcel1, localParcel2, 0);
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

      public void c(ga paramga, boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            int i = 0;
            if (paramBoolean)
              i = 1;
            localParcel1.writeInt(i);
            this.ky.transact(8027, localParcel1, localParcel2, 0);
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

      public void c(ga paramga, String[] paramArrayOfString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeStringArray(paramArrayOfString);
            this.ky.transact(10020, localParcel1, localParcel2, 0);
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

      public void c(String paramString1, String paramString2, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          localParcel1.writeInt(paramInt);
          this.ky.transact(8026, localParcel1, localParcel2, 0);
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

      public Bundle cY()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.ky.transact(5004, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0)
          {
            localBundle = (Bundle)Bundle.CREATOR.createFromParcel(localParcel2);
            return localBundle;
          }
          Bundle localBundle = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void d(ga paramga)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            this.ky.transact(5026, localParcel1, localParcel2, 0);
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

      public void d(ga paramga, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          IBinder localIBinder;
          int j;
          if (paramga != null)
          {
            localIBinder = paramga.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            if (!paramBoolean1)
              break label112;
            j = i;
            label53: localParcel1.writeInt(j);
            if (!paramBoolean2)
              break label118;
          }
          while (true)
          {
            localParcel1.writeInt(i);
            this.ky.transact(6003, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label112: j = 0;
            break label53;
            label118: i = 0;
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void d(ga paramga, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            this.ky.transact(5037, localParcel1, localParcel2, 0);
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

      public void d(ga paramga, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          IBinder localIBinder;
          int j;
          if (paramga != null)
          {
            localIBinder = paramga.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            localParcel1.writeInt(paramInt);
            if (!paramBoolean1)
              break label119;
            j = i;
            label60: localParcel1.writeInt(j);
            if (!paramBoolean2)
              break label125;
          }
          while (true)
          {
            localParcel1.writeInt(i);
            this.ky.transact(9020, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label119: j = 0;
            break label60;
            label125: i = 0;
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void d(ga paramga, String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            this.ky.transact(8015, localParcel1, localParcel2, 0);
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

      public void d(ga paramga, String paramString, boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            int i = 0;
            if (paramBoolean)
              i = 1;
            localParcel1.writeInt(i);
            this.ky.transact(6505, localParcel1, localParcel2, 0);
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

      public ParcelFileDescriptor e(Uri paramUri)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (paramUri == null)
              continue;
            localParcel1.writeInt(1);
            paramUri.writeToParcel(localParcel1, 0);
            this.ky.transact(6507, localParcel1, localParcel2, 0);
            localParcel2.readException();
            if (localParcel2.readInt() != 0)
            {
              localParcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(localParcel2);
              return localParcelFileDescriptor;
              localParcel1.writeInt(0);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          ParcelFileDescriptor localParcelFileDescriptor = null;
        }
      }

      public void e(ga paramga)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            this.ky.transact(5027, localParcel1, localParcel2, 0);
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

      public void e(ga paramga, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          IBinder localIBinder;
          int j;
          if (paramga != null)
          {
            localIBinder = paramga.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            if (!paramBoolean1)
              break label112;
            j = i;
            label53: localParcel1.writeInt(j);
            if (!paramBoolean2)
              break label118;
          }
          while (true)
          {
            localParcel1.writeInt(i);
            this.ky.transact(6004, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label112: j = 0;
            break label53;
            label118: i = 0;
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void e(ga paramga, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            this.ky.transact(5042, localParcel1, localParcel2, 0);
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

      public void e(ga paramga, String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            this.ky.transact(8016, localParcel1, localParcel2, 0);
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

      public void f(ga paramga)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            this.ky.transact(5047, localParcel1, localParcel2, 0);
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

      public void f(ga paramga, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            this.ky.transact(5043, localParcel1, localParcel2, 0);
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

      public int fA()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.ky.transact(9019, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public String fB()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.ky.transact(5003, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int fC()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.ky.transact(8024, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public Intent fD()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.ky.transact(10015, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0)
          {
            localIntent = (Intent)Intent.CREATOR.createFromParcel(localParcel2);
            return localIntent;
          }
          Intent localIntent = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public int fE()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.ky.transact(10013, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int fF()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.ky.transact(10023, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void fH()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.ky.transact(5006, localParcel1, localParcel2, 0);
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

      public DataHolder fI()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.ky.transact(5013, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0)
          {
            DataHolder localDataHolder2 = DataHolder.CREATOR.createFromParcel(localParcel2);
            localDataHolder1 = localDataHolder2;
            return localDataHolder1;
          }
          DataHolder localDataHolder1 = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean fJ()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.ky.transact(5067, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          int j = 0;
          if (i != 0)
            j = 1;
          return j;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public DataHolder fK()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.ky.transact(5502, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0)
          {
            DataHolder localDataHolder2 = DataHolder.CREATOR.createFromParcel(localParcel2);
            localDataHolder1 = localDataHolder2;
            return localDataHolder1;
          }
          DataHolder localDataHolder1 = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void fL()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.ky.transact(8022, localParcel1, localParcel2, 0);
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

      public Intent fM()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.ky.transact(9013, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0)
          {
            localIntent = (Intent)Intent.CREATOR.createFromParcel(localParcel2);
            return localIntent;
          }
          Intent localIntent = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public String fn()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.ky.transact(5007, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public String fo()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.ky.transact(5012, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public Intent fr()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.ky.transact(9003, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0)
          {
            localIntent = (Intent)Intent.CREATOR.createFromParcel(localParcel2);
            return localIntent;
          }
          Intent localIntent = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public Intent fs()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.ky.transact(9005, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0)
          {
            localIntent = (Intent)Intent.CREATOR.createFromParcel(localParcel2);
            return localIntent;
          }
          Intent localIntent = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public Intent ft()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.ky.transact(9006, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0)
          {
            localIntent = (Intent)Intent.CREATOR.createFromParcel(localParcel2);
            return localIntent;
          }
          Intent localIntent = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public Intent fu()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.ky.transact(9007, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0)
          {
            localIntent = (Intent)Intent.CREATOR.createFromParcel(localParcel2);
            return localIntent;
          }
          Intent localIntent = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public Intent fy()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.ky.transact(9010, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0)
          {
            localIntent = (Intent)Intent.CREATOR.createFromParcel(localParcel2);
            return localIntent;
          }
          Intent localIntent = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public Intent fz()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.ky.transact(9012, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0)
          {
            localIntent = (Intent)Intent.CREATOR.createFromParcel(localParcel2);
            return localIntent;
          }
          Intent localIntent = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void g(ga paramga)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            this.ky.transact(5049, localParcel1, localParcel2, 0);
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

      public void g(ga paramga, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            this.ky.transact(5052, localParcel1, localParcel2, 0);
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

      public DataHolder h(ga paramga, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            this.ky.transact(5053, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int i = localParcel2.readInt();
            Object localObject2 = null;
            if (i != 0)
            {
              DataHolder localDataHolder = DataHolder.CREATOR.createFromParcel(localParcel2);
              localObject2 = localDataHolder;
            }
            return localObject2;
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject1;
      }

      public void h(ga paramga)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            this.ky.transact(5056, localParcel1, localParcel2, 0);
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

      public void i(ga paramga)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            this.ky.transact(5062, localParcel1, localParcel2, 0);
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

      public void i(ga paramga, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            this.ky.transact(5061, localParcel1, localParcel2, 0);
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

      public void j(ga paramga, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            this.ky.transact(5057, localParcel1, localParcel2, 0);
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

      public void j(String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          this.ky.transact(5065, localParcel1, localParcel2, 0);
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

      public void k(ga paramga, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            this.ky.transact(7001, localParcel1, localParcel2, 0);
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

      public void k(String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          this.ky.transact(8025, localParcel1, localParcel2, 0);
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

      public void l(ga paramga, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            this.ky.transact(8005, localParcel1, localParcel2, 0);
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

      public void l(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          this.ky.transact(5029, localParcel1, localParcel2, 0);
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

      public void m(ga paramga, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            this.ky.transact(8006, localParcel1, localParcel2, 0);
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

      public void m(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          this.ky.transact(5028, localParcel1, localParcel2, 0);
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

      public void n(long paramLong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeLong(paramLong);
          this.ky.transact(5001, localParcel1, localParcel2, 0);
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

      public void n(ga paramga, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            this.ky.transact(8009, localParcel1, localParcel2, 0);
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

      public void n(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          this.ky.transact(5055, localParcel1, localParcel2, 0);
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

      public void o(long paramLong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeLong(paramLong);
          this.ky.transact(5059, localParcel1, localParcel2, 0);
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

      public void o(ga paramga, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            this.ky.transact(8010, localParcel1, localParcel2, 0);
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

      public void o(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          this.ky.transact(10014, localParcel1, localParcel2, 0);
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

      public void p(long paramLong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeLong(paramLong);
          this.ky.transact(8013, localParcel1, localParcel2, 0);
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

      public void p(ga paramga, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            this.ky.transact(8014, localParcel1, localParcel2, 0);
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

      public void q(long paramLong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeLong(paramLong);
          this.ky.transact(10002, localParcel1, localParcel2, 0);
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

      public void q(ga paramga, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramga != null);
          for (IBinder localIBinder = paramga.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            this.ky.transact(9002, localParcel1, localParcel2, 0);
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

      public void y(boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          int i = 0;
          if (paramBoolean)
            i = 1;
          localParcel1.writeInt(i);
          this.ky.transact(5068, localParcel1, localParcel2, 0);
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
 * Qualified Name:     com.google.android.gms.internal.gb
 * JD-Core Version:    0.6.0
 */