package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api.b;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.People.LoadPeopleResult;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.Plus.a;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.PersonBuffer;
import java.util.Collection;

public final class il
  implements People
{
  private final Api.b<e> Rw;

  public il(Api.b<e> paramb)
  {
    this.Rw = paramb;
  }

  public Person getCurrentPerson(GoogleApiClient paramGoogleApiClient)
  {
    return Plus.a(paramGoogleApiClient, this.Rw).getCurrentPerson();
  }

  public PendingResult<People.LoadPeopleResult> load(GoogleApiClient paramGoogleApiClient, Collection<String> paramCollection)
  {
    return paramGoogleApiClient.a(new a(this.Rw, paramCollection)
    {
      protected void a(e parame)
      {
        parame.a(this, this.RI);
      }
    });
  }

  public PendingResult<People.LoadPeopleResult> load(GoogleApiClient paramGoogleApiClient, String[] paramArrayOfString)
  {
    return paramGoogleApiClient.a(new a(this.Rw, paramArrayOfString)
    {
      protected void a(e parame)
      {
        parame.c(this, this.RJ);
      }
    });
  }

  public PendingResult<People.LoadPeopleResult> loadConnected(GoogleApiClient paramGoogleApiClient)
  {
    return paramGoogleApiClient.a(new a(this.Rw)
    {
      protected void a(e parame)
      {
        parame.j(this);
      }
    });
  }

  public PendingResult<People.LoadPeopleResult> loadVisible(GoogleApiClient paramGoogleApiClient, int paramInt, String paramString)
  {
    return paramGoogleApiClient.a(new a(this.Rw, paramInt, paramString)
    {
      protected void a(e parame)
      {
        parame.a(this, this.RG, this.Rz);
      }
    });
  }

  public PendingResult<People.LoadPeopleResult> loadVisible(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    return paramGoogleApiClient.a(new a(this.Rw, paramString)
    {
      protected void a(e parame)
      {
        parame.i(this, this.Rz);
      }
    });
  }

  private static abstract class a extends Plus.a<People.LoadPeopleResult>
  {
    a(Api.b<e> paramb)
    {
      super();
    }

    public People.LoadPeopleResult N(Status paramStatus)
    {
      return new People.LoadPeopleResult(paramStatus)
      {
        public String getNextPageToken()
        {
          return null;
        }

        public PersonBuffer getPersonBuffer()
        {
          return null;
        }

        public Status getStatus()
        {
          return this.vb;
        }

        public void release()
        {
        }
      };
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.il
 * JD-Core Version:    0.6.0
 */