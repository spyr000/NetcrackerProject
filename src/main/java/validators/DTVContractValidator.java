package validators;

import contracts.DigitalTelevisionContract;

/**
 * Digital television contract validator
 *
 * @author almtn
 */
public class DTVContractValidator extends ContractValidator{

    public DTVContractValidator(){super();}

    /**
     * Validates digital television contract and sets {@link #status}
     *
     * @param contract Digital television contract
     */
    public void validateContract(DigitalTelevisionContract contract) {
        super.validateContract(contract);
        if(contract.getChannelPackage() == null) {
            status = ValidationStatus.RedRisk;
            return;
        }
        for (String channel : contract.getChannelPackage()) {
            if(channel == null) {
                status = ValidationStatus.Error;
                return;
            }
        }
    }
}
