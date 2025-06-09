package amphitheremod.mixin.client;

import amphitheremod.config.ConfigHandler;
import com.github.alexthe666.iceandfire.entity.EntityAmphithere;
import com.github.alexthe666.iceandfire.entity.EntityFireDragon;
import com.github.alexthe666.iceandfire.event.EventClient;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EventClient.class)
public abstract class AmphiDragonView {
    @Unique private static EntityFireDragon amphimod$somekindadragon = null;

    @ModifyExpressionValue(
            method = "onCameraSetup",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/EntityPlayer;getRidingEntity()Lnet/minecraft/entity/Entity;")
    )
    private Entity amphimod_alsoAllowAmphiDragonView(Entity original){
        if(original instanceof EntityAmphithere){
            if(amphimod$somekindadragon == null) {
                amphimod$somekindadragon = new EntityFireDragon(Minecraft.getMinecraft().world);
                switch (ConfigHandler.amphiDragonStage){
                    case 1: amphimod$somekindadragon.setAgeInDays(1); break;
                    case 2: amphimod$somekindadragon.setAgeInDays(25); break;
                    case 3: amphimod$somekindadragon.setAgeInDays(50); break;
                    case 4: amphimod$somekindadragon.setAgeInDays(75); break;
                    case 5: amphimod$somekindadragon.setAgeInDays(100); break;
                }
            }
            return amphimod$somekindadragon;
        }
        return original;
    }
}
