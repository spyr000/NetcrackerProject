package predicates;

import contracts.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

/**
 * Client's passport data predicate
 * @author almtn
 */
public class PassportDataPredicate implements Predicate<Contract> {
    private String[] passportData;

    /**
     * @param passportData passport data
     */
    public PassportDataPredicate(int @NotNull [] passportData) {
        this.passportData = new String[]{Integer.toString(passportData[0]), Integer.toString(passportData[1])};
    }

    /**
     * @param contract Contract
     * @return true if {@link PassportDataPredicate#passportData} contains contract owner's passport data, false otherwise
     */
    @Override
    public boolean test(@NotNull Contract contract) {
        int series = contract.getOwner().getPassportData()[0];
        int number = contract.getOwner().getPassportData()[1];
        return passportData[0].contains(Integer.toString(series)) &&
                passportData[1].contains(Integer.toString(number));
    }

    /**
     * @param passportData passport data
     */
    public void setPassportData(int @NotNull [] passportData) {
        this.passportData = new String[]{Integer.toString(passportData[0]), Integer.toString(passportData[1])};
    }
}
