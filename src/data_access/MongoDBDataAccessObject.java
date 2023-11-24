package data_access;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import entity.Profile;
import org.bson.Document;
import use_case.leaderboard.LeaderboardDataAccessInterface;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MongoDBDataAccessObject implements LeaderboardDataAccessInterface {
    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    public MongoDBDataAccessObject(String connectionString, String databaseName, String collectionName) {
        this.mongoClient = MongoClients.create(connectionString);
        this.database = mongoClient.getDatabase(databaseName);
        this.collection = database.getCollection(collectionName);
    }

    private void insertDocument(Document document) {
        try {
            collection.insertOne(document);
            System.out.println("Document inserted successfully!");
        } catch (Exception e) {
            System.err.println("Error inserting document: " + e.getMessage());
        }
    }

    public void findDocuments() {
        try {
            collection.find().forEach(document -> System.out.println(document.toJson()));
        } catch (Exception e) {
            System.err.println("Error finding documents: " + e.getMessage());
        }
    }

    public void close() {
        mongoClient.close();
    }

    @Override
    public ArrayList<Profile> getLeaderboard() {
        return null;
    }

    @Override
    public void addProfile(Profile profile) {
        LinkedHashMap<String, String> profileDict = new LinkedHashMap<String, String>();
        profileDict.put("avgScore", String.valueOf(profile.getAverage_score()));
        profileDict.put("gamesPlayed", String.valueOf(profile.getGames_played()));
        insertDocument(new Document(profileDict));
    }
}
