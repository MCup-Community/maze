package mcup.gamemode.maze.loot;

import mcup.gamemode.maze.Maze;
import org.bukkit.EntityEffect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class LootItem {

  public void get(Player player) {
    player.playSound(player, Sound.ENTITY_ITEM_PICKUP, 1.0f, 1.0f);
  }

  protected Maze plugin;
  public LootItem(Maze plugin_) {
    plugin = plugin_;
  }

}
