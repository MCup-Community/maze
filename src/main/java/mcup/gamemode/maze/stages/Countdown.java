package mcup.gamemode.maze.stages;

import mcup.core.Core;
import mcup.gamemode.maze.Maze;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public class Countdown extends mcup.core.stages.Countdown {

  @Override
  public void load() {
    super.load();
    initBossBarCountdown();
    core.apiManager.playerManager.setPlayersGamemode(GameMode.ADVENTURE);

    getSpawnLocations();
    spawnPlayers();

    buildWall(Material.AIR, Material.GREEN_TERRACOTTA);
  }

  @Override
  public void unload() {
    buildWall(Material.GREEN_TERRACOTTA, Material.AIR);
    super.unload();
  }

  private void buildWall(Material deletion, Material replacement) {

    Location centerLocation = plugin.getConfig().getLocation("center.location");
    int centerRadius = plugin.getConfig().getInt("center.radius");
    int centerHeight = plugin.getConfig().getInt("center.height");

    Location anchor1 = centerLocation.clone().add(centerRadius, 0, centerRadius);
    Location anchor2 = centerLocation.clone().add(centerRadius, 0, -centerRadius);
    Location anchor3 = centerLocation.clone().add(-centerRadius, 0, -centerRadius);
    Location anchor4 = centerLocation.clone().add(-centerRadius, 0, centerRadius);

    for (int i = 0; i < 2 * centerRadius + 1; i++) {
      for (int j = 0; j < centerHeight; j++) {
        replaceBlock(anchor1.clone().add(0, j, 0), deletion, replacement);
        replaceBlock(anchor2.clone().add(0, j, 0), deletion, replacement);
        replaceBlock(anchor3.clone().add(0, j, 0), deletion, replacement);
        replaceBlock(anchor4.clone().add(0, j, 0), deletion, replacement);
      }

      anchor1.add(0, 0, -1);
      anchor2.add(-1, 0, 0);
      anchor3.add(0, 0, 1);
      anchor4.add(1, 0, 0);
    }
  }

  private void replaceBlock(Location location, Material deletion, Material replacement) {
    if (location.getBlock().getType() == deletion)
      location.getBlock().setType(replacement);
  }

  protected boolean updateSkipCondition(int secondsRemaining) {
    if (secondsRemaining == 30 || secondsRemaining == 15 || secondsRemaining <= 10)
      return false;

    return true;
  }

  protected Maze plugin;
  public Countdown(Core core_, JavaPlugin plugin_) {
    super(core_, plugin_);
    plugin = (Maze)plugin_;
    timeLimit = 20 * 30;
  }
}
