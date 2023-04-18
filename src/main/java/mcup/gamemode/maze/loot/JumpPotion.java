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

public class JumpPotion extends LootItem {

  @Override
  public void get(Player player) {
    super.get(player);

    ItemStack itemStack = new ItemStack(Material.SPLASH_POTION);
    PotionMeta potionMeta = (PotionMeta) itemStack.getItemMeta();

    potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 30 * 20, 3), true);
    potionMeta.setColor(Color.LIME);
    potionMeta.setDisplayName(displayName);

    itemStack.setItemMeta(potionMeta);

    player.getInventory().addItem(itemStack);
  }

  public JumpPotion(Maze plugin_) {
    super(plugin_);
    displayName = ChatColor.GREEN + "Лягушачье зелье";
  }
}
