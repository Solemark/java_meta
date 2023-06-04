import java.util.Random;

public class GachaRoll {
    /**
     * simulates getting an SSR in a gacha game
     * 
     * @param game
     * @return String
     */
    public static String roll(String game) {
        int rolls = 0, currentRoll = 0, rate = 0, pity = 0;
        String rarity = "";
        Random rng = new Random();
        switch (game) {
            case "FGO":
                rate = 100;
                pity = 100;
                rarity = "5*";
                break;
            case "AK":
                rate = 50;
                pity = 100;
                rarity = "6*";
                break;
            case "GI":
                rate = 60;
                pity = 90;
                rarity = "5*";
                break;
            default:
                return "Unknown game!";
        }
        while (true) {
            rolls++;
            currentRoll = rng.nextInt(rate + 1);
            if (currentRoll == rate) {
                return String.format("It took %d rolls to get a %s in %s", rolls, rarity, game);
            }
            if (rolls == pity) {
                return String.format("It hit pity at %d rolls to get a %s in %s", pity, rarity, game);
            }
        }
    }
}
