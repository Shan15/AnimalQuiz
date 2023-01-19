package ch.bbw.DbServices;

import ch.bbw.models.Animal;
import ch.bbw.models.Question;
import ch.bbw.models.Topic;
import ch.bbw.models.Statistics;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class UserDBService {
    private static final ConnectionString connectionString = new ConnectionString("mongodb://root:root@localhost");

    public List<Animal> getAnimalsFromDB() {
        List<Animal> result = new ArrayList<>();

        try (MongoClient mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                        .applyConnectionString(connectionString)
                        .build())) {
            MongoDatabase database = mongoClient.getDatabase("animalQuiz");
            try {
                MongoCollection<Document> animalDocs = database.getCollection("animal");
                FindIterable<Document> iterDoc = animalDocs.find();
                Iterator<Document> it = iterDoc.iterator();
                while (it.hasNext()) {
                    Document doc = it.next();
                    Gson gson = new GsonBuilder().create();
                    Animal animal = gson.fromJson(doc.toJson(), Animal.class);
                    result.add(animal);
                }
            } catch (MongoException me) {
                System.err.println("An error occurred while attempting to run a command: " + me);
            }

        }
        System.out.println("Found " + result.size());
        return result;
    }

    public List<Topic> getQuestionsFromDB() {
        List<Topic> result = new ArrayList<>();

        try (MongoClient mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                        .applyConnectionString(connectionString)
                        .build())) {
            MongoDatabase database = mongoClient.getDatabase("animalQuiz");
            try {
                MongoCollection<Document> questionDocs = database.getCollection("questions");
                FindIterable<Document> iterDoc = questionDocs.find();
                Iterator<Document> it = iterDoc.iterator();
                while (it.hasNext()) {
                    Document doc = it.next();
                    Gson gson = new GsonBuilder().create();
                    Topic topic = gson.fromJson(doc.toJson(), Topic.class);
                    result.add(topic);
                }
            } catch (MongoException me) {
                System.err.println("An error occurred while attempting to run a command: " + me);
            }

        }
        System.out.println("Found " + result.size());
        return result;
    }

    public static List<Question> getQuestion(String askTopic) {
        List<Question> result = new ArrayList<>();

        try (MongoClient mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                        .applyConnectionString(connectionString)
                        .build())) {
            MongoDatabase database = mongoClient.getDatabase("animalQuiz");
            try {
                MongoCollection<Document> questionDocs = database.getCollection("questions");
                Gson gson = new GsonBuilder().create();
                AggregateIterable<Document> documents = questionDocs.aggregate(Arrays.asList(Aggregates.match(Filters.eq("topic", askTopic))));
                for (Document doc : documents) {
                    Topic topic = gson.fromJson(doc.toJson(), Topic.class);
                    result.addAll(topic.getQuestions());
                }
            } catch (MongoException me) {
                System.err.println("An error occurred while attempting to run a command: " + me);
            }

        }
        System.out.println("Found " + result.size());
        return result;

    }


    public void createUser(Statistics stat) {

        try (MongoClient mongoClient = MongoClients.create(
                MongoClientSettings.builder().applyConnectionString(connectionString).build())) {
            MongoDatabase database = mongoClient.getDatabase("animalQuiz");
            try {

                Gson gson = new GsonBuilder().create();
                // Object zu JSON String konvertieren, danach JSON String zu MongoDB Document
                // konvertieren
                Document d = org.bson.Document.parse(gson.toJson(stat));
                MongoCollection<org.bson.Document> statistics = database.getCollection("statistics");

                statistics.insertOne(d);

            } catch (MongoException me) {
                System.err.println("An error occurred while attempting to run a command: " + me);
            }

        }
    }
}