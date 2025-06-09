package amphitheremod.mixin.client;

import amphitheremod.client.LayerAmphithereEyes;
import amphitheremod.client.LayerAmphithereGender;
import amphitheremod.client.LayerAmphithereMouth;
import amphitheremod.config.ConfigHandler;
import com.github.alexthe666.iceandfire.client.render.entity.RenderAmphithere;
import com.github.alexthe666.iceandfire.entity.EntityAmphithere;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderAmphithere.class)
public abstract class RenderAmphiLayers extends RenderLiving<EntityAmphithere> {
    public RenderAmphiLayers(RenderManager renderManagerIn) {
        super(renderManagerIn, null, 0F);
    }

    @Inject(method = "<init>(Lnet/minecraft/client/renderer/entity/RenderManager;)V", at = @At("RETURN"), remap = false)
    private void addGlowingLayer(RenderManager renderManager, CallbackInfo ci) {
        this.addLayer(new LayerAmphithereEyes(this));
        if(ConfigHandler.mixins.amphisWithGender)
            this.addLayer(new LayerAmphithereGender(this));
        this.addLayer(new LayerAmphithereMouth(this));
    }
}
