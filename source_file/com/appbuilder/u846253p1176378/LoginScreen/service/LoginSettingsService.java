package com.appbuilder.u846253p1176378.LoginScreen.service;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class LoginSettingsService
{
  public static String LOGIN_SETTING;
  public static String PASSWORD_SETTING = "PASSWORD_SETTING";

  static
  {
    LOGIN_SETTING = "LOGIN_SETTING";
  }

  public static void deleteSettings(SharedPreferences paramSharedPreferences)
  {
    paramSharedPreferences.edit().remove(LOGIN_SETTING).remove(PASSWORD_SETTING).commit();
  }

  public static LoginSettings loadSettings(SharedPreferences paramSharedPreferences)
  {
    return new LoginSettings(paramSharedPreferences.getString(LOGIN_SETTING, ""), paramSharedPreferences.getString(PASSWORD_SETTING, ""));
  }

  public static void saveSettings(SharedPreferences paramSharedPreferences, LoginSettings paramLoginSettings)
  {
    paramSharedPreferences.edit().putString(LOGIN_SETTING, paramLoginSettings.getUsername()).putString(PASSWORD_SETTING, paramLoginSettings.getPassword()).commit();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.LoginScreen.service.LoginSettingsService
 * JD-Core Version:    0.6.0
 */