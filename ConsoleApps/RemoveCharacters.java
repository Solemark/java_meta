public class RemoveCharacters {
    /**
     * remove select characters from string
     * 
     * @param input
     * @param chars
     * @return String
     */
    public String removeCharacters(String input, String[] chars) {
        String output = input;
        for (String string : chars) {
            output = output.replace(string, "");
        }
        return output;
    }
}
