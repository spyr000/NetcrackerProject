package sorting.comparators;

import contracts.Contract;

import java.util.Comparator;

public class FinishDateComparator implements Comparator<Contract> {
    public boolean descending;

    public FinishDateComparator() {
        descending = false;
    }
    public FinishDateComparator(boolean descending)
    {
        this.descending = descending;
    }

    @Override
    public int compare(Contract o1, Contract o2) {
        return descending ? o2.getFinishDate().compareTo(o1.getFinishDate()) : o1.getFinishDate().compareTo(o2.getFinishDate());
    }
}