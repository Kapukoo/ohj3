public class Circle implements IShapeMetrics {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public String toString() {
        return String.format("Circle with radius: %.2f", radius);
    }
    
    public String name() {
        return "circle";
    }
    
    public double area() {
        return PI*radius*radius;
    }
    
    public double circumference() {
        return 2*PI*radius;
    }
    
}