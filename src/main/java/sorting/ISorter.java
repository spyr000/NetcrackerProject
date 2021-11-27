package sorting;

import contracts.Contract;
import repository.ContractRepository;
import java.util.Comparator;

/**
 * @author almtn
 */
public interface ISorter {
    public ContractRepository sort(ContractRepository sortingRepo, Comparator<Contract> comparator);
}
