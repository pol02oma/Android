package com.ibuildapp.romanblack.MapPlugin;

import android.util.Log;
import java.util.ArrayList;

public class MapWebPageCreator
{
  static String createMapPage(String paramString, ArrayList<MapItem> paramArrayList, int paramInt, float paramFloat1, float paramFloat2)
  {
    return replaceCenterCoordinates(paramString.replace("__RePlAcE-Points__", createPoints(paramArrayList)), paramArrayList, paramInt, paramFloat1, paramFloat2);
  }

  private static String createPoints(ArrayList<MapItem> paramArrayList)
  {
    Object localObject = "";
    int i = 0;
    while (true)
      if (i < paramArrayList.size())
        try
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("myMap.points.push({\n");
          localStringBuilder.append("point: '");
          localStringBuilder.append(((MapItem)paramArrayList.get(i)).getTitle().replaceAll("\n", " ").replaceAll("'", "\\\\'") + "',\n");
          localStringBuilder.append("latitude:");
          localStringBuilder.append(((MapItem)paramArrayList.get(i)).getLatitude());
          localStringBuilder.append(",\n");
          localStringBuilder.append("longitude:");
          localStringBuilder.append(((MapItem)paramArrayList.get(i)).getLongitude());
          localStringBuilder.append(",\n");
          localStringBuilder.append("details: '");
          localStringBuilder.append(((MapItem)paramArrayList.get(i)).getSubtitle().replaceAll("\n", " ").replaceAll("'", "\\\\'"));
          localStringBuilder.append("',\n");
          localStringBuilder.append("url: '");
          localStringBuilder.append(((MapItem)paramArrayList.get(i)).getDescription().replaceAll("\n", " ").replaceAll("'", "\\\\'"));
          localStringBuilder.append("',\n");
          if (((MapItem)paramArrayList.get(i)).getIconUrl().length() > 0)
          {
            localStringBuilder.append("icon: '");
            localStringBuilder.append(((MapItem)paramArrayList.get(i)).getIconUrl().trim());
            localStringBuilder.append("',\n");
          }
          localStringBuilder.append("})\n\n");
          String str1 = localStringBuilder.toString();
          String str2 = (String)localObject + str1;
          localObject = str2;
          i++;
        }
        catch (Exception localException)
        {
          while (true)
            Log.e("", "");
        }
    return (String)localObject;
  }

  private static int processZoom(float paramFloat1, float paramFloat2)
  {
    float f = Math.abs(paramFloat2 - paramFloat1);
    if (f > 120.0F)
      return 1;
    if (f > 60.0F)
      return 2;
    if (f > 30.0F)
      return 3;
    if (f > 15.0F)
      return 4;
    if (f > 8.0F)
      return 5;
    if (f > 4.0F)
      return 6;
    if (f > 2.0F)
      return 7;
    if (f > 1.0F)
      return 8;
    if (f > 0.5D)
      return 9;
    return 10;
  }

  private static String replaceCenterCoordinates(String paramString, ArrayList<MapItem> paramArrayList, int paramInt, float paramFloat1, float paramFloat2)
  {
    float f1 = -90.0F;
    float f2 = -180.0F;
    float f3 = 90.0F;
    float f4 = 180.0F;
    for (int i = 0; i < paramArrayList.size(); i++)
    {
      if (((MapItem)paramArrayList.get(i)).getLatitude() > f1)
        f1 = (float)((MapItem)paramArrayList.get(i)).getLatitude();
      if (((MapItem)paramArrayList.get(i)).getLongitude() > f2)
        f2 = (float)((MapItem)paramArrayList.get(i)).getLongitude();
      if (((MapItem)paramArrayList.get(i)).getLatitude() < f3)
        f3 = (float)((MapItem)paramArrayList.get(i)).getLatitude();
      if (((MapItem)paramArrayList.get(i)).getLongitude() >= f4)
        continue;
      f4 = (float)((MapItem)paramArrayList.get(i)).getLongitude();
    }
    float f5 = (f1 + f3) / 2.0F;
    float f6 = (f2 + f4) / 2.0F;
    String str = paramString.replace("__RePlAcE-Lat__", new Float(f5).toString()).replace("__RePlAcE-Lng__", new Float(f6).toString());
    if (paramInt != -1)
      return str.replace("__RePlAcE-Zoom__", Integer.toString(paramInt));
    return str.replace("__RePlAcE-Zoom__", new Integer(processZoom(f4, f2)).toString());
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.MapPlugin.MapWebPageCreator
 * JD-Core Version:    0.6.0
 */