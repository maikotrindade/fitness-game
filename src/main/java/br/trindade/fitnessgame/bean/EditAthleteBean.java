package br.trindade.fitnessgame.bean;

import br.trindade.fitnessgame.entity.Athlete;
import br.trindade.fitnessgame.service.AthleteService;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author trindade
 */
@ManagedBean
@ViewScoped
public class EditAthleteBean implements Serializable {

    @EJB
    private AthleteService athleteService;

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;

    private Integer id;
    private Athlete athlete;

    public void preRenderView() throws IOException {
        verifySession();
        id = sessionBean.getSessionUser().getId();
        athlete = athleteService.getById(id);
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    public String updateAthlete() {
        String hashedPassword = BCrypt.hashpw(athlete.getPassword(), BCrypt.gensalt());
        athlete.setPassword(hashedPassword);
        athleteService.update(athlete);
        return "perfil?faces-redirect=true";
    }

    public void verifySession() throws IOException {
        if (sessionBean.getSessionUser() == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
        }
    }
}
