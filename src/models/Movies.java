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
import java.io.File;
import java.util.Arrays;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;

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
    public void toArffs(String toUrl) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(toUrl));
        Filter valueFilter = new ReplaceMissingValues();
        try {
            Attribute movieId = new Attribute("movieId");
            Attribute title = new Attribute("title", true);
            Attribute releaseDate = new Attribute("releaseDate", true);
            Attribute directedBy = new Attribute("directedBy", true);
            Attribute starring = new Attribute("starring", true);
            Attribute imdbId = new Attribute("imdbId");
            Attribute tmdbId = new Attribute("tmdbId");
            Attribute genres = new Attribute("genres", true);

            List<Attribute> attributes = new ArrayList<>(Arrays.asList(movieId, title, releaseDate, directedBy, starring, imdbId, tmdbId, genres));

            // Create an empty training set
            Instances dataSet = new Instances("movies", (ArrayList<Attribute>) attributes, 0);
            Instance newInstance = new DenseInstance(8);
            for (Movie movie : movies) {
                newInstance.setValue(movieId, Double.valueOf(movie.getMovieId()));
                newInstance.setValue(title, movie.getTitle());
                newInstance.setValue(releaseDate, movie.getReleaseDate());
                newInstance.setValue(directedBy, movie.getDirectedBy());
                newInstance.setValue(starring, movie.getStarring());
                newInstance.setValue(imdbId, Double.valueOf(movie.getImdbId()));
                newInstance.setValue(tmdbId, Double.valueOf(movie.getTmdbId()));
                newInstance.setValue(genres, movie.getGenres());
                dataSet.add(newInstance);
            }
            bw.write(dataSet.toString());
            bw.flush();
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        } finally {
            bw.close();
        }
    }

    /**
     *
     * @param toUrl
     * @throws IOException
     */
    @Override
    @Deprecated
    public void toArff(String toUrl) throws IOException {
        Filter valueFilter = new ReplaceMissingValues();
        StringBuilder sb = new StringBuilder();
        CSVLoader loader = new CSVLoader();
        File tempFile = File.createTempFile("answers", ".csv");
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
        loader.setSource(tempFile); // May throw IOException !
        //loader.setEnclosureCharacters("\"");
        loader.setFieldSeparator(";");
        loader.setMissingValue("?");
        try {
            sb.append(String.join(";", Movie.getFieldsName())).append("\n");
            for (Movie movie : movies) {
                sb.append(String.join(";", movie.getFieldsValue())).append("\n");
            }
            bw.write(sb.toString()); // Write built string to temp file.
            bw.flush();
            Instances data = loader.getDataSet();
            valueFilter.setInputFormat(data);
            data = Filter.useFilter(data, valueFilter);
            ArffSaver as = new ArffSaver();
            as.setInstances(data);
            as.setFile(new File(toUrl));
            as.writeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bw.close();
        }
    }

    /**
     *
     * @param fromUrl
     * @return
     * @throws FileNotFoundException
     */
    @Override
    public Movies read(String fromUrl) throws FileNotFoundException {
        // Reading the file from parameter @fromUrl.
        BufferedReader br = new BufferedReader(new FileReader(fromUrl));
        movies = new ArrayList<>();
        try {
            String line;
            String[] fields;
            br.readLine(); // Skips the first line in csv file.
            while ((line = br.readLine()) != null) {
                fields = line.replaceAll("\"\"", "\"?\"").replaceAll("NA", "?").replaceAll("\'", " ").split("\",\"");
                //fields[0] = fields[0].replaceAll("\"", "");
                fields[0] = fields[0].substring(1);
                //fields[fields.length - 1] = fields[fields.length - 1].replaceAll("\"", "");
                int last = fields.length - 1;
                fields[last] = fields[last].substring(0, fields[last].length() - 1);
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
