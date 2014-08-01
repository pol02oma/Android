package com.google.android.gms.ads.identifier;

import android.content.Context;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.internal.er;
import java.io.IOException;

public final class AdvertisingIdClient
{
  // ERROR //
  static Info a(Context paramContext, com.google.android.gms.common.a parama)
    throws IOException
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 24	com/google/android/gms/common/a:dm	()Landroid/os/IBinder;
    //   4: invokestatic 30	com/google/android/gms/internal/t$a:b	(Landroid/os/IBinder;)Lcom/google/android/gms/internal/t;
    //   7: astore 8
    //   9: new 32	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info
    //   12: dup
    //   13: aload 8
    //   15: invokeinterface 38 1 0
    //   20: aload 8
    //   22: iconst_1
    //   23: invokeinterface 41 2 0
    //   28: invokespecial 44	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:<init>	(Ljava/lang/String;Z)V
    //   31: astore 9
    //   33: aload_0
    //   34: aload_1
    //   35: invokevirtual 50	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
    //   38: aload 9
    //   40: areturn
    //   41: astore 10
    //   43: ldc 52
    //   45: ldc 54
    //   47: aload 10
    //   49: invokestatic 60	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   52: pop
    //   53: aload 9
    //   55: areturn
    //   56: astore 6
    //   58: ldc 52
    //   60: ldc 62
    //   62: aload 6
    //   64: invokestatic 60	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   67: pop
    //   68: new 12	java/io/IOException
    //   71: dup
    //   72: ldc 64
    //   74: invokespecial 67	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   77: athrow
    //   78: astore_3
    //   79: aload_0
    //   80: aload_1
    //   81: invokevirtual 50	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
    //   84: aload_3
    //   85: athrow
    //   86: astore_2
    //   87: new 12	java/io/IOException
    //   90: dup
    //   91: ldc 69
    //   93: invokespecial 67	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   96: athrow
    //   97: astore 4
    //   99: ldc 52
    //   101: ldc 54
    //   103: aload 4
    //   105: invokestatic 60	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   108: pop
    //   109: goto -25 -> 84
    //
    // Exception table:
    //   from	to	target	type
    //   33	38	41	java/lang/IllegalArgumentException
    //   0	33	56	android/os/RemoteException
    //   0	33	78	finally
    //   58	78	78	finally
    //   87	97	78	finally
    //   0	33	86	java/lang/InterruptedException
    //   79	84	97	java/lang/IllegalArgumentException
  }

  // ERROR //
  private static com.google.android.gms.common.a g(Context paramContext)
    throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 81	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: ldc 83
    //   6: iconst_0
    //   7: invokevirtual 89	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   10: pop
    //   11: aload_0
    //   12: invokestatic 95	com/google/android/gms/common/GooglePlayServicesUtil:s	(Landroid/content/Context;)V
    //   15: new 20	com/google/android/gms/common/a
    //   18: dup
    //   19: invokespecial 96	com/google/android/gms/common/a:<init>	()V
    //   22: astore 4
    //   24: new 98	android/content/Intent
    //   27: dup
    //   28: ldc 100
    //   30: invokespecial 101	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   33: astore 5
    //   35: aload 5
    //   37: ldc 103
    //   39: invokevirtual 107	android/content/Intent:setPackage	(Ljava/lang/String;)Landroid/content/Intent;
    //   42: pop
    //   43: aload_0
    //   44: aload 5
    //   46: aload 4
    //   48: iconst_1
    //   49: invokevirtual 111	android/content/Context:bindService	(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    //   52: ifeq +27 -> 79
    //   55: aload 4
    //   57: areturn
    //   58: astore_1
    //   59: new 73	com/google/android/gms/common/GooglePlayServicesNotAvailableException
    //   62: dup
    //   63: bipush 9
    //   65: invokespecial 114	com/google/android/gms/common/GooglePlayServicesNotAvailableException:<init>	(I)V
    //   68: athrow
    //   69: astore_3
    //   70: new 12	java/io/IOException
    //   73: dup
    //   74: aload_3
    //   75: invokespecial 117	java/io/IOException:<init>	(Ljava/lang/Throwable;)V
    //   78: athrow
    //   79: new 12	java/io/IOException
    //   82: dup
    //   83: ldc 119
    //   85: invokespecial 67	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   88: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   0	11	58	android/content/pm/PackageManager$NameNotFoundException
    //   11	15	69	com/google/android/gms/common/GooglePlayServicesNotAvailableException
  }

  public static Info getAdvertisingIdInfo(Context paramContext)
    throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException
  {
    er.ad("Calling this from your main thread can lead to deadlock");
    return a(paramContext, g(paramContext));
  }

  public static final class Info
  {
    private final String kF;
    private final boolean kG;

    public Info(String paramString, boolean paramBoolean)
    {
      this.kF = paramString;
      this.kG = paramBoolean;
    }

    public String getId()
    {
      return this.kF;
    }

    public boolean isLimitAdTrackingEnabled()
    {
      return this.kG;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.ads.identifier.AdvertisingIdClient
 * JD-Core Version:    0.6.0
 */