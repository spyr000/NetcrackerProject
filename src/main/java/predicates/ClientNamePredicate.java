package predicates;

import contracts.Contract;
import person.Person;

import java.util.function.Predicate;

public class ClientNamePredicate implements Predicate<Contract> {
    private String clientName;

    public ClientNamePredicate(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public boolean test(Contract contract) {
        return this.clientName.contains(contract.getOwner().getName());
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
