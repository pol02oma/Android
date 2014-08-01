package org.apache.james.mime4j.field.address;

public class ASTaddr_spec extends SimpleNode
{
  public ASTaddr_spec(int paramInt)
  {
    super(paramInt);
  }

  public ASTaddr_spec(AddressListParser paramAddressListParser, int paramInt)
  {
    super(paramAddressListParser, paramInt);
  }

  public Object jjtAccept(AddressListParserVisitor paramAddressListParserVisitor, Object paramObject)
  {
    return paramAddressListParserVisitor.visit(this, paramObject);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.address.ASTaddr_spec
 * JD-Core Version:    0.6.0
 */