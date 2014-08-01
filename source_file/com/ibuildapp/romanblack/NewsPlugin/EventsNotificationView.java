package com.ibuildapp.romanblack.NewsPlugin;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class EventsNotificationView extends Activity
  implements View.OnTouchListener
{
  private final int SLIDE_COMPLETE = 2;
  private final int SLIDE_TO_LEFT_START = 1;
  private final int SLIDE_TO_RIGHT_START = 0;
  private ImageView btnNext = null;
  private ImageView btnPrev = null;
  Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
      case 1:
      case 0:
        do
        {
          do
            return;
          while (EventsNotificationView.this.position >= -1 + EventsNotificationView.this.notifications.size());
          EventsNotificationView.access$008(EventsNotificationView.this);
          EventsNotificationView.this.slidePanel(-500);
          return;
        }
        while (EventsNotificationView.this.position <= 0);
        EventsNotificationView.access$010(EventsNotificationView.this);
        EventsNotificationView.this.slidePanel(500);
        return;
      case 2:
      }
      EventsNotificationView.this.showNotification();
    }
  };
  private LinearLayout notificationPanel = null;
  private ArrayList<String> notifications = new ArrayList();
  private int position = 0;
  float startPos = 0.0F;
  private TextView textViewCounter = null;
  private TextView textViewNotification = null;

  private void closeMessage()
  {
    finish();
  }

  private void showNotification()
  {
    if (this.notifications.size() > 1)
    {
      if (this.position <= 0)
        break label142;
      this.btnPrev.setVisibility(0);
      if (this.position >= -1 + this.notifications.size())
        break label153;
      this.btnNext.setVisibility(0);
    }
    while (true)
    {
      this.textViewNotification.setText((CharSequence)this.notifications.get(this.position));
      this.textViewCounter.setText(1 + this.position + " " + getString(R.string.romanblack_rss_from) + " " + this.notifications.size());
      this.notificationPanel.clearAnimation();
      this.notificationPanel.refreshDrawableState();
      return;
      label142: this.btnPrev.setVisibility(4);
      break;
      label153: this.btnNext.setVisibility(4);
    }
  }

  private void slidePanel(int paramInt)
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(0.0F, paramInt, 0.0F, 0.0F);
    localTranslateAnimation.setFillAfter(true);
    localTranslateAnimation.setInterpolator(new LinearInterpolator());
    localTranslateAnimation.setDuration(500L);
    localTranslateAnimation.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnimation)
      {
        EventsNotificationView.this.handler.sendEmptyMessage(2);
      }

      public void onAnimationRepeat(Animation paramAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnimation)
      {
      }
    });
    this.notificationPanel.startAnimation(localTranslateAnimation);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    setContentView(R.layout.romanblack_rss_notification_screen);
    File localFile = new File(Environment.getExternalStorageDirectory() + "/AppBuilder/" + getPackageName() + "/.notifications");
    if (localFile.exists());
    try
    {
      ObjectInputStream localObjectInputStream = new ObjectInputStream(new FileInputStream(localFile));
      this.notifications = ((ArrayList)localObjectInputStream.readObject());
      localObjectInputStream.close();
      label113: localFile.delete();
      this.textViewNotification = ((TextView)findViewById(R.id.romanblack_rss_notification));
      this.textViewCounter = ((TextView)findViewById(R.id.romanblack_rss_notification_counter));
      RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(R.id.romanblack_rss_notification_main);
      this.notificationPanel = ((LinearLayout)findViewById(R.id.romanblack_rss_notification_panel));
      ((Button)findViewById(R.id.romanblack_rss_push_button_close)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          EventsNotificationView.this.closeMessage();
        }
      });
      Button localButton = (Button)findViewById(R.id.romanblack_rss_button_app);
      localButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
        }
      });
      localButton.setVisibility(8);
      this.btnNext = ((ImageView)findViewById(R.id.romanblack_rss_notification_next));
      this.btnNext.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          EventsNotificationView.this.handler.sendEmptyMessage(1);
        }
      });
      this.btnPrev = ((ImageView)findViewById(R.id.romanblack_rss_notification_prev));
      this.btnPrev.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          EventsNotificationView.this.handler.sendEmptyMessage(0);
        }
      });
      this.textViewNotification.setText("");
      this.textViewCounter.setText("");
      if (this.notifications.size() > 0)
      {
        this.textViewCounter.setText(1 + this.position + " " + getString(R.string.romanblack_rss_from) + " " + this.notifications.size());
        this.textViewNotification.setText((CharSequence)this.notifications.get(this.position));
        if (this.notifications.size() > 1)
          this.btnNext.setVisibility(0);
      }
      localRelativeLayout.setOnTouchListener(this);
      return;
      finish();
      return;
    }
    catch (Exception localException)
    {
      break label113;
    }
  }

  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    case 2:
    default:
    case 0:
    case 1:
    }
    do
    {
      return true;
      this.startPos = paramMotionEvent.getX();
      return true;
      if (paramMotionEvent.getX() - this.startPos <= 90.0F)
        continue;
      this.handler.sendEmptyMessage(0);
      return true;
    }
    while (this.startPos - paramMotionEvent.getX() <= 90.0F);
    this.handler.sendEmptyMessage(1);
    return true;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.NewsPlugin.EventsNotificationView
 * JD-Core Version:    0.6.0
 */