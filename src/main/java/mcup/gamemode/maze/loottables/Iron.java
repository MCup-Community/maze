package mcup.gamemode.maze.loottables;

import mcup.gamemode.maze.Maze;
import mcup.gamemode.maze.loot.SpeedBoots;
import mcup.gamemode.maze.loot.VanilaItem;
import mcup.gamemode.maze.loot.WallShears;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;

public class Iron extends LootTable {

  public Iron(Maze plugin_) {
    super(plugin_);

    table = new ArrayList<>(Arrays.asList(
      new TableEntry(new VanilaItem(plugin, 7, 10, Material.IRON_INGOT), 90),
      new TableEntry(new WallShears(plugin), 5),
      new TableEntry(new SpeedBoots(plugin), 5)
    ));
  }
}
