package mcup.gamemode.maze.loottables;

import mcup.gamemode.maze.Maze;
import mcup.gamemode.maze.loot.VanilaItem;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;

public class Iron extends LootTable {

  public Iron(Maze plugin_) {
    super(plugin_);

    table = new ArrayList<>(Arrays.asList(
      new TableEntry(new VanilaItem(plugin, 7, 10, Material.IRON_INGOT), 100)
    ));
  }
}
