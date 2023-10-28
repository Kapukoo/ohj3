import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Kasper PC
 */
public class Median {

    public static void main(String[] args) {
        ArrayList<Double> numbers = new ArrayList<>();
        for ( var num : args ){
            numbers.add(Double.parseDouble(num));
        }
        Collections.sort(numbers);
        
        Double median;
        
        if ( numbers.size() % 2 == 0){
            int index = numbers.size() / 2 - 1;
            //System.out.println(index);   debug
            median = (numbers.get(index)+numbers.get(index+1))/2;
        }
        else {
            median = numbers.get(numbers.size()/2);
        }
        
        System.out.print("Median: " + median);
        
    }
}
