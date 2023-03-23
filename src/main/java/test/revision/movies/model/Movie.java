package test.revision.movies.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Movie {
    
    private Integer entries;
    private List<Result> results = new ArrayList<>();

    public Movie(Integer entries, List<Result> results) {
        this.entries = entries;
        this.results = results;
    }

    public Movie() {
    }

    public Integer getEntries() {
        return entries;
    }
    public void setEntries(Integer entries) {
        this.entries = entries;
    }
    public List<Result> getResults() {
        return results;
    }
    public void setResults(List<Result> results) {
        this.results = results;
    }


    public static Movie create(JsonObject jo) {
        Movie movie = new Movie();
        List<Result> results = new ArrayList<>();
        movie.setEntries(jo.getInt("entries"));
        JsonArray resultsArr = jo.getJsonArray("results");
        for (int i = 0; i < resultsArr.size(); i++) {
            String id = resultsArr.getJsonObject(i).getString("id");
            String title = resultsArr.getJsonObject(0).getJsonObject("titleText").getString("text");
            Integer releaseYear = resultsArr.getJsonObject(0).getJsonObject("releaseYear").getInt("year");
            Result result = new Result(id, title, releaseYear);
            results.add(result);
            movie.setResults(results);
        }

        return movie;
    }

    public JsonObject toJSON(){
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        JsonObjectBuilder resultBuilder = Json.createObjectBuilder();
        builder.add("entries", this.entries);
        for (int i = 0; i < this.results.size(); i++) {
            resultBuilder.add("id", this.results.get(i).getId())
            .add("title", this.results.get(i).getTitle())
            .add("year", this.results.get(i).getReleaseYear());
            arrBuilder.add(resultBuilder);
            
        }
        builder.add("results", arrBuilder);
        return builder.build();
    }
    
}
