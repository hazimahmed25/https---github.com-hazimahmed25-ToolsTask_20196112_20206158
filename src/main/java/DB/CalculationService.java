package DB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
//import com.example.entity.Calculation; //

@Stateless
public class CalculationService {

    @PersistenceContext
    private EntityManager em;

    public Calculation createCalculation(Calculation calculation) {
        int result;
        switch (calculation.getOperation()) {
            case "+":
                result = calculation.getNumber1() + calculation.getNumber2();
                break;
            case "-":
                result = calculation.getNumber1() - calculation.getNumber2();
                break;
            case "*":
                result = calculation.getNumber1() * calculation.getNumber2();
                break;
            case "/":
                result = calculation.getNumber1() / calculation.getNumber2();
                break;
            default:
                throw new IllegalArgumentException("Invalid operation");
        }

        calculation.setId(null); // ensure that a new entity is created
        calculation.setOperation(calculation.getOperation().trim()); // trim the operation string
        em.persist(calculation);

        calculation.setNumber1(result);
        calculation.setNumber2(0);
        calculation.setOperation(null);
        return calculation;
    }

    public List<Calculation> getAllCalculations() {
        return em.createQuery("SELECT c FROM Calculation c", Calculation.class).getResultList();
    }
}
