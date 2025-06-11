package amphitheremod;

import amphitheremod.client.ColorizedEntityRender;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = AmphithereMod.MODID,
        version = AmphithereMod.VERSION,
        name = AmphithereMod.NAME,
        dependencies = "required-after:fermiumbooter"
)
public class AmphithereMod {
    public static final String MODID = "amphitheremod";
    public static final String VERSION = "1.0.1";
    public static final String NAME = "Amphithere Mod";
    public static final Logger LOGGER = LogManager.getLogger();

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if (FMLLaunchHandler.side() == Side.CLIENT) {
            MinecraftForge.EVENT_BUS.register(new ColorizedEntityRender());
        }
    }
}