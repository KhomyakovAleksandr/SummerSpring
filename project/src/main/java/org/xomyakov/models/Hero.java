package org.xomyakov.models;
import jakarta.persistence.*;

@Entity
@Table(name = "heroes")
public class Hero extends BaseEntity{
    private String name;
    private String primaryAttribute;
    private String attackType;
    private String role;
    private int complexity;
    private double winRate;

    public Hero() {
    }

    public Hero(String name, String primaryAttribute, String attackType,
                String role, int complexity, double winRate) {
        this.name = name;
        this.primaryAttribute = primaryAttribute;
        this.attackType = attackType;
        this.role = role;
        this.complexity = complexity;
        this.winRate = winRate;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "primary_attribute", nullable = false)
    public String getPrimaryAttribute() {
        return primaryAttribute;
    }

    public void setPrimaryAttribute(String primaryAttribute) {
        this.primaryAttribute = primaryAttribute;
    }

    @Column(name = "attack_type", nullable = false)
    public String getAttackType() {
        return attackType;
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }

    @Column(name = "role", nullable = false)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name = "complexity", nullable = false)
    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    @Column(name = "win_rate", nullable = false)
    public double getWinRate() {
        return winRate;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }
}
