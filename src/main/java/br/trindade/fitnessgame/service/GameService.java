package br.trindade.fitnessgame.service;

import br.trindade.fitnessgame.entity.Athlete;
import br.trindade.fitnessgame.entity.Training;
import br.trindade.fitnessgame.to.PerformanceTO;
import br.trindade.fitnessgame.util.OrderPerformance;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

/**
 * the brain of the game
 * @author trindade
 */
@Stateless
public class GameService {

    @PersistenceContext
    EntityManager em;

    @EJB
    AthleteService athleteService;

    static final Logger logger = LogManager.getLogger(GameService.class.getName());

    public List<PerformanceTO> findByMonth() {
        logger.entry();

        //This implementation is very specific because its necessary specify
        //when the month starts even hours and seconds besides day
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        Date startDate = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDate = calendar.getTime();

        List<Training> trainings = em.createQuery("SELECT t FROM training t "
                + "WHERE t.dateTraining > :startDate AND t.dateTraining < :endDate "
                + "ORDER BY t.athlete", Training.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate).
                getResultList();

        return logger.exit(findBest(trainings));
    }

    public List<PerformanceTO> findByWeek() {
        logger.entry();

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_WEEK));
        Date startDate = calendar.getTime();

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_WEEK));
        Date endDate = calendar.getTime();

        List<Training> trainings = em.createQuery("SELECT t FROM training t "
                + "WHERE t.dateTraining >= :startDate AND t.dateTraining < :endDate "
                + "ORDER BY t.athlete", Training.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate).
                getResultList();

        return logger.exit(findBest(trainings));
    }

    private List<PerformanceTO> findBest(List<Training> trainings) {

        List<PerformanceTO> performances = generatePerformances();
        for (Training training : trainings) {
            for (PerformanceTO performance : performances) {
                if (performance.getAthlete().equals(training.getAthlete())) {
                    Double caloriesLost = performance.getCaloriesLost() + training.getCaloriesLost();
                    performance.setCaloriesLost(caloriesLost);

                    int quantity = performance.getQuantityTrainings() + 1;
                    performance.setQuantityTrainings(quantity);
                }
            }
        }

        OrderPerformance comparator = new OrderPerformance();
        Collections.sort(performances, comparator);

        return performances;
    }

    private List<PerformanceTO> generatePerformances() {
        List<PerformanceTO> performances = new ArrayList<>();
        List<Athlete> athletes = athleteService.getList();

        for (Athlete athlete : athletes) {
            PerformanceTO performanceTO = new PerformanceTO();
            performanceTO.setAthlete(athlete);
            performanceTO.setCaloriesLost(0.0);
            performanceTO.setQuantityTrainings(0);
            performances.add(performanceTO);
        }

        return performances;
    }

    public int login(String email, String uncheckedPassword) {
        logger.entry();
        List<Athlete> athletes = em.createQuery("SELECT a FROM athlete a "
                + "WHERE a.email=:email ", Athlete.class)
                .setParameter("email", email)
                .getResultList();
        //if athlete found and match unchecked pass and hashed
        if (athletes.size() > 0 && BCrypt.checkpw(uncheckedPassword, athletes.get(0).getPassword())) {
            logger.info("Athlete logged.");
            int userId = athletes.get(0).getId();
            return logger.exit(userId);
        }
        logger.info("Athlete not logged.");
        return logger.exit(0);
    }
}
