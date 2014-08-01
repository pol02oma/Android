package com.google.android.gms.maps;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.dynamic.a;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.internal.er;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import com.google.android.gms.maps.internal.p;
import com.google.android.gms.maps.internal.q;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class MapFragment extends Fragment
{
  private final b Pm = new b(this);
  private GoogleMap Pn;

  public static MapFragment newInstance()
  {
    return new MapFragment();
  }

  public static MapFragment newInstance(GoogleMapOptions paramGoogleMapOptions)
  {
    MapFragment localMapFragment = new MapFragment();
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("MapOptions", paramGoogleMapOptions);
    localMapFragment.setArguments(localBundle);
    return localMapFragment;
  }

  protected IMapFragmentDelegate gV()
  {
    this.Pm.gW();
    if (this.Pm.fj() == null)
      return null;
    return ((a)this.Pm.fj()).gV();
  }

  public final GoogleMap getMap()
  {
    IMapFragmentDelegate localIMapFragmentDelegate = gV();
    if (localIMapFragmentDelegate == null);
    while (true)
    {
      return null;
      try
      {
        IGoogleMapDelegate localIGoogleMapDelegate = localIMapFragmentDelegate.getMap();
        if (localIGoogleMapDelegate == null)
          continue;
        if ((this.Pn == null) || (this.Pn.gM().asBinder() != localIGoogleMapDelegate.asBinder()))
          this.Pn = new GoogleMap(localIGoogleMapDelegate);
        return this.Pn;
      }
      catch (RemoteException localRemoteException)
      {
      }
    }
    throw new RuntimeRemoteException(localRemoteException);
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    if (paramBundle != null)
      paramBundle.setClassLoader(MapFragment.class.getClassLoader());
    super.onActivityCreated(paramBundle);
  }

  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    b.a(this.Pm, paramActivity);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.Pm.onCreate(paramBundle);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return this.Pm.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
  }

  public void onDestroy()
  {
    this.Pm.onDestroy();
    super.onDestroy();
  }

  public void onDestroyView()
  {
    this.Pm.onDestroyView();
    super.onDestroyView();
  }

  public void onInflate(Activity paramActivity, AttributeSet paramAttributeSet, Bundle paramBundle)
  {
    super.onInflate(paramActivity, paramAttributeSet, paramBundle);
    b.a(this.Pm, paramActivity);
    GoogleMapOptions localGoogleMapOptions = GoogleMapOptions.createFromAttributes(paramActivity, paramAttributeSet);
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("MapOptions", localGoogleMapOptions);
    this.Pm.onInflate(paramActivity, localBundle, paramBundle);
  }

  public void onLowMemory()
  {
    this.Pm.onLowMemory();
    super.onLowMemory();
  }

  public void onPause()
  {
    this.Pm.onPause();
    super.onPause();
  }

  public void onResume()
  {
    super.onResume();
    this.Pm.onResume();
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    if (paramBundle != null)
      paramBundle.setClassLoader(MapFragment.class.getClassLoader());
    super.onSaveInstanceState(paramBundle);
    this.Pm.onSaveInstanceState(paramBundle);
  }

  public void setArguments(Bundle paramBundle)
  {
    super.setArguments(paramBundle);
  }

  static class a
    implements LifecycleDelegate
  {
    private final Fragment Po;
    private final IMapFragmentDelegate Pp;

    public a(Fragment paramFragment, IMapFragmentDelegate paramIMapFragmentDelegate)
    {
      this.Pp = ((IMapFragmentDelegate)er.f(paramIMapFragmentDelegate));
      this.Po = ((Fragment)er.f(paramFragment));
    }

    public IMapFragmentDelegate gV()
    {
      return this.Pp;
    }

    public void onCreate(Bundle paramBundle)
    {
      if (paramBundle == null);
      try
      {
        paramBundle = new Bundle();
        Bundle localBundle = this.Po.getArguments();
        if ((localBundle != null) && (localBundle.containsKey("MapOptions")))
          p.a(paramBundle, "MapOptions", localBundle.getParcelable("MapOptions"));
        this.Pp.onCreate(paramBundle);
        return;
      }
      catch (RemoteException localRemoteException)
      {
      }
      throw new RuntimeRemoteException(localRemoteException);
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
      try
      {
        b localb = this.Pp.onCreateView(com.google.android.gms.dynamic.c.h(paramLayoutInflater), com.google.android.gms.dynamic.c.h(paramViewGroup), paramBundle);
        return (View)com.google.android.gms.dynamic.c.b(localb);
      }
      catch (RemoteException localRemoteException)
      {
      }
      throw new RuntimeRemoteException(localRemoteException);
    }

    public void onDestroy()
    {
      try
      {
        this.Pp.onDestroy();
        return;
      }
      catch (RemoteException localRemoteException)
      {
      }
      throw new RuntimeRemoteException(localRemoteException);
    }

    public void onDestroyView()
    {
      try
      {
        this.Pp.onDestroyView();
        return;
      }
      catch (RemoteException localRemoteException)
      {
      }
      throw new RuntimeRemoteException(localRemoteException);
    }

    public void onInflate(Activity paramActivity, Bundle paramBundle1, Bundle paramBundle2)
    {
      GoogleMapOptions localGoogleMapOptions = (GoogleMapOptions)paramBundle1.getParcelable("MapOptions");
      try
      {
        this.Pp.onInflate(com.google.android.gms.dynamic.c.h(paramActivity), localGoogleMapOptions, paramBundle2);
        return;
      }
      catch (RemoteException localRemoteException)
      {
      }
      throw new RuntimeRemoteException(localRemoteException);
    }

    public void onLowMemory()
    {
      try
      {
        this.Pp.onLowMemory();
        return;
      }
      catch (RemoteException localRemoteException)
      {
      }
      throw new RuntimeRemoteException(localRemoteException);
    }

    public void onPause()
    {
      try
      {
        this.Pp.onPause();
        return;
      }
      catch (RemoteException localRemoteException)
      {
      }
      throw new RuntimeRemoteException(localRemoteException);
    }

    public void onResume()
    {
      try
      {
        this.Pp.onResume();
        return;
      }
      catch (RemoteException localRemoteException)
      {
      }
      throw new RuntimeRemoteException(localRemoteException);
    }

    public void onSaveInstanceState(Bundle paramBundle)
    {
      try
      {
        this.Pp.onSaveInstanceState(paramBundle);
        return;
      }
      catch (RemoteException localRemoteException)
      {
      }
      throw new RuntimeRemoteException(localRemoteException);
    }

    public void onStart()
    {
    }

    public void onStop()
    {
    }
  }

  static class b extends a<MapFragment.a>
  {
    private final Fragment Po;
    protected d<MapFragment.a> Pq;
    private Activity nd;

    b(Fragment paramFragment)
    {
      this.Po = paramFragment;
    }

    private void setActivity(Activity paramActivity)
    {
      this.nd = paramActivity;
      gW();
    }

    protected void a(d<MapFragment.a> paramd)
    {
      this.Pq = paramd;
      gW();
    }

    public void gW()
    {
      if ((this.nd != null) && (this.Pq != null) && (fj() == null));
      try
      {
        MapsInitializer.initialize(this.nd);
        IMapFragmentDelegate localIMapFragmentDelegate = q.A(this.nd).f(com.google.android.gms.dynamic.c.h(this.nd));
        this.Pq.a(new MapFragment.a(this.Po, localIMapFragmentDelegate));
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
      catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
      {
      }
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.maps.MapFragment
 * JD-Core Version:    0.6.0
 */