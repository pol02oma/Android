package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.er;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class MetadataBundle
  implements SafeParcelable
{
  public static final Parcelable.Creator<MetadataBundle> CREATOR = new f();
  final Bundle Ek;
  final int wj;

  MetadataBundle(int paramInt, Bundle paramBundle)
  {
    this.wj = paramInt;
    this.Ek = ((Bundle)er.f(paramBundle));
    this.Ek.setClassLoader(getClass().getClassLoader());
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator1 = this.Ek.keySet().iterator();
    while (localIterator1.hasNext())
    {
      String str2 = (String)localIterator1.next();
      if (c.ar(str2) != null)
        continue;
      localArrayList.add(str2);
      Log.w("MetadataBundle", "Ignored unknown metadata field in bundle: " + str2);
    }
    Iterator localIterator2 = localArrayList.iterator();
    while (localIterator2.hasNext())
    {
      String str1 = (String)localIterator2.next();
      this.Ek.remove(str1);
    }
  }

  private MetadataBundle(Bundle paramBundle)
  {
    this(1, paramBundle);
  }

  public static <T> MetadataBundle a(MetadataField<T> paramMetadataField, T paramT)
  {
    MetadataBundle localMetadataBundle = fh();
    localMetadataBundle.b(paramMetadataField, paramT);
    return localMetadataBundle;
  }

  public static MetadataBundle a(MetadataBundle paramMetadataBundle)
  {
    return new MetadataBundle(new Bundle(paramMetadataBundle.Ek));
  }

  public static MetadataBundle fh()
  {
    return new MetadataBundle(new Bundle());
  }

  public <T> T a(MetadataField<T> paramMetadataField)
  {
    return paramMetadataField.d(this.Ek);
  }

  public <T> void b(MetadataField<T> paramMetadataField, T paramT)
  {
    if (c.ar(paramMetadataField.getName()) == null)
      throw new IllegalArgumentException("Unregistered field: " + paramMetadataField.getName());
    paramMetadataField.a(paramT, this.Ek);
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if (!(paramObject instanceof MetadataBundle))
      return false;
    MetadataBundle localMetadataBundle = (MetadataBundle)paramObject;
    Set localSet = this.Ek.keySet();
    if (!localSet.equals(localMetadataBundle.Ek.keySet()))
      return false;
    Iterator localIterator = localSet.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (!ep.equal(this.Ek.get(str), localMetadataBundle.Ek.get(str)))
        return false;
    }
    return true;
  }

  public Set<MetadataField<?>> fi()
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = this.Ek.keySet().iterator();
    while (localIterator.hasNext())
      localHashSet.add(c.ar((String)localIterator.next()));
    return localHashSet;
  }

  public int hashCode()
  {
    Iterator localIterator = this.Ek.keySet().iterator();
    String str;
    for (int i = 1; localIterator.hasNext(); i = i * 31 + this.Ek.get(str).hashCode())
      str = (String)localIterator.next();
    return i;
  }

  public String toString()
  {
    return "MetadataBundle [values=" + this.Ek + "]";
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    f.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.metadata.internal.MetadataBundle
 * JD-Core Version:    0.6.0
 */