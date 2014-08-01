package org.apache.james.mime4j.field.address;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.codec.DecoderUtil;
import org.apache.james.mime4j.dom.address.Address;
import org.apache.james.mime4j.dom.address.AddressList;
import org.apache.james.mime4j.dom.address.DomainList;
import org.apache.james.mime4j.dom.address.Group;
import org.apache.james.mime4j.dom.address.Mailbox;
import org.apache.james.mime4j.stream.ParserCursor;
import org.apache.james.mime4j.stream.RawFieldParser;
import org.apache.james.mime4j.util.ByteSequence;
import org.apache.james.mime4j.util.CharsetUtil;
import org.apache.james.mime4j.util.ContentUtil;

public class LenientAddressBuilder
{
  private static final int AT = 64;
  private static final BitSet AT_AND_CLOSING_BRACKET = RawFieldParser.INIT_BITSET(new int[] { 64, 62 });
  private static final int CLOSING_BRACKET = 62;
  private static final BitSet CLOSING_BRACKET_ONLY = RawFieldParser.INIT_BITSET(new int[] { 62 });
  private static final int COLON = 58;
  private static final BitSet COLON_ONLY;
  private static final int COMMA = 44;
  private static final BitSet COMMA_ONLY = RawFieldParser.INIT_BITSET(new int[] { 44 });
  public static final LenientAddressBuilder DEFAULT;
  private static final int OPENING_BRACKET = 60;
  private static final int SEMICOLON = 59;
  private static final BitSet SEMICOLON_ONLY;
  private final DecodeMonitor monitor;
  private final RawFieldParser parser;

  static
  {
    COLON_ONLY = RawFieldParser.INIT_BITSET(new int[] { 58 });
    SEMICOLON_ONLY = RawFieldParser.INIT_BITSET(new int[] { 59 });
    DEFAULT = new LenientAddressBuilder(DecodeMonitor.SILENT);
  }

  protected LenientAddressBuilder(DecodeMonitor paramDecodeMonitor)
  {
    this.monitor = paramDecodeMonitor;
    this.parser = new RawFieldParser();
  }

  private Mailbox createMailbox(String paramString)
  {
    if ((paramString != null) && (paramString.length() > 0))
      return new Mailbox(null, null, paramString, null);
    return null;
  }

  private Mailbox createMailbox(String paramString1, DomainList paramDomainList, String paramString2, String paramString3)
  {
    if (paramString1 != null);
    for (String str = DecoderUtil.decodeEncodedWords(paramString1, this.monitor); ; str = null)
      return new Mailbox(str, paramDomainList, paramString2, paramString3);
  }

  public Address parseAddress(String paramString)
  {
    return parseAddress(ContentUtil.encode(paramString), new ParserCursor(0, paramString.length()), null);
  }

  public Address parseAddress(ByteSequence paramByteSequence, ParserCursor paramParserCursor, BitSet paramBitSet)
  {
    BitSet localBitSet = RawFieldParser.INIT_BITSET(new int[] { 58, 64, 60 });
    if (paramBitSet != null)
      localBitSet.or(paramBitSet);
    String str = this.parser.parseValue(paramByteSequence, paramParserCursor, localBitSet);
    if (paramParserCursor.atEnd())
      return createMailbox(str);
    int i = paramParserCursor.getPos();
    int j = (char)(0xFF & paramByteSequence.byteAt(i));
    if (j == 60)
      return parseMailboxAddress(str, paramByteSequence, paramParserCursor);
    if (j == 64)
    {
      paramParserCursor.updatePos(i + 1);
      return new Mailbox(null, null, str, parseDomain(paramByteSequence, paramParserCursor, paramBitSet));
    }
    if (j == 58)
    {
      paramParserCursor.updatePos(i + 1);
      List localList = parseMailboxes(paramByteSequence, paramParserCursor, SEMICOLON_ONLY);
      if (!paramParserCursor.atEnd())
      {
        int k = paramParserCursor.getPos();
        if ((char)(0xFF & paramByteSequence.byteAt(k)) == ';')
          paramParserCursor.updatePos(k + 1);
      }
      return new Group(str, localList);
    }
    return createMailbox(str);
  }

