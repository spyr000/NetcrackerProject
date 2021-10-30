package contracts;

import person.Person;

import java.util.ArrayList;
import java.util.Date;

public class DigitalTelevisionContract extends Contract{
    private ArrayList<String> channelPackage;

    public ArrayList<String> getChannelPackage() {
        return channelPackage;
    }
    public void setChannelPackage(ArrayList<String> channelPackage) {
        this.channelPackage = channelPackage;
    }

    public DigitalTelevisionContract(int id, Date startTime, Date finishTime, int number, Person owner, ArrayList<String> channelPackage) {
        super(id, startTime, finishTime, number, owner);
        this.channelPackage = channelPackage;
    }
}
