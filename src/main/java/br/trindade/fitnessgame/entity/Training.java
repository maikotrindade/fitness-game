package br.trindade.fitnessgame.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author trindade
 */
@Entity(name = "training")
@Table(name = "training")
@XmlRootElement
public class Training implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "calories_lost")
    private double caloriesLost;

    @Column(name = "date_training")
    @Temporal(TemporalType.DATE)
    private Date dateTraining;

    @Column(name = "type")
    private String type;
    
    @Min(0) @Max(5)
    @Column(name = "rating")
    private Integer rating;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "athlete_id", nullable = false)
    private Athlete athlete;

    public Training() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCaloriesLost() {
        return caloriesLost;
    }

    public void setCaloriesLost(double caloriesLost) {
        this.caloriesLost = caloriesLost;
    }

    public Date getDateTraining() {
        return dateTraining;
    }

    public void setDateTraining(Date dateTraining) {
        this.dateTraining = dateTraining;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }
}
