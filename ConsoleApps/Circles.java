public class Circles {
    public float getCircumference(float radius) {
        if (radius <= 0) {
            return 0;
        } else {
            return (float) (2 * Math.PI * radius);
        }
    }

    public float getArea(float radius) {
        if (radius <= 0) {
            return 0;
        } else {
            return (float) (Math.PI * (radius * radius));
        }
    }
}
