package org.apache.james.mime4j.field.address;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.codec.DecoderUtil;
import org.apache.james.mime4j.dom.address.Address;
import org.apache.james.mime4j.dom.address.AddressList;
import org.apache.james.mime4j.dom.address.DomainList;
import org.apache.james.mime4j.dom.address.Group;
import org.apache.james.mime4j.dom.address.Mailbox;
import org.apache.james.mime4j.dom.address.MailboxList;

class Builder
{
  private static Builder singleton = new Builder();

  private void addSpecials(StringBuilder paramStringBuilder, Token paramToken)
  {
    if (paramToken != null)
    {
      addSpecials(paramStringBuilder, paramToken.specialToken);
      paramStringBuilder.append(paramToken.image);
    }
  }

  private Mailbox buildAddrSpec(DomainList paramDomainList, ASTaddr_spec paramASTaddr_spec)
  {
    ChildNodeIterator localChildNodeIterator = new ChildNodeIterator(paramASTaddr_spec);
    return new Mailbox(paramDomainList, buildString((ASTlocal_part)localChildNodeIterator.next(), true), buildString((ASTdomain)localChildNodeIterator.next(), true));
  }

  private Mailbox buildAddrSpec(ASTaddr_spec paramASTaddr_spec)
  {
    return buildAddrSpec(null, paramASTaddr_spec);
  }

  private Mailbox buildAngleAddr(ASTangle_addr paramASTangle_addr)
    throws ParseException
  {
    ChildNodeIterator localChildNodeIterator = new ChildNodeIterator(paramASTangle_addr);
    Node localNode = localChildNodeIterator.next();
    DomainList localDomainList;
    if ((localNode instanceof ASTroute))
    {
      localDomainList = buildRoute((ASTroute)localNode);
      localNode = localChildNodeIterator.next();
    }
    while ((localNode instanceof ASTaddr_spec))
    {
      return buildAddrSpec(localDomainList, (ASTaddr_spec)localNode);
      boolean bool = localNode instanceof ASTaddr_spec;
      localDomainList = null;
      if (bool)
        continue;
      throw new ParseException();
    }
    throw new ParseException();
  }

  private MailboxList buildGroupBody(ASTgroup_body paramASTgroup_body, DecodeMonitor paramDecodeMonitor)
    throws ParseException
  {
    ArrayList localArrayList = new ArrayList();
    ChildNodeIterator localChildNodeIterator = new ChildNodeIterator(paramASTgroup_body);
    while (localChildNodeIterator.hasNext())
    {
      Node localNode = localChildNodeIterator.next();
      if ((localNode instanceof ASTmailbox))
      {
        localArrayList.add(buildMailbox((ASTmailbox)localNode, paramDecodeMonitor));
        continue;
      }
      throw new ParseException();
    }
    return new MailboxList(localArrayList, true);
  }

  private Mailbox buildNameAddr(ASTname_addr paramASTname_addr, DecodeMonitor paramDecodeMonitor)
    throws ParseException
  {
    ChildNodeIterator localChildNodeIterator = new ChildNodeIterator(paramASTname_addr);
    Node localNode1 = localChildNodeIterator.next();
    String str1;
    Node localNode2;
    if ((localNode1 instanceof ASTphrase))
    {
      str1 = buildString((ASTphrase)localNode1, false);
      localNode2 = localChildNodeIterator.next();
      if (!(localNode2 instanceof ASTangle_addr));
    }
    else
    {
      try
      {
        String str2 = DecoderUtil.decodeEncodedWords(str1, paramDecodeMonitor);
        Mailbox localMailbox = buildAngleAddr((ASTangle_addr)localNode2);
        return new Mailbox(str2, localMailbox.getRoute(), localMailbox.getLocalPart(), localMailbox.getDomain());
        throw new ParseException();
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        throw new ParseException(localIllegalArgumentException.getMessage());
      }
    }
    throw new ParseException();
  }

