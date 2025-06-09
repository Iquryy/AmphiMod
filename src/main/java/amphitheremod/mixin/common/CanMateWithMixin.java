package amphitheremod.mixin.common;

import amphitheremod.IAmphithereData;
import com.github.alexthe666.iceandfire.entity.EntityAmphithere;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(EntityAmphithere.class)
public abstract class CanMateWithMixin extends EntityAnimal{
    public CanMateWithMixin(World world) {
        super(world);
    }
    // MALE WITH FEMALE
    @Unique public boolean canMateWith(EntityAnimal otherAnimal) {
        if (otherAnimal instanceof EntityAmphithere && otherAnimal != this && otherAnimal.getClass() == this.getClass()) {
            EntityAmphithere amphithere = (EntityAmphithere)otherAnimal;
            IAmphithereData data1 = (IAmphithereData) amphithere;
            IAmphithereData data2 = (IAmphithereData) this;
            if (!this.isChild() && this.isInLove() && !this.isBeingRidden()) {
                if (!amphithere.isChild() && amphithere.isInLove() && !amphithere.isBeingRidden()) {
                    return data2.getGender() && !data1.getGender() || !data2.getGender() && data1.getGender();
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
