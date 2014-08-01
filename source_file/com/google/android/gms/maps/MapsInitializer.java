package com.google.android.gms.maps;

public final class MapsInitializer
{
  // ERROR //
  public static int initialize(android.content.Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 20	com/google/android/gms/internal/er:f	(Ljava/lang/Object;)Ljava/lang/Object;
    //   4: pop
    //   5: aload_0
    //   6: invokestatic 26	com/google/android/gms/maps/internal/q:A	(Landroid/content/Context;)Lcom/google/android/gms/maps/internal/c;
    //   9: astore_3
    //   10: aload_3
    //   11: invokeinterface 32 1 0
    //   16: invokestatic 38	com/google/android/gms/maps/CameraUpdateFactory:a	(Lcom/google/android/gms/maps/internal/ICameraUpdateFactoryDelegate;)V
    //   19: aload_3
    //   20: invokeinterface 42 1 0
    //   25: invokestatic 47	com/google/android/gms/maps/model/BitmapDescriptorFactory:a	(Lcom/google/android/gms/maps/model/internal/a;)V
    //   28: iconst_0
    //   29: ireturn
    //   30: astore_2
    //   31: aload_2
    //   32: getfield 51	com/google/android/gms/common/GooglePlayServicesNotAvailableException:errorCode	I
    //   35: ireturn
    //   36: astore 4
    //   38: new 53	com/google/android/gms/maps/model/RuntimeRemoteException
    //   41: dup
    //   42: aload 4
    //   44: invokespecial 56	com/google/android/gms/maps/model/RuntimeRemoteException:<init>	(Landroid/os/RemoteException;)V
    //   47: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   5	10	30	com/google/android/gms/common/GooglePlayServicesNotAvailableException
    //   10	28	36	android/os/RemoteException
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.maps.MapsInitializer
 * JD-Core Version:    0.6.0
 */