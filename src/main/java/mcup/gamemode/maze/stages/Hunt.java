package mcup.gamemode.maze.stages;

import mcup.core.Core;
import mcup.core.stages.GamemodeStage;
import mcup.gamemode.maze.Maze;
import mcup.gamemode.maze.NBTManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Hunt extends GamemodeStage {

  @Override
  public void load() {
    super.load();
    initBossBarCountdown();

    core.apiManager.playerManager.sendTitle(
      ChatColor.GOLD + "" + ChatColor.BOLD + "Вперед, на поиски!",
      "Успейте сохранить сокровища до закрытия лабиринта!",
      10,
      60,
      10,
      Bukkit.getOnlinePlayers()
    );

    core.apiManager.playerManager.playSound(Sound.BLOCK_BELL_RESONATE, 1.0f, Bukkit.getOnlinePlayers());

    for (mcup.core.local.data.Player player : core.apiManager.playerManager.getPlayers())
      plugin.storage.giveDefaultEquipment(player.nickname);
  }

  @Override
  public void tickSecond() {
    super.tickSecond();

    for (Player player : Bukkit.getOnlinePlayers())
      if (player.getInventory().getBoots() != null && NBTManager.checkTag(player.getInventory().getBoots(), "speedBoots", "true"))
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30, 0));


    if (getSecondsLeft() == 300 || getSecondsLeft() == 60 || getSecondsLeft() == 30 || getSecondsLeft() <= 10) {
      core.apiManager.playerManager.playSound(Sound.UI_BUTTON_CLICK, 1.0f, Bukkit.getOnlinePlayers());

      if (getSecondsLeft() > 60) {
        core.apiManager.playerManager.sendTitle(
          ChatColor.YELLOW + "" + (getSecondsLeft() / 60) ,
          "минут" + (getSecondsLeft() == 60 ? "a" : "") + " до закрытия лабиринта!",
          10,
          20,
          10,
          Bukkit.getOnlinePlayers()
        );
      }

      else {
        core.apiManager.playerManager.sendTitle(
          ChatColor.YELLOW + "" + getSecondsLeft(),
          (getSecondsLeft() == 10 ? " секунд до закрытия лабиринта!" : ""),
          0,
          20,
          0,
          Bukkit.getOnlinePlayers()
        );
      }
    }

  }

  @Override
  public boolean endCondition() {

    for (Player player : core.apiManager.playerManager.getOnlinePlayers())
      if (player.getGameMode() != GameMode.SPECTATOR)
        return false;

    return true;
  }

  protected final Maze plugin;
  public Hunt(Core core_, JavaPlugin plugin_) {
    super(core_, plugin_);
    plugin = (Maze)plugin_;
    timeLimit = 10 * 60 * 20;
    bossBarCountdownLabelPrefix = "До закрытия лабиринта: ";
  }
}
