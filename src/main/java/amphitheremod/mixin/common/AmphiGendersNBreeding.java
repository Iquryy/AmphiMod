package amphitheremod.mixin.common;

import amphitheremod.util.EnumAmphiType;
import amphitheremod.util.IAmphithereData;
import com.github.alexthe666.iceandfire.entity.EntityAmphithere;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Mixin(EntityAmphithere.class)
public abstract class AmphiGendersNBreeding extends EntityAnimal implements IAmphithereData {
    @Shadow public abstract int getVariant();

    public AmphiGendersNBreeding(World worldIn) {
        super(worldIn);
    }

    @Unique private static DataParameter<Boolean> DATA_GENDER;

    @Override
    public boolean getGender(){
        return this.getDataManager().get(DATA_GENDER);
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

    @Override
    public boolean canMateWith(@Nonnull EntityAnimal otherAnimal) {
        if(!super.canMateWith(otherAnimal)) return false;

        if(this.isChild() || otherAnimal.isChild()) return false;
        if(this.isBeingRidden() || otherAnimal.isBeingRidden()) return false;

        AmphiGendersNBreeding amphi1 = this;
        AmphiGendersNBreeding amphi2 = (AmphiGendersNBreeding) otherAnimal;

        if(amphi1.getGender() != amphi2.getGender()) {
            int var1 = amphi1.getVariant();
            int var2 = amphi2.getVariant();

            List<Integer> amphiBreed = Arrays.asList(var1, var2);
            Collections.sort(amphiBreed);

            System.out.println("male + female: " + amphiBreed);
            System.out.println("Outpust custom amphi plz send it once");
            System.out.println();
            System.out.println("1. amphio type:" + amphiMod$getAmphiEnum(amphiBreed.get(0)));
            System.out.println("2. amphio type:" + amphiMod$getAmphiEnum(amphiBreed.get(1))); // also works :d
        }

        return this.getDataManager().get(DATA_GENDER) != otherAnimal.getDataManager().get(DATA_GENDER);
    }

    @Unique EnumAmphiType amphiMod$getAmphiEnum(int var){
        return EnumAmphiType.values()[var];
    }

    @WrapOperation(method = "createChild", at = @At(value = "INVOKE", target = "Lcom/github/alexthe666/iceandfire/entity/EntityAmphithere;setVariant(I)V", remap = false))
    public void amphiMod_createChildWithOtherVariants(EntityAmphithere amphithere, int variant, Operation<Void> original) {
        original.call(amphithere, 22); // testing which worked
    }
}