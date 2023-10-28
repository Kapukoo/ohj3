package fi.tuni.prog3.jsoncountries;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class CountryData {
    
    public static List<Country> readFromJsons(String areaFile, 
            String populationFile, String gdpFile) throws FileNotFoundException {
        
        ArrayList<Country> data = new ArrayList<>();
        
        JsonArray records = JsonParser.parseReader(new JsonReader(new FileReader(areaFile)))
        .getAsJsonObject().get("Root")
        .getAsJsonObject().get("data")
        .getAsJsonObject().getAsJsonArray("record");
        
        int count = records.size();
        int i = 0;
        while ( i < count ) {
            
            // nimi
            JsonReader reader = new JsonReader(new FileReader(areaFile));
            JsonElement nameElement = JsonParser.parseReader(reader)
            .getAsJsonObject().get("Root")
            .getAsJsonObject().get("data")
            .getAsJsonObject().getAsJsonArray("record").get(i)
            .getAsJsonObject().getAsJsonArray("field").get(0)
            .getAsJsonObject().getAsJsonPrimitive("value");
            String name = nameElement.getAsString();
            
            // pinta-ala
            reader = new JsonReader(new FileReader(areaFile));
            JsonElement areaElement = JsonParser.parseReader(reader)
            .getAsJsonObject().get("Root")
            .getAsJsonObject().get("data")
            .getAsJsonObject().getAsJsonArray("record").get(i)
            .getAsJsonObject().getAsJsonArray("field").get(2)
            .getAsJsonObject().getAsJsonPrimitive("value");
            double area = areaElement.getAsDouble();
            
            // asukasluku
            reader = new JsonReader(new FileReader(populationFile));
            JsonElement popElement = JsonParser.parseReader(reader)
            .getAsJsonObject().get("Root")
            .getAsJsonObject().get("data")
            .getAsJsonObject().getAsJsonArray("record").get(i)
            .getAsJsonObject().getAsJsonArray("field").get(2)
            .getAsJsonObject().getAsJsonPrimitive("value");
            long population = popElement.getAsLong();
            
            // BKT
            reader = new JsonReader(new FileReader(gdpFile));
            JsonElement gdpElement = JsonParser.parseReader(reader)
            .getAsJsonObject().get("Root")
            .getAsJsonObject().get("data")
            .getAsJsonObject().getAsJsonArray("record").get(i)
            .getAsJsonObject().getAsJsonArray("field").get(2)
            .getAsJsonObject().getAsJsonPrimitive("value");
            double gdp = gdpElement.getAsDouble();
            
            Country c = new Country(name, area, population, gdp);
            data.add(c);
            
            i++;
        }
        
        return data;
    }
    
    public static void writeToJson(List<Country> countries, String countryFile) throws IOException {
        
        try ( Writer writer = new FileWriter(countryFile) ) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(countries, writer);
        }
        
    }
}