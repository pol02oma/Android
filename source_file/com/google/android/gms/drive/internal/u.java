package com.google.android.gms.drive.internal;

import android.content.IntentSender;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface u extends IInterface
{
  public abstract IntentSender a(CreateFileIntentSenderRequest paramCreateFileIntentSenderRequest)
    throws RemoteException;

  public abstract IntentSender a(OpenFileIntentSenderRequest paramOpenFileIntentSenderRequest)
    throws RemoteException;

  public abstract void a(AddEventListenerRequest paramAddEventListenerRequest, w paramw, String paramString, v paramv)
    throws RemoteException;

  public abstract void a(AuthorizeAccessRequest paramAuthorizeAccessRequest, v paramv)
    throws RemoteException;

  public abstract void a(CloseContentsAndUpdateMetadataRequest paramCloseContentsAndUpdateMetadataRequest, v paramv)
    throws RemoteException;

  public abstract void a(CloseContentsRequest paramCloseContentsRequest, v paramv)
    throws RemoteException;

  public abstract void a(CreateContentsRequest paramCreateContentsRequest, v paramv)
    throws RemoteException;

  public abstract void a(CreateFileRequest paramCreateFileRequest, v paramv)
    throws RemoteException;

  public abstract void a(CreateFolderRequest paramCreateFolderRequest, v paramv)
    throws RemoteException;

  public abstract void a(DisconnectRequest paramDisconnectRequest)
    throws RemoteException;

  public abstract void a(GetMetadataRequest paramGetMetadataRequest, v paramv)
    throws RemoteException;

  public abstract void a(ListParentsRequest paramListParentsRequest, v paramv)
    throws RemoteException;

  public abstract void a(OpenContentsRequest paramOpenContentsRequest, v paramv)
    throws RemoteException;

  public abstract void a(QueryRequest paramQueryRequest, v paramv)
    throws RemoteException;

  public abstract void a(RemoveEventListenerRequest paramRemoveEventListenerRequest, w paramw, String paramString, v paramv)
    throws RemoteException;

  public abstract void a(TrashResourceRequest paramTrashResourceRequest, v paramv)
    throws RemoteException;

  public abstract void a(UpdateMetadataRequest paramUpdateMetadataRequest, v paramv)
    throws RemoteException;

  public abstract void a(v paramv)
    throws RemoteException;

  public static abstract class a extends Binder
    implements u
  {
    public static u D(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.drive.internal.IDriveService");
      if ((localIInterface != null) && ((localIInterface instanceof u)))
        return (u)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.drive.internal.IDriveService");
        return true;
      case 1:
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        int i12 = paramParcel1.readInt();
        GetMetadataRequest localGetMetadataRequest = null;
        if (i12 != 0)
          localGetMetadataRequest = (GetMetadataRequest)GetMetadataRequest.CREATOR.createFromParcel(paramParcel1);
        a(localGetMetadataRequest, v.a.E(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 2:
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        int i11 = paramParcel1.readInt();
        QueryRequest localQueryRequest = null;
        if (i11 != 0)
          localQueryRequest = (QueryRequest)QueryRequest.CREATOR.createFromParcel(paramParcel1);
        a(localQueryRequest, v.a.E(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 3:
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        int i10 = paramParcel1.readInt();
        UpdateMetadataRequest localUpdateMetadataRequest = null;
        if (i10 != 0)
          localUpdateMetadataRequest = (UpdateMetadataRequest)UpdateMetadataRequest.CREATOR.createFromParcel(paramParcel1);
        a(localUpdateMetadataRequest, v.a.E(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 4:
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        int i9 = paramParcel1.readInt();
        CreateContentsRequest localCreateContentsRequest = null;
        if (i9 != 0)
          localCreateContentsRequest = (CreateContentsRequest)CreateContentsRequest.CREATOR.createFromParcel(paramParcel1);
        a(localCreateContentsRequest, v.a.E(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 5:
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        int i8 = paramParcel1.readInt();
        CreateFileRequest localCreateFileRequest = null;
        if (i8 != 0)
          localCreateFileRequest = (CreateFileRequest)CreateFileRequest.CREATOR.createFromParcel(paramParcel1);
        a(localCreateFileRequest, v.a.E(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 6:
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        int i7 = paramParcel1.readInt();
        CreateFolderRequest localCreateFolderRequest = null;
        if (i7 != 0)
          localCreateFolderRequest = (CreateFolderRequest)CreateFolderRequest.CREATOR.createFromParcel(paramParcel1);
        a(localCreateFolderRequest, v.a.E(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 7:
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        int i6 = paramParcel1.readInt();
        OpenContentsRequest localOpenContentsRequest = null;
        if (i6 != 0)
          localOpenContentsRequest = (OpenContentsRequest)OpenContentsRequest.CREATOR.createFromParcel(paramParcel1);
        a(localOpenContentsRequest, v.a.E(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 8:
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        int i5 = paramParcel1.readInt();
        CloseContentsRequest localCloseContentsRequest = null;
        if (i5 != 0)
          localCloseContentsRequest = (CloseContentsRequest)CloseContentsRequest.CREATOR.createFromParcel(paramParcel1);
        a(localCloseContentsRequest, v.a.E(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 9:
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        a(v.a.E(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 10:
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        int i4 = paramParcel1.readInt();
        OpenFileIntentSenderRequest localOpenFileIntentSenderRequest = null;
        if (i4 != 0)
          localOpenFileIntentSenderRequest = (OpenFileIntentSenderRequest)OpenFileIntentSenderRequest.CREATOR.createFromParcel(paramParcel1);
        IntentSender localIntentSender2 = a(localOpenFileIntentSenderRequest);
        paramParcel2.writeNoException();
        if (localIntentSender2 != null)
        {
          paramParcel2.writeInt(1);
          localIntentSender2.writeToParcel(paramParcel2, 1);
        }
        while (true)
        {
          return true;
          paramParcel2.writeInt(0);
        }
      case 11:
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        int i3 = paramParcel1.readInt();
        CreateFileIntentSenderRequest localCreateFileIntentSenderRequest = null;
        if (i3 != 0)
          localCreateFileIntentSenderRequest = (CreateFileIntentSenderRequest)CreateFileIntentSenderRequest.CREATOR.createFromParcel(paramParcel1);
        IntentSender localIntentSender1 = a(localCreateFileIntentSenderRequest);
        paramParcel2.writeNoException();
        if (localIntentSender1 != null)
        {
          paramParcel2.writeInt(1);
          localIntentSender1.writeToParcel(paramParcel2, 1);
        }
        while (true)
        {
          return true;
          paramParcel2.writeInt(0);
        }
      case 12:
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        int i2 = paramParcel1.readInt();
        AuthorizeAccessRequest localAuthorizeAccessRequest = null;
        if (i2 != 0)
          localAuthorizeAccessRequest = (AuthorizeAccessRequest)AuthorizeAccessRequest.CREATOR.createFromParcel(paramParcel1);
        a(localAuthorizeAccessRequest, v.a.E(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 13:
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        int i1 = paramParcel1.readInt();
        ListParentsRequest localListParentsRequest = null;
        if (i1 != 0)
          localListParentsRequest = (ListParentsRequest)ListParentsRequest.CREATOR.createFromParcel(paramParcel1);
        a(localListParentsRequest, v.a.E(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 14:
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        int n = paramParcel1.readInt();
        AddEventListenerRequest localAddEventListenerRequest = null;
        if (n != 0)
          localAddEventListenerRequest = (AddEventListenerRequest)AddEventListenerRequest.CREATOR.createFromParcel(paramParcel1);
        a(localAddEventListenerRequest, w.a.F(paramParcel1.readStrongBinder()), paramParcel1.readString(), v.a.E(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 15:
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        int m = paramParcel1.readInt();
        RemoveEventListenerRequest localRemoveEventListenerRequest = null;
        if (m != 0)
          localRemoveEventListenerRequest = (RemoveEventListenerRequest)RemoveEventListenerRequest.CREATOR.createFromParcel(paramParcel1);
        a(localRemoveEventListenerRequest, w.a.F(paramParcel1.readStrongBinder()), paramParcel1.readString(), v.a.E(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 16:
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        int k = paramParcel1.readInt();
        DisconnectRequest localDisconnectRequest = null;
        if (k != 0)
          localDisconnectRequest = (DisconnectRequest)DisconnectRequest.CREATOR.createFromParcel(paramParcel1);
        a(localDisconnectRequest);
        paramParcel2.writeNoException();
        return true;
      case 17:
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        int j = paramParcel1.readInt();
        TrashResourceRequest localTrashResourceRequest = null;
        if (j != 0)
          localTrashResourceRequest = (TrashResourceRequest)TrashResourceRequest.CREATOR.createFromParcel(paramParcel1);
        a(localTrashResourceRequest, v.a.E(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 18:
      }
      paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
      int i = paramParcel1.readInt();
      CloseContentsAndUpdateMetadataRequest localCloseContentsAndUpdateMetadataRequest = null;
      if (i != 0)
        localCloseContentsAndUpdateMetadataRequest = (CloseContentsAndUpdateMetadataRequest)CloseContentsAndUpdateMetadataRequest.CREATOR.createFromParcel(paramParcel1);
      a(localCloseContentsAndUpdateMetadataRequest, v.a.E(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      return true;
    }

    private static class a
      implements u
    {
      private IBinder ky;

      a(IBinder paramIBinder)
      {
        this.ky = paramIBinder;
      }

      public IntentSender a(CreateFileIntentSenderRequest paramCreateFileIntentSenderRequest)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramCreateFileIntentSenderRequest == null)
              continue;
            localParcel1.writeInt(1);
            paramCreateFileIntentSenderRequest.writeToParcel(localParcel1, 0);
            this.ky.transact(11, localParcel1, localParcel2, 0);
            localParcel2.readException();
            if (localParcel2.readInt() != 0)
            {
              localIntentSender = (IntentSender)IntentSender.CREATOR.createFromParcel(localParcel2);
              return localIntentSender;
              localParcel1.writeInt(0);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          IntentSender localIntentSender = null;
        }
      }

      public IntentSender a(OpenFileIntentSenderRequest paramOpenFileIntentSenderRequest)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramOpenFileIntentSenderRequest == null)
              continue;
            localParcel1.writeInt(1);
            paramOpenFileIntentSenderRequest.writeToParcel(localParcel1, 0);
            this.ky.transact(10, localParcel1, localParcel2, 0);
            localParcel2.readException();
            if (localParcel2.readInt() != 0)
            {
              localIntentSender = (IntentSender)IntentSender.CREATOR.createFromParcel(localParcel2);
              return localIntentSender;
              localParcel1.writeInt(0);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          IntentSender localIntentSender = null;
        }
      }

      public void a(AddEventListenerRequest paramAddEventListenerRequest, w paramw, String paramString, v paramv)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramAddEventListenerRequest == null)
              continue;
            localParcel1.writeInt(1);
            paramAddEventListenerRequest.writeToParcel(localParcel1, 0);
            if (paramw != null)
            {
              localIBinder1 = paramw.asBinder();
              localParcel1.writeStrongBinder(localIBinder1);
              localParcel1.writeString(paramString);
              IBinder localIBinder2 = null;
              if (paramv == null)
                continue;
              localIBinder2 = paramv.asBinder();
              localParcel1.writeStrongBinder(localIBinder2);
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
          IBinder localIBinder1 = null;
        }
      }

      public void a(AuthorizeAccessRequest paramAuthorizeAccessRequest, v paramv)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramAuthorizeAccessRequest == null)
              continue;
            localParcel1.writeInt(1);
            paramAuthorizeAccessRequest.writeToParcel(localParcel1, 0);
            if (paramv != null)
            {
              localIBinder = paramv.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
              this.ky.transact(12, localParcel1, localParcel2, 0);
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

      public void a(CloseContentsAndUpdateMetadataRequest paramCloseContentsAndUpdateMetadataRequest, v paramv)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramCloseContentsAndUpdateMetadataRequest == null)
              continue;
            localParcel1.writeInt(1);
            paramCloseContentsAndUpdateMetadataRequest.writeToParcel(localParcel1, 0);
            if (paramv != null)
            {
              localIBinder = paramv.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
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
          IBinder localIBinder = null;
        }
      }

      public void a(CloseContentsRequest paramCloseContentsRequest, v paramv)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramCloseContentsRequest == null)
              continue;
            localParcel1.writeInt(1);
            paramCloseContentsRequest.writeToParcel(localParcel1, 0);
            if (paramv != null)
            {
              localIBinder = paramv.asBinder();
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

      public void a(CreateContentsRequest paramCreateContentsRequest, v paramv)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramCreateContentsRequest == null)
              continue;
            localParcel1.writeInt(1);
            paramCreateContentsRequest.writeToParcel(localParcel1, 0);
            if (paramv != null)
            {
              localIBinder = paramv.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
              this.ky.transact(4, localParcel1, localParcel2, 0);
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

      public void a(CreateFileRequest paramCreateFileRequest, v paramv)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramCreateFileRequest == null)
              continue;
            localParcel1.writeInt(1);
            paramCreateFileRequest.writeToParcel(localParcel1, 0);
            if (paramv != null)
            {
              localIBinder = paramv.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
              this.ky.transact(5, localParcel1, localParcel2, 0);
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

      public void a(CreateFolderRequest paramCreateFolderRequest, v paramv)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramCreateFolderRequest == null)
              continue;
            localParcel1.writeInt(1);
            paramCreateFolderRequest.writeToParcel(localParcel1, 0);
            if (paramv != null)
            {
              localIBinder = paramv.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
              this.ky.transact(6, localParcel1, localParcel2, 0);
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

      public void a(DisconnectRequest paramDisconnectRequest)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
          if (paramDisconnectRequest != null)
          {
            localParcel1.writeInt(1);
            paramDisconnectRequest.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.ky.transact(16, localParcel1, localParcel2, 0);
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

      public void a(GetMetadataRequest paramGetMetadataRequest, v paramv)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramGetMetadataRequest == null)
              continue;
            localParcel1.writeInt(1);
            paramGetMetadataRequest.writeToParcel(localParcel1, 0);
            if (paramv != null)
            {
              localIBinder = paramv.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
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

      public void a(ListParentsRequest paramListParentsRequest, v paramv)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramListParentsRequest == null)
              continue;
            localParcel1.writeInt(1);
            paramListParentsRequest.writeToParcel(localParcel1, 0);
            if (paramv != null)
            {
              localIBinder = paramv.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
              this.ky.transact(13, localParcel1, localParcel2, 0);
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

      public void a(OpenContentsRequest paramOpenContentsRequest, v paramv)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramOpenContentsRequest == null)
              continue;
            localParcel1.writeInt(1);
            paramOpenContentsRequest.writeToParcel(localParcel1, 0);
            if (paramv != null)
            {
              localIBinder = paramv.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
              this.ky.transact(7, localParcel1, localParcel2, 0);
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

      public void a(QueryRequest paramQueryRequest, v paramv)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramQueryRequest == null)
              continue;
            localParcel1.writeInt(1);
            paramQueryRequest.writeToParcel(localParcel1, 0);
            if (paramv != null)
            {
              localIBinder = paramv.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
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

      public void a(RemoveEventListenerRequest paramRemoveEventListenerRequest, w paramw, String paramString, v paramv)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramRemoveEventListenerRequest == null)
              continue;
            localParcel1.writeInt(1);
            paramRemoveEventListenerRequest.writeToParcel(localParcel1, 0);
            if (paramw != null)
            {
              localIBinder1 = paramw.asBinder();
              localParcel1.writeStrongBinder(localIBinder1);
              localParcel1.writeString(paramString);
              IBinder localIBinder2 = null;
              if (paramv == null)
                continue;
              localIBinder2 = paramv.asBinder();
              localParcel1.writeStrongBinder(localIBinder2);
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
          IBinder localIBinder1 = null;
        }
      }

      public void a(TrashResourceRequest paramTrashResourceRequest, v paramv)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramTrashResourceRequest == null)
              continue;
            localParcel1.writeInt(1);
            paramTrashResourceRequest.writeToParcel(localParcel1, 0);
            if (paramv != null)
            {
              localIBinder = paramv.asBinder();
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
          IBinder localIBinder = null;
        }
      }

      public void a(UpdateMetadataRequest paramUpdateMetadataRequest, v paramv)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramUpdateMetadataRequest == null)
              continue;
            localParcel1.writeInt(1);
            paramUpdateMetadataRequest.writeToParcel(localParcel1, 0);
            if (paramv != null)
            {
              localIBinder = paramv.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
              this.ky.transact(3, localParcel1, localParcel2, 0);
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

      public void a(v paramv)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
          if (paramv != null);
          for (IBinder localIBinder = paramv.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            this.ky.transact(9, localParcel1, localParcel2, 0);
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

      public IBinder asBinder()
      {
        return this.ky;
      }
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.u
 * JD-Core Version:    0.6.0
 */