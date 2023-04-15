package mcup.gamemode.maze;

import de.tr7zw.changeme.nbtapi.NBTItem;
import de.tr7zw.changeme.nbtapi.NBTList;
import mcup.gamemode.maze.loottables.Diamond;
import mcup.gamemode.maze.loottables.Gold;
import mcup.gamemode.maze.loottables.Iron;
import mcup.gamemode.maze.loottables.LootTable;
import org.bukkit.ChatColor;
import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class Storage {

  public void resetStorage() {

  }

  public void giveDefaultEquipment(Player player) {
    giveAxe(player);
  }

  public void giveAxe(Player player) {

    ItemStack axe = new ItemStack(Material.IRON_AXE, 1);
    axe.addEnchantment(Enchantment.DIG_SPEED, 2);

    ItemMeta axeMeta = axe.getItemMeta();
    axeMeta.setUnbreakable(true);
    axe.setItemMeta(axeMeta);

    NBTItem nbtItem = new NBTItem(axe);
    NBTList<String> canDestroyList = nbtItem.getStringList("CanDestroy");

    for (Material lootBoxMaterial : lootBoxes.keySet())
      canDestroyList.add("minecraft:" + lootBoxMaterial.name().toLowerCase());

    player.getInventory().addItem(nbtItem.getItem());
  }

  public void scrollTeleport(Player player) {
    player.teleport(plugin.getConfig().getLocation("center.location"));
    player.playEffect(EntityEffect.TOTEM_RESURRECT);
    player.sendMessage(chatPrefix + "Вы были телепортированы в центр лабиринта");
  }

  public HashMap<Material, LootTable> lootBoxes = new HashMap<>();

  public String chatPrefix = "[" + ChatColor.GREEN + "" + ChatColor.BOLD + "Лабиринт" + ChatColor.RESET + "] ";

  protected Maze plugin;
  public Storage(Maze plugin_) {
    plugin = plugin_;

    lootBoxes.put(Material.MANGROVE_PLANKS, new Iron(plugin));
    lootBoxes.put(Material.JUNGLE_PLANKS, new Gold(plugin));
    lootBoxes.put(Material.ACACIA_PLANKS, new Diamond(plugin));
  }

}
