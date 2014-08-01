package com.google.android.gms.internal;

import java.io.IOException;
import java.util.List;

public abstract interface c
{
  public static final class a extends ka<a>
  {
    public int eP;
    public int eQ;
    public int level;

    public a()
    {
      b();
    }

    public a a(jy paramjy)
      throws IOException
    {
      while (true)
      {
        int i = paramjy.ky();
        switch (i)
        {
        default:
          if (a(paramjy, i))
            continue;
        case 0:
          return this;
        case 8:
          int j = paramjy.kB();
          switch (j)
          {
          default:
            break;
          case 1:
          case 2:
          case 3:
          }
          this.level = j;
          break;
        case 16:
          this.eP = paramjy.kB();
          break;
        case 24:
        }
        this.eQ = paramjy.kB();
      }
    }

    public void a(jz paramjz)
      throws IOException
    {
      if (this.level != 1)
        paramjz.f(1, this.level);
      if (this.eP != 0)
        paramjz.f(2, this.eP);
      if (this.eQ != 0)
        paramjz.f(3, this.eQ);
      super.a(paramjz);
    }

    public a b()
    {
      this.level = 1;
      this.eP = 0;
      this.eQ = 0;
      this.aae = null;
      this.DY = -1;
      return this;
    }

    public int c()
    {
      int i = super.c();
      if (this.level != 1)
        i += jz.g(1, this.level);
      if (this.eP != 0)
        i += jz.g(2, this.eP);
      if (this.eQ != 0)
        i += jz.g(3, this.eQ);
      this.DY = i;
      return i;
    }

    public boolean equals(Object paramObject)
    {
      int i;
      if (paramObject == this)
        i = 1;
      a locala;
      while (true)
      {
        return i;
        boolean bool1 = paramObject instanceof a;
        i = 0;
        if (!bool1)
          continue;
        locala = (a)paramObject;
        int j = this.level;
        int k = locala.level;
        i = 0;
        if (j != k)
          continue;
        int m = this.eP;
        int n = locala.eP;
        i = 0;
        if (m != n)
          continue;
        int i1 = this.eQ;
        int i2 = locala.eQ;
        i = 0;
        if (i1 != i2)
          continue;
        if ((this.aae != null) && (!this.aae.isEmpty()))
          break;
        if (locala.aae != null)
        {
          boolean bool2 = locala.aae.isEmpty();
          i = 0;
          if (!bool2)
            continue;
        }
        return true;
      }
      return this.aae.equals(locala.aae);
    }

    public int hashCode()
    {
      int i = 31 * (31 * (31 * (527 + this.level) + this.eP) + this.eQ);
      if ((this.aae == null) || (this.aae.isEmpty()));
      for (int j = 0; ; j = this.aae.hashCode())
        return j + i;
    }
  }

  public static final class b extends ka<b>
  {
    private static volatile b[] eR;
    public int[] eS;
    public int eT;
    public boolean eU;
    public boolean eV;
    public int name;

    public b()
    {
      e();
    }

    public static b[] d()
    {
      if (eR == null);
      synchronized (kc.aah)
      {
        if (eR == null)
          eR = new b[0];
        return eR;
      }
    }

    public void a(jz paramjz)
      throws IOException
    {
      if (this.eV)
        paramjz.a(1, this.eV);
      paramjz.f(2, this.eT);
      if ((this.eS != null) && (this.eS.length > 0))
        for (int i = 0; i < this.eS.length; i++)
          paramjz.f(3, this.eS[i]);
      if (this.name != 0)
        paramjz.f(4, this.name);
      if (this.eU)
        paramjz.a(6, this.eU);
      super.a(paramjz);
    }

    public int c()
    {
      int i = 0;
      int j = super.c();
      if (this.eV)
        j += jz.b(1, this.eV);
      int k = j + jz.g(2, this.eT);
      if ((this.eS != null) && (this.eS.length > 0))
        for (int n = 0; n < this.eS.length; n++)
          i += jz.cC(this.eS[n]);
      for (int m = k + i + 1 * this.eS.length; ; m = k)
      {
        if (this.name != 0)
          m += jz.g(4, this.name);
        if (this.eU)
          m += jz.b(6, this.eU);
        this.DY = m;
        return m;
      }
    }

    public b c(jy paramjy)
      throws IOException
    {
      while (true)
      {
        int i = paramjy.ky();
        switch (i)
        {
        default:
          if (a(paramjy, i))
            continue;
        case 0:
          return this;
        case 8:
          this.eV = paramjy.kC();
          break;
        case 16:
          this.eT = paramjy.kB();
          break;
        case 24:
          int i1 = kh.c(paramjy, 24);
          if (this.eS == null);
          int[] arrayOfInt2;
          for (int i2 = 0; ; i2 = this.eS.length)
          {
            arrayOfInt2 = new int[i1 + i2];
            if (i2 != 0)
              System.arraycopy(this.eS, 0, arrayOfInt2, 0, i2);
            while (i2 < -1 + arrayOfInt2.length)
            {
              arrayOfInt2[i2] = paramjy.kB();
              paramjy.ky();
              i2++;
            }
          }
          arrayOfInt2[i2] = paramjy.kB();
          this.eS = arrayOfInt2;
          break;
        case 26:
          int j = paramjy.cw(paramjy.kE());
          int k = paramjy.getPosition();
          for (int m = 0; paramjy.kJ() > 0; m++)
            paramjy.kB();
          paramjy.cy(k);
          if (this.eS == null);
          int[] arrayOfInt1;
          for (int n = 0; ; n = this.eS.length)
          {
            arrayOfInt1 = new int[m + n];
            if (n != 0)
              System.arraycopy(this.eS, 0, arrayOfInt1, 0, n);
            while (n < arrayOfInt1.length)
            {
              arrayOfInt1[n] = paramjy.kB();
              n++;
            }
          }
          this.eS = arrayOfInt1;
          paramjy.cx(j);
          break;
        case 32:
          this.name = paramjy.kB();
          break;
        case 48:
        }
        this.eU = paramjy.kC();
      }
    }

    public b e()
    {
      this.eS = kh.aaj;
      this.eT = 0;
      this.name = 0;
      this.eU = false;
      this.eV = false;
      this.aae = null;
      this.DY = -1;
      return this;
    }

    public boolean equals(Object paramObject)
    {
      int i;
      if (paramObject == this)
        i = 1;
      b localb;
      while (true)
      {
        return i;
        boolean bool1 = paramObject instanceof b;
        i = 0;
        if (!bool1)
          continue;
        localb = (b)paramObject;
        boolean bool2 = kc.equals(this.eS, localb.eS);
        i = 0;
        if (!bool2)
          continue;
        int j = this.eT;
        int k = localb.eT;
        i = 0;
        if (j != k)
          continue;
        int m = this.name;
        int n = localb.name;
        i = 0;
        if (m != n)
          continue;
        boolean bool3 = this.eU;
        boolean bool4 = localb.eU;
        i = 0;
        if (bool3 != bool4)
          continue;
        boolean bool5 = this.eV;
        boolean bool6 = localb.eV;
        i = 0;
        if (bool5 != bool6)
          continue;
        if ((this.aae != null) && (!this.aae.isEmpty()))
          break;
        if (localb.aae != null)
        {
          boolean bool7 = localb.aae.isEmpty();
          i = 0;
          if (!bool7)
            continue;
        }
        return true;
      }
      return this.aae.equals(localb.aae);
    }

    public int hashCode()
    {
      int i = 1231;
      int j = 31 * (31 * (31 * (527 + kc.hashCode(this.eS)) + this.eT) + this.name);
      int k;
      label59: int n;
      if (this.eU)
      {
        k = i;
        int m = 31 * (k + j);
        if (!this.eV)
          break label103;
        n = 31 * (m + i);
        if ((this.aae != null) && (!this.aae.isEmpty()))
          break label110;
      }
      label103: label110: for (int i1 = 0; ; i1 = this.aae.hashCode())
      {
        return i1 + n;
        k = 1237;
        break;
        i = 1237;
        break label59;
      }
    }
  }

  public static final class c extends ka<c>
  {
    private static volatile c[] eW;
    public String eX;
    public long eY;
    public long eZ;
    public boolean fa;
    public long fb;

    public c()
    {
      g();
    }

    public static c[] f()
    {
      if (eW == null);
      synchronized (kc.aah)
      {
        if (eW == null)
          eW = new c[0];
        return eW;
      }
    }

    public void a(jz paramjz)
      throws IOException
    {
      if (!this.eX.equals(""))
        paramjz.b(1, this.eX);
      if (this.eY != 0L)
        paramjz.b(2, this.eY);
      if (this.eZ != 2147483647L)
        paramjz.b(3, this.eZ);
      if (this.fa)
        paramjz.a(4, this.fa);
      if (this.fb != 0L)
        paramjz.b(5, this.fb);
      super.a(paramjz);
    }

    public int c()
    {
      int i = super.c();
      if (!this.eX.equals(""))
        i += jz.g(1, this.eX);
      if (this.eY != 0L)
        i += jz.d(2, this.eY);
      if (this.eZ != 2147483647L)
        i += jz.d(3, this.eZ);
      if (this.fa)
        i += jz.b(4, this.fa);
      if (this.fb != 0L)
        i += jz.d(5, this.fb);
      this.DY = i;
      return i;
    }

    public c d(jy paramjy)
      throws IOException
    {
      while (true)
      {
        int i = paramjy.ky();
        switch (i)
        {
        default:
          if (a(paramjy, i))
            continue;
        case 0:
          return this;
        case 10:
          this.eX = paramjy.readString();
          break;
        case 16:
          this.eY = paramjy.kA();
          break;
        case 24:
          this.eZ = paramjy.kA();
          break;
        case 32:
          this.fa = paramjy.kC();
          break;
        case 40:
        }
        this.fb = paramjy.kA();
      }
    }

