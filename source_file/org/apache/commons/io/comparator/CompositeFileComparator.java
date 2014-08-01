package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class CompositeFileComparator extends AbstractFileComparator
  implements Serializable
{
  private static final Comparator<?>[] NO_COMPARATORS = new Comparator[0];
  private final Comparator<File>[] delegates;

  public CompositeFileComparator(Iterable<Comparator<File>> paramIterable)
  {
    if (paramIterable == null)
    {
      this.delegates = ((Comparator[])NO_COMPARATORS);
      return;
    }
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramIterable.iterator();
    while (localIterator.hasNext())
      localArrayList.add((Comparator)localIterator.next());
    this.delegates = ((Comparator[])(Comparator[])localArrayList.toArray(new Comparator[localArrayList.size()]));
  }

  public CompositeFileComparator(Comparator<File>[] paramArrayOfComparator)
  {
    if (paramArrayOfComparator == null)
    {
      this.delegates = ((Comparator[])NO_COMPARATORS);
      return;
    }
    this.delegates = ((Comparator[])new Comparator[paramArrayOfComparator.length]);
    System.arraycopy(paramArrayOfComparator, 0, this.delegates, 0, paramArrayOfComparator.length);
  }

  public int compare(File paramFile1, File paramFile2)
  {
    int i = 0;
    Comparator[] arrayOfComparator = this.delegates;
    int j = arrayOfComparator.length;
    for (int k = 0; ; k++)
      if (k < j)
      {
        i = arrayOfComparator[k].compare(paramFile1, paramFile2);
        if (i == 0)
          continue;
      }
      else
      {
        return i;
      }
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(super.toString());
    localStringBuilder.append('{');
    for (int i = 0; i < this.delegates.length; i++)
    {
      if (i > 0)
        localStringBuilder.append(',');
      localStringBuilder.append(this.delegates[i]);
    }
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.comparator.CompositeFileComparator
 * JD-Core Version:    0.6.0
 */