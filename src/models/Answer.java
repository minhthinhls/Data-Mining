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
public class Answer {

    private String userId;
    private String movieId;
    private String rating;
    private String timestamp;
    private String predictedRating;
    private String s1;
    private String s2;
    private String s3;
    private String s4;
    private String s5;
    private String s6;
    private String s7;
    private String s8;
    private String q;
    private String s_ser_rel;
    private String s_ser_find;
    private String s_ser_imp;
    private String s_ser_rec;
    private String m_ser_rel;
    private String m_ser_find;
    private String m_ser_imp;
    private String m_ser_rec;

    public Answer() {
        // Constructor
    }

    public Answer(String userId, String movieId, String rating, String timestamp, String predictedRating, String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String q, String s_ser_rel, String s_ser_find, String s_ser_imp, String s_ser_rec, String m_ser_rel, String m_ser_find, String m_ser_imp, String m_ser_rec) {
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.timestamp = timestamp;
        this.predictedRating = predictedRating;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
        this.s6 = s6;
        this.s7 = s7;
        this.s8 = s8;
        this.q = q;
        this.s_ser_rel = s_ser_rel;
        this.s_ser_find = s_ser_find;
        this.s_ser_imp = s_ser_imp;
        this.s_ser_rec = s_ser_rec;
        this.m_ser_rel = m_ser_rel;
        this.m_ser_find = m_ser_find;
        this.m_ser_imp = m_ser_imp;
        this.m_ser_rec = m_ser_rec;
    }

    public Answer(String[] fields) {
        this.userId = fields[0];
        this.movieId = fields[1];
        this.rating = fields[2];
        this.timestamp = fields[3];
        this.predictedRating = fields[4];
        this.s1 = fields[5];
        this.s2 = fields[6];
        this.s3 = fields[7];
        this.s4 = fields[8];
        this.s5 = fields[9];
        this.s6 = fields[10];
        this.s7 = fields[11];
        this.s8 = fields[12];
        this.q = fields[13];
        this.s_ser_rel = fields[14];
        this.s_ser_find = fields[15];
        this.s_ser_imp = fields[16];
        this.s_ser_rec = fields[17];
        this.m_ser_rel = fields[18];
        this.m_ser_find = fields[19];
        this.m_ser_imp = fields[20];
        this.m_ser_rec = fields[21];
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPredictedRating() {
        return predictedRating;
    }

    public void setPredictedRating(String predictedRating) {
        this.predictedRating = predictedRating;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public String getS3() {
        return s3;
    }

    public void setS3(String s3) {
        this.s3 = s3;
    }

    public String getS4() {
        return s4;
    }

    public void setS4(String s4) {
        this.s4 = s4;
    }

    public String getS5() {
        return s5;
    }

    public void setS5(String s5) {
        this.s5 = s5;
    }

    public String getS6() {
        return s6;
    }

    public void setS6(String s6) {
        this.s6 = s6;
    }

    public String getS7() {
        return s7;
    }

    public void setS7(String s7) {
        this.s7 = s7;
    }

    public String getS8() {
        return s8;
    }

    public void setS8(String s8) {
        this.s8 = s8;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getS_ser_rel() {
        return s_ser_rel;
    }

    public void setS_ser_rel(String s_ser_rel) {
        this.s_ser_rel = s_ser_rel;
    }

    public String getS_ser_find() {
        return s_ser_find;
    }

    public void setS_ser_find(String s_ser_find) {
        this.s_ser_find = s_ser_find;
    }

    public String getS_ser_imp() {
        return s_ser_imp;
    }

    public void setS_ser_imp(String s_ser_imp) {
        this.s_ser_imp = s_ser_imp;
    }

    public String getS_ser_rec() {
        return s_ser_rec;
    }

    public void setS_ser_rec(String s_ser_rec) {
        this.s_ser_rec = s_ser_rec;
    }

    public String getM_ser_rel() {
        return m_ser_rel;
    }

    public void setM_ser_rel(String m_ser_rel) {
        this.m_ser_rel = m_ser_rel;
    }

    public String getM_ser_find() {
        return m_ser_find;
    }

    public void setM_ser_find(String m_ser_find) {
        this.m_ser_find = m_ser_find;
    }

    public String getM_ser_imp() {
        return m_ser_imp;
    }

    public void setM_ser_imp(String m_ser_imp) {
        this.m_ser_imp = m_ser_imp;
    }

    public String getM_ser_rec() {
        return m_ser_rec;
    }

    public void setM_ser_rec(String m_ser_rec) {
        this.m_ser_rec = m_ser_rec;
    }

}