    public boolean equals(Object paramObject)
    {
      int i;
      if (paramObject == this)
        i = 1;
      c localc;
      String str;
      do
      {
        boolean bool1;
        do
        {
          return i;
          bool1 = paramObject instanceof c;
          i = 0;
        }
        while (!bool1);
        localc = (c)paramObject;
        if (this.eX != null)
          break;
        str = localc.eX;
        i = 0;
      }
      while (str != null);
      do
      {
        boolean bool2 = this.eY < localc.eY;
        i = 0;
        if (bool2)
          break;
        boolean bool3 = this.eZ < localc.eZ;
        i = 0;
        if (bool3)
          break;
        boolean bool4 = this.fa;
        boolean bool5 = localc.fa;
        i = 0;
        if (bool4 != bool5)
          break;
        boolean bool6 = this.fb < localc.fb;
        i = 0;
        if (bool6)
          break;
        if ((this.aae != null) && (!this.aae.isEmpty()))
          break label191;
        if (localc.aae != null)
        {
          boolean bool7 = localc.aae.isEmpty();
          i = 0;
          if (!bool7)
            break;
        }
        return true;
      }
      while (this.eX.equals(localc.eX));
      return false;
      label191: return this.aae.equals(localc.aae);
    }

    public c g()
    {
      this.eX = "";
      this.eY = 0L;
      this.eZ = 2147483647L;
      this.fa = false;
      this.fb = 0L;
      this.aae = null;
      this.DY = -1;
      return this;
    }

    public int hashCode()
    {
      int i;
      int k;
      label63: int m;
      int n;
      if (this.eX == null)
      {
        i = 0;
        int j = 31 * (31 * (31 * (i + 527) + (int)(this.eY ^ this.eY >>> 32)) + (int)(this.eZ ^ this.eZ >>> 32));
        if (!this.fa)
          break label138;
        k = 1231;
        m = 31 * (31 * (k + j) + (int)(this.fb ^ this.fb >>> 32));
        List localList = this.aae;
        n = 0;
        if (localList != null)
        {
          boolean bool = this.aae.isEmpty();
          n = 0;
          if (!bool)
            break label145;
        }
      }
      while (true)
      {
        return m + n;
        i = this.eX.hashCode();
        break;
        label138: k = 1237;
        break label63;
        label145: n = this.aae.hashCode();
      }
    }
  }

  public static final class d extends ka<d>
  {
    public d.a[] fc;
    public d.a[] fd;
    public c.c[] fe;

    public d()
    {
      h();
    }

    public void a(jz paramjz)
      throws IOException
    {
      if ((this.fc != null) && (this.fc.length > 0))
        for (int m = 0; m < this.fc.length; m++)
        {
          d.a locala2 = this.fc[m];
          if (locala2 == null)
            continue;
          paramjz.a(1, locala2);
        }
      if ((this.fd != null) && (this.fd.length > 0))
        for (int k = 0; k < this.fd.length; k++)
        {
          d.a locala1 = this.fd[k];
          if (locala1 == null)
            continue;
          paramjz.a(2, locala1);
        }
      if (this.fe != null)
      {
        int i = this.fe.length;
        int j = 0;
        if (i > 0)
          while (j < this.fe.length)
          {
            c.c localc = this.fe[j];
            if (localc != null)
              paramjz.a(3, localc);
            j++;
          }
      }
      super.a(paramjz);
    }

    public int c()
    {
      int i = super.c();
      if ((this.fc != null) && (this.fc.length > 0))
      {
        int i1 = i;
        for (int i2 = 0; i2 < this.fc.length; i2++)
        {
          d.a locala2 = this.fc[i2];
          if (locala2 == null)
            continue;
          i1 += jz.b(1, locala2);
        }
        i = i1;
      }
      if ((this.fd != null) && (this.fd.length > 0))
      {
        int m = i;
        for (int n = 0; n < this.fd.length; n++)
        {
          d.a locala1 = this.fd[n];
          if (locala1 == null)
            continue;
          m += jz.b(2, locala1);
        }
        i = m;
      }
      if (this.fe != null)
      {
        int j = this.fe.length;
        int k = 0;
        if (j > 0)
          while (k < this.fe.length)
          {
            c.c localc = this.fe[k];
            if (localc != null)
              i += jz.b(3, localc);
            k++;
          }
      }
      this.DY = i;
      return i;
    }

    public d e(jy paramjy)
      throws IOException
    {
      while (true)
      {
        int i = paramjy.ky();
        switch (i)
        {
        default:
          if (a(paramjy, i))
            continue;
        case 0:
          return this;
        case 10:
          int i1 = kh.c(paramjy, 10);
          if (this.fc == null);
          d.a[] arrayOfa2;
          for (int i2 = 0; ; i2 = this.fc.length)
          {
            arrayOfa2 = new d.a[i1 + i2];
            if (i2 != 0)
              System.arraycopy(this.fc, 0, arrayOfa2, 0, i2);
            while (i2 < -1 + arrayOfa2.length)
            {
              arrayOfa2[i2] = new d.a();
              paramjy.a(arrayOfa2[i2]);
              paramjy.ky();
              i2++;
            }
          }
          arrayOfa2[i2] = new d.a();
          paramjy.a(arrayOfa2[i2]);
          this.fc = arrayOfa2;
          break;
        case 18:
          int m = kh.c(paramjy, 18);
          if (this.fd == null);
          d.a[] arrayOfa1;
          for (int n = 0; ; n = this.fd.length)
          {
            arrayOfa1 = new d.a[m + n];
            if (n != 0)
              System.arraycopy(this.fd, 0, arrayOfa1, 0, n);
            while (n < -1 + arrayOfa1.length)
            {
              arrayOfa1[n] = new d.a();
              paramjy.a(arrayOfa1[n]);
              paramjy.ky();
              n++;
            }
          }
          arrayOfa1[n] = new d.a();
          paramjy.a(arrayOfa1[n]);
          this.fd = arrayOfa1;
          break;
        case 26:
        }
        int j = kh.c(paramjy, 26);
        if (this.fe == null);
        c.c[] arrayOfc;
        for (int k = 0; ; k = this.fe.length)
        {
          arrayOfc = new c.c[j + k];
          if (k != 0)
            System.arraycopy(this.fe, 0, arrayOfc, 0, k);
          while (k < -1 + arrayOfc.length)
          {
            arrayOfc[k] = new c.c();
            paramjy.a(arrayOfc[k]);
            paramjy.ky();
            k++;
          }
        }
        arrayOfc[k] = new c.c();
        paramjy.a(arrayOfc[k]);
        this.fe = arrayOfc;
      }
    }

    public boolean equals(Object paramObject)
    {
      int i;
      if (paramObject == this)
        i = 1;
      d locald;
      while (true)
      {
        return i;
        boolean bool1 = paramObject instanceof d;
        i = 0;
        if (!bool1)
          continue;
        locald = (d)paramObject;
        boolean bool2 = kc.equals(this.fc, locald.fc);
        i = 0;
        if (!bool2)
          continue;
        boolean bool3 = kc.equals(this.fd, locald.fd);
        i = 0;
        if (!bool3)
          continue;
        boolean bool4 = kc.equals(this.fe, locald.fe);
        i = 0;
        if (!bool4)
          continue;
        if ((this.aae != null) && (!this.aae.isEmpty()))
          break;
        if (locald.aae != null)
        {
          boolean bool5 = locald.aae.isEmpty();
          i = 0;
          if (!bool5)
            continue;
        }
        return true;
      }
      return this.aae.equals(locald.aae);
    }

    public d h()
    {
      this.fc = d.a.r();
      this.fd = d.a.r();
      this.fe = c.c.f();
      this.aae = null;
      this.DY = -1;
      return this;
    }

    public int hashCode()
    {
      int i = 31 * (31 * (31 * (527 + kc.hashCode(this.fc)) + kc.hashCode(this.fd)) + kc.hashCode(this.fe));
      if ((this.aae == null) || (this.aae.isEmpty()));
      for (int j = 0; ; j = this.aae.hashCode())
        return j + i;
    }
  }

  public static final class e extends ka<e>
  {
    private static volatile e[] ff;
    public int key;
    public int value;

    public e()
    {
      j();
    }

    public static e[] i()
    {
      if (ff == null);
      synchronized (kc.aah)
      {
        if (ff == null)
          ff = new e[0];
        return ff;
      }
    }

    public void a(jz paramjz)
      throws IOException
    {
      paramjz.f(1, this.key);
      paramjz.f(2, this.value);
      super.a(paramjz);
    }

    public int c()
    {
      int i = super.c() + jz.g(1, this.key) + jz.g(2, this.value);
      this.DY = i;
      return i;
    }

    public boolean equals(Object paramObject)
    {
      int i;
      if (paramObject == this)
        i = 1;
      e locale;
      while (true)
      {
        return i;
        boolean bool1 = paramObject instanceof e;
        i = 0;
        if (!bool1)
          continue;
        locale = (e)paramObject;
        int j = this.key;
        int k = locale.key;
        i = 0;
        if (j != k)
          continue;
        int m = this.value;
        int n = locale.value;
        i = 0;
        if (m != n)
          continue;
        if ((this.aae != null) && (!this.aae.isEmpty()))
          break;
        if (locale.aae != null)
        {
          boolean bool2 = locale.aae.isEmpty();
          i = 0;
          if (!bool2)
            continue;
        }
        return true;
      }
      return this.aae.equals(locale.aae);
    }

    public e f(jy paramjy)
      throws IOException
    {
      while (true)
      {
        int i = paramjy.ky();
        switch (i)
        {
        default:
          if (a(paramjy, i))
            continue;
        case 0:
          return this;
        case 8:
          this.key = paramjy.kB();
          break;
        case 16:
        }
        this.value = paramjy.kB();
      }
    }

    public int hashCode()
    {
      int i = 31 * (31 * (527 + this.key) + this.value);
      if ((this.aae == null) || (this.aae.isEmpty()));
      for (int j = 0; ; j = this.aae.hashCode())
        return j + i;
    }

    public e j()
    {
      this.key = 0;
      this.value = 0;
      this.aae = null;
      this.DY = -1;
      return this;
    }
  }

  public static final class f extends ka<f>
  {
    public String[] fg;
    public String[] fh;
    public d.a[] fi;
    public c.e[] fj;
    public c.b[] fk;
    public c.b[] fl;
    public c.b[] fm;
    public c.g[] fn;
    public String fo;
    public String fp;
    public String fq;
    public String fr;
    public c.a fs;
    public float ft;
    public boolean fu;
    public String[] fv;
    public int fw;

