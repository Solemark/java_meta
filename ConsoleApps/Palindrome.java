public class Palindrome {
    public boolean checkPalindrome(String input) {
        int c = input.length() - 1;
        for (int i = 0; i < c; i++, c--) {
            if (input.charAt(i) != input.charAt(c)) {
                return false;
            }
        }
        return true;
    }
}
