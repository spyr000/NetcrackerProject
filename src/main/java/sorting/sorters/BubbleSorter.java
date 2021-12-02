package sorting.sorters;

import contracts.Contract;
import repository.ContractRepository;
import sorting.ISorter;

import java.util.Comparator;

/**
 * This class implements method sort from ISorter interface as a method which sorts repository with bubble sort algorithm
 * @author almtn
 */
public class BubbleSorter implements ISorter {

    /**
     * Sorts repository with bubble sort algorithm
     * @param sortingRepo sorting repository
     * @param comparator comparator
     * @return sorted repository
     */
    @Override
    public ContractRepository sort(ContractRepository sortingRepo, Comparator<Contract> comparator) {
        int sortingRepoLength = sortingRepo.getLength();

        ContractRepository resultRepo = null;
        try {
            resultRepo = (ContractRepository) sortingRepo.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        assert resultRepo != null;

        for(int j = 0; j < sortingRepoLength; j++)
        {
            for (int i = 0; i < sortingRepoLength - 1; i++) {
                Contract current = resultRepo.getContractByIndex(i);
                Contract next = resultRepo.getContractByIndex(i + 1);
                if (comparator.compare(current, next) > 0) {
                    resultRepo.setContractByIndex(i, next);
                    resultRepo.setContractByIndex(i + 1, current);
                }

            }
        }
        return resultRepo;
    }
}
