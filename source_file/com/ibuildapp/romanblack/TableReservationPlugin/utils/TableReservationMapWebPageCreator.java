package com.ibuildapp.romanblack.TableReservationPlugin.utils;

public class TableReservationMapWebPageCreator
{
  public static String createMapPage(String paramString1, Double paramDouble1, Double paramDouble2, String paramString2, String paramString3)
  {
    return replaceCenterCoordinates(paramString1.replace("__RePlAcE-Points__", createPoints(paramDouble1, paramDouble2, paramString2, paramString3)), paramDouble1, paramDouble2);
  }

  private static String createPoints(Double paramDouble1, Double paramDouble2, String paramString1, String paramString2)
  {
    return "myMap.points.push({\npoint: '" + paramString1.replaceAll("\n", " ").replaceAll("'", "\\\\'") + "',\n" + "latitude:" + Double.toString(paramDouble1.doubleValue()) + ",\n" + "longitude:" + Double.toString(paramDouble2.doubleValue()) + ",\n" + "details: '" + paramString2.replaceAll("\n", " ").replaceAll("'", "\\\\'") + "',\n" + "})\n\n";
  }

  private static String replaceCenterCoordinates(String paramString, Double paramDouble1, Double paramDouble2)
  {
    return paramString.replace("__RePlAcE-Lat__", Double.toString(paramDouble1.doubleValue())).replace("__RePlAcE-Lng__", Double.toString(paramDouble2.doubleValue())).replace("__RePlAcE-Zoom__", "15");
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.TableReservationPlugin.utils.TableReservationMapWebPageCreator
 * JD-Core Version:    0.6.0
 */