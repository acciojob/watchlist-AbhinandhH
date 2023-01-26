package com.driver;

public class Director {
    private int numberOfMovies;
    private String name;
    private double imdbRating;

    public Director(){}

    public Director(int numberOfMovies, String name, double imdbRating) {
        this.numberOfMovies = numberOfMovies;
        this.name = name;
        this.imdbRating = imdbRating;
    }

    public int getNumberOfMovies() {
        return numberOfMovies;
    }

    public void setNumberOfMovies(int numberOfMovies) {
        this.numberOfMovies = numberOfMovies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }
}
