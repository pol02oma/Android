package org.jsoup.select;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;
import org.jsoup.parser.TokenQueue;

class QueryParser
{
  private static final String[] combinators = { ",", ">", "+", "~", " " };
  private List<Evaluator> evals = new ArrayList();
  private String query;
  private TokenQueue tq;

  private QueryParser(String paramString)
  {
    this.query = paramString;
    this.tq = new TokenQueue(paramString);
  }

  private void allElements()
  {
    this.evals.add(new Evaluator.AllElements());
  }

  private void byAttribute()
  {
    TokenQueue localTokenQueue = new TokenQueue(this.tq.chompBalanced('[', ']'));
    String str = localTokenQueue.consumeToAny(new String[] { "=", "!=", "^=", "$=", "*=", "~=" });
    Validate.notEmpty(str);
    localTokenQueue.consumeWhitespace();
    if (localTokenQueue.isEmpty())
    {
      if (str.startsWith("^"))
      {
        this.evals.add(new Evaluator.AttributeStarting(str.substring(1)));
        return;
      }
      this.evals.add(new Evaluator.Attribute(str));
      return;
    }
    if (localTokenQueue.matchChomp("="))
    {
      this.evals.add(new Evaluator.AttributeWithValue(str, localTokenQueue.remainder()));
      return;
    }
    if (localTokenQueue.matchChomp("!="))
    {
      this.evals.add(new Evaluator.AttributeWithValueNot(str, localTokenQueue.remainder()));
      return;
    }
    if (localTokenQueue.matchChomp("^="))
    {
      this.evals.add(new Evaluator.AttributeWithValueStarting(str, localTokenQueue.remainder()));
      return;
    }
    if (localTokenQueue.matchChomp("$="))
    {
      this.evals.add(new Evaluator.AttributeWithValueEnding(str, localTokenQueue.remainder()));
      return;
    }
    if (localTokenQueue.matchChomp("*="))
    {
      this.evals.add(new Evaluator.AttributeWithValueContaining(str, localTokenQueue.remainder()));
      return;
    }
    if (localTokenQueue.matchChomp("~="))
    {
      this.evals.add(new Evaluator.AttributeWithValueMatching(str, Pattern.compile(localTokenQueue.remainder())));
      return;
    }
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.query;
    arrayOfObject[1] = localTokenQueue.remainder();
    throw new Selector.SelectorParseException("Could not parse attribute query '%s': unexpected token at '%s'", arrayOfObject);
  }

  private void byClass()
  {
    String str = this.tq.consumeCssIdentifier();
    Validate.notEmpty(str);
    this.evals.add(new Evaluator.Class(str.trim().toLowerCase()));
  }

  private void byId()
  {
    String str = this.tq.consumeCssIdentifier();
    Validate.notEmpty(str);
    this.evals.add(new Evaluator.Id(str));
  }

  private void byTag()
  {
    String str = this.tq.consumeElementSelector();
    Validate.notEmpty(str);
    if (str.contains("|"))
      str = str.replace("|", ":");
    this.evals.add(new Evaluator.Tag(str.trim().toLowerCase()));
  }

  private void combinator(char paramChar)
  {
    this.tq.consumeWhitespace();
    String str = consumeSubQuery();
    if (this.evals.size() == 1);
    Evaluator localEvaluator;
    for (Object localObject = (Evaluator)this.evals.get(0); ; localObject = new CombiningEvaluator.And(this.evals))
    {
      this.evals.clear();
      localEvaluator = parse(str);
      if (paramChar != '>')
        break;
      List localList4 = this.evals;
      Evaluator[] arrayOfEvaluator4 = new Evaluator[2];
      arrayOfEvaluator4[0] = localEvaluator;
      arrayOfEvaluator4[1] = new StructuralEvaluator.ImmediateParent((Evaluator)localObject);
      localList4.add(new CombiningEvaluator.And(arrayOfEvaluator4));
      return;
    }
    if (paramChar == ' ')
    {
      List localList3 = this.evals;
      Evaluator[] arrayOfEvaluator3 = new Evaluator[2];
      arrayOfEvaluator3[0] = localEvaluator;
      arrayOfEvaluator3[1] = new StructuralEvaluator.Parent((Evaluator)localObject);
      localList3.add(new CombiningEvaluator.And(arrayOfEvaluator3));
      return;
    }
    if (paramChar == '+')
    {
      List localList2 = this.evals;
      Evaluator[] arrayOfEvaluator2 = new Evaluator[2];
      arrayOfEvaluator2[0] = localEvaluator;
      arrayOfEvaluator2[1] = new StructuralEvaluator.ImmediatePreviousSibling((Evaluator)localObject);
      localList2.add(new CombiningEvaluator.And(arrayOfEvaluator2));
      return;
    }
    if (paramChar == '~')
    {
      List localList1 = this.evals;
      Evaluator[] arrayOfEvaluator1 = new Evaluator[2];
      arrayOfEvaluator1[0] = localEvaluator;
      arrayOfEvaluator1[1] = new StructuralEvaluator.PreviousSibling((Evaluator)localObject);
      localList1.add(new CombiningEvaluator.And(arrayOfEvaluator1));
      return;
    }
    throw new Selector.SelectorParseException("Unknown combinator: " + paramChar, new Object[0]);
  }

  private int consumeIndex()
  {
    String str = this.tq.chompTo(")").trim();
    Validate.isTrue(StringUtil.isNumeric(str), "Index must be numeric");
    return Integer.parseInt(str);
  }

