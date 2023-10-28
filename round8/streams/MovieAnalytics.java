
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class MovieAnalytics {
    
    private ArrayList<Movie> movies;
    
    public MovieAnalytics() {
        movies = new ArrayList<>();
    }
    
    public static class Display implements Consumer<Movie> {
        @Override
        public void accept(Movie movie){
            System.out.println(String.format("%s (By %s, %d)",
                    movie.getTitle(),
                    movie.getDirector(),
                    movie.getReleaseYear()));  
        }

        @Override
        public Consumer<Movie> andThen(Consumer<? super Movie> after) {
            return Consumer.super.andThen(after); 
        }
    } 
    
    public static Consumer<Movie> showInfo() {
        
        //Consumer<Movie> display = (movie) -> System.out.println(" "+movie.getTitle());
        //Consumer<Movie> display = MovieAnalytics::print;
        return new Display();
        /*
        Consumer<Movie> display = new Consumer<Movie>() {
            
           @Override
            public void accept(Movie movie){
                
                System.out.println(String.format("%s (By %s, %d)\n",
                        movie.getTitle(),
                        movie.getDirector(),
                        movie.getReleaseYear()));  
            }
        };
        
        Consumer<Book> consumer = (book) -> System.out.println("Book id :"+book.getBookId());
        */
    }
    
    
    /*
    public static class Info1 implements Consumer<Movie> {
        @Override
        public void accept(Movie movie){
            String line = String.format("%s (By %s, %d)\n",
                    movie.getTitle(),
                    movie.getDirector(),
                    movie.getReleaseYear());
            System.out.println(line);  
        }
    }
    
    
    
    */
    
    
    
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
            //.collect(Collectors.groupingBy(Movie::getTitle));
            /*
            for ( var mv : movies ) {
                System.out.println(mv.getTitle());
            }
            */
            
            
            //System.out.println(movies.getTitle());
        }
    }
    
    private ArrayList<Movie> sorting(ArrayList<Movie> mvs) {
        mvs.sort((a,b)->{
            String as = a.getTitle();
            String bs = b.getTitle();
            int ai = a.getReleaseYear();
            int bi = b.getReleaseYear();
            
            int i = 0;
            if (ai > bi ) {
                i = 1;
            }
            if (ai < bi) {
                i = -1;
            }
            if (i == 0) {
                return as.compareTo(bs);
            }
            else {
                return i;
            }
        });
        return mvs;
    }
    
    public Stream<Movie> moviesAfter(int year) {
        ArrayList<Movie> mvs = new ArrayList<>();
        for (var mv : movies) {
            if ( mv.getReleaseYear() >= year ) {
                mvs.add(mv);
            }
        }
        mvs = sorting(mvs);
        
        Movie[] mvsA = new Movie[mvs.size()];
        mvsA = mvs.toArray(mvsA);
        //System.out.println(mvsA);
        return Arrays.stream(mvsA);
    }
    
    public Stream<Movie> moviesBefore(int year) {
        ArrayList<Movie> mvs = new ArrayList<>();
        for (var mv : movies) {
            if ( mv.getReleaseYear() <= year ) {
                mvs.add(mv);
            }
        }
        mvs = sorting(mvs);
        
        Movie[] mvsA = new Movie[mvs.size()];
        mvsA = mvs.toArray(mvsA);
        //System.out.println(mvsA);
        return Arrays.stream(mvsA);
    }
    
    public Stream<Movie> moviesBetween(int yearA, int yearB) {
        ArrayList<Movie> mvs = new ArrayList<>();
        for ( Movie mv : movies ) {
            if ( mv.getReleaseYear() >= yearA &&  mv.getReleaseYear() <= yearB ) {
                mvs.add(mv);
            }
        }
        mvs = sorting(mvs);
        
        Movie[] mvsA = new Movie[mvs.size()];
        mvsA = mvs.toArray(mvsA);
        //System.out.println(mvsA);
        return Arrays.stream(mvsA);       
    }
    
    public Stream<Movie> moviesByDirector(String director) {
        ArrayList<Movie> mvs = new ArrayList<>();
        for ( Movie mv : movies ) {
            if ( mv.getDirector().equals(director) ) {
                mvs.add(mv);
            }
        }
        mvs = sorting(mvs);
        
        Movie[] mvsA = new Movie[mvs.size()];
        mvsA = mvs.toArray(mvsA);
        //System.out.println(mvsA);
        return Arrays.stream(mvsA);
    }
    
}