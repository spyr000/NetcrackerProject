package validators;

public abstract class ContractValidator {
    private static final String nameMask = "% % %";
    private static final String numberStartsWithChar = "8";
    private static final int numberLen = 11;
    private ValidationStatus status;

    protected ContractValidator() {
        status = ValidationStatus.Undefined;
    }

    public static String getPlusNumberStartsWithChar() {
        return String.valueOf(Integer.parseInt(numberStartsWithChar) - 1);
    }

    private void validateName(String name) {
        if (name == null) {
            status = ValidationStatus.RedRisk;
            return;
        }
        name = name.replaceAll("\\p{C}", "").trim();
        if (nameMask.replaceAll("\\p{C}", "").trim().split(" ").length == name.split(" ").length) {
            status = ValidationStatus.OK;
        } else {
            status = ValidationStatus.Error;
        }
    }

    private void numberValidation(String number) {
        if (number == null) {
            status = ValidationStatus.RedRisk;
            return;
        }
        number = number.replaceAll("\\p{C}", "").trim();
        if (number.startsWith("+")) {
            number = number.substring(1);
            if (number.startsWith(getPlusNumberStartsWithChar()) && number.length() == numberLen)
                status = ValidationStatus.OK;
            else
                status = ValidationStatus.Error;
        } else if (number.startsWith(numberStartsWithChar) && number.length() == numberLen)
            status = ValidationStatus.OK;
        else
            status = ValidationStatus.Error;
    }

    public ValidationStatus getStatus() {
        return status;
    }
}
