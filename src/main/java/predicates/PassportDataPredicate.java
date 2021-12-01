package predicates;

import contracts.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class PassportDataPredicate implements Predicate<Contract> {
    private String[] passportData;

    public PassportDataPredicate(int @NotNull [] passportData) {
        this.passportData = new String[]{Integer.toString(passportData[0]),Integer.toString(passportData[1])};
    }

    @Override
    public boolean test(@NotNull Contract contract) {
        int series = contract.getOwner().getPassportData()[0];
        int number = contract.getOwner().getPassportData()[1];
        return passportData[0].contains(Integer.toString(series)) &&
                passportData[1].contains(Integer.toString(number));
    }

    public void setPassportData(int @NotNull [] passportData) {
        this.passportData = new String[]{Integer.toString(passportData[0]),Integer.toString(passportData[1])};;
    }
}
