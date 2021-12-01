package predicates;

import contracts.Contract;
import person.Person;

import java.time.LocalDate;
import java.util.function.Predicate;

public class DatePredicate implements Predicate<Contract> {
    private LocalDate date;
    private boolean isStartDate = true;

    public DatePredicate(LocalDate date) {
        this.date = date;
    }

    public DatePredicate(LocalDate date,boolean isStartDate) {
        this.date = date;
        this.isStartDate = isStartDate;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setIsStartDate(boolean isStartDate) {
        this.isStartDate = isStartDate;
    }

    @Override
    public boolean test(Contract contract) {
        return isStartDate ? this.date.equals(contract.getStartDate()) : this.date.equals(contract.getFinishDate());
    }
}
