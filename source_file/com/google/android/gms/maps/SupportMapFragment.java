package com.google.android.gms.maps;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
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

public class SupportMapFragment extends Fragment
{
  private GoogleMap Pn;
  private final b Py = new b(this);

  public static SupportMapFragment newInstance()
  {
    return new SupportMapFragment();
  }

  public static SupportMapFragment newInstance(GoogleMapOptions paramGoogleMapOptions)
  {
    SupportMapFragment localSupportMapFragment = new SupportMapFragment();
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("MapOptions", paramGoogleMapOptions);
    localSupportMapFragment.setArguments(localBundle);
    return localSupportMapFragment;
  }

  protected IMapFragmentDelegate gV()
  {
    this.Py.gW();
    if (this.Py.fj() == null)
      return null;
    return ((a)this.Py.fj()).gV();
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
      paramBundle.setClassLoader(SupportMapFragment.class.getClassLoader());
    super.onActivityCreated(paramBundle);
  }

  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    b.a(this.Py, paramActivity);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.Py.onCreate(paramBundle);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return this.Py.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
  }

  public void onDestroy()
  {
    this.Py.onDestroy();
    super.onDestroy();
  }

  public void onDestroyView()
  {
    this.Py.onDestroyView();
    super.onDestroyView();
  }

  public void onInflate(Activity paramActivity, AttributeSet paramAttributeSet, Bundle paramBundle)
  {
    super.onInflate(paramActivity, paramAttributeSet, paramBundle);
    b.a(this.Py, paramActivity);
    GoogleMapOptions localGoogleMapOptions = GoogleMapOptions.createFromAttributes(paramActivity, paramAttributeSet);
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("MapOptions", localGoogleMapOptions);
    this.Py.onInflate(paramActivity, localBundle, paramBundle);
  }

  public void onLowMemory()
  {
    this.Py.onLowMemory();
    super.onLowMemory();
  }

  public void onPause()
  {
    this.Py.onPause();
    super.onPause();
  }

  public void onResume()
  {
    super.onResume();
    this.Py.onResume();
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    if (paramBundle != null)
      paramBundle.setClassLoader(SupportMapFragment.class.getClassLoader());
    super.onSaveInstanceState(paramBundle);
    this.Py.onSaveInstanceState(paramBundle);
  }

  public void setArguments(Bundle paramBundle)
  {
    super.setArguments(paramBundle);
  }

  static class a
    implements LifecycleDelegate
  {
    private final IMapFragmentDelegate Pp;
    private final Fragment Pz;

    public a(Fragment paramFragment, IMapFragmentDelegate paramIMapFragmentDelegate)
    {
      this.Pp = ((IMapFragmentDelegate)er.f(paramIMapFragmentDelegate));
      this.Pz = ((Fragment)er.f(paramFragment));
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
        Bundle localBundle = this.Pz.getArguments();
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

  static class b extends a<SupportMapFragment.a>
  {
    protected d<SupportMapFragment.a> Pq;
    private final Fragment Pz;
    private Activity nd;

    b(Fragment paramFragment)
    {
      this.Pz = paramFragment;
    }

    private void setActivity(Activity paramActivity)
    {
      this.nd = paramActivity;
      gW();
    }

    protected void a(d<SupportMapFragment.a> paramd)
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
        this.Pq.a(new SupportMapFragment.a(this.Pz, localIMapFragmentDelegate));
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
 * Qualified Name:     com.google.android.gms.maps.SupportMapFragment
 * JD-Core Version:    0.6.0
 */