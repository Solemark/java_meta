public class ConvertDistance {
    public float convert(float distance, String key) {
        if (key == "ktm") {
            return (float) (distance * 0.6213712);
        } else {
            return (float) (distance * 1.609344);
        }
    }
}
