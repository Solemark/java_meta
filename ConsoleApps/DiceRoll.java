import java.util.Random;

public class DiceRoll {
    public int roll(int max) {
        Random rng = new Random();
        return rng.nextInt(max + 1);
    }
}
