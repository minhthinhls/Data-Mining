/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import interfaces.Model;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Minh Thinh
 */
public class Recommendation implements Model {

    private List<String> allFieldsValue;
    private String userId;
    private String movieId;

    @Deprecated
    public Recommendation() {
        // Constructor
    }

    @Deprecated
    public Recommendation(String userId, String movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }

    public Recommendation(String[] fields) {
        this.allFieldsValue = Arrays.asList(fields);
        this.userId = fields[0];
        this.movieId = fields[1];
    }

    /**
     * Get the name of all String fields into String[].
     *
     * @return List<>
     */
    public static List getFieldsName() {
        Field[] fields = Recommendation.class.getDeclaredFields();
        List<String> allFieldsName = Stream.of(fields).filter(field -> {
            return field.getType().equals(String.class);
        }).map(field -> field.getName()).collect(Collectors.toList());
        return allFieldsName;
    }

    @Override
    public List getFieldsValue() {
        return allFieldsValue;
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
