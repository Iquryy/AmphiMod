package amphitheremod.mixin.common;

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
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Mixin(EntityAmphithere.class)
public abstract class AmphiGenders extends EntityAnimal implements IAmphithereData {
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
    /*
    private static String testDim;
    private static Random rand = new Random();

    public static void main(String[] args) {
        String[] dims = {"end", "ow", "nether"};
        testDim = dims[rand.nextInt(dims.length)];
        int var1 = 0;
        int var2 = rand.nextInt(3);

        List<Integer> test = Arrays.asList(var1, var2);
        Collections.sort(test);

        System.out.println("dimension: " + testDim);
        System.out.println("list: " + test);
        System.out.print("var1: " + test.get(0)+", ");
        System.out.println("var2: " + test.get(1));
        System.out.println("choosen variant: " + returnVar(var1, var2));
    }

    // what dimension amphis ion
    private static int returnVar(int var1, int var2) {
        int var = 0;
        if(testDim.contains("ow"))
            var = Overworld(var1, var2);
        if(testDim.contains("nether"))
            var = Nether(var1, var2);
        if(testDim.contains("end"))
            var = End(var1, var2);
        return var;
    }


    // ow variants
    static int Overworld(int var1, int var2){
        int var = 0;
        if(var1 == 0 && var2 == 0)
            var = 16;
        if(var1 == 0 && var2 == 1)
            var = 19;
        if(var1 == 0 && var2 == 2)
            var = 17;
        return var;
    }

    // Nether variants
    static int Nether(int var1, int var2){
        int var = 0;
        if(var1 == 0 && var2 == 0)
            var = 26;
        if(var1 == 0 && var2 == 1)
            var = 29;
        if(var1 == 0 && var2 == 2)
            var = 27;
        return var;
    }

    // End variants
    static int End(int var1, int var2){
        int var = 0;
        if(var1 == 0 && var2 == 0)
            var = 36;
        if(var1 == 0 && var2 == 1)
            var = 39;
        if(var1 == 0 && var2 == 2)
            var = 69;
        return var;
    }
    */
}