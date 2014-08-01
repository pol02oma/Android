package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.drive.metadata.MetadataField;
import java.util.Collection;

public abstract class h<T extends Parcelable> extends MetadataField<T>
{
  public h(String paramString, Collection<String> paramCollection, int paramInt)
  {
    super(paramString, paramCollection, paramInt);
  }

  protected void a(Bundle paramBundle, T paramT)
  {
    paramBundle.putParcelable(getName(), paramT);
  }

  protected T k(Bundle paramBundle)
  {
    return paramBundle.getParcelable(getName());
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.metadata.internal.h
 * JD-Core Version:    0.6.0
 */