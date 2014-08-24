package br.trindade.fitnessgame.util;

import br.trindade.fitnessgame.to.PerformanceTO;
import java.util.Comparator;

/**
 *
 * @author trindade
 */
public class OrderPerformance implements Comparator<PerformanceTO>{

    @Override
    public int compare(PerformanceTO o1, PerformanceTO o2) {
        return o2.getCaloriesLost().compareTo(o1.getCaloriesLost());
    }
    
}
