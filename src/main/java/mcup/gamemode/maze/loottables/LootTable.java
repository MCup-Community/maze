package mcup.gamemode.maze.loottables;

import mcup.gamemode.maze.Maze;
import mcup.gamemode.maze.loot.LootItem;

import java.util.ArrayList;

public class LootTable {

  public ArrayList<TableEntry> table;

  public LootItem generateLoot() {

    double totalWeight = 0;

    for (TableEntry tableEntry : table)
      totalWeight += tableEntry.weight;

    double r = totalWeight * Math.random();
    int lootIndex = 0;

    for (lootIndex = 0; lootIndex < table.size() - 1; lootIndex++) {
      r -= table.get(lootIndex).weight;

      if (r <= 0)
        break;
    }

    return table.get(lootIndex).item;
  }

  protected final Maze plugin;

  public LootTable(Maze plugin_) {
    plugin = plugin_;
  }

}

class TableEntry {

  LootItem item;
  int weight;

  TableEntry(LootItem item_, int weight_) {
    item = item_;
    weight = weight_;
  }

}