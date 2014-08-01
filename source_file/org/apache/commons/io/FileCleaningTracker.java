package org.apache.commons.io;

import java.io.File;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class FileCleaningTracker
{
  final List<String> deleteFailures = Collections.synchronizedList(new ArrayList());
  volatile boolean exitWhenFinished = false;
  ReferenceQueue<Object> q = new ReferenceQueue();
  Thread reaper;
  final Collection<Tracker> trackers = Collections.synchronizedSet(new HashSet());

  private void addTracker(String paramString, Object paramObject, FileDeleteStrategy paramFileDeleteStrategy)
  {
    monitorenter;
    try
    {
      if (this.exitWhenFinished)
        throw new IllegalStateException("No new trackers can be added once exitWhenFinished() is called");
    }
    finally
    {
      monitorexit;
    }
    if (this.reaper == null)
    {
      this.reaper = new Reaper();
      this.reaper.start();
    }
    this.trackers.add(new Tracker(paramString, paramFileDeleteStrategy, paramObject, this.q));
    monitorexit;
  }

  public void exitWhenFinished()
  {
    monitorenter;
    try
    {
      this.exitWhenFinished = true;
      if (this.reaper != null);
      synchronized (this.reaper)
      {
        this.reaper.interrupt();
        monitorexit;
        return;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public List<String> getDeleteFailures()
  {
    return this.deleteFailures;
  }

  public int getTrackCount()
  {
    return this.trackers.size();
  }

  public void track(File paramFile, Object paramObject)
  {
    track(paramFile, paramObject, (FileDeleteStrategy)null);
  }

  public void track(File paramFile, Object paramObject, FileDeleteStrategy paramFileDeleteStrategy)
  {
    if (paramFile == null)
      throw new NullPointerException("The file must not be null");
    addTracker(paramFile.getPath(), paramObject, paramFileDeleteStrategy);
  }

  public void track(String paramString, Object paramObject)
  {
    track(paramString, paramObject, (FileDeleteStrategy)null);
  }

  public void track(String paramString, Object paramObject, FileDeleteStrategy paramFileDeleteStrategy)
  {
    if (paramString == null)
      throw new NullPointerException("The path must not be null");
    addTracker(paramString, paramObject, paramFileDeleteStrategy);
  }

  private final class Reaper extends Thread
  {
    Reaper()
    {
      super();
      setPriority(10);
      setDaemon(true);
    }

    public void run()
    {
      while ((!FileCleaningTracker.this.exitWhenFinished) || (FileCleaningTracker.this.trackers.size() > 0))
        try
        {
          FileCleaningTracker.Tracker localTracker = (FileCleaningTracker.Tracker)FileCleaningTracker.this.q.remove();
          FileCleaningTracker.this.trackers.remove(localTracker);
          if (!localTracker.delete())
            FileCleaningTracker.this.deleteFailures.add(localTracker.getPath());
          localTracker.clear();
        }
        catch (InterruptedException localInterruptedException)
        {
        }
    }
  }

  private static final class Tracker extends PhantomReference<Object>
  {
    private final FileDeleteStrategy deleteStrategy;
    private final String path;

    Tracker(String paramString, FileDeleteStrategy paramFileDeleteStrategy, Object paramObject, ReferenceQueue<? super Object> paramReferenceQueue)
    {
      super(paramReferenceQueue);
      this.path = paramString;
      if (paramFileDeleteStrategy == null)
        paramFileDeleteStrategy = FileDeleteStrategy.NORMAL;
      this.deleteStrategy = paramFileDeleteStrategy;
    }

    public boolean delete()
    {
      return this.deleteStrategy.deleteQuietly(new File(this.path));
    }

    public String getPath()
    {
      return this.path;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.FileCleaningTracker
 * JD-Core Version:    0.6.0
 */