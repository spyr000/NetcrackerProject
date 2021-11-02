package contracts;

import person.Person;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author almtn
 */
public class DigitalTelevisionContract extends Contract {
    private ArrayList<String> channelPackage;

    /**
     * @param id Contract ID
     * @param startTime Start date of the contract
     * @param finishTime Contract expiration date
     * @param number Contract number
     * @param owner Contract owner
     * @param channelPackage Package of channels
     */
    public DigitalTelevisionContract(int id, LocalDate startTime, LocalDate finishTime, int number, Person owner, ArrayList<String> channelPackage) {
        super(id, startTime, finishTime, number, owner);
        this.channelPackage = channelPackage;
    }

    /**
     * @return Package of channels
     */
    public ArrayList<String> getChannelPackage() {
        return channelPackage;
    }

    /**
     * Sets package of channels
     * @param channelPackage Package of channels
     */
    public void setChannelPackage(ArrayList<String> channelPackage) {
        this.channelPackage = channelPackage;
    }

    /**
     * @return Package of channels Amount
     */
    public int getChannelsAmount() {
        return channelPackage.size();
    }
}
