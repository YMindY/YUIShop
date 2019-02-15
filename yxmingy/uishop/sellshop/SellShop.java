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
    ui.addLabel((String)idata.get("名称"));
    ui.addLabel("价格"+String.valueOf(idata.get("价格")));
    ui.addSlider("数量",1,64,1);
    ui.send(player);
  }
}
