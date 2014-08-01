package com.appbuilder.sdk.android;

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class SwipeLinearLayout extends LinearLayout
{
  private OnSwipeInterface eventsHandler;
  private GestureDetector gestureDetector;
  private MotionEvent previous = null;
  private boolean showSidebar;

  public SwipeLinearLayout(Context paramContext, boolean paramBoolean)
  {
    super(paramContext);
    this.showSidebar = paramBoolean;
    if (paramBoolean)
      this.gestureDetector = new GestureDetector(new GestureListener(null));
  }

  public void disableSwipe()
  {
    this.showSidebar = false;
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.showSidebar)
    {
      int i = paramMotionEvent.getAction();
      if (i == 3)
      {
        this.previous = MotionEvent.obtain(paramMotionEvent);
        return false;
      }
      if (i == 1)
      {
        this.previous = MotionEvent.obtain(paramMotionEvent);
        if (this.eventsHandler != null)
          return this.eventsHandler.onTouchEvent(paramMotionEvent.getX());
        return false;
      }
      switch (i)
      {
      default:
      case 2:
      }
    }
    while (true)
    {
      this.previous = MotionEvent.obtain(paramMotionEvent);
      return false;
      if (this.previous.getAction() == 2)
      {
        float f1 = paramMotionEvent.getY() - this.previous.getY();
        float f2 = paramMotionEvent.getX() - this.previous.getX();
        if ((Math.abs(f2) <= Math.abs(f1)) || (Math.abs(f2) <= 50.0F))
          continue;
        if (f2 > 0.0F)
        {
          if (this.eventsHandler != null)
            this.eventsHandler.onSwipeRight();
          this.previous = MotionEvent.obtain(paramMotionEvent);
          return true;
        }
        if (this.eventsHandler != null)
          this.eventsHandler.onSwipeLeft();
        this.previous = MotionEvent.obtain(paramMotionEvent);
        return true;
      }
    }
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.showSidebar)
      this.gestureDetector.onTouchEvent(paramMotionEvent);
    return true;
  }

  public void setOnSwipeEvents(OnSwipeInterface paramOnSwipeInterface)
  {
    this.eventsHandler = paramOnSwipeInterface;
  }

  private final class GestureListener extends GestureDetector.SimpleOnGestureListener
  {
    private static final int SWIPE_THRESHOLD = 50;
    private static final int SWIPE_VELOCITY_THRESHOLD = 50;

    private GestureListener()
    {
    }

    public boolean onDown(MotionEvent paramMotionEvent)
    {
      if (SwipeLinearLayout.this.eventsHandler != null)
        SwipeLinearLayout.this.eventsHandler.onTouchEvent(paramMotionEvent.getX());
      return true;
    }

    public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
    {
      try
      {
        float f1 = paramMotionEvent2.getY() - paramMotionEvent1.getY();
        float f2 = paramMotionEvent2.getX() - paramMotionEvent1.getX();
        if (Math.abs(f2) > Math.abs(f1))
        {
          boolean bool3 = Math.abs(f2) < 50.0F;
          i = 0;
          if (!bool3)
            break label236;
          boolean bool4 = Math.abs(paramFloat1) < 50.0F;
          i = 0;
          if (!bool4)
            break label236;
          if (f2 > 0.0F)
          {
            if (SwipeLinearLayout.this.eventsHandler != null)
              SwipeLinearLayout.this.eventsHandler.onSwipeRight();
          }
          else
          {
            if (SwipeLinearLayout.this.eventsHandler == null)
              break label239;
            SwipeLinearLayout.this.eventsHandler.onSwipeLeft();
            break label239;
          }
        }
        else
        {
          boolean bool1 = Math.abs(f1) < 50.0F;
          i = 0;
          if (!bool1)
            break label236;
          boolean bool2 = Math.abs(paramFloat2) < 50.0F;
          i = 0;
          if (!bool2)
            break label236;
          if (f1 > 0.0F)
          {
            if (SwipeLinearLayout.this.eventsHandler == null)
              break label245;
            SwipeLinearLayout.this.eventsHandler.onSwipeBottom();
            break label245;
          }
          if (SwipeLinearLayout.this.eventsHandler != null)
            SwipeLinearLayout.this.eventsHandler.onSwipeTop();
          i = 1;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return false;
      }
      int i = 1;
      while (true)
      {
        label236: return i;
        label239: i = 1;
        continue;
        label245: i = 1;
      }
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.sdk.android.SwipeLinearLayout
 * JD-Core Version:    0.6.0
 */