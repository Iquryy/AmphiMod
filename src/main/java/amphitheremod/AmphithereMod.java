package amphitheremod;

import amphitheremod.client.ColorizedEntityRender;
import amphitheremod.nukable.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
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
    public static final String CLIENT_PROXY = "amphitheremod.nukable.proxy.ClientProxy";
    public static final String COMMON_PROXY = "amphitheremod.nukable.proxy.CommonProxy";
    public static final Logger LOGGER = LogManager.getLogger();

    @Mod.Instance
    public static AmphithereMod instance;

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public static void PreInit(FMLPreInitializationEvent event) {
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if (FMLLaunchHandler.side() == Side.CLIENT) {
            MinecraftForge.EVENT_BUS.register(new ColorizedEntityRender());
        }
    }

    @Mod.EventHandler
    public static void PostInit(FMLPostInitializationEvent event) {
    }
}