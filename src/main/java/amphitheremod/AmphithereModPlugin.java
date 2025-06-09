package amphitheremod;

import amphitheremod.config.ConfigHandler;
import fermiumbooter.FermiumRegistryAPI;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;

import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.12.2")
public class AmphithereModPlugin implements IFMLLoadingPlugin {

	public AmphithereModPlugin() {
		MixinBootstrap.init();

		FermiumRegistryAPI.registerAnnotatedMixinConfig(ConfigHandler.class, null);
		FermiumRegistryAPI.enqueueMixin(true, "mixins.amphitheremod.renderlayers.json");
	}

	@Override
	public String[] getASMTransformerClass()
	{
		return new String[0];
	}
	
	@Override
	public String getModContainerClass()
	{
		return null;
	}
	
	@Override
	public String getSetupClass()
	{
		return null;
	}
	
	@Override
	public void injectData(Map<String, Object> data) { }
	
	@Override
	public String getAccessTransformerClass()
	{
		return null;
	}
}