package amphitheremod.config;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;

@SuppressWarnings("unused")
public class MixinToggleConfig {
    @Config.Comment("With this mixin, cocoa beans will not heal a fixed amount of health (2.5 hearts) anymore but a variable amount depending on the missing health and max health of the amphi.")
    @Config.Name("Enable Cocoa Bean Heal Change")
    @Config.RequiresMcRestart
    @MixinConfig.LateMixin(name = "mixins.amphitheremod.cocoabeans.json")
    public boolean changeCocoaBeanHeal = true;

    @Config.Comment("With this mixin, amphitheres will come in male and female genders and will only be able to mate with amphitheres of the other gender")
    @Config.Name("Enable Amphithere Genders")
    @Config.RequiresMcRestart
    @MixinConfig.LateMixin(name = "mixins.amphitheremod.gender.json")
    public boolean amphisWithGender = true;

    @Config.Comment("With this mixin, the dragon 3rd person view in F5 can also be used with amphis")
    @Config.Name("Enable Dragon View")
    @Config.RequiresMcRestart
    @MixinConfig.LateMixin(name = "mixins.amphitheremod.dragonview.json")
    public boolean dragonView = true;

    @Config.Comment("With this mixin, amphis will spawn with and breed to various new color variants. There are even some hidden named variants")
    @Config.Name("Enable New Variants")
    @Config.RequiresMcRestart
    @MixinConfig.LateMixin(name = "mixins.amphitheremod.newvariants.json")
    public boolean newVariants = true;
}
