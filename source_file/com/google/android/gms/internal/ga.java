package com.google.android.gms.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataHolderCreator;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;

public abstract interface ga extends IInterface
{
  public abstract void A(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void B(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void C(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void D(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void E(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void F(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void a(int paramInt, Bundle paramBundle)
    throws RemoteException;

  public abstract void a(int paramInt, String paramString, boolean paramBoolean)
    throws RemoteException;

  public abstract void a(DataHolder paramDataHolder1, DataHolder paramDataHolder2)
    throws RemoteException;

  public abstract void a(DataHolder paramDataHolder, String[] paramArrayOfString)
    throws RemoteException;

  public abstract void aR(int paramInt)
    throws RemoteException;

  public abstract void aS(int paramInt)
    throws RemoteException;

  public abstract void b(int paramInt1, int paramInt2, String paramString)
    throws RemoteException;

  public abstract void b(int paramInt, Bundle paramBundle)
    throws RemoteException;

  public abstract void b(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void b(DataHolder paramDataHolder, String[] paramArrayOfString)
    throws RemoteException;

  public abstract void c(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void c(DataHolder paramDataHolder, String[] paramArrayOfString)
    throws RemoteException;

  public abstract void cM()
    throws RemoteException;

  public abstract void d(int paramInt, String paramString)
    throws RemoteException;

  public abstract void d(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void d(DataHolder paramDataHolder, String[] paramArrayOfString)
    throws RemoteException;

  public abstract void e(int paramInt, String paramString)
    throws RemoteException;

  public abstract void e(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void e(DataHolder paramDataHolder, String[] paramArrayOfString)
    throws RemoteException;

  public abstract void f(int paramInt, String paramString)
    throws RemoteException;

  public abstract void f(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void f(DataHolder paramDataHolder, String[] paramArrayOfString)
    throws RemoteException;

  public abstract void g(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void h(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void i(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void j(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void k(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void l(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void m(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void n(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void o(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void onInvitationRemoved(String paramString)
    throws RemoteException;

  public abstract void onLeftRoom(int paramInt, String paramString)
    throws RemoteException;

  public abstract void onP2PConnected(String paramString)
    throws RemoteException;

  public abstract void onP2PDisconnected(String paramString)
    throws RemoteException;

  public abstract void onRealTimeMessageReceived(RealTimeMessage paramRealTimeMessage)
    throws RemoteException;

  public abstract void onRequestRemoved(String paramString)
    throws RemoteException;

  public abstract void onTurnBasedMatchRemoved(String paramString)
    throws RemoteException;

  public abstract void p(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void q(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void r(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void s(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void t(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void u(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void v(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void w(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void x(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void y(DataHolder paramDataHolder)
    throws RemoteException;

  public abstract void z(DataHolder paramDataHolder)
    throws RemoteException;

  public static abstract class a extends Binder
    implements ga
  {
    public a()
    {
      attachInterface(this, "com.google.android.gms.games.internal.IGamesCallbacks");
    }

    public static ga I(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.games.internal.IGamesCallbacks");
      if ((localIInterface != null) && ((localIInterface instanceof ga)))
        return (ga)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.games.internal.IGamesCallbacks");
        return true;
      case 5001:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        d(paramParcel1.readInt(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5002:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i39 = paramParcel1.readInt();
        DataHolder localDataHolder39 = null;
        if (i39 != 0)
          localDataHolder39 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        b(localDataHolder39);
        paramParcel2.writeNoException();
        return true;
      case 5003:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        e(paramParcel1.readInt(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5004:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i38 = paramParcel1.readInt();
        DataHolder localDataHolder38 = null;
        if (i38 != 0)
          localDataHolder38 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        c(localDataHolder38);
        paramParcel2.writeNoException();
        return true;
      case 5005:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        if (paramParcel1.readInt() != 0);
        for (DataHolder localDataHolder36 = DataHolder.CREATOR.createFromParcel(paramParcel1); ; localDataHolder36 = null)
        {
          int i37 = paramParcel1.readInt();
          DataHolder localDataHolder37 = null;
          if (i37 != 0)
            localDataHolder37 = DataHolder.CREATOR.createFromParcel(paramParcel1);
          a(localDataHolder36, localDataHolder37);
          paramParcel2.writeNoException();
          return true;
        }
      case 5006:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i36 = paramParcel1.readInt();
        DataHolder localDataHolder35 = null;
        if (i36 != 0)
          localDataHolder35 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        d(localDataHolder35);
        paramParcel2.writeNoException();
        return true;
      case 5007:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i35 = paramParcel1.readInt();
        DataHolder localDataHolder34 = null;
        if (i35 != 0)
          localDataHolder34 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        e(localDataHolder34);
        paramParcel2.writeNoException();
        return true;
      case 5008:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i34 = paramParcel1.readInt();
        DataHolder localDataHolder33 = null;
        if (i34 != 0)
          localDataHolder33 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        f(localDataHolder33);
        paramParcel2.writeNoException();
        return true;
      case 5009:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i33 = paramParcel1.readInt();
        DataHolder localDataHolder32 = null;
        if (i33 != 0)
          localDataHolder32 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        g(localDataHolder32);
        paramParcel2.writeNoException();
        return true;
      case 5010:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i32 = paramParcel1.readInt();
        DataHolder localDataHolder31 = null;
        if (i32 != 0)
          localDataHolder31 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        h(localDataHolder31);
        paramParcel2.writeNoException();
        return true;
      case 5011:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i31 = paramParcel1.readInt();
        DataHolder localDataHolder30 = null;
        if (i31 != 0)
          localDataHolder30 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        i(localDataHolder30);
        paramParcel2.writeNoException();
        return true;
      case 5016:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        cM();
        paramParcel2.writeNoException();
        return true;
      case 5017:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i30 = paramParcel1.readInt();
        DataHolder localDataHolder29 = null;
        if (i30 != 0)
          localDataHolder29 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        k(localDataHolder29);
        paramParcel2.writeNoException();
        return true;
      case 5037:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i29 = paramParcel1.readInt();
        DataHolder localDataHolder28 = null;
        if (i29 != 0)
          localDataHolder28 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        l(localDataHolder28);
        paramParcel2.writeNoException();
        return true;
      case 5018:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i28 = paramParcel1.readInt();
        DataHolder localDataHolder27 = null;
        if (i28 != 0)
          localDataHolder27 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        s(localDataHolder27);
        paramParcel2.writeNoException();
        return true;
      case 5019:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i27 = paramParcel1.readInt();
        DataHolder localDataHolder26 = null;
        if (i27 != 0)
          localDataHolder26 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        t(localDataHolder26);
        paramParcel2.writeNoException();
        return true;
      case 5020:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        onLeftRoom(paramParcel1.readInt(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5021:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i26 = paramParcel1.readInt();
        DataHolder localDataHolder25 = null;
        if (i26 != 0)
          localDataHolder25 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        u(localDataHolder25);
        paramParcel2.writeNoException();
        return true;
      case 5022:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i25 = paramParcel1.readInt();
        DataHolder localDataHolder24 = null;
        if (i25 != 0)
          localDataHolder24 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        v(localDataHolder24);
        paramParcel2.writeNoException();
        return true;
      case 5023:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i24 = paramParcel1.readInt();
        DataHolder localDataHolder23 = null;
        if (i24 != 0)
          localDataHolder23 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        w(localDataHolder23);
        paramParcel2.writeNoException();
        return true;
      case 5024:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i23 = paramParcel1.readInt();
        DataHolder localDataHolder22 = null;
        if (i23 != 0)
          localDataHolder22 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        x(localDataHolder22);
        paramParcel2.writeNoException();
        return true;
      case 5025:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i22 = paramParcel1.readInt();
        DataHolder localDataHolder21 = null;
        if (i22 != 0)
          localDataHolder21 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        y(localDataHolder21);
        paramParcel2.writeNoException();
        return true;
      case 5026:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i21 = paramParcel1.readInt();
        DataHolder localDataHolder20 = null;
        if (i21 != 0)
          localDataHolder20 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        a(localDataHolder20, paramParcel1.createStringArray());
        paramParcel2.writeNoException();
        return true;
      case 5027:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i20 = paramParcel1.readInt();
        DataHolder localDataHolder19 = null;
        if (i20 != 0)
          localDataHolder19 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        b(localDataHolder19, paramParcel1.createStringArray());
        paramParcel2.writeNoException();
        return true;
      case 5028:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i19 = paramParcel1.readInt();
        DataHolder localDataHolder18 = null;
        if (i19 != 0)
          localDataHolder18 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        c(localDataHolder18, paramParcel1.createStringArray());
        paramParcel2.writeNoException();
        return true;
      case 5029:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i18 = paramParcel1.readInt();
        DataHolder localDataHolder17 = null;
        if (i18 != 0)
          localDataHolder17 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        d(localDataHolder17, paramParcel1.createStringArray());
        paramParcel2.writeNoException();
        return true;
      case 5030:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i17 = paramParcel1.readInt();
        DataHolder localDataHolder16 = null;
        if (i17 != 0)
          localDataHolder16 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        e(localDataHolder16, paramParcel1.createStringArray());
        paramParcel2.writeNoException();
        return true;
      case 5031:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i16 = paramParcel1.readInt();
        DataHolder localDataHolder15 = null;
        if (i16 != 0)
          localDataHolder15 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        f(localDataHolder15, paramParcel1.createStringArray());
        paramParcel2.writeNoException();
        return true;
      case 5032:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i15 = paramParcel1.readInt();
        RealTimeMessage localRealTimeMessage = null;
        if (i15 != 0)
          localRealTimeMessage = (RealTimeMessage)RealTimeMessage.CREATOR.createFromParcel(paramParcel1);
        onRealTimeMessageReceived(localRealTimeMessage);
        paramParcel2.writeNoException();
        return true;
      case 5033:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        b(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5034:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i14 = paramParcel1.readInt();
        String str = paramParcel1.readString();
        if (paramParcel1.readInt() != 0);
        for (boolean bool = true; ; bool = false)
        {
          a(i14, str, bool);
          paramParcel2.writeNoException();
          return true;
        }
      case 5038:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i13 = paramParcel1.readInt();
        DataHolder localDataHolder14 = null;
        if (i13 != 0)
          localDataHolder14 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        z(localDataHolder14);
        paramParcel2.writeNoException();
        return true;
      case 5035:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i12 = paramParcel1.readInt();
        DataHolder localDataHolder13 = null;
        if (i12 != 0)
          localDataHolder13 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        A(localDataHolder13);
        paramParcel2.writeNoException();
        return true;
      case 5036:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        aR(paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 5039:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i11 = paramParcel1.readInt();
        DataHolder localDataHolder12 = null;
        if (i11 != 0)
          localDataHolder12 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        B(localDataHolder12);
        paramParcel2.writeNoException();
        return true;
      case 5040:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        aS(paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 6001:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        onP2PConnected(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 6002:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        onP2PDisconnected(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8001:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i10 = paramParcel1.readInt();
        DataHolder localDataHolder11 = null;
        if (i10 != 0)
          localDataHolder11 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        C(localDataHolder11);
        paramParcel2.writeNoException();
        return true;
      case 8002:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i8 = paramParcel1.readInt();
        int i9 = paramParcel1.readInt();
        Bundle localBundle2 = null;
        if (i9 != 0)
          localBundle2 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        a(i8, localBundle2);
        paramParcel2.writeNoException();
        return true;
      case 8003:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i7 = paramParcel1.readInt();
        DataHolder localDataHolder10 = null;
        if (i7 != 0)
          localDataHolder10 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        n(localDataHolder10);
        paramParcel2.writeNoException();
        return true;
      case 8004:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i6 = paramParcel1.readInt();
        DataHolder localDataHolder9 = null;
        if (i6 != 0)
          localDataHolder9 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        o(localDataHolder9);
        paramParcel2.writeNoException();
        return true;
      case 8005:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i5 = paramParcel1.readInt();
        DataHolder localDataHolder8 = null;
        if (i5 != 0)
          localDataHolder8 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        p(localDataHolder8);
        paramParcel2.writeNoException();
        return true;
      case 8006:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i4 = paramParcel1.readInt();
        DataHolder localDataHolder7 = null;
        if (i4 != 0)
          localDataHolder7 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        q(localDataHolder7);
        paramParcel2.writeNoException();
        return true;
      case 8007:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        f(paramParcel1.readInt(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8008:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i3 = paramParcel1.readInt();
        DataHolder localDataHolder6 = null;
        if (i3 != 0)
          localDataHolder6 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        r(localDataHolder6);
        paramParcel2.writeNoException();
        return true;
      case 8009:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        onTurnBasedMatchRemoved(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8010:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        onInvitationRemoved(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 9001:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i2 = paramParcel1.readInt();
        DataHolder localDataHolder5 = null;
        if (i2 != 0)
          localDataHolder5 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        j(localDataHolder5);
        paramParcel2.writeNoException();
        return true;
      case 10001:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int i1 = paramParcel1.readInt();
        DataHolder localDataHolder4 = null;
        if (i1 != 0)
          localDataHolder4 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        m(localDataHolder4);
        paramParcel2.writeNoException();
        return true;
      case 10002:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        onRequestRemoved(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 10003:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int n = paramParcel1.readInt();
        DataHolder localDataHolder3 = null;
        if (n != 0)
          localDataHolder3 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        D(localDataHolder3);
        paramParcel2.writeNoException();
        return true;
      case 10004:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int m = paramParcel1.readInt();
        DataHolder localDataHolder2 = null;
        if (m != 0)
          localDataHolder2 = DataHolder.CREATOR.createFromParcel(paramParcel1);
        E(localDataHolder2);
        paramParcel2.writeNoException();
        return true;
      case 10005:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        int j = paramParcel1.readInt();
        int k = paramParcel1.readInt();
        Bundle localBundle1 = null;
        if (k != 0)
          localBundle1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        b(j, localBundle1);
        paramParcel2.writeNoException();
        return true;
      case 10006:
      }
      paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
      int i = paramParcel1.readInt();
      DataHolder localDataHolder1 = null;
      if (i != 0)
        localDataHolder1 = DataHolder.CREATOR.createFromParcel(paramParcel1);
      F(localDataHolder1);
      paramParcel2.writeNoException();
      return true;
    }

    private static class a
      implements ga
    {
      private IBinder ky;

      a(IBinder paramIBinder)
      {
        this.ky = paramIBinder;
      }

      public void A(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(5035, localParcel1, localParcel2, 0);
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

      public void B(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(5039, localParcel1, localParcel2, 0);
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

      public void C(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(8001, localParcel1, localParcel2, 0);
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

      public void D(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(10003, localParcel1, localParcel2, 0);
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

      public void E(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(10004, localParcel1, localParcel2, 0);
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

      public void F(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(10006, localParcel1, localParcel2, 0);
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

      public void a(int paramInt, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          localParcel1.writeInt(paramInt);
          if (paramBundle != null)
          {
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(8002, localParcel1, localParcel2, 0);
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

      public void a(int paramInt, String paramString, boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          localParcel1.writeInt(paramInt);
          localParcel1.writeString(paramString);
          int i = 0;
          if (paramBoolean)
            i = 1;
          localParcel1.writeInt(i);
          this.ky.transact(5034, localParcel1, localParcel2, 0);
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

      public void a(DataHolder paramDataHolder1, DataHolder paramDataHolder2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (paramDataHolder1 == null)
              continue;
            localParcel1.writeInt(1);
            paramDataHolder1.writeToParcel(localParcel1, 0);
            if (paramDataHolder2 != null)
            {
              localParcel1.writeInt(1);
              paramDataHolder2.writeToParcel(localParcel1, 0);
              this.ky.transact(5005, localParcel1, localParcel2, 0);
              localParcel2.readException();
              return;
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

      public void a(DataHolder paramDataHolder, String[] paramArrayOfString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            localParcel1.writeStringArray(paramArrayOfString);
            this.ky.transact(5026, localParcel1, localParcel2, 0);
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

      public void aR(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
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

      public void aS(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          localParcel1.writeInt(paramInt);
          this.ky.transact(5040, localParcel1, localParcel2, 0);
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

      public void b(int paramInt1, int paramInt2, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          localParcel1.writeString(paramString);
          this.ky.transact(5033, localParcel1, localParcel2, 0);
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

      public void b(int paramInt, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          localParcel1.writeInt(paramInt);
          if (paramBundle != null)
          {
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(10005, localParcel1, localParcel2, 0);
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

      public void b(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(5002, localParcel1, localParcel2, 0);
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

      public void b(DataHolder paramDataHolder, String[] paramArrayOfString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            localParcel1.writeStringArray(paramArrayOfString);
            this.ky.transact(5027, localParcel1, localParcel2, 0);
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

      public void c(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(5004, localParcel1, localParcel2, 0);
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

      public void c(DataHolder paramDataHolder, String[] paramArrayOfString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            localParcel1.writeStringArray(paramArrayOfString);
            this.ky.transact(5028, localParcel1, localParcel2, 0);
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

      public void cM()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          this.ky.transact(5016, localParcel1, localParcel2, 0);
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

      public void d(int paramInt, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          localParcel1.writeInt(paramInt);
          localParcel1.writeString(paramString);
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

      public void d(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(5006, localParcel1, localParcel2, 0);
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

      public void d(DataHolder paramDataHolder, String[] paramArrayOfString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            localParcel1.writeStringArray(paramArrayOfString);
            this.ky.transact(5029, localParcel1, localParcel2, 0);
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

      public void e(int paramInt, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          localParcel1.writeInt(paramInt);
          localParcel1.writeString(paramString);
          this.ky.transact(5003, localParcel1, localParcel2, 0);
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

      public void e(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(5007, localParcel1, localParcel2, 0);
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

      public void e(DataHolder paramDataHolder, String[] paramArrayOfString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            localParcel1.writeStringArray(paramArrayOfString);
            this.ky.transact(5030, localParcel1, localParcel2, 0);
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

      public void f(int paramInt, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          localParcel1.writeInt(paramInt);
          localParcel1.writeString(paramString);
          this.ky.transact(8007, localParcel1, localParcel2, 0);
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

      public void f(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(5008, localParcel1, localParcel2, 0);
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

      public void f(DataHolder paramDataHolder, String[] paramArrayOfString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            localParcel1.writeStringArray(paramArrayOfString);
            this.ky.transact(5031, localParcel1, localParcel2, 0);
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

      public void g(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(5009, localParcel1, localParcel2, 0);
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

      public void h(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(5010, localParcel1, localParcel2, 0);
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

      public void i(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(5011, localParcel1, localParcel2, 0);
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

      public void j(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(9001, localParcel1, localParcel2, 0);
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

      public void k(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(5017, localParcel1, localParcel2, 0);
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

      public void l(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(5037, localParcel1, localParcel2, 0);
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

      public void m(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(10001, localParcel1, localParcel2, 0);
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

      public void n(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(8003, localParcel1, localParcel2, 0);
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

      public void o(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(8004, localParcel1, localParcel2, 0);
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

      public void onInvitationRemoved(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          localParcel1.writeString(paramString);
          this.ky.transact(8010, localParcel1, localParcel2, 0);
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

      public void onLeftRoom(int paramInt, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          localParcel1.writeInt(paramInt);
          localParcel1.writeString(paramString);
          this.ky.transact(5020, localParcel1, localParcel2, 0);
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

      public void onP2PConnected(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          localParcel1.writeString(paramString);
          this.ky.transact(6001, localParcel1, localParcel2, 0);
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

      public void onP2PDisconnected(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          localParcel1.writeString(paramString);
          this.ky.transact(6002, localParcel1, localParcel2, 0);
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

      public void onRealTimeMessageReceived(RealTimeMessage paramRealTimeMessage)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramRealTimeMessage != null)
          {
            localParcel1.writeInt(1);
            paramRealTimeMessage.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(5032, localParcel1, localParcel2, 0);
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

      public void onRequestRemoved(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          localParcel1.writeString(paramString);
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

      public void onTurnBasedMatchRemoved(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          localParcel1.writeString(paramString);
          this.ky.transact(8009, localParcel1, localParcel2, 0);
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

      public void p(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(8005, localParcel1, localParcel2, 0);
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

      public void q(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(8006, localParcel1, localParcel2, 0);
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

      public void r(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(8008, localParcel1, localParcel2, 0);
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

      public void s(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(5018, localParcel1, localParcel2, 0);
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

      public void t(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(5019, localParcel1, localParcel2, 0);
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

      public void u(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(5021, localParcel1, localParcel2, 0);
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

      public void v(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(5022, localParcel1, localParcel2, 0);
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

      public void w(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(5023, localParcel1, localParcel2, 0);
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

      public void x(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(5024, localParcel1, localParcel2, 0);
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

      public void y(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(5025, localParcel1, localParcel2, 0);
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

      public void z(DataHolder paramDataHolder)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          if (paramDataHolder != null)
          {
            localParcel1.writeInt(1);
            paramDataHolder.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(5038, localParcel1, localParcel2, 0);
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
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ga
 * JD-Core Version:    0.6.0
 */