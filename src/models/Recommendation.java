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
public class Recommendation {

    private String userId;
    private String movieId;

    public Recommendation() {
        // Constructor
    }

    public Recommendation(String userId, String movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }

    public Recommendation(String[] fields) {
        this.userId = fields[0];
        this.movieId = fields[1];
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

}
