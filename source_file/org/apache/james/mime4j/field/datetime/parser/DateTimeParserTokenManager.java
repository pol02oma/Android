package org.apache.james.mime4j.field.datetime.parser;

import java.io.IOException;
import java.io.PrintStream;

public class DateTimeParserTokenManager
  implements DateTimeParserConstants
{
  static int commentNest;
  static final long[] jjbitVec0 = { 0L, 0L, -1L, -1L };
  public static final int[] jjnewLexState;
  static final int[] jjnextStates = new int[0];
  public static final String[] jjstrLiteralImages = { "", "\r", "\n", ",", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec", ":", null, "UT", "GMT", "EST", "EDT", "CST", "CDT", "MST", "MDT", "PST", "PDT", null, null, null, null, null, null, null, null, null, null, null, null, null, null };
  static final long[] jjtoMore;
  static final long[] jjtoSkip;
  static final long[] jjtoSpecial;
  static final long[] jjtoToken;
  public static final String[] lexStateNames = { "DEFAULT", "INCOMMENT", "NESTED_COMMENT" };
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
  private final int[] jjrounds = new int[4];
  private final int[] jjstateSet = new int[8];
  private int lengthOfMatch;

  static
  {
    jjnewLexState = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 0, -1, 2, -1, -1, -1, -1, -1, -1, -1, -1 };
    jjtoToken = new long[] { 70437463654399L };
    jjtoSkip = new long[] { 343597383680L };
    jjtoSpecial = new long[] { 68719476736L };
    jjtoMore = new long[] { 69956427317248L };
  }

  public DateTimeParserTokenManager(SimpleCharStream paramSimpleCharStream)
  {
    this.input_stream = paramSimpleCharStream;
  }

  public DateTimeParserTokenManager(SimpleCharStream paramSimpleCharStream, int paramInt)
  {
    this(paramSimpleCharStream);
    SwitchTo(paramInt);
  }

  private void ReInitRounds()
  {
    this.jjround = -2147483647;
    int j;
    for (int i = 4; ; i = j)
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
    this.jjnewStateCnt = 4;
    int j = 1;
    this.jjstateSet[0] = paramInt1;
    int k = 2147483647;
    while (true)
    {
      int m = 1 + this.jjround;
      this.jjround = m;
      if (m == 2147483647)
        ReInitRounds();
      long l2;
      if (this.curChar < '@')
      {
        l2 = 1L << this.curChar;
        int[] arrayOfInt3 = this.jjstateSet;
        j--;
        switch (arrayOfInt3[j])
        {
        case 1:
        default:
          label108: if (j != i)
            break;
        case 0:
        case 2:
        case 3:
        }
      }
      while (true)
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
        i = 4 - i;
        if (j != i)
          break label421;
        return paramInt2;
        if ((0x0 & l2) != 0L)
        {
          if (k > 46)
            k = 46;
          jjCheckNAdd(3);
          break label108;
        }
        if ((0x200 & l2) != 0L)
        {
          if (k > 36)
            k = 36;
          jjCheckNAdd(2);
          break label108;
        }
        if (((0x0 & l2) == 0L) || (k <= 24))
          break label108;
        k = 24;
        break label108;
        if ((0x200 & l2) == 0L)
          break label108;
        k = 36;
        jjCheckNAdd(2);
        break label108;
        if ((0x0 & l2) == 0L)
          break label108;
        k = 46;
        jjCheckNAdd(3);
        break label108;
        break;
        if (this.curChar < '')
        {
          long l1 = 1L << (0x3F & this.curChar);
          label373: 
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
                break label373;
              break;
              if ((0x7FFFBFE & l1) == 0L)
                continue;
              k = 35;
            }
          }
        }
        ((0xFF & this.curChar) >> '\006');
        (1L << (0x3F & this.curChar));
        do
        {
          int[] arrayOfInt1 = this.jjstateSet;
          j--;
          arrayOfInt1[j];
        }
        while (j != i);
      }
      try
      {
        label421: this.curChar = this.input_stream.readChar();
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
            if (k <= 41)
              continue;
            k = 41;
            continue;
            if (k <= 39)
              continue;
            k = 39;
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
            if (k > 41)
              k = 41;
            if (this.curChar != '\\')
              continue;
            int[] arrayOfInt3 = this.jjstateSet;
            int i1 = this.jjnewStateCnt;
            this.jjnewStateCnt = (i1 + 1);
            arrayOfInt3[i1] = 1;
            continue;
            if (k <= 39)
              continue;
            k = 39;
            continue;
            if (k <= 41)
              continue;
            k = 41;
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
          if (((l & jjbitVec0[n]) == 0L) || (k <= 41))
            continue;
          k = 41;
          continue;
          if (((l & jjbitVec0[n]) == 0L) || (k <= 39))
            continue;
          k = 39;
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
            if (k <= 45)
              continue;
            k = 45;
            continue;
            if (k <= 42)
              continue;
            k = 42;
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
            if (k > 45)
              k = 45;
            if (this.curChar != '\\')
              continue;
            int[] arrayOfInt3 = this.jjstateSet;
            int i1 = this.jjnewStateCnt;
            this.jjnewStateCnt = (i1 + 1);
            arrayOfInt3[i1] = 1;
            continue;
            if (k <= 42)
              continue;
            k = 42;
            continue;
            if (k <= 45)
              continue;
            k = 45;
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
          if (((l & jjbitVec0[n]) == 0L) || (k <= 45))
            continue;
          k = 45;
          continue;
          if (((l & jjbitVec0[n]) == 0L) || (k <= 42))
            continue;
          k = 42;
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

  private int jjMoveStringLiteralDfa0_0()
  {
    switch (this.curChar)
    {
    default:
      return jjMoveNfa_0(0, 0);
    case '\n':
      return jjStopAtPos(0, 2);
    case '\r':
      return jjStopAtPos(0, 1);
    case '(':
      return jjStopAtPos(0, 37);
    case ',':
      return jjStopAtPos(0, 3);
    case ':':
      return jjStopAtPos(0, 23);
    case 'A':
      return jjMoveStringLiteralDfa1_0(278528L);
    case 'C':
      return jjMoveStringLiteralDfa1_0(1610612736L);
    case 'D':
      return jjMoveStringLiteralDfa1_0(4194304L);
    case 'E':
      return jjMoveStringLiteralDfa1_0(402653184L);
    case 'F':
      return jjMoveStringLiteralDfa1_0(4352L);
    case 'G':
      return jjMoveStringLiteralDfa1_0(67108864L);
    case 'J':
      return jjMoveStringLiteralDfa1_0(198656L);
    case 'M':
      return jjMoveStringLiteralDfa1_0(6442491920L);
    case 'N':
      return jjMoveStringLiteralDfa1_0(2097152L);
    case 'O':
      return jjMoveStringLiteralDfa1_0(1048576L);
    case 'P':
      return jjMoveStringLiteralDfa1_0(25769803776L);
    case 'S':
      return jjMoveStringLiteralDfa1_0(525824L);
    case 'T':
      return jjMoveStringLiteralDfa1_0(160L);
    case 'U':
      return jjMoveStringLiteralDfa1_0(33554432L);
    case 'W':
    }
    return jjMoveStringLiteralDfa1_0(64L);
  }

  private int jjMoveStringLiteralDfa0_1()
  {
    switch (this.curChar)
    {
    default:
      return jjMoveNfa_1(0, 0);
    case '(':
      return jjStopAtPos(0, 40);
    case ')':
    }
    return jjStopAtPos(0, 38);
  }

  private int jjMoveStringLiteralDfa0_2()
  {
    switch (this.curChar)
    {
    default:
      return jjMoveNfa_2(0, 0);
    case '(':
      return jjStopAtPos(0, 43);
    case ')':
    }
    return jjStopAtPos(0, 44);
  }

  private int jjMoveStringLiteralDfa1_0(long paramLong)
  {
    do
    {
      try
      {
        this.curChar = this.input_stream.readChar();
        switch (this.curChar)
        {
        default:
          return jjStartNfa_0(0, paramLong);
        case 'D':
        case 'M':
        case 'S':
        case 'T':
        case 'a':
        case 'c':
        case 'e':
        case 'h':
        case 'o':
        case 'p':
        case 'r':
        case 'u':
        }
      }
      catch (IOException localIOException)
      {
        jjStopStringLiteralDfa_0(0, paramLong);
        return 1;
      }
      return jjMoveStringLiteralDfa2_0(paramLong, 22817013760L);
      return jjMoveStringLiteralDfa2_0(paramLong, 67108864L);
      return jjMoveStringLiteralDfa2_0(paramLong, 11408506880L);
    }
    while ((0x2000000 & paramLong) == 0L);
    return jjStopAtPos(1, 25);
    return jjMoveStringLiteralDfa2_0(paramLong, 43520L);
    return jjMoveStringLiteralDfa2_0(paramLong, 1048576L);
    return jjMoveStringLiteralDfa2_0(paramLong, 4722752L);
    return jjMoveStringLiteralDfa2_0(paramLong, 128L);
    return jjMoveStringLiteralDfa2_0(paramLong, 2097168L);
    return jjMoveStringLiteralDfa2_0(paramLong, 16384L);
    return jjMoveStringLiteralDfa2_0(paramLong, 256L);
    return jjMoveStringLiteralDfa2_0(paramLong, 459808L);
  }

  private int jjMoveStringLiteralDfa2_0(long paramLong1, long paramLong2)
  {
    long l = paramLong2 & paramLong1;
    if (l == 0L)
      return jjStartNfa_0(0, paramLong1);
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    do
                    {
                      do
                      {
                        do
                        {
                          do
                          {
                            do
                            {
                              do
                              {
                                do
                                {
                                  try
                                  {
                                    this.curChar = this.input_stream.readChar();
                                    switch (this.curChar)
                                    {
                                    default:
                                      return jjStartNfa_0(1, l);
                                    case 'T':
                                    case 'b':
                                    case 'c':
                                    case 'd':
                                    case 'e':
                                    case 'g':
                                    case 'i':
                                    case 'l':
                                    case 'n':
                                    case 'p':
                                    case 'r':
                                    case 't':
                                    case 'u':
                                    case 'v':
                                    case 'y':
                                    }
                                  }
                                  catch (IOException localIOException)
                                  {
                                    jjStopStringLiteralDfa_0(1, l);
                                    return 2;
                                  }
                                  if ((0x4000000 & l) != 0L)
                                    return jjStopAtPos(2, 26);
                                  if ((0x8000000 & l) != 0L)
                                    return jjStopAtPos(2, 27);
                                  if ((0x10000000 & l) != 0L)
                                    return jjStopAtPos(2, 28);
                                  if ((0x20000000 & l) != 0L)
                                    return jjStopAtPos(2, 29);
                                  if ((0x40000000 & l) != 0L)
                                    return jjStopAtPos(2, 30);
                                  if ((0x80000000 & l) != 0L)
                                    return jjStopAtPos(2, 31);
                                  if ((0x0 & l) != 0L)
                                    return jjStopAtPos(2, 32);
                                  if ((0x0 & l) != 0L)
                                    return jjStopAtPos(2, 33);
                                }
                                while ((0x0 & l) == 0L);
                                return jjStopAtPos(2, 34);
                              }
                              while ((0x1000 & l) == 0L);
                              return jjStopAtPos(2, 12);
                            }
                            while ((0x400000 & l) == 0L);
                            return jjStopAtPos(2, 22);
                          }
                          while ((0x40 & l) == 0L);
                          return jjStopAtPos(2, 6);
                        }
                        while ((0x20 & l) == 0L);
                        return jjStopAtPos(2, 5);
                      }
                      while ((0x40000 & l) == 0L);
                      return jjStopAtPos(2, 18);
                    }
                    while ((0x100 & l) == 0L);
                    return jjStopAtPos(2, 8);
                  }
                  while ((0x20000 & l) == 0L);
                  return jjStopAtPos(2, 17);
                  if ((0x10 & l) != 0L)
                    return jjStopAtPos(2, 4);
                  if ((0x400 & l) != 0L)
                    return jjStopAtPos(2, 10);
                  if ((0x800 & l) != 0L)
                    return jjStopAtPos(2, 11);
                }
                while ((0x10000 & l) == 0L);
                return jjStopAtPos(2, 16);
              }
              while ((0x80000 & l) == 0L);
              return jjStopAtPos(2, 19);
              if ((0x2000 & l) != 0L)
                return jjStopAtPos(2, 13);
            }
            while ((0x4000 & l) == 0L);
            return jjStopAtPos(2, 14);
            if ((0x200 & l) != 0L)
              return jjStopAtPos(2, 9);
          }
          while ((0x100000 & l) == 0L);
          return jjStopAtPos(2, 20);
        }
        while ((0x80 & l) == 0L);
        return jjStopAtPos(2, 7);
      }
      while ((0x200000 & l) == 0L);
      return jjStopAtPos(2, 21);
    }
    while ((0x8000 & l) == 0L);
    return jjStopAtPos(2, 15);
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

  private int jjStopAtPos(int paramInt1, int paramInt2)
  {
    this.jjmatchedKind = paramInt2;
    this.jjmatchedPos = paramInt1;
    return paramInt1 + 1;
  }

  private final int jjStopStringLiteralDfa_0(int paramInt, long paramLong)
  {
    switch (paramInt)
    {
    default:
    case 0:
    case 1:
    }
    do
    {
      do
        return -1;
      while ((0xFE7CF7F0 & paramLong) == 0L);
      this.jjmatchedKind = 35;
      return -1;
    }
    while (((0xFE7CF7F0 & paramLong) == 0L) || (this.jjmatchedPos != 0));
    this.jjmatchedKind = 35;
    this.jjmatchedPos = 0;
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

  void MoreLexicalActions()
  {
    int i = this.jjimageLen;
    int j = 1 + this.jjmatchedPos;
    this.lengthOfMatch = j;
    this.jjimageLen = (i + j);
    switch (this.jjmatchedKind)
    {
    case 41:
    default:
    case 39:
    case 40:
    case 42:
    case 43:
    case 44:
    }
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
    if ((paramInt >= 3) || (paramInt < 0))
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + paramInt + ". State unchanged.", 2);
    this.curLexState = paramInt;
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
            break label409;
          if (1 + this.jjmatchedPos >= i)
            continue;
          this.input_stream.backup(-1 + (i - this.jjmatchedPos));
          if ((jjtoToken[(this.jjmatchedKind >> 6)] & 1L << (0x3F & this.jjmatchedKind)) == 0L)
            break label245;
          Token localToken3 = jjFillToken();
          localToken3.specialToken = localObject;
          if (jjnewLexState[this.jjmatchedKind] == -1)
            continue;
          this.curLexState = jjnewLexState[this.jjmatchedKind];
          return localToken3;
        case 0:
        case 1:
        case 2:
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
      label245: if ((jjtoSkip[(this.jjmatchedKind >> 6)] & 1L << (0x3F & this.jjmatchedKind)) != 0L)
      {
        Token localToken2;
        if ((jjtoSpecial[(this.jjmatchedKind >> 6)] & 1L << (0x3F & this.jjmatchedKind)) != 0L)
        {
          localToken2 = jjFillToken();
          if (localObject != null)
            break label337;
        }
        for (localObject = localToken2; ; localObject = localToken2)
        {
          if (jjnewLexState[this.jjmatchedKind] == -1)
            break label353;
          this.curLexState = jjnewLexState[this.jjmatchedKind];
          break;
          localToken2.specialToken = localObject;
          localObject.next = localToken2;
        }
        continue;
      }
      label337: label353: MoreLexicalActions();
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
    label409: int j = this.input_stream.getEndLine();
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
            break label549;
          j++;
          k = 0;
          break;
        }
        label549: k++;
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
 * Qualified Name:     org.apache.james.mime4j.field.datetime.parser.DateTimeParserTokenManager
 * JD-Core Version:    0.6.0
 */