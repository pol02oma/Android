package com.ibuildapp.romanblack.TableReservationPlugin;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import com.appbuilder.sdk.android.authorization.entities.User;
import com.ibuildapp.romanblack.TableReservationPlugin.entity.HouresMinutes;
import com.ibuildapp.romanblack.TableReservationPlugin.utils.TableReservationHTTP;
import java.util.ArrayList;
import java.util.Date;

public class TableReservatioinListOfReservations extends AppBuilderModuleMain
{
  private final int CONNECTION_TIMEOUT = 3;
  private final int DRAW_UI = 2;
  private final int HIDE_PROGRESS_DIALOG = 1;
  private final int SHOW_PROGRESS_DIALOG = 0;
  private final int SUMMARY_ACTIVITY = 4;
  private int backColor;
  private int fontColor;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      default:
        return;
      case 0:
        TableReservatioinListOfReservations.this.showProgressDialog();
        return;
      case 1:
        TableReservatioinListOfReservations.this.hideProgressDialog();
        return;
      case 2:
        TableReservatioinListOfReservations.this.drawUI();
        return;
      case 3:
      }
      Toast localToast = Toast.makeText(TableReservatioinListOfReservations.this, R.string.tablereservation_warning_timeout, 1);
      localToast.setGravity(81, 0, 95);
      localToast.show();
    }
  };
  private LinearLayout mainLayout;
  private LinearLayout noPastLayout;
  private TextView noPastText;
  private TextView noUpcomingText;
  private LinearLayout noUpcominglayout;
  private Thread orderDownloader;
  private TableReservationInfo orderInfo;
  private orderParsedInfo orderList = new orderParsedInfo();
  private ListView pastList;
  private ArrayList<TableReservationOrderInfo> pastListData = new ArrayList();
  private ProgressDialog progressDialog = null;
  private ListView upcomingList;
  private ArrayList<TableReservationOrderInfo> upcomingListData = new ArrayList();
  private User user;

  private void drawUI()
  {
    if (this.orderList == null)
      return;
    Long localLong = Long.valueOf(new Date().getTime());
    int i = 0;
    if (i < this.orderList.itemsArray.size())
    {
      TableReservationOrderInfo localTableReservationOrderInfo = (TableReservationOrderInfo)this.orderList.itemsArray.get(i);
      Date localDate = localTableReservationOrderInfo.orderDate;
      localDate.setHours(localTableReservationOrderInfo.orderTime.houres);
      localDate.setMinutes(localTableReservationOrderInfo.orderTime.minutes);
      if ((localTableReservationOrderInfo.status == 1) || (localTableReservationOrderInfo.status == 2))
      {
        if (Long.valueOf(localDate.getTime()).longValue() <= localLong.longValue())
          break label140;
        this.upcomingListData.add(localTableReservationOrderInfo);
      }
      while (true)
      {
        i++;
        break;
        label140: this.pastListData.add(localTableReservationOrderInfo);
      }
    }
    if (this.upcomingListData.size() == 0)
      this.noUpcominglayout.setVisibility(0);
    while (this.pastListData.size() == 0)
    {
      this.noPastLayout.setVisibility(0);
      return;
      TableReservationUpcomingAdapter localTableReservationUpcomingAdapter1 = new TableReservationUpcomingAdapter(this, this.fontColor, this.upcomingListData);
      this.upcomingList.setAdapter(localTableReservationUpcomingAdapter1);
    }
    TableReservationUpcomingAdapter localTableReservationUpcomingAdapter2 = new TableReservationUpcomingAdapter(this, this.fontColor, this.pastListData);
    this.pastList.setAdapter(localTableReservationUpcomingAdapter2);
  }

  private void hideProgressDialog()
  {
    if (this.progressDialog != null)
    {
      this.progressDialog.dismiss();
      this.progressDialog = null;
    }
  }

  private void showProgressDialog()
  {
    if (this.progressDialog == null)
      this.progressDialog = ProgressDialog.show(this, null, getResources().getString(R.string.common_loading_upper), true, true, new DialogInterface.OnCancelListener()
      {
        public void onCancel(DialogInterface paramDialogInterface)
        {
          try
          {
            if ((TableReservatioinListOfReservations.this.orderDownloader != null) && (TableReservatioinListOfReservations.this.orderDownloader.isAlive()))
              TableReservatioinListOfReservations.this.orderDownloader.interrupt();
            TableReservatioinListOfReservations.this.progressDialog.dismiss();
            TableReservatioinListOfReservations.access$1202(TableReservatioinListOfReservations.this, null);
            return;
          }
          catch (Exception localException)
          {
            Log.d("", "");
          }
        }
      });
  }

  public void create()
  {
    setContentView(R.layout.sergeyb_tablereservation_listofreservations);
    setTopBarTitle(getResources().getString(R.string.tablereservation_my_reservations));
    swipeBlock();
    setTopBarLeftButtonText(getResources().getString(R.string.common_back_upper), true, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        TableReservatioinListOfReservations.this.finish();
      }
    });
    this.upcomingList = ((ListView)findViewById(R.id.sergeyb_tablereservation_upcoming));
    this.upcomingList.setCacheColorHint(0);
    this.upcomingList.setSelector(getResources().getDrawable(R.drawable.sergeyb_tablereservation_custom_background));
    this.pastList = ((ListView)findViewById(R.id.sergeyb_tablereservation_past));
    this.pastList.setCacheColorHint(0);
    this.pastList.setSelector(getResources().getDrawable(R.drawable.sergeyb_tablereservation_custom_background));
    this.mainLayout = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_main_layout));
    this.noUpcomingText = ((TextView)findViewById(R.id.sergeyb_tablereservation_no_upcoming_text));
    this.noPastText = ((TextView)findViewById(R.id.sergeyb_tablereservation_no_past_text));
    this.noUpcominglayout = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_no_upcoming));
    this.noPastLayout = ((LinearLayout)findViewById(R.id.sergeyb_tablereservation_no_past));
    Intent localIntent = getIntent();
    this.orderInfo = ((TableReservationInfo)localIntent.getSerializableExtra("xml"));
    this.fontColor = localIntent.getIntExtra("fontColor", -1);
    this.backColor = localIntent.getIntExtra("backColor", Color.parseColor("#37393b"));
    this.user = ((User)localIntent.getSerializableExtra("userinfo"));
    this.mainLayout.setBackgroundColor(this.backColor);
    this.noUpcomingText.setTextColor(this.fontColor);
    this.noPastText.setTextColor(this.fontColor);
    NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo == null) || (!localNetworkInfo.isConnectedOrConnecting()))
    {
      Toast localToast = Toast.makeText(this, R.string.alert_no_internet, 1);
      localToast.setGravity(81, 0, 95);
      localToast.show();
      finish();
    }
    this.handler.sendEmptyMessage(0);
    this.orderDownloader = new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          String str = TableReservationHTTP.sendListOrdersRequest(TableReservatioinListOfReservations.this.user, TableReservatioinListOfReservations.this.orderInfo);
          if (str.compareToIgnoreCase("error") != 0)
          {
            TableReservatioinListOfReservations.access$502(TableReservatioinListOfReservations.this, JSONParser.parseOrderList(str));
            TableReservatioinListOfReservations.this.handler.sendEmptyMessage(2);
          }
          TableReservatioinListOfReservations.this.handler.sendEmptyMessage(1);
          return;
        }
        catch (Exception localException)
        {
          Log.d("", "");
        }
      }
    });
    this.orderDownloader.start();
    this.upcomingList.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        TableReservationOrderInfo localTableReservationOrderInfo = (TableReservationOrderInfo)TableReservatioinListOfReservations.this.upcomingListData.get(paramInt);
        Intent localIntent = new Intent(TableReservatioinListOfReservations.this, TableReservationSummary.class);
        localIntent.putExtra("fontColor", TableReservatioinListOfReservations.this.fontColor);
        localIntent.putExtra("backColor", TableReservatioinListOfReservations.this.backColor);
        TableReservatioinListOfReservations.this.orderInfo.setOrderDate(localTableReservationOrderInfo.orderDate);
        TableReservatioinListOfReservations.this.orderInfo.setOrderTime(localTableReservationOrderInfo.orderTime.houres, localTableReservationOrderInfo.orderTime.minutes, localTableReservationOrderInfo.orderTime.am_pm);
        TableReservatioinListOfReservations.this.orderInfo.setPersonsAmount(localTableReservationOrderInfo.personsAmount);
        TableReservatioinListOfReservations.this.orderInfo.setSpecialRequest(localTableReservationOrderInfo.specRequest);
        localIntent.putExtra("xml", TableReservatioinListOfReservations.this.orderInfo);
        localIntent.putExtra("userinfo", TableReservatioinListOfReservations.this.user);
        localIntent.putExtra("orderUUID", localTableReservationOrderInfo.uuid);
        TableReservatioinListOfReservations.this.startActivityForResult(localIntent, 4);
      }
    });
    this.pastList.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        TableReservationOrderInfo localTableReservationOrderInfo = (TableReservationOrderInfo)TableReservatioinListOfReservations.this.pastListData.get(paramInt);
        Intent localIntent = new Intent(TableReservatioinListOfReservations.this, TableReservationSummary.class);
        localIntent.putExtra("fontColor", TableReservatioinListOfReservations.this.fontColor);
        localIntent.putExtra("backColor", TableReservatioinListOfReservations.this.backColor);
        localIntent.putExtra("reservationTIme", "past");
        TableReservatioinListOfReservations.this.orderInfo.setOrderDate(localTableReservationOrderInfo.orderDate);
        TableReservatioinListOfReservations.this.orderInfo.setOrderTime(localTableReservationOrderInfo.orderTime.houres, localTableReservationOrderInfo.orderTime.minutes, localTableReservationOrderInfo.orderTime.am_pm);
        TableReservatioinListOfReservations.this.orderInfo.setPersonsAmount(localTableReservationOrderInfo.personsAmount);
        TableReservatioinListOfReservations.this.orderInfo.setSpecialRequest(localTableReservationOrderInfo.specRequest);
        localIntent.putExtra("xml", TableReservatioinListOfReservations.this.orderInfo);
        localIntent.putExtra("userinfo", TableReservatioinListOfReservations.this.user);
        localIntent.putExtra("orderUUID", localTableReservationOrderInfo.uuid);
        TableReservatioinListOfReservations.this.startActivityForResult(localIntent, 4);
      }
    });
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    switch (paramInt1)
    {
    default:
    case 4:
    }
    String str;
    do
    {
      do
        return;
      while (paramInt2 != -1);
      str = paramIntent.getStringExtra("howtoclose");
    }
    while ((str.length() == 0) || (str == null));
    finish();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.TableReservationPlugin.TableReservatioinListOfReservations
 * JD-Core Version:    0.6.0
 */