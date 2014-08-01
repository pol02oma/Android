package com.google.android.gms.analytics;

import java.util.SortedSet;
import java.util.TreeSet;

class u
{
  private static final u sk = new u();
  private SortedSet<a> sh = new TreeSet();
  private StringBuilder si = new StringBuilder();
  private boolean sj = false;

  public static u bR()
  {
    return sk;
  }

  public void a(a parama)
  {
    monitorenter;
    try
    {
      if (!this.sj)
      {
        this.sh.add(parama);
        this.si.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(parama.ordinal()));
      }
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public String bS()
  {
    monitorenter;
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      int i = 6;
      int j = 0;
      while (this.sh.size() > 0)
      {
        a locala = (a)this.sh.first();
        this.sh.remove(locala);
        int k = locala.ordinal();
        while (k >= i)
        {
          localStringBuilder.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(j));
          i += 6;
          j = 0;
        }
        j += (1 << locala.ordinal() % 6);
      }
      if ((j > 0) || (localStringBuilder.length() == 0))
        localStringBuilder.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(j));
      this.sh.clear();
      String str = localStringBuilder.toString();
      return str;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public String bT()
  {
    monitorenter;
    try
    {
      if (this.si.length() > 0)
        this.si.insert(0, ".");
      String str = this.si.toString();
      this.si = new StringBuilder();
      return str;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void r(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      this.sj = paramBoolean;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static enum a
  {
    static
    {
      sA = new a("BLANK_15", 15);
      sB = new a("BLANK_16", 16);
      sC = new a("BLANK_17", 17);
      sD = new a("BLANK_18", 18);
      sE = new a("BLANK_19", 19);
      sF = new a("BLANK_20", 20);
      sG = new a("BLANK_21", 21);
      sH = new a("BLANK_22", 22);
      sI = new a("BLANK_23", 23);
      sJ = new a("BLANK_24", 24);
      sK = new a("BLANK_25", 25);
      sL = new a("BLANK_26", 26);
      sM = new a("BLANK_27", 27);
      sN = new a("BLANK_28", 28);
      sO = new a("BLANK_29", 29);
      sP = new a("SET_EXCEPTION_PARSER", 30);
      sQ = new a("GET_EXCEPTION_PARSER", 31);
      sR = new a("CONSTRUCT_TRANSACTION", 32);
      sS = new a("CONSTRUCT_EXCEPTION", 33);
      sT = new a("CONSTRUCT_RAW_EXCEPTION", 34);
      sU = new a("CONSTRUCT_TIMING", 35);
      sV = new a("CONSTRUCT_SOCIAL", 36);
      sW = new a("BLANK_37", 37);
      sX = new a("BLANK_38", 38);
      sY = new a("GET_TRACKER", 39);
      sZ = new a("GET_DEFAULT_TRACKER", 40);
      ta = new a("SET_DEFAULT_TRACKER", 41);
      tb = new a("SET_APP_OPT_OUT", 42);
      tc = new a("GET_APP_OPT_OUT", 43);
      td = new a("DISPATCH", 44);
      te = new a("SET_DISPATCH_PERIOD", 45);
      tf = new a("BLANK_46", 46);
      tg = new a("REPORT_UNCAUGHT_EXCEPTIONS", 47);
      th = new a("SET_AUTO_ACTIVITY_TRACKING", 48);
      ti = new a("SET_SESSION_TIMEOUT", 49);
      tj = new a("CONSTRUCT_EVENT", 50);
      tk = new a("CONSTRUCT_ITEM", 51);
      tl = new a("BLANK_52", 52);
      tm = new a("BLANK_53", 53);
      tn = new a("SET_DRY_RUN", 54);
      to = new a("GET_DRY_RUN", 55);
      tp = new a("SET_LOGGER", 56);
      tq = new a("SET_FORCE_LOCAL_DISPATCH", 57);
      tr = new a("GET_TRACKER_NAME", 58);
      ts = new a("CLOSE_TRACKER", 59);
      tt = new a("EASY_TRACKER_ACTIVITY_START", 60);
      tu = new a("EASY_TRACKER_ACTIVITY_STOP", 61);
      tv = new a("CONSTRUCT_APP_VIEW", 62);
      a[] arrayOfa = new a[63];
      arrayOfa[0] = sl;
      arrayOfa[1] = sm;
      arrayOfa[2] = sn;
      arrayOfa[3] = so;
      arrayOfa[4] = sp;
      arrayOfa[5] = sq;
      arrayOfa[6] = sr;
      arrayOfa[7] = ss;
      arrayOfa[8] = st;
      arrayOfa[9] = su;
      arrayOfa[10] = sv;
      arrayOfa[11] = sw;
      arrayOfa[12] = sx;
      arrayOfa[13] = sy;
      arrayOfa[14] = sz;
      arrayOfa[15] = sA;
      arrayOfa[16] = sB;
      arrayOfa[17] = sC;
      arrayOfa[18] = sD;
      arrayOfa[19] = sE;
      arrayOfa[20] = sF;
      arrayOfa[21] = sG;
      arrayOfa[22] = sH;
      arrayOfa[23] = sI;
      arrayOfa[24] = sJ;
      arrayOfa[25] = sK;
      arrayOfa[26] = sL;
      arrayOfa[27] = sM;
      arrayOfa[28] = sN;
      arrayOfa[29] = sO;
      arrayOfa[30] = sP;
      arrayOfa[31] = sQ;
      arrayOfa[32] = sR;
      arrayOfa[33] = sS;
      arrayOfa[34] = sT;
      arrayOfa[35] = sU;
      arrayOfa[36] = sV;
      arrayOfa[37] = sW;
      arrayOfa[38] = sX;
      arrayOfa[39] = sY;
      arrayOfa[40] = sZ;
      arrayOfa[41] = ta;
      arrayOfa[42] = tb;
      arrayOfa[43] = tc;
      arrayOfa[44] = td;
      arrayOfa[45] = te;
      arrayOfa[46] = tf;
      arrayOfa[47] = tg;
      arrayOfa[48] = th;
      arrayOfa[49] = ti;
      arrayOfa[50] = tj;
      arrayOfa[51] = tk;
      arrayOfa[52] = tl;
      arrayOfa[53] = tm;
      arrayOfa[54] = tn;
      arrayOfa[55] = to;
      arrayOfa[56] = tp;
      arrayOfa[57] = tq;
      arrayOfa[58] = tr;
      arrayOfa[59] = ts;
      arrayOfa[60] = tt;
      arrayOfa[61] = tu;
      arrayOfa[62] = tv;
      tw = arrayOfa;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.analytics.u
 * JD-Core Version:    0.6.0
 */