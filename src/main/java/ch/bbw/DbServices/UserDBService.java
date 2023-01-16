package ch.bbw.DbServices;

import ch.bbw.models.Statistics;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class UserDBService {
    private final ConnectionString connectionString = new ConnectionString("mongodb://root:root@localhost");

    public void createUser(Statistics stat) {

        try (MongoClient mongoClient = MongoClients.create(
            MongoClientSettings.builder().applyConnectionString(connectionString).build()
        )) {
            MongoDatabase database = mongoClient.getDatabase("animalQuiz");
            try {

                Gson gson = new GsonBuilder().create();
                // Object zu JSON String konvertieren, danach JSON String zu MongoDB Document konvertieren
                Document d = org.bson.Document.parse(gson.toJson(stat));
                MongoCollection<org.bson.Document> statistics = database.getCollection("statistics");

                statistics.insertOne(d);

            } catch (MongoException me) {
                System.err.println("An error occurred while attempting to run a command: " + me);
            }

        }
    }
}