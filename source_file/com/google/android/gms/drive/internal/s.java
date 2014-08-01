package com.google.android.gms.drive.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.drive.events.DriveEvent;
import com.google.android.gms.drive.events.DriveEvent.Listener;
import com.google.android.gms.internal.er;

public class s<C extends DriveEvent> extends w.a
{
  private final DriveEvent.Listener<C> DR;
  private final a<C> DS;
  private final int Dm;

  public s(Looper paramLooper, int paramInt, DriveEvent.Listener<C> paramListener)
  {
    this.Dm = paramInt;
    this.DR = paramListener;
    this.DS = new a(paramLooper, null);
  }

  public void a(OnEventResponse paramOnEventResponse)
    throws RemoteException
  {
    if (this.Dm == paramOnEventResponse.getEventType());
    for (boolean bool = true; ; bool = false)
    {
      er.v(bool);
      switch (paramOnEventResponse.getEventType())
      {
      default:
        Log.w("EventCallback", "Unexpected event type:" + paramOnEventResponse.getEventType());
        return;
      case 1:
      case 2:
      }
    }
    this.DS.a(this.DR, paramOnEventResponse.fa());
    return;
    this.DS.a(this.DR, paramOnEventResponse.fb());
  }

  private static class a<E extends DriveEvent> extends Handler
  {
    private a(Looper paramLooper)
    {
      super();
    }

    public void a(DriveEvent.Listener<E> paramListener, E paramE)
    {
      sendMessage(obtainMessage(1, new Pair(paramListener, paramE)));
    }

    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        Log.wtf("EventCallback", "Don't know how to handle this event");
        return;
      case 1:
      }
      Pair localPair = (Pair)paramMessage.obj;
      ((DriveEvent.Listener)localPair.first).onEvent((DriveEvent)localPair.second);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.s
 * JD-Core Version:    0.6.0
 */