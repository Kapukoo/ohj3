import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Parameters {

    public static void main(String[] args) {
        ArrayList<String> params= new ArrayList<>();
        
        int longest = 0;
        int size = 0;
        
        for ( var i : args ){
            params.add(i);
            size += 1;
            if ( i.length() > longest ) {
                longest = i.length();
            }
        }
        
        Collections.sort(params);
        
        String size_string = String.valueOf(size);
        int size_chars = size_string.length();
        
        // print start and stop
        char[] chars = new char[longest+size_chars+2+2+3];
        Arrays.fill(chars, '#');
        String top_line = new String(chars);
        
        // lines between 
        char[] between1 = new char[longest+2];
        Arrays.fill(between1, '-');
        
        char[] between2 = new char[size_chars+2];
        Arrays.fill(between2, '-');
        
        String betweens1 = new String(between1);
        String betweens2 = new String(between2);
        String between = "#" + betweens2 + "+" + betweens1 + "#";
        
        
        
        
        // print
        System.out.println(top_line);
        int line = 1;
        
        for ( var x : params ){
            System.out.format("# %" + size_chars + "d | %-" + longest + "s #%n",line,x);
                line += 1;
                if ( line > size ){
                    System.out.println(top_line);
                    return;
                }
                System.out.println(between);
        }
        
        
    }
}
