package fi.tuni.prog3.sevenzipsearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;


public class Sevenzipsearch {

    public static void main(String[] args) throws IOException {
       String fileName = args[0];
       String searchWord = args[1];
       searchWord = searchWord.toUpperCase();
       
       try(SevenZFile zf = new SevenZFile( new File(fileName))) {
            SevenZArchiveEntry entry = zf.getNextEntry();
            while ( entry != null ) {
                String name = entry.getName();
                if ( name.length() >= 4 ) {
                    if ( name.substring(name.length() - 4).equals(".txt")) {
                        // homma pletaa -> open sesame

                        // nimi
                        System.out.println(entry.getName());
                        
                        
                        // luetaan rivi kerrallaan
                        try ( BufferedReader bf = new BufferedReader (new InputStreamReader(zf.getInputStream(entry)))) {
                            String line = null;
                            int column = 1;
                            
                            // niin kauan kun rivi ei oo tyhjä
                            while( (line = bf.readLine()) != null ) {
                                String newLine = "";
                                char[] chars = line.toCharArray();
                                
                                for ( int i = 0; i < chars.length - searchWord.length()+1; i++ ) {
                                    String check = new String(chars,i,searchWord.length());
                                    if ( check.toUpperCase().equals(searchWord) ) {
                                        
                                        for ( int j = 0; j < searchWord.length(); j++) {
                                            chars[i+j] = searchWord.charAt(j);
                                        }
                                        newLine = new String(chars);
                                    }
                                }
                                if ( !newLine.equals("")){
                                    System.out.println(column+": "+newLine);
                                }
                                
                                column += 1;
                            }
                        }
                        System.out.println();
                    }
                }
                entry = zf.getNextEntry();
            }
       }
    }
}
