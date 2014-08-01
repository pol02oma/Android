package org.apache.james.mime4j.message;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.apache.james.mime4j.dom.Entity;
import org.apache.james.mime4j.dom.Multipart;

public abstract class AbstractMultipart
  implements Multipart
{
  protected List<Entity> bodyParts = new LinkedList();
  private Entity parent = null;
  private String subType;

  public AbstractMultipart(String paramString)
  {
    this.subType = paramString;
  }

  public void addBodyPart(Entity paramEntity)
  {
    if (paramEntity == null)
      throw new IllegalArgumentException();
    this.bodyParts.add(paramEntity);
    paramEntity.setParent(this.parent);
  }

  public void addBodyPart(Entity paramEntity, int paramInt)
  {
    if (paramEntity == null)
      throw new IllegalArgumentException();
    this.bodyParts.add(paramInt, paramEntity);
    paramEntity.setParent(this.parent);
  }

  public void dispose()
  {
    Iterator localIterator = this.bodyParts.iterator();
    while (localIterator.hasNext())
      ((Entity)localIterator.next()).dispose();
  }

  public List<Entity> getBodyParts()
  {
    return Collections.unmodifiableList(this.bodyParts);
  }

  public int getCount()
  {
    return this.bodyParts.size();
  }

  public abstract String getEpilogue();

  public Entity getParent()
  {
    return this.parent;
  }

  public abstract String getPreamble();

  public String getSubType()
  {
    return this.subType;
  }

  public Entity removeBodyPart(int paramInt)
  {
    Entity localEntity = (Entity)this.bodyParts.remove(paramInt);
    localEntity.setParent(null);
    return localEntity;
  }

  public Entity replaceBodyPart(Entity paramEntity, int paramInt)
  {
    if (paramEntity == null)
      throw new IllegalArgumentException();
    Entity localEntity = (Entity)this.bodyParts.set(paramInt, paramEntity);
    if (paramEntity == localEntity)
      throw new IllegalArgumentException("Cannot replace body part with itself");
    paramEntity.setParent(this.parent);
    localEntity.setParent(null);
    return localEntity;
  }

  public void setBodyParts(List<Entity> paramList)
  {
    this.bodyParts = paramList;
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
      ((Entity)localIterator.next()).setParent(this.parent);
  }

  public abstract void setEpilogue(String paramString);

  public void setParent(Entity paramEntity)
  {
    this.parent = paramEntity;
    Iterator localIterator = this.bodyParts.iterator();
    while (localIterator.hasNext())
      ((Entity)localIterator.next()).setParent(paramEntity);
  }

  public abstract void setPreamble(String paramString);

  public void setSubType(String paramString)
  {
    this.subType = paramString;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.message.AbstractMultipart
 * JD-Core Version:    0.6.0
 */