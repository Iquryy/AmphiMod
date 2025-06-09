package amphitheremod.mixin.common;

import amphitheremod.IAmphithereData;
import com.github.alexthe666.iceandfire.entity.EntityAmphithere;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.DifficultyInstance;
import net.minecraftforge.common.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.Random;

@Mixin(EntityAmphithere.class)
public abstract class CustomNBTMixin implements IAmphithereData {

    @Unique private static final Random rand = new Random();

    @Unique private boolean gender = false;
    @Unique private static DataParameter<Boolean> DATA_GENDER;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void clinit(CallbackInfo ci) {
        DATA_GENDER = EntityDataManager.createKey(EntityAmphithere.class, DataSerializers.BOOLEAN);
    }

    @Inject(method = "entityInit", at = @At("TAIL"))
    private void onEntityInit(CallbackInfo ci) {
        EntityAmphithere self = (EntityAmphithere)(Object)this;
        self.getDataManager().register(DATA_GENDER, false);
    }

    @Inject(method = "onInitialSpawn", at = @At("TAIL"))
    private void onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata, CallbackInfoReturnable<IEntityLivingData> cir) {
        boolean initialGender = rand.nextBoolean();

        this.gender = initialGender;

        EntityAmphithere self = (EntityAmphithere)(Object)this;
        self.getDataManager().set(DATA_GENDER, initialGender);
    }

    @Inject(method = "writeEntityToNBT", at = @At("TAIL"))
    private void onWriteEntityToNBT(NBTTagCompound compound, CallbackInfo ci) {
        compound.setBoolean("Gender", this.gender);
    }

    @Inject(method = "readEntityFromNBT", at = @At("TAIL"))
    private void onReadEntityFromNBT(NBTTagCompound compound, CallbackInfo ci) {
        EntityAmphithere self = (EntityAmphithere)(Object)this;
        if (compound.hasKey("Gender", Constants.NBT.TAG_BYTE)) {
            this.gender = compound.getBoolean("Gender");
        } else {
            this.gender = rand.nextBoolean();
        }
        self.getDataManager().set(DATA_GENDER, this.gender);
    }

    @Unique @Override public boolean getGender() {
        EntityAmphithere self = (EntityAmphithere)(Object)this;
        if (self.world.isRemote) {
            return self.getDataManager().get(DATA_GENDER);
        }
        return this.gender;
    }
    @Unique @Override public void setGender(boolean gender) {
        this.gender = gender;
        EntityAmphithere self = (EntityAmphithere)(Object)this;
        if (!self.world.isRemote) {
            self.getDataManager().set(DATA_GENDER, gender);
        }
    }
}