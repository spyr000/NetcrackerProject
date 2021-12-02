package sorting.comparators;

import contracts.Contract;

import java.util.Comparator;

/**
 * Contracts' owners comparator
 * @author almtn
 */
public class OwnerComparator implements Comparator<Contract> {
    public boolean descending;

    public OwnerComparator() {
        descending = false;
    }

    /**
     * @param descending true if sorting in descending order
     */
    public OwnerComparator(boolean descending)
    {
        this.descending = descending;
    }

    /**
     * @param o1 first contract
     * @param o2 second contract
     * @return -1, if o1's owner precedes o1's owner,
     *          0, if o1's owner is equal to o1's owner,
     *          1, otherwise.
     *          On the contrary, if descending is true.
     */
    @Override
    public int compare(Contract o1, Contract o2) {
        return descending ? o2.getOwner().compareTo(o1.getOwner()) : o1.getOwner().compareTo(o2.getOwner());
    }
}
