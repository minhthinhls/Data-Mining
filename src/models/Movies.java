/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import interfaces.FileHandler;
import weka.core.Attribute;

/**
 *
 * @author Minh Thinh
 */
public class Movies implements FileHandler {

    private List<Movie> movies;

    /**
     *
     * @return
     */
    @Override
    public List<Movie> getList() {
        return movies;
    }

    /**
     *
     * @param toUrl
     * @throws IOException
     */
    @Override
    public void toArff(String toUrl) throws IOException {
        Attribute att = new Attribute("Id", true);
    }

    /**
     *
     * @param url
     * @return
     * @throws FileNotFoundException
     */
    @Override
    public Movies read(String url) throws FileNotFoundException {
        // Reading the file from parameter @url.
        BufferedReader br = new BufferedReader(new FileReader(url));
        movies = new ArrayList<>();
        try {
            String line;
            String[] fields;
            br.readLine();
            while ((line = br.readLine()) != null) {
                fields = line.replaceAll("NA", "?").replaceAll("\"\"", "\"?\"").split("\",\"");
                fields[0] = fields[0].replaceAll("\"", "");
                fields[fields.length - 1] = fields[fields.length - 1].replaceAll("\"", "");
                movies.add(new Movie(fields));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     *
     * @param url
     */
    public void write(String url) {
        // Writing the file to parameter @url.
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(url));

            for (Movie movie : movies) {
                bw.write(String.join(",", movie.toString()));
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
