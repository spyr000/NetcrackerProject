package predicates;

import contracts.Contract;

import java.util.function.Predicate;

public class IDpredicate implements Predicate<Contract> {
    private int id;

    public IDpredicate(int id) {
        this.id = id;
    }

    @Override
    public boolean test(Contract contract) {
        return id == contract.getId();
    }

    public void setId(int id) {
        this.id = id;
    }
}
