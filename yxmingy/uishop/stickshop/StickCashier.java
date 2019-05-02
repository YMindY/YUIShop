package yxmingy.uishop.stickshop;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.ConsoleCommandSender;
import cn.nukkit.item.Item;
import me.onebone.economyapi.EconomyAPI;

import java.util.*;

import yxmingy.uishop.Main;
import yxmingy.yupi.*;

public class StickCashier extends HandlerBase{
  Map<String,Object> idata;
  public StickCashier(Map<String,Object> data)
  {
    idata = data;
  }
  public void handle(String data,Player player)
  {
  	if(data.contentEquals("null")) return;
    try{
      int price = Integer.parseInt(String.valueOf(idata.get("游戏币"))),
          stick = Integer.parseInt(String.valueOf(idata.get("枯木数")));
      Item stickItem = new Item(32, 0, stick);
      EconomyAPI eapi = EconomyAPI.getInstance();
      if(eapi.myMoney(player) < price || stick>0 && !player.getInventory().contains(stickItem)) {
      	player.sendMessage("死开，穷鬼！");
      	return;
      }
      eapi.reduceMoney(player, price);
      if(stick > 0) player.getInventory().removeItem(stickItem);
      Server.getInstance().dispatchCommand(new ConsoleCommandSender(), String.valueOf(idata.get("指令")).replaceAll("@s", player.getName()));
      player.sendMessage("购买成功，共花费"+String.valueOf(stick)+"个枯木和"+String.valueOf(price)+Main.getCurrency()+"，祝您购物愉快！");
    }catch(NumberFormatException e)
    {
      player.sendMessage("未知错误!通知服主并让服主通知开发者!");
      e.printStackTrace();
      return;
    }
  }
}
