package exceptions;

public class ContractValidationException extends Exception {
    public ContractValidationException(int errors,int redrisks,int contracts){
        super("\nOut of " + contracts + " contracts added " + (contracts - errors - redrisks) + ":\n\t-"
                        + redrisks + " red risk errors \n\t-" + errors + " standart errors");
    }
}