    public f()
    {
      k();
    }

    public static f a(byte[] paramArrayOfByte)
      throws kd
    {
      return (f)ke.a(new f(), paramArrayOfByte);
    }

    public void a(jz paramjz)
      throws IOException
    {
      if ((this.fh != null) && (this.fh.length > 0))
        for (int i5 = 0; i5 < this.fh.length; i5++)
        {
          String str3 = this.fh[i5];
          if (str3 == null)
            continue;
          paramjz.b(1, str3);
        }
      if ((this.fi != null) && (this.fi.length > 0))
        for (int i4 = 0; i4 < this.fi.length; i4++)
        {
          d.a locala = this.fi[i4];
          if (locala == null)
            continue;
          paramjz.a(2, locala);
        }
      if ((this.fj != null) && (this.fj.length > 0))
        for (int i3 = 0; i3 < this.fj.length; i3++)
        {
          c.e locale = this.fj[i3];
          if (locale == null)
            continue;
          paramjz.a(3, locale);
        }
      if ((this.fk != null) && (this.fk.length > 0))
        for (int i2 = 0; i2 < this.fk.length; i2++)
        {
          c.b localb3 = this.fk[i2];
          if (localb3 == null)
            continue;
          paramjz.a(4, localb3);
        }
      if ((this.fl != null) && (this.fl.length > 0))
        for (int i1 = 0; i1 < this.fl.length; i1++)
        {
          c.b localb2 = this.fl[i1];
          if (localb2 == null)
            continue;
          paramjz.a(5, localb2);
        }
      if ((this.fm != null) && (this.fm.length > 0))
        for (int n = 0; n < this.fm.length; n++)
        {
          c.b localb1 = this.fm[n];
          if (localb1 == null)
            continue;
          paramjz.a(6, localb1);
        }
      if ((this.fn != null) && (this.fn.length > 0))
        for (int m = 0; m < this.fn.length; m++)
        {
          c.g localg = this.fn[m];
          if (localg == null)
            continue;
          paramjz.a(7, localg);
        }
      if (!this.fo.equals(""))
        paramjz.b(9, this.fo);
      if (!this.fp.equals(""))
        paramjz.b(10, this.fp);
      if (!this.fq.equals("0"))
        paramjz.b(12, this.fq);
      if (!this.fr.equals(""))
        paramjz.b(13, this.fr);
      if (this.fs != null)
        paramjz.a(14, this.fs);
      if (Float.floatToIntBits(this.ft) != Float.floatToIntBits(0.0F))
        paramjz.a(15, this.ft);
      if ((this.fv != null) && (this.fv.length > 0))
        for (int k = 0; k < this.fv.length; k++)
        {
          String str2 = this.fv[k];
          if (str2 == null)
            continue;
          paramjz.b(16, str2);
        }
      if (this.fw != 0)
        paramjz.f(17, this.fw);
      if (this.fu)
        paramjz.a(18, this.fu);
      if (this.fg != null)
      {
        int i = this.fg.length;
        int j = 0;
        if (i > 0)
          while (j < this.fg.length)
          {
            String str1 = this.fg[j];
            if (str1 != null)
              paramjz.b(19, str1);
            j++;
          }
      }
      super.a(paramjz);
    }

    public int c()
    {
      int i = 0;
      int j = super.c();
      int i17;
      int i18;
      if ((this.fh != null) && (this.fh.length > 0))
      {
        int i16 = 0;
        i17 = 0;
        i18 = 0;
        while (i16 < this.fh.length)
        {
          String str3 = this.fh[i16];
          if (str3 != null)
          {
            i18++;
            i17 += jz.bQ(str3);
          }
          i16++;
        }
      }
      for (int k = j + i17 + i18 * 1; ; k = j)
      {
        if ((this.fi != null) && (this.fi.length > 0))
        {
          int i14 = k;
          for (int i15 = 0; i15 < this.fi.length; i15++)
          {
            d.a locala = this.fi[i15];
            if (locala == null)
              continue;
            i14 += jz.b(2, locala);
          }
          k = i14;
        }
        if ((this.fj != null) && (this.fj.length > 0))
        {
          int i12 = k;
          for (int i13 = 0; i13 < this.fj.length; i13++)
          {
            c.e locale = this.fj[i13];
            if (locale == null)
              continue;
            i12 += jz.b(3, locale);
          }
          k = i12;
        }
        if ((this.fk != null) && (this.fk.length > 0))
        {
          int i10 = k;
          for (int i11 = 0; i11 < this.fk.length; i11++)
          {
            c.b localb3 = this.fk[i11];
            if (localb3 == null)
              continue;
            i10 += jz.b(4, localb3);
          }
          k = i10;
        }
        if ((this.fl != null) && (this.fl.length > 0))
        {
          int i8 = k;
          for (int i9 = 0; i9 < this.fl.length; i9++)
          {
            c.b localb2 = this.fl[i9];
            if (localb2 == null)
              continue;
            i8 += jz.b(5, localb2);
          }
          k = i8;
        }
        if ((this.fm != null) && (this.fm.length > 0))
        {
          int i6 = k;
          for (int i7 = 0; i7 < this.fm.length; i7++)
          {
            c.b localb1 = this.fm[i7];
            if (localb1 == null)
              continue;
            i6 += jz.b(6, localb1);
          }
          k = i6;
        }
        if ((this.fn != null) && (this.fn.length > 0))
        {
          int i4 = k;
          for (int i5 = 0; i5 < this.fn.length; i5++)
          {
            c.g localg = this.fn[i5];
            if (localg == null)
              continue;
            i4 += jz.b(7, localg);
          }
          k = i4;
        }
        if (!this.fo.equals(""))
          k += jz.g(9, this.fo);
        if (!this.fp.equals(""))
          k += jz.g(10, this.fp);
        if (!this.fq.equals("0"))
          k += jz.g(12, this.fq);
        if (!this.fr.equals(""))
          k += jz.g(13, this.fr);
        if (this.fs != null)
          k += jz.b(14, this.fs);
        if (Float.floatToIntBits(this.ft) != Float.floatToIntBits(0.0F))
          k += jz.b(15, this.ft);
        if ((this.fv != null) && (this.fv.length > 0))
        {
          int i1 = 0;
          int i2 = 0;
          int i3 = 0;
          while (i1 < this.fv.length)
          {
            String str2 = this.fv[i1];
            if (str2 != null)
            {
              i3++;
              i2 += jz.bQ(str2);
            }
            i1++;
          }
          k = k + i2 + i3 * 2;
        }
        if (this.fw != 0)
          k += jz.g(17, this.fw);
        if (this.fu)
          k += jz.b(18, this.fu);
        if ((this.fg != null) && (this.fg.length > 0))
        {
          int m = 0;
          int n = 0;
          while (i < this.fg.length)
          {
            String str1 = this.fg[i];
            if (str1 != null)
            {
              n++;
              m += jz.bQ(str1);
            }
            i++;
          }
          k = k + m + n * 2;
        }
        this.DY = k;
        return k;
      }
    }

    public boolean equals(Object paramObject)
    {
      int i;
      if (paramObject == this)
        i = 1;
      f localf;
      label215: label236: c.a locala;
      label257: label278: 
      do
      {
        String str1;
        do
        {
          String str2;
          do
          {
            String str3;
            do
            {
              String str4;
              do
              {
                boolean bool9;
                do
                {
                  boolean bool8;
                  do
                  {
                    boolean bool7;
                    do
                    {
                      boolean bool6;
                      do
                      {
                        boolean bool5;
                        do
                        {
                          boolean bool4;
                          do
                          {
                            boolean bool3;
                            do
                            {
                              boolean bool2;
                              do
                              {
                                boolean bool1;
                                do
                                {
                                  return i;
                                  bool1 = paramObject instanceof f;
                                  i = 0;
                                }
                                while (!bool1);
                                localf = (f)paramObject;
                                bool2 = kc.equals(this.fg, localf.fg);
                                i = 0;
                              }
                              while (!bool2);
                              bool3 = kc.equals(this.fh, localf.fh);
                              i = 0;
                            }
                            while (!bool3);
                            bool4 = kc.equals(this.fi, localf.fi);
                            i = 0;
                          }
                          while (!bool4);
                          bool5 = kc.equals(this.fj, localf.fj);
                          i = 0;
                        }
                        while (!bool5);
                        bool6 = kc.equals(this.fk, localf.fk);
                        i = 0;
                      }
                      while (!bool6);
                      bool7 = kc.equals(this.fl, localf.fl);
                      i = 0;
                    }
                    while (!bool7);
                    bool8 = kc.equals(this.fm, localf.fm);
                    i = 0;
                  }
                  while (!bool8);
                  bool9 = kc.equals(this.fn, localf.fn);
                  i = 0;
                }
                while (!bool9);
                if (this.fo != null)
                  break;
                str4 = localf.fo;
                i = 0;
              }
              while (str4 != null);
              if (this.fp != null)
                break label457;
              str3 = localf.fp;
              i = 0;
            }
            while (str3 != null);
            if (this.fq != null)
              break label474;
            str2 = localf.fq;
            i = 0;
          }
          while (str2 != null);
          if (this.fr != null)
            break label491;
          str1 = localf.fr;
          i = 0;
        }
        while (str1 != null);
        if (this.fs != null)
          break label508;
        locala = localf.fs;
        i = 0;
      }
      while (locala != null);
      label457: label474: label491: label508: 
      do
      {
        int j = Float.floatToIntBits(this.ft);
        int k = Float.floatToIntBits(localf.ft);
        i = 0;
        if (j != k)
          break;
        boolean bool10 = this.fu;
        boolean bool11 = localf.fu;
        i = 0;
        if (bool10 != bool11)
          break;
        boolean bool12 = kc.equals(this.fv, localf.fv);
        i = 0;
        if (!bool12)
          break;
        int m = this.fw;
        int n = localf.fw;
        i = 0;
        if (m != n)
          break;
        if ((this.aae != null) && (!this.aae.isEmpty()))
          break label525;
        if (localf.aae != null)
        {
          boolean bool13 = localf.aae.isEmpty();
          i = 0;
          if (!bool13)
            break;
        }
        return true;
        if (this.fo.equals(localf.fo))
          break label215;
        return false;
        if (this.fp.equals(localf.fp))
          break label236;
        return false;
        if (this.fq.equals(localf.fq))
          break label257;
        return false;
        if (this.fr.equals(localf.fr))
          break label278;
        return false;
      }
      while (this.fs.equals(localf.fs));
      return false;
      label525: return this.aae.equals(localf.aae);
    }

