package br.trindade.fitnessgame.bean;

import br.trindade.fitnessgame.entity.Athlete;
import br.trindade.fitnessgame.entity.Training;
import br.trindade.fitnessgame.service.AthleteService;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author trindade
 */
@ManagedBean
@ViewScoped
public class ProfileBean implements Serializable {

    @EJB
    private AthleteService athleteService;

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;

    private Athlete athlete;
    private Integer age;
    private int id;

    public ProfileBean() {
    }

    public void preRenderView() throws IOException {
        verifySession();
        id = sessionBean.getSessionUser().getId();
        athlete = athleteService.getById(id);
        age = athleteService.calculateAge(athlete.getBirthday());
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public Integer getAge() {
        return age;
    }

    public String remove() {
        athleteService.delete(id);
        sessionBean.setSessionUser(null);
        return "index?faces-redirect=true";
    }

    public void verifySession() throws IOException {
        if (sessionBean.getSessionUser() == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
        }
    }
}
