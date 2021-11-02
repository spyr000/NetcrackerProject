package contracts;

import person.Person;

import java.time.LocalDate;

public class WiredInternetContract extends Contract {

    private double connectionSpeed;

    public double getConnectionSpeed() {
        return connectionSpeed;
    }

    public void setConnectionSpeed(double connectionSpeed) {
        this.connectionSpeed = connectionSpeed;
    }

    public WiredInternetContract(int id, LocalDate startTime, LocalDate finishTime, int number, Person owner, double connectionSpeed) {
        super(id, startTime, finishTime, number, owner);
        this.connectionSpeed = connectionSpeed;
    }
}