    public f g(jy paramjy)
      throws IOException
    {
      while (true)
      {
        int i = paramjy.ky();
        switch (i)
        {
        default:
          if (a(paramjy, i))
            continue;
        case 0:
          return this;
        case 10:
          int i13 = kh.c(paramjy, 10);
          if (this.fh == null);
          String[] arrayOfString3;
          for (int i14 = 0; ; i14 = this.fh.length)
          {
            arrayOfString3 = new String[i13 + i14];
            if (i14 != 0)
              System.arraycopy(this.fh, 0, arrayOfString3, 0, i14);
            while (i14 < -1 + arrayOfString3.length)
            {
              arrayOfString3[i14] = paramjy.readString();
              paramjy.ky();
              i14++;
            }
          }
          arrayOfString3[i14] = paramjy.readString();
          this.fh = arrayOfString3;
          break;
        case 18:
          int i11 = kh.c(paramjy, 18);
          if (this.fi == null);
          d.a[] arrayOfa;
          for (int i12 = 0; ; i12 = this.fi.length)
          {
            arrayOfa = new d.a[i11 + i12];
            if (i12 != 0)
              System.arraycopy(this.fi, 0, arrayOfa, 0, i12);
            while (i12 < -1 + arrayOfa.length)
            {
              arrayOfa[i12] = new d.a();
              paramjy.a(arrayOfa[i12]);
              paramjy.ky();
              i12++;
            }
          }
          arrayOfa[i12] = new d.a();
          paramjy.a(arrayOfa[i12]);
          this.fi = arrayOfa;
          break;
        case 26:
          int i9 = kh.c(paramjy, 26);
          if (this.fj == null);
          c.e[] arrayOfe;
          for (int i10 = 0; ; i10 = this.fj.length)
          {
            arrayOfe = new c.e[i9 + i10];
            if (i10 != 0)
              System.arraycopy(this.fj, 0, arrayOfe, 0, i10);
            while (i10 < -1 + arrayOfe.length)
            {
              arrayOfe[i10] = new c.e();
              paramjy.a(arrayOfe[i10]);
              paramjy.ky();
              i10++;
            }
          }
          arrayOfe[i10] = new c.e();
          paramjy.a(arrayOfe[i10]);
          this.fj = arrayOfe;
          break;
        case 34:
          int i7 = kh.c(paramjy, 34);
          if (this.fk == null);
          c.b[] arrayOfb3;
          for (int i8 = 0; ; i8 = this.fk.length)
          {
            arrayOfb3 = new c.b[i7 + i8];
            if (i8 != 0)
              System.arraycopy(this.fk, 0, arrayOfb3, 0, i8);
            while (i8 < -1 + arrayOfb3.length)
            {
              arrayOfb3[i8] = new c.b();
              paramjy.a(arrayOfb3[i8]);
              paramjy.ky();
              i8++;
            }
          }
          arrayOfb3[i8] = new c.b();
          paramjy.a(arrayOfb3[i8]);
          this.fk = arrayOfb3;
          break;
        case 42:
          int i5 = kh.c(paramjy, 42);
          if (this.fl == null);
          c.b[] arrayOfb2;
          for (int i6 = 0; ; i6 = this.fl.length)
          {
            arrayOfb2 = new c.b[i5 + i6];
            if (i6 != 0)
              System.arraycopy(this.fl, 0, arrayOfb2, 0, i6);
            while (i6 < -1 + arrayOfb2.length)
            {
              arrayOfb2[i6] = new c.b();
              paramjy.a(arrayOfb2[i6]);
              paramjy.ky();
              i6++;
            }
          }
          arrayOfb2[i6] = new c.b();
          paramjy.a(arrayOfb2[i6]);
          this.fl = arrayOfb2;
          break;
        case 50:
          int i3 = kh.c(paramjy, 50);
          if (this.fm == null);
          c.b[] arrayOfb1;
          for (int i4 = 0; ; i4 = this.fm.length)
          {
            arrayOfb1 = new c.b[i3 + i4];
            if (i4 != 0)
              System.arraycopy(this.fm, 0, arrayOfb1, 0, i4);
            while (i4 < -1 + arrayOfb1.length)
            {
              arrayOfb1[i4] = new c.b();
              paramjy.a(arrayOfb1[i4]);
              paramjy.ky();
              i4++;
            }
          }
          arrayOfb1[i4] = new c.b();
          paramjy.a(arrayOfb1[i4]);
          this.fm = arrayOfb1;
          break;
        case 58:
          int i1 = kh.c(paramjy, 58);
          if (this.fn == null);
          c.g[] arrayOfg;
          for (int i2 = 0; ; i2 = this.fn.length)
          {
            arrayOfg = new c.g[i1 + i2];
            if (i2 != 0)
              System.arraycopy(this.fn, 0, arrayOfg, 0, i2);
            while (i2 < -1 + arrayOfg.length)
            {
              arrayOfg[i2] = new c.g();
              paramjy.a(arrayOfg[i2]);
              paramjy.ky();
              i2++;
            }
          }
          arrayOfg[i2] = new c.g();
          paramjy.a(arrayOfg[i2]);
          this.fn = arrayOfg;
          break;
        case 74:
          this.fo = paramjy.readString();
          break;
        case 82:
          this.fp = paramjy.readString();
          break;
        case 98:
          this.fq = paramjy.readString();
          break;
        case 106:
          this.fr = paramjy.readString();
          break;
        case 114:
          if (this.fs == null)
            this.fs = new c.a();
          paramjy.a(this.fs);
          break;
        case 125:
          this.ft = paramjy.readFloat();
          break;
        case 130:
          int m = kh.c(paramjy, 130);
          if (this.fv == null);
          String[] arrayOfString2;
          for (int n = 0; ; n = this.fv.length)
          {
            arrayOfString2 = new String[m + n];
            if (n != 0)
              System.arraycopy(this.fv, 0, arrayOfString2, 0, n);
            while (n < -1 + arrayOfString2.length)
            {
              arrayOfString2[n] = paramjy.readString();
              paramjy.ky();
              n++;
            }
          }
          arrayOfString2[n] = paramjy.readString();
          this.fv = arrayOfString2;
          break;
        case 136:
          this.fw = paramjy.kB();
          break;
        case 144:
          this.fu = paramjy.kC();
          break;
        case 154:
        }
        int j = kh.c(paramjy, 154);
        if (this.fg == null);
        String[] arrayOfString1;
        for (int k = 0; ; k = this.fg.length)
        {
          arrayOfString1 = new String[j + k];
          if (k != 0)
            System.arraycopy(this.fg, 0, arrayOfString1, 0, k);
          while (k < -1 + arrayOfString1.length)
          {
            arrayOfString1[k] = paramjy.readString();
            paramjy.ky();
            k++;
          }
        }
        arrayOfString1[k] = paramjy.readString();
        this.fg = arrayOfString1;
      }
    }

