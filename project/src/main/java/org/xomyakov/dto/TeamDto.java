package org.xomyakov.dto;
import java.util.Set;

public class TeamDto {
    private Long id;
    private String teamName;
    private int countWins;
    private int countLosses;
    private Long tournamentId;

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getCountWins() {
        return countWins;
    }

    public void setCountWins(int countWins) {
        this.countWins = countWins;
    }

    public int getCountLosses() {
        return countLosses;
    }

    public void setCountLosses(int countLosses) {
        this.countLosses = countLosses;
    }
}

