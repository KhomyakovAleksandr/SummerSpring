package org.xomyakov.models;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "matches")
public class Match extends BaseEntity{

    private Tournament tournament;
    private String matchDate;
    private String matchTime;
    private Team team1;
    private Team team2;
    private Team winnerTeam;
    private int matchDuration;
    private String patchVersion;
    private String matchType;
    private Set<Long> team1PickHeroes;
    private Set<Long> team2PickHeroes;
    private Set<Long> team1BanHeroes;
    private Set<Long> team2BanHeroes;

    public Match() {
        this.team1PickHeroes = new HashSet<>();
        this.team2PickHeroes = new HashSet<>();
        this.team1BanHeroes = new HashSet<>();
        this.team2BanHeroes = new HashSet<>();
    }


    public Match(Tournament tournament, String matchDate, String matchTime,
                 Team team1, Team team2, Team winnerTeam, int matchDuration,
                 String patchVersion, String matchType) {
        this();
        this.tournament = tournament;
        this.matchDate = matchDate;
        this.matchTime = matchTime;
        this.team1 = team1;
        this.team2 = team2;
        this.winnerTeam = winnerTeam;
        this.matchDuration = matchDuration;
        this.patchVersion = patchVersion;
        this.matchType = matchType;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "tourname", referencedColumnName = "id", nullable = false)
    @Column(name = "tourname", nullable = false)
    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    @Column(name = "match_date", nullable = false)
    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    @Column(name = "match_time", nullable = false)
    public String getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_1", referencedColumnName = "id", nullable = false)
    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_2", referencedColumnName = "id", nullable = false)
    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    @Column(name = "winner_team", nullable = false)
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

    @Column(name = "patch_version", nullable = false)
    public String getPatchVersion() {
        return patchVersion;
    }

    public void setPatchVersion(String patchVersion) {
        this.patchVersion = patchVersion;
    }

    @Column(name = "match_type", nullable = false)
    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    @OneToMany
    @JoinColumn(name = "team_1_pick_hero", referencedColumnName = "id", nullable = false)
    public Set<Long> getTeam1PickHeroes() {
        return team1PickHeroes;
    }

    public void setTeam1PickHeroes(Set<Long> team1PickHeroes) {
        this.team1PickHeroes = team1PickHeroes;
    }

    @OneToMany
    @JoinColumn(name = "team_2_pick_hero", referencedColumnName = "id", nullable = false)
    public Set<Long> getTeam2PickHeroes() {
        return team2PickHeroes;
    }

    public void setTeam2PickHeroes(Set<Long> team2PickHeroes) {
        this.team2PickHeroes = team2PickHeroes;
    }
    @OneToMany
    @JoinColumn(name = "team_1_ban_hero", referencedColumnName = "id", nullable = false)
    public Set<Long> getTeam1BanHeroes() {
        return team1BanHeroes;
    }

    public void setTeam1BanHeroes(Set<Long> team1BanHeroes) {
        this.team1BanHeroes = team1BanHeroes;
    }

    @OneToMany
    @JoinColumn(name = "team_2_ban_hero", referencedColumnName = "id", nullable = false)
    public Set<Long> getTeam2BanHeroes() {
        return team2BanHeroes;
    }

    public void setTeam2BanHeroes(Set<Long> team2BanHeroes) {
        this.team2BanHeroes = team2BanHeroes;
    }
}
