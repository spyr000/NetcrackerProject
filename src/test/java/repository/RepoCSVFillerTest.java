package repository;

import exceptions.CSVParseException;
import junit.framework.TestCase;

import java.io.File;

public class RepoCSVFillerTest extends TestCase {

    private final ContractRepository contractRepository = new ContractRepository();
    private final File file = new File("files/data.csv");
    RepoCSVFiller repoCSVFiller = new RepoCSVFiller(file);

    public void testFillRepo(){
        try {
            repoCSVFiller.fillRepo(contractRepository);
        } catch (CSVParseException e) {
            e.printStackTrace();
        }
        for(int i = 0;i<contractRepository.getLength();i++)
            System.out.println(contractRepository.getContractByIndex(i).toString());
    }
}