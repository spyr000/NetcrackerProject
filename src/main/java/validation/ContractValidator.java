package validation;

import contracts.Contract;
import person.Person;

/**
 * Wired internet contract validator
 *
 * @author almtn
 */
public abstract class ContractValidator {
    public static final int[] ppDataLens = {4, 6};
    private static final String nameMask = "% % %";
    private static final String numberStartsWithChar = "8";
    private static final int numberLen = 11;
    protected boolean redriskFlag = false;
    protected boolean errorFlag = false;


    /**
     * @return {@link ContractValidator#numberStartsWithChar} - 1
     */
    private static String getPlusNumberStartsWithChar() {
        return String.valueOf(Integer.parseInt(numberStartsWithChar) - 1);
    }

    /**
     * Validates whether the owner matches the owner's data masks
     *
     * @param owner contract owner
     */
    private void validateOwner(Person owner) {
        String name = owner.getName();
        if (name == null) {
            redriskFlag = true;
            return;
        }
        if (owner.getGender() == null) {
            redriskFlag = true;
            return;
        }
        if (owner.getPassportData() == null) {
            redriskFlag = true;
            return;
        }
        name = name.replaceAll("\\p{C}", "").trim();
        for (char c : name.toCharArray()) {
            if (Character.isDigit(c)) {
                redriskFlag = true;
                return;
            }
        }
        if (nameMask.replaceAll("\\p{C}", "").trim().split(" ").length != name.split(" ").length) {
            errorFlag = true;
            return;
        }
        if (owner.getPassportData().length != 2) {
            errorFlag = true;
            return;
        }
        if (Integer.toString(owner.getPassportData()[0]).length() != ppDataLens[0] ||
                Integer.toString(owner.getPassportData()[1]).length() != ppDataLens[1]) {
            errorFlag = true;
        } else if (owner.getPassportData()[0] < 0 || owner.getPassportData()[1] < 0) {
            errorFlag = true;
        }
    }

    /**
     * Validates whether the number matches the mask
     *
     * @param number phone number
     */
    private void validateNumber(String number) {
        if (number == null) {
            redriskFlag = true;
            return;
        }
        for (char c : number.toCharArray()) {
            if (Character.isLetter(c)) {
                redriskFlag = true;
                return;
            }
        }
        number = number.replaceAll("\\p{C}", "").trim();
        if (number.startsWith("+")) {
            number = number.substring(1);
            if (!number.startsWith(getPlusNumberStartsWithChar()) || number.length() != numberLen) {
                errorFlag = true;
            }
        } else if (!number.startsWith(numberStartsWithChar) || number.length() != numberLen) {
            errorFlag = true;
        }
    }

    /**
     * Validates contract
     *
     * @param contract Contract
     */
    protected void validateContract(Contract contract) {
        validateNumber(contract.getNumber());
        validateOwner(contract.getOwner());
        if (contract.getFinishDate().isBefore(contract.getStartDate())) {
            errorFlag = true;
        }
    }

    /**
     * @return contract's validation status
     */
    public ValidationStatus getStatus() {
        if (redriskFlag) {
            return ValidationStatus.RedRisk;
        } else if (errorFlag){
            return ValidationStatus.Error;
        } else
            return ValidationStatus.OK;
    }
}
