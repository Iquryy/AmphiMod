package amphitheremod.nukable.items;

import amphitheremod.AmphithereMod;
import amphitheremod.nukable.init.ModItems;
import amphitheremod.nukable.util.IHasModels;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

public class ItemBase extends Item implements IHasModels {
    public ItemBase(String name, CreativeTabs tabs){
        setTranslationKey(name);
        setRegistryName(name);
        ModItems.ITEMS.add(this);
        this.setCreativeTab(tabs);
        MinecraftForge.EVENT_BUS.register(this);
        ModItems.ITEMS.add(this);

        //Iknow I didnt add the mod id in the translation or register whatever here but I didnt add it in the other none mixins mod too which works fine with texture and lang file "item.test_item.name"
        // that project used net.minecraftforge:forge:1.12.2-14.23.5.2859
    }

    @Override
    public void registerModels(){
        AmphithereMod.proxy.registerItemRenderer(this, 0, "inventory");
    }
}