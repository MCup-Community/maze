package mcup.gamemode.maze.loottables;

import mcup.gamemode.maze.Maze;
import mcup.gamemode.maze.loot.MagicClock;
import mcup.gamemode.maze.loot.SpeedBoots;
import mcup.gamemode.maze.loot.TeleportationScroll;
import mcup.gamemode.maze.loot.VanilaItem;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;

public class Diamond extends LootTable {
  public Diamond(Maze plugin_) {
    super(plugin_);


    table = new ArrayList<>(Arrays.asList(
      new TableEntry(new VanilaItem(plugin, 1, 2, Material.DIAMOND), 80),
      new TableEntry(new TeleportationScroll(plugin), 10),
      new TableEntry(new SpeedBoots(plugin), 10),
      new TableEntry(new MagicClock(plugin), 100)
    ));
  }
}
