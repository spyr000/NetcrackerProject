package sorting.comparators;

import contracts.Contract;

import java.util.Comparator;

/**
 * Contracts' numbers comparator
 * @author almtn
 */
public class NumberComparator implements Comparator<Contract> {
    public boolean descending;

    public NumberComparator() {
        descending = false;
    }

    /**
     * @param descending true if sorting in descending order
     */
    public NumberComparator(boolean descending)
    {
        this.descending = descending;
    }

    /**
     * @param o1 first contract
     * @param o2 second contract
     * @return -1, if o1's number precedes o1's number,
     *          0, if o1's number is equal to o1's number,
     *          1, otherwise.
     *          On the contrary, if descending is true.
     */
    @Override
    public int compare(Contract o1, Contract o2) {
        return descending ? CharSequence.compare(o2.getNumber(), o1.getNumber()) : CharSequence.compare(o1.getNumber(), o2.getNumber());
    }
}