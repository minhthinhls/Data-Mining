/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab07;

import java.io.File;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

/**
 *
 * @author Minh Thinh
 */
public class NaiveBayesClassifier {

    /**
     * File names are defined
     */
    public static final String TRAINING_DATA_SET_FILENAME = "data\\weather.nominal.arff";
    public static final String TESTING_DATA_SET_FILENAME = "data\\weather.nominal.arff";

    /**
     * This method is to load the data set.
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    public static Instances getDataSet(String fileName) throws Exception {
        StringToWordVector filter = new StringToWordVector();

        ArffLoader loader = new ArffLoader(); // Create new Arff Loader.
        loader.setFile(new File(fileName)); // Set path to load file
        Instances dataSet = loader.getDataSet(); // Loading instances from File.
        try {
            dataSet.setClass(dataSet.attribute("play")); // Set class attribute for the data.
        } catch (Exception ex) {
            dataSet.setClassIndex(0); // Set default class attribute for the data.
            ex.printStackTrace();
        }

        filter.setInputFormat(dataSet);
        dataSet = Filter.useFilter(dataSet, filter);
        return dataSet;
    }

    /**
     * This method is used to process the input and return the statistics.
     *
     * @throws Exception
     */
    public static void process() throws Exception {

        Instances trainingDataSet = getDataSet(TRAINING_DATA_SET_FILENAME);
        Instances testingDataSet = getDataSet(TESTING_DATA_SET_FILENAME);
        /**
         * Classifier here is Linear Regression
         */
        Classifier classifier = new NaiveBayes();
        /**
         *
         */
        classifier.buildClassifier(trainingDataSet);
        /**
         * Train the algorithm with the training data and evaluate the algorithm
         * with testing data
         */
        Evaluation eval = new Evaluation(trainingDataSet);
        eval.evaluateModel(classifier, testingDataSet);
        /**
         * Print the algorithm summary
         */
        System.out.println("** Naive Bayes Evaluation with Datasets **");
        System.out.println(eval.toSummaryString());
        System.out.print("The expression for the input data as per alogorithm is ");
        System.out.println(classifier);
    }

    public static void main(String[] args) {
        NaiveBayesClassifier naiveBayes = new NaiveBayesClassifier();
        try {
            naiveBayes.process();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
