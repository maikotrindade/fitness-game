package br.trindade.fitnessgame.entity;

import br.trindade.fitnessgame.entity.Training;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2014-08-24T12:25:51")
@StaticMetamodel(Athlete.class)
public class Athlete_ { 

    public static volatile SingularAttribute<Athlete, Date> birthday;
    public static volatile SingularAttribute<Athlete, String> password;
    public static volatile SingularAttribute<Athlete, String> name;
    public static volatile ListAttribute<Athlete, Training> trainings;
    public static volatile SingularAttribute<Athlete, Date> registration;
    public static volatile SingularAttribute<Athlete, Integer> id;
    public static volatile SingularAttribute<Athlete, String> email;

}