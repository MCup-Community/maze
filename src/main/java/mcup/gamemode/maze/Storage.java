package mcup.gamemode.maze;

import mcup.core.local.data.Team;
import mcup.gamemode.maze.loottables.Diamond;
import mcup.gamemode.maze.loottables.Gold;
import mcup.gamemode.maze.loottables.Iron;
import mcup.gamemode.maze.loottables.LootTable;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Storage {

  public void resetStorage() {

  }

  public void giveDefaultEquipment(Player player) {
    giveAxe(player);
  }

  public void giveAxe(Player player) {

    ItemStack axe = new ItemStack(Material.IRON_AXE, 1);
    axe.addEnchantment(Enchantment.DIG_SPEED, 2);

    ItemMeta itemMeta = axe.getItemMeta();
    itemMeta.setUnbreakable(true);

    axe.setItemMeta(itemMeta);

    player.getInventory().addItem(NBTManager.setCanDestroyTag(axe, new ArrayList<>(lootBoxes.keySet())));
  }

  public void scrollTeleport(Player player) {

    if (plugin.getConfig().getLocation("center.location") == null)  {
      player.playSound(player, Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);
      player.sendMessage(chatPrefix + "Свиток не сработал! Такое бывает, вы же не маг :)");
      return;
    }

    player.teleport(plugin.getConfig().getLocation("center.location"));
    player.playEffect(EntityEffect.TOTEM_RESURRECT);
    player.sendMessage(chatPrefix + "Вы были телепортированы в центр лабиринта");
  }

  public void magicClockActivate(Player player) {

    Team team = plugin.core.apiManager.teamManager.getTeamByPlayer(player.getName());

    String colorCode = "";
    if (team != null)
      colorCode = team.color;

    plugin.core.apiManager.playerManager.sendTitle(
      ChatColor.GREEN + "+1 минута к таймеру!",
      colorCode + player.getName() + ChatColor.RESET + " активировал " + ChatColor.YELLOW + "Маховик времени",
      20,
      60,
      20,
      Bukkit.getOnlinePlayers()
    );

    plugin.core.apiManager.playerManager.playSound(Sound.BLOCK_BEACON_ACTIVATE, 1.0f, Bukkit.getOnlinePlayers());

    plugin.core.stageManager.getCurrentStage().timeLimit += 60 * 20;


  }

  public void finishPlayer(Player player) {

    player.setGameMode(GameMode.SPECTATOR);

    LinkedHashMap<Material, String> valuables = new LinkedHashMap<>();
    valuables.put(Material.IRON_INGOT, "Железо");
    valuables.put(Material.GOLD_INGOT, "Золото");
    valuables.put(Material.DIAMOND, "Алмазы");

    String padding = "\n        ";

    String message = chatPrefix + "Вы вынесли из лабиринта:\n";
    int totalScore = 0;

    for (Material valuable : valuables.keySet()) {
      int count = 0;
      int valuablePrice = plugin.getConfig().getInt("value." + valuable.toString().toLowerCase());

      for (ItemStack itemStack : player.getInventory().all(valuable).values())
        count += itemStack.getAmount();

      int valuableScore = count * valuablePrice;
      totalScore += valuableScore;

      message += padding + ChatColor.BOLD + valuables.get(valuable) + ChatColor.RESET + " [x" + count + "] * " + valuablePrice + " = " + valuableScore;

    }

    player.getInventory().clear();
    message += padding + ChatColor.BOLD + "" + ChatColor.YELLOW + "Итого: " + totalScore;

    player.sendMessage(message);
    plugin.core.apiManager.scoreManager.addScorePlayer(player.getName(), totalScore, "Собраны сокровища лабиринта");
  }

  public HashMap<Material, LootTable> lootBoxes = new HashMap<>();

  public Material mazeWallMaterial = Material.OAK_LEAVES;

  public Material mazeFloowMaterial = Material.LIME_STAINED_GLASS;

  public String chatPrefix = "[" + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Лабиринт" + ChatColor.RESET + "] ";

  protected Maze plugin;
  public Storage(Maze plugin_) {
    plugin = plugin_;

    lootBoxes.put(Material.MANGROVE_PLANKS, new Iron(plugin));
    lootBoxes.put(Material.JUNGLE_PLANKS, new Gold(plugin));
    lootBoxes.put(Material.ACACIA_PLANKS, new Diamond(plugin));
  }

}
