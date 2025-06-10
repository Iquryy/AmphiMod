package amphitheremod.util;

import net.minecraft.util.ResourceLocation;

public enum EnumAmphiType {
    BLUE(Group.NORMAL, Eyes.YELLOW, "default_variants/blue", true),
    GREEN(Group.NORMAL, Eyes.YELLOW, "default_variants/green", true),
    OLIVE(Group.NORMAL, Eyes.YELLOW, "default_variants/olive", true),
    RED(Group.NORMAL, Eyes.YELLOW, "default_variants/red", true),
    YELLOW(Group.NORMAL, Eyes.YELLOW, "default_variants/yellow", true),
    PURPLE(Group.NORMAL, Eyes.YELLOW, "default_variants/purple", true),

    RAINBOW(Group.SPECIAL, Eyes.YELLOW, "default_variants/rainbow", true),

    BLACK(Group.RARE, Eyes.NORMAL, "new_variants/rare/black", true),
    WHITE(Group.RARE, Eyes.PINK, "new_variants/rare/white", true),
    RADISH(Group.RARE, Eyes.LIME, "new_variants/rare/radishe", true),

    BLACK_GEM(Group.GEM_RARE, Eyes.LIME, "new_variants/rare_gem/black", true),
    WHITE_GEM(Group.GEM_RARE, Eyes.LIGHT_BLUE, "new_variants/rare_gem/white", true),

    BLUE_GEM(Group.GEM, Eyes.LIME, "new_variants/gem/blue", true),
    CYAN_GEM(Group.GEM, Eyes.LIME, "new_variants/gem/cyan", true),
    GREEN_GEM(Group.GEM, Eyes.PINK, "new_variants/gem/green", true),
    LIME_GEM(Group.GEM, Eyes.ORANGE, "new_variants/gem/lime", true),
    OLIVE_GEM(Group.GEM, Eyes.PINK, "new_variants/gem/olive", true),
    RED_GEM(Group.GEM, Eyes.PURPLE, "new_variants/gem/red", true),
    YELLOW_GEM(Group.GEM, Eyes.MAGENTA, "new_variants/gem/yellow", true),
    PINK_GEM(Group.GEM, Eyes.LIGHT_BLUE, "new_variants/gem/pink", true),
    PURPLE_GEM(Group.GEM, Eyes.LIGHT_BLUE, "new_variants/gem/purple", true),

    SKELETON(Group.SKELETON, Eyes.NONE, "new_variants/special/skeleton", false),
    WITHER_SKELETON(Group.SKELETON, Eyes.NONE, "new_variants/special/wither_skeleton", false);

    private final Group group;
    public Group getGroup(){
        return group;
    }

    private final Eyes eyes;
    public Eyes getEyes(){
        return eyes;
    }

    private final ResourceLocation loc;
    private final ResourceLocation loc_blink;
    public ResourceLocation getTexture(boolean isBlinking){
        if(isBlinking && loc_blink != null) return loc_blink;
        else return loc;
    }

    EnumAmphiType(Group group, Eyes eyes, String loc, boolean hasBlinkVariant) {
        this.group = group;
        this.eyes = eyes;
        this.loc = new ResourceLocation("amphitheremod:textures/entity/amphithere/"+loc+".png");
        this.loc_blink = hasBlinkVariant ? new ResourceLocation("amphitheremod:textures/entity/amphithere/"+loc+"_blink.png") : null;
    }

    public enum Group {
        NORMAL,
        RARE,
        GEM_RARE,
        GEM,
        SKELETON,
        SPECIAL
    }

    public enum Eyes {
        NONE, //Does the same as normal (not render the eyes layer) but gets its own type just for safety
        NORMAL,
        YELLOW,
        PINK,
        LIME,
        LIGHT_BLUE,
        MAGENTA,
        ORANGE,
        PURPLE
    }

    // Gonna add another way to get skeleton amphi
    public static boolean nameIsSkeleton(String name){
        return name.equals("s") || name.equals("S") || name.equals("w") || name.equals("W");
    }
}
