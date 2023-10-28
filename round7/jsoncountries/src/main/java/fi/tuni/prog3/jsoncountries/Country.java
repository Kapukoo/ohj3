package fi.tuni.prog3.jsoncountries;

public class Country implements Comparable<Country>{
    private final String name;
    private final double area;
    private final long population;
    private final double gdp;

    public Country(String name, double area, long population, double gdp) {
        this.name = name;
        this.area = area;
        this.population = population;
        this.gdp = gdp;
    }
    
    

    public String getName() {
        return name;
    }

    public double getArea() {
        return area;
    }

    public long getPopulation() {
        return population;
    }

    public double getGdp() {
        return gdp;
    }

    @Override
    public String toString() {
        return String.format("""
                             %s
                               Area: %.1f km2
                               Population: %d
                               GDP: %.1f (2015 USD) 
                             """,
                getName(), getArea()
                , getPopulation(), getGdp());
    }
    
    @Override
    public int compareTo(Country o) {
        return this.getName().compareTo(o.getName());
    }

    
    
    
}