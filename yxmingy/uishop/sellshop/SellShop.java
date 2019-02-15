package yxmingy.uishop.sellshop;

import cn.nukkit.Player;
import cn.nukkit.utils.Config;
import yxmingy.uishop.Main;
import yxmingy.yupi.ui.MultiOption;

import java.util.*;

public class SellShop {
  private static Config conf = new Config(Main.getDataPath()+"出售商店.yml",Config.YAML);
  public static void send(Player player)
  {
    MultiOption ui = new MultiOption("§r§l出售商店");
    Map<String,String> idata;
    for(Map.Entry<String,Object> item : conf.getAll().entrySet())
    {
      idata = (Map<String,String>)item.getValue();
      String name = (String)idata.get("名称"),
             price = (String)idata.get("价格");
      ui.addButton(name+" | "+price);
    }
    ui.send(player);
  }
}
