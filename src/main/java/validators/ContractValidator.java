package validators;

import contracts.Contract;
import person.Person;

/**
 * Wired internet contract validator
 *
 * @author almtn
 */
public abstract class ContractValidator {
    private static final String nameMask = "% % %";
    private static final String numberStartsWithChar = "8";
    private static final int numberLen = 11;
    public static final int[] ppDataLens = {4,6};
    protected ValidationStatus status;

    protected ContractValidator() {
        status = ValidationStatus.Undefined;
    }

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
            status = ValidationStatus.RedRisk;
            return;
        }
        if(owner.getGender() == null){
            status = ValidationStatus.RedRisk;
            return;
        }
        if(owner.getPassportData() == null) {
            status = ValidationStatus.RedRisk;
            return;
        }
        name = name.replaceAll("\\p{C}", "").trim();
        for (char c : name.toCharArray()) {
            if(Character.isDigit(c)){
                status = ValidationStatus.Error;
                return;
            }
        }
        if (nameMask.replaceAll("\\p{C}", "").trim().split(" ").length != name.split(" ").length) {
            status = ValidationStatus.Error;
            return;
        }
        if(owner.getPassportData().length != 2)
        {
            status = ValidationStatus.Error;
            return;
        }
        if(Integer.toString(owner.getPassportData()[0]).length() != ppDataLens[0] ||
                Integer.toString(owner.getPassportData()[1]).length() != ppDataLens[1])
        {
            status = ValidationStatus.Error;
            return;
        } else if(owner.getPassportData()[0]< 0 || owner.getPassportData()[1] < 0)
        {
            status = ValidationStatus.Error;
            return;
        }
        status = ValidationStatus.OK;
    }

    /**
     * Validates whether the number matches the mask
     *
     * @param number phone number
     */
    private void validateNumber(String number) {
        if (number == null ) {
            status = ValidationStatus.RedRisk;
            return;
        }
        for (char c : number.toCharArray()) {
            if(Character.isLetter(c)) {
                status = ValidationStatus.RedRisk;
                return;
            }
        }
        number = number.replaceAll("\\p{C}", "").trim();
        if (number.startsWith("+")) {
            number = number.substring(1);
            if (!number.startsWith(getPlusNumberStartsWithChar()) || number.length() != numberLen) {
                status = ValidationStatus.Error;
                return;
            }
        } else if (!number.startsWith(numberStartsWithChar) || number.length() != numberLen) {
            status = ValidationStatus.Error;
            return;
        }
        status = ValidationStatus.OK;
    }

    /**
     * Validates contract and sets {@link #status}
     *
     * @param contract Contract
     */
    public void validateContract(Contract contract)
    {
        validateNumber(contract.getNumber());
        validateOwner(contract.getOwner());
        if(contract.getFinishDate().isBefore(contract.getStartDate())) {
            status = ValidationStatus.Error;
        } else
            status = ValidationStatus.OK;
    }

    /**
     * @return {@link ContractValidator#status}
     */
    public ValidationStatus getStatus() {
        return status;
    }
}
