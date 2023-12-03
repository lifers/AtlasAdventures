package data_access;
import com.mongodb.*;
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

public class MongoDBDataAccessObject implements LeaderboardDataAccessInterface, AutoCloseable {
    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;
    private final Comparator<Profile> profileComparator;

    public MongoDBDataAccessObject(String connectionString, String databaseName, String collectionName) {
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();
        // Create a new client and connect to the server

        this.mongoClient = MongoClients.create(settings);
        this.database = mongoClient.getDatabase(databaseName);
        this.collection = database.getCollection(collectionName);

        try {
            // Send a ping to confirm a successful connection
            database.runCommand(new Document("ping", 1));
            System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
        } catch (MongoException e) {
            e.printStackTrace();
        }

        // Create a comparator that sorts by average score in descending order
        this.profileComparator = new Comparator<Profile>() {
            @Override
            public int compare(Profile p1, Profile p2) {
                return Double.compare(p2.getAverage_score(), p1.getAverage_score());
            }
        };
    }

    public void clearCollection() {
        collection.deleteMany(new Document());
    }
    @Override
    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    @Override
    public ArrayList<Profile> getLeaderboard() {
        ArrayList<Profile> leaderboard = new ArrayList<>();
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
    }

    @Override
    public boolean uidExists(int uid) {
        Document doc = collection.find(new Document("uid", uid)).first();
        return doc != null;
    }

    @Override
    public void updateProfile(Profile profile) {
        Document query = new Document("uid", profile.getUid());
        Bson updates = Updates.combine(
                Updates.set("avgScore", profile.getAverage_score()),
                Updates.set("gamesPlayed", profile.getGames_played())
        );
        UpdateOptions options = new UpdateOptions().upsert(true);

        collection.updateOne(query, updates, options);
    }

    @Override
    public void addProfile(Profile profile) {
        LinkedHashMap<String, Object> profileDict = new LinkedHashMap<>();
        Document profileDoc = new Document();
        profileDoc.append("uid", profile.getUid());
        profileDoc.append("avgScore", profile.getAverage_score());
        profileDoc.append("gamesPlayed", profile.getGames_played());
        collection.insertOne(profileDoc);
    }

    @Override
    public int generateNewUid() {
        Document largestUidDoc = collection.find().sort(new Document("uid", -1)).first();
        if (largestUidDoc != null) {
            int largestUid = largestUidDoc.getInteger("uid");
            return largestUid + 1;
        } else {
            return 1;
        }
    }
}
