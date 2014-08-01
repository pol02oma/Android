package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.common.images.WebImage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class dz
{
  private static final du xE = new du("MetadataUtils");
  private static final String[] yE = { "Z", "+hh", "+hhmm", "+hh:mm" };
  private static final String yF = "yyyyMMdd'T'HHmmss" + yE[0];

  public static Calendar V(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
    {
      xE.b("Input string is empty or null", new Object[0]);
      return null;
    }
    String str1 = W(paramString);
    if (TextUtils.isEmpty(str1))
    {
      xE.b("Invalid date format", new Object[0]);
      return null;
    }
    String str2 = X(paramString);
    String str3 = "yyyyMMdd";
    if (!TextUtils.isEmpty(str2))
    {
      str1 = str1 + "T" + str2;
      if (str2.length() != "HHmmss".length())
        break label130;
      str3 = "yyyyMMdd'T'HHmmss";
    }
    while (true)
    {
      Calendar localCalendar = GregorianCalendar.getInstance();
      try
      {
        Date localDate = new SimpleDateFormat(str3).parse(str1);
        localCalendar.setTime(localDate);
        return localCalendar;
        label130: str3 = yF;
      }
      catch (ParseException localParseException)
      {
        du localdu = xE;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localParseException.getMessage();
        localdu.b("Error parsing string: %s", arrayOfObject);
      }
    }
    return null;
  }

  private static String W(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
    {
      xE.b("Input string is empty or null", new Object[0]);
      return null;
    }
    try
    {
      String str = paramString.substring(0, "yyyyMMdd".length());
      return str;
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
      du localdu = xE;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localIndexOutOfBoundsException.getMessage();
      localdu.c("Error extracting the date: %s", arrayOfObject);
    }
    return null;
  }

  private static String X(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      xE.b("string is empty or null", new Object[0]);
    String str;
    do
    {
      do
      {
        return null;
        int i = paramString.indexOf('T');
        int j = i + 1;
        if (i != "yyyyMMdd".length())
        {
          xE.b("T delimeter is not found", new Object[0]);
          return null;
        }
        try
        {
          str = paramString.substring(j);
          if (str.length() == "HHmmss".length())
            return str;
        }
        catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
        {
          du localdu = xE;
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = localIndexOutOfBoundsException.getMessage();
          localdu.b("Error extracting the time substring: %s", arrayOfObject);
          return null;
        }
        switch (str.charAt("HHmmss".length()))
        {
        default:
          return null;
        case '+':
        case '-':
        case 'Z':
        }
      }
      while (!Y(str));
      return str.replaceAll("([\\+\\-]\\d\\d):(\\d\\d)", "$1$2");
    }
    while (str.length() != "HHmmss".length() + yE[0].length());
    return str.substring(0, -1 + str.length()) + "+0000";
  }

  private static boolean Y(String paramString)
  {
    int i = paramString.length();
    int j = "HHmmss".length();
    return (i == j + yE[1].length()) || (i == j + yE[2].length()) || (i == j + yE[3].length());
  }

  public static String a(Calendar paramCalendar)
  {
    String str2;
    if (paramCalendar == null)
    {
      xE.b("Calendar object cannot be null", new Object[0]);
      str2 = null;
    }
    do
    {
      return str2;
      String str1 = yF;
      if ((paramCalendar.get(11) == 0) && (paramCalendar.get(12) == 0) && (paramCalendar.get(13) == 0))
        str1 = "yyyyMMdd";
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(str1);
      localSimpleDateFormat.setTimeZone(paramCalendar.getTimeZone());
      str2 = localSimpleDateFormat.format(paramCalendar.getTime());
    }
    while (!str2.endsWith("+0000"));
    return str2.replace("+0000", yE[0]);
  }

  // ERROR //
  public static void a(List<WebImage> paramList, JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokeinterface 192 1 0
    //   6: aload_1
    //   7: ldc 194
    //   9: invokevirtual 200	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   12: astore_3
    //   13: aload_3
    //   14: invokevirtual 203	org/json/JSONArray:length	()I
    //   17: istore 4
    //   19: iconst_0
    //   20: istore 5
    //   22: iload 5
    //   24: iload 4
    //   26: if_icmpge +34 -> 60
    //   29: aload_3
    //   30: iload 5
    //   32: invokevirtual 207	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   35: astore 6
    //   37: aload_0
    //   38: new 209	com/google/android/gms/common/images/WebImage
    //   41: dup
    //   42: aload 6
    //   44: invokespecial 212	com/google/android/gms/common/images/WebImage:<init>	(Lorg/json/JSONObject;)V
    //   47: invokeinterface 216 2 0
    //   52: pop
    //   53: iinc 5 1
    //   56: goto -34 -> 22
    //   59: astore_2
    //   60: return
    //   61: astore 7
    //   63: goto -10 -> 53
    //
    // Exception table:
    //   from	to	target	type
    //   0	19	59	org/json/JSONException
    //   29	37	59	org/json/JSONException
    //   37	53	59	org/json/JSONException
    //   37	53	61	java/lang/IllegalArgumentException
  }

  public static void a(JSONObject paramJSONObject, List<WebImage> paramList)
  {
    JSONArray localJSONArray;
    if ((paramList != null) && (!paramList.isEmpty()))
    {
      localJSONArray = new JSONArray();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
        localJSONArray.put(((WebImage)localIterator.next()).cT());
    }
    try
    {
      paramJSONObject.put("images", localJSONArray);
      return;
    }
    catch (JSONException localJSONException)
    {
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.dz
 * JD-Core Version:    0.6.0
 */