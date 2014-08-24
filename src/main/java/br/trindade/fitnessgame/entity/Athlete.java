package br.trindade.fitnessgame.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author trindade
 */
@Entity(name = "athlete")
@Table(name = "athlete")
@XmlRootElement
public class Athlete implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name="name", length = 50)
    @NotNull
    private String name;

    @Column(unique = true, name="email",length = 100)
    @NotNull
    @Size(min = 7, max = 100)
    private String email;

    @Column (name="birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    
    @Column (name="registration")
    @Temporal(TemporalType.DATE)
    private Date registration;

    @Column(name="password")
    @NotNull
    private String password;
    
    @OneToMany(mappedBy="athlete", cascade = {CascadeType.REMOVE, CascadeType.MERGE})    
    private List<Training> trainings = new ArrayList<>();

    public Athlete() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getRegistration() {
        return registration;
    }

    public void setRegistration(Date registration) {
        this.registration = registration;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }
}
