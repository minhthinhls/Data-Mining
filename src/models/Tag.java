/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import interfaces.Model;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Minh Thinh
 */
public class Tag implements Model {

    private List<String> allFieldsValue;
    private String userId;
    private String movieId;
    private String tag;
    private String timestamp;
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Deprecated
    public Tag() {
        // Constructor
    }

    @Deprecated
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
        //Date date = new Date(Long.parseLong(fields[3]));
        //this.timestamp = fields[3] = SDF.format(date);
        this.allFieldsValue = Arrays.asList(fields);
    }

    /**
     * Get the name of all String fields into String[].
     *
     * @return List<>
     */
    public static List getFieldsName() {
        Field[] fields = Tag.class.getDeclaredFields();
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
