package org.apache.commons.logging.impl;

import java.util.Enumeration;

class WeakHashtable$1
  implements Enumeration
{
  private final WeakHashtable this$0;
  private final Enumeration val$enumer;

  public boolean hasMoreElements()
  {
    return this.val$enumer.hasMoreElements();
  }

  public Object nextElement()
  {
    return WeakHashtable.Referenced.access$100((WeakHashtable.Referenced)this.val$enumer.nextElement());
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.logging.impl.WeakHashtable.1
 * JD-Core Version:    0.6.0
 */