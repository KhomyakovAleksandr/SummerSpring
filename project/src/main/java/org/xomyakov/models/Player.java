package org.xomyakov.models;
import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class Player extends BaseEntity{
    private String nickname;
    private String realName;
    private String dateOfBirth;
    private Team team;
    private String role;
    private int totalWins;
    private int totalLosses;
    private double winRate;

    public Player() {
    }

    public Player(String nickname, String realName, String dateOfBirth,
                  Team team, String role, int totalWins, int totalLosses,
                  double winRate) {
        this.nickname = nickname;
        this.realName = realName;
        this.dateOfBirth = dateOfBirth;
        this.team = team;
        this.role = role;
        this.totalWins = totalWins;
        this.totalLosses = totalLosses;
        this.winRate = winRate;
    }


    @Column(name = "nickname", nullable = false)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Column(name = "real_name", nullable = false)
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Column(name = "date_of_birth", nullable = false)
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @OneToOne
    @JoinColumn(name = "team", referencedColumnName = "id", nullable = false)
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Column(name = "role", nullable = false)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
}
