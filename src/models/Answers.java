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

/**
 *
 * @author Minh Thinh
 */
public class Answers {

    private List<Answer> answers;

    public List<Answer> getAnswers() {
        return answers;
    }

    /**
     *
     * @param url
     * @throws FileNotFoundException
     */
    public void read(String url) throws FileNotFoundException {
        // Reading the file from parameter @url.
        try {
            answers = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(url));
            String line;
            String[] fields;
            while ((line = br.readLine()) != null) {
                fields = line.split(",");
                answers.add(new Answer(fields));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
