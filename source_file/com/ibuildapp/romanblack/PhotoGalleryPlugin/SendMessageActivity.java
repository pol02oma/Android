package com.ibuildapp.romanblack.PhotoGalleryPlugin;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMain;
import com.appbuilder.sdk.android.authorization.Authorization;
import com.appbuilder.sdk.android.authorization.entities.User;
import com.appbuilder.sdk.android.authorization.entities.User.ACCOUNT_TYPES;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.CommentItem;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.ImageItem;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.utils.JSONParser;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

public class SendMessageActivity extends AppBuilderModuleMain
  implements View.OnClickListener, TextView.OnEditorActionListener, TextWatcher, ViewTreeObserver.OnGlobalLayoutListener
{
  private final int CLOSE_ACTIVITY_BAD = 1;
  private final int CLOSE_ACTIVITY_OK = 0;
  private final int PICK_IMAGE_ACTIVITY = 10001;
  private final int PROGRESS_SIZE = 20;
  private String TAG = "com.ibuildapp.romanblack.PhotoGalleryPlugin.SendMessageActivity";
  private final int TAKE_A_PICTURE_ACTIVITY = 10000;
  private LinearLayout bottomPanel = null;
  private TextView clearButton = null;
  private int displayHeight = 0;
  private LinearLayout editLayout = null;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      default:
        return;
      case 0:
        SendMessageActivity.this.closeActivityOK();
        return;
      case 1:
      }
      SendMessageActivity.this.closeActivityBad();
    }
  };
  private LinearLayout header = null;
  private View headerView = null;
  private ImageItem image = null;
  private String imagePath = "";
  private CommentItem message = null;
  private EditText messageEditText = null;
  private TextView postButton = null;
  private ProgressBar progressBar = null;
  private CommentItem recievedMessage = null;
  private LinearLayout root = null;
  private TextView symbolCounter = null;
  private boolean uploading = false;

  private void closeActivityBad()
  {
    setResult(0);
    finish();
  }

  private void closeActivityOK()
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("message", this.recievedMessage);
    setResult(-1, localIntent);
    finish();
  }

  private Bitmap decodeImageFile(String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      File localFile = new File(paramString);
      BitmapFactory.Options localOptions1 = new BitmapFactory.Options();
      localOptions1.inJustDecodeBounds = true;
      BitmapFactory.decodeStream(new FileInputStream(localFile), null, localOptions1);
      int i = localOptions1.outWidth;
      int j = localOptions1.outHeight;
      int k = 1;
      Bitmap localBitmap;
      int m;
      int n;
      if ((i / 2 < paramInt1) || (j / 2 < paramInt2))
      {
        BitmapFactory.Options localOptions2 = new BitmapFactory.Options();
        localOptions2.inSampleSize = k;
        localBitmap = BitmapFactory.decodeStream(new FileInputStream(localFile), null, localOptions2);
        if (i <= j)
          break label209;
        m = (i - j) / 2;
        n = 1;
      }
      for (int i1 = j - 1; ; i1 = i - 1)
      {
        float f1 = (paramInt1 - 4) / i1;
        float f2 = (paramInt2 - 4) / i1;
        Matrix localMatrix = new Matrix();
        localMatrix.postScale(f1, f2);
        return Bitmap.createBitmap(localBitmap, m, n, i1, i1, localMatrix, true);
        i /= 2;
        j /= 2;
        k *= 2;
        break;
        label209: m = 1;
        n = (j - i) / 2;
      }
    }
    catch (Exception localException)
    {
      Log.d("", "");
    }
    return null;
  }

  private void keyboardHidden()
  {
    this.header.setVisibility(0);
    this.bottomPanel.setVisibility(8);
    int i = 90 * (int)getResources().getDisplayMetrics().density;
    this.editLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, i));
  }

  private void keyboardShown()
  {
    this.header.setVisibility(0);
    if (this.displayHeight < 481)
      this.header.setVisibility(8);
    this.bottomPanel.setVisibility(0);
    int i = 90 * (int)getResources().getDisplayMetrics().density;
    this.editLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, i));
  }

  public void afterTextChanged(Editable paramEditable)
  {
    this.symbolCounter.setText(paramEditable.length() + "/150");
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  // ERROR //
  public void create()
  {
    // Byte code:
    //   0: aload_0
    //   1: getstatic 265	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$layout:romanblack_photogallery_send_message	I
    //   4: invokevirtual 268	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:setContentView	(I)V
    //   7: aload_0
    //   8: invokevirtual 271	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:swipeBlock	()V
    //   11: aload_0
    //   12: invokevirtual 205	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:getResources	()Landroid/content/res/Resources;
    //   15: getstatic 276	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$string:back	I
    //   18: invokevirtual 280	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   21: astore_1
    //   22: new 282	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity$2
    //   25: dup
    //   26: aload_0
    //   27: invokespecial 283	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity$2:<init>	(Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity;)V
    //   30: astore_2
    //   31: aload_0
    //   32: aload_1
    //   33: iconst_1
    //   34: aload_2
    //   35: invokevirtual 287	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:setTopBarLeftButtonText	(Ljava/lang/String;ZLandroid/view/View$OnClickListener;)V
    //   38: new 289	android/widget/ProgressBar
    //   41: dup
    //   42: aload_0
    //   43: invokespecial 292	android/widget/ProgressBar:<init>	(Landroid/content/Context;)V
    //   46: astore_3
    //   47: aload_0
    //   48: aload_3
    //   49: putfield 98	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:progressBar	Landroid/widget/ProgressBar;
    //   52: aload_0
    //   53: getfield 98	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:progressBar	Landroid/widget/ProgressBar;
    //   56: iconst_4
    //   57: invokevirtual 293	android/widget/ProgressBar:setVisibility	(I)V
    //   60: aload_0
    //   61: invokevirtual 205	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:getResources	()Landroid/content/res/Resources;
    //   64: invokevirtual 211	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   67: getfield 217	android/util/DisplayMetrics:density	F
    //   70: fstore 4
    //   72: new 295	android/view/ViewGroup$LayoutParams
    //   75: dup
    //   76: ldc_w 296
    //   79: fload 4
    //   81: fmul
    //   82: f2i
    //   83: ldc_w 296
    //   86: fload 4
    //   88: fmul
    //   89: f2i
    //   90: invokespecial 297	android/view/ViewGroup$LayoutParams:<init>	(II)V
    //   93: astore 5
    //   95: aload_0
    //   96: getfield 98	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:progressBar	Landroid/widget/ProgressBar;
    //   99: aload 5
    //   101: invokevirtual 298	android/widget/ProgressBar:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   104: aload_0
    //   105: aload_0
    //   106: getfield 98	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:progressBar	Landroid/widget/ProgressBar;
    //   109: invokevirtual 302	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:drawTopBarRightButton	(Landroid/view/View;)V
    //   112: aload_0
    //   113: invokevirtual 306	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:getIntent	()Landroid/content/Intent;
    //   116: astore 6
    //   118: aload_0
    //   119: aload 6
    //   121: ldc 134
    //   123: invokevirtual 310	android/content/Intent:getSerializableExtra	(Ljava/lang/String;)Ljava/io/Serializable;
    //   126: checkcast 312	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/CommentItem
    //   129: putfield 74	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:message	Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/entities/CommentItem;
    //   132: aload_0
    //   133: aload 6
    //   135: ldc_w 314
    //   138: invokevirtual 310	android/content/Intent:getSerializableExtra	(Ljava/lang/String;)Ljava/io/Serializable;
    //   141: checkcast 316	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem
    //   144: putfield 78	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:image	Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem;
    //   147: aload_0
    //   148: aload_0
    //   149: invokevirtual 205	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:getResources	()Landroid/content/res/Resources;
    //   152: invokevirtual 211	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   155: getfield 319	android/util/DisplayMetrics:heightPixels	I
    //   158: putfield 66	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:displayHeight	I
    //   161: aload_0
    //   162: aload_0
    //   163: getstatic 324	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_sendmessage_edittext	I
    //   166: invokevirtual 328	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:findViewById	(I)Landroid/view/View;
    //   169: checkcast 330	android/widget/EditText
    //   172: putfield 90	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:messageEditText	Landroid/widget/EditText;
    //   175: aload_0
    //   176: getfield 90	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:messageEditText	Landroid/widget/EditText;
    //   179: ldc_w 331
    //   182: invokevirtual 334	android/widget/EditText:setImeOptions	(I)V
    //   185: aload_0
    //   186: getfield 90	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:messageEditText	Landroid/widget/EditText;
    //   189: aload_0
    //   190: invokevirtual 338	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   193: aload_0
    //   194: aload_0
    //   195: getstatic 341	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_sendmessage_clear_btn	I
    //   198: invokevirtual 328	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:findViewById	(I)Landroid/view/View;
    //   201: checkcast 253	android/widget/TextView
    //   204: putfield 92	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:clearButton	Landroid/widget/TextView;
    //   207: aload_0
    //   208: getfield 92	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:clearButton	Landroid/widget/TextView;
    //   211: aload_0
    //   212: invokevirtual 345	android/widget/TextView:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   215: aload_0
    //   216: aload_0
    //   217: getfield 92	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:clearButton	Landroid/widget/TextView;
    //   220: aload_0
    //   221: getfield 349	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:bottomBarDesign	Lcom/appbuilder/sdk/android/BarDesigner;
    //   224: getfield 355	com/appbuilder/sdk/android/BarDesigner:leftButtonDesign	Lcom/appbuilder/sdk/android/BarDesigner$TitleDesign;
    //   227: invokevirtual 359	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:designButton	(Landroid/widget/TextView;Lcom/appbuilder/sdk/android/BarDesigner$TitleDesign;)V
    //   230: aload_0
    //   231: getfield 92	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:clearButton	Landroid/widget/TextView;
    //   234: aload_0
    //   235: getfield 349	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:bottomBarDesign	Lcom/appbuilder/sdk/android/BarDesigner;
    //   238: getfield 355	com/appbuilder/sdk/android/BarDesigner:leftButtonDesign	Lcom/appbuilder/sdk/android/BarDesigner$TitleDesign;
    //   241: getfield 364	com/appbuilder/sdk/android/BarDesigner$TitleDesign:textColor	I
    //   244: invokevirtual 367	android/widget/TextView:setTextColor	(I)V
    //   247: aload_0
    //   248: aload_0
    //   249: getstatic 370	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_sendmessage_post_btn	I
    //   252: invokevirtual 328	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:findViewById	(I)Landroid/view/View;
    //   255: checkcast 253	android/widget/TextView
    //   258: putfield 94	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:postButton	Landroid/widget/TextView;
    //   261: aload_0
    //   262: getfield 94	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:postButton	Landroid/widget/TextView;
    //   265: aload_0
    //   266: invokevirtual 345	android/widget/TextView:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   269: aload_0
    //   270: aload_0
    //   271: getfield 94	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:postButton	Landroid/widget/TextView;
    //   274: aload_0
    //   275: getfield 349	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:bottomBarDesign	Lcom/appbuilder/sdk/android/BarDesigner;
    //   278: getfield 373	com/appbuilder/sdk/android/BarDesigner:rightButtonDesign	Lcom/appbuilder/sdk/android/BarDesigner$TitleDesign;
    //   281: invokevirtual 359	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:designButton	(Landroid/widget/TextView;Lcom/appbuilder/sdk/android/BarDesigner$TitleDesign;)V
    //   284: aload_0
    //   285: getfield 94	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:postButton	Landroid/widget/TextView;
    //   288: aload_0
    //   289: getfield 349	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:bottomBarDesign	Lcom/appbuilder/sdk/android/BarDesigner;
    //   292: getfield 355	com/appbuilder/sdk/android/BarDesigner:leftButtonDesign	Lcom/appbuilder/sdk/android/BarDesigner$TitleDesign;
    //   295: getfield 364	com/appbuilder/sdk/android/BarDesigner$TitleDesign:textColor	I
    //   298: invokevirtual 367	android/widget/TextView:setTextColor	(I)V
    //   301: aload_0
    //   302: aload_0
    //   303: getstatic 376	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_sendmessage_symbols_counter	I
    //   306: invokevirtual 328	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:findViewById	(I)Landroid/view/View;
    //   309: checkcast 253	android/widget/TextView
    //   312: putfield 96	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:symbolCounter	Landroid/widget/TextView;
    //   315: aload_0
    //   316: aload_0
    //   317: getstatic 379	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_sendmessage_bottom_panel	I
    //   320: invokevirtual 328	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:findViewById	(I)Landroid/view/View;
    //   323: checkcast 198	android/widget/LinearLayout
    //   326: putfield 82	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:bottomPanel	Landroid/widget/LinearLayout;
    //   329: aload_0
    //   330: getfield 82	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:bottomPanel	Landroid/widget/LinearLayout;
    //   333: bipush 8
    //   335: invokevirtual 201	android/widget/LinearLayout:setVisibility	(I)V
    //   338: aload_0
    //   339: aload_0
    //   340: getstatic 382	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_sendmessage_root	I
    //   343: invokevirtual 328	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:findViewById	(I)Landroid/view/View;
    //   346: checkcast 198	android/widget/LinearLayout
    //   349: putfield 80	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:root	Landroid/widget/LinearLayout;
    //   352: aload_0
    //   353: getfield 80	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:root	Landroid/widget/LinearLayout;
    //   356: getstatic 387	com/ibuildapp/romanblack/PhotoGalleryPlugin/Statics:color1	I
    //   359: invokevirtual 390	android/widget/LinearLayout:setBackgroundColor	(I)V
    //   362: aload_0
    //   363: getfield 80	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:root	Landroid/widget/LinearLayout;
    //   366: invokevirtual 394	android/widget/LinearLayout:getViewTreeObserver	()Landroid/view/ViewTreeObserver;
    //   369: aload_0
    //   370: invokevirtual 400	android/view/ViewTreeObserver:addOnGlobalLayoutListener	(Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;)V
    //   373: aload_0
    //   374: aload_0
    //   375: getstatic 403	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_sendmessage_edittext_layout	I
    //   378: invokevirtual 328	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:findViewById	(I)Landroid/view/View;
    //   381: checkcast 198	android/widget/LinearLayout
    //   384: putfield 84	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:editLayout	Landroid/widget/LinearLayout;
    //   387: aload_0
    //   388: aload_0
    //   389: getstatic 406	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_sendmessage_header	I
    //   392: invokevirtual 328	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:findViewById	(I)Landroid/view/View;
    //   395: checkcast 198	android/widget/LinearLayout
    //   398: putfield 86	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:header	Landroid/widget/LinearLayout;
    //   401: aload_0
    //   402: getfield 74	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:message	Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/entities/CommentItem;
    //   405: ifnonnull +226 -> 631
    //   408: aload_0
    //   409: aload_0
    //   410: getstatic 409	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$string:romanblack_photogallery_comment	I
    //   413: invokevirtual 410	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:getString	(I)Ljava/lang/String;
    //   416: invokevirtual 413	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:setTopBarTitle	(Ljava/lang/String;)V
    //   419: aload_0
    //   420: aload_0
    //   421: invokestatic 419	android/view/LayoutInflater:from	(Landroid/content/Context;)Landroid/view/LayoutInflater;
    //   424: getstatic 422	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$layout:romanblack_photogallery_comments_list_header	I
    //   427: aconst_null
    //   428: invokevirtual 426	android/view/LayoutInflater:inflate	(ILandroid/view/ViewGroup;)Landroid/view/View;
    //   431: putfield 88	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:headerView	Landroid/view/View;
    //   434: aload_0
    //   435: getfield 88	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:headerView	Landroid/view/View;
    //   438: getstatic 429	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_comments_listview_header_title	I
    //   441: invokevirtual 432	android/view/View:findViewById	(I)Landroid/view/View;
    //   444: checkcast 253	android/widget/TextView
    //   447: astore 26
    //   449: aload 26
    //   451: aload_0
    //   452: getfield 78	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:image	Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem;
    //   455: invokevirtual 435	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem:getTitle	()Ljava/lang/String;
    //   458: invokevirtual 257	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   461: aload 26
    //   463: getstatic 438	com/ibuildapp/romanblack/PhotoGalleryPlugin/Statics:color3	I
    //   466: invokevirtual 367	android/widget/TextView:setTextColor	(I)V
    //   469: aload_0
    //   470: getfield 88	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:headerView	Landroid/view/View;
    //   473: getstatic 441	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_comments_listview_header_description	I
    //   476: invokevirtual 432	android/view/View:findViewById	(I)Landroid/view/View;
    //   479: checkcast 253	android/widget/TextView
    //   482: astore 27
    //   484: aload 27
    //   486: aload_0
    //   487: getfield 78	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:image	Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem;
    //   490: invokevirtual 444	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem:getDescription	()Ljava/lang/String;
    //   493: invokevirtual 257	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   496: aload 27
    //   498: getstatic 447	com/ibuildapp/romanblack/PhotoGalleryPlugin/Statics:color4	I
    //   501: invokevirtual 367	android/widget/TextView:setTextColor	(I)V
    //   504: aload_0
    //   505: getfield 88	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:headerView	Landroid/view/View;
    //   508: getstatic 450	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_comments_listview_header_thumb	I
    //   511: invokevirtual 432	android/view/View:findViewById	(I)Landroid/view/View;
    //   514: checkcast 452	android/widget/ImageView
    //   517: aload_0
    //   518: aload_0
    //   519: getfield 78	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:image	Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem;
    //   522: invokevirtual 455	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem:getImagePath	()Ljava/lang/String;
    //   525: bipush 70
    //   527: bipush 70
    //   529: invokespecial 457	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:decodeImageFile	(Ljava/lang/String;II)Landroid/graphics/Bitmap;
    //   532: invokevirtual 461	android/widget/ImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   535: aload_0
    //   536: getfield 78	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:image	Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem;
    //   539: invokevirtual 464	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem:getImageUrl	()Ljava/lang/String;
    //   542: ifnonnull +22 -> 564
    //   545: aload_0
    //   546: getfield 88	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:headerView	Landroid/view/View;
    //   549: getstatic 450	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_comments_listview_header_thumb	I
    //   552: invokevirtual 432	android/view/View:findViewById	(I)Landroid/view/View;
    //   555: checkcast 452	android/widget/ImageView
    //   558: getstatic 469	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$drawable:romanblack_photogallery_placeholder	I
    //   561: invokevirtual 472	android/widget/ImageView:setImageResource	(I)V
    //   564: aload_0
    //   565: getfield 78	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:image	Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem;
    //   568: invokevirtual 464	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/ImageItem:getImageUrl	()Ljava/lang/String;
    //   571: invokevirtual 475	java/lang/String:length	()I
    //   574: ifne +22 -> 596
    //   577: aload_0
    //   578: getfield 88	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:headerView	Landroid/view/View;
    //   581: getstatic 450	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_comments_listview_header_thumb	I
    //   584: invokevirtual 432	android/view/View:findViewById	(I)Landroid/view/View;
    //   587: checkcast 452	android/widget/ImageView
    //   590: getstatic 469	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$drawable:romanblack_photogallery_placeholder	I
    //   593: invokevirtual 472	android/widget/ImageView:setImageResource	(I)V
    //   596: aload_0
    //   597: getfield 86	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:header	Landroid/widget/LinearLayout;
    //   600: aload_0
    //   601: getfield 88	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:headerView	Landroid/view/View;
    //   604: new 219	android/widget/LinearLayout$LayoutParams
    //   607: dup
    //   608: iconst_m1
    //   609: bipush 254
    //   611: invokespecial 222	android/widget/LinearLayout$LayoutParams:<init>	(II)V
    //   614: invokevirtual 479	android/widget/LinearLayout:addView	(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V
    //   617: return
    //   618: astore 24
    //   620: ldc 70
    //   622: ldc 70
    //   624: invokestatic 195	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   627: pop
    //   628: goto -32 -> 596
    //   631: aload_0
    //   632: aload_0
    //   633: getstatic 482	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$string:romanblack_photogallery_reply	I
    //   636: invokevirtual 410	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:getString	(I)Ljava/lang/String;
    //   639: invokevirtual 413	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:setTopBarTitle	(Ljava/lang/String;)V
    //   642: aload_0
    //   643: aload_0
    //   644: invokestatic 419	android/view/LayoutInflater:from	(Landroid/content/Context;)Landroid/view/LayoutInflater;
    //   647: getstatic 485	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$layout:romanblack_photogallery_commentstocomments_header	I
    //   650: aconst_null
    //   651: invokevirtual 426	android/view/LayoutInflater:inflate	(ILandroid/view/ViewGroup;)Landroid/view/View;
    //   654: putfield 88	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:headerView	Landroid/view/View;
    //   657: aload_0
    //   658: getfield 88	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:headerView	Landroid/view/View;
    //   661: getstatic 488	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_commentstocomment_listview_header_name	I
    //   664: invokevirtual 432	android/view/View:findViewById	(I)Landroid/view/View;
    //   667: checkcast 253	android/widget/TextView
    //   670: astore 9
    //   672: aload 9
    //   674: aload_0
    //   675: getfield 74	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:message	Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/entities/CommentItem;
    //   678: invokevirtual 491	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/CommentItem:getAuthor	()Ljava/lang/String;
    //   681: invokevirtual 257	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   684: aload 9
    //   686: getstatic 438	com/ibuildapp/romanblack/PhotoGalleryPlugin/Statics:color3	I
    //   689: invokevirtual 367	android/widget/TextView:setTextColor	(I)V
    //   692: aload_0
    //   693: getfield 88	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:headerView	Landroid/view/View;
    //   696: getstatic 494	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_commentstocomment_listview_header_text	I
    //   699: invokevirtual 432	android/view/View:findViewById	(I)Landroid/view/View;
    //   702: checkcast 253	android/widget/TextView
    //   705: astore 10
    //   707: aload 10
    //   709: aload_0
    //   710: getfield 74	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:message	Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/entities/CommentItem;
    //   713: invokevirtual 497	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/CommentItem:getText	()Ljava/lang/String;
    //   716: invokevirtual 257	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   719: aload 10
    //   721: getstatic 447	com/ibuildapp/romanblack/PhotoGalleryPlugin/Statics:color4	I
    //   724: invokevirtual 367	android/widget/TextView:setTextColor	(I)V
    //   727: ldc 70
    //   729: astore 11
    //   731: aload_0
    //   732: getfield 74	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:message	Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/entities/CommentItem;
    //   735: invokevirtual 501	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/CommentItem:getDate	()Ljava/util/Date;
    //   738: invokevirtual 507	java/util/Date:getTime	()J
    //   741: lstore 13
    //   743: invokestatic 512	java/lang/System:currentTimeMillis	()J
    //   746: lload 13
    //   748: lsub
    //   749: lstore 15
    //   751: lload 15
    //   753: ldc2_w 513
    //   756: lcmp
    //   757: ifge +84 -> 841
    //   760: aload_0
    //   761: invokevirtual 205	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:getResources	()Landroid/content/res/Resources;
    //   764: getstatic 517	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$string:romanblack_photogallery_date_just_now	I
    //   767: invokevirtual 280	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   770: astore 17
    //   772: aload 17
    //   774: astore 11
    //   776: aload_0
    //   777: getfield 88	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:headerView	Landroid/view/View;
    //   780: getstatic 520	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_commentstocomment_listview_header_date	I
    //   783: invokevirtual 432	android/view/View:findViewById	(I)Landroid/view/View;
    //   786: checkcast 253	android/widget/TextView
    //   789: aload 11
    //   791: invokevirtual 257	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   794: aload_0
    //   795: getfield 88	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:headerView	Landroid/view/View;
    //   798: getstatic 523	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$id:romanblack_photogallery_commentstocomment_listview_header_thumb	I
    //   801: invokevirtual 432	android/view/View:findViewById	(I)Landroid/view/View;
    //   804: checkcast 452	android/widget/ImageView
    //   807: aload_0
    //   808: aload_0
    //   809: getfield 74	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:message	Lcom/ibuildapp/romanblack/PhotoGalleryPlugin/entities/CommentItem;
    //   812: invokevirtual 526	com/ibuildapp/romanblack/PhotoGalleryPlugin/entities/CommentItem:getAvatarPath	()Ljava/lang/String;
    //   815: bipush 70
    //   817: bipush 70
    //   819: invokespecial 457	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:decodeImageFile	(Ljava/lang/String;II)Landroid/graphics/Bitmap;
    //   822: invokevirtual 461	android/widget/ImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   825: goto -229 -> 596
    //   828: astore 7
    //   830: ldc 70
    //   832: ldc 70
    //   834: invokestatic 195	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   837: pop
    //   838: goto -242 -> 596
    //   841: lload 15
    //   843: ldc2_w 527
    //   846: lcmp
    //   847: ifge +18 -> 865
    //   850: aload_0
    //   851: invokevirtual 205	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:getResources	()Landroid/content/res/Resources;
    //   854: getstatic 531	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$string:romanblack_photogallery_date_one_minute	I
    //   857: invokevirtual 280	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   860: astore 11
    //   862: goto -86 -> 776
    //   865: lload 15
    //   867: ldc2_w 532
    //   870: lcmp
    //   871: ifge +50 -> 921
    //   874: new 231	java/lang/StringBuilder
    //   877: dup
    //   878: invokespecial 232	java/lang/StringBuilder:<init>	()V
    //   881: lload 15
    //   883: ldc2_w 534
    //   886: ldiv
    //   887: ldc2_w 536
    //   890: ldiv
    //   891: invokevirtual 540	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   894: ldc_w 542
    //   897: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   900: aload_0
    //   901: invokevirtual 205	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:getResources	()Landroid/content/res/Resources;
    //   904: getstatic 545	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$string:romanblack_photogallery_date_minutes	I
    //   907: invokevirtual 280	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   910: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   913: invokevirtual 251	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   916: astore 11
    //   918: goto -142 -> 776
    //   921: lload 15
    //   923: ldc2_w 546
    //   926: lcmp
    //   927: ifge +18 -> 945
    //   930: aload_0
    //   931: invokevirtual 205	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:getResources	()Landroid/content/res/Resources;
    //   934: getstatic 550	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$string:romanblack_photogallery_date_one_hour	I
    //   937: invokevirtual 280	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   940: astore 11
    //   942: goto -166 -> 776
    //   945: lload 15
    //   947: ldc2_w 551
    //   950: lcmp
    //   951: ifge +54 -> 1005
    //   954: new 231	java/lang/StringBuilder
    //   957: dup
    //   958: invokespecial 232	java/lang/StringBuilder:<init>	()V
    //   961: lload 15
    //   963: ldc2_w 534
    //   966: ldiv
    //   967: ldc2_w 534
    //   970: ldiv
    //   971: ldc2_w 536
    //   974: ldiv
    //   975: invokevirtual 540	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   978: ldc_w 542
    //   981: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   984: aload_0
    //   985: invokevirtual 205	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:getResources	()Landroid/content/res/Resources;
    //   988: getstatic 555	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$string:romanblack_photogallery_date_hours	I
    //   991: invokevirtual 280	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   994: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   997: invokevirtual 251	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1000: astore 11
    //   1002: goto -226 -> 776
    //   1005: lload 15
    //   1007: ldc2_w 556
    //   1010: lcmp
    //   1011: ifge +18 -> 1029
    //   1014: aload_0
    //   1015: invokevirtual 205	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:getResources	()Landroid/content/res/Resources;
    //   1018: getstatic 560	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$string:romanblack_photogallery_date_yesterday	I
    //   1021: invokevirtual 280	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   1024: astore 11
    //   1026: goto -250 -> 776
    //   1029: lload 15
    //   1031: ldc2_w 561
    //   1034: lcmp
    //   1035: ifge +62 -> 1097
    //   1038: new 231	java/lang/StringBuilder
    //   1041: dup
    //   1042: invokespecial 232	java/lang/StringBuilder:<init>	()V
    //   1045: lload 15
    //   1047: ldc2_w 563
    //   1050: ldiv
    //   1051: ldc2_w 534
    //   1054: ldiv
    //   1055: ldc2_w 534
    //   1058: ldiv
    //   1059: ldc2_w 536
    //   1062: ldiv
    //   1063: invokevirtual 540	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1066: ldc_w 542
    //   1069: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1072: aload_0
    //   1073: invokevirtual 205	com/ibuildapp/romanblack/PhotoGalleryPlugin/SendMessageActivity:getResources	()Landroid/content/res/Resources;
    //   1076: getstatic 567	com/ibuildapp/romanblack/PhotoGalleryPlugin/R$string:romanblack_photogallery_date_days	I
    //   1079: invokevirtual 280	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   1082: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1085: invokevirtual 251	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1088: astore 18
    //   1090: aload 18
    //   1092: astore 11
    //   1094: goto -318 -> 776
    //   1097: new 569	java/text/SimpleDateFormat
    //   1100: dup
    //   1101: ldc_w 571
    //   1104: invokespecial 572	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   1107: astore 19
    //   1109: new 503	java/util/Date
    //   1112: dup
    //   1113: lload 15
    //   1115: invokespecial 575	java/util/Date:<init>	(J)V
    //   1118: astore 20
    //   1120: aload 19
    //   1122: aload 20
    //   1124: invokevirtual 579	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   1127: astore 23
    //   1129: aload 23
    //   1131: astore 11
    //   1133: goto -357 -> 776
    //   1136: astore 21
    //   1138: ldc 70
    //   1140: ldc 70
    //   1142: invokestatic 195	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   1145: pop
    //   1146: goto -370 -> 776
    //   1149: astore 12
    //   1151: goto -375 -> 776
    //
    // Exception table:
    //   from	to	target	type
    //   419	564	618	java/lang/Exception
    //   564	596	618	java/lang/Exception
    //   642	727	828	java/lang/Exception
    //   776	825	828	java/lang/Exception
    //   1097	1129	1136	java/lang/Exception
    //   731	751	1149	java/lang/Exception
    //   760	772	1149	java/lang/Exception
    //   850	862	1149	java/lang/Exception
    //   874	918	1149	java/lang/Exception
    //   930	942	1149	java/lang/Exception
    //   954	1002	1149	java/lang/Exception
    //   1014	1026	1149	java/lang/Exception
    //   1038	1090	1149	java/lang/Exception
    //   1138	1146	1149	java/lang/Exception
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 10000)
      if (paramInt2 == -1)
      {
        this.imagePath = paramIntent.getStringExtra("imagePath");
        if (this.imagePath != null)
          break label38;
      }
    label38: 
    do
    {
      do
      {
        do
          return;
        while (this.imagePath.length() != 0);
        return;
      }
      while ((paramInt1 != 10001) || (paramInt2 != -1));
      Uri localUri = paramIntent.getData();
      String[] arrayOfString = { "_data" };
      Cursor localCursor = getContentResolver().query(localUri, arrayOfString, null, null, null);
      localCursor.moveToFirst();
      String str = localCursor.getString(localCursor.getColumnIndex(arrayOfString[0]));
      localCursor.close();
      this.imagePath = str;
    }
    while ((this.imagePath == null) || (this.imagePath.length() == 0) || (!this.imagePath.startsWith("http")));
    Toast.makeText(this, getString(R.string.romanblack_photogallery_alert_image_cannot_selected), 1).show();
  }

  public void onBackPressed()
  {
    finish();
  }

  public void onClick(View paramView)
  {
    if (!this.uploading)
    {
      if (paramView.getId() != R.id.romanblack_photogallery_sendmessage_clear_btn)
        break label27;
      this.messageEditText.setText("");
    }
    label27: 
    do
      return;
    while (paramView.getId() != R.id.romanblack_photogallery_sendmessage_post_btn);
    if (this.messageEditText.getText().length() < 1)
      Toast.makeText(this, getString(R.string.romanblack_photogallery_dialog_sure_save_image), 1).show();
    this.progressBar.setVisibility(0);
    this.uploading = true;
    if (this.messageEditText.getText().length() > 150)
    {
      Toast.makeText(this, getString(R.string.romanblack_photogallery_alert_big_text), 1).show();
      this.uploading = false;
      this.progressBar.setVisibility(4);
      return;
    }
    if (this.messageEditText.getText().length() == 0)
    {
      Toast.makeText(this, getString(R.string.romanblack_photogallery_alert_empty_message), 1).show();
      this.uploading = false;
      this.progressBar.setVisibility(4);
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        BasicHttpParams localBasicHttpParams = new BasicHttpParams();
        localBasicHttpParams.setParameter("http.protocol.version", HttpVersion.HTTP_1_1);
        DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(localBasicHttpParams);
        while (true)
        {
          MultipartEntity localMultipartEntity;
          try
          {
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append(Statics.BASE_URL);
            localStringBuilder.append("/");
            HttpPost localHttpPost = new HttpPost(localStringBuilder.toString());
            localMultipartEntity = new MultipartEntity();
            localMultipartEntity.addPart("action", new StringBody("postcomment", Charset.forName("UTF-8")));
            localMultipartEntity.addPart("app_id", new StringBody(com.appbuilder.sdk.android.Statics.appId, Charset.forName("UTF-8")));
            localMultipartEntity.addPart("token", new StringBody(com.appbuilder.sdk.android.Statics.appToken, Charset.forName("UTF-8")));
            localMultipartEntity.addPart("module_id", new StringBody(Statics.MODULE_ID, Charset.forName("UTF-8")));
            localMultipartEntity.addPart("parent_id", new StringBody(SendMessageActivity.this.image.getId() + "", Charset.forName("UTF-8")));
            if (SendMessageActivity.this.message == null)
              continue;
            localMultipartEntity.addPart("reply_id", new StringBody(SendMessageActivity.this.message.getId() + "", Charset.forName("UTF-8")));
            if (Authorization.getAuthorizedUser().getAccountType() != User.ACCOUNT_TYPES.FACEBOOK)
              continue;
            localMultipartEntity.addPart("account_type", new StringBody("facebook", Charset.forName("UTF-8")));
            localMultipartEntity.addPart("account_id", new StringBody(Authorization.getAuthorizedUser().getAccountId(), Charset.forName("UTF-8")));
            localMultipartEntity.addPart("username", new StringBody(Authorization.getAuthorizedUser().getUserName(), Charset.forName("UTF-8")));
            localMultipartEntity.addPart("avatar", new StringBody(Authorization.getAuthorizedUser().getAvatarUrl(), Charset.forName("UTF-8")));
            localMultipartEntity.addPart("text", new StringBody(SendMessageActivity.this.messageEditText.getText().toString(), Charset.forName("UTF-8")));
            localHttpPost.setEntity(localMultipartEntity);
            Statics.onPost();
            String str1 = (String)localDefaultHttpClient.execute(localHttpPost, new BasicResponseHandler());
            SendMessageActivity.access$502(SendMessageActivity.this, (CommentItem)JSONParser.parseCommentsString(str1).get(0));
            if (SendMessageActivity.this.message != null)
              break label700;
            localObject = Statics.BASE_URL + "/getcomments/" + com.appbuilder.sdk.android.Statics.appId + "/" + Statics.MODULE_ID + "/" + SendMessageActivity.this.image.getId() + "/0/" + com.appbuilder.sdk.android.Statics.appId + "/" + com.appbuilder.sdk.android.Statics.appToken;
            ArrayList localArrayList = JSONParser.parseCommentsUrl((String)localObject);
            if ((localArrayList == null) || (localArrayList.isEmpty()))
              continue;
            Statics.onCommentsUpdate(SendMessageActivity.this.image, SendMessageActivity.this.message, localArrayList.size(), 0, localArrayList);
            Log.d("", "");
            SendMessageActivity.this.handler.sendEmptyMessage(0);
            return;
            if (Authorization.getAuthorizedUser().getAccountType() == User.ACCOUNT_TYPES.TWITTER)
            {
              localMultipartEntity.addPart("account_type", new StringBody("twitter", Charset.forName("UTF-8")));
              continue;
            }
          }
          catch (Exception localException)
          {
            Log.d("", "");
            SendMessageActivity.this.handler.sendEmptyMessage(1);
            return;
          }
          localMultipartEntity.addPart("account_type", new StringBody("ibuildapp", Charset.forName("UTF-8")));
          continue;
          label700: String str2 = Statics.BASE_URL + "/getcomments/" + com.appbuilder.sdk.android.Statics.appId + "/" + Statics.MODULE_ID + "/" + SendMessageActivity.this.image.getId() + "/" + SendMessageActivity.this.message.getId() + "/" + com.appbuilder.sdk.android.Statics.appId + "/" + com.appbuilder.sdk.android.Statics.appToken;
          Object localObject = str2;
        }
      }
    }).start();
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    Log.d("", "");
  }

  public boolean onEditorAction(TextView paramTextView, int paramInt, KeyEvent paramKeyEvent)
  {
    return false;
  }

  public void onGlobalLayout()
  {
    if (this.root.getRootView().getHeight() - this.root.getHeight() > 100)
    {
      keyboardShown();
      Log.d("", "");
      return;
    }
    keyboardHidden();
    Log.d("", "");
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.PhotoGalleryPlugin.SendMessageActivity
 * JD-Core Version:    0.6.0
 */