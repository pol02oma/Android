package com.appbuilder.u846253p1176378;

import android.graphics.Color;
import android.util.Log;
import com.appbuilder.sdk.android.AppAdvData;
import com.appbuilder.sdk.android.BarDesigner;
import com.appbuilder.sdk.android.LoginScreen;
import com.appbuilder.sdk.android.Widget;
import com.appbuilder.u846253p1176378.GPSNotification.GPSItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class AppConfigure extends AppConfigureItem
  implements Serializable
{
  private static AppConfigure mCurrentInstance;
  private static final long serialVersionUID = 1L;
  private BarDesigner bottomBarDesign;
  private int defaultOrder = -1;
  private String googleAnalyticsId = "";
  private ArrayList<GPSItem> gpsNotifications = new ArrayList();
  private LoginScreen loginScreen;
  private AppAdvData mAppAdv = null;
  private String mAppId = "";
  private String mAppName = "";
  private String mBackgorundImageData = "";
  private String mBackgroundColor = "";
  private DownloadStatus mBackgroundDownloaded = DownloadStatus.NOT_DOWNLOADED;
  private String mBackgroundImageCache = "";
  private String mBackgroundImageUrl = "";
  private ArrayList<WidgetUIButton> mButtons = new ArrayList();
  private ArrayList<AppConfigureItem> mControls = new ArrayList();
  private int mDateFormat = 0;
  private ArrayList<WidgetUIImage> mImages = new ArrayList();
  private ArrayList<WidgetUILabel> mLabels = new ArrayList();
  private boolean mShowLink = false;
  private int mShowMenu = 1;
  private String mSplashScreen = "";
  private ArrayList<WidgetUITab> mTabs = new ArrayList();
  private ArrayList<Widget> mWidgets = new ArrayList();
  private BarDesigner navBarDesign = new BarDesigner();
  private String pushNotificationAccount = "";
  private boolean showSidebar = false;
  private BarDesigner tabBarDesign;

  public AppConfigure()
  {
    this.navBarDesign.color = -16777216;
    this.navBarDesign.titleDesign.fontWeight = "bold";
    this.navBarDesign.titleDesign.fontSize = 16;
    this.navBarDesign.titleDesign.numberOfLines = 0;
    this.navBarDesign.titleDesign.textAlignment = "center";
    this.navBarDesign.titleDesign.textColor = -1;
    this.navBarDesign.itemDesign.fontWeight = "normal";
    this.navBarDesign.itemDesign.fontSize = 16;
    this.navBarDesign.itemDesign.numberOfLines = 0;
    this.navBarDesign.itemDesign.textAlignment = "center";
    this.navBarDesign.itemDesign.textColor = Color.parseColor("#007aff");
    this.tabBarDesign = new BarDesigner();
    this.tabBarDesign.color = -16777216;
    this.tabBarDesign = new BarDesigner();
    this.tabBarDesign.itemDesign.fontWeight = "bold";
    this.tabBarDesign.itemDesign.selectedColor = Color.parseColor("#CFCFCF");
    this.tabBarDesign.itemDesign.fontSize = 16;
    this.tabBarDesign.itemDesign.numberOfLines = 0;
    this.tabBarDesign.itemDesign.textAlignment = "center";
    this.tabBarDesign.itemDesign.textColor = Color.parseColor("#ffffff");
    this.bottomBarDesign = new BarDesigner();
    this.bottomBarDesign.color = -16777216;
    this.bottomBarDesign = new BarDesigner();
    this.bottomBarDesign.leftButtonDesign.fontWeight = "bold";
    this.bottomBarDesign.leftButtonDesign.fontSize = 16;
    this.bottomBarDesign.leftButtonDesign.numberOfLines = 0;
    this.bottomBarDesign.leftButtonDesign.textAlignment = "left";
    this.bottomBarDesign.leftButtonDesign.textColor = Color.parseColor("#007aff");
    this.bottomBarDesign.rightButtonDesign.fontWeight = "bold";
    this.bottomBarDesign.rightButtonDesign.fontSize = 16;
    this.bottomBarDesign.rightButtonDesign.numberOfLines = 0;
    this.bottomBarDesign.rightButtonDesign.textAlignment = "right";
    this.bottomBarDesign.rightButtonDesign.textColor = Color.parseColor("#0005DE");
    mCurrentInstance = this;
  }

  public static AppConfigure getCurrent()
  {
    return mCurrentInstance;
  }

  public void addButton(WidgetUIButton paramWidgetUIButton)
  {
    this.mButtons.add(paramWidgetUIButton);
  }

  public void addControl(AppConfigureItem paramAppConfigureItem)
  {
    this.mControls.add(paramAppConfigureItem);
  }

  public void addGPSNotification(GPSItem paramGPSItem)
  {
    this.gpsNotifications.add(paramGPSItem);
  }

  public void addImage(WidgetUIImage paramWidgetUIImage)
  {
    this.mImages.add(paramWidgetUIImage);
  }

  public void addLabel(WidgetUILabel paramWidgetUILabel)
  {
    this.mLabels.add(paramWidgetUILabel);
  }

  public void addTab(WidgetUITab paramWidgetUITab)
  {
    this.mTabs.add(paramWidgetUITab);
  }

  public void addWidget(Widget paramWidget)
  {
    this.mWidgets.add(paramWidget);
  }

  public void clearCollections()
  {
    this.mLabels.clear();
    this.mButtons.clear();
    this.mImages.clear();
    this.mWidgets.clear();
  }

  public void clearGPSNotifications()
  {
    this.gpsNotifications.clear();
  }

  public void clearWidgets()
  {
    this.mWidgets.clear();
  }

  public int getAllDownloadStatus()
  {
    int i = 1;
    Iterator localIterator1 = this.mImages.iterator();
    WidgetUIImage localWidgetUIImage;
    label69: WidgetUIButton localWidgetUIButton;
    label131: WidgetUILabel localWidgetUILabel;
    Iterator localIterator4;
    if (localIterator1.hasNext())
    {
      localWidgetUIImage = (WidgetUIImage)localIterator1.next();
      if (i == 1);
    }
    else
    {
      Log.i("DOWNLOAD STATUS", "images: " + i);
      Iterator localIterator2 = this.mButtons.iterator();
      if (localIterator2.hasNext())
      {
        localWidgetUIButton = (WidgetUIButton)localIterator2.next();
        if (i == 1)
          break label327;
      }
      Log.i("DOWNLOAD STATUS", "buttons: " + i);
      Iterator localIterator3 = this.mLabels.iterator();
      if (localIterator3.hasNext())
      {
        localWidgetUILabel = (WidgetUILabel)localIterator3.next();
        if (i == 1)
          break label356;
      }
      Log.i("DOWNLOAD STATUS", "labels: " + i);
      localIterator4 = this.mTabs.iterator();
    }
    while (true)
    {
      WidgetUITab localWidgetUITab;
      if (localIterator4.hasNext())
      {
        localWidgetUITab = (WidgetUITab)localIterator4.next();
        if (i == 1);
      }
      else
      {
        Log.i("DOWNLOAD STATUS", "tabs: " + i);
        if (this.mBackgroundDownloaded == DownloadStatus.NOT_DOWNLOADED)
          i = 0;
        if (this.mBackgroundDownloaded == DownloadStatus.FAILED)
          i = -1;
        Log.i("DOWNLOAD STATUS", "background: " + i);
        return i;
        if (localWidgetUIImage.getDownloadStatus() == DownloadStatus.NOT_DOWNLOADED)
          i = 0;
        if (localWidgetUIImage.getDownloadStatus() != DownloadStatus.FAILED)
          break;
        i = -1;
        break;
        label327: if (localWidgetUIButton.getDownloadStatus() == DownloadStatus.NOT_DOWNLOADED)
          i = 0;
        if (localWidgetUIButton.getDownloadStatus() != DownloadStatus.FAILED)
          break label69;
        i = -1;
        break label69;
        label356: if (localWidgetUILabel.getDownloadStatus() == DownloadStatus.NOT_DOWNLOADED)
          i = 0;
        if (localWidgetUILabel.getDownloadStatus() != DownloadStatus.FAILED)
          break label131;
        i = -1;
        break label131;
      }
      if (localWidgetUITab.getDownloadStatus() == DownloadStatus.NOT_DOWNLOADED)
        i = 0;
      if (localWidgetUITab.getDownloadStatus() != DownloadStatus.FAILED)
        continue;
      i = -1;
    }
  }

  public AppAdvData getAppAdv()
  {
    return this.mAppAdv;
  }

  public String getAppName()
  {
    return this.mAppName;
  }

  public String getBackgroundColor()
  {
    return this.mBackgroundColor;
  }

  public String getBackgroundImageCache()
  {
    return this.mBackgroundImageCache;
  }

  public String getBackgroundImageUrl()
  {
    return this.mBackgroundImageUrl;
  }

  public BarDesigner getBottomBarDesign()
  {
    return this.bottomBarDesign;
  }

  public WidgetUIButton getButtonAtIndex(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.mButtons.size()))
      return null;
    return (WidgetUIButton)this.mButtons.get(paramInt);
  }

  public int getButtonsCount()
  {
    return this.mButtons.size();
  }

  public AppConfigureItem getControlAtIndex(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.mControls.size()))
      return null;
    return (AppConfigureItem)this.mControls.get(paramInt);
  }

  public int getControlsCount()
  {
    return this.mControls.size();
  }

  public int getDateFormat()
  {
    return this.mDateFormat;
  }

  public int getDefaultOrder()
  {
    return this.defaultOrder;
  }

  public GPSItem getGPSNotificationAtIndex(int paramInt)
  {
    return (GPSItem)this.gpsNotifications.get(paramInt);
  }

  public ArrayList<GPSItem> getGPSNotifications()
  {
    return this.gpsNotifications;
  }

  public String getGoogleAnalyticsId()
  {
    return this.googleAnalyticsId;
  }

  public WidgetUIImage getImageAtIndex(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.mImages.size()))
      return null;
    return (WidgetUIImage)this.mImages.get(paramInt);
  }

  public int getImagesCount()
  {
    return this.mImages.size();
  }

  public WidgetUILabel getLabelAtIndex(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.mLabels.size()))
      return null;
    return (WidgetUILabel)this.mLabels.get(paramInt);
  }

  public int getLabelsCount()
  {
    return this.mLabels.size();
  }

  public LoginScreen getLoginScreen()
  {
    return this.loginScreen;
  }

  public BarDesigner getNavBarDesign()
  {
    return this.navBarDesign;
  }

  public String getPushNotificationAccount()
  {
    return this.pushNotificationAccount;
  }

  public boolean getShowLink()
  {
    return this.mShowLink;
  }

  public int getShowMenu()
  {
    return this.mShowMenu;
  }

  public String getSplashScreen()
  {
    return this.mSplashScreen;
  }

  public WidgetUITab getTabAtIndex(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.mTabs.size()))
      return null;
    return (WidgetUITab)this.mTabs.get(paramInt);
  }

  public BarDesigner getTabBarDesign()
  {
    return this.tabBarDesign;
  }

  public int getTabsCount()
  {
    return this.mTabs.size();
  }

  public Widget getWidgetAtIndex(int paramInt)
  {
    for (int i = 0; i < this.mWidgets.size(); i++)
    {
      Widget localWidget = (Widget)this.mWidgets.get(i);
      if ((localWidget == null) || (localWidget.getOrder() != paramInt))
        continue;
      localWidget.setAppName(this.mAppName);
      return localWidget;
    }
    return null;
  }

  public Widget getWidgetWithOrder(int paramInt)
  {
    Iterator localIterator = this.mWidgets.iterator();
    while (localIterator.hasNext())
    {
      Widget localWidget = (Widget)localIterator.next();
      if (localWidget.getOrder() == paramInt)
        return localWidget;
    }
    return null;
  }

  public int getWidgetsCount()
  {
    return this.mWidgets.size();
  }

  public String getmAppId()
  {
    return this.mAppId;
  }

  public String getmBackgorundImageData()
  {
    return this.mBackgorundImageData;
  }

  public ArrayList<Widget> getmWidgets()
  {
    Iterator localIterator = this.mWidgets.iterator();
    while (localIterator.hasNext())
      ((Widget)localIterator.next()).setAppName(this.mAppName);
    return this.mWidgets;
  }

  public boolean hasDefaultOrder()
  {
    return this.defaultOrder != -1;
  }

  public boolean isShowSidebar()
  {
    return this.showSidebar;
  }

  public boolean needShowMenu()
  {
    return (this.mShowMenu != 0) && (this.mTabs.size() > 0);
  }

  public void removeGPSNotificationAtIndex(int paramInt)
  {
    this.gpsNotifications.remove(paramInt);
  }

  public void setAppAdv(AppAdvData paramAppAdvData)
  {
    this.mAppAdv = paramAppAdvData;
  }

  public void setAppName(String paramString)
  {
    this.mAppName = paramString;
  }

  public void setBackgroundColor(String paramString)
  {
    this.mBackgroundColor = paramString;
  }

  public void setBackgroundDownloaded(DownloadStatus paramDownloadStatus)
  {
    this.mBackgroundDownloaded = paramDownloadStatus;
  }

  public void setBackgroundImageCache(String paramString)
  {
    this.mBackgroundImageCache = paramString;
  }

  public void setBackgroundImageUrl(String paramString)
  {
    this.mBackgroundImageUrl = paramString;
  }

  public void setBottomBarDesign(BarDesigner paramBarDesigner)
  {
    this.bottomBarDesign = paramBarDesigner;
  }

  public void setButtonAtIndex(int paramInt, WidgetUIButton paramWidgetUIButton)
  {
    this.mButtons.set(paramInt, paramWidgetUIButton);
  }

  public void setDateFormat(int paramInt)
  {
    this.mDateFormat = paramInt;
  }

  public void setDefaultOrder(int paramInt)
  {
    this.defaultOrder = paramInt;
  }

  public void setGoogleAnalyticsId(String paramString)
  {
    this.googleAnalyticsId = paramString;
  }

  public void setImageAtIndex(int paramInt, WidgetUIImage paramWidgetUIImage)
  {
    this.mImages.set(paramInt, paramWidgetUIImage);
  }

  public void setLoginScreen(LoginScreen paramLoginScreen)
  {
    this.loginScreen = paramLoginScreen;
  }

  public void setNavBarDesign(BarDesigner paramBarDesigner)
  {
    this.navBarDesign = paramBarDesigner;
  }

  public void setPushNotificationAccount(String paramString)
  {
    this.pushNotificationAccount = paramString;
  }

  public void setShowLink(boolean paramBoolean)
  {
    this.mShowLink = paramBoolean;
  }

  public void setShowMenu(int paramInt)
  {
    this.mShowMenu = paramInt;
  }

  public void setShowSidebar(boolean paramBoolean)
  {
    this.showSidebar = paramBoolean;
  }

  public void setSplashScreen(String paramString)
  {
    this.mSplashScreen = paramString;
  }

  public void setTabAtIndex(int paramInt, WidgetUITab paramWidgetUITab)
  {
    this.mTabs.set(paramInt, paramWidgetUITab);
  }

  public void setTabBarDesign(BarDesigner paramBarDesigner)
  {
    this.tabBarDesign = paramBarDesigner;
  }

  public void setmAppId(String paramString)
  {
    this.mAppId = paramString;
  }

  public void setmBackgorundImageData(String paramString)
  {
    this.mBackgorundImageData = paramString;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.AppConfigure
 * JD-Core Version:    0.6.0
 */