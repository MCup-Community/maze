package mcup.gamemode.maze.loot;

import mcup.gamemode.maze.Maze;
import mcup.gamemode.maze.NBTManager;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Arrays;

public class SpeedBoots extends LootItem {

  @Override
  public void get(Player player) {
    super.get(player);

    ItemStack itemStack = new ItemStack(Material.LEATHER_BOOTS);
    LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemStack.getItemMeta();

    leatherArmorMeta.setDisplayName(displayName);
    leatherArmorMeta.setLore(Arrays.asList(
      ChatColor.GRAY + "Ускоряют игрока",
      "",
      ChatColor.GRAY + "Что за что за тяги, бархатные тяги",
      ChatColor.GRAY + "Ребята что за тяги, тяга кефтеме"
    ));

    leatherArmorMeta.setColor(Color.GREEN);

    itemStack.setItemMeta(leatherArmorMeta);
    itemStack = NBTManager.setTag(itemStack, "speedBoots", "true");

    player.getInventory().addItem(itemStack);

  }

  public SpeedBoots(Maze plugin_) {
    super(plugin_);
    displayName = ChatColor.YELLOW + "Подкрадули";
  }
}
