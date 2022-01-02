package validators;

import contracts.WiredInternetContract;

/**
 * Wired internet contract validator
 *
 * @author almtn
 */
public class WIContractValidator extends ContractValidator{

    public WIContractValidator(){super();}

    /**
     * Validates wired internet contract
     *
     * @param contract Wired internet contract
     */
    public void validateContract(WiredInternetContract contract) {
        super.validateContract(contract);
        if(contract.getConnectionSpeed() <= 0)
            errorFlag = true;
    }
}
