package io.vov.vitamio;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;

public class ThumbnailUtils
{
  private static final int OPTIONS_NONE = 0;
  public static final int OPTIONS_RECYCLE_INPUT = 2;
  private static final int OPTIONS_SCALE_UP = 1;
  public static final int TARGET_SIZE_MICRO_THUMBNAIL_HEIGHT = 160;
  public static final int TARGET_SIZE_MICRO_THUMBNAIL_WIDTH = 212;
  public static final int TARGET_SIZE_MINI_THUMBNAIL_HEIGHT = 320;
  public static final int TARGET_SIZE_MINI_THUMBNAIL_WIDTH = 426;

  // ERROR //
  public static Bitmap createVideoThumbnail(android.content.Context paramContext, java.lang.String paramString, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 35	io/vov/vitamio/Vitamio:isInitialized	(Landroid/content/Context;)Z
    //   4: ifne +9 -> 13
    //   7: aconst_null
    //   8: astore 7
    //   10: aload 7
    //   12: areturn
    //   13: aconst_null
    //   14: astore_3
    //   15: new 37	io/vov/vitamio/MediaMetadataRetriever
    //   18: dup
    //   19: aload_0
    //   20: invokespecial 40	io/vov/vitamio/MediaMetadataRetriever:<init>	(Landroid/content/Context;)V
    //   23: astore 4
    //   25: aload 4
    //   27: aload_1
    //   28: invokevirtual 44	io/vov/vitamio/MediaMetadataRetriever:setDataSource	(Ljava/lang/String;)V
    //   31: aload 4
    //   33: ldc2_w 45
    //   36: invokevirtual 50	io/vov/vitamio/MediaMetadataRetriever:getFrameAtTime	(J)Landroid/graphics/Bitmap;
    //   39: astore 10
    //   41: aload 10
    //   43: astore 7
    //   45: aload 4
    //   47: invokevirtual 53	io/vov/vitamio/MediaMetadataRetriever:release	()V
    //   50: aload 7
    //   52: ifnull -42 -> 10
    //   55: iload_2
    //   56: iconst_3
    //   57: if_icmpne +50 -> 107
    //   60: aload 7
    //   62: sipush 212
    //   65: sipush 160
    //   68: iconst_2
    //   69: invokestatic 57	io/vov/vitamio/ThumbnailUtils:extractThumbnail	(Landroid/graphics/Bitmap;III)Landroid/graphics/Bitmap;
    //   72: areturn
    //   73: astore 11
    //   75: goto -25 -> 50
    //   78: astore 12
    //   80: aload_3
    //   81: invokevirtual 53	io/vov/vitamio/MediaMetadataRetriever:release	()V
    //   84: aconst_null
    //   85: astore 7
    //   87: goto -37 -> 50
    //   90: astore 6
    //   92: aconst_null
    //   93: astore 7
    //   95: goto -45 -> 50
    //   98: astore 8
    //   100: aload_3
    //   101: invokevirtual 53	io/vov/vitamio/MediaMetadataRetriever:release	()V
    //   104: aload 8
    //   106: athrow
    //   107: iload_2
    //   108: iconst_1
    //   109: if_icmpne -99 -> 10
    //   112: aload 7
    //   114: sipush 426
    //   117: sipush 320
    //   120: iconst_2
    //   121: invokestatic 57	io/vov/vitamio/ThumbnailUtils:extractThumbnail	(Landroid/graphics/Bitmap;III)Landroid/graphics/Bitmap;
    //   124: areturn
    //   125: astore 9
    //   127: goto -23 -> 104
    //   130: astore 8
    //   132: aload 4
    //   134: astore_3
    //   135: goto -35 -> 100
    //   138: astore 5
    //   140: aload 4
    //   142: astore_3
    //   143: goto -63 -> 80
    //
    // Exception table:
    //   from	to	target	type
    //   45	50	73	java/lang/RuntimeException
    //   15	25	78	java/lang/Exception
    //   80	84	90	java/lang/RuntimeException
    //   15	25	98	finally
    //   100	104	125	java/lang/RuntimeException
    //   25	41	130	finally
    //   25	41	138	java/lang/Exception
  }

  public static Bitmap extractThumbnail(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    return extractThumbnail(paramBitmap, paramInt1, paramInt2, 0);
  }

  public static Bitmap extractThumbnail(Bitmap paramBitmap, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramBitmap == null)
      return null;
    float f;
    if (paramBitmap.getWidth() < paramBitmap.getHeight())
      f = paramInt1 / paramBitmap.getWidth();
    while (true)
    {
      Matrix localMatrix = new Matrix();
      localMatrix.setScale(f, f);
      return transform(localMatrix, paramBitmap, paramInt1, paramInt2, paramInt3 | 0x1);
      f = paramInt2 / paramBitmap.getHeight();
    }
  }

  private static Bitmap transform(Matrix paramMatrix, Bitmap paramBitmap, int paramInt1, int paramInt2, int paramInt3)
  {
    int i;
    if ((paramInt3 & 0x1) != 0)
    {
      i = 1;
      if ((paramInt3 & 0x2) == 0)
        break label199;
    }
    Bitmap localBitmap2;
    label199: for (int j = 1; ; j = 0)
    {
      int k = paramBitmap.getWidth() - paramInt1;
      int m = paramBitmap.getHeight() - paramInt2;
      if ((i != 0) || ((k >= 0) && (m >= 0)))
        break label205;
      localBitmap2 = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
      Canvas localCanvas = new Canvas(localBitmap2);
      int i2 = Math.max(0, k / 2);
      int i3 = Math.max(0, m / 2);
      Rect localRect1 = new Rect(i2, i3, i2 + Math.min(paramInt1, paramBitmap.getWidth()), i3 + Math.min(paramInt2, paramBitmap.getHeight()));
      int i4 = (paramInt1 - localRect1.width()) / 2;
      int i5 = (paramInt2 - localRect1.height()) / 2;
      Rect localRect2 = new Rect(i4, i5, paramInt1 - i4, paramInt2 - i5);
      localCanvas.drawBitmap(paramBitmap, localRect1, localRect2, null);
      if (j != 0)
        paramBitmap.recycle();
      return localBitmap2;
      i = 0;
      break;
    }
    label205: float f1 = paramBitmap.getWidth();
    float f2 = paramBitmap.getHeight();
    float f3;
    if (f1 / f2 > paramInt1 / paramInt2)
    {
      f3 = paramInt2 / f2;
      label240: if ((f3 >= 0.9F) && (f3 <= 1.0F))
        break label379;
      paramMatrix.setScale(f3, f3);
      label263: if (paramMatrix == null)
        break label384;
    }
    label384: for (Bitmap localBitmap1 = Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), paramMatrix, true); ; localBitmap1 = paramBitmap)
    {
      if ((j != 0) && (localBitmap1 != paramBitmap))
        paramBitmap.recycle();
      int n = Math.max(0, localBitmap1.getWidth() - paramInt1);
      int i1 = Math.max(0, localBitmap1.getHeight() - paramInt2);
      localBitmap2 = Bitmap.createBitmap(localBitmap1, n / 2, i1 / 2, paramInt1, paramInt2);
      if ((localBitmap2 == localBitmap1) || ((j == 0) && (localBitmap1 == paramBitmap)))
        break;
      localBitmap1.recycle();
      return localBitmap2;
      f3 = paramInt1 / f1;
      break label240;
      label379: paramMatrix = null;
      break label263;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     io.vov.vitamio.ThumbnailUtils
 * JD-Core Version:    0.6.0
 */