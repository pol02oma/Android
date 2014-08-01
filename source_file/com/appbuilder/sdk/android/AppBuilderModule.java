package com.appbuilder.sdk.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;
import com.appbuilder.statistics.StatisticsCollector;
import com.flurry.android.FlurryAgent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class AppBuilderModule extends Activity
  implements AppBuilderInterface
{
  private static final String TAG = "com.ibuildapp.core.AppBuilderModule";
  private final int MENU_ITEM_ADD_CONTACT_CLICK = 3;
  private final int MENU_ITEM_ADD_EVENT_CLICK = 4;
  private final int MENU_ITEM_EMAIL_CLICK = 2;
  private final int MENU_ITEM_SMS_CLICK = 1;
  private AppAdvView adView = null;
  private AppAdvData advData = null;
  private String flurryId = "";
  private boolean flurryStarted = false;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        return;
      case 1:
        AppBuilderModule.this.sendSMS();
        return;
      case 2:
        AppBuilderModule.this.sendEmail();
        return;
      case 3:
        AppBuilderModule.this.addContact();
        return;
      case 4:
      }
      AppBuilderModule.this.addEvent();
    }
  };
  private long millis;
  protected HashMap<Object, HashMap<Object, Object>> nativeFeatures = new HashMap();
  private Serializable session = null;
  protected Bundle state = null;
  private Widget widget = null;

  private void LogDebug(String paramString)
  {
    Log.d("com.ibuildapp.core.AppBuilderModule", paramString);
  }

  private void LogError(Exception paramException)
  {
    Log.e("com.ibuildapp.core.AppBuilderModule", "", paramException);
  }

  private void addContact()
  {
    ArrayList localArrayList1;
    ArrayList localArrayList2;
    try
    {
      if (!this.nativeFeatures.containsKey(NATIVE_FEATURES.ADD_CONTACT))
        return;
      localArrayList1 = new ArrayList();
      String str1 = (String)((HashMap)this.nativeFeatures.get(NATIVE_FEATURES.ADD_CONTACT)).get(AppNativeFeature.CONTACT.NAME);
      if ((str1 != null) && (!"".equals(str1)))
        localArrayList1.add(new Object(0, str1)
        {
          protected int type;
          protected String value;

          public String getDescription()
          {
            return this.value;
          }

          public int getType()
          {
            return this.type;
          }
        });
      String str2 = (String)((HashMap)this.nativeFeatures.get(NATIVE_FEATURES.ADD_CONTACT)).get(AppNativeFeature.CONTACT.PHONE);
      if ((str2 != null) && (!"".equals(str2)))
        localArrayList1.add(new Object(1, str2)
        {
          protected int type;
          protected String value;

          public String getDescription()
          {
            return this.value;
          }

          public int getType()
          {
            return this.type;
          }
        });
      String str3 = (String)((HashMap)this.nativeFeatures.get(NATIVE_FEATURES.ADD_CONTACT)).get(AppNativeFeature.CONTACT.EMAIL);
      if ((str3 != null) && (!"".equals(str3)))
        localArrayList1.add(new Object(2, str3)
        {
          protected int type;
          protected String value;

          public String getDescription()
          {
            return this.value;
          }

          public int getType()
          {
            return this.type;
          }
        });
      String str4 = (String)((HashMap)this.nativeFeatures.get(NATIVE_FEATURES.ADD_CONTACT)).get(AppNativeFeature.CONTACT.WEBSITE);
      if ((str4 != null) && (!"".equals(str4)))
        localArrayList1.add(new Object(3, str4)
        {
          protected int type;
          protected String value;

          public String getDescription()
          {
            return this.value;
          }

          public int getType()
          {
            return this.type;
          }
        });
      localArrayList2 = new ArrayList();
      String str5 = ((1Contact)localArrayList1.get(0)).getDescription();
      try
      {
        Cursor localCursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, new String[] { "_id", "display_name" }, "display_name = ?", new String[] { str5 }, null);
        if (localCursor.getCount() > 0)
        {
          localCursor.moveToFirst();
          localCursor.getString(0);
          String str6 = localCursor.getString(1);
          AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
          localBuilder.setTitle("The contact is already in your address book.");
          localBuilder.setMessage("Do you want to replace it?");
          localBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener(localArrayList1, str6)
          {
            public void onClick(DialogInterface paramDialogInterface, int paramInt)
            {
              ArrayList localArrayList = new ArrayList();
              int i = 0;
              if (i < this.val$contacts.size())
              {
                int j = ((AppBuilderModule.1Contact)this.val$contacts.get(i)).getType();
                Cursor localCursor = AppBuilderModule.this.getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, null, null, null);
                int k = 0;
                while (localCursor.moveToNext())
                  for (int m = 0; m < localCursor.getColumnCount(); m++)
                  {
                    if ((localCursor.getString(m) == null) || (!localCursor.getString(m).equals(((AppBuilderModule.1Contact)this.val$contacts.get(i)).getDescription())))
                      continue;
                    k = 1;
                    if (!localArrayList.isEmpty())
                      continue;
                    localArrayList.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI).withValue("account_type", null).withValue("account_name", null).build());
                    localArrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/name").withValue("data1", this.val$contactName).build());
                  }
                if (k == 0)
                  switch (j)
                  {
                  case 0:
                  default:
                  case 1:
                  case 2:
                  case 3:
                  }
                while (true)
                {
                  i++;
                  break;
                  localArrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/phone_v2").withValue("data1", ((AppBuilderModule.1Contact)this.val$contacts.get(i)).getDescription()).build());
                  continue;
                  localArrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/email_v2").withValue("data1", ((AppBuilderModule.1Contact)this.val$contacts.get(i)).getDescription()).build());
                  continue;
                  localArrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/website").withValue("data1", ((AppBuilderModule.1Contact)this.val$contacts.get(i)).getDescription()).build());
                }
              }
              try
              {
                AppBuilderModule.this.getContentResolver().applyBatch("com.android.contacts", localArrayList);
                return;
              }
              catch (RemoteException localRemoteException)
              {
                Log.w("", "");
                return;
              }
              catch (OperationApplicationException localOperationApplicationException)
              {
                Log.w("", "");
              }
            }
          });
          localBuilder.setNegativeButton("No", new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramDialogInterface, int paramInt)
            {
            }
          });
          localBuilder.create().show();
          return;
        }
      }
      catch (Exception localException2)
      {
        Log.d("", "");
        return;
      }
    }
    catch (Exception localException1)
    {
      StatisticsCollector.newError(localException1, "AppBuilderModule.");
      return;
    }
    while (true)
    {
      int i;
      int j;
      try
      {
        i = localArrayList2.size();
        localArrayList2.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI).withValue("account_type", null).withValue("account_name", null).build());
        j = 0;
        if (j >= localArrayList1.size())
          break label740;
        switch (((1Contact)localArrayList1.get(j)).getType())
        {
        case 0:
          localArrayList2.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", i).withValue("mimetype", "vnd.android.cursor.item/name").withValue("data1", ((1Contact)localArrayList1.get(j)).getDescription()).build());
        case 1:
        case 2:
        case 3:
        }
      }
      catch (Exception localException3)
      {
        Log.w("CONTACT!", localException3);
        return;
      }
      localArrayList2.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", i).withValue("mimetype", "vnd.android.cursor.item/phone_v2").withValue("data1", ((1Contact)localArrayList1.get(j)).getDescription()).build());
      break label765;
      localArrayList2.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", i).withValue("mimetype", "vnd.android.cursor.item/email_v2").withValue("data1", ((1Contact)localArrayList1.get(j)).getDescription()).build());
      break label765;
      localArrayList2.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", i).withValue("mimetype", "vnd.android.cursor.item/website").withValue("data1", ((1Contact)localArrayList1.get(j)).getDescription()).build());
      break label765;
      label740: getContentResolver().applyBatch("com.android.contacts", localArrayList2);
      Toast.makeText(this, "The contact has beed saved into your address book.", 1).show();
      return;
      label765: j++;
    }
  }

  private void addEvent()
  {
    try
    {
      if (this.nativeFeatures.containsKey(NATIVE_FEATURES.ADD_EVENT))
      {
        String str1 = (String)((HashMap)this.nativeFeatures.get(NATIVE_FEATURES.ADD_EVENT)).get(AppNativeFeature.EVENT.TITLE);
        String str2 = (String)((HashMap)this.nativeFeatures.get(NATIVE_FEATURES.ADD_EVENT)).get(AppNativeFeature.EVENT.BEGIN);
        ((String)((HashMap)this.nativeFeatures.get(NATIVE_FEATURES.ADD_EVENT)).get(AppNativeFeature.EVENT.END));
        ((String)((HashMap)this.nativeFeatures.get(NATIVE_FEATURES.ADD_EVENT)).get(AppNativeFeature.EVENT.FREQUENCY));
        ContentResolver localContentResolver = getContentResolver();
        Uri.Builder localBuilder = Uri.parse("content://com.android.calendar/instances/when").buildUpon();
        Long localLong = Long.valueOf(new Date(str2).getTime());
        ContentUris.appendId(localBuilder, localLong.longValue() - 600000L);
        ContentUris.appendId(localBuilder, 600000L + localLong.longValue());
        String[] arrayOfString = { "title", "begin" };
        Cursor localCursor = localContentResolver.query(localBuilder.build(), arrayOfString, null, null, null);
        int i = 0;
        if (localCursor != null)
          while (localCursor.moveToNext())
          {
            if ((localLong.longValue() != localCursor.getLong(1)) || (!str1.equals(localCursor.getString(0))))
              continue;
            i = 1;
          }
        if (i == 0)
        {
          Calendar.getInstance();
          Intent localIntent = new Intent("android.intent.action.EDIT");
          localIntent.setType("vnd.android.cursor.item/event");
          localIntent.putExtra("beginTime", localLong);
          localIntent.putExtra("allDay", false);
          localIntent.putExtra("endTime", 3600000L + localLong.longValue());
          localIntent.putExtra("title", str1);
          startActivity(localIntent);
          return;
        }
        Toast.makeText(this, "Event already exist!", 1).show();
        return;
      }
    }
    catch (Exception localException)
    {
      StatisticsCollector.newError(localException, "AppBuilderModule.addEvent()");
    }
  }

  private void readConfiguration()
  {
  }

  // ERROR //
  private void sendEmail()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 60	com/appbuilder/sdk/android/AppBuilderModule:nativeFeatures	Ljava/util/HashMap;
    //   4: getstatic 440	com/appbuilder/sdk/android/AppBuilderModule$NATIVE_FEATURES:EMAIL	Lcom/appbuilder/sdk/android/AppBuilderModule$NATIVE_FEATURES;
    //   7: invokevirtual 119	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   10: ifeq +148 -> 158
    //   13: new 399	android/content/Intent
    //   16: dup
    //   17: ldc_w 442
    //   20: invokespecial 402	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   23: astore_2
    //   24: aload_2
    //   25: ldc_w 444
    //   28: invokevirtual 408	android/content/Intent:setType	(Ljava/lang/String;)Landroid/content/Intent;
    //   31: pop
    //   32: aload_0
    //   33: getfield 60	com/appbuilder/sdk/android/AppBuilderModule:nativeFeatures	Ljava/util/HashMap;
    //   36: getstatic 440	com/appbuilder/sdk/android/AppBuilderModule$NATIVE_FEATURES:EMAIL	Lcom/appbuilder/sdk/android/AppBuilderModule$NATIVE_FEATURES;
    //   39: invokevirtual 126	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   42: checkcast 57	java/util/HashMap
    //   45: getstatic 450	com/appbuilder/sdk/android/AppNativeFeature$EMAIL:TEXT	Lcom/appbuilder/sdk/android/AppNativeFeature$EMAIL;
    //   48: invokevirtual 119	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   51: istore 4
    //   53: iload 4
    //   55: ifeq +36 -> 91
    //   58: aload_2
    //   59: ldc_w 452
    //   62: aload_0
    //   63: getfield 60	com/appbuilder/sdk/android/AppBuilderModule:nativeFeatures	Ljava/util/HashMap;
    //   66: getstatic 440	com/appbuilder/sdk/android/AppBuilderModule$NATIVE_FEATURES:EMAIL	Lcom/appbuilder/sdk/android/AppBuilderModule$NATIVE_FEATURES;
    //   69: invokevirtual 126	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   72: checkcast 57	java/util/HashMap
    //   75: getstatic 450	com/appbuilder/sdk/android/AppNativeFeature$EMAIL:TEXT	Lcom/appbuilder/sdk/android/AppNativeFeature$EMAIL;
    //   78: invokevirtual 126	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   81: checkcast 134	java/lang/String
    //   84: invokestatic 458	android/text/Html:fromHtml	(Ljava/lang/String;)Landroid/text/Spanned;
    //   87: invokevirtual 461	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/CharSequence;)Landroid/content/Intent;
    //   90: pop
    //   91: aload_0
    //   92: getfield 60	com/appbuilder/sdk/android/AppBuilderModule:nativeFeatures	Ljava/util/HashMap;
    //   95: getstatic 440	com/appbuilder/sdk/android/AppBuilderModule$NATIVE_FEATURES:EMAIL	Lcom/appbuilder/sdk/android/AppBuilderModule$NATIVE_FEATURES;
    //   98: invokevirtual 126	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   101: checkcast 57	java/util/HashMap
    //   104: getstatic 464	com/appbuilder/sdk/android/AppNativeFeature$EMAIL:SUBJECT	Lcom/appbuilder/sdk/android/AppNativeFeature$EMAIL;
    //   107: invokevirtual 119	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   110: istore 5
    //   112: iload 5
    //   114: ifeq +33 -> 147
    //   117: aload_2
    //   118: ldc_w 466
    //   121: aload_0
    //   122: getfield 60	com/appbuilder/sdk/android/AppBuilderModule:nativeFeatures	Ljava/util/HashMap;
    //   125: getstatic 440	com/appbuilder/sdk/android/AppBuilderModule$NATIVE_FEATURES:EMAIL	Lcom/appbuilder/sdk/android/AppBuilderModule$NATIVE_FEATURES;
    //   128: invokevirtual 126	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   131: checkcast 57	java/util/HashMap
    //   134: getstatic 464	com/appbuilder/sdk/android/AppNativeFeature$EMAIL:SUBJECT	Lcom/appbuilder/sdk/android/AppNativeFeature$EMAIL;
    //   137: invokevirtual 126	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   140: checkcast 134	java/lang/String
    //   143: invokevirtual 429	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   146: pop
    //   147: aload_0
    //   148: aload_2
    //   149: ldc_w 468
    //   152: invokestatic 472	android/content/Intent:createChooser	(Landroid/content/Intent;Ljava/lang/CharSequence;)Landroid/content/Intent;
    //   155: invokevirtual 433	com/appbuilder/sdk/android/AppBuilderModule:startActivity	(Landroid/content/Intent;)V
    //   158: return
    //   159: astore_1
    //   160: aload_1
    //   161: ldc_w 474
    //   164: invokestatic 249	com/appbuilder/statistics/StatisticsCollector:newError	(Ljava/lang/Exception;Ljava/lang/String;)V
    //   167: return
    //   168: astore 6
    //   170: goto -23 -> 147
    //   173: astore 8
    //   175: goto -84 -> 91
    //
    // Exception table:
    //   from	to	target	type
    //   0	53	159	java/lang/Exception
    //   91	112	159	java/lang/Exception
    //   147	158	159	java/lang/Exception
    //   117	147	168	java/lang/Exception
    //   58	91	173	java/lang/Exception
  }

  // ERROR //
  private void sendSMS()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 60	com/appbuilder/sdk/android/AppBuilderModule:nativeFeatures	Ljava/util/HashMap;
    //   4: getstatic 477	com/appbuilder/sdk/android/AppBuilderModule$NATIVE_FEATURES:SMS	Lcom/appbuilder/sdk/android/AppBuilderModule$NATIVE_FEATURES;
    //   7: invokevirtual 119	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   10: ifeq +82 -> 92
    //   13: new 399	android/content/Intent
    //   16: dup
    //   17: ldc_w 479
    //   20: ldc_w 481
    //   23: invokestatic 346	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   26: invokespecial 484	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   29: astore_2
    //   30: ldc 43
    //   32: astore_3
    //   33: aload_0
    //   34: getfield 60	com/appbuilder/sdk/android/AppBuilderModule:nativeFeatures	Ljava/util/HashMap;
    //   37: getstatic 477	com/appbuilder/sdk/android/AppBuilderModule$NATIVE_FEATURES:SMS	Lcom/appbuilder/sdk/android/AppBuilderModule$NATIVE_FEATURES;
    //   40: invokevirtual 126	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   43: checkcast 57	java/util/HashMap
    //   46: getstatic 489	com/appbuilder/sdk/android/AppNativeFeature$SMS:TEXT	Lcom/appbuilder/sdk/android/AppNativeFeature$SMS;
    //   49: invokevirtual 119	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   52: ifeq +26 -> 78
    //   55: aload_0
    //   56: getfield 60	com/appbuilder/sdk/android/AppBuilderModule:nativeFeatures	Ljava/util/HashMap;
    //   59: getstatic 477	com/appbuilder/sdk/android/AppBuilderModule$NATIVE_FEATURES:SMS	Lcom/appbuilder/sdk/android/AppBuilderModule$NATIVE_FEATURES;
    //   62: invokevirtual 126	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   65: checkcast 57	java/util/HashMap
    //   68: getstatic 489	com/appbuilder/sdk/android/AppNativeFeature$SMS:TEXT	Lcom/appbuilder/sdk/android/AppNativeFeature$SMS;
    //   71: invokevirtual 126	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   74: checkcast 491	java/lang/CharSequence
    //   77: astore_3
    //   78: aload_2
    //   79: ldc_w 493
    //   82: aload_3
    //   83: invokevirtual 461	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/CharSequence;)Landroid/content/Intent;
    //   86: pop
    //   87: aload_0
    //   88: aload_2
    //   89: invokevirtual 433	com/appbuilder/sdk/android/AppBuilderModule:startActivity	(Landroid/content/Intent;)V
    //   92: return
    //   93: astore_1
    //   94: aload_1
    //   95: ldc_w 495
    //   98: invokestatic 249	com/appbuilder/statistics/StatisticsCollector:newError	(Ljava/lang/Exception;Ljava/lang/String;)V
    //   101: return
    //   102: astore 4
    //   104: goto -17 -> 87
    //
    // Exception table:
    //   from	to	target	type
    //   0	30	93	java/lang/Exception
    //   87	92	93	java/lang/Exception
    //   33	78	102	java/lang/Exception
    //   78	87	102	java/lang/Exception
  }

  private void writeConfiguration()
  {
  }

  public final void addNativeFeature(NATIVE_FEATURES paramNATIVE_FEATURES, Object paramObject1, Object paramObject2)
  {
    while (true)
    {
      HashMap localHashMap1;
      try
      {
        if (this.nativeFeatures.containsKey(paramNATIVE_FEATURES))
          continue;
        localHashMap1 = new HashMap();
        if (!paramNATIVE_FEATURES.equals(NATIVE_FEATURES.SMS))
          continue;
        HashMap localHashMap5 = (HashMap)paramObject2;
        localHashMap1.put(AppNativeFeature.SMS.TEXT, localHashMap5.get("text"));
        this.nativeFeatures.put(paramNATIVE_FEATURES, localHashMap1);
        if ((!this.nativeFeatures.containsKey(paramNATIVE_FEATURES)) || (!((HashMap)this.nativeFeatures.get(paramNATIVE_FEATURES)).containsKey(paramObject1)))
          break;
        ((HashMap)this.nativeFeatures.get(paramNATIVE_FEATURES)).put(paramObject1, paramObject2);
        return;
        if (paramNATIVE_FEATURES.equals(NATIVE_FEATURES.EMAIL))
        {
          HashMap localHashMap4 = (HashMap)paramObject2;
          localHashMap1.put(AppNativeFeature.EMAIL.ADDRESS, null);
          localHashMap1.put(AppNativeFeature.EMAIL.SUBJECT, localHashMap4.get("subject"));
          localHashMap1.put(AppNativeFeature.EMAIL.TEXT, localHashMap4.get("text"));
          continue;
        }
      }
      catch (Exception localException)
      {
        StatisticsCollector.newError(localException, "AppBuilderModule.addNativeFeature()");
        return;
      }
      if (paramNATIVE_FEATURES.equals(NATIVE_FEATURES.ADD_CONTACT))
      {
        HashMap localHashMap3 = (HashMap)paramObject2;
        localHashMap1.put(AppNativeFeature.CONTACT.NAME, localHashMap3.get("contactName"));
        localHashMap1.put(AppNativeFeature.CONTACT.PHONE, localHashMap3.get("contactNumber"));
        localHashMap1.put(AppNativeFeature.CONTACT.EMAIL, localHashMap3.get("contactEmail"));
        localHashMap1.put(AppNativeFeature.CONTACT.WEBSITE, localHashMap3.get("contactSite"));
        continue;
      }
      if (!paramNATIVE_FEATURES.equals(NATIVE_FEATURES.ADD_EVENT))
        continue;
      HashMap localHashMap2 = (HashMap)paramObject2;
      localHashMap1.put(AppNativeFeature.EVENT.TITLE, localHashMap2.get("title"));
      localHashMap1.put(AppNativeFeature.EVENT.BEGIN, localHashMap2.get("begin"));
      localHashMap1.put(AppNativeFeature.EVENT.END, localHashMap2.get("end"));
      localHashMap1.put(AppNativeFeature.EVENT.FREQUENCY, localHashMap2.get("frequency"));
    }
  }

  public void create()
  {
  }

  public void destroy()
  {
  }

  protected final String getAdvType()
  {
    return this.advData.getAdvType();
  }

  public final Serializable getSession()
  {
    return this.session;
  }

  protected final boolean hasAdView()
  {
    return this.adView != null;
  }

  // ERROR //
  public final void onCreate(Bundle paramBundle)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokespecial 537	android/app/Activity:onCreate	(Landroid/os/Bundle;)V
    //   5: aload_0
    //   6: aload_1
    //   7: putfield 53	com/appbuilder/sdk/android/AppBuilderModule:state	Landroid/os/Bundle;
    //   10: aload_0
    //   11: aload_1
    //   12: ldc_w 538
    //   15: invokevirtual 544	android/os/Bundle:getSerializable	(Ljava/lang/String;)Ljava/io/Serializable;
    //   18: putfield 51	com/appbuilder/sdk/android/AppBuilderModule:session	Ljava/io/Serializable;
    //   21: aload_0
    //   22: invokespecial 546	com/appbuilder/sdk/android/AppBuilderModule:readConfiguration	()V
    //   25: aload_0
    //   26: invokevirtual 550	com/appbuilder/sdk/android/AppBuilderModule:getIntent	()Landroid/content/Intent;
    //   29: invokevirtual 554	android/content/Intent:getExtras	()Landroid/os/Bundle;
    //   32: astore 6
    //   34: aload_0
    //   35: aload 6
    //   37: ldc_w 556
    //   40: invokevirtual 544	android/os/Bundle:getSerializable	(Ljava/lang/String;)Ljava/io/Serializable;
    //   43: checkcast 528	com/appbuilder/sdk/android/AppAdvData
    //   46: putfield 47	com/appbuilder/sdk/android/AppBuilderModule:advData	Lcom/appbuilder/sdk/android/AppAdvData;
    //   49: aload 6
    //   51: ldc_w 558
    //   54: invokevirtual 562	android/os/Bundle:getBoolean	(Ljava/lang/String;)Z
    //   57: istore 7
    //   59: aload_0
    //   60: getfield 47	com/appbuilder/sdk/android/AppBuilderModule:advData	Lcom/appbuilder/sdk/android/AppAdvData;
    //   63: invokevirtual 530	com/appbuilder/sdk/android/AppAdvData:getAdvType	()Ljava/lang/String;
    //   66: invokevirtual 565	java/lang/String:length	()I
    //   69: ifle +21 -> 90
    //   72: aload_0
    //   73: new 567	com/appbuilder/sdk/android/AppAdvView
    //   76: dup
    //   77: aload_0
    //   78: aload_0
    //   79: getfield 47	com/appbuilder/sdk/android/AppBuilderModule:advData	Lcom/appbuilder/sdk/android/AppAdvData;
    //   82: iload 7
    //   84: invokespecial 570	com/appbuilder/sdk/android/AppAdvView:<init>	(Landroid/content/Context;Lcom/appbuilder/sdk/android/AppAdvData;Z)V
    //   87: putfield 49	com/appbuilder/sdk/android/AppBuilderModule:adView	Lcom/appbuilder/sdk/android/AppAdvView;
    //   90: aload_0
    //   91: aload 6
    //   93: ldc_w 572
    //   96: invokevirtual 575	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   99: putfield 45	com/appbuilder/sdk/android/AppBuilderModule:flurryId	Ljava/lang/String;
    //   102: aload_0
    //   103: aload 6
    //   105: ldc_w 577
    //   108: invokevirtual 544	android/os/Bundle:getSerializable	(Ljava/lang/String;)Ljava/io/Serializable;
    //   111: checkcast 579	com/appbuilder/sdk/android/Widget
    //   114: putfield 55	com/appbuilder/sdk/android/AppBuilderModule:widget	Lcom/appbuilder/sdk/android/Widget;
    //   117: aload_0
    //   118: aload_0
    //   119: getfield 55	com/appbuilder/sdk/android/AppBuilderModule:widget	Lcom/appbuilder/sdk/android/Widget;
    //   122: invokevirtual 582	com/appbuilder/sdk/android/Widget:getTitle	()Ljava/lang/String;
    //   125: invokespecial 585	android/app/Activity:setTitle	(Ljava/lang/CharSequence;)V
    //   128: aload_0
    //   129: invokevirtual 587	com/appbuilder/sdk/android/AppBuilderModule:create	()V
    //   132: return
    //   133: astore_2
    //   134: aload_2
    //   135: ldc_w 589
    //   138: invokestatic 249	com/appbuilder/statistics/StatisticsCollector:newError	(Ljava/lang/Exception;Ljava/lang/String;)V
    //   141: aload_0
    //   142: invokevirtual 592	com/appbuilder/sdk/android/AppBuilderModule:finish	()V
    //   145: goto -17 -> 128
    //   148: astore_3
    //   149: aload_3
    //   150: aload_0
    //   151: invokevirtual 598	java/lang/Object:getClass	()Ljava/lang/Class;
    //   154: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   157: invokestatic 249	com/appbuilder/statistics/StatisticsCollector:newError	(Ljava/lang/Exception;Ljava/lang/String;)V
    //   160: return
    //   161: astore 5
    //   163: goto -35 -> 128
    //   166: astore 4
    //   168: goto -147 -> 21
    //
    // Exception table:
    //   from	to	target	type
    //   0	10	133	java/lang/Exception
    //   21	25	133	java/lang/Exception
    //   128	132	148	java/lang/Exception
    //   25	90	161	java/lang/Exception
    //   90	128	161	java/lang/Exception
    //   10	21	166	java/lang/Exception
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return true;
  }

  public final void onDestroy()
  {
    try
    {
      writeConfiguration();
    }
    catch (Exception localException1)
    {
      try
      {
        while (true)
        {
          destroy();
          super.onDestroy();
          return;
          localException1 = localException1;
          StatisticsCollector.newError(localException1, "AppBuilderModule.onDestroy()");
        }
      }
      catch (Exception localException2)
      {
        while (true)
          StatisticsCollector.newError(localException2, getClass().getName());
      }
    }
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    return super.onOptionsItemSelected(paramMenuItem);
  }

  public final void onPause()
  {
    try
    {
      pause();
      super.onPause();
      return;
    }
    catch (Exception localException)
    {
      while (true)
        StatisticsCollector.newError(localException, getClass().getName());
    }
  }

  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    while (true)
    {
      Object localObject;
      try
      {
        if (this.nativeFeatures.isEmpty())
          return false;
        paramMenu.clear();
        Iterator localIterator = this.nativeFeatures.entrySet().iterator();
        if (!localIterator.hasNext())
          break;
        localObject = ((Map.Entry)localIterator.next()).getKey();
        if (localObject.equals(NATIVE_FEATURES.SMS))
        {
          MenuItem localMenuItem4 = paramMenu.add("");
          localMenuItem4.setTitle("Send SMS");
          localMenuItem4.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
          {
            public boolean onMenuItemClick(MenuItem paramMenuItem)
            {
              AppBuilderModule.this.handler.sendEmptyMessage(1);
              return true;
            }
          });
          continue;
        }
      }
      catch (Exception localException)
      {
        StatisticsCollector.newError(localException, "AppBuilderModule.onPrepareOptionsMenu()");
        return false;
      }
      if (localObject.equals(NATIVE_FEATURES.EMAIL))
      {
        MenuItem localMenuItem3 = paramMenu.add("");
        localMenuItem3.setTitle("Send Email");
        localMenuItem3.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
          public boolean onMenuItemClick(MenuItem paramMenuItem)
          {
            AppBuilderModule.this.handler.sendEmptyMessage(2);
            return true;
          }
        });
        continue;
      }
      if (localObject.equals(NATIVE_FEATURES.ADD_CONTACT))
      {
        MenuItem localMenuItem2 = paramMenu.add("");
        localMenuItem2.setTitle("Add Contact");
        localMenuItem2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
          public boolean onMenuItemClick(MenuItem paramMenuItem)
          {
            AppBuilderModule.this.handler.sendEmptyMessage(3);
            return true;
          }
        });
        continue;
      }
      if (!localObject.equals(NATIVE_FEATURES.ADD_EVENT))
        continue;
      MenuItem localMenuItem1 = paramMenu.add("");
      localMenuItem1.setTitle("Add Event");
      localMenuItem1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
      {
        public boolean onMenuItemClick(MenuItem paramMenuItem)
        {
          AppBuilderModule.this.handler.sendEmptyMessage(4);
          return true;
        }
      });
    }
    boolean bool = super.onPrepareOptionsMenu(paramMenu);
    return bool;
  }

  public final void onRestart()
  {
    try
    {
      restart();
      super.onRestart();
      return;
    }
    catch (Exception localException)
    {
      while (true)
        StatisticsCollector.newError(localException, getClass().getName());
    }
  }

  public final void onResume()
  {
    try
    {
      resume();
      super.onResume();
      return;
    }
    catch (Exception localException)
    {
      while (true)
        StatisticsCollector.newError(localException, getClass().getName());
    }
  }

  public final void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle.putSerializable("session", this.session);
  }

  public final void onStart()
  {
    try
    {
      this.millis = System.currentTimeMillis();
      HashMap localHashMap = new HashMap();
      localHashMap.put("action", "start");
      FlurryAgent.logEvent(getClass().getSimpleName(), localHashMap);
    }
    catch (Exception localException1)
    {
      try
      {
        while (true)
        {
          start();
          super.onStart();
          return;
          localException1 = localException1;
          Log.d("", "");
        }
      }
      catch (Exception localException2)
      {
        while (true)
          StatisticsCollector.newError(localException2, getClass().getName());
      }
    }
  }

  public final void onStop()
  {
    try
    {
      int i = (int)(System.currentTimeMillis() - this.millis) / 1000;
      HashMap localHashMap = new HashMap();
      localHashMap.put("action", "stop");
      localHashMap.put("usage interval", "" + i);
      FlurryAgent.logEvent(getClass().getSimpleName(), localHashMap);
    }
    catch (Exception localException1)
    {
      try
      {
        while (true)
        {
          stop();
          super.onPause();
          return;
          localException1 = localException1;
          Log.d("", "");
        }
      }
      catch (Exception localException2)
      {
        while (true)
          StatisticsCollector.newError(localException2, getClass().getName());
      }
    }
  }

  public void pause()
  {
  }

  public final void removeNativeFeature(NATIVE_FEATURES paramNATIVE_FEATURES)
  {
    if (this.nativeFeatures.containsKey(paramNATIVE_FEATURES))
      this.nativeFeatures.remove(paramNATIVE_FEATURES);
  }

  public void restart()
  {
  }

  public void resume()
  {
  }

  public final void setContentView(int paramInt)
  {
    try
    {
      LinearLayout localLinearLayout = new LinearLayout(this);
      localLinearLayout.setOrientation(1);
      try
      {
        if (this.adView != null)
        {
          if (this.adView.getParent() != null)
            ((ViewGroup)this.adView.getParent()).removeAllViews();
          localLinearLayout.addView(this.adView);
        }
        View localView = LayoutInflater.from(this).inflate(paramInt, null);
        LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
        localLayoutParams.weight = 1.0F;
        localView.setLayoutParams(localLayoutParams);
        localLinearLayout.addView(localView);
        super.setContentView(localLinearLayout);
        return;
      }
      catch (Exception localException2)
      {
        while (true)
          Log.w("", "");
      }
    }
    catch (Exception localException1)
    {
      StatisticsCollector.newError(localException1, "AppBilderModule.setContentView(int)");
    }
  }

  public final void setContentView(View paramView)
  {
    try
    {
      LinearLayout localLinearLayout = new LinearLayout(this);
      localLinearLayout.setOrientation(1);
      if (this.adView != null)
        localLinearLayout.addView(this.adView);
      localLinearLayout.addView(paramView);
      super.setContentView(localLinearLayout);
      return;
    }
    catch (Exception localException)
    {
      StatisticsCollector.newError(localException, "AppBuilderModule.setContentView(View)");
    }
  }

  public final void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    try
    {
      LinearLayout localLinearLayout = new LinearLayout(this);
      localLinearLayout.setOrientation(1);
      if (this.adView != null)
        localLinearLayout.addView(this.adView);
      localLinearLayout.addView(paramView, paramLayoutParams);
      super.setContentView(localLinearLayout);
      return;
    }
    catch (Exception localException)
    {
      StatisticsCollector.newError(localException, "AppBuilderModule.setContentView(View,ViewGroup.LayoutParams)");
    }
  }

  public final void setSession(Serializable paramSerializable)
  {
    this.session = paramSerializable;
  }

  public final void setTitle(int paramInt)
  {
  }

  public final void setTitle(CharSequence paramCharSequence)
  {
  }

  public void start()
  {
  }

  // ERROR //
  public final void startActivity(Intent paramIntent)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_2
    //   2: aload_1
    //   3: invokevirtual 831	android/content/Intent:getAction	()Ljava/lang/String;
    //   6: astore 4
    //   8: aload 4
    //   10: ifnonnull +248 -> 258
    //   13: aload_1
    //   14: ldc_w 556
    //   17: aload_0
    //   18: getfield 47	com/appbuilder/sdk/android/AppBuilderModule:advData	Lcom/appbuilder/sdk/android/AppAdvData;
    //   21: invokevirtual 414	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;
    //   24: pop
    //   25: aload_1
    //   26: aload_0
    //   27: invokevirtual 835	com/appbuilder/sdk/android/AppBuilderModule:getPackageManager	()Landroid/content/pm/PackageManager;
    //   30: invokevirtual 839	android/content/Intent:resolveActivity	(Landroid/content/pm/PackageManager;)Landroid/content/ComponentName;
    //   33: invokevirtual 844	android/content/ComponentName:getClassName	()Ljava/lang/String;
    //   36: astore 7
    //   38: aload 7
    //   40: invokestatic 848	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   43: astore 16
    //   45: aload 16
    //   47: astore 10
    //   49: aload 10
    //   51: invokevirtual 851	java/lang/Class:getSuperclass	()Ljava/lang/Class;
    //   54: astore 12
    //   56: aload 12
    //   58: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   61: ldc_w 853
    //   64: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   67: ifne +189 -> 256
    //   70: aload 10
    //   72: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   75: ldc_w 855
    //   78: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   81: ifne +175 -> 256
    //   84: aload 12
    //   86: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   89: ldc_w 857
    //   92: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   95: ifne +161 -> 256
    //   98: aload 12
    //   100: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   103: ldc_w 859
    //   106: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   109: ifne +147 -> 256
    //   112: aload 10
    //   114: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   117: ldc_w 861
    //   120: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   123: ifne +133 -> 256
    //   126: aload 10
    //   128: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   131: ldc_w 863
    //   134: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   137: ifne +119 -> 256
    //   140: aload 10
    //   142: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   145: ldc_w 865
    //   148: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   151: ifne +105 -> 256
    //   154: aload 10
    //   156: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   159: ldc_w 867
    //   162: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   165: ifne +91 -> 256
    //   168: aload 10
    //   170: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   173: ldc_w 869
    //   176: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   179: ifne +77 -> 256
    //   182: aload 10
    //   184: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   187: ldc_w 871
    //   190: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   193: ifne +63 -> 256
    //   196: aload 10
    //   198: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   201: ldc_w 873
    //   204: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   207: ifne +49 -> 256
    //   210: aload 10
    //   212: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   215: ldc_w 875
    //   218: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   221: ifne +35 -> 256
    //   224: aload 10
    //   226: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   229: ldc_w 877
    //   232: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   235: ifne +21 -> 256
    //   238: aload 10
    //   240: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   243: ldc_w 879
    //   246: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   249: istore 13
    //   251: iload 13
    //   253: ifeq +57 -> 310
    //   256: iconst_1
    //   257: istore_2
    //   258: iload_2
    //   259: ifeq +85 -> 344
    //   262: aload_0
    //   263: aload_1
    //   264: invokespecial 880	android/app/Activity:startActivity	(Landroid/content/Intent;)V
    //   267: return
    //   268: astore 5
    //   270: ldc_w 882
    //   273: aload 5
    //   275: invokevirtual 883	java/lang/Exception:toString	()Ljava/lang/String;
    //   278: invokestatic 83	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   281: pop
    //   282: goto -257 -> 25
    //   285: astore_3
    //   286: aload_3
    //   287: ldc_w 885
    //   290: invokestatic 249	com/appbuilder/statistics/StatisticsCollector:newError	(Ljava/lang/Exception;Ljava/lang/String;)V
    //   293: return
    //   294: astore 8
    //   296: ldc 43
    //   298: ldc 43
    //   300: invokestatic 887	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   303: pop
    //   304: aconst_null
    //   305: astore 10
    //   307: goto -258 -> 49
    //   310: aload 12
    //   312: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   315: ldc_w 889
    //   318: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   321: istore 14
    //   323: iconst_0
    //   324: istore_2
    //   325: iload 14
    //   327: ifne -69 -> 258
    //   330: aload 12
    //   332: invokevirtual 851	java/lang/Class:getSuperclass	()Ljava/lang/Class;
    //   335: astore 15
    //   337: aload 15
    //   339: astore 12
    //   341: goto -285 -> 56
    //   344: aload_0
    //   345: ldc_w 891
    //   348: iconst_1
    //   349: invokestatic 319	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   352: invokevirtual 320	android/widget/Toast:show	()V
    //   355: return
    //   356: astore 11
    //   358: iconst_0
    //   359: istore_2
    //   360: goto -102 -> 258
    //
    // Exception table:
    //   from	to	target	type
    //   13	25	268	java/lang/Exception
    //   2	8	285	java/lang/Exception
    //   25	38	285	java/lang/Exception
    //   38	45	285	java/lang/Exception
    //   49	56	285	java/lang/Exception
    //   56	251	285	java/lang/Exception
    //   262	267	285	java/lang/Exception
    //   270	282	285	java/lang/Exception
    //   296	304	285	java/lang/Exception
    //   310	323	285	java/lang/Exception
    //   330	337	285	java/lang/Exception
    //   344	355	285	java/lang/Exception
    //   38	45	294	java/lang/ClassNotFoundException
    //   49	56	356	java/lang/NullPointerException
    //   56	251	356	java/lang/NullPointerException
    //   310	323	356	java/lang/NullPointerException
    //   330	337	356	java/lang/NullPointerException
  }

  // ERROR //
  public final void startActivityForResult(Intent paramIntent, int paramInt)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_3
    //   2: aload_1
    //   3: invokevirtual 831	android/content/Intent:getAction	()Ljava/lang/String;
    //   6: astore 5
    //   8: aload 5
    //   10: ifnonnull +248 -> 258
    //   13: aload_1
    //   14: ldc_w 556
    //   17: aload_0
    //   18: getfield 47	com/appbuilder/sdk/android/AppBuilderModule:advData	Lcom/appbuilder/sdk/android/AppAdvData;
    //   21: invokevirtual 414	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;
    //   24: pop
    //   25: aload_1
    //   26: aload_0
    //   27: invokevirtual 835	com/appbuilder/sdk/android/AppBuilderModule:getPackageManager	()Landroid/content/pm/PackageManager;
    //   30: invokevirtual 839	android/content/Intent:resolveActivity	(Landroid/content/pm/PackageManager;)Landroid/content/ComponentName;
    //   33: invokevirtual 844	android/content/ComponentName:getClassName	()Ljava/lang/String;
    //   36: astore 8
    //   38: aload 8
    //   40: invokestatic 848	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   43: astore 17
    //   45: aload 17
    //   47: astore 11
    //   49: aload 11
    //   51: invokevirtual 851	java/lang/Class:getSuperclass	()Ljava/lang/Class;
    //   54: astore 13
    //   56: aload 13
    //   58: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   61: ldc_w 853
    //   64: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   67: ifne +189 -> 256
    //   70: aload 13
    //   72: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   75: ldc_w 859
    //   78: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   81: ifne +175 -> 256
    //   84: aload 11
    //   86: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   89: ldc_w 855
    //   92: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   95: ifne +161 -> 256
    //   98: aload 13
    //   100: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   103: ldc_w 857
    //   106: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   109: ifne +147 -> 256
    //   112: aload 11
    //   114: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   117: ldc_w 861
    //   120: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   123: ifne +133 -> 256
    //   126: aload 11
    //   128: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   131: ldc_w 863
    //   134: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   137: ifne +119 -> 256
    //   140: aload 11
    //   142: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   145: ldc_w 865
    //   148: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   151: ifne +105 -> 256
    //   154: aload 11
    //   156: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   159: ldc_w 867
    //   162: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   165: ifne +91 -> 256
    //   168: aload 11
    //   170: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   173: ldc_w 869
    //   176: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   179: ifne +77 -> 256
    //   182: aload 11
    //   184: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   187: ldc_w 871
    //   190: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   193: ifne +63 -> 256
    //   196: aload 11
    //   198: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   201: ldc_w 873
    //   204: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   207: ifne +49 -> 256
    //   210: aload 11
    //   212: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   215: ldc_w 875
    //   218: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   221: ifne +35 -> 256
    //   224: aload 11
    //   226: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   229: ldc_w 877
    //   232: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   235: ifne +21 -> 256
    //   238: aload 11
    //   240: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   243: ldc_w 879
    //   246: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   249: istore 14
    //   251: iload 14
    //   253: ifeq +60 -> 313
    //   256: iconst_1
    //   257: istore_3
    //   258: iload_3
    //   259: ifeq +88 -> 347
    //   262: aload_0
    //   263: aload_1
    //   264: iload_2
    //   265: invokespecial 895	android/app/Activity:startActivityForResult	(Landroid/content/Intent;I)V
    //   268: return
    //   269: astore 6
    //   271: ldc_w 882
    //   274: aload 6
    //   276: invokevirtual 883	java/lang/Exception:toString	()Ljava/lang/String;
    //   279: invokestatic 83	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   282: pop
    //   283: goto -258 -> 25
    //   286: astore 4
    //   288: aload 4
    //   290: ldc_w 885
    //   293: invokestatic 249	com/appbuilder/statistics/StatisticsCollector:newError	(Ljava/lang/Exception;Ljava/lang/String;)V
    //   296: return
    //   297: astore 9
    //   299: ldc 43
    //   301: ldc 43
    //   303: invokestatic 887	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   306: pop
    //   307: aconst_null
    //   308: astore 11
    //   310: goto -261 -> 49
    //   313: aload 13
    //   315: invokevirtual 603	java/lang/Class:getName	()Ljava/lang/String;
    //   318: ldc_w 889
    //   321: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   324: istore 15
    //   326: iconst_0
    //   327: istore_3
    //   328: iload 15
    //   330: ifne -72 -> 258
    //   333: aload 13
    //   335: invokevirtual 851	java/lang/Class:getSuperclass	()Ljava/lang/Class;
    //   338: astore 16
    //   340: aload 16
    //   342: astore 13
    //   344: goto -288 -> 56
    //   347: aload_0
    //   348: ldc_w 891
    //   351: iconst_1
    //   352: invokestatic 319	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   355: invokevirtual 320	android/widget/Toast:show	()V
    //   358: return
    //   359: astore 12
    //   361: iconst_0
    //   362: istore_3
    //   363: goto -105 -> 258
    //
    // Exception table:
    //   from	to	target	type
    //   13	25	269	java/lang/Exception
    //   2	8	286	java/lang/Exception
    //   25	38	286	java/lang/Exception
    //   38	45	286	java/lang/Exception
    //   49	56	286	java/lang/Exception
    //   56	251	286	java/lang/Exception
    //   262	268	286	java/lang/Exception
    //   271	283	286	java/lang/Exception
    //   299	307	286	java/lang/Exception
    //   313	326	286	java/lang/Exception
    //   333	340	286	java/lang/Exception
    //   347	358	286	java/lang/Exception
    //   38	45	297	java/lang/ClassNotFoundException
    //   49	56	359	java/lang/NullPointerException
    //   56	251	359	java/lang/NullPointerException
    //   313	326	359	java/lang/NullPointerException
    //   333	340	359	java/lang/NullPointerException
  }

  public void stop()
  {
  }

  public static enum NATIVE_FEATURES
  {
    static
    {
      EMAIL = new NATIVE_FEATURES("EMAIL", 1);
      ADD_CONTACT = new NATIVE_FEATURES("ADD_CONTACT", 2);
      ADD_EVENT = new NATIVE_FEATURES("ADD_EVENT", 3);
      LOCAL_NOTIFICATION = new NATIVE_FEATURES("LOCAL_NOTIFICATION", 4);
      NATIVE_FEATURES[] arrayOfNATIVE_FEATURES = new NATIVE_FEATURES[5];
      arrayOfNATIVE_FEATURES[0] = SMS;
      arrayOfNATIVE_FEATURES[1] = EMAIL;
      arrayOfNATIVE_FEATURES[2] = ADD_CONTACT;
      arrayOfNATIVE_FEATURES[3] = ADD_EVENT;
      arrayOfNATIVE_FEATURES[4] = LOCAL_NOTIFICATION;
      $VALUES = arrayOfNATIVE_FEATURES;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.sdk.android.AppBuilderModule
 * JD-Core Version:    0.6.0
 */