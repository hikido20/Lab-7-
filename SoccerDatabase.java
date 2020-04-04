package cs301.Soccer;

import android.util.Log;
import cs301.Soccer.soccerPlayer.SoccerPlayer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 *
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */

    private HashMap<String, SoccerPlayer> hashmap = new HashMap();

    @Override
    public boolean addPlayer(String firstName, String lastName,
                             int uniformNumber, String teamName) {
        SoccerPlayer player = new SoccerPlayer(firstName, lastName, uniformNumber, teamName);
        for (SoccerPlayer value : hashmap.values()) {
            if (value.equals(player)) {
                Log.e(nameAsString(firstName, lastName), "addPlayer");
                return false;
            }
        }
        hashmap.put(nameAsString(firstName,lastName),player);
        Log.e(nameAsString(firstName,lastName), "addPlayer: The player was added to the database.");
        return true;
    }

    public String nameAsString(String first, String last){
        return first + "," + last;
    }
    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        if(hashmap.containsKey(nameAsString(firstName,lastName))){
            hashmap.remove(nameAsString(firstName,lastName));
            return true;
        }
        return false;
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
    public SoccerPlayer getPlayer(String firstName, String lastName) {
        if(hashmap.containsKey((nameAsString(firstName,lastName)))){
            return hashmap.get(nameAsString(firstName,lastName));
        }
        return null;
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        if(hashmap.containsKey(nameAsString(firstName,lastName))){
            hashmap.get(nameAsString(firstName,lastName)).bumpGoals();;
            Log.e(nameAsString(firstName,lastName),"The number of goals is: " + hashmap.get(nameAsString(firstName,lastName)).getGoals());
            return true;
        }
        return false;
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        if(hashmap.containsKey(nameAsString(firstName,lastName))){
            hashmap.get(nameAsString(firstName,lastName)).bumpAssists();
            return true;
        }
        return false;
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        if(hashmap.containsKey(nameAsString(firstName,lastName))){
            hashmap.get(nameAsString(firstName,lastName)).bumpShots();
            return true;
        }
        return false;
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        if(hashmap.containsKey(nameAsString(firstName,lastName))) {
            hashmap.get(nameAsString(firstName, lastName)).bumpSaves();
            return true;
        }
        return false;
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        if(hashmap.containsKey(nameAsString(firstName,lastName))) {
            hashmap.get(nameAsString(firstName, lastName)).bumpFouls();
            return true;
        }
        return false;
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        if(hashmap.containsKey(nameAsString(firstName,lastName))) {
            hashmap.get(nameAsString(firstName, lastName)).bumpYellowCards();
            return true;
        }
        return false;
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        if(hashmap.containsKey(nameAsString(firstName,lastName))) {
            hashmap.get(nameAsString(firstName, lastName)).bumpRedCards();
            return true;
        }
        return false;
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
    public int numPlayers(String teamName) {
        int toReturn = 0;
        if(teamName == null){
            return hashmap.size();
        }
        for(SoccerPlayer value: hashmap.values()){
            if(value.getTeamName().equalsIgnoreCase(teamName)){
                toReturn++;
            }
        }
        return toReturn;
    }

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
    // get the nTH player
    @Override
    public SoccerPlayer playerNum(int idx, String teamName) {
        int temp = idx;
        if (teamName == null) {
            for (SoccerPlayer value : hashmap.values()) {
                if (temp == 0) {
                    return value;
                } else temp--;
            }
        }
        for (SoccerPlayer value : hashmap.values()) {
            if (value.getTeamName().equalsIgnoreCase(teamName)) {
                temp--;
            }
            if (temp == 0 && value.getTeamName().equalsIgnoreCase(teamName)) {
                return value;
            }
        }
        return null;
    }


    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
    // read data from file
    @Override
    public boolean readData(File file) {
        Scanner scan;
        try {
            scan = new Scanner(file);
            String first = "";
            String last = "";
            int uniformNum = 0;
            String teamName = "";
            while (scan.hasNextLine())
                first = scan.next();
            last = scan.next();
            uniformNum = scan.nextInt();
            teamName = scan.next();
            SoccerPlayer player = new SoccerPlayer(first, last, uniformNum, teamName);

            for (int i = 0; i < scan.nextInt(); i++) {
                player.bumpGoals();
            }
            for (int i = 0; i < scan.nextInt(); i++) {
                player.bumpAssists();
            }
            for (int i = 0; i < scan.nextInt(); i++) {
                player.bumpShots();
            }
            for (int i = 0; i < scan.nextInt(); i++) {
                player.bumpSaves();
            }
            for (int i = 0; i < scan.nextInt(); i++) {
                player.bumpFouls();
            }
            for (int i = 0; i < scan.nextInt(); i++) {
                player.bumpYellowCards();
            }
            for (int i = 0; i < scan.nextInt(); i++) {
                player.bumpRedCards();
            }
            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
    // write data to file
    @Override
    public boolean writeData(File file) {
        PrintWriter pw;
        try {
            pw = new PrintWriter(file);
            for (SoccerPlayer player : hashmap.values()) {
                pw.println(logString(player.getFirstName()));
                pw.println(logString(player.getLastName()));
                pw.println(logString(player.getUniform() + ""));
                pw.println(logString(player.getTeamName()));
                pw.println(logString(player.getGoals() + ""));
                pw.println(logString(player.getAssists() + ""));
                pw.println(logString(player.getShots() + ""));
                pw.println(logString(player.getSaves() + ""));
                pw.println(logString(player.getFouls() + ""));
                pw.println(logString(player.getYellowCards() + ""));
                pw.println(logString(player.getRedCards() + ""));
            }
            return true;
        } catch (FileNotFoundException fi) {
            Log.e("file", "We could not find the file.");
            return false;
        }
    }


    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see cs301.Soccer.SoccerDB#getTeams()
     */
    // return list of teams
    @Override
    public HashSet<String> getTeams() {
        HashSet<String> teamName = new HashSet<String>();
        for(SoccerPlayer player: hashmap.values()){
            if(!teamName.contains(player.getTeamName())){
                teamName.add(player.getTeamName());
            }
        }
        return teamName;
    }

}
