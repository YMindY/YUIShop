package yxmingy.uishop.sellshop;

import cn.nukkit.Player;
import cn.nukkit.utils.Config;
import java.util.*;
import yxmingy.uishop.Main;
import yxmingy.uishop.blackmarket.BlackMarket;
import yxmingy.yupi.*;
import yxmingy.yupi.ui.*;

public class SellShop extends HandlerBase{
  private static Config conf = new Config(Main.getDataPath()+"/出售商店.yml",Config.YAML);
  @SuppressWarnings("unchecked")
	public static void send(Player player)//给玩家发分类页面
  {
    MultiOption ui = new MultiOption("§r§l出售商店");
    Map<String,Object> idata;
    String name;
    for(Map.Entry<String,Object> item : conf.getAll().entrySet())
    {
      idata = (Map<String,Object>)item.getValue();
      name = item.getKey();
      if(idata.containsKey("图标"))
      {
      	ui.addButton(name,true,String.valueOf(idata.get("图标")));
      }else {
      	ui.addButton(name);
      }
    }
    ui.setHandler(new SellShop());
    ui.send(player);
  }
  @SuppressWarnings("unchecked")
  public void handle(String data,Player player)//处理分类页面
  {
  	int i = 0;
  	Map<String, Object> list;
    for(Map.Entry<String,Object> group : conf.getAll().entrySet())
    {
    	if(String.valueOf(i).contentEquals(data))
    	{
    		list = (Map<String, Object>)group.getValue();
    		SellClassifier.send(player, (ArrayList<Map<String, Object>>)list.get("列表"), group.getKey());
    	}
    	i++;
    }
  }
  public static void initBlackMarket()
  {
  	BlackMarket.init(conf);
  }
}
