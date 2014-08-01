package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationRequestCreator;
import com.google.android.gms.location.a;
import com.google.android.gms.location.a.a;
import com.google.android.gms.location.b;
import com.google.android.gms.location.c;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLngBoundsCreator;
import com.google.android.gms.maps.model.LatLngCreator;
import java.util.ArrayList;
import java.util.List;

public abstract interface hg extends IInterface
{
  public abstract void a(long paramLong, boolean paramBoolean, PendingIntent paramPendingIntent)
    throws RemoteException;

  public abstract void a(PendingIntent paramPendingIntent)
    throws RemoteException;

  public abstract void a(PendingIntent paramPendingIntent, hf paramhf, String paramString)
    throws RemoteException;

  public abstract void a(Location paramLocation, int paramInt)
    throws RemoteException;

  public abstract void a(hf paramhf, String paramString)
    throws RemoteException;

  public abstract void a(hn paramhn, id paramid, hv paramhv)
    throws RemoteException;

  public abstract void a(hp paramhp, id paramid)
    throws RemoteException;

  public abstract void a(hr paramhr, id paramid, PendingIntent paramPendingIntent)
    throws RemoteException;

  public abstract void a(id paramid, PendingIntent paramPendingIntent)
    throws RemoteException;

  public abstract void a(LocationRequest paramLocationRequest, PendingIntent paramPendingIntent)
    throws RemoteException;

  public abstract void a(LocationRequest paramLocationRequest, a parama)
    throws RemoteException;

  public abstract void a(LocationRequest paramLocationRequest, a parama, String paramString)
    throws RemoteException;

  public abstract void a(a parama)
    throws RemoteException;

  public abstract void a(LatLng paramLatLng, hn paramhn, id paramid, hv paramhv)
    throws RemoteException;

  public abstract void a(LatLngBounds paramLatLngBounds, int paramInt, hn paramhn, id paramid, hv paramhv)
    throws RemoteException;

  public abstract void a(String paramString, id paramid, hv paramhv)
    throws RemoteException;

  public abstract void a(List<hx.a> paramList)
    throws RemoteException;

  public abstract void a(List<hj> paramList, PendingIntent paramPendingIntent, hf paramhf, String paramString)
    throws RemoteException;

  public abstract void a(String[] paramArrayOfString, hf paramhf, String paramString)
    throws RemoteException;

  public abstract Location aF(String paramString)
    throws RemoteException;

  public abstract b aG(String paramString)
    throws RemoteException;

  public abstract void b(String paramString, id paramid, hv paramhv)
    throws RemoteException;

  public abstract Location gk()
    throws RemoteException;

  public abstract void removeActivityUpdates(PendingIntent paramPendingIntent)
    throws RemoteException;

  public abstract void setMockLocation(Location paramLocation)
    throws RemoteException;

  public abstract void setMockMode(boolean paramBoolean)
    throws RemoteException;

