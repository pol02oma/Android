package com.google.android.gms.maps;

import android.graphics.Bitmap;
import android.location.Location;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.internal.er;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.ILocationSourceDelegate.a;
import com.google.android.gms.maps.internal.b.a;
import com.google.android.gms.maps.internal.d.a;
import com.google.android.gms.maps.internal.e.a;
import com.google.android.gms.maps.internal.f.a;
import com.google.android.gms.maps.internal.g;
import com.google.android.gms.maps.internal.h.a;
import com.google.android.gms.maps.internal.i.a;
import com.google.android.gms.maps.internal.j.a;
import com.google.android.gms.maps.internal.k.a;
import com.google.android.gms.maps.internal.l.a;
import com.google.android.gms.maps.internal.m.a;
import com.google.android.gms.maps.internal.n.a;
import com.google.android.gms.maps.internal.o.a;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.internal.d;
import com.google.android.gms.maps.model.internal.f;

public final class GoogleMap
{
  public static final int MAP_TYPE_HYBRID = 4;
  public static final int MAP_TYPE_NONE = 0;
  public static final int MAP_TYPE_NORMAL = 1;
  public static final int MAP_TYPE_SATELLITE = 2;
  public static final int MAP_TYPE_TERRAIN = 3;
  private final IGoogleMapDelegate OK;
  private UiSettings OL;

  protected GoogleMap(IGoogleMapDelegate paramIGoogleMapDelegate)
  {
    this.OK = ((IGoogleMapDelegate)er.f(paramIGoogleMapDelegate));
  }

