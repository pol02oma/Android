package com.appbuilder.sdk.android;

import java.util.HashMap;

public class Statics
{
  public static String BASE_DOMEN;
  public static String FACEBOOK_APP_ID;
  public static String FACEBOOK_APP_SECRET;
  public static final String FORCE_CLOSE_MODULE = "closeme";
  public static final String FORCE_CLOSE_MODULE_FLAG = "closeme_flag";
  public static final String FORCE_CLOSE_NEW_MODULE_ORDER = "new_order";
  public static String LINKEDIN_CLIENT_ID;
  public static String LINKEDIN_CLIENT_SECRET;
  public static String TWITTER_CONSUMER_KEY;
  public static String TWITTER_CONSUMER_SECRET;
  public static String VKONTAKTE_CLIENT_ID;
  public static GoogleAnaliticsHandler analiticsHandler;
  public static String appId;
  public static String appName;
  public static String appToken;
  public static boolean closeMain = false;
  public static String googleAnalyticsIbuildAppId;
  public static String googleAnalyticsId;
  public static HashMap<String, String> mapPluginConversation;
  public static boolean showLink = false;

  static
  {
    BASE_DOMEN = "ibuildapp.com";
    FACEBOOK_APP_ID = "207296122640913";
    FACEBOOK_APP_SECRET = "3cae87e561313d4dd07c076566e2c67a";
    TWITTER_CONSUMER_KEY = "p48aBftV8vXXfG6UWo0BcQ";
    TWITTER_CONSUMER_SECRET = "YYkHCKtSD7uYhSC3jtPL1H2b6NaX2u6x5kOLLgRUA";
    VKONTAKTE_CLIENT_ID = "3829591";
    LINKEDIN_CLIENT_ID = "83kt13bp2ex3";
    LINKEDIN_CLIENT_SECRET = "qKeywrtNNAbseOXV";
    mapPluginConversation = setupHashMap();
    appId = "1416";
    appToken = "TTooKKeeNN";
  }

  private static HashMap<String, String> setupHashMap()
  {
    mapPluginConversation = new HashMap();
    mapPluginConversation.put("AudioPlugin", "Audio List");
    mapPluginConversation.put("CalculatorPlugin", "Calculator");
    mapPluginConversation.put("CallPlugin", "Tap To Call");
    mapPluginConversation.put("CameraPlugin", "Take a Picture");
    mapPluginConversation.put("ContactsPlugin", "Grouped Contacts");
    mapPluginConversation.put("CouponPlugin", "Coupons");
    mapPluginConversation.put("CustomFormPlugin", "Custom Form");
    mapPluginConversation.put("DirectoryPlugin", "Directory");
    mapPluginConversation.put("ECommercePlugin", "eCommerce");
    mapPluginConversation.put("EMailPlugin", "Tap To Email");
    mapPluginConversation.put("FanWallPlugin", "FanWall");
    mapPluginConversation.put("MapPlugin", "Google Map");
    mapPluginConversation.put("MenuPlugin", "Menu");
    mapPluginConversation.put("MultiContactsPlugin", "Grouped Contacts");
    mapPluginConversation.put("NewsPlugin", "News");
    mapPluginConversation.put("OpenTablePlugin", "OpenTable");
    mapPluginConversation.put("QRPlugin", "QR-Code Scaner");
    mapPluginConversation.put("PhotoGalleryPlugin", "Photo Gallery");
    mapPluginConversation.put("SkCataloguePlugin", "Book Store");
    mapPluginConversation.put("TablePlugin", "eBook");
    mapPluginConversation.put("TableReservationPlugin", "Reservation");
    mapPluginConversation.put("TwitterPlugin", "Twitter");
    mapPluginConversation.put("VideoPlugin", "Video List");
    mapPluginConversation.put("WebPlugin", "Web");
    return mapPluginConversation;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.sdk.android.Statics
 * JD-Core Version:    0.6.0
 */