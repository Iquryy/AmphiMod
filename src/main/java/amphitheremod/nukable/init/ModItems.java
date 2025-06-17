package amphitheremod.nukable.init;

import amphitheremod.nukable.items.TestItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static final List<Item> ITEMS = new ArrayList<Item>();
    public static final Item TEST_ITEM = new TestItem("test_item", CreativeTabs.MATERIALS);
}