package yxmingy.uishop.sellshop;

import java.util.*;

import cn.nukkit.Player;
import yxmingy.yupi.HandlerBase;
import yxmingy.yupi.ui.*;

public class SellClassifier extends HandlerBase {
	
	private ArrayList<Map<String, Object>> idata;
	
	public SellClassifier(ArrayList<Map<String, Object>> idata)
	{
		this.idata = idata;
	}
	
	/* 给玩家发商品列表 */
	public static void send(Player player,ArrayList<Map<String, Object>> list,String title)
	{
		MultiOption ui = new MultiOption("§r§l出售商店-"+title);
    for(Map<String, Object> idata : list)
    {
      String name = String.valueOf(idata.get("名称")),
             price = String.valueOf(idata.get("价格"));
      if(idata.containsKey("图标"))
      {
      	ui.addButton(name+" | "+price+" Ft币",true,String.valueOf(idata.get("图标")));
      }else {
      	ui.addButton(name+" | "+price+" Ft币");
      }
    }
    ui.setHandler(new SellClassifier(list));
    ui.send(player);
	}

	@Override
	public void handle(String data, Player player) {
		if(data.contentEquals("null")) return;
		try {
	    Map<String,Object> idata = (Map<String,Object>)this.idata.get(Integer.parseInt(data));
	    GarishForm ui = new GarishForm((String)idata.get("标题"));
	    ui.addLabel("你要购买的商品为["+String.valueOf(idata.get("名称"))+"] 单价为"+String.valueOf(idata.get("价格"))+"Ft币");
	    ui.addInput("数量", "输入你要购买的数量");
	    ui.setHandler(new SellCashier(idata));
	    ui.send(player);
		}catch (IndexOutOfBoundsException e) {
			player.sendMessage("配置爆炸!服主背锅!");
		}
	}
}
