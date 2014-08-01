package com.ibuildapp.romanblack.VideoPlugin.callbacks;

import com.ibuildapp.romanblack.VideoPlugin.CommentItem;
import com.ibuildapp.romanblack.VideoPlugin.VideoItem;
import java.util.ArrayList;

public abstract interface OnCommentPushedListener
{
  public abstract void onCommentPushed(CommentItem paramCommentItem);

  public abstract void onCommentsUpdate(VideoItem paramVideoItem, CommentItem paramCommentItem, int paramInt1, int paramInt2, ArrayList<CommentItem> paramArrayList);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.VideoPlugin.callbacks.OnCommentPushedListener
 * JD-Core Version:    0.6.0
 */