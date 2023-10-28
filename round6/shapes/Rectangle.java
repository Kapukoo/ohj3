public class Rectangle implements IShapeMetrics {
    private final double height;
    private final double width;

    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }
    
    @Override
    public String toString() {
        return String.format("Rectangle with height %.2f and width %.2f",
                height, width);
    }
    
    public String name() {
        return "rectangle";
    }
    
    public double area() {
        return width*height;
    }
    
    public double circumference() {
        return 2*width+2*height;
    }
    
}