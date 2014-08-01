package com.ibuildapp.romanblack.TableReservationPlugin;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.seppius.i18n.plurals.PluralResources;

public class TableReservationPersonPickerAdapter extends BaseAdapter
{
  private LayoutInflater layoutInflater;
  private int maxPersons = -1;
  private Integer[] personsList;
  private Resources res;

  TableReservationPersonPickerAdapter(Context paramContext, int paramInt)
  {
    this.maxPersons = paramInt;
    this.layoutInflater = LayoutInflater.from(paramContext);
    this.res = paramContext.getResources();
    this.personsList = new Integer[paramInt];
    for (int i = 1; i < paramInt + 1; i++)
      this.personsList[(i - 1)] = Integer.valueOf(i);
  }

  public int getCount()
  {
    return this.maxPersons;
  }

  public Object getItem(int paramInt)
  {
    return this.personsList[paramInt];
  }

  public long getItemId(int paramInt)
  {
    return 0L;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = paramView;
    if (localView == null)
      localView = this.layoutInflater.inflate(R.layout.sergeyb_tablereservation_person_picker_item, null);
    TextView localTextView = (TextView)localView.findViewById(R.id.sergeyb_tablereservation_person_layout_text);
    Object localObject = "";
    try
    {
      PluralResources localPluralResources = new PluralResources(this.res);
      int i = R.plurals.tablereservation_persons_list;
      int j = paramInt + 1;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt + 1);
      String str = localPluralResources.getQuantityString(i, j, arrayOfObject);
      localObject = str;
      localTextView.setText((CharSequence)localObject);
      return localView;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      while (true)
        localNoSuchMethodException.printStackTrace();
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.TableReservationPlugin.TableReservationPersonPickerAdapter
 * JD-Core Version:    0.6.0
 */