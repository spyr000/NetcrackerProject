package contracts;

import person.Person;

import java.util.Date;

public class WiredInternetContract extends Contract{

    private double connectionSpeed;

    public double getConnectionSpeed() {
        return connectionSpeed;
    }
    public void setConnectionSpeed(double connectionSpeed) {
        this.connectionSpeed = connectionSpeed;
    }

    public WiredInternetContract(int id, Date startTime, Date finishTime, int number, Person owner, double connectionSpeed) {
        super(id, startTime, finishTime, number, owner);
        this.connectionSpeed = connectionSpeed;
    }
}
