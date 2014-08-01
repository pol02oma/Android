package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class j extends Metadata
{
  private final MetadataBundle CZ;

  public j(MetadataBundle paramMetadataBundle)
  {
    this.CZ = paramMetadataBundle;
  }

  protected <T> T a(MetadataField<T> paramMetadataField)
  {
    return this.CZ.a(paramMetadataField);
  }

  public Metadata eQ()
  {
    return new j(MetadataBundle.a(this.CZ));
  }

  public boolean isDataValid()
  {
    return this.CZ != null;
  }

  public String toString()
  {
    return "Metadata [mImpl=" + this.CZ + "]";
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.j
 * JD-Core Version:    0.6.0
 */