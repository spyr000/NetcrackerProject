package sorting.comparators;

import contracts.Contract;
import contracts.DigitalTelevisionContract;
import contracts.MobileContract;
import contracts.WiredInternetContract;
import person.Gender;
import person.Person;

import java.time.LocalDate;
import java.util.Calendar;
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
        return o1.getClass().toString().compareTo(o2.getClass().toString());
    }
}
