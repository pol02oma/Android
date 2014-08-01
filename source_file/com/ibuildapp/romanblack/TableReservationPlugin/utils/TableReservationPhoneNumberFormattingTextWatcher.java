package com.ibuildapp.romanblack.TableReservationPlugin.utils;

import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;

public class TableReservationPhoneNumberFormattingTextWatcher extends PhoneNumberFormattingTextWatcher
{
  public void afterTextChanged(Editable paramEditable)
  {
    monitorenter;
    try
    {
      super.afterTextChanged(paramEditable);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    super.beforeTextChanged(paramCharSequence, paramInt1, paramInt2, paramInt3);
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    super.onTextChanged(paramCharSequence, paramInt1, paramInt2, paramInt3);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.TableReservationPlugin.utils.TableReservationPhoneNumberFormattingTextWatcher
 * JD-Core Version:    0.6.0
 */