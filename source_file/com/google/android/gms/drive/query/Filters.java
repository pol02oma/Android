package com.google.android.gms.drive.query;

import com.google.android.gms.drive.metadata.CollectionMetadataField;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.OrderedMetadataField;
import com.google.android.gms.drive.query.internal.ComparisonFilter;
import com.google.android.gms.drive.query.internal.FieldOnlyFilter;
import com.google.android.gms.drive.query.internal.InFilter;
import com.google.android.gms.drive.query.internal.LogicalFilter;
import com.google.android.gms.drive.query.internal.NotFilter;
import com.google.android.gms.drive.query.internal.Operator;

public class Filters
{
  public static Filter and(Filter paramFilter, Filter[] paramArrayOfFilter)
  {
    return new LogicalFilter(Operator.Ff, paramFilter, paramArrayOfFilter);
  }

  public static Filter and(Iterable<Filter> paramIterable)
  {
    return new LogicalFilter(Operator.Ff, paramIterable);
  }

  public static Filter contains(MetadataField<String> paramMetadataField, String paramString)
  {
    return new ComparisonFilter(Operator.Fi, paramMetadataField, paramString);
  }

  public static <T> Filter eq(MetadataField<T> paramMetadataField, T paramT)
  {
    return new ComparisonFilter(Operator.Fa, paramMetadataField, paramT);
  }

  public static <T extends Comparable<T>> Filter greaterThan(OrderedMetadataField<T> paramOrderedMetadataField, T paramT)
  {
    return new ComparisonFilter(Operator.Fd, paramOrderedMetadataField, paramT);
  }

  public static <T extends Comparable<T>> Filter greaterThanEquals(OrderedMetadataField<T> paramOrderedMetadataField, T paramT)
  {
    return new ComparisonFilter(Operator.Fe, paramOrderedMetadataField, paramT);
  }

  public static <T> Filter in(CollectionMetadataField<T> paramCollectionMetadataField, T paramT)
  {
    return new InFilter(paramCollectionMetadataField, paramT);
  }

  public static <T extends Comparable<T>> Filter lessThan(OrderedMetadataField<T> paramOrderedMetadataField, T paramT)
  {
    return new ComparisonFilter(Operator.Fb, paramOrderedMetadataField, paramT);
  }

  public static <T extends Comparable<T>> Filter lessThanEquals(OrderedMetadataField<T> paramOrderedMetadataField, T paramT)
  {
    return new ComparisonFilter(Operator.Fc, paramOrderedMetadataField, paramT);
  }

  public static Filter not(Filter paramFilter)
  {
    return new NotFilter(paramFilter);
  }

  public static Filter or(Filter paramFilter, Filter[] paramArrayOfFilter)
  {
    return new LogicalFilter(Operator.Fg, paramFilter, paramArrayOfFilter);
  }

  public static Filter or(Iterable<Filter> paramIterable)
  {
    return new LogicalFilter(Operator.Fg, paramIterable);
  }

  public static Filter sharedWithMe()
  {
    return new FieldOnlyFilter(SearchableField.EH);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.query.Filters
 * JD-Core Version:    0.6.0
 */