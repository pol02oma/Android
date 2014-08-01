package org.apache.james.mime4j.field.structured.parser;

import java.io.IOException;
import java.io.PrintStream;

public class StructuredFieldParserTokenManager
  implements StructuredFieldParserConstants
{
  static final long[] jjbitVec0 = { 0L, 0L, -1L, -1L };
  public static final int[] jjnewLexState;
  static final int[] jjnextStates = new int[0];
  public static final String[] jjstrLiteralImages = { "", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null };
  static final long[] jjtoMore;
  static final long[] jjtoSkip;
  static final long[] jjtoToken;
  public static final String[] lexStateNames = { "DEFAULT", "INCOMMENT", "NESTED_COMMENT", "INQUOTEDSTRING" };
  int commentNest;
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
  private final int[] jjrounds = new int[6];
  private final int[] jjstateSet = new int[12];
  private int lengthOfMatch;

  static
  {
    jjnewLexState = new int[] { -1, 1, 0, 2, -1, -1, -1, -1, -1, 3, -1, -1, -1, 0, -1, -1, -1, -1 };
    jjtoToken = new long[] { 63489L };
    jjtoSkip = new long[] { 1022L };
    jjtoMore = new long[] { 1024L };
  }

  public StructuredFieldParserTokenManager(SimpleCharStream paramSimpleCharStream)
  {
    this.input_stream = paramSimpleCharStream;
  }

  public StructuredFieldParserTokenManager(SimpleCharStream paramSimpleCharStream, int paramInt)
  {
    this(paramSimpleCharStream);
    SwitchTo(paramInt);
  }

  private void ReInitRounds()
  {
    this.jjround = -2147483647;
    int j;
    for (int i = 6; ; i = j)
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
    this.jjnewStateCnt = 2;
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
        long l2 = 1L << this.curChar;
        while (true)
        {
          int[] arrayOfInt3 = this.jjstateSet;
          j--;
          switch (arrayOfInt3[j])
          {
          default:
          case 2:
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
            i = 2 - i;
            if (j != i)
              break label442;
            return paramInt2;
            if ((0xFFFFD9FF & l2) != 0L)
            {
              if (k > 15)
                k = 15;
              jjCheckNAdd(1);
              continue;
            }
            if ((0x2600 & l2) == 0L)
              continue;
            if (k > 14)
              k = 14;
            jjCheckNAdd(0);
            continue;
            if ((0x2600 & l2) == 0L)
              continue;
            k = 14;
            jjCheckNAdd(0);
            continue;
            if ((0xFFFFD9FF & l2) == 0L)
              continue;
            k = 15;
            jjCheckNAdd(1);
          }
        }
      }
      if (this.curChar < '')
      {
        (1L << (0x3F & this.curChar));
        label339: 
        while (true)
        {
          int[] arrayOfInt2 = this.jjstateSet;
          j--;
          switch (arrayOfInt2[j])
          {
          default:
          case 1:
          case 2:
          }
          while (true)
          {
            if (j != i)
              break label339;
            break;
            k = 15;
            jjCheckNAdd(1);
          }
        }
      }
      int n = (0xFF & this.curChar) >> '\006';
      long l1 = 1L << (0x3F & this.curChar);
      label440: 
      while (true)
      {
        int[] arrayOfInt1 = this.jjstateSet;
        j--;
        switch (arrayOfInt1[j])
        {
        default:
        case 1:
        case 2:
        }
        while (true)
        {
          if (j != i)
            break label440;
          break;
          if ((l1 & jjbitVec0[n]) == 0L)
            continue;
          if (k > 15)
            k = 15;
          jjCheckNAdd(1);
        }
      }
      try
      {
        label442: this.curChar = this.input_stream.readChar();
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
    this.jjnewStateCnt = 1;
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
        long l2 = 1L << this.curChar;
        while (true)
        {
          int[] arrayOfInt3 = this.jjstateSet;
          j--;
          switch (arrayOfInt3[j])
          {
          default:
          case 0:
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
            i = 1 - i;
            if (j != i)
              break label327;
            return paramInt2;
            if ((0xFFFFFFFF & l2) == 0L)
              continue;
            k = 4;
          }
        }
      }
      if (this.curChar < '')
      {
        (1L << (0x3F & this.curChar));
        label233: 
        while (true)
        {
          int[] arrayOfInt2 = this.jjstateSet;
          j--;
          switch (arrayOfInt2[j])
          {
          default:
          case 0:
          }
          while (true)
          {
            if (j != i)
              break label233;
            break;
            k = 4;
          }
        }
      }
      int n = (0xFF & this.curChar) >> '\006';
      long l1 = 1L << (0x3F & this.curChar);
      label325: 
      while (true)
      {
        int[] arrayOfInt1 = this.jjstateSet;
        j--;
        switch (arrayOfInt1[j])
        {
        default:
        case 0:
        }
        while (true)
        {
          if (j != i)
            break label325;
          break;
          if (((l1 & jjbitVec0[n]) == 0L) || (k <= 4))
            continue;
          k = 4;
        }
      }
      try
      {
        label327: this.curChar = this.input_stream.readChar();
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
        long l2 = 1L << this.curChar;
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
              break label503;
            return paramInt2;
            if (((0xFFFFFFFF & l2) == 0L) || (k <= 8))
              continue;
            k = 8;
            continue;
            if (k > 7)
              k = 7;
            int[] arrayOfInt5 = this.jjstateSet;
            int i2 = this.jjnewStateCnt;
            this.jjnewStateCnt = (i2 + 1);
            arrayOfInt5[i2] = 1;
          }
        }
      }
      if (this.curChar < '')
      {
        (1L << (0x3F & this.curChar));
        label348: 
        while (true)
        {
          int[] arrayOfInt3 = this.jjstateSet;
          j--;
          switch (arrayOfInt3[j])
          {
          default:
          case 0:
          case 1:
          case 2:
          }
          while (true)
          {
            if (j != i)
              break label348;
            break;
            if (k > 8)
              k = 8;
            if (this.curChar != '\\')
              continue;
            jjCheckNAdd(1);
            continue;
            if (k > 7)
              k = 7;
            jjCheckNAdd(1);
            continue;
            if (k <= 8)
              continue;
            k = 8;
          }
        }
      }
      int n = (0xFF & this.curChar) >> '\006';
      long l1 = 1L << (0x3F & this.curChar);
      label501: 
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
            break label501;
          break;
          if (((l1 & jjbitVec0[n]) == 0L) || (k <= 8))
            continue;
          k = 8;
          continue;
          if ((l1 & jjbitVec0[n]) == 0L)
            continue;
          if (k > 7)
            k = 7;
          int[] arrayOfInt2 = this.jjstateSet;
          int i1 = this.jjnewStateCnt;
          this.jjnewStateCnt = (i1 + 1);
          arrayOfInt2[i1] = 1;
        }
      }
      try
      {
        label503: this.curChar = this.input_stream.readChar();
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
    this.jjnewStateCnt = 6;
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
          case 1:
          case 2:
          case 3:
          case 4:
          case 5:
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
            i = 6 - i;
            if (j != i)
              break label732;
            return paramInt2;
            if ((0xFFFFDFFF & l3) != 0L)
            {
              if (k > 11)
                k = 11;
              jjCheckNAdd(2);
              continue;
            }
            if (this.curChar != '\r')
              continue;
            int[] arrayOfInt7 = this.jjstateSet;
            int i4 = this.jjnewStateCnt;
            this.jjnewStateCnt = (i4 + 1);
            arrayOfInt7[i4] = 3;
            continue;
            if (k > 10)
              k = 10;
            int[] arrayOfInt6 = this.jjstateSet;
            int i3 = this.jjnewStateCnt;
            this.jjnewStateCnt = (i3 + 1);
            arrayOfInt6[i3] = 1;
            continue;
            if ((0xFFFFDFFF & l3) == 0L)
              continue;
            if (k > 11)
              k = 11;
            jjCheckNAdd(2);
            continue;
            if (this.curChar != '\n')
              continue;
            if (k > 12)
              k = 12;
            jjCheckNAdd(4);
            continue;
            if ((0x200 & l3) == 0L)
              continue;
            if (k > 12)
              k = 12;
            jjCheckNAdd(4);
            continue;
            if (this.curChar != '\r')
              continue;
            int[] arrayOfInt5 = this.jjstateSet;
            int i2 = this.jjnewStateCnt;
            this.jjnewStateCnt = (i2 + 1);
            arrayOfInt5[i2] = 3;
          }
        }
      }
      if (this.curChar < '')
      {
        long l2 = 1L << (0x3F & this.curChar);
        label571: 
        while (true)
        {
          int[] arrayOfInt3 = this.jjstateSet;
          j--;
          switch (arrayOfInt3[j])
          {
          default:
          case 0:
          case 1:
          case 2:
          }
          while (true)
          {
            if (j != i)
              break label571;
            break;
            if ((0xEFFFFFFF & l2) != 0L)
            {
              if (k > 11)
                k = 11;
              jjCheckNAdd(2);
              continue;
            }
            if (this.curChar != '\\')
              continue;
            jjCheckNAdd(1);
            continue;
            if (k > 10)
              k = 10;
            jjCheckNAdd(1);
            continue;
            if ((0xEFFFFFFF & l2) == 0L)
              continue;
            if (k > 11)
              k = 11;
            jjCheckNAdd(2);
          }
        }
      }
      int n = (0xFF & this.curChar) >> '\006';
      long l1 = 1L << (0x3F & this.curChar);
      label730: 
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
            break label730;
          break;
          if ((l1 & jjbitVec0[n]) == 0L)
            continue;
          if (k > 11)
            k = 11;
          jjCheckNAdd(2);
          continue;
          if ((l1 & jjbitVec0[n]) == 0L)
            continue;
          if (k > 10)
            k = 10;
          int[] arrayOfInt2 = this.jjstateSet;
          int i1 = this.jjnewStateCnt;
          this.jjnewStateCnt = (i1 + 1);
          arrayOfInt2[i1] = 1;
        }
      }
      try
      {
        label732: this.curChar = this.input_stream.readChar();
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
      return jjMoveNfa_0(2, 0);
    case '"':
      return jjStopAtPos(0, 9);
    case '(':
    }
    return jjStopAtPos(0, 1);
  }

  private int jjMoveStringLiteralDfa0_1()
  {
    switch (this.curChar)
    {
    default:
      return jjMoveNfa_1(0, 0);
    case '(':
      return jjStopAtPos(0, 3);
    case ')':
    }
    return jjStopAtPos(0, 2);
  }

  private int jjMoveStringLiteralDfa0_2()
  {
    switch (this.curChar)
    {
    default:
      return jjMoveNfa_2(0, 0);
    case '(':
      return jjStopAtPos(0, 5);
    case ')':
    }
    return jjStopAtPos(0, 6);
  }

  private int jjMoveStringLiteralDfa0_3()
  {
    switch (this.curChar)
    {
    default:
      return jjMoveNfa_3(0, 0);
    case '"':
    }
    return jjStopAtPos(0, 13);
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
    default:
      return;
    case 10:
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

  void SkipLexicalActions(Token paramToken)
  {
    switch (this.jjmatchedKind)
    {
    case 4:
    default:
    case 3:
    case 5:
    case 6:
      do
      {
        return;
        StringBuilder localStringBuilder4 = this.image;
        SimpleCharStream localSimpleCharStream4 = this.input_stream;
        int i2 = this.jjimageLen;
        int i3 = 1 + this.jjmatchedPos;
        this.lengthOfMatch = i3;
        localStringBuilder4.append(localSimpleCharStream4.GetSuffix(i2 + i3));
        this.commentNest = 1;
        return;
        StringBuilder localStringBuilder3 = this.image;
        SimpleCharStream localSimpleCharStream3 = this.input_stream;
        int n = this.jjimageLen;
        int i1 = 1 + this.jjmatchedPos;
        this.lengthOfMatch = i1;
        localStringBuilder3.append(localSimpleCharStream3.GetSuffix(n + i1));
        this.commentNest = (1 + this.commentNest);
        return;
        StringBuilder localStringBuilder2 = this.image;
        SimpleCharStream localSimpleCharStream2 = this.input_stream;
        int k = this.jjimageLen;
        int m = 1 + this.jjmatchedPos;
        this.lengthOfMatch = m;
        localStringBuilder2.append(localSimpleCharStream2.GetSuffix(k + m));
        this.commentNest = (-1 + this.commentNest);
      }
      while (this.commentNest != 0);
      SwitchTo(1);
      return;
    case 7:
    }
    StringBuilder localStringBuilder1 = this.image;
    SimpleCharStream localSimpleCharStream1 = this.input_stream;
    int i = this.jjimageLen;
    int j = 1 + this.jjmatchedPos;
    this.lengthOfMatch = j;
    localStringBuilder1.append(localSimpleCharStream1.GetSuffix(i + j));
    this.image.deleteCharAt(-2 + this.image.length());
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
    case 13:
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
            break label366;
          if (1 + this.jjmatchedPos >= i)
            continue;
          this.input_stream.backup(-1 + (i - this.jjmatchedPos));
          if ((jjtoToken[(this.jjmatchedKind >> 6)] & 1L << (0x3F & this.jjmatchedKind)) == 0L)
            break label254;
          Token localToken = jjFillToken();
          TokenLexicalActions(localToken);
          if (jjnewLexState[this.jjmatchedKind] == -1)
            continue;
          this.curLexState = jjnewLexState[this.jjmatchedKind];
          return localToken;
        case 0:
        case 1:
        case 2:
        case 3:
        }
      }
      catch (IOException localIOException1)
      {
        this.jjmatchedKind = 0;
        return jjFillToken();
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
      label254: if ((jjtoSkip[(this.jjmatchedKind >> 6)] & 1L << (0x3F & this.jjmatchedKind)) != 0L)
      {
        SkipLexicalActions(null);
        if (jjnewLexState[this.jjmatchedKind] == -1)
          continue;
        this.curLexState = jjnewLexState[this.jjmatchedKind];
        continue;
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
    label366: int j = this.input_stream.getEndLine();
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
            break label504;
          j++;
          k = 0;
          break;
        }
        label504: k++;
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
 * Qualified Name:     org.apache.james.mime4j.field.structured.parser.StructuredFieldParserTokenManager
 * JD-Core Version:    0.6.0
 */