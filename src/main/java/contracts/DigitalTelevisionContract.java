package contracts;

import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import person.Person;
import xml.adapters.DigitalTelevisionContractAdapter;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * @author almtn
 */
@XmlRootElement
@XmlJavaTypeAdapter(DigitalTelevisionContractAdapter.class)
public class DigitalTelevisionContract extends Contract {

    private String[] channelPackage;

    /**
     * @param id             Contract ID
     * @param startTime      Start date of the contract
     * @param finishTime     Contract expiration date
     * @param number         Contract number
     * @param owner          Contract owner
     * @param channelPackage Package of channels
     */
    public DigitalTelevisionContract(int id, LocalDate startTime, LocalDate finishTime, String number, Person owner, String[] channelPackage) {
        super(id, startTime, finishTime, number, owner);
        this.channelPackage = channelPackage;
    }

    private DigitalTelevisionContract() {super();}

    /**
     * @return Package of channels
     */
    public String[] getChannelPackage() {
        return channelPackage;
    }

    /**
     * Sets package of channels
     *
     * @param channelPackage Package of channels
     */
    public void setChannelPackage(String[] channelPackage) {
        this.channelPackage = channelPackage;
    }

    /**
     * @return Package of channels Amount
     */
    public int getChannelsAmount() {
        return channelPackage.length;
    }

    @Override
    public String toString() {
        return super.toString() + " " + Arrays.toString(getChannelPackage());
    }
}
