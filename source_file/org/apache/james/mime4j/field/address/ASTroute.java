package org.apache.james.mime4j.field.address;

public class ASTroute extends SimpleNode
{
  public ASTroute(int paramInt)
  {
    super(paramInt);
  }

  public ASTroute(AddressListParser paramAddressListParser, int paramInt)
  {
    super(paramAddressListParser, paramInt);
  }

  public Object jjtAccept(AddressListParserVisitor paramAddressListParserVisitor, Object paramObject)
  {
    return paramAddressListParserVisitor.visit(this, paramObject);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.address.ASTroute
 * JD-Core Version:    0.6.0
 */