package org.xomyakov.models;
import jakarta.persistence.*;

@Entity
@Table(name = "heroes")
public class Hero extends BaseEntity{
    private String name;
    private String primaryAttribute;
    private double winRate;

    public Hero() {
    }

    public Hero(String name, String primaryAttribute, double winRate) {
        this.name = name;
        this.primaryAttribute = primaryAttribute;
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

    @Column(name = "win_rate", nullable = false)
    public double getWinRate() {
        return winRate;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }
}
