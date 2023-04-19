package mcup.gamemode.maze.loot;

import mcup.gamemode.maze.Maze;
import mcup.core.NBTManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class WallShears extends LootItem {

  @Override
  public void get(Player player) {
    super.get(player);

    ItemStack itemStack = new ItemStack(Material.SHEARS);
    Damageable itemMeta = (Damageable) itemStack.getItemMeta();

    itemMeta.setDamage(Material.SHEARS.getMaxDurability() - 2);

    itemMeta.setLore(Arrays.asList(
      org.bukkit.ChatColor.RESET + "" + org.bukkit.ChatColor.GRAY + "Листорезы - режут листья",
      org.bukkit.ChatColor.RESET + "" + org.bukkit.ChatColor.GRAY + "Но их хватит лишь на маленькую дырочку!"
    ));

    itemMeta.setDisplayName(displayName);

    itemStack.setItemMeta(itemMeta);

    itemStack = NBTManager.setCanDestroyTag(itemStack, new ArrayList<>(Collections.singleton(plugin.storage.mazeWallMaterial)));

    player.getInventory().addItem(itemStack);
  }

  public WallShears(Maze plugin_) {
    super(plugin_);
    displayName = ChatColor.DARK_PURPLE + "Листорезы";
  }
}
