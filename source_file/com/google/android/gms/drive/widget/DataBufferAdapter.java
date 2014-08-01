package com.google.android.gms.drive.widget;

import android.content.Context;
import android.database.CursorIndexOutOfBoundsException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.android.gms.common.data.DataBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DataBufferAdapter<T> extends BaseAdapter
{
  private final int Fj;
  private int Fk;
  private final int Fl;
  private final List<DataBuffer<T>> Fm;
  private final LayoutInflater Fn;
  private boolean Fo = true;
  private final Context mContext;

  public DataBufferAdapter(Context paramContext, int paramInt)
  {
    this(paramContext, paramInt, 0, new ArrayList());
  }

  public DataBufferAdapter(Context paramContext, int paramInt1, int paramInt2)
  {
    this(paramContext, paramInt1, paramInt2, new ArrayList());
  }

  public DataBufferAdapter(Context paramContext, int paramInt1, int paramInt2, List<DataBuffer<T>> paramList)
  {
    this.mContext = paramContext;
    this.Fk = paramInt1;
    this.Fj = paramInt1;
    this.Fl = paramInt2;
    this.Fm = paramList;
    this.Fn = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
  }

  public DataBufferAdapter(Context paramContext, int paramInt1, int paramInt2, DataBuffer<T>[] paramArrayOfDataBuffer)
  {
    this(paramContext, paramInt1, paramInt2, Arrays.asList(paramArrayOfDataBuffer));
  }

  public DataBufferAdapter(Context paramContext, int paramInt, List<DataBuffer<T>> paramList)
  {
    this(paramContext, paramInt, 0, paramList);
  }

  public DataBufferAdapter(Context paramContext, int paramInt, DataBuffer<T>[] paramArrayOfDataBuffer)
  {
    this(paramContext, paramInt, 0, Arrays.asList(paramArrayOfDataBuffer));
  }

  private View a(int paramInt1, View paramView, ViewGroup paramViewGroup, int paramInt2)
  {
    View localView;
    if (paramView == null)
      localView = this.Fn.inflate(paramInt2, paramViewGroup, false);
    Object localObject1;
    Object localObject2;
    try
    {
      TextView localTextView2;
      if (this.Fl == 0)
        localTextView2 = (TextView)localView;
      TextView localTextView1;
      for (localObject1 = localTextView2; ; localObject1 = localTextView1)
      {
        localObject2 = getItem(paramInt1);
        if (!(localObject2 instanceof CharSequence))
          break label112;
        ((TextView)localObject1).setText((CharSequence)localObject2);
        return localView;
        localView = paramView;
        break;
        localTextView1 = (TextView)localView.findViewById(this.Fl);
      }
    }
    catch (ClassCastException localClassCastException)
    {
      Log.e("DataBufferAdapter", "You must supply a resource ID for a TextView");
      throw new IllegalStateException("DataBufferAdapter requires the resource ID to be a TextView", localClassCastException);
    }
    label112: ((TextView)localObject1).setText(localObject2.toString());
    return (View)localView;
  }

  public void append(DataBuffer<T> paramDataBuffer)
  {
    this.Fm.add(paramDataBuffer);
    if (this.Fo)
      notifyDataSetChanged();
  }

  public void clear()
  {
    Iterator localIterator = this.Fm.iterator();
    while (localIterator.hasNext())
      ((DataBuffer)localIterator.next()).close();
    this.Fm.clear();
    if (this.Fo)
      notifyDataSetChanged();
  }

  public Context getContext()
  {
    return this.mContext;
  }

  public int getCount()
  {
    Iterator localIterator = this.Fm.iterator();
    int i = 0;
    while (localIterator.hasNext())
      i += ((DataBuffer)localIterator.next()).getCount();
    return i;
  }

  public View getDropDownView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return a(paramInt, paramView, paramViewGroup, this.Fk);
  }

  public T getItem(int paramInt)
    throws CursorIndexOutOfBoundsException
  {
    Iterator localIterator = this.Fm.iterator();
    int i = paramInt;
    while (localIterator.hasNext())
    {
      DataBuffer localDataBuffer = (DataBuffer)localIterator.next();
      int j = localDataBuffer.getCount();
      if (j <= i)
      {
        i -= j;
        continue;
      }
      try
      {
        Object localObject = localDataBuffer.get(i);
        return localObject;
      }
      catch (CursorIndexOutOfBoundsException localCursorIndexOutOfBoundsException)
      {
        throw new CursorIndexOutOfBoundsException(paramInt, getCount());
      }
    }
    throw new CursorIndexOutOfBoundsException(paramInt, getCount());
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return a(paramInt, paramView, paramViewGroup, this.Fj);
  }

  public void notifyDataSetChanged()
  {
    super.notifyDataSetChanged();
    this.Fo = true;
  }

  public void setDropDownViewResource(int paramInt)
  {
    this.Fk = paramInt;
  }

  public void setNotifyOnChange(boolean paramBoolean)
  {
    this.Fo = paramBoolean;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.widget.DataBufferAdapter
 * JD-Core Version:    0.6.0
 */