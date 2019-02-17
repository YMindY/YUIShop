package yxmingy.uishop.sellshop;

import cn.nukkit.Player;
import yxmingy.yupi.*;

import java.util.*;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SellCashier extends HandlerBase{
  Map<String,Object> idata;
  public SellCashier(Map<String,Object> data)
  {
    idata = data;
  }
  public void handle(String data,Player player)
  {
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    @SuppressWarnings("serial")
		Object[] pdata = gson.fromJson(data,new TypeToken<Object[]>() {
		}.getType());
    for(Object p : pdata) {
    	player.sendMessage(String.valueOf(p));
    }
  }
}
