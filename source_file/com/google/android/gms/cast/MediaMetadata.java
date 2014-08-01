package com.google.android.gms.cast;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.internal.dz;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class MediaMetadata
{
  public static final String KEY_ALBUM_ARTIST = "com.google.android.gms.cast.metadata.ALBUM_ARTIST";
  public static final String KEY_ALBUM_TITLE = "com.google.android.gms.cast.metadata.ALBUM_TITLE";
  public static final String KEY_ARTIST = "com.google.android.gms.cast.metadata.ARTIST";
  public static final String KEY_BROADCAST_DATE = "com.google.android.gms.cast.metadata.BROADCAST_DATE";
  public static final String KEY_COMPOSER = "com.google.android.gms.cast.metadata.COMPOSER";
  public static final String KEY_CREATION_DATE = "com.google.android.gms.cast.metadata.CREATION_DATE";
  public static final String KEY_DISC_NUMBER = "com.google.android.gms.cast.metadata.DISC_NUMBER";
  public static final String KEY_EPISODE_NUMBER = "com.google.android.gms.cast.metadata.EPISODE_NUMBER";
  public static final String KEY_HEIGHT = "com.google.android.gms.cast.metadata.HEIGHT";
  public static final String KEY_LOCATION_LATITUDE = "com.google.android.gms.cast.metadata.LOCATION_LATITUDE";
  public static final String KEY_LOCATION_LONGITUDE = "com.google.android.gms.cast.metadata.LOCATION_LONGITUDE";
  public static final String KEY_LOCATION_NAME = "com.google.android.gms.cast.metadata.LOCATION_NAME";
  public static final String KEY_RELEASE_DATE = "com.google.android.gms.cast.metadata.RELEASE_DATE";
  public static final String KEY_SEASON_NUMBER = "com.google.android.gms.cast.metadata.SEASON_NUMBER";
  public static final String KEY_SERIES_TITLE = "com.google.android.gms.cast.metadata.SERIES_TITLE";
  public static final String KEY_STUDIO = "com.google.android.gms.cast.metadata.STUDIO";
  public static final String KEY_SUBTITLE = "com.google.android.gms.cast.metadata.SUBTITLE";
  public static final String KEY_TITLE = "com.google.android.gms.cast.metadata.TITLE";
  public static final String KEY_TRACK_NUMBER = "com.google.android.gms.cast.metadata.TRACK_NUMBER";
  public static final String KEY_WIDTH = "com.google.android.gms.cast.metadata.WIDTH";
  public static final int MEDIA_TYPE_GENERIC = 0;
  public static final int MEDIA_TYPE_MOVIE = 1;
  public static final int MEDIA_TYPE_MUSIC_TRACK = 3;
  public static final int MEDIA_TYPE_PHOTO = 4;
  public static final int MEDIA_TYPE_TV_SHOW = 2;
  public static final int MEDIA_TYPE_USER = 100;
  private static final String[] wR = { null, "String", "int", "double", "ISO-8601 date String" };
  private static final a wS = new a().a("com.google.android.gms.cast.metadata.CREATION_DATE", "creationDateTime", 4).a("com.google.android.gms.cast.metadata.RELEASE_DATE", "releaseDate", 4).a("com.google.android.gms.cast.metadata.BROADCAST_DATE", "originalAirdate", 4).a("com.google.android.gms.cast.metadata.TITLE", "title", 1).a("com.google.android.gms.cast.metadata.SUBTITLE", "subtitle", 1).a("com.google.android.gms.cast.metadata.ARTIST", "artist", 1).a("com.google.android.gms.cast.metadata.ALBUM_ARTIST", "albumArtist", 1).a("com.google.android.gms.cast.metadata.ALBUM_TITLE", "albumName", 1).a("com.google.android.gms.cast.metadata.COMPOSER", "composer", 1).a("com.google.android.gms.cast.metadata.DISC_NUMBER", "discNumber", 2).a("com.google.android.gms.cast.metadata.TRACK_NUMBER", "trackNumber", 2).a("com.google.android.gms.cast.metadata.SEASON_NUMBER", "season", 2).a("com.google.android.gms.cast.metadata.EPISODE_NUMBER", "episode", 2).a("com.google.android.gms.cast.metadata.SERIES_TITLE", "seriesTitle", 1).a("com.google.android.gms.cast.metadata.STUDIO", "studio", 1).a("com.google.android.gms.cast.metadata.WIDTH", "width", 2).a("com.google.android.gms.cast.metadata.HEIGHT", "height", 2).a("com.google.android.gms.cast.metadata.LOCATION_NAME", "location", 1).a("com.google.android.gms.cast.metadata.LOCATION_LATITUDE", "latitude", 3).a("com.google.android.gms.cast.metadata.LOCATION_LONGITUDE", "longitude", 3);
  private final Bundle wT = new Bundle();
  private int wU;
  private final List<WebImage> wl = new ArrayList();

  public MediaMetadata()
  {
    this(0);
  }

  public MediaMetadata(int paramInt)
  {
    this.wU = paramInt;
  }

  private void a(JSONObject paramJSONObject, String[] paramArrayOfString)
  {
    try
    {
      int i = paramArrayOfString.length;
      int j = 0;
      String str1;
      if (j < i)
      {
        str1 = paramArrayOfString[j];
        if (!this.wT.containsKey(str1));
      }
      else
      {
        switch (wS.O(str1))
        {
        case 1:
        case 4:
          paramJSONObject.put(wS.M(str1), this.wT.getString(str1));
          break;
        case 2:
          paramJSONObject.put(wS.M(str1), this.wT.getInt(str1));
          break;
        case 3:
          paramJSONObject.put(wS.M(str1), this.wT.getDouble(str1));
          break;
          Iterator localIterator = this.wT.keySet().iterator();
          while (localIterator.hasNext())
          {
            String str2 = (String)localIterator.next();
            if (str2.startsWith("com.google."))
              continue;
            Object localObject = this.wT.get(str2);
            if ((localObject instanceof String))
            {
              paramJSONObject.put(str2, localObject);
              continue;
            }
            if ((localObject instanceof Integer))
            {
              paramJSONObject.put(str2, localObject);
              continue;
            }
            if (!(localObject instanceof Double))
              continue;
            paramJSONObject.put(str2, localObject);
          }
        }
      }
      while (true)
      {
        j++;
        break;
      }
    }
    catch (JSONException localJSONException)
    {
    }
  }

  private boolean a(Bundle paramBundle1, Bundle paramBundle2)
  {
    if (paramBundle1.size() != paramBundle2.size())
      return false;
    Iterator localIterator = paramBundle1.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      Object localObject1 = paramBundle1.get(str);
      Object localObject2 = paramBundle2.get(str);
      if (((localObject1 instanceof Bundle)) && ((localObject2 instanceof Bundle)) && (!a((Bundle)localObject1, (Bundle)localObject2)))
        return false;
      if (localObject1 == null)
        if ((localObject2 != null) || (!paramBundle2.containsKey(str)))
          return false;
      if (!localObject1.equals(localObject2))
        return false;
    }
    return true;
  }

  private void b(JSONObject paramJSONObject, String[] paramArrayOfString)
  {
    HashSet localHashSet = new HashSet(Arrays.asList(paramArrayOfString));
    try
    {
      Iterator localIterator = paramJSONObject.keys();
      while (localIterator.hasNext())
      {
        String str1 = (String)localIterator.next();
        if ("metadataType".equals(str1))
          continue;
        String str2 = wS.N(str1);
        if (str2 != null)
        {
          boolean bool = localHashSet.contains(str2);
          if (!bool)
            continue;
        }
        try
        {
          Object localObject1 = paramJSONObject.get(str1);
          if (localObject1 == null)
            continue;
          switch (wS.O(str2))
          {
          case 1:
            if (!(localObject1 instanceof String))
              continue;
            this.wT.putString(str2, (String)localObject1);
            break;
          case 4:
            if ((!(localObject1 instanceof String)) || (dz.V((String)localObject1) == null))
              continue;
            this.wT.putString(str2, (String)localObject1);
            break;
          case 2:
            if (!(localObject1 instanceof Integer))
              continue;
            this.wT.putInt(str2, ((Integer)localObject1).intValue());
            break;
          case 3:
            if (!(localObject1 instanceof Double))
              continue;
            this.wT.putDouble(str2, ((Double)localObject1).doubleValue());
            continue;
            Object localObject2 = paramJSONObject.get(str1);
            if ((localObject2 instanceof String))
            {
              this.wT.putString(str1, (String)localObject2);
              continue;
            }
            if ((localObject2 instanceof Integer))
            {
              this.wT.putInt(str1, ((Integer)localObject2).intValue());
              continue;
            }
            if (!(localObject2 instanceof Double))
              continue;
            this.wT.putDouble(str1, ((Double)localObject2).doubleValue());
            continue;
          }
        }
        catch (JSONException localJSONException2)
        {
        }
      }
    }
    catch (JSONException localJSONException1)
    {
    }
  }

  private void d(String paramString, int paramInt)
    throws IllegalArgumentException
  {
    if (TextUtils.isEmpty(paramString))
      throw new IllegalArgumentException("null and empty keys are not allowed");
    int i = wS.O(paramString);
    if ((i != paramInt) && (i != 0))
      throw new IllegalArgumentException("Value for " + paramString + " must be a " + wR[paramInt]);
  }

  public void addImage(WebImage paramWebImage)
  {
    this.wl.add(paramWebImage);
  }

  public void b(JSONObject paramJSONObject)
  {
    clear();
    this.wU = 0;
    try
    {
      this.wU = paramJSONObject.getInt("metadataType");
      label20: dz.a(this.wl, paramJSONObject);
      switch (this.wU)
      {
      default:
        b(paramJSONObject, new String[0]);
        return;
      case 0:
        b(paramJSONObject, new String[] { "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.SUBTITLE", "com.google.android.gms.cast.metadata.RELEASE_DATE" });
        return;
      case 1:
        b(paramJSONObject, new String[] { "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.STUDIO", "com.google.android.gms.cast.metadata.SUBTITLE", "com.google.android.gms.cast.metadata.RELEASE_DATE" });
        return;
      case 2:
        b(paramJSONObject, new String[] { "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.SERIES_TITLE", "com.google.android.gms.cast.metadata.SEASON_NUMBER", "com.google.android.gms.cast.metadata.EPISODE_NUMBER", "com.google.android.gms.cast.metadata.BROADCAST_DATE" });
        return;
      case 3:
        b(paramJSONObject, new String[] { "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ALBUM_TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.ALBUM_ARTIST", "com.google.android.gms.cast.metadata.COMPOSER", "com.google.android.gms.cast.metadata.TRACK_NUMBER", "com.google.android.gms.cast.metadata.DISC_NUMBER", "com.google.android.gms.cast.metadata.RELEASE_DATE" });
        return;
      case 4:
      }
      b(paramJSONObject, new String[] { "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.LOCATION_NAME", "com.google.android.gms.cast.metadata.LOCATION_LATITUDE", "com.google.android.gms.cast.metadata.LOCATION_LONGITUDE", "com.google.android.gms.cast.metadata.WIDTH", "com.google.android.gms.cast.metadata.HEIGHT", "com.google.android.gms.cast.metadata.CREATION_DATE" });
      return;
    }
    catch (JSONException localJSONException)
    {
      break label20;
    }
  }

  public JSONObject cT()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("metadataType", this.wU);
      label20: dz.a(localJSONObject, this.wl);
      switch (this.wU)
      {
      default:
        a(localJSONObject, new String[0]);
        return localJSONObject;
      case 0:
        a(localJSONObject, new String[] { "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.SUBTITLE", "com.google.android.gms.cast.metadata.RELEASE_DATE" });
        return localJSONObject;
      case 1:
        a(localJSONObject, new String[] { "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.STUDIO", "com.google.android.gms.cast.metadata.SUBTITLE", "com.google.android.gms.cast.metadata.RELEASE_DATE" });
        return localJSONObject;
      case 2:
        a(localJSONObject, new String[] { "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.SERIES_TITLE", "com.google.android.gms.cast.metadata.SEASON_NUMBER", "com.google.android.gms.cast.metadata.EPISODE_NUMBER", "com.google.android.gms.cast.metadata.BROADCAST_DATE" });
        return localJSONObject;
      case 3:
        a(localJSONObject, new String[] { "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.ALBUM_TITLE", "com.google.android.gms.cast.metadata.ALBUM_ARTIST", "com.google.android.gms.cast.metadata.COMPOSER", "com.google.android.gms.cast.metadata.TRACK_NUMBER", "com.google.android.gms.cast.metadata.DISC_NUMBER", "com.google.android.gms.cast.metadata.RELEASE_DATE" });
        return localJSONObject;
      case 4:
      }
      a(localJSONObject, new String[] { "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.LOCATION_NAME", "com.google.android.gms.cast.metadata.LOCATION_LATITUDE", "com.google.android.gms.cast.metadata.LOCATION_LONGITUDE", "com.google.android.gms.cast.metadata.WIDTH", "com.google.android.gms.cast.metadata.HEIGHT", "com.google.android.gms.cast.metadata.CREATION_DATE" });
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      break label20;
    }
  }

  public void clear()
  {
    this.wT.clear();
    this.wl.clear();
  }

  public void clearImages()
  {
    this.wl.clear();
  }

  public boolean containsKey(String paramString)
  {
    return this.wT.containsKey(paramString);
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    MediaMetadata localMediaMetadata;
    do
    {
      return true;
      if (!(paramObject instanceof MediaMetadata))
        return false;
      localMediaMetadata = (MediaMetadata)paramObject;
    }
    while ((a(this.wT, localMediaMetadata.wT)) && (this.wl.equals(localMediaMetadata.wl)));
    return false;
  }

  public Calendar getDate(String paramString)
  {
    d(paramString, 4);
    String str = this.wT.getString(paramString);
    if (str != null)
      return dz.V(str);
    return null;
  }

  public String getDateAsString(String paramString)
  {
    d(paramString, 4);
    return this.wT.getString(paramString);
  }

  public double getDouble(String paramString)
  {
    d(paramString, 3);
    return this.wT.getDouble(paramString);
  }

  public List<WebImage> getImages()
  {
    return this.wl;
  }

  public int getInt(String paramString)
  {
    d(paramString, 2);
    return this.wT.getInt(paramString);
  }

  public int getMediaType()
  {
    return this.wU;
  }

  public String getString(String paramString)
  {
    d(paramString, 1);
    return this.wT.getString(paramString);
  }

  public boolean hasImages()
  {
    return (this.wl != null) && (!this.wl.isEmpty());
  }

  public int hashCode()
  {
    Iterator localIterator = this.wT.keySet().iterator();
    String str;
    for (int i = 17; localIterator.hasNext(); i = i * 31 + this.wT.get(str).hashCode())
      str = (String)localIterator.next();
    return i * 31 + this.wl.hashCode();
  }

  public Set<String> keySet()
  {
    return this.wT.keySet();
  }

  public void putDate(String paramString, Calendar paramCalendar)
  {
    d(paramString, 4);
    this.wT.putString(paramString, dz.a(paramCalendar));
  }

  public void putDouble(String paramString, double paramDouble)
  {
    d(paramString, 3);
    this.wT.putDouble(paramString, paramDouble);
  }

  public void putInt(String paramString, int paramInt)
  {
    d(paramString, 2);
    this.wT.putInt(paramString, paramInt);
  }

  public void putString(String paramString1, String paramString2)
  {
    d(paramString1, 1);
    this.wT.putString(paramString1, paramString2);
  }

  private static class a
  {
    private final Map<String, String> wV = new HashMap();
    private final Map<String, String> wW = new HashMap();
    private final Map<String, Integer> wX = new HashMap();

    public String M(String paramString)
    {
      return (String)this.wV.get(paramString);
    }

    public String N(String paramString)
    {
      return (String)this.wW.get(paramString);
    }

    public int O(String paramString)
    {
      Integer localInteger = (Integer)this.wX.get(paramString);
      if (localInteger != null)
        return localInteger.intValue();
      return 0;
    }

    public a a(String paramString1, String paramString2, int paramInt)
    {
      this.wV.put(paramString1, paramString2);
      this.wW.put(paramString2, paramString1);
      this.wX.put(paramString1, Integer.valueOf(paramInt));
      return this;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.cast.MediaMetadata
 * JD-Core Version:    0.6.0
 */