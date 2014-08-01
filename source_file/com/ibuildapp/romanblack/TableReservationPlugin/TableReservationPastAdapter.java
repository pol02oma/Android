package com.ibuildapp.romanblack.TableReservationPlugin;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
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

public class TableReservationPastAdapter extends BaseAdapter
{
  private Context context;
  private LayoutInflater layoutInflater;
  private ArrayList<TableReservationOrderInfo> source;

  TableReservationPastAdapter(Context paramContext, ArrayList<TableReservationOrderInfo> paramArrayList)
  {
    this.context = paramContext;
    this.source = paramArrayList;
    this.layoutInflater = LayoutInflater.from(paramContext);
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

  public ArrayList<TableReservationOrderInfo> getSource()
  {
    return this.source;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      if (paramInt == 0)
      {
        View localView2 = this.layoutInflater.inflate(R.layout.sergeyb_tablereservation_list_text, null);
        ((TextView)localView2.findViewById(R.id.textView)).setText(this.context.getResources().getString(R.string.tablereservation_past));
        return localView2;
      }
      View localView1 = this.layoutInflater.inflate(R.layout.sergeyb_tablereservation_list_item, null);
      Drawable localDrawable2 = localView1.getBackground();
      localDrawable2.setAlpha(38);
      localView1.setBackgroundDrawable(localDrawable2);
      TextView localTextView3 = (TextView)localView1.findViewById(R.id.sergeyb_tablereservation_date_text);
      TextView localTextView4 = (TextView)localView1.findViewById(R.id.sergeyb_tablereservation_persons_text);
      TableReservationOrderInfo localTableReservationOrderInfo2 = (TableReservationOrderInfo)this.source.get(paramInt);
      String str4 = new SimpleDateFormat("E").format(localTableReservationOrderInfo2.orderDate) + ", " + new SimpleDateFormat("MMMMMMM").format(localTableReservationOrderInfo2.orderDate) + " " + new SimpleDateFormat("d").format(localTableReservationOrderInfo2.orderDate) + ", ";
      String str5;
      Object localObject2;
      if (Locale.getDefault().toString().equals("ru_RU"))
      {
        str5 = Utils.convertTimeToFormat(localTableReservationOrderInfo2.orderTime.houres, localTableReservationOrderInfo2.orderTime.minutes, true);
        localTextView3.setText(str4 + str5);
        if (!Locale.getDefault().toString().equals("en_EN"))
          break label362;
        localObject2 = this.context.getResources().getString(R.string.tablereservation_party_of) + " " + localTableReservationOrderInfo2.personsAmount;
      }
      while (true)
      {
        localTextView4.setText((CharSequence)localObject2);
        return localView1;
        str5 = Utils.convertTimeToFormat(localTableReservationOrderInfo2.orderTime.houres, localTableReservationOrderInfo2.orderTime.minutes, false);
        break;
        try
        {
          label362: PluralResources localPluralResources2 = new PluralResources(this.context.getResources());
          int k = R.plurals.orderTableForPerson;
          int m = localTableReservationOrderInfo2.personsAmount;
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = Integer.valueOf(localTableReservationOrderInfo2.personsAmount);
          String str6 = localPluralResources2.getQuantityString(k, m, arrayOfObject2);
          localObject2 = str6;
        }
        catch (NoSuchMethodException localNoSuchMethodException2)
        {
          localObject2 = "";
        }
      }
    }
    if (paramInt == 0)
    {
      ((TextView)paramView.findViewById(R.id.textView)).setText(this.context.getResources().getString(R.string.tablereservation_past));
      return paramView;
    }
    Drawable localDrawable1 = paramView.getBackground();
    localDrawable1.setAlpha(38);
    paramView.setBackgroundDrawable(localDrawable1);
    TextView localTextView1 = (TextView)paramView.findViewById(R.id.sergeyb_tablereservation_date_text);
    TextView localTextView2 = (TextView)paramView.findViewById(R.id.sergeyb_tablereservation_persons_text);
    TableReservationOrderInfo localTableReservationOrderInfo1 = (TableReservationOrderInfo)this.source.get(paramInt);
    String str1 = new SimpleDateFormat("E").format(localTableReservationOrderInfo1.orderDate) + ", " + new SimpleDateFormat("MMMMMMM").format(localTableReservationOrderInfo1.orderDate) + " " + new SimpleDateFormat("d").format(localTableReservationOrderInfo1.orderDate) + ", ";
    String str2;
    Object localObject1;
    if (Locale.getDefault().toString().equals("ru_RU"))
    {
      str2 = Utils.convertTimeToFormat(localTableReservationOrderInfo1.orderTime.houres, localTableReservationOrderInfo1.orderTime.minutes, true);
      localTextView1.setText(str1 + str2);
      if (!Locale.getDefault().toString().equals("en_EN"))
        break label762;
      localObject1 = this.context.getResources().getString(R.string.tablereservation_party_of) + " " + localTableReservationOrderInfo1.personsAmount;
    }
    while (true)
    {
      localTextView2.setText((CharSequence)localObject1);
      return paramView;
      str2 = Utils.convertTimeToFormat(localTableReservationOrderInfo1.orderTime.houres, localTableReservationOrderInfo1.orderTime.minutes, false);
      break;
      try
      {
        label762: PluralResources localPluralResources1 = new PluralResources(this.context.getResources());
        int i = R.plurals.orderTableForPerson;
        int j = localTableReservationOrderInfo1.personsAmount;
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = Integer.valueOf(localTableReservationOrderInfo1.personsAmount);
        String str3 = localPluralResources1.getQuantityString(i, j, arrayOfObject1);
        localObject1 = str3;
      }
      catch (NoSuchMethodException localNoSuchMethodException1)
      {
        localObject1 = "";
      }
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.TableReservationPlugin.TableReservationPastAdapter
 * JD-Core Version:    0.6.0
 */