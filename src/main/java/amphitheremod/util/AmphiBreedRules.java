package amphitheremod.util;

public class AmphiBreedRules {
    Enum var1;
    Enum var2;
    Enum varOut;
    int weight;
    int dim;

    AmphiBreedRules(Enum var1, Enum var2, Enum varOut, int weight, int dim){
        this.var1 = var1;
        this.var2 = var2;
        this.varOut = varOut;
        this.weight = weight;
        this.dim = dim;
    }

    void isValid(Enum var1, Enum var2, Enum varOut, int weight, int dim) {
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
