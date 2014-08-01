package com.ibuildapp.romanblack.PhotoGalleryPlugin.callback;

import com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.CommentItem;
import com.ibuildapp.romanblack.PhotoGalleryPlugin.entities.ImageItem;
import java.util.ArrayList;

public abstract interface OnCommentPushedListener
{
  public abstract void onCommentPushed(CommentItem paramCommentItem);

  public abstract void onCommentsUpdate(ImageItem paramImageItem, CommentItem paramCommentItem, int paramInt1, int paramInt2, ArrayList<CommentItem> paramArrayList);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.PhotoGalleryPlugin.callback.OnCommentPushedListener
 * JD-Core Version:    0.6.0
 */