package amphitheremod.util;

import com.google.common.collect.Maps;
import java.util.Map;

public enum EnumCustomColor {

    WHITE(0, "white", "customWhite", 16777215),
    BLACK(1, "black", "customBlack", 0),
    RED(2, "red", "red", 16711680),
    ORANGE(3, "orange", "orange", 16742144),
    YELLOW(4, "yellow", "yellow", 16187136),
    LIME(5, "lime", "lime", 917248),
    CYAN(6, "cyan", "cyan", 65509),
    BLUE(7, "blue", "blue", 23039),
    DEEP_BLUE(8, "deepBlue", "deep_blue", 852223),
    PURPLE(9, "purple", "purple", 9437439),
    PINK(10, "pink", "pink", 16711922),
    MAGENTA(11, "magenta", "magenta", 16711756);

    private final int id;
    private final String name;
    private final String translationKey;
    private final int colorValue;
    private static final Map<EnumCustomColor, float[]> COLOR_TO_RGB;

    EnumCustomColor(int id, String name, String translationKey, int colorValue) {
        this.id = id;
        this.name = name;
        this.translationKey = translationKey;
        this.colorValue = colorValue;
    }

    static {
        COLOR_TO_RGB = Maps.newEnumMap(EnumCustomColor.class);

        for (EnumCustomColor color : values()) {
            COLOR_TO_RGB.put(color, createRenderColor(color));
        }

        COLOR_TO_RGB.put(EnumCustomColor.WHITE, new float[]{0.9019608F, 0.9019608F, 0.9019608F});
    }

    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public String getTransKey() { return this.translationKey; }
    public int getColorValue() { return this.colorValue; }

    public float[] getColorComponentValues() {
        int r = (this.getColorValue() >> 16) & 255;
        int g = (this.getColorValue() >> 8) & 255;
        int b = this.getColorValue() & 255;
        return new float[]{(float) r / 255.0F, (float) g / 255.0F, (float) b / 255.0F};
    }

    private static float[] createRenderColor(EnumCustomColor color) {
        float[] baseComponents = color.getColorComponentValues();
        float brightness = 0.75F;
        return new float[]{
                baseComponents[0] * brightness,
                baseComponents[1] * brightness,
                baseComponents[2] * brightness
        };
    }

    public static float[] getColorRgb(EnumCustomColor color) {
        return COLOR_TO_RGB.get(color);
    }
}