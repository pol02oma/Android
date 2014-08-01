package com.google.android.gms.analytics;

import android.content.Context;
import java.util.ArrayList;

public class ExceptionReporter
  implements Thread.UncaughtExceptionHandler
{
  private final Context mContext;
  private final Thread.UncaughtExceptionHandler rd;
  private final Tracker re;
  private ExceptionParser rf;

  public ExceptionReporter(Tracker paramTracker, Thread.UncaughtExceptionHandler paramUncaughtExceptionHandler, Context paramContext)
  {
    if (paramTracker == null)
      throw new NullPointerException("tracker cannot be null");
    if (paramContext == null)
      throw new NullPointerException("context cannot be null");
    this.rd = paramUncaughtExceptionHandler;
    this.re = paramTracker;
    this.rf = new StandardExceptionParser(paramContext, new ArrayList());
    this.mContext = paramContext.getApplicationContext();
    StringBuilder localStringBuilder = new StringBuilder().append("ExceptionReporter created, original handler is ");
    if (paramUncaughtExceptionHandler == null);
    for (String str = "null"; ; str = paramUncaughtExceptionHandler.getClass().getName())
    {
      aa.v(str);
      return;
    }
  }

  public ExceptionParser getExceptionParser()
  {
    return this.rf;
  }

  public void setExceptionParser(ExceptionParser paramExceptionParser)
  {
    this.rf = paramExceptionParser;
  }

  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    String str1 = "UncaughtException";
    if (this.rf != null)
      if (paramThread == null)
        break label114;
    label114: for (String str2 = paramThread.getName(); ; str2 = null)
    {
      str1 = this.rf.getDescription(str2, paramThrowable);
      aa.v("Tracking Exception: " + str1);
      this.re.send(new HitBuilders.ExceptionBuilder().setDescription(str1).setFatal(true).build());
      GoogleAnalytics.getInstance(this.mContext).dispatchLocalHits();
      if (this.rd != null)
      {
        aa.v("Passing exception to original handler.");
        this.rd.uncaughtException(paramThread, paramThrowable);
      }
      return;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.analytics.ExceptionReporter
 * JD-Core Version:    0.6.0
 */