package com.appbuilder.sdk.android.pushnotification;

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
  private static String TAG;
  private static Context context;
  private static String databaseName = "APP_NOTIFICATOINS";
  private static SQLiteDatabase db;
  private static String notificationTable = "NOTIFICATION_MESSAGES";

  static
  {
    db = null;
    TAG = "com.appbuilder.core.PushNotification.AppPushNotificationDB";
  }

  private static void createNotificationTable()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = notificationTable;
    String str = String.format("CREATE TABLE %s ", arrayOfObject) + "( " + " ID INTEGER, " + " PACKAGE TEXT, " + " TITLE TEXT, " + " MESSAGE TEXT, " + " IMAGE_URL TEXT, " + " IMAGE_PATH TEXT, " + " NOTIFICATION_DATE INTEGER, " + " WIDGET_ORDER INTEGER, " + " PACKAGE_EXIST INTEGER, " + " CONSTRAINT PK_NOTIFICATION PRIMARY KEY (ID) " + ")";
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

  public static void init(Context paramContext)
  {
    context = paramContext;
    if (db == null)
      db = context.openOrCreateDatabase(databaseName, 0, null);
    if (!existTable(notificationTable))
      createNotificationTable();
  }

  private static void logError(Exception paramException)
  {
    Log.e(TAG, "", paramException);
  }

  private static void logError(String paramString)
  {
    Log.e(TAG, paramString);
  }

  private static AppPushNotificationMessage parseNotification(Cursor paramCursor)
  {
    AppPushNotificationMessage localAppPushNotificationMessage = new AppPushNotificationMessage();
    int i = 0;
    if (i < paramCursor.getColumnCount())
    {
      if (paramCursor.getColumnName(i).equals("ID"))
        localAppPushNotificationMessage.uid = paramCursor.getLong(i);
      label263: 
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
            break label263;
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
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.sdk.android.pushnotification.AppPushNotificationDB
 * JD-Core Version:    0.6.0
 */