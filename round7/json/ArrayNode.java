import java.util.*;

public class ArrayNode extends Node implements Iterable<Node> {
    
    private Node[] arrayList;
    private int currentSize;
    
    public ArrayNode(Node[] newArray) {
        this.arrayList = newArray;
        this.currentSize = arrayList.length;
    }
    
    public ArrayNode() {
        this.arrayList = null;
        this.currentSize = 0;
    }
    
    
    @Override
    public Iterator<Node> iterator() {
        Iterator<Node> it = new Iterator<Node>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < currentSize && arrayList[currentIndex] != null;
            }

            @Override
            public Node next() {
                return arrayList[currentIndex++];
            }
        };
        return it;
    }
    
    //private int size = 0;
    //private ArrayList<Node> list;
    
    public void add(Node node){
        int s = currentSize;
      Node newarr[] = new Node[s+1];
      for (int i = 0; i<s; i++) {
          newarr[i] = arrayList[i];
      }
      newarr[s] = node;
      arrayList = newarr;
      currentSize += 1;
    }
    
    public int size() {
        return currentSize;
    }
    
}
