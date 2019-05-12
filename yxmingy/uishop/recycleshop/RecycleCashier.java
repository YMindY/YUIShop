package yxmingy.uishop.recycleshop;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import me.onebone.economyapi.EconomyAPI;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.*;
import java.util.regex.Pattern;

import yxmingy.uishop.Main;
import yxmingy.yupi.*;

public class RecycleCashier extends HandlerBase{
  Map<String,Object> idata;
  public RecycleCashier(Map<String,Object> data)
  {
    idata = data;
  }
  public void handle(String data,Player player)
  {
  	if(data.contentEquals("null")) return;
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
    	double total,price = Double.parseDouble(String.valueOf(idata.get("价格")));
      int count = Integer.parseInt(String.valueOf(pdata[1])),
          id = Integer.parseInt(String.valueOf(idata.get("id"))),
          meta = Integer.parseInt(String.valueOf(idata.get("特殊值")));
      total = count*price;
      EconomyAPI eapi = EconomyAPI.getInstance();
      Item item = new Item(id, meta, count);
      if(!player.getInventory().contains(item)) {
      	player.sendMessage("你根本没有足够的物品，滚！");
      	return;
      }
      if(player.isCreative()) {
      	player.sendMessage("想拿创造骗我？滚！");
      	return;
      }
      player.getInventory().removeItem(item);
      eapi.addMoney(player, total);
      player.sendMessage("回收成功，共收到"+String.valueOf(total)+Main.getCurrency()+"，祝您交易愉快！");
    }catch(NumberFormatException e)
    {
      player.sendMessage("未知错误!通知服主并让服主通知开发者!");
      e.printStackTrace();
      return;
    }
  }
}
