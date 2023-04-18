package mcup.gamemode.maze.stages;

import mcup.core.Core;
import mcup.core.local.data.Team;
import mcup.core.stages.GamemodeStage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Ending extends GamemodeStage {

  @Override
  public void load() {
    super.load();

    for (Player player : Bukkit.getOnlinePlayers()) {

      Team playerTeam = core.apiManager.teamManager.getTeamByPlayer(player.getName());

      player.sendTitle(
        ChatColor.GOLD + "Игра окончена!",
        (playerTeam == null) ? "" : "Ваша команда заработала " + core.apiManager.scoreManager.getTeamDeltaScore(playerTeam.name) + " очков",
        5,
        70,
        30
      );
    }

    core.apiManager.playerManager.playSound(Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, Bukkit.getOnlinePlayers());

    core.apiManager.teamManager.writeRepository();
  }

  public Ending(Core core_, JavaPlugin plugin_) {
    super(core_, plugin_);
  }
}
