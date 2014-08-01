package com.ibuildapp.romanblack.TableReservationPlugin;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ibuildapp.romanblack.TableReservationPlugin.entity.HouresMinutes;
import com.ibuildapp.romanblack.TableReservationPlugin.utils.Utils;
import com.seppius.i18n.plurals.PluralResources;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class TableReservationUpcomingAdapter extends BaseAdapter
{
  private Context context;
  private int fontColor;
  private LayoutInflater layoutInflater;
  private ArrayList<TableReservationOrderInfo> source;

  TableReservationUpcomingAdapter(Context paramContext, int paramInt, ArrayList<TableReservationOrderInfo> paramArrayList)
  {
    this.source = paramArrayList;
    this.fontColor = paramInt;
    this.layoutInflater = LayoutInflater.from(paramContext);
    this.context = paramContext;
  }

  public int getCount()
  {
    return this.source.size();
  }

  public Object getItem(int paramInt)
  {
    return this.source.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return 0L;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = paramView;
    if (paramView == null)
      localView = this.layoutInflater.inflate(R.layout.sergeyb_tablereservation_list_item, null);
    TextView localTextView1 = (TextView)localView.findViewById(R.id.sergeyb_tablereservation_date_text);
    TextView localTextView2 = (TextView)localView.findViewById(R.id.sergeyb_tablereservation_persons_text);
    TableReservationOrderInfo localTableReservationOrderInfo = (TableReservationOrderInfo)this.source.get(paramInt);
    String str1 = new SimpleDateFormat("E").format(localTableReservationOrderInfo.orderDate) + ", " + new SimpleDateFormat("MMMMMMM").format(localTableReservationOrderInfo.orderDate) + " " + new SimpleDateFormat("d").format(localTableReservationOrderInfo.orderDate) + ", ";
    String str2;
    if (Locale.getDefault().toString().equals("ru_RU"))
    {
      str2 = Utils.convertTimeToFormat(localTableReservationOrderInfo.orderTime.houres, localTableReservationOrderInfo.orderTime.minutes, true);
      localTextView1.setText(str1 + str2);
      localTextView1.setTextColor(this.fontColor);
      if (!Locale.getDefault().toString().equals("ru_RU"))
        break label346;
    }
    while (true)
    {
      try
      {
        PluralResources localPluralResources = new PluralResources(this.context.getResources());
        int i = R.plurals.orderTableForPerson;
        int j = localTableReservationOrderInfo.personsAmount;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(localTableReservationOrderInfo.personsAmount);
        String str4 = localPluralResources.getQuantityString(i, j, arrayOfObject);
        str3 = str4;
        localTextView2.setText(str3);
        localTextView2.setTextColor(this.fontColor);
        return localView;
        str2 = Utils.convertTimeToFormat(localTableReservationOrderInfo.orderTime.houres, localTableReservationOrderInfo.orderTime.minutes, false);
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        str3 = "";
        continue;
      }
      label346: String str3 = this.context.getResources().getString(R.string.tablereservation_party_of) + " " + localTableReservationOrderInfo.personsAmount;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.TableReservationPlugin.TableReservationUpcomingAdapter
 * JD-Core Version:    0.6.0
 */