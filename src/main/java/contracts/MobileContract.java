package contracts;

import person.Person;

import java.util.Date;

public class MobileContract extends Contract{
    private double minutesAmount;
    private int smsAmount;
    private double trafficGbAmount;

    public double getMinutesAmount() {
        return minutesAmount;
    }
    public void setMinutesAmount(double minutesAmount) {
        this.minutesAmount = minutesAmount;
    }
    public int getSmsAmount() {
        return smsAmount;
    }
    public void setSmsAmount(int smsAmount) {
        this.smsAmount = smsAmount;
    }
    public double getTrafficGbAmount() {
        return trafficGbAmount;
    }
    public void setTrafficGbAmount(double trafficGbAmount) {
        this.trafficGbAmount = trafficGbAmount;
    }

    public MobileContract(int id, Date startTime, Date finishTime, int number, Person owner, double minutesAmount, int smsAmount, double trafficGbAmount) {
        super(id, startTime, finishTime, number, owner);
        this.minutesAmount = minutesAmount;
        this.smsAmount = smsAmount;
        this.trafficGbAmount = trafficGbAmount;
    }
}
