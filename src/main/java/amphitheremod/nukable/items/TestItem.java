package amphitheremod.nukable.items;

import amphitheremod.AmphithereMod;
import amphitheremod.nukable.util.IHasModels;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TestItem extends Item implements IHasModels {
    public TestItem(String name, CreativeTabs tab) {
        new ItemBase(name, tab);
    }


    @Override
    public void registerModels(){
        AmphithereMod.proxy.registerItemRenderer(this, 0, "inventory");
    }

}
