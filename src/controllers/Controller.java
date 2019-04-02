/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import models.*;
import weka.core.*;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

/**
 *
 * @author Minh Thinh
 */
public class Controller {

    static void toArff() throws IOException {
// Declare two numeric attributes
        Attribute Attribute1 = new Attribute("firstNumeric");
        Attribute Attribute2 = new Attribute("secondNumeric");

        // Declare a nominal attribute along with its values
        FastVector fvNominalVal = new FastVector(3);
        fvNominalVal.addElement("blue");
        fvNominalVal.addElement("gray");
        fvNominalVal.addElement("black");
        Attribute Attribute3 = new Attribute("aNominal", fvNominalVal);

        // Declare the class attribute along with its values
        FastVector fvClassVal = new FastVector(2);
        fvClassVal.addElement("positive");
        fvClassVal.addElement("negative");
        Attribute ClassAttribute = new Attribute("theClass", fvClassVal);

        // Declare the feature vector
        FastVector fvWekaAttributes = new FastVector(4);
        fvWekaAttributes.addElement(Attribute1);
        fvWekaAttributes.addElement(Attribute2);
        fvWekaAttributes.addElement(Attribute3);
        fvWekaAttributes.addElement(ClassAttribute);

        // Create an empty training set
        Instances isTrainingSet = new Instances("Rel", fvWekaAttributes, 10);

        // Set class index
        isTrainingSet.setClassIndex(3);

        // Create the instance
        Instance iExample = new Instance(4);
        iExample.setValue((Attribute) fvWekaAttributes.elementAt(0), 1.0);
        iExample.setValue((Attribute) fvWekaAttributes.elementAt(1), 0.5);
        iExample.setValue((Attribute) fvWekaAttributes.elementAt(2), "gray");
        iExample.setValue((Attribute) fvWekaAttributes.elementAt(3), "positive");

        BufferedWriter writer = new BufferedWriter(new FileWriter("D:/Work/test.arff"));
        writer.write(isTrainingSet.toString());
        writer.write(iExample.toString());
        writer.flush();
        writer.close();
    }

    static void CsvToArff(String fromUrl, String toUrl) throws IOException {
        try {
            CSVLoader loader = new CSVLoader();
            loader.setSource(new File(fromUrl));
            Instances data = loader.getDataSet();

            ArffSaver saver = new ArffSaver();
            saver.setInstances(data);
            saver.setFile(new File(toUrl));
            saver.writeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        Answers answers = new Answers();
        answers.read("dataset/answers.csv");

        Movies movies = new Movies();
        movies.read("dataset/movies.csv");

        Recommendations recommendations = new Recommendations();
        recommendations.read("dataset/recommendations.csv");

        Tags tags = new Tags();
        tags.read("dataset/tags.csv");

        CsvToArff("dataset/answers.csv", "D:/Work/answers.arff");
        CsvToArff("dataset/movies.csv", "D:/Work/movies.arff");
        CsvToArff("dataset/recommendations.csv", "D:/Work/recommendations.arff");
        CsvToArff("dataset/tags.csv", "D:/Work/tags.arff");
    }

}
