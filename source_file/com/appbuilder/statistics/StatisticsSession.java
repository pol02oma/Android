package com.appbuilder.statistics;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class StatisticsSession
{
  private ArrayList<StatisticsAction> actions = null;
  private Date endDate = null;
  private Date startDate = null;

  public StatisticsSession(Date paramDate)
  {
    this.startDate = paramDate;
    this.actions = new ArrayList();
  }

  void endSession()
  {
    this.endDate = new Date(System.currentTimeMillis());
  }

  public ArrayList<StatisticsAction> getActions()
  {
    return this.actions;
  }

  public Date getEndDate()
  {
    return this.endDate;
  }

  public Date getStartDate()
  {
    return this.startDate;
  }

  void newAction(String paramString)
  {
    Iterator localIterator = getActions().iterator();
    while (localIterator.hasNext())
    {
      StatisticsAction localStatisticsAction2 = (StatisticsAction)localIterator.next();
      if (!localStatisticsAction2.moduleId.equals(paramString))
        continue;
      localStatisticsAction2.usage = (1L + localStatisticsAction2.usage);
      return;
    }
    StatisticsAction localStatisticsAction1 = new StatisticsAction(paramString);
    localStatisticsAction1.usage = (1L + localStatisticsAction1.usage);
    getActions().add(localStatisticsAction1);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.statistics.StatisticsSession
 * JD-Core Version:    0.6.0
 */