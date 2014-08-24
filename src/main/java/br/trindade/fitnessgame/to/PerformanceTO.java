package br.trindade.fitnessgame.to;

import br.trindade.fitnessgame.entity.Athlete;

/**
 *
 * @author trindade
 */
public class PerformanceTO {

    private Athlete athlete;
    private Double caloriesLost;
    private Integer quantityTrainings;

    public PerformanceTO() {
    }

    public PerformanceTO(Athlete athleteName, Double caloriesLost, Integer quantityTrainings) {
        this.athlete = athleteName;
        this.caloriesLost = caloriesLost;
        this.quantityTrainings = quantityTrainings;
    }

    public Double getCaloriesLost() {
        return caloriesLost;
    }

    public void setCaloriesLost(Double caloriesLost) {
        this.caloriesLost = caloriesLost;
    }

    public Integer getQuantityTrainings() {
        return quantityTrainings;
    }

    public void setQuantityTrainings(Integer quantityTrainings) {
        this.quantityTrainings = quantityTrainings;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }
    
   
}
