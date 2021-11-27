package sorting.sorters;

import contracts.Contract;
import repository.ContractRepository;
import sorting.ISorter;

import java.util.Comparator;

public class BubbleSorter implements ISorter {

    @Override
    public ContractRepository sort(ContractRepository sortingRepo, Comparator<Contract> comparator) {
        int sortingRepoLength = sortingRepo.getLength();
        for(int i = 0; i < sortingRepoLength - 1; i ++)
        {
            //if(comparator(sortingRepo.getContractByIndex(i),))

        }
        return new ContractRepository();
    }
}
