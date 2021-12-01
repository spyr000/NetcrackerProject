package sorting.comparators;

import contracts.Contract;

import java.util.Comparator;

public class StartDateComparator implements Comparator<Contract> {
    public boolean descending;

    public StartDateComparator() {
        descending = false;
    }

    public StartDateComparator(boolean descending)
    {
        this.descending = descending;
    }

    @Override
    public int compare(Contract o1, Contract o2) {
        return descending ? o2.getStartDate().compareTo(o1.getStartDate()) : Integer.compare(o1.getNumber(), o2.getNumber());
    }
}