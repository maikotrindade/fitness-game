package br.trindade.fitnessgame.entity;

import br.trindade.fitnessgame.entity.Athlete;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2014-08-24T12:25:51")
@StaticMetamodel(Training.class)
public class Training_ { 

    public static volatile SingularAttribute<Training, Double> caloriesLost;
    public static volatile SingularAttribute<Training, Date> dateTraining;
    public static volatile SingularAttribute<Training, Athlete> athlete;
    public static volatile SingularAttribute<Training, Integer> rating;
    public static volatile SingularAttribute<Training, Integer> id;
    public static volatile SingularAttribute<Training, String> type;

}