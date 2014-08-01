package com.appbuilder.sdk.android;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import java.util.ArrayList;

public class MenuAdapter extends BaseAdapter
{
  private int IMAGE_ID = 10001;
  private int SUBTITLE_ID = 10003;
  private int TITLE_ID = 10002;
  private Context context;
  private float density;
  private ImageView image;
  private LayoutInflater layoutInflater;
  private TextView subtitle;
  private ArrayList<Bitmap> thumbnails = new ArrayList();
  private TextView title;
  private ArrayList<Widget> widgets;

  public MenuAdapter(Context paramContext, ArrayList<Widget> paramArrayList, ArrayList<Bitmap> paramArrayList1)
  {
    this.context = paramContext;
    this.widgets = paramArrayList;
    this.thumbnails = paramArrayList1;
    this.layoutInflater = LayoutInflater.from(paramContext);
    this.density = paramContext.getResources().getDisplayMetrics().density;
  }

  private View customView()
  {
    LinearLayout localLinearLayout1 = new LinearLayout(this.context);
    localLinearLayout1.setOrientation(0);
    LinearLayout localLinearLayout2 = new LinearLayout(this.context);
    LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-2, -2);
    localLinearLayout2.setOrientation(0);
    localLinearLayout2.setLayoutParams(localLayoutParams1);
    LinearLayout localLinearLayout3 = new LinearLayout(this.context);
    LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-2, -2);
    localLayoutParams2.setMargins((int)(6.0F * this.density), (int)(6.0F * this.density), (int)(6.0F * this.density), (int)(6.0F * this.density));
    localLinearLayout2.setOrientation(0);
    localLinearLayout2.setLayoutParams(localLayoutParams2);
    this.image = new ImageView(this.context);
    this.image.setId(this.IMAGE_ID);
    LinearLayout.LayoutParams localLayoutParams3 = new LinearLayout.LayoutParams((int)(60.0F * this.density), (int)(60.0F * this.density));
    this.image.setLayoutParams(localLayoutParams3);
    this.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
    localLinearLayout3.addView(this.image);
    localLinearLayout2.addView(localLinearLayout3);
    LinearLayout localLinearLayout4 = new LinearLayout(this.context);
    LinearLayout.LayoutParams localLayoutParams4 = new LinearLayout.LayoutParams(-1, -1);
    localLayoutParams4.setMargins((int)(6.0F * this.density), 0, 0, 0);
    localLinearLayout4.setOrientation(1);
    localLinearLayout4.setLayoutParams(localLayoutParams4);
    RelativeLayout localRelativeLayout = new RelativeLayout(this.context);
    LinearLayout.LayoutParams localLayoutParams5 = new LinearLayout.LayoutParams(-1, -2);
    localLayoutParams5.setMargins(0, (int)(4.0F * this.density), 0, 0);
    localLayoutParams5.gravity = 5;
    localRelativeLayout.setLayoutParams(localLayoutParams5);
    this.title = new TextView(this.context);
    this.title.setId(this.TITLE_ID);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams.setMargins(0, 0, (int)(6.0F * this.density), 0);
    this.title.setLayoutParams(localLayoutParams);
    this.title.setTextSize(2, 16.0F);
    this.title.setTypeface(Typeface.DEFAULT_BOLD);
    this.title.setSingleLine();
    localRelativeLayout.addView(this.title);
    LinearLayout localLinearLayout5 = new LinearLayout(this.context);
    LinearLayout.LayoutParams localLayoutParams6 = new LinearLayout.LayoutParams(-1, -2);
    localLayoutParams6.setMargins(0, (int)(2.0F * this.density), 0, 0);
    localLinearLayout5.setOrientation(1);
    localLinearLayout5.setLayoutParams(localLayoutParams6);
    this.subtitle = new TextView(this.context);
    this.subtitle.setId(this.SUBTITLE_ID);
    LinearLayout.LayoutParams localLayoutParams7 = new LinearLayout.LayoutParams(-2, -2);
    localLayoutParams7.setMargins(0, 0, (int)(10.0F * this.density), 0);
    this.subtitle.setTextSize(2, 13.0F);
    this.subtitle.setMaxLines(2);
    this.subtitle.setLayoutParams(localLayoutParams7);
    localLinearLayout5.addView(this.subtitle);
    localLinearLayout4.addView(localRelativeLayout);
    localLinearLayout4.addView(localLinearLayout5);
    localLinearLayout1.addView(localLinearLayout2);
    localLinearLayout1.addView(localLinearLayout4);
    return localLinearLayout1;
  }

  // ERROR //
  public static Bitmap proccessBitmap(String paramString)
  {
    // Byte code:
    //   0: new 171	android/graphics/BitmapFactory$Options
    //   3: dup
    //   4: invokespecial 172	android/graphics/BitmapFactory$Options:<init>	()V
    //   7: astore_1
    //   8: new 174	java/io/File
    //   11: dup
    //   12: aload_0
    //   13: invokespecial 177	java/io/File:<init>	(Ljava/lang/String;)V
    //   16: astore_2
    //   17: invokestatic 182	java/lang/System:gc	()V
    //   20: new 184	java/io/FileInputStream
    //   23: dup
    //   24: aload_2
    //   25: invokespecial 187	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   28: aconst_null
    //   29: aload_1
    //   30: invokestatic 193	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   33: astore 15
    //   35: aload 15
    //   37: astore 7
    //   39: aload 7
    //   41: areturn
    //   42: astore 13
    //   44: ldc 195
    //   46: ldc 195
    //   48: invokestatic 201	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   51: pop
    //   52: aconst_null
    //   53: astore 7
    //   55: goto -16 -> 39
    //   58: astore 8
    //   60: ldc 195
    //   62: ldc 195
    //   64: invokestatic 201	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   67: pop
    //   68: aconst_null
    //   69: areturn
    //   70: astore_3
    //   71: ldc 195
    //   73: ldc 195
    //   75: invokestatic 201	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   78: pop
    //   79: invokestatic 182	java/lang/System:gc	()V
    //   82: new 184	java/io/FileInputStream
    //   85: dup
    //   86: aload_2
    //   87: invokespecial 187	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   90: aconst_null
    //   91: aload_1
    //   92: invokestatic 193	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   95: astore 12
    //   97: aload 12
    //   99: astore 7
    //   101: goto -62 -> 39
    //   104: astore 10
    //   106: ldc 195
    //   108: ldc 195
    //   110: invokestatic 201	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   113: pop
    //   114: aconst_null
    //   115: astore 7
    //   117: goto -78 -> 39
    //   120: astore 5
    //   122: ldc 203
    //   124: ldc 205
    //   126: invokestatic 208	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   129: pop
    //   130: aconst_null
    //   131: astore 7
    //   133: goto -94 -> 39
    //
    // Exception table:
    //   from	to	target	type
    //   17	35	42	java/lang/Exception
    //   8	17	58	java/lang/Exception
    //   44	52	58	java/lang/Exception
    //   71	82	58	java/lang/Exception
    //   106	114	58	java/lang/Exception
    //   122	130	58	java/lang/Exception
    //   17	35	70	java/lang/OutOfMemoryError
    //   82	97	104	java/lang/Exception
    //   82	97	120	java/lang/OutOfMemoryError
  }

  public int getCount()
  {
    return this.widgets.size();
  }

  public Object getItem(int paramInt)
  {
    return this.widgets.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return ((Widget)this.widgets.get(paramInt)).hashCode();
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = paramView;
    if (localView == null)
      localView = customView();
    while (true)
    {
      if ((((Widget)this.widgets.get(paramInt)).getTitle() != null) && (((Widget)this.widgets.get(paramInt)).getTitle().equals("") != true))
      {
        this.title.setText(((Widget)this.widgets.get(paramInt)).getTitle());
        label75: if ((((Widget)this.widgets.get(paramInt)).getSubtitle() == null) || (((Widget)this.widgets.get(paramInt)).getSubtitle().equals("") == true))
          break label219;
        this.subtitle.setText(((Widget)this.widgets.get(paramInt)).getSubtitle());
      }
      try
      {
        while (true)
        {
          this.image.setImageBitmap((Bitmap)this.thumbnails.get(paramInt));
          return localView;
          this.image = ((ImageView)localView.findViewById(this.IMAGE_ID));
          this.title = ((TextView)localView.findViewById(this.TITLE_ID));
          this.subtitle = ((TextView)localView.findViewById(this.SUBTITLE_ID));
          break;
          this.title.setVisibility(4);
          break label75;
          label219: this.subtitle.setVisibility(4);
        }
      }
      catch (Exception localException)
      {
        Log.d("", "");
      }
    }
    return localView;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.sdk.android.MenuAdapter
 * JD-Core Version:    0.6.0
 */