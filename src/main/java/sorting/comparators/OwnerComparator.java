package sorting.comparators;

import contracts.Contract;

import java.util.Comparator;

public class OwnerComparator implements Comparator<Contract> {
    public boolean descending;

    public OwnerComparator() {
        descending = false;
    }

    public OwnerComparator(boolean descending)
    {
        this.descending = descending;
    }

    @Override
    public int compare(Contract o1, Contract o2) {
        return descending ? o2.getOwner().compareTo(o1.getOwner()) : o1.getOwner().compareTo(o2.getOwner());
    }
}
