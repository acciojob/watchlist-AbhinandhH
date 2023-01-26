package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public String addMovie(Movie movie){
        String response = movieRepository.addMovie(movie);
        return response;
    }
    public String addDirector(Director director){
        String response = movieRepository.addDirector(director);
        return response;
    }
    public String addMovieDirectorPair(Movie movie, Director director){
        String response = movieRepository.addMovieDirectorPair(movie, director);
        return response;
    }
    public Movie getMovieByName(String movieName){
        Movie response = movieRepository.getMovieByName(movieName);
        return response;
    }
    public Director getDirectorByName(String directorName){
        Director response = movieRepository.getDirectorByName(directorName);
        return response;
    }
    public List getMoviesByDirectorName(String directorName){
        List response = movieRepository.getMoviesByDirectorName(directorName);
        return response;
    }
    public List findAllMovies(){
        List response = movieRepository.findAllMovies();
        return response;
    }
    public String deleteDirectorByName(String directorName){
        String response = movieRepository.deleteDirectorByName(directorName);
        return response;
    }
    public String deleteAllDirectors(){
        String response = movieRepository.deleteAllDirectors();
        return response;
    }
}
