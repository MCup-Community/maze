package mcup.gamemode.maze.loot;

import mcup.gamemode.maze.Maze;
import mcup.gamemode.maze.NBTManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class TeleportationScroll extends LootItem {

  @Override
  public void get(Player player) {
    super.get(player);

    ItemStack itemStack = new ItemStack(Material.DIAMOND_HORSE_ARMOR, 1);
    ItemMeta itemMeta = itemStack.getItemMeta();

    itemMeta.setLore(Arrays.asList(
      ChatColor.RESET + "" + ChatColor.GRAY + "Телепортирует игрока в центр",
      ChatColor.RESET + "" + ChatColor.GRAY + "Для активации нажмите ПКМ и произнесите магические слова:",
      ChatColor.MAGIC + "Абракадабра!"
    ));

    itemMeta.setDisplayName(displayName);

    itemStack.setItemMeta(itemMeta);
    itemStack = NBTManager.setTag(itemStack, "teleportScroll", "true");

    player.getInventory().addItem(itemStack);
  }

  public TeleportationScroll(Maze plugin_) {
    super(plugin_);
    displayName = ChatColor.YELLOW + "Свиток телепортации";
  }
}
