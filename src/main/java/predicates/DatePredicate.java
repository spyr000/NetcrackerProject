package predicates;

import contracts.Contract;
import person.Person;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * Contract's date predicate
 * @author almtn
 */
public class DatePredicate implements Predicate<Contract> {
    private LocalDate date;
    private boolean isStartDate = true;

    /**
     * @param date Date
     */
    public DatePredicate(LocalDate date) {
        this.date = date;
    }

    /**
     * @param date Date
     * @param isStartDate true if we are looking for contract's start date,
     *                    false if we are looking for contract's finish date
     */
    public DatePredicate(LocalDate date,boolean isStartDate) {
        this.date = date;
        this.isStartDate = isStartDate;
    }

    /**
     * @param date Date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @param isStartDate true if we are looking for contract's start date,
     *                   false if we are looking for contract's finish date
     */
    public void setIsStartDate(boolean isStartDate) {
        this.isStartDate = isStartDate;
    }

    /**
     * @param contract Contract
     * @return true if {@link DatePredicate#date} contains contract's start or finish date, false otherwise
     */
    @Override
    public boolean test(Contract contract) {
        return isStartDate ? this.date.equals(contract.getStartDate()) : this.date.equals(contract.getFinishDate());
    }
}