  private DomainList buildRoute(ASTroute paramASTroute)
    throws ParseException
  {
    ArrayList localArrayList = new ArrayList(paramASTroute.jjtGetNumChildren());
    ChildNodeIterator localChildNodeIterator = new ChildNodeIterator(paramASTroute);
    while (localChildNodeIterator.hasNext())
    {
      Node localNode = localChildNodeIterator.next();
      if ((localNode instanceof ASTdomain))
      {
        localArrayList.add(buildString((ASTdomain)localNode, true));
        continue;
      }
      throw new ParseException();
    }
    return new DomainList(localArrayList, true);
  }

  private String buildString(SimpleNode paramSimpleNode, boolean paramBoolean)
  {
    Token localToken1 = paramSimpleNode.firstToken;
    Token localToken2 = paramSimpleNode.lastToken;
    StringBuilder localStringBuilder = new StringBuilder();
    while (localToken1 != localToken2)
    {
      localStringBuilder.append(localToken1.image);
      localToken1 = localToken1.next;
      if (paramBoolean)
        continue;
      addSpecials(localStringBuilder, localToken1.specialToken);
    }
    localStringBuilder.append(localToken2.image);
    return localStringBuilder.toString();
  }

  public static Builder getInstance()
  {
    return singleton;
  }

  public Address buildAddress(ASTaddress paramASTaddress, DecodeMonitor paramDecodeMonitor)
    throws ParseException
  {
    ChildNodeIterator localChildNodeIterator = new ChildNodeIterator(paramASTaddress);
    Node localNode1 = localChildNodeIterator.next();
    if ((localNode1 instanceof ASTaddr_spec))
      return buildAddrSpec((ASTaddr_spec)localNode1);
    if ((localNode1 instanceof ASTangle_addr))
      return buildAngleAddr((ASTangle_addr)localNode1);
    if ((localNode1 instanceof ASTphrase))
    {
      String str1 = buildString((ASTphrase)localNode1, false);
      Node localNode2 = localChildNodeIterator.next();
      if ((localNode2 instanceof ASTgroup_body))
        return new Group(str1, buildGroupBody((ASTgroup_body)localNode2, paramDecodeMonitor));
      if ((localNode2 instanceof ASTangle_addr))
        try
        {
          String str2 = DecoderUtil.decodeEncodedWords(str1, paramDecodeMonitor);
          Mailbox localMailbox = buildAngleAddr((ASTangle_addr)localNode2);
          return new Mailbox(str2, localMailbox.getRoute(), localMailbox.getLocalPart(), localMailbox.getDomain());
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          throw new ParseException(localIllegalArgumentException.getMessage());
        }
      throw new ParseException();
    }
    throw new ParseException();
  }

  public AddressList buildAddressList(ASTaddress_list paramASTaddress_list, DecodeMonitor paramDecodeMonitor)
    throws ParseException
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < paramASTaddress_list.jjtGetNumChildren(); i++)
      localArrayList.add(buildAddress((ASTaddress)paramASTaddress_list.jjtGetChild(i), paramDecodeMonitor));
    return new AddressList(localArrayList, true);
  }

  public Mailbox buildMailbox(ASTmailbox paramASTmailbox, DecodeMonitor paramDecodeMonitor)
    throws ParseException
  {
    Node localNode = new ChildNodeIterator(paramASTmailbox).next();
    if ((localNode instanceof ASTaddr_spec))
      return buildAddrSpec((ASTaddr_spec)localNode);
    if ((localNode instanceof ASTangle_addr))
      return buildAngleAddr((ASTangle_addr)localNode);
    if ((localNode instanceof ASTname_addr))
      return buildNameAddr((ASTname_addr)localNode, paramDecodeMonitor);
    throw new ParseException();
  }

  private static class ChildNodeIterator
    implements Iterator<Node>
  {
    private int index;
    private int len;
    private SimpleNode simpleNode;

    public ChildNodeIterator(SimpleNode paramSimpleNode)
    {
      this.simpleNode = paramSimpleNode;
      this.len = paramSimpleNode.jjtGetNumChildren();
      this.index = 0;
    }

    public boolean hasNext()
    {
      return this.index < this.len;
    }

    public Node next()
    {
      SimpleNode localSimpleNode = this.simpleNode;
      int i = this.index;
      this.index = (i + 1);
      return localSimpleNode.jjtGetChild(i);
    }

    public void remove()
    {
      throw new UnsupportedOperationException();
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.address.Builder
 * JD-Core Version:    0.6.0
 */