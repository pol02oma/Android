package com.ibuildapp.romanblack.NewsPlugin;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Locale;

public class EventsAdapter extends BaseAdapter
{
  private static final String TAG = "com.ibuildapp.newsplugin.eventsadapter";
  private ArrayList<FeedItem> items;
  private LayoutInflater layoutInflater;

  EventsAdapter(Context paramContext, int paramInt1, ArrayList<FeedItem> paramArrayList, int paramInt2)
  {
    this.items = paramArrayList;
    this.layoutInflater = LayoutInflater.from(paramContext);
  }

  public int getCount()
  {
    return this.items.size();
  }

  public Object getItem(int paramInt)
  {
    return this.items.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null);
    for (View localView = this.layoutInflater.inflate(R.layout.romanblack_events_item, null); ; localView = paramView)
      try
      {
        TextView localTextView1 = (TextView)localView.findViewById(R.id.romanblack_rss_title);
        localTextView1.setTextColor(Statics.color3);
        TextView localTextView2 = (TextView)localView.findViewById(R.id.romanblack_rss_description);
        localTextView2.setTextColor(Statics.color4);
        TextView localTextView3 = (TextView)localView.findViewById(R.id.romanblack_rss_date);
        localTextView3.setTextColor(Statics.color4);
        if (Locale.getDefault().toString().equals("ru_RU"))
          localTextView3.setText(((FeedItem)this.items.get(paramInt)).getPubdate("EEE, d MMM yyyy HH:mm"));
        while (true)
        {
          TextView localTextView4 = (TextView)localView.findViewById(R.id.romanblack_rss_day);
          TextView localTextView5 = (TextView)localView.findViewById(R.id.romanblack_rss_month);
          localTextView5.setTextColor(Statics.color2);
          localTextView1.setText(((FeedItem)this.items.get(paramInt)).getTitle());
          localTextView2.setText(((FeedItem)this.items.get(paramInt)).getAnounce(75));
          localTextView4.setText(((FeedItem)this.items.get(paramInt)).getPubdate("dd"));
          localTextView5.setText(((FeedItem)this.items.get(paramInt)).getPubdate("MMM").toUpperCase());
          localView.setBackgroundColor(Statics.color1);
          return localView;
          localTextView3.setText(((FeedItem)this.items.get(paramInt)).getPubdate("EEE, d MMM yyyy hh:mm a"));
        }
      }
      catch (Exception localException)
      {
        Log.d("com.ibuildapp.newsplugin.eventsadapter", "", localException);
        return localView;
      }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.NewsPlugin.EventsAdapter
 * JD-Core Version:    0.6.0
 */