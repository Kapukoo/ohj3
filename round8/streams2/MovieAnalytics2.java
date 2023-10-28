
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Stream;

public class MovieAnalytics2 {
    
    ArrayList<Movie> movies;

    public MovieAnalytics2() {
        movies = new ArrayList<Movie>();
    }
    
    private void addition(String title, int releaseYear, int duration, 
                            String genre, double score, String director) {
        
        Movie mv = new Movie(title, releaseYear, duration, 
                                genre, score, director);
        movies.add(mv);
    }
    
    public void populateWithData(String fileName) throws IOException {
        try(var br = new BufferedReader(new FileReader(fileName))) {
            
            br.lines()
            .map(line -> line.split(";"))
            .forEach(mv -> addition(mv[0],Integer.parseInt(mv[1]),
                                Integer.parseInt(mv[2]),mv[3],
                                Double.parseDouble(mv[4]),mv[5]));
        }
    }
    
    public void printCountByDirector(int n) {
        
        // tavarat mappiin
        Map<String, Integer> dirMovies = new HashMap<>();
        movies.stream().forEach(mv -> {
            
            String dir = mv.getDirector();
            int count = dirMovies.containsKey(dir) ? dirMovies.get(dir) : 0;
            dirMovies.put(dir, count + 1);
        });
        
        // mappi streaamiin
        Set<Map.Entry<String, Integer>> entries = dirMovies.entrySet();
        Stream<Map.Entry<String, Integer>> entryStream = entries.stream();
        
        // sorttaillaan ja tulostellaan n kpl
        entryStream.sorted((a,b) -> {
            String aName = a.getKey();
            String bName = b.getKey();
            int aCount = a.getValue();
            int bCount = b.getValue();
            
            int i = 0;
            if (aCount < bCount) i = 1;
            if (aCount > bCount) i = -1;
            if (i == 0) return aName.compareTo(bName);
            else return i;
        })
        .limit(n)
        .forEach(dir -> {
            System.out.println(
                String.format("%s: %d movies",
                    dir.getKey(), 
                    dir.getValue()));
        });
    }
    
    private double average(ArrayList<Integer> ls) {
        
        OptionalDouble avg = ls.stream()
                .mapToDouble(a -> a)
                .average();
        
        return avg.isPresent() ? avg.getAsDouble() : 0; 
    }
    
    private double averageDouble(ArrayList<Double> ls) {
        
        OptionalDouble avg = ls.stream()
                .mapToDouble(a -> a)
                .average();
        
        return avg.isPresent() ? avg.getAsDouble() : 0; 
    }
    
    public void printAverageDurationByGenre() {
        
        // tavarat mappiin
        Map<String, ArrayList<Integer>> genreDurat = new HashMap<>();
        movies.stream().forEach(mv -> {
            
            String genr = mv.getGenre();
            if ( genreDurat.containsKey(genr)) {
                genreDurat.get(genr).add(mv.getDuration());
            }
            else {
                genreDurat
                .computeIfAbsent(genr, k -> new ArrayList<>())
                .add(mv.getDuration());
            }
        });
        
        // entryks
        Set<Map.Entry<String, ArrayList<Integer>>> entries = genreDurat.entrySet();
        Stream<Map.Entry<String, ArrayList<Integer>>> entryStream = entries.stream();
        
        
        // jaa sitte  taas sorttaillaan ja tulostellaan
        entryStream.sorted((a,b) -> {
            String aGenr = a.getKey();
            String bGenr = b.getKey();
            double aAvg = average(a.getValue());
            double bAvg = average(b.getValue());
            
            int i = 0;
            if (aAvg > bAvg) i = 1;
            if (aAvg < bAvg) i = -1;
            if (i == 0) return aGenr.compareTo(bGenr);
            else return i;
        })
        .forEach(genr -> {
            System.out.println(
            String.format("%s: %.2f",
                    genr.getKey(), 
                    average(genr.getValue()))
            );
        });
    }
    
    public void printAverageScoreByGenre() {
        
        // tavarat mappiin
        Map<String, ArrayList<Double>> genreScore = new HashMap<>();
        movies.stream().forEach(mv -> {
            
            String genr = mv.getGenre();
            if ( genreScore.containsKey(genr)) {
                genreScore.get(genr).add(mv.getScore());
            }
            else {
                genreScore
                .computeIfAbsent(genr, k -> new ArrayList<>())
                .add(mv.getScore());
            }
        });
        
        // entryks
        Set<Map.Entry<String, ArrayList<Double>>> entries = genreScore.entrySet();
        Stream<Map.Entry<String, ArrayList<Double>>> entryStream = entries.stream();
        
        
        // jaa sitte  taas sorttaillaan ja tulostellaan
        entryStream.sorted((a,b) -> {
            String aGenr = a.getKey();
            String bGenr = b.getKey();
            double aScore = averageDouble(a.getValue());
            double bScore = averageDouble(b.getValue());
            
            int i = 0;
            if (aScore < bScore) i = 1;
            if (aScore > bScore) i = -1;
            if (i == 0) return aGenr.compareTo(bGenr);
            else return i;
        })
        .forEach(genr -> {
            System.out.println(
            String.format("%s: %.2f",
                    genr.getKey(), 
                    averageDouble(genr.getValue()))
            );
        });
    }
    
}