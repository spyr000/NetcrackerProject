package sorting.comparators;

import contracts.Contract;

import java.util.Comparator;

/**
 * Contracts' start dates comparator
 * @author almtn
 */
public class StartDateComparator implements Comparator<Contract> {
    public boolean descending;

    public StartDateComparator() {
        descending = false;
    }

    /**
     * @param descending true if sorting in descending order
     */
    public StartDateComparator(boolean descending)
    {
        this.descending = descending;
    }

    /**
     * @param o1 first contract
     * @param o2 second contract
     * @return -1, if o1's start date precedes o1's start date,
     *          0, if o1's start date is equal to o1's owner,
     *          1, otherwise.
     *          On the contrary, if descending is true.
     */
    @Override
    public int compare(Contract o1, Contract o2) {
        return descending ? o2.getStartDate().compareTo(o1.getStartDate()) : o1.getStartDate().compareTo(o2.getStartDate());
    }
}