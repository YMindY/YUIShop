package yxmingy.uishop.sellshop;

import cn.nukkit.Player;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.*;
import java.util.regex.Pattern;
import yxmingy.yupi.*;

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
    if(!Pattern.matches("^[\\+]?[\\d]+$", String.valueOf(pdata[1]))){
      player.sendMessage("请输入有效正整数!");
      return;
    }
    try{
      int count = Integer.parseInt(String.valueOf(pdata[1]));
      int price = Integer.parseInt(String.valueOf(idata.get("价格")));
    }catch(NumberFormatException e)
    {
      player.sendMessage("未知错误!通知服主并让服主通知开发者!");
      e.printStackTrace();
      return;
    }
    
  }
}
