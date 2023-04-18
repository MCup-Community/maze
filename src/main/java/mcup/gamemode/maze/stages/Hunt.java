package mcup.gamemode.maze.stages;

import mcup.core.Core;
import mcup.core.stages.GamemodeStage;
import mcup.gamemode.maze.Maze;
import mcup.gamemode.maze.NBTManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Hunt extends GamemodeStage {

  @Override
  public void load() {
    super.load();
    initBossBarCountdown();

    for (Player player : Bukkit.getOnlinePlayers())
      plugin.storage.giveDefaultEquipment(player);
  }

  @Override
  public void tickSecond() {
    super.tickSecond();

    for (Player player : Bukkit.getOnlinePlayers())
      if (player.getInventory().getBoots() != null && NBTManager.checkTag(player.getInventory().getBoots(), "speedBoots", "true"))
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30, 0));

  }

  protected final Maze plugin;
  public Hunt(Core core_, JavaPlugin plugin_) {
    super(core_, plugin_);
    plugin = (Maze)plugin_;
    timeLimit = 2 * 60 * 20;
  }
}
