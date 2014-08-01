package org.apache.http.message;

import java.util.List;
import java.util.NoSuchElementException;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class BasicListHeaderIterator
  implements HeaderIterator
{
  protected final List<Header> allHeaders;
  protected int currentIndex;
  protected String headerName;
  protected int lastIndex;

  public BasicListHeaderIterator(List<Header> paramList, String paramString)
  {
    if (paramList == null)
      throw new IllegalArgumentException("Header list must not be null.");
    this.allHeaders = paramList;
    this.headerName = paramString;
    this.currentIndex = findNext(-1);
    this.lastIndex = -1;
  }

  protected boolean filterHeader(int paramInt)
  {
    if (this.headerName == null)
      return true;
    String str = ((Header)this.allHeaders.get(paramInt)).getName();
    return this.headerName.equalsIgnoreCase(str);
  }

  protected int findNext(int paramInt)
  {
    if (paramInt < -1);
    boolean bool;
    do
    {
      return -1;
      int i = -1 + this.allHeaders.size();
      for (bool = false; (!bool) && (paramInt < i); bool = filterHeader(paramInt))
        paramInt++;
    }
    while (!bool);
    return paramInt;
  }

  public boolean hasNext()
  {
    return this.currentIndex >= 0;
  }

  public final Object next()
    throws NoSuchElementException
  {
    return nextHeader();
  }

  public Header nextHeader()
    throws NoSuchElementException
  {
    int i = this.currentIndex;
    if (i < 0)
      throw new NoSuchElementException("Iteration already finished.");
    this.lastIndex = i;
    this.currentIndex = findNext(i);
    return (Header)this.allHeaders.get(i);
  }

  public void remove()
    throws UnsupportedOperationException
  {
    if (this.lastIndex < 0)
      throw new IllegalStateException("No header to remove.");
    this.allHeaders.remove(this.lastIndex);
    this.lastIndex = -1;
    this.currentIndex = (-1 + this.currentIndex);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.message.BasicListHeaderIterator
 * JD-Core Version:    0.6.0
 */