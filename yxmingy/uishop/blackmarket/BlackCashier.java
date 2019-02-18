package yxmingy.uishop.blackmarket;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import me.onebone.economyapi.EconomyAPI;

import java.util.*;
import yxmingy.yupi.*;

public class BlackCashier extends HandlerBase{
  Map<String,Object> idata;
  public BlackCashier(Map<String,Object> data)
  {
    idata = data;
  }
  public void handle(String data,Player player)
  {
  	if(data.contentEquals("null")) return;
    try{
      int count = 1,
          id = Integer.parseInt(String.valueOf(idata.get("id"))),
          meta = Integer.parseInt(String.valueOf(idata.get("特殊值")));
      double price = (Double.parseDouble(String.valueOf(idata.get("价格"))))*0.8,
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
