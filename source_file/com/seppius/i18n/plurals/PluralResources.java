package com.seppius.i18n.plurals;

import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

public class PluralResources
{
  private Method getResourceBagTextMethod;
  private String language;
  private Resources resources;
  private PluralRules rules;
  private boolean treatZero = true;

  public PluralResources(Resources paramResources)
    throws SecurityException, NoSuchMethodException
  {
    this.resources = paramResources;
    Class localClass = paramResources.getAssets().getClass();
    Class[] arrayOfClass = new Class[2];
    arrayOfClass[0] = Integer.TYPE;
    arrayOfClass[1] = Integer.TYPE;
    this.getResourceBagTextMethod = localClass.getDeclaredMethod("getResourceBagText", arrayOfClass);
    this.getResourceBagTextMethod.setAccessible(true);
  }

  public String getQuantityString(int paramInt1, int paramInt2)
    throws Resources.NotFoundException
  {
    Locale localLocale = this.resources.getConfiguration().locale;
    if (!localLocale.getLanguage().equals(this.language))
    {
      this.language = localLocale.getLanguage();
      this.rules = PluralRules.ruleForLocale(localLocale);
    }
    if (this.rules == null)
      return this.resources.getQuantityString(paramInt1, paramInt2);
    if (this.getResourceBagTextMethod == null)
      throw new IllegalArgumentException();
    Object localObject1 = null;
    if (paramInt2 == 0);
    try
    {
      boolean bool = this.treatZero;
      localObject1 = null;
      if (bool)
      {
        Method localMethod3 = this.getResourceBagTextMethod;
        AssetManager localAssetManager3 = this.resources.getAssets();
        Object[] arrayOfObject3 = new Object[2];
        arrayOfObject3[0] = Integer.valueOf(paramInt1);
        arrayOfObject3[1] = Integer.valueOf(16777221);
        localObject1 = localMethod3.invoke(localAssetManager3, arrayOfObject3);
      }
      if (localObject1 == null)
      {
        Method localMethod1 = this.getResourceBagTextMethod;
        AssetManager localAssetManager1 = this.resources.getAssets();
        Object[] arrayOfObject1 = new Object[2];
        arrayOfObject1[0] = Integer.valueOf(paramInt1);
        arrayOfObject1[1] = Integer.valueOf(PluralRules.attrForQuantity(this.rules.quantityForNumber(paramInt2)));
        localObject1 = localMethod1.invoke(localAssetManager1, arrayOfObject1);
      }
      if (localObject1 == null)
      {
        Method localMethod2 = this.getResourceBagTextMethod;
        AssetManager localAssetManager2 = this.resources.getAssets();
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = Integer.valueOf(paramInt1);
        arrayOfObject2[1] = Integer.valueOf(16777220);
        Object localObject2 = localMethod2.invoke(localAssetManager2, arrayOfObject2);
        localObject1 = localObject2;
      }
      if (localObject1 == null)
        throw new Resources.NotFoundException("Plural resource ID #0x" + Integer.toHexString(paramInt1) + " quantity=" + paramInt2 + " item=" + PluralRules.stringForQuantity(this.rules.quantityForNumber(paramInt2)));
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      throw new Resources.NotFoundException(localIllegalArgumentException.getMessage());
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new Resources.NotFoundException(localIllegalAccessException.getMessage());
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new Resources.NotFoundException(localInvocationTargetException.getMessage());
    }
    return localObject1.toString();
  }

  public String getQuantityString(int paramInt1, int paramInt2, Object[] paramArrayOfObject)
    throws Resources.NotFoundException
  {
    return String.format(this.resources.getConfiguration().locale, getQuantityString(paramInt1, paramInt2), paramArrayOfObject);
  }

  public void setTreatZero(boolean paramBoolean)
  {
    this.treatZero = paramBoolean;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.seppius.i18n.plurals.PluralResources
 * JD-Core Version:    0.6.0
 */