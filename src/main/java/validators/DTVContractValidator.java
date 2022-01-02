package validators;

import contracts.Contract;
import contracts.DigitalTelevisionContract;

/**
 * Digital television contract validator
 *
 * @author almtn
 */
public class DTVContractValidator extends ContractValidator{

    public DTVContractValidator(){super();}

    /**
     * Validates digital television contract
     *
     * @param contract Digital television contract
     */
    public void validateContract(DigitalTelevisionContract contract) {
        super.validateContract(contract);
        if(contract.getChannelPackage() == null) {
            redriskFlag = true;
            return;
        }
        for (String channel : contract.getChannelPackage()) {
            if(channel == null) {
                errorFlag = true;
                return;
            }
        }
    }
}
