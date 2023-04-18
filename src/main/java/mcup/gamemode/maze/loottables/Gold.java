package mcup.gamemode.maze.loottables;

import mcup.gamemode.maze.Maze;
import mcup.gamemode.maze.loot.FlightPotion;
import mcup.gamemode.maze.loot.JumpPotion;
import mcup.gamemode.maze.loot.SpeedPotion;
import mcup.gamemode.maze.loot.VanilaItem;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;

public class Gold extends LootTable {
  public Gold(Maze plugin_) {
    super(plugin_);


    table = new ArrayList<>(Arrays.asList(
      new TableEntry(new VanilaItem(plugin, 3, 6, Material.GOLD_INGOT), 70),
      new TableEntry(new JumpPotion(plugin), 10),
      new TableEntry(new SpeedPotion(plugin), 10),
      new TableEntry(new FlightPotion(plugin), 10)
    ));
  }
}
