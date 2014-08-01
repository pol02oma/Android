package com.ibuildapp.romanblack.MenuPlugin;

import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import java.io.Serializable;
import java.util.ArrayList;

public class MenuPluginItemsList extends AppBuilderModuleMain
{
  private String appid;
  private String appname;
  private String cachePath;
  private ArrayList<MenuPluginXmlClasses.categoryItem> categoryItems;
  private String categoryName;
  private MenuPluginXmlClasses.ColorSkin colorskin;
  private String currency;
  private String currencyposition;
  private boolean hasAd;
  private ListView itemsList;
  private LinearLayout root;
  private boolean showImages = false;

  private void drawUI()
  {
    MenuPluginItemsListAdapter localMenuPluginItemsListAdapter = new MenuPluginItemsListAdapter(this, this.categoryItems, this.currency, this.colorskin.color3, this.colorskin.color4, this.colorskin.color5, this.showImages, this.cachePath, this.currencyposition);
    this.itemsList.setAdapter(localMenuPluginItemsListAdapter);
  }

  public void create()
  {
    requestWindowFeature(1);
    setContentView(R.layout.sergeyb_menuplugin_items_list);
    getWindow().setFlags(1024, 1024);
    this.itemsList = ((ListView)findViewById(R.id.sergeyb_menuplugin_items_listview));
    this.root = ((LinearLayout)findViewById(R.id.sergeyb_menuplugin_root));
    this.itemsList.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        Intent localIntent = new Intent(MenuPluginItemsList.this, MenuPluginItemDetails.class);
        localIntent.putExtra("itemInfo", (Serializable)MenuPluginItemsList.this.categoryItems.get(paramInt));
        localIntent.putExtra("categoryName", MenuPluginItemsList.this.categoryName);
        localIntent.putExtra("colorskin", MenuPluginItemsList.this.colorskin);
        localIntent.putExtra("currency", MenuPluginItemsList.this.currency);
        localIntent.putExtra("hasAd", MenuPluginItemsList.this.hasAd);
        localIntent.putExtra("appname", MenuPluginItemsList.this.appname);
        localIntent.putExtra("appid", MenuPluginItemsList.this.appid);
        localIntent.putExtra("cachePath", MenuPluginItemsList.this.cachePath);
        localIntent.putExtra("currencyposition", MenuPluginItemsList.this.currencyposition);
        MenuPluginItemsList.this.startActivity(localIntent);
      }
    });
    Intent localIntent = getIntent();
    this.categoryName = localIntent.getStringExtra("categoryName");
    this.categoryItems = ((ArrayList)localIntent.getSerializableExtra("categoryItems"));
    this.cachePath = localIntent.getStringExtra("cachePath");
    this.colorskin = ((MenuPluginXmlClasses.ColorSkin)localIntent.getSerializableExtra("colorskin"));
    this.currency = localIntent.getStringExtra("currency");
    this.hasAd = localIntent.getBooleanExtra("hasAd", true);
    this.appname = localIntent.getStringExtra("appname");
    this.appid = localIntent.getStringExtra("appid");
    this.root.setBackgroundColor(this.colorskin.color1);
    this.currencyposition = localIntent.getStringExtra("currencyposition");
    for (int i = 0; ; i++)
    {
      if (i < this.categoryItems.size())
      {
        String str = ((MenuPluginXmlClasses.categoryItem)this.categoryItems.get(i)).getItemUrl();
        if ((str == null) || (str.equals("")))
          continue;
        this.showImages = true;
      }
      setTopBarLeftButtonText(getResources().getString(R.string.common_back_upper), true, new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          MenuPluginItemsList.this.finish();
        }
      });
      setTopBarTitle(this.categoryName);
      swipeBlock();
      drawUI();
      return;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.MenuPlugin.MenuPluginItemsList
 * JD-Core Version:    0.6.0
 */