public class ValueNode extends Node {
    private final Object data;

    // konssit
    public ValueNode() {
        this.data = null;
    }

    public ValueNode(double value) {
        this.data = value;
    }
    
    public ValueNode(boolean value) {
        this.data = value;
    }
    
    public ValueNode(String value) {
        this.data = value;
    }
    
    // testit
    public boolean isNumber() {
        return data instanceof Double;
    }
    
    public boolean isBoolean() {
        return data instanceof Boolean;
    }
    
    public boolean isString() {
        return data instanceof String;
    }
    
    public boolean isNull() {
        return data == null;
    }

    // getterit
    public double getNumber(){
        return Double.parseDouble(data.toString());
    }
    
    public boolean getBoolean(){
        return Boolean.parseBoolean(data.toString());
    }
    
    public String getString() {
        return data.toString();
    }
    
    public Object getNull() {
        return data;
    }
    
}

