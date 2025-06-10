package amphitheremod.mixin.common;

import amphitheremod.util.EnumAmphiType;
import amphitheremod.util.IAmphithereData;
import com.github.alexthe666.iceandfire.entity.EntityAmphithere;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Mixin(EntityAmphithere.class)
public abstract class AmphiGenders extends EntityAnimal implements IAmphithereData {
    @Shadow public abstract int getVariant();

    public AmphiGenders(World worldIn) {
        super(worldIn);
    }

    @Unique private static DataParameter<Boolean> DATA_GENDER;

    @Override
    public boolean getGender(){
        return this.getDataManager().get(DATA_GENDER);
    }

    @Override
    public boolean canMateWith(@Nonnull EntityAnimal otherAnimal) {
        if(!super.canMateWith(otherAnimal)) return false;

        if(this.isChild() || otherAnimal.isChild()) return false;
        if(this.isBeingRidden() || otherAnimal.isBeingRidden()) return false;

        /* yea idk how to convert this to enum or amphienum to use in the amphi breed rules
        int test1 = this.getVariant();
        int test2 = ((EntityAmphithere) otherAnimal).getVariant();
        */

        return this.getDataManager().get(DATA_GENDER) != otherAnimal.getDataManager().get(DATA_GENDER);
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void amphimod_createGenderDataParam(CallbackInfo ci) {
        DATA_GENDER = EntityDataManager.createKey(EntityAmphithere.class, DataSerializers.BOOLEAN);
    }

    @Inject(method = "entityInit", at = @At("TAIL"))
    private void amphimod_registerGenderDataParam(CallbackInfo ci) {
        this.getDataManager().register(DATA_GENDER, false);
    }

    @Inject(method = "onInitialSpawn", at = @At("TAIL"))
    private void amphimod_spawnWithRandomGender(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata, CallbackInfoReturnable<IEntityLivingData> cir) {
        this.getDataManager().set(DATA_GENDER, this.getRNG().nextBoolean());
    }

    @Inject(method = "writeEntityToNBT", at = @At("TAIL"))
    private void amphimod_writeGenderToNBT(NBTTagCompound compound, CallbackInfo ci) {
        compound.setBoolean("Gender", this.getDataManager().get(DATA_GENDER));
    }

    @Inject(method = "readEntityFromNBT", at = @At("TAIL"))
    private void amphimod_readGenderFromNBT(NBTTagCompound compound, CallbackInfo ci) {
        if (compound.hasKey("Gender"))
            this.getDataManager().set(DATA_GENDER, compound.getBoolean("Gender"));
        else
            this.getDataManager().set(DATA_GENDER, this.getRNG().nextBoolean());
    }
}