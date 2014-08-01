package com.google.android.gms.internal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Games.a;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.GameRequestBuffer;
import com.google.android.gms.games.request.OnRequestReceivedListener;
import com.google.android.gms.games.request.Requests;
import com.google.android.gms.games.request.Requests.LoadRequestsResult;
import com.google.android.gms.games.request.Requests.UpdateRequestsResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class go
  implements Requests
{
  public PendingResult<Requests.UpdateRequestsResult> acceptRequest(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramString);
    return acceptRequests(paramGoogleApiClient, localArrayList);
  }

  public PendingResult<Requests.UpdateRequestsResult> acceptRequests(GoogleApiClient paramGoogleApiClient, List<String> paramList)
  {
    if (paramList == null);
    for (String[] arrayOfString = null; ; arrayOfString = (String[])paramList.toArray(new String[paramList.size()]))
      return paramGoogleApiClient.b(new b(arrayOfString)
      {
        protected void a(fx paramfx)
        {
          paramfx.a(this, this.Ij);
        }
      });
  }

  public PendingResult<Requests.UpdateRequestsResult> dismissRequest(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramString);
    return dismissRequests(paramGoogleApiClient, localArrayList);
  }

  public PendingResult<Requests.UpdateRequestsResult> dismissRequests(GoogleApiClient paramGoogleApiClient, List<String> paramList)
  {
    if (paramList == null);
    for (String[] arrayOfString = null; ; arrayOfString = (String[])paramList.toArray(new String[paramList.size()]))
      return paramGoogleApiClient.b(new b(arrayOfString)
      {
        protected void a(fx paramfx)
        {
          paramfx.b(this, this.Ij);
        }
      });
  }

  public ArrayList<GameRequest> getGameRequestsFromBundle(Bundle paramBundle)
  {
    if ((paramBundle == null) || (!paramBundle.containsKey("requests")))
      return new ArrayList();
    ArrayList localArrayList1 = (ArrayList)paramBundle.get("requests");
    ArrayList localArrayList2 = new ArrayList();
    int i = localArrayList1.size();
    for (int j = 0; j < i; j++)
      localArrayList2.add((GameRequest)localArrayList1.get(j));
    return localArrayList2;
  }

  public ArrayList<GameRequest> getGameRequestsFromInboxResponse(Intent paramIntent)
  {
    if (paramIntent == null)
      return new ArrayList();
    return getGameRequestsFromBundle(paramIntent.getExtras());
  }

  public Intent getInboxIntent(GoogleApiClient paramGoogleApiClient)
  {
    return Games.c(paramGoogleApiClient).fD();
  }

  public int getMaxLifetimeDays(GoogleApiClient paramGoogleApiClient)
  {
    return Games.c(paramGoogleApiClient).fF();
  }

  public int getMaxPayloadSize(GoogleApiClient paramGoogleApiClient)
  {
    return Games.c(paramGoogleApiClient).fE();
  }

  public Intent getSendIntent(GoogleApiClient paramGoogleApiClient, int paramInt1, byte[] paramArrayOfByte, int paramInt2, Bitmap paramBitmap, String paramString)
  {
    return Games.c(paramGoogleApiClient).a(paramInt1, paramArrayOfByte, paramInt2, paramBitmap, paramString);
  }

  public PendingResult<Requests.LoadRequestsResult> loadRequests(GoogleApiClient paramGoogleApiClient, int paramInt1, int paramInt2, int paramInt3)
  {
    return paramGoogleApiClient.a(new a(paramInt1, paramInt2, paramInt3)
    {
      protected void a(fx paramfx)
      {
        paramfx.a(this, this.Il, this.Im, this.HP);
      }
    });
  }

  public void registerRequestListener(GoogleApiClient paramGoogleApiClient, OnRequestReceivedListener paramOnRequestReceivedListener)
  {
    Games.c(paramGoogleApiClient).a(paramOnRequestReceivedListener);
  }

  public void unregisterRequestListener(GoogleApiClient paramGoogleApiClient)
  {
    Games.c(paramGoogleApiClient).fx();
  }

  private static abstract class a extends Games.a<Requests.LoadRequestsResult>
  {
    public Requests.LoadRequestsResult B(Status paramStatus)
    {
      return new Requests.LoadRequestsResult(paramStatus)
      {
        public GameRequestBuffer getRequests(int paramInt)
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

  private static abstract class b extends Games.a<Requests.UpdateRequestsResult>
  {
    public Requests.UpdateRequestsResult C(Status paramStatus)
    {
      return new Requests.UpdateRequestsResult(paramStatus)
      {
        public Set<String> getRequestIds()
        {
          return null;
        }

        public int getRequestOutcome(String paramString)
        {
          throw new IllegalArgumentException("Unknown request ID " + paramString);
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
 * Qualified Name:     com.google.android.gms.internal.go
 * JD-Core Version:    0.6.0
 */