package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {
    Map<String, Movie> movieDB = new HashMap<>();
    Map<String, Director> directorDB = new HashMap<>();
    Map<String, List<String>> pairDB = new HashMap<>();
    public String addMovie(Movie movie){
        String movieName = movie.getName();
        movieDB.put(movieName, movie);
        return "success";
    }
    public String addDirector(Director director){
        String directorName = director.getName();
        directorDB.put(directorName, director);
        return "success";
    }
    public String addMovieDirectorPair(String movieName, String directorName){
        if(!movieDB.containsKey(movieName) || !directorDB.containsKey(directorName)){
            return null;
        }
        if(!pairDB.containsKey(directorName)){
            List<String> movies = new ArrayList<>();
            movies.add(movieName);
            pairDB.put(directorName, movies);
        }else{
            pairDB.get(directorName).add(movieName);
        }
        return "success";
    }
    public Movie getMovieByName(String movieName){
        if(!movieDB.containsKey(movieName)){
            return null;
        }
        return movieDB.get(movieName);
    }
    public Director getDirectorByName(String directorName){
        if(!directorDB.containsKey(directorName)){
            return null;
        }
        return directorDB.get(directorName);
    }
    public List getMoviesByDirectorName(String directorName){
        if(!pairDB.containsKey(directorName)){
            return null;
        }
        return pairDB.get(directorName);
    }
    public List findAllMovies(){
        List<String> movies = new ArrayList<>();
        for(String name : movieDB.keySet()){
            movies.add(name);
        }
        return List.of(movies);
    }
    public String deleteDirectorByName(String directorName){
        if(!directorDB.containsKey(directorName)){
            return null;
        }
        if(pairDB.containsKey(directorName)){
            List<String> listHasToBeDeleted = pairDB.get(directorName);
            for(String movie : listHasToBeDeleted){
                if(movieDB.containsKey(movie)){
                    movieDB.remove(movie);
                }
            }
            pairDB.remove(directorName);
        }
        directorDB.remove(directorName);
        return "success";
    }
    public String deleteAllDirectors(){
        Set<String> mov = new HashSet<>();
        for(String director : pairDB.keySet()){
            for(String movie : pairDB.get(director)){
                mov.add(movie);
            }
            pairDB.remove(director);
        }
        for(String movie : mov){
            if(movieDB.containsKey(movie)){
                movieDB.remove(movie);
            }
        }
        directorDB.clear();
        return "success";
    }
}
