package yxmingy.uishop.sellshop;

import cn.nukkit.Player;
import cn.nukkit.utils.Config;
import java.util.*;
import yxmingy.uishop.Main;
import yxmingy.yupi.*;
import yxmingy.yupi.ui.*;

public class SellShop extends HandlerBase{
  private static Config conf = new Config(Main.getDataPath()+"/出售商店.yml",Config.YAML);
  @SuppressWarnings("unchecked")
	public static void send(Player player)
  {
    MultiOption ui = new MultiOption("§r§l出售商店");
    Map<String,String> idata;
    for(Map.Entry<String,Object> item : conf.getAll().entrySet())
    {
      idata = (Map<String,String>)item.getValue();
      String name = (String)idata.get("名称"),
             price = (String)String.valueOf(idata.get("价格"));
      ui.addButton(name+" | "+price+" Ft币");
    }
    ui.setHandler(new SellShop());
    ui.send(player);
  }
  @SuppressWarnings("unchecked")
  public void handle(String data,Player player)
  {
    if(!conf.exists(data)) {
      player.sendMessage("配置爆炸!服主背锅!");
      return;
    }
    Map<String,Object> idata = (Map<String,Object>)conf.get(data);
    GarishForm ui = new GarishForm((String)idata.get("标题"));
    ui.addLabel("你要购买的商品为["+String.valueOf(idata.get("名称"))+"] 单价为["+String.valueOf(idata.get("价格"))+"]Ft币");
    ui.addInput("数量", "输入你要购买的数量");
    ui.setHandler(new SellCashier(idata));
    ui.send(player);
  }
}
