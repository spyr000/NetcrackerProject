package sorting.comparators;

import contracts.Contract;

import java.util.Comparator;

public class TypeComparator implements Comparator<Contract> {
    public boolean descending;

    public TypeComparator() {
        descending = false;
    }

    public TypeComparator(boolean descending)
    {
        this.descending = descending;
    }

    @Override
    public int compare(Contract o1, Contract o2) {
        return descending ? o2.getClass().toString().compareTo(o1.getClass().toString()) : o1.getClass().toString().compareTo(o2.getClass().toString());
    }
}
