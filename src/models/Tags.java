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
import weka.filters.unsupervised.attribute.NominalToString;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;

/**
 *
 * @author Minh Thinh
 */
public class Tags implements FileHandler {

    private List<Tag> tags;

    /**
     *
     * @return
     */
    @Override
    public List<Tag> getList() {
        return tags;
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
            // Declare 4 numeric attributes
            Attribute userId = new Attribute("userId");
            Attribute movieId = new Attribute("movieId");
            Attribute tag = new Attribute("tag", true);
            Attribute timestamp = new Attribute("timestamp", "yyyy-MM-dd HH:mm:ss");

            List<Attribute> attributes = new ArrayList<>(Arrays.asList(userId, movieId, tag, timestamp));

            // Create an empty training set
            Instances dataSet = new Instances("tags", (ArrayList<Attribute>) attributes, 0);
            Instance newInstance = new DenseInstance(4);
            for (Tag iTag : tags) {
                newInstance.setValue(userId, Double.valueOf(iTag.getUserId()));
                newInstance.setValue(movieId, Double.valueOf(iTag.getMovieId()));
                newInstance.setValue(tag, iTag.getTag());
                newInstance.setValue(timestamp, Double.valueOf(iTag.getTimestamp()));
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
        Filter stringFilter = new NominalToString();
        StringBuilder sb = new StringBuilder();
        CSVLoader loader = new CSVLoader();
        File tempFile = File.createTempFile("tags", ".csv");
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
        loader.setSource(tempFile); // May throw IOException !
        loader.setFieldSeparator(";");
        loader.setMissingValue("?");
        try {
            sb.append(String.join(";", Tag.getFieldsName())).append("\n");
            for (Tag tag : tags) {
                sb.append(String.join(";", tag.getFieldsValue())).append("\n");
            }
            bw.write(sb.toString()); // Write built string to temp file.
            bw.flush();
            Instances data = loader.getDataSet();
            valueFilter.setInputFormat(data);
            data = Filter.useFilter(data, valueFilter);
            stringFilter.setInputFormat(data);
            data = Filter.useFilter(data, stringFilter);
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
    public Tags read(String fromUrl) throws FileNotFoundException {
        // Reading the file from parameter @fromUrl.
        BufferedReader br = new BufferedReader(new FileReader(fromUrl));
        tags = new ArrayList<>();
        try {
            String line;
            String[] fields;
            br.readLine(); // Skips the first line in csv file.
            while ((line = br.readLine()) != null) {
                fields = line.replaceAll("\'", " ").replaceAll("\" \",", "\"?\",").replaceAll("\"", "").split(",");
                tags.add(new Tag(fields));
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

            for (Tag tag : tags) {
                bw.write(String.join(",", tag.toString()));
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
