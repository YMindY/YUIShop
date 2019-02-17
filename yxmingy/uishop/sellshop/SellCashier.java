package yxmingy.uishop.sellshop;

import cn.nukkit.Player;
import yxmingy.yupi.*;

import java.util.Map;

public class SellCashier extends HandlerBase{
  Map<String,Object> idata;
  public SellCashier(Map<String,Object> data)
  {
    idata = data;
  }
  public void handle(String data,Player player)
  {
    player.sendMessage(data);
  }
}
