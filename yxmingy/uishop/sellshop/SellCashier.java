package yxmingy.uishop.sellshop;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import me.onebone.economyapi.EconomyAPI;

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
      int count = Integer.parseInt(String.valueOf(pdata[1])),
          price = Integer.parseInt(String.valueOf(idata.get("价格"))),
          id = Integer.parseInt(String.valueOf(idata.get("id"))),
          meta = Integer.parseInt(String.valueOf(idata.get("特殊值"))),
          total = count*price;
      EconomyAPI eapi = EconomyAPI.getInstance();
      if(eapi.myMoney(player) < total) {
      	player.sendMessage("死开，穷鬼！");
      	return;
      }
      eapi.reduceMoney(player, total);
      player.getInventory().addItem(new Item(id, meta, count));
      player.sendMessage("购买成功，共花费"+String.valueOf(total)+"Ft币，祝您购物愉快！");
    }catch(NumberFormatException e)
    {
      player.sendMessage("未知错误!通知服主并让服主通知开发者!");
      e.printStackTrace();
      return;
    }
  }
}
