package com.ibuildapp.romanblack.TableReservationPlugin.utils;

public class Utils
{
  private static final int SECONDS_IN_DAY = 86400;

  public static String convertTimeToFormat(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    int i = paramInt1 * 3600 + paramInt2 * 60;
    if (i > 86400)
    {
      int k = i - 86400;
      paramInt1 = k / 3600;
      paramInt2 = k / 60 - paramInt1 * 60;
    }
    if (paramBoolean)
    {
      String str7;
      String str9;
      if (Integer.toString(paramInt1).length() < 2)
      {
        String str10 = Integer.toString(paramInt1);
        str7 = "0" + str10;
        if (Integer.toString(paramInt2).length() >= 2)
          break label158;
        str9 = Integer.toString(paramInt2);
      }
      label158: for (String str8 = "0" + str9; ; str8 = Integer.toString(paramInt2))
      {
        return str7 + ":" + str8;
        str7 = Integer.toString(paramInt1);
        break;
      }
    }
    String str1;
    String str2;
    label222: int j;
    String str3;
    if (paramInt2 + paramInt1 * 100 >= 1200)
    {
      str1 = "PM";
      if (Integer.toString(paramInt2).length() >= 2)
        break label318;
      String str6 = Integer.toString(paramInt2);
      str2 = "0" + str6;
      if (paramInt1 <= 12)
        break label337;
      j = -12 + paramInt1;
      if (Integer.toString(j).length() >= 2)
        break label327;
      String str5 = Integer.toString(j);
      str3 = "0" + str5;
    }
    while (true)
    {
      return str3 + ":" + str2 + " " + str1;
      str1 = "AM";
      break;
      label318: str2 = Integer.toString(paramInt2);
      break label222;
      label327: str3 = Integer.toString(j);
      continue;
      label337: if (Integer.toString(paramInt1).length() < 2)
      {
        String str4 = Integer.toString(paramInt1).toString();
        str3 = "0" + str4;
        continue;
      }
      str3 = Integer.toString(paramInt1).toString();
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.TableReservationPlugin.utils.Utils
 * JD-Core Version:    0.6.0
 */