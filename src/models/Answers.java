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

    }

    /**
     *
     * @param url
     * @return
     * @throws FileNotFoundException
     */
    @Override
    public Answers read(String url) throws FileNotFoundException {
        // Reading the file from parameter @url.
        BufferedReader br = new BufferedReader(new FileReader(url));
        answers = new ArrayList<>();
        try {
            String line;
            String[] fields;
            br.readLine();
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
