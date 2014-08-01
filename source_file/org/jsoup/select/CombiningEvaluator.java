package org.jsoup.select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Element;

abstract class CombiningEvaluator extends Evaluator
{
  final List<Evaluator> evaluators = new ArrayList();

  CombiningEvaluator()
  {
  }

  CombiningEvaluator(Collection<Evaluator> paramCollection)
  {
    this();
    this.evaluators.addAll(paramCollection);
  }

  static final class And extends CombiningEvaluator
  {
    And(Collection<Evaluator> paramCollection)
    {
      super();
    }

    And(Evaluator[] paramArrayOfEvaluator)
    {
      this(Arrays.asList(paramArrayOfEvaluator));
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      Iterator localIterator = this.evaluators.iterator();
      while (localIterator.hasNext())
        if (!((Evaluator)localIterator.next()).matches(paramElement1, paramElement2))
          return false;
      return true;
    }

    public String toString()
    {
      return StringUtil.join(this.evaluators, " ");
    }
  }

  static final class Or extends CombiningEvaluator
  {
    Or(Collection<Evaluator> paramCollection)
    {
      if (paramCollection.size() > 1)
      {
        this.evaluators.add(new CombiningEvaluator.And(paramCollection));
        return;
      }
      this.evaluators.addAll(paramCollection);
    }

    public void add(Evaluator paramEvaluator)
    {
      this.evaluators.add(paramEvaluator);
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      Iterator localIterator = this.evaluators.iterator();
      while (localIterator.hasNext())
        if (((Evaluator)localIterator.next()).matches(paramElement1, paramElement2))
          return true;
      return false;
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.evaluators;
      return String.format(":or%s", arrayOfObject);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.jsoup.select.CombiningEvaluator
 * JD-Core Version:    0.6.0
 */