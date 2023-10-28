import java.util.*;

public class ObjectNode extends Node implements Iterable<String> {
    
    private TreeMap<String, Node> map;
    private int currentSize;

    public ObjectNode() {
        this.map = new TreeMap<>();
    }

    public ObjectNode(TreeMap<String, Node> map) {
        this.map = map;
        this.currentSize = map.size();
    }
    
    
    
    @Override
    public Iterator<String> iterator() {
        Iterator<String> it = new Iterator<String>() {
            private int currentIndex =0 ;
            String[] keys = map.keySet().toArray(new String[map.keySet().size()]);
            
            @Override
            public boolean hasNext() {
                
                return currentIndex < currentSize && keys[currentIndex] != null;
            }
            
            @Override
            public String next() {
                return keys[currentIndex++];
            }
        };
        return it;
    }
    
    public Node get(String key) {
        if ( map.containsKey(key) ) {
            return map.get(key);
        }
        else {
            return null;
        }
    }
    
    public void set(String key, Node node) {
        map.put(key, node);
        currentSize += 1;
    }
    
    public int size() {
        return currentSize;
    }
    
}