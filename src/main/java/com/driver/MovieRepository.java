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
        if(movieDB.containsKey(movieName)) return null;
        movieDB.put(movieName, movie);
        return "Movie added to the list";
    }
    public String addDirector(Director director){
        String directorName = director.getName();
        if(directorDB.containsKey(directorName)) return null;
        directorDB.put(directorName, director);
        return "Director added to the list";
    }
    public String addMovieDirectorPair(String movieName, String directorName){
        if(movieDB.containsKey(movieName) && directorDB.containsKey(directorName)){
            if(!pairDB.containsKey(directorName)){
                pairDB.put(directorName, new ArrayList<>());
                pairDB.get(directorName).add(movieName);
            }else{
                pairDB.get(directorName).add(movieName);
            }
            return "Movie - Director pair created";
        }
        return null;
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
    public List<String> getMoviesByDirectorName(String directorName){
        if(!pairDB.containsKey(directorName)){
            return null;
        }
        return pairDB.get(directorName);
    }
    public List<String> findAllMovies(){
        return new ArrayList<>(movieDB.keySet());
    }
    public String deleteDirectorByName(String directorName){
        if(pairDB.containsKey(directorName)){
            List<String> listHasToBeDeleted = pairDB.get(directorName);
            for(String movie : listHasToBeDeleted){
                if(movieDB.containsKey(movie)){
                    movieDB.remove(movie);
                }
            }
            pairDB.remove(directorName);
            directorDB.remove(directorName);
            return "Deleted successfully";
        }
        return null;
    }
    public String deleteAllDirectors(){
        Set<String> mov = new HashSet<>();
        for(String director : pairDB.keySet()){
            for(String movie : pairDB.get(director)){
                mov.add(movie);
            }
        }
        for(String movie : mov){
            if(movieDB.containsKey(movie)){
                movieDB.remove(movie);
            }
            mov.remove(movie);
        }
        pairDB.clear();
        directorDB.clear();
        return "Deletion successful";
    }
}
