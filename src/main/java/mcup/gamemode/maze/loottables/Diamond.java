package mcup.gamemode.maze.loottables;

import mcup.gamemode.maze.Maze;
import mcup.gamemode.maze.loot.VanilaItem;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;

public class Diamond extends LootTable {
  public Diamond(Maze plugin_) {
    super(plugin_);


    table = new ArrayList<>(Arrays.asList(
      new TableEntry(new VanilaItem(plugin, 1, 2, Material.DIAMOND), 100)
    ));
  }
}
