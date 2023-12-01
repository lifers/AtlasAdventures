package data_access;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import entity.Profile;
import org.bson.Document;
import org.bson.conversions.Bson;
import use_case.leaderboard.LeaderboardDataAccessInterface;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;

import static com.mongodb.client.model.Updates.combine;
import static java.lang.reflect.Array.set;

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
        ArrayList<Profile> leaderboard = new ArrayList<>();
        try {
            collection.find()
                    .sort(new Document("avgScore", -1))
                    .limit(10)
                    .forEach(doc -> {
                        int uid = doc.getInteger("uid");
                        double avgScore = doc.getDouble("avgScore");
                        int gamesPlayed = doc.getInteger("gamesPlayed");
                        leaderboard.add(new Profile(uid, avgScore, gamesPlayed));
                    });
            leaderboard.sort(profileComparator);
            return leaderboard;
        } catch (Exception e) {
            System.err.println("Error finding documents: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public boolean uidExists(int uid) {
        try {
            Document doc = collection.find(new Document("uid", uid)).first();
            if (doc != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error accessing database :" + e.getMessage());
            return false;
        }
    }

    @Override
    public void updateProfile(Profile profile) {
        Document query = new Document("uid", profile.getUid());
        Bson updates = Updates.combine(
                Updates.set("avgScore", profile.getAverage_score()),
                Updates.set("gamesPlayed", profile.getGames_played())
        );
        UpdateOptions options = new UpdateOptions().upsert(true);
        try {
            collection.updateOne(query, updates, options);
        } catch (Exception e) {
            System.err.println("Error updating document: " + e.getMessage());
        }
    }

    @Override
    public void addProfile(Profile profile) {
        LinkedHashMap<String, Object> profileDict = new LinkedHashMap<>();
        Document profileDoc = new Document();
        profileDoc.append("uid", profile.getUid());
        profileDoc.append("avgScore", profile.getAverage_score());
        profileDoc.append("gamesPlayed", profile.getGames_played());
        insertDocument(profileDoc);
    }
}
