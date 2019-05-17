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
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import interfaces.FileHandler;
import java.util.Arrays;
import weka.core.DenseInstance;

/**
 *
 * @author Minh Thinh
 */
public class Recommendations implements FileHandler {

    private List<Recommendation> recommendations;

    /**
     *
     * @return
     */
    @Override
    public List<Recommendation> getList() {
        return recommendations;
    }

    /**
     *
     * @param toUrl
     * @throws IOException
     */
    @Override
    public void toArff(String toUrl) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(toUrl));
        try {
            // Declare two numeric attributes
            Attribute userId = new Attribute("userId");
            Attribute movieId = new Attribute("movieId");

            List<Attribute> attributes = new ArrayList<>(Arrays.asList(userId, movieId));

            // Create an empty training set
            Instances dataSet = new Instances("recommendations", (ArrayList<Attribute>) attributes, 0);
            Instance newInstance = new DenseInstance(2);
            for (Recommendation recommendation : recommendations) {
                newInstance.setValue(userId, Double.valueOf(recommendation.getUserId()));
                newInstance.setValue(movieId, Double.valueOf(recommendation.getMovieId()));
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
     * @param fromUrl
     * @return
     * @throws FileNotFoundException
     */
    @Override
    public Recommendations read(String fromUrl) throws FileNotFoundException {
        // Reading the file from parameter @fromUrl.
        BufferedReader br = new BufferedReader(new FileReader(fromUrl));
        recommendations = new ArrayList<>();
        try {
            String line;
            String[] fields;
            br.readLine(); // Skips the first line in csv file.
            while ((line = br.readLine()) != null) {
                fields = line.replaceAll(" ", "").split(",");
                recommendations.add(new Recommendation(fields));
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

            for (Recommendation recommendation : recommendations) {
                bw.write(String.join(",", new String[]{recommendation.getUserId(), recommendation.getMovieId()}));
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
