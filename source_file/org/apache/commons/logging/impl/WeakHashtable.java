package org.apache.commons.logging.impl;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class WeakHashtable extends Hashtable
{
  private static final int MAX_CHANGES_BEFORE_PURGE = 100;
  private static final int PARTIAL_PURGE_COUNT = 10;
  private int changeCount = 0;
  private ReferenceQueue queue = new ReferenceQueue();

  private void purge()
  {
    synchronized (this.queue)
    {
      WeakKey localWeakKey = (WeakKey)this.queue.poll();
      if (localWeakKey != null)
        super.remove(localWeakKey.getReferenced());
    }
    monitorexit;
  }

  private void purgeOne()
  {
    synchronized (this.queue)
    {
      WeakKey localWeakKey = (WeakKey)this.queue.poll();
      if (localWeakKey != null)
        super.remove(localWeakKey.getReferenced());
      return;
    }
  }

  public boolean containsKey(Object paramObject)
  {
    return super.containsKey(new Referenced(paramObject, null));
  }

  public Enumeration elements()
  {
    purge();
    return super.elements();
  }

  public Set entrySet()
  {
    purge();
    Set localSet = super.entrySet();
    HashSet localHashSet = new HashSet();
    Iterator localIterator = localSet.iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Object localObject1 = ((Referenced)localEntry.getKey()).getValue();
      Object localObject2 = localEntry.getValue();
      if (localObject1 == null)
        continue;
      localHashSet.add(new Entry(localObject1, localObject2, null));
    }
    return localHashSet;
  }

  public Object get(Object paramObject)
  {
    return super.get(new Referenced(paramObject, null));
  }

  public boolean isEmpty()
  {
    purge();
    return super.isEmpty();
  }

  public Set keySet()
  {
    purge();
    Set localSet = super.keySet();
    HashSet localHashSet = new HashSet();
    Iterator localIterator = localSet.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = ((Referenced)localIterator.next()).getValue();
      if (localObject == null)
        continue;
      localHashSet.add(localObject);
    }
    return localHashSet;
  }

  public Enumeration keys()
  {
    purge();
    return new WeakHashtable.1(this, super.keys());
  }

  public Object put(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == null)
      throw new NullPointerException("Null keys are not allowed");
    if (paramObject2 == null)
      throw new NullPointerException("Null values are not allowed");
    int i = this.changeCount;
    this.changeCount = (i + 1);
    if (i > 100)
    {
      purge();
      this.changeCount = 0;
    }
    while (true)
    {
      return super.put(new Referenced(paramObject1, this.queue, null), paramObject2);
      if (this.changeCount % 10 != 0)
        continue;
      purgeOne();
    }
  }

  public void putAll(Map paramMap)
  {
    if (paramMap != null)
    {
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        put(localEntry.getKey(), localEntry.getValue());
      }
    }
  }

  protected void rehash()
  {
    purge();
    super.rehash();
  }

  public Object remove(Object paramObject)
  {
    int i = this.changeCount;
    this.changeCount = (i + 1);
    if (i > 100)
    {
      purge();
      this.changeCount = 0;
    }
    while (true)
    {
      return super.remove(new Referenced(paramObject, null));
      if (this.changeCount % 10 != 0)
        continue;
      purgeOne();
    }
  }

  public int size()
  {
    purge();
    return super.size();
  }

  public String toString()
  {
    purge();
    return super.toString();
  }

  public Collection values()
  {
    purge();
    return super.values();
  }

  private static final class Entry
    implements Map.Entry
  {
    private final Object key;
    private final Object value;

    private Entry(Object paramObject1, Object paramObject2)
    {
      this.key = paramObject1;
      this.value = paramObject2;
    }

    Entry(Object paramObject1, Object paramObject2, WeakHashtable.1 param1)
    {
      this(paramObject1, paramObject2);
    }

    public boolean equals(Object paramObject)
    {
      int i = 0;
      Map.Entry localEntry;
      if (paramObject != null)
      {
        boolean bool = paramObject instanceof Map.Entry;
        i = 0;
        if (bool)
        {
          localEntry = (Map.Entry)paramObject;
          if (getKey() != null)
            break label61;
          if (localEntry.getKey() != null)
            break label78;
          if (getValue() != null)
            break label80;
          if (localEntry.getValue() != null)
            break label78;
        }
      }
      while (true)
      {
        i = 1;
        return i;
        label61: if (getKey().equals(localEntry.getKey()))
          break;
        label78: label80: 
        do
          return false;
        while (!getValue().equals(localEntry.getValue()));
      }
    }

    public Object getKey()
    {
      return this.key;
    }

    public Object getValue()
    {
      return this.value;
    }

    public int hashCode()
    {
      int i;
      int j;
      if (getKey() == null)
      {
        i = 0;
        Object localObject = getValue();
        j = 0;
        if (localObject != null)
          break label35;
      }
      while (true)
      {
        return i ^ j;
        i = getKey().hashCode();
        break;
        label35: j = getValue().hashCode();
      }
    }

    public Object setValue(Object paramObject)
    {
      throw new UnsupportedOperationException("Entry.setValue is not supported.");
    }
  }

  private static final class Referenced
  {
    private final int hashCode;
    private final WeakReference reference;

    private Referenced(Object paramObject)
    {
      this.reference = new WeakReference(paramObject);
      this.hashCode = paramObject.hashCode();
    }

    private Referenced(Object paramObject, ReferenceQueue paramReferenceQueue)
    {
      this.reference = new WeakHashtable.WeakKey(paramObject, paramReferenceQueue, this, null);
      this.hashCode = paramObject.hashCode();
    }

    Referenced(Object paramObject, ReferenceQueue paramReferenceQueue, WeakHashtable.1 param1)
    {
      this(paramObject, paramReferenceQueue);
    }

    Referenced(Object paramObject, WeakHashtable.1 param1)
    {
      this(paramObject);
    }

    private Object getValue()
    {
      return this.reference.get();
    }

    public boolean equals(Object paramObject)
    {
      boolean bool = paramObject instanceof Referenced;
      int i = 0;
      Referenced localReferenced;
      Object localObject1;
      Object localObject2;
      if (bool)
      {
        localReferenced = (Referenced)paramObject;
        localObject1 = getValue();
        localObject2 = localReferenced.getValue();
        if (localObject1 != null)
          break label70;
        if (localObject2 != null)
          break label63;
      }
      label63: for (i = 1; ; i = 0)
      {
        if (i == 1)
        {
          if (hashCode() != localReferenced.hashCode())
            break;
          i = 1;
        }
        return i;
      }
      return false;
      label70: return localObject1.equals(localObject2);
    }

    public int hashCode()
    {
      return this.hashCode;
    }
  }

  private static final class WeakKey extends WeakReference
  {
    private final WeakHashtable.Referenced referenced;

    private WeakKey(Object paramObject, ReferenceQueue paramReferenceQueue, WeakHashtable.Referenced paramReferenced)
    {
      super(paramReferenceQueue);
      this.referenced = paramReferenced;
    }

    WeakKey(Object paramObject, ReferenceQueue paramReferenceQueue, WeakHashtable.Referenced paramReferenced, WeakHashtable.1 param1)
    {
      this(paramObject, paramReferenceQueue, paramReferenced);
    }

    private WeakHashtable.Referenced getReferenced()
    {
      return this.referenced;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.logging.impl.WeakHashtable
 * JD-Core Version:    0.6.0
 */