package com.google.android.gms.internal;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.os.Message;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebStorage.QuotaUpdater;
import android.webkit.WebView;
import android.webkit.WebView.WebViewTransport;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class df extends WebChromeClient
{
  private final dd ng;

  public df(dd paramdd)
  {
    this.ng = paramdd;
  }

  private static void a(AlertDialog.Builder paramBuilder, String paramString, JsResult paramJsResult)
  {
    paramBuilder.setMessage(paramString).setPositiveButton(17039370, new DialogInterface.OnClickListener(paramJsResult)
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        this.qo.confirm();
      }
    }).setNegativeButton(17039360, new DialogInterface.OnClickListener(paramJsResult)
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        this.qo.cancel();
      }
    }).setOnCancelListener(new DialogInterface.OnCancelListener(paramJsResult)
    {
      public void onCancel(DialogInterface paramDialogInterface)
      {
        this.qo.cancel();
      }
    }).create().show();
  }

  private static void a(Context paramContext, AlertDialog.Builder paramBuilder, String paramString1, String paramString2, JsPromptResult paramJsPromptResult)
  {
    LinearLayout localLinearLayout = new LinearLayout(paramContext);
    localLinearLayout.setOrientation(1);
    TextView localTextView = new TextView(paramContext);
    localTextView.setText(paramString1);
    EditText localEditText = new EditText(paramContext);
    localEditText.setText(paramString2);
    localLinearLayout.addView(localTextView);
    localLinearLayout.addView(localEditText);
    paramBuilder.setView(localLinearLayout).setPositiveButton(17039370, new DialogInterface.OnClickListener(paramJsPromptResult, localEditText)
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        this.qp.confirm(this.qq.getText().toString());
      }
    }).setNegativeButton(17039360, new DialogInterface.OnClickListener(paramJsPromptResult)
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        this.qp.cancel();
      }
    }).setOnCancelListener(new DialogInterface.OnCancelListener(paramJsPromptResult)
    {
      public void onCancel(DialogInterface paramDialogInterface)
      {
        this.qp.cancel();
      }
    }).create().show();
  }

  private static boolean a(Context paramContext, String paramString1, String paramString2, String paramString3, JsResult paramJsResult, JsPromptResult paramJsPromptResult, boolean paramBoolean)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle(paramString1);
    if (paramBoolean)
      a(paramContext, localBuilder, paramString2, paramString3, paramJsPromptResult);
    while (true)
    {
      return true;
      a(localBuilder, paramString2, paramJsResult);
    }
  }

  protected final void a(View paramView, int paramInt, WebChromeClient.CustomViewCallback paramCustomViewCallback)
  {
    bo localbo = this.ng.ba();
    if (localbo == null)
    {
      da.w("Could not get ad overlay when showing custom view.");
      paramCustomViewCallback.onCustomViewHidden();
      return;
    }
    localbo.a(paramView, paramCustomViewCallback);
    localbo.setRequestedOrientation(paramInt);
  }

  public final void onCloseWindow(WebView paramWebView)
  {
    if (!(paramWebView instanceof dd))
    {
      da.w("Tried to close a WebView that wasn't an AdWebView.");
      return;
    }
    bo localbo = ((dd)paramWebView).ba();
    if (localbo == null)
    {
      da.w("Tried to close an AdWebView not associated with an overlay.");
      return;
    }
    localbo.close();
  }

  public final boolean onConsoleMessage(ConsoleMessage paramConsoleMessage)
  {
    String str = "JS: " + paramConsoleMessage.message() + " (" + paramConsoleMessage.sourceId() + ":" + paramConsoleMessage.lineNumber() + ")";
    switch (7.qr[paramConsoleMessage.messageLevel().ordinal()])
    {
    default:
      da.u(str);
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    }
    while (true)
    {
      return super.onConsoleMessage(paramConsoleMessage);
      da.t(str);
      continue;
      da.w(str);
      continue;
      da.u(str);
      continue;
      da.s(str);
    }
  }

  public final boolean onCreateWindow(WebView paramWebView, boolean paramBoolean1, boolean paramBoolean2, Message paramMessage)
  {
    WebView.WebViewTransport localWebViewTransport = (WebView.WebViewTransport)paramMessage.obj;
    WebView localWebView = new WebView(paramWebView.getContext());
    localWebView.setWebViewClient(this.ng.bb());
    localWebViewTransport.setWebView(localWebView);
    paramMessage.sendToTarget();
    return true;
  }

  public final void onExceededDatabaseQuota(String paramString1, String paramString2, long paramLong1, long paramLong2, long paramLong3, WebStorage.QuotaUpdater paramQuotaUpdater)
  {
    long l = 5242880L - paramLong3;
    if (l <= 0L)
    {
      paramQuotaUpdater.updateQuota(paramLong1);
      return;
    }
    if (paramLong1 == 0L)
      if ((paramLong2 > l) || (paramLong2 > 1048576L));
    while (true)
    {
      paramQuotaUpdater.updateQuota(paramLong2);
      return;
      paramLong2 = 0L;
      continue;
      if (paramLong2 == 0L)
      {
        paramLong2 = Math.min(paramLong1 + Math.min(131072L, l), 1048576L);
        continue;
      }
      if (paramLong2 <= Math.min(1048576L - paramLong1, l))
        paramLong1 += paramLong2;
      paramLong2 = paramLong1;
    }
  }

  public final void onHideCustomView()
  {
    bo localbo = this.ng.ba();
    if (localbo == null)
    {
      da.w("Could not get ad overlay when hiding custom view.");
      return;
    }
    localbo.aq();
  }

  public final boolean onJsAlert(WebView paramWebView, String paramString1, String paramString2, JsResult paramJsResult)
  {
    return a(paramWebView.getContext(), paramString1, paramString2, null, paramJsResult, null, false);
  }

  public final boolean onJsBeforeUnload(WebView paramWebView, String paramString1, String paramString2, JsResult paramJsResult)
  {
    return a(paramWebView.getContext(), paramString1, paramString2, null, paramJsResult, null, false);
  }

  public final boolean onJsConfirm(WebView paramWebView, String paramString1, String paramString2, JsResult paramJsResult)
  {
    return a(paramWebView.getContext(), paramString1, paramString2, null, paramJsResult, null, false);
  }

  public final boolean onJsPrompt(WebView paramWebView, String paramString1, String paramString2, String paramString3, JsPromptResult paramJsPromptResult)
  {
    return a(paramWebView.getContext(), paramString1, paramString2, paramString3, null, paramJsPromptResult, true);
  }

  public final void onReachedMaxAppCacheSize(long paramLong1, long paramLong2, WebStorage.QuotaUpdater paramQuotaUpdater)
  {
    long l1 = 5242880L - paramLong2;
    long l2 = 131072L + paramLong1;
    if (l1 < l2)
    {
      paramQuotaUpdater.updateQuota(0L);
      return;
    }
    paramQuotaUpdater.updateQuota(l2);
  }

  public final void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
  {
    a(paramView, -1, paramCustomViewCallback);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.df
 * JD-Core Version:    0.6.0
 */