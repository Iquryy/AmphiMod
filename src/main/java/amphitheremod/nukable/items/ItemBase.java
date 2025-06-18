package amphitheremod.nukable.items;

import amphitheremod.AmphithereMod;
import amphitheremod.nukable.init.ModItems;
import amphitheremod.nukable.util.IHasModels;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModels {
    public ItemBase(String name, CreativeTabs creativeTab) {
        this.setRegistryName(name);
        this.setTranslationKey(name);
        this.setCreativeTab(creativeTab);
        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        AmphithereMod.proxy.registerItemRenderer(this, 0, "inventory");
    }
}