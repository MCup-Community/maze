package mcup.gamemode.maze.loot;

import mcup.gamemode.maze.Maze;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

public class VanilaItem extends LootItem {


  @Override
  public void get(Player player) {
    super.get(player);
    int amount = ThreadLocalRandom.current().nextInt(min, max + 1);
    player.getInventory().addItem(new ItemStack(material, amount));
  }

  private final int min;
  private final int max;

  private final Material material;

  public VanilaItem(Maze plugin_, int min_, int max_, Material material_) {
    super(plugin_);
    min = min_;
    max = max_;
    material = material_;
  }

  public VanilaItem(Maze plugin_, Material material_) {
    super(plugin_);
    min = max = 1;
    material = material_;
  }
}
