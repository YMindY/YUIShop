package yxmingy.uishop;

import cn.nukkit.Player;
import yxmingy.uishop.recycleshop.RecycleShop;
import yxmingy.uishop.sellshop.SellShop;
import yxmingy.uishop.stickshop.StickShop;
import yxmingy.yupi.HandlerBase;

public class ShopHandler extends HandlerBase{
  final String sellshop = "0",
               recycleshop = "1",
               stickshop = "2",
               blackmarket = "3";
  public void handle(String data,Player player)
  {
    switch(data)
    {
    case sellshop:
      SellShop.send(player);
    break;
    case recycleshop:
      RecycleShop.send(player);
    break;
    case stickshop:
      StickShop.send(player);
    break;
    case blackmarket:
      
    }
  }
}
