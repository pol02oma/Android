package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class in
  implements Parcelable.Creator<im>
{
  static void a(im paramim, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    Set localSet = paramim.hB();
    if (localSet.contains(Integer.valueOf(1)))
      b.c(paramParcel, 1, paramim.getVersionCode());
    if (localSet.contains(Integer.valueOf(2)))
      b.a(paramParcel, 2, paramim.hC(), paramInt, true);
    if (localSet.contains(Integer.valueOf(3)))
      b.a(paramParcel, 3, paramim.getAdditionalName(), true);
    if (localSet.contains(Integer.valueOf(4)))
      b.a(paramParcel, 4, paramim.hD(), paramInt, true);
    if (localSet.contains(Integer.valueOf(5)))
      b.a(paramParcel, 5, paramim.getAddressCountry(), true);
    if (localSet.contains(Integer.valueOf(6)))
      b.a(paramParcel, 6, paramim.getAddressLocality(), true);
    if (localSet.contains(Integer.valueOf(7)))
      b.a(paramParcel, 7, paramim.getAddressRegion(), true);
    if (localSet.contains(Integer.valueOf(8)))
      b.b(paramParcel, 8, paramim.hE(), true);
    if (localSet.contains(Integer.valueOf(9)))
      b.c(paramParcel, 9, paramim.getAttendeeCount());
    if (localSet.contains(Integer.valueOf(10)))
      b.b(paramParcel, 10, paramim.hF(), true);
    if (localSet.contains(Integer.valueOf(11)))
      b.a(paramParcel, 11, paramim.hG(), paramInt, true);
    if (localSet.contains(Integer.valueOf(12)))
      b.b(paramParcel, 12, paramim.hH(), true);
    if (localSet.contains(Integer.valueOf(13)))
      b.a(paramParcel, 13, paramim.getBestRating(), true);
    if (localSet.contains(Integer.valueOf(14)))
      b.a(paramParcel, 14, paramim.getBirthDate(), true);
    if (localSet.contains(Integer.valueOf(15)))
      b.a(paramParcel, 15, paramim.hI(), paramInt, true);
    if (localSet.contains(Integer.valueOf(17)))
      b.a(paramParcel, 17, paramim.getContentSize(), true);
    if (localSet.contains(Integer.valueOf(16)))
      b.a(paramParcel, 16, paramim.getCaption(), true);
    if (localSet.contains(Integer.valueOf(19)))
      b.b(paramParcel, 19, paramim.hJ(), true);
    if (localSet.contains(Integer.valueOf(18)))
      b.a(paramParcel, 18, paramim.getContentUrl(), true);
    if (localSet.contains(Integer.valueOf(21)))
      b.a(paramParcel, 21, paramim.getDateModified(), true);
    if (localSet.contains(Integer.valueOf(20)))
      b.a(paramParcel, 20, paramim.getDateCreated(), true);
    if (localSet.contains(Integer.valueOf(23)))
      b.a(paramParcel, 23, paramim.getDescription(), true);
    if (localSet.contains(Integer.valueOf(22)))
      b.a(paramParcel, 22, paramim.getDatePublished(), true);
    if (localSet.contains(Integer.valueOf(25)))
      b.a(paramParcel, 25, paramim.getEmbedUrl(), true);
    if (localSet.contains(Integer.valueOf(24)))
      b.a(paramParcel, 24, paramim.getDuration(), true);
    if (localSet.contains(Integer.valueOf(27)))
      b.a(paramParcel, 27, paramim.getFamilyName(), true);
    if (localSet.contains(Integer.valueOf(26)))
      b.a(paramParcel, 26, paramim.getEndDate(), true);
    if (localSet.contains(Integer.valueOf(29)))
      b.a(paramParcel, 29, paramim.hK(), paramInt, true);
    if (localSet.contains(Integer.valueOf(28)))
      b.a(paramParcel, 28, paramim.getGender(), true);
    if (localSet.contains(Integer.valueOf(31)))
      b.a(paramParcel, 31, paramim.getHeight(), true);
    if (localSet.contains(Integer.valueOf(30)))
      b.a(paramParcel, 30, paramim.getGivenName(), true);
    if (localSet.contains(Integer.valueOf(34)))
      b.a(paramParcel, 34, paramim.hL(), paramInt, true);
    if (localSet.contains(Integer.valueOf(32)))
      b.a(paramParcel, 32, paramim.getId(), true);
    if (localSet.contains(Integer.valueOf(33)))
      b.a(paramParcel, 33, paramim.getImage(), true);
    if (localSet.contains(Integer.valueOf(38)))
      b.a(paramParcel, 38, paramim.getLongitude());
    if (localSet.contains(Integer.valueOf(39)))
      b.a(paramParcel, 39, paramim.getName(), true);
    if (localSet.contains(Integer.valueOf(36)))
      b.a(paramParcel, 36, paramim.getLatitude());
    if (localSet.contains(Integer.valueOf(37)))
      b.a(paramParcel, 37, paramim.hM(), paramInt, true);
    if (localSet.contains(Integer.valueOf(42)))
      b.a(paramParcel, 42, paramim.getPlayerType(), true);
    if (localSet.contains(Integer.valueOf(43)))
      b.a(paramParcel, 43, paramim.getPostOfficeBoxNumber(), true);
    if (localSet.contains(Integer.valueOf(40)))
      b.a(paramParcel, 40, paramim.hN(), paramInt, true);
    if (localSet.contains(Integer.valueOf(41)))
      b.b(paramParcel, 41, paramim.hO(), true);
    if (localSet.contains(Integer.valueOf(46)))
      b.a(paramParcel, 46, paramim.hP(), paramInt, true);
    if (localSet.contains(Integer.valueOf(47)))
      b.a(paramParcel, 47, paramim.getStartDate(), true);
    if (localSet.contains(Integer.valueOf(44)))
      b.a(paramParcel, 44, paramim.getPostalCode(), true);
    if (localSet.contains(Integer.valueOf(45)))
      b.a(paramParcel, 45, paramim.getRatingValue(), true);
    if (localSet.contains(Integer.valueOf(51)))
      b.a(paramParcel, 51, paramim.getThumbnailUrl(), true);
    if (localSet.contains(Integer.valueOf(50)))
      b.a(paramParcel, 50, paramim.hQ(), paramInt, true);
    if (localSet.contains(Integer.valueOf(49)))
      b.a(paramParcel, 49, paramim.getText(), true);
    if (localSet.contains(Integer.valueOf(48)))
      b.a(paramParcel, 48, paramim.getStreetAddress(), true);
    if (localSet.contains(Integer.valueOf(55)))
      b.a(paramParcel, 55, paramim.getWidth(), true);
    if (localSet.contains(Integer.valueOf(54)))
      b.a(paramParcel, 54, paramim.getUrl(), true);
    if (localSet.contains(Integer.valueOf(53)))
      b.a(paramParcel, 53, paramim.getType(), true);
    if (localSet.contains(Integer.valueOf(52)))
      b.a(paramParcel, 52, paramim.getTickerSymbol(), true);
    if (localSet.contains(Integer.valueOf(56)))
      b.a(paramParcel, 56, paramim.getWorstRating(), true);
    b.D(paramParcel, i);
  }

  public im aG(Parcel paramParcel)
  {
    int i = a.o(paramParcel);
    HashSet localHashSet = new HashSet();
    int j = 0;
    Object localObject1 = null;
    ArrayList localArrayList1 = null;
    Object localObject2 = null;
    String str1 = null;
    String str2 = null;
    String str3 = null;
    ArrayList localArrayList2 = null;
    int k = 0;
    ArrayList localArrayList3 = null;
    Object localObject3 = null;
    ArrayList localArrayList4 = null;
    String str4 = null;
    String str5 = null;
    Object localObject4 = null;
    String str6 = null;
    String str7 = null;
    String str8 = null;
    ArrayList localArrayList5 = null;
    String str9 = null;
    String str10 = null;
    String str11 = null;
    String str12 = null;
    String str13 = null;
    String str14 = null;
    String str15 = null;
    String str16 = null;
    String str17 = null;
    Object localObject5 = null;
    String str18 = null;
    String str19 = null;
    String str20 = null;
    String str21 = null;
    Object localObject6 = null;
    double d1 = 0.0D;
    Object localObject7 = null;
    double d2 = 0.0D;
    String str22 = null;
    Object localObject8 = null;
    ArrayList localArrayList6 = null;
    String str23 = null;
    String str24 = null;
    String str25 = null;
    String str26 = null;
    Object localObject9 = null;
    String str27 = null;
    String str28 = null;
    String str29 = null;
    Object localObject10 = null;
    String str30 = null;
    String str31 = null;
    String str32 = null;
    String str33 = null;
    String str34 = null;
    String str35 = null;
    while (paramParcel.dataPosition() < i)
    {
      int m = a.n(paramParcel);
      switch (a.S(m))
      {
      case 35:
      default:
        a.b(paramParcel, m);
        break;
      case 1:
        j = a.g(paramParcel, m);
        localHashSet.add(Integer.valueOf(1));
        break;
      case 2:
        im localim10 = (im)a.a(paramParcel, m, im.CREATOR);
        localHashSet.add(Integer.valueOf(2));
        localObject1 = localim10;
        break;
      case 3:
        localArrayList1 = a.y(paramParcel, m);
        localHashSet.add(Integer.valueOf(3));
        break;
      case 4:
        im localim9 = (im)a.a(paramParcel, m, im.CREATOR);
        localHashSet.add(Integer.valueOf(4));
        localObject2 = localim9;
        break;
      case 5:
        str1 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(5));
        break;
      case 6:
        str2 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(6));
        break;
      case 7:
        str3 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(7));
        break;
      case 8:
        localArrayList2 = a.c(paramParcel, m, im.CREATOR);
        localHashSet.add(Integer.valueOf(8));
        break;
      case 9:
        k = a.g(paramParcel, m);
        localHashSet.add(Integer.valueOf(9));
        break;
      case 10:
        localArrayList3 = a.c(paramParcel, m, im.CREATOR);
        localHashSet.add(Integer.valueOf(10));
        break;
      case 11:
        im localim8 = (im)a.a(paramParcel, m, im.CREATOR);
        localHashSet.add(Integer.valueOf(11));
        localObject3 = localim8;
        break;
      case 12:
        localArrayList4 = a.c(paramParcel, m, im.CREATOR);
        localHashSet.add(Integer.valueOf(12));
        break;
      case 13:
        str4 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(13));
        break;
      case 14:
        str5 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(14));
        break;
      case 15:
        im localim7 = (im)a.a(paramParcel, m, im.CREATOR);
        localHashSet.add(Integer.valueOf(15));
        localObject4 = localim7;
        break;
      case 17:
        str7 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(17));
        break;
      case 16:
        str6 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(16));
        break;
      case 19:
        localArrayList5 = a.c(paramParcel, m, im.CREATOR);
        localHashSet.add(Integer.valueOf(19));
        break;
      case 18:
        str8 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(18));
        break;
      case 21:
        str10 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(21));
        break;
      case 20:
        str9 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(20));
        break;
      case 23:
        str12 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(23));
        break;
      case 22:
        str11 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(22));
        break;
      case 25:
        str14 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(25));
        break;
      case 24:
        str13 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(24));
        break;
      case 27:
        str16 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(27));
        break;
      case 26:
        str15 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(26));
        break;
      case 29:
        im localim6 = (im)a.a(paramParcel, m, im.CREATOR);
        localHashSet.add(Integer.valueOf(29));
        localObject5 = localim6;
        break;
      case 28:
        str17 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(28));
        break;
      case 31:
        str19 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(31));
        break;
      case 30:
        str18 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(30));
        break;
      case 34:
        im localim5 = (im)a.a(paramParcel, m, im.CREATOR);
        localHashSet.add(Integer.valueOf(34));
        localObject6 = localim5;
        break;
      case 32:
        str20 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(32));
        break;
      case 33:
        str21 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(33));
        break;
      case 38:
        d2 = a.k(paramParcel, m);
        localHashSet.add(Integer.valueOf(38));
        break;
      case 39:
        str22 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(39));
        break;
      case 36:
        d1 = a.k(paramParcel, m);
        localHashSet.add(Integer.valueOf(36));
        break;
      case 37:
        im localim4 = (im)a.a(paramParcel, m, im.CREATOR);
        localHashSet.add(Integer.valueOf(37));
        localObject7 = localim4;
        break;
      case 42:
        str23 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(42));
        break;
      case 43:
        str24 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(43));
        break;
      case 40:
        im localim3 = (im)a.a(paramParcel, m, im.CREATOR);
        localHashSet.add(Integer.valueOf(40));
        localObject8 = localim3;
        break;
      case 41:
        localArrayList6 = a.c(paramParcel, m, im.CREATOR);
        localHashSet.add(Integer.valueOf(41));
        break;
      case 46:
        im localim2 = (im)a.a(paramParcel, m, im.CREATOR);
        localHashSet.add(Integer.valueOf(46));
        localObject9 = localim2;
        break;
      case 47:
        str27 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(47));
        break;
      case 44:
        str25 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(44));
        break;
      case 45:
        str26 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(45));
        break;
      case 51:
        str30 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(51));
        break;
      case 50:
        im localim1 = (im)a.a(paramParcel, m, im.CREATOR);
        localHashSet.add(Integer.valueOf(50));
        localObject10 = localim1;
        break;
      case 49:
        str29 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(49));
        break;
      case 48:
        str28 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(48));
        break;
      case 55:
        str34 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(55));
        break;
      case 54:
        str33 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(54));
        break;
      case 53:
        str32 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(53));
        break;
      case 52:
        str31 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(52));
        break;
      case 56:
        str35 = a.m(paramParcel, m);
        localHashSet.add(Integer.valueOf(56));
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new im(localHashSet, j, localObject1, localArrayList1, localObject2, str1, str2, str3, localArrayList2, k, localArrayList3, localObject3, localArrayList4, str4, str5, localObject4, str6, str7, str8, localArrayList5, str9, str10, str11, str12, str13, str14, str15, str16, str17, localObject5, str18, str19, str20, str21, localObject6, d1, localObject7, d2, str22, localObject8, localArrayList6, str23, str24, str25, str26, localObject9, str27, str28, str29, localObject10, str30, str31, str32, str33, str34, str35);
  }

  public im[] bD(int paramInt)
  {
    return new im[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.in
 * JD-Core Version:    0.6.0
 */