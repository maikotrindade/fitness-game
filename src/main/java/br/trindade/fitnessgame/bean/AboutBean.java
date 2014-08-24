package br.trindade.fitnessgame.bean;

import br.trindade.fitnessgame.entity.Athlete;
import br.trindade.fitnessgame.service.AthleteService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author trindade
 */
@Stateless
@ManagedBean
public class AboutBean implements Serializable{

    @EJB
    private AthleteService athleteService;

    private List<Athlete> athletes;

    public void preRenderView() {
        athletes = athleteService.getList();
    }

    public List<Athlete> getAthletes() {
        return athletes;
    }

}
