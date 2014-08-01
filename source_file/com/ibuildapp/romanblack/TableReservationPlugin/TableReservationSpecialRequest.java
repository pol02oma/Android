package com.ibuildapp.romanblack.TableReservationPlugin;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import com.appbuilder.sdk.android.BarDesigner;
import com.appbuilder.sdk.android.BarDesigner.TitleDesign;

public class TableReservationSpecialRequest extends AppBuilderModuleMain
{
  private int backColor;
  private LinearLayout clearButton;
  private TextView clearButtonTxt;
  private boolean colorSchema;
  private LinearLayout doneButton;
  private TextView doneButtonTxt;
  private LinearLayout editTextLayout;
  private int fontColor;
  private LinearLayout mainLayout;
  private EditText specText;
  private TextView specTextText;

  private void closeActivityOk(String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("special", paramString);
    setResult(-1, localIntent);
    finish();
  }

  private void closeActivityOkNoChange()
  {
    setResult(0, new Intent());
    finish();
  }

  public void create()
  {
    requestWindowFeature(1);
    setContentView(R.layout.sergeyb_tablereservation_special_request);
    getWindow().setFlags(1024, 1024);
    setTopBarTitle(getResources().getString(R.string.tablereservation_spec_request));
    swipeBlock();
    setTopBarLeftButtonText(getResources().getString(R.string.common_back_upper), true, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        TableReservationSpecialRequest.this.finish();
      }
    });
    this.specText = ((EditText)findViewById(R.id.sergeyb_tablereservation_special_request_edittext));
    this.doneButton = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_done_button));
    this.mainLayout = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_main_layout));
    this.specTextText = ((TextView)findViewById(R.id.sergeyb_tablereservation_special_request_text));
    this.clearButton = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_clear_button));
    this.editTextLayout = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_special_request_edittext_layout));
    this.doneButtonTxt = ((TextView)findViewById(R.id.sergeyb_tablereservation_done_button_text));
    this.clearButtonTxt = ((TextView)findViewById(R.id.sergeyb_tablereservation_clear_button_text));
    Intent localIntent = getIntent();
    this.specText.setText(localIntent.getStringExtra("specRequest"));
    this.fontColor = localIntent.getIntExtra("fontColor", -1);
    this.backColor = localIntent.getIntExtra("backColor", Color.parseColor("#37393b"));
    this.colorSchema = localIntent.getBooleanExtra("colorSchema", true);
    designButton(this.doneButtonTxt, this.bottomBarDesign.rightButtonDesign);
    this.doneButtonTxt.setTextColor(this.bottomBarDesign.leftButtonDesign.textColor);
    designButton(this.clearButtonTxt, this.bottomBarDesign.leftButtonDesign);
    if (this.colorSchema)
      this.editTextLayout.setBackgroundResource(R.drawable.sergeyb_tablereservation_back_transperant_15alpha_dark);
    while (true)
    {
      this.mainLayout.setBackgroundColor(this.backColor);
      this.specTextText.setTextColor(this.fontColor);
      this.specText.setTextColor(this.fontColor);
      this.doneButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          TableReservationSpecialRequest.this.closeActivityOk(TableReservationSpecialRequest.this.specText.getText().toString());
        }
      });
      this.clearButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          TableReservationSpecialRequest.this.specText.setText("");
        }
      });
      return;
      this.editTextLayout.setBackgroundResource(R.drawable.sergeyb_tablereservation_back_transperant_15alpha);
    }
  }

  public void onBackPressed()
  {
    closeActivityOkNoChange();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.TableReservationPlugin.TableReservationSpecialRequest
 * JD-Core Version:    0.6.0
 */