public class ConvertDistance {
    /**
     * convert distance from km to miles if true and miles to km is false
     * 
     * @param distance
     * @param key
     * @return float
     */
    public float convert(float distance, String key) {
        if (key == "ktm") {
            return (float) (distance * 0.6213712);
        } else {
            return (float) (distance * 1.609344);
        }
    }
}
