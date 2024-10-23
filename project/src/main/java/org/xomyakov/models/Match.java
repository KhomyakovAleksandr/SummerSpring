package org.xomyakov.models;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "matches")
public class Match extends BaseEntity {

    private Tournament tournament;
    private Date matchDate;
    private String matchTime;
    private Team team1;
    private Team team2;
    private Team winnerTeam;
    private int matchDuration;
    private String matchType;

    public Match() {
    }

    public Match(Tournament tournament, Date matchDate, String matchTime, Team team1, Team team2, Team winnerTeam, int matchDuration, String matchType) {
        this.tournament = tournament;
        this.matchDate = matchDate;
        this.matchTime = matchTime;
        this.team1 = team1;
        this.team2 = team2;
        this.winnerTeam = winnerTeam;
        this.matchDuration = matchDuration;
        this.matchType = matchType;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tournament_id", referencedColumnName = "id", nullable = false)
    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "match_date", nullable = false)
    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    @Column(name = "match_time", nullable = false)
    public String getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_1", referencedColumnName = "id", nullable = false)
    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_2", referencedColumnName = "id", nullable = false)
    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonIgnore
    @JoinColumn(name = "winner_team", referencedColumnName = "id", nullable = true) // Изменено на nullable = true
    public Team getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(Team winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    @Column(name = "match_duration", nullable = false)
    public int getMatchDuration() {
        return matchDuration;
    }

    public void setMatchDuration(int matchDuration) {
        this.matchDuration = matchDuration;
    }

    @Column(name = "match_type", nullable = false)
    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }
}