    public int hashCode()
    {
      int i = 31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * (527 + kc.hashCode(this.fg)) + kc.hashCode(this.fh)) + kc.hashCode(this.fi)) + kc.hashCode(this.fj)) + kc.hashCode(this.fk)) + kc.hashCode(this.fl)) + kc.hashCode(this.fm)) + kc.hashCode(this.fn));
      int j;
      int m;
      label118: int i1;
      label137: int i3;
      label157: int i5;
      label177: int i7;
      label210: int i8;
      int i9;
      if (this.fo == null)
      {
        j = 0;
        int k = 31 * (j + i);
        if (this.fp != null)
          break label289;
        m = 0;
        int n = 31 * (m + k);
        if (this.fq != null)
          break label301;
        i1 = 0;
        int i2 = 31 * (i1 + n);
        if (this.fr != null)
          break label313;
        i3 = 0;
        int i4 = 31 * (i3 + i2);
        if (this.fs != null)
          break label325;
        i5 = 0;
        int i6 = 31 * (31 * (i5 + i4) + Float.floatToIntBits(this.ft));
        if (!this.fu)
          break label337;
        i7 = 1231;
        i8 = 31 * (31 * (31 * (i7 + i6) + kc.hashCode(this.fv)) + this.fw);
        List localList = this.aae;
        i9 = 0;
        if (localList != null)
        {
          boolean bool = this.aae.isEmpty();
          i9 = 0;
          if (!bool)
            break label345;
        }
      }
      while (true)
      {
        return i8 + i9;
        j = this.fo.hashCode();
        break;
        label289: m = this.fp.hashCode();
        break label118;
        label301: i1 = this.fq.hashCode();
        break label137;
        label313: i3 = this.fr.hashCode();
        break label157;
        label325: i5 = this.fs.hashCode();
        break label177;
        label337: i7 = 1237;
        break label210;
        label345: i9 = this.aae.hashCode();
      }
    }

    public f k()
    {
      this.fg = kh.aao;
      this.fh = kh.aao;
      this.fi = d.a.r();
      this.fj = c.e.i();
      this.fk = c.b.d();
      this.fl = c.b.d();
      this.fm = c.b.d();
      this.fn = c.g.l();
      this.fo = "";
      this.fp = "";
      this.fq = "0";
      this.fr = "";
      this.fs = null;
      this.ft = 0.0F;
      this.fu = false;
      this.fv = kh.aao;
      this.fw = 0;
      this.aae = null;
      this.DY = -1;
      return this;
    }
  }

  public static final class g extends ka<g>
  {
    private static volatile g[] fx;
    public int[] fA;
    public int[] fB;
    public int[] fC;
    public int[] fD;
    public int[] fE;
    public int[] fF;
    public int[] fG;
    public int[] fH;
    public int[] fy;
    public int[] fz;

    public g()
    {
      m();
    }

    public static g[] l()
    {
      if (fx == null);
      synchronized (kc.aah)
      {
        if (fx == null)
          fx = new g[0];
        return fx;
      }
    }

    public void a(jz paramjz)
      throws IOException
    {
      if ((this.fy != null) && (this.fy.length > 0))
        for (int i6 = 0; i6 < this.fy.length; i6++)
          paramjz.f(1, this.fy[i6]);
      if ((this.fz != null) && (this.fz.length > 0))
        for (int i5 = 0; i5 < this.fz.length; i5++)
          paramjz.f(2, this.fz[i5]);
      if ((this.fA != null) && (this.fA.length > 0))
        for (int i4 = 0; i4 < this.fA.length; i4++)
          paramjz.f(3, this.fA[i4]);
      if ((this.fB != null) && (this.fB.length > 0))
        for (int i3 = 0; i3 < this.fB.length; i3++)
          paramjz.f(4, this.fB[i3]);
      if ((this.fC != null) && (this.fC.length > 0))
        for (int i2 = 0; i2 < this.fC.length; i2++)
          paramjz.f(5, this.fC[i2]);
      if ((this.fD != null) && (this.fD.length > 0))
        for (int i1 = 0; i1 < this.fD.length; i1++)
          paramjz.f(6, this.fD[i1]);
      if ((this.fE != null) && (this.fE.length > 0))
        for (int n = 0; n < this.fE.length; n++)
          paramjz.f(7, this.fE[n]);
      if ((this.fF != null) && (this.fF.length > 0))
        for (int m = 0; m < this.fF.length; m++)
          paramjz.f(8, this.fF[m]);
      if ((this.fG != null) && (this.fG.length > 0))
        for (int k = 0; k < this.fG.length; k++)
          paramjz.f(9, this.fG[k]);
      if (this.fH != null)
      {
        int i = this.fH.length;
        int j = 0;
        if (i > 0)
          while (j < this.fH.length)
          {
            paramjz.f(10, this.fH[j]);
            j++;
          }
      }
      super.a(paramjz);
    }

    public int c()
    {
      int i = 0;
      int j = super.c();
      int i17;
      if ((this.fy != null) && (this.fy.length > 0))
      {
        int i16 = 0;
        i17 = 0;
        while (i16 < this.fy.length)
        {
          i17 += jz.cC(this.fy[i16]);
          i16++;
        }
      }
      for (int k = j + i17 + 1 * this.fy.length; ; k = j)
      {
        if ((this.fz != null) && (this.fz.length > 0))
        {
          int i14 = 0;
          int i15 = 0;
          while (i14 < this.fz.length)
          {
            i15 += jz.cC(this.fz[i14]);
            i14++;
          }
          k = k + i15 + 1 * this.fz.length;
        }
        if ((this.fA != null) && (this.fA.length > 0))
        {
          int i12 = 0;
          int i13 = 0;
          while (i12 < this.fA.length)
          {
            i13 += jz.cC(this.fA[i12]);
            i12++;
          }
          k = k + i13 + 1 * this.fA.length;
        }
        if ((this.fB != null) && (this.fB.length > 0))
        {
          int i10 = 0;
          int i11 = 0;
          while (i10 < this.fB.length)
          {
            i11 += jz.cC(this.fB[i10]);
            i10++;
          }
          k = k + i11 + 1 * this.fB.length;
        }
        if ((this.fC != null) && (this.fC.length > 0))
        {
          int i8 = 0;
          int i9 = 0;
          while (i8 < this.fC.length)
          {
            i9 += jz.cC(this.fC[i8]);
            i8++;
          }
          k = k + i9 + 1 * this.fC.length;
        }
        if ((this.fD != null) && (this.fD.length > 0))
        {
          int i6 = 0;
          int i7 = 0;
          while (i6 < this.fD.length)
          {
            i7 += jz.cC(this.fD[i6]);
            i6++;
          }
          k = k + i7 + 1 * this.fD.length;
        }
        if ((this.fE != null) && (this.fE.length > 0))
        {
          int i4 = 0;
          int i5 = 0;
          while (i4 < this.fE.length)
          {
            i5 += jz.cC(this.fE[i4]);
            i4++;
          }
          k = k + i5 + 1 * this.fE.length;
        }
        if ((this.fF != null) && (this.fF.length > 0))
        {
          int i2 = 0;
          int i3 = 0;
          while (i2 < this.fF.length)
          {
            i3 += jz.cC(this.fF[i2]);
            i2++;
          }
          k = k + i3 + 1 * this.fF.length;
        }
        if ((this.fG != null) && (this.fG.length > 0))
        {
          int n = 0;
          int i1 = 0;
          while (n < this.fG.length)
          {
            i1 += jz.cC(this.fG[n]);
            n++;
          }
          k = k + i1 + 1 * this.fG.length;
        }
        if ((this.fH != null) && (this.fH.length > 0))
        {
          int m = 0;
          while (i < this.fH.length)
          {
            m += jz.cC(this.fH[i]);
            i++;
          }
          k = k + m + 1 * this.fH.length;
        }
        this.DY = k;
        return k;
      }
    }

    public boolean equals(Object paramObject)
    {
      int i;
      if (paramObject == this)
        i = 1;
      g localg;
      while (true)
      {
        return i;
        boolean bool1 = paramObject instanceof g;
        i = 0;
        if (!bool1)
          continue;
        localg = (g)paramObject;
        boolean bool2 = kc.equals(this.fy, localg.fy);
        i = 0;
        if (!bool2)
          continue;
        boolean bool3 = kc.equals(this.fz, localg.fz);
        i = 0;
        if (!bool3)
          continue;
        boolean bool4 = kc.equals(this.fA, localg.fA);
        i = 0;
        if (!bool4)
          continue;
        boolean bool5 = kc.equals(this.fB, localg.fB);
        i = 0;
        if (!bool5)
          continue;
        boolean bool6 = kc.equals(this.fC, localg.fC);
        i = 0;
        if (!bool6)
          continue;
        boolean bool7 = kc.equals(this.fD, localg.fD);
        i = 0;
        if (!bool7)
          continue;
        boolean bool8 = kc.equals(this.fE, localg.fE);
        i = 0;
        if (!bool8)
          continue;
        boolean bool9 = kc.equals(this.fF, localg.fF);
        i = 0;
        if (!bool9)
          continue;
        boolean bool10 = kc.equals(this.fG, localg.fG);
        i = 0;
        if (!bool10)
          continue;
        boolean bool11 = kc.equals(this.fH, localg.fH);
        i = 0;
        if (!bool11)
          continue;
        if ((this.aae != null) && (!this.aae.isEmpty()))
          break;
        if (localg.aae != null)
        {
          boolean bool12 = localg.aae.isEmpty();
          i = 0;
          if (!bool12)
            continue;
        }
        return true;
      }
      return this.aae.equals(localg.aae);
    }

    public g h(jy paramjy)
      throws IOException
    {
      while (true)
      {
        int i = paramjy.ky();
        switch (i)
        {
        default:
          if (a(paramjy, i))
            continue;
        case 0:
          return this;
        case 8:
          int i55 = kh.c(paramjy, 8);
          if (this.fy == null);
          int[] arrayOfInt20;
          for (int i56 = 0; ; i56 = this.fy.length)
          {
            arrayOfInt20 = new int[i55 + i56];
            if (i56 != 0)
              System.arraycopy(this.fy, 0, arrayOfInt20, 0, i56);
            while (i56 < -1 + arrayOfInt20.length)
            {
              arrayOfInt20[i56] = paramjy.kB();
              paramjy.ky();
              i56++;
            }
          }
          arrayOfInt20[i56] = paramjy.kB();
          this.fy = arrayOfInt20;
          break;
        case 10:
          int i51 = paramjy.cw(paramjy.kE());
          int i52 = paramjy.getPosition();
          for (int i53 = 0; paramjy.kJ() > 0; i53++)
            paramjy.kB();
          paramjy.cy(i52);
          if (this.fy == null);
          int[] arrayOfInt19;
          for (int i54 = 0; ; i54 = this.fy.length)
          {
            arrayOfInt19 = new int[i53 + i54];
            if (i54 != 0)
              System.arraycopy(this.fy, 0, arrayOfInt19, 0, i54);
            while (i54 < arrayOfInt19.length)
            {
              arrayOfInt19[i54] = paramjy.kB();
              i54++;
            }
          }
          this.fy = arrayOfInt19;
          paramjy.cx(i51);
          break;
        case 16:
          int i49 = kh.c(paramjy, 16);
          if (this.fz == null);
          int[] arrayOfInt18;
          for (int i50 = 0; ; i50 = this.fz.length)
          {
            arrayOfInt18 = new int[i49 + i50];
            if (i50 != 0)
              System.arraycopy(this.fz, 0, arrayOfInt18, 0, i50);
            while (i50 < -1 + arrayOfInt18.length)
            {
              arrayOfInt18[i50] = paramjy.kB();
              paramjy.ky();
              i50++;
            }
          }
          arrayOfInt18[i50] = paramjy.kB();
          this.fz = arrayOfInt18;
          break;
        case 18:
          int i45 = paramjy.cw(paramjy.kE());
          int i46 = paramjy.getPosition();
          for (int i47 = 0; paramjy.kJ() > 0; i47++)
            paramjy.kB();
          paramjy.cy(i46);
          if (this.fz == null);
          int[] arrayOfInt17;
          for (int i48 = 0; ; i48 = this.fz.length)
          {
            arrayOfInt17 = new int[i47 + i48];
            if (i48 != 0)
              System.arraycopy(this.fz, 0, arrayOfInt17, 0, i48);
            while (i48 < arrayOfInt17.length)
            {
              arrayOfInt17[i48] = paramjy.kB();
              i48++;
            }
          }
          this.fz = arrayOfInt17;
          paramjy.cx(i45);
          break;
        case 24:
          int i43 = kh.c(paramjy, 24);
          if (this.fA == null);
          int[] arrayOfInt16;
          for (int i44 = 0; ; i44 = this.fA.length)
          {
            arrayOfInt16 = new int[i43 + i44];
            if (i44 != 0)
              System.arraycopy(this.fA, 0, arrayOfInt16, 0, i44);
            while (i44 < -1 + arrayOfInt16.length)
            {
              arrayOfInt16[i44] = paramjy.kB();
              paramjy.ky();
              i44++;
            }
          }
          arrayOfInt16[i44] = paramjy.kB();
          this.fA = arrayOfInt16;
          break;
        case 26:
          int i39 = paramjy.cw(paramjy.kE());
          int i40 = paramjy.getPosition();
          for (int i41 = 0; paramjy.kJ() > 0; i41++)
            paramjy.kB();
          paramjy.cy(i40);
          if (this.fA == null);
          int[] arrayOfInt15;
          for (int i42 = 0; ; i42 = this.fA.length)
          {
            arrayOfInt15 = new int[i41 + i42];
            if (i42 != 0)
              System.arraycopy(this.fA, 0, arrayOfInt15, 0, i42);
            while (i42 < arrayOfInt15.length)
            {
              arrayOfInt15[i42] = paramjy.kB();
              i42++;
            }
          }
          this.fA = arrayOfInt15;
          paramjy.cx(i39);
          break;
        case 32:
          int i37 = kh.c(paramjy, 32);
          if (this.fB == null);
          int[] arrayOfInt14;
          for (int i38 = 0; ; i38 = this.fB.length)
          {
            arrayOfInt14 = new int[i37 + i38];
            if (i38 != 0)
              System.arraycopy(this.fB, 0, arrayOfInt14, 0, i38);
            while (i38 < -1 + arrayOfInt14.length)
            {
              arrayOfInt14[i38] = paramjy.kB();
              paramjy.ky();
              i38++;
            }
          }
          arrayOfInt14[i38] = paramjy.kB();
          this.fB = arrayOfInt14;
          break;
        case 34:
          int i33 = paramjy.cw(paramjy.kE());
          int i34 = paramjy.getPosition();
          for (int i35 = 0; paramjy.kJ() > 0; i35++)
            paramjy.kB();
          paramjy.cy(i34);
          if (this.fB == null);
          int[] arrayOfInt13;
          for (int i36 = 0; ; i36 = this.fB.length)
          {
            arrayOfInt13 = new int[i35 + i36];
            if (i36 != 0)
              System.arraycopy(this.fB, 0, arrayOfInt13, 0, i36);
            while (i36 < arrayOfInt13.length)
            {
              arrayOfInt13[i36] = paramjy.kB();
              i36++;
            }
          }
          this.fB = arrayOfInt13;
          paramjy.cx(i33);
          break;
        case 40:
          int i31 = kh.c(paramjy, 40);
          if (this.fC == null);
          int[] arrayOfInt12;
          for (int i32 = 0; ; i32 = this.fC.length)
          {
            arrayOfInt12 = new int[i31 + i32];
            if (i32 != 0)
              System.arraycopy(this.fC, 0, arrayOfInt12, 0, i32);
            while (i32 < -1 + arrayOfInt12.length)
            {
              arrayOfInt12[i32] = paramjy.kB();
              paramjy.ky();
              i32++;
            }
          }
          arrayOfInt12[i32] = paramjy.kB();
          this.fC = arrayOfInt12;
          break;
        case 42:
          int i27 = paramjy.cw(paramjy.kE());
          int i28 = paramjy.getPosition();
          for (int i29 = 0; paramjy.kJ() > 0; i29++)
            paramjy.kB();
          paramjy.cy(i28);
          if (this.fC == null);
          int[] arrayOfInt11;
          for (int i30 = 0; ; i30 = this.fC.length)
          {
            arrayOfInt11 = new int[i29 + i30];
            if (i30 != 0)
              System.arraycopy(this.fC, 0, arrayOfInt11, 0, i30);
            while (i30 < arrayOfInt11.length)
            {
              arrayOfInt11[i30] = paramjy.kB();
              i30++;
            }
          }
          this.fC = arrayOfInt11;
          paramjy.cx(i27);
          break;
        case 48:
          int i25 = kh.c(paramjy, 48);
          if (this.fD == null);
          int[] arrayOfInt10;
          for (int i26 = 0; ; i26 = this.fD.length)
          {
            arrayOfInt10 = new int[i25 + i26];
            if (i26 != 0)
              System.arraycopy(this.fD, 0, arrayOfInt10, 0, i26);
            while (i26 < -1 + arrayOfInt10.length)
            {
              arrayOfInt10[i26] = paramjy.kB();
              paramjy.ky();
              i26++;
            }
          }
          arrayOfInt10[i26] = paramjy.kB();
          this.fD = arrayOfInt10;
          break;
        case 50:
          int i21 = paramjy.cw(paramjy.kE());
          int i22 = paramjy.getPosition();
          for (int i23 = 0; paramjy.kJ() > 0; i23++)
            paramjy.kB();
          paramjy.cy(i22);
          if (this.fD == null);
          int[] arrayOfInt9;
          for (int i24 = 0; ; i24 = this.fD.length)
          {
            arrayOfInt9 = new int[i23 + i24];
            if (i24 != 0)
              System.arraycopy(this.fD, 0, arrayOfInt9, 0, i24);
            while (i24 < arrayOfInt9.length)
            {
              arrayOfInt9[i24] = paramjy.kB();
              i24++;
            }
          }
          this.fD = arrayOfInt9;
          paramjy.cx(i21);
          break;
        case 56:
          int i19 = kh.c(paramjy, 56);
          if (this.fE == null);
          int[] arrayOfInt8;
          for (int i20 = 0; ; i20 = this.fE.length)
          {
            arrayOfInt8 = new int[i19 + i20];
            if (i20 != 0)
              System.arraycopy(this.fE, 0, arrayOfInt8, 0, i20);
            while (i20 < -1 + arrayOfInt8.length)
            {
              arrayOfInt8[i20] = paramjy.kB();
              paramjy.ky();
              i20++;
            }
          }
          arrayOfInt8[i20] = paramjy.kB();
          this.fE = arrayOfInt8;
          break;
        case 58:
          int i15 = paramjy.cw(paramjy.kE());
          int i16 = paramjy.getPosition();
          for (int i17 = 0; paramjy.kJ() > 0; i17++)
            paramjy.kB();
          paramjy.cy(i16);
          if (this.fE == null);
          int[] arrayOfInt7;
          for (int i18 = 0; ; i18 = this.fE.length)
          {
            arrayOfInt7 = new int[i17 + i18];
            if (i18 != 0)
              System.arraycopy(this.fE, 0, arrayOfInt7, 0, i18);
            while (i18 < arrayOfInt7.length)
            {
              arrayOfInt7[i18] = paramjy.kB();
              i18++;
            }
          }
          this.fE = arrayOfInt7;
          paramjy.cx(i15);
          break;
        case 64:
          int i13 = kh.c(paramjy, 64);
          if (this.fF == null);
          int[] arrayOfInt6;
          for (int i14 = 0; ; i14 = this.fF.length)
          {
            arrayOfInt6 = new int[i13 + i14];
            if (i14 != 0)
              System.arraycopy(this.fF, 0, arrayOfInt6, 0, i14);
            while (i14 < -1 + arrayOfInt6.length)
            {
              arrayOfInt6[i14] = paramjy.kB();
              paramjy.ky();
              i14++;
            }
          }
          arrayOfInt6[i14] = paramjy.kB();
          this.fF = arrayOfInt6;
          break;
        case 66:
          int i9 = paramjy.cw(paramjy.kE());
          int i10 = paramjy.getPosition();
          for (int i11 = 0; paramjy.kJ() > 0; i11++)
            paramjy.kB();
          paramjy.cy(i10);
          if (this.fF == null);
          int[] arrayOfInt5;
          for (int i12 = 0; ; i12 = this.fF.length)
          {
            arrayOfInt5 = new int[i11 + i12];
            if (i12 != 0)
              System.arraycopy(this.fF, 0, arrayOfInt5, 0, i12);
            while (i12 < arrayOfInt5.length)
            {
              arrayOfInt5[i12] = paramjy.kB();
              i12++;
            }
          }
          this.fF = arrayOfInt5;
          paramjy.cx(i9);
          break;
        case 72:
          int i7 = kh.c(paramjy, 72);
          if (this.fG == null);
          int[] arrayOfInt4;
          for (int i8 = 0; ; i8 = this.fG.length)
          {
            arrayOfInt4 = new int[i7 + i8];
            if (i8 != 0)
              System.arraycopy(this.fG, 0, arrayOfInt4, 0, i8);
            while (i8 < -1 + arrayOfInt4.length)
            {
              arrayOfInt4[i8] = paramjy.kB();
              paramjy.ky();
              i8++;
            }
          }
          arrayOfInt4[i8] = paramjy.kB();
          this.fG = arrayOfInt4;
          break;
        case 74:
          int i3 = paramjy.cw(paramjy.kE());
          int i4 = paramjy.getPosition();
          for (int i5 = 0; paramjy.kJ() > 0; i5++)
            paramjy.kB();
          paramjy.cy(i4);
          if (this.fG == null);
          int[] arrayOfInt3;
          for (int i6 = 0; ; i6 = this.fG.length)
          {
            arrayOfInt3 = new int[i5 + i6];
            if (i6 != 0)
              System.arraycopy(this.fG, 0, arrayOfInt3, 0, i6);
            while (i6 < arrayOfInt3.length)
            {
              arrayOfInt3[i6] = paramjy.kB();
              i6++;
            }
          }
          this.fG = arrayOfInt3;
          paramjy.cx(i3);
          break;
        case 80:
          int i1 = kh.c(paramjy, 80);
          if (this.fH == null);
          int[] arrayOfInt2;
          for (int i2 = 0; ; i2 = this.fH.length)
          {
            arrayOfInt2 = new int[i1 + i2];
            if (i2 != 0)
              System.arraycopy(this.fH, 0, arrayOfInt2, 0, i2);
            while (i2 < -1 + arrayOfInt2.length)
            {
              arrayOfInt2[i2] = paramjy.kB();
              paramjy.ky();
              i2++;
            }
          }
          arrayOfInt2[i2] = paramjy.kB();
          this.fH = arrayOfInt2;
          break;
        case 82:
        }
        int j = paramjy.cw(paramjy.kE());
        int k = paramjy.getPosition();
        for (int m = 0; paramjy.kJ() > 0; m++)
          paramjy.kB();
        paramjy.cy(k);
        if (this.fH == null);
        int[] arrayOfInt1;
        for (int n = 0; ; n = this.fH.length)
        {
          arrayOfInt1 = new int[m + n];
          if (n != 0)
            System.arraycopy(this.fH, 0, arrayOfInt1, 0, n);
          while (n < arrayOfInt1.length)
          {
            arrayOfInt1[n] = paramjy.kB();
            n++;
          }
        }
        this.fH = arrayOfInt1;
        paramjy.cx(j);
      }
    }

    public int hashCode()
    {
      int i = 31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * (527 + kc.hashCode(this.fy)) + kc.hashCode(this.fz)) + kc.hashCode(this.fA)) + kc.hashCode(this.fB)) + kc.hashCode(this.fC)) + kc.hashCode(this.fD)) + kc.hashCode(this.fE)) + kc.hashCode(this.fF)) + kc.hashCode(this.fG)) + kc.hashCode(this.fH));
      if ((this.aae == null) || (this.aae.isEmpty()));
      for (int j = 0; ; j = this.aae.hashCode())
        return j + i;
    }

    public g m()
    {
      this.fy = kh.aaj;
      this.fz = kh.aaj;
      this.fA = kh.aaj;
      this.fB = kh.aaj;
      this.fC = kh.aaj;
      this.fD = kh.aaj;
      this.fE = kh.aaj;
      this.fF = kh.aaj;
      this.fG = kh.aaj;
      this.fH = kh.aaj;
      this.aae = null;
      this.DY = -1;
      return this;
    }
  }

  public static final class h extends ka<h>
  {
    public static final kb<d.a, h> fI = kb.a(11, h.class, 810);
    private static final h[] fJ = new h[0];
    public int[] fK;
    public int[] fL;
    public int[] fM;
    public int fN;
    public int[] fO;
    public int fP;
    public int fQ;

    public h()
    {
      n();
    }

    public void a(jz paramjz)
      throws IOException
    {
      if ((this.fK != null) && (this.fK.length > 0))
        for (int n = 0; n < this.fK.length; n++)
          paramjz.f(1, this.fK[n]);
      if ((this.fL != null) && (this.fL.length > 0))
        for (int m = 0; m < this.fL.length; m++)
          paramjz.f(2, this.fL[m]);
      if ((this.fM != null) && (this.fM.length > 0))
        for (int k = 0; k < this.fM.length; k++)
          paramjz.f(3, this.fM[k]);
      if (this.fN != 0)
        paramjz.f(4, this.fN);
      if (this.fO != null)
      {
        int i = this.fO.length;
        int j = 0;
        if (i > 0)
          while (j < this.fO.length)
          {
            paramjz.f(5, this.fO[j]);
            j++;
          }
      }
      if (this.fP != 0)
        paramjz.f(6, this.fP);
      if (this.fQ != 0)
        paramjz.f(7, this.fQ);
      super.a(paramjz);
    }

    public int c()
    {
      int i = 0;
      int j = super.c();
      int i5;
      if ((this.fK != null) && (this.fK.length > 0))
      {
        int i4 = 0;
        i5 = 0;
        while (i4 < this.fK.length)
        {
          i5 += jz.cC(this.fK[i4]);
          i4++;
        }
      }
      for (int k = j + i5 + 1 * this.fK.length; ; k = j)
      {
        if ((this.fL != null) && (this.fL.length > 0))
        {
          int i2 = 0;
          int i3 = 0;
          while (i2 < this.fL.length)
          {
            i3 += jz.cC(this.fL[i2]);
            i2++;
          }
          k = k + i3 + 1 * this.fL.length;
        }
        if ((this.fM != null) && (this.fM.length > 0))
        {
          int n = 0;
          int i1 = 0;
          while (n < this.fM.length)
          {
            i1 += jz.cC(this.fM[n]);
            n++;
          }
          k = k + i1 + 1 * this.fM.length;
        }
        if (this.fN != 0)
          k += jz.g(4, this.fN);
        if ((this.fO != null) && (this.fO.length > 0))
        {
          int m = 0;
          while (i < this.fO.length)
          {
            m += jz.cC(this.fO[i]);
            i++;
          }
          k = k + m + 1 * this.fO.length;
        }
        if (this.fP != 0)
          k += jz.g(6, this.fP);
        if (this.fQ != 0)
          k += jz.g(7, this.fQ);
        this.DY = k;
        return k;
      }
    }

    public boolean equals(Object paramObject)
    {
      int i;
      if (paramObject == this)
        i = 1;
      h localh;
      while (true)
      {
        return i;
        boolean bool1 = paramObject instanceof h;
        i = 0;
        if (!bool1)
          continue;
        localh = (h)paramObject;
        boolean bool2 = kc.equals(this.fK, localh.fK);
        i = 0;
        if (!bool2)
          continue;
        boolean bool3 = kc.equals(this.fL, localh.fL);
        i = 0;
        if (!bool3)
          continue;
        boolean bool4 = kc.equals(this.fM, localh.fM);
        i = 0;
        if (!bool4)
          continue;
        int j = this.fN;
        int k = localh.fN;
        i = 0;
        if (j != k)
          continue;
        boolean bool5 = kc.equals(this.fO, localh.fO);
        i = 0;
        if (!bool5)
          continue;
        int m = this.fP;
        int n = localh.fP;
        i = 0;
        if (m != n)
          continue;
        int i1 = this.fQ;
        int i2 = localh.fQ;
        i = 0;
        if (i1 != i2)
          continue;
        if ((this.aae != null) && (!this.aae.isEmpty()))
          break;
        if (localh.aae != null)
        {
          boolean bool6 = localh.aae.isEmpty();
          i = 0;
          if (!bool6)
            continue;
        }
        return true;
      }
      return this.aae.equals(localh.aae);
    }

    public int hashCode()
    {
      int i = 31 * (31 * (31 * (31 * (31 * (31 * (31 * (527 + kc.hashCode(this.fK)) + kc.hashCode(this.fL)) + kc.hashCode(this.fM)) + this.fN) + kc.hashCode(this.fO)) + this.fP) + this.fQ);
      if ((this.aae == null) || (this.aae.isEmpty()));
      for (int j = 0; ; j = this.aae.hashCode())
        return j + i;
    }

    public h i(jy paramjy)
      throws IOException
    {
      while (true)
      {
        int i = paramjy.ky();
        switch (i)
        {
        default:
          if (a(paramjy, i))
            continue;
        case 0:
          return this;
        case 8:
          int i19 = kh.c(paramjy, 8);
          if (this.fK == null);
          int[] arrayOfInt8;
          for (int i20 = 0; ; i20 = this.fK.length)
          {
            arrayOfInt8 = new int[i19 + i20];
            if (i20 != 0)
              System.arraycopy(this.fK, 0, arrayOfInt8, 0, i20);
            while (i20 < -1 + arrayOfInt8.length)
            {
              arrayOfInt8[i20] = paramjy.kB();
              paramjy.ky();
              i20++;
            }
          }
          arrayOfInt8[i20] = paramjy.kB();
          this.fK = arrayOfInt8;
          break;
        case 10:
          int i15 = paramjy.cw(paramjy.kE());
          int i16 = paramjy.getPosition();
          for (int i17 = 0; paramjy.kJ() > 0; i17++)
            paramjy.kB();
          paramjy.cy(i16);
          if (this.fK == null);
          int[] arrayOfInt7;
          for (int i18 = 0; ; i18 = this.fK.length)
          {
            arrayOfInt7 = new int[i17 + i18];
            if (i18 != 0)
              System.arraycopy(this.fK, 0, arrayOfInt7, 0, i18);
            while (i18 < arrayOfInt7.length)
            {
              arrayOfInt7[i18] = paramjy.kB();
              i18++;
            }
          }
          this.fK = arrayOfInt7;
          paramjy.cx(i15);
          break;
        case 16:
          int i13 = kh.c(paramjy, 16);
          if (this.fL == null);
          int[] arrayOfInt6;
          for (int i14 = 0; ; i14 = this.fL.length)
          {
            arrayOfInt6 = new int[i13 + i14];
            if (i14 != 0)
              System.arraycopy(this.fL, 0, arrayOfInt6, 0, i14);
            while (i14 < -1 + arrayOfInt6.length)
            {
              arrayOfInt6[i14] = paramjy.kB();
              paramjy.ky();
              i14++;
            }
          }
          arrayOfInt6[i14] = paramjy.kB();
          this.fL = arrayOfInt6;
          break;
        case 18:
          int i9 = paramjy.cw(paramjy.kE());
          int i10 = paramjy.getPosition();
          for (int i11 = 0; paramjy.kJ() > 0; i11++)
            paramjy.kB();
          paramjy.cy(i10);
          if (this.fL == null);
          int[] arrayOfInt5;
          for (int i12 = 0; ; i12 = this.fL.length)
          {
            arrayOfInt5 = new int[i11 + i12];
            if (i12 != 0)
              System.arraycopy(this.fL, 0, arrayOfInt5, 0, i12);
            while (i12 < arrayOfInt5.length)
            {
              arrayOfInt5[i12] = paramjy.kB();
              i12++;
            }
          }
          this.fL = arrayOfInt5;
          paramjy.cx(i9);
          break;
        case 24:
          int i7 = kh.c(paramjy, 24);
          if (this.fM == null);
          int[] arrayOfInt4;
          for (int i8 = 0; ; i8 = this.fM.length)
          {
            arrayOfInt4 = new int[i7 + i8];
            if (i8 != 0)
              System.arraycopy(this.fM, 0, arrayOfInt4, 0, i8);
            while (i8 < -1 + arrayOfInt4.length)
            {
              arrayOfInt4[i8] = paramjy.kB();
              paramjy.ky();
              i8++;
            }
          }
          arrayOfInt4[i8] = paramjy.kB();
          this.fM = arrayOfInt4;
          break;
        case 26:
          int i3 = paramjy.cw(paramjy.kE());
          int i4 = paramjy.getPosition();
          for (int i5 = 0; paramjy.kJ() > 0; i5++)
            paramjy.kB();
          paramjy.cy(i4);
          if (this.fM == null);
          int[] arrayOfInt3;
          for (int i6 = 0; ; i6 = this.fM.length)
          {
            arrayOfInt3 = new int[i5 + i6];
            if (i6 != 0)
              System.arraycopy(this.fM, 0, arrayOfInt3, 0, i6);
            while (i6 < arrayOfInt3.length)
            {
              arrayOfInt3[i6] = paramjy.kB();
              i6++;
            }
          }
          this.fM = arrayOfInt3;
          paramjy.cx(i3);
          break;
        case 32:
          this.fN = paramjy.kB();
          break;
        case 40:
          int i1 = kh.c(paramjy, 40);
          if (this.fO == null);
          int[] arrayOfInt2;
          for (int i2 = 0; ; i2 = this.fO.length)
          {
            arrayOfInt2 = new int[i1 + i2];
            if (i2 != 0)
              System.arraycopy(this.fO, 0, arrayOfInt2, 0, i2);
            while (i2 < -1 + arrayOfInt2.length)
            {
              arrayOfInt2[i2] = paramjy.kB();
              paramjy.ky();
              i2++;
            }
          }
          arrayOfInt2[i2] = paramjy.kB();
          this.fO = arrayOfInt2;
          break;
        case 42:
          int j = paramjy.cw(paramjy.kE());
          int k = paramjy.getPosition();
          for (int m = 0; paramjy.kJ() > 0; m++)
            paramjy.kB();
          paramjy.cy(k);
          if (this.fO == null);
          int[] arrayOfInt1;
          for (int n = 0; ; n = this.fO.length)
          {
            arrayOfInt1 = new int[m + n];
            if (n != 0)
              System.arraycopy(this.fO, 0, arrayOfInt1, 0, n);
            while (n < arrayOfInt1.length)
            {
              arrayOfInt1[n] = paramjy.kB();
              n++;
            }
          }
          this.fO = arrayOfInt1;
          paramjy.cx(j);
          break;
        case 48:
          this.fP = paramjy.kB();
          break;
        case 56:
        }
        this.fQ = paramjy.kB();
      }
    }

    public h n()
    {
      this.fK = kh.aaj;
      this.fL = kh.aaj;
      this.fM = kh.aaj;
      this.fN = 0;
      this.fO = kh.aaj;
      this.fP = 0;
      this.fQ = 0;
      this.aae = null;
      this.DY = -1;
      return this;
    }
  }

  public static final class i extends ka<i>
  {
    private static volatile i[] fR;
    public d.a fS;
    public c.d fT;
    public String name;

    public i()
    {
      p();
    }

    public static i[] o()
    {
      if (fR == null);
      synchronized (kc.aah)
      {
        if (fR == null)
          fR = new i[0];
        return fR;
      }
    }

    public void a(jz paramjz)
      throws IOException
    {
      if (!this.name.equals(""))
        paramjz.b(1, this.name);
      if (this.fS != null)
        paramjz.a(2, this.fS);
      if (this.fT != null)
        paramjz.a(3, this.fT);
      super.a(paramjz);
    }

    public int c()
    {
      int i = super.c();
      if (!this.name.equals(""))
        i += jz.g(1, this.name);
      if (this.fS != null)
        i += jz.b(2, this.fS);
      if (this.fT != null)
        i += jz.b(3, this.fT);
      this.DY = i;
      return i;
    }

    public boolean equals(Object paramObject)
    {
      int i;
      if (paramObject == this)
        i = 1;
      i locali;
      label47: label68: c.d locald;
      do
      {
        d.a locala;
        do
        {
          String str;
          do
          {
            boolean bool1;
            do
            {
              return i;
              bool1 = paramObject instanceof i;
              i = 0;
            }
            while (!bool1);
            locali = (i)paramObject;
            if (this.name != null)
              break;
            str = locali.name;
            i = 0;
          }
          while (str != null);
          if (this.fS != null)
            break label154;
          locala = locali.fS;
          i = 0;
        }
        while (locala != null);
        if (this.fT != null)
          break label171;
        locald = locali.fT;
        i = 0;
      }
      while (locald != null);
      label154: label171: 
      do
      {
        if ((this.aae != null) && (!this.aae.isEmpty()))
          break label188;
        if (locali.aae != null)
        {
          boolean bool2 = locali.aae.isEmpty();
          i = 0;
          if (!bool2)
            break;
        }
        return true;
        if (this.name.equals(locali.name))
          break label47;
        return false;
        if (this.fS.equals(locali.fS))
          break label68;
        return false;
      }
      while (this.fT.equals(locali.fT));
      return false;
      label188: return this.aae.equals(locali.aae);
    }

    public int hashCode()
    {
      int i;
      int k;
      label27: int n;
      label45: int i1;
      int i2;
      if (this.name == null)
      {
        i = 0;
        int j = 31 * (i + 527);
        if (this.fS != null)
          break label105;
        k = 0;
        int m = 31 * (k + j);
        if (this.fT != null)
          break label116;
        n = 0;
        i1 = 31 * (n + m);
        List localList = this.aae;
        i2 = 0;
        if (localList != null)
        {
          boolean bool = this.aae.isEmpty();
          i2 = 0;
          if (!bool)
            break label128;
        }
      }
      while (true)
      {
        return i1 + i2;
        i = this.name.hashCode();
        break;
        label105: k = this.fS.hashCode();
        break label27;
        label116: n = this.fT.hashCode();
        break label45;
        label128: i2 = this.aae.hashCode();
      }
    }

    public i j(jy paramjy)
      throws IOException
    {
      while (true)
      {
        int i = paramjy.ky();
        switch (i)
        {
        default:
          if (a(paramjy, i))
            continue;
        case 0:
          return this;
        case 10:
          this.name = paramjy.readString();
          break;
        case 18:
          if (this.fS == null)
            this.fS = new d.a();
          paramjy.a(this.fS);
          break;
        case 26:
        }
        if (this.fT == null)
          this.fT = new c.d();
        paramjy.a(this.fT);
      }
    }

    public i p()
    {
      this.name = "";
      this.fS = null;
      this.fT = null;
      this.aae = null;
      this.DY = -1;
      return this;
    }
  }

  public static final class j extends ka<j>
  {
    public c.i[] fU;
    public c.f fV;
    public String fW;

    public j()
    {
      q();
    }

    public static j b(byte[] paramArrayOfByte)
      throws kd
    {
      return (j)ke.a(new j(), paramArrayOfByte);
    }

    public void a(jz paramjz)
      throws IOException
    {
      if ((this.fU != null) && (this.fU.length > 0))
        for (int i = 0; i < this.fU.length; i++)
        {
          c.i locali = this.fU[i];
          if (locali == null)
            continue;
          paramjz.a(1, locali);
        }
      if (this.fV != null)
        paramjz.a(2, this.fV);
      if (!this.fW.equals(""))
        paramjz.b(3, this.fW);
      super.a(paramjz);
    }

    public int c()
    {
      int i = super.c();
      if ((this.fU != null) && (this.fU.length > 0))
        for (int j = 0; j < this.fU.length; j++)
        {
          c.i locali = this.fU[j];
          if (locali == null)
            continue;
          i += jz.b(1, locali);
        }
      if (this.fV != null)
        i += jz.b(2, this.fV);
      if (!this.fW.equals(""))
        i += jz.g(3, this.fW);
      this.DY = i;
      return i;
    }

    public boolean equals(Object paramObject)
    {
      int i;
      if (paramObject == this)
        i = 1;
      j localj;
      label68: String str;
      do
      {
        c.f localf;
        do
        {
          boolean bool2;
          do
          {
            boolean bool1;
            do
            {
              return i;
              bool1 = paramObject instanceof j;
              i = 0;
            }
            while (!bool1);
            localj = (j)paramObject;
            bool2 = kc.equals(this.fU, localj.fU);
            i = 0;
          }
          while (!bool2);
          if (this.fV != null)
            break;
          localf = localj.fV;
          i = 0;
        }
        while (localf != null);
        if (this.fW != null)
          break label154;
        str = localj.fW;
        i = 0;
      }
      while (str != null);
      label154: 
      do
      {
        if ((this.aae != null) && (!this.aae.isEmpty()))
          break label171;
        if (localj.aae != null)
        {
          boolean bool3 = localj.aae.isEmpty();
          i = 0;
          if (!bool3)
            break;
        }
        return true;
        if (this.fV.equals(localj.fV))
          break label68;
        return false;
      }
      while (this.fW.equals(localj.fW));
      return false;
      label171: return this.aae.equals(localj.aae);
    }

    public int hashCode()
    {
      int i = 31 * (527 + kc.hashCode(this.fU));
      int j;
      int m;
      label41: int n;
      int i1;
      if (this.fV == null)
      {
        j = 0;
        int k = 31 * (j + i);
        if (this.fW != null)
          break label100;
        m = 0;
        n = 31 * (m + k);
        List localList = this.aae;
        i1 = 0;
        if (localList != null)
        {
          boolean bool = this.aae.isEmpty();
          i1 = 0;
          if (!bool)
            break label112;
        }
      }
      while (true)
      {
        return n + i1;
        j = this.fV.hashCode();
        break;
        label100: m = this.fW.hashCode();
        break label41;
        label112: i1 = this.aae.hashCode();
      }
    }

    public j k(jy paramjy)
      throws IOException
    {
      while (true)
      {
        int i = paramjy.ky();
        switch (i)
        {
        default:
          if (a(paramjy, i))
            continue;
        case 0:
          return this;
        case 10:
          int j = kh.c(paramjy, 10);
          if (this.fU == null);
          c.i[] arrayOfi;
          for (int k = 0; ; k = this.fU.length)
          {
            arrayOfi = new c.i[j + k];
            if (k != 0)
              System.arraycopy(this.fU, 0, arrayOfi, 0, k);
            while (k < -1 + arrayOfi.length)
            {
              arrayOfi[k] = new c.i();
              paramjy.a(arrayOfi[k]);
              paramjy.ky();
              k++;
            }
          }
          arrayOfi[k] = new c.i();
          paramjy.a(arrayOfi[k]);
          this.fU = arrayOfi;
          break;
        case 18:
          if (this.fV == null)
            this.fV = new c.f();
          paramjy.a(this.fV);
          break;
        case 26:
        }
        this.fW = paramjy.readString();
      }
    }

    public j q()
    {
      this.fU = c.i.o();
      this.fV = null;
      this.fW = "";
      this.aae = null;
      this.DY = -1;
      return this;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.c
 * JD-Core Version:    0.6.0
 */