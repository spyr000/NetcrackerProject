package contracts;

import person.Person;

import java.time.LocalDate;
import java.util.ArrayList;

public class DigitalTelevisionContract extends Contract {
    private ArrayList<String> channelPackage;

    public DigitalTelevisionContract(int id, LocalDate startTime, LocalDate finishTime, int number, Person owner, ArrayList<String> channelPackage) {
        super(id, startTime, finishTime, number, owner);
        this.channelPackage = channelPackage;
    }

    public ArrayList<String> getChannelPackage() {
        return channelPackage;
    }

    public void setChannelPackage(ArrayList<String> channelPackage) {
        this.channelPackage = channelPackage;
    }

    public int getChannelsAmount() {
        return channelPackage.size();
    }
}
