package com.romanblack.android.widget.imagegallery;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.View.OnClickListener;
import android.widget.AbsoluteLayout;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.ImageView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageGallery extends AbsoluteLayout
{
  public static final int DIRECTION_SLIDE_LEFT = 0;
  public static final int DIRECTION_SLIDE_RIGHT = 1;
  private static final int POSITION_CENTER = 0;
  private static final int POSITION_LEFT = -1;
  private static final int POSITION_RIGHT = 1;
  private final int DECODING_END = 10;
  private final int HANDLER_INIT_CENTER_DECODED = 0;
  private final int HANDLER_INIT_LEFT_DECODED = 2;
  private final int HANDLER_INIT_RIGHT_DECODED = 1;
  private final int HANDLER_NO_SLIDING = 7;
  private final int HANDLER_RETURN_FROM_LEFT = 8;
  private final int HANDLER_RETURN_FROM_RIGHT = 9;
  private final int HANDLER_SLIDE_LEFT = 5;
  private final int HANDLER_SLIDE_RIGHT = 6;
  private final int HANDLER_SLIDING_LEFT = 3;
  private final int HANDLER_SLIDING_RIGHT = 4;
  private final int INVALIDATE = 11;
  private final int MAXIMUM_SCALE = 2;
  private final int MINIMUM_SCALE = 1;
  private float actualScale = 1.0F;
  private int actualX = 0;
  private int actualY = 0;
  private int bitmapPosition = 0;
  private Bitmap[] bitmaps = new Bitmap[5];
  private int correctingSpeed = 30;
  private Context ctx = null;
  private int currentImagePosition = 0;
  private float density = 1.0F;
  private long downTime = 0L;
  private ExecutorService executorService = null;
  private int fPosition = 0;
  private ImageView firstImgView = null;
  private int frapsInterval = 15;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      default:
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
        do
        {
          do
          {
            do
            {
              return;
              ImageGallery.this.drawCenter();
              return;
              ImageGallery.this.drawRight();
              return;
              ImageGallery.this.drawLeft();
              return;
              ImageGallery.this.handler.sendEmptyMessage(5);
              return;
              ImageGallery.this.handler.sendEmptyMessage(6);
              return;
              ImageGallery.access$402(ImageGallery.this, ImageGallery.this.actualX + Math.round(ImageGallery.this.correctingSpeed * ImageGallery.this.density));
              ImageGallery.this.requestSizes();
              ImageGallery.this.invalidate();
              if (ImageGallery.this.actualX >= 15.0F * ImageGallery.this.density + Math.round(ImageGallery.this.width))
              {
                ImageGallery.access$402(ImageGallery.this, Math.round(15.0F * ImageGallery.this.density + Math.round(ImageGallery.this.width) * ImageGallery.this.actualScale));
                ImageGallery.this.onSlideEnd(0);
                return;
              }
              ImageGallery.this.handler.sendEmptyMessageDelayed(5, ImageGallery.this.frapsInterval);
              return;
              ImageGallery.access$402(ImageGallery.this, ImageGallery.this.actualX - Math.round(ImageGallery.this.correctingSpeed * ImageGallery.this.density));
              ImageGallery.this.requestSizes();
              ImageGallery.this.invalidate();
              if (ImageGallery.this.actualX <= -(15.0F * ImageGallery.this.density + Math.round(ImageGallery.this.width) * ImageGallery.this.actualScale))
              {
                ImageGallery.access$402(ImageGallery.this, Math.round(-(15.0F * ImageGallery.this.density + Math.round(ImageGallery.this.width) * ImageGallery.this.actualScale)));
                ImageGallery.this.onSlideEnd(1);
                return;
              }
              ImageGallery.this.handler.sendEmptyMessageDelayed(6, ImageGallery.this.frapsInterval);
              return;
              if (ImageGallery.this.actualX <= 0)
                continue;
              ImageGallery.this.handler.sendEmptyMessage(8);
              return;
            }
            while (ImageGallery.this.actualX >= (1.0F - ImageGallery.this.actualScale) * ImageGallery.this.width);
            ImageGallery.this.handler.sendEmptyMessage(9);
            return;
            ImageGallery.access$402(ImageGallery.this, ImageGallery.this.actualX - Math.round(ImageGallery.this.correctingSpeed * ImageGallery.this.density));
            if (ImageGallery.this.actualX < 0)
              ImageGallery.access$402(ImageGallery.this, 0);
            ImageGallery.this.requestSizes();
            ImageGallery.this.invalidate();
          }
          while (ImageGallery.this.actualX == 0);
          ImageGallery.this.handler.sendEmptyMessageDelayed(8, ImageGallery.this.frapsInterval);
          return;
          ImageGallery.access$402(ImageGallery.this, ImageGallery.this.actualX + Math.round(ImageGallery.this.correctingSpeed * ImageGallery.this.density));
          if (ImageGallery.this.actualX > -((ImageGallery.this.actualScale - 1.0F) * ImageGallery.this.width))
            ImageGallery.access$402(ImageGallery.this, Math.round(-((ImageGallery.this.actualScale - 1.0F) * ImageGallery.this.width)));
          ImageGallery.this.requestSizes();
          ImageGallery.this.invalidate();
        }
        while (ImageGallery.this.actualX == Math.round(-((ImageGallery.this.actualScale - 1.0F) * ImageGallery.this.width)));
        ImageGallery.this.handler.sendEmptyMessageDelayed(9, ImageGallery.this.frapsInterval);
        return;
      case 10:
        switch (ImageGallery.this.fPosition)
        {
        default:
          label820: switch (ImageGallery.this.sPosition)
          {
          default:
          case -1:
          case 0:
          case 1:
          }
        case -1:
        case 0:
        case 1:
        }
        while (true)
          switch (ImageGallery.this.tPosition)
          {
          default:
            return;
          case -1:
            ImageGallery.this.thirdImageView.setImageBitmap(ImageGallery.this.bitmaps[1]);
            return;
            ImageGallery.this.firstImgView.setImageBitmap(ImageGallery.this.bitmaps[1]);
            break label820;
            ImageGallery.this.firstImgView.setImageBitmap(ImageGallery.this.bitmaps[2]);
            break label820;
            ImageGallery.this.firstImgView.setImageBitmap(ImageGallery.this.bitmaps[3]);
            break label820;
            ImageGallery.this.secondImageView.setImageBitmap(ImageGallery.this.bitmaps[1]);
            continue;
            ImageGallery.this.secondImageView.setImageBitmap(ImageGallery.this.bitmaps[2]);
            continue;
            ImageGallery.this.secondImageView.setImageBitmap(ImageGallery.this.bitmaps[3]);
          case 0:
          case 1:
          }
        ImageGallery.this.thirdImageView.setImageBitmap(ImageGallery.this.bitmaps[2]);
        return;
        ImageGallery.this.thirdImageView.setImageBitmap(ImageGallery.this.bitmaps[3]);
        return;
      case 11:
      }
      ImageGallery.this.requestSizes();
      ImageGallery.this.invalidate();
    }
  };
  private int height = 0;
  private ArrayList<String> imagePaths = new ArrayList();
  private boolean initialized = false;
  private int lastXTouch = 0;
  private int lastYTouch = 0;
  private final int minPadding = -25;
  private int minY = 0;
  private View.OnClickListener onClickListener = null;
  private OnSlideEndListener onSlideEndListener = null;
  private int padding = -25;
  private PositionChangedListener positionChangedListener = null;
  private int sPosition = 0;
  private ScaleGestureDetector scaleGestureDetector;
  private ImageView secondImageView = null;
  private boolean slidingLeft = false;
  private int tPosition = 0;
  private ImageView thirdImageView = null;
  private boolean touchEnabled = true;
  private int width = 0;

  public ImageGallery(Context paramContext)
  {
    super(paramContext);
    this.ctx = paramContext;
    this.density = paramContext.getResources().getDisplayMetrics().density;
    setPadding(0, (int)(-this.padding / 2 * this.density), 0, 0);
    this.minY = (int)(25.0F * this.density);
    this.actualY = (-this.minY);
  }

  public ImageGallery(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.ctx = paramContext;
    this.density = paramContext.getResources().getDisplayMetrics().density;
    setPadding(0, (int)(this.padding / 2 * this.density), 0, 0);
    this.minY = (int)(25.0F * this.density);
    this.actualY = (-this.minY);
  }

  public ImageGallery(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.ctx = paramContext;
    this.density = paramContext.getResources().getDisplayMetrics().density;
    setPadding(0, (int)(this.padding / 2 * this.density), 0, 0);
    this.minY = (int)(25.0F * this.density);
    this.actualY = (-this.minY);
  }

  public ImageGallery(Context paramContext, AttributeSet paramAttributeSet, int paramInt, ArrayList<String> paramArrayList)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.ctx = paramContext;
    this.imagePaths = paramArrayList;
    this.density = paramContext.getResources().getDisplayMetrics().density;
    setPadding(0, (int)(this.padding / 2 * this.density), 0, 0);
    this.minY = (int)(25.0F * this.density);
    this.actualY = (-this.minY);
  }

  public ImageGallery(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, ArrayList<String> paramArrayList, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1);
    this.ctx = paramContext;
    this.imagePaths = paramArrayList;
    this.currentImagePosition = paramInt2;
    this.density = paramContext.getResources().getDisplayMetrics().density;
    setPadding(0, (int)(this.padding / 2 * this.density), 0, 0);
    this.minY = (int)(25.0F * this.density);
    this.actualY = (-this.minY);
  }

  public ImageGallery(Context paramContext, AttributeSet paramAttributeSet, ArrayList<String> paramArrayList)
  {
    super(paramContext, paramAttributeSet);
    this.ctx = paramContext;
    this.imagePaths = paramArrayList;
    this.density = paramContext.getResources().getDisplayMetrics().density;
    setPadding(0, (int)(this.padding / 2 * this.density), 0, 0);
    this.minY = (int)(25.0F * this.density);
    this.actualY = (-this.minY);
  }

  public ImageGallery(Context paramContext, AttributeSet paramAttributeSet, ArrayList<String> paramArrayList, int paramInt)
  {
    super(paramContext, paramAttributeSet);
    this.ctx = paramContext;
    this.imagePaths = paramArrayList;
    this.currentImagePosition = paramInt;
    this.density = paramContext.getResources().getDisplayMetrics().density;
    setPadding(0, (int)(this.padding / 2 * this.density), 0, 0);
    this.minY = (int)(25.0F * this.density);
    this.actualY = (-this.minY);
  }

  public ImageGallery(Context paramContext, ArrayList<String> paramArrayList)
  {
    super(paramContext);
    this.ctx = paramContext;
    this.imagePaths = paramArrayList;
    this.density = paramContext.getResources().getDisplayMetrics().density;
    setPadding(0, (int)(this.padding / 2 * this.density), 0, 0);
    this.minY = (int)(25.0F * this.density);
    this.actualY = (-this.minY);
  }

  public ImageGallery(Context paramContext, ArrayList<String> paramArrayList, int paramInt)
  {
    super(paramContext);
    this.ctx = paramContext;
    this.imagePaths = paramArrayList;
    this.currentImagePosition = paramInt;
    this.density = paramContext.getResources().getDisplayMetrics().density;
    setPadding(0, (int)(this.padding / 2 * this.density), 0, 0);
    this.minY = (int)(25.0F * this.density);
    this.actualY = (-this.minY);
  }

  private void addBitmapToEnd(Bitmap paramBitmap)
  {
    try
    {
      this.bitmaps[0].recycle();
      label9: for (int i = 0; i < 4; i++)
        this.bitmaps[i] = this.bitmaps[(i + 1)];
      this.bitmaps[4] = paramBitmap;
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      break label9;
    }
  }

  private void addBitmapToStart(Bitmap paramBitmap)
  {
    try
    {
      this.bitmaps[4].recycle();
      label9: for (int i = 0; i < 4; i++)
        this.bitmaps[(4 - i)] = this.bitmaps[(-1 + (4 - i))];
      this.bitmaps[0] = paramBitmap;
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      break label9;
    }
  }

  private Bitmap decodeImageFile(int paramInt)
  {
    int i = 1;
    while (true)
    {
      if (i <= 5);
      try
      {
        BitmapFactory.Options localOptions1 = new BitmapFactory.Options();
        localOptions1.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(new FileInputStream(new File((String)this.imagePaths.get(paramInt))), null, localOptions1);
        int j = localOptions1.outWidth;
        int k = localOptions1.outHeight;
        int m = i;
        while (true)
        {
          if ((j / 2 < j) || (k / 2 < k))
          {
            BitmapFactory.Options localOptions2 = new BitmapFactory.Options();
            localOptions2.inSampleSize = m;
            System.gc();
            return BitmapFactory.decodeFile((String)this.imagePaths.get(paramInt), localOptions2);
          }
          j /= 2;
          k /= 2;
          m *= 2;
        }
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        i++;
        continue;
        return null;
      }
      catch (OutOfMemoryError localOutOfMemoryError)
      {
        label143: break label143;
      }
    }
  }

  private void drawCenter()
  {
    if (this.fPosition == 0)
      this.firstImgView.setImageBitmap(this.bitmaps[2]);
    do
    {
      return;
      if (this.sPosition != 0)
        continue;
      this.secondImageView.setImageBitmap(this.bitmaps[2]);
      return;
    }
    while (this.tPosition != 0);
    this.thirdImageView.setImageBitmap(this.bitmaps[2]);
  }

  private void drawLeft()
  {
    if (this.fPosition == -1)
      this.firstImgView.setImageBitmap(this.bitmaps[1]);
    do
    {
      return;
      if (this.sPosition != -1)
        continue;
      this.secondImageView.setImageBitmap(this.bitmaps[1]);
      return;
    }
    while (this.tPosition != -1);
    this.thirdImageView.setImageBitmap(this.bitmaps[1]);
  }

  private void drawRight()
  {
    if (this.fPosition == 1)
      this.firstImgView.setImageBitmap(this.bitmaps[3]);
    do
    {
      return;
      if (this.sPosition != 1)
        continue;
      this.secondImageView.setImageBitmap(this.bitmaps[3]);
      return;
    }
    while (this.tPosition != 1);
    this.thirdImageView.setImageBitmap(this.bitmaps[3]);
  }

  private void init()
  {
    setWillNotDraw(false);
    this.density = this.ctx.getResources().getDisplayMetrics().density;
    this.firstImgView = new ImageView(this.ctx);
    this.secondImageView = new ImageView(this.ctx);
    this.thirdImageView = new ImageView(this.ctx);
    this.fPosition = -1;
    this.sPosition = 0;
    this.tPosition = 1;
    addView(this.firstImgView);
    addView(this.secondImageView);
    addView(this.thirdImageView);
    requestLayout(this.firstImgView, this.fPosition);
    requestLayout(this.secondImageView, this.sPosition);
    requestLayout(this.thirdImageView, this.tPosition);
    this.executorService = Executors.newFixedThreadPool(3);
    this.executorService.execute(new InatializationBitmapsDecodingRunnable(null));
    this.scaleGestureDetector = new ScaleGestureDetector(this.ctx, new ScaleDetectorListener(null));
  }

  private void onSlideEnd(int paramInt)
  {
    int i = this.currentImagePosition;
    if (paramInt == 1)
    {
      this.slidingLeft = false;
      switch (this.fPosition)
      {
      default:
        switch (this.sPosition)
        {
        default:
          label76: switch (this.tPosition)
          {
          default:
            label108: this.currentImagePosition = (1 + this.currentImagePosition);
            this.actualX = 0;
            this.actualY = (-this.minY);
            this.actualScale = 1.0F;
            requestSizes();
            invalidate();
          case -1:
          case 0:
          case 1:
          }
        case -1:
        case 0:
        case 1:
        }
      case -1:
      case 0:
      case 1:
      }
    }
    do
    {
      this.executorService.execute(new BitmapDecodingRunnable(null));
      if (this.onSlideEndListener != null)
        this.onSlideEndListener.onSlideEnd(paramInt, this.currentImagePosition);
      if (this.positionChangedListener != null)
        this.positionChangedListener.onPositionChanged(this.currentImagePosition, i);
      this.touchEnabled = true;
      return;
      this.fPosition = 1;
      break;
      this.fPosition = -1;
      break;
      this.fPosition = 0;
      break;
      this.sPosition = 1;
      break label76;
      this.sPosition = -1;
      break label76;
      this.sPosition = 0;
      break label76;
      this.tPosition = 1;
      break label108;
      this.tPosition = -1;
      break label108;
      this.tPosition = 0;
      break label108;
    }
    while (paramInt != 0);
    this.slidingLeft = true;
    switch (this.fPosition)
    {
    default:
      label324: switch (this.sPosition)
      {
      default:
        label356: switch (this.tPosition)
        {
        default:
        case -1:
        case 0:
        case 1:
        }
      case -1:
      case 0:
      case 1:
      }
    case -1:
    case 0:
    case 1:
    }
    while (true)
    {
      this.currentImagePosition = (-1 + this.currentImagePosition);
      this.actualX = 0;
      this.actualY = (-this.minY);
      this.actualScale = 1.0F;
      requestSizes();
      invalidate();
      break;
      this.fPosition = 0;
      break label324;
      this.fPosition = 1;
      break label324;
      this.fPosition = -1;
      break label324;
      this.sPosition = 0;
      break label356;
      this.sPosition = 1;
      break label356;
      this.sPosition = -1;
      break label356;
      this.tPosition = 0;
      continue;
      this.tPosition = 1;
      continue;
      this.tPosition = -1;
    }
  }

  private void requestLayout(ImageView paramImageView, int paramInt)
  {
    int i = 0;
    int j = 0;
    int k = 0;
    int m = 0;
    switch (paramInt)
    {
    default:
    case -1:
    case 0:
    case 1:
    }
    while (true)
    {
      paramImageView.setLayoutParams(new AbsoluteLayout.LayoutParams(j, i, k, m));
      return;
      i = this.height;
      j = this.width;
      k = this.actualX - (int)(15.0F * this.density) - j;
      m = -this.minY;
      continue;
      k = this.actualX;
      m = this.actualY;
      i = (int)(this.height * this.actualScale);
      j = (int)(this.width * this.actualScale);
      continue;
      i = this.height;
      j = this.width;
      k = this.actualX + (int)(15.0F * this.density) + Math.round(j * this.actualScale);
      m = -this.minY;
    }
  }

  private void requestSizes()
  {
    requestLayout(this.firstImgView, this.fPosition);
    requestLayout(this.secondImageView, this.sPosition);
    requestLayout(this.thirdImageView, this.tPosition);
  }

  public boolean canSlideLeft()
  {
    return this.currentImagePosition > 0;
  }

  public boolean canSlideRight()
  {
    return this.currentImagePosition < -1 + this.imagePaths.size();
  }

  public ArrayList<String> getImagePaths()
  {
    return this.imagePaths;
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
  }

  protected void onDetachedFromWindow()
  {
    try
    {
      this.executorService.shutdown();
      this.executorService = null;
      System.gc();
      label17: int i = 0;
      while (true)
        if (i < 5)
          try
          {
            this.bitmaps[i].recycle();
            this.bitmaps[i] = null;
            i++;
          }
          catch (NullPointerException localNullPointerException2)
          {
            while (true)
              this.bitmaps[i] = null;
          }
      System.gc();
      super.onDetachedFromWindow();
      return;
    }
    catch (NullPointerException localNullPointerException1)
    {
      break label17;
    }
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    DisplayMetrics localDisplayMetrics = this.ctx.getResources().getDisplayMetrics();
    this.width = localDisplayMetrics.widthPixels;
    this.height = localDisplayMetrics.heightPixels;
    this.height = (this.height - (int)(25.0F * localDisplayMetrics.density) - this.minY);
    if ((!this.initialized) && (!this.imagePaths.isEmpty()))
    {
      init();
      this.initialized = true;
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    this.handler.sendEmptyMessageDelayed(11, 30L);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (!this.touchEnabled)
      return false;
    this.scaleGestureDetector.onTouchEvent(paramMotionEvent);
    if (this.scaleGestureDetector.isInProgress())
      return true;
    switch (paramMotionEvent.getAction())
    {
    default:
    case 0:
    case 1:
    case 2:
    }
    while (true)
    {
      return true;
      this.downTime = System.currentTimeMillis();
      this.handler.removeMessages(8);
      this.handler.removeMessages(9);
      this.handler.removeMessages(5);
      this.handler.removeMessages(6);
      this.lastXTouch = Math.round(paramMotionEvent.getX());
      this.lastYTouch = Math.round(paramMotionEvent.getY());
      continue;
      this.lastXTouch = 0;
      this.lastYTouch = 0;
      if (this.actualX + 8.0F * this.density + Math.round(this.width * this.actualScale) <= this.width / 2)
        this.handler.sendEmptyMessage(4);
      while (true)
      {
        if ((System.currentTimeMillis() - this.downTime <= 100L) && (this.onClickListener != null))
          this.onClickListener.onClick(this);
        this.downTime = 0L;
        break;
        if (this.actualX - 8.0F * this.density >= this.width / 2)
        {
          this.handler.sendEmptyMessage(3);
          continue;
        }
        this.handler.sendEmptyMessage(7);
      }
      int i = Math.round(paramMotionEvent.getX()) - this.lastXTouch;
      int j = Math.round(paramMotionEvent.getY()) - this.lastYTouch;
      this.lastXTouch = Math.round(paramMotionEvent.getX());
      this.lastYTouch = Math.round(paramMotionEvent.getY());
      this.actualX = (i + this.actualX);
      this.actualY = (j + this.actualY);
      if (this.actualY > -this.minY)
        this.actualY = (-this.minY);
      int k = this.height - Math.round(this.height * this.actualScale) - this.minY;
      if (this.actualY < k)
        this.actualY = k;
      if (this.currentImagePosition == 0)
      {
        int n = Math.round(0.4F * this.width);
        if (this.actualX > n)
          this.actualX = n;
      }
      if (this.currentImagePosition == -1 + this.imagePaths.size())
      {
        int m = Math.round(this.width * (0.6F - this.actualScale));
        if (this.actualX < m)
          this.actualX = m;
      }
      requestSizes();
      invalidate();
    }
  }

  public void setImagePaths(ArrayList<String> paramArrayList)
  {
    this.imagePaths = paramArrayList;
    if ((!this.initialized) && (!paramArrayList.isEmpty()))
    {
      init();
      this.initialized = true;
    }
  }

  public void setOnClickListener(View.OnClickListener paramOnClickListener)
  {
    this.onClickListener = paramOnClickListener;
  }

  public void setOnSlideEndListener(OnSlideEndListener paramOnSlideEndListener)
  {
    this.onSlideEndListener = paramOnSlideEndListener;
  }

  public void setPadding(int paramInt)
  {
    this.padding = (paramInt - 25);
    setPadding(0, (int)(this.padding / 2 * this.density), 0, 0);
    invalidate();
  }

  public void setPosition(int paramInt)
  {
    int i = this.currentImagePosition;
    this.currentImagePosition = paramInt;
    if (this.initialized)
    {
      this.executorService.execute(new InatializationBitmapsDecodingRunnable(null));
      if (this.positionChangedListener != null)
        this.positionChangedListener.onPositionChanged(this.currentImagePosition, i);
    }
  }

  public void setPositionChangedListener(PositionChangedListener paramPositionChangedListener)
  {
    this.positionChangedListener = paramPositionChangedListener;
  }

  public void slideLeft()
  {
    if (canSlideLeft())
    {
      this.touchEnabled = false;
      this.handler.sendEmptyMessage(5);
    }
  }

  public void slideRight()
  {
    if (canSlideRight())
    {
      this.touchEnabled = false;
      this.handler.sendEmptyMessage(6);
    }
  }

  private class BitmapDecodingRunnable
    implements Runnable
  {
    private BitmapDecodingRunnable()
    {
    }

    public void run()
    {
      int i;
      Bitmap localBitmap;
      if (ImageGallery.this.slidingLeft)
      {
        i = -2 + ImageGallery.this.currentImagePosition;
        if ((i <= -1) || (i >= ImageGallery.this.imagePaths.size()))
          break label105;
        localBitmap = ImageGallery.this.decodeImageFile(i);
        if (!ImageGallery.this.slidingLeft)
          break label94;
        ImageGallery.this.addBitmapToStart(localBitmap);
      }
      while (true)
      {
        ImageGallery.this.handler.sendEmptyMessage(10);
        return;
        i = 2 + ImageGallery.this.currentImagePosition;
        break;
        label94: ImageGallery.this.addBitmapToEnd(localBitmap);
        continue;
        label105: if (ImageGallery.this.slidingLeft)
        {
          ImageGallery.this.addBitmapToStart(null);
          continue;
        }
        ImageGallery.this.addBitmapToEnd(null);
      }
    }
  }

  private class InatializationBitmapsDecodingRunnable
    implements Runnable
  {
    private InatializationBitmapsDecodingRunnable()
    {
    }

    public void run()
    {
      ImageGallery.this.bitmaps[2] = ImageGallery.access$2600(ImageGallery.this, ImageGallery.access$2400(ImageGallery.this));
      ImageGallery.this.handler.sendEmptyMessage(0);
      if (ImageGallery.this.currentImagePosition == -1 + ImageGallery.this.imagePaths.size())
      {
        ImageGallery.this.bitmaps[3] = null;
        ImageGallery.this.handler.sendEmptyMessage(1);
        if (ImageGallery.this.currentImagePosition != 0)
          break label194;
        ImageGallery.this.bitmaps[1] = null;
        label99: ImageGallery.this.handler.sendEmptyMessage(2);
        if (ImageGallery.this.currentImagePosition <= -3 + ImageGallery.this.imagePaths.size())
          break label222;
        ImageGallery.this.bitmaps[4] = null;
      }
      while (true)
      {
        if (ImageGallery.this.currentImagePosition >= 2)
          break label250;
        ImageGallery.this.bitmaps[0] = null;
        return;
        ImageGallery.this.bitmaps[3] = ImageGallery.access$2600(ImageGallery.this, 1 + ImageGallery.access$2400(ImageGallery.this));
        break;
        label194: ImageGallery.this.bitmaps[1] = ImageGallery.access$2600(ImageGallery.this, -1 + ImageGallery.access$2400(ImageGallery.this));
        break label99;
        label222: ImageGallery.this.bitmaps[4] = ImageGallery.access$2600(ImageGallery.this, 2 + ImageGallery.access$2400(ImageGallery.this));
      }
      label250: ImageGallery.this.bitmaps[0] = ImageGallery.access$2600(ImageGallery.this, -2 + ImageGallery.access$2400(ImageGallery.this));
    }
  }

  public static abstract interface OnSlideEndListener
  {
    public abstract void onSlideEnd(int paramInt1, int paramInt2);
  }

  public static abstract interface PositionChangedListener
  {
    public abstract void onPositionChanged(int paramInt1, int paramInt2);
  }

  private class ScaleDetectorListener
    implements ScaleGestureDetector.OnScaleGestureListener
  {
    float scaleFocusX = 0.0F;
    float scaleFocusY = 0.0F;

    private ScaleDetectorListener()
    {
    }

    public boolean onScale(ScaleGestureDetector paramScaleGestureDetector)
    {
      int i = ImageGallery.this.actualX;
      int j = ImageGallery.this.actualY;
      float f = 100.0F * (paramScaleGestureDetector.getScaleFactor() * ImageGallery.this.actualScale) / 100.0F;
      if (f < 1.0F)
      {
        ImageGallery.access$902(ImageGallery.this, 1.0F);
        ImageGallery.access$402(ImageGallery.this, 0);
        ImageGallery.access$2902(ImageGallery.this, 0);
      }
      while (true)
      {
        ImageGallery.this.requestSizes();
        ImageGallery.this.invalidate();
        return true;
        if (f > 2.0F)
        {
          ImageGallery.access$902(ImageGallery.this, 2.0F);
          continue;
        }
        ImageGallery.access$902(ImageGallery.this, f);
        (this.scaleFocusX - i);
        ImageGallery.access$402(ImageGallery.this, Math.round(i - (this.scaleFocusX - i) * (paramScaleGestureDetector.getScaleFactor() - 1.0F)));
        ImageGallery.access$2902(ImageGallery.this, Math.round(j - (this.scaleFocusY - j) * (paramScaleGestureDetector.getScaleFactor() - 1.0F)));
      }
    }

    public boolean onScaleBegin(ScaleGestureDetector paramScaleGestureDetector)
    {
      ImageGallery.this.invalidate();
      this.scaleFocusX = paramScaleGestureDetector.getFocusX();
      this.scaleFocusY = paramScaleGestureDetector.getFocusY();
      return true;
    }

    public void onScaleEnd(ScaleGestureDetector paramScaleGestureDetector)
    {
      this.scaleFocusX = 0.0F;
      this.scaleFocusY = 0.0F;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.romanblack.android.widget.imagegallery.ImageGallery
 * JD-Core Version:    0.6.0
 */