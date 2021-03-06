package sorting.comparators;

import contracts.Contract;

import java.util.Comparator;

/**
 * Contracts' IDs comparator
 * @author almtn
 */
public class IDComparator implements Comparator<Contract> {
    public boolean descending;

    public IDComparator() {
        descending = false;
    }

    /**
     * @param descending true if sorting in descending order
     */
    public IDComparator(boolean descending)
    {
        this.descending = descending;
    }

    /**
     * @param o1 first contract
     * @param o2 second contract
     * @return -1, if o1's id precedes o1's id,
     *          0, if o1's id is equal to o1's id,
     *          1, otherwise.
     *          On the contrary, if descending is true.
     */
    @Override
    public int compare(Contract o1, Contract o2) {
        return descending ? Integer.compare(o2.getId(), o1.getId()) : Integer.compare(o1.getId(), o2.getId());
    }
}
