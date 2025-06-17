package amphitheremod.probnukable2;

import amphitheremod.AmphithereMod;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = AmphithereMod.MODID)
public class ItemRegistry {
    public static final Item RUBY = new CustomItems("ruby");
    public static final Item SAPPHIRE = new CustomItems("sapphire");
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                RUBY,
                SAPPHIRE
        );
    }
}