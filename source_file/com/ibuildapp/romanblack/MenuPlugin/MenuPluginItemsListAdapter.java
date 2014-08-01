package com.ibuildapp.romanblack.MenuPlugin;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.appbuilder.sdk.android.Utils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MenuPluginItemsListAdapter extends BaseAdapter
{
  private AssetManager assetMgr;
  private String cachePath;
  private Context context;
  private String currency;
  private String currencyposition;
  private ImageDownloadTask downloadTask = null;
  private int itemDescColor;
  private int itemNameColor;
  private int itemPriceColor;
  private LayoutInflater layoutInflater;
  private boolean showImages = false;
  private ArrayList<MenuPluginXmlClasses.categoryItem> source;
  private ArrayList<Bitmap> thumbnails;

  MenuPluginItemsListAdapter(Context paramContext, ArrayList<MenuPluginXmlClasses.categoryItem> paramArrayList, String paramString1, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, String paramString2, String paramString3)
  {
    this.currencyposition = paramString3;
    this.cachePath = paramString2;
    this.showImages = paramBoolean;
    this.context = paramContext;
    this.source = paramArrayList;
    this.thumbnails = new ArrayList();
    this.currency = paramString1;
    this.itemPriceColor = paramInt3;
    this.itemNameColor = paramInt1;
    this.itemDescColor = paramInt2;
    this.layoutInflater = LayoutInflater.from(paramContext);
    this.assetMgr = paramContext.getAssets();
    this.downloadTask = new ImageDownloadTask(null);
    this.downloadTask.execute(new ArrayList[] { paramArrayList });
  }

  private void viewUpdated()
  {
    notifyDataSetChanged();
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
    return ((MenuPluginXmlClasses.categoryItem)this.source.get(paramInt)).hashCode();
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = paramView;
    if (localView == null)
      localView = this.layoutInflater.inflate(R.layout.sergeyb_menuplugin_items_list_item, null);
    TextView localTextView1 = (TextView)localView.findViewById(R.id.sergeyb_menuplugin_item_name);
    TextView localTextView2 = (TextView)localView.findViewById(R.id.sergeyb_menuplugin_item_price);
    TextView localTextView3 = (TextView)localView.findViewById(R.id.sergeyb_menuplugin_item_description);
    ImageView localImageView;
    ProgressBar localProgressBar;
    if (this.showImages)
    {
      localImageView = (ImageView)localView.findViewById(R.id.sergeyb_menuplugin_item_image);
      localProgressBar = (ProgressBar)localView.findViewById(R.id.sergeyb_menuplugin_item_progress);
    }
    while (true)
    {
      try
      {
        localImageView.setImageBitmap((Bitmap)this.thumbnails.get(paramInt));
        localProgressBar.setVisibility(8);
        localTextView1.setTextColor(this.itemNameColor);
        localTextView2.setTextColor(this.itemPriceColor);
        localTextView3.setTextColor(this.itemDescColor);
        localTextView1.setText(((MenuPluginXmlClasses.categoryItem)this.source.get(paramInt)).getItemName());
        localTextView3.setText(((MenuPluginXmlClasses.categoryItem)this.source.get(paramInt)).getItemDescripton());
        if ((this.currency == null) || (this.currency.length() == 0))
          break label413;
        if ((localTextView2 == null) || (localTextView2.length() == 0))
          break label403;
        if (this.currencyposition == null)
          break;
        if (!this.currencyposition.equals("left"))
          break label309;
        localTextView2.setText(this.currency + " " + ((MenuPluginXmlClasses.categoryItem)this.source.get(paramInt)).getItemPrice());
        return localView;
      }
      catch (Exception localException)
      {
        Log.d("", "");
        continue;
      }
      ((LinearLayout)localView.findViewById(R.id.sergeyb_menuplugin_image_placeholder)).setVisibility(8);
      continue;
      label309: localTextView2.setText(((MenuPluginXmlClasses.categoryItem)this.source.get(paramInt)).getItemPrice() + " " + this.currency);
      return localView;
    }
    localTextView2.setText(this.currency + " " + ((MenuPluginXmlClasses.categoryItem)this.source.get(paramInt)).getItemPrice());
    return localView;
    label403: localTextView2.setVisibility(8);
    return localView;
    label413: localTextView2.setVisibility(8);
    return localView;
  }

  private class ImageDownloadTask extends AsyncTask<ArrayList<MenuPluginXmlClasses.categoryItem>, String, Void>
  {
    private ImageDownloadTask()
    {
    }

    protected Void doInBackground(ArrayList<MenuPluginXmlClasses.categoryItem>[] paramArrayOfArrayList)
    {
      int i = 0;
      while (true)
        if (i < paramArrayOfArrayList[0].size())
          try
          {
            String str3 = ((MenuPluginXmlClasses.categoryItem)paramArrayOfArrayList[0].get(i)).getItemThumbnailResPath();
            InputStream localInputStream2;
            if ((str3 != null) && (!str3.equals("")))
              localInputStream2 = MenuPluginItemsListAdapter.this.assetMgr.open(str3);
            for (localInputStream1 = localInputStream2; ; localInputStream1 = null)
            {
              if (localInputStream1 == null)
                break label112;
              Bitmap localBitmap4 = BitmapFactory.decodeStream(localInputStream1);
              MenuPluginItemsListAdapter.this.thumbnails.add(i, localBitmap4);
              publishProgress(new String[0]);
              i++;
              break;
            }
          }
          catch (IOException localIOException)
          {
            while (true)
            {
              InputStream localInputStream1 = null;
              continue;
              label112: String str1 = MenuPluginItemsListAdapter.this.cachePath + File.separator + Utils.md5(((MenuPluginXmlClasses.categoryItem)paramArrayOfArrayList[0].get(i)).getItemThumbnailUrl()) + ".jpg";
              File localFile = new File(str1);
              if (localFile.exists())
              {
                Bitmap localBitmap3 = MenuPluginUtils.proccessBitmap(localFile.getAbsolutePath());
                MenuPluginItemsListAdapter.this.thumbnails.add(i, localBitmap3);
                ((MenuPluginXmlClasses.categoryItem)paramArrayOfArrayList[0].get(i)).setItemThumbnailPath(localFile.getAbsolutePath());
                publishProgress(new String[0]);
                continue;
              }
              String str2 = MenuPluginUtils.downloadImg(((MenuPluginXmlClasses.categoryItem)paramArrayOfArrayList[0].get(i)).getItemThumbnailUrl(), str1);
              if (str2 != null)
              {
                Bitmap localBitmap2 = MenuPluginUtils.proccessBitmap(str2);
                MenuPluginItemsListAdapter.this.thumbnails.add(i, localBitmap2);
                ((MenuPluginXmlClasses.categoryItem)paramArrayOfArrayList[0].get(i)).setItemThumbnailPath(localFile.getAbsolutePath());
                publishProgress(new String[0]);
                continue;
              }
              Bitmap localBitmap1 = BitmapFactory.decodeResource(MenuPluginItemsListAdapter.this.context.getResources(), R.drawable.sergeyb_menuplugin_no_image);
              MenuPluginItemsListAdapter.this.thumbnails.add(i, localBitmap1);
              publishProgress(new String[0]);
            }
          }
      return null;
    }

    protected void onPostExecute(Void paramVoid)
    {
    }

    protected void onProgressUpdate(String[] paramArrayOfString)
    {
      MenuPluginItemsListAdapter.this.viewUpdated();
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.MenuPlugin.MenuPluginItemsListAdapter
 * JD-Core Version:    0.6.0
 */