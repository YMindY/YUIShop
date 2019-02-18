package yxmingy.uishop.blackmarket;

import cn.nukkit.Player;
import cn.nukkit.utils.Config;

import java.util.*;
import yxmingy.yupi.*;
import yxmingy.yupi.ui.*;

public class BlackMarket extends HandlerBase{
  private static ArrayList<Map<String, Object>> conf = new ArrayList<>();
  @SuppressWarnings("unchecked")
	public static void init(Config sellconf)
  {
  	Random rand = new Random();
		int i,ran;
  	Map<String, Object> groupMap;
  	ArrayList<Map<String, Object>> groupArrayList = new ArrayList<Map<String,Object>>();
  	for(Object group : sellconf.getAll().values()) {
  		groupMap = (Map<String, Object>)group;
  		groupArrayList.addAll((ArrayList<Map<String, Object>>)groupMap.get("列表"));
  	}
  	//Server.getInstance().getLogger().warning(String.valueOf(groupArrayList.size()));
  	if(groupArrayList.size() == 0) {
  		return;
  	}else if (groupArrayList.size() <= 5){
  		conf.addAll(groupArrayList);
  	}else {
  		for(i=0;i<5;i++) {
    		ran = rand.nextInt(groupArrayList.size());
    		//Server.getInstance().getLogger().warning(String.valueOf(ran));
    		if(conf.contains(groupArrayList.get(ran))) {
    			i--;
    			continue;
    		}
    		conf.add(groupArrayList.get(ran));
    	}
		}
  }
	public static void send(Player player)
  {
    MultiOption ui = new MultiOption("§r§l黑市");
    for(Map<String,Object> idata : conf)
    {
      String name = (String)idata.get("名称"),
             price = (String)String.valueOf(idata.get("价格"));
      if(idata.containsKey("图标"))
      {
      	ui.addButton(name+" | 原价:"+price+"Ft币 | 现价:"+(Double.parseDouble(price)*0.8)+"Ft币",true,String.valueOf(idata.get("图标")));
      }else {
      	ui.addButton(name+" | 原价:"+price+"Ft币 | 现价:"+(Double.parseDouble(price)*0.8)+"Ft币");
      }
    }
    ui.setHandler(new BlackMarket());
    ui.send(player);
  }
  public void handle(String data,Player player)
  {
  	if(data.contentEquals("null")) return;
  	try {
	    Map<String,Object> idata = (Map<String,Object>)conf.get(Integer.parseInt(data));
	    double price = (Double.parseDouble(String.valueOf(idata.get("价格"))))*0.8;
	    GarishForm ui = new GarishForm((String)idata.get("标题"));
	    ui.addLabel("你要购买的商品为["+String.valueOf(idata.get("名称"))+"] 八折后价格为"+String.valueOf(price)+"Ft币");
	    ui.addInput("数量", "输入你要购买的数量");
	    ui.setHandler(new BlackCashier(idata));
	    ui.send(player);
  	}catch (IndexOutOfBoundsException e) {
  		player.sendMessage("配置爆炸!服主背锅!");
  	}
  }
}
