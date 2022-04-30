package contracts;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import person.Person;
import xml.adapters.MobileContractAdapter;

import java.time.LocalDate;

/**
 * @author almtn
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlJavaTypeAdapter(MobileContractAdapter.class)
public class MobileContract extends Contract {
    private double minutesAmount;
    private int smsAmount;
    private double trafficGbAmount;

    /**
     * @param id              Contract ID
     * @param startTime       Start date of the contract
     * @param finishTime      Сontract expiration date
     * @param number          Contract number
     * @param owner           Contract owner
     * @param minutesAmount   Minutes amount
     * @param smsAmount       SMS amount
     * @param trafficGbAmount Traffic GigaBytes amount
     */
    public MobileContract(int id, LocalDate startTime, LocalDate finishTime, String number, Person owner, double minutesAmount, int smsAmount, double trafficGbAmount) {
        super(id, startTime, finishTime, number, owner);
        this.minutesAmount = minutesAmount;
        this.smsAmount = smsAmount;
        this.trafficGbAmount = trafficGbAmount;
    }

    private MobileContract() {
        super();
    }

    /**
     * @return Minutes amount
     */
    public double getMinutesAmount() {
        return minutesAmount;
    }

    /**
     * Sets minutes amount
     *
     * @param minutesAmount Minutes amount
     */
    public void setMinutesAmount(double minutesAmount) {
        this.minutesAmount = minutesAmount;
    }

    /**
     * @return SMS amount
     */
    public int getSmsAmount() {
        return smsAmount;
    }

    /**
     * Sets SMS amount
     *
     * @param smsAmount SMS amount
     */
    public void setSmsAmount(int smsAmount) {
        this.smsAmount = smsAmount;
    }

    /**
     * @return Traffic GigaBytes amount
     */
    public double getTrafficGbAmount() {
        return trafficGbAmount;
    }

    /**
     * Sets traffic GigaBytes amount
     *
     * @param trafficGbAmount Traffic GigaBytes amount
     */
    public void setTrafficGbAmount(double trafficGbAmount) {
        this.trafficGbAmount = trafficGbAmount;
    }

    @Override
    public String toString() {
        return super.toString() + " " + getMinutesAmount() + " " + getSmsAmount() + " " + getTrafficGbAmount();
    }
}
