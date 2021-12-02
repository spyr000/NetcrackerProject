package predicates;

import contracts.Contract;

import java.util.function.Predicate;

/**
 * Contract's ID predicate
 * @author almtn
 */
public class IDPredicate implements Predicate<Contract> {
    private int id;

    /**
     * @param id ID
     */
    public IDPredicate(int id) {
        this.id = id;
    }

    /**
     * @param contract Contract
     * @return true if {@link IDPredicate#id} equals to contract's id, false otherwise
     */
    @Override
    public boolean test(Contract contract) {
        return id == contract.getId();
    }

    /**
     * @param id ID
     */
    public void setId(int id) {
        this.id = id;
    }
}
