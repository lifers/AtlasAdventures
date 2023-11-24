package data_access;

import entity.Profile;
import use_case.profile.ProfileDataAccessInterface;
import use_case.profile.ProfileInputBoundary;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileUserDataAccessObject implements ProfileDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private Profile profile = new Profile(0, 0);

    public FileUserDataAccessObject(String csvPath) throws IOException {


        csvFile = new File(csvPath);
        headers.put("GAMES_PLAYED", 0);
        headers.put("AVERAGE_SCORE", 1);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("GAMES_PLAYED,AVERAGE_SCORE");

                String row = reader.readLine();

                String[] col = row.split(",");
                int gamesPlayed = Integer.parseInt(col[headers.get("GAMES_PLAYED")]);
                double score = Double.parseDouble(col[headers.get("AVERAGE_SCORE")]);

                this.profile.setAverage_score(score);
                this.profile.setGames_played(gamesPlayed);

            }
        }
    }


    public void update() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String header = reader.readLine();

            // For later: clean this up by creating a new Exception subclass and handling it in the UI.
            assert header.equals("GAMES_PLAYED,AVERAGE_SCORE");

            String row = reader.readLine();

            String[] col = row.split(",");
            int gamesPlayed = Integer.parseInt(col[headers.get("GAMES_PLAYED")]);
            double score = Double.parseDouble(col[headers.get("AVERAGE_SCORE")]);

            this.profile.setAverage_score(score);
            this.profile.setGames_played(gamesPlayed);
        }
    }
    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            String line = String.valueOf((int) this.profile.getGames_played()) + "," + String.valueOf(this.profile.getAverage_score());
            writer.write(line);
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setGamesPlayed(int gamesPlayed) {
        this.profile.setGames_played(gamesPlayed);
    }

    @Override
    public void setAverageScore(Double score) {
        this.profile.setAverage_score(score);
    }

    @Override
    public int getGamesPlayed() {
        return (int) this.profile.getGames_played();
    }

    @Override
    public Double getAverageScore() {
        return this.profile.getAverage_score();
    }
}
