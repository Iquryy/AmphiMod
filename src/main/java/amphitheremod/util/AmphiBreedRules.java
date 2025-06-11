package amphitheremod.util;

public class AmphiBreedRules {
    // yea idk how to do this id assume its something along like (enum.blue + enum.red = enum.purple) but I got 0 vlue how to do that at all
    // I also wanted to do something along
    /*
    (enum.blue + enum.red = enum.purple or enum.gem_purple)
    same with every other amphi and then leave the black, white, radish as last roll of weight if none of the previous choosen and then all the weights aaaaaaaaaa
    my  brain thinking about it  just goes https://shorturl.at/CvD8M
    */

    public static  int isValid(EnumAmphiType var1, EnumAmphiType var2, int dim) {
        int var = 0;
        if(var1 != null && var2 != null){
            var = CheckDimension(var1, var2, dim);
        }
         return var;
    }

    static int CheckDimension(EnumAmphiType var1, EnumAmphiType var2, int dim) {
        // 0 = overworld
        // -1 nether
        // 1 = end
        int var = 0;
        switch(dim) {
            case 0:
                System.out.println("overworld");
                var = Overworld(var1, var2);
                break;
            case -1:
                System.out.println("nether");
                var =  Nether(var1, var2);
                break;
            case 1:
                System.out.println("end");
                var =  End(var1, var2);
                break;
        }
        return var;
    }

    // just test placeholders return 20;   return 21;   return 22;
    static int Overworld(EnumAmphiType var1, EnumAmphiType var2){
        return 20;
    }

    static int Nether(EnumAmphiType var1, EnumAmphiType var2){
        return 21;
    }

    static int End(EnumAmphiType var1, EnumAmphiType var2){
        return 22;
    }
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
    |


        AmphiGenders amphi1 = this;
        AmphiGenders amphi2 = (AmphiGenders) otherAnimal;

        if(amphi1.getGender() != amphi2.getGender()) {
            int var1 = amphi1.getVariant();
            int var2 = amphi2.getVariant();

            List<Integer> amphiBreed = Arrays.asList(var1, var2);
            Collections.sort(amphiBreed);

            System.out.println("male + female: " + amphiBreed);
            System.out.println("Outpust custom amphi plz send it once");
        }

    */
