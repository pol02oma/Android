package com.appbuilder.u846253p1176378.PushNotification;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AppPushNotificationDB
{
  private static final String TAG = "PUSHNS_db";
  private static Context context;
  private static String databaseName = "APP_NOTIFICATOINS";
  private static SQLiteDatabase db;
  private static String notificationTable = "NOTIFICATION_MESSAGES";
  private static String xmlUrlTable = "XML_URL_TABLE";

  static
  {
    db = null;
  }

  private static void createNotificationTable()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = notificationTable;
    String str = String.format("CREATE TABLE %s ", arrayOfObject) + "( " + " ID INTEGER, " + " PACKAGE TEXT, " + " TITLE TEXT, " + " MESSAGE TEXT, " + " IMAGE_URL TEXT, " + " IMAGE_PATH TEXT, " + " NOTIFICATION_DATE INTEGER, " + " WIDGET_ORDER INTEGER, " + " PACKAGE_EXIST INTEGER, " + " CONSTRAINT PK_NOTIFICATION PRIMARY KEY (ID) " + ")";
    db.execSQL(str);
  }

  private static void createXmlUrlTable()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = xmlUrlTable;
    String str = String.format("CREATE TABLE %s ", arrayOfObject) + "( " + " ID INTEGER, " + " XML TEXT, " + " CONSTRAINT PK_NOTIFICATION PRIMARY KEY (ID) " + ")";
    db.execSQL(str);
  }

  public static void deleteItemFromRelations(long paramLong)
  {
    try
    {
      SQLiteDatabase localSQLiteDatabase = db;
      String str = notificationTable;
      String[] arrayOfString = new String[1];
      arrayOfString[0] = String.valueOf(paramLong);
      int i = localSQLiteDatabase.delete(str, "ID = ?", arrayOfString);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(i);
      logError(String.format("deleteItemFromNotifications = %d", arrayOfObject));
      return;
    }
    catch (Exception localException)
    {
      logError(localException);
    }
  }

  private static boolean existTable(String paramString)
  {
    Cursor localCursor = db.rawQuery("SELECT DISTINCT tbl_name FROM sqlite_master WHERE tbl_name = ?", new String[] { paramString });
    if (localCursor != null)
    {
      if (localCursor.getCount() > 0)
      {
        localCursor.close();
        return true;
      }
      localCursor.close();
    }
    return false;
  }

  private static ContentValues fillNotification(AppPushNotificationMessage paramAppPushNotificationMessage)
  {
    int i = 1;
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("PACKAGE", paramAppPushNotificationMessage.packageName);
    localContentValues.put("TITLE", paramAppPushNotificationMessage.titleText);
    localContentValues.put("MESSAGE", paramAppPushNotificationMessage.descriptionText);
    localContentValues.put("IMAGE_URL", paramAppPushNotificationMessage.imgUrl);
    localContentValues.put("IMAGE_PATH", paramAppPushNotificationMessage.imagePath);
    localContentValues.put("NOTIFICATION_DATE", Long.valueOf(paramAppPushNotificationMessage.notificationDate.getTime()));
    localContentValues.put("WIDGET_ORDER", Integer.valueOf(paramAppPushNotificationMessage.widgetOrder));
    if (paramAppPushNotificationMessage.isPackageExist == i);
    while (true)
    {
      localContentValues.put("PACKAGE_EXIST", Integer.valueOf(i));
      return localContentValues;
      i = 0;
    }
  }

  private static ContentValues fillXmlString(String paramString)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("ID", Integer.valueOf(0));
    localContentValues.put("XML", paramString);
    return localContentValues;
  }

  private static AppPushNotificationMessage getNotificationById(long paramLong)
  {
    SQLiteDatabase localSQLiteDatabase = db;
    String str = notificationTable;
    String[] arrayOfString = new String[1];
    arrayOfString[0] = String.valueOf(paramLong);
    Cursor localCursor = localSQLiteDatabase.query(str, null, "ID = ?", arrayOfString, null, null, null);
    if ((localCursor == null) || (localCursor.getCount() <= 0))
      return null;
    boolean bool = localCursor.moveToFirst();
    AppPushNotificationMessage localAppPushNotificationMessage = null;
    if (bool)
      localAppPushNotificationMessage = parseNotification(localCursor);
    localCursor.close();
    return localAppPushNotificationMessage;
  }

  public static AppPushNotificationMessage getNotificationIfExist()
  {
    Cursor localCursor = db.query(notificationTable, null, null, null, null, null, null);
    Object localObject;
    if ((localCursor == null) || (localCursor.getCount() <= 0))
      localObject = null;
    while (true)
    {
      return localObject;
      ArrayList localArrayList = new ArrayList(localCursor.getCount());
      if (localCursor.moveToFirst())
        do
          localArrayList.add(parseNotification(localCursor));
        while (localCursor.moveToNext());
      localCursor.close();
      localObject = new AppPushNotificationMessage();
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
      {
        AppPushNotificationMessage localAppPushNotificationMessage = (AppPushNotificationMessage)localIterator.next();
        if (localAppPushNotificationMessage.notificationDate.getTime() <= ((AppPushNotificationMessage)localObject).notificationDate.getTime())
          continue;
        localObject = localAppPushNotificationMessage;
      }
    }
  }

  public static String getXmlUrl()
  {
    Cursor localCursor = db.query(xmlUrlTable, null, null, null, null, null, null);
    if ((localCursor == null) || (localCursor.getCount() <= 0))
      return null;
    String str = "";
    if (localCursor.moveToFirst())
      do
        str = parseXmlUrl(localCursor);
      while (localCursor.moveToNext());
    localCursor.close();
    return str;
  }

  public static void init(Context paramContext)
  {
    context = paramContext;
    if (db == null)
      db = context.openOrCreateDatabase(databaseName, 0, null);
    if (!existTable(notificationTable))
    {
      createNotificationTable();
      createXmlUrlTable();
    }
  }

  public static long insertNotification(AppPushNotificationMessage paramAppPushNotificationMessage)
  {
    try
    {
      long l = db.insertWithOnConflict(notificationTable, "", fillNotification(paramAppPushNotificationMessage), 5);
      logError("ROW id = " + String.valueOf(l));
      return l;
    }
    catch (Exception localException)
    {
      logError(localException);
    }
    return -1L;
  }

  public static long insertXmlUrl(String paramString)
  {
    try
    {
      Log.e("PUSHNS_db", db.toString());
      long l = db.insertWithOnConflict(xmlUrlTable, "", fillXmlString(paramString), 5);
      logError("insertCategoryRows = " + String.valueOf(l));
      return l;
    }
    catch (Exception localException)
    {
      logError(localException);
    }
    return -1L;
  }

  private static void logError(Exception paramException)
  {
    Log.e("PUSHNS_db", "", paramException);
  }

  private static void logError(String paramString)
  {
    Log.e("PUSHNS_db", paramString);
  }

  private static AppPushNotificationMessage parseNotification(Cursor paramCursor)
  {
    AppPushNotificationMessage localAppPushNotificationMessage = new AppPushNotificationMessage();
    int i = 0;
    if (i < paramCursor.getColumnCount())
    {
      if (paramCursor.getColumnName(i).equals("ID"))
        localAppPushNotificationMessage.uid = paramCursor.getLong(i);
      label262: 
      do
        while (true)
        {
          i++;
          break;
          if (paramCursor.getColumnName(i).equals("MESSAGE"))
          {
            localAppPushNotificationMessage.descriptionText = paramCursor.getString(i);
            continue;
          }
          if (paramCursor.getColumnName(i).equals("PACKAGE"))
          {
            localAppPushNotificationMessage.packageName = paramCursor.getString(i);
            continue;
          }
          if (paramCursor.getColumnName(i).equals("IMAGE_URL"))
          {
            localAppPushNotificationMessage.imgUrl = paramCursor.getString(i);
            continue;
          }
          if (paramCursor.getColumnName(i).equals("IMAGE_PATH"))
          {
            localAppPushNotificationMessage.imagePath = paramCursor.getString(i);
            continue;
          }
          if (paramCursor.getColumnName(i).equals("TITLE"))
          {
            localAppPushNotificationMessage.titleText = paramCursor.getString(i);
            continue;
          }
          if (paramCursor.getColumnName(i).equals("NOTIFICATION_DATE"))
          {
            localAppPushNotificationMessage.notificationDate = new Date(paramCursor.getLong(i));
            continue;
          }
          if (!paramCursor.getColumnName(i).equals("WIDGET_ORDER"))
            break label262;
          localAppPushNotificationMessage.widgetOrder = paramCursor.getInt(i);
        }
      while (!paramCursor.getColumnName(i).equals("PACKAGE_EXIST"));
      if (paramCursor.getInt(i) == 1);
      for (boolean bool = true; ; bool = false)
      {
        localAppPushNotificationMessage.isPackageExist = bool;
        break;
      }
    }
    return localAppPushNotificationMessage;
  }

  private static String parseXmlUrl(Cursor paramCursor)
  {
    for (int i = 0; i < paramCursor.getColumnCount(); i++)
      if (paramCursor.getColumnName(i).equals("XML"))
        return paramCursor.getString(i);
    return null;
  }

  public static List<AppPushNotificationMessage> selectAllNotifications()
  {
    Cursor localCursor = db.query(notificationTable, null, null, null, null, null, null);
    if ((localCursor == null) || (localCursor.getCount() <= 0))
      return new ArrayList();
    ArrayList localArrayList = new ArrayList(localCursor.getCount());
    if (localCursor.moveToFirst())
      do
        localArrayList.add(parseNotification(localCursor));
      while (localCursor.moveToNext());
    localCursor.close();
    return localArrayList;
  }

  public static void updateNotificationImage(long paramLong, String paramString)
  {
    AppPushNotificationMessage localAppPushNotificationMessage = getNotificationById(paramLong);
    if (localAppPushNotificationMessage != null)
    {
      localAppPushNotificationMessage.imagePath = paramString;
      SQLiteDatabase localSQLiteDatabase = db;
      String str = notificationTable;
      ContentValues localContentValues = fillNotification(localAppPushNotificationMessage);
      String[] arrayOfString = new String[1];
      arrayOfString[0] = String.valueOf(paramLong);
      localSQLiteDatabase.update(str, localContentValues, "ID = ?", arrayOfString);
      return;
    }
    logError("updateNotificationImage() => notification == null");
  }

  public static void updateNotificationPackageStatus(long paramLong, boolean paramBoolean)
  {
    AppPushNotificationMessage localAppPushNotificationMessage = getNotificationById(paramLong);
    if (localAppPushNotificationMessage != null)
    {
      localAppPushNotificationMessage.isPackageExist = paramBoolean;
      SQLiteDatabase localSQLiteDatabase = db;
      String str = notificationTable;
      ContentValues localContentValues = fillNotification(localAppPushNotificationMessage);
      String[] arrayOfString = new String[1];
      arrayOfString[0] = String.valueOf(paramLong);
      localSQLiteDatabase.update(str, localContentValues, "ID = ?", arrayOfString);
      return;
    }
    logError("updateNotificationPackageStatus() => notification == null");
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.PushNotification.AppPushNotificationDB
 * JD-Core Version:    0.6.0
 */