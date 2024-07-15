package org.xomyakov.models;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Table(name = "team")
public class Team extends BaseEntity{

    private String teamName;
    private int totalWins;
    private int totalLosses;
    private double winRate;
    private Set<Player> players;
    private Set<Match> matches;


    public Team() {
        this.players = new HashSet<>();
        this.matches = new HashSet<>();
    }

    public Team(String teamName, int totalWins, int totalLosses, double winRate) {
        this();
        this.teamName = teamName;
        this.totalWins = totalWins;
        this.totalLosses = totalLosses;
        this.winRate = winRate;
    }

//                                                                                       ???????????????????
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "matches", referencedColumnName = "id", nullable = false)
    public Set<Match> getMatches() {
        return matches;
    }

    public void setMatches(Set<Match> matches) {
        this.matches = matches;
    }

    @Column(name = "team_name", nullable = false)
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Column(name = "total_wins", nullable = false)
    public int getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(int totalWins) {
        this.totalWins = totalWins;
    }

    @Column(name = "total_losses", nullable = false)
    public int getTotalLosses() {
        return totalLosses;
    }

    public void setTotalLosses(int totalLosses) {
        this.totalLosses = totalLosses;
    }

    @Column(name = "win_rate", nullable = false)
    public double getWinRate() {
        return winRate;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "players", referencedColumnName = "id", nullable = false)
    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}

// мб добивать ключ/значение для определения какой игрок какого персонажа выбрал