package amphitheremod.config;

import amphitheremod.AmphithereMod;
import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;

@Config(modid = AmphithereMod.MODID)
public class ConfigHandler {
    @Config.Comment("How much hp to heal amphis per max hp when feeding them cocoa beans")
    @Config.Name("Cocoa Beans - Heal per MaxHP")
    public static float healPerMaxHealth = 0.04F;

    @Config.Comment("How much hp to heal amphis per missing hp when feeding them cocoa beans")
    @Config.Name("Cocoa Beans - Heal per Missing HP")
    public static float healPerMissingHealth = 0.035F;

    @Config.Comment("As what stage dragon should the dragon view assume the amphi to be. Higher stages will have the camera be more zoomed out")
    @Config.Name("Dragon View - Amphi Dragon Stage")
    @Config.RangeInt(min = 1, max = 5)
    @Config.RequiresMcRestart
    public static int amphiDragonStage = 3;

    @Config.Comment("Mixin options")
    @Config.Name("Mixin toggles")
    @MixinConfig.SubInstance
    @SuppressWarnings("unused")
    public static MixinToggleConfig mixins = new MixinToggleConfig();
}
