package com.driver;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {
    List<Director> listDirector = new ArrayList<>();
    List<Movie> listMovie = new ArrayList<>();
    Map<Movie, Director> MovieDirDb = new HashMap();
    public String addMovie(Movie movie){
        if(listMovie.contains(movie)){
            return null;
        }
        listMovie.add(movie);
        return "success";
    }

    public String addDirector(Director director){
        if(listDirector.contains(director)){
            return null;
        }
        listDirector.add(director);
        return "success";
    }

    public String addMovieDirectorPair(Movie movie, Director director){
        if(MovieDirDb.containsKey(movie)){
            return null;
        }
        else if(!listMovie.contains(movie) || !listDirector.contains(director)){
            return null;
        }
        MovieDirDb.put(movie, director);
        return "success";
    }

    public Movie getMovieByName(String movieName){
        for(Movie movie : listMovie){
            if(movie.getName().equals(movieName)){
                return movie;
            }
        }
        return null;
    }

    public Director getDirectorByName(String directorName){
        for(Director director : listDirector){
            if(director.getName().equals(directorName)){
                return director;
            }
        }
        return null;
    }

    public List getMoviesByDirectorName(String directorName){
        List<Movie> movies = new ArrayList<>();
        for(Movie movie : MovieDirDb.keySet()){
            if(MovieDirDb.get(movie).equals(directorName)){
                movies.add(movie);
            }
        }
        return List.of(movies);
    }

    public List findAllMovies(){
        List<String> movies = new ArrayList<>();
        for(Movie movie : listMovie){
            movies.add(movie.getName());
        }
        return List.of(movies);
    }

    public String deleteDirectorByName(String directorName){
        Director directorToBeDeleted = null;
        for(Director director : listDirector){
            if(director.getName().equals(directorName)){
                directorToBeDeleted = director;
                listDirector.remove(director);
                break;
            }
        }
        if(directorToBeDeleted == null){
            return null;
        }
        for(Movie key : MovieDirDb.keySet()){
            if(MovieDirDb.get(key).equals(directorToBeDeleted)){
                Movie movieToBeDeleted = key;
                listMovie.remove(movieToBeDeleted);
                MovieDirDb.remove(key);
            }
        }
        return "success";
    }

    public String deleteAllDirectors(){
        if(listDirector.size() == 0){
            return null;
        }
        for(Movie movie : MovieDirDb.keySet()){
            if(listMovie.contains(movie)){
                listMovie.remove(movie);
            }
            MovieDirDb.remove(movie);
        }
        listDirector.clear();
        return "success";
    }
}
