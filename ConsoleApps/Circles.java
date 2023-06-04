public class Circles {
    /**
     * get circumference of circle from radius
     * 
     * @param radius
     * @return float
     */
    public float getCircumference(float radius) {
        if (radius <= 0) {
            return 0;
        } else {
            return (float) (2 * Math.PI * radius);
        }
    }

    /**
     * get area of circle from radius
     * 
     * @param radius
     * @return float
     */
    public float getArea(float radius) {
        if (radius <= 0) {
            return 0;
        } else {
            return (float) (Math.PI * (radius * radius));
        }
    }
}
