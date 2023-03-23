package test.revision.movies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import test.revision.movies.model.Movie;
import test.revision.movies.service.MoviesService;

@RestController
@RequestMapping
public class MovieController {

    @Autowired
    MoviesService service;
    
    @GetMapping(path = "/")
    public String getMovie(Model model, @RequestParam(required = true) List<String> idsList) {
        Movie movie = service.getMovie(idsList);
        model.addAttribute("movie", movie);
        return "view1";
    }

    @GetMapping(path = "/test/")
    public ResponseEntity<String> giveMovie(@RequestParam List<String> idsList) {
        Movie movie = service.getMovie(idsList);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
        .contentType(MediaType.APPLICATION_JSON)
        .body(movie.toJSON().toString());
    }
}
