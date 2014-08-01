package com.ibuildapp.romanblack.TableReservationPlugin;

import com.ibuildapp.romanblack.TableReservationPlugin.entity.HouresMinutes;
import java.io.Serializable;
import java.util.Date;

class TableReservationOrderInfo
  implements Serializable
{
  public Date orderDate = new Date();
  public HouresMinutes orderTime = new HouresMinutes();
  public int personsAmount = -1;
  public String specRequest = "";
  public int status;
  public String uuid = "";
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.TableReservationPlugin.TableReservationOrderInfo
 * JD-Core Version:    0.6.0
 */