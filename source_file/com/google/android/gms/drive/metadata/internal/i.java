package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.CollectionMetadataField;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import org.json.JSONArray;
import org.json.JSONException;

public class i extends CollectionMetadataField<String>
{
  public i(String paramString, int paramInt)
  {
    super(paramString, paramInt);
  }

  public static final Collection<String> as(String paramString)
    throws JSONException
  {
    if (paramString == null)
      return null;
    ArrayList localArrayList = new ArrayList();
    JSONArray localJSONArray = new JSONArray(paramString);
    for (int i = 0; i < localJSONArray.length(); i++)
      localArrayList.add(localJSONArray.getString(i));
    return Collections.unmodifiableCollection(localArrayList);
  }

  protected Collection<String> a(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    try
    {
      Collection localCollection = as(paramDataHolder.getString(getName(), paramInt1, paramInt2));
      return localCollection;
    }
    catch (JSONException localJSONException)
    {
    }
    throw new IllegalStateException("DataHolder supplied invalid JSON", localJSONException);
  }

  protected void a(Bundle paramBundle, Collection<String> paramCollection)
  {
    paramBundle.putStringArrayList(getName(), new ArrayList(paramCollection));
  }

  protected Collection<String> j(Bundle paramBundle)
  {
    return paramBundle.getStringArrayList(getName());
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.metadata.internal.i
 * JD-Core Version:    0.6.0
 */