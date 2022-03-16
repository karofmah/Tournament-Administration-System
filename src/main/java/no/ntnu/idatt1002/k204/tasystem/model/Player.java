package no.ntnu.idatt1002.k204.tasystem.model;

public class Player {
    private String gamertag;
    private String rank;

    /**
     * Player Constructor
     * @param gamertag gamertag of player
     * @param rank rank of player
     */
    public Player(String gamertag, String rank) throws IllegalArgumentException {
        if(!gamertag.equals(null) && !gamertag.equals("")) {
            this.gamertag = gamertag;
        } else {
            throw new IllegalArgumentException("Gamertag cannot be null or empty");
        }
        if (!rank.equals(null) && !rank.equals("")){
            this.rank = rank;
        } else{
            this.rank = "Unranked";
        }
    }

    /**
     * Get gamertag of player
     * @return gamertag
     */
    public String getGamertag() {
        return gamertag;
    }

    /**
     * Get rank of player
     * @return rank
     */
    public String getRank() {
        return rank;
    }

    /**
     * Modifies the gamertag of player
     * @param gamertag gamertag of player
     */
    public void setGamertag(String gamertag) {
        if(!gamertag.equals(null) && !gamertag.equals("")) {
            this.gamertag = gamertag;
        } else {
            throw new IllegalArgumentException("Gamertag cannot be null or empty");
        }
    }

    /**
     * Modifies the rank of player
     * @param rank rank of player
     */
    public void setRank(String rank) {
        if (!rank.equals(null) && !rank.equals("")){
            this.rank = rank;
        } else{
            this.rank = "Unranked";
        }

    }

    @Override
    public String toString() {
        return "Player{" +
                "gamertag='" + gamertag + '\'' +
                ", rank='" + rank + '\'' +
                '}';
    }
}
