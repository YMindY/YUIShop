package yxmingy.uishop;

import cn.nukkit.Player;
import cn.nukkit.command.*;
import cn.nukkit.plugin.PluginBase;
import java.io.File;
import yxmingy.yupi.ui.MultiOption;

public class Main extends PluginBase{
  private static File datapath;
	public void onEnable() {
	  datapath = getDataFolder();
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
