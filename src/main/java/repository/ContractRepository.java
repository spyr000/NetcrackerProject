package repository;

import contracts.Contract;

import java.util.function.Predicate;

/**
 * @author almtn
 */
public class ContractRepository implements Cloneable {
    private Contract[] contracts;

    /**
     * This constructor initializes an empty contract repository
     */
    public ContractRepository() {
        contracts = new Contract[0];
    }

    /**
     * This constructor initializes contract repository with an array of Contract objects
     * @param inputArr array of Contract objects
     */
    public ContractRepository(Contract[] inputArr) {
        contracts = inputArr;
    }

    /**
     * This function adding Contract c to contract repository
     * @param c Сontract to be added
     * @throws RuntimeException if you try to add a Contract with already existing in contract repository ID
     */
    public void add(Contract c) {
        if (getContractByID(c.getId()) != null)
            throw new RuntimeException("You're trying to add a contract with already existing id");
        if (getLength() == 0)
            contracts = new Contract[]{c};
        else {
            Contract[] result = new Contract[getLength() + 1];
            System.arraycopy(contracts, 0, result, 0, getLength());
            contracts = result;
            contracts[getLength() - 1] = c;
        }
    }

    /**
     * This function returns by ID Contract from contract repository if Contract with this ID exists in contract repository
     * @param id ID of the Contract you want to get from repository
     * @return Contract object if Contract with this id exists in contract repository, else returns null
     */
    public Contract getContractByID(int id) {
        for (Contract contract : contracts) {
            if (contract.getId() == id)
                return contract;
        }
        return null;
    }

    /**
     * This function returns by index Contract from contract repository
     * @param index index of the Contract you want to get from repository
     * @return Contract object if contract repository length is bigger than index, else returns null
     */
    public Contract getContractByIndex(int index) {
        return index < getLength() ? contracts[index] : null;
    }

    public void setContractByIndex(int index, Contract contract) {
        if(index < getLength())
            contracts[index] = contract;
    }

    /**
     * This function deletes by ID Contract from contract repository if Contract with this ID exists in contract repository
     * @param id ID of the Contract you want to delete from repository
     */
    public void deleteByID(int id) {
        int index = -1;
        for (int i = 0; i < getLength(); i++) {
            if (contracts[i].getId() == id) {
                index = i;
                break;
            }
        }
        if (index == -1)
            return;

        Contract[] result = new Contract[getLength() - 1];
        System.arraycopy(contracts, 0, result, 0, index);
        System.arraycopy(contracts, index + 1, result, index, getLength() - index - 1);
        contracts = result;
    }

    /**
     * This function returns length of contract repository
     * @return length of contract repository
     */
    public int getLength() {
        return contracts.length;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public ContractRepository findByPredicate(Predicate<Contract> predicate)
    {
        ContractRepository result = new ContractRepository();
        for(Contract contract: contracts)
        {
            if(predicate.test(contract))
                result.add(contract);
        }
        return result;
    }
}
