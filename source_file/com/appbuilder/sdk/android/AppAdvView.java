package com.appbuilder.sdk.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.millennialmedia.android.MMAdView;
import com.smaato.soma.AdSettings;
import com.smaato.soma.BannerView;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AppAdvView extends RelativeLayout
{
  private final int ADD_CLOSE_BUTTON = 1;
  private final String CLOSE_BTN_PARH = "/com/appbuilder/sdk/android/res/adview_btn_close.png";
  private final int CLOSE_VIEW = 0;
  private AdView adView = null;
  private AppAdvData advData = null;
  private Context context = null;
  private float density;
  private boolean firstStart = false;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        return;
      case 1:
        AppAdvView.this.addCloseButton();
        return;
      case 0:
      }
      AppAdvView.this.closeView();
    }
  };
  private int smaatoCount = 0;

  public AppAdvView(Context paramContext, AttributeSet paramAttributeSet, AppAdvData paramAppAdvData)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
    this.advData = paramAppAdvData;
    this.density = getResources().getDisplayMetrics().density;
    initAdvData();
  }

  public AppAdvView(Context paramContext, AppAdvData paramAppAdvData)
  {
    super(paramContext);
    this.context = paramContext;
    this.advData = paramAppAdvData;
    this.density = getResources().getDisplayMetrics().density;
    initAdvData();
  }

  public AppAdvView(Context paramContext, AppAdvData paramAppAdvData, boolean paramBoolean)
  {
    super(paramContext);
    this.context = paramContext;
    this.advData = paramAppAdvData;
    this.firstStart = paramBoolean;
    this.density = getResources().getDisplayMetrics().density;
    initAdvData();
  }

  private void addCloseButton()
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.addRule(11, -1);
    localLayoutParams.addRule(10, -1);
    setLayoutParams(localLayoutParams);
    setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    LinearLayout localLinearLayout = new LinearLayout(this.context);
    localLinearLayout.setLayoutParams(localLayoutParams);
    ImageView localImageView = new ImageView(this.context);
    localImageView.setId(1);
    try
    {
      localImageView.setImageBitmap(BitmapFactory.decodeStream(new ByteArrayInputStream(Base64.decode("iVBORw0KGgoAAAANSUhEUgAAACMAAAAjCAYAAAAe2bNZAAAABGdBTUEAAK/INwWK6QAAABl0RVh0U29mdHdhcmUAQWRvYmUgSW1hZ2VSZWFkeXHJZTwAAAR2SURBVHja7FdbbBRVGP7O7G53thcLurtcSiMXL0SotlpqsKZqLAYKgRoVE4MxpjXhQWPQNKYm+oyJDwYfNAWixqgPajCKoiagtIVwKUK3REzD3S2Udku73e5ut9ud439m/knXtU+zTeRhT/JlO2fO/PPNf77//06FlBK3ynDn+bzGMYoIxYRSQjn/lvC8GmlCghAjjPOvup7ie0a+ZBQRD0EnlBHuIAQICwh+JqXz2hSTiBCGGDcJ0SyyhlMygj/Exy9dSKgkLEVfVzX2dzyM86cXYuhKCTQNRqAyIZatjohN24/jgcdP07rLhKsEF5NSY0o41IxNZB5hMWEFYSU62rbgp92roXs1+CsoP0ss2lFKyMg12piojFQ1XSpv6/jW4ys5S3fOEwaYUNIJGRXeS7iNidyNyXgN2hpb0X8ygFV1wHzaqflBi8zUJHD9AjAxRq+k9177C3/ry2P+Xb/v9QUWHafn+wnEFGNaHlpROqE3Yhneb3kO544F4NUht70L2bCVVJK0CMTHTEKy4XnIza/TtURlrL9sZGfLs9IwlrPGVCyv2yEZnTMTxJnfqnH46xUqYXJHB1DdaK3KZIAj3wDTVDD1zwBPvmhOy9EIxCftCJ75eUm8+4fa0oYtYRZ01GlmvPw1fvzy6RoDkrZOQhz7keoiZa164gUY9z0Co+oxYN3L1tzoDYgTB4go1bxHIn3oqxquPBWryElmXNw/VB8pl31di4Utu4NfQtD2yLc+g/T6IJp3WNlQuhwdhHinmRRyylpLU+nergWcYZ9TMiKLkI5I2CeQVQTd+8zsyPbPITyUQCFgxKPQ2puAi30z66jNaWNDOmdZxXJpeXZgYcw2W3EXCZgabJIabSIGQfoRq+r/s8zj1mS+dqACZLiVT06X+VOu6HUdzEo2vwps3g6RnIBUZFxuCL0UcuubKknA9x9b+lZ74180qWJwrIyTzBj8cFK1eHlv3TA4iiQSWLeNsjEOERmAeO8liF1EbnyYSjoKufEVYIMl5rQS8craIbYJ06eckLGzoswu4tnY2mOkrRoTNwfpTsLquB+9AXH5HMTZoxC73ybqZEMxarSDF00jEoK2qKm1h/4c4Vgpt8PM2MY37Fq76WTiwafqikO/VuDod6SPaeql9MEXQjNP9B6G+GKnKexMbydctNGetU1hrFn/B/cYk4xTO9DZIJUd3EOdtnbqtfrWovCf5eaWGaysnO5kGNbDcmlVVPugcw9K5/XkaweSLT/OKb5KQUNFHx7Zk3lofVglxlwhZqAujYx1ma5pHGAiIXbuEY417dS1BfcG1axu5yPEncqnMt376uSBvbUi1BmksvaYi4vL0sb9DTfEhpZTrkefPkFTlwhXCIO2Y+dzhMg2TPtM42fjDM5yuJrkg1T24SrCc8l8D1e2kNM5FWaKmr2mmMnmHjsVgQnGv46dYg4O5LY9eHjrvNktPodsimGTyGRLXczhfwc2KS0HdhazkZml3uaUTN5Dwy00CmQKZApkCmQKZP6v8Y8AAwAM1bZSYou4NgAAAABJRU5ErkJggg=="))));
      localImageView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          try
          {
            AppAdvView.this.handler.sendEmptyMessage(0);
            return;
          }
          catch (Exception localException)
          {
          }
        }
      });
      localLinearLayout.addView(localImageView);
      addView(localLinearLayout);
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        Log.d("", "");
    }
  }

  private void closeBtn_v2()
  {
    Bitmap localBitmap = BitmapFactory.decodeStream(getClass().getResourceAsStream("/com/appbuilder/sdk/android/res/adview_btn_close.png"));
    ImageView localImageView = new ImageView(this.context);
    localImageView.setImageBitmap(localBitmap);
    new RelativeLayout.LayoutParams(-2, -2);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams((int)(25.0F * this.density), (int)(25.0F * this.density));
    localLayoutParams.addRule(11, -1);
    localLayoutParams.addRule(10, -1);
    localImageView.setLayoutParams(localLayoutParams);
    localImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        try
        {
          AppAdvView.this.handler.sendEmptyMessage(0);
          return;
        }
        catch (Exception localException)
        {
        }
      }
    });
    addView(localImageView);
  }

  private void closeView()
  {
    try
    {
      setVisibility(8);
      this.adView.destroy();
      this.advData.setAdvState(2);
      String str = this.context.getCacheDir().toString();
      File localFile = new File(str + "/" + md5(new StringBuilder().append("ibuildapp-").append(this.advData.getAdvSessionUid()).toString()));
      try
      {
        localFile.createNewFile();
        ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(new FileOutputStream(localFile));
        localObjectOutputStream.writeObject(this.advData);
        localObjectOutputStream.close();
        return;
      }
      catch (Exception localException2)
      {
        localFile.delete();
        return;
      }
    }
    catch (Exception localException1)
    {
    }
  }

  private void initAdvData()
  {
    if (this.advData == null);
    while (true)
    {
      return;
      NetworkInfo localNetworkInfo = ((ConnectivityManager)this.context.getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo == null)
      {
        this.advData.setAdvState(2);
        return;
      }
      if (!localNetworkInfo.isConnectedOrConnecting())
      {
        this.advData.setAdvState(2);
        return;
      }
      String str = this.context.getCacheDir().toString();
      File localFile = new File(str + "/" + md5(new StringBuilder().append("ibuildapp-").append(this.advData.getAdvSessionUid()).toString()));
      if (localFile.exists());
      try
      {
        ObjectInputStream localObjectInputStream = new ObjectInputStream(new FileInputStream(localFile));
        AppAdvData localAppAdvData = (AppAdvData)localObjectInputStream.readObject();
        localObjectInputStream.close();
        if (this.firstStart)
          this.advData.setAdvState(1);
        while (true)
        {
          try
          {
            localFile.createNewFile();
            ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(new FileOutputStream(localFile));
            localObjectOutputStream.writeObject(this.advData);
            localObjectOutputStream.close();
            label214: if (this.advData.getAdvState() != 1)
              break;
            if (!this.advData.getAdvType().equals("gAd"))
              break label278;
            setAdMob(this.advData.getAdvContent());
            return;
          }
          catch (Exception localException2)
          {
            localFile.delete();
            continue;
          }
          this.advData.setAdvState(localAppAdvData.getAdvState());
        }
        label278: if (this.advData.getAdvType().equals("url"))
        {
          setBanner(this.advData.getAdvType(), this.advData.getAdvContent(), this.advData.getAdvRedirect());
          return;
        }
        if (this.advData.getAdvType().equals("html"))
        {
          setBanner(this.advData.getAdvType(), this.advData.getAdvContent(), this.advData.getAdvRedirect());
          return;
        }
        if (this.advData.getAdvType().equals("smaato"))
        {
          setSmaatoAd(this.advData.getAdvPublisherId(), this.advData.getAdvAdSpaceId());
          return;
        }
        if (!this.advData.getAdvType().equals("mlmedia"))
          continue;
        setMMediaAd(this.advData.getAdvApId());
        return;
      }
      catch (Exception localException1)
      {
        break label214;
      }
    }
  }

  private String md5(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.reset();
      localMessageDigest.update(paramString.getBytes());
      byte[] arrayOfByte = localMessageDigest.digest();
      int i = arrayOfByte.length;
      StringBuilder localStringBuilder = new StringBuilder(i << 1);
      for (int j = 0; j < i; j++)
      {
        localStringBuilder.append(Character.forDigit((0xF0 & arrayOfByte[j]) >> 4, 16));
        localStringBuilder.append(Character.forDigit(0xF & arrayOfByte[j], 16));
      }
      String str = localStringBuilder.toString();
      return str;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
    }
    return null;
  }

  private void setAdMob(String paramString)
  {
    this.adView = new AdView((Activity)this.context);
    this.adView.setAdUnitId(paramString);
    this.adView.setAdSize(AdSize.BANNER);
    AdRequest localAdRequest = new AdRequest.Builder().build();
    this.adView.loadAd(localAdRequest);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.addRule(7, -1);
    localLayoutParams.addRule(11, -1);
    localLayoutParams.addRule(15, -1);
    setLayoutParams(localLayoutParams);
    LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, (int)TypedValue.applyDimension(1, 50.0F, this.context.getResources().getDisplayMetrics()));
    setLayoutParams(localLayoutParams1);
    new LinearLayout(this.context).setLayoutParams(localLayoutParams);
    this.adView.setLayoutParams(localLayoutParams1);
    addView(this.adView);
    closeBtn_v2();
  }

  private void setBanner(String paramString1, String paramString2, String paramString3)
  {
    WebView localWebView = new WebView(this.context);
    localWebView.setLayoutParams(new LinearLayout.LayoutParams(-1, (int)TypedValue.applyDimension(1, 50.0F, this.context.getResources().getDisplayMetrics())));
    localWebView.getSettings().setJavaScriptEnabled(true);
    localWebView.getSettings().setAllowFileAccess(false);
    localWebView.getSettings().setAppCacheEnabled(false);
    localWebView.getSettings().setUserAgentString("Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_0 like Mac OS X; en-us) AppleWebKit/532.9 (KHTML, like Gecko) Version/4.0.5 Mobile/8A293 Safari/6531.22.7");
    localWebView.setVerticalScrollBarEnabled(false);
    localWebView.setHorizontalScrollBarEnabled(false);
    localWebView.setWebViewClient(new WebViewClient()
    {
      public void onPageFinished(WebView paramWebView, String paramString)
      {
      }

      public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
      {
      }

      public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
      {
      }

      public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
      {
        return false;
      }
    });
    localWebView.getSettings().setLoadWithOverviewMode(true);
    if (paramString1.equalsIgnoreCase("url"))
    {
      localWebView.loadUrl(paramString2);
      if (this.advData.getAdvRedirect().length() <= 0)
        break label199;
      localWebView.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
        {
          Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(AppAdvView.this.advData.getAdvRedirect()));
          AppAdvView.this.context.startActivity(localIntent);
          return paramMotionEvent.getAction() == 2;
        }
      });
      localWebView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
        }
      });
    }
    while (true)
    {
      addView(localWebView);
      closeBtn_v2();
      return;
      localWebView.loadDataWithBaseURL(null, paramString2, "text/html", "utf-8", null);
      break;
      label199: localWebView.setWebViewClient(new WebViewClient()
      {
        public void onPageFinished(WebView paramWebView, String paramString)
        {
          Log.d("", "");
        }

        public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
        {
          Log.d("", "");
        }

        public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
        {
          Log.d("", "");
        }

        public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
        {
          if ((paramString.contains("smaato.net")) && (AppAdvView.this.smaatoCount == 0))
          {
            AppAdvView.access$508(AppAdvView.this);
            return false;
          }
          Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
          AppAdvView.this.context.startActivity(localIntent);
          AppAdvView.access$502(AppAdvView.this, 0);
          return true;
        }
      });
    }
  }

  private void setMMediaAd(String paramString)
  {
    MMAdView localMMAdView = new MMAdView((Activity)this.context, paramString, "MMBannerAdTop", 45);
    localMMAdView.setId(10000);
    (int)TypedValue.applyDimension(1, 50.0F, this.context.getResources().getDisplayMetrics());
    localMMAdView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    localMMAdView.callForAd();
    addView(localMMAdView);
    closeBtn_v2();
  }

  private void setSmaatoAd(int paramInt1, int paramInt2)
  {
    BannerView localBannerView = new BannerView(this.context);
    localBannerView.setLayoutParams(new LinearLayout.LayoutParams(-1, (int)TypedValue.applyDimension(1, 50.0F, this.context.getResources().getDisplayMetrics())));
    localBannerView.getAdSettings().setPublisherId(paramInt1);
    localBannerView.getAdSettings().setAdspaceId(paramInt2);
    addView(localBannerView);
    localBannerView.asyncLoadNewBanner();
    closeBtn_v2();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.sdk.android.AppAdvView
 * JD-Core Version:    0.6.0
 */