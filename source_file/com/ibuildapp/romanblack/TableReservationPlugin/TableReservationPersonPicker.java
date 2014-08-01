package com.ibuildapp.romanblack.TableReservationPlugin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class TableReservationPersonPicker extends Activity
{
  private void closeActivityBad()
  {
    setResult(0);
    finish();
  }

  private void closeActivityGood(int paramInt)
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("persons", paramInt);
    setResult(-1, localIntent);
    finish();
  }

  public void onBackPressed()
  {
    closeActivityBad();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(R.layout.sergeyb_tablereservation_person_picker);
    getWindow().setFlags(1024, 1024);
    int i = getIntent().getIntExtra("maxperson", 50);
    ListView localListView = (ListView)findViewById(R.id.sergeyb_tablereservation_person_layout_listView);
    localListView.setAdapter(new TableReservationPersonPickerAdapter(this, i));
    localListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        TableReservationPersonPicker.this.closeActivityGood(paramInt + 1);
      }
    });
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.TableReservationPlugin.TableReservationPersonPicker
 * JD-Core Version:    0.6.0
 */