package no.ntnu.idatt1002.k204.tasystem;

public class Player {
    private String gamertag;
    private String rank;

    /**
     * Player Constructor
     * @param gamertag gamertag of player
     * @param rank rank of player
     */
    public Player(String gamertag, String rank) {
        this.gamertag = gamertag;
        if (rank.equals(null) && !rank.equals("")){
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
        this.gamertag = gamertag;
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
}