  public static abstract class a extends Binder
    implements hg
  {
    public static hg P(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
      if ((localIInterface != null) && ((localIInterface instanceof hg)))
        return (hg)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        return true;
      case 1:
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        ArrayList localArrayList = paramParcel1.createTypedArrayList(hj.CREATOR);
        int i15 = paramParcel1.readInt();
        PendingIntent localPendingIntent8 = null;
        if (i15 != 0)
          localPendingIntent8 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);
        a(localArrayList, localPendingIntent8, hf.a.O(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 2:
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        int i14 = paramParcel1.readInt();
        PendingIntent localPendingIntent7 = null;
        if (i14 != 0)
          localPendingIntent7 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);
        a(localPendingIntent7, hf.a.O(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 3:
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        a(paramParcel1.createStringArray(), hf.a.O(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 4:
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        a(hf.a.O(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5:
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        long l = paramParcel1.readLong();
        int i12 = paramParcel1.readInt();
        boolean bool2 = false;
        if (i12 != 0)
          bool2 = true;
        int i13 = paramParcel1.readInt();
        PendingIntent localPendingIntent6 = null;
        if (i13 != 0)
          localPendingIntent6 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);
        a(l, bool2, localPendingIntent6);
        paramParcel2.writeNoException();
        return true;
      case 6:
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        int i11 = paramParcel1.readInt();
        PendingIntent localPendingIntent5 = null;
        if (i11 != 0)
          localPendingIntent5 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);
        removeActivityUpdates(localPendingIntent5);
        paramParcel2.writeNoException();
        return true;
      case 7:
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        Location localLocation4 = gk();
        paramParcel2.writeNoException();
        if (localLocation4 != null)
        {
          paramParcel2.writeInt(1);
          localLocation4.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 8:
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        int i10 = paramParcel1.readInt();
        LocationRequest localLocationRequest3 = null;
        if (i10 != 0)
          localLocationRequest3 = LocationRequest.CREATOR.createFromParcel(paramParcel1);
        a(localLocationRequest3, a.a.N(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 9:
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        if (paramParcel1.readInt() != 0);
        for (LocationRequest localLocationRequest2 = LocationRequest.CREATOR.createFromParcel(paramParcel1); ; localLocationRequest2 = null)
        {
          int i9 = paramParcel1.readInt();
          PendingIntent localPendingIntent4 = null;
          if (i9 != 0)
            localPendingIntent4 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);
          a(localLocationRequest2, localPendingIntent4);
          paramParcel2.writeNoException();
          return true;
        }
      case 10:
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        a(a.a.N(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 11:
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        int i8 = paramParcel1.readInt();
        PendingIntent localPendingIntent3 = null;
        if (i8 != 0)
          localPendingIntent3 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);
        a(localPendingIntent3);
        paramParcel2.writeNoException();
        return true;
      case 12:
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        int i7 = paramParcel1.readInt();
        boolean bool1 = false;
        if (i7 != 0)
          bool1 = true;
        setMockMode(bool1);
        paramParcel2.writeNoException();
        return true;
      case 13:
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        int i6 = paramParcel1.readInt();
        Location localLocation3 = null;
        if (i6 != 0)
          localLocation3 = (Location)Location.CREATOR.createFromParcel(paramParcel1);
        setMockLocation(localLocation3);
        paramParcel2.writeNoException();
        return true;
      case 14:
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        LatLngBounds localLatLngBounds;
        int i5;
        hn localhn3;
        if (paramParcel1.readInt() != 0)
        {
          localLatLngBounds = LatLngBounds.CREATOR.createFromParcel(paramParcel1);
          i5 = paramParcel1.readInt();
          if (paramParcel1.readInt() == 0)
            break label949;
          localhn3 = hn.CREATOR.aw(paramParcel1);
          if (paramParcel1.readInt() == 0)
            break label955;
        }
        for (id localid8 = id.CREATOR.aD(paramParcel1); ; localid8 = null)
        {
          a(localLatLngBounds, i5, localhn3, localid8, hv.a.R(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
          localLatLngBounds = null;
          break;
          localhn3 = null;
          break label902;
        }
      case 15:
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        String str2 = paramParcel1.readString();
        int i4 = paramParcel1.readInt();
        id localid7 = null;
        if (i4 != 0)
          localid7 = id.CREATOR.aD(paramParcel1);
        a(str2, localid7, hv.a.R(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 16:
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        LatLng localLatLng;
        if (paramParcel1.readInt() != 0)
        {
          localLatLng = LatLng.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0)
            break label1107;
        }
        for (hn localhn2 = hn.CREATOR.aw(paramParcel1); ; localhn2 = null)
        {
          int i3 = paramParcel1.readInt();
          id localid6 = null;
          if (i3 != 0)
            localid6 = id.CREATOR.aD(paramParcel1);
          a(localLatLng, localhn2, localid6, hv.a.R(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
          localLatLng = null;
          break;
        }
      case 17:
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        if (paramParcel1.readInt() != 0);
        for (hn localhn1 = hn.CREATOR.aw(paramParcel1); ; localhn1 = null)
        {
          int i2 = paramParcel1.readInt();
          id localid5 = null;
          if (i2 != 0)
            localid5 = id.CREATOR.aD(paramParcel1);
          a(localhn1, localid5, hv.a.R(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        }
      case 42:
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        String str1 = paramParcel1.readString();
        int i1 = paramParcel1.readInt();
        id localid4 = null;
        if (i1 != 0)
          localid4 = id.CREATOR.aD(paramParcel1);
        b(str1, localid4, hv.a.R(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 18:
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        hr localhr;
        if (paramParcel1.readInt() != 0)
        {
          localhr = hr.CREATOR.ay(paramParcel1);
          if (paramParcel1.readInt() == 0)
            break label1329;
        }
        for (id localid3 = id.CREATOR.aD(paramParcel1); ; localid3 = null)
        {
          int n = paramParcel1.readInt();
          PendingIntent localPendingIntent2 = null;
          if (n != 0)
            localPendingIntent2 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);
          a(localhr, localid3, localPendingIntent2);
          paramParcel2.writeNoException();
          return true;
          localhr = null;
          break;
        }
      case 19:
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        if (paramParcel1.readInt() != 0);
        for (id localid2 = id.CREATOR.aD(paramParcel1); ; localid2 = null)
        {
          int m = paramParcel1.readInt();
          PendingIntent localPendingIntent1 = null;
          if (m != 0)
            localPendingIntent1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);
          a(localid2, localPendingIntent1);
          paramParcel2.writeNoException();
          return true;
        }
      case 20:
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        int k = paramParcel1.readInt();
        LocationRequest localLocationRequest1 = null;
        if (k != 0)
          localLocationRequest1 = LocationRequest.CREATOR.createFromParcel(paramParcel1);
        a(localLocationRequest1, a.a.N(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 21:
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        Location localLocation2 = aF(paramParcel1.readString());
        paramParcel2.writeNoException();
        if (localLocation2 != null)
        {
          paramParcel2.writeInt(1);
          localLocation2.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 24:
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        a(paramParcel1.createTypedArrayList(hx.a.CREATOR));
        return true;
      case 25:
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        if (paramParcel1.readInt() != 0);
        for (hp localhp = hp.CREATOR.ax(paramParcel1); ; localhp = null)
        {
          int j = paramParcel1.readInt();
          id localid1 = null;
          if (j != 0)
            localid1 = id.CREATOR.aD(paramParcel1);
          a(localhp, localid1);
          return true;
        }
      case 26:
        label902: label949: label955: label1107: paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        label1329: int i = paramParcel1.readInt();
        Location localLocation1 = null;
        if (i != 0)
          localLocation1 = (Location)Location.CREATOR.createFromParcel(paramParcel1);
        a(localLocation1, paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 34:
      }
      paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
      b localb = aG(paramParcel1.readString());
      paramParcel2.writeNoException();
      if (localb != null)
      {
        paramParcel2.writeInt(1);
        localb.writeToParcel(paramParcel2, 1);
        return true;
      }
      paramParcel2.writeInt(0);
      return true;
    }

    private static class a
      implements hg
    {
      private IBinder ky;

      a(IBinder paramIBinder)
      {
        this.ky = paramIBinder;
      }

      public void a(long paramLong, boolean paramBoolean, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          localParcel1.writeLong(paramLong);
          if (paramBoolean)
          {
            localParcel1.writeInt(i);
            if (paramPendingIntent == null)
              break label94;
            localParcel1.writeInt(1);
            paramPendingIntent.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(5, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            i = 0;
            break;
            label94: localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void a(PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          if (paramPendingIntent != null)
          {
            localParcel1.writeInt(1);
            paramPendingIntent.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(11, localParcel1, localParcel2, 0);
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

      public void a(PendingIntent paramPendingIntent, hf paramhf, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramPendingIntent == null)
              continue;
            localParcel1.writeInt(1);
            paramPendingIntent.writeToParcel(localParcel1, 0);
            if (paramhf != null)
            {
              localIBinder = paramhf.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
              localParcel1.writeString(paramString);
              this.ky.transact(2, localParcel1, localParcel2, 0);
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
          IBinder localIBinder = null;
        }
      }

      public void a(Location paramLocation, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          if (paramLocation != null)
          {
            localParcel1.writeInt(1);
            paramLocation.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            localParcel1.writeInt(paramInt);
            this.ky.transact(26, localParcel1, localParcel2, 0);
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

      public void a(hf paramhf, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          if (paramhf != null);
          for (IBinder localIBinder = paramhf.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
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

      public void a(hn paramhn, id paramid, hv paramhv)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramhn == null)
              continue;
            localParcel1.writeInt(1);
            paramhn.writeToParcel(localParcel1, 0);
            if (paramid != null)
            {
              localParcel1.writeInt(1);
              paramid.writeToParcel(localParcel1, 0);
              if (paramhv == null)
                break label136;
              localIBinder = paramhv.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
              this.ky.transact(17, localParcel1, localParcel2, 0);
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
          continue;
          label136: IBinder localIBinder = null;
        }
      }

      public void a(hp paramhp, id paramid)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramhp == null)
              continue;
            localParcel.writeInt(1);
            paramhp.writeToParcel(localParcel, 0);
            if (paramid != null)
            {
              localParcel.writeInt(1);
              paramid.writeToParcel(localParcel, 0);
              this.ky.transact(25, localParcel, null, 1);
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

      public void a(hr paramhr, id paramid, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramhr == null)
              continue;
            localParcel1.writeInt(1);
            paramhr.writeToParcel(localParcel1, 0);
            if (paramid != null)
            {
              localParcel1.writeInt(1);
              paramid.writeToParcel(localParcel1, 0);
              if (paramPendingIntent == null)
                break label134;
              localParcel1.writeInt(1);
              paramPendingIntent.writeToParcel(localParcel1, 0);
              this.ky.transact(18, localParcel1, localParcel2, 0);
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
          continue;
          label134: localParcel1.writeInt(0);
        }
      }

      public void a(id paramid, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramid == null)
              continue;
            localParcel1.writeInt(1);
            paramid.writeToParcel(localParcel1, 0);
            if (paramPendingIntent != null)
            {
              localParcel1.writeInt(1);
              paramPendingIntent.writeToParcel(localParcel1, 0);
              this.ky.transact(19, localParcel1, localParcel2, 0);
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

      public void a(LocationRequest paramLocationRequest, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramLocationRequest == null)
              continue;
            localParcel1.writeInt(1);
            paramLocationRequest.writeToParcel(localParcel1, 0);
            if (paramPendingIntent != null)
            {
              localParcel1.writeInt(1);
              paramPendingIntent.writeToParcel(localParcel1, 0);
              this.ky.transact(9, localParcel1, localParcel2, 0);
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

      public void a(LocationRequest paramLocationRequest, a parama)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramLocationRequest == null)
              continue;
            localParcel1.writeInt(1);
            paramLocationRequest.writeToParcel(localParcel1, 0);
            if (parama != null)
            {
              localIBinder = parama.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
              this.ky.transact(8, localParcel1, localParcel2, 0);
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
          IBinder localIBinder = null;
        }
      }

      public void a(LocationRequest paramLocationRequest, a parama, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramLocationRequest == null)
              continue;
            localParcel1.writeInt(1);
            paramLocationRequest.writeToParcel(localParcel1, 0);
            if (parama != null)
            {
              localIBinder = parama.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
              localParcel1.writeString(paramString);
              this.ky.transact(20, localParcel1, localParcel2, 0);
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
          IBinder localIBinder = null;
        }
      }

      public void a(a parama)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          if (parama != null);
          for (IBinder localIBinder = parama.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
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

      public void a(LatLng paramLatLng, hn paramhn, id paramid, hv paramhv)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramLatLng == null)
              continue;
            localParcel1.writeInt(1);
            paramLatLng.writeToParcel(localParcel1, 0);
            if (paramhn != null)
            {
              localParcel1.writeInt(1);
              paramhn.writeToParcel(localParcel1, 0);
              if (paramid == null)
                break label155;
              localParcel1.writeInt(1);
              paramid.writeToParcel(localParcel1, 0);
              if (paramhv == null)
                break label164;
              localIBinder = paramhv.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
              this.ky.transact(16, localParcel1, localParcel2, 0);
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
          continue;
          label155: localParcel1.writeInt(0);
          continue;
          label164: IBinder localIBinder = null;
        }
      }

      public void a(LatLngBounds paramLatLngBounds, int paramInt, hn paramhn, id paramid, hv paramhv)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramLatLngBounds == null)
              continue;
            localParcel1.writeInt(1);
            paramLatLngBounds.writeToParcel(localParcel1, 0);
            localParcel1.writeInt(paramInt);
            if (paramhn != null)
            {
              localParcel1.writeInt(1);
              paramhn.writeToParcel(localParcel1, 0);
              if (paramid == null)
                break label163;
              localParcel1.writeInt(1);
              paramid.writeToParcel(localParcel1, 0);
              if (paramhv == null)
                break label172;
              localIBinder = paramhv.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
              this.ky.transact(14, localParcel1, localParcel2, 0);
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
          continue;
          label163: localParcel1.writeInt(0);
          continue;
          label172: IBinder localIBinder = null;
        }
      }

      public void a(String paramString, id paramid, hv paramhv)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            localParcel1.writeString(paramString);
            if (paramid == null)
              continue;
            localParcel1.writeInt(1);
            paramid.writeToParcel(localParcel1, 0);
            if (paramhv != null)
            {
              localIBinder = paramhv.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
              this.ky.transact(15, localParcel1, localParcel2, 0);
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
          IBinder localIBinder = null;
        }
      }

      public void a(List<hx.a> paramList)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          localParcel.writeTypedList(paramList);
          this.ky.transact(24, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void a(List<hj> paramList, PendingIntent paramPendingIntent, hf paramhf, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            localParcel1.writeTypedList(paramList);
            if (paramPendingIntent == null)
              continue;
            localParcel1.writeInt(1);
            paramPendingIntent.writeToParcel(localParcel1, 0);
            if (paramhf != null)
            {
              localIBinder = paramhf.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
              localParcel1.writeString(paramString);
              this.ky.transact(1, localParcel1, localParcel2, 0);
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
          IBinder localIBinder = null;
        }
      }

      public void a(String[] paramArrayOfString, hf paramhf, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          localParcel1.writeStringArray(paramArrayOfString);
          if (paramhf != null);
          for (IBinder localIBinder = paramhf.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
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

      public Location aF(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          localParcel1.writeString(paramString);
          this.ky.transact(21, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0)
          {
            localLocation = (Location)Location.CREATOR.createFromParcel(localParcel2);
            return localLocation;
          }
          Location localLocation = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public b aG(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          localParcel1.writeString(paramString);
          this.ky.transact(34, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0)
          {
            b localb2 = b.CREATOR.au(localParcel2);
            localb1 = localb2;
            return localb1;
          }
          b localb1 = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public IBinder asBinder()
      {
        return this.ky;
      }

      public void b(String paramString, id paramid, hv paramhv)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            localParcel1.writeString(paramString);
            if (paramid == null)
              continue;
            localParcel1.writeInt(1);
            paramid.writeToParcel(localParcel1, 0);
            if (paramhv != null)
            {
              localIBinder = paramhv.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
              this.ky.transact(42, localParcel1, localParcel2, 0);
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
          IBinder localIBinder = null;
        }
      }

      public Location gk()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          this.ky.transact(7, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0)
          {
            localLocation = (Location)Location.CREATOR.createFromParcel(localParcel2);
            return localLocation;
          }
          Location localLocation = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void removeActivityUpdates(PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          if (paramPendingIntent != null)
          {
            localParcel1.writeInt(1);
            paramPendingIntent.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(6, localParcel1, localParcel2, 0);
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

      public void setMockLocation(Location paramLocation)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          if (paramLocation != null)
          {
            localParcel1.writeInt(1);
            paramLocation.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(13, localParcel1, localParcel2, 0);
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

      public void setMockMode(boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          int i = 0;
          if (paramBoolean)
            i = 1;
          localParcel1.writeInt(i);
          this.ky.transact(12, localParcel1, localParcel2, 0);
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
 * Qualified Name:     com.google.android.gms.internal.hg
 * JD-Core Version:    0.6.0
 */