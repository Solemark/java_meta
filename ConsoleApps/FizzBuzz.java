public class FizzBuzz {
    public String fizzBuzz(int fizz, int buzz, int max) {
        String output = "";
        Boolean flag = false;
        for (int i = 1; i <= max; i++) {
            flag = false;
            if (0 == i % fizz) {
                flag = true;
                output += "fizz";
            }
            if (0 == i % buzz) {
                flag = true;
                output += "buzz";
            }
            if (!flag) {
                output += i;
            }
            if (i != max) {
                output += "\n";
            }
        }
        return output;
    }
}
