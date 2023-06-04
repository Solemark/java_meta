import java.util.Random;

public class DiceRoll {
    /**
     * randomly gets a number between 1 and max
     * 
     * @param max
     * @return int
     */
    public int roll(int max) {
        Random rng = new Random();
        return rng.nextInt(max + 1);
    }
}
