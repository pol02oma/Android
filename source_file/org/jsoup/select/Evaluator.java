package org.jsoup.select;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;

public abstract class Evaluator
{
  public abstract boolean matches(Element paramElement1, Element paramElement2);

  public static final class AllElements extends Evaluator
  {
    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return true;
    }

    public String toString()
    {
      return "*";
    }
  }

  public static final class Attribute extends Evaluator
  {
    private String key;

    public Attribute(String paramString)
    {
      this.key = paramString;
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return paramElement2.hasAttr(this.key);
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.key;
      return String.format("[%s]", arrayOfObject);
    }
  }

  public static abstract class AttributeKeyPair extends Evaluator
  {
    String key;
    String value;

    public AttributeKeyPair(String paramString1, String paramString2)
    {
      Validate.notEmpty(paramString1);
      Validate.notEmpty(paramString2);
      this.key = paramString1.trim().toLowerCase();
      this.value = paramString2.trim().toLowerCase();
    }
  }

  public static final class AttributeStarting extends Evaluator
  {
    private String keyPrefix;

    public AttributeStarting(String paramString)
    {
      this.keyPrefix = paramString;
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      Iterator localIterator = paramElement2.attributes().asList().iterator();
      while (localIterator.hasNext())
        if (((Attribute)localIterator.next()).getKey().startsWith(this.keyPrefix))
          return true;
      return false;
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.keyPrefix;
      return String.format("[^%s]", arrayOfObject);
    }
  }

  public static final class AttributeWithValue extends Evaluator.AttributeKeyPair
  {
    public AttributeWithValue(String paramString1, String paramString2)
    {
      super(paramString2);
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return (paramElement2.hasAttr(this.key)) && (this.value.equalsIgnoreCase(paramElement2.attr(this.key)));
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = this.key;
      arrayOfObject[1] = this.value;
      return String.format("[%s=%s]", arrayOfObject);
    }
  }

  public static final class AttributeWithValueContaining extends Evaluator.AttributeKeyPair
  {
    public AttributeWithValueContaining(String paramString1, String paramString2)
    {
      super(paramString2);
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return (paramElement2.hasAttr(this.key)) && (paramElement2.attr(this.key).toLowerCase().contains(this.value));
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = this.key;
      arrayOfObject[1] = this.value;
      return String.format("[%s*=%s]", arrayOfObject);
    }
  }

  public static final class AttributeWithValueEnding extends Evaluator.AttributeKeyPair
  {
    public AttributeWithValueEnding(String paramString1, String paramString2)
    {
      super(paramString2);
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return (paramElement2.hasAttr(this.key)) && (paramElement2.attr(this.key).toLowerCase().endsWith(this.value));
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = this.key;
      arrayOfObject[1] = this.value;
      return String.format("[%s$=%s]", arrayOfObject);
    }
  }

  public static final class AttributeWithValueMatching extends Evaluator
  {
    String key;
    Pattern pattern;

    public AttributeWithValueMatching(String paramString, Pattern paramPattern)
    {
      this.key = paramString.trim().toLowerCase();
      this.pattern = paramPattern;
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return (paramElement2.hasAttr(this.key)) && (this.pattern.matcher(paramElement2.attr(this.key)).find());
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = this.key;
      arrayOfObject[1] = this.pattern.toString();
      return String.format("[%s~=%s]", arrayOfObject);
    }
  }

  public static final class AttributeWithValueNot extends Evaluator.AttributeKeyPair
  {
    public AttributeWithValueNot(String paramString1, String paramString2)
    {
      super(paramString2);
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return !this.value.equalsIgnoreCase(paramElement2.attr(this.key));
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = this.key;
      arrayOfObject[1] = this.value;
      return String.format("[%s!=%s]", arrayOfObject);
    }
  }

  public static final class AttributeWithValueStarting extends Evaluator.AttributeKeyPair
  {
    public AttributeWithValueStarting(String paramString1, String paramString2)
    {
      super(paramString2);
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return (paramElement2.hasAttr(this.key)) && (paramElement2.attr(this.key).toLowerCase().startsWith(this.value));
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = this.key;
      arrayOfObject[1] = this.value;
      return String.format("[%s^=%s]", arrayOfObject);
    }
  }

  public static final class Class extends Evaluator
  {
    private String className;

    public Class(String paramString)
    {
      this.className = paramString;
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return paramElement2.hasClass(this.className);
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.className;
      return String.format(".%s", arrayOfObject);
    }
  }

  public static final class ContainsOwnText extends Evaluator
  {
    private String searchText;

    public ContainsOwnText(String paramString)
    {
      this.searchText = paramString.toLowerCase();
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return paramElement2.ownText().toLowerCase().contains(this.searchText);
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.searchText;
      return String.format(":containsOwn(%s", arrayOfObject);
    }
  }

  public static final class ContainsText extends Evaluator
  {
    private String searchText;

    public ContainsText(String paramString)
    {
      this.searchText = paramString.toLowerCase();
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return paramElement2.text().toLowerCase().contains(this.searchText);
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.searchText;
      return String.format(":contains(%s", arrayOfObject);
    }
  }

  public static final class Id extends Evaluator
  {
    private String id;

    public Id(String paramString)
    {
      this.id = paramString;
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return this.id.equals(paramElement2.id());
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.id;
      return String.format("#%s", arrayOfObject);
    }
  }

  public static final class IndexEquals extends Evaluator.IndexEvaluator
  {
    public IndexEquals(int paramInt)
    {
      super();
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return paramElement2.elementSiblingIndex().intValue() == this.index;
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(this.index);
      return String.format(":eq(%d)", arrayOfObject);
    }
  }

  public static abstract class IndexEvaluator extends Evaluator
  {
    int index;

    public IndexEvaluator(int paramInt)
    {
      this.index = paramInt;
    }
  }

  public static final class IndexGreaterThan extends Evaluator.IndexEvaluator
  {
    public IndexGreaterThan(int paramInt)
    {
      super();
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return paramElement2.elementSiblingIndex().intValue() > this.index;
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(this.index);
      return String.format(":gt(%d)", arrayOfObject);
    }
  }

  public static final class IndexLessThan extends Evaluator.IndexEvaluator
  {
    public IndexLessThan(int paramInt)
    {
      super();
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return paramElement2.elementSiblingIndex().intValue() < this.index;
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(this.index);
      return String.format(":lt(%d)", arrayOfObject);
    }
  }

  public static final class Matches extends Evaluator
  {
    private Pattern pattern;

    public Matches(Pattern paramPattern)
    {
      this.pattern = paramPattern;
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return this.pattern.matcher(paramElement2.text()).find();
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.pattern;
      return String.format(":matches(%s", arrayOfObject);
    }
  }

  public static final class MatchesOwn extends Evaluator
  {
    private Pattern pattern;

    public MatchesOwn(Pattern paramPattern)
    {
      this.pattern = paramPattern;
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return this.pattern.matcher(paramElement2.ownText()).find();
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.pattern;
      return String.format(":matchesOwn(%s", arrayOfObject);
    }
  }

  public static final class Tag extends Evaluator
  {
    private String tagName;

    public Tag(String paramString)
    {
      this.tagName = paramString;
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return paramElement2.tagName().equals(this.tagName);
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.tagName;
      return String.format("%s", arrayOfObject);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.jsoup.select.Evaluator
 * JD-Core Version:    0.6.0
 */