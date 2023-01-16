package ch.bbw;

import ch.bbw.DbServices.UserDBService;
import ch.bbw.models.Statistics;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import java.util.Scanner;

public class Main {
    private static Statistics currentStats = new Statistics();
    private static UserDBService userDBService = new UserDBService();

    public static void main(String[] args) {
        System.out.println("What's your name?");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        currentStats.setName(name);
        userDBService.createUser(currentStats);
    }
}
