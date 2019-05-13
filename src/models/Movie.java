/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Minh Thinh
 */
public class Movie {

    private String movieId;
    private String title;
    private String releaseDate;
    private String directedBy;
    private String starring;
    private String imdbId;
    private String tmdbId;
    private String genres;

    public Movie() {
        // Constructor
    }

    public Movie(String movieId, String title, String releaseDate, String directedBy, String starring, String imdbId, String tmdbId, String genres) {
        this.movieId = movieId;
        this.title = title;
        this.releaseDate = releaseDate;
        this.directedBy = directedBy;
        this.starring = starring;
        this.imdbId = imdbId;
        this.tmdbId = tmdbId;
        this.genres = genres;
    }

    public Movie(String[] fields) {
        this.movieId = fields[0];
        this.title = fields[1];
        this.releaseDate = fields[2];
        this.directedBy = fields[3];
        this.starring = fields[4];
        this.imdbId = fields[5];
        this.tmdbId = fields[6];
        this.genres = fields[7];
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDirectedBy() {
        return directedBy;
    }

    public void setDirectedBy(String directedBy) {
        this.directedBy = directedBy;
    }

    public String getStarring() {
        return starring;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(String tmdbId) {
        this.tmdbId = tmdbId;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

}
