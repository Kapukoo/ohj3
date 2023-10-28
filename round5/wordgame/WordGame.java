import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class WordGame {
    // se vaadittu sisäluokka
    public static class WordGameState {
            
        private String word;
        
        private int mistakes;
        private final int mistakeLimit;
        private int missingChars;

        private WordGameState(String word, int mistakes, 
                int mistakeLimit, int missingChars) {
            this.word = word;
            
            this.mistakes = mistakes;
            this.mistakeLimit = mistakeLimit;
            this.missingChars = missingChars;
        }
        
        public String getWord() {
            return this.word;
        }

        public int getMistakes() {
            return this.mistakes;
        }

        public int getMistakeLimit() {
            return this.mistakeLimit;
        }

        public int getMissingChars() {
            return this.missingChars;
        }   
    } // sisäluokka päättyy
    
    private ArrayList<String> words = new ArrayList<>();
    private WordGameState gameCurrent = null;
    private String realWord;
    
    // ite conssi
    public  WordGame(String wordFileName) throws FileNotFoundException, IOException {
        try(var wordFile = new BufferedReader(new FileReader(wordFileName))) {
            String word;
            while ( (word = wordFile.readLine()) != null ){
                //System.out.println(word);
                words.add(word);
            }
        }
        /*
        for ( var word : words ){
            System.out.println("Testi printti");
            System.out.println(word);
        }*/
    }
    
    public void initGame (int wordIndex, int mistakeLimit) {
        int N = words.size();
        int gameIndex = wordIndex % N;
        String word = words.get(gameIndex);
        realWord = word;
        int misChars = word.length();
        // varmista tämä alta 
        String wordState = "_".repeat(misChars);
        //System.out.println("testi  : "+wordState);
        
        gameCurrent = new WordGameState(wordState,
                0, mistakeLimit, misChars ); 
    }
    
    public boolean isGameActive() {
        
        if ( gameCurrent == null ){
            return false;
        }
        
        // haetaan tiedot
        String currentWord = gameCurrent.getWord();
        int misLimit = gameCurrent.getMistakeLimit();
        int missingChars = gameCurrent.getMissingChars();
        int curMistakes = gameCurrent.getMistakes();
        
        if ( curMistakes > misLimit || missingChars == 0  ) {
            gameCurrent = new WordGameState(realWord,
                        curMistakes, misLimit, missingChars );
            return false;
        }
        return true;
    }
    
    public WordGameState getGameState() throws GameStateException {
        if ( gameCurrent == null ){
            throw new GameStateException("There is currently no active word game!");
        }
        return this.gameCurrent;
    }
    
    public WordGameState guess(char c) throws GameStateException {
        if ( !isGameActive() ) {
            throw new GameStateException("There is currently no active word game!");
        }
        
        // haetaan tiedot
        String currentWord = gameCurrent.getWord();
        int misLimit = gameCurrent.getMistakeLimit();
        int missingChars = gameCurrent.getMissingChars();
        int curMistakes = gameCurrent.getMistakes();
        
        c = Character.toLowerCase(c);
        
        // onko jo c jo arvattu
        for ( char x : currentWord.toCharArray() ){
            if ( x == c ) {
                //System.out.println("alredy found");
                gameCurrent = new WordGameState(currentWord,
                        curMistakes+1, misLimit, missingChars );
                
                return this.gameCurrent;
            }
        }
        
        // ei oltu arvattu
        char[] newWord = currentWord.toCharArray();
        //System.out.println("test  "+Arrays.toString(newWord));
        int found = 0;
        for ( int i=0; i<realWord.length(); i++ ){
            if ( realWord.charAt(i) == c ){
                // löytyy -> uusi gameState
                missingChars = missingChars - 1;
                newWord[i] = c;
                found += 1;
            }           
        }
        
        // löydettiinkö mitään
        if ( found == 0 ){
            // not found -> more error
            curMistakes += 1;
            gameCurrent = new WordGameState(currentWord,
                        curMistakes, misLimit, missingChars );
        }
        else {
            //System.out.println("test  "+String.valueOf(newWord));
            gameCurrent = new WordGameState(String.valueOf(newWord),
                        curMistakes, misLimit, missingChars );
        }
        if ( curMistakes > misLimit ){
            gameCurrent = new WordGameState(realWord,
                        curMistakes, misLimit, missingChars );
        }
        
        return this.gameCurrent;
    }
    
    
    public WordGameState guess(String word) throws GameStateException {
        if ( !isGameActive() ) {
            throw new GameStateException("There is currently no active word game!");
        }
        
        // haetaan tiedot
        String currentWord = gameCurrent.getWord();
        int misLimit = gameCurrent.getMistakeLimit();
        int missingChars = gameCurrent.getMissingChars();
        int curMistakes = gameCurrent.getMistakes();
        
        // sana oikein -> voitto 
        if ( realWord.equals(word) ){
            gameCurrent = new WordGameState(realWord,
                        curMistakes, misLimit, 0 );
        }
        // sana väärin -> virhe
        else {
            curMistakes += 1;
            gameCurrent = new WordGameState(currentWord,
                        curMistakes, misLimit, missingChars );
        }
        
        if ( curMistakes > misLimit ){
            gameCurrent = new WordGameState(realWord,
                        curMistakes, misLimit, missingChars );
        }
        
        return this.gameCurrent;
    }
    
}