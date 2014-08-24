package br.trindade.fitnessgame.bean;

import br.trindade.fitnessgame.entity.Athlete;
import br.trindade.fitnessgame.service.AthleteService;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author trindade
 */
@ManagedBean
@ViewScoped
public class RegisterAthleteBean implements Serializable{
    @EJB
    private AthleteService athleteService;
 
    private int id;
    private String name;
    private String email;
    private Date birthday;
    private String password;

    public RegisterAthleteBean() {
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String insertAthlete() {
        Athlete athlete = new Athlete();

        athlete.setEmail(email);
        athlete.setName(name);
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        athlete.setPassword(hashedPassword);
        athlete.setBirthday(birthday);
        athlete.setRegistration(new Date());
        athleteService.create(athlete);

        return "/index?faces-redirect=true";
    }

}
