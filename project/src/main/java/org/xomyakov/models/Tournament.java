package org.xomyakov.models;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "tournament")
public class Tournament extends BaseEntity{

    private Set<Match> matches;
    private Set<Team> teams;
    private String venue;
    private double prizePool;
    private String organizer;

    public Tournament() {
        this.matches = new HashSet<>();
        this.teams = new HashSet<>();
    }

    public Tournament(String venue, double prizePool, String organizer) {
        this();
        this.venue = venue;
        this.prizePool = prizePool;
        this.organizer = organizer;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "matches", referencedColumnName = "id", nullable = false)
    public Set<Match> getMatches() {
        return matches;
    }

    public void setMatches(Set<Match> matches) {
        this.matches = matches;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "teams", referencedColumnName = "id", nullable = false)
    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    @Column(name = "venue", nullable = false)
    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    @Column(name = "price_pool", nullable = false)
    public double getPrizePool() {
        return prizePool;
    }

    public void setPrizePool(double prizePool) {
        this.prizePool = prizePool;
    }

    @Column(name = "organizer", nullable = false)
    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }
}


