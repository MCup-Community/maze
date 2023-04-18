package mcup.gamemode.maze.loot;

import mcup.gamemode.maze.Maze;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpeedPotion extends LootItem {


  @Override
  public void get(Player player) {
    super.get(player);

    ItemStack itemStack = new ItemStack(Material.POTION);
    PotionMeta potionMeta = (PotionMeta) itemStack.getItemMeta();

    potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 30 * 20, 2), false);
    potionMeta.setColor(Color.AQUA);
    potionMeta.setDisplayName(displayName);

    itemStack.setItemMeta(potionMeta);

    player.getInventory().addItem(itemStack);
  }
  public SpeedPotion(Maze plugin_) {
    super(plugin_);
    displayName = ChatColor.GREEN + "Зелье скорости";
  }
}
