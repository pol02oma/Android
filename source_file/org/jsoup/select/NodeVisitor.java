package org.jsoup.select;

import org.jsoup.nodes.Node;

public abstract interface NodeVisitor
{
  public abstract void head(Node paramNode, int paramInt);

  public abstract void tail(Node paramNode, int paramInt);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.jsoup.select.NodeVisitor
 * JD-Core Version:    0.6.0
 */