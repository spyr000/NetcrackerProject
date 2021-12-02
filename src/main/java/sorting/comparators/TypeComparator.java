package sorting.comparators;

import contracts.Contract;

import java.util.Comparator;

/**
 * Contracts' types comparator
 * @author almtn
 */
public class TypeComparator implements Comparator<Contract> {
    public boolean descending;

    public TypeComparator() {
        descending = false;
    }

    /**
     * @param descending true if sorting in descending order
     */
    public TypeComparator(boolean descending)
    {
        this.descending = descending;
    }

    /**
     * @param o1 first contract
     * @param o2 second contract
     * @return -1, if o1's type precedes o1's type,
     *          0, if o1's type is equal to o1's type,
     *          1, otherwise.
     *          On the contrary, if descending is true.
     */
    @Override
    public int compare(Contract o1, Contract o2) {
        return descending ? o2.getClass().toString().compareTo(o1.getClass().toString()) : o1.getClass().toString().compareTo(o2.getClass().toString());
    }
}
