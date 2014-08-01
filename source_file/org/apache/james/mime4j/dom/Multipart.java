package org.apache.james.mime4j.dom;

import java.util.List;

public abstract interface Multipart extends Body
{
  public abstract void addBodyPart(Entity paramEntity);

  public abstract void addBodyPart(Entity paramEntity, int paramInt);

  public abstract List<Entity> getBodyParts();

  public abstract int getCount();

  public abstract String getEpilogue();

  public abstract String getPreamble();

  public abstract String getSubType();

  public abstract Entity removeBodyPart(int paramInt);

  public abstract Entity replaceBodyPart(Entity paramEntity, int paramInt);

  public abstract void setBodyParts(List<Entity> paramList);

  public abstract void setEpilogue(String paramString);

  public abstract void setPreamble(String paramString);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.dom.Multipart
 * JD-Core Version:    0.6.0
 */