package br.trindade.fitnessgame.service;

import br.trindade.fitnessgame.entity.Athlete;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class AthleteService {

    @PersistenceContext
    EntityManager em;

    static final Logger logger = LogManager.getLogger(AthleteService.class.getName());

    public Athlete create(Athlete athlete) {
        logger.entry();
        em.persist(athlete);
        return logger.exit(athlete);
    }

    public List<Athlete> getList() {
        logger.entry();
        return logger.exit(em.createQuery("SELECT a FROM athlete a", Athlete.class)
                .getResultList());
    }

    public Athlete getById(int id) {
        logger.entry();
        Athlete athlete = em.find(Athlete.class, id);
        return logger.exit(athlete);
    }

    public Athlete update(Athlete athlete) {
        logger.entry();
        em.merge(athlete);
        return logger.exit(athlete);
    }

    public void delete(Athlete athlete) {
        logger.entry();
        Athlete merge = em.merge(athlete);
        logger.exit("Remove athlete: " + merge.getName());
        em.remove(merge);
    }

    public void delete(int id) {
        logger.entry();
        Athlete athlete = getById(id);
        em.remove(athlete);
        logger.info("Removed athlete: " + athlete.getName());
    }

    public Integer calculateAge(Date birthday) {
        logger.entry();
        Calendar currentDate = new GregorianCalendar();
        Calendar birthDate = new GregorianCalendar();
        birthDate.setTime(birthday);

        int currentDay = currentDate.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        int currentMonth = currentDate.get(Calendar.MONTH);
        int bDay = birthDate.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        int bMonth = birthDate.get(Calendar.MONTH);

        if (currentMonth > bMonth) {
            return logger.exit(currentDate.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR));
        } else if (currentMonth < bMonth) {
            return logger.exit(currentDate.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR) - 1);
        } else {
            if (currentDay > bDay) {
                return logger.exit(currentDate.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR) - 1);
            } else {
                return logger.exit(currentDate.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR));
            }
        }
    }
}
