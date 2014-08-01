package org.apache.james.mime4j.stream;

public class ParserCursor
{
  private final int lowerBound;
  private int pos;
  private final int upperBound;

  public ParserCursor(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0)
      throw new IndexOutOfBoundsException("Lower bound cannot be negative");
    if (paramInt1 > paramInt2)
      throw new IndexOutOfBoundsException("Lower bound cannot be greater then upper bound");
    this.lowerBound = paramInt1;
    this.upperBound = paramInt2;
    this.pos = paramInt1;
  }

  public boolean atEnd()
  {
    return this.pos >= this.upperBound;
  }

  public int getLowerBound()
  {
    return this.lowerBound;
  }

  public int getPos()
  {
    return this.pos;
  }

  public int getUpperBound()
  {
    return this.upperBound;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('[');
    localStringBuilder.append(Integer.toString(this.lowerBound));
    localStringBuilder.append('>');
    localStringBuilder.append(Integer.toString(this.pos));
    localStringBuilder.append('>');
    localStringBuilder.append(Integer.toString(this.upperBound));
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }

  public void updatePos(int paramInt)
  {
    if (paramInt < this.lowerBound)
      throw new IndexOutOfBoundsException("pos: " + paramInt + " < lowerBound: " + this.lowerBound);
    if (paramInt > this.upperBound)
      throw new IndexOutOfBoundsException("pos: " + paramInt + " > upperBound: " + this.upperBound);
    this.pos = paramInt;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.stream.ParserCursor
 * JD-Core Version:    0.6.0
 */