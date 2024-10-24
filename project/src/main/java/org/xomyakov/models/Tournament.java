package org.xomyakov.models;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tournament")
public class Tournament extends BaseEntity{


    private String name;
    private double prizePool;
    private String organizer;

    public Tournament() {
    }



    public Tournament(String name, double prizePool, String organizer) {
        this.name = name;
        this.prizePool = prizePool;
        this.organizer = organizer;
    }

        @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


