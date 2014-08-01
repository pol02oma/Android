package org.apache.james.mime4j.dom.address;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.apache.james.mime4j.util.LangUtils;

public class Mailbox extends Address
{
  private static final DomainList EMPTY_ROUTE_LIST = new DomainList(Collections.emptyList(), true);
  private static final long serialVersionUID = 1L;
  private final String domain;
  private final String localPart;
  private final String name;
  private final DomainList route;

  public Mailbox(String paramString1, String paramString2)
  {
    this(null, null, paramString1, paramString2);
  }

  public Mailbox(String paramString1, String paramString2, String paramString3)
  {
    this(paramString1, null, paramString2, paramString3);
  }

  public Mailbox(String paramString1, DomainList paramDomainList, String paramString2, String paramString3)
  {
    if (paramString2 == null)
      throw new IllegalArgumentException();
    if ((paramString1 == null) || (paramString1.length() == 0))
      paramString1 = null;
    this.name = paramString1;
    if (paramDomainList == null)
      paramDomainList = EMPTY_ROUTE_LIST;
    this.route = paramDomainList;
    this.localPart = paramString2;
    if ((paramString3 == null) || (paramString3.length() == 0))
      paramString3 = null;
    this.domain = paramString3;
  }

  Mailbox(String paramString, Mailbox paramMailbox)
  {
    this(paramString, paramMailbox.getRoute(), paramMailbox.getLocalPart(), paramMailbox.getDomain());
  }

  public Mailbox(DomainList paramDomainList, String paramString1, String paramString2)
  {
    this(null, paramDomainList, paramString1, paramString2);
  }

  protected final void doAddMailboxesTo(List<Mailbox> paramList)
  {
    paramList.add(this);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == this);
    Mailbox localMailbox;
    do
    {
      return true;
      if (!(paramObject instanceof Mailbox))
        return false;
      localMailbox = (Mailbox)paramObject;
    }
    while ((LangUtils.equals(this.localPart, localMailbox.localPart)) && (LangUtils.equalsIgnoreCase(this.domain, localMailbox.domain)));
    return false;
  }

  public String getAddress()
  {
    if (this.domain == null)
      return this.localPart;
    return this.localPart + '@' + this.domain;
  }

  public String getDomain()
  {
    return this.domain;
  }

  public String getLocalPart()
  {
    return this.localPart;
  }

  public String getName()
  {
    return this.name;
  }

  public DomainList getRoute()
  {
    return this.route;
  }

  public int hashCode()
  {
    int i = LangUtils.hashCode(17, this.localPart);
    if (this.domain != null);
    for (String str = this.domain.toLowerCase(Locale.US); ; str = null)
      return LangUtils.hashCode(i, str);
  }

  public String toString()
  {
    return getAddress();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.dom.address.Mailbox
 * JD-Core Version:    0.6.0
 */