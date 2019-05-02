package yxmingy.uishop.stickshop;

import java.util.*;

import cn.nukkit.Player;
import yxmingy.uishop.Main;
import yxmingy.yupi.HandlerBase;
import yxmingy.yupi.ui.*;

public class StickClassifier extends HandlerBase {
	
	private ArrayList<Map<String, Object>> idata;
	
	public StickClassifier(ArrayList<Map<String, Object>> idata)
	{
		this.idata = idata;
	}
	
	/* 给玩家发商品列表 */
	public static void send(Player player,ArrayList<Map<String, Object>> list,String title)
	{
		MultiOption ui = new MultiOption("§r§l枯木商店-"+title);
    for(Map<String, Object> idata : list)
    {
      String name = String.valueOf(idata.get("名称")),
             price = String.valueOf(idata.get("游戏币")),
             stick = String.valueOf(idata.get("枯木数"));
      if(idata.containsKey("图标"))
      {
      	ui.addButton(name+" | 枯木*"+stick+" + "+price+Main.getCurrency(),true,String.valueOf(idata.get("图标")));
      }else {
      	ui.addButton(name+" | 枯木*"+stick+" + "+price+Main.getCurrency());
      }
    }
    ui.setHandler(new StickClassifier(list));
    ui.send(player);
	}

	@Override
	public void handle(String data, Player player) {
		if(data.contentEquals("null")) return;
		try {
	    Map<String,Object> idata = (Map<String,Object>)this.idata.get(Integer.parseInt(data));
	    GarishForm ui = new GarishForm((String)idata.get("标题"));
	    String price = String.valueOf(idata.get("游戏币")),
             stick = String.valueOf(idata.get("枯木数")),
             primsg = "枯木*"+stick+" + "+price+"Ft币";
	    ui.addLabel("你要购买的商品为["+String.valueOf(idata.get("名称"))+"] 单价为"+primsg);
	    ui.setHandler(new StickCashier(idata));
	    ui.send(player);
		}catch (IndexOutOfBoundsException e) {
			player.sendMessage("配置爆炸!服主背锅!");
		}
	}
}
