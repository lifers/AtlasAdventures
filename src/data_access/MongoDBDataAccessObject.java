package data_access;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import entity.Profile;
import org.bson.Document;
import use_case.leaderboard.LeaderboardDataAccessInterface;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;

public class MongoDBDataAccessObject implements LeaderboardDataAccessInterface {
    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;
    private final Comparator<Profile> profileComparator;

    public MongoDBDataAccessObject(String connectionString, String databaseName, String collectionName) {
        this.mongoClient = MongoClients.create(connectionString);
        this.database = mongoClient.getDatabase(databaseName);
        this.collection = database.getCollection(collectionName);
        this.profileComparator = new Comparator<Profile>() {
            // Sorts in descending order
            @Override
            public int compare(Profile p1, Profile p2) {
                if(p1.getAverage_score() < p2.getAverage_score()) {
                    return 1;
                } else if (p1.getAverage_score() > p2.getAverage_score()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };
    }

    private void insertDocument(Document document) {
        try {
            collection.insertOne(document);
            System.out.println("Document inserted successfully!");
        } catch (Exception e) {
            System.err.println("Error inserting document: " + e.getMessage());
        }
    }

    public void clearCollection() {
        try {
            collection.deleteMany(new Document());
        } catch (Exception e) {
            System.err.println("Error clearing collection: " + e.getMessage());
        }
    }

    public void close() {
        try {
            mongoClient.close();
        } catch (Exception e) {
            System.err.println("Error closing connection: " + e);
        }
    }

    @Override
    public ArrayList<Profile> getLeaderboard() {
        try {
            ArrayList<Profile> leaderboard = new ArrayList<>();
            collection.find()
                    .sort(new Document("avgScore", -1))
                    .limit(10)
                    .forEach(doc -> {
                        double avgScore = doc.getDouble("avgScore");
                        int gamesPlayed = doc.getInteger("gamesPlayed");
                        leaderboard.add(new Profile(avgScore, gamesPlayed));
                    });
            leaderboard.sort(profileComparator);
            return leaderboard;
        } catch (Exception e) {
            System.err.println("Error finding documents: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void addProfile(Profile profile) {
        LinkedHashMap<String, Object> profileDict = new LinkedHashMap<>();
        Document profileDoc = new Document();
        profileDoc.append("avgScore", profile.getAverage_score());
        profileDoc.append("gamesPlayed", profile.getGames_played());
        insertDocument(profileDoc);
    }
}
