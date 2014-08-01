package com.appbuilder.u846253p1176378.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;
import android.util.Xml;
import com.appbuilder.sdk.android.Widget;
import com.appbuilder.u846253p1176378.AppConfigure;
import com.appbuilder.u846253p1176378.AppConfigureParser;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Tools
{
  public static int checkNetwork(Context paramContext)
  {
    int i = 1;
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    NetworkInfo localNetworkInfo1 = localConnectivityManager.getActiveNetworkInfo();
    if ((localNetworkInfo1 != null) && (localNetworkInfo1.isConnectedOrConnecting()));
    try
    {
      NetworkInfo localNetworkInfo2 = localConnectivityManager.getNetworkInfo(1);
      if (localConnectivityManager.getNetworkInfo(0).isConnected())
      {
        boolean bool = localNetworkInfo2.isConnectedOrConnecting();
        if (!bool)
          i = 2;
      }
      return i;
      return -1;
    }
    catch (Exception localException)
    {
    }
    return i;
  }

  public static AppConfigure updateAppConfig(Context paramContext, String paramString)
  {
    try
    {
      URL localURL = new URL(paramString);
      InputStream localInputStream = localURL.openConnection().getInputStream();
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      byte[] arrayOfByte = new byte[1024];
      while (true)
      {
        int i = localInputStream.read(arrayOfByte, 0, 1024);
        if (i == -1)
          break;
        localByteArrayOutputStream.write(arrayOfByte, 0, i);
        Arrays.fill(arrayOfByte, 0);
      }
      String str1 = localByteArrayOutputStream.toString();
      int j = 1;
      try
      {
        Xml.parse(str1, null);
        if (j == 0)
          break label336;
        AppConfigure localAppConfigure1 = new AppConfigureParser(str1).parseSAX();
        if (localAppConfigure1 != null)
        {
          String str2 = Environment.getExternalStorageDirectory() + "/AppBuilder/" + paramContext.getPackageName();
          localFile = new File(str2 + "/cache.data");
          localAppConfigure2 = null;
          boolean bool = localFile.exists();
          if (!bool)
            break label339;
          try
          {
            FileInputStream localFileInputStream = new FileInputStream(localFile);
            ObjectInputStream localObjectInputStream = new ObjectInputStream(localFileInputStream);
            localAppConfigure2 = (AppConfigure)localObjectInputStream.readObject();
            localObjectInputStream.close();
            localAppConfigure2.clearWidgets();
            Iterator localIterator = localAppConfigure1.getmWidgets().iterator();
            while (localIterator.hasNext())
            {
              Widget localWidget = (Widget)localIterator.next();
              localAppConfigure2.addWidget(localWidget);
            }
          }
          catch (Exception localException3)
          {
            Log.e("", "");
            return localAppConfigure2;
          }
        }
      }
      catch (Exception localException2)
      {
        File localFile;
        AppConfigure localAppConfigure2;
        while (true)
          j = 0;
        localFile.delete();
        FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
        ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localFileOutputStream);
        localObjectOutputStream.writeObject(localAppConfigure2);
        localObjectOutputStream.close();
        return localAppConfigure2;
      }
      return null;
      label336: return null;
    }
    catch (Exception localException1)
    {
    }
    label339: return null;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.tools.Tools
 * JD-Core Version:    0.6.0
 */