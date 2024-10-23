package org.xomyakov.dto;
import java.util.Date;

public class PlayerDto {
    private Long id;
    private String nickname;
    private String realName;
    private Date dateOfBirth;
    private String teamName;
    private String role;
    private int countWins;
    private int countLosses;

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

