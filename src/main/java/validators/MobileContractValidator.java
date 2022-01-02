package validators;

import contracts.MobileContract;

/**
 * Mobile contract validator
 *
 * @author almtn
 */
public class MobileContractValidator extends ContractValidator{

    public MobileContractValidator(){super();}

    /**
     * Validates mobile contract
     *
     * @param contract Mobile contract
     */
    public void validateContract(MobileContract contract) {
        super.validateContract(contract);
        if(contract.getTrafficGbAmount() < 0 || contract.getSmsAmount() < 0 || contract.getMinutesAmount() < 0)
            errorFlag = true;
    }
}