  public final Circle addCircle(CircleOptions paramCircleOptions)
  {
    try
    {
      Circle localCircle = new Circle(this.OK.addCircle(paramCircleOptions));
      return localCircle;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final GroundOverlay addGroundOverlay(GroundOverlayOptions paramGroundOverlayOptions)
  {
    try
    {
      com.google.android.gms.maps.model.internal.c localc = this.OK.addGroundOverlay(paramGroundOverlayOptions);
      if (localc != null)
      {
        GroundOverlay localGroundOverlay = new GroundOverlay(localc);
        return localGroundOverlay;
      }
      return null;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final Marker addMarker(MarkerOptions paramMarkerOptions)
  {
    try
    {
      d locald = this.OK.addMarker(paramMarkerOptions);
      if (locald != null)
      {
        Marker localMarker = new Marker(locald);
        return localMarker;
      }
      return null;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final Polygon addPolygon(PolygonOptions paramPolygonOptions)
  {
    try
    {
      Polygon localPolygon = new Polygon(this.OK.addPolygon(paramPolygonOptions));
      return localPolygon;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final Polyline addPolyline(PolylineOptions paramPolylineOptions)
  {
    try
    {
      Polyline localPolyline = new Polyline(this.OK.addPolyline(paramPolylineOptions));
      return localPolyline;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final TileOverlay addTileOverlay(TileOverlayOptions paramTileOverlayOptions)
  {
    try
    {
      f localf = this.OK.addTileOverlay(paramTileOverlayOptions);
      if (localf != null)
      {
        TileOverlay localTileOverlay = new TileOverlay(localf);
        return localTileOverlay;
      }
      return null;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final void animateCamera(CameraUpdate paramCameraUpdate)
  {
    try
    {
      this.OK.animateCamera(paramCameraUpdate.gK());
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final void animateCamera(CameraUpdate paramCameraUpdate, int paramInt, CancelableCallback paramCancelableCallback)
  {
    try
    {
      IGoogleMapDelegate localIGoogleMapDelegate = this.OK;
      com.google.android.gms.dynamic.b localb = paramCameraUpdate.gK();
      if (paramCancelableCallback == null);
      for (Object localObject = null; ; localObject = new a(paramCancelableCallback))
      {
        localIGoogleMapDelegate.animateCameraWithDurationAndCallback(localb, paramInt, (com.google.android.gms.maps.internal.b)localObject);
        return;
      }
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final void animateCamera(CameraUpdate paramCameraUpdate, CancelableCallback paramCancelableCallback)
  {
    try
    {
      IGoogleMapDelegate localIGoogleMapDelegate = this.OK;
      com.google.android.gms.dynamic.b localb = paramCameraUpdate.gK();
      if (paramCancelableCallback == null);
      for (Object localObject = null; ; localObject = new a(paramCancelableCallback))
      {
        localIGoogleMapDelegate.animateCameraWithCallback(localb, (com.google.android.gms.maps.internal.b)localObject);
        return;
      }
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final void clear()
  {
    try
    {
      this.OK.clear();
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  IGoogleMapDelegate gM()
  {
    return this.OK;
  }

  public final CameraPosition getCameraPosition()
  {
    try
    {
      CameraPosition localCameraPosition = this.OK.getCameraPosition();
      return localCameraPosition;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final int getMapType()
  {
    try
    {
      int i = this.OK.getMapType();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final float getMaxZoomLevel()
  {
    try
    {
      float f = this.OK.getMaxZoomLevel();
      return f;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final float getMinZoomLevel()
  {
    try
    {
      float f = this.OK.getMinZoomLevel();
      return f;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  @Deprecated
  public final Location getMyLocation()
  {
    try
    {
      Location localLocation = this.OK.getMyLocation();
      return localLocation;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final Projection getProjection()
  {
    try
    {
      Projection localProjection = new Projection(this.OK.getProjection());
      return localProjection;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final UiSettings getUiSettings()
  {
    try
    {
      if (this.OL == null)
        this.OL = new UiSettings(this.OK.getUiSettings());
      UiSettings localUiSettings = this.OL;
      return localUiSettings;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final boolean isBuildingsEnabled()
  {
    try
    {
      boolean bool = this.OK.isBuildingsEnabled();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final boolean isIndoorEnabled()
  {
    try
    {
      boolean bool = this.OK.isIndoorEnabled();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final boolean isMyLocationEnabled()
  {
    try
    {
      boolean bool = this.OK.isMyLocationEnabled();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final boolean isTrafficEnabled()
  {
    try
    {
      boolean bool = this.OK.isTrafficEnabled();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final void moveCamera(CameraUpdate paramCameraUpdate)
  {
    try
    {
      this.OK.moveCamera(paramCameraUpdate.gK());
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final void setBuildingsEnabled(boolean paramBoolean)
  {
    try
    {
      this.OK.setBuildingsEnabled(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final boolean setIndoorEnabled(boolean paramBoolean)
  {
    try
    {
      boolean bool = this.OK.setIndoorEnabled(paramBoolean);
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final void setInfoWindowAdapter(InfoWindowAdapter paramInfoWindowAdapter)
  {
    if (paramInfoWindowAdapter == null);
    try
    {
      this.OK.setInfoWindowAdapter(null);
      return;
      this.OK.setInfoWindowAdapter(new d.a(paramInfoWindowAdapter)
      {
        public com.google.android.gms.dynamic.b f(d paramd)
        {
          return com.google.android.gms.dynamic.c.h(this.OZ.getInfoWindow(new Marker(paramd)));
        }

        public com.google.android.gms.dynamic.b g(d paramd)
        {
          return com.google.android.gms.dynamic.c.h(this.OZ.getInfoContents(new Marker(paramd)));
        }
      });
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final void setLocationSource(LocationSource paramLocationSource)
  {
    if (paramLocationSource == null);
    try
    {
      this.OK.setLocationSource(null);
      return;
      this.OK.setLocationSource(new ILocationSourceDelegate.a(paramLocationSource)
      {
        public void activate(g paramg)
        {
          this.OM.activate(new LocationSource.OnLocationChangedListener(paramg)
          {
            public void onLocationChanged(Location paramLocation)
            {
              try
              {
                this.OO.g(com.google.android.gms.dynamic.c.h(paramLocation));
                return;
              }
              catch (RemoteException localRemoteException)
              {
              }
              throw new RuntimeRemoteException(localRemoteException);
            }
          });
        }

        public void deactivate()
        {
          this.OM.deactivate();
        }
      });
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final void setMapType(int paramInt)
  {
    try
    {
      this.OK.setMapType(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final void setMyLocationEnabled(boolean paramBoolean)
  {
    try
    {
      this.OK.setMyLocationEnabled(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final void setOnCameraChangeListener(OnCameraChangeListener paramOnCameraChangeListener)
  {
    if (paramOnCameraChangeListener == null);
    try
    {
      this.OK.setOnCameraChangeListener(null);
      return;
      this.OK.setOnCameraChangeListener(new e.a(paramOnCameraChangeListener)
      {
        public void onCameraChange(CameraPosition paramCameraPosition)
        {
          this.OT.onCameraChange(paramCameraPosition);
        }
      });
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final void setOnInfoWindowClickListener(OnInfoWindowClickListener paramOnInfoWindowClickListener)
  {
    if (paramOnInfoWindowClickListener == null);
    try
    {
      this.OK.setOnInfoWindowClickListener(null);
      return;
      this.OK.setOnInfoWindowClickListener(new f.a(paramOnInfoWindowClickListener)
      {
        public void e(d paramd)
        {
          this.OY.onInfoWindowClick(new Marker(paramd));
        }
      });
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final void setOnMapClickListener(OnMapClickListener paramOnMapClickListener)
  {
    if (paramOnMapClickListener == null);
    try
    {
      this.OK.setOnMapClickListener(null);
      return;
      this.OK.setOnMapClickListener(new h.a(paramOnMapClickListener)
      {
        public void onMapClick(LatLng paramLatLng)
        {
          this.OU.onMapClick(paramLatLng);
        }
      });
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public void setOnMapLoadedCallback(OnMapLoadedCallback paramOnMapLoadedCallback)
  {
    if (paramOnMapLoadedCallback == null);
    try
    {
      this.OK.setOnMapLoadedCallback(null);
      return;
      this.OK.setOnMapLoadedCallback(new i.a(paramOnMapLoadedCallback)
      {
        public void onMapLoaded()
          throws RemoteException
        {
          this.OR.onMapLoaded();
        }
      });
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final void setOnMapLongClickListener(OnMapLongClickListener paramOnMapLongClickListener)
  {
    if (paramOnMapLongClickListener == null);
    try
    {
      this.OK.setOnMapLongClickListener(null);
      return;
      this.OK.setOnMapLongClickListener(new j.a(paramOnMapLongClickListener)
      {
        public void onMapLongClick(LatLng paramLatLng)
        {
          this.OV.onMapLongClick(paramLatLng);
        }
      });
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final void setOnMarkerClickListener(OnMarkerClickListener paramOnMarkerClickListener)
  {
    if (paramOnMarkerClickListener == null);
    try
    {
      this.OK.setOnMarkerClickListener(null);
      return;
      this.OK.setOnMarkerClickListener(new k.a(paramOnMarkerClickListener)
      {
        public boolean a(d paramd)
        {
          return this.OW.onMarkerClick(new Marker(paramd));
        }
      });
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final void setOnMarkerDragListener(OnMarkerDragListener paramOnMarkerDragListener)
  {
    if (paramOnMarkerDragListener == null);
    try
    {
      this.OK.setOnMarkerDragListener(null);
      return;
      this.OK.setOnMarkerDragListener(new l.a(paramOnMarkerDragListener)
      {
        public void b(d paramd)
        {
          this.OX.onMarkerDragStart(new Marker(paramd));
        }

        public void c(d paramd)
        {
          this.OX.onMarkerDragEnd(new Marker(paramd));
        }

        public void d(d paramd)
        {
          this.OX.onMarkerDrag(new Marker(paramd));
        }
      });
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final void setOnMyLocationButtonClickListener(OnMyLocationButtonClickListener paramOnMyLocationButtonClickListener)
  {
    if (paramOnMyLocationButtonClickListener == null);
    try
    {
      this.OK.setOnMyLocationButtonClickListener(null);
      return;
      this.OK.setOnMyLocationButtonClickListener(new m.a(paramOnMyLocationButtonClickListener)
      {
        public boolean onMyLocationButtonClick()
          throws RemoteException
        {
          return this.OQ.onMyLocationButtonClick();
        }
      });
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  @Deprecated
  public final void setOnMyLocationChangeListener(OnMyLocationChangeListener paramOnMyLocationChangeListener)
  {
    if (paramOnMyLocationChangeListener == null);
    try
    {
      this.OK.setOnMyLocationChangeListener(null);
      return;
      this.OK.setOnMyLocationChangeListener(new n.a(paramOnMyLocationChangeListener)
      {
        public void d(com.google.android.gms.dynamic.b paramb)
        {
          this.Pa.onMyLocationChange((Location)com.google.android.gms.dynamic.c.b(paramb));
        }
      });
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final void setPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    try
    {
      this.OK.setPadding(paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final void setTrafficEnabled(boolean paramBoolean)
  {
    try
    {
      this.OK.setTrafficEnabled(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final void snapshot(SnapshotReadyCallback paramSnapshotReadyCallback)
  {
    snapshot(paramSnapshotReadyCallback, null);
  }

  public final void snapshot(SnapshotReadyCallback paramSnapshotReadyCallback, Bitmap paramBitmap)
  {
    com.google.android.gms.dynamic.b localb;
    if (paramBitmap != null)
      localb = com.google.android.gms.dynamic.c.h(paramBitmap);
    while (true)
    {
      com.google.android.gms.dynamic.c localc = (com.google.android.gms.dynamic.c)(com.google.android.gms.dynamic.c)localb;
      try
      {
        this.OK.snapshot(new o.a(paramSnapshotReadyCallback)
        {
          public void c(com.google.android.gms.dynamic.b paramb)
            throws RemoteException
          {
            this.OS.onSnapshotReady((Bitmap)com.google.android.gms.dynamic.c.b(paramb));
          }

          public void onSnapshotReady(Bitmap paramBitmap)
            throws RemoteException
          {
            this.OS.onSnapshotReady(paramBitmap);
          }
        }
        , localc);
        return;
        localb = null;
      }
      catch (RemoteException localRemoteException)
      {
      }
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public final void stopAnimation()
  {
    try
    {
      this.OK.stopAnimation();
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public static abstract interface CancelableCallback
  {
    public abstract void onCancel();

    public abstract void onFinish();
  }

  public static abstract interface InfoWindowAdapter
  {
    public abstract View getInfoContents(Marker paramMarker);

    public abstract View getInfoWindow(Marker paramMarker);
  }

  public static abstract interface OnCameraChangeListener
  {
    public abstract void onCameraChange(CameraPosition paramCameraPosition);
  }

  public static abstract interface OnInfoWindowClickListener
  {
    public abstract void onInfoWindowClick(Marker paramMarker);
  }

  public static abstract interface OnMapClickListener
  {
    public abstract void onMapClick(LatLng paramLatLng);
  }

  public static abstract interface OnMapLoadedCallback
  {
    public abstract void onMapLoaded();
  }

  public static abstract interface OnMapLongClickListener
  {
    public abstract void onMapLongClick(LatLng paramLatLng);
  }

  public static abstract interface OnMarkerClickListener
  {
    public abstract boolean onMarkerClick(Marker paramMarker);
  }

  public static abstract interface OnMarkerDragListener
  {
    public abstract void onMarkerDrag(Marker paramMarker);

    public abstract void onMarkerDragEnd(Marker paramMarker);

    public abstract void onMarkerDragStart(Marker paramMarker);
  }

  public static abstract interface OnMyLocationButtonClickListener
  {
    public abstract boolean onMyLocationButtonClick();
  }

  @Deprecated
  public static abstract interface OnMyLocationChangeListener
  {
    public abstract void onMyLocationChange(Location paramLocation);
  }

  public static abstract interface SnapshotReadyCallback
  {
    public abstract void onSnapshotReady(Bitmap paramBitmap);
  }

  private static final class a extends b.a
  {
    private final GoogleMap.CancelableCallback Pb;

    a(GoogleMap.CancelableCallback paramCancelableCallback)
    {
      this.Pb = paramCancelableCallback;
    }

    public void onCancel()
    {
      this.Pb.onCancel();
    }

    public void onFinish()
    {
      this.Pb.onFinish();
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.maps.GoogleMap
 * JD-Core Version:    0.6.0
 */