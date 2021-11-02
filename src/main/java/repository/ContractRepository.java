package repository;

import contracts.Contract;

public class ContractRepository {
    private Contract[] contracts;

    public ContractRepository() {
        contracts = new Contract[0];
    }

    public ContractRepository(Contract[] inputArr) {
        contracts = inputArr;
    }

    public void add(Contract c) {
        if (getContractByID(c.getId()) != null)
            throw new RuntimeException("You trying to add a contract with already existing id");
        if (getLength() == 0)
            contracts = new Contract[]{c};
        else {
            Contract[] result = new Contract[getLength() + 1];
            System.arraycopy(contracts, 0, result, 0, getLength());
            contracts = result;
            contracts[getLength() - 1] = c;
        }
    }

    public Contract getContractByID(int id) {
        for (Contract contract : contracts) {
            if (contract.getId() == id)
                return contract;
        }
        return null;
    }

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

    public int getLength() {
        return contracts.length;
    }
}
