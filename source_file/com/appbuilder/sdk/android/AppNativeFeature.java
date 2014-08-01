package com.appbuilder.sdk.android;

public class AppNativeFeature
{
  public static enum CONTACT
  {
    static
    {
      EMAIL = new CONTACT("EMAIL", 2);
      WEBSITE = new CONTACT("WEBSITE", 3);
      CONTACT[] arrayOfCONTACT = new CONTACT[4];
      arrayOfCONTACT[0] = NAME;
      arrayOfCONTACT[1] = PHONE;
      arrayOfCONTACT[2] = EMAIL;
      arrayOfCONTACT[3] = WEBSITE;
      $VALUES = arrayOfCONTACT;
    }
  }

  public static enum EMAIL
  {
    static
    {
      EMAIL[] arrayOfEMAIL = new EMAIL[3];
      arrayOfEMAIL[0] = ADDRESS;
      arrayOfEMAIL[1] = SUBJECT;
      arrayOfEMAIL[2] = TEXT;
      $VALUES = arrayOfEMAIL;
    }
  }

  public static enum EVENT
  {
    static
    {
      BEGIN = new EVENT("BEGIN", 1);
      END = new EVENT("END", 2);
      FREQUENCY = new EVENT("FREQUENCY", 3);
      EVENT[] arrayOfEVENT = new EVENT[4];
      arrayOfEVENT[0] = TITLE;
      arrayOfEVENT[1] = BEGIN;
      arrayOfEVENT[2] = END;
      arrayOfEVENT[3] = FREQUENCY;
      $VALUES = arrayOfEVENT;
    }
  }

  public static enum NOTIFICATION
  {
    static
    {
      NOTIFICATION[] arrayOfNOTIFICATION = new NOTIFICATION[1];
      arrayOfNOTIFICATION[0] = TEXT;
      $VALUES = arrayOfNOTIFICATION;
    }
  }

  public static enum SMS
  {
    static
    {
      SMS[] arrayOfSMS = new SMS[1];
      arrayOfSMS[0] = TEXT;
      $VALUES = arrayOfSMS;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.sdk.android.AppNativeFeature
 * JD-Core Version:    0.6.0
 */