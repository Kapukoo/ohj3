


import java.util.ArrayList;
public class Sudoku {
    
//private ArrayList<ArrayList<Character>> panel = new ArrayList<>();
private char[][] panel = {
        {' ',' ',' ' , ' ',' ',' ' , ' ',' ',' '},
        {' ',' ',' ' , ' ',' ',' ' , ' ',' ',' '},
        {' ',' ',' ' , ' ',' ',' ' , ' ',' ',' '},
        {' ',' ',' ' , ' ',' ',' ' , ' ',' ',' '},
        {' ',' ',' ' , ' ',' ',' ' , ' ',' ',' '},
        {' ',' ',' ' , ' ',' ',' ' , ' ',' ',' '},
        {' ',' ',' ' , ' ',' ',' ' , ' ',' ',' '},
        {' ',' ',' ' , ' ',' ',' ' , ' ',' ',' '},
        {' ',' ',' ' , ' ',' ',' ' , ' ',' ',' '},
        {' ',' ',' ' , ' ',' ',' ' , ' ',' ',' '},
};
    
    public void set(int i, int j, char c){
        
        if ( i<0 || i>8 || j<0 || j>8 ){
            System.out.println("Trying to access illegal cell ("+i+", "+j+")!");
        }
        else if (c != ' ' && c != '1' && c != '2' && c != '3' && c != '4' &&
                c != '5' && c != '6' && c != '7' && c != '8' && c != '9'){
            System.out.println("Trying to set illegal character "+c+" to ("+i+", "+j+")!");
        }
        else {
            panel[i][j] = c;
        }
    }
    
    public void print(){
        
        System.out.println("#####################################");
        
        for ( int i = 0; i < 9; i++){
            System.out.print("#");
            
            for ( int j = 0; j < 9; j++){
                System.out.print(" " + panel[i][j] + " ");
                
                if ( j == 2 || j == 5 || j == 8){
                    System.out.print("#");
                }
                else {
                    System.out.print("|");
                }
            }
            
            System.out.println();
            if ( i == 2 || i == 5 || i == 8){
                    System.out.println("#####################################");
            }
            else {
                System.out.println("#---+---+---#---+---+---#---+---+---#");
                
            }
        }
        
    }
    public boolean check(){
        
        // panel[y][x]
        // array to check rows and columns
        ArrayList<Character> found = new ArrayList<>();
        
        // rows check 
        for ( int j = 0; j < 9; j++){
            found.clear();
            for ( int i = 0; i < 9; i++){
                
                if ( found.contains(panel[j][i])){
                    System.out.println("Row "+j+" has multiple "+panel[j][i]+"'s!");
                    return false;
                }
                else if (panel[j][i] != ' ') {
                    //System.out.println(panel[j][i]);
                    found.add(panel[j][i]);
                }
               
            }
        }
        
        // columns check
        for ( int j = 0; j < 9; j++){
            found.clear();
            for ( int i = 0; i < 9; i++){
                
                if ( found.contains(panel[i][j])){
                    System.out.println("Column "+j+" has multiple "+panel[i][j]+"'s!");
                    return false;
                }
                else if (panel[i][j] != ' ') {
                    //System.out.println(panel[i][j]);
                    found.add(panel[i][j]);
                }
            }
        }
        
        // box check
        int x = 0;
        int y = 0;
        
        while ( true ){
            
            found.clear();
            for ( int i = y; i < y+3; i++){
                for ( int j = x; j<x+3; j++ ){
                    
                    if ( found.contains(panel[i][j]) ){
                        System.out.println("Block at ("+y+", "+x+") has multiple "+panel[i][j]+"'s!");
                        return false;
                    }
                    else if (panel[i][j] != ' ') {
                        //System.out.print(panel[i][j]);
                        found.add(panel[i][j]);
                    } 
                    
                }
            }
            
            if ( x >= 6){
                x = 0;
                y += 3;
            }
            else {
                x += 3;
            }
            if ( y > 6){
                break;
            }
            
        }
        
        return true;
    }
}
