package org.xomyakov.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "team")
public class Team extends BaseEntity {

    private String teamName;
    private int countWins;
    private int countLosses;
    private Set<Match> matches;
    private Tournament tournament;

    public Team() {
        this.matches = new HashSet<>();
    }

    public Team(String teamName, int countWins, int countLosses) {
        this();
        this.teamName = teamName;
        this.countWins = countWins;
        this.countLosses = countLosses;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tournament_id")
    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "team_matches",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "match_id")
    )
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

    @Column(name = "count_wins", nullable = false)
    public int getCountWins() {
        return countWins;
    }

    public void setCountWins(int countWins) {
        this.countWins = countWins;
    }

    @Column(name = "count_losses", nullable = false)
    public int getCountLosses() {
        return countLosses;
    }

    public void setCountLosses(int countLosses) {
        this.countLosses = countLosses;
    }
}


// мб добивать ключ/значение для определения какой игрок какого персонажа выбрал