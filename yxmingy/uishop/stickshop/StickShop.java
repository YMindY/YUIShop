package yxmingy.uishop.stickshop;

import cn.nukkit.Player;
import cn.nukkit.utils.Config;
import java.util.*;
import yxmingy.uishop.Main;
import yxmingy.yupi.*;
import yxmingy.yupi.ui.*;

public class StickShop extends HandlerBase{
  private static Config conf;
  public static void init() {
		conf = new Config(Main.getDataPath()+"/枯木商店.yml",Config.YAML);
	}
  @SuppressWarnings("unchecked")
	public static void send(Player player)//给玩家发分类页面
  {
    MultiOption ui = new MultiOption("§r§l枯木商店");
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
    ui.setHandler(new StickShop());
    ui.send(player);
  }
  @SuppressWarnings("unchecked")
  public void handle(String data,Player player)//处理分类页面
  {
  	if(data.contentEquals("null")) return;
  	int i = 0;
  	Map<String, Object> list;
    for(Map.Entry<String,Object> group : conf.getAll().entrySet())
    {
    	if(String.valueOf(i).contentEquals(data))
    	{
    		list = (Map<String, Object>)group.getValue();
    		StickClassifier.send(player, (ArrayList<Map<String, Object>>)list.get("列表"), group.getKey());
    	}
    	i++;
    }
  }
}
