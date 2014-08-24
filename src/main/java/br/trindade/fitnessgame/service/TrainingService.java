package br.trindade.fitnessgame.service;

import br.trindade.fitnessgame.entity.Athlete;
import br.trindade.fitnessgame.entity.Training;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author trindade
 */
@Stateless
public class TrainingService {

    @PersistenceContext
    EntityManager em;

    static final Logger logger = LogManager.getLogger(TrainingService.class.getName());

    public Training create(Training training) {
        logger.entry();
        em.persist(training);
        return logger.exit(training);
    }

    public List<Training> getList() {
        logger.entry();
        return logger.exit(em.createQuery("SELECT t FROM training t", Training.class)
                .getResultList());
    }
    
    public List<Training> getListByAthlete(Athlete athlete) {
        logger.entry();
        return logger.exit(em.createQuery("SELECT t FROM training t WHERE t.athlete = :athlete ORDER BY t.dateTraining DESC", Training.class)
                .setParameter("athlete", athlete)
                .getResultList());
    }
    
    public Training getById(int id) {
        logger.entry();
        Training training = em.find(Training.class, id);
        return logger.exit(training);
    }

    public Training update(Training training) {
        logger.entry();
        em.merge(training);
        return logger.exit(training);
    }

    public void delete(Training training) {
        logger.entry();
        Training merge = em.merge(training);
        em.remove(merge);
        logger.info("Removed training: " + training.getId());
    }

    public void delete(int id) {
        logger.entry();
        Training training = getById(id);
        em.remove(training);
        logger.info("Remove training: " + training.getId());
    }

    public Double getCaloriesByMonth(int athlete_id) {
        logger.entry();
        List<Training> trainings = em.createQuery("SELECT t FROM training t "
                + "WHERE t.athlete_id = :athlete_id ", Training.class)
                .setParameter("athlete_id", athlete_id).
                getResultList();

        Double caloriesLost = 0.0;
        for (Training training : trainings) {
            caloriesLost = caloriesLost + training.getCaloriesLost();
        }

        return logger.exit(caloriesLost);
        //TODO
    }
    
    public List<String> getTypes() {
       logger.entry();
       return logger.exit(em.createQuery("SELECT DISTINCT t.type FROM training t", String.class)
                .getResultList());
    } 
}
