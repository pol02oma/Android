package com.appbuilder.u846253p1176378.GPSNotification;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;
import java.util.ArrayList;
import java.util.Iterator;

public class GPSLocationMapOverlay extends Overlay
{
  private Paint borderPaint;
  private Paint innerPaint;
  private ArrayList<GPSLocationMapItem> points = new ArrayList();
  private ArrayList<GeoPoint> route = new ArrayList();
  private int routeColor = Color.argb(127, 204, 51, 255);

  private ArrayList<String> splitString(String paramString, int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramInt == 1)
    {
      localArrayList.add(paramString);
      return localArrayList;
    }
    int i = paramString.length() / paramInt;
    int j = 0;
    if (j < paramInt);
    label247: 
    while (true)
      try
      {
        String str1 = paramString.substring(0, i);
        String str2 = paramString.substring(i);
        int k = str1.lastIndexOf(" ");
        int m = str2.indexOf(" ");
        if (m != -1)
          continue;
        m = str2.length();
        if (str1.length() - k >= m)
          continue;
        localArrayList.add(str1.substring(0, k));
        paramString = str1.substring(k + 1) + str2;
        if (paramString.length() >= i)
          continue;
        localArrayList.add(paramString);
        paramString = "";
        if (paramString.length() <= 0)
          break;
        localArrayList.add(paramString);
        return localArrayList;
        localArrayList.add(str1 + str2.substring(0, m));
        if (m != str2.length())
          continue;
        paramString = "";
        break label247;
        String str3 = str2.substring(m + 1);
        paramString = str3;
        break label247;
        j++;
      }
      catch (Exception localException)
      {
        return localArrayList;
      }
  }

  public void addPoint(GPSLocationMapItem paramGPSLocationMapItem)
  {
    this.points.add(paramGPSLocationMapItem);
  }

  public boolean draw(Canvas paramCanvas, MapView paramMapView, boolean paramBoolean, long paramLong)
  {
    Projection localProjection = paramMapView.getProjection();
    Paint localPaint = new Paint();
    localPaint.setAntiAlias(true);
    localPaint.setStrokeWidth(3.0F);
    localPaint.setAlpha(120);
    localPaint.setColor(this.routeColor);
    for (int i = 0; i < -1 + this.route.size(); i++)
    {
      Point localPoint1 = new Point();
      localProjection.toPixels((GeoPoint)this.route.get(i), localPoint1);
      Point localPoint2 = new Point();
      localProjection.toPixels((GeoPoint)this.route.get(i + 1), localPoint2);
      paramCanvas.drawLine(localPoint1.x, localPoint1.y, localPoint2.x, localPoint2.y, localPaint);
    }
    for (int j = 0; j < this.points.size(); j++)
    {
      Point localPoint3 = new Point();
      localProjection.toPixels(((GPSLocationMapItem)this.points.get(j)).getGeoPoint(), localPoint3);
      if (((GPSLocationMapItem)this.points.get(j)).getIconShadow() != null)
        paramCanvas.drawBitmap(((GPSLocationMapItem)this.points.get(j)).getIconShadow(), localPoint3.x, localPoint3.y - ((GPSLocationMapItem)this.points.get(j)).getIconShadow().getHeight(), null);
      if (((GPSLocationMapItem)this.points.get(j)).getIcon() != null)
        paramCanvas.drawBitmap(((GPSLocationMapItem)this.points.get(j)).getIcon(), localPoint3.x - ((GPSLocationMapItem)this.points.get(j)).getIcon().getWidth() / 2, localPoint3.y - ((GPSLocationMapItem)this.points.get(j)).getIcon().getHeight(), null);
      if (((((GPSLocationMapItem)this.points.get(j)).getTitle().length() <= 0) && (((GPSLocationMapItem)this.points.get(j)).getDescription().length() <= 0)) || (((GPSLocationMapItem)this.points.get(j)).getState() != GPSLocationMapItem.states.SHOW))
        continue;
      TextPaint localTextPaint = new TextPaint();
      localTextPaint.setARGB(255, 255, 255, 255);
      localTextPaint.setTextSize(14.0F);
      localTextPaint.setAntiAlias(true);
      Rect localRect = new Rect();
      ArrayList localArrayList1 = new ArrayList();
      ArrayList localArrayList2 = new ArrayList();
      String str1 = ((GPSLocationMapItem)this.points.get(j)).getTitle();
      int k = str1.length();
      int m = 0;
      int n = 0;
      int i1 = 0;
      int i2 = 0;
      if (k > 0)
      {
        int i22 = 1;
        if (k > 25)
          i22 = 2;
        localArrayList1 = splitString(str1, i22);
        localTextPaint.setTextSize(16.0F);
        localTextPaint.getTextBounds((String)localArrayList1.get(0), 0, ((String)localArrayList1.get(0)).length(), localRect);
        int i23 = i22;
        n = 0;
        if (i23 > 1)
          n = localRect.width();
        m = localRect.height() * localArrayList1.size() + 2 * localArrayList1.size();
        i2 = localRect.width();
        i1 = 2 + localRect.height();
      }
      String str2 = ((GPSLocationMapItem)this.points.get(j)).getDescription();
      int i3 = str2.length();
      int i4 = 0;
      int i20;
      if (i3 > 0)
      {
        int i16 = 1;
        if (n == 0)
          if (i3 < 20)
            i16 = 1;
        Object localObject;
        while (true)
        {
          localArrayList2 = splitString(str2, i16);
          localObject = "";
          int i18 = 0;
          Iterator localIterator = localArrayList2.iterator();
          while (localIterator.hasNext())
          {
            String str3 = (String)localIterator.next();
            int i21 = str3.length();
            if (i18 >= i21)
              continue;
            i18 = str3.length();
            localObject = str3;
          }
          if ((i3 > 20) && (i3 < 61))
          {
            i16 = 2;
            continue;
          }
          if ((i3 > 60) && (i3 < 120))
          {
            i16 = 3;
            continue;
          }
          if ((i3 > 121) && (i3 < 160))
          {
            i16 = 4;
            continue;
          }
          if ((i3 > 161) && (i3 < 200))
          {
            i16 = 5;
            continue;
          }
          i16 = 6;
          continue;
          localTextPaint.setTextSize(12.0F);
          localTextPaint.getTextBounds(str2, 0, str2.length(), localRect);
          int i17 = localRect.width();
          if (i17 <= n)
            continue;
          i16 = i17 / n;
          if (i17 % n == 0)
            continue;
          i16++;
        }
        localTextPaint.setTextSize(12.0F);
        int i19 = ((String)localObject).length();
        localTextPaint.getTextBounds((String)localObject, 0, i19, localRect);
        m += (2 + localRect.height()) * localArrayList2.size();
        i4 = 2 + localRect.height();
        i20 = localRect.width();
        if (i2 <= i20)
          break label1212;
      }
      int i9;
      int i10;
      label1212: for (n = i2; ; n = i20)
      {
        int i5 = 18 + (n + 20);
        int i6 = 10 + (m + 20);
        RectF localRectF = new RectF(0.0F, 0.0F, i5, i6);
        int i7 = localPoint3.x - i5 / 2;
        int i8 = -2 + (localPoint3.y - i6);
        if (((GPSLocationMapItem)this.points.get(j)).getIcon() != null)
          i8 -= ((GPSLocationMapItem)this.points.get(j)).getIcon().getHeight();
        localRectF.offset(i7, i8);
        paramCanvas.drawRoundRect(localRectF, 5.0F, 5.0F, getInnerPaint());
        paramCanvas.drawRoundRect(localRectF, 5.0F, 5.0F, getBorderPaint());
        i9 = i7 + 10;
        i10 = i8 + 10;
        localTextPaint.setTextSize(16.0F);
        for (int i12 = 0; ; i12++)
        {
          int i13 = localArrayList1.size();
          if (i12 >= i13)
            break;
          i10 += i1;
          paramCanvas.drawText((String)localArrayList1.get(i12), i9, i10, localTextPaint);
        }
      }
      if (localArrayList1.size() > 0)
        i10 += 5;
      localTextPaint.setTextSize(14.0F);
      for (int i14 = 0; ; i14++)
      {
        int i15 = localArrayList2.size();
        if (i14 >= i15)
          break;
        int i11;
        i10 += i4;
        paramCanvas.drawText((String)localArrayList2.get(i14), i9, i11 + 1, localTextPaint);
      }
    }
    return super.draw(paramCanvas, paramMapView, paramBoolean, paramLong);
  }

  public Paint getBorderPaint()
  {
    if (this.borderPaint == null)
    {
      this.borderPaint = new Paint();
      this.borderPaint.setARGB(225, 255, 255, 255);
      this.borderPaint.setAntiAlias(true);
      this.borderPaint.setStyle(Paint.Style.STROKE);
      this.borderPaint.setStrokeWidth(2.0F);
    }
    return this.borderPaint;
  }

  public Paint getInnerPaint()
  {
    if (this.innerPaint == null)
    {
      this.innerPaint = new Paint();
      this.innerPaint.setARGB(196, 75, 75, 75);
      this.innerPaint.setAntiAlias(true);
    }
    return this.innerPaint;
  }

  public TextPaint getTextPaint(float paramFloat)
  {
    TextPaint localTextPaint = new TextPaint();
    localTextPaint.setTextSize(paramFloat);
    localTextPaint.setARGB(255, 255, 255, 255);
    localTextPaint.setAntiAlias(true);
    return localTextPaint;
  }

  public boolean onTap(GeoPoint paramGeoPoint, MapView paramMapView)
  {
    RectF localRectF = new RectF();
    Point localPoint = new Point();
    for (int i = 0; ; i++)
    {
      if (i < this.points.size())
      {
        paramMapView.getProjection().toPixels(((GPSLocationMapItem)this.points.get(i)).getGeoPoint(), localPoint);
        localRectF.set(-((GPSLocationMapItem)this.points.get(i)).getIcon().getWidth(), -((GPSLocationMapItem)this.points.get(i)).getIcon().getHeight(), ((GPSLocationMapItem)this.points.get(i)).getIcon().getWidth(), 0.0F);
        localRectF.offset(localPoint.x, localPoint.y);
        paramMapView.getProjection().toPixels(paramGeoPoint, localPoint);
        if (!localRectF.contains(localPoint.x, localPoint.y))
          continue;
        if (((GPSLocationMapItem)this.points.get(i)).getState() != GPSLocationMapItem.states.SHOW)
          break label216;
        ((GPSLocationMapItem)this.points.get(i)).setState(GPSLocationMapItem.states.HIDE);
      }
      while (true)
      {
        paramMapView.invalidate();
        return true;
        label216: ((GPSLocationMapItem)this.points.get(i)).setState(GPSLocationMapItem.states.SHOW);
      }
    }
  }

  public void setRoute(ArrayList<GeoPoint> paramArrayList, int paramInt)
  {
    this.route = paramArrayList;
    this.routeColor = paramInt;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.GPSNotification.GPSLocationMapOverlay
 * JD-Core Version:    0.6.0
 */