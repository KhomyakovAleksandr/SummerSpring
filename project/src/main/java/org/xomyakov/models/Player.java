package org.xomyakov.models;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "players")
public class Player extends BaseEntity {
    private String nickname;
    private String realName;
    private Date dateOfBirth;
    private Team team;
    private String role;
    private int countWins;
    private int countLosses;

    public Player() {
    }

    public Player(String nickname, String realName, Date dateOfBirth,
                  Team team, String role, int totalWins, int totalLosses) {
        this.nickname = nickname;
        this.realName = realName;
        this.dateOfBirth = dateOfBirth;
        this.team = team;
        this.role = role;
        this.countWins = totalWins;
        this.countLosses = totalLosses;
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

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth", nullable = false)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = false)
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

    @Column(name = "count_wins", nullable = false)
    public int getTotalWins() {
        return countWins;
    }

    public void setTotalWins(int totalWins) {
        this.countWins = totalWins;
    }

    @Column(name = "count_losses", nullable = false)
    public int getTotalLosses() {
        return countLosses;
    }

    public void setTotalLosses(int totalLosses) {
        this.countLosses = totalLosses;
    }
}
