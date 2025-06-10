package amphitheremod.client;

import amphitheremod.util.EnumAmphiType;
import com.github.alexthe666.iceandfire.entity.EntityAmphithere;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static amphitheremod.util.Refs.EMPTY;
import static amphitheremod.util.Refs.MOUTH;

@SideOnly(Side.CLIENT)
public class LayerAmphithereMouth extends AbstractAmphithereLayer {
    public LayerAmphithereMouth(RenderLiving<EntityAmphithere> rendererIn) {
        super(rendererIn);
    }

    @Override protected ResourceLocation getTextureToBind(EntityAmphithere amphithere) {
        //if (EnumAmphiType.nameIsSkeleton(amphithere.getCustomNameTag())) return EMPTY;
        if (amphithere.getVariant() == EnumAmphiType.SKELETON.ordinal()) return EMPTY;
        if (amphithere.getVariant() == EnumAmphiType.WITHER_SKELETON.ordinal()) return EMPTY;

        return MOUTH;
    }
}