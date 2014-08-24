package br.trindade.fitnessgame.bean;

import br.trindade.fitnessgame.entity.Athlete;
import br.trindade.fitnessgame.entity.Training;
import br.trindade.fitnessgame.service.AthleteService;
import br.trindade.fitnessgame.service.TrainingService;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author trindade
 */
@ViewScoped
@ManagedBean
public class TrainingBean implements Serializable {

    @EJB
    private AthleteService athleteService;

    @EJB
    private TrainingService trainingService;

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;

    private int id;
    private double caloriesLost;
    private Date dateTraining;
    private String type;
    private List<Training> trainings;
    private Training selectedTraining;
    private Integer rating;
    private Athlete athlete;
    final private Date maxDate = new Date();
    
    public void preRenderView() throws IOException {
        verifySession();
        id = sessionBean.getSessionUser().getId();
        athlete = athleteService.getById(id);
        trainings = trainingService.getListByAthlete(athlete);
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
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

    public List<Training> getTrainings() {
        return trainings;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
    
    public Training getSelectedTraining() {
        return selectedTraining;
    }

    public void setSelectedTraining(Training selectedTraining) {
        this.selectedTraining = selectedTraining;
    }

    public String remove() {
        trainingService.delete(selectedTraining);
        FacesMessage msg = new FacesMessage("Sucesso", "Treino removido");
        FacesContext.getCurrentInstance().addMessage("msgs", msg);
        return "treinos?faces-redirect=true";
    }

    public List<String> autoCompleteType(String query) {
        return trainingService.getTypes();
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void onRowEdit(RowEditEvent event) {
        trainingService.update((Training) event.getObject());
        FacesMessage msg = new FacesMessage("Sucesso", "Treino atualizado");
        FacesContext.getCurrentInstance().addMessage("msgs", msg);
    }

    public String insertTraining() {
        Training training = new Training();
        training.setAthlete(athlete);
        training.setCaloriesLost(caloriesLost);
        training.setDateTraining(dateTraining);
        training.setType(type);
        training.setRating(rating);
        trainingService.create(training);

        return "treinos?faces-redirect=true";
    }
    
    public void verifySession() throws IOException {
        if (sessionBean.getSessionUser() == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        }
    }
}
