package com.ibuildapp.romanblack.MenuPlugin;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.appbuilder.sdk.android.Utils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MenuPluginMainListAdapter extends BaseAdapter
{
  private AssetManager assetMgr;
  private String cachePath;
  private MenuPluginXmlClasses.ColorSkin colorSkin;
  private Context context;
  private ImageDownloadTask downloadTask = null;
  private LayoutInflater layoutInflater;
  private boolean showImages;
  private ArrayList<MenuPluginXmlClasses.MenuCategory> source;

  MenuPluginMainListAdapter(Context paramContext, ArrayList<MenuPluginXmlClasses.MenuCategory> paramArrayList, String paramString, boolean paramBoolean, MenuPluginXmlClasses.ColorSkin paramColorSkin)
  {
    this.showImages = paramBoolean;
    this.context = paramContext;
    this.colorSkin = paramColorSkin;
    this.source = paramArrayList;
    this.cachePath = paramString;
    this.assetMgr = paramContext.getAssets();
    this.layoutInflater = LayoutInflater.from(paramContext);
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
    return ((MenuPluginXmlClasses.MenuCategory)this.source.get(paramInt)).hashCode();
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = paramView;
    if (localView == null)
      localView = this.layoutInflater.inflate(R.layout.sergeyb_menuplugin_main_list_row, null);
    if (this.showImages)
    {
      ImageView localImageView = (ImageView)localView.findViewById(R.id.sergeyb_menuplugin_item_image);
      ProgressBar localProgressBar = (ProgressBar)localView.findViewById(R.id.sergeyb_menuplugin_item_progress);
      if (((MenuPluginXmlClasses.MenuCategory)this.source.get(paramInt)).getCategoryBitmap() != null)
      {
        localImageView.setImageBitmap(((MenuPluginXmlClasses.MenuCategory)this.source.get(paramInt)).getCategoryBitmap());
        localProgressBar.setVisibility(8);
      }
    }
    while (true)
    {
      TextView localTextView = (TextView)localView.findViewById(R.id.sergeyb_menuplugin_item_text);
      localTextView.setTextColor(this.colorSkin.color2);
      localTextView.setText(((MenuPluginXmlClasses.MenuCategory)this.source.get(paramInt)).getCategoryName());
      return localView;
      ((FrameLayout)localView.findViewById(R.id.sergeyb_menuplugin_img_layout)).setVisibility(8);
    }
  }

  private class ImageDownloadTask extends AsyncTask<ArrayList<MenuPluginXmlClasses.MenuCategory>, String, Void>
  {
    private ImageDownloadTask()
    {
    }

    protected Void doInBackground(ArrayList<MenuPluginXmlClasses.MenuCategory>[] paramArrayOfArrayList)
    {
      int i = 0;
      try
      {
        while (true)
        {
          int j = paramArrayOfArrayList[0].size();
          if (i < j)
            try
            {
              String str3 = ((MenuPluginXmlClasses.MenuCategory)paramArrayOfArrayList[0].get(i)).getCategoryResFile();
              InputStream localInputStream2;
              if ((str3 != null) && (!str3.equals("")))
                localInputStream2 = MenuPluginMainListAdapter.this.assetMgr.open(str3);
              for (localInputStream1 = localInputStream2; ; localInputStream1 = null)
              {
                if (localInputStream1 != null)
                {
                  Bitmap localBitmap4 = BitmapFactory.decodeStream(localInputStream1);
                  ((MenuPluginXmlClasses.MenuCategory)paramArrayOfArrayList[0].get(i)).setCategoryBitmap(localBitmap4);
                  publishProgress(new String[0]);
                }
                else
                {
                  String str1 = MenuPluginMainListAdapter.this.cachePath + File.separator + Utils.md5(((MenuPluginXmlClasses.MenuCategory)paramArrayOfArrayList[0].get(i)).getCategoryImg()) + ".jpg";
                  File localFile = new File(str1);
                  if (localFile.exists())
                  {
                    Bitmap localBitmap3 = MenuPluginUtils.proccessBitmap(localFile.getAbsolutePath());
                    ((MenuPluginXmlClasses.MenuCategory)paramArrayOfArrayList[0].get(i)).setCategoryBitmap(localBitmap3);
                    ((MenuPluginXmlClasses.MenuCategory)paramArrayOfArrayList[0].get(i)).setCategoryImgFileName(localFile.getAbsolutePath());
                    publishProgress(new String[0]);
                  }
                  else
                  {
                    String str2 = MenuPluginUtils.downloadImg(((MenuPluginXmlClasses.MenuCategory)paramArrayOfArrayList[0].get(i)).getCategoryImg(), str1);
                    if (str2 != null)
                    {
                      Bitmap localBitmap1 = MenuPluginUtils.proccessBitmap(str2);
                      ((MenuPluginXmlClasses.MenuCategory)paramArrayOfArrayList[0].get(i)).setCategoryBitmap(localBitmap1);
                      ((MenuPluginXmlClasses.MenuCategory)paramArrayOfArrayList[0].get(i)).setCategoryImgFileName(localFile.getAbsolutePath());
                      publishProgress(new String[0]);
                    }
                    else
                    {
                      Bitmap localBitmap2 = BitmapFactory.decodeResource(MenuPluginMainListAdapter.this.context.getResources(), R.drawable.sergeyb_menuplugin_no_image);
                      ((MenuPluginXmlClasses.MenuCategory)paramArrayOfArrayList[0].get(i)).setCategoryBitmap(localBitmap2);
                      publishProgress(new String[0]);
                    }
                  }
                }
                i++;
                break;
              }
            }
            catch (IOException localIOException)
            {
              while (true)
                InputStream localInputStream1 = null;
            }
        }
      }
      catch (Exception localException)
      {
      }
      return null;
    }

    protected void onPostExecute(Void paramVoid)
    {
    }

    protected void onProgressUpdate(String[] paramArrayOfString)
    {
      MenuPluginMainListAdapter.this.viewUpdated();
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.MenuPlugin.MenuPluginMainListAdapter
 * JD-Core Version:    0.6.0
 */