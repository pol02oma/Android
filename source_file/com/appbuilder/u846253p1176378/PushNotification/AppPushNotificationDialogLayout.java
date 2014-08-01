package com.appbuilder.u846253p1176378.PushNotification;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class AppPushNotificationDialogLayout extends FrameLayout
{
  private final int CLOSE_BTN_SIZE = 15;
  private final int CORNER_RADIUS = 10;
  private final double DIALOG_PERCENT = 0.9D;
  private final int ROOT_LAYOUT_PADDING = 18;
  private final int TEXT_MARGIN = 10;
  private final int TITLE_RIGHT_LEFT_MARGIN = 30;
  private View.OnClickListener closeBtnEvent;
  private Context context;
  private float density;
  private View.OnClickListener imageTapEvent;
  private String imgPath;
  private String message;
  private int screenHeight;
  private int screenWidth;
  private String titleText;

  public AppPushNotificationDialogLayout(Context paramContext, AttributeSet paramAttributeSet, String paramString1, String paramString2, String paramString3, View.OnClickListener paramOnClickListener1, View.OnClickListener paramOnClickListener2)
  {
    super(paramContext, paramAttributeSet);
    this.message = paramString2;
    this.titleText = paramString1;
    this.imgPath = paramString3;
    this.closeBtnEvent = paramOnClickListener1;
    this.imageTapEvent = paramOnClickListener2;
    this.context = paramContext;
    this.density = paramContext.getResources().getDisplayMetrics().density;
    Display localDisplay = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    this.screenWidth = localDisplay.getWidth();
    this.screenHeight = localDisplay.getHeight();
    initialize();
  }

  public AppPushNotificationDialogLayout(Context paramContext, String paramString1, String paramString2, String paramString3, View.OnClickListener paramOnClickListener1, View.OnClickListener paramOnClickListener2)
  {
    super(paramContext);
    this.message = paramString2;
    this.imgPath = paramString3;
    this.titleText = paramString1;
    this.closeBtnEvent = paramOnClickListener1;
    this.imageTapEvent = paramOnClickListener2;
    this.context = paramContext;
    this.density = paramContext.getResources().getDisplayMetrics().density;
    Display localDisplay = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    this.screenWidth = localDisplay.getWidth();
    this.screenHeight = localDisplay.getHeight();
    initialize();
  }

  private void initialize()
  {
    setLayoutParams(new FrameLayout.LayoutParams((int)(0.9D * this.screenWidth), -2));
    GradientDrawable.Orientation localOrientation = GradientDrawable.Orientation.BOTTOM_TOP;
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = Color.parseColor("#E5ffffff");
    arrayOfInt[1] = Color.parseColor("#E5ffffff");
    GradientDrawable localGradientDrawable = new GradientDrawable(localOrientation, arrayOfInt);
    float f = 10.0F * this.density;
    localGradientDrawable.setCornerRadii(new float[] { f, f, f, f, f, f, f, f });
    setBackgroundDrawable(localGradientDrawable);
    if (this.imageTapEvent != null)
      setOnClickListener(this.imageTapEvent);
    LinearLayout localLinearLayout1 = new LinearLayout(this.context);
    localLinearLayout1.setOrientation(1);
    localLinearLayout1.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
    int i = (int)(18.0F * this.density);
    localLinearLayout1.setPadding(i, i, i, i);
    TextView localTextView1 = new TextView(this.context);
    localTextView1.setTextColor(Color.parseColor("#5B5B5B"));
    LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, -1);
    localLayoutParams1.setMargins((int)(30.0F * this.density), 0, (int)(30.0F * this.density), (int)(10.0F * this.density));
    localTextView1.setLayoutParams(localLayoutParams1);
    localTextView1.setGravity(1);
    localTextView1.setTextSize(17.0F);
    localTextView1.setTypeface(null, 1);
    localTextView1.setMaxLines(2);
    ImageView localImageView1;
    TextView localTextView2;
    LinearLayout localLinearLayout2;
    if (TextUtils.isEmpty(this.titleText))
    {
      localTextView1.setVisibility(8);
      localImageView1 = new ImageView(this.context);
      localImageView1.setAdjustViewBounds(true);
      LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-1, -2);
      localLayoutParams2.setMargins(0, (int)(10.0F * this.density), 0, 0);
      localTextView2 = new TextView(this.context);
      localTextView2.setTextColor(-16777216);
      localTextView2.setLayoutParams(localLayoutParams2);
      localTextView2.setMaxLines(3);
      localTextView2.setText(this.message);
      localLinearLayout2 = new LinearLayout(this.context);
      localLinearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
      if (!TextUtils.isEmpty(this.imgPath))
        break label710;
      localImageView1.setVisibility(8);
      ((LinearLayout.LayoutParams)localTextView2.getLayoutParams()).setMargins(0, 0, 0, 0);
      ((LinearLayout.LayoutParams)localTextView1.getLayoutParams()).setMargins(0, 0, 0, (int)(5.0F * this.density));
    }
    while (true)
    {
      localLinearLayout1.addView(localTextView1);
      localLinearLayout1.addView(localImageView1);
      localLinearLayout1.addView(localTextView2);
      localLinearLayout1.addView(localLinearLayout2);
      LinearLayout localLinearLayout3 = new LinearLayout(this.context);
      if (this.closeBtnEvent != null)
        localLinearLayout3.setOnClickListener(this.closeBtnEvent);
      FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams((int)(15.0D * (1.5D * this.density)), (int)(15.0D * (1.5D * this.density)));
      localLayoutParams.gravity = 5;
      int j = (int)(8.0F * this.density);
      localLayoutParams.setMargins(0, j, j, 0);
      LinearLayout.LayoutParams localLayoutParams3 = new LinearLayout.LayoutParams((int)(15.0F * this.density), (int)(15.0F * this.density));
      localLayoutParams3.gravity = 5;
      localLayoutParams3.setMargins(0, 0, -10, 0);
      ImageView localImageView2 = new ImageView(this.context);
      localImageView2.setLayoutParams(localLayoutParams3);
      localImageView2.setImageResource(2130837545);
      localLinearLayout3.addView(localImageView2, localLayoutParams3);
      addView(localLinearLayout1, new ViewGroup.LayoutParams(-1, -1));
      addView(localLinearLayout3, localLayoutParams);
      return;
      localTextView1.setText(this.titleText);
      break;
      label710: localImageView1.setVisibility(0);
      Bitmap localBitmap = BitmapFactory.decodeFile(this.imgPath);
      if (localBitmap != null)
      {
        localImageView1.setImageBitmap(localBitmap);
        LinearLayout.LayoutParams localLayoutParams4 = new LinearLayout.LayoutParams((int)(0.9D * this.screenWidth) - i * 2, (int)(0.9D * this.screenWidth) - i * 2);
        if (TextUtils.isEmpty(this.titleText))
          localLayoutParams4.setMargins(0, (int)(18.0F * this.density), 0, 0);
        localImageView1.setLayoutParams(localLayoutParams4);
        continue;
      }
      localImageView1.setVisibility(8);
      ((LinearLayout.LayoutParams)localTextView2.getLayoutParams()).setMargins(0, 0, 0, 0);
      ((LinearLayout.LayoutParams)localTextView1.getLayoutParams()).setMargins(0, 0, 0, (int)(5.0F * this.density));
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.PushNotification.AppPushNotificationDialogLayout
 * JD-Core Version:    0.6.0
 */