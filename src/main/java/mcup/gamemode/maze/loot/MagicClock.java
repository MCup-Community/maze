package mcup.gamemode.maze.loot;

import mcup.gamemode.maze.Maze;
import mcup.core.NBTManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class MagicClock extends LootItem {

  @Override
  public void get(Player player) {
    super.get(player);


    ItemStack itemStack = new ItemStack(Material.CLOCK, 1);
    ItemMeta itemMeta = itemStack.getItemMeta();

    itemMeta.setLore(Arrays.asList(
      org.bukkit.ChatColor.RESET + "" + org.bukkit.ChatColor.GRAY + "Добавляет одну минуту к игровому таймеру",
      org.bukkit.ChatColor.RESET + "" + org.bukkit.ChatColor.GRAY + "Можно активировать по нажатию ПКМ в " + ChatColor.BOLD + "любой момент"
    ));

    itemMeta.setDisplayName(displayName);

    itemStack.setItemMeta(itemMeta);
    itemStack = NBTManager.setTag(itemStack, "magicClock", "true");
    itemStack = NBTManager.setTag(itemStack, "clockId", "" + System.currentTimeMillis());

    player.getInventory().addItem(itemStack);
  }

  public MagicClock(Maze plugin_) {
    super(plugin_);
    displayName = ChatColor.YELLOW + "Маховик времени";
  }
}
