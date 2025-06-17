package amphitheremod.probnukable2;

import amphitheremod.AmphithereMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CustomItems extends Item {
    public CustomItems(String name) {
        setRegistryName(AmphithereMod.MODID, name);
        setTranslationKey(AmphithereMod.MODID + "." + name);
        setCreativeTab(CreativeTabs.MATERIALS);
    }
}