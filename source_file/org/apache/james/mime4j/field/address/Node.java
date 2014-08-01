package org.apache.james.mime4j.field.address;

public abstract interface Node
{
  public abstract Object jjtAccept(AddressListParserVisitor paramAddressListParserVisitor, Object paramObject);

  public abstract void jjtAddChild(Node paramNode, int paramInt);

  public abstract void jjtClose();

  public abstract Node jjtGetChild(int paramInt);

  public abstract int jjtGetNumChildren();

  public abstract Node jjtGetParent();

  public abstract void jjtOpen();

  public abstract void jjtSetParent(Node paramNode);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.address.Node
 * JD-Core Version:    0.6.0
 */