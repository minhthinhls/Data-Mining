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
public class Tag {

    private String userId;
    private String movieId;
    private String tag;
    private String timestamp;

    public Tag() {
        // Constructor
    }

    public Tag(String userId, String movieId, String tag, String timestamp) {
        this.userId = userId;
        this.movieId = movieId;
        this.tag = tag;
        this.timestamp = timestamp;
    }

    public Tag(String[] fields) {
        this.userId = fields[0];
        this.movieId = fields[1];
        this.tag = fields[2];
        this.timestamp = fields[3];
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
