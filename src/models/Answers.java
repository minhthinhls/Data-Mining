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
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;

/**
 *
 * @author Minh Thinh
 */
public class Answers implements FileHandler {

    private List<Answer> answers;

    /**
     *
     * @return
     */
    @Override
    public List<Answer> getList() {
        return answers;
    }

    /**
     *
     * @param toUrl
     * @throws IOException
     */
    @Override
    public void toArff(String toUrl) throws IOException {
        Filter valueFilter = new ReplaceMissingValues();
        StringBuilder sb = new StringBuilder();
        CSVLoader loader = new CSVLoader();
        File tempFile = File.createTempFile("answers", ".csv");
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
        loader.setSource(tempFile); // May throw IOException !
        loader.setFieldSeparator(";");
        loader.setMissingValue("?");
        try {
            sb.append(String.join(";", Answer.getFieldsName())).append("\n");
            for (Answer answer : answers) {
                sb.append(String.join(";", answer.getFieldsValue())).append("\n");
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
    public Answers read(String fromUrl) throws FileNotFoundException {
        // Reading the file from parameter @fromUrl.
        BufferedReader br = new BufferedReader(new FileReader(fromUrl));
        answers = new ArrayList<>();
        try {
            String line;
            String[] fields;
            br.readLine(); // Skips the first line in csv file.
            while ((line = br.readLine()) != null) {
                fields = line.replaceAll("NA", "?").replaceAll(" ", "").split(",");
                answers.add(new Answer(fields));
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

            for (Answer answer : answers) {
                bw.write(String.join(",", answer.toString()));
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
