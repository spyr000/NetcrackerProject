package sorting.comparators;

import contracts.Contract;

import java.time.LocalDate;
import java.util.Calendar;
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
        return o1.getStartDate().compareTo(o2.getStartDate());
    }
}