package br.trindade.fitnessgame.bean;

import br.trindade.fitnessgame.entity.Athlete;
import br.trindade.fitnessgame.service.AthleteService;
import br.trindade.fitnessgame.service.GameService;
import br.trindade.fitnessgame.session.SessionUser;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author trindade
 */
@ManagedBean(name = "sessionBean")
@SessionScoped
public class SessionBean implements Serializable {

    @EJB
    private AthleteService athleteService;

    @EJB
    private GameService gameService;

    private String email;
    private String password;
    private SessionUser sessionUser;

    public SessionBean() {
    }

    public SessionUser getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(SessionUser sessionUser) {
        this.sessionUser = sessionUser;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        if ((!email.isEmpty()) && (!password.isEmpty())) {
            int athleteId = gameService.login(email, password);
            if (athleteId > 0) {
                Athlete athlete = athleteService.getById(athleteId);
                sessionUser = new SessionUser(athlete.getId(), athlete.getName(), athlete.getEmail(), athlete.getPassword());
                return "/atleta/perfil.xhtml?faces-redirect=true";
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("message-error", new FacesMessage("","Senha inválida"));
                return "";
            }
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("message-error", new FacesMessage("","E-mail e/ou senha inválido(s)"));
            return "";
        }
    }

    public String logout() {
        if (sessionUser != null) {
            sessionUser = null;
        }
        return "/index.xhtml?faces-redirect=true";
    }
}
