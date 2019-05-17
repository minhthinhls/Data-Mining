/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import interfaces.FileHandler;
import models.*;
import weka.core.Attribute;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;

/**
 *
 * @author Minh Thinh
 */
public class Controller {

    public static void toArff(String toUrl) {
        Field[] fields = Answer.class.getDeclaredFields();
        List<String> listField = Stream.of(fields).map(x -> x.getName()).collect(Collectors.toList());
        listField.stream().forEach((i) -> {
            System.out.println(i);
        });
    }

    static <T extends FileHandler> void toArff(T object, String toUrl) {
        Field objectField = object.getClass().getDeclaredFields()[0];
        ParameterizedType genericType = (ParameterizedType) objectField.getGenericType();
        Class<?> genericClass = (Class<?>) genericType.getActualTypeArguments()[0]; // List<genericClass> objectField;

        Field[] fields = genericClass.getDeclaredFields();
        List<String> listField = Stream.of(fields).map(x -> x.getName()).collect(Collectors.toList());
        listField.stream().forEach((i) -> {
            System.out.println(i);
        });
        List list = object.getList();
        List<Attribute> listAtt = listField.stream().map(x -> new Attribute(x)).collect(Collectors.toList());

    }

    /**
     * Using Weka framework.
     *
     * @param fromUrl
     * @param toUrl
     * @throws IOException
     */
    static void CsvToArff(String fromUrl, String toUrl) throws IOException {
        Filter valueFilter = new ReplaceMissingValues();
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File(fromUrl)); // May throw IOException !
        try {
            loader.setMissingValue("NA");
            Instances data = loader.getDataSet();
            valueFilter.setInputFormat(data);
            data = Filter.useFilter(data, valueFilter);
            ArffSaver as = new ArffSaver();
            as.setInstances(data);
            as.setFile(new File(toUrl));
            as.writeBatch();
            System.out.println(String.format("=> Success reading from %s and writing to %s", fromUrl, toUrl));
        } catch (Exception e) {
            System.out.println(String.format("=> Failed reading from %s and writing to %s", fromUrl, toUrl));
            e.printStackTrace();
        } finally {
            // Do nothing !
        }
    }

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here

        //Answers answers = new Answers();
        //answers.read("dataset/answers.csv").toArff("dataset/answers.arff");
        //Movies movies = new Movies();
        //movies.read("dataset/movies.csv").toArffs("dataset/movies.arff");
        //Recommendations recommendations = new Recommendations();
        //recommendations.read("dataset/recommendations.csv").toArff("dataset/recommendations.arff");
        //Tags tags = new Tags();
        //tags.read("dataset/tags.csv").toArffs("dataset/tags.arff");
        //CsvToArff("dataset/answers.csv", "dataset/answers.arff");
        //CsvToArff("dataset/movies.csv", "dataset/movies.arff");
        //CsvToArff("dataset/recommendations.csv", "dataset/recommendations.arff");
        //CsvToArff("dataset/tags.csv", "dataset/tags.arff");
    }

}
