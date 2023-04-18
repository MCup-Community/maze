package mcup.gamemode.maze.loottables;

import mcup.gamemode.maze.Maze;
import mcup.gamemode.maze.loot.JumpPotion;
import mcup.gamemode.maze.loot.VanilaItem;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;

public class Gold extends LootTable {
  public Gold(Maze plugin_) {
    super(plugin_);


    table = new ArrayList<>(Arrays.asList(
      new TableEntry(new VanilaItem(plugin, 3, 6, Material.GOLD_INGOT), 90),
      new TableEntry(new JumpPotion(plugin), 10)
    ));
  }
}
