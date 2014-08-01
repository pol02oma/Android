package org.apache.james.mime4j.field.address;

public class ASTphrase extends SimpleNode
{
  public ASTphrase(int paramInt)
  {
    super(paramInt);
  }

  public ASTphrase(AddressListParser paramAddressListParser, int paramInt)
  {
    super(paramAddressListParser, paramInt);
  }

  public Object jjtAccept(AddressListParserVisitor paramAddressListParserVisitor, Object paramObject)
  {
    return paramAddressListParserVisitor.visit(this, paramObject);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.address.ASTphrase
 * JD-Core Version:    0.6.0
 */