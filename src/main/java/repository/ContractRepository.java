package repository;

import contracts.Contract;

import java.util.Arrays;

public class ContractRepository {
    private Contract[] contracts;
    public ContractRepository(){
        contracts = new Contract[1];
    }
    public ContractRepository(Contract[] inputArr) {
        contracts = inputArr;
    }

    public void add(Contract c) {
        if (getContractByID(c.getId()) != null)
            throw new RuntimeException("You trying to add a contract with already existing id!!!");
        System.arraycopy(contracts,0,contracts,0,getLenght()+1);
        contracts[getLenght()-1] = c;
    }

    public Contract getContractByID(int id) {
        for(Contract contract: contracts) {
            if(contract.getId() == id)
                return contract;
        }
        return null;
    }

    public void deleteByID(int id){
        ;
    }

    public int getLenght() {
        return contracts.length;
    }
}
