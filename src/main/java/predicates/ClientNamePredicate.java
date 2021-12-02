package predicates;

import contracts.Contract;

import java.util.function.Predicate;

/**
 * Client's name predicate
 * @author almtn
 */
public class ClientNamePredicate implements Predicate<Contract> {
    private String clientName;

    /**
     * @param clientName Client's name
     */
    public ClientNamePredicate(String clientName) {
        this.clientName = clientName;
    }

    /**
     * @param contract Contract
     * @return true if {@link ClientNamePredicate#clientName} contains contract owner's name, false otherwise
     */
    @Override
    public boolean test(Contract contract) {
        return this.clientName.contains(contract.getOwner().getName());
    }

    /**
     * @param clientName Client's name
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
