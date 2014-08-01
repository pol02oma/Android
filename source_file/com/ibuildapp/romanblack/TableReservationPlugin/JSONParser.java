package com.ibuildapp.romanblack.TableReservationPlugin;

import android.util.Log;
import com.ibuildapp.romanblack.TableReservationPlugin.entity.FanWallMessage;
import com.ibuildapp.romanblack.TableReservationPlugin.entity.FanWallUser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser
{
  private static String loadURLData(String paramString)
  {
    BufferedReader localBufferedReader;
    StringBuilder localStringBuilder;
    try
    {
      localBufferedReader = new BufferedReader(new InputStreamReader(new URL(paramString).openConnection().getInputStream()));
      localStringBuilder = new StringBuilder();
      while (true)
      {
        String str1 = localBufferedReader.readLine();
        if (str1 == null)
          break;
        localStringBuilder.append(str1);
        localStringBuilder.append("\n");
      }
    }
    catch (IOException localIOException)
    {
      return "";
    }
    localBufferedReader.close();
    String str2 = localStringBuilder.toString();
    return str2;
  }

  public static ArrayList<FanWallMessage> parseGalleryUrl(String paramString)
  {
    ArrayList localArrayList;
    try
    {
      String str = loadURLData(paramString);
      if (str == null)
        return null;
      if (str.length() == 0)
        return null;
      JSONArray localJSONArray1 = new JSONObject(str).getJSONArray("gallery");
      localArrayList = new ArrayList();
      for (int i = 0; i < localJSONArray1.length(); i++)
      {
        JSONObject localJSONObject = localJSONArray1.getJSONObject(i);
        FanWallMessage localFanWallMessage = new FanWallMessage();
        localFanWallMessage.setAuthor(localJSONObject.getString("user_name"));
        localFanWallMessage.setText(localJSONObject.getString("text"));
        JSONArray localJSONArray2 = localJSONObject.getJSONArray("images");
        if (localJSONArray2.length() > 0)
          localFanWallMessage.setImageUrl(localJSONArray2.getString(0));
        localArrayList.add(localFanWallMessage);
      }
    }
    catch (JSONException localJSONException)
    {
      localArrayList = null;
    }
    return localArrayList;
  }

  public static FanWallUser parseLoginRequestString(String paramString)
  {
    if (paramString == null)
      return null;
    try
    {
      if (paramString.length() == 0)
        return null;
      JSONObject localJSONObject = new JSONObject(paramString).getJSONObject("data");
      FanWallUser localFanWallUser = new FanWallUser();
      localFanWallUser.setAccountId(localJSONObject.getString("user_id"));
      localFanWallUser.setUserName(localJSONObject.getString("username"));
      localFanWallUser.setAvatarUrl(localJSONObject.getString("user_avatar"));
      localFanWallUser.setAccountType("ibuildapp");
      return localFanWallUser;
    }
    catch (JSONException localJSONException)
    {
    }
    return null;
  }

  public static FanWallUser parseLoginRequestUrl(String paramString)
  {
    try
    {
      String str = loadURLData(paramString);
      if (str == null)
        return null;
      if (str.length() == 0)
        return null;
      JSONObject localJSONObject = new JSONObject(str).getJSONObject("data");
      FanWallUser localFanWallUser = new FanWallUser();
      localFanWallUser.setAccountId(localJSONObject.getString("user_id"));
      localFanWallUser.setUserName(localJSONObject.getString("user_name"));
      localFanWallUser.setAvatarUrl(localJSONObject.getString("avatar"));
      localFanWallUser.setAccountType("ibuildapp");
      return localFanWallUser;
    }
    catch (JSONException localJSONException)
    {
    }
    return null;
  }

  public static ArrayList<FanWallMessage> parseMessagesString(String paramString)
  {
    ArrayList localArrayList;
    if (paramString == null)
      localArrayList = null;
    while (true)
    {
      return localArrayList;
      try
      {
        if (paramString.length() == 0)
          return null;
        JSONArray localJSONArray1 = new JSONObject(paramString).getJSONArray("posts");
        localArrayList = new ArrayList();
        int i = 0;
        while (i < localJSONArray1.length())
        {
          JSONObject localJSONObject = localJSONArray1.getJSONObject(i);
          FanWallMessage localFanWallMessage = new FanWallMessage();
          localFanWallMessage.setId(new Long(localJSONObject.getString("post_id")).longValue());
          localFanWallMessage.setAuthor(localJSONObject.getString("user_name"));
          localFanWallMessage.setDate(new Date(new Long(localJSONObject.getString("create")).longValue()));
          localFanWallMessage.setUserAvatarUrl(localJSONObject.getString("user_avatar"));
          localFanWallMessage.setText(localJSONObject.getString("text"));
          try
          {
            localFanWallMessage.setPoint(new Float(localJSONObject.getString("latitude")).floatValue(), new Float(localJSONObject.getString("longitude")).floatValue());
          }
          catch (NumberFormatException localNumberFormatException2)
          {
            try
            {
              localFanWallMessage.setParentId(new Integer(localJSONObject.getString("parent_id")).intValue());
            }
            catch (NumberFormatException localNumberFormatException2)
            {
              try
              {
                while (true)
                {
                  localFanWallMessage.setReplyId(new Integer(localJSONObject.getString("reply_id")).intValue());
                  localFanWallMessage.setTotalComments(new Integer(localJSONObject.getString("total_comments")).intValue());
                  JSONArray localJSONArray2 = localJSONObject.getJSONArray("images");
                  if (localJSONArray2.length() > 0)
                    localFanWallMessage.setImageUrl(localJSONArray2.getString(0));
                  localFanWallMessage.setAccountId(localJSONObject.getString("account_id"));
                  localFanWallMessage.setAccountType(localJSONObject.getString("account_type"));
                  localArrayList.add(localFanWallMessage);
                  i++;
                  break;
                  localNumberFormatException1 = localNumberFormatException1;
                  Log.e("", "");
                  continue;
                  localNumberFormatException2 = localNumberFormatException2;
                  Log.e("", "");
                }
              }
              catch (NumberFormatException localNumberFormatException3)
              {
                while (true)
                  Log.e("", "");
              }
            }
          }
        }
      }
      catch (JSONException localJSONException)
      {
      }
    }
    return null;
  }

  public static ArrayList<FanWallMessage> parseMessagesUrl(String paramString)
  {
    try
    {
      String str = loadURLData(paramString);
      if (str == null)
        return null;
      if (str.length() == 0)
        return null;
      JSONArray localJSONArray1 = new JSONObject(str).getJSONArray("posts");
      ArrayList localArrayList = new ArrayList();
      int i = 0;
      while (true)
        if (i < localJSONArray1.length())
        {
          JSONObject localJSONObject = localJSONArray1.getJSONObject(i);
          FanWallMessage localFanWallMessage = new FanWallMessage();
          localFanWallMessage.setId(Long.valueOf(localJSONObject.getString("post_id")).longValue());
          localFanWallMessage.setAuthor(localJSONObject.getString("user_name"));
          localFanWallMessage.setDate(new Date(Long.valueOf(localJSONObject.getString("create")).longValue()));
          localFanWallMessage.setUserAvatarUrl(localJSONObject.getString("user_avatar"));
          localFanWallMessage.setText(localJSONObject.getString("text"));
          try
          {
            localFanWallMessage.setPoint(Float.valueOf(localJSONObject.getString("latitude")).floatValue(), Float.valueOf(localJSONObject.getString("longitude")).floatValue());
            try
            {
              label182: localFanWallMessage.setParentId(Integer.valueOf(localJSONObject.getString("parent_id")).intValue());
            }
            catch (NumberFormatException localNumberFormatException2)
            {
              try
              {
                while (true)
                {
                  localFanWallMessage.setReplyId(Integer.valueOf(localJSONObject.getString("reply_id")).intValue());
                  localFanWallMessage.setTotalComments(Integer.valueOf(localJSONObject.getString("total_comments")).intValue());
                  JSONArray localJSONArray2 = localJSONObject.getJSONArray("images");
                  if (localJSONArray2.length() > 0)
                    localFanWallMessage.setImageUrl(localJSONArray2.getString(0));
                  localFanWallMessage.setAccountId(localJSONObject.getString("account_id"));
                  localFanWallMessage.setAccountType(localJSONObject.getString("account_type"));
                  localArrayList.add(localFanWallMessage);
                  i++;
                  break;
                  localNumberFormatException2 = localNumberFormatException2;
                  Log.e("", "");
                }
              }
              catch (NumberFormatException localNumberFormatException3)
              {
                while (true)
                  Log.e("", "");
              }
            }
          }
          catch (NumberFormatException localNumberFormatException1)
          {
            break label182;
          }
        }
      return localArrayList;
    }
    catch (JSONException localJSONException)
    {
    }
    return null;
  }

  // ERROR //
  public static orderParsedInfo parseOrderList(String paramString)
  {
    // Byte code:
    //   0: new 245	com/ibuildapp/romanblack/TableReservationPlugin/orderParsedInfo
    //   3: dup
    //   4: invokespecial 246	com/ibuildapp/romanblack/TableReservationPlugin/orderParsedInfo:<init>	()V
    //   7: astore_1
    //   8: new 72	org/json/JSONObject
    //   11: dup
    //   12: aload_0
    //   13: invokespecial 73	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   16: astore_2
    //   17: aload_1
    //   18: aload_2
    //   19: ldc 248
    //   21: invokevirtual 97	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   24: putfield 252	com/ibuildapp/romanblack/TableReservationPlugin/orderParsedInfo:errorNo	Ljava/lang/String;
    //   27: aload_2
    //   28: ldc 121
    //   30: invokevirtual 79	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   33: astore 4
    //   35: iconst_0
    //   36: istore 5
    //   38: iload 5
    //   40: aload 4
    //   42: invokevirtual 85	org/json/JSONArray:length	()I
    //   45: if_icmpge +151 -> 196
    //   48: aload 4
    //   50: iload 5
    //   52: invokevirtual 256	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   55: checkcast 72	org/json/JSONObject
    //   58: astore 6
    //   60: new 258	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationOrderInfo
    //   63: dup
    //   64: invokespecial 259	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationOrderInfo:<init>	()V
    //   67: astore 7
    //   69: aload 7
    //   71: aload 6
    //   73: ldc_w 261
    //   76: invokevirtual 97	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   79: putfield 264	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationOrderInfo:uuid	Ljava/lang/String;
    //   82: aload 7
    //   84: aload 6
    //   86: ldc_w 266
    //   89: invokevirtual 270	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   92: putfield 274	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationOrderInfo:personsAmount	I
    //   95: aload 7
    //   97: aload 6
    //   99: ldc_w 276
    //   102: invokevirtual 270	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   105: putfield 278	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationOrderInfo:status	I
    //   108: aload 7
    //   110: aload 6
    //   112: ldc_w 280
    //   115: invokevirtual 97	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   118: putfield 283	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationOrderInfo:specRequest	Ljava/lang/String;
    //   121: aload 7
    //   123: new 170	java/util/Date
    //   126: dup
    //   127: ldc2_w 284
    //   130: aload 6
    //   132: ldc_w 287
    //   135: invokevirtual 97	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   138: invokestatic 291	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   141: lmul
    //   142: invokespecial 174	java/util/Date:<init>	(J)V
    //   145: putfield 295	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationOrderInfo:orderDate	Ljava/util/Date;
    //   148: aload 7
    //   150: getfield 299	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationOrderInfo:orderTime	Lcom/ibuildapp/romanblack/TableReservationPlugin/entity/HouresMinutes;
    //   153: aload 7
    //   155: getfield 295	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationOrderInfo:orderDate	Ljava/util/Date;
    //   158: invokevirtual 302	java/util/Date:getHours	()I
    //   161: putfield 307	com/ibuildapp/romanblack/TableReservationPlugin/entity/HouresMinutes:houres	I
    //   164: aload 7
    //   166: getfield 299	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationOrderInfo:orderTime	Lcom/ibuildapp/romanblack/TableReservationPlugin/entity/HouresMinutes;
    //   169: aload 7
    //   171: getfield 295	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationOrderInfo:orderDate	Ljava/util/Date;
    //   174: invokevirtual 310	java/util/Date:getMinutes	()I
    //   177: putfield 313	com/ibuildapp/romanblack/TableReservationPlugin/entity/HouresMinutes:minutes	I
    //   180: aload_1
    //   181: getfield 317	com/ibuildapp/romanblack/TableReservationPlugin/orderParsedInfo:itemsArray	Ljava/util/ArrayList;
    //   184: aload 7
    //   186: invokevirtual 117	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   189: pop
    //   190: iinc 5 1
    //   193: goto -155 -> 38
    //   196: aload_1
    //   197: areturn
    //   198: astore_3
    //   199: aload_3
    //   200: invokevirtual 320	org/json/JSONException:printStackTrace	()V
    //   203: aconst_null
    //   204: areturn
    //   205: astore_3
    //   206: goto -7 -> 199
    //
    // Exception table:
    //   from	to	target	type
    //   0	17	198	org/json/JSONException
    //   17	35	205	org/json/JSONException
    //   38	190	205	org/json/JSONException
  }

  public static String[] parseProfileData(String paramString)
  {
    try
    {
      String[] arrayOfString = new String[3];
      String str = loadURLData(paramString);
      if (str == null)
        return null;
      if (str.length() == 0)
        return null;
      JSONObject localJSONObject = new JSONObject(str).getJSONObject("data");
      try
      {
        arrayOfString[0] = localJSONObject.getString("total_posts");
      }
      catch (JSONException localJSONException3)
      {
        try
        {
          arrayOfString[1] = localJSONObject.getString("total_comments");
        }
        catch (JSONException localJSONException3)
        {
          try
          {
            while (true)
            {
              arrayOfString[2] = localJSONObject.getString("last_message");
              Log.e("", "");
              return arrayOfString;
              localJSONException2 = localJSONException2;
              Log.e("", "");
              continue;
              localJSONException3 = localJSONException3;
              Log.e("", "");
            }
          }
          catch (JSONException localJSONException4)
          {
            while (true)
              Log.e("", "");
          }
        }
      }
    }
    catch (JSONException localJSONException1)
    {
    }
    return null;
  }

  // ERROR //
  public static String parseQueryError(String paramString)
  {
    // Byte code:
    //   0: new 72	org/json/JSONObject
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 73	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   8: astore_1
    //   9: aload_1
    //   10: ldc 248
    //   12: invokevirtual 97	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   15: astore_3
    //   16: aload_3
    //   17: areturn
    //   18: astore_2
    //   19: aload_2
    //   20: invokevirtual 320	org/json/JSONException:printStackTrace	()V
    //   23: aconst_null
    //   24: areturn
    //   25: astore_2
    //   26: goto -7 -> 19
    //
    // Exception table:
    //   from	to	target	type
    //   0	9	18	org/json/JSONException
    //   9	16	25	org/json/JSONException
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.TableReservationPlugin.JSONParser
 * JD-Core Version:    0.6.0
 */