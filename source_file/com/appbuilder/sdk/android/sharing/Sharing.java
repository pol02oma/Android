package com.appbuilder.sdk.android.sharing;

import android.app.Activity;

public class Sharing
{
  private static void internalShare(SharingTypes paramSharingTypes, String paramString1, String paramString2, String paramString3, Integer paramInteger, String paramString4, Activity paramActivity)
    throws IllegalStateException
  {
    switch (1.$SwitchMap$com$appbuilder$sdk$android$sharing$Sharing$SharingTypes[paramSharingTypes.ordinal()])
    {
    case 1:
    case 4:
    default:
      return;
    case 2:
      VkontakteSharing.share(paramString1, paramString2, paramInteger, paramString4, paramActivity);
      return;
    case 3:
    }
    LinkedInSharing.share(paramString1, paramString2, paramInteger, paramString4, paramActivity, paramString3);
  }

  public static void share(SharingTypes paramSharingTypes, String paramString)
    throws IllegalStateException
  {
    internalShare(paramSharingTypes, null, paramString, null, null, null, null);
  }

  public static void share(SharingTypes paramSharingTypes, String paramString, Integer paramInteger, Activity paramActivity)
    throws IllegalStateException
  {
    internalShare(paramSharingTypes, null, paramString, null, paramInteger, null, paramActivity);
  }

  public static void share(SharingTypes paramSharingTypes, String paramString1, String paramString2)
    throws IllegalStateException
  {
    internalShare(paramSharingTypes, null, paramString1, null, null, paramString2, null);
  }

  public static void share(SharingTypes paramSharingTypes, String paramString1, String paramString2, Integer paramInteger, Activity paramActivity)
    throws IllegalStateException
  {
    internalShare(paramSharingTypes, paramString1, paramString2, null, paramInteger, null, paramActivity);
  }

  public static void share(SharingTypes paramSharingTypes, String paramString1, String paramString2, String paramString3)
    throws IllegalStateException
  {
    internalShare(paramSharingTypes, paramString1, paramString2, null, null, paramString3, null);
  }

  public static void share(SharingTypes paramSharingTypes, String paramString1, String paramString2, String paramString3, Activity paramActivity)
    throws IllegalStateException
  {
    internalShare(paramSharingTypes, paramString1, paramString2, null, null, null, paramActivity);
  }

  public static void share(SharingTypes paramSharingTypes, String paramString1, String paramString2, String paramString3, String paramString4, Activity paramActivity)
    throws IllegalStateException
  {
    internalShare(paramSharingTypes, paramString1, paramString2, paramString3, null, paramString4, paramActivity);
  }

  public static enum SharingTypes
  {
    static
    {
      SHARING_TYPE_LINKEDIN = new SharingTypes("SHARING_TYPE_LINKEDIN", 2);
      SHARING_TYPE_FACEBOOK = new SharingTypes("SHARING_TYPE_FACEBOOK", 3);
      SHARING_TYPE_TWITTER = new SharingTypes("SHARING_TYPE_TWITTER", 4);
      SharingTypes[] arrayOfSharingTypes = new SharingTypes[5];
      arrayOfSharingTypes[0] = SHARING_TYPE_ANY;
      arrayOfSharingTypes[1] = SHARING_TYPE_VKONTAKTE;
      arrayOfSharingTypes[2] = SHARING_TYPE_LINKEDIN;
      arrayOfSharingTypes[3] = SHARING_TYPE_FACEBOOK;
      arrayOfSharingTypes[4] = SHARING_TYPE_TWITTER;
      $VALUES = arrayOfSharingTypes;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.sdk.android.sharing.Sharing
 * JD-Core Version:    0.6.0
 */