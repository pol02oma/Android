package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.h;
import java.util.Arrays;

public class fu extends h<DriveId>
{
  public static final fu EI = new fu();

  private fu()
  {
    super("driveId", Arrays.asList(new String[] { "sqlId", "resourceId" }), 4100000);
  }

  protected DriveId i(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    long l = paramDataHolder.getMetadata().getLong("dbInstanceId");
    String str = paramDataHolder.getString("resourceId", paramInt1, paramInt2);
    if ((str != null) && (str.startsWith("generated-android-")))
      str = null;
    return new DriveId(str, Long.valueOf(paramDataHolder.getLong("sqlId", paramInt1, paramInt2)).longValue(), l);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.fu
 * JD-Core Version:    0.6.0
 */