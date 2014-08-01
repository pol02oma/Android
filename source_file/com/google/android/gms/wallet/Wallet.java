package com.google.android.gms.wallet;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.b;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ApiOptions;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a.a;
import com.google.android.gms.internal.ee;
import com.google.android.gms.internal.er;
import com.google.android.gms.internal.jg;

public final class Wallet
{
  public static final Api API;
  static final Api.b<jg> va = new Api.b()
  {
    public int getPriority()
    {
      return 2147483647;
    }

    public jg i(Context paramContext, Looper paramLooper, ee paramee, GoogleApiClient.ApiOptions paramApiOptions, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      er.b(paramContext instanceof Activity, "An Activity must be used for Wallet APIs");
      Activity localActivity = (Activity)paramContext;
      boolean bool;
      if ((paramApiOptions == null) || ((paramApiOptions instanceof Wallet.WalletOptions)))
      {
        bool = true;
        er.b(bool, "WalletOptions must be used for Wallet APIs");
        if (paramApiOptions == null)
          break label85;
      }
      label85: for (Wallet.WalletOptions localWalletOptions = (Wallet.WalletOptions)paramApiOptions; ; localWalletOptions = new Wallet.WalletOptions(null))
      {
        return new jg(localActivity, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, localWalletOptions.environment, paramee.getAccountName(), localWalletOptions.theme);
        bool = false;
        break;
      }
    }
  };

  static
  {
    API = new Api(va, new Scope[0]);
  }

  public static void changeMaskedWallet(GoogleApiClient paramGoogleApiClient, String paramString1, String paramString2, int paramInt)
  {
    paramGoogleApiClient.a(new a(paramString1, paramString2, paramInt)
    {
      protected void a(jg paramjg)
      {
        paramjg.changeMaskedWallet(this.Zt, this.Zu, this.Kx);
        a(Status.zQ);
      }
    });
  }

  public static void checkForPreAuthorization(GoogleApiClient paramGoogleApiClient, int paramInt)
  {
    paramGoogleApiClient.a(new a(paramInt)
    {
      protected void a(jg paramjg)
      {
        paramjg.checkForPreAuthorization(this.Kx);
        a(Status.zQ);
      }
    });
  }

  public static void loadFullWallet(GoogleApiClient paramGoogleApiClient, FullWalletRequest paramFullWalletRequest, int paramInt)
  {
    paramGoogleApiClient.a(new a(paramFullWalletRequest, paramInt)
    {
      protected void a(jg paramjg)
      {
        paramjg.loadFullWallet(this.Zs, this.Kx);
        a(Status.zQ);
      }
    });
  }

  public static void loadMaskedWallet(GoogleApiClient paramGoogleApiClient, MaskedWalletRequest paramMaskedWalletRequest, int paramInt)
  {
    paramGoogleApiClient.a(new a(paramMaskedWalletRequest, paramInt)
    {
      protected void a(jg paramjg)
      {
        paramjg.loadMaskedWallet(this.Zr, this.Kx);
        a(Status.zQ);
      }
    });
  }

  public static void notifyTransactionStatus(GoogleApiClient paramGoogleApiClient, NotifyTransactionStatusRequest paramNotifyTransactionStatusRequest)
  {
    paramGoogleApiClient.a(new a(paramNotifyTransactionStatusRequest)
    {
      protected void a(jg paramjg)
      {
        paramjg.notifyTransactionStatus(this.Zv);
        a(Status.zQ);
      }
    });
  }

  public static final class WalletOptions
    implements GoogleApiClient.ApiOptions
  {
    public final int environment;
    public final int theme;

    private WalletOptions()
    {
      this(new Builder());
    }

    private WalletOptions(Builder paramBuilder)
    {
      this.environment = Builder.a(paramBuilder);
      this.theme = Builder.b(paramBuilder);
    }

    public static final class Builder
    {
      private int Zw = 0;
      private int mTheme = 0;

      public Wallet.WalletOptions build()
      {
        return new Wallet.WalletOptions(this, null);
      }

      public Builder setEnvironment(int paramInt)
      {
        if ((paramInt == 0) || (paramInt == 2) || (paramInt == 1))
        {
          this.Zw = paramInt;
          return this;
        }
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(paramInt);
        throw new IllegalArgumentException(String.format("Invalid environment value %d", arrayOfObject));
      }

      public Builder setTheme(int paramInt)
      {
        if ((paramInt == 0) || (paramInt == 1))
        {
          this.mTheme = paramInt;
          return this;
        }
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(paramInt);
        throw new IllegalArgumentException(String.format("Invalid theme value %d", arrayOfObject));
      }
    }
  }

  private static abstract class a extends a.a<Status, jg>
  {
    public a()
    {
      super();
    }

    public Status f(Status paramStatus)
    {
      return paramStatus;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.wallet.Wallet
 * JD-Core Version:    0.6.0
 */