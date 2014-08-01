package com.seppius.i18n.plurals;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public abstract class PluralRules
{
  static final int ID_OTHER = 16777220;
  static final int ID_ZERO = 16777221;
  static final int QUANTITY_FEW = 8;
  static final int QUANTITY_MANY = 16;
  static final int QUANTITY_ONE = 2;
  static final int QUANTITY_OTHER = 0;
  static final int QUANTITY_TWO = 4;
  static final int QUANTITY_ZERO = 1;
  private static Map<String, PluralRules> allRules = new HashMap();

  static
  {
    addRules(new String[] { "bem", "brx", "da", "de", "el", "en", "eo", "es", "et", "fi", "fo", "gl", "he", "iw", "it", "nb", "nl", "nn", "no", "sv", "af", "bg", "bn", "ca", "eu", "fur", "fy", "gu", "ha", "is", "ku", "lb", "ml", "mr", "nah", "ne", "om", "or", "pa", "pap", "ps", "so", "sq", "sw", "ta", "te", "tk", "ur", "zu", "mn", "gsw", "chr", "rm", "pt" }, new PluralRules_One());
    addRules(new String[] { "cs", "sk" }, new PluralRules_Czech());
    addRules(new String[] { "ff", "fr", "kab" }, new PluralRules_French());
    addRules(new String[] { "hr", "ru", "sr", "uk", "be", "bs", "sh" }, new PluralRules_Balkan());
    addRules(new String[] { "lv" }, new PluralRules_Latvian());
    addRules(new String[] { "lt" }, new PluralRules_Lithuanian());
    addRules(new String[] { "pl" }, new PluralRules_Polish());
    addRules(new String[] { "ro", "mo" }, new PluralRules_Romanian());
    addRules(new String[] { "sl" }, new PluralRules_Slovenian());
    addRules(new String[] { "ar" }, new PluralRules_Arabic());
    addRules(new String[] { "mk" }, new PluralRules_Macedonian());
    addRules(new String[] { "cy" }, new PluralRules_Welsh());
    addRules(new String[] { "br" }, new PluralRules_Breton());
    addRules(new String[] { "lag" }, new PluralRules_Langi());
    addRules(new String[] { "shi" }, new PluralRules_Tachelhit());
    addRules(new String[] { "mt" }, new PluralRules_Maltese());
    addRules(new String[] { "ga", "se", "sma", "smi", "smj", "smn", "sms" }, new PluralRules_Two());
    addRules(new String[] { "ak", "am", "bh", "fil", "tl", "guw", "hi", "ln", "mg", "nso", "ti", "wa" }, new PluralRules_Zero());
    addRules(new String[] { "az", "bm", "fa", "ig", "hu", "ja", "kde", "kea", "ko", "my", "ses", "sg", "to", "tr", "vi", "wo", "yo", "zh", "bo", "dz", "id", "jv", "ka", "km", "kn", "ms", "th" }, new PluralRules_None());
  }

  public static void addRules(String paramString, PluralRules paramPluralRules)
  {
    allRules.put(paramString, paramPluralRules);
  }

  public static void addRules(String[] paramArrayOfString, PluralRules paramPluralRules)
  {
    int i = paramArrayOfString.length;
    for (int j = 0; j < i; j++)
    {
      String str = paramArrayOfString[j];
      allRules.put(str, paramPluralRules);
    }
  }

  static final int attrForQuantity(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return 16777220;
    case 1:
      return 16777221;
    case 2:
      return 16777222;
    case 4:
      return 16777223;
    case 8:
      return 16777224;
    case 16:
    }
    return 16777225;
  }

  static final PluralRules ruleForLocale(Locale paramLocale)
  {
    return (PluralRules)allRules.get(paramLocale.getLanguage());
  }

  static final String stringForQuantity(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return "other";
    case 1:
      return "zero";
    case 2:
      return "one";
    case 4:
      return "two";
    case 8:
      return "few";
    case 16:
    }
    return "many";
  }

  final int attrForNumber(int paramInt)
  {
    return attrForQuantity(quantityForNumber(paramInt));
  }

  abstract int quantityForNumber(int paramInt);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.seppius.i18n.plurals.PluralRules
 * JD-Core Version:    0.6.0
 */