  private String consumeSubQuery()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    while (true)
    {
      if (!this.tq.isEmpty())
      {
        if (this.tq.matches("("))
        {
          localStringBuilder.append("(").append(this.tq.chompBalanced('(', ')')).append(")");
          continue;
        }
        if (this.tq.matches("["))
        {
          localStringBuilder.append("[").append(this.tq.chompBalanced('[', ']')).append("]");
          continue;
        }
        if (!this.tq.matchesAny(combinators));
      }
      else
      {
        return localStringBuilder.toString();
      }
      localStringBuilder.append(this.tq.consume());
    }
  }

  private void contains(boolean paramBoolean)
  {
    TokenQueue localTokenQueue = this.tq;
    if (paramBoolean);
    String str2;
    for (String str1 = ":containsOwn"; ; str1 = ":contains")
    {
      localTokenQueue.consume(str1);
      str2 = TokenQueue.unescape(this.tq.chompBalanced('(', ')'));
      Validate.notEmpty(str2, ":contains(text) query must not be empty");
      if (!paramBoolean)
        break;
      this.evals.add(new Evaluator.ContainsOwnText(str2));
      return;
    }
    this.evals.add(new Evaluator.ContainsText(str2));
  }

  private void findElements()
  {
    if (this.tq.matchChomp("#"))
    {
      byId();
      return;
    }
    if (this.tq.matchChomp("."))
    {
      byClass();
      return;
    }
    if (this.tq.matchesWord())
    {
      byTag();
      return;
    }
    if (this.tq.matches("["))
    {
      byAttribute();
      return;
    }
    if (this.tq.matchChomp("*"))
    {
      allElements();
      return;
    }
    if (this.tq.matchChomp(":lt("))
    {
      indexLessThan();
      return;
    }
    if (this.tq.matchChomp(":gt("))
    {
      indexGreaterThan();
      return;
    }
    if (this.tq.matchChomp(":eq("))
    {
      indexEquals();
      return;
    }
    if (this.tq.matches(":has("))
    {
      has();
      return;
    }
    if (this.tq.matches(":contains("))
    {
      contains(false);
      return;
    }
    if (this.tq.matches(":containsOwn("))
    {
      contains(true);
      return;
    }
    if (this.tq.matches(":matches("))
    {
      matches(false);
      return;
    }
    if (this.tq.matches(":matchesOwn("))
    {
      matches(true);
      return;
    }
    if (this.tq.matches(":not("))
    {
      not();
      return;
    }
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.query;
    arrayOfObject[1] = this.tq.remainder();
    throw new Selector.SelectorParseException("Could not parse query '%s': unexpected token at '%s'", arrayOfObject);
  }

  private void has()
  {
    this.tq.consume(":has");
    String str = this.tq.chompBalanced('(', ')');
    Validate.notEmpty(str, ":has(el) subselect must not be empty");
    this.evals.add(new StructuralEvaluator.Has(parse(str)));
  }

  private void indexEquals()
  {
    this.evals.add(new Evaluator.IndexEquals(consumeIndex()));
  }

  private void indexGreaterThan()
  {
    this.evals.add(new Evaluator.IndexGreaterThan(consumeIndex()));
  }

  private void indexLessThan()
  {
    this.evals.add(new Evaluator.IndexLessThan(consumeIndex()));
  }

  private void matches(boolean paramBoolean)
  {
    TokenQueue localTokenQueue = this.tq;
    if (paramBoolean);
    String str2;
    for (String str1 = ":matchesOwn"; ; str1 = ":matches")
    {
      localTokenQueue.consume(str1);
      str2 = this.tq.chompBalanced('(', ')');
      Validate.notEmpty(str2, ":matches(regex) query must not be empty");
      if (!paramBoolean)
        break;
      this.evals.add(new Evaluator.MatchesOwn(Pattern.compile(str2)));
      return;
    }
    this.evals.add(new Evaluator.Matches(Pattern.compile(str2)));
  }

  private void not()
  {
    this.tq.consume(":not");
    String str = this.tq.chompBalanced('(', ')');
    Validate.notEmpty(str, ":not(selector) subselect must not be empty");
    this.evals.add(new StructuralEvaluator.Not(parse(str)));
  }

  public static Evaluator parse(String paramString)
  {
    return new QueryParser(paramString).parse();
  }

  Evaluator parse()
  {
    this.tq.consumeWhitespace();
    if (this.tq.matchesAny(combinators))
    {
      this.evals.add(new StructuralEvaluator.Root());
      combinator(this.tq.consume());
    }
    while (!this.tq.isEmpty())
    {
      boolean bool = this.tq.consumeWhitespace();
      if (this.tq.matchChomp(","))
      {
        CombiningEvaluator.Or localOr = new CombiningEvaluator.Or(this.evals);
        this.evals.clear();
        this.evals.add(localOr);
        while (!this.tq.isEmpty())
          localOr.add(parse(this.tq.chompTo(",")));
        continue;
        findElements();
        continue;
      }
      if (this.tq.matchesAny(combinators))
      {
        combinator(this.tq.consume());
        continue;
      }
      if (bool)
      {
        combinator(' ');
        continue;
      }
      findElements();
    }
    if (this.evals.size() == 1)
      return (Evaluator)this.evals.get(0);
    return new CombiningEvaluator.And(this.evals);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.jsoup.select.QueryParser
 * JD-Core Version:    0.6.0
 */