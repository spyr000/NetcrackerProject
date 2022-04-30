package repository;

import contracts.Contract;
import injection.AutoInjectable;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import sorting.ISorter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author almtn
 */
@XmlRootElement(name = "contract_repository")
@XmlAccessorType(XmlAccessType.FIELD)
public class ContractRepository implements Cloneable {
    @AutoInjectable
    private static List<ISorter> sorterPlugins = new ArrayList<ISorter>();
    @XmlElementWrapper
    private Contract[] contracts;

    /**
     * This constructor initializes an empty contract repository
     */
    public ContractRepository() {
        contracts = new Contract[0];
    }

    /**
     * This constructor initializes contract repository with an array of Contract objects
     *
     * @param inputArr array of Contract objects
     */
    public ContractRepository(Contract[] inputArr) {
        contracts = inputArr;
    }

    /**
     * This function adding Contract c to contract repository
     *
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
     *
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
     *
     * @param index index of the Contract you want to get from repository
     * @return Contract object if contract repository length is bigger than index, else returns null
     */
    public Contract getContractByIndex(int index) {
        return index < getLength() ? contracts[index] : null;
    }

    public void setContractByIndex(int index, Contract contract) {
        if (index < getLength())
            contracts[index] = contract;
    }

    /**
     * This function deletes by ID Contract from contract repository if Contract with this ID exists in contract repository
     *
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
     *
     * @return length of contract repository
     */
    public int getLength() {
        return contracts.length;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Finds contracts in repository by predicate
     *
     * @param predicate search predicate
     * @return length of contract repository
     */
    public ContractRepository findByPredicate(Predicate<Contract> predicate) {
        ContractRepository result = new ContractRepository();
        for (Contract contract : contracts) {
            if (predicate.test(contract))
                result.add(contract);
        }
        return result;
    }

    public ISorter getSorterByName(String sorterName) {
        ISorter result = null;
        for (ISorter sorter : sorterPlugins) {
            if (sorter.getClass().getName().equals(sorterName)) {
                result = sorter;
                break;
            }
        }
        return result;
    }

    public ContractRepository sort(String sorterName, Comparator<Contract> comparator) {
        return getSorterByName(sorterName).sort(this, comparator);
    }

    /**
     * Function for saving Contract Repository to the .xml file
     *
     * @throws JAXBException         if JAXB can't marshal contract repository
     * @throws FileNotFoundException if file path is bad
     */
    public void marshal() throws JAXBException, FileNotFoundException {
        Marshaller marshaller = JAXBContext.newInstance(ContractRepository.class).createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(this, new FileOutputStream("files/repo.xml"));
    }

    /**
     * Function for extracting Contract Repository from the .xml file
     *
     * @return Contract Repository from xml file
     * @throws JAXBException if JAXB can't unmarshal contract repository
     * @throws IOException   if JAXB can't find the file
     */
    public ContractRepository unmarshall() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(ContractRepository.class);
        return (ContractRepository) context.createUnmarshaller()
                .unmarshal(new FileReader("./files/repo.xml"));
    }
}