  public AddressList parseAddressList(String paramString)
  {
    return parseAddressList(ContentUtil.encode(paramString), new ParserCursor(0, paramString.length()));
  }

  public AddressList parseAddressList(ByteSequence paramByteSequence, ParserCursor paramParserCursor)
  {
    ArrayList localArrayList = new ArrayList();
    while (!paramParserCursor.atEnd())
    {
      int i = paramParserCursor.getPos();
      if ((char)(0xFF & paramByteSequence.byteAt(i)) == ',')
      {
        paramParserCursor.updatePos(i + 1);
        continue;
      }
      Address localAddress = parseAddress(paramByteSequence, paramParserCursor, COMMA_ONLY);
      if (localAddress == null)
        continue;
      localArrayList.add(localAddress);
    }
    return new AddressList(localArrayList, false);
  }

  String parseDomain(ByteSequence paramByteSequence, ParserCursor paramParserCursor, BitSet paramBitSet)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    while (true)
    {
      char c;
      if (!paramParserCursor.atEnd())
      {
        c = (char)(0xFF & paramByteSequence.byteAt(paramParserCursor.getPos()));
        if ((paramBitSet == null) || (!paramBitSet.get(c)));
      }
      else
      {
        return localStringBuilder.toString();
      }
      if (CharsetUtil.isWhitespace(c))
      {
        this.parser.skipWhiteSpace(paramByteSequence, paramParserCursor);
        continue;
      }
      if (c == '(')
      {
        this.parser.skipComment(paramByteSequence, paramParserCursor);
        continue;
      }
      this.parser.copyContent(paramByteSequence, paramParserCursor, paramBitSet, localStringBuilder);
    }
  }

  public Group parseGroup(String paramString)
  {
    return parseGroup(ContentUtil.encode(paramString), new ParserCursor(0, paramString.length()));
  }

  public Group parseGroup(ByteSequence paramByteSequence, ParserCursor paramParserCursor)
  {
    String str = this.parser.parseToken(paramByteSequence, paramParserCursor, COLON_ONLY);
    if (paramParserCursor.atEnd())
      return new Group(str, Collections.emptyList());
    int i = paramParserCursor.getPos();
    if ((char)(0xFF & paramByteSequence.byteAt(i)) == ':')
      paramParserCursor.updatePos(i + 1);
    return new Group(str, parseMailboxes(paramByteSequence, paramParserCursor, SEMICOLON_ONLY));
  }

  public Mailbox parseMailbox(String paramString)
  {
    return parseMailbox(ContentUtil.encode(paramString), new ParserCursor(0, paramString.length()), null);
  }

  public Mailbox parseMailbox(ByteSequence paramByteSequence, ParserCursor paramParserCursor, BitSet paramBitSet)
  {
    BitSet localBitSet = RawFieldParser.INIT_BITSET(new int[] { 64, 60 });
    if (paramBitSet != null)
      localBitSet.or(paramBitSet);
    String str = this.parser.parseValue(paramByteSequence, paramParserCursor, localBitSet);
    if (paramParserCursor.atEnd())
      return createMailbox(str);
    int i = paramParserCursor.getPos();
    int j = (char)(0xFF & paramByteSequence.byteAt(i));
    if (j == 60)
      return parseMailboxAddress(str, paramByteSequence, paramParserCursor);
    if (j == 64)
    {
      paramParserCursor.updatePos(i + 1);
      return new Mailbox(null, null, str, parseDomain(paramByteSequence, paramParserCursor, paramBitSet));
    }
    return createMailbox(str);
  }

  Mailbox parseMailboxAddress(String paramString, ByteSequence paramByteSequence, ParserCursor paramParserCursor)
  {
    if (paramParserCursor.atEnd())
      return createMailbox(null, null, paramString, null);
    int i = paramParserCursor.getPos();
    DomainList localDomainList;
    String str1;
    if ((char)(0xFF & paramByteSequence.byteAt(i)) == '<')
    {
      paramParserCursor.updatePos(i + 1);
      localDomainList = parseRoute(paramByteSequence, paramParserCursor, CLOSING_BRACKET_ONLY);
      str1 = this.parser.parseValue(paramByteSequence, paramParserCursor, AT_AND_CLOSING_BRACKET);
      if (paramParserCursor.atEnd())
        return createMailbox(paramString, localDomainList, str1, null);
    }
    else
    {
      return createMailbox(null, null, paramString, null);
    }
    int j = paramParserCursor.getPos();
    String str2;
    if ((char)(0xFF & paramByteSequence.byteAt(j)) == '@')
    {
      paramParserCursor.updatePos(j + 1);
      str2 = parseDomain(paramByteSequence, paramParserCursor, CLOSING_BRACKET_ONLY);
      if (paramParserCursor.atEnd())
        return createMailbox(paramString, localDomainList, str1, str2);
    }
    else
    {
      return createMailbox(paramString, localDomainList, str1, null);
    }
    int k = paramParserCursor.getPos();
    if ((char)(0xFF & paramByteSequence.byteAt(k)) == '>')
      paramParserCursor.updatePos(k + 1);
    while (!paramParserCursor.atEnd())
    {
      char c = (char)(0xFF & paramByteSequence.byteAt(paramParserCursor.getPos()));
      if (CharsetUtil.isWhitespace(c))
      {
        this.parser.skipWhiteSpace(paramByteSequence, paramParserCursor);
        continue;
        return createMailbox(paramString, localDomainList, str1, str2);
      }
      if (c != '(')
        break;
      this.parser.skipComment(paramByteSequence, paramParserCursor);
    }
    return createMailbox(paramString, localDomainList, str1, str2);
  }

  List<Mailbox> parseMailboxes(ByteSequence paramByteSequence, ParserCursor paramParserCursor, BitSet paramBitSet)
  {
    BitSet localBitSet = RawFieldParser.INIT_BITSET(new int[] { 44 });
    if (paramBitSet != null)
      localBitSet.or(paramBitSet);
    ArrayList localArrayList = new ArrayList();
    while (true)
    {
      int i;
      int j;
      if (!paramParserCursor.atEnd())
      {
        i = paramParserCursor.getPos();
        j = (char)(0xFF & paramByteSequence.byteAt(i));
        if ((paramBitSet == null) || (!paramBitSet.get(j)));
      }
      else
      {
        return localArrayList;
      }
      if (j == 44)
      {
        paramParserCursor.updatePos(i + 1);
        continue;
      }
      Mailbox localMailbox = parseMailbox(paramByteSequence, paramParserCursor, localBitSet);
      if (localMailbox == null)
        continue;
      localArrayList.add(localMailbox);
    }
  }

  DomainList parseRoute(ByteSequence paramByteSequence, ParserCursor paramParserCursor, BitSet paramBitSet)
  {
    BitSet localBitSet = RawFieldParser.INIT_BITSET(new int[] { 44, 58 });
    if (paramBitSet != null)
      localBitSet.or(paramBitSet);
    Object localObject = null;
    this.parser.skipAllWhiteSpace(paramByteSequence, paramParserCursor);
    if (paramParserCursor.atEnd());
    while (true)
    {
      if (localObject == null)
        break label206;
      return new DomainList((List)localObject, true);
      int i = paramParserCursor.getPos();
      if ((char)(0xFF & paramByteSequence.byteAt(i)) != '@')
        continue;
      paramParserCursor.updatePos(i + 1);
      String str = parseDomain(paramByteSequence, paramParserCursor, localBitSet);
      if ((str != null) && (str.length() > 0))
      {
        if (localObject == null)
          localObject = new ArrayList();
        ((List)localObject).add(str);
      }
      if (paramParserCursor.atEnd())
        continue;
      int j = paramParserCursor.getPos();
      int k = (char)(0xFF & paramByteSequence.byteAt(j));
      if (k == 44)
      {
        paramParserCursor.updatePos(j + 1);
        break;
      }
      if (k != 58)
        continue;
      paramParserCursor.updatePos(j + 1);
    }
    label206: return (DomainList)null;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.address.LenientAddressBuilder
 * JD-Core Version:    0.6.0
 */