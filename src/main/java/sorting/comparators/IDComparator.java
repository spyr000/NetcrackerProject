package sorting.comparators;

import contracts.Contract;

import java.util.Comparator;

public class IDComparator implements Comparator<Contract> {
    public boolean descending;

    public IDComparator() {
        descending = false;
    }
    public IDComparator(boolean descending)
    {
        this.descending = descending;
    }

    @Override
    public int compare(Contract o1, Contract o2) {
        return descending ? Integer.compare(o2.getId(), o1.getId()) : Integer.compare(o1.getId(), o2.getId());
    }
}
