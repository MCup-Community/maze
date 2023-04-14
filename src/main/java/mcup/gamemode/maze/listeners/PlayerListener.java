package mcup.gamemode.maze.listeners;

import mcup.gamemode.maze.Maze;
import mcup.gamemode.maze.stages.Hunt;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerListener implements Listener {

  @EventHandler
  public void onBlockBreak(BlockBreakEvent event) {

    if (!(plugin.core.stageManager.getCurrentStage() instanceof Hunt))
      return;

    if (!plugin.storage.lootBoxes.containsKey(event.getBlock().getType()))
      return;

    plugin.storage.lootBoxes.get(event.getBlock().getType()).generateLoot().get(event.getPlayer());

    event.getBlock().setType(Material.AIR);
    event.setCancelled(true);
  }

  @EventHandler
  public void onPlayerUse(PlayerInteractEvent event) {

    if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK)
      return;

    if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.DIAMOND_HORSE_ARMOR) {
      plugin.storage.scrollTeleport(event.getPlayer());
      event.getPlayer().getInventory().remove(event.getPlayer().getInventory().getItemInMainHand());
      event.setCancelled(true);
    }

  }

  protected Maze plugin;

  public PlayerListener(Maze plugin_) {
    plugin = plugin_;
  }
}
