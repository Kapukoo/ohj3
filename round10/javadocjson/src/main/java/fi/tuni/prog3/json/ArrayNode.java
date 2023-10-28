package fi.tuni.prog3.json;
import java.util.*;
/**
 * A class for representing a JSON array.
 */
public class ArrayNode extends Node implements Iterable<Node> {
    
    private Node[] arrayList;
    private int currentSize;
    
    /**
     * Constructs an initially empty JSON array node.
     */
    public ArrayNode() {
        this.arrayList = null;
        this.currentSize = 0;
    }
    
    /**
     * Returns the number of JSON nodes stored in this JSON array.
     * @return the number of JSON nodes in this JSON array.
     */
    public int size() {
        return currentSize;
    }
    
    /**
     * Adds a new JSON node to the end of this JSON array.
     * @param node the new JSON node to be added.
     */
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
    
    /**
     * Returns a Node iterator that iterates the JSON nodes stored in this JSON array.
     * @return a Node iterator that iterates the JSON nodes stored in this JSON array.
     */
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
}
