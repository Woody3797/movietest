package test.revision.movies.service;

import java.io.StringReader;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import test.revision.movies.model.Movie;

@Service
public class MoviesService {
    

    public Movie getMovie(List<String> idsList) {

        String idsLists = String.join(",", idsList);
        String url = UriComponentsBuilder.fromUriString("https://moviesdatabase.p.rapidapi.com/titles/x/titles-by-ids").queryParam("idsList", idsLists).toUriString();

        RequestEntity<Void> request = RequestEntity.get(url).accept(MediaType.APPLICATION_JSON).header("X-RapidAPI-Key", "05dcc99f9cmshd4112ffe8d33df7p1ecde4jsn6d47dec2084d")
        .header("X-RapidAPI-Host", "moviesdatabase.p.rapidapi.com").build();

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.exchange(request, String.class);

        JsonReader reader = Json.createReader(new StringReader(response.getBody()));
        JsonObject jo = reader.readObject();
        Movie movie = Movie.create(jo);
        
        return movie;
    }
}
