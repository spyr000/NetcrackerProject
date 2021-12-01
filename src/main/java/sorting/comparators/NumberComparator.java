package sorting.comparators;

import contracts.Contract;

import java.util.Comparator;

public class NumberComparator implements Comparator<Contract> {
    public boolean descending;

    public NumberComparator() {
        descending = false;
    }
    public NumberComparator(boolean descending)
    {
        this.descending = descending;
    }

    @Override
    public int compare(Contract o1, Contract o2) {
        return descending ? Integer.compare(o2.getNumber(), o1.getNumber()) : Integer.compare(o1.getNumber(), o2.getNumber());
    }
}