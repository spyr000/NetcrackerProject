package sorting.comparators;

import contracts.Contract;

import java.util.Comparator;

/**
 * Contracts' finish dates comparator
 * @author almtn
 */
public class FinishDateComparator implements Comparator<Contract> {
    public boolean descending;

    public FinishDateComparator() {
        descending = false;
    }

    /**
     * @param descending true if sorting in descending order
     */
    public FinishDateComparator(boolean descending)
    {
        this.descending = descending;
    }

    /**
     * @param o1 first contract
     * @param o2 second contract
     * @return -1, if o1's finish date precedes o1's finish date,
     *          0, if o1's finish date is equal to o1's finish date,
     *          1, otherwise.
     *          On the contrary, if descending is true.
     */
    @Override
    public int compare(Contract o1, Contract o2) {
        return descending ? o2.getFinishDate().compareTo(o1.getFinishDate()) : o1.getFinishDate().compareTo(o2.getFinishDate());
    }
}