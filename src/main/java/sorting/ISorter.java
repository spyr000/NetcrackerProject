package sorting;

import contracts.Contract;
import repository.ContractRepository;
import java.util.Comparator;

/**
 * interface for sorting contract repositories
 * @author almtn
 */
public interface ISorter {
    /**
     * Sorts repository with comparator
     * @param sortingRepo sorting repository
     * @param comparator sorting comparator
     * @return sorted repository
     */
    public ContractRepository sort(ContractRepository sortingRepo, Comparator<Contract> comparator);
}
