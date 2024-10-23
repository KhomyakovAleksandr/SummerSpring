package org.xomyakov.models;
import jakarta.persistence.*;

@Entity
@Table(name = "player_statistics")
public class PlayerStatistics extends BaseEntity {

    private Player player;
    private Team team;
    private Hero hero;
    private int gamesPlayed;
    private int gamesWon;

    public PlayerStatistics(){}

    public PlayerStatistics(Player player, Team team, Hero hero, int gamesPlayed, int gamesWon) {
        this.player = player;
        this.team = team;
        this.hero = hero;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id", referencedColumnName = "id", nullable = false)
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = false)
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hero_id", referencedColumnName = "id", nullable = false)
    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    @Column(name = "games_played", nullable = false)
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    @Column(name = "games_won", nullable = false)
    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }
}
