package org.apache.james.mime4j.field.address;

import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddressListParser
  implements AddressListParserTreeConstants, AddressListParserConstants
{
  private static int[] jj_la1_0;
  private static int[] jj_la1_1;
  private final JJCalls[] jj_2_rtns = new JJCalls[2];
  private int jj_endpos;
  private List<int[]> jj_expentries = new ArrayList();
  private int[] jj_expentry;
  private int jj_gc = 0;
  private int jj_gen;
  SimpleCharStream jj_input_stream;
  private int jj_kind = -1;
  private int jj_la;
  private final int[] jj_la1 = new int[22];
  private Token jj_lastpos;
  private int[] jj_lasttokens = new int[100];
  private final LookaheadSuccess jj_ls = new LookaheadSuccess(null);
  public Token jj_nt;
  private int jj_ntk;
  private boolean jj_rescan = false;
  private Token jj_scanpos;
  protected JJTAddressListParserState jjtree = new JJTAddressListParserState();
  public Token token;
  public AddressListParserTokenManager token_source;

  static
  {
    jj_la1_init_0();
    jj_la1_init_1();
  }

  public AddressListParser(InputStream paramInputStream)
  {
    this(paramInputStream, null);
  }

  public AddressListParser(InputStream paramInputStream, String paramString)
  {
    try
    {
      this.jj_input_stream = new SimpleCharStream(paramInputStream, paramString, 1, 1);
      this.token_source = new AddressListParserTokenManager(this.jj_input_stream);
      this.token = new Token();
      this.jj_ntk = -1;
      this.jj_gen = 0;
      for (int i = 0; i < 22; i++)
        this.jj_la1[i] = -1;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new RuntimeException(localUnsupportedEncodingException);
    }
    for (int j = 0; j < this.jj_2_rtns.length; j++)
      this.jj_2_rtns[j] = new JJCalls();
  }

  public AddressListParser(Reader paramReader)
  {
    this.jj_input_stream = new SimpleCharStream(paramReader, 1, 1);
    this.token_source = new AddressListParserTokenManager(this.jj_input_stream);
    this.token = new Token();
    this.jj_ntk = -1;
    this.jj_gen = 0;
    for (int i = 0; i < 22; i++)
      this.jj_la1[i] = -1;
    for (int j = 0; j < this.jj_2_rtns.length; j++)
      this.jj_2_rtns[j] = new JJCalls();
  }

  public AddressListParser(AddressListParserTokenManager paramAddressListParserTokenManager)
  {
    this.token_source = paramAddressListParserTokenManager;
    this.token = new Token();
    this.jj_ntk = -1;
    this.jj_gen = 0;
    for (int i = 0; i < 22; i++)
      this.jj_la1[i] = -1;
    for (int j = 0; j < this.jj_2_rtns.length; j++)
      this.jj_2_rtns[j] = new JJCalls();
  }

  // ERROR //
  private boolean jj_2_1(int paramInt)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_2
    //   2: aload_0
    //   3: iload_1
    //   4: putfield 127	org/apache/james/mime4j/field/address/AddressListParser:jj_la	I
    //   7: aload_0
    //   8: getfield 108	org/apache/james/mime4j/field/address/AddressListParser:token	Lorg/apache/james/mime4j/field/address/Token;
    //   11: astore_3
    //   12: aload_0
    //   13: aload_3
    //   14: putfield 129	org/apache/james/mime4j/field/address/AddressListParser:jj_scanpos	Lorg/apache/james/mime4j/field/address/Token;
    //   17: aload_0
    //   18: aload_3
    //   19: putfield 131	org/apache/james/mime4j/field/address/AddressListParser:jj_lastpos	Lorg/apache/james/mime4j/field/address/Token;
    //   22: aload_0
    //   23: invokespecial 135	org/apache/james/mime4j/field/address/AddressListParser:jj_3_1	()Z
    //   26: istore 6
    //   28: iload 6
    //   30: ifne +11 -> 41
    //   33: aload_0
    //   34: iconst_0
    //   35: iload_1
    //   36: invokespecial 139	org/apache/james/mime4j/field/address/AddressListParser:jj_save	(II)V
    //   39: iload_2
    //   40: ireturn
    //   41: iconst_0
    //   42: istore_2
    //   43: goto -10 -> 33
    //   46: astore 5
    //   48: aload_0
    //   49: iconst_0
    //   50: iload_1
    //   51: invokespecial 139	org/apache/james/mime4j/field/address/AddressListParser:jj_save	(II)V
    //   54: iload_2
    //   55: ireturn
    //   56: astore 4
    //   58: aload_0
    //   59: iconst_0
    //   60: iload_1
    //   61: invokespecial 139	org/apache/james/mime4j/field/address/AddressListParser:jj_save	(II)V
    //   64: aload 4
    //   66: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   22	28	46	org/apache/james/mime4j/field/address/AddressListParser$LookaheadSuccess
    //   22	28	56	finally
  }

  // ERROR //
  private boolean jj_2_2(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: iload_1
    //   2: putfield 127	org/apache/james/mime4j/field/address/AddressListParser:jj_la	I
    //   5: aload_0
    //   6: getfield 108	org/apache/james/mime4j/field/address/AddressListParser:token	Lorg/apache/james/mime4j/field/address/Token;
    //   9: astore_2
    //   10: aload_0
    //   11: aload_2
    //   12: putfield 129	org/apache/james/mime4j/field/address/AddressListParser:jj_scanpos	Lorg/apache/james/mime4j/field/address/Token;
    //   15: aload_0
    //   16: aload_2
    //   17: putfield 131	org/apache/james/mime4j/field/address/AddressListParser:jj_lastpos	Lorg/apache/james/mime4j/field/address/Token;
    //   20: aload_0
    //   21: invokespecial 143	org/apache/james/mime4j/field/address/AddressListParser:jj_3_2	()Z
    //   24: istore 5
    //   26: iload 5
    //   28: ifne +15 -> 43
    //   31: iconst_1
    //   32: istore 6
    //   34: aload_0
    //   35: iconst_1
    //   36: iload_1
    //   37: invokespecial 139	org/apache/james/mime4j/field/address/AddressListParser:jj_save	(II)V
    //   40: iload 6
    //   42: ireturn
    //   43: iconst_0
    //   44: istore 6
    //   46: goto -12 -> 34
    //   49: astore 4
    //   51: aload_0
    //   52: iconst_1
    //   53: iload_1
    //   54: invokespecial 139	org/apache/james/mime4j/field/address/AddressListParser:jj_save	(II)V
    //   57: iconst_1
    //   58: ireturn
    //   59: astore_3
    //   60: aload_0
    //   61: iconst_1
    //   62: iload_1
    //   63: invokespecial 139	org/apache/james/mime4j/field/address/AddressListParser:jj_save	(II)V
    //   66: aload_3
    //   67: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   20	26	49	org/apache/james/mime4j/field/address/AddressListParser$LookaheadSuccess
    //   20	26	59	finally
  }

  private boolean jj_3R_10()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3R_12())
    {
      this.jj_scanpos = localToken;
      if (jj_scan_token(18))
        return true;
    }
    return false;
  }

  private boolean jj_3R_11()
  {
    Token localToken1 = this.jj_scanpos;
    if (jj_scan_token(9))
      this.jj_scanpos = localToken1;
    Token localToken2 = this.jj_scanpos;
    if (jj_scan_token(14))
    {
      this.jj_scanpos = localToken2;
      if (jj_scan_token(31))
        return true;
    }
    return false;
  }

  private boolean jj_3R_12()
  {
    if (jj_scan_token(14))
      return true;
    Token localToken;
    do
      localToken = this.jj_scanpos;
    while (!jj_3R_13());
    this.jj_scanpos = localToken;
    return false;
  }

  private boolean jj_3R_13()
  {
    Token localToken = this.jj_scanpos;
    if (jj_scan_token(9))
      this.jj_scanpos = localToken;
    return jj_scan_token(14);
  }

  private boolean jj_3R_8()
  {
    if (jj_3R_9());
    do
      return true;
    while ((jj_scan_token(8)) || (jj_3R_10()));
    return false;
  }

  private boolean jj_3R_9()
  {
    Token localToken1 = this.jj_scanpos;
    if (jj_scan_token(14))
    {
      this.jj_scanpos = localToken1;
      if (jj_scan_token(31))
        return true;
    }
    Token localToken2;
    do
      localToken2 = this.jj_scanpos;
    while (!jj_3R_11());
    this.jj_scanpos = localToken2;
    return false;
  }

  private boolean jj_3_1()
  {
    return jj_3R_8();
  }

  private boolean jj_3_2()
  {
    return jj_3R_8();
  }

  private void jj_add_error_token(int paramInt1, int paramInt2)
  {
    if (paramInt2 >= 100);
    label170: 
    do
    {
      do
      {
        return;
        if (paramInt2 != 1 + this.jj_endpos)
          continue;
        int[] arrayOfInt3 = this.jj_lasttokens;
        int k = this.jj_endpos;
        this.jj_endpos = (k + 1);
        arrayOfInt3[k] = paramInt1;
        return;
      }
      while (this.jj_endpos == 0);
      this.jj_expentry = new int[this.jj_endpos];
      for (int i = 0; i < this.jj_endpos; i++)
        this.jj_expentry[i] = this.jj_lasttokens[i];
      Iterator localIterator = this.jj_expentries.iterator();
      while (localIterator.hasNext())
      {
        int[] arrayOfInt2 = (int[])(int[])localIterator.next();
        if (arrayOfInt2.length != this.jj_expentry.length)
          continue;
        for (int j = 0; ; j++)
        {
          if (j >= this.jj_expentry.length)
            break label170;
          if (arrayOfInt2[j] != this.jj_expentry[j])
            break;
        }
        this.jj_expentries.add(this.jj_expentry);
      }
    }
    while (paramInt2 == 0);
    int[] arrayOfInt1 = this.jj_lasttokens;
    this.jj_endpos = paramInt2;
    arrayOfInt1[(paramInt2 - 1)] = paramInt1;
  }

  private Token jj_consume_token(int paramInt)
    throws ParseException
  {
    Token localToken1 = this.token;
    if (localToken1.next != null)
    {
      this.token = this.token.next;
      this.jj_ntk = -1;
      if (this.token.kind != paramInt)
        break label170;
      this.jj_gen = (1 + this.jj_gen);
      int i = 1 + this.jj_gc;
      this.jj_gc = i;
      if (i > 100)
        this.jj_gc = 0;
    }
    else
    {
      for (int j = 0; ; j++)
      {
        if (j >= this.jj_2_rtns.length)
          break label165;
        JJCalls localJJCalls = this.jj_2_rtns[j];
        while (true)
          if (localJJCalls != null)
          {
            if (localJJCalls.gen < this.jj_gen)
              localJJCalls.first = null;
            localJJCalls = localJJCalls.next;
            continue;
            Token localToken2 = this.token;
            Token localToken3 = this.token_source.getNextToken();
            localToken2.next = localToken3;
            this.token = localToken3;
            break;
          }
      }
    }
    label165: return this.token;
    label170: this.token = localToken1;
    this.jj_kind = paramInt;
    throw generateParseException();
  }

  private static void jj_la1_init_0()
  {
    jj_la1_0 = new int[] { 2, -2147467200, 8, -2147467200, 80, -2147467200, -2147467200, -2147467200, 8, -2147467200, 256, 264, 8, -2147467264, -2147467264, -2147467264, -2147466752, 512, -2147467264, 16896, 512, 278528 };
  }

  private static void jj_la1_init_1()
  {
    jj_la1_1 = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
  }

  private int jj_ntk()
  {
    Token localToken1 = this.token.next;
    this.jj_nt = localToken1;
    if (localToken1 == null)
    {
      Token localToken2 = this.token;
      Token localToken3 = this.token_source.getNextToken();
      localToken2.next = localToken3;
      int j = localToken3.kind;
      this.jj_ntk = j;
      return j;
    }
    int i = this.jj_nt.kind;
    this.jj_ntk = i;
    return i;
  }

  private void jj_rescan_token()
  {
    this.jj_rescan = true;
    int i = 0;
    while (true)
    {
      if (i < 2);
      try
      {
        JJCalls localJJCalls = this.jj_2_rtns[i];
        if (localJJCalls.gen > this.jj_gen)
        {
          this.jj_la = localJJCalls.arg;
          Token localToken = localJJCalls.first;
          this.jj_scanpos = localToken;
          this.jj_lastpos = localToken;
          switch (i)
          {
          default:
          case 0:
          case 1:
          }
        }
        while (true)
        {
          localJJCalls = localJJCalls.next;
          if (localJJCalls != null)
            break;
          break label114;
          jj_3_1();
          continue;
          jj_3_2();
        }
        this.jj_rescan = false;
        return;
        label114: i++;
      }
      catch (LookaheadSuccess localLookaheadSuccess)
      {
        break label114;
      }
    }
  }

  private void jj_save(int paramInt1, int paramInt2)
  {
    for (Object localObject = this.jj_2_rtns[paramInt1]; ; localObject = ((JJCalls)localObject).next)
    {
      if (((JJCalls)localObject).gen > this.jj_gen)
      {
        if (((JJCalls)localObject).next != null)
          continue;
        JJCalls localJJCalls = new JJCalls();
        ((JJCalls)localObject).next = localJJCalls;
        localObject = localJJCalls;
      }
      ((JJCalls)localObject).gen = (paramInt2 + this.jj_gen - this.jj_la);
      ((JJCalls)localObject).first = this.token;
      ((JJCalls)localObject).arg = paramInt2;
      return;
    }
  }

  private boolean jj_scan_token(int paramInt)
  {
    if (this.jj_scanpos == this.jj_lastpos)
    {
      this.jj_la = (-1 + this.jj_la);
      if (this.jj_scanpos.next == null)
      {
        Token localToken3 = this.jj_scanpos;
        Token localToken4 = this.token_source.getNextToken();
        localToken3.next = localToken4;
        this.jj_scanpos = localToken4;
        this.jj_lastpos = localToken4;
      }
    }
    while (this.jj_rescan)
    {
      int i = 0;
      Token localToken1 = this.token;
      while (true)
        if ((localToken1 != null) && (localToken1 != this.jj_scanpos))
        {
          i++;
          localToken1 = localToken1.next;
          continue;
          Token localToken2 = this.jj_scanpos.next;
          this.jj_scanpos = localToken2;
          this.jj_lastpos = localToken2;
          break;
          this.jj_scanpos = this.jj_scanpos.next;
          break;
        }
      if (localToken1 == null)
        break;
      jj_add_error_token(paramInt, i);
    }
    if (this.jj_scanpos.kind != paramInt)
      return true;
    if ((this.jj_la == 0) && (this.jj_scanpos == this.jj_lastpos))
      throw this.jj_ls;
    return false;
  }

  public static void main(String[] paramArrayOfString)
    throws ParseException
  {
    try
    {
      while (true)
      {
        AddressListParser localAddressListParser = new AddressListParser(System.in);
        localAddressListParser.parseLine();
        ((SimpleNode)localAddressListParser.jjtree.rootNode()).dump("> ");
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void ReInit(InputStream paramInputStream)
  {
    ReInit(paramInputStream, null);
  }

  public void ReInit(InputStream paramInputStream, String paramString)
  {
    try
    {
      this.jj_input_stream.ReInit(paramInputStream, paramString, 1, 1);
      this.token_source.ReInit(this.jj_input_stream);
      this.token = new Token();
      this.jj_ntk = -1;
      this.jjtree.reset();
      this.jj_gen = 0;
      for (int i = 0; i < 22; i++)
        this.jj_la1[i] = -1;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new RuntimeException(localUnsupportedEncodingException);
    }
    for (int j = 0; j < this.jj_2_rtns.length; j++)
      this.jj_2_rtns[j] = new JJCalls();
  }

  public void ReInit(Reader paramReader)
  {
    this.jj_input_stream.ReInit(paramReader, 1, 1);
    this.token_source.ReInit(this.jj_input_stream);
    this.token = new Token();
    this.jj_ntk = -1;
    this.jjtree.reset();
    this.jj_gen = 0;
    for (int i = 0; i < 22; i++)
      this.jj_la1[i] = -1;
    for (int j = 0; j < this.jj_2_rtns.length; j++)
      this.jj_2_rtns[j] = new JJCalls();
  }

  public void ReInit(AddressListParserTokenManager paramAddressListParserTokenManager)
  {
    this.token_source = paramAddressListParserTokenManager;
    this.token = new Token();
    this.jj_ntk = -1;
    this.jjtree.reset();
    this.jj_gen = 0;
    for (int i = 0; i < 22; i++)
      this.jj_la1[i] = -1;
    for (int j = 0; j < this.jj_2_rtns.length; j++)
      this.jj_2_rtns[j] = new JJCalls();
  }

  public final void addr_spec()
    throws ParseException
  {
    ASTaddr_spec localASTaddr_spec = new ASTaddr_spec(9);
    int i = 1;
    this.jjtree.openNodeScope(localASTaddr_spec);
    jjtreeOpenNodeScope(localASTaddr_spec);
    while (true)
    {
      try
      {
        local_part();
        jj_consume_token(8);
        domain();
        return;
      }
      catch (Throwable localThrowable)
      {
        if (i != 0)
        {
          this.jjtree.clearNodeScope(localASTaddr_spec);
          i = 0;
          if (!(localThrowable instanceof RuntimeException))
            break;
          throw ((RuntimeException)localThrowable);
        }
      }
      finally
      {
        if (i == 0)
          continue;
        this.jjtree.closeNodeScope(localASTaddr_spec, true);
        jjtreeCloseNodeScope(localASTaddr_spec);
      }
      this.jjtree.popNode();
    }
    if ((localThrowable instanceof ParseException))
      throw ((ParseException)localThrowable);
    throw ((Error)localThrowable);
  }

  public final void address()
    throws ParseException
  {
    ASTaddress localASTaddress = new ASTaddress(2);
    int i = 1;
    this.jjtree.openNodeScope(localASTaddress);
    jjtreeOpenNodeScope(localASTaddress);
    int j;
    int k;
    while (true)
    {
      try
      {
        if (!jj_2_1(2147483647))
          continue;
        addr_spec();
        return;
        if (this.jj_ntk == -1)
        {
          j = jj_ntk();
          break label264;
          this.jj_la1[5] = this.jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      catch (Throwable localThrowable)
      {
        if (i == 0)
          break label233;
        this.jjtree.clearNodeScope(localASTaddress);
        i = 0;
        if (!(localThrowable instanceof RuntimeException))
          break;
        throw ((RuntimeException)localThrowable);
      }
      finally
      {
        if (i == 0)
          continue;
        this.jjtree.closeNodeScope(localASTaddress, true);
        jjtreeCloseNodeScope(localASTaddress);
      }
      j = this.jj_ntk;
      break label264;
      angle_addr();
      continue;
      phrase();
      if (this.jj_ntk == -1)
      {
        k = jj_ntk();
        break label300;
        this.jj_la1[4] = this.jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      k = this.jj_ntk;
      break label300;
      group_body();
      continue;
      angle_addr();
      continue;
      label233: this.jjtree.popNode();
    }
    if ((localThrowable instanceof ParseException))
      throw ((ParseException)localThrowable);
    throw ((Error)localThrowable);
    label264: switch (j)
    {
    default:
    case 6:
    case 14:
    case 31:
    }
    label300: switch (k)
    {
    case 5:
    default:
    case 4:
    case 6:
    }
  }

  public final void address_list()
    throws ParseException
  {
    ASTaddress_list localASTaddress_list = new ASTaddress_list(1);
    int i = 1;
    this.jjtree.openNodeScope(localASTaddress_list);
    jjtreeOpenNodeScope(localASTaddress_list);
    int j;
    int k;
    int m;
    while (true)
    {
      try
      {
        if (this.jj_ntk != -1)
          continue;
        j = jj_ntk();
        break label256;
        this.jj_la1[1] = this.jj_gen;
        if (this.jj_ntk == -1)
        {
          k = jj_ntk();
          break label292;
          this.jj_la1[2] = this.jj_gen;
          return;
          j = this.jj_ntk;
          break label256;
          address();
          continue;
        }
      }
      catch (Throwable localThrowable)
      {
        if (i == 0)
          break label225;
        this.jjtree.clearNodeScope(localASTaddress_list);
        i = 0;
        if (!(localThrowable instanceof RuntimeException))
          break;
        throw ((RuntimeException)localThrowable);
      }
      finally
      {
        if (i == 0)
          continue;
        this.jjtree.closeNodeScope(localASTaddress_list, true);
        jjtreeCloseNodeScope(localASTaddress_list);
      }
      k = this.jj_ntk;
      break label292;
      jj_consume_token(3);
      if (this.jj_ntk == -1)
      {
        m = jj_ntk();
        break label312;
        this.jj_la1[3] = this.jj_gen;
        continue;
      }
      m = this.jj_ntk;
      break label312;
      address();
      continue;
      label225: this.jjtree.popNode();
    }
    if ((localThrowable instanceof ParseException))
      throw ((ParseException)localThrowable);
    throw ((Error)localThrowable);
    label256: switch (j)
    {
    default:
    case 6:
    case 14:
    case 31:
    }
    label292: switch (k)
    {
    default:
    case 3:
    }
    label312: switch (m)
    {
    default:
    case 6:
    case 14:
    case 31:
    }
  }

  public final void angle_addr()
    throws ParseException
  {
    ASTangle_addr localASTangle_addr = new ASTangle_addr(6);
    int i = 1;
    this.jjtree.openNodeScope(localASTangle_addr);
    jjtreeOpenNodeScope(localASTangle_addr);
    int j;
    while (true)
    {
      try
      {
        jj_consume_token(6);
        if (this.jj_ntk != -1)
          continue;
        j = jj_ntk();
        break label188;
        this.jj_la1[10] = this.jj_gen;
        addr_spec();
        jj_consume_token(7);
        return;
        j = this.jj_ntk;
        break label188;
        route();
        continue;
      }
      catch (Throwable localThrowable)
      {
        if (i != 0)
        {
          this.jjtree.clearNodeScope(localASTangle_addr);
          i = 0;
          if (!(localThrowable instanceof RuntimeException))
            break;
          throw ((RuntimeException)localThrowable);
        }
      }
      finally
      {
        if (i == 0)
          continue;
        this.jjtree.closeNodeScope(localASTangle_addr, true);
        jjtreeCloseNodeScope(localASTangle_addr);
      }
      this.jjtree.popNode();
    }
    if ((localThrowable instanceof ParseException))
      throw ((ParseException)localThrowable);
    throw ((Error)localThrowable);
    label188: switch (j)
    {
    default:
    case 8:
    }
  }

  public final void disable_tracing()
  {
  }

  public final void domain()
    throws ParseException
  {
    ASTdomain localASTdomain = new ASTdomain(11);
    this.jjtree.openNodeScope(localASTdomain);
    jjtreeOpenNodeScope(localASTdomain);
    try
    {
      if (this.jj_ntk == -1)
      {
        i = jj_ntk();
        break label260;
        this.jj_la1[21] = this.jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    finally
    {
      if (1 != 0)
      {
        this.jjtree.closeNodeScope(localASTdomain, true);
        jjtreeCloseNodeScope(localASTdomain);
      }
    }
    int i = this.jj_ntk;
    break label260;
    Token localToken = jj_consume_token(14);
    int j;
    int k;
    if (this.jj_ntk == -1)
    {
      j = jj_ntk();
      break label288;
      this.jj_la1[19] = this.jj_gen;
    }
    else
    {
      while (true)
      {
        if (1 != 0)
        {
          this.jjtree.closeNodeScope(localASTdomain, true);
          jjtreeCloseNodeScope(localASTdomain);
        }
        return;
        j = this.jj_ntk;
        break label288;
        if (this.jj_ntk == -1)
        {
          k = jj_ntk();
          break label316;
          this.jj_la1[20] = this.jj_gen;
        }
        while (localToken.image.charAt(-1 + localToken.image.length()) != '.')
        {
          throw new ParseException("Atoms in domain names must be separated by '.'");
          k = this.jj_ntk;
          break label316;
          localToken = jj_consume_token(9);
        }
        localToken = jj_consume_token(14);
        break;
        jj_consume_token(18);
      }
      label260: switch (i)
      {
      default:
      case 14:
      case 18:
      }
    }
    label288: switch (j)
    {
    default:
    case 9:
    case 14:
    }
    label316: switch (k)
    {
    default:
    case 9:
    }
  }

  public final void enable_tracing()
  {
  }

  public ParseException generateParseException()
  {
    this.jj_expentries.clear();
    boolean[] arrayOfBoolean = new boolean[34];
    if (this.jj_kind >= 0)
    {
      arrayOfBoolean[this.jj_kind] = true;
      this.jj_kind = -1;
    }
    for (int i = 0; i < 22; i++)
    {
      if (this.jj_la1[i] != this.jj_gen)
        continue;
      for (int m = 0; m < 32; m++)
      {
        if ((jj_la1_0[i] & 1 << m) != 0)
          arrayOfBoolean[m] = true;
        if ((jj_la1_1[i] & 1 << m) == 0)
          continue;
        arrayOfBoolean[(m + 32)] = true;
      }
    }
    for (int j = 0; j < 34; j++)
    {
      if (arrayOfBoolean[j] == 0)
        continue;
      this.jj_expentry = new int[1];
      this.jj_expentry[0] = j;
      this.jj_expentries.add(this.jj_expentry);
    }
    this.jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] arrayOfInt = new int[this.jj_expentries.size()][];
    for (int k = 0; k < this.jj_expentries.size(); k++)
      arrayOfInt[k] = ((int[])this.jj_expentries.get(k));
    return new ParseException(this.token, arrayOfInt, tokenImage);
  }

  public final Token getNextToken()
  {
    if (this.token.next != null);
    Token localToken2;
    for (this.token = this.token.next; ; this.token = localToken2)
    {
      this.jj_ntk = -1;
      this.jj_gen = (1 + this.jj_gen);
      return this.token;
      Token localToken1 = this.token;
      localToken2 = this.token_source.getNextToken();
      localToken1.next = localToken2;
    }
  }

  public final Token getToken(int paramInt)
  {
    Token localToken1 = this.token;
    int i = 0;
    Object localObject = localToken1;
    if (i < paramInt)
    {
      Token localToken2;
      if (((Token)localObject).next != null)
        localToken2 = ((Token)localObject).next;
      while (true)
      {
        i++;
        localObject = localToken2;
        break;
        localToken2 = this.token_source.getNextToken();
        ((Token)localObject).next = localToken2;
      }
    }
    return (Token)localObject;
  }

  public final void group_body()
    throws ParseException
  {
    ASTgroup_body localASTgroup_body = new ASTgroup_body(5);
    int i = 1;
    this.jjtree.openNodeScope(localASTgroup_body);
    jjtreeOpenNodeScope(localASTgroup_body);
    int j;
    int k;
    int m;
    while (true)
    {
      try
      {
        jj_consume_token(4);
        if (this.jj_ntk != -1)
          continue;
        j = jj_ntk();
        break label271;
        this.jj_la1[7] = this.jj_gen;
        if (this.jj_ntk == -1)
        {
          k = jj_ntk();
          break label308;
          this.jj_la1[8] = this.jj_gen;
          jj_consume_token(5);
          return;
          j = this.jj_ntk;
          break label271;
          mailbox();
          continue;
        }
      }
      catch (Throwable localThrowable)
      {
        if (i == 0)
          break label240;
        this.jjtree.clearNodeScope(localASTgroup_body);
        i = 0;
        if (!(localThrowable instanceof RuntimeException))
          break;
        throw ((RuntimeException)localThrowable);
      }
      finally
      {
        if (i == 0)
          continue;
        this.jjtree.closeNodeScope(localASTgroup_body, true);
        jjtreeCloseNodeScope(localASTgroup_body);
      }
      k = this.jj_ntk;
      break label308;
      jj_consume_token(3);
      if (this.jj_ntk == -1)
      {
        m = jj_ntk();
        break label328;
        this.jj_la1[9] = this.jj_gen;
        continue;
      }
      m = this.jj_ntk;
      break label328;
      mailbox();
      continue;
      label240: this.jjtree.popNode();
    }
    if ((localThrowable instanceof ParseException))
      throw ((ParseException)localThrowable);
    throw ((Error)localThrowable);
    label271: switch (j)
    {
    default:
    case 6:
    case 14:
    case 31:
    }
    label308: switch (k)
    {
    default:
    case 3:
    }
    label328: switch (m)
    {
    default:
    case 6:
    case 14:
    case 31:
    }
  }

  void jjtreeCloseNodeScope(Node paramNode)
  {
    ((SimpleNode)paramNode).lastToken = getToken(0);
  }

  void jjtreeOpenNodeScope(Node paramNode)
  {
    ((SimpleNode)paramNode).firstToken = getToken(1);
  }

  public final void local_part()
    throws ParseException
  {
    ASTlocal_part localASTlocal_part = new ASTlocal_part(10);
    this.jjtree.openNodeScope(localASTlocal_part);
    jjtreeOpenNodeScope(localASTlocal_part);
    try
    {
      if (this.jj_ntk == -1)
      {
        i = jj_ntk();
        break label337;
        this.jj_la1[15] = this.jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    finally
    {
      if (1 != 0)
      {
        this.jjtree.closeNodeScope(localASTlocal_part, true);
        jjtreeCloseNodeScope(localASTlocal_part);
      }
    }
    int i = this.jj_ntk;
    break label337;
    Object localObject2 = jj_consume_token(14);
    int j;
    int k;
    int m;
    while (true)
    {
      if (this.jj_ntk == -1)
      {
        j = jj_ntk();
        break;
        this.jj_la1[16] = this.jj_gen;
        if (1 != 0)
        {
          this.jjtree.closeNodeScope(localASTlocal_part, true);
          jjtreeCloseNodeScope(localASTlocal_part);
        }
        return;
        localObject2 = jj_consume_token(31);
        continue;
      }
      j = this.jj_ntk;
      break;
      if (this.jj_ntk == -1)
      {
        k = jj_ntk();
        break label400;
        this.jj_la1[17] = this.jj_gen;
      }
      while ((((Token)localObject2).kind == 31) || (((Token)localObject2).image.charAt(-1 + ((Token)localObject2).image.length()) != '.'))
      {
        throw new ParseException("Words in local part must be separated by '.'");
        k = this.jj_ntk;
        break label400;
        localObject2 = jj_consume_token(9);
      }
      if (this.jj_ntk == -1)
      {
        m = jj_ntk();
        break label420;
        this.jj_la1[18] = this.jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      m = this.jj_ntk;
      break label420;
      localObject2 = jj_consume_token(14);
      continue;
      Token localToken = jj_consume_token(31);
      localObject2 = localToken;
    }
    label337: switch (i)
    {
    default:
    case 14:
    case 31:
    }
    switch (j)
    {
    default:
    case 9:
    case 14:
    case 31:
    }
    label400: switch (k)
    {
    default:
    case 9:
    }
    label420: switch (m)
    {
    default:
    case 14:
    case 31:
    }
  }

  public final void mailbox()
    throws ParseException
  {
    ASTmailbox localASTmailbox = new ASTmailbox(3);
    int i = 1;
    this.jjtree.openNodeScope(localASTmailbox);
    jjtreeOpenNodeScope(localASTmailbox);
    int j;
    while (true)
    {
      try
      {
        if (!jj_2_2(2147483647))
          continue;
        addr_spec();
        return;
        if (this.jj_ntk == -1)
        {
          j = jj_ntk();
          break label204;
          this.jj_la1[6] = this.jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      catch (Throwable localThrowable)
      {
        if (i == 0)
          break label173;
        this.jjtree.clearNodeScope(localASTmailbox);
        i = 0;
        if (!(localThrowable instanceof RuntimeException))
          break;
        throw ((RuntimeException)localThrowable);
      }
      finally
      {
        if (i == 0)
          continue;
        this.jjtree.closeNodeScope(localASTmailbox, true);
        jjtreeCloseNodeScope(localASTmailbox);
      }
      j = this.jj_ntk;
      break label204;
      angle_addr();
      continue;
      name_addr();
      continue;
      label173: this.jjtree.popNode();
    }
    if ((localThrowable instanceof ParseException))
      throw ((ParseException)localThrowable);
    throw ((Error)localThrowable);
    label204: switch (j)
    {
    default:
    case 6:
    case 14:
    case 31:
    }
  }

  public final void name_addr()
    throws ParseException
  {
    ASTname_addr localASTname_addr = new ASTname_addr(4);
    int i = 1;
    this.jjtree.openNodeScope(localASTname_addr);
    jjtreeOpenNodeScope(localASTname_addr);
    while (true)
    {
      try
      {
        phrase();
        angle_addr();
        return;
      }
      catch (Throwable localThrowable)
      {
        if (i != 0)
        {
          this.jjtree.clearNodeScope(localASTname_addr);
          i = 0;
          if (!(localThrowable instanceof RuntimeException))
            break;
          throw ((RuntimeException)localThrowable);
        }
      }
      finally
      {
        if (i == 0)
          continue;
        this.jjtree.closeNodeScope(localASTname_addr, true);
        jjtreeCloseNodeScope(localASTname_addr);
      }
      this.jjtree.popNode();
    }
    if ((localThrowable instanceof ParseException))
      throw ((ParseException)localThrowable);
    throw ((Error)localThrowable);
  }

  public ASTaddress parseAddress()
    throws ParseException
  {
    try
    {
      parseAddress0();
      ASTaddress localASTaddress = (ASTaddress)this.jjtree.rootNode();
      return localASTaddress;
    }
    catch (TokenMgrError localTokenMgrError)
    {
    }
    throw new ParseException(localTokenMgrError.getMessage());
  }

  public final void parseAddress0()
    throws ParseException
  {
    address();
    jj_consume_token(0);
  }

  public ASTaddress_list parseAddressList()
    throws ParseException
  {
    try
    {
      parseAddressList0();
      ASTaddress_list localASTaddress_list = (ASTaddress_list)this.jjtree.rootNode();
      return localASTaddress_list;
    }
    catch (TokenMgrError localTokenMgrError)
    {
    }
    throw new ParseException(localTokenMgrError.getMessage());
  }

  public final void parseAddressList0()
    throws ParseException
  {
    address_list();
    jj_consume_token(0);
  }

  public final void parseLine()
    throws ParseException
  {
    address_list();
    int i;
    if (this.jj_ntk == -1)
    {
      i = jj_ntk();
      switch (i)
      {
      default:
        this.jj_la1[0] = this.jj_gen;
      case 1:
      }
    }
    while (true)
    {
      jj_consume_token(2);
      return;
      i = this.jj_ntk;
      break;
      jj_consume_token(1);
    }
  }

  public ASTmailbox parseMailbox()
    throws ParseException
  {
    try
    {
      parseMailbox0();
      ASTmailbox localASTmailbox = (ASTmailbox)this.jjtree.rootNode();
      return localASTmailbox;
    }
    catch (TokenMgrError localTokenMgrError)
    {
    }
    throw new ParseException(localTokenMgrError.getMessage());
  }

  public final void parseMailbox0()
    throws ParseException
  {
    mailbox();
    jj_consume_token(0);
  }

  public final void phrase()
    throws ParseException
  {
    ASTphrase localASTphrase = new ASTphrase(8);
    this.jjtree.openNodeScope(localASTphrase);
    jjtreeOpenNodeScope(localASTphrase);
    try
    {
      if (this.jj_ntk == -1)
      {
        i = jj_ntk();
        break label166;
        this.jj_la1[13] = this.jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    finally
    {
      if (1 != 0)
      {
        this.jjtree.closeNodeScope(localASTphrase, true);
        jjtreeCloseNodeScope(localASTphrase);
      }
    }
    int i = this.jj_ntk;
    break label166;
    jj_consume_token(14);
    while (this.jj_ntk == -1)
    {
      j = jj_ntk();
      break label192;
      this.jj_la1[14] = this.jj_gen;
      if (1 != 0)
      {
        this.jjtree.closeNodeScope(localASTphrase, true);
        jjtreeCloseNodeScope(localASTphrase);
      }
      return;
      jj_consume_token(31);
    }
    int j = this.jj_ntk;
    break label192;
    label166: switch (i)
    {
    default:
    case 14:
    case 31:
    }
    label192: switch (j)
    {
    case 14:
    case 31:
    }
  }

  public final void route()
    throws ParseException
  {
    ASTroute localASTroute = new ASTroute(7);
    int i = 1;
    this.jjtree.openNodeScope(localASTroute);
    jjtreeOpenNodeScope(localASTroute);
    int j;
    int k;
    while (true)
    {
      try
      {
        jj_consume_token(8);
        domain();
        if (this.jj_ntk != -1)
          continue;
        j = jj_ntk();
        break label237;
        this.jj_la1[11] = this.jj_gen;
        jj_consume_token(4);
        return;
        j = this.jj_ntk;
        break label237;
        jj_consume_token(3);
        if (this.jj_ntk == -1)
        {
          k = jj_ntk();
          break label264;
          this.jj_la1[12] = this.jj_gen;
          jj_consume_token(8);
          domain();
          continue;
        }
      }
      catch (Throwable localThrowable)
      {
        if (i == 0)
          break label206;
        this.jjtree.clearNodeScope(localASTroute);
        i = 0;
        if (!(localThrowable instanceof RuntimeException))
          break;
        throw ((RuntimeException)localThrowable);
      }
      finally
      {
        if (i == 0)
          continue;
        this.jjtree.closeNodeScope(localASTroute, true);
        jjtreeCloseNodeScope(localASTroute);
      }
      k = this.jj_ntk;
      break label264;
      label206: this.jjtree.popNode();
    }
    if ((localThrowable instanceof ParseException))
      throw ((ParseException)localThrowable);
    throw ((Error)localThrowable);
    label237: switch (j)
    {
    default:
    case 3:
    case 8:
    }
    label264: switch (k)
    {
    case 3:
    }
  }

  static final class JJCalls
  {
    int arg;
    Token first;
    int gen;
    JJCalls next;
  }

  private static final class LookaheadSuccess extends Error
  {
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.address.AddressListParser
 * JD-Core Version:    0.6.0
 */