package br.trindade.fitnessgame.bean;

import br.trindade.fitnessgame.service.GameService;
import br.trindade.fitnessgame.to.PerformanceTO;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * 
 * @author trindade
 */
@ManagedBean
@ViewScoped
public class IndexBean implements Serializable {
    
    @EJB
    private GameService gameService;

    private List<PerformanceTO> bestMonth;
    private List<PerformanceTO> bestWeek;

    public void preRenderView() {
        bestMonth = gameService.findByMonth();
        bestWeek = gameService.findByWeek();
    }

    public List<PerformanceTO> getBestMonth() {
        return bestMonth;
    }

    public void setBestMonth(List<PerformanceTO> performances) {
        this.bestMonth = performances;
    }

    public List<PerformanceTO> getBestWeek() {
        return bestWeek;
    }

    public void setBestWeek(List<PerformanceTO> bestWeek) {
        this.bestWeek = bestWeek;
    }
}
