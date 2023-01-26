package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/movies/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        String response = movieService.addMovie(movie);
        if(response == null){
            return new ResponseEntity("Already added", HttpStatus.IM_USED);
        }
        return new ResponseEntity(response, HttpStatus.ACCEPTED);
    }

    @PostMapping("/movies/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        String response = movieService.addDirector(director);
        if(response == null){
            return new ResponseEntity("Already added", HttpStatus.IM_USED);
        }
        return new ResponseEntity(response, HttpStatus.ACCEPTED);
    }

    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("mov") Movie movie, @RequestParam("dir") Director director){
        String response = movieService.addMovieDirectorPair(movie, director);
        if(response == null){
            return new ResponseEntity("Not done", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String movieName){
        Movie response = movieService.getMovieByName(movieName);
        if(response == null){
            return new ResponseEntity("Not a valid entry", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(response, HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String directorName){
        Director response = movieService.getDirectorByName(directorName);
        if(response == null){
            return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(response, HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String directorName){
        List response = movieService.getMoviesByDirectorName(directorName);
        if(response == null){
            return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(response, HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-all-movies")
    public ResponseEntity findAllMovies(){
        List response = movieService.findAllMovies();
        if(response == null){
            return new ResponseEntity("The list is empty", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(response, HttpStatus.FOUND);
    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("dir") String directorName){
        String response = movieService.deleteDirectorByName(directorName);
        if(response == null){
            return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        String response = movieService.deleteAllDirectors();
        if(response == null){
            return new ResponseEntity("The list is empty", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
