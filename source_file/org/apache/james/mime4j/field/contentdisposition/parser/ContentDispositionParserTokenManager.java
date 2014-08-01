package org.apache.james.mime4j.field.contentdisposition.parser;

import java.io.IOException;
import java.io.PrintStream;

public class ContentDispositionParserTokenManager
  implements ContentDispositionParserConstants
{
  static int commentNest;
  static final long[] jjbitVec0 = { 0L, 0L, -1L, -1L };
  public static final int[] jjnewLexState;
  static final int[] jjnextStates = new int[0];
  public static final String[] jjstrLiteralImages = { "", "\r", "\n", ";", "=", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null };
  static final long[] jjtoMore;
  static final long[] jjtoSkip;
  static final long[] jjtoSpecial;
  static final long[] jjtoToken;
  public static final String[] lexStateNames = { "DEFAULT", "INCOMMENT", "NESTED_COMMENT", "INQUOTEDSTRING" };
  protected char curChar;
  int curLexState = 0;
  public PrintStream debugStream = System.out;
  int defaultLexState = 0;
  private StringBuilder image = this.jjimage;
  protected SimpleCharStream input_stream;
  private final StringBuilder jjimage = new StringBuilder();
  private int jjimageLen;
  int jjmatchedKind;
  int jjmatchedPos;
  int jjnewStateCnt;
  int jjround;
  private final int[] jjrounds = new int[3];
  private final int[] jjstateSet = new int[6];
  private int lengthOfMatch;

  static
  {
    jjnewLexState = new int[] { -1, -1, -1, -1, -1, -1, 1, 0, -1, 2, -1, -1, -1, -1, -1, 3, -1, -1, 0, -1, -1, -1, -1 };
    jjtoToken = new long[] { 1835039L };
    jjtoSkip = new long[] { 160L };
    jjtoSpecial = new long[] { 32L };
    jjtoMore = new long[] { 261952L };
  }

  public ContentDispositionParserTokenManager(SimpleCharStream paramSimpleCharStream)
  {
    this.input_stream = paramSimpleCharStream;
  }

  public ContentDispositionParserTokenManager(SimpleCharStream paramSimpleCharStream, int paramInt)
  {
    this(paramSimpleCharStream);
    SwitchTo(paramInt);
  }

  private void ReInitRounds()
  {
    this.jjround = -2147483647;
    int j;
    for (int i = 3; ; i = j)
    {
      j = i - 1;
      if (i <= 0)
        break;
      this.jjrounds[j] = -2147483648;
    }
  }

  private void jjAddStates(int paramInt1, int paramInt2)
  {
    while (true)
    {
      int[] arrayOfInt = this.jjstateSet;
      int i = this.jjnewStateCnt;
      this.jjnewStateCnt = (i + 1);
      arrayOfInt[i] = jjnextStates[paramInt1];
      int j = paramInt1 + 1;
      if (paramInt1 == paramInt2)
        return;
      paramInt1 = j;
    }
  }

  private void jjCheckNAdd(int paramInt)
  {
    if (this.jjrounds[paramInt] != this.jjround)
    {
      int[] arrayOfInt = this.jjstateSet;
      int i = this.jjnewStateCnt;
      this.jjnewStateCnt = (i + 1);
      arrayOfInt[i] = paramInt;
      this.jjrounds[paramInt] = this.jjround;
    }
  }

  private void jjCheckNAddTwoStates(int paramInt1, int paramInt2)
  {
    jjCheckNAdd(paramInt1);
    jjCheckNAdd(paramInt2);
  }

  private int jjMoveNfa_0(int paramInt1, int paramInt2)
  {
    int i = 0;
    this.jjnewStateCnt = 3;
    int j = 1;
    this.jjstateSet[0] = paramInt1;
    int k = 2147483647;
    while (true)
    {
      int m = 1 + this.jjround;
      this.jjround = m;
      if (m == 2147483647)
        ReInitRounds();
      if (this.curChar < '@')
      {
        long l3 = 1L << this.curChar;
        while (true)
        {
          int[] arrayOfInt3 = this.jjstateSet;
          j--;
          switch (arrayOfInt3[j])
          {
          default:
          case 3:
          case 0:
          case 1:
          case 2:
          }
          while (j == i)
          {
            if (k != 2147483647)
            {
              this.jjmatchedKind = k;
              this.jjmatchedPos = paramInt2;
              k = 2147483647;
            }
            paramInt2++;
            j = this.jjnewStateCnt;
            this.jjnewStateCnt = i;
            i = 3 - i;
            if (j != i)
              break label522;
            return paramInt2;
            if ((0xFFFFFDFF & l3) != 0L)
            {
              if (k > 20)
                k = 20;
              jjCheckNAdd(2);
            }
            while ((0x0 & l3) != 0L)
            {
              if (k > 19)
                k = 19;
              jjCheckNAdd(1);
              break;
              if ((0x200 & l3) == 0L)
                continue;
              if (k > 5)
                k = 5;
              jjCheckNAdd(0);
            }
            if ((0x200 & l3) == 0L)
              continue;
            k = 5;
            jjCheckNAdd(0);
            continue;
            if ((0x0 & l3) == 0L)
              continue;
            if (k > 19)
              k = 19;
            jjCheckNAdd(1);
            continue;
            if ((0xFFFFFDFF & l3) == 0L)
              continue;
            if (k > 20)
              k = 20;
            jjCheckNAdd(2);
          }
        }
      }
      if (this.curChar < '')
      {
        long l2 = 1L << (0x3F & this.curChar);
        label418: 
        while (true)
        {
          int[] arrayOfInt2 = this.jjstateSet;
          j--;
          switch (arrayOfInt2[j])
          {
          default:
          case 2:
          case 3:
          }
          while (true)
          {
            if (j != i)
              break label418;
            break;
            if ((0xC7FFFFFE & l2) == 0L)
              continue;
            k = 20;
            jjCheckNAdd(2);
          }
        }
      }
      int n = (0xFF & this.curChar) >> '\006';
      long l1 = 1L << (0x3F & this.curChar);
      label520: 
      while (true)
      {
        int[] arrayOfInt1 = this.jjstateSet;
        j--;
        switch (arrayOfInt1[j])
        {
        default:
        case 2:
        case 3:
        }
        while (true)
        {
          if (j != i)
            break label520;
          break;
          if ((l1 & jjbitVec0[n]) == 0L)
            continue;
          if (k > 20)
            k = 20;
          jjCheckNAdd(2);
        }
      }
      try
      {
        label522: this.curChar = this.input_stream.readChar();
      }
      catch (IOException localIOException)
      {
      }
    }
    return paramInt2;
  }

  private int jjMoveNfa_1(int paramInt1, int paramInt2)
  {
    int i = 0;
    this.jjnewStateCnt = 3;
    int j = 1;
    this.jjstateSet[0] = paramInt1;
    int k = 2147483647;
    while (true)
    {
      int m = 1 + this.jjround;
      this.jjround = m;
      if (m == 2147483647)
        ReInitRounds();
      if (this.curChar < '@')
      {
        (1L << this.curChar);
        while (true)
        {
          int[] arrayOfInt4 = this.jjstateSet;
          j--;
          switch (arrayOfInt4[j])
          {
          default:
          case 0:
          case 1:
          }
          while (j == i)
          {
            if (k != 2147483647)
            {
              this.jjmatchedKind = k;
              this.jjmatchedPos = paramInt2;
              k = 2147483647;
            }
            paramInt2++;
            j = this.jjnewStateCnt;
            this.jjnewStateCnt = i;
            i = 3 - i;
            if (j != i)
              break label457;
            return paramInt2;
            if (k <= 10)
              continue;
            k = 10;
            continue;
            if (k <= 8)
              continue;
            k = 8;
          }
        }
      }
      if (this.curChar < '')
      {
        (1L << (0x3F & this.curChar));
        label328: 
        while (true)
        {
          int[] arrayOfInt2 = this.jjstateSet;
          j--;
          switch (arrayOfInt2[j])
          {
          default:
          case 0:
          case 1:
          case 2:
          }
          while (true)
          {
            if (j != i)
              break label328;
            break;
            if (k > 10)
              k = 10;
            if (this.curChar != '\\')
              continue;
            int[] arrayOfInt3 = this.jjstateSet;
            int i1 = this.jjnewStateCnt;
            this.jjnewStateCnt = (i1 + 1);
            arrayOfInt3[i1] = 1;
            continue;
            if (k <= 8)
              continue;
            k = 8;
            continue;
            if (k <= 10)
              continue;
            k = 10;
          }
        }
      }
      int n = (0xFF & this.curChar) >> '\006';
      long l = 1L << (0x3F & this.curChar);
      label455: 
      while (true)
      {
        int[] arrayOfInt1 = this.jjstateSet;
        j--;
        switch (arrayOfInt1[j])
        {
        default:
        case 0:
        case 1:
        }
        while (true)
        {
          if (j != i)
            break label455;
          break;
          if (((l & jjbitVec0[n]) == 0L) || (k <= 10))
            continue;
          k = 10;
          continue;
          if (((l & jjbitVec0[n]) == 0L) || (k <= 8))
            continue;
          k = 8;
        }
      }
      try
      {
        label457: this.curChar = this.input_stream.readChar();
      }
      catch (IOException localIOException)
      {
      }
    }
    return paramInt2;
  }

  private int jjMoveNfa_2(int paramInt1, int paramInt2)
  {
    int i = 0;
    this.jjnewStateCnt = 3;
    int j = 1;
    this.jjstateSet[0] = paramInt1;
    int k = 2147483647;
    while (true)
    {
      int m = 1 + this.jjround;
      this.jjround = m;
      if (m == 2147483647)
        ReInitRounds();
      if (this.curChar < '@')
      {
        (1L << this.curChar);
        while (true)
        {
          int[] arrayOfInt4 = this.jjstateSet;
          j--;
          switch (arrayOfInt4[j])
          {
          default:
          case 0:
          case 1:
          }
          while (j == i)
          {
            if (k != 2147483647)
            {
              this.jjmatchedKind = k;
              this.jjmatchedPos = paramInt2;
              k = 2147483647;
            }
            paramInt2++;
            j = this.jjnewStateCnt;
            this.jjnewStateCnt = i;
            i = 3 - i;
            if (j != i)
              break label457;
            return paramInt2;
            if (k <= 14)
              continue;
            k = 14;
            continue;
            if (k <= 11)
              continue;
            k = 11;
          }
        }
      }
      if (this.curChar < '')
      {
        (1L << (0x3F & this.curChar));
        label328: 
        while (true)
        {
          int[] arrayOfInt2 = this.jjstateSet;
          j--;
          switch (arrayOfInt2[j])
          {
          default:
          case 0:
          case 1:
          case 2:
          }
          while (true)
          {
            if (j != i)
              break label328;
            break;
            if (k > 14)
              k = 14;
            if (this.curChar != '\\')
              continue;
            int[] arrayOfInt3 = this.jjstateSet;
            int i1 = this.jjnewStateCnt;
            this.jjnewStateCnt = (i1 + 1);
            arrayOfInt3[i1] = 1;
            continue;
            if (k <= 11)
              continue;
            k = 11;
            continue;
            if (k <= 14)
              continue;
            k = 14;
          }
        }
      }
      int n = (0xFF & this.curChar) >> '\006';
      long l = 1L << (0x3F & this.curChar);
      label455: 
      while (true)
      {
        int[] arrayOfInt1 = this.jjstateSet;
        j--;
        switch (arrayOfInt1[j])
        {
        default:
        case 0:
        case 1:
        }
        while (true)
        {
          if (j != i)
            break label455;
          break;
          if (((l & jjbitVec0[n]) == 0L) || (k <= 14))
            continue;
          k = 14;
          continue;
          if (((l & jjbitVec0[n]) == 0L) || (k <= 11))
            continue;
          k = 11;
        }
      }
      try
      {
        label457: this.curChar = this.input_stream.readChar();
      }
      catch (IOException localIOException)
      {
      }
    }
    return paramInt2;
  }

  private int jjMoveNfa_3(int paramInt1, int paramInt2)
  {
    int i = 0;
    this.jjnewStateCnt = 3;
    int j = 1;
    this.jjstateSet[0] = paramInt1;
    int k = 2147483647;
    while (true)
    {
      int m = 1 + this.jjround;
      this.jjround = m;
      if (m == 2147483647)
        ReInitRounds();
      if (this.curChar < '@')
      {
        long l3 = 1L << this.curChar;
        while (true)
        {
          int[] arrayOfInt4 = this.jjstateSet;
          j--;
          switch (arrayOfInt4[j])
          {
          default:
          case 0:
          case 2:
          case 1:
          }
          while (j == i)
          {
            if (k != 2147483647)
            {
              this.jjmatchedKind = k;
              this.jjmatchedPos = paramInt2;
              k = 2147483647;
            }
            paramInt2++;
            j = this.jjnewStateCnt;
            this.jjnewStateCnt = i;
            i = 3 - i;
            if (j != i)
              break label518;
            return paramInt2;
            if ((0xFFFFFFFF & l3) == 0L)
              continue;
            if (k > 17)
              k = 17;
            jjCheckNAdd(2);
            continue;
            if (k <= 16)
              continue;
            k = 16;
          }
        }
      }
      if (this.curChar < '')
      {
        long l2 = 1L << (0x3F & this.curChar);
        label383: 
        while (true)
        {
          int[] arrayOfInt2 = this.jjstateSet;
          j--;
          switch (arrayOfInt2[j])
          {
          default:
          case 0:
          case 1:
          case 2:
          }
          while (true)
          {
            if (j != i)
              break label383;
            break;
            if ((0xEFFFFFFF & l2) != 0L)
            {
              if (k > 17)
                k = 17;
              jjCheckNAdd(2);
              continue;
            }
            if (this.curChar != '\\')
              continue;
            int[] arrayOfInt3 = this.jjstateSet;
            int i1 = this.jjnewStateCnt;
            this.jjnewStateCnt = (i1 + 1);
            arrayOfInt3[i1] = 1;
            continue;
            if (k <= 16)
              continue;
            k = 16;
            continue;
            if ((0xEFFFFFFF & l2) == 0L)
              continue;
            if (k > 17)
              k = 17;
            jjCheckNAdd(2);
          }
        }
      }
      int n = (0xFF & this.curChar) >> '\006';
      long l1 = 1L << (0x3F & this.curChar);
      label516: 
      while (true)
      {
        int[] arrayOfInt1 = this.jjstateSet;
        j--;
        switch (arrayOfInt1[j])
        {
        default:
        case 0:
        case 2:
        case 1:
        }
        while (true)
        {
          if (j != i)
            break label516;
          break;
          if ((l1 & jjbitVec0[n]) == 0L)
            continue;
          if (k > 17)
            k = 17;
          jjCheckNAdd(2);
          continue;
          if (((l1 & jjbitVec0[n]) == 0L) || (k <= 16))
            continue;
          k = 16;
        }
      }
      try
      {
        label518: this.curChar = this.input_stream.readChar();
      }
      catch (IOException localIOException)
      {
      }
    }
    return paramInt2;
  }

  private int jjMoveStringLiteralDfa0_0()
  {
    switch (this.curChar)
    {
    default:
      return jjMoveNfa_0(3, 0);
    case '\n':
      return jjStartNfaWithStates_0(0, 2, 2);
    case '\r':
      return jjStartNfaWithStates_0(0, 1, 2);
    case '"':
      return jjStopAtPos(0, 15);
    case '(':
      return jjStopAtPos(0, 6);
    case ';':
      return jjStopAtPos(0, 3);
    case '=':
    }
    return jjStopAtPos(0, 4);
  }

  private int jjMoveStringLiteralDfa0_1()
  {
    switch (this.curChar)
    {
    default:
      return jjMoveNfa_1(0, 0);
    case '(':
      return jjStopAtPos(0, 9);
    case ')':
    }
    return jjStopAtPos(0, 7);
  }

  private int jjMoveStringLiteralDfa0_2()
  {
    switch (this.curChar)
    {
    default:
      return jjMoveNfa_2(0, 0);
    case '(':
      return jjStopAtPos(0, 12);
    case ')':
    }
    return jjStopAtPos(0, 13);
  }

  private int jjMoveStringLiteralDfa0_3()
  {
    switch (this.curChar)
    {
    default:
      return jjMoveNfa_3(0, 0);
    case '"':
    }
    return jjStopAtPos(0, 18);
  }

  private int jjStartNfaWithStates_0(int paramInt1, int paramInt2, int paramInt3)
  {
    this.jjmatchedKind = paramInt2;
    this.jjmatchedPos = paramInt1;
    try
    {
      this.curChar = this.input_stream.readChar();
      return jjMoveNfa_0(paramInt3, paramInt1 + 1);
    }
    catch (IOException localIOException)
    {
    }
    return paramInt1 + 1;
  }

  private final int jjStartNfa_0(int paramInt, long paramLong)
  {
    return jjMoveNfa_0(jjStopStringLiteralDfa_0(paramInt, paramLong), paramInt + 1);
  }

  private final int jjStartNfa_1(int paramInt, long paramLong)
  {
    return jjMoveNfa_1(jjStopStringLiteralDfa_1(paramInt, paramLong), paramInt + 1);
  }

  private final int jjStartNfa_2(int paramInt, long paramLong)
  {
    return jjMoveNfa_2(jjStopStringLiteralDfa_2(paramInt, paramLong), paramInt + 1);
  }

  private final int jjStartNfa_3(int paramInt, long paramLong)
  {
    return jjMoveNfa_3(jjStopStringLiteralDfa_3(paramInt, paramLong), paramInt + 1);
  }

  private int jjStopAtPos(int paramInt1, int paramInt2)
  {
    this.jjmatchedKind = paramInt2;
    this.jjmatchedPos = paramInt1;
    return paramInt1 + 1;
  }

  private final int jjStopStringLiteralDfa_0(int paramInt, long paramLong)
  {
    return -1;
  }

  private final int jjStopStringLiteralDfa_1(int paramInt, long paramLong)
  {
    return -1;
  }

  private final int jjStopStringLiteralDfa_2(int paramInt, long paramLong)
  {
    return -1;
  }

  private final int jjStopStringLiteralDfa_3(int paramInt, long paramLong)
  {
    return -1;
  }

  void MoreLexicalActions()
  {
    int i = this.jjimageLen;
    int j = 1 + this.jjmatchedPos;
    this.lengthOfMatch = j;
    this.jjimageLen = (i + j);
    switch (this.jjmatchedKind)
    {
    case 10:
    case 14:
    default:
    case 8:
    case 9:
    case 11:
    case 12:
    case 13:
      do
      {
        return;
        this.image.append(this.input_stream.GetSuffix(this.jjimageLen));
        this.jjimageLen = 0;
        this.image.deleteCharAt(-2 + this.image.length());
        return;
        this.image.append(this.input_stream.GetSuffix(this.jjimageLen));
        this.jjimageLen = 0;
        commentNest = 1;
        return;
        this.image.append(this.input_stream.GetSuffix(this.jjimageLen));
        this.jjimageLen = 0;
        this.image.deleteCharAt(-2 + this.image.length());
        return;
        this.image.append(this.input_stream.GetSuffix(this.jjimageLen));
        this.jjimageLen = 0;
        commentNest = 1 + commentNest;
        return;
        this.image.append(this.input_stream.GetSuffix(this.jjimageLen));
        this.jjimageLen = 0;
        commentNest = -1 + commentNest;
      }
      while (commentNest != 0);
      SwitchTo(1);
      return;
    case 15:
      this.image.append(this.input_stream.GetSuffix(this.jjimageLen));
      this.jjimageLen = 0;
      this.image.deleteCharAt(-1 + this.image.length());
      return;
    case 16:
    }
    this.image.append(this.input_stream.GetSuffix(this.jjimageLen));
    this.jjimageLen = 0;
    this.image.deleteCharAt(-2 + this.image.length());
  }

  public void ReInit(SimpleCharStream paramSimpleCharStream)
  {
    this.jjnewStateCnt = 0;
    this.jjmatchedPos = 0;
    this.curLexState = this.defaultLexState;
    this.input_stream = paramSimpleCharStream;
    ReInitRounds();
  }

  public void ReInit(SimpleCharStream paramSimpleCharStream, int paramInt)
  {
    ReInit(paramSimpleCharStream);
    SwitchTo(paramInt);
  }

  public void SwitchTo(int paramInt)
  {
    if ((paramInt >= 4) || (paramInt < 0))
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + paramInt + ". State unchanged.", 2);
    this.curLexState = paramInt;
  }

  void TokenLexicalActions(Token paramToken)
  {
    switch (this.jjmatchedKind)
    {
    default:
      return;
    case 18:
    }
    StringBuilder localStringBuilder = this.image;
    SimpleCharStream localSimpleCharStream = this.input_stream;
    int i = this.jjimageLen;
    int j = 1 + this.jjmatchedPos;
    this.lengthOfMatch = j;
    localStringBuilder.append(localSimpleCharStream.GetSuffix(i + j));
    paramToken.image = this.image.substring(0, -1 + this.image.length());
  }

  public Token getNextToken()
  {
    Object localObject = null;
    int i = 0;
    while (true)
    {
      try
      {
        this.curChar = this.input_stream.BeginToken();
        this.image = this.jjimage;
        this.image.setLength(0);
        this.jjimageLen = 0;
        switch (this.curLexState)
        {
        default:
          if (this.jjmatchedKind == 2147483647)
            break label438;
          if (1 + this.jjmatchedPos >= i)
            continue;
          this.input_stream.backup(-1 + (i - this.jjmatchedPos));
          if ((jjtoToken[(this.jjmatchedKind >> 6)] & 1L << (0x3F & this.jjmatchedKind)) == 0L)
            break label274;
          Token localToken3 = jjFillToken();
          localToken3.specialToken = localObject;
          TokenLexicalActions(localToken3);
          if (jjnewLexState[this.jjmatchedKind] == -1)
            continue;
          this.curLexState = jjnewLexState[this.jjmatchedKind];
          return localToken3;
        case 0:
        case 1:
        case 2:
        case 3:
        }
      }
      catch (IOException localIOException1)
      {
        this.jjmatchedKind = 0;
        Token localToken1 = jjFillToken();
        localToken1.specialToken = localObject;
        return localToken1;
      }
      this.jjmatchedKind = 2147483647;
      this.jjmatchedPos = 0;
      i = jjMoveStringLiteralDfa0_0();
      continue;
      this.jjmatchedKind = 2147483647;
      this.jjmatchedPos = 0;
      i = jjMoveStringLiteralDfa0_1();
      continue;
      this.jjmatchedKind = 2147483647;
      this.jjmatchedPos = 0;
      i = jjMoveStringLiteralDfa0_2();
      continue;
      this.jjmatchedKind = 2147483647;
      this.jjmatchedPos = 0;
      i = jjMoveStringLiteralDfa0_3();
      continue;
      label274: if ((jjtoSkip[(this.jjmatchedKind >> 6)] & 1L << (0x3F & this.jjmatchedKind)) != 0L)
      {
        Token localToken2;
        if ((jjtoSpecial[(this.jjmatchedKind >> 6)] & 1L << (0x3F & this.jjmatchedKind)) != 0L)
        {
          localToken2 = jjFillToken();
          if (localObject != null)
            break label366;
        }
        for (localObject = localToken2; ; localObject = localToken2)
        {
          if (jjnewLexState[this.jjmatchedKind] == -1)
            break label382;
          this.curLexState = jjnewLexState[this.jjmatchedKind];
          break;
          label366: localToken2.specialToken = localObject;
          localObject.next = localToken2;
        }
        label382: continue;
      }
      MoreLexicalActions();
      if (jjnewLexState[this.jjmatchedKind] != -1)
        this.curLexState = jjnewLexState[this.jjmatchedKind];
      i = 0;
      this.jjmatchedKind = 2147483647;
      try
      {
        this.curChar = this.input_stream.readChar();
        i = 0;
      }
      catch (IOException localIOException3)
      {
      }
    }
    label438: int j = this.input_stream.getEndLine();
    int k = this.input_stream.getEndColumn();
    String str = null;
    boolean bool = false;
    try
    {
      this.input_stream.readChar();
      this.input_stream.backup(1);
      if (!bool)
      {
        this.input_stream.backup(1);
        if (i <= 1)
          str = "";
      }
      else
      {
        throw new TokenMgrError(bool, this.curLexState, j, k, str, this.curChar, 0);
      }
    }
    catch (IOException localIOException2)
    {
      while (true)
      {
        bool = true;
        if (i <= 1);
        for (str = ""; ; str = this.input_stream.GetImage())
        {
          if ((this.curChar != '\n') && (this.curChar != '\r'))
            break label578;
          j++;
          k = 0;
          break;
        }
        label578: k++;
        continue;
        str = this.input_stream.GetImage();
      }
    }
  }

  protected Token jjFillToken()
  {
    String str1 = jjstrLiteralImages[this.jjmatchedKind];
    if (str1 == null);
    for (String str2 = this.input_stream.GetImage(); ; str2 = str1)
    {
      int i = this.input_stream.getBeginLine();
      int j = this.input_stream.getBeginColumn();
      int k = this.input_stream.getEndLine();
      int m = this.input_stream.getEndColumn();
      Token localToken = Token.newToken(this.jjmatchedKind, str2);
      localToken.beginLine = i;
      localToken.endLine = k;
      localToken.beginColumn = j;
      localToken.endColumn = m;
      return localToken;
    }
  }

  public void setDebugStream(PrintStream paramPrintStream)
  {
    this.debugStream = paramPrintStream;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.contentdisposition.parser.ContentDispositionParserTokenManager
 * JD-Core Version:    0.6.0
 */