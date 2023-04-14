package mcup.gamemode.maze.stages;

import mcup.core.Core;
import mcup.core.stages.GamemodeStage;
import mcup.gamemode.maze.Maze;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Hunt extends GamemodeStage {

  @Override
  public void load() {
    super.load();
    initBossBarCountdown();

    for (Player player : Bukkit.getOnlinePlayers())
      plugin.storage.giveDefaultEquipment(player);
  }

  protected final Maze plugin;
  public Hunt(Core core_, JavaPlugin plugin_) {
    super(core_, plugin_);
    plugin = (Maze)plugin_;
    timeLimit = 15 * 60 * 20;
  }
}
