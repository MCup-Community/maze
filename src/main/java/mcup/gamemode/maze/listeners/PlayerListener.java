package mcup.gamemode.maze.listeners;

import mcup.gamemode.maze.Maze;
import mcup.core.NBTManager;
import mcup.gamemode.maze.stages.Hunt;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

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

    if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.DIAMOND_HORSE_ARMOR &&
      NBTManager.checkTag(event.getPlayer().getInventory().getItemInMainHand(), "teleportScroll",  "true")) {
      event.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.AIR));
      plugin.storage.scrollTeleport(event.getPlayer());
      event.setCancelled(true);
    }


    if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.CLOCK &&
      NBTManager.checkTag(event.getPlayer().getInventory().getItemInMainHand(), "magicClock",  "true")) {

      event.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.AIR));
      plugin.storage.magicClockActivate(event.getPlayer());
      event.setCancelled(true);
    }

  }

  @EventHandler
  public void onPlayerPortal(EntityPortalEnterEvent event) {

    if (event.getEntity() instanceof Player && ((Player) event.getEntity()).getGameMode() != GameMode.SPECTATOR
        && plugin.core.stageManager.getCurrentStage() instanceof Hunt)
      plugin.storage.finishPlayer((Player)event.getEntity());
  }

  @EventHandler
  public void onPlayerAttack(EntityDamageByEntityEvent event) {
    if (event.getDamager() instanceof Player)
      event.setCancelled(true);
  }

  @EventHandler
  public void onPlayerRespawn(PlayerRespawnEvent event) {
    event.getPlayer().teleport(plugin.getConfig().getLocation("center.location"));
  }

  @EventHandler
  public void onPlayerPortalEnter(PlayerPortalEvent event) {
    event.setCancelled(true);
  }

  protected Maze plugin;

  public PlayerListener(Maze plugin_) {
    plugin = plugin_;
  }
}
