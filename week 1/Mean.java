/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Kasper Kivistö
 * kasper.kivisto@tuni.fi
 * 50302871
 * 
 */
public class Mean {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        double mean = 0;
        int count = 0;
        
        for (String s : args ) {
            
            double value = Double.parseDouble(s);
            mean += value;
            count += 1;
            
        }
        
        mean = mean / count;
        
        System.out.print("Mean: " +  mean);
        
        
    }
}
