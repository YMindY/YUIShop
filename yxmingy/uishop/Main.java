package yxmingy.uishop;

import cn.nukkit.Player;
import cn.nukkit.command.*;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

import java.io.File;

import yxmingy.uishop.sellshop.SellShop;
import yxmingy.yupi.ui.MultiOption;

public class Main extends PluginBase{
  private static File datapath;
  private static String currency;
  private static Config conf = new Config(getDataPath()+"/Currency.yml",Config.YAML);
	public static String getCurrency() {
		return currency;
	}
  public void onEnable() {
	  datapath = getDataFolder();
	  if (conf.getAll().isEmpty()) {
			conf.set("货币名字", "人民币");
			conf.save();
	  }
	  currency = conf.getString("货币名字");
	  SellShop.initBlackMarket();
		getLogger().notice("YUIShop is Enabled! Author: xMing.");
	}
	public void onDisable() {
		getLogger().warning("YUIShop is Turned Off!");
	}
	public static File getDataPath()
	{
	  return datapath;
}
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	  if(!command.getName().equals("shop")) return true;
		MultiOption ui = new MultiOption("§r§l商店系统");
		ui.addButton("出售商店");
		ui.addButton("回收商店");
		ui.addButton("枯木商店");
		ui.addButton("黑市");
		ui.setHandler(new ShopHandler());
		ui.send((Player)sender);
		return true;
	}
}
