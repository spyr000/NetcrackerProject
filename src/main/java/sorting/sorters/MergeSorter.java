package sorting.sorters;

import contracts.Contract;
import org.jetbrains.annotations.NotNull;
import repository.ContractRepository;
import sorting.ISorter;

import java.util.Comparator;

/**
 * This class implements method sort from ISorter interface as a method which sorts repository with merge sort algorithm
 * @author almtn
 */
public class MergeSorter implements ISorter {
    /**
     * Sorts repository with merge sort algorithm
     * @param sortingRepo sorting repository
     * @param comparator comparator
     * @return sorted repository
     */
    @Override
    public ContractRepository sort(@NotNull ContractRepository sortingRepo, Comparator<Contract> comparator) {
        int lenght = sortingRepo.getLength();
        if (lenght < 2) {
            return sortingRepo;
        }

        ContractRepository leftRepo = new ContractRepository();
        for(int i = 0; i < lenght/2;i++)
            leftRepo.add(sortingRepo.getContractByIndex(i));

        ContractRepository rightRepo = new ContractRepository();
        for(int i = lenght/2; i < lenght;i++)
            rightRepo.add(sortingRepo.getContractByIndex(i));

        leftRepo = sort(leftRepo,comparator);
        rightRepo = sort(rightRepo,comparator);

        return mergeRepos(leftRepo,rightRepo,comparator);
    }

    /**
     * Merges two repositories
     * @param leftRepo left repository
     * @param rightRepo right repository
     * @param comparator comparator
     * @return merged repository
     */
    private @NotNull ContractRepository mergeRepos(@NotNull ContractRepository leftRepo, @NotNull ContractRepository rightRepo, Comparator<Contract> comparator) {

        ContractRepository resultRepo = new ContractRepository();
        int leftLen =  leftRepo.getLength();
        int rightLen = rightRepo.getLength();
        int length = leftLen + rightLen;

        for (int i = 0; i < length; i++) {
            leftLen =  leftRepo.getLength();
            rightLen = rightRepo.getLength();
            if (leftLen == 0){
                Contract contract = rightRepo.getContractByIndex(0);
                resultRepo.add(contract);
                rightRepo.deleteByID(contract.getId());
            } else if (rightLen == 0) {
                Contract contract = leftRepo.getContractByIndex(0);
                resultRepo.add(contract);
                leftRepo.deleteByID(contract.getId());
            } else if (comparator.compare(leftRepo.getContractByIndex(0),rightRepo.getContractByIndex(0)) < 0) {
                Contract contract = leftRepo.getContractByIndex(0);
                resultRepo.add(contract);
                leftRepo.deleteByID(contract.getId());
            } else {
                Contract contract = rightRepo.getContractByIndex(0);
                resultRepo.add(contract);
                rightRepo.deleteByID(contract.getId());
            }
        }
        return resultRepo;
    }

}
