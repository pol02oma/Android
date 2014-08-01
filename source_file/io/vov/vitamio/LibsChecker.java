package io.vov.vitamio;

import android.app.Activity;
import android.content.Intent;

public final class LibsChecker
{
  public static final String FROM_ME = "fromVitamioInitActivity";

  public static final boolean checkVitamioLibs(Activity paramActivity)
  {
    if ((!Vitamio.isInitialized(paramActivity)) && (!paramActivity.getIntent().getBooleanExtra("fromVitamioInitActivity", false)))
    {
      Intent localIntent = new Intent();
      localIntent.setClassName(Vitamio.getVitamioPackage(), "io.vov.vitamio.activity.InitActivity");
      localIntent.putExtras(paramActivity.getIntent());
      localIntent.setData(paramActivity.getIntent().getData());
      localIntent.putExtra("package", paramActivity.getPackageName());
      localIntent.putExtra("className", paramActivity.getClass().getName());
      paramActivity.startActivity(localIntent);
      paramActivity.finish();
      return false;
    }
    return true;
  }

  public static boolean checkVitamioLibs(Activity paramActivity, int paramInt)
  {
    if ((!Vitamio.isInitialized(paramActivity)) && (!paramActivity.getIntent().getBooleanExtra("fromVitamioInitActivity", false)))
    {
      Intent localIntent = new Intent();
      localIntent.setClassName(Vitamio.getVitamioPackage(), "io.vov.vitamio.activity.InitActivity");
      localIntent.putExtras(paramActivity.getIntent());
      localIntent.setData(paramActivity.getIntent().getData());
      localIntent.putExtra("package", paramActivity.getPackageName());
      localIntent.putExtra("className", paramActivity.getClass().getName());
      localIntent.putExtra("libarm", paramInt);
      paramActivity.startActivity(localIntent);
      paramActivity.finish();
      return false;
    }
    return true;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     io.vov.vitamio.LibsChecker
 * JD-Core Version:    0.6.0
 */