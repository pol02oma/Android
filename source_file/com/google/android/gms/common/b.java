package com.google.android.gms.common;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import com.google.android.gms.internal.er;

public class b extends DialogFragment
{
  private Dialog mDialog = null;
  private DialogInterface.OnCancelListener yK = null;

  public static b a(Dialog paramDialog, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    b localb = new b();
    Dialog localDialog = (Dialog)er.b(paramDialog, "Cannot display null dialog");
    localDialog.setOnCancelListener(null);
    localDialog.setOnDismissListener(null);
    localb.mDialog = localDialog;
    if (paramOnCancelListener != null)
      localb.yK = paramOnCancelListener;
    return localb;
  }

  public void onCancel(DialogInterface paramDialogInterface)
  {
    if (this.yK != null)
      this.yK.onCancel(paramDialogInterface);
  }

  public Dialog onCreateDialog(Bundle paramBundle)
  {
    return this.mDialog;
  }

  public void show(FragmentManager paramFragmentManager, String paramString)
  {
    super.show(paramFragmentManager, paramString);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.common.b
 * JD-Core Version:    0.6.0
 */