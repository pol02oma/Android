package com.google.android.gms.cast;

import android.text.TextUtils;
import com.google.android.gms.internal.dr;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.fp;
import org.json.JSONException;
import org.json.JSONObject;

public final class MediaInfo
{
  public static final int STREAM_TYPE_BUFFERED = 1;
  public static final int STREAM_TYPE_INVALID = -1;
  public static final int STREAM_TYPE_LIVE = 2;
  public static final int STREAM_TYPE_NONE;
  private final String wK;
  private int wL;
  private String wM;
  private MediaMetadata wN;
  private long wO;
  private JSONObject wP;

  MediaInfo(String paramString)
    throws IllegalArgumentException
  {
    if (TextUtils.isEmpty(paramString))
      throw new IllegalArgumentException("content ID cannot be null or empty");
    this.wK = paramString;
    this.wL = -1;
  }

  MediaInfo(JSONObject paramJSONObject)
    throws JSONException
  {
    this.wK = paramJSONObject.getString("contentId");
    String str = paramJSONObject.getString("streamType");
    if ("NONE".equals(str))
      this.wL = 0;
    while (true)
    {
      this.wM = paramJSONObject.getString("contentType");
      if (paramJSONObject.has("metadata"))
      {
        JSONObject localJSONObject = paramJSONObject.getJSONObject("metadata");
        this.wN = new MediaMetadata(localJSONObject.getInt("metadataType"));
        this.wN.b(localJSONObject);
      }
      this.wO = dr.b(paramJSONObject.optDouble("duration", 0.0D));
      this.wP = paramJSONObject.optJSONObject("customData");
      return;
      if ("BUFFERED".equals(str))
      {
        this.wL = 1;
        continue;
      }
      if ("LIVE".equals(str))
      {
        this.wL = 2;
        continue;
      }
      this.wL = -1;
    }
  }

  void a(MediaMetadata paramMediaMetadata)
  {
    this.wN = paramMediaMetadata;
  }

  void a(JSONObject paramJSONObject)
  {
    this.wP = paramJSONObject;
  }

  void cS()
    throws IllegalArgumentException
  {
    if (TextUtils.isEmpty(this.wK))
      throw new IllegalArgumentException("content ID cannot be null or empty");
    if (TextUtils.isEmpty(this.wM))
      throw new IllegalArgumentException("content type cannot be null or empty");
    if (this.wL == -1)
      throw new IllegalArgumentException("a valid stream type must be specified");
  }

  public JSONObject cT()
  {
    JSONObject localJSONObject = new JSONObject();
    while (true)
    {
      try
      {
        localJSONObject.put("contentId", this.wK);
        switch (this.wL)
        {
        case 2:
          localJSONObject.put("streamType", localObject);
          if (this.wM == null)
            continue;
          localJSONObject.put("contentType", this.wM);
          if (this.wN == null)
            continue;
          localJSONObject.put("metadata", this.wN.cT());
          localJSONObject.put("duration", dr.l(this.wO));
          if (this.wP == null)
            break label143;
          localJSONObject.put("customData", this.wP);
          return localJSONObject;
          localObject = "LIVE";
          continue;
        default:
        case 1:
        }
      }
      catch (JSONException localJSONException)
      {
        return localJSONObject;
      }
      Object localObject = "NONE";
      continue;
      label143: return localJSONObject;
      localObject = "BUFFERED";
    }
  }

  public boolean equals(Object paramObject)
  {
    int i = 1;
    int j;
    if (this == paramObject)
      j = i;
    int k;
    int m;
    while (true)
    {
      return j;
      boolean bool1 = paramObject instanceof MediaInfo;
      j = 0;
      if (!bool1)
        continue;
      MediaInfo localMediaInfo = (MediaInfo)paramObject;
      if (this.wP != null)
        break;
      k = i;
      if (localMediaInfo.wP != null)
        break label177;
      m = i;
      label52: j = 0;
      if (k != m)
        continue;
      if ((this.wP != null) && (localMediaInfo.wP != null))
      {
        boolean bool2 = fp.d(this.wP, localMediaInfo.wP);
        j = 0;
        if (!bool2)
          continue;
      }
      if ((!dr.a(this.wK, localMediaInfo.wK)) || (this.wL != localMediaInfo.wL) || (!dr.a(this.wM, localMediaInfo.wM)) || (!dr.a(this.wN, localMediaInfo.wN)) || (this.wO != localMediaInfo.wO))
        break label183;
    }
    while (true)
    {
      return i;
      k = 0;
      break;
      label177: m = 0;
      break label52;
      label183: i = 0;
    }
  }

  public String getContentId()
  {
    return this.wK;
  }

  public String getContentType()
  {
    return this.wM;
  }

  public JSONObject getCustomData()
  {
    return this.wP;
  }

  public MediaMetadata getMetadata()
  {
    return this.wN;
  }

  public long getStreamDuration()
  {
    return this.wO;
  }

  public int getStreamType()
  {
    return this.wL;
  }

  public int hashCode()
  {
    Object[] arrayOfObject = new Object[6];
    arrayOfObject[0] = this.wK;
    arrayOfObject[1] = Integer.valueOf(this.wL);
    arrayOfObject[2] = this.wM;
    arrayOfObject[3] = this.wN;
    arrayOfObject[4] = Long.valueOf(this.wO);
    arrayOfObject[5] = String.valueOf(this.wP);
    return ep.hashCode(arrayOfObject);
  }

  void j(long paramLong)
    throws IllegalArgumentException
  {
    if (paramLong < 0L)
      throw new IllegalArgumentException("Stream duration cannot be negative");
    this.wO = paramLong;
  }

  void setContentType(String paramString)
    throws IllegalArgumentException
  {
    if (TextUtils.isEmpty(paramString))
      throw new IllegalArgumentException("content type cannot be null or empty");
    this.wM = paramString;
  }

  void setStreamType(int paramInt)
    throws IllegalArgumentException
  {
    if ((paramInt < -1) || (paramInt > 2))
      throw new IllegalArgumentException("invalid stream type");
    this.wL = paramInt;
  }

  public static class Builder
  {
    private final MediaInfo wQ;

    public Builder(String paramString)
      throws IllegalArgumentException
    {
      if (TextUtils.isEmpty(paramString))
        throw new IllegalArgumentException("Content ID cannot be empty");
      this.wQ = new MediaInfo(paramString);
    }

    public MediaInfo build()
      throws IllegalArgumentException
    {
      this.wQ.cS();
      return this.wQ;
    }

    public Builder setContentType(String paramString)
      throws IllegalArgumentException
    {
      this.wQ.setContentType(paramString);
      return this;
    }

    public Builder setCustomData(JSONObject paramJSONObject)
    {
      this.wQ.a(paramJSONObject);
      return this;
    }

    public Builder setMetadata(MediaMetadata paramMediaMetadata)
    {
      this.wQ.a(paramMediaMetadata);
      return this;
    }

    public Builder setStreamDuration(long paramLong)
      throws IllegalArgumentException
    {
      this.wQ.j(paramLong);
      return this;
    }

    public Builder setStreamType(int paramInt)
      throws IllegalArgumentException
    {
      this.wQ.setStreamType(paramInt);
      return this;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.cast.MediaInfo
 * JD-Core Version:    0.6.0
 */