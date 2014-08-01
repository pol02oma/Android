package com.ibuildapp.romanblack.NewsPlugin;

import android.graphics.Color;

public class Statics
{
  static int color1 = Color.parseColor("#ffffff");
  static int color2 = Color.parseColor("#ffffff");
  static int color3 = Color.parseColor("#000000");
  static int color4 = Color.parseColor("#000000");
  static int color5 = Color.parseColor("#000000");

  public static int BackColorToFontColor(int paramInt)
  {
    int i = 0xFF & paramInt >> 16;
    int j = 0xFF & paramInt >> 8;
    int k = 0xFF & paramInt >> 0;
    if (0.299D * i + 0.587D * j + 0.114D * k > 127.0D)
      return -16777216;
    return -1;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.NewsPlugin.Statics
 * JD-Core Version:    0.6.0
 